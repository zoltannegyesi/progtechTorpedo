package hu.nye.progtech.torpedo.service.game;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.table.TableCreator;
import hu.nye.progtech.torpedo.service.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Component, that controls the flow of the game.
 */

@Service
public class GameController {


    private final StepController stepController;
    private final MapUtil mapUtil;
    private final GameState gameState;
    private final TableCreator tableCreator;
    private final Ai ai;

    @Autowired
    public GameController(StepController stepController, MapUtil mapUtil, GameState gameState, TableCreator tableCreator, Ai ai) {
        this.stepController = stepController;
        this.mapUtil = mapUtil;
        this.gameState = gameState;
        this.tableCreator = tableCreator;
        this.ai = ai;
    }


    private boolean isGameRunning() {
        return !gameState.isShouldExit() && mapUtil.areAllShipsDestroyed();
    }

    private void createStartingTables() {
        tableCreator.createTable(ai.getTable());
        ai.createTable();
        tableCreator.createTable(gameState.getCurrentTable());
        tableCreator.createTable(gameState.getAiTable());
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        createStartingTables();
        while (isGameRunning()) {
            gameState.setRan(true);
            stepController.performStep();
            stepController.performAiStep(gameState);
        }
    }


}
