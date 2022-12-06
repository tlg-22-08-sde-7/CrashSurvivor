package com.crashsurvivor;

import java.util.List;

class Items {
    private String name;
    private int hydration;
    private int health;

    public Items(String name, int hydration, int health) {
        this.name = name;
        this.hydration = hydration;
        this.health = health;
    }
    public Items(int health, String name) {
        this.name = name;
        this.health = health;
    }
    public Items(String name, int hydration) {
        this.name = name;
        this.hydration = hydration;
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

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", hydration=" + hydration +
                ", health=" + health +
                '}';
    }
}
