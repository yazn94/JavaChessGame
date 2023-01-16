package org.example.validatation;

import org.example.generalUse.Move;
import org.example.mainComponent.board.Board;
import org.example.mainComponent.Player;

/**
 * This interface represents a validator that is part of a chain of validators.
 * it has setNextValidator to connect the current validator with the next one,
 * also it has isValid which validates the given move
 * depending on each subclass implementation.
 */


public abstract class Validator {
    protected Validator nextValidator = null;

    public void setNextValidator(Validator nextValidator) {
        if (nextValidator == null)
            throw new IllegalArgumentException();
        this.nextValidator = nextValidator;
    }

    public abstract boolean isValid(Board board, Move move, Player player);
}
