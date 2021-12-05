package hu.nye.progtech.torpedo.service.interactions;

import java.util.ArrayList;

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

    public ArrayList<Character> shoot() {
        System.out.println("Type in the coordinate to shoot");
        char[] input = userInput.scanInput().toCharArray();
        if (input[0] <= 'J' && input[0] >= 'A') {
            int x = coordinateConverter.checkCoordinate(input[0]);
            int y = 0;
            if (input.length == 3 && input[1] == '1' && input[2] == '0') {
                y = 9;
            } else if (input.length == 2 && input[1] > '0' && input[1] <= '9') {
                y = coordinateConverter.checkCoordinate(input[1]);
            } else {
                System.out.println("Wrong second coordinate! Shoot again!");
                return this.shoot();
            }
            placeCharacter(x, gameState.getAiTableForPlayer().getTable().get(y), gameState.getAiTable().getTable().get(y));
            return gameState.getAiTableForPlayer().getTable().get(y);

        } else {
            System.out.println("Wrong first coordinate! Shoot again!");
            return this.shoot();
        }

    }

    /**
     * Places down + or X to the given coordinate if it can.
     * Sends back the put character.
     *
     * @param x the x coordinate.
     * @param playerAiRow the player's ai table's row.
     * @param aiRow tha ai table's row.
     * @return the returning character.
     *
     */

    public char placeCharacter(int x, ArrayList<Character> playerAiRow, ArrayList<Character> aiRow) {
        char character = ' ';
        if (aiRow.get(x) == 'o') {
            System.out.println("Its a hit");
            character = '+';
        } else if (aiRow.get(x) == 'X' || aiRow.get(x) == '+') {
            System.out.println("It has already been shot! Shoot again!");
            character = aiRow.get(x);
            this.shoot();
        } else {
            System.out.println("Missed!");
            character = 'X';
        }
        playerAiRow.set(x, character);
        aiRow.set(x, character);
        return character;
    }
}
