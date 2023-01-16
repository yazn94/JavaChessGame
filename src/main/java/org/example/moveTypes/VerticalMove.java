package org.example.moveTypes;

import org.example.chessPiece.NullPiece;
import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.mainComponent.board.Board;


public class VerticalMove {

    public static boolean check(Board board, Move move) {
        // This method creates a copy of the higher one of source and destination positions
        // in order to modify it without changing the original objects inside the move instance.
        Position top = (Position) move.getHigherPosition().clone();
        Position down = move.getLowerPosition();

        // Each on the same column.
        if (top.getColIndex() != down.getColIndex())
            return false;

        while (top.isInsideBoard()) {
            top.increaseRowIndex();
            if (top.equals(down))
                return true;
            if (top.isInsideBoard() && !(board.getPieceAtPosition(top) instanceof NullPiece)) {
                return false;
            }
        }
        return false;
    }
}
