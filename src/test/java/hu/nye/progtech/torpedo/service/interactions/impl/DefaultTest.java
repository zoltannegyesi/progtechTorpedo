package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import hu.nye.progtech.torpedo.service.game.StepController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DefaultTest {

    private static final String UNKNOWN_COMMAND = "Unknown command";

    @Mock
    private StepController stepController;

    private Default underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Default();
    }

    @Test
    public void testProcessShouldCallPerformStepFromStepController() {
        // given in setup

        // when
        underTest.process(null, stepController);

        // test
        verify(stepController).performStep();
    }

    @Test
    public void testIsEqualToCommandShouldReturnTrueEveryTime() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(UNKNOWN_COMMAND);

        // then
        assertTrue(result);
    }


    @Test
    public void testSetUsableShouldSetUsableFieldOfDefault() {
        // given in setup


        // when
        underTest.setUsable(false);

        // then
        assertFalse(underTest.isUsable());
    }

    @Test
    public void testGetNameShouldReturnUnknownCommandMessageFieldOfDefault() {
        // given in setup

        //when
        String result = underTest.getName();

        // then
        assertEquals(result, UNKNOWN_COMMAND);
    }
}
