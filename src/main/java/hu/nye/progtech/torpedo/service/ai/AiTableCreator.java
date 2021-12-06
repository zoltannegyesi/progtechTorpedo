package hu.nye.progtech.torpedo.service.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

/**
 * Component for creating AI's table.
 */

@Service
public class AiTableCreator {


    private final Random rand;

    public AiTableCreator() {
        rand = new Random();
    }

    /**
     * Get an empty coordinate.
     *
     * @param table table, that is being searched
     * @param size  size of the table
     * @return int[]
     */

    public List<Integer> searchCoordinate(ArrayList<ArrayList<Character>> table, int size) {
        List<Integer> coordinates = new ArrayList<>();

        boolean notUsed = true;
        while (notUsed) {
            int x1 = rand.nextInt(size);
            int y1 = rand.nextInt(size);
            if (!(table.get(y1).get(x1) == 'o')) {
                notUsed = false;
                coordinates.add(x1);
                coordinates.add(x1);
            }
        }
        return coordinates;
    }

    /**
     * Puts down the ship to the given coordinate.
     *
     * @param num      where should it be facing
     * @param table    the table that is being changed
     * @param shipSize the size of the sip
     * @param x        the x coordinate
     * @param y        the y coordinate
     * @return {@link ArrayList} of {@link ArrayList} of boolean
     */

    public ArrayList<ArrayList<Character>> putToCoordinate(int num, ArrayList<ArrayList<Character>> table, int shipSize, int x, int y) {

        switch (num) {
            case 0: // north
                for (int i = 0; i < shipSize; i++) {
                    table.get(y + i).set(x, 'o');
                }
                break;

            case 1: // east
                for (int i = 0; i < shipSize; i++) {
                    table.get(y).set(x + i, 'o');
                }
                break;

            case 2: // south
                for (int i = 0; i < shipSize; i++) {
                    table.get(y - i).set(x, 'o');
                }
                break;
            default: // west
                for (int i = 0; i < shipSize; i++) {
                    table.get(y).set(x - i, 'o');
                }
                break;

        }
        return table;
    }

    /**
     * Check if the coordinate is in the boundaries of the map.
     *
     * @param num      the coordinate
     * @param shipSize the size of the ship
     * @return boolean
     */

    public boolean canPutDown(int num, int shipSize, int size) {
        return ((num + shipSize - 1) <= size - 1 && (num - shipSize) >= 0);
    }

    /**
     * Creates AI's table,
     * and puts down the ships randomly.
     *
     * @param table    table which is being created
     * @param size     the size of the map
     * @param shipSize the size of the map
     */

    public ArrayList<ArrayList<Character>> putDownShip(ArrayList<ArrayList<Character>> table, int size, int shipSize, int num) {

        List<Integer> coordinates = searchCoordinate(table, size);
        int x = coordinates.get(0);
        int y = coordinates.get(1);
        switch (num) {
            case 0: // north
                if (canPutDown(y, shipSize, size)) {
                    if (!(table.get(y + 1).get(x) == 'o')) {
                        putToCoordinate(num, table, shipSize, x, y);
                        return table;
                    }
                } else {
                    return this.putDownShip(table, size, shipSize, 1);
                }
                break;

            case 1: // east
                if (canPutDown(x, shipSize, size)) {
                    if (!(table.get(y).get(x + 1) == 'o')) {
                        putToCoordinate(num, table, shipSize, x, y);
                        return table;
                    }
                } else {
                    return this.putDownShip(table, size, shipSize, 2);
                }
                break;

            case 2: // south
                if (canPutDown(y, shipSize, size)) {
                    if (!(table.get(y - 1).get(x) == 'o')) {
                        putToCoordinate(num, table, shipSize, x, y);
                        return table;
                    }
                } else {
                    return this.putDownShip(table, size, shipSize, 3);
                }
                break;
            default: // west
                if (canPutDown(x, shipSize, size)) {
                    if (!(table.get(y).get(x - 1) == 'o')) {
                        putToCoordinate(num, table, shipSize, x, y);
                        return table;
                    }
                } else {
                    return this.putDownShip(table, size, shipSize, 0);
                }
                break;
        }
        return null;
    }
}
