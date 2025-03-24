# Scientific Calculator - Java Application

## Overview

This is a scientific calculator application built using Java. It provides various mathematical operations and a user-friendly interface for performing calculations. The project follows a structured directory format and includes source code, binaries, and a script to run the application.

## Project Structure

```
scientific-calculator/   
├── bin/
├── lib/  
├── src/   
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── calculator/
│       │           ├── logic/
│       │           │   └── ScientificCalculatorLogic.java
│       │           ├── ui/
│       │           │   ├── ButtonPanel.java
│       │           │   ├── CalculatorUI.java
│       │           │   └── Display.java
│       │           └── Main.java 
│       └── resources/
│          └── calculator.png
├── .gitignore    
├── run.sh          
```

## Files Description

- **src/main/java/com/calculator/logic/ScientificCalculatorLogic.java** - Implements various scientific and basic arithmetic operations.
- **src/main/java/com/calculator/ui/CalculatorUI.java** - The main class that initializes and runs the calculator's user interface.
- **src/main/java/com/calculator/ui/ButtonPanel.java** - Manages the button layout and actions for the calculator.
- **src/main/java/com/calculator/ui/Display.java** - Manages the calculator's display area.
- **src/main/java/com/calculator/Main.java** - The entry point of the application.
- **bin/** - Contains compiled `.class` files for execution.
- **lib/** - Contains any required external libraries or JAR files.
- **src/main/resources/calculator.png** - The icon or image used in the application.
- **run.sh** - A script to run the application from the terminal.
- **.gitignore** - Specifies files and directories that Git should ignore.

## Installation & Usage

### Prerequisites

- Java Development Kit (JDK) installed (Version 8 or higher)
- A terminal or command prompt



### Running the Application

Simply execute the application using:

```bash
./run.sh
```

## Features

- Basic Arithmetic Operations: Addition (`+`), Subtraction (`-`), Multiplication (`*`), Division (`/`)
- Scientific functions:
  - Exponents & Roots: Square (`x²`), Square root (`√`), Power (`xʸ`), Exponential (`10ˣ`)
  - Logarithmic Functions: Logarithm (`log`), Natural logarithm (`ln`)
  - Trigonometric Functions: Sine (`sin`), Cosine (`cos`), Tangent (`tan`)
  - Constants & Special Functions: Pi (`π`), Euler’s number (`e`)
  - Sign Operations: Negation (`±`)
- Memory functions:
  - Clear (`C`), Backspace (`Back`)
- User-friendly with well-organized buttons
- Command-line execution support for extended functionality

## Image

![Calculator Screenshot](src/main/resources/calculator.png)
