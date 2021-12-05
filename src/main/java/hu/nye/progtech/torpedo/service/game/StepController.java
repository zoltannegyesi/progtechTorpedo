package hu.nye.progtech.torpedo.service.game;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
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
    private final Random rnd;

    @Autowired
    public StepController(UserInput userInput, InputHandler inputHandler, List<Interaction> interactions, Ai ai, AiShooter aiShooter) {
        this.userInput = userInput;
        this.inputHandler = inputHandler;
        this.interactions = interactions;
        this.aiShooter = aiShooter;
        this.ai = ai;
        rnd = new Random();
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

    public void performAiStep(GameState gameState) {
        aiShooter.shoot(gameState.getCurrentTable().getTable().get(rnd.nextInt(10)), rnd.nextInt(10));
    }

    /**
     * Reads input,
     * and calls the command handling method.
     */
    public void performStep() {
        printCommands();
        String in = userInput.scanInput();
        boolean unknown = inputHandler.handleInput(in, this);
        if (unknown) {
            inputHandler.unknownInteraction(in, this);
        }

    }
}
