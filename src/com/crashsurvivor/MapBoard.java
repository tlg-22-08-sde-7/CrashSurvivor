package com.crashsurvivor;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MapBoard {

    public void printMap() {
        // make path of json into a variable
        File input = new File("CrashSurvivor/resources/location.json");
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
                // get the "description" objects
                String description = locationJsonObject.get("description").getAsString();
                // This allows us to use the toString method of the Location class
                Location location = new Location(name, description);
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
    public static void main(String[] args) {
        MapBoard map = new MapBoard();
        map.printMap();
    }
}



