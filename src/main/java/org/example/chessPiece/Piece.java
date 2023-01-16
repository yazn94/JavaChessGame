package org.example.chessPiece;

import org.example.generalUse.Move;
import org.example.generalUse.enumeration.Color;
import org.example.mainComponent.board.Board;

/**
 * This abstract class represents a piece on the chess board, including both real pieces and null pieces.
 * Each piece has a color, and the main difference between different types of pieces is the implementation of the
 * canMakeMove method, which determines whether a piece is able to move to a given position, or not.
 */


public abstract class Piece {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean canMakeMove(Board board, Move move);
}