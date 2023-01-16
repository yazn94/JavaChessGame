package org.example.moveTypes;


import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.mainComponent.board.Board;

/**
 * this class will help the Bishop and the Queen Pieces
 * to check if they can make the given move.
 */


public class DiagonalMove {

    public static boolean check(Board board, Move move) {
        // go from the higher one of source, and destination
        Position top = move.getHigherPosition(), down = move.getLowerPosition();

        return traverseLeftDownDiagonal(board, top, down)
                || traverseRightDownDiagonal(board, top, down);
    }

    /**
     * Check if the path from the top position to the down position is left diagonal and free.
     */
    private static boolean traverseLeftDownDiagonal(Board board, Position top, Position down) {
        // This method creates a copy of the higher one of source and destination positions
        // in order to modify it without changing the original objects inside the move instance.
        Position current = (Position) top.clone();
        while (current.isInsideBoard()) {
            current.increaseRowIndex();
            current.decreaseColIndex();
            if (current.equals(down)) {
                return true;
            }
            // The first condition is a short circuit to avoid outOfBounds
            if (current.isInsideBoard() && !board.isPositionClear(current))
                return false;
        }
        return false;
    }

    /**
     * Check if the path from the top position to the down position is right diagonal and free.
     */
    private static boolean traverseRightDownDiagonal(Board board, Position top, Position down) {
        // This method creates a copy of the Higher source and destination positions
        // in order to modify it without changing the original objects inside the move instance.
        Position current = (Position) top.clone();
        while (current.isInsideBoard()) {
            current.increaseRowIndex();
            current.increaseColIndex();
            if (current.equals(down)) {
                return true;
            }
            // The first condition is a short circuit to avoid outOfBounds
            if (current.isInsideBoard() && !board.isPositionClear(current))
                return false;
        }
        return false;
    }
}
