package hu.nye.progtech.torpedo.service.table;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.TableVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TableCreator}.
 */

public class TableCreatorTest {

    private static final List<Character> ROW = List.of(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ');

    private static final ArrayList<List<Character>> EXPECTED_MAP = new ArrayList<>();

    @BeforeEach
    public void setup() {
        for (int i = 0; i < 10; i++) {
            EXPECTED_MAP.add(ROW);
        }
    }

    @Test
    public void testCreateTableShouldReturnATableWithSpaceCharacters() {
        // given
        TableVO tableVO = new TableVO();
        TableCreator underTest = new TableCreator();


        // when
        ArrayList<List<Character>> result = underTest.createTable(tableVO);

        // then
        assertEquals(EXPECTED_MAP, result);


    }
}
