package hu.nye.progtech.torpedo.service.interactions.impl;

import hu.nye.progtech.torpedo.model.Ai;
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
    private final Ai ai;

    public PrintAiTable(TablePrinter tablePrinter, Ai ai) {
        this.tablePrinter = tablePrinter;
        this.ai = ai;
    }

    @Override
    public void process(String in, StepController stepController) {
        System.out.println(tablePrinter.drawTable(ai.getTable()));
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
