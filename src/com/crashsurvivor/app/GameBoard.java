package com.crashsurvivor.app;

import com.apps.util.Prompter;
import com.crashsurvivor.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map;

public class GameBoard extends MapBoard{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private static boolean isRunning = false;
    Music audioPlayer = new Music();
    public static String inputDirection;
    String previousLocation;

    public void execute() throws FileNotFoundException {
        clearConsole();
        welcome();
        pressToContinue();
        clearConsole();
        isRunning = true;
        while (isRunning) {
            clearConsole();
            promptStartGame();
        }
    }

    public void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    private void pressToContinue() {
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

    private void promptStartGame() throws FileNotFoundException {

        System.out.println("1) Start New Game");
        System.out.println("2) Load Saved Game");
        System.out.println("3) See Instructions");
        System.out.println();
        String choiceInput = prompter.prompt("What would you like to do? (1, 2, or 3):\n>", "1|2|3|quit|help",
                "Invalid Category. Please choose 1, 2, or 3.");

        if (choiceInput.equals("1")) {
            clearConsole();
            startGame();
        } else if (choiceInput.equals("2")) {
            clearConsole();
            loadSavedGame();
        } else if (choiceInput.equals("quit")) {
            quitGame(1);
        } else {
            showInstructions();
        }
        isRunning = false;
    }

    private void startGame() {
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
            printOnePlayerData();
            readAllJson();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        clearConsole();
        System.out.println("Your current location: " + player.getCurrentLocation());
        System.out.println(player.getLocationVisited());
        try {
            printSinglePlayerInfo();
            printMapData();
            lookPrompt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getDirectionPrompt();
    }

    public void getDirectionPrompt() {
        try {
            StringBuilder directionsStr = new StringBuilder();
            Map<String, String> directionsHM = new HashMap<>();

            for (Direction dir : directionsList) {
                directionsHM.put(dir.getDirectionName().toLowerCase(), dir.getPlace());

                directionsStr.append("Go ");
                directionsStr.append(dir.getDirectionName());
                directionsStr.append(" | ");
            }

            String directionPrompt = "Choose your next destination wisely, " + player.getName() + "? \n" + directionsStr.toString() + "\n>";
            String directionOptions = convertToPromptOption();
            String directionErrMsg = "Invalid input!)";

            printLine(100);
            inputDirection = prompter.prompt(ANSI_YELLOW + directionPrompt, directionOptions, directionErrMsg + ANSI_RESET);
            printLine(100);
            inputDirection = inputDirection.equalsIgnoreCase("no") || inputDirection.equalsIgnoreCase("quit") || inputDirection.equalsIgnoreCase("help") ? inputDirection : inputDirection.toLowerCase().substring(3);

            if (inputDirection != null && inputDirection != "") {
                if (inputDirection.equalsIgnoreCase("quit")) {
                    quitGame(6);
                }
                if (inputDirection.equalsIgnoreCase("help")) {
                    showInstructions();
                }
                previousLocation = player.getCurrentLocation();
                player.setCurrentLocation(directionsHM.get(inputDirection));
                clearConsole();
                System.out.println("Your current location: " + player.getCurrentLocation());
                player.setLocationVisited(player.getCurrentLocation());
                System.out.println(player.getCurrentLocation());
                clearData();
                refreshData();
                printMapData();
                System.out.println(player.getLocationVisited());
                player.getInventory().showInventory();

                lookPrompt();
                getDirectionPrompt();
                clearConsole();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void lookPrompt() throws FileNotFoundException {
        String input = prompter.prompt("If you want to see things that are in this location, type 'look' else type 'no'\n>", "look|no", "Please type 'look' or 'no'");
        clearConsole();
        if (input.equalsIgnoreCase("look")){
            System.out.println("Your current location: " + player.getCurrentLocation());
            printMapData();
            printLookData();
            wildlifePrompt();
            getItemsPrompt();
            getKeyItemsPrompt();

            player.getInventory().showInventory();
        }

    }

    public void printLine(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void getItemsPrompt() throws FileNotFoundException {
        //if items in this locations, allow player to pick
        if (!itemsList.isEmpty()) {
            //get all items in Player's current location
            String input = prompter.prompt(ANSI_BLUE + "Do you want to pick an item? (Type Get [Item_Name] or press 'no')\n>", convertToPromptOptionItems(), "Please select from the items available!" + ANSI_RESET);
            input = input.equalsIgnoreCase("no") || input.equalsIgnoreCase("quit") ? input : input.toLowerCase().substring(4);
            if (input.equalsIgnoreCase("quit")) {
                quitGame(4);
            } else if (!input.equalsIgnoreCase("no")) {
                for (Items item : itemsList) {
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

    private void getKeyItemsPrompt() throws FileNotFoundException {

        if (!keyItemsList.isEmpty()) {
            String input = prompter.prompt(ANSI_BLUE + "Do you want to pick a key item? (Type Get [KeyItem_Name] or press 'no')\n>", convertToPromptOptionKeyItems(), "Please select from the key items available!" + ANSI_RESET);
            input = input.equalsIgnoreCase("no") || input.equalsIgnoreCase("quit") ? input : input.toLowerCase().substring(4);

            if (input.equalsIgnoreCase("quit")) {
                quitGame(5);
            }

            if (!input.equalsIgnoreCase("no")) {
                for (KeyItems item : keyItemsList) {
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

    private String convertToPromptOption() {
        StringBuilder directionsStr = new StringBuilder();
        directionsStr.append("(?i)");
        for (Direction dir : directionsList) {
            directionsStr.append("Go ");
            directionsStr.append(dir.getDirectionName());
            directionsStr.append("|");
        }
        directionsStr.append("no|");
        directionsStr.append("quit|");
        directionsStr.append("help");
        return directionsStr.toString();
    }

    private String convertToPromptOptionItems() {
        StringBuilder itemsStr = new StringBuilder();
        itemsStr.append("(?i)");
        for (Items item : itemsList) {
            itemsStr.append("Get ");
            itemsStr.append(item.getName());
            itemsStr.append("|");
        }
        itemsStr.append("no|");
        itemsStr.append("quit");

        return itemsStr.toString();
    }

    private String convertToPromptOptionKeyItems() {
        StringBuilder itemsStr = new StringBuilder();
        itemsStr.append("(?i)");
        for (KeyItems item : keyItemsList) {
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

    private void showInstructions() throws FileNotFoundException {

        try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/instructions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String choiceInput = prompter.prompt("To leave, type exit. To turn music on, type music on. To turn music off, type music off\n>", "exit|quit|help|music on|music off", "Please type exit to leave!");
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
        if (choiceInput.equals("music on")) {
            try {
                audioPlayer.start("CrashSurvivor/resources/rain-and-thunder.wav");
                promptStartGame();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        if (choiceInput.equals("music off")) {
            audioPlayer.stop();
            promptStartGame();
        }
    }

    private void quitGame(int stage) throws FileNotFoundException {

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
                    promptStartGame();
                    break;
                case 2:
                    startGame();
                    break;
                case 3:
                    showInstructions();
                    break;
                case 4:
                    getItemsPrompt();
                    break;
                case 5:
                    getKeyItemsPrompt();
                    break;
                case 6:
                    getDirectionPrompt();
                    break;
                default:
            }
        }
    }

    public void wildlifePrompt() throws FileNotFoundException {
        if (wildlifeAtLocation.length() >= 1 && wildlife.getHealth() >= 1 && player.getHealth() >= 1) {
            String choice = prompter.prompt("Do you wish to attack, flee, or use item?",
                    "attack|flee|use item", "Invalid Choice, choose a valid choice!");
            choice.toLowerCase();
            clearConsole();

            if (choice.equals("attack")) {
                addedPlayerAttributes();
                playerAttack();
                wildlifeAttack();
                if (player.getHealth() < 1) {
                    gameOver();
                }
                currentWildlife();
                printSinglePlayerInfo();
                wildlifePrompt();
            } else if (choice.equals("flee")) {
                flee();
            } else if (choice.equals("use item")) {
                clearConsole();
                List<String> itemsToEat = Arrays.asList(new String[]{"apple", "banana", "coconut"});
                List<String> itemsToDrink = Arrays.asList(new String[]{"water"});

                player.getInventory().showInventory();
                List<Items> itemList = player.getInventory().getInventoryList();

                StringBuilder itemsStr = new StringBuilder();
                itemsStr.append("(?i)");

                for (Items item : itemList) {
                    itemsStr.append(item.getName());
                    itemsStr.append("|");
                }

                String useItem = prompter.prompt("Type an item name to use from Items inventory?", itemsStr.toString(),
                        "Invalid choice. Please choose a valid item name from items inventory!");
                useItem.toLowerCase();

                if (itemsToEat.contains(useItem)) {
                    player.eat(useItem);
                    currentWildlife();

                    printSinglePlayerInfo();
                    wildlifePrompt();
                } else if (itemsToDrink.contains(useItem)) {

                    player.drink(useItem);
                    currentWildlife();

                    printSinglePlayerInfo();
                    wildlifePrompt();
                }

            }
        } else if (wildlife.getHealth() < 1) {
            getDirectionPrompt();

        } else if (player.getHealth() < 1) {
            gameOver();
        }
    }
    public void playerAttack() {
        int newWildlifeHealth = wildlife.getHealth() - player.getStrength();
        wildlife.setHealth(newWildlifeHealth);
    }
    public void wildlifeAttack() {
        int newPlayerHealth = player.getHealth() - wildlife.getStrength();
        player.setHealth(newPlayerHealth);
    }
    public void addedPlayerAttributes() {
        for (Items item : Inventory.inventoryList) {
            if (Objects.equals(item.getName(), "spear")) {
                player.setStrength(player.getStrength() + 50);
            }
            if (Objects.equals(item.getName(), "machete")) {
                player.setStrength(player.getStrength() + 30);
            }
            if (Objects.equals(item.getName(), "running shoes")) {
                player.setSpeed(player.getSpeed() + 50);
            }
        }
    }
    public void flee() throws FileNotFoundException {
        player.setCurrentLocation(previousLocation);
        if (inputDirection.equalsIgnoreCase("quit")) {
            quitGame(6);
        }
        if (inputDirection.equalsIgnoreCase("help")) {
            showInstructions();
        }
        previousLocation = player.getCurrentLocation();
        clearConsole();
        System.out.println("Your current location: " + player.getCurrentLocation());
        System.out.println(player.getCurrentLocation());
        clearData();
        refreshData();
        printMapData();
        System.out.println(player.getLocationVisited());
        player.getInventory().showInventory();

        lookPrompt();
        getDirectionPrompt();
        clearConsole();
    }

    public void gameOver() {
        printLine(150);
        printLine(150);
        try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/gameover.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ANSI_YELLOW + "You have failed to Survive!" + ANSI_RESET);
        System.out.println();
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
    }
}
