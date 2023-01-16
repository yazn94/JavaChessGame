package org.example.chessPiece;

import org.example.generalUse.ANSI;
import org.example.generalUse.Move;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;


public class King extends Piece {
    public King(Color c) {
        super(c);
    }

    @Override
    public boolean canMakeMove(Board board, Move move) {
        // Check if the destination is one position from the kings adjacent positions.
        int rowDiff = move.getRowDiff();
        int colDiff = move.getColDiff();
        return (Math.abs(rowDiff) <= 1 && Math.abs(colDiff) <= 1);
    }

    @Override
    public String toString() {
        if (isWhite()) {
            return ANSI.UNDER_LINED + " King " + ANSI.RESET;
        } else {
            return ANSI.BLACK_BOLD + ANSI.UNDER_LINED + " King " + ANSI.RESET;
        }
    }

    public static String toStringInThreat() {
        return ANSI.RED + ANSI.UNDER_LINED + " King " + ANSI.RESET;
    }
}