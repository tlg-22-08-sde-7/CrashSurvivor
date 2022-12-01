package com.crashsurvivor.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameBoard {

    public void execute(){
        welcome();
        showBoard();
    }

    private void welcome() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("W E L C O M E  T O  T H E  G A M E!");
        try (BufferedReader br = new BufferedReader(new FileReader("CrashSurvivor/resources/banner.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("CREATED BY: DAVID, MARTIN, NIMA");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("As you make your maiden flight in your brand new (to you) plane you experience a sudden engine failure in your number 2 engine.\n" +
                "In your efforts to resolve the issue your ultimately lose control. \n" +
                "You check your heading and coordinates to try to remember where you are as there is nothing that you can do. everything fades to black..");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    private void showBoard(){

    }
}
