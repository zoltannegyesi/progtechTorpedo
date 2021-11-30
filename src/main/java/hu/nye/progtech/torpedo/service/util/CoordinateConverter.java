package hu.nye.progtech.torpedo.service.util;

import org.springframework.stereotype.Service;

/**
 * Component used to convert characters to numbers.
 */

@Service
public class CoordinateConverter {

    /**
     * Converts ASCII characters to numbers.
     *
     * @param character that is being checked.
     * @return integer.
     */

    public int checkCoordinate(char character) {
        // -65 and -49 -> to convert char to number -> ASCII table
        if (character >= 'A' && character <= 'J') {
            return (int) (character - 65);
        } else {
            return (int) (character - 49);
        }
    }
}
