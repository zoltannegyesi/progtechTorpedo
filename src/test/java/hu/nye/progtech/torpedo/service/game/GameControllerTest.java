package hu.nye.progtech.torpedo.service.game;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.TableVO;
import hu.nye.progtech.torpedo.service.table.TableCreator;
import hu.nye.progtech.torpedo.service.util.MapUtil;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link GameController}.
 */


@ExtendWith(MockitoExtension.class)
public class GameControllerTest {



    private static final TableVO TABLE_VO = new TableVO();

    private GameState gameState;

    @Mock
    private StepController stepController;
    @Mock
    private MapUtil mapUtil;
    @Mock
    private TableCreator tableCreator;
    @Mock
    private Ai ai;

    private GameController underTest;

    private static final String RETURN = "name";

    @Test
    public void testStartShouldLoopTheGameUntilTheUserDoesNotForceExit() {
        // given
        UserInput userInput = Mockito.mock(UserInput.class);
        gameState = new GameState(null, null, null);

        underTest = new GameController(stepController, mapUtil, gameState, tableCreator, ai, userInput);

        when(userInput.scanInput()).thenReturn(RETURN);


        // when
        underTest.start();


        // then
        verifyNoInteractions(stepController);
    }

    @Test
    public void testStartShouldLoopTheGameAndCallMethodsFromStepController() {
        // given

        gameState = new GameState(TABLE_VO, TABLE_VO, TABLE_VO);
        UserInput userInput = Mockito.mock(UserInput.class);
        underTest = new GameController(stepController, mapUtil,gameState ,tableCreator, ai, userInput);
        given(mapUtil.areAllShipsDestroyed()).willReturn(true, false);

        when(userInput.scanInput()).thenReturn(RETURN);
        // when
        underTest.start();


        // then
        verify(mapUtil, times(2)).areAllShipsDestroyed();
        verify(stepController, times(1)).performStep();
    }
}
