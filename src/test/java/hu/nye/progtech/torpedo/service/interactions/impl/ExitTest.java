package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.FatalBeanException;

/**
 * Unit tests for {@link Exit}
 */

public class ExitTest {

    private static final String EXIT_COMMAND = "exit";
    private static final String NOT_EXIT_COMMAND = "not-exit";

    private Exit underTest;
    private GameState gameState;
    private TablePrinter tablePrinter;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, null);
        tablePrinter = Mockito.mock(TablePrinter.class);
        underTest = new Exit(tablePrinter, gameState);
    }

    @Test
    public void testIsEqualToCommandShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(EXIT_COMMAND);

        // then
        assertTrue(result);
    }


}
