package org.example.chessPiece;

import org.example.moveTypes.HorizontalMove;
import org.example.moveTypes.VerticalMove;
import org.example.generalUse.ANSI;
import org.example.generalUse.Move;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;


public class Rook extends Piece {

    public Rook(Color c) {
        super(c);
    }

    @Override
    public boolean canMakeMove(Board board, Move move) {
        return HorizontalMove.check(board, move) || VerticalMove.check(board, move);
    }


    @Override
    public String toString() {
        if (isWhite())
            return " Rook ";
        return ANSI.BLACK_BOLD + " Rook " + ANSI.RESET;
    }
}
