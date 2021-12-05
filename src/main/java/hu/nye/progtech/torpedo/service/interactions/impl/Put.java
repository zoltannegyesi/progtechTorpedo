package hu.nye.progtech.torpedo.service.interactions.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.ships.Ship;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.service.interactions.ShipPutter;
import hu.nye.progtech.torpedo.service.util.InteractionEnabler;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.springframework.stereotype.Service;

/**
 * Command used to request the putting method.
 */

@Service
public class Put implements Interaction {
    private static final String PUT_COMMAND = "put";
    private boolean usable = true;

    private final GameState game;
    private final List<Ship> ships;
    private final ShipPutter shipPutter;
    private final UserInput userInput;
    private final InteractionEnabler interactionEnabler;
    private final List<Interaction> interactions;

    public Put(GameState game, List<Ship> ships,
               ShipPutter shipPutter, UserInput userInput, InteractionEnabler interactionEnabler, List<Interaction> interactions) {
        this.game = game;
        this.ships = ships;
        this.shipPutter = shipPutter;
        this.userInput = userInput;
        this.interactionEnabler = interactionEnabler;
        this.interactions = interactions;
    }

    /**
     * Should turn on shoot.
     *
     * @return {@link List} of {@link Ship}
     */

    public int shipsLeft(List<Ship> ships) {
        List<Ship> shipsLeft = ships.stream().filter(ship -> !ship.isUsed()).collect(Collectors.toList());
        System.out.print("You can put down a: ");
        shipsLeft.forEach(ship -> System.out.print(ship.getName() + ", "));
        System.out.println();
        return shipsLeft.size();
    }



    /**
     * Should use ship.
     *
     * @param ships ships
     * @param input input
     * @return {@link Interaction}
     */

    public boolean useShip(List<Ship> ships, String input) {
        AtomicBoolean allShipsPutDown = new AtomicBoolean(false);
        ships.stream()
                .filter(ship -> ship.getName().equals(input))
                .forEach(ship -> {
                    if (shipPutter.putShip(ship.getSize(), game.getCurrentTable())) {
                        ship.useShip();
                        if (ships.stream().allMatch(Ship::isUsed)) {
                            System.out.println("All ships has been put down!");
                            allShipsPutDown.set(true);
                        }
                    }
                });
        if (allShipsPutDown.get()) {
            interactionEnabler.disablePut(interactions, this);
            interactionEnabler.enableShoot(interactions);
        }
        return true;
    }

    @Override
    public void process(String in, StepController stepController) {
        shipsLeft(ships);
        String input = userInput.scanInput();
        useShip(ships, input);
        stepController.performStep();
    }

    @Override
    public boolean isEqualToCommand(String in) {
        return PUT_COMMAND.equals(in);
    }

    @Override
    public String getName() {
        return PUT_COMMAND;
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
