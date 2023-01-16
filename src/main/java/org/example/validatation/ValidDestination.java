package org.example.validatation;

import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.mainComponent.board.Board;
import org.example.mainComponent.Player;

/**
 * This class is part of the chain of validation process,
 * it validates the destination position of a move to ensure that it is either:
 * - empty
 * - contains an opponent's piece that can be captured
 * The destination position must not contain the player's own piece.
 */


public class ValidDestination extends Validator {
    @Override
    public boolean isValid(Board board, Move move, Player player) {
        Position source = move.getSource();

        if (board.getPieceAtPosition(move.getDestination()).getColor() == player.getColor())
            return false;

        if (nextValidator == null)
            return true;

        return nextValidator.isValid(board, move, player);
    }
}
