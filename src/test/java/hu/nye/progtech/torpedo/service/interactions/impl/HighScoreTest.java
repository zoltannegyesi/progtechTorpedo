package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HighScoreTest {
    private static final String HIGH_COMMAND = "highscore";
    private static final String NOT_HIGH_COMMAND = "not-highscore";

    private HighScore underTest;


    @BeforeEach
    public void setUp() {
        underTest = new HighScore();
    }

    @Test
    public void testIsEqualToCommandShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(HIGH_COMMAND);

        // then
        assertTrue(result);
    }


    @Test
    public void testIsEqualToCommandShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(NOT_HIGH_COMMAND);

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

    /*
    @Test
    public void testProcessShouldReturnCallStepController() {
        // given
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName("asd");
        p1.setWins(2L);
        p2.setName("test");
        p2.setWins(3L);
        List<Player> players = List.of(p1, p2);

        PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);

        when(playerRepository.findAll()).thenReturn(players);

        // when
        underTest.process(null, null);

        // then
        verify(playerRepository).findAll();
        verifyNoInteractions(playerRepository);
    }

     */

    @Test
    public void testGetNameShouldReturnExitCommandFieldOfExit() {
        // given in setup

        //when
        String result = underTest.getName();

        // then
        assertEquals(result, HIGH_COMMAND);
    }
}
