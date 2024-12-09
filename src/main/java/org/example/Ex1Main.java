import java.util.Scanner;

public class Ex1Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the Number Converter and Calculator!");
        System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");

        while (true) {
            // Take input from user
            String num1 = scanner.nextLine().trim();

            if (num1.equalsIgnoreCase("quit")) {
                System.out.println("quitting now...");
                break;
            }

            // Check if the first input is valid
            if (!isValidNumber(num1)) {
                System.out.println("ERR: " + num1 + " is in the wrong format!");
                continue;  // Ask for the first number again
            }

            System.out.println("num1= " + num1 + " is number: " + Ex1.isValidFormat(num1) + ", value: " + getValue(num1));
            System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");

            String num2 = scanner.nextLine().trim();

            if (num2.equalsIgnoreCase("quit")) {
                System.out.println("quitting now...");
                break;
            }

            // Validate second input
            if (!isValidNumber(num2)) {
                System.out.println("ERR: " + num2 + " is in the wrong format!");
                continue;  // Ask for the second number again
            }

            System.out.println("num2= " + num2 + " is number: " + Ex1.isValidFormat(num2) + ", value: " + getValue(num2));
            System.out.println("Enter a base for output (a number [2,16]): ");

            // Get output base
            int outputBase = -1;
            try {
                outputBase = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("ERR: Invalid base input!");
                continue;
            }

            if (outputBase < 2 || outputBase > 16) {
                System.out.println("ERR: Base must be between 2 and 16!");
                continue;
            }

            // Perform calculations and display results
            Ex1.processNumbers(num1, num2, outputBase);

            System.out.println("\nEnter a string as number#1 (or \"quit\" to end the program): ");
        }

        scanner.close();
    }

    // Helper method to retrieve the value of the number (for display purposes)
    private static String getValue(String num) {
        if (!Ex1.isValidFormat(num)) {
            return "-1";
        }

        String[] parts = num.split("b");
        int base = parts.length == 1 ? 10 : Integer.parseInt(parts[1]);
        int value = Ex1.convertToDecimal(parts[0], base);
        return String.valueOf(value);
    }

    // Check if the input string is a valid number (with or without the 'b' format)
    private static boolean isValidNumber(String num) {
        if (num == null || num.trim().isEmpty()) {
            return false;
        }

        // If the number contains 'b', check the format <number>b<base>
        if (num.contains("b")) {
            return Ex1.isValidFormat(num);
        }

        // If there's no 'b', treat the number as a valid decimal
        try {
            Integer.parseInt(num);  // Try parsing it as a decimal number
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
