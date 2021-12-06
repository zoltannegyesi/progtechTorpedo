package hu.nye.progtech.torpedo.service.interactions.impl;

import java.util.ArrayList;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import org.springframework.stereotype.Service;

/**
 * Command used exit the game.
 */

@Service
public class Exit implements Interaction {

    private static final String EXIT_COMMAND = "exit";
    private boolean usable = true;

    private final GameState game;

    public Exit(GameState game) {
        this.game = game;
    }

    @Override
    public void process(String in, StepController stepController) {
        game.setShouldExit(true);
    }

    @Override
    public boolean isEqualToCommand(String in) {
        return EXIT_COMMAND.equals(in);
    }

    @Override
    public String getName() {
        return EXIT_COMMAND;
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
