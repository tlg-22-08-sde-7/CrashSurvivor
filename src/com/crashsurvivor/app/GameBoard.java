package com.crashsurvivor.app;

import com.apps.util.Prompter;
import com.crashsurvivor.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map;

public class GameBoard {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private static boolean isRunning = false;
    private Prompter prompter;
    MapBoard mapBoard = new MapBoard();
    Player player;
    Wildlife wildlife;
    Music audioPlayer = new Music();

    public void execute(GameBoard gameBoard) throws FileNotFoundException {
        clearConsole();
        welcome();
        pressToContinue();
        clearConsole();
        isRunning = true;
        while (isRunning) {
            clearConsole();
            promptStartGame(gameBoard);
        }
    }

    public void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    private void pressToContinue() {
        prompter = new Prompter(new Scanner(System.in));
        prompter.prompt("\nPress 'c' to continue...", "c", "Invalid Category. Please press 'c'");
    }

    private void welcome() {
        System.out.println();
        printLine(150);
        printLine(150);

        try {
            audioPlayer.start("CrashSurvivor/resources/rain-and-thunder.wav");
        } catch (Exception e) {

            e.printStackTrace();
        }
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
        printLine(150);
        System.out.println(ANSI_YELLOW + "As you make your maiden flight in your brand new (to you) plane you experience a sudden engine failure in your number 2 engine.\n" +
                "In your efforts to resolve the issue your ultimately lose control. \n" +
                "You check your heading and coordinates to try to remember where you are as there is nothing that you can do. everything fades to black.." + ANSI_RESET);
        printLine(150);
        printLine(150);
        System.out.println();
    }

    private void showBoard() {
        System.out.println("board right here");
        clearConsole();
    }

    private void promptStartGame(GameBoard gameBoard) throws FileNotFoundException {

        prompter = new Prompter(new Scanner(System.in));
        System.out.println("1) Start New Game");
        System.out.println("2) Load Saved Game");
        System.out.println("3) See Instructions");
        System.out.println();
        String choiceInput = prompter.prompt("What would you like to do? (1, 2, or 3):\n>", "1|2|3|quit|help",
                "Invalid Category. Please choose 1, 2, or 3.");

        if (choiceInput.equals("1")) {
            clearConsole();
            startGame(gameBoard);
        } else if (choiceInput.equals("2")) {
            clearConsole();
            loadSavedGame();
        } else if (choiceInput.equals("quit")) {
            quitGame(1, gameBoard);
        } else {
            showInstructions(gameBoard);
        }
        isRunning = false;
    }

    private void startGame(GameBoard gameBoard) {
        audioPlayer.stop();
        try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/intro.txt"))) {
            String line1;
            while ((line1 = br.readLine()) != null) {
                System.out.println(line1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        pressToContinue();
        clearConsole();
        try {
            player = mapBoard.printPlayerData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        clearConsole();
        mapBoard.printMap();
        mapBoard.printDescriptionData();
        System.out.println(player.getLocationVisited());
        try {
            mapBoard.showKeyItemsAtLocation();
            mapBoard.showWildlifeAtLocation(wildlife, player, mapBoard, gameBoard);
            mapBoard.printPlayerInfo(player);

            mapBoard.showItemsAtLocation();
            getItemsPrompt(gameBoard);
            getKeyItemsPrompt(gameBoard);
            player.getInventory().showInventory();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getDirectionPrompt(gameBoard);
    }

    public void getDirectionPrompt(GameBoard gameBoard) {
        try {
            StringBuilder directionsStr = new StringBuilder();
            List<Direction> allDirections = mapBoard.getAllDirections();
            Map<String, String> directionsHM = new HashMap<>();

            for (Direction dir : allDirections) {
                directionsHM.put(dir.getDirectionName().toLowerCase(), dir.getPlace());

                directionsStr.append("Go ");
                directionsStr.append(dir.getDirectionName());
                directionsStr.append(" | ");
            }

            String directionPrompt = "Choose your next destination wisely, " + player.getName() + "? \n" + directionsStr.toString() + "\n>";
            String directionOptions = convertToPromptOption(allDirections);
            String directionErrMsg = "Invalid input!)";

            printLine(100);
            String inputDirection = prompter.prompt(ANSI_YELLOW + directionPrompt, directionOptions, directionErrMsg + ANSI_RESET);
            printLine(100);
            inputDirection = inputDirection.equalsIgnoreCase("no") || inputDirection.equalsIgnoreCase("quit") ? inputDirection : inputDirection.toLowerCase().substring(3);

            if (inputDirection != null && inputDirection != "") {
                if (inputDirection.equalsIgnoreCase("quit")) {
                    quitGame(6, gameBoard);
                }
                player.setCurrentLocation(directionsHM.get(inputDirection));
                clearConsole();
                System.out.println("Your current location: " + player.getCurrentLocation());
                player.setLocationVisited(player.getCurrentLocation());
                mapBoard.printMap();
                mapBoard.printDescriptionData();
                System.out.println(player.getLocationVisited());
                mapBoard.printPlayerInfo(player);
                mapBoard.showItemsAtLocation();
                printLine(50);

                getItemsPrompt(gameBoard);

                mapBoard.showKeyItemsAtLocation();
                getKeyItemsPrompt(gameBoard);

                player.getInventory().showInventory();
                mapBoard.showWildlifeAtLocation(wildlife, player, mapBoard, gameBoard);

                getDirectionPrompt(gameBoard);
                clearConsole();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printLine(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void getItemsPrompt(GameBoard gameBoard) throws FileNotFoundException {
        //if items in this locations, allow player to pick
        List<Items> allItems = mapBoard.getItemsAtLocation(player.getCurrentLocation());
        if (allItems != null && allItems.size() > 0) {
            //get all items in Player's current location
            String input = prompter.prompt(ANSI_BLUE + "Do you want to pick an item? (Type Get [Item_Name] or press 'no')\n>", convertToPromptOptionItems(allItems), "Please select from the items available!" + ANSI_RESET);
            input = input.equalsIgnoreCase("no") || input.equalsIgnoreCase("quit") ? input : input.toLowerCase().substring(4);
            if (input.equalsIgnoreCase("quit")) {
                quitGame(4, gameBoard);
            } else if (!input.equalsIgnoreCase("no")) {
                for (Items item : allItems) {
                    if (item.getName().equalsIgnoreCase(input)) {
                        player.getInventory().addToInventory(item);
                        printLine(100);
                        pressToContinue();
                        break;
                    }
                }
                clearConsole();
            }
        }
    }

    private void getKeyItemsPrompt(GameBoard gameBoard) throws FileNotFoundException {
        List<KeyItems> allKeyItems = mapBoard.getKeyItemsAtLocation(player.getCurrentLocation());
        if (allKeyItems != null && allKeyItems.size() > 0) {
            String input = prompter.prompt(ANSI_BLUE + "Do you want to pick a key item? (Type Get [KeyItem_Name] or press 'no')\n>", convertToPromptOptionKeyItems(allKeyItems), "Please select from the key items available!" + ANSI_RESET);
            input = input.equalsIgnoreCase("no") || input.equalsIgnoreCase("quit") ? input : input.toLowerCase().substring(4);

            if (input.equalsIgnoreCase("quit")) {
                quitGame(5, gameBoard);
            }

            if (!input.equalsIgnoreCase("no")) {
                for (KeyItems item : allKeyItems) {
                    if (item.getKeyItems().equalsIgnoreCase(input)) {
                        player.getInventory().addToKeyItemsInventory(item);
                        printLine(100);
                        pressToContinue();
                        break;
                    }
                }
                clearConsole();
            }
        }
    }

    private String convertToPromptOption(List<Direction> allDirections) {
        StringBuilder directionsStr = new StringBuilder();
        directionsStr.append("(?i)");
        for (Direction dir : allDirections) {
            directionsStr.append("Go ");
            directionsStr.append(dir.getDirectionName());
            directionsStr.append("|");
        }
        directionsStr.append("no|");
        directionsStr.append("quit");
        return directionsStr.toString();
    }

    private String convertToPromptOptionItems(List<Items> allItems) {
        StringBuilder itemsStr = new StringBuilder();
        itemsStr.append("(?i)");
        for (Items item : allItems) {
            itemsStr.append("Get ");
            itemsStr.append(item.getName());
            itemsStr.append("|");
        }
        itemsStr.append("no|");
        itemsStr.append("quit");

        return itemsStr.toString();
    }

    private String convertToPromptOptionKeyItems(List<KeyItems> allItems) {
        StringBuilder itemsStr = new StringBuilder();
        itemsStr.append("(?i)");
        for (KeyItems item : allItems) {
            itemsStr.append("Get ");
            itemsStr.append(item.getKeyItems().toString());
            itemsStr.append("|");
        }
        itemsStr.append("no|");
        itemsStr.append("quit");

        return itemsStr.toString();
    }

    private void loadSavedGame() {
        System.out.println("loadSavedGame");
    }

    private void showInstructions(GameBoard gameBoard) throws FileNotFoundException {

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
            promptStartGame(gameBoard);
        }
        if (choiceInput.equals("quit")) {
            quitGame(3, gameBoard);
        }
        if (choiceInput.equals("help")) {
            showInstructions(gameBoard);
        }
    }

    private void quitGame(int stage, GameBoard gameBoard) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String inputQuit = prompter.prompt("Are you sure you want to quit the game?( yes or no ): \n>", "yes|no", "Invalid input!");
        inputQuit.toLowerCase();

        if (inputQuit.equals("yes")) {
            isRunning = false;
            printLine(150);
            printLine(150);
            System.out.println(ANSI_YELLOW + "Thank you for playing the game!" + ANSI_RESET);
            try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/banner.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            printLine(150);
            printLine(150);
            System.exit(0);
        } else {

            switch (stage) {
                case 1:
                    promptStartGame(gameBoard);
                    break;
                case 2:
                    startGame(gameBoard);
                    break;
                case 3:
                    showInstructions(gameBoard);
                    break;
                case 4:
                    getItemsPrompt(gameBoard);
                    break;
                case 5:
                    getKeyItemsPrompt(gameBoard);
                    break;
                case 6:
                    getDirectionPrompt(gameBoard);
                    break;
                default:
            }
        }
    }
/*    public void wildlifePrompt(String file) throws FileNotFoundException {
        if (wildlife.getHealth() >= 1 && player.getHealth() >= 1) {
            String choice = prompter.prompt("Do you wish to attack, flee, or use item?",
                    "attack|flee|use item", "Invalid Choice, choose a valid choice!");
            choice.toLowerCase();
            if (choice.equals("attack")) {
                player.playerAttack(player, wildlife);
                System.out.println(wildlife.getHealth());
                wildlife.wildlifeAttack(player, wildlife);
                System.out.println(player.getHealth());
                mapBoard.displayWildlifePicture(file);
                mapBoard.printSinglePlayerInfo();
                wildlifePrompt(file);
            } else if (choice.equals("flee")) {
                player.flee();
            } else if (choice.equals("use item")) {
                String useItem = prompter.prompt("Do you wish to eat or drink?", "eat|drink",
                        "Invalid choice. Please choose a valid choice!");
                useItem.toLowerCase();
                if (useItem.equals("eat")) {

//                    eat();
                    mapBoard.displayWildlifePicture(file);

                    mapBoard.printSinglePlayerInfo();
                    wildlifePrompt(file);
                } else if (useItem.equals("drink")) {

//                    drink();
                    mapBoard.displayWildlifePicture(file);

                    mapBoard.printSinglePlayerInfo();
                    wildlifePrompt(file);
                }

            }
        } else if (wildlife.getHealth() < 1) {


        } else if (player.getHealth() < 1) {
            gameOver();
        }
    }*/

    public void gameOver() {

    }
}
