package com.crashsurvivor;

import com.apps.util.Prompter;
import com.crashsurvivor.app.GameBoard;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {
    private static String currentLocation = "A1";
    private String name;
    private int health;
    private int hydration;
    private int strength;
    private int speed;
    String currentPlayer;
    private Prompter prompter;
    private String use;
    GameBoard gameBoard;
    Items weapons;
    Inventory inventory;
    private List<String> locationVisited = new ArrayList<>();


    public Player(String name, int health, int hydration, int strength, int speed, String currentLocation) {
        setSpeed(speed);
        setName(name);
        setHealth(health);
        setHydration(hydration);
        setStrength(strength);
        setCurrentLocation(currentLocation);
        inventory = new Inventory();
    }

    private void move() {

    }


    private void playerAttack(Player player, Wildlife wildlife) {

        int newWildlifeHealth = wildlife.getHealth() - player.getStrength();
        wildlife.setHealth(newWildlifeHealth);
    }

    public void wildlifePrompt(GameBoard gameBoard, Wildlife wildlife, Player player, MapBoard map, String file) throws FileNotFoundException {
        if (wildlife.getHealth() >= 1 && player.getHealth() >= 1) {
            prompter = new Prompter(new Scanner(System.in));
            gameBoard.printLine(50);
            String choice = prompter.prompt("Do you wish to attack, flee, or use item?",
                    "attack|flee|use item", "Invalid Choice, choose a valid choice!");
            choice.toLowerCase();
            if (choice.equals("attack")) {
                player.playerAttack(player, wildlife);
                System.out.println(wildlife.getHealth());
                wildlife.wildlifeAttack(player, wildlife);
                System.out.println(player.getHealth());
                map.displayWildlife(wildlife, file);
                map.printPlayerInfo(player);
                wildlifePrompt(gameBoard, wildlife, player, map, file);
            } else if (choice.equals("flee")) {
                flee();
            } else if (choice.equals("use item")) {
                gameBoard.clearConsole();
                List<String> itemsToEat = Arrays.asList(new String[]{"apple", "banana"});
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
                    eat(useItem);
                    map.displayWildlife(wildlife, file);

                    map.printPlayerInfo(player);
                    wildlifePrompt(gameBoard, wildlife, player, map, file);
                } else if (itemsToDrink.contains(useItem)) {

                    drink(useItem);
                    map.displayWildlife(wildlife, file);

                    map.printPlayerInfo(player);
                    wildlifePrompt(gameBoard, wildlife, player, map, file);
                }

            }
        } else if (wildlife.getHealth() < 1) {
            gameBoard.getDirectionPrompt(gameBoard);

        } else if (getHealth() < 1) {
            gameBoard.gameOver();
        }
    }

    private void flee() {

    }

    private void eat(String food) {
        Items foodType = inventory.getItemFromInventory(food);
        setHealth(foodType.getHealth());
        clearConsole();

        System.out.printf("%s, used.\nYour health has been recovered by, %s.\n", food,  foodType.getHealth());

        getInventory().removeFromInventory(foodType);
        pressToContinue();
        getInventory().showInventory();

    }

    private void drink(String drink) {
        Items drinkType = inventory.getItemFromInventory(drink);
        setHydration(drinkType.getHydration());
        clearConsole();

        System.out.printf("%s, used!\nYour hydration has been recovered by, %s.\n", drink,  drinkType.getHydration());
        getInventory().removeFromInventory(drinkType);
        pressToContinue();
        getInventory().showInventory();

    }

    private void build() {

    }

    public void currentLocation() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health += health;
    }

    public int getHydration() {
        return hydration;
    }

    public void setHydration(int hydration) {
        this.hydration += hydration;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength += strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed += speed;
    }

    public static String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        Player.currentLocation = currentLocation;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public List<String> getLocationVisited() {
        System.out.print("Location Visited: ");
        return locationVisited;
    }

    public void setLocationVisited(String locationVisited) {
        this.locationVisited.add(locationVisited);
    }

    @Override
    public String toString() {
        return getName() + " | HP = " + getHealth() + " | hydration = " + getHydration() + " | strength = "
                + getStrength() + " | speed = " + getSpeed();
    }

    private void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    private void pressToContinue() {
        prompter = new Prompter(new Scanner(System.in));
        prompter.prompt("\nPress 'c' to continue...", "c", "Invalid Category. Please press 'c'");
        clearConsole();
    }
}
