package hu.nye.progtech.torpedo.service.interactions.impl;

import java.util.List;
import java.util.stream.Collectors;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.service.interactions.ShipPutter;
import hu.nye.progtech.torpedo.service.ships.Ship;
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
    private final List<Interaction> interactions;

    public Put(GameState game, List<Ship> ships,
               ShipPutter shipPutter, UserInput userInput, List<Interaction> interactions) {
        this.game = game;
        this.ships = ships;
        this.shipPutter = shipPutter;
        this.userInput = userInput;
        this.interactions = interactions;
    }

    @Override
    public void process(String in, StepController stepController) {

        List<Ship> shipsLeft = ships.stream().filter(ship -> !ship.isUsed()).collect(Collectors.toList());
        System.out.print("You can put down a: ");
        shipsLeft.forEach(ship -> System.out.print(ship.getName() + ", "));
        System.out.println();
        String input = userInput.scanInput();
        ships.stream()
                .filter(ship -> ship.getName().equals(input))
                .forEach(ship -> {
                    if (shipPutter.putShip(ship.getSize(), game.getCurrentTable())) {
                        ship.useShip();
                        if (ships.stream().allMatch(Ship::isUsed)) {
                            System.out.println("All ships has been put down!");
                            interactions.stream()
                                    .filter(interaction -> interaction.getName().equals("put"))
                                    .forEach(interaction -> interaction.setUsable(false));
                            interactions.stream()
                                    .filter(interaction -> interaction.getName().equals("shoot"))
                                    .forEach(interaction -> interaction.setUsable(true));
                        }
                    }
                });
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
