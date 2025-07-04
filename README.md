# FooBarQuix

This project implements an algorithm that transforms a number (between 0 and 100) into a string based on specific rules.
The project is built using Maven, Java, and Spring Boot.

## Technogies

- Maven
- Java 21
- Spring Boot 3.5.0
- Spring Batch
- H2 Database
- Junit (tests)

## Algorithm Rules

The purpose of the algorithm is to transform a number into a string based on its divisibility by certain integers and the presence of specific digits within the number.
The transformations are as follows:

1. If the number is divisible by 3, append "FOO" to the result.
1. If the number is divisible by 5, append "BAR" to the result.

And then, for each digit in the number, analyzed from left to right: 
1. If the digit is '3', append "FOO" to the result.
1. If the digit is '5', append "BAR" to the result.
1. If the digit is '7', append "QUIX" to the result.

## Implementation

The project includes two implementations of the algorithm, an normal version, and an alternative one.

### `transformToFooBarQuix` (in the `FooBarQuixService` file)

This method processes the input number and constructs the output string based on the defined rules.
It uses a `StringBuilder` to accumulate the results.

### `transformToFooBarQuixAlternative` (in the `FooBarQuixService` file)

This method provides an alternative implementation using Java Streams.
It achieves the same functionality but utilizes a more functional programming approach.

## API Documentation

### Endpoint: GET `/runBatch`

This endpoint allows users to process a batch of numbers from a specified input file and generate an output file containing the transformed results using Spring Batch.

#### Query Parameters

- `inputFilePath` (required): The path to the input `.txt` file containing the numbers to be processed. Each number should be on a new line. The file should exist.
- `outputFilePath` (required): The path where the output file will be saved, containing the transformed results.
- `alternate` (optional): A boolean value (`true` or `false`) indicating whether to use the alternative implementation. Defaults to `false`.

#### Response

200 OK
```Txt
Batch job has been launched
```

### Endpoint: GET `/foobarquix`

This endpoint transforms a single integer number into a string based on the defined rules.

#### Query Parameters

- `number` (required): An integer between 1 and 100.

#### Response

200 OK
```JSON
{
  "inputNumber": 42,
  "transformedString": "FOO",
  "error": null
}
```

400 Bad Request
```JSON
{
  "inputNumber": -1,
  "transformedString": null,
  "error": "Number must be between 0 and 100"
}
```

### Endpoint: GET `/foobarquix-alt`

This endpoint transforms a single integer number into a string using the alternative implementation.

#### Query Parameters

- `number` (required): An integer between 1 and 100.

#### Response

200 OK
```JSON
{
  "inputNumber": 42,
  "transformedString": "FOO",
  "error": null
}
```

400 Bad Request
```JSON
{
  "inputNumber": -1,
  "transformedString": null,
  "error": "Number must be between 0 and 100"
}
```


## How to deploy

### Prerequisites

- Java 21
- Maven

### Setup

- Clone this git
- Build the project by running  `mvn clean package`
- In case of success, you'll have a .jar file located into your `target` folder
- Run the apps using `java -jar FooBarQuix-0.0.1-SNAPSHOT.jar`