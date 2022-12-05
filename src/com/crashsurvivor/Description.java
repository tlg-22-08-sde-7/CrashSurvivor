package com.crashsurvivor;

class Description {
    private final String description;

    Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Description of Area:\n>" + getDescription();
    }
}
