package hu.nye.progtech.torpedo.service.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.TableVO;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AiShooterTest {

    private static ArrayList<Character> ROW = new ArrayList<>() {
        {
            add(' ');
            add('o');
            add('X');
            add(' ');
            add('+');
            add(' ');
            add(' ');
            add(' ');
            add('o');
            add('X');
        }
    };
    private static final ArrayList<Character> ROW_X = new ArrayList<>() {
        {
            add('X');
            add('o');
            add('X');
            add(' ');
            add('+');
            add(' ');
            add(' ');
            add(' ');
            add('o');
            add('X');
        }
    };

    private static final ArrayList<Character> ROW_O = new ArrayList<>() {
        {
            add('X');
            add('+');
            add('X');
            add(' ');
            add('+');
            add(' ');
            add(' ');
            add(' ');
            add('o');
            add('X');
        }
    };

    private static final ArrayList<Character> ROW_RECURSIVE_1 = new ArrayList<>() {
        {
            add('X');
            add('+');
            add('X');
            add(' ');
            add('+');
            add('X');
            add('X');
            add('X');
            add('+');
            add('X');
        }
    };


    private static final ArrayList<Character> ROW_RECURSIVE_2 = new ArrayList<>() {
        {
            add('X');
            add('+');
            add('+');
            add(' ');
            add('+');
            add('X');
            add('X');
            add('X');
            add('+');
            add('X');
        }
    };

    private static final ArrayList<Character> ROW_RECURSIVE_3 = new ArrayList<>() {
        {
            add('X');
            add('+');
            add('+');
            add(' ');
            add('+');
            add('X');
            add('X');
            add('X');
            add('+');
            add('X');
        }
    };


    private static final ArrayList<Character> ROW_RECURSIVE_4 = new ArrayList<>() {
        {
            add('X');
            add('+');
            add('+');
            add('X');
            add('+');
            add('X');
            add('X');
            add('X');
            add('+');
            add('X');
        }
    };

    private static final ArrayList<Character> ROW_NULL = new ArrayList<>() {
        {
            add('a');
            add('+');
            add('X');
            add('X');
            add('+');
            add('X');
            add('X');
            add('X');
            add('+');
            add('a');
        }
    };

    private static final int COORDINATE_EMPTY = 0;
    private static final int COORDINATE_O = 1;
    private static final int COORDINATE_X = 2;
    private static final int COORDINATE_NULL = 0;

    private AiShooter underTest;


    @Test
    public void testShootShouldSetCoordinateToX() {
        // given
        underTest = new AiShooter();

        // when
        ArrayList<Character> result = underTest.shoot(ROW, COORDINATE_EMPTY);

        // then
        assertEquals(result, ROW_X);
    }

    @Test
    public void testShootShouldSetCoordinateToPlus() {
        // given
        underTest = new AiShooter();

        // when
        ArrayList<Character> result = underTest.shoot(ROW_X, COORDINATE_O);

        // then
        assertEquals(result, ROW_O);
    }

    @Test
    public void testShootShouldCallItselfWithXAndFillInTheLastCoordinate() {
        // given
        underTest = new AiShooter();

        // when
        ArrayList<Character> result = underTest.shoot(ROW_RECURSIVE_1, COORDINATE_X);

        // then
        assertEquals(result, ROW_RECURSIVE_2);
    }

    @Test
    public void testShootShouldCallItselfWithPlusAndFillInTheLastCoordinate() {
        // given
        underTest = new AiShooter();

        // when
        ArrayList<Character> result = underTest.shoot(ROW_RECURSIVE_3, COORDINATE_X);

        // then
        assertEquals(result, ROW_RECURSIVE_4);
    }

    @Test
    public void testShootShouldReturnWithNull() {
        // given
        underTest = new AiShooter();

        // when
        ArrayList<Character> result = underTest.shoot(ROW_NULL, COORDINATE_NULL);

        // then
        assertNull(result);
    }



}
