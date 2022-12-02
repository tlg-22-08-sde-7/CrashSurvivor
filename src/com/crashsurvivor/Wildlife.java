package com.crashsurvivor;

class Wildlife {
    private String name;
    private int hp;
    private int attackPoints;

    public Wildlife(String name, int hp, int attackPoints) {
        setName(name);
        setHp(hp);
        setAttackPoints(attackPoints);
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
