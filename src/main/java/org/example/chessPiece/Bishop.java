package org.example.chessPiece;

import org.example.moveTypes.DiagonalMove;
import org.example.generalUse.ANSI;
import org.example.generalUse.Move;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;


public class Bishop extends Piece {
    public Bishop(Color c) {
        super(c);
    }

    @Override
    public boolean canMakeMove(Board board, Move move) {
        return DiagonalMove.check(board, move);
    }

    @Override
    public String toString() {
        if (isWhite())
            return "Bishop";
        return ANSI.BLACK_BOLD + "Bishop" + ANSI.RESET;
    }
}
