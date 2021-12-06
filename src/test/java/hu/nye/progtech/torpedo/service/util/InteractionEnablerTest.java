package hu.nye.progtech.torpedo.service.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.service.interactions.impl.Exit;
import hu.nye.progtech.torpedo.service.interactions.impl.Put;
import hu.nye.progtech.torpedo.service.interactions.impl.Shoot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InteractionEnablerTest {

    InteractionEnabler underTest;

    private static List<Interaction> interactions;

    @BeforeEach
    public void setUp() {
        underTest = new InteractionEnabler();
        interactions = new ArrayList<>();
        Interaction exit = new Exit(null);
        Interaction shoot = new Shoot(null, null);
        interactions.add(exit);
        interactions.add(shoot);
    }

    @Test
    public void testDisablePutShouldReturnPutOperationAndDisableIt() {
        // given
        Interaction put = new Put( null, null,null, null, null, null);
        Interaction put2 = new Put(  null, null, null, null, null, null);
        put2.setUsable(false);
        // when
        Interaction result = underTest.disablePut(interactions, put);

        // then
        assertEquals(result.getName(), put2.getName());
        assertFalse(result.isUsable());
    }

    @Test
    public void testDisablePutShouldReturnShootOperationAndEnableIt() {
        // given
        Interaction shoot = new Shoot(null, null);
        shoot.setUsable(true);
        // when
        Interaction result = underTest.enableShoot(interactions);

        // then
        assertEquals(result.getName(), shoot.getName());
        assertTrue(result.isUsable());
    }
}
