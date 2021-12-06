package hu.nye.progtech.torpedo.service.interactions.impl;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Save interaction to save player.
 */

@Service
public class Save implements Interaction {

    private static final String SAVE_COMMAND = "save";
    private boolean usable = true;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameState gameState;


    public Save() {
    }

    @Override
    public void process(String in, StepController stepController) {
        playerRepository.save(gameState.getPlayer());
        System.out.println("Score saved!");
        stepController.performStep();
    }

    @Override
    public boolean isEqualToCommand(String in) {
        return SAVE_COMMAND.equals(in);
    }

    @Override
    public String getName() {
        return SAVE_COMMAND;
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
