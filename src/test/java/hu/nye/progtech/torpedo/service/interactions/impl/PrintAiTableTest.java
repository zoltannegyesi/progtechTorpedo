package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PrintAiTableTest {
    private static final String PRINTAITABLE_COMMAND = "aitable";
    private static final String NOT_PRINTAITABLE_COMMAND = "not-aitable";



    private PrintAiTable underTest;
    private GameState gameState;
    @Mock
    private StepController stepController;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, null, null);
        TablePrinter tablePrinter = Mockito.mock(TablePrinter.class);
        underTest = new PrintAiTable(tablePrinter, gameState);
        stepController = Mockito.mock(StepController.class);
    }


    @Test
    public void testProcessShouldCallPerformStepFromStepController() {
        // given in setup
        // a kiírás ki lett kommentelve a processben
        // when
        underTest.process(PRINTAITABLE_COMMAND, stepController);

        // then
        verify(stepController).performStep();
    }



    @Test
    public void testIsEqualToCommandShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(PRINTAITABLE_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsEqualToCommandShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(NOT_PRINTAITABLE_COMMAND);

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
        assertEquals(result, PRINTAITABLE_COMMAND);
    }
}
