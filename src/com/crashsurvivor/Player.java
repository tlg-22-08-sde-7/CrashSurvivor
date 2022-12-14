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



    private void flee() {

    }

    private void eat(String food) {
        Items foodType = inventory.getItemFromInventory(food);
        this.health += foodType.getHealth();
        clearConsole();

        System.out.printf("%s, used.\nYour health has been recovered by, %s.\n", food,  foodType.getHealth());

        getInventory().removeFromInventory(foodType);
        pressToContinue();
        getInventory().showInventory();

    }

    private void drink(String drink) {
        Items drinkType = inventory.getItemFromInventory(drink);
        this.hydration += drinkType.getHydration();
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

    public void clearConsole() {
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
