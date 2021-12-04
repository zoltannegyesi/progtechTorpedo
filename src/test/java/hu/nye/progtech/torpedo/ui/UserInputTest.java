package hu.nye.progtech.torpedo.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserInputTest {

    private static final String INPUT = "input";

    @Mock
    private BufferedReader bufferedReader;

    private UserInput underTest;

    @BeforeEach
    public void setUp() {
        underTest = new UserInput(bufferedReader);
    }

    @Test
    public void testScanInputShouldReturnTheInputReadByBufferedReader() throws IOException {
        // given
        given(bufferedReader.readLine()).willReturn(INPUT);

        // when
        String result = underTest.scanInput();

        // then
        assertEquals(INPUT, result);
    }

    @Test
    public void testScanInputShouldReturnNullWhenBufferedReaderThrowsException() throws IOException {
        // given
        doThrow(IOException.class).when(bufferedReader).readLine();

        // when
        String result = underTest.scanInput();

        // then
        assertNull(result);
    }

}
