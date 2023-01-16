package org.example.moveTypes;

import org.example.generalUse.Move;
import org.example.mainComponent.board.Board;

/**
 * this class will help the Kniight Pieces
 * to check for valid moves.
 */


public class LShapeMove {

    // no need for a board object, and that's because
    // the knight can jump over pieces, so it doesn't matter
    // whether the path is clear or not.

    public static boolean check(Board board, Move move) {
        if (move.getAbsColDiff() == 1 && move.getAbsRowDiff() == 2)
            return true;
        return move.getAbsColDiff() == 2 && move.getAbsRowDiff() == 1;
    }
}
