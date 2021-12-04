package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.ships.Ship;
import hu.nye.progtech.torpedo.model.ships.impl.*;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PutTest {
    private static final String PUT_COMMAND = "put";
    private static final String NOT_PUT_COMMAND = "not-put";

    private static final String INPUT = "Destroyer";

    private static final int SHIPS_REMAINING = 2;

   // private final GameState game;
    private static List<Ship> ships;
    private static List<Interaction> interactions;
   // private final ShipPutter shipPutter;
   // private final UserInput userInput;

    @Mock
    private UserInput userInput;


    private Put underTest;

    @BeforeEach
    public void setUp() {
        ships = new ArrayList<>();
        Destroyer destroyer = new Destroyer();
        destroyer.useShip();
        Submarine submarine = new Submarine();
        Cruiser cruiser = new Cruiser();
        ships.add(destroyer);
        ships.add(submarine);
        ships.add(cruiser);

        interactions = new ArrayList<>();
        interactions.add(new Exit(null, null));
       // interactions.add(new PrintTest(null, null));
        interactions.add(new Put(null, null, null, null, null));
        interactions.add(new PrintAiTable(null, null));
        Shoot shoot = new Shoot(null);
        shoot.setUsable(false);
        interactions.add(shoot);


        //gameState = new GameState(null, null);
       //TablePrinter tablePrinter = Mockito.mock(TablePrinter.class);
        underTest = new Put(null, null, null, null, null);
        //stepController = Mockito.mock(StepController.class);
    }

    @Test
    public void testShipsLeftShouldReturnTheNumberOfShipsRemaining() {
        // given in setup

        // when
        int result = underTest.shipsLeft(ships);

        // then
        assertEquals(SHIPS_REMAINING, result);
    }


    @Test
    public void testTurnOnShootShouldTurnOnShootInteraction() {
        // given in setup

        // when
        Interaction result = underTest.turnOnShoot(interactions);

        // then
        assertTrue(result.isUsable());

    }

    @Test
    public void testTurnOffPutShouldTurnOffPutInteraction() {
        // given in setup

        // when
        Interaction result = underTest.turnOffPut(interactions);

        // then
        assertFalse(result.isUsable());
    }

    /*
    @Test
    public void testProcessShouldReadInputAndCallPerformStepFromStepController() {
        // given
        StepController stepController = Mockito.mock(StepController.class);
        List<Ship>
        given(underTest.shipsLeft()).willReturn(SHIPS_REMAINING);
        given(userInput.scanInput()).willReturn(INPUT);
        given(underTest.useShip(ships, INPUT)).willReturn(true);

        // when
        underTest.process(null, stepController);

        // then
        verify(stepController).performStep();
    }*/


    @Test
    public void testIsEqualToCommandShouldReturnTrueWhenInputIsPut() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(PUT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsEqualToCommandShouldReturnFalseWhenInputIsNotPut() {
        // given in setup

        // when
        boolean result = underTest.isEqualToCommand(NOT_PUT_COMMAND);

        // then
        assertFalse(result);
    }


    @Test
    public void testSetUsableShouldSetUsableFieldOfPut() {
        // given in setup


        // when
        underTest.setUsable(false);

        // then
        assertFalse(underTest.isUsable());
    }

    @Test
    public void testGetNameShouldReturnExitCommandFieldOfPut() {
        // given in setup

        //when
        String result = underTest.getName();

        // then
        assertEquals(result, PUT_COMMAND);
    }
}
