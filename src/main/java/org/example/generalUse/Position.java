package org.example.generalUse;

/**
 * The Position class represents a location on a chess board
 * and provides methods for interacting with position.
 */

public class Position {

    private int rowIndex;
    private int colIndex;

    public Position(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }


    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void increaseRowIndex() {
        rowIndex++;
    }

    public void increaseColIndex() {
        colIndex++;
    }

    public void decreaseColIndex() {
        colIndex--;
    }

    public boolean isInsideBoard() {
        return colIndex >= 0 && colIndex < 8 && rowIndex >= 0 && rowIndex < 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rowIndex == position.rowIndex && colIndex == position.colIndex;
    }

    @Override
    public Object clone() {
        return new Position(this.rowIndex, this.colIndex);
    }
}