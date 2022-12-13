package com.crashsurvivor;

public class KeyItems {
    private String keyItems;

    public KeyItems(String keyItems) {
        setKeyItems(keyItems);
    }

    public String getKeyItems() {
        return keyItems;
    }

    public void setKeyItems(String keyItems) {
        this.keyItems = keyItems;
    }

    @Override
    public String toString() {
            return "Key Item: " + keyItems;
    }
}
