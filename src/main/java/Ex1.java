import java.util.ArrayList;
import java.util.List;

public class Ex1 {

    // Function to check if the string follows the valid format <number>b<base>
    public static boolean isValidFormat(String input) {
        if (input == null || input.isEmpty()) return false;

        // If it contains 'b', check for the <number>b<base> format
        if (input.contains("b")) {
            String[] parts = input.split("b");
            if (parts.length != 2) return false;

            String numberPart = parts[0];
            String basePart = parts[1];

            try {
                int base = Integer.parseInt(basePart);
                if (base < 2 || base > 16) return false;

                // Check if number fits the base
                for (char c : numberPart.toCharArray()) {
                    if (!isValidCharForBase(c, base)) {
                        return false;
                    }
                }
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            // If there is no 'b', consider it a valid decimal number
            return input.matches("[0-9]+"); // Only digits, no letters or special chars
        }
    }

    // Function to check if a character is valid in a given base
    private static boolean isValidCharForBase(char c, int base) {
        if (Character.isDigit(c)) {
            return c - '0' < base;
        } else if (Character.isLetter(c)) {
            return (c >= 'A' && c <= 'F') && base >= 16;
        }
        return false;
    }

    // Function to convert a number from any base (2-16) to decimal (base 10)
    public static int convertToDecimal(String number, int base) {
        int result = 0;
        for (char c : number.toCharArray()) {
            result *= base;
            if (Character.isDigit(c)) {
                result += c - '0';
            } else if (Character.isLetter(c)) {
                result += c - 'A' + 10;
            }
        }
        return result;
    }

    // Function to convert a decimal (base 10) number to any base (2-16)
    public static String convertFromDecimal(int number, int base) {
        if (number == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int digit = number % base;
            if (digit < 10) {
                sb.append((char) ('0' + digit));
            } else {
                sb.append((char) ('A' + digit - 10));
            }
            number /= base;
        }
        return sb.reverse().toString();
    }

    // Main logic to perform arithmetic and output results
    public static void processNumbers(String num1, String num2, int outputBase) {
        // Validate the numbers
        boolean validNum1 = isValidFormat(num1);
        boolean validNum2 = isValidFormat(num2);
        if (!validNum1) {
            System.out.println("ERR: " + num1 + " is in the wrong format!");
        }
        if (!validNum2) {
            System.out.println("ERR: " + num2 + " is in the wrong format!");
        }

        if (!validNum1 || !validNum2) return; // Exit if either number is invalid

        // Process num1
        String[] num1Parts = num1.split("b");
        int base1 = num1Parts.length == 2 ? Integer.parseInt(num1Parts[1]) : 10; // Default to base 10 if no base specified
        int value1 = convertToDecimal(num1Parts[0], base1);
        String num1Validity = validNum1 ? "true" : "false";

        // Process num2
        String[] num2Parts = num2.split("b");
        int base2 = num2Parts.length == 2 ? Integer.parseInt(num2Parts[1]) : 10; // Default to base 10 if no base specified
        int value2 = convertToDecimal(num2Parts[0], base2);
        String num2Validity = validNum2 ? "true" : "false";

        // Print the validity and decimal values
        System.out.println("num1= " + num1 + " is number: " + num1Validity + " , value: " + value1);
        System.out.println("num2= " + num2 + " is number: " + num2Validity + " , value: " + value2);

        // Perform calculations
        int sum = value1 + value2;
        int product = value1 * value2;

        // Convert the result to the desired base
        String sumStr = convertFromDecimal(sum, outputBase);
        String productStr = convertFromDecimal(product, outputBase);

        // Print the result of addition and multiplication
        System.out.println(num1 + " + " + num2 + " = " + sumStr + "b" + outputBase);
        System.out.println(num1 + " * " + num2 + " = " + productStr + "b" + outputBase);

        // Store all involved numbers (inputs + results)
        List<String> numbers = new ArrayList<>();
        numbers.add(num1);
        numbers.add(num2);
        numbers.add(sumStr + "b" + outputBase);
        numbers.add(productStr + "b" + outputBase);

        // Convert all numbers to decimal for comparison
        List<Integer> decimalValues = new ArrayList<>();
        decimalValues.add(value1);
        decimalValues.add(value2);
        decimalValues.add(convertToDecimal(sumStr, outputBase));
        decimalValues.add(convertToDecimal(productStr, outputBase));

        // Find the maximum number in decimal, then convert it back to the output base
        int maxDecimalValue = decimalValues.stream().max(Integer::compare).get();
        String maxNumber = convertFromDecimal(maxDecimalValue, outputBase);

        // Print the max number involved
        System.out.println("Max number over " + numbers + " is: " + maxNumber);
    }

    public static void main(String[] args) {
        // Example input (you will modify this to accept user input in your final version)
        String num1 = "12";  // Decimal 12
        String num2 = "10011b2"; // Base 2, value 19
        int outputBase = 16;

        processNumbers(num1, num2, outputBase);
    }
}
