package com.crashsurvivor.client;

import com.apps.util.Console;
import com.crashsurvivor.app.GameBoard;

import java.io.FileNotFoundException;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Console.clear();
        GameBoard board = new GameBoard();
        board.execute(board);
    }
}