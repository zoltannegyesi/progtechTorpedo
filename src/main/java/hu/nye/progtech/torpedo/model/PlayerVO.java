package hu.nye.progtech.torpedo.model;

import java.util.Objects;

/**
 * Value Object for the player.
 */

public final class PlayerVO {

    private final String name;
    private int shipsRemaining = 0;
    private int emptyTilesRemaining = 0;
    private TableVO tableVO;

    public PlayerVO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getShipsRemaining() {
        return shipsRemaining;
    }

    public void setShipsRemaining(int shipsRemaining) {
        this.shipsRemaining = shipsRemaining;
    }

    public int getEmptyTilesRemaining() {
        return emptyTilesRemaining;
    }

    public void setEmptyTilesRemaining(int emptyTilesRemaining) {
        this.emptyTilesRemaining = emptyTilesRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerVO playerVO = (PlayerVO) o;
        return shipsRemaining == playerVO.shipsRemaining && emptyTilesRemaining
                == playerVO.emptyTilesRemaining && Objects.equals(name, playerVO.name)
                && Objects.equals(tableVO, playerVO.tableVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shipsRemaining, emptyTilesRemaining, tableVO);
    }

    @Override
    public String toString() {
        return "PlayerVO{" +
                "name='" + name + '\'' +
                ", shipsRemaining=" + shipsRemaining +
                ", emptyTilesRemaining=" + emptyTilesRemaining +
                ", tableVO=" + tableVO +
                '}';
    }
}
