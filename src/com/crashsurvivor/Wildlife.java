package com.crashsurvivor;

class Wildlife {
    private String name;
    private int health;
    private int strength;
    private int speed;

    public Wildlife(String name, int health, int strength, int speed) {
        setSpeed(speed);
        setName(name);
        setHealth(health);
        setStrength(strength);
    }

    private void attack() {

    }

    private void retreat() {

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
        return "Wildlife = " + getName() + " | health = " + getHealth() + " | strength = " +
                getStrength() + " | speed = " + getSpeed();
    }
}
