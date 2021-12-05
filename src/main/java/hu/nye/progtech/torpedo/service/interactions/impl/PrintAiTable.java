package hu.nye.progtech.torpedo.service.interactions.impl;

import hu.nye.progtech.torpedo.model.Ai;
import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.StepController;
import hu.nye.progtech.torpedo.service.interactions.Interaction;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import org.springframework.stereotype.Service;

/**
 * Command used to request the printing of AI's map.
 * Only used for testing.
 */

@Service
public class PrintAiTable implements Interaction {
    private static final String PRINTAITABLE_COMMAND = "aitable";
    private boolean usable = true;

    private final TablePrinter tablePrinter;
    GameState gameState;

    public PrintAiTable(TablePrinter tablePrinter, GameState gameState) {
        this.tablePrinter = tablePrinter;
        this.gameState = gameState;
    }

    /**
     * //System.out.println(tablePrinter.drawTable(gameState.getAiTable.getTable()));.
     *
     * @param in asd
     * @param stepController sad
     *
     */
    @Override
    public void process(String in, StepController stepController) {

        stepController.performStep();
    }

    @Override
    public boolean isEqualToCommand(String in) {
        return PRINTAITABLE_COMMAND.equals(in);
    }

    @Override
    public String getName() {
        return PRINTAITABLE_COMMAND;
    }

    @Override
    public void setUsable(boolean bool) {
        this.usable = bool;
    }

    @Override
    public boolean isUsable() {
        return this.usable;
    }
}
