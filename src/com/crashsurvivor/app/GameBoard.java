package com.crashsurvivor.app;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class GameBoard {
    private Prompter prompter;

    public void execute() {
        welcome();
        promptStartGame();
        showBoard();
    }

    private void welcome() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("W E L C O M E  T O  T H E  G A M E!");
        try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/banner.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("CREATED BY: DAVID, MARTIN, NIMA");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("As you make your maiden flight in your brand new (to you) plane you experience a sudden engine failure in your number 2 engine.\n" +
                "In your efforts to resolve the issue your ultimately lose control. \n" +
                "You check your heading and coordinates to try to remember where you are as there is nothing that you can do. everything fades to black..");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    private void showBoard() {

    }

    private void promptStartGame() {
        prompter = new Prompter(new Scanner(System.in));
        System.out.println("1) Start New Game");
        System.out.println("2) Load Saved Game");
        System.out.println("3) See Instructions");
        System.out.println();
        String choiceInput = prompter.prompt("What would you like to do? (1, 2, or 3):\n>", "1|2|3",
                "Invalid Category. Please choose 1, 2, or 3.");
        if (choiceInput.equals("1")) {
            startGame();
        } else if (choiceInput.equals("2")) {
            loadSavedGame();
        } else {
            showInstructions();
        }
    }

    private void startGame() {
    }

    private void loadSavedGame() {
    }

    private void showInstructions() {

        try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/instructions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String choiceInput = prompter.prompt("To leave, type exit\n>", "exit", "Please type exit to leave!");
        choiceInput.toLowerCase();
        if (choiceInput.equals("exit")) {
            promptStartGame();
        }
    }
}
