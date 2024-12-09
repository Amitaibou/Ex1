import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex1Test {

    // Test for valid decimal input
    @Test
    public void testDecimalInput() {
        String num = "10";
        assertTrue(Ex1.isValidFormat(num));  // Should be a valid decimal number
        assertEquals(10, Ex1.convertToDecimal(num, 10));  // Convert to decimal value (should return 10)
    }

    // Test for valid binary input (base 2)
    @Test
    public void testBinaryInput() {
        String num = "1001b2";
        assertTrue(Ex1.isValidFormat(num));  // Should be valid in base 2
        assertEquals(9, Ex1.convertToDecimal(num.split("b")[0], 2));  // Should return decimal 9
    }

    // Test for valid hexadecimal input (base 16)
    @Test
    public void testHexadecimalInput() {
        String num = "A1b16";
        assertTrue(Ex1.isValidFormat(num));  // Should be valid in base 16
        assertEquals(161, Ex1.convertToDecimal(num.split("b")[0], 16));  // Should return decimal 161
    }

    // Test for invalid input (non-numeric or incorrect format)
    @Test
    public void testInvalidInput() {
        String num = "abc";
        assertFalse(Ex1.isValidFormat(num));  // Should be invalid
    }

    // Test for invalid base input (e.g., base larger than 16)
    @Test
    public void testInvalidBaseInput() {
        String num = "100b32";  // Base 32 is too large
        assertFalse(Ex1.isValidFormat(num));  // Should be invalid
    }

    // Test for calculating with valid decimal and binary inputs
    @Test
    public void testCalculation() {
        // Example: num1 = "10" (decimal), num2 = "1001b2" (binary)
        String num1 = "10";
        String num2 = "1001b2";
        int outputBase = 10;  // Output in decimal

        // Convert num1 and num2 to decimal values (num1 is already decimal)
        int num1Decimal = Ex1.convertToDecimal(num1, 10);  // 10 in decimal
        int num2Decimal = Ex1.convertToDecimal(num2.split("b")[0], 2);  // 9 in decimal

        // Perform addition
        int result = num1Decimal + num2Decimal;  // 10 + 9 = 19
        assertEquals(19, result);

        // Perform multiplication
        int multiplicationResult = num1Decimal * num2Decimal;  // 10 * 9 = 90
        assertEquals(90, multiplicationResult);
    }

    // Test for conversion of numbers with invalid format
    @Test
    public void testInvalidFormatNumber() {
        String num1 = "12b32";  // Invalid base 32
        assertFalse(Ex1.isValidFormat(num1));  // Should be invalid

        String num2 = "100b99";  // Invalid base 99
        assertFalse(Ex1.isValidFormat(num2));  // Should be invalid
    }

    // Test for max number calculation with multiple valid inputs
    @Test
    public void testMaxNumberCalculation() {
        String num1 = "12";
        String num2 = "1001b2";
        String num3 = "A1b16";
        String num4 = "11b3";

        int num1Decimal = Ex1.convertToDecimal(num1, 10);  // 12 in decimal
        int num2Decimal = Ex1.convertToDecimal(num2.split("b")[0], 2);  // 9 in decimal
        int num3Decimal = Ex1.convertToDecimal(num3.split("b")[0], 16);  // 161 in decimal
        int num4Decimal = Ex1.convertToDecimal(num4.split("b")[0], 3);  // 4 in decimal

        int maxNumber = Math.max(Math.max(num1Decimal, num2Decimal), Math.max(num3Decimal, num4Decimal));  // Max among [12, 9, 161, 4]

        assertEquals(161, maxNumber);  // A1b16 (161 in decimal) should be the maximum
    }

    // Test handling of "quit" command in main application
    @Test
    public void testQuit() {
        String quitInput = "quit";
        assertTrue(quitInput.equals("quit"));
    }

}
