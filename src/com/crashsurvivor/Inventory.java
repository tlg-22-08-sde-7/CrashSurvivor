package com.crashsurvivor;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Items> inventoryList;

    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    public Inventory(String inventoryName, String inventoryDescription) {
    }

    public void showInventory() {
        System.out.println("Inventory Items: ");
        for(Items item: inventoryList){
            System.out.println(item.toString());
        }
    }

    public void addToInventory(Items item) {
        inventoryList.add(item);
    }

    private void removeFromInventory(Items item) {
        inventoryList.remove(item);
    }

    public Items getItemFromInventory(String itemName){
        Items item = null;
        if(inventoryList != null && inventoryList.size() > 0){
            for(Items i: inventoryList){
                if(itemName.equalsIgnoreCase(item.getName())){
                    item = i;
                    break;
                }
            }
        }
        return item;
    }
}
