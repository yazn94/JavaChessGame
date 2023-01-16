package org.example.chessPiece;

import org.example.moveTypes.LShapeMove;
import org.example.generalUse.ANSI;
import org.example.generalUse.Move;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;


public class Knight extends Piece {

    public Knight(Color c) {
        super(c);
    }

    @Override
    public boolean canMakeMove(Board board, Move move) {
        return LShapeMove.check(board, move);
    }

    @Override
    public String toString() {
        if (isWhite())
            return "Knight";
        return ANSI.BLACK_BOLD + "Knight" + ANSI.RESET;
    }

}
