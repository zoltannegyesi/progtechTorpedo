package hu.nye.progtech.torpedo.service.util;

import java.util.concurrent.atomic.AtomicInteger;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.TableVO;
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
     * @param tableVO that is being checked.
     * @return boolean.
     */
    public boolean areAllShipsDestroyed(TableVO tableVO) {
        AtomicInteger shipLeft = new AtomicInteger(0);
        AtomicInteger aiShipLeft = new AtomicInteger(0);
        gameState.getCurrentTable().getTable().forEach(row -> {
            row.forEach(item -> {
                if (item == 'o') {
                    shipLeft.incrementAndGet();
                }
            });
        });
        gameState.getCurrentTable().getTable().forEach(row -> {
            row.forEach(item -> {
                if (item == 'o') {
                    aiShipLeft.incrementAndGet();
                }
            });
        });
        if (shipLeft.get() == 0) {
            System.out.println("AI won!");
        } else if (aiShipLeft.get() == 0) {
            System.out.println("Player won");
        }
        return !(shipLeft.get() == 0 && aiShipLeft.get() == 0);
    }
}
