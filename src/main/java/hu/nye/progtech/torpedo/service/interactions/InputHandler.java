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
     * @return boolean.
     *
     */
    public boolean handleInput(String in, StepController stepController) {
        List<Interaction> command = interactions.stream()
                .filter(interaction -> interaction.isEqualToCommand(in))
                .collect(Collectors.toList());
        if (command.isEmpty()) {
            return false;
        } else {
            command.forEach(interaction -> interaction.process(in, stepController));
            return true;
        }

    }
}
