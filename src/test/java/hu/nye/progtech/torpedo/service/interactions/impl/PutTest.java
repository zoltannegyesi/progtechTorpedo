package hu.nye.progtech.torpedo.service.interactions.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.ships.Ship;
import hu.nye.progtech.torpedo.model.ships.impl.Cruiser;
import hu.nye.progtech.torpedo.model.ships.impl.Destroyer;
import hu.nye.progtech.torpedo.model.ships.impl.Submarine;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.service.interactions.ShipPutter;
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
        Submarine submarine = new Submarine();
        Cruiser cruiser = new Cruiser();
        ships.add(destroyer);
        ships.add(submarine);
        ships.add(cruiser);

        interactions = new ArrayList<>();
        interactions.add(new Exit(null));
        interactions.add(new Put(null, null,  null, null, null, null));
        interactions.add(new PrintAiTable(null, null));
        Shoot shoot = new Shoot(null, null);
        shoot.setUsable(false);
        interactions.add(shoot);


        //gameState = new GameState(null, null);
       //TablePrinter tablePrinter = Mockito.mock(TablePrinter.class);
        underTest = new Put(null, null,  null, null, null, null);
        //stepController = Mockito.mock(StepController.class);
    }
/*
    @Test
    public void testUseShipShouldReturnTrueIfItUsedTheShip() {
        // given
        ShipPutter shipPutter = new ShipPutter(null, null);
        ShipPutter spy = spy(shipPutter);

        spy.managePut(2, null);

        // when
        underTest.useShip(ships, null, spy, null);

        // then
        verify(spy).managePut(2, null);
    }
*/
    @Test
    public void testShipsLeftShouldReturnTheNumberOfShipsRemaining() {
        // given
        Ship destroyer = new Destroyer();
        Ship cruiser = new Cruiser();
        cruiser.useShip();

        List<Ship> ships = List.of(destroyer, cruiser);

        // when
        int result = underTest.shipsLeft(ships);

        // then
        assertEquals(result, 1);
    }


/*
    @Test
    public void testProcessShouldReadInputAndCallPerformStepFromStepController() {
        // given
        Ship destroyer = new Destroyer();
        Ship cruiser = new Cruiser();

        TableVO tableVO = new TableVO();
        ArrayList<ArrayList<Character>> table = new ArrayList<>() {
            {
                add(new ArrayList<>() {
                    {
                        add(' ');
                        add(' ');
                        add(' ');
                    }
                });
                add(new ArrayList<>() {
                    {
                        add(' ');
                        add(' ');
                        add(' ');
                    }
                });
                add(new ArrayList<>() {
                    {
                        add(' ');
                        add(' ');
                        add(' ');
                    }
                });
            }
        };

        tableVO.setTable(table);
        tableVO.setTableSize(3);

        List<Ship> ships = List.of(destroyer, cruiser);

        String input = "Destroyer";


        ShipPutter shipPutter = Mockito.mock(ShipPutter.class);
        Put put = Mockito.mock(Put.class);
        UserInput userInput = Mockito.mock(UserInput.class);
        StepController stepController = Mockito.mock(StepController.class);
        doCallRealMethod().when(put).shipsLeft(ships);
        doCallRealMethod().when(put).useShip(ships, input, shipPutter);
        when(put.shipsLeft(ships)).thenReturn(2);
        when(userInput.scanInput()).thenReturn(input);
        when(shipPutter.putShip(ships.get(0).getSize(), tableVO)).thenReturn(true);
        when(shipPutter.putShip(ships.get(1).getSize(), tableVO)).thenReturn(true);
        when(put.useShip(ships, input, shipPutter)).thenReturn(true);



        // when
        underTest.process(input, stepController);

        // then
        verify(stepController).performStep();
    }
*/



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
