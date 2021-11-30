package hu.nye.progtech.torpedo.service.ai;

import java.util.List;
import java.util.Random;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.service.ships.Ship;
import org.springframework.stereotype.Service;

/**
 * Component for creating AI's table.
 */

@Service
public class AiTableCreator {

    private final List<Ship> ships;
    private final Random rand;

    public AiTableCreator(List<Ship> ships) {
        this.ships = ships;
        rand = new Random();
    }

    /**
     * Creates AI's table,
     * and puts down the ships randomly.
     *
     * @param ai ai, that's table is being created.
     */
    public void createAiTable(Ai ai) {
        ships.forEach(ship -> {
            boolean cannotPutDown = true;
            while (cannotPutDown) {
                boolean notUsed = true;
                int x = 0;
                int y = 0;
                while (notUsed) {
                    int x1 = rand.nextInt(10);
                    int y1 = rand.nextInt(10);
                    if (!(ai.getTable().getTable().get(y1).get(x1) == '+')) {
                        notUsed = false;
                        x = x1;
                        y = y1;
                    }
                }
                boolean put = false;
                while (!put) {
                    switch (rand.nextInt(4)) {
                        case 0: //north
                            if ((y - ship.getSize()) >= 0) {
                                if (!(ai.getTable().getTable().get(y - 1).get(x) == 'o')) {
                                    for (int i = 0; i < ship.getSize(); i++) {
                                        ai.getTable().getTable().get(y - i).set(x, 'o');
                                    }
                                    cannotPutDown = false;
                                    put = true;
                                }
                            }
                            break;

                        case 1: //east
                            if ((x + ship.getSize() - 1) <= 9) {
                                if (!(ai.getTable().getTable().get(y).get(x + 1) == 'o')) {
                                    for (int i = 0; i < ship.getSize(); i++) {
                                        ai.getTable().getTable().get(y).set(x + i, 'o');
                                    }
                                    cannotPutDown = false;
                                    put = true;
                                }
                            }
                            break;

                        case 2: // south
                            if ((y + ship.getSize() - 1) <= 9) {
                                if (!(ai.getTable().getTable().get(y + 1).get(x) == 'o')) {
                                    for (int i = 0; i < ship.getSize(); i++) {
                                        ai.getTable().getTable().get(y + i).set(x, 'o');
                                    }
                                    cannotPutDown = false;
                                    put = true;
                                }

                            }
                            break;
                        default: //west
                            if ((x - ship.getSize() - 1) >= 0) {
                                if (!(ai.getTable().getTable().get(y).get(x - 1) == 'o')) {
                                    for (int i = 0; i < ship.getSize(); i++) {
                                        ai.getTable().getTable().get(y).set(x - i, 'o');
                                    }
                                    cannotPutDown = false;
                                    put = true;
                                }
                            }
                            break;
                    }
                }
            }
        });
    }
}
