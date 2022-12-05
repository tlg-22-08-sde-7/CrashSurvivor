package com.crashsurvivor;

public enum Map {
    A0("You are standing on the outside of your jet. " +
            "You can choose to go South or East, but not North or West."),
    A1("You can choose to go South, West, or East, but not North."),
    A2("You can choose to go South, West, or East, but not North."),
    A3("You can choose to go South, West, or East, but not North."),
    A4("You can choose to go South, West, or East, but not North."),
    A5("You can choose to go South, West, or East, but not North."),
    A6("You can choose to go South or West, but not North or East."),
    B0("You can choose to go North, East, or South, but not West."),
    B1("You can choose to go North, East, South, or West."),
    B2("You can choose to go North, East, South, or West."),
    B3("You can choose to go North, East, South, or West."),
    B4("You can choose to go North, East, South, or West."),
    B5("You can choose to go North, East, South, or West."),
    B6("You can choose to go North, South, or West, but not East."),
    C0("You can choose to go North, East, or South, but not West."),
    C1("You can choose to go North, East, South, or West."),
    C2("You can choose to go North, East, South, or West."),
    C3("You can choose to go North, East, South, or West."),
    C4("You can choose to go North, East, South, or West."),
    C5("You can choose to go North, East, South, or West."),
    C6("You can choose to go North, South, or West, but not East."),
    D0("You can choose to go North, East, or South, but not West."),
    D1("You can choose to go North, East, South, or West."),
    D2("You can choose to go North, East, South, or West."),
    D3("You can choose to go North, East, South, or West."),
    D4("You can choose to go North, East, South, or West."),
    D5("You can choose to go North, East, South, or West."),
    D6("You can choose to go North, South, or West, but not East."),
    E0("You can choose to go North, East, or South, but not West."),
    E1("You can choose to go North, East, South, or West."),
    E2("You can choose to go North, East, South, or West."),
    E3("You can choose to go North, East, South, or West."),
    E4("You can choose to go North, East, South, or West."),
    E5("You can choose to go North, East, South, or West."),
    E6("You can choose to go North, South, or West, but not East."),
    F0("You can choose to go North or East, but not South or West."),
    F1("You can choose to go North, East, or West, but not South."),
    F2("You can choose to go North, East, or West, but not South."),
    F3("You can choose to go North, East, or West, but not South."),
    F4("You can choose to go North, East, or West, but not South."),
    F5("You can choose to go North, East, or West, but not South."),
    F6("You can choose to go North or West, but not South or East.");

    private String description;

    Map(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static Map fromString(String description) {
        for (Map b : Map.values()) {
            if (b.description.equalsIgnoreCase(description)) {
                return b;
            }
        }
        return null;
    }
}
