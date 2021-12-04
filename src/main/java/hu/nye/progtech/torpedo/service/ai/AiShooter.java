package hu.nye.progtech.torpedo.service.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.nye.progtech.torpedo.model.GameState;
import org.springframework.stereotype.Service;

/**
 * Component for AI shooting.
 */

@Service
public class AiShooter {
    private final Random rnd;

    public AiShooter() {
        this.rnd = new Random();
    }

    /**
     * generates a random coordinate,
     * and places a '+' or 'X' on the coordinate.
     *
     *
     */

    public ArrayList<Character> shoot(ArrayList<Character> row, int x) {
        if (row.get(x) == ' ') {
            row.set(x, 'X');
            System.out.println("AI shot and missed!");
            return row;
        } else if (row.get(x) == 'o') {
            row.set(x, '+');
            System.out.println("AI shot and hit a ship");
            return row;
        } else if (row.get(x) == '+' || row.get(x) == 'X'){
            return this.shoot(row, rnd.nextInt(10));
        } else {
            return null;
        }
    }
}
