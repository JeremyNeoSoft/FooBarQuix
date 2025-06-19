package com.github.jeremyneosoft.foobarquix.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.jeremyneosoft.foobarquix.domain.ValidatedNumber;
import com.github.jeremyneosoft.foobarquix.service.FooBarQuixService;

@Configuration
@EnableBatchProcessing
public class FooBarQuixBatchConfiguration {

	private final FooBarQuixService fooBarQuixService;

	public FooBarQuixBatchConfiguration(FooBarQuixService fooBarQuixService) {
		this.fooBarQuixService = fooBarQuixService;
	}
	
	@Bean
    DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(
                new ClassPathResource("org/springframework/batch/core/schema-h2.sql")
        ));
        return initializer;
    }

	@Bean
	@StepScope
	FlatFileItemReader<String> reader(@Value("#{jobParameters['inputFilePath']}") String inputFilePath) {
		return new FlatFileItemReaderBuilder<String>()
				.name("numberItemReader")
				.resource(new FileSystemResource(inputFilePath))
				.lineMapper(new PassThroughLineMapper())
				.build();
	}

	@Bean
	@StepScope
	ItemProcessor<String, String> processor(@Value("#{jobParameters['alternate']}") String alternate) {
		boolean useAlternate = Boolean.parseBoolean(alternate);
		return item -> {
			try {
				int number = Integer.parseInt(item.trim());
				ValidatedNumber validatedNumber = new ValidatedNumber(number);
				StringBuilder result = new StringBuilder(item);
				result.append("\t\"");
				if (useAlternate) {
					result.append(fooBarQuixService.transformToFooBarQuixAlternative(validatedNumber));
	            } else {
	            	result.append(fooBarQuixService.transformToFooBarQuix(validatedNumber));
	            }
				result.append("\"");
				return result.toString();
			} catch (IllegalArgumentException e) {
				return item;
			}
		};
	}

	@Bean
	@StepScope
	FlatFileItemWriter<String> writer(@Value("#{jobParameters['outputFilePath']}") String outputFilePath) {
		return new FlatFileItemWriterBuilder<String>()
				.name("numberItemWriter")
				.resource(new FileSystemResource(outputFilePath))
				.lineAggregator(new PassThroughLineAggregator<>())
				.build();
	}

	@Bean
	Job createJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, Step createStep) {
		return new JobBuilder("fooBarQuixJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.flow(createStep)
				.end()
				.build();
	}

	@Bean
	Step createStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, ItemReader<String> reader, ItemProcessor<String, String> processor, ItemWriter<String> writer) {
		return new StepBuilder("fooBarQuixStep", jobRepository)
				.<String, String>chunk(10, platformTransactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

}
