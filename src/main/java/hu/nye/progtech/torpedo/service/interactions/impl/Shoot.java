package hu.nye.progtech.torpedo.service.interactions.impl;

import java.util.ArrayList;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.service.interactions.Shooter;
import org.springframework.stereotype.Service;

/**
 * Command used to request the shooting method for the player.
 */

@Service
public class Shoot implements Interaction {

    private static final String SHOOT_COMMAND = "shoot";
    private boolean usable = false;
    private GameState gameState;

    private final Shooter shooter;

    public Shoot(Shooter shooter, GameState gameState) {
        this.shooter = shooter;
        this.gameState = gameState;
    }

    @Override
    public void process(String in, StepController stepController) {
        shooter.shoot();
    }

    @Override
    public boolean isEqualToCommand(String in) {
        return SHOOT_COMMAND.equals(in);
    }

    @Override
    public String getName() {
        return SHOOT_COMMAND;
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