package hu.nye.progtech.torpedo.service.interactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.TableVO;
import hu.nye.progtech.torpedo.service.util.CoordinateConverter;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

public class ShooterTest {


    private ArrayList<Character> playerRowPlus = new ArrayList<>() {
        {
            add('o');
            add('X');
        }
    };

    private ArrayList<Character> aiRowPlus = new ArrayList<>() {
        {
            add('o');
            add('X');
        }
    };

    private ArrayList<Character> playerRowAlreadyShot = new ArrayList<>() {
        {
            add('+');
            add('X');
        }
    };

    private ArrayList<Character> aiRowAlreadyShot = new ArrayList<>() {
        {
            add('+');
            add('X');
        }
    };

    private ArrayList<Character> playerRowMissed = new ArrayList<>() {
        {
            add(' ');
            add('X');
        }
    };

    private ArrayList<Character> aiRowMissed = new ArrayList<>() {
        {
            add(' ');
            add('X');
        }
    };

    private ArrayList<ArrayList<Character>> playerTable = new ArrayList<>() {
        {
            add(new ArrayList<>() {
                {
                    add('o');
                    add('X');
                }
            });
            add(new ArrayList<>() {
                {
                    add('o');
                    add('X');
                }
            });
        }
    };

    private ArrayList<ArrayList<Character>> aiTable = new ArrayList<>() {
        {
            add(new ArrayList<>() {
                {
                    add('o');
                    add('X');
                }
            });
            add(new ArrayList<>() {
                {
                    add('o');
                    add('X');
                }
            });
        }
    };

    private ArrayList<Character> shootPlayerTable = new ArrayList<>() {
        {
            add('o');
            add('X');
        }
    };

    private ArrayList<Character> shootAiTable = new ArrayList<>() {
        {
            add('o');
            add('X');
        }
    };

    private ArrayList<Character> shootedPlayerTable = new ArrayList<>() {
        {
            add('+');
            add('X');
        }
    };

    private static final int X_1 = 0;
    private static final char COORDINATE_1 = '+';

    private static final int X_2 = 1;
    private static final char COORDINATE_2 = 'X';

    private static final int X_3 = 0;
    private static final char COORDINATE_3 = '+';

    private static final int X_4 = 0;
    private static final char COORDINATE_4 = 'X';


    private Shooter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Shooter(null, null, null);
    }

    @Test
    public void testPlaceCharacterShouldReturnWithPlusIfItHits() {
        // given in setup

        // when
        char result = underTest.placeCharacter(X_1, playerRowPlus, aiRowPlus);

        // then
        assertEquals(result, COORDINATE_1);
    }

    @Test
    public void testPlaceCharacterShouldReturnWithPlusIfItDoesntHitAndCallShootMethod() {
        // given
        Shooter shooter = Mockito.mock(Shooter.class);

        // then
        doCallRealMethod().when(shooter).placeCharacter(X_3, playerRowAlreadyShot, aiRowAlreadyShot);

        char result = shooter.placeCharacter(X_3, playerRowAlreadyShot, aiRowAlreadyShot);

        verify(shooter).shoot();
        assertEquals(result, COORDINATE_3);
    }

    @Test
    public void testPlaceCharacterShouldReturnWithXIfItDoesntHitAndCallShootMethod() {
        // given
        Shooter shooter = Mockito.mock(Shooter.class);


        doCallRealMethod().when(shooter).placeCharacter(X_2, playerRowAlreadyShot, aiRowAlreadyShot);

        // when
        char result = shooter.placeCharacter(X_2, playerRowAlreadyShot, aiRowAlreadyShot);

        // then
        verify(shooter).shoot();
        assertEquals(result, COORDINATE_2);
    }

    @Test
    public void testPlaceCharacterShouldReturnWithXIfItHitsButMisses() {
        // given in setup

        // when
        char result = underTest.placeCharacter(X_4, playerRowMissed, aiRowMissed);

        // then
        assertEquals(result, COORDINATE_4);
    }

/*
    @Test
    public void testShoot() {
        // given
        TableVO tableVO1 = new TableVO();
        tableVO1.setTable(playerTable);
        tableVO1.setTableSize(2);
        TableVO tableVO2 = new TableVO();
        tableVO1.setTable(aiTable);
        tableVO1.setTableSize(2);

        GameState gameState = new GameState(null, tableVO1, tableVO2);

        UserInput userInput = Mockito.mock(UserInput.class);
        CoordinateConverter coordinateConverter = Mockito.mock(CoordinateConverter.class);
        Shooter shooter = Mockito.mock(Shooter.class);

        //underTest = new Shooter(gameState, userInput, coordinateConverter);
        underTest = spy(new Shooter(gameState, userInput, coordinateConverter));
        doCallRealMethod().when(underTest).shoot();

        when(userInput.scanInput()).thenReturn("A1");
        when(coordinateConverter.checkCoordinate('A')).thenReturn(0);
        when(coordinateConverter.checkCoordinate('1')).thenReturn(0);

        when(underTest.placeCharacter(1, playerTable.get(0), aiTable.get(0))).thenReturn('+');
        // when
        ArrayList<Character> result = underTest.shoot();

        // then
        assertEquals(result, shootedPlayerTable);
    }
*/

}
