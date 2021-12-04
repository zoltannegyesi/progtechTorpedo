package hu.nye.progtech.torpedo.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordinateConverterTest {

    private static final char ALPHABET = 'C';
    private static final int ALPHABET_ASCII = 2;

    private static final char NUMBER = '4';
    private static final int NUMBER_ASCII = 3;

    private CoordinateConverter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CoordinateConverter();
    }

    @Test
    public void testCheckCoordinateShouldReturnTheGivenAlphabetsIntValue() {
        // given

        // when
        int result = underTest.checkCoordinate(ALPHABET);

        // then
        assertEquals(result, ALPHABET_ASCII);
    }

    @Test
    public void testCheckCoordinateShouldReturnTheGivenNumbersIntValue() {
        // given

        // when
        int result = underTest.checkCoordinate(NUMBER);

        // then
        assertEquals(result, NUMBER_ASCII);
    }
}
