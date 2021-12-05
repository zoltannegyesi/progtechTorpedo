package hu.nye.progtech.torpedo.service.interactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ShooterTest {


    private ArrayList<Character> playerRowPlus = new ArrayList<>() { {
       add('o');
       add('X');
    }};

    private ArrayList<Character> aiRowPlus = new ArrayList<>() { {
        add('o');
        add('X');
    }};

    private ArrayList<Character> playerRowAlreadyShot = new ArrayList<>() { {
        add('+');
        add('X');
    }};

    private ArrayList<Character> aiRowAlreadyShot = new ArrayList<>() { {
        add('+');
        add('X');
    }};

    private static final int X_1 = 0;
    private static final char COORDINATE_1 = '+';

    private static final int X_2 = 1;
    private static final char COORDINATE_2 = 'X';

    private static final int X_3 = 0;
    private static final char COORDINATE_3 = '+';

    private Shooter underTest;

    @BeforeEach
    public void setUp(){
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

        Mockito.verify(shooter).shoot();
        assertEquals(result, COORDINATE_3);
    }

    @Test
    public void testPlaceCharacterShouldReturnWithXIfItDoesntHitAndCallShootMethod() {
        // given
        Shooter shooter = Mockito.mock(Shooter.class);

        // then
        doCallRealMethod().when(shooter).placeCharacter(X_2, playerRowAlreadyShot, aiRowAlreadyShot);

        char result = shooter.placeCharacter(X_2, playerRowAlreadyShot, aiRowAlreadyShot);

        Mockito.verify(shooter).shoot();
        assertEquals(result, COORDINATE_2);
    }


}
