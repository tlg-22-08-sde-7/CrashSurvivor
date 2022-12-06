package com.crashsurvivor;

public class Player {
    private static String currentLocation = "A1";
    private String name;
    private int health;
    private int hydration;
    private int strength;
    private int speed;
    String currentPlayer;

    public Player(String name, int health, int hydration, int strength, int speed, String currentLocation) {
        setSpeed(speed);
        setName(name);
        setHealth(health);
        setHydration(hydration);
        setStrength(strength);
        setCurrentLocation(currentLocation);
    }




    private void move() {

    }

    private void attack() {

    }

    private void eat() {

    }

    private void drink() {

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

    @Override
    public String toString() {
        return  getName() + " | HP = " + getHealth() + " | hydration = " + getHydration() + " | strength = "
                + getStrength() + " | speed = " + getSpeed();
    }
}
