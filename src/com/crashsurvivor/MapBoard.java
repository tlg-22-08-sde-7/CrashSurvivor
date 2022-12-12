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
    private final Prompter prompter = new Prompter(new Scanner(System.in));
//    List<Location> locations = new ArrayList<>();
//    List<Description> descriptions = new ArrayList<>();
//    List<Player> playersList = new ArrayList<>();
//    List<Items> items = new ArrayList<>();
//    List<Wildlife> wildlifeList = new ArrayList<>();
//    List<KeyItems> keyItems = new ArrayList<>();
//    List<Direction> directions = new ArrayList<>();
//    JsonArray jsonArrayOfWildlifeInLocation = new JsonArray();
//    Player player;
//    Wildlife wildlife;

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
            List<Location> alphaRow = locations.subList(0, 7);
            List<Location> bravoRow = locations.subList(7, 14);
            List<Location> charlieRow = locations.subList(14, 21);
            List<Location> deltaRow = locations.subList(21, 28);
            List<Location> echoRow = locations.subList(28, 35);
            List<Location> foxtrotRow = locations.subList(35, 42);

            // Create layout of the MapBoard, removing unnecessary items from Array
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            aLocations(alphaRow);
            bLocations(bravoRow);
            cLocations(charlieRow);
            dLocations(deltaRow);
            eLocations(echoRow);
            fLocations(foxtrotRow);
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
        String chosenPlayer = valueInput.toLowerCase();

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

    public void printPlayersInfo(List<Player> data) {
        for (Player player : data) {
            System.out.println(player.toString());
        }
    }

    public void printPlayerInfo(Player player) {
        System.out.println(player.toString());
    }
    public Player getPlayerInfo(Player player) {
        return player;
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

    private void allPlayersList(JsonArray jsonArrayOfPlayers, List<Player> data) {
        for (JsonElement playerElement : jsonArrayOfPlayers) {
            JsonObject playerJsonObject = playerElement.getAsJsonObject();

            String name = playerJsonObject.get("name").getAsString();
            JsonElement health = playerJsonObject.get("health");
            JsonElement hydration = playerJsonObject.get("hydration");
            JsonElement strength = playerJsonObject.get("strength");
            JsonElement speed = playerJsonObject.get("speed");
            String currentLocation = playerJsonObject.get("currentLocation").getAsString();

            Player player1 = new Player(name, health.getAsInt(), hydration.getAsInt(),
                    strength.getAsInt(), speed.getAsInt(), currentLocation);

            data.add(player1);

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

    public List<Items> getItemsAtLocation(String currentLocation) throws FileNotFoundException {
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

    public List<KeyItems> getKeyItemsAtLocation(String currentLocation) throws FileNotFoundException {

        List<KeyItems> keyItems = new ArrayList<>();
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();

        JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
        for (JsonElement jsonLocationElement : jsonArrayOfLocations) {
            JsonObject jsonObjectOfLocations = jsonLocationElement.getAsJsonObject();
            String name = jsonObjectOfLocations.get("name").getAsString();

            if (Objects.equals(currentLocation, name)) {
                JsonArray jsonArrayOfKeyItems = jsonObjectOfLocations.get("keyItems").getAsJsonArray();
                for (JsonElement keyItemElement : jsonArrayOfKeyItems) {
                    KeyItems keyItemsAtLocation = new KeyItems(keyItemElement.getAsString());
                    keyItems.add(keyItemsAtLocation);
                }
                break;
            }
        }
        return keyItems;
    }

    // show key items at the locations
    public void showKeyItemsAtLocation() throws FileNotFoundException {

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
    public void showWildlifeAtLocation(Wildlife wildlife, Player player, MapBoard map, GameBoard gameBoard) throws FileNotFoundException {
        List<Wildlife> wildlifeList = new ArrayList<>();
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
                    wildlife = new Wildlife(wildlifeName,
                            health.getAsInt(), strength.getAsInt(), speed.getAsInt());
                    wildlifeList.add(wildlife);
                }

                if (wildlifeList.get(0).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(0);
                    currentWildlife(wildlifeAtLocation, "Monkey", "CrashSurvivor/resources/monkey.txt", wildlife, player, map, gameBoard);
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(1).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(1);
                    currentWildlife(wildlifeAtLocation, "Wild Boar", "CrashSurvivor/resources/wildboar.txt", wildlife, player, map, gameBoard);
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(2).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(2);
                    currentWildlife(wildlifeAtLocation, "Bat", "CrashSurvivor/resources/bat.txt", wildlife, player, map, gameBoard);
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(3).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(3);
                    currentWildlife(wildlifeAtLocation, "Snake", "CrashSurvivor/resources/snake.txt", wildlife, player, map, gameBoard);
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(4).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(4);
                    System.out.println(jsonArrayOfWildlifeInLocation);
                    currentWildlife(wildlifeAtLocation, "Crocodile", "CrashSurvivor/resources/crocodile.txt", wildlife, player, map, gameBoard);
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(5).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(5);
                    currentWildlife(wildlifeAtLocation, "Rhino", "CrashSurvivor/resources/rhino.txt", wildlife, player, map, gameBoard);
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                }
            }
        }
    }

    private void currentWildlife(String wildlifeAtLocation, String wildlifeName, String wildlifeFile,
                                 Wildlife wildlife, Player player, MapBoard map, GameBoard gameBoard) throws FileNotFoundException {
        if (wildlifeAtLocation.equals(wildlife.getName()) && wildlife.getHealth() > 1) {
            displayWildlife(wildlife, wildlifeFile);
            printWildlife(wildlife);
            player.wildlifePrompt(gameBoard, wildlife, player, map, wildlifeFile);
        }
    }


    private void printWildlife(Wildlife wildlife) {
        System.out.println(wildlife.toString()
                .replace("[", "")
                .replace("]", ""));
    }

    public void displayWildlife(Wildlife wildlife, String file) {
        printWildlife(wildlife);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line1;
            while ((line1 = br.readLine()) != null) {
                System.out.println(line1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeWildlife(Wildlife wildlife) throws FileNotFoundException {
        if (wildlife.getHealth() <= 0) {
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
                for (JsonElement element : jsonArrayOfWildlifeInLocation) {
                    String wildlifeString = element.getAsString().replace("\"", "");
                    if (wildlifeString.equals(wildlife.getName()) && Player.getCurrentLocation().equals(name)) {
                        char quotes = '"';
                        wildlifeString = quotes + wildlifeString + quotes;
                        if (element.toString().equals(wildlifeString)) {
                            JsonArray jsonArrayOfOneWildlife = jsonObjectOfWildlife.get("wildlifeInLocation").getAsJsonArray();
                            jsonArrayOfOneWildlife.remove(0);
                        }
                    }
                }
            }
        }
    }
    /*public void readAllJson() throws FileNotFoundException {

        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
        JsonArray jsonArrayOfPlayers = fileObject.get("players").getAsJsonArray();
        JsonArray jsonArrayOfWildlife = fileObject.get("wildlife").getAsJsonArray();
        for (JsonElement jsonLocationElement : jsonArrayOfLocations) {
            JsonObject jsonObjectOfLocations = jsonLocationElement.getAsJsonObject();
            String name = jsonObjectOfLocations.get("name").getAsString();
            String description = jsonObjectOfLocations.get("description").getAsString();
            jsonArrayOfWildlifeInLocation = jsonObjectOfLocations.get("wildlifeInLocation").getAsJsonArray();
            JsonArray allDirections = jsonObjectOfLocations.get("directions").getAsJsonArray();
            JsonArray jsonArrayOfItems = jsonObjectOfLocations.get("items").getAsJsonArray();
            JsonArray jsonArrayOfKeyItems = jsonObjectOfLocations.get("keyItems").getAsJsonArray();


            if (Objects.equals(Player.getCurrentLocation(), name)) {
                Description descriptionText = new Description(description);
                descriptions.add(descriptionText);
                String keyItemName = jsonArrayOfKeyItems.toString();
                KeyItems keyItemsAtLocation = new KeyItems(keyItemName);
                keyItems.add(keyItemsAtLocation);
            }
            String wildlifeAtLocation = jsonArrayOfWildlifeInLocation.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "");
            System.out.println(wildlifeAtLocation);
            if (Objects.equals(Player.getCurrentLocation(), name)) {
                for (JsonElement wildlifeElement : jsonArrayOfWildlife) {
                    JsonObject wildlifeJsonObject = wildlifeElement.getAsJsonObject();
                    String wildlifeName = wildlifeJsonObject.get("name").getAsString();
                    JsonElement health = wildlifeJsonObject.get("health");
                    JsonElement strength = wildlifeJsonObject.get("strength");
                    JsonElement speed = wildlifeJsonObject.get("speed");
                    wildlife = new Wildlife(wildlifeName,
                            health.getAsInt(), strength.getAsInt(), speed.getAsInt());
                    wildlifeList.add(wildlife);
                    System.out.println(wildlifeList);
                }
                if (wildlifeList.get(0).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(0);
                    currentWildlifeAtLocation(wildlifeAtLocation, "Monkey", "CrashSurvivor/resources/monkey.txt");
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(1).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(1);
                    currentWildlifeAtLocation(wildlifeAtLocation, "Wild Boar", "CrashSurvivor/resources/wildboar.txt");
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(2).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(2);
                    currentWildlifeAtLocation(wildlifeAtLocation, "Bat", "CrashSurvivor/resources/bat.txt");
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(3).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(3);
                    currentWildlifeAtLocation(wildlifeAtLocation, "Snake", "CrashSurvivor/resources/snake.txt");
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(4).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(4);
                    System.out.println(jsonArrayOfWildlifeInLocation);
                    currentWildlifeAtLocation(wildlifeAtLocation, "Crocodile", "CrashSurvivor/resources/crocodile.txt");
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                } else if (wildlifeList.get(5).getName().equals(wildlifeAtLocation)) {
                    wildlife = wildlifeList.get(5);
                    currentWildlifeAtLocation(wildlifeAtLocation, "Rhino", "CrashSurvivor/resources/rhino.txt");
                    if (wildlife.getHealth() < 1) {
                        jsonArrayOfWildlifeInLocation.remove(0);
                    }
                }
            }
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
            }
            if (Objects.equals(Player.getCurrentLocation(), name)) {
                for (JsonElement directionElement : allDirections) {
                    JsonObject directionJsonObject = directionElement.getAsJsonObject();
                    String directionName = directionJsonObject.get("directionName").getAsString();
                    String place = directionJsonObject.get("place").getAsString();
                    Direction direction = new Direction(directionName, place);
                    directions.add(direction);
                }
            }
            if (Objects.equals(Player.getCurrentLocation(), name)) {
                name = "{XX}";
            } else {
                name = "{  }";
            }

            // This allows us to use the toString method of the Location class
            Location location = new Location(name);
            // Every name and description is added to the locations List
            locations.add(location);
            System.out.println(locations);
        }
        System.out.println(descriptions);
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
    public Player printOnePlayerData() throws FileNotFoundException {
        System.out.println("Choose from the following characters (" +
                "Arnold, Jennifer, Jason, or Scarlett):\n");

        printPlayersInfo(playersList);

        String valueInput = prompter.prompt("Type your choice> ", getPlayersName(playersList),
                "Please select a valid character!");
        String chosenPlayer = valueInput.toLowerCase();

        for (Player p : playersList) {
            if (p.getName().toLowerCase().equalsIgnoreCase(chosenPlayer)) {
                player = p;
                System.out.println(player);
            }
        }
        return player;
    }

    private void printKeyItemsAtLocation() {
        System.out.println(keyItems.toString()
                .replace("[", "")
                .replace("]", ""));
    }

    private void printItemsAtLocation() {
        System.out.println(items.toString()
                .replace("[", "")
                .replace("]", ""));
    }

    private void printMapOfLocations() {
        // Create sublists for the List<Locations>
        List<Location> alphaRow = locations.subList(0, 7);
        List<Location> bravoRow = locations.subList(7, 14);
        List<Location> charlieRow = locations.subList(14, 21);
        List<Location> deltaRow = locations.subList(21, 28);
        List<Location> echoRow = locations.subList(28, 35);
        List<Location> foxtrotRow = locations.subList(35, 42);

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
    private void currentWildlifeAtLocation(String wildlifeAtLocation, String wildlifeName, String wildlifeFile) throws FileNotFoundException {
        if (wildlifeAtLocation.equals(wildlife.getName()) && wildlife.getHealth() > 1) {
            displayWildlife(wildlife, wildlifeFile);
            printWildlife(wildlife);
        }
    }
    public void printSinglePlayerInfo() {
        System.out.println(player.toString());
    }

    public static void main(String[] args) throws FileNotFoundException {
        MapBoard map = new MapBoard();
        Player player;
        map.readAllJson();
        player = map.printOnePlayerData();
        map.printMapOfLocations();
        map.printKeyItemsAtLocation();
        map.printItemsAtLocation();
        map.printSinglePlayerInfo();
    }*/
}




