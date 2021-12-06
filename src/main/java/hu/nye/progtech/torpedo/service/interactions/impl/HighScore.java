package hu.nye.progtech.torpedo.service.interactions.impl;

import java.util.Comparator;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.Player;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Command to list high score.
 */

@Service
public class HighScore implements Interaction {

    private static final String HIGH_SCORE_COMMAND = "highscore";
    private boolean usable = true;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameState gameState;


    public HighScore() {
    }

    @Override
    public void process(String in, StepController stepController) {
        System.out.println("High score:");
        playerRepository.findAll().stream()
                .sorted(Comparator.comparing(Player::getWins).reversed())
                .forEach(player ->
                    System.out.printf("%s - score: %d%n", player.getName(), player.getId()));
        stepController.performStep();
    }

    @Override
    public boolean isEqualToCommand(String in) {
        return HIGH_SCORE_COMMAND.equals(in);
    }

    @Override
    public String getName() {
        return HIGH_SCORE_COMMAND;
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
