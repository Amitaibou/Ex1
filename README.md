# Ex1 - # Ex1 - Number Formatting Converter and Calculator

## Abstract
 The goal of this project is to build a number formatting converter and calculator that can handle numbers in bases ranging from binary (base 2) to hexadecimal (base 16). The project includes operations like addition, multiplication, and finding the maximum number between different inputs.

The program accepts strings in the format `<number><b><base>`, such as `135bA`, `1001b2`, or `12345b6`, and performs the appropriate calculations based on the given base. It also handles invalid inputs by printing error messages.

---

## How to Run

1. Clone the repository:
  
   git clone <https://github.com/Amitaibou/Ex1.git>
   cd Ex1





Features:

Input Parsing: Validates and parses numbers in the format <number><b><base> where the base can range from 2 to 16.
Conversions: Converts numbers between bases and calculates their decimal values.
Operations: Performs addition, multiplication, and finds the maximum value among given inputs.
Edge Case Handling: Rejects invalid inputs like GbG, 123b, or 1234b11 and prints appropriate error messages.
Interactive Program: Allows users to interactively input numbers, choose a base, and see results.

Testing:

Testing was done using the Ex1Test.java class with JUnit.

The following aspects were tested:

Valid inputs in various bases ( 1b2, AAb11, FFb16).
Invalid inputs ( 1b, GbG, 10b17).
Edge cases like 0, single-character inputs, and large numbers.

Example of a passing test case:

assertTrue(Ex1.isValidFormat("AAb11"), "AAb11 should be valid");
assertEquals(120, Ex1.convertToDecimal("AA", 11), "AA in base 11 should be 120 in decimal");

Examples:

Here are some examples of how the program works:

Example 1 :

Ex1 class solution:
Enter a string as number#1 (or "quit" to end the program):
1001b2
num1= 1001b2 is number: true , value: 9
Enter a string as number#2 (or "quit" to end the program):
11b3
num2= 11b3 is number: true , value: 4
Enter a base for output: (a number [2,16])
10
1001b2 + 11b3 = 13
1001b2 * 11b3 = 36
Max number over [1001b2, 11b3, 13, 36] is: 36

Example 2 :

Ex1 class solution:
Enter a string as number#1 (or "quit" to end the program):
1
num1= 1 is number: true , value: 1
Enter a string as number#2 (or "quit" to end the program):
0b2
num2= 0b2 is number: true , value: 0
Enter a base for output: (a number [2,16])
2
1 + 0b2 = 1b2
1 * 0b2 = 0b2
Max number over [1, 0b2, 1b2, 0b2] is: 1b2

Example 3 :

Ex1 class solution:
Enter a string as number#1 (or "quit" to end the program):
1DbG
num1= 1DbG is number: true , value: 29
Enter a string as number#2 (or "quit" to end the program):
AAbB
num2= AAbB is number: true , value: 120
Enter a base for output: (a number [2,16])
2
1DbG + AAbB = 10010101b2
1DbG * AAbB = 110110011000b2
Max number over [1DbG, AAbB, 10010101b2, 110110011000b2] is: 110110011000b2

Notes :

The repository includes the following files:
Ex1.java: Core implementation of the functionality.
Ex1Test.java: JUnit tests to validate the implementation.
Ex1Main.java: Interactive program to demonstrate functionality.

Challenges Faced:

Handling Invalid Inputs: Ensuring the program gracefully handles edge cases like GbG, 1b, or missing bases was challenging but resolved with proper validation logic.
Base Conversions: Converting between different bases required careful handling of characters like A-F and ensuring no overflow occurs.
Testing: Writing thorough test cases for edge cases was time-consuming but essential for ensuring program correctness.

Conclusion:

This project helped me understand the fundamentals of function programming and testing. It was a great opportunity to apply what I learned in class to a real-world problem. If there are any questions about my implementation, feel free to reach out.

