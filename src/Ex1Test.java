import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ex1Test {

    @Test
    void testIsValidFormat() {
        // Valid cases
        assertTrue(Ex1.isValidFormat("1b2"), "1b2 should be valid");
        assertTrue(Ex1.isValidFormat("AAb11"), "AAb11 should be valid");
        assertTrue(Ex1.isValidFormat("0b10"), "0b10 should be valid");
        assertTrue(Ex1.isValidFormat("FFb16"), "FFb16 should be valid");

        // Invalid cases
        assertFalse(Ex1.isValidFormat("1b"), "1b should be invalid (missing base)");
        assertFalse(Ex1.isValidFormat("GbG"), "GbG should be invalid (base is not numeric or valid letter)");
        assertFalse(Ex1.isValidFormat("10b17"), "10b17 should be invalid (base > 16)");
        assertFalse(Ex1.isValidFormat(""), "Empty string should be invalid");
    }

    @Test
    void testConvertToDecimal() {
        // Valid conversions
        assertEquals(1, Ex1.convertToDecimal("1", 2), "1 in base 2 should be 1 in decimal");
        assertEquals(10, Ex1.convertToDecimal("A", 11), "A in base 11 should be 10 in decimal");
        assertEquals(255, Ex1.convertToDecimal("FF", 16), "FF in base 16 should be 255 in decimal");
        assertEquals(120, Ex1.convertToDecimal("AA", 11), "AA in base 11 should be 120 in decimal");

        // Edge cases
        assertEquals(0, Ex1.convertToDecimal("0", 10), "0 in any base should be 0 in decimal");
    }

    @Test
    void testConvertFromDecimal() {
        // Valid conversions
        assertEquals("1", Ex1.convertFromDecimal(1, 2), "1 in decimal to base 2 should be 1");
        assertEquals("A", Ex1.convertFromDecimal(10, 11), "10 in decimal to base 11 should be A");
        assertEquals("FF", Ex1.convertFromDecimal(255, 16), "255 in decimal to base 16 should be FF");
        assertEquals("AA", Ex1.convertFromDecimal(120, 11), "120 in decimal to base 11 should be AA");

        // Edge cases
        assertEquals("0", Ex1.convertFromDecimal(0, 10), "0 in decimal to any base should be 0");
    }

    @Test
    void testProcessNumbers() {
        // Valid inputs
        Ex1.processNumbers("AAb11", "10b11", 10);
        Ex1.processNumbers("FFb16", "1b16", 10);
        Ex1.processNumbers("1b2", "0b2", 2);

        // Invalid inputs
        Ex1.processNumbers("1b", "10b11", 10);
        Ex1.processNumbers("AAb17", "10b11", 10);
        Ex1.processNumbers("AAb11", "GbG", 10);
    }
}
