package org.example.validatation;


/**
 * This is a singleton class provides a simple interface for clients to validate a move in a chess game.
 * Given a board, a move, and a player, it will return whether the move is valid or not.
 * The validation process consists of a chain of responsibility of several `Validator` objects,
 * each responsible for checking a specific aspect of the move. The chain starts with `ValidSource`,
 * which checks the source position of the move, followed by `ValidDestination`, which checks the
 * destination position, then `CanPieceMakeMove`, which checks if the piece at the source position
 * can make the given move, and finally `IsKingSafe`, which checks if the move would leave the
 * player's king in a safe position.
 */


import org.example.generalUse.Move;
import org.example.mainComponent.board.Board;
import org.example.mainComponent.Player;

public class ValidatorFacade {
    private static final ValidatorFacade soloInstance = new ValidatorFacade();
    private final ValidSource validSource;
    private final ValidDestination validDestination;
    private final CanPieceMakeMove canPieceMakeMove;
    private final IsKingSafe isKingSafe;

    private ValidatorFacade() {
        validSource = new ValidSource();
        validDestination = new ValidDestination();
        canPieceMakeMove = new CanPieceMakeMove();
        isKingSafe = new IsKingSafe();

        // setting up the chain.
        validSource.setNextValidator(validDestination);
        validDestination.setNextValidator(canPieceMakeMove);
        canPieceMakeMove.setNextValidator(isKingSafe);
    }

    public static ValidatorFacade getInstance() {
        return soloInstance;
    }


    public boolean validMove(Board board, Move move, Player player) {
        // ValidSource is the head of the chain.
        return validSource.isValid(board, move, player);
    }

}
