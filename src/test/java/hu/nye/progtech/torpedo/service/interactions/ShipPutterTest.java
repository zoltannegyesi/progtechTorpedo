package hu.nye.progtech.torpedo.service.interactions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import hu.nye.progtech.torpedo.service.util.CoordinateConverter;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ShipPutterTest {

    private static final String COORDINATE_10 = "A10";
    private static final int COORDINATE_10_EXPECTED = 9;

    private static final String COORDINATE_1DIGIT = "B5";
    private static final int COORDINATE_1DIGIT_EXPECTED = 5;

    private static final String TWO_COORDINATES = "A3 C3";
    private static final String TWO_FIRST_COORDINATE = "A3";
    private static final String TWO_SECOND_COORDINATE = "C3";

    private static final int X1 = 0;
    private static final int X2 = 2;
    private static final int Y1 = 2;
    private static final int Y2 = 2;
    private static final int SHIP_SIZE_1 = 3;

    private static final int X3 = 0;
    private static final int X4 = 0;
    private static final int Y3 = 0;
    private static final int Y4 = 2;
    private static final int SHIP_SIZE_2 = 3;

    private static final int X5 = 2;
    private static final int X6 = 0;
    private static final int Y5 = 1;
    private static final int Y6 = 1;
    private static final int SHIP_SIZE_3 = 3;

    private static final int X7 = 0;
    private static final int X8 = 0;
    private static final int Y7 = 1;
    private static final int Y8 = 0;
    private static final int SHIP_SIZE_4 = 2;

    private static final int X9 = 0;
    private static final int X10 = 0;
    private static final int Y9 = 2;
    private static final int Y10 = 0;
    private static final int SHIP_SIZE_5 = 2;

    private static final int X11 = 0;
    private static final int X12 = 0;
    private static final int Y11 = 1;
    private static final int Y12 = 0;
    private static final int SHIP_SIZE_6 = 3;

    private ArrayList<ArrayList<Character>> table;


    ShipPutter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ShipPutter(null, null);
        table = new ArrayList<>() {
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
    }

    @Test
    public void testCoordinateCheckerShouldReturn9IfTheSecondCoordinateIs10() {
        // given in setup

        // when
        int result = underTest.coordinateChecker(COORDINATE_10);
        // then
        assertEquals(result, COORDINATE_10_EXPECTED);
    }

    @Test
    public void testCoordinateCheckerShouldReturnTheSecondCoordinateIfTheSecondCoordinateIs1Digit() {
        // given

        CoordinateConverter coordinateConverter = Mockito.mock(CoordinateConverter.class);
        underTest = new ShipPutter(null, coordinateConverter);
        when(coordinateConverter.checkCoordinate(COORDINATE_1DIGIT.toCharArray()[1])).thenReturn(COORDINATE_1DIGIT_EXPECTED);

        // when
        int result = underTest.coordinateChecker(COORDINATE_1DIGIT);
        // then
        assertEquals(result, COORDINATE_1DIGIT_EXPECTED);
    }

    @Test
    public void testPutDownShipShouldReturnTrueIfTheShipWasPutDownAndYCoordinatesAreEqualAndX1IsBigger() {
        // given in setup

        // when
        boolean result = underTest.putDownShip(X1, X2, Y1, Y2, SHIP_SIZE_1, table);

        // then
        assertTrue(result);
    }

    @Test
    public void testPutDownShipShouldReturnTrueIfTheShipWasPutDownAndXCoordinatesAreEqualAndY1IsBigger() {
        // given in setup

        // when
        boolean result = underTest.putDownShip(X3, X4, Y3, Y4, SHIP_SIZE_2, table);

        // then
        assertTrue(result);
    }

    @Test
    public void testPutDownShipShouldReturnTrueIfTheShipWasPutDownAndYCoordinatesAreEqualAndX2IsBigger() {
        // given in setup

        // when
        boolean result = underTest.putDownShip(X5, X6, Y5, Y6, SHIP_SIZE_3, table);

        // then
        assertTrue(result);
    }

    @Test
    public void testPutDownShipShouldReturnTrueIfTheShipWasPutDownAndXCoordinatesAreEqualAndY2IsBigger() {
        // given in setup

        // when
        boolean result = underTest.putDownShip(X7, X8, Y7, Y8, SHIP_SIZE_4, table);

        // then
        assertTrue(result);
    }
    @Test
    public void testPutDownShipShouldReturnFalseIfTheShipIsTooLong() {
        // given in setup

        // when
        boolean result = underTest.putDownShip(X9, X10, Y9, Y10, SHIP_SIZE_5, table);

        // then
        assertFalse(result);
    }
    @Test
    public void testPutDownShipShouldReturnFalseIfTheShipIsTooShort() {
        // given in setup

        // when
        boolean result = underTest.putDownShip(X11, X12, Y11, Y12, SHIP_SIZE_6, table);

        // then
        assertFalse(result);
    }

    @Test
    public void testManagePutShouldReturnTrueIfItCanPutDownTheShip() {
        // given

        CoordinateConverter coordinateConverter = Mockito.mock(CoordinateConverter.class);
        UserInput userInput = Mockito.mock(UserInput.class);

        ShipPutter shipPutter = spy(new ShipPutter(userInput, coordinateConverter));

        doReturn(true).when(shipPutter).putDownShip(0,0,0,0,0,null);


        when(userInput.scanInput()).thenReturn(TWO_COORDINATES);

        when(coordinateConverter.checkCoordinate(TWO_FIRST_COORDINATE.toCharArray()[0])).thenReturn(0);
        when(coordinateConverter.checkCoordinate(TWO_SECOND_COORDINATE.toCharArray()[0])).thenReturn(2);

        doReturn(2).when(shipPutter).coordinateChecker(TWO_FIRST_COORDINATE);
        doReturn(2).when(shipPutter).coordinateChecker(TWO_SECOND_COORDINATE);


        // when
        boolean result = shipPutter.managePut(SHIP_SIZE_1, table);

        // then
        assertTrue(result);
    }

    @Test
    public void testManagePutShouldReturnFalseIfItCannotPutDownTheShip() {
        // given

        CoordinateConverter coordinateConverter = Mockito.mock(CoordinateConverter.class);
        UserInput userInput = Mockito.mock(UserInput.class);

        ShipPutter shipPutter = spy(new ShipPutter(userInput, coordinateConverter));

        doReturn(true).when(shipPutter).putDownShip(0,0,0,0,0,null);


        when(userInput.scanInput()).thenReturn(TWO_COORDINATES);

        when(coordinateConverter.checkCoordinate(TWO_FIRST_COORDINATE.toCharArray()[0])).thenReturn(0);
        when(coordinateConverter.checkCoordinate(TWO_SECOND_COORDINATE.toCharArray()[0])).thenReturn(2);

        doReturn(0).when(shipPutter).coordinateChecker(TWO_FIRST_COORDINATE);
        doReturn(0).when(shipPutter).coordinateChecker(TWO_SECOND_COORDINATE);


        // when
        boolean result = shipPutter.managePut(SHIP_SIZE_1, table);

        // then
        assertFalse(result);
    }

}

