import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ex1Test {

    @Test
    void testIsValidFormat() {
        // Valid cases
        assertTrue(Ex1.isNumber("1b2"), "1b2 should be valid");
        assertTrue(Ex1.isNumber("AAb11"), "AAb11 should be valid");
        assertTrue(Ex1.isNumber("0b10"), "0b10 should be valid");
        assertTrue(Ex1.isNumber("FFb16"), "FFb16 should be valid");

        // Invalid cases
        assertFalse(Ex1.isNumber("1b"), "1b should be invalid (missing base)");
        assertFalse(Ex1.isNumber("GbG"), "GbG should be invalid (base is not numeric or valid letter)");
        assertFalse(Ex1.isNumber("10b17"), "10b17 should be invalid (base > 16)");
        assertFalse(Ex1.isNumber(""), "Empty string should be invalid");
    }
    // Test for valid characters in base 2
    @Test
    void testIsValidCharForBaseBase2() {
        assertTrue(Ex1.isValidCharForBase('0', 2), "0 should be valid in base 2");
        assertTrue(Ex1.isValidCharForBase('1', 2), "1 should be valid in base 2");
        assertFalse(Ex1.isValidCharForBase('2', 2), "2 should be invalid in base 2");
        assertFalse(Ex1.isValidCharForBase('A', 2), "A should be invalid in base 2");
    }

    // Test for valid characters in base 10
    @Test
    void testIsValidCharForBaseBase10() {
        assertTrue(Ex1.isValidCharForBase('0', 10), "0 should be valid in base 10");
        assertTrue(Ex1.isValidCharForBase('5', 10), "5 should be valid in base 10");
        assertFalse(Ex1.isValidCharForBase('A', 10), "A should be invalid in base 10");
        assertFalse(Ex1.isValidCharForBase('G', 10), "G should be invalid in base 10");
    }

    // Test for valid characters in base 16
    @Test
    void testIsValidCharForBaseBase16() {
        assertTrue(Ex1.isValidCharForBase('0', 16), "0 should be valid in base 16");
        assertTrue(Ex1.isValidCharForBase('A', 16), "A should be valid in base 16");
        assertTrue(Ex1.isValidCharForBase('F', 16), "F should be valid in base 16");
        assertFalse(Ex1.isValidCharForBase('G', 16), "G should be invalid in base 16");
        assertFalse(Ex1.isValidCharForBase('Z', 16), "Z should be invalid in base 16");
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
        assertEquals("1", Ex1.int2Number(1, 2), "1 in decimal to base 2 should be 1");
        assertEquals("A", Ex1.int2Number(10, 11), "10 in decimal to base 11 should be A");
        assertEquals("FF", Ex1.int2Number(255, 16), "255 in decimal to base 16 should be FF");
        assertEquals("AA", Ex1.int2Number(120, 11), "120 in decimal to base 11 should be AA");

        // Edge cases
        assertEquals("0", Ex1.int2Number(0, 10), "0 in decimal to any base should be 0");
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
    @Test
    void maxIndexTest() {
        // Test where the max value is at index 2
        assertEquals(2, Ex1.maxIndex(new String[]{"AAb11", "10b11", "FFb16", "1b16"}), "The index of the max value should be 2");

        // Test when the max value is at the beginning of the array (index 0)
        assertEquals(0, Ex1.maxIndex(new String[]{"FFb16", "1b16", "AAb11", "10b11"}), "The index of the max value should be 0");

        // Test when the max value is at the end of the array (index 3)
        assertEquals(3, Ex1.maxIndex(new String[]{"10b11", "AAb11", "1b16", "FFb16"}), "The index of the max value should be 3");

        // Test with all values being equal (should return the first index)
        assertEquals(0, Ex1.maxIndex(new String[]{"1b2", "1b2", "1b2", "1b2"}), "The index of the max value should be 0");

        /* Ex1.maxIndex(new String[]{"AAb11", "10b11","FFb16", "1b16"});
        Ex1.maxIndex(new String[]{"FFb16", "1b16"});
        Ex1.maxIndex(new String[]{"1b2", "0b2"});

        // Invalid inputs
        Ex1.maxIndex(new String[]{"1b", "10b11","1b2", "0b2"});
        Ex1.maxIndex(new String[]{"AAb17", "10b11"});
        Ex1.maxIndex(new String[]{"AAb11", "GbG"});*/
    }
}
