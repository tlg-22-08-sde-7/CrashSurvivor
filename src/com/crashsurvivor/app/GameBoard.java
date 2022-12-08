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

    public void execute() {
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
    private void clearConsole(){
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
        clearConsole();
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
        try {
            mapBoard.showKeyItemsAtLocation();
            mapBoard.printPlayerInfo(player);

            if (mapBoard.showWildlifeAtLocation().length() > 1) {
                mapBoard.showWildlifeAtLocation();
            }


            mapBoard.showItemsAtLocation();
            getItemsPrompt();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getDirectionPrompt();
    }

    public void getDirectionPrompt() {
        try {
            StringBuilder directionsStr = new StringBuilder();
            List<Direction> allDirections =  mapBoard.getAllDirections();
            Map<String, String> directionsHM = new HashMap<>();

            for (Direction dir: allDirections){
                directionsHM.put(dir.getDirectionName().toLowerCase(), dir.getPlace());

                directionsStr.append("Go ");
                directionsStr.append(dir.getDirectionName());
                directionsStr.append(" | ");
            }

            String directionPrompt = "Choose your next destination wisely, " + player.getName() + "? \n" +directionsStr.toString()+ "\n>";
            String directionOptions = convertToPromptOption(allDirections);
            String directionErrMsg = "Invalid input!(Case Sensitive)";

            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
            String inputDirection = prompter.prompt(ANSI_YELLOW + directionPrompt,  directionOptions,directionErrMsg + ANSI_RESET);
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
            inputDirection = inputDirection.toLowerCase().substring(3);

            if (inputDirection != null && inputDirection != ""){
                player.setCurrentLocation(directionsHM.get(inputDirection));
                clearConsole();
                System.out.println("Your current location: " + player.getCurrentLocation());
                mapBoard.printMap();
                mapBoard.printDescriptionData();

                mapBoard.printPlayerInfo(player);

                mapBoard.showItemsAtLocation();
                getItemsPrompt();

                player.getInventory().showInventory();

                mapBoard.showWildlifeAtLocation();
                //
                getDirectionPrompt();
                clearConsole();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getItemsPrompt() throws FileNotFoundException {
        //if items in this locations, allow player to pick
        List<Items> allItems = mapBoard.getItemsAtLocation(player.getCurrentLocation());
        if (allItems != null && allItems.size()>0){
            //get all items in Player's current location
            String inputDirection = prompter.prompt( "Do you want to pick an item? (Type Get [Item_Name])", convertToPromptOptionItems(allItems) , "Please select from the items available!" );
            inputDirection = inputDirection.toLowerCase().substring(4);

            for (Items item: allItems){
                if (item.getName().equalsIgnoreCase(inputDirection)){
                    player.getInventory().addToInventory(item);
                    System.out.printf("%s, successfully added to the inventory!\n", item.getName().toUpperCase());
                }
                break;
            }
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

    private String convertToPromptOptionItems( List<Items> allItems){
        StringBuilder itemsStr = new StringBuilder();
        for (Items item: allItems){
            itemsStr.append("Get ");
            itemsStr.append(item.getName());
            itemsStr.append("|");
        }
        return itemsStr.toString();
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
    public void gameOver() {

    }
}
