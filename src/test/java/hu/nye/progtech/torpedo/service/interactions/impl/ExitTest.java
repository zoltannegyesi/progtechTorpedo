package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
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
    private StepController stepController;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, null);
        tablePrinter = Mockito.mock(TablePrinter.class);
        underTest = new Exit(tablePrinter, gameState);
        stepController = Mockito.mock(StepController.class);
    }

    @Test
    public void testIsEqualToCommandShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(EXIT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsEqualToCommandShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(NOT_EXIT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldChangeShouldExitFieldOfGameState() {
        // given in setup

        // when
        underTest.process(EXIT_COMMAND, stepController);

        // then
        assertTrue(gameState.isShouldExit());
    }

    @Test
    public void testSetUsableShouldSetUsableFieldOfExit() {
        // given in setup


        // when
        underTest.setUsable(false);

        // then
        assertFalse(underTest.isUsable());
    }

    @Test
    public void testGetNameShouldReturnExitCommandFieldOfExit() {
        // given in setup

        //when
        String result = underTest.getName();

        // then
        assertEquals(result, EXIT_COMMAND);
    }


}
