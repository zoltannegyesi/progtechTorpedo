package hu.nye.progtech.torpedo.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

/**
 * UI class to read from the standard input.
 */

@Service
public class UserInput {

    private final BufferedReader reader;


    public UserInput(BufferedReader reader) {
        this.reader = reader;
    }


    /**
     * Scans input and returns the read input.
     *
     * @return String.
     */
    public String scanInput() {
        String in = null;
        try {
            in = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error happened during the reading");
        }
        return in;
    }
}
