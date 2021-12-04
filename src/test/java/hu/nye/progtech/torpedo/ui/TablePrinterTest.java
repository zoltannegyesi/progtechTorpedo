package hu.nye.progtech.torpedo.ui;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.TableVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TablePrinterTest {


    ArrayList<ArrayList<Character>> listSample = new ArrayList<>();

    private final String TABLE_SAMPLE =

            "    A B C D E F G H I J\n" +
            "   _____________________\n" +
            "1  |X X X X X X X X X X|\n" +
            "2  |X X X X X X X X X X|\n" +
            "3  |X X X X X X X X X X|\n" +
            "4  |X X X X X X X X X X|\n" +
            "5  |X X X X X X X X X X|\n" +
            "6  |X X X X X X X X X X|\n" +
            "7  |X X X X X X X X X X|\n" +
            "8  |X X X X X X X X X X|\n" +
            "9  |X X X X X X X X X X|\n" +
            "10 |X X X X X X X X X X|\n" +
            "   ---------------------";

    TablePrinter underTest;

    @Mock
    private GameState gameState;

    @Test
    public void testDrawTableShouldPrintTheTableToTheScreen() {
        // given
        ArrayList<Character> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add('X');
        }
        for (int i = 0; i < 10; i++) {
            listSample.add(temp);
        }
        TableVO tableVO = new TableVO();
        tableVO.setTable(listSample);
        underTest = new TablePrinter();


        // when
        String result = underTest.drawTable(tableVO);

        // then
        assertEquals(TABLE_SAMPLE, result);
    }

}