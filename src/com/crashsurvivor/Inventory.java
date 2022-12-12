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
        if (inventoryList.size() <= 0){
            System.out.println("No item(s)");
        }
        for(Items item: inventoryList){
            System.out.println(item.toString());
        }
        for (KeyItems item: inventoryKeyItems){
            System.out.println(item.toString());
        }
    }

    public void addToInventory(Items item) {
        List<String> allItems = new ArrayList<>();
        for (Items ki: inventoryList){
            allItems.add(ki.getName());
        }

        if (!allItems.contains(item.getName())){
            inventoryList.add(item);
            System.out.printf("%s, successfully added to the inventory!\n", item.getName().toUpperCase());
        }else {
            System.out.printf("%s, is already in the inventory,\n", item.getName());
        }

    }

    public void addToKeyItemsInventory(KeyItems item) {
        List<String> allKeyItems = new ArrayList<>();
        for (KeyItems ki: inventoryKeyItems){
            allKeyItems.add(ki.getKeyItems());
        }

        if (!allKeyItems.contains(item.getKeyItems())){
            inventoryKeyItems.add(item);
            System.out.printf("%s, successfully added to the inventory!\n", item.getKeyItems().toUpperCase());
        }else {
            System.out.printf("%s, is already in the inventory\n", item.getKeyItems());
        }

    }

    public void removeFromInventory(Items item) {
        inventoryList.remove(item);
    }

    private void removeFromKeyItemsInventory(KeyItems item) {
        inventoryKeyItems.remove(item);
    }

    public Items getItemFromInventory(String itemName){
        Items item = null;
        if(inventoryList != null && inventoryList.size() > 0){
            for(Items i: inventoryList){
                if(itemName.equalsIgnoreCase(i.getName())){
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

    public List<Items> getInventoryList() {
        return inventoryList;
    }

    public List<KeyItems> getInventoryKeyItems() {
        return inventoryKeyItems;
    }
}
