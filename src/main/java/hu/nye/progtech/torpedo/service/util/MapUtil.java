package hu.nye.progtech.torpedo.service.util;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
import org.springframework.stereotype.Service;

/**
 * Class to see if all ships are destroyed.
 */

@Service
public class MapUtil {

    private final GameState gameState;
    private final Ai ai;

    public MapUtil(GameState gameState, Ai ai) {
        this.gameState = gameState;
        this.ai = ai;
    }

    /**
     * Checks, if the given tableVO has unshoted ships left.
     *
     * @return boolean.
     */
    public boolean areAllShipsDestroyed() {
        if (gameState.isRan()) {
            AtomicBoolean shipLeft = new AtomicBoolean(true);
            AtomicBoolean aiShipLeft = new AtomicBoolean(true);
            gameState.getCurrentTable().getTable().forEach(row -> {
                row.forEach(item -> {
                    if (item == 'o') {
                        shipLeft.set(false);
                    }
                });
            });
            gameState.getCurrentTable().getTable().forEach(row -> {
                row.forEach(item -> {
                    if (item == 'o') {
                        shipLeft.set(false);
                    }
                });
            });
            if (shipLeft.get()) {
                System.out.println("AI won!");
            } else if (aiShipLeft.get()) {
                System.out.println("Player won");
            }
            return !(shipLeft.get() && shipLeft.get());

        } else {
           return true;
        }


    }
}
