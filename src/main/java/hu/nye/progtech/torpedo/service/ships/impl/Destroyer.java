package hu.nye.progtech.torpedo.service.ships.impl;

import hu.nye.progtech.torpedo.service.ships.Ship;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * A Destroyer Ship, that is 2 tiles long.
 */


@Getter
@Service
public class Destroyer implements Ship {
    private final String name = "Destroyer";
    private final int size = 2;
    private boolean used = false;

    @Override
    public void useShip() {
        this.used = true;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isUsed() {
        return this.used;
    }
}
