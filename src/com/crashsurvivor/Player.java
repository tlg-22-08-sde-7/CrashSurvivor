package com.crashsurvivor;

class Player {
    private String name;
    private int hp;
    private int hydration;
    private int attackPoints;

    public Player(String name, int hp, int hydration, int attackPoints) {
        setName(name);
        setHp(hp);
        setHydration(hydration);
        setAttackPoints(attackPoints);
    }

    private void move() {

    }

    private void attack(){

    }

    private void eat(){

    }

    private void drink(){

    }

    private void build() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHydration() {
        return hydration;
    }

    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
