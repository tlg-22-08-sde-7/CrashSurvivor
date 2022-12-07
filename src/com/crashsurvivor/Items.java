package com.crashsurvivor;

import java.util.List;

class Items {
    private String name;
    private int hydration;
    private int health;
    private int strength;
    private int speed;

    public Items(String name, int hydration, int health, int strength, int speed) {
        this.name = name;
        this.hydration = hydration;
        this.health = health;
        this.strength = strength;
        this.speed = speed;
    }

    public Items(String itemName) {
    }

    private void addItem() {

    }

    private void removeItem() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHydration() {
        return hydration;
    }

    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    @Override
    public String toString() {
        return "Item: " +
                getName() + " | HP = " + getHealth() + " | H2O = " + getHydration() + " | STR = " +
                getStrength() + " | SP = " + getSpeed();
    }
}
