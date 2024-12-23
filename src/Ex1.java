public class Ex1 {

    // function to check if the string follows the valid format <number>b<base>
    public static boolean isNumber(String input) {
        if (input == null || input.isEmpty()) {
            return false; // input must not be null or empty
        }

        if (input.contains("b")) {
            String[] parts = input.split("b");
            if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                return false; // must have both number and base
            }

            String numberPart = parts[0]; // "AAbB"
            String basePart = parts[1];   // "11" or "B"

            try {
                // convert the base part to an integer,for letters like "A" for 10, "B" for 11, ..., "G" for 16
                int base = parseBase(basePart);
                if (base < 2 || base > 16) {
                    return false; // base must be between 2 and 16
                }

                // check if each character in the number part is valid for the given base
                for (char c : numberPart.toCharArray()) {
                    if (!isValidCharForBase(c, base)) {
                        return false; // invalid character for the given base
                    }
                }
                return true;
            } catch (NumberFormatException e) {
                return false; // base part must be a valid number or letter
            }
        } else {
            // assume the base is understood and not directly given
            // check if the number is only valid decimal characters
            return input.matches("[0-9]+");
        }
    }

    // function to parse a base part, allowing letters like "A" for 10, "B" for 11, ..., "G" for 16
    static int parseBase(String basePart) throws NumberFormatException {
        if (basePart.length() == 1 && Character.isLetter(basePart.charAt(0))) {
            return basePart.charAt(0) - 'A' + 10; // convert letters to numbers (A=10, B=11, ..., G=16)
        } else {
            return Integer.parseInt(basePart); // parse as a regular number
        }
    }

    // function to check if a character is valid in a given base
    static boolean isValidCharForBase(char c, int base) {
        if (Character.isDigit(c)) {
            return c - '0' < base; // Digits must be less than the base
        } else if (Character.isLetter(c)) {
            int value = c - 'A' + 10; // Convert letters A-F to values 10-15
            return value < base; // Letters must be less than the base
        }
        return false;
    }

    // function to convert a number from any base (2-16) to decimal (base 10)
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

    // function to convert a decimal number to any base (2-16)
    public static String int2Number(int number, int base) {
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

    // process the input numbers and perform calculations
    public static void processNumbers(String num1, String num2, int outputBase) {
        // validate the numbers
        if (!isNumber(num1)) {
            System.out.println("num1 = " + num1 + " is number: false , value: -1");
            System.out.println("ERR: num1 is in the wrong format! (" + num1 + ")");
            return;
        }

        if (!isNumber(num2)) {
            System.out.println("num2 = " + num2 + " is number: false , value: -1");
            System.out.println("ERR: num2 is in the wrong format! (" + num2 + ")");
            return;
        }

        // split the inputs into number and base
        String[] num1Parts = num1.split("b");
        String[] num2Parts = num2.split("b");

        // handle inputs without a base (assume base 10)
        int base1 = (num1Parts.length == 2) ? parseBase(num1Parts[1]) : 10;
        int value1 = convertToDecimal(num1Parts[0], base1);

        int base2 = (num2Parts.length == 2) ? parseBase(num2Parts[1]) : 10;
        int value2 = convertToDecimal(num2Parts[0], base2);

        // print validation and decimal value
        System.out.println("num1 = " + num1 + " is number: true , value: " + value1);
        System.out.println("num2 = " + num2 + " is number: true , value: " + value2);

        // all the  calculations
        int sum = value1 + value2;
        int product = value1 * value2;

        // convert results to the output base
        String sumStr = int2Number(sum, outputBase);
        String productStr = int2Number(product, outputBase);

        // output results format
        System.out.println(num1 + " + " + num2 + " = " + sumStr + "b" + outputBase);
        System.out.println(num1 + " * " + num2 + " = " + productStr + "b" + outputBase);

        // find the maximum value of the results
        int maxValue = Math.max(Math.max(value1, value2), Math.max(sum, product));
        String maxNumber = int2Number(maxValue, outputBase);
        System.out.println("Max number over [" + num1 + ", " + num2 + ", " + sumStr + "b" + outputBase + ", " + productStr + "b" + outputBase + "] is: " + maxNumber + "b" + outputBase);
    }

    public static int maxIndex(String[] arr) {
        int ans = 0;
        int index =0;
        int base1 , num;
        for (int i = 0; i < arr.length; i++) {
            String[] num1Parts = arr[i].split("b");
            base1 = (num1Parts.length == 2) ? parseBase(num1Parts[1]) : 10;
            num = convertToDecimal(num1Parts[0], base1);
            if(ans < num) {
                ans = num;
                index = i;
            }
        }
        System.out.println("the max is: " + ans + " and is index is: " + index);
        return index;
    }

}
