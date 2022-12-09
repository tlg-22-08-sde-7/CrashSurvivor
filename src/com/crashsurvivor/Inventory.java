package com.crashsurvivor;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Items> inventoryList;
    List<KeyItems> inventoryKeyItems;

    public Inventory() {
        inventoryList = new ArrayList<>();
        inventoryKeyItems = new ArrayList<>();
    }

    public void showInventory() {
        System.out.println("Inventory: ");
        for(Items item: inventoryList){
            System.out.println(item.toString());
        }
        for (KeyItems item: inventoryKeyItems){
            System.out.println(item.toString());
        }
    }

    public void addToInventory(Items item) {
        inventoryList.add(item);
    }

    public void addToKeyItemsInventory(KeyItems item) {
        inventoryKeyItems.add(item);
    }

    private void removeFromInventory(Items item) {
        inventoryList.remove(item);
    }

    private void removeFromKeyItemsInventory(KeyItems item) {
        inventoryKeyItems.remove(item);
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

    public KeyItems getKeyItemFromInventory(String keyItem){
        KeyItems item = null;
        if(inventoryKeyItems != null && inventoryKeyItems.size() > 0){
            for(KeyItems i: inventoryKeyItems){
                if(keyItem.equalsIgnoreCase(item.getKeyItems())){
                    item = i;
                    break;
                }
            }
        }
        return item;
    }
}
