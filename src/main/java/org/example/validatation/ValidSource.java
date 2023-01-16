package org.example.validatation;

import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.mainComponent.board.Board;
import org.example.mainComponent.Player;

/**
 * This class is part of the chain of validators for validating a move.
 * It checks that the source position of the move
 * contains a piece that belongs to the player making the move.
 */

public class ValidSource extends Validator {
    @Override
    public boolean isValid(Board board, Move move, Player player) {
        Position source = move.getSource();
        if (board.isPositionClear(source))
            return false;
        if (player.getColor() == board.getPieceAtPosition(source).getColor()) {
            if (nextValidator == null)
                return true;
            return nextValidator.isValid(board, move, player);
        }
        return false;
    }
}
