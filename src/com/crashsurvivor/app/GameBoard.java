package com.crashsurvivor.app;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class GameBoard {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private static boolean isRunning = false;
    private Prompter prompter;

    public void execute() {
        welcome();
        isRunning = true;
        while (isRunning) {
            promptStartGame();
        }
        showBoard();
    }

    private void welcome() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(ANSI_GREEN + "W E L C O M E  T O  T H E  G A M E!" + ANSI_RESET);
        try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/banner.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(ANSI_YELLOW + line + ANSI_RESET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ANSI_GREEN + "CREATED BY: DAVID, MARTIN, NIMA" + ANSI_RESET);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(ANSI_YELLOW + "As you make your maiden flight in your brand new (to you) plane you experience a sudden engine failure in your number 2 engine.\n" +
                "In your efforts to resolve the issue your ultimately lose control. \n" +
                "You check your heading and coordinates to try to remember where you are as there is nothing that you can do. everything fades to black.." + ANSI_RESET);
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
        String choiceInput = prompter.prompt("What would you like to do? (1, 2, or 3):\n>", "1|2|3|quit",
                "Invalid Category. Please choose 1, 2, or 3.");

        if (choiceInput.equals("1")) {
            startGame();
        } else if (choiceInput.equals("2")) {
            loadSavedGame();
        } else if (choiceInput.equals("quit")) {
            quitGame();
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
        String choiceInput = prompter.prompt("To leave, type exit\n>", "exit|quit", "Please type exit to leave!");
        choiceInput.toLowerCase();
        if (choiceInput.equals("exit")) {
            promptStartGame();
        }
        if (choiceInput.equals("quit")) {
            quitGame();
        }
    }

    private void quitGame() {
        Scanner input = new Scanner(System.in);
        String inputQuit = prompter.prompt("Are you sure you want to quit the game?( yes or no ): \n>", "yes|no", "Invalid input!");
        inputQuit.toLowerCase();

        if (inputQuit.equals("yes")) {
            isRunning = false;
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(ANSI_YELLOW + "Thank you for playing the game!" + ANSI_RESET);
            try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/banner.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
}
