package hu.nye.progtech.torpedo.service.ai;

import java.util.Random;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
import org.springframework.stereotype.Service;

/**
 * Component for AI shooting.
 */

@Service
public class AiShooter {
    private final GameState gameState;
    private final Random rnd;

    public AiShooter(GameState gameState) {
        this.gameState = gameState;
        this.rnd = new Random();
    }

    /**
     * generates a random coordinate,
     * and places a '+' or 'X' on the coordinate.
     *
     * @param ai that shoots.
     */


    public void shoot(Ai ai) {
        int x = rnd.nextInt(10);
        int y = rnd.nextInt(10);
        if (gameState.getCurrentTable().getTable().get(y).get(x) == ' ') {
            gameState.getCurrentTable().getTable().get(y).set(x, 'X');
            System.out.println("AI shot and missed!");
        } else if (gameState.getCurrentTable().getTable().get(y).get(x) == 'o') {
            gameState.getCurrentTable().getTable().get(y).set(x, 'X');
            System.out.println("AI shot and hit a ship");
        } else {
            this.shoot(ai);
        }
    }
}
