package hu.nye.progtech.torpedo.service.game;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.service.ai.AiShooter;
import hu.nye.progtech.torpedo.service.interactions.InputHandler;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.service.interactions.impl.*;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StepControllerTest {

    private static final String INPUT = "input";

    private static List<Interaction> interactions;

    @Mock
    private InputHandler inputHandler;


    //private StepController stepController;

    @Mock
    private Ai ai;

    @Mock
    private AiShooter aiShooter;

    @Mock
    private UserInput userInput;

    private StepController underTest;

    @BeforeEach
    public void setUp() {
        interactions = new ArrayList<>();
        interactions.add(Mockito.mock(Exit.class));
        //interactions.add(Mockito.mock(Print.class));
        interactions.add(Mockito.mock(Put.class));
        interactions.add(Mockito.mock(PrintAiTable.class));
        interactions.add(Mockito.mock(Shoot.class));
        underTest = new StepController(userInput, inputHandler, interactions, ai, aiShooter);
    }

    @Test
    public void testPerformAiStepShouldCallShootMethodFromAi() {
        // given in setup

        // when
        underTest.performAiStep();

        // then
        verify(aiShooter).shoot(ai);
    }

    @Test
    public void testPerformStepShouldReadUserInputAndCallHandleInputFromInputHandler() {
        // given
        given(userInput.scanInput()).willReturn(INPUT);

        // when
        underTest.performStep();

        // then
        verify(userInput).scanInput();
        verify(inputHandler).handleInput(INPUT, underTest);
    }

}
