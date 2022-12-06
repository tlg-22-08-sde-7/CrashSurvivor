package com.crashsurvivor;

import com.apps.util.Prompter;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MapBoard {
    private Prompter prompter = new Prompter(new Scanner(System.in));
    String currentPlayer;

    File input = new File("CrashSurvivor/resources/location.json");


    public void printMap() {
        // make path of json into a variable

        try {
            // new JsonElement using JsonParser to parse through the file
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            // convert to JsonObject
            JsonObject fileObject = fileElement.getAsJsonObject();
            // get the entire locations array with all of the objects inside
            JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
            List<Location> locations = new ArrayList<>();
            // take all of the json elements inside of "locations" and separate them
            allLocationsList(jsonArrayOfLocations, locations);

            // Create sublists for the List<Locations>
            List<Location> A = locations.subList(0,7);
            List<Location> B = locations.subList(7,14);
            List<Location> C = locations.subList(14,21);
            List<Location> D = locations.subList(21,28);
            List<Location> E = locations.subList(28,35);
            List<Location> F = locations.subList(35,42);

            // Create layout of the MapBoard, removing unnecessary items from Array
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            aLocations(A);
            bLocations(B);
            cLocations(C);
            dLocations(D);
            eLocations(E);
            fLocations(F);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

    private void allLocationsList(JsonArray jsonArrayOfLocations, List<Location> locations) {
        for (JsonElement locationElement : jsonArrayOfLocations) {
            JsonObject locationJsonObject = locationElement.getAsJsonObject();
            // get the "name" objects
            String name = locationJsonObject.get("name").getAsString();
            // Need to build a getCurrentLocation method for the map to pull data from

            if (Objects.equals(Player.getCurrentLocation(), name)) {
                name = "{XX}";
            } else {
                name = "{  }";
            }

            // This allows us to use the toString method of the Location class
            Location location = new Location(name);
            // Every name and description is added to the locations List
            locations.add(location);
        }
    }

    public void printDescriptionData() {
        try {
            getLocationDescriptions();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getLocationDescriptions() throws FileNotFoundException {
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
        List<Description> descriptions = new ArrayList<>();

        allLocationDescriptions(jsonArrayOfLocations, descriptions);
    }

    private void allLocationDescriptions(JsonArray jsonArrayOfLocations, List<Description> descriptions) {

        for (JsonElement locationElement : jsonArrayOfLocations) {
            JsonObject locationJsonObject = locationElement.getAsJsonObject();
            String name = locationJsonObject.get("name").getAsString();
            String description = locationJsonObject.get("description").getAsString();

            if (Objects.equals(Player.getCurrentLocation(), name)) {
                System.out.println(description);
                Description place = new Description(description);
                descriptions.add(place);
                System.out.println();
            }
        }
    }

    // get all directions
    public List<Direction> getAllDirections() throws FileNotFoundException{
        List<Direction> directions = new ArrayList<>();

        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();

        for (JsonElement locationElement : jsonArrayOfLocations) {
            JsonObject locationJsonObject = locationElement.getAsJsonObject();
            String name = locationJsonObject.get("name").getAsString();
            JsonArray allDirections = locationJsonObject.get("directions").getAsJsonArray();

            if (Objects.equals(Player.getCurrentLocation(), name)) {
                for (JsonElement directionElement : allDirections) {
                    JsonObject directionJsonObject = directionElement.getAsJsonObject();
                    String directionName = directionJsonObject.get("directionName").getAsString();
                    String place = directionJsonObject.get("place").getAsString();

                    Direction direction = new Direction(directionName, place);
                    directions.add(direction);
                }
            }
        }
        return directions;
    }
    public String currentPlayerData(String currentPlayer) {
        return currentPlayer;
    }

    public String printPlayerData() throws FileNotFoundException {
        System.out.println("Choose from the following characters (" +
                "Arnold, Jennifer, Jason, or Scarlett):\n");

        arnoldData();
        jenniferData();
        jasonData();
        scarlettData();
        String valueInput = prompter.prompt("Type your choice> ", "(?i)arnold|jennifer|jason|scarlett",
                "Please select a valid character!");
        String ignoredExtraValueInput = valueInput.toLowerCase();
        switch (ignoredExtraValueInput) {
            case "arnold":
                currentPlayer = arnoldData();
                return currentPlayer;
            case "jennifer":
                return jenniferData();
            case "jason":
                return jasonData();
            case "scarlett":
                return scarlettData();
            default:
                return printPlayerData();
        }
    }

    private String scarlettData() throws FileNotFoundException {
        List<Player> data = getPlayerData();
        scarlett(data);
        return null;
    }

    private String jasonData() throws FileNotFoundException {
        List<Player> data = getPlayerData();
        jason(data);
        return null;
    }

    private String jenniferData() throws FileNotFoundException {
        List<Player> data = getPlayerData();
        jennifer(data);
        return null;
    }

    private String arnoldData() throws FileNotFoundException {
        List<Player> data = getPlayerData();
        arnold(data);
        return null;
    }

    private List<Player> getPlayerData() throws FileNotFoundException {
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfLocations = fileObject.get("players").getAsJsonArray();
        List<Player> data = new ArrayList<>();

        allPlayersList(jsonArrayOfLocations, data);
        return data;
    }

    private void scarlett(List<Player> data) {
        List<Player> scarlett = data.subList(3,4);
        System.out.println(scarlett.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void jason(List<Player> data) {
        List<Player> jason = data.subList(2,3);
        System.out.println(jason.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void jennifer(List<Player> data) {
        List<Player> jennifer = data.subList(1,2);
        System.out.println(jennifer.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void arnold(List<Player> data) {
        List<Player> arnold = data.subList(0,1);
        System.out.println(arnold.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    private void allPlayersList(JsonArray jsonArrayOfLocations, List<Player> data) {
        for (JsonElement playerElement : jsonArrayOfLocations) {
            JsonObject playerJsonObject = playerElement.getAsJsonObject();

            String name = playerJsonObject.get("name").getAsString();
            JsonElement health = playerJsonObject.get("health");
            JsonElement hydration = playerJsonObject.get("hydration");
            JsonElement strength = playerJsonObject.get("strength");
            JsonElement speed = playerJsonObject.get("speed");
            String currentLocation = playerJsonObject.get("currentLocation").getAsString();


            Player player = new Player(name, health.getAsInt(), hydration.getAsInt(),
                    strength.getAsInt(), speed.getAsInt(), currentLocation);

            data.add(player);

        }
    }
    // show all of the items at location
    private List<Items> showItemsAtLocation() throws FileNotFoundException {
        List<Items> items = new ArrayList<>();
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();

        JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
        for (JsonElement jsonLocationElement : jsonArrayOfLocations) {
            JsonObject jsonObjectOfItems = jsonLocationElement.getAsJsonObject();
            String name = jsonObjectOfItems.get("name").getAsString();
            JsonArray jsonArrayOfItems = jsonObjectOfItems.get("items").getAsJsonArray();

            if (Objects.equals(Player.getCurrentLocation(), name)) {
                for (JsonElement itemElement : jsonArrayOfItems) {

                    JsonObject itemJsonObject = itemElement.getAsJsonObject();
                    String itemName = itemJsonObject.get("name").getAsString();
                    JsonElement hydration = itemJsonObject.get("hydration");
                    JsonElement health = itemJsonObject.get("health");
                    Items itemsAtLocation = new Items(itemName, hydration.getAsInt(), health.getAsInt());
                    items.add(itemsAtLocation);

                }
                System.out.println(items);
            }
        }
        return items;
    }

    public static void main(String[] args) throws FileNotFoundException {

        MapBoard map = new MapBoard();
        map.printPlayerData();
        map.printMap();
        map.printDescriptionData();
        map.showItemsAtLocation();

        System.out.println();

    }
}



