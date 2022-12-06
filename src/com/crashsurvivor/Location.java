package com.crashsurvivor;

import java.util.List;

class Location {

    private final String name;
    public List<Direction> directions = null;
    public List<String> wildlifeInLocation = null;
    public List<String> keyItems = null;


    public Location(String name) {
        this.name = name;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public List<String> getWildlifeInLocation() {
        return wildlifeInLocation;
    }

    public void setWildlifeInLocation(List<String> wildlifeInLocation) {
        this.wildlifeInLocation = wildlifeInLocation;
    }

    public List<String> getKeyItems() {
        return keyItems;
    }

    public void setKeyItems(List<String> keyItems) {
        this.keyItems = keyItems;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
