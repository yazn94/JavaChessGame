package org.example.validatation;

import org.example.generalUse.Move;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;
import org.example.mainComponent.Player;

/**
 * This class is part of the chain of validation process,
 * it makes sure that the king of the player who is making the move
 * will not be in check after making the move.
 */


public class IsKingSafe extends Validator {

    @Override
    public boolean isValid(Board board, Move move, Player player) {
        boolean kingIsChecked;
        if (player.getColor() == Color.WHITE) {
            kingIsChecked = StatusChecker.isWhiteKingInCheck(board);
        } else {
            kingIsChecked = StatusChecker.isBlackKingInCheck(board);
        }
        return !kingIsChecked;
    }
}
