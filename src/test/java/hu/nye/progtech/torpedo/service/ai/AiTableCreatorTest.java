package hu.nye.progtech.torpedo.service.ai;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AiTableCreatorTest {


    private static final ArrayList<Character> ROW_1 = new ArrayList<>() {
        {
            add(' ');
            add('o');
        }
    };

    private static final ArrayList<Character> ROW_2 = new ArrayList<>() {
        {
            add('o');
            add('o');
        }
    };

    private static final ArrayList<Character> ROW_3 = new ArrayList<>() {
        {
            add('o');
            add(' ');
            add(' ');
        }
    };

    private static final ArrayList<Character> ROW_EAST = new ArrayList<>() {
        {
            add(' ');
            add('o');
            add('o');
        }
    };

    private static final ArrayList<Character> ROW_WEST = new ArrayList<>() {
        {
            add('o');
            add('o');
            add(' ');
        }
    };
    private static final ArrayList<ArrayList<Character>> COORDINATE_CHECKER_TABLE = new ArrayList<>() {
        {
            add(ROW_1);
            add(ROW_2);
        }
    };

    private static ArrayList<ArrayList<Character>> PUT_COORDINATE_EMPTY_TABLE;

    private static final ArrayList<ArrayList<Character>> PUT_COORDINATE_NORTH_EXPECTED_TABLE = new ArrayList<ArrayList<Character>>() {
        {
            add(ROW_3);
            add(ROW_3);
            add(new ArrayList<>() {
                {
                    add(' ');
                    add(' ');
                    add(' ');
                }
            });
        }
    };

        private final ArrayList<ArrayList<Character>> PUT_COORDINATE_SOUTH_EXPECTED_TABLE = new ArrayList<>() {
        {
            add(new ArrayList<>() {
                {
                    add(' ');
                    add(' ');
                    add(' ');
                }
            });
            add(ROW_3);
            add(ROW_3);
        }
    };

    private final ArrayList<ArrayList<Character>> PUT_COORDINATE_EAST_EXPECTED_TABLE = new ArrayList<>() {
        {
            add(new ArrayList<>() {
                {
                    add(' ');
                    add(' ');
                    add(' ');
                }
            });
            add(ROW_EAST);
            add(new ArrayList<>() {
                {
                    add(' ');
                    add(' ');
                    add(' ');
                }
            });
        }
    };

    private final ArrayList<ArrayList<Character>> PUT_COORDINATE_WEST_EXPECTED_TABLE = new ArrayList<>() {
        {
            add(new ArrayList<>() {
                {
                    add(' ');
                    add(' ');
                    add(' ');
                }
            });
            add(ROW_WEST);
            add(new ArrayList<>() {
                {
                    add(' ');
                    add(' ');
                    add(' ');
                }
            });
        }
    };

    private final ArrayList<ArrayList<Character>>  PUT_SHIP_TABLE = new ArrayList<>() {
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
                    add('o');
                    add('o');
                    add('o');
                }
            });
            add(new ArrayList<>() {
                {
                    add('o');
                    add('o');
                    add('o');
                }
            });
        }
    };

    private final ArrayList<ArrayList<Character>>  PUT_SHIP_TABLE_FULL = new ArrayList<>() {
        {
            add(new ArrayList<>() {
                {
                    add(' ');
                    add('o');
                    add('o');
                }
            });
            add(new ArrayList<>() {
                {
                    add('o');
                    add('o');
                    add('o');
                }
            });
            add(new ArrayList<>() {
                {
                    add('o');
                    add('o');
                    add('o');
                }
            });
        }
    };

    private final ArrayList<ArrayList<Character>>  PUT_SHIP_EXPECTED_TABLE = new ArrayList<>() {
        {
            add(new ArrayList<>() {
                {
                    add('o');
                    add('o');
                    add('o');
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

    private static final int[] COORDINATES = new int[] {0, 0};

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    private static final int SHIP_SIZE_PUT_COORDINATE = 2;

    private static final int TABLE_SIZE = 2;
    private final int[] RESULT = new int[]{0, 0};

    private static final int COORDINATE_CAN_PUT_DOWN = 5;
    private static final int COORDINATE_LESS_PUT_DOWN = 6;
    private static final int COORDINATE_BIGGER_PUT_DOWN = 4;
    private static final int SHIP_SIZE_CAN_PUT_DOWN = 5;
    private static final int TABLE_SIZE_PUT_DOWN = 10;

    private AiTableCreator underTest;

    @BeforeEach
    public void setup() {
         PUT_COORDINATE_EMPTY_TABLE = new ArrayList<>() {
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
        underTest = new AiTableCreator();
    }

    @Test
    public void testSearchCoordinatesShouldReturnWithCoordinate() {
        // given in setup

        // when
        List<Integer> result = underTest.searchCoordinate(COORDINATE_CHECKER_TABLE, TABLE_SIZE);

        //then
        assertEquals(result.get(0), RESULT[0]);
        assertEquals(result.get(0), RESULT[1]);
    }

    @Test
    public void testCanPutDownShouldReturnTrueIfNumberIsBetween0And9() {
        // given in setup

        // when
        boolean result = underTest.canPutDown(COORDINATE_CAN_PUT_DOWN, SHIP_SIZE_CAN_PUT_DOWN, TABLE_SIZE_PUT_DOWN);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanPutDownShouldReturnFalseIfNumberIsBiggerThan9() {
        // given in setup

        // when
        boolean result = underTest.canPutDown(COORDINATE_LESS_PUT_DOWN, SHIP_SIZE_CAN_PUT_DOWN, TABLE_SIZE_PUT_DOWN);

        // then
        assertFalse(result);
    }

    @Test
    public void testCanPutDownShouldReturnFalseIfNumberIsLessThen0() {
        // given in setup

        // when
        boolean result = underTest.canPutDown(COORDINATE_BIGGER_PUT_DOWN, SHIP_SIZE_CAN_PUT_DOWN, TABLE_SIZE_PUT_DOWN);

        // then
        assertFalse(result);
    }

    @Test
    public void testPutToCoordinateShouldPutDownTheGivenShipToTheCoordinateToNorth() {
        // given in setup

        // when
        ArrayList<ArrayList<Character>> result =
                underTest.putToCoordinate(NORTH, PUT_COORDINATE_EMPTY_TABLE, SHIP_SIZE_PUT_COORDINATE, 0, 0);

        // then
        assertEquals(result, PUT_COORDINATE_NORTH_EXPECTED_TABLE);
    }

    @Test
    public void testPutToCoordinateShouldPutDownTheGivenShipToTheCoordinateToSouth() {
        // given in setup

        // when
        ArrayList<ArrayList<Character>> result =
                underTest.putToCoordinate(SOUTH, PUT_COORDINATE_EMPTY_TABLE, SHIP_SIZE_PUT_COORDINATE, 0, 2);

        // then
        assertEquals(result, PUT_COORDINATE_SOUTH_EXPECTED_TABLE);
    }

    @Test
    public void testPutToCoordinateShouldPutDownTheGivenShipToTheCoordinateToEast() {
        // given in setup

        // when
        ArrayList<ArrayList<Character>> result =
                underTest.putToCoordinate(EAST, PUT_COORDINATE_EMPTY_TABLE, SHIP_SIZE_PUT_COORDINATE, 1, 1);

        // then
        assertEquals(result, PUT_COORDINATE_EAST_EXPECTED_TABLE);
    }

    @Test
    public void testPutToCoordinateShouldPutDownTheGivenShipToTheCoordinateToWest() {
        // given in setup

        // when
        ArrayList<ArrayList<Character>> result =
                underTest.putToCoordinate(WEST, PUT_COORDINATE_EMPTY_TABLE, SHIP_SIZE_PUT_COORDINATE, 1, 1);

        // then
        assertEquals(result, PUT_COORDINATE_WEST_EXPECTED_TABLE);
    }
/*
    @Test
    public void testPutDownShipShouldReturnTableWithPutDownShips() {
        // given
        AiTableCreator aiTableCreator = spy(new AiTableCreator());

        Random rnd = new Random();

       // doReturn(COORDINATES).when(aiTableCreator).searchCoordinate(PUT_SHIP_TABLE, 3);

        doReturn(true).when(aiTableCreator).canPutDown(0,3,3);

        doReturn(PUT_SHIP_EXPECTED_TABLE).when(aiTableCreator).putToCoordinate(0, null, 0, 0, 0);

        // when
        ArrayList<ArrayList<Character>> result = aiTableCreator.putDownShip(PUT_SHIP_TABLE, 3, 3, 0);

        // then
        assertEquals(result, PUT_SHIP_EXPECTED_TABLE);
    }

/*
    @Test
    public void testPutDownShipShouldReturnTableWithPutDownShip() {
        // given
        AiTableCreator aiTableCreator = spy(new AiTableCreator());
        List<Integer> coordinates = List.of(1, 1);
        Random rnd = Mockito.mock(Random.class);

        doReturn(true).when(aiTableCreator.putDownShip(any(), any(), any(), any()));

        doReturn(List.of(0,0)).when(aiTableCreator).searchCoordinate(any(), any());

        doReturn(true).when(aiTableCreator).canPutDown(any(), any(), any());



        // when
        ArrayList<ArrayList<Character>> result = aiTableCreator.putDownShip(PUT_SHIP_TABLE, 3, 3, 1);

        // then
        assertEquals(result, PUT_SHIP_EXPECTED_TABLE);
    }
/*
    @Test
    public void testPutDownShipShouldReturnTableWithPutDownShipss() {
        final ArgumentCaptor<Foo> captor = ArgumentCaptor.forClass(Foo.class);

        verify(mock).doSomething(captor.capture());

        final Foo argument = captor.getValue();

// Test the argument

        // given
        AiTableCreator aiTableCreator = spy(new AiTableCreator());
        List<Integer> coordinates = List.of(1, 1);
        Random rnd = Mockito.mock(Random.class);

        doReturn(true).when(aiTableCreator.putDownShip(any(), any(), any(), any()));

        doReturn(List.of(0,0)).when(aiTableCreator).searchCoordinate(any(), any());

        doReturn(true).when(aiTableCreator).canPutDown(any(), any(), any());



        // when
        ArrayList<ArrayList<Character>> result = aiTableCreator.putDownShip(PUT_SHIP_TABLE, 3, 3, 1);

        // then
        assertEquals(result, PUT_SHIP_EXPECTED_TABLE);
    }
*/

}
