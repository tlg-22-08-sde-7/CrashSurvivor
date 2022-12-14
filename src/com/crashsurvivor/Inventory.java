package com.crashsurvivor;

import com.apps.util.Prompter;
import com.crashsurvivor.app.GameBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    List<Items> inventoryList;
    List<KeyItems> inventoryKeyItems;
    private final Prompter prompter = new Prompter(new Scanner(System.in));

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
        if (inventoryKeyItems.size() <= 0){
            System.out.println("No Key item(s)");
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

        if (allItems.size()<8){
            inventoryList.add(item);
            System.out.printf("%s, successfully added to the inventory!\n", item.getName().toUpperCase());
        }else {
            System.out.printf("You already have 7 items in your inventory, you cannot add more than 7 items in inventory");
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
/*    public void dropItems(Items item) {

        String apple = getItemFromInventory("apple").toString();
        String banana = getItemFromInventory("banana").toString();
        String machete = getItemFromInventory("machete").toString();
        String runningShoes = getItemFromInventory("running shoes").toString();
        String coconut = getItemFromInventory("coconut").toString();
        String spear = getItemFromInventory("spear").toString();
        if (inventoryList.contains(item)) {
            if (GameBoard.inputDirection != null && GameBoard.inputDirection != "") {
                if (GameBoard.inputDirection.equalsIgnoreCase("drop " + getItemFromInventory("water"))) {
                    item = getItemFromInventory("water");
                    String input = prompter.prompt("Are you sure you would like to drop " + item + "?"
                    , "yes|no", "Please choose a correct response!");
                    if (input.equalsIgnoreCase("yes")) {
                        removeFromInventory(item);
                    } else {

                    }
                }

                }
                    quitGame(6, gameBoard);
            inventoryList.remove(item);
        }

    }*/
/*    public void dropKeyItems(KeyItems item) {
        if (inventoryList.contains(item))
            inventoryKeyItems.remove(item);
    }*/

    public List<Items> getInventoryList() {
        return inventoryList;
    }

    public List<KeyItems> getInventoryKeyItems() {
        return inventoryKeyItems;
    }
}
