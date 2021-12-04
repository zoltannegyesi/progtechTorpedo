package hu.nye.progtech.torpedo.service.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.TableVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapUtilTest {

    private static ArrayList<ArrayList<Character>> NOT_COMPLETED_TABLE;
    private static final ArrayList<Character> ROW_WITH_SHIP = new ArrayList<>() {
        {
            add('o');
            add(' ');
            add('X');
            add('+');
        }
    };
    private static ArrayList<ArrayList<Character>> COMPLETED_TABLE;
    private static final ArrayList<Character> ROW_WITHOUT_SHIP = new ArrayList<>() {
        {
            add('X');
            add(' ');
            add('+');
        }
    };

    private GameState gameState;

    private MapUtil underTest;

    @BeforeEach
    public void setUp() {
        NOT_COMPLETED_TABLE = new ArrayList<>();
        NOT_COMPLETED_TABLE.add(ROW_WITH_SHIP);
        NOT_COMPLETED_TABLE.add(ROW_WITH_SHIP);
        NOT_COMPLETED_TABLE.add(ROW_WITH_SHIP);
        NOT_COMPLETED_TABLE.add(ROW_WITH_SHIP);
        COMPLETED_TABLE = new ArrayList<>();
        COMPLETED_TABLE.add(ROW_WITHOUT_SHIP);
        COMPLETED_TABLE.add(ROW_WITHOUT_SHIP);
        COMPLETED_TABLE.add(ROW_WITHOUT_SHIP);
    }

    @Test
    public void testAreAllShipsDestroyedShouldReturnTrueWhenThereAreNoShipsRemainingOnTheMap() {
        // given
        TableVO tableVO = new TableVO();
        tableVO.setTable(COMPLETED_TABLE);
        gameState = new GameState(tableVO, tableVO);
        gameState.setRan(true);
        underTest = new MapUtil(gameState);

        //when
        boolean result = underTest.areAllShipsDestroyed();

        // then
        assertTrue(result);
    }

    @Test
    public void testAreAllShipsDestroyedShouldReturnFalseWhenThereAreShipsRemainingOnTheMap() {
        // given
        TableVO tableVO = new TableVO();
        tableVO.setTable(NOT_COMPLETED_TABLE);
        gameState = new GameState(tableVO, tableVO);
        gameState.setRan(true);
        underTest = new MapUtil(gameState);

        //when
        boolean result = underTest.areAllShipsDestroyed();

        // then
        assertFalse(result);
    }


}
