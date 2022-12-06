package com.crashsurvivor;

class KeyItems {
    private String keyItems;

    public KeyItems(String keyItems) {
        this.keyItems = keyItems;
    }

    private void addKeyItem() {

    }

    private void removeKeyItem() {

    }

    public String getKeyItems() {
        return keyItems;
    }

    public void setKeyItems(String keyItems) {
        this.keyItems = keyItems;
    }

    @Override
    public String toString() {
        return "KeyItems{" +
                "keyItems='" + keyItems + '\'' +
                '}';
    }
}
