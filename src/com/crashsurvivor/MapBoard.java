package com.crashsurvivor;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MapBoard {

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
            for (JsonElement locationElement : jsonArrayOfLocations) {
                JsonObject locationJsonObject = locationElement.getAsJsonObject();
                // get the "name" objects
                String name = locationJsonObject.get("name").getAsString();
                // Need to build a getCurrentLocation method for the map to pull data from
                /*
                if (player.getCurrentLocation() == name) {
                    name = "{XX}";
                } else {
                    name = "{  }";
                }
                */
                name = "{  }";

                // This allows us to use the toString method of the Location class
                Location location = new Location(name);
                // Every name and description is added to the locations List
                locations.add(location);
            }

            // Create sublists for the List<Locations>
            List<Location> A = locations.subList(0,7);
            List<Location> B = locations.subList(7,14);
            List<Location> C = locations.subList(14,21);
            List<Location> D = locations.subList(21,28);
            List<Location> E = locations.subList(28,35);
            List<Location> F = locations.subList(35,42);

            // Create layout of the MapBoard, removing unnecessary items from Array
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(A.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));

            System.out.println(B.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));

            System.out.println(C.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));

            System.out.println(D.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));

            System.out.println(E.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));

            System.out.println(F.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void printDescriptionData() {
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray jsonArrayOfLocations = fileObject.get("locations").getAsJsonArray();
            List<Description> descriptions = new ArrayList<>();

            for (JsonElement locationElement : jsonArrayOfLocations) {
                JsonObject locationJsonObject = locationElement.getAsJsonObject();

                String description = locationJsonObject.get("description").getAsString();
                /*
                if (player.getCurrentLocation() == name) {
                    name = "{XX}";
                } else {
                    name = "{  }";
                }
                */

                // This allows us to use the toString method of the Location class
                Description place = new Description(description);
                // Every name and description is added to the locations List
                descriptions.add(place);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void printPlayerData() {
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray jsonArrayOfLocations = fileObject.get("players").getAsJsonArray();
            List<Player> data = new ArrayList<>();

            for (JsonElement playerElement : jsonArrayOfLocations) {
                JsonObject playerJsonObject = playerElement.getAsJsonObject();

                String name = playerJsonObject.get("name").getAsString();
                JsonElement health = playerJsonObject.get("health");
                JsonElement hydration = playerJsonObject.get("hydration");
                JsonElement strength = playerJsonObject.get("strength");
                JsonElement speed = playerJsonObject.get("speed");
                String currentLocation = playerJsonObject.get("currentLocation").getAsString();
                /*
                if (player.getCurrentLocation() == name) {
                    name = "{XX}";
                } else {
                    name = "{  }";
                }
                */

                // This allows us to use the toString method of the Location class
                Player player = new Player(name, health.getAsInt(), hydration.getAsInt(),
                        strength.getAsInt(), speed.getAsInt(), currentLocation);
                // Every name and description is added to the locations List
                data.add(player);
            }
            List<Player> arnold = data.subList(0,1);
            List<Player> jennifer = data.subList(1,2);
            List<Player> jason = data.subList(2,3);
            List<Player> scarlett = data.subList(3,4);

            System.out.println("Choose from the following characters (1, 2, 3, or 4):\n\n1:   " + arnold.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));

            System.out.println("2: " + jennifer.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));

            System.out.println("3)    " + jason.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));

            System.out.println("4) " + scarlett.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MapBoard map = new MapBoard();
        map.printMap();
        map.printPlayerData();

    }
}



