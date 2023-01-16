package org.example.mainComponent.board;

import org.example.chessPiece.*;
import org.example.generalUse.Move;
import org.example.generalUse.Position;
import org.example.generalUse.enumeration.Color;


import java.util.ArrayList;

/**
 * This is a singleton class since we have only one instance of it
 * in the whole project, it holds all pieces(actual & NullPieces) and performs multiple
 * utilities such as doMove , undoLastMove, display the board , getPieces, etc.
 */


public class Board {
    private static final Board soloBoard = new Board();

    // This helps with iterations on the whole board.
    public static ArrayList<Position> allBoardPositions;
    private  Piece[][] board;
    private Position whiteKingPosition, blackKingPosition;
    // This variable helps with the undo function
    // it will store the killed piece, to restore it later.
    private Piece lastKilled =  NullPiece.getInstance();
    private Board() {
        board = new Piece[8][8];
        setUpPieces();
        whiteKingPosition = new Position(7, 4);
        blackKingPosition = new Position(0, 4);
        allBoardPositions = new ArrayList<>();

        // inserting all positions to allBoardPositions ArrayList
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            for (int colIndex = 0; colIndex < 8; colIndex++) {
                allBoardPositions.add(new Position(rowIndex, colIndex));
            }
        }
    }

    public void restart() {
        board = new Piece[8][8];
        setUpPieces();
        whiteKingPosition = new Position(7, 4);
        blackKingPosition = new Position(0, 4);
        allBoardPositions = new ArrayList<>();

        // inserting all positions to allBoardPositions ArrayList
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            for (int colIndex = 0; colIndex < 8; colIndex++) {
                allBoardPositions.add(new Position(rowIndex, colIndex));
            }
        }
    }
    public static Board getInstance() {
        return soloBoard;
    }

    public Piece getPieceAtPosition(Position pos) {
        return board[pos.getRowIndex()][pos.getColIndex()];
    }

    public void setPieceAtPosition(Position pos, Piece piece) {
        board[pos.getRowIndex()][pos.getColIndex()] = piece;
    }

    private void setUpPieces() {
        setUpPawns();
        setUpRooks();
        setUpKnights();
        setUpBishops();
        setUpKings();
        setUpQueens();
        setUpNullPieces();
    }

    private void setUpPawns() {
        for (int j = 0; j < 8; j++) {
            board[1][j] = new Pawn(Color.BLACK);
        }
        for (int j = 0; j < 8; j++) {
            board[6][j] = new Pawn(Color.WHITE);
        }
    }

    private void setUpRooks() {
        board[0][0] = new Rook(Color.BLACK);
        board[0][7] = new Rook(Color.BLACK);
        board[7][7] = new Rook(Color.WHITE);
        board[7][0] = new Rook(Color.WHITE);
    }

    private void setUpKings() {
        board[0][4] = new King(Color.BLACK);
        board[7][4] = new King(Color.WHITE);
    }

    private void setUpQueens() {
        board[0][3] = new Queen(Color.BLACK);
        board[7][3] = new Queen(Color.WHITE);
    }

    private void setUpBishops() {
        board[0][2] = new Bishop(Color.BLACK);
        board[0][5] = new Bishop(Color.BLACK);
        board[7][2] = new Bishop(Color.WHITE);
        board[7][5] = new Bishop(Color.WHITE);
    }

    private void setUpKnights() {
        board[0][1] = new Knight(Color.BLACK);
        board[0][6] = new Knight(Color.BLACK);
        board[7][1] = new Knight(Color.WHITE);
        board[7][6] = new Knight(Color.WHITE);
    }

    private void setUpNullPieces() {
        // Setting up the empty places with NullPiece objects.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    board[i][j] = NullPiece.getInstance();
                }
            }
        }
    }

    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }


    public void doMove(Move move) {
        Piece pieceFromSource = getPieceAtPosition(move.getSource());

        // Track the changes for both kings
        if (pieceFromSource instanceof King) {
            if (pieceFromSource.isWhite())
                whiteKingPosition = move.getDestination();
            else
                blackKingPosition = move.getDestination();
        }
        lastKilled = getPieceAtPosition(move.getDestination());
        setPieceAtPosition(move.getDestination(), pieceFromSource);
        setPieceAtPosition(move.getSource(), NullPiece.getInstance());
    }

    public void undoLastMove(Move move) {
        Piece pieceFromDestination = getPieceAtPosition(move.getDestination());

        // Track the changes for both kings
        if (pieceFromDestination instanceof King) {
            if (pieceFromDestination.isWhite())
                whiteKingPosition = move.getSource();
            else
                blackKingPosition = move.getSource();
        }

        setPieceAtPosition(move.getSource(), pieceFromDestination);
        setPieceAtPosition(move.getDestination(), lastKilled);
    }
    public boolean isPositionClear(Position position) {
        Piece piece = board[position.getRowIndex()][position.getColIndex()];
        return piece instanceof NullPiece;
    }
}
