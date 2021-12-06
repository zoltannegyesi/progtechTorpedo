package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import hu.nye.progtech.torpedo.service.interactions.Shooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ShootTest {
    private static final String SHOOT_COMMAND = "shoot";
    private static final String NOT_SHOOT_COMMAND = "not-shoot";

    private Shoot underTest;

    private Shooter shooter;

    @BeforeEach
    public void setUp() {
        shooter = Mockito.mock(Shooter.class);
        underTest = new Shoot(shooter, null);
    }

    @Test
    public void testIsEqualToCommandShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(SHOOT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsEqualToCommandShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(NOT_SHOOT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldCallShootMethodFromShooter() {
        // given in setup

        // when
        underTest.process(null, null);

        // then
        verify(shooter).shoot();
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
        assertEquals(result, SHOOT_COMMAND);
    }

}
