package hu.nye.progtech.torpedo.service.interactions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import org.springframework.stereotype.Service;

/**
 * Component for handling the input for the commands.
 */

@Service
public class InputHandler {

    private final List<Interaction> interactions;
    private GameState gameState;

    public InputHandler(List<Interaction> interactions, GameState gameState) {
        this.interactions = interactions;
        this.gameState = gameState;
    }

    /**
     * Method used to call the input command.
     *
     * @param in             the input.
     * @param stepController the controller, that used it.
     */
    public boolean handleInput(String in, StepController stepController) {
        boolean unknown = true;
        for (Interaction interaction : interactions) {
            if (interaction.isEqualToCommand(in) && !interaction.getName().equals("Unknown command")) {
                interaction.process(in, stepController);
                unknown = false;
                break;
            }
        }
        return unknown;
    }

    /**
     * Call the unknown command's process method.
     *
     * @param in command name
     * @param stepController stepController
     */

    public void unknownInteraction(String in, StepController stepController) {
        Interaction interaction = interactions.stream().filter(command ->
                command.getName().equals("Unknown command")).collect(Collectors.toList()).get(0);
        interaction.process(in, stepController);

    }
}
