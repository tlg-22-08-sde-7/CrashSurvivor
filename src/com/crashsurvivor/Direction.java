package com.crashsurvivor;

public class Direction {

    public String directionName;
    public String place;

    public Direction(String directionName, String place) {
        this.directionName = directionName;
        this.place = place;
    }

    public String getDirectionName() {
        return directionName;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "directionName='" + directionName + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
