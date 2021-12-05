package hu.nye.progtech.torpedo.service.interactions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.impl.Default;
import org.aspectj.apache.bcel.classfile.Unknown;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InputHandlerTest {

    private static final String INPUT = "input";
    private static final String UNKNOWN_COMMAND = "Unknown command";

    @Mock
    private Interaction interaction1;

    @Mock
    private Interaction interaction2;

    @Mock
    private StepController stepController;

    private InputHandler underTest;

    @BeforeEach
    public void setUp() {
        underTest = new InputHandler(List.of(interaction1, interaction2));
    }

    @Test
    public void testHandleInputShouldRunOnlyTheFirstApplicableInteraction() {
        // given
        given(interaction1.isEqualToCommand(INPUT)).willReturn(true);
        given(interaction1.getName()).willReturn(INPUT);

        // when
        underTest.handleInput(INPUT, stepController);

        // then
        verify(interaction1).isEqualToCommand(INPUT);
        verify(interaction1).process(INPUT, stepController);
        verifyNoInteractions(interaction2);

    }

    @Test
    public void testHandleInputShouldRunNoCommandsWhenNoneOfThemIsApplicable() {
        // given

        // when
        boolean result = underTest.handleInput(INPUT, stepController);

        // then
        assertTrue(result);

    }

    @Test
    public void testUnknownInteractionShouldCallUnknownProcessMethod() {
        // given
        given(interaction1.getName()).willReturn(INPUT);
        given(interaction2.getName()).willReturn(UNKNOWN_COMMAND);

        // when
        underTest.unknownInteraction(INPUT, stepController);

        // then
        verify(interaction2).process(INPUT, stepController);
    }

}
