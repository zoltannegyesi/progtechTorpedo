package hu.nye.progtech.torpedo.service.interactions;

import java.util.List;
import java.util.stream.Collectors;

import hu.nye.progtech.torpedo.service.game.StepController;
import org.springframework.stereotype.Service;

/**
 * Component for handling the input for the commands.
 */

@Service
public class InputHandler {

    private final List<Interaction> interactions;


    public InputHandler(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    /**
     * Method used to call the input command.
     *
     *
     * @param in                the input.
     * @param stepController    the controller, that used it.
     *
     */
    public void handleInput(String in, StepController stepController) {
        boolean unknown = true;
        for (Interaction interaction : interactions) {
            if (interaction.isEqualToCommand(in) && !interaction.getName().equals("Unknown command")) {
                interaction.process(in, stepController);
                unknown = false;
                break;
            }
        }
        if (unknown) {
            Interaction interaction = interactions.stream().filter(command ->
                    command.getName().equals("Unknown command")).collect(Collectors.toList()).get(0);
            interaction.process(in, stepController);
        }

    }
}
