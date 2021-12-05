package hu.nye.progtech.torpedo.service.interactions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.nye.progtech.torpedo.model.TableVO;
import hu.nye.progtech.torpedo.service.util.CoordinateConverter;
import hu.nye.progtech.torpedo.ui.UserInput;
import org.springframework.stereotype.Service;

/**
 * Component responsible for putting down the ships.
 */

@Service
public class ShipPutter {

    private final UserInput userInput;
    private final CoordinateConverter coordinateConverter;

    public ShipPutter(UserInput userInput, CoordinateConverter coordinateConverter) {
        this.userInput = userInput;
        this.coordinateConverter = coordinateConverter;
    }

    /**
     * Puts down the ship or checks if it is too long,
     * or too short for put.
     *
     * @param x1       x1 coordinate
     * @param x2       x2 coordinate
     * @param y1       y1 coordinate
     * @param y2       y2 coordinate
     * @param shipSize the size of the ship
     * @param table    the table, which is being modified
     * @return boolean
     */

    public boolean putDownShip(int x1, int x2, int y1, int y2, int shipSize, ArrayList<ArrayList<Character>> table) {
        int differenceX = Math.abs(x1 - x2);
        int differenceY = Math.abs(y1 - y2);
        if (differenceX == shipSize - 1 || differenceY == shipSize - 1) {
            if (x1 == x2) {
                if (y1 > y2) {
                    for (int i = 0; i < shipSize; i++) {
                        table.get(y1 - i).set(x1, 'o');
                    }
                } else {
                    for (int i = 0; i < shipSize; i++) {
                        table.get(y1 + i).set(x1, 'o');
                    }
                }
            } else {
                if (x1 > x2) {
                    for (int i = 0; i < shipSize; i++) {
                        table.get(y1).set(x1 - i, 'o');
                    }
                } else {
                    for (int i = 0; i < shipSize; i++) {
                        table.get(y1).set(x1 + i, 'o');
                    }
                }
            }
            System.out.println("Ship is put down");
            return true;
        } else if (differenceX > shipSize - 1 || differenceY > shipSize - 1) {
            System.out.println("Too long for this ship!");
            return false;
        } else {
            System.out.println("Too short for this ship!");
            return false;
        }
    }

    /**
     * Checks if the given coordinate's second coordinate is 10,
     * or 1 digit long. And turns it into 1 digit.
     *
     * @param coordinate that is being checked
     * @return the coordinate.
     */

    public int coordinateChecker(String coordinate) {
        int result = 0;
        if (coordinate.length() == 3 && coordinate.toCharArray()[1] == '1' && coordinate.toCharArray()[2] == '0') {
            result = 9;
        } else {
            result = coordinateConverter.checkCoordinate(coordinate.toCharArray()[1]);
        }
        return result;
    }

    /**
     * Reads input, calls the coordinateChecker method,
     * and the putDownShip method.
     *
     * @param shipSize the size of the ship
     * @param table    the table, that the ship is put on
     * @return boolean
     */

    public boolean managePut(int shipSize, ArrayList<ArrayList<Character>> table) {
        System.out.println("The ship is " + shipSize + " tile long.");
        System.out.println("Type in the starting and the ending coordinate: ");
        String input = userInput.scanInput();
        List<String> coordinates = Arrays.asList(input.split(" "));
        int x1 = coordinateConverter.checkCoordinate(coordinates.get(0).toCharArray()[0]);
        int x2 = coordinateConverter.checkCoordinate(coordinates.get(1).toCharArray()[0]);
        int y1 = coordinateChecker(coordinates.get(0));
        int y2 = coordinateChecker(coordinates.get(1));
        if (y1 == 0 || y2 == 0) {
            System.out.println("Invalid input");
            return false;
        } else {
            return putDownShip(x1, x2, y1, y2, shipSize, table);
        }
    }

}
