package hu.nye.progtech.torpedo.service.interactions;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.util.CoordinateConverter;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.springframework.stereotype.Service;

/**
 * Component for player shooting.
 */

@Service
public class Shooter {

    private final GameState gameState;
    private final UserInput userInput;
    private final CoordinateConverter coordinateConverter;

    public Shooter(GameState gameState, UserInput userInput, CoordinateConverter coordinateConverter) {
        this.gameState = gameState;
        this.userInput = userInput;
        this.coordinateConverter = coordinateConverter;
    }

    /**
     * Checks if an input is correct
     * and sets the given coordinate to a '+' or 'X'.
     */

    public void shoot() {
        System.out.println("Type in the coordinate to shoot");
        char[] input = userInput.scanInput().toCharArray();
        if (input.length != 2 || input[0] > 'J' || input[0] < 'A' || input[1] < '0' || input[1] > '9') {      //doesnt work
            System.out.println("Wrong input! Shoot again!");
            this.shoot();
        } else {
            int x = coordinateConverter.checkCoordinate(input[0]);
            int y = coordinateConverter.checkCoordinate(input[1]);
            if (gameState.getAiTable().getTable().get(y).get(x) == 'o') {
                System.out.println("It's a hit!");
                gameState.getAiTable().getTable().get(y).set(x, '+');
                gameState.getAiTableForPlayer().getTable().get(y).set(x, '+');
            } else if (gameState.getAiTable().getTable().get(y).get(x) == 'X' || gameState.getAiTable().getTable().get(y).get(x) == '+') {
                System.out.println("It has already been shot! Shoot again!");
                this.shoot();
            } else {
                System.out.println("Missed!");
                gameState.getAiTable().getTable().get(y).set(x, 'X');
                gameState.getAiTableForPlayer().getTable().get(y).set(x, 'X');
            }

        }
    }
}
