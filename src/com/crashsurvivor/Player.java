package com.crashsurvivor;

class Player {
    private String name;
    private int health;
    private int hydration;
    private int strength;
    private int speed;

    public Player(String name, int health, int hydration, int strength, int speed) {
        setSpeed(speed);
        setName(name);
        setHealth(health);
        setHydration(hydration);
        setStrength(strength);
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
}
