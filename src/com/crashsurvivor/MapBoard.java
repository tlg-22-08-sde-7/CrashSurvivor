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
    private String chosenPlayer;
    private Wildlife wildlifeAttributes;

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
            List<Location> A = locations.subList(0, 7);
            List<Location> B = locations.subList(7, 14);
            List<Location> C = locations.subList(14, 21);
            List<Location> D = locations.subList(21, 28);
            List<Location> E = locations.subList(28, 35);
            List<Location> F = locations.subList(35, 42);

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
    public List<Direction> getAllDirections() throws FileNotFoundException {
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

    public Player printPlayerData() throws FileNotFoundException {
        Player player = null;
        System.out.println("Choose from the following characters (" +
                "Arnold, Jennifer, Jason, or Scarlett):\n");
        List<Player> allPlayers = getAllPlayerData();
        printPlayersInfo(allPlayers);

        String valueInput = prompter.prompt("Type your choice> ", getPlayersName(allPlayers),
                "Please select a valid character!");
        chosenPlayer = valueInput.toLowerCase();

        for (Player p : allPlayers) {
            if (p.getName().toLowerCase().equalsIgnoreCase(chosenPlayer)) {
                player = p;
                break;
            }
        }

        return player;
    }

    private List<Player> getAllPlayerData() throws FileNotFoundException {
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfPlayers = fileObject.get("players").getAsJsonArray();
        List<Player> data = new ArrayList<>();

        allPlayersList(jsonArrayOfPlayers, data);
        return data;
    }

    private void printPlayersInfo(List<Player> data) {
        for (Player player : data) {
            System.out.println(player.toString());
        }
    }

    public void printPlayerInfo(Player player) {
        System.out.println(player.toString());
    }

    private String getPlayersName(List<Player> allPlayers) {
        StringBuilder sb = new StringBuilder();
        //display all Players' Info
        for (Player player : allPlayers) {
            sb.append(player.getName());
            sb.append("|");
        }
        return sb.toString();
    }

    private void allPlayersList(JsonArray jsonArrayOfPlayers, List<Player> data) {
        for (JsonElement playerElement : jsonArrayOfPlayers) {
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
    public void showItemsAtLocation() throws FileNotFoundException {
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
                    JsonElement strength = itemJsonObject.get("strength");
                    JsonElement speed = itemJsonObject.get("speed");
                    Items itemsAtLocation = new Items(itemName, hydration.getAsInt(),
                            health.getAsInt(), strength.getAsInt(), speed.getAsInt());
                    items.add(itemsAtLocation);

                }
                System.out.println(items.toString()
                        .replace("[", "")
                        .replace("]", ""));
            }
        }
    }

    public  List<Items> getItemsAtLocation(String currentLocation) throws FileNotFoundException {
        List<Items> items = new ArrayList<>();
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();

        JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
        for (JsonElement jsonLocationElement : jsonArrayOfLocations) {
            JsonObject jsonObjectOfItems = jsonLocationElement.getAsJsonObject();
            String name = jsonObjectOfItems.get("name").getAsString();
            JsonArray jsonArrayOfItems = jsonObjectOfItems.get("items").getAsJsonArray();

            if (Objects.equals(currentLocation, name)) {
                for (JsonElement itemElement : jsonArrayOfItems) {
                    JsonObject itemJsonObject = itemElement.getAsJsonObject();
                    String itemName = itemJsonObject.get("name").getAsString();
                    JsonElement hydration = itemJsonObject.get("hydration");
                    JsonElement health = itemJsonObject.get("health");
                    JsonElement strength = itemJsonObject.get("strength");
                    JsonElement speed = itemJsonObject.get("speed");

                    Items itemsAtLocation = new Items(itemName, hydration.getAsInt(),
                            health.getAsInt(), strength.getAsInt(), speed.getAsInt());

                    items.add(itemsAtLocation);
                }
                break;
            }
        }
        return items;
    }

    // get inventory items and key items from json file location.json and display items in inventory
//    public void showInventory() throws FileNotFoundException {
//        List<Items> inventoryItems = new ArrayList<>();
//        List<Items> keyItems = new ArrayList<>();
//        try {
//            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
//            JsonObject fileObject = fileElement.getAsJsonObject();
//            JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
//
//            for (JsonElement locationElement : jsonArrayOfLocations) {
//                JsonObject locationJsonObject = locationElement.getAsJsonObject();
//                String name = locationJsonObject.get("name").getAsString();
//                JsonArray jsonArrayOfItems = locationJsonObject.get("items").getAsJsonArray();
//
//                if (Objects.equals(Player.getCurrentLocation(), name)) {
//                    for (JsonElement itemElement : jsonArrayOfItems) {
//                        JsonObject itemJsonObject = itemElement.getAsJsonObject();
//                        String itemName = itemJsonObject.get("name").getAsString();
//                        Items itemsAtLocation = new Items(itemName);
//                        inventoryItems.add(itemsAtLocation);
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
//            JsonObject fileObject = fileElement.getAsJsonObject();
//            JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
//
//            for (JsonElement locationElement : jsonArrayOfLocations) {
//                JsonObject locationJsonObject = locationElement.getAsJsonObject();
//                String name = locationJsonObject.get("name").getAsString();
//                JsonArray jsonArrayOfItems = locationJsonObject.get("keyItems").getAsJsonArray();
//
//                if (Objects.equals(Player.getCurrentLocation(), name)) {
//                    for (JsonElement itemElement : jsonArrayOfItems) {
//                        JsonObject itemJsonObject = itemElement.getAsJsonObject();
//                        String itemName = itemJsonObject.get("name").getAsString();
//                        Items itemsAtLocation = new Items(itemName);
//                        keyItems.add(itemsAtLocation);
//                    }
//                }
//            }
//
//    } catch (FileNotFoundException e) {
//        e.printStackTrace();
//    }
//        System.out.println("Inventory: " + inventoryItems.toString()
//                .replace("[", "")
//                .replace("]", ""));
//        System.out.println("Key Items: " + keyItems.toString()
//                .replace("[", "")
//                .replace("]", ""));
//    }




    // show key items at the locations
    public void showKeyItemsAtLocation() throws FileNotFoundException{

        List<KeyItems> keyItems = new ArrayList<>();
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();

        JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
        for (JsonElement jsonLocationElement : jsonArrayOfLocations) {
            JsonObject jsonObjectOfLocations = jsonLocationElement.getAsJsonObject();
            String name = jsonObjectOfLocations.get("name").getAsString();
            if (Objects.equals(Player.getCurrentLocation(), name)) {
                JsonArray jsonArrayOfKeyItems = jsonObjectOfLocations.get("keyItems").getAsJsonArray();
                String keyItemName = jsonArrayOfKeyItems.toString();
                KeyItems keyItemsAtLocation = new KeyItems(keyItemName);
                keyItems.add(keyItemsAtLocation);

                System.out.println(keyItems.toString()
                        .replace("[", "")
                        .replace("]", ""));
            }
        }
    }
    // show wildlife at the locations
    public void showWildlifeAtLocation() throws FileNotFoundException {
        List<Wildlife> wildlife = new ArrayList<>();
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();

        JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
        for (JsonElement jsonLocationElement : jsonArrayOfLocations) {
            JsonObject jsonObjectOfWildlife = jsonLocationElement.getAsJsonObject();
            String name = jsonObjectOfWildlife.get("name").getAsString();
            JsonArray jsonArrayOfWildlifeInLocation = jsonObjectOfWildlife.get("wildlifeInLocation").getAsJsonArray();
            String wildlifeAtLocation = jsonArrayOfWildlifeInLocation.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "");
            if (Objects.equals(Player.getCurrentLocation(), name)) {
                JsonArray jsonArrayOfWildlife = fileObject.get("wildlife").getAsJsonArray();
                for (JsonElement itemElement : jsonArrayOfWildlife) {
                    JsonObject itemJsonObject = itemElement.getAsJsonObject();
                    String wildlifeName = itemJsonObject.get("name").getAsString();
                    JsonElement health = itemJsonObject.get("health");
                    JsonElement strength = itemJsonObject.get("strength");
                    JsonElement speed = itemJsonObject.get("speed");
                    wildlifeAttributes = new Wildlife(wildlifeName,
                            health.getAsInt(), strength.getAsInt(), speed.getAsInt());
                    wildlife.add(wildlifeAttributes);
                }

                List<Wildlife> monkey = wildlife.subList(0, 1);
                List<Wildlife> wildBoar = wildlife.subList(1, 2);
                List<Wildlife> bat = wildlife.subList(2, 3);
                List<Wildlife> snake = wildlife.subList(3, 4);
                List<Wildlife> rhino = wildlife.subList(4, 5);
                List<Wildlife> crocodile = wildlife.subList(5, 6);
                currentWildlife(wildlifeAtLocation, monkey, "Monkey", "CrashSurvivor/resources/monkey.txt");
                currentWildlife(wildlifeAtLocation, wildBoar, "Wild Boar", "CrashSurvivor/resources/wildboar.txt");
                currentWildlife(wildlifeAtLocation, snake, "Snake", "CrashSurvivor/resources/snake.txt");
                currentWildlife(wildlifeAtLocation, rhino, "Rhino", "CrashSurvivor/resources/rhino.txt");
                currentWildlife(wildlifeAtLocation, crocodile, "Crocodile", "CrashSurvivor/resources/crocodile.txt");
                currentWildlife(wildlifeAtLocation, bat, "Bat", "CrashSurvivor/resources/bat.txt");
            }
        }
    }
    private void currentWildlife(String wildlifeAtLocation, List<Wildlife> animal, String wildlife, String wildlifeFile) {
        if (wildlifeAtLocation.equals(wildlife)) {
            displayWildlife(wildlifeFile);
            System.out.println(animal.toString()
                    .replace("[", "")
                    .replace("]", ""));
        }
    }
    private void displayWildlife(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line1;
            while ((line1 = br.readLine()) != null) {
                System.out.println(line1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void removeWildlife(List<Wildlife> wildlife) throws FileNotFoundException {
        if (wildlifeAttributes.getHealth() <= 0) {
            showWildlifeAtLocation();
        }
    }
}



