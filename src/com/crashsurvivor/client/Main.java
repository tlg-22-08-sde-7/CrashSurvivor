package com.crashsurvivor.client;

import com.apps.util.Console;
import com.crashsurvivor.app.GameBoard;

class Main {
    public static void main(String[] args) {
        Console.clear();
        GameBoard board = new GameBoard();
        board.execute(board);
    }
}