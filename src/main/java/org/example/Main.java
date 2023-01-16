package org.example;

import org.example.mainComponent.ChessGame;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new ChessGame().start();
    }
}
