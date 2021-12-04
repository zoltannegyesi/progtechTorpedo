package hu.nye.progtech.torpedo.service.interactions.impl;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import org.springframework.stereotype.Service;

/**
 * Command used for not available command.
 */

@Service
public class Default implements Interaction {

    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";
    private boolean usable = false;


    public Default() {

    }

    @Override
    public void process(String in, StepController stepController) {
        System.out.println("Unknown command!");
        stepController.performStep();
    }

    @Override
    public boolean isEqualToCommand(String in) {
        return true;
    }

    @Override
    public String getName() {
        return UNKNOWN_COMMAND_MESSAGE;
    }

    @Override
    public void setUsable(boolean bool) {
        this.usable = bool;
    }

    @Override
    public boolean isUsable() {
        return this.usable;
    }
}