# Bingo 90 Ticket Generator

## Overview

This application generates Bingo 90 tickets in strips of six tickets. A full strip ensures that every number from 1 to 90 appears exactly once, guaranteeing that players can mark off at least one number per call.

## Features

- Generates Bingo strips, each containing six tickets.
- Each ticket follows the standard Bingo 90 rules:
    - 9 columns and 3 rows.
    - Each row contains exactly 5 numbers and 4 blank spaces.
    - Each column contains numbers from a predefined range (e.g., column 1: 1-9, column 2: 10-19, ..., column 9: 80-90).
    - No duplicate numbers across a strip.
    - Numbers are sorted in ascending order within each column.
- High-performance implementation capable of generating 10,000 strips in under 1 second.
- Extensive unit tests to validate correctness and performance.

## How to Run

### Prerequisites

- Java 11+
- Maven

### Build and Run

1. Clone the repository:
   ```sh
   git clone https://github.com/jeny83/lindar-bingo-app.git
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn exec:java -Dexec.mainClass="com.lindar.Main"
   ```

## Algorithm Explanation

1. **Number Distribution**

    - Numbers 1-90 are divided into 9 column groups (1-9, 10-19, etc.).
    - Each number is placed into the correct column and randomly assigned to one of the six tickets.

2. **Row and Column Constraints**

    - Each row must contain exactly 5 numbers and 4 blank spaces.
    - Each column must have at least one and at most three numbers.
    - Numbers are sorted in ascending order within each column.

3. **Randomization**

    - Numbers are shuffled before assignment to avoid predictable patterns.
    - The final ticket structure adheres to Bingo 90 rules.

## Test Coverage

This project includes a comprehensive test suite using JUnit 5. Tests include:

- **Ticket Structure Validation**: Ensures each ticket has 9 columns and 3 rows.
- **Number Distribution**: Verifies that numbers are within their expected range per column.
- **No Duplicates**: Ensures all numbers 1-90 appear exactly once in a strip.
- **Row Composition**: Checks that each row contains exactly 5 numbers and 4 blank spaces.
- **Performance Tests**: Confirms that 10,000 strips can be generated in under 1 second.

To run the tests:

```sh
mvn test
```
