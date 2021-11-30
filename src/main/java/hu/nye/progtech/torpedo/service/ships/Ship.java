package hu.nye.progtech.torpedo.service.ships;

/**
 * Interface that represents a ship,
 * which can be put down, or can be shot.
 */


public interface Ship {

    void useShip();

    int getSize();

    String getName();

    boolean isUsed();
}
