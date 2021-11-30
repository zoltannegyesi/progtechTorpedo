package hu.nye.progtech.torpedo.service.interactions.impl;

import hu.nye.progtech.torpedo.service.Shooter;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import org.springframework.stereotype.Service;

/**
 * Command used to request the shooting method for the player.
 */

@Service
public class Shoot implements Interaction {

    private static final String SHOOT_COMMAND = "shoot";
    private boolean usable = false;

    private final Shooter shooter;

    public Shoot(Shooter shooter) {
        this.shooter = shooter;
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