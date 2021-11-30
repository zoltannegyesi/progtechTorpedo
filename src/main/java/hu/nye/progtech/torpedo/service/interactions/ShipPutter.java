package hu.nye.progtech.torpedo.service.interactions;

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
     * Checks if the input coordinate is valid,
     * and puts down the given ship,
     * and sets the ship to used.
     *
     * @param shipSize the size of the ship.
     * @param tableVO  the table that the ship is put to.
     * @return boolean.
     */
    public boolean putShip(int shipSize, TableVO tableVO) {
        System.out.println("The ship is " + shipSize + " tile long.");
        System.out.println("Type in the starting and the ending coordinate: ");
        String input = userInput.scanInput();
        List<String> coordinates = Arrays.asList(input.split(" "));
        int x1 = coordinateConverter.checkCoordinate(coordinates.get(0).toCharArray()[0]);
        int x2 = coordinateConverter.checkCoordinate(coordinates.get(1).toCharArray()[0]);
        int y1 = -1;
        int y2 = -1;
        if (coordinates.get(0).length() == 3 && coordinates.get(0).toCharArray()[1] == '1' && coordinates.get(0).toCharArray()[2] == '0') {

            y1 = 9;
        } else {
            y1 = coordinateConverter.checkCoordinate(coordinates.get(0).toCharArray()[1]);
        }
        if (coordinates.get(1).length() == 3 && coordinates.get(1).toCharArray()[1] == '1' && coordinates.get(1).toCharArray()[2] == '0') {
            y2 = 9;
        } else {
            y2 = coordinateConverter.checkCoordinate(coordinates.get(1).toCharArray()[1]);
        }
        if (y1 == -1 || y2 == -1) {
            System.out.println("Invalid input");
            return false;
        } else {
            int firstCoordinateDifference = Math.abs(x1 - x2);
            int secondCoordinateDifference = Math.abs(y1 - y2);
            if (firstCoordinateDifference == shipSize - 1 || secondCoordinateDifference == shipSize - 1) {
                if (x1 == x2) {
                    if (y1 > y2) {
                        for (int i = 0; i < shipSize; i++) {
                            tableVO.getTable().get(y1 - i).set(x1, 'o');
                        }
                    } else {
                        for (int i = 0; i < shipSize; i++) {
                            tableVO.getTable().get(y1 + i).set(x1, 'o');
                        }
                    }

                } else {
                    if (x1 > x2) {
                        for (int i = 0; i < shipSize; i++) {
                            tableVO.getTable().get(y1).set(x1 - i, 'o');
                        }
                    } else {
                        for (int i = 0; i < shipSize; i++) {
                            tableVO.getTable().get(y1).set(x1 + i, 'o');
                        }
                    }
                }
                System.out.println("Ship is put down");
                return true;
            } else if (firstCoordinateDifference > shipSize - 1 || secondCoordinateDifference > shipSize - 1) {
                System.out.println("Too long for this ship!");
                return false;
            } else {
                System.out.println("Too short for this ship!");
                return false;
            }
        }
    }
}
