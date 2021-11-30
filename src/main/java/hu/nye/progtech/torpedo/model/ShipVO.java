package hu.nye.progtech.torpedo.model;

import java.util.Objects;

/**
 * Value Object for ships.
 */

public class ShipVO {

    private final int size;
    private final int coordinateX;
    private final int coordinateY;

    public ShipVO(int size, int coordinateX, int coordinateY) {
        this.size = size;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getSize() {
        return size;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShipVO shipVO = (ShipVO) o;
        return size == shipVO.size && coordinateX == shipVO.coordinateX && coordinateY == shipVO.coordinateY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        return "ShipVO{" +
                "size=" + size +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                '}';
    }
}
