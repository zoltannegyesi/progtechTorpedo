package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PrintTest {

    private static final String PRINT_COMMAND = "print";
    private static final String NOT_PRINT_COMMAND = "not-print";

    private Print underTest;
    private GameState gameState;
    private StepController stepController;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, null);
        TablePrinter tablePrinter = Mockito.mock(TablePrinter.class);
        underTest = new Print(null, null);
        stepController = Mockito.mock(StepController.class);
    }

    @Test
    public void testIsEqualToCommandShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(PRINT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsEqualToCommandShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(NOT_PRINT_COMMAND);

        // then
        assertFalse(result);
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
        assertEquals(result, PRINT_COMMAND);
    }
}