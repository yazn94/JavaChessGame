package org.example.chessPiece;

import org.example.generalUse.ANSI;
import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;


public class Pawn extends Piece {

    public Pawn(Color c) {
        super(c);
    }

    @Override
    public boolean canMakeMove(Board board, Move move) {

        return canMoveOneStepForward(board, move) || canMoveTwoStepsForward(board, move)
                || canMoveOneDiagonalForward(board, move);
    }

    public boolean canMoveOneStepForward(Board board, Move move) {
        // Making sure the move is one row forward.
        if (isWhite()) {
            if (!(move.getRowDiff() == -1 && move.getColDiff() == 0))
                return false;
        } else {
            if (!(move.getRowDiff() == 1 && move.getColDiff() == 0))
                return false;
        }
        // To move the Pawn one step forward, it's front should be empty.
        return board.isPositionClear(move.getDestination());
    }

    public boolean canMoveTwoStepsForward(Board board, Move move) {

        // The pawn could move two steps only if this move is
        // the very step for it.
        if (!isFirstMove(move.getSource()))
            return false;

        // Making sure it's two rows forward
        if (getColor() == Color.WHITE) {
            if (!(move.getRowDiff() == -2 && move.getColDiff() == 0))
                return false;
        } else {
            if (!(move.getRowDiff() == 2 && move.getColDiff() == 0))
                return false;
        }

        // To move the Pawn two steps forward, the destination should be empty.
        return board.isPositionClear(move.getDestination());
    }

    public boolean canMoveOneDiagonalForward(Board board, Move move) {
        // Should be one row forward.
        if (isWhite()) {
            if (move.getRowDiff() != -1) {
                return false;
            }
        } else {
            if (move.getRowDiff() != 1) {
                return false;
            }
        }

        // Right diagonal
        if (move.getColDiff() == 1) {
            return hasEnemyPiece(board, move.getDestination());
        }
        // Left diagonal
        if (move.getColDiff() == -1) {
            return hasEnemyPiece(board, move.getDestination());
        }
        // Not diagonal
        return false;
    }

    private boolean isFirstMove(Position pos) {
        if (isBlack()) {
            return pos.getRowIndex() == 1;
        } else {
            return pos.getRowIndex() == 6;
        }
    }

    private boolean hasEnemyPiece(Board board, Position pos) {
        Piece candidateEnemyPiece = board.getPieceAtPosition(pos);
        if (candidateEnemyPiece instanceof NullPiece)
            return false;
        return candidateEnemyPiece.getColor() != getColor();
    }

    @Override
    public String toString() {
        if (isWhite())
            return " Pawn ";
        return ANSI.BLACK_BOLD + " Pawn " + ANSI.RESET;
    }
}
