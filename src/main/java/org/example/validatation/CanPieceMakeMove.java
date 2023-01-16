package org.example.validatation;

import org.example.chessPiece.Piece;
import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.mainComponent.board.Board;
import org.example.mainComponent.Player;

/**
 * This class is part of the chain of validation process. It ensures that the piece
 * at the source position of the given move is capable of making that move.
 * This is determined by calling the `canMakeMove` method on the piece at the source position.
 */


public class CanPieceMakeMove extends Validator {
    @Override
    public boolean isValid(Board board, Move move, Player player) {
        Position source = move.getSource();
        Piece attackingPiece = board.getPieceAtPosition(source);

        if (!attackingPiece.canMakeMove(board, move))
            return false;

        if (nextValidator != null) {
            // try the move and get the result
            board.doMove(move);
            boolean result = nextValidator.isValid(board, move, player);
            board.undoLastMove(move);
            return result;
        }

        return true;
    }

}
