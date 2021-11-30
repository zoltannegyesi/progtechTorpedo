package hu.nye.progtech.torpedo.model;

import hu.nye.progtech.torpedo.model.TableVO;
import hu.nye.progtech.torpedo.service.ai.AiShooter;
import hu.nye.progtech.torpedo.service.ai.AiTableCreator;
import hu.nye.progtech.torpedo.ui.TablePrinter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


/**
 * Model for the AI.
 */

@Service
@Getter
@Setter
public class Ai {

    private final AiShooter aiShooter;
    private TableVO table;
    private final AiTableCreator aiTableCreator;
    private final TablePrinter tablePrinter;

    public Ai(TableVO table, AiTableCreator aiTableCreator, TablePrinter tablePrinter, AiShooter aiShooter) {
        this.table = table;
        this.aiTableCreator = aiTableCreator;
        this.tablePrinter = tablePrinter;
        this.aiShooter = aiShooter;
    }

    public void createTable() {
        aiTableCreator.createAiTable(this);
    }

    public void shoot() {
        this.aiShooter.shoot(this);
    }
}
