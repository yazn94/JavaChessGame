package org.example.moveTypes;

import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.mainComponent.board.Board;

/**
 * this move class will help the rook and the Queen Pieces
 * to check for valid moves.
 */


public class HorizontalMove {

    public static boolean check(Board board, Move move) {
        // This method creates a copy of the left most one of source and destination positions
        // in order to modify it without changing the original objects inside the move instance.
        Position left = (Position) move.getLeftMostPosition().clone();
        Position right = move.getRightMostPosition();
        // Each on the same row.
        if (left.getRowIndex() != right.getRowIndex())
            return false;

        while (left.isInsideBoard()) {
            left.increaseColIndex();
            if (left.equals(right))
                return true;
            if (left.isInsideBoard() && !(board.isPositionClear(left))) {
                return false;
            }
        }

        return false;
    }
}
