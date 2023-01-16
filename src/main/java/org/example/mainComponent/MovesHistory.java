package org.example.mainComponent;

import org.example.generalUse.Move;

import java.util.ArrayList;


public class MovesHistory {

    private final String firstPlayerName;
    private final String secondPlayerName;
    private final ArrayList<Move> movesList;

    public MovesHistory(String firstPlayerName, String secondPlayerName) {
        movesList = new ArrayList<>();
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }


    public void add(Move m) {
        movesList.add(m);
    }

    public void displayHistory() {

        for (int i = 0; i < movesList.size(); i++) {
            System.out.print(i + 1 + ") ");
            // this means it's the first player turn.
            if (i % 2 == 0) {
                System.out.println(firstPlayerName + " made this move : " + movesList.get(i));
            } else {
                System.out.println(secondPlayerName + " made this move : " + movesList.get(i));
            }
        }
        System.out.println("Total Number of Moves = " + movesList.size());
    }

    public int getSize() {
        return movesList.size();
    }


}
