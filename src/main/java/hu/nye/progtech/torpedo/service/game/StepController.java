package hu.nye.progtech.torpedo.service.game;

import java.util.List;
import java.util.stream.Collectors;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.service.ai.AiShooter;
import hu.nye.progtech.torpedo.service.interactions.InputHandler;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Component that performs a step.
 */

@Service
public class StepController {

    private final UserInput userInput;
    private final InputHandler inputHandler;
    private final List<Interaction> interactions;
    private final AiShooter aiShooter;
    private final Ai ai;

    @Autowired
    public StepController(UserInput userInput, InputHandler inputHandler, List<Interaction> interactions, Ai ai, AiShooter aiShooter) {
        this.userInput = userInput;
        this.inputHandler = inputHandler;
        this.interactions = interactions;
        this.aiShooter = aiShooter;
        this.ai = ai;
    }
    /**
     * Method that lists the available commands.
     *
     * @return {@link List} of {@link Interaction}
     */

    private List<Interaction> printCommands() {
        System.out.print("Commands: ");
        List<Interaction> interactionList = interactions.stream().filter(Interaction::isUsable).collect(Collectors.toList());
        interactionList.forEach(interaction -> System.out.print(interaction.getName() + " "));
        System.out.println();
        return interactionList;
    }

    public void performAiStep() {
        aiShooter.shoot(ai);
    }

    /**
     * Reads input,
     * and calls the command handling method.
     */
    public void performStep() {
        printCommands();
        String in = userInput.scanInput();
        inputHandler.handleInput(in, this);
    }
}
