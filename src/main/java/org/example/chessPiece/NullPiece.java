package org.example.chessPiece;

import org.example.generalUse.Move;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;

/**
 * The Flyweight pattern can be applied to optimize the use of NullPiece objects
 * in the chess board. As most of the board is usually occupied by null pieces and
 * they are replaced when a piece is captured, creating a single NullPiece object
 * can save memory and improve performance by reusing the same object instead of creating
 * a new one each time it is needed.
 */

public class NullPiece extends Piece {

    private static NullPiece flyWeight = new NullPiece();
    private NullPiece() {
        super(Color.NONE);
    }
    public static NullPiece getInstance() {
        return flyWeight;
    }
    @Override
    public boolean canMakeMove(Board board, Move move) {
        return false;
    }


    // To print empty spaces in the nullPiece places inside the board.
    @Override
    public String toString() {
        return "      ";
    }
}
