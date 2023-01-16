package org.example.mainComponent.board;

import org.example.chessPiece.King;
import org.example.chessPiece.Piece;
import org.example.generalUse.Position;
import org.example.generalUse.enumeration.Color;
import org.example.validatation.StatusChecker;

public class BoardPrinter {
    public static void displayBoard(Board board)  {
        horizontalBorder();
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {

            // Print the row number
            System.out.print((8 - rowIndex) + " ");

            for (int colIndex = 0; colIndex < 8; colIndex++) {
                System.out.print("| ");

                // To check whether this piece is a king,
                // if so, then check if it's in threat and make it red.
                Piece currentPiece = board.getPieceAtPosition(new Position(rowIndex, colIndex));
                boolean kingIsChecked = false;
                if (currentPiece instanceof King) {
                    if (currentPiece.getColor() == Color.WHITE)
                        kingIsChecked = StatusChecker.isWhiteKingInCheck(board);
                    else
                        kingIsChecked = StatusChecker.isBlackKingInCheck(board);
                }
                // If the piece is king, and it's in check, this prints it in red.
                if (kingIsChecked)
                    System.out.print(King.toStringInThreat());
                else
                    System.out.print(currentPiece + " ");
            }

            System.out.println("|");
            horizontalBorder();
        }

        // Prints the letters corresponding to each column.
        System.out.print("       ");
        for (int i = 0; i < 8; i++) {
            System.out.print((char) ('A' + i) + "        ");
        }
        System.out.println("\n");
    }

    public static void horizontalBorder() {
        System.out.print("  ");
        for (int colIndex = 0; colIndex < 8; colIndex++) {
            System.out.print("+--------");
        }
        System.out.println("+");
    }
}
