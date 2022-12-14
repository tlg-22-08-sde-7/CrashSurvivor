package com.crashsurvivor;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    //Variables / fields
    private Clip audioClip;

    //constructor
    public Music() {

    }

    public void start(String path) throws LineUnavailableException {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.loop(0);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }


    public void stop(){
        audioClip.close();

    }


}