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

    public GameState(TableVO currentTable, TableVO aiTable) {
        this.currentTable = currentTable;
        this.aiTable = aiTable;
    }
}
