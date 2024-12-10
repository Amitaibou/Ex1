import java.util.Scanner;

public class Ex1Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Ex1 class solution:");
        System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");

        while (true) {
            // Take input for the first number
            String num1 = scanner.nextLine().trim();

            if (num1.equalsIgnoreCase("quit")) {
                System.out.println("Quitting now...");
                break;
            }

            // Validate and process the first number
            if (!Ex1.isValidFormat(num1)) {
                System.out.println("num1 = " + num1 + " is number: false , value: -1");
                System.out.println("ERR: num1 is in the wrong format! (" + num1 + ")");
                continue; // Ask for the first number again
            }

            // Parse the base and compute the decimal value for num1
            String[] num1Parts = num1.split("b");
            int base1 = (num1Parts.length == 2) ? Ex1.parseBase(num1Parts[1]) : 10;
            int value1 = Ex1.convertToDecimal(num1Parts[0], base1);

            // Print the immediate output for num1
            System.out.println("num1 = " + num1 + " is number: true , value: " + value1);

            // Take input for the second number
            System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
            String num2 = scanner.nextLine().trim();

            if (num2.equalsIgnoreCase("quit")) {
                System.out.println("Quitting now...");
                break;
            }

            // Validate and process the second number
            if (!Ex1.isValidFormat(num2)) {
                System.out.println("num2 = " + num2 + " is number: false , value: -1");
                System.out.println("ERR: num2 is in the wrong format! (" + num2 + ")");
                continue; // Ask for the second number again
            }

            // Parse the base and compute the decimal value for num2
            String[] num2Parts = num2.split("b");
            int base2 = (num2Parts.length == 2) ? Ex1.parseBase(num2Parts[1]) : 10;
            int value2 = Ex1.convertToDecimal(num2Parts[0], base2);

            // Print the immediate output for num2
            System.out.println("num2 = " + num2 + " is number: true , value: " + value2);

            // Take input for the output base
            System.out.println("Enter a base for output: (a number [2,16])");
            int outputBase;
            try {
                outputBase = Integer.parseInt(scanner.nextLine().trim());
                if (outputBase < 2 || outputBase > 16) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("ERR: Base must be between 2 and 16!");
                continue; // Ask for the base again
            }

            // Process and display results using Ex1 methods
            Ex1.processNumbers(num1, num2, outputBase);

            System.out.println("\nEnter a string as number#1 (or \"quit\" to end the program): ");
        }

        scanner.close();
    }
}
