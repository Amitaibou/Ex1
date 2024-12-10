public class Ex1 {

    // Function to check if the string follows the valid format <number>b<base>
    public static boolean isValidFormat(String input) {
        if (input == null || input.isEmpty()) {
            return false; // Input must not be null or empty
        }

        if (input.contains("b")) {
            String[] parts = input.split("b");
            if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                return false; // Must have both number and base
            }

            String numberPart = parts[0]; // e.g., "AAbB"
            String basePart = parts[1];   // e.g., "11" or "B"

            try {
                // Convert the base part to an integer, handling letters like "A" for 10, "B" for 11, ..., "G" for 16
                int base = parseBase(basePart);
                if (base < 2 || base > 16) {
                    return false; // Base must be between 2 and 16
                }

                // Check if each character in the number part is valid for the given base
                for (char c : numberPart.toCharArray()) {
                    if (!isValidCharForBase(c, base)) {
                        return false; // Invalid character for the given base
                    }
                }
                return true;
            } catch (NumberFormatException e) {
                return false; // Base part must be a valid number or letter
            }
        } else {
            // Assume it's in the format where the base is implicit
            // Check if the number contains only valid decimal characters
            return input.matches("[0-9]+");
        }
    }

    // Function to parse a base part, allowing letters like "A" for 10, "B" for 11, ..., "G" for 16
    static int parseBase(String basePart) throws NumberFormatException {
        if (basePart.length() == 1 && Character.isLetter(basePart.charAt(0))) {
            return basePart.charAt(0) - 'A' + 10; // Convert letters to numbers (A=10, B=11, ..., G=16)
        } else {
            return Integer.parseInt(basePart); // Parse as a regular number
        }
    }

    // Function to check if a character is valid in a given base
    private static boolean isValidCharForBase(char c, int base) {
        if (Character.isDigit(c)) {
            return c - '0' < base; // Digits must be less than the base
        } else if (Character.isLetter(c)) {
            int value = c - 'A' + 10; // Convert letters A-F to values 10-15
            return value < base; // Letters must be less than the base
        }
        return false;
    }

    // Function to convert a number from any base (2-16) to decimal (base 10)
    public static int convertToDecimal(String number, int base) {
        int result = 0;
        for (char c : number.toCharArray()) {
            result *= base; // Multiply the current result by the base
            if (Character.isDigit(c)) {
                result += c - '0'; // Add the numeric value of the digit
            } else if (Character.isLetter(c)) {
                result += c - 'A' + 10; // Add the numeric value of the letter (A=10, B=11, ...)
            }
        }
        return result;
    }

    // Function to convert a decimal number to any base (2-16)
    public static String convertFromDecimal(int number, int base) {
        if (number == 0) {
            return "0";
        }

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

    // Process the input numbers and perform calculations
    public static void processNumbers(String num1, String num2, int outputBase) {
        // Validate the numbers
        if (!isValidFormat(num1)) {
            System.out.println("num1 = " + num1 + " is number: false , value: -1");
            System.out.println("ERR: num1 is in the wrong format! (" + num1 + ")");
            return;
        }

        if (!isValidFormat(num2)) {
            System.out.println("num2 = " + num2 + " is number: false , value: -1");
            System.out.println("ERR: num2 is in the wrong format! (" + num2 + ")");
            return;
        }

        // Split the inputs into number and base
        String[] num1Parts = num1.split("b");
        String[] num2Parts = num2.split("b");

        // Handle inputs without a base (assume base 10)
        int base1 = (num1Parts.length == 2) ? parseBase(num1Parts[1]) : 10;
        int value1 = convertToDecimal(num1Parts[0], base1);

        int base2 = (num2Parts.length == 2) ? parseBase(num2Parts[1]) : 10;
        int value2 = convertToDecimal(num2Parts[0], base2);

        // Print validation and decimal value
        System.out.println("num1 = " + num1 + " is number: true , value: " + value1);
        System.out.println("num2 = " + num2 + " is number: true , value: " + value2);

        // Perform calculations
        int sum = value1 + value2;
        int product = value1 * value2;

        // Convert results to the output base
        String sumStr = convertFromDecimal(sum, outputBase);
        String productStr = convertFromDecimal(product, outputBase);

        // Output results in the specified format
        System.out.println(num1 + " + " + num2 + " = " + sumStr + "b" + outputBase);
        System.out.println(num1 + " * " + num2 + " = " + productStr + "b" + outputBase);

        // Find the maximum value among the results
        int maxValue = Math.max(Math.max(value1, value2), Math.max(sum, product));
        String maxNumber = convertFromDecimal(maxValue, outputBase);
        System.out.println("Max number over [" + num1 + ", " + num2 + ", " + sumStr + "b" + outputBase + ", " + productStr + "b" + outputBase + "] is: " + maxNumber + "b" + outputBase);
    }

}
