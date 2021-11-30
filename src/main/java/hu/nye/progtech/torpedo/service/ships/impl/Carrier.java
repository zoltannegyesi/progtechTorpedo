package hu.nye.progtech.torpedo.service.ships.impl;

import hu.nye.progtech.torpedo.service.ships.Ship;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * A Carrier Ship, that is 5 tiles long.
 */


@Getter
@Service
public class Carrier implements Ship {
    private final String name = "Carrier";
    private final int size = 5;
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
