package org.example.mainComponent;

import org.example.mainComponent.board.Board;
import org.example.mainComponent.board.BoardPrinter;
import org.example.validatation.StatusChecker;
import org.example.validatation.ValidatorFacade;
import org.example.generalUse.ANSI;
import org.example.generalUse.Move;
import org.example.generalUse.enumeration.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is a facade that provides a simple interface for users to play a game of chess.
 * It manages the board, players, and moves made during the game.
 */


public class ChessGame {

    private final Board board = Board.getInstance();
    private final ValidatorFacade validatorFacade = ValidatorFacade.getInstance();
    private Scanner scanner = new Scanner(System.in);

    // Number of moves before the draw, default is 50.
    private int drawMoves = 50;
    private Player currentPlayer, whitePlayer, blackPlayer;
    private MovesHistory movesHistory;

    public ChessGame() {
    }

    public ChessGame(int drawMoves) {
        this.drawMoves = drawMoves;
    }
    public ChessGame(String filePath) throws FileNotFoundException {
        scanner = new Scanner(new File(filePath));
    }


    public ChessGame(String filePath, int drawMoves) throws FileNotFoundException {
        this(filePath);
        this.drawMoves = drawMoves;
    }

    // Count from 1 to 3 before starting the game.
    private static void countToThree() {
        try {
            for (int i = 1; i <= 3; i++) {
                System.out.println(i);
                Thread.sleep(1000); // Sleep for 1 second
            }
        } catch (InterruptedException e) {
            // Handle the interruption
            System.out.println("Counter interrupted");
        }
    }

    private void setUpPlayers() {

        System.out.print("First player name (" + ANSI.WHITE_BOLD + "white player" + ANSI.RESET + "): ");
        whitePlayer = new Player(scanner.nextLine(), Color.WHITE);

        System.out.print("Second player name (" + ANSI.BLACK_BOLD + "black player" + ANSI.RESET + "): ");
        blackPlayer = new Player(scanner.nextLine(), Color.BLACK);
        System.out.println();

        // White always starts the game.
        currentPlayer = whitePlayer;
    }

    public void start() {
        setUpPlayers();
        movesHistory = new MovesHistory(whitePlayer.getName(), blackPlayer.getName());

        System.out.println("Note that the moves looks like this : ("
                + ANSI.BLUE + "move d2 d4" + ANSI.RESET +
                "), and it's not sensitive case.");

        System.out.println("At any time each player can enter H to show the history of the moves");

        countToThree();
        while (drawMoves > 0) {
            // To mark the beginning of the new move.
            System.out.println("________________________________________________________" +
                    "____________________________\n");

            BoardPrinter.displayBoard(board);
            // Take the move from the user.
            System.out.print(currentPlayer.getName() + ", enter your move, or show History: ");
            String input = scanner.nextLine();
            System.out.println();

            // Checks if the user wants to show history.
            input = input.trim();
            if (input.equals("H")) {
                movesHistory.displayHistory();
                System.out.println();
                continue;
            }


            Move move = Move.createMove(input);

            // This means, the given move template is wrong.
            // as the createMove didn't create an object with the given format
            // , and it will return a null, and here we handle this exception.
            if (move == null) {
                System.out.println(ANSI.RED + "Invalid move template! Try again"+ ANSI.RESET);
                continue;
            }

            // Invalid move.
            if (!validatorFacade.validMove(board, move, currentPlayer)) {
                System.out.println(ANSI.RED + "The move (" + move + ")"
                        + ANSI.RED + ", is invalid move! Try again" + ANSI.RESET);
                continue;
            }
            board.doMove(move);
            movesHistory.add(move);
            // Now, check if the other player is checkmated.
            alternatePlayer();
            if (StatusChecker.isCheckmated(board, currentPlayer)) {
                endGame();
                return;
            }
            drawMoves--;
        }
        BoardPrinter.displayBoard(board);
        // Now, if the moves ended and no one wins then this is a draw.
        System.out.println("Draw! the game exceeded " + drawMoves + " moves without checkmates");
        HistoryOrExist();
        scanner.close();
    }

    private void endGame() {
        // Get back to the winner.
        alternatePlayer();
        System.out.print(ANSI.GREEN);
        System.out.print(" . . . . . . . . . ");
        System.out.print("Congratulations " + currentPlayer.getName() + ANSI.GREEN + "! You won the game");
        System.out.print(" . . . . . . . . .");
        System.out.println(ANSI.RESET);

        BoardPrinter.displayBoard(board);

        HistoryOrExist();
        scanner.close();
    }

    private void HistoryOrExist() {
        // Printing history or stop the game.
        System.out.print("Enter H for history, E for exist: ");

        String temp = scanner.nextLine();
        // Get rid of additional spaces.
        temp = temp.trim();

        if (temp.equals("H")) {
            System.out.println();
            movesHistory.displayHistory();
        }
    }
    private void alternatePlayer() {
        if (currentPlayer == whitePlayer)
            currentPlayer = blackPlayer;
        else
            currentPlayer = whitePlayer;
    }
}