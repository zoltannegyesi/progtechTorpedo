package hu.nye.progtech.torpedo.service.game;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.Player;
import hu.nye.progtech.torpedo.model.TableVO;
import hu.nye.progtech.torpedo.service.interactions.impl.Save;
import hu.nye.progtech.torpedo.service.table.TableCreator;
import hu.nye.progtech.torpedo.service.util.MapUtil;
import hu.nye.progtech.torpedo.ui.UserInput;
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

    UserInput userInput;

    @Autowired
    public GameController(StepController stepController,
                          MapUtil mapUtil, GameState gameState,
                          TableCreator tableCreator, Ai ai, UserInput userInput) {
        this.stepController = stepController;
        this.mapUtil = mapUtil;
        this.gameState = gameState;
        this.tableCreator = tableCreator;
        this.ai = ai;
        this.userInput = userInput;
    }


    private boolean isGameRunning() {
        return !gameState.isShouldExit() && mapUtil.areAllShipsDestroyed();
    }

    private void createStartingTables() {
        tableCreator.createTable(ai.getTable());
        TableVO tableVO = new TableVO();
        tableVO.setTable(ai.createTable());
        gameState.setAiTable(tableVO);
        tableCreator.createTable(gameState.getCurrentTable());
        tableCreator.createTable(gameState.getAiTableForPlayer());
    }

    private void createPlayer() {
        System.out.println("Type in your name: ");
        String name = userInput.scanInput();
        gameState.setPlayer(new Player());
        gameState.getPlayer().setWins(0L);
        gameState.getPlayer().setName(name);
        System.out.println("Hello " + name + "!");
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        createPlayer();
        createStartingTables();
        while (isGameRunning()) {
            gameState.setRan(true);
            stepController.performStep();
            if (!gameState.isShouldExit()) {
                stepController.performAiStep(gameState.getCurrentTable().getTable());
            }
        }
    }


}
