package hu.nye.progtech.torpedo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

/**
 * Model for storing the AI's and Player's table.
 */

@Repository
@Getter
@Setter
public class GameState {

    private TableVO currentTable;
    private TableVO aiTable;
    private boolean shouldExit;
    private boolean ran;

    public GameState(TableVO currentTable, TableVO aiTable) {
        this.currentTable = currentTable;
        this.aiTable = aiTable;
        shouldExit = false;
        ran = false;
    }
}
