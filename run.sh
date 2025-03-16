#!/bin/bash

# Set script to exit on any error
set -e

# Create bin directory if not exists
mkdir -p bin

# Compile Java files and store class files in 'bin' directory
javac -d bin src/*.java

# Run the program from the 'bin' directory
java -cp bin Main
