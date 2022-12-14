package com.crashsurvivor;

import com.apps.util.Prompter;
import com.crashsurvivor.app.GameBoard;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MapBoard {
    protected final Prompter prompter = new Prompter(new Scanner(System.in));
    protected List<Location> locationsList = new ArrayList<>();
    protected List<Description> descriptionsList = new ArrayList<>();
    protected List<Player> playersList = new ArrayList<>();
    protected List<Items> itemsList = new ArrayList<>();
    protected List<Wildlife> wildlifeList = new ArrayList<>();
    protected List<KeyItems> keyItemsList = new ArrayList<>();
    protected List<Direction> directionsList = new ArrayList<>();
    JsonArray jsonArrayOfWildlifeInLocation = new JsonArray();
    JsonArray jsonArrayOfItems = new JsonArray();
    JsonArray jsonArrayOfPlayers = new JsonArray();
    JsonArray jsonArrayofAllDirections = new JsonArray();
    JsonArray jsonArrayOfKeyItems = new JsonArray();
    JsonArray jsonArrayOfLocations = new JsonArray();
    JsonArray jsonArrayOfWildlife = new JsonArray();
    protected String wildlifeAtLocation;
    protected String locationName;
    String description;
    protected String currentLocation;
    protected Player player;
    protected Wildlife wildlife;
    protected Items items;
    protected Direction direction;
    protected KeyItems keyItems;
    protected Description descriptions;
    protected Location location;


    File input = new File("CrashSurvivor/resources/location.json");

    public void readAllJson() throws FileNotFoundException {

        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();
        jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
        jsonArrayOfWildlife = fileObject.get("wildlife").getAsJsonArray();

        for (JsonElement jsonLocationElement : jsonArrayOfLocations) {
            JsonObject jsonObjectOfLocations = jsonLocationElement.getAsJsonObject();
            locationName = jsonObjectOfLocations.get("name").getAsString();
            description = jsonObjectOfLocations.get("description").getAsString();
            jsonArrayOfWildlifeInLocation = jsonObjectOfLocations.get("wildlifeInLocation").getAsJsonArray();
            jsonArrayofAllDirections = jsonObjectOfLocations.get("directions").getAsJsonArray();
            jsonArrayOfItems = jsonObjectOfLocations.get("items").getAsJsonArray();
            jsonArrayOfKeyItems = jsonObjectOfLocations.get("keyItems").getAsJsonArray();

            descriptionData();
            keyItemData();
            wildlifeData();
            itemsData();
            directionsData();
            locationData();
        }


    }
    // This allows us to use the toString method of the Location class

    private void wildlifeData() {
        if (Objects.equals(player.getCurrentLocation(), locationName)) {
            for (JsonElement wildlifeElement : jsonArrayOfWildlife) {
                JsonObject wildlifeJsonObject = wildlifeElement.getAsJsonObject();
                String wildlifeName = wildlifeJsonObject.get("name").getAsString();
                JsonElement health = wildlifeJsonObject.get("health");
                JsonElement strength = wildlifeJsonObject.get("strength");
                JsonElement speed = wildlifeJsonObject.get("speed");

                wildlife = new Wildlife(wildlifeName,
                        health.getAsInt(), strength.getAsInt(), speed.getAsInt());
                wildlifeList.add(wildlife);
            }

            wildlifeAtLocation = jsonArrayOfWildlifeInLocation.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "");
        }
    }

    protected void currentWildlife() throws FileNotFoundException {

        if (wildlifeList.get(0).getName().equals(wildlifeAtLocation)) {
            wildlife = wildlifeList.get(0);
            try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/monkey.txt"))) {
                String line1;
                while ((line1 = br.readLine()) != null) {
                    System.out.println(line1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentWildlifeAtLocation();
        } else if (wildlifeList.get(1).getName().equals(wildlifeAtLocation)) {
            wildlife = wildlifeList.get(1);
            try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/wildboar.txt"))) {
                String line1;
                while ((line1 = br.readLine()) != null) {
                    System.out.println(line1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentWildlifeAtLocation();
        } else if (wildlifeList.get(2).getName().equals(wildlifeAtLocation)) {
            wildlife = wildlifeList.get(2);
            try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/bat.txt"))) {
                String line1;
                while ((line1 = br.readLine()) != null) {
                    System.out.println(line1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentWildlifeAtLocation();
        } else if (wildlifeList.get(3).getName().equals(wildlifeAtLocation)) {
            wildlife = wildlifeList.get(3);
            try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/snake.txt"))) {
                String line1;
                while ((line1 = br.readLine()) != null) {
                    System.out.println(line1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentWildlifeAtLocation();
        } else if (wildlifeList.get(4).getName().equals(wildlifeAtLocation)) {
            wildlife = wildlifeList.get(4);
            try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/crocodile.txt"))) {
                String line1;
                while ((line1 = br.readLine()) != null) {
                    System.out.println(line1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentWildlifeAtLocation();
        } else if (wildlifeList.get(5).getName().equals(wildlifeAtLocation)) {
            wildlife = wildlifeList.get(5);
            try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/rhino.txt"))) {
                String line1;
                while ((line1 = br.readLine()) != null) {
                    System.out.println(line1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentWildlifeAtLocation();
        }
    }

    private void printCurrentWildlife() {
        System.out.println(wildlife.toString()
                .replace("[", "")
                .replace("]", ""));
    }
    public void currentWildlifeAtLocation() throws FileNotFoundException {
        if (wildlifeAtLocation.equals(wildlife.getName()) && wildlife.getHealth() > 1) {
            printCurrentWildlife();
        }
    }

    public void playerJson() throws FileNotFoundException {
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();
        jsonArrayOfPlayers = fileObject.get("players").getAsJsonArray();
        for (JsonElement playerElement : jsonArrayOfPlayers) {
            JsonObject playerJsonObject = playerElement.getAsJsonObject();
            String name = playerJsonObject.get("name").getAsString();
            JsonElement health = playerJsonObject.get("health");
            JsonElement hydration = playerJsonObject.get("hydration");
            JsonElement strength = playerJsonObject.get("strength");
            JsonElement speed = playerJsonObject.get("speed");
            String currentLocation = playerJsonObject.get("currentLocation").getAsString();
            player = new Player(name, health.getAsInt(), hydration.getAsInt(),
                    strength.getAsInt(), speed.getAsInt(), currentLocation);
            playersList.add(player);
        }
    }

    public void printPlayersInfo() {
        for (Player player : playersList) {
            System.out.println(player.toString());
        }
    }

    public String getPlayersName(List<Player> allPlayers) {
        StringBuilder sb = new StringBuilder();
        //display all Players' Info
        sb.append("(?i)");
        for (Player player : allPlayers) {
            sb.append(player.getName());
            sb.append("|");
        }
        return sb.toString();
    }

    public void printOnePlayerData() throws FileNotFoundException {
        System.out.println("Choose from the following characters (" +
                "Arnold, Jennifer, Jason, or Scarlett):\n");
        playerJson();
        printPlayersInfo();

        String valueInput = prompter.prompt("Type your choice> ", getPlayersName(playersList),
                "Please select a valid character!");
        String chosenPlayer = valueInput.toLowerCase();

        for (Player p : playersList) {
            if (p.getName().toLowerCase().equalsIgnoreCase(chosenPlayer)) {
                player = p;
                System.out.println(player);
            }
        }
    }
    public void printSinglePlayerInfo() {
        System.out.println(player.toString());
    }

    private void keyItemData() {
        if (Objects.equals(player.getCurrentLocation(), locationName)) {
            for (JsonElement keyItemElement : jsonArrayOfKeyItems) {
                keyItems = new KeyItems(keyItemElement.getAsString());
                keyItemsList.add(keyItems);
            }
        }
    }

    private void printKeyItemsAtLocation() {
        System.out.println(keyItemsList.toString()
                .replace("[", "")
                .replace("]", ""));
    }

    private void itemsData() {
        if (Objects.equals(player.getCurrentLocation(), locationName)) {
            for (JsonElement itemElement : jsonArrayOfItems) {
                JsonObject itemJsonObject = itemElement.getAsJsonObject();
                String itemName = itemJsonObject.get("name").getAsString();
                JsonElement hydration = itemJsonObject.get("hydration");
                JsonElement health = itemJsonObject.get("health");
                JsonElement strength = itemJsonObject.get("strength");
                JsonElement speed = itemJsonObject.get("speed");

                items = new Items(itemName, hydration.getAsInt(),
                        health.getAsInt(), strength.getAsInt(), speed.getAsInt());
                itemsList.add(items);
            }
        }
    }

    private void printItemsAtLocation() {
        System.out.println(itemsList.toString()
                .replace("[", "")
                .replace("]", ""));
    }

    private void locationData() {

        if (Objects.equals(player.getCurrentLocation(), locationName)) {
            locationName = "{XX}";
        } else {
            locationName = "{  }";
        }
        location = new Location(locationName);
        // Every name and description is added to the locations List

        locationsList.add(location);

    }

    private void fLocations(List<Location> f) {
        System.out.println(f.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void eLocations(List<Location> e) {
        System.out.println(e.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void dLocations(List<Location> d) {
        System.out.println(d.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void cLocations(List<Location> c) {
        System.out.println(c.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void bLocations(List<Location> b) {
        System.out.println(b.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void aLocations(List<Location> a) {
        System.out.println(a.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void printMapOfLocations() {
        // Create sublists for the List<Locations>
        List<Location> alphaRow = locationsList.subList(0, 7);
        List<Location> bravoRow = locationsList.subList(7, 14);
        List<Location> charlieRow = locationsList.subList(14, 21);
        List<Location> deltaRow = locationsList.subList(21, 28);
        List<Location> echoRow = locationsList.subList(28, 35);
        List<Location> foxtrotRow = locationsList.subList(35, 42);

        // Create layout of the MapBoard, removing unnecessary items from Array
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        aLocations(alphaRow);
        bLocations(bravoRow);
        cLocations(charlieRow);
        dLocations(deltaRow);
        eLocations(echoRow);
        fLocations(foxtrotRow);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void descriptionData() {
        if (Objects.equals(player.getCurrentLocation(), locationName)) {
            descriptions = new Description(description);
            descriptionsList.add(descriptions);
        }
    }

    private void printDescriptions() {
        System.out.println(descriptionsList.toString()
                .replace("[", "")
                .replace("]", ""));
    }

    private void directionsData() {

        if (Objects.equals(player.getCurrentLocation(), locationName)) {
            for (JsonElement directionElement : jsonArrayofAllDirections) {
                JsonObject directionJsonObject = directionElement.getAsJsonObject();
                String directionName = directionJsonObject.get("directionName").getAsString();
                String place = directionJsonObject.get("place").getAsString();
                direction = new Direction(directionName, place);
                directionsList.add(direction);
            }

        }
    }

    public void clearData() {
        locationsList.clear();
        descriptionsList.clear();
        keyItemsList.clear();
        itemsList.clear();
        wildlifeList.clear();
        directionsList.clear();
        locationName = "";
    }

    public void refreshData() {
        for (JsonElement jsonLocationElement : jsonArrayOfLocations) {
            JsonObject jsonObjectOfLocations = jsonLocationElement.getAsJsonObject();
            locationName = jsonObjectOfLocations.get("name").getAsString();
            description = jsonObjectOfLocations.get("description").getAsString();
            jsonArrayOfWildlifeInLocation = jsonObjectOfLocations.get("wildlifeInLocation").getAsJsonArray();
            jsonArrayofAllDirections = jsonObjectOfLocations.get("directions").getAsJsonArray();
            jsonArrayOfItems = jsonObjectOfLocations.get("items").getAsJsonArray();
            jsonArrayOfKeyItems = jsonObjectOfLocations.get("keyItems").getAsJsonArray();

            descriptionData();
            keyItemData();
            wildlifeData();
            itemsData();
            directionsData();
            locationData();
        }
    }

    public void printMapData() throws FileNotFoundException {
        printMapOfLocations();
        printDescriptions();
        printSinglePlayerInfo();
    }
    public void printLookData() throws FileNotFoundException {
        printKeyItemsAtLocation();
        printItemsAtLocation();
        currentWildlife();
    }

}




