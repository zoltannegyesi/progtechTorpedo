package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.Player;
import hu.nye.progtech.torpedo.service.game.GameController;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.repository.PlayerRepository;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class SaveTest {

    private static final String SAVE_COMMAND = "save";
    private static final String NOT_SAVE_COMMAND = "not-save";

    private Save underTest;
    private GameState gameState;
    private StepController stepController;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, null, null);
        underTest = new Save();
        stepController = Mockito.mock(StepController.class);
    }

    @Test
    public void testIsEqualToCommandShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(SAVE_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsEqualToCommandShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(NOT_SAVE_COMMAND);

        // then
        assertFalse(result);
    }
/*
    @Test
    public void testProcessShouldShouldCallStepControllerPerformStep() {
        // given
        Player player = Mockito.mock(Player.class);
        PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
        PlayerRepository spyRepo = spy(playerRepository);

        // when
        spyRepo.save(player);
        // then
        verify(spyRepo).save(player);
        verify(stepController).performStep();
    }
*/
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
        assertEquals(result, SAVE_COMMAND);
    }
}
