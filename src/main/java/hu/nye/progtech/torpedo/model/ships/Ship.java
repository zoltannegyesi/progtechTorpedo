package hu.nye.progtech.torpedo.model.ships;

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
