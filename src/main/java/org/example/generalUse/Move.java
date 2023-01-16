package  org.example.generalUse;

import java.util.regex.Pattern;

/**
 * The Move class represents a move given from one of the players
 * It contains information about the source and destination positions of the move,
 * as well as methods for accessing and comparing these positions.
 * The class also provides factory method for creating a move object from a string input,
 * and for validating the input using regular expressions.
 */


public class Move {
    private final Position source;
    private final Position destination;
    private String moveTemplate;
    private Move(String sourceTemplate, String destinationTemplate, String moveTemplate) {
        // Upper case, for consistency when trying to convert source and
        // destination templates into Positions.
        sourceTemplate = sourceTemplate.toUpperCase();
        destinationTemplate = destinationTemplate.toUpperCase();

        source = new Position('8' - sourceTemplate.charAt(1), sourceTemplate.charAt(0) - 'A');
        destination = new Position('8' - destinationTemplate.charAt(1), destinationTemplate.charAt(0) - 'A');
        this.moveTemplate = moveTemplate;

    }

    public Move(Position source, Position destination) {
        this.source = source;
        this.destination = destination;
    }

    // Factory method pattern, this method creates valid move instances
    // and validates the given string using regex.
    public static Move createMove(String format) {
        // A variable to store the format in case of passing the validation.
        String formatSaver = format;

        // to split by spaces, remove additional spaces.
        format = format.replaceAll("[\\s]+", " ");
        format = format.trim();
        String[] args = format.split(" ");

        // should have 3 arguments.
        if (args.length != 3) {
            return null;
        }
        // validating "move".
        if (!Pattern.compile("[Mm][Oo][Vv][Ee]").matcher(args[0]).matches()) {
            return null;
        }
        // validating source template.
        if (!Pattern.compile("[a-hA-H][1-8]").matcher(args[1]).matches()) {
            System.out.println(args[1]);
            return null;
        }
        // validating destination template.
        if (!Pattern.compile("[a-hA-H][1-8]").matcher(args[2]).matches()) {
            return null;
        }
        // gives the constructor the arguments needed to create a move.
        return new Move(args[1], args[2], formatSaver);
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }

    public int getAbsRowDiff() {
        return Math.abs(source.getRowIndex() - destination.getRowIndex());
    }


    public int getAbsColDiff() {
        return Math.abs(source.getColIndex() - destination.getColIndex());
    }


    // col and row difference from source to destination.
    public int getRowDiff() {
        return destination.getRowIndex() - source.getRowIndex();
    }

    public int getColDiff() {
        return destination.getColIndex() - source.getColIndex();
    }

    // The following 4 methods compares between source and destination locations
    // and returns the needed one.
    public Position getHigherPosition() {
        if (source.getRowIndex() < destination.getRowIndex())
            return source;
        return destination;
    }

    public Position getLowerPosition() {
        if (source.getRowIndex() > destination.getRowIndex())
            return source;
        return destination;
    }

    public Position getLeftMostPosition() {
        if (source.getColIndex() < destination.getColIndex())
            return source;
        return destination;
    }

    public Position getRightMostPosition() {
        if (source.getColIndex() > destination.getColIndex())
            return source;
        return destination;
    }

    public String toString() {
        return ANSI.BLUE + moveTemplate + ANSI.RESET;
    }
}


