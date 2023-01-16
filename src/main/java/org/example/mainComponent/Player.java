package org.example.mainComponent;

import org.example.generalUse.ANSI;
import org.example.generalUse.enumeration.Color;


public class Player {
    private String name;
    private Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    /**
     * returns string that represents the name of the player,
     * and the name is colored with the color of the player.
     */

    public String getName() {
        if (color == Color.WHITE) {
            return (ANSI.WHITE_BOLD + name + ANSI.RESET);
        } else {
            return ANSI.BLACK_BOLD + name + ANSI.RESET;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
