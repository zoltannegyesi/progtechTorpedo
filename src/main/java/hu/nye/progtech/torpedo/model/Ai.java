package hu.nye.progtech.torpedo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

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
    private final Random rnd;

    public Ai(AiTableCreator aiTableCreator, TableVO table, List<Ship> ships) {
        this.aiTableCreator = aiTableCreator;
        this.table = table;
        this.ships = ships;
        this.rnd = new Random();
    }

    /**
     * Calls putDownShip for every  ship.
     */

    public ArrayList<ArrayList<Character>> createTable() {
        AtomicReference<ArrayList<ArrayList<Character>>> aiTable = new AtomicReference<>(new ArrayList<>());
        ships.stream().filter(ship -> !ship.isUsed()).forEach(ship ->
                aiTable.set(aiTableCreator.putDownShip(this.table.getTable(), 10, ship.getSize(), rnd.nextInt(4)))
        );
        return aiTable.get();
    }

}
