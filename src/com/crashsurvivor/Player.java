package com.crashsurvivor;

import com.apps.util.Prompter;
import com.crashsurvivor.app.GameBoard;

import java.io.FileNotFoundException;
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
    GameBoard gameBoard;
    Items weapons;
    Inventory inventory;

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
                String useItem = prompter.prompt("Do you wish to eat or drink?", "eat|drink",
                        "Invalid choice. Please choose a valid choice!");
                useItem.toLowerCase();
                if (useItem.equals("eat")) {
//                    eat();
                    map.displayWildlife(wildlife, file);
                    map.printPlayerInfo(player);
                    wildlifePrompt(gameBoard, wildlife, player, map, file);
                } else if (useItem.equals("drink")) {
//                    drink();
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
    }

    private void drink(String drink) {
        Items drinkType = inventory.getItemFromInventory(drink);
        setHydration(drinkType.getHydration());
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
        this.health = health;
    }

    public int getHydration() {
        return hydration;
    }

    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
    @Override
    public String toString() {
        return  getName() + " | HP = " + getHealth() + " | hydration = " + getHydration() + " | strength = "
                + getStrength() + " | speed = " + getSpeed();
    }
}
