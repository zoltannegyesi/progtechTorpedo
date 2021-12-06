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

    private Player player;
    private TableVO currentTable;
    private TableVO aiTableForPlayer;
    private TableVO aiTable;
    private boolean shouldExit;
    private boolean ran;

    public GameState(TableVO currentTable, TableVO aiTableForPlayer, TableVO aiTable) {
        this.currentTable = currentTable;
        this.aiTableForPlayer = aiTableForPlayer;
        this.aiTable = aiTable;
        this.shouldExit = false;
        this.ran = false;
    }
}
