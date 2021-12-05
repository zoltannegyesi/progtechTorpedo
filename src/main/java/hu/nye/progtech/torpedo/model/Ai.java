package hu.nye.progtech.torpedo.model;

import java.util.List;

import hu.nye.progtech.torpedo.model.ships.Ship;
import hu.nye.progtech.torpedo.service.ai.AiTableCreator;
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

    private final AiTableCreator aiTableCreator;
    private final TableVO table;
    private final List<Ship> ships;

    public Ai(AiTableCreator aiTableCreator, GameState gameState, TableVO table, List<Ship> ships) {
        this.aiTableCreator = aiTableCreator;
        this.table = table;
        this.ships = ships;

    }

    /**
     * Calls putDownShip for every ship.
     */

    public void createTable() {
        ships.forEach(ship ->
                aiTableCreator.putDownShip(this.table.getTable(), 10, ship.getSize())
        );
    }

}
