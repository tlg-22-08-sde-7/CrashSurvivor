package com.crashsurvivor.app;

import com.apps.util.Prompter;
import com.crashsurvivor.Direction;
import com.crashsurvivor.MapBoard;
import com.crashsurvivor.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameBoard {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private static boolean isRunning = false;
    private Prompter prompter;

    MapBoard mapBoard = new MapBoard();
    Player player = new Player("Arnold", 1000, 100, 100, 50, "A1");

    public void execute() {
        welcome();
        isRunning = true;
        while (isRunning) {
            promptStartGame();
        }
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
        System.out.println("board right here");
        getDescriptionPrompt();
        getDirectionPrompt();

    }

    private void promptStartGame() {

        prompter = new Prompter(new Scanner(System.in));
        System.out.println("1) Start New Game");
        System.out.println("2) Load Saved Game");
        System.out.println("3) See Instructions");
        System.out.println();
        String choiceInput = prompter.prompt("What would you like to do? (1, 2, or 3):\n>", "1|2|3|quit|help",
                "Invalid Category. Please choose 1, 2, or 3.");

        if (choiceInput.equals("1")) {
            startGame();
        } else if (choiceInput.equals("2")) {
            loadSavedGame();
        } else if (choiceInput.equals("quit")) {
            quitGame(1);
        } else {
            showInstructions();
        }
        isRunning = false;
    }

    private void startGame() {
        try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/intro.txt"))) {
            String line1;
            while ((line1 = br.readLine()) != null) {
                System.out.println(line1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String choiceInput = prompter.prompt("Please choose starting item\n>", "flare|flint|pocket knife|quit|help", "Please choose something to survive with!");
        choiceInput.toLowerCase();
        if (choiceInput.equals("flare")) {
            showBoard();
        }
        if (choiceInput.equals("flint")) {
            showBoard();
        }
        if(choiceInput.equals("pocket knife")){
            showBoard();
        }
        if (choiceInput.equals("quit")) {
            quitGame(2);
        }
        if (choiceInput.equals("help")) {
            showInstructions();
        }
    }
    private void getDescriptionPrompt() {

    }

    private void getDirectionPrompt() {
        try {
            StringBuilder directionsStr = new StringBuilder();
            List<Direction> allDirections =  mapBoard.getAllDirections();
            Map<String, String> directionsHM = new HashMap<>();

            for (Direction dir: allDirections){
                directionsHM.put(dir.getDirectionName().toLowerCase(), dir.getPlace());

                directionsStr.append("Go ");
                directionsStr.append(dir.getDirectionName());
                directionsStr.append(" \n");
            }

            String directionPrompt = "What is going to be your next destination? \n" +directionsStr.toString()+ ">";
            String directionOptions = convertToPromptOption(allDirections);
            String directionErrMsg = "Invalid input!(Case Sensitive)";

            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
            String inputDirection = prompter.prompt(ANSI_YELLOW + directionPrompt,  directionOptions,directionErrMsg + ANSI_RESET);
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
            inputDirection = inputDirection.toLowerCase().substring(3);

            if (inputDirection != null && inputDirection != ""){
                player.setCurrentLocation(directionsHM.get(inputDirection));
            }
            System.out.println(player.getCurrentLocation());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String convertToPromptOption(List<Direction> allDirections){
        StringBuilder directionsStr = new StringBuilder();
        for (Direction dir: allDirections){
            directionsStr.append("Go ");
            directionsStr.append(dir.getDirectionName());
            directionsStr.append("|");
        }
        return directionsStr.toString();
    }

    private void loadSavedGame() {
        System.out.println("loadSavedGame");
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
        String choiceInput = prompter.prompt("To leave, type exit\n>", "exit|quit|help", "Please type exit to leave!");
        choiceInput.toLowerCase();
        if (choiceInput.equals("exit")) {
            promptStartGame();
        }
        if (choiceInput.equals("quit")) {
            quitGame(3);
        }
        if (choiceInput.equals("help")) {
            showInstructions();
        }
    }

    private void quitGame(int stage) {
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
        } else {
           switch (stage){
               case 1:
                   promptStartGame();
                   break;
               case 2:
                   startGame();
                   break;
               case 3:
                   showInstructions();
                   break;
               default:
           }
        }
    }
}
