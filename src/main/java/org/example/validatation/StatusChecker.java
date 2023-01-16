package org.example.validatation;

import org.example.chessPiece.Piece;
import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;
import org.example.mainComponent.Player;

/**
 * This is a utility class which provide methods that look for checks and checkmates.
 */


public class StatusChecker {

    private static final ValidatorFacade validatorFacade = ValidatorFacade.getInstance();

    public static boolean isWhiteKingInCheck(Board board) {
        Position kingPosition = board.getWhiteKingPosition();
        // For each black piece, try to attack the white king.
        for (Position currentPosition : Board.allBoardPositions) {
            Piece currentPiece = board.getPieceAtPosition(currentPosition);
            Move move = new Move(currentPosition, kingPosition);
            if (currentPiece.isBlack() && currentPiece.canMakeMove(board, move))
                return true;
        }
        return false;
    }

    public static boolean isBlackKingInCheck(Board board) {
        Position kingPosition = board.getBlackKingPosition();
        // For each white piece, try to attack the black king.
        for (Position currentPosition : Board.allBoardPositions) {
            Piece currentPiece = board.getPieceAtPosition(currentPosition);
            Move move = new Move(currentPosition, kingPosition);
            if (currentPiece.isWhite() && currentPiece.canMakeMove(board, move))
                return true;
        }
        return false;
    }

    /**
     * Checks whether the king of the given player is checkmated or not.
     */
    public static boolean isCheckmated(Board board, Player player) {
        // If the piece isn't checked, then it's not checkmated.
        if (player.getColor() == Color.WHITE) {
            if (!isWhiteKingInCheck(board))
                return false;
        } else {
            if (!isBlackKingInCheck(board))
                return false;
        }

        /*
           Iterate through all possible moves for the given player and validate each move
           using the ValidatorFacade. If a move passes all layers of validation
           (ValidSource -> ValidDestination -> CanPieceMakeMove -> IsKingSafe), it means that
            there exists a move that can take the king out of check and therefore the game is not a checkmate.
         */

        for (Position first : Board.allBoardPositions) {
            for (Position second : Board.allBoardPositions) {
                Move move = new Move(first, second);
                if (validatorFacade.validMove(board, move, player)) {
                    return false;
                }
            }
        }
        return true;
    }
}
