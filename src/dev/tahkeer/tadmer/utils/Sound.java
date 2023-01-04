package dev.tahkeer.tadmer.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class Sound {
    private static final String BOMB_EXPLOSION = "./res/bomb_explosion.wav";
    private static final String Game_Sound = "./res/circus.wav";


    private static final Sound instance = new Sound();

    private Sound() {
    }

    public void playExplosion() {
        new Thread(() -> {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(BOMB_EXPLOSION));
                Clip explosion = AudioSystem.getClip();
                explosion.open(audioStream);
                explosion.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        explosion.close();
                        try {
                            audioStream.close();
                        } catch (IOException ignored) {
                        }
                    }
                });
                explosion.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }

    public static Sound getInstance() {
        return instance;
    }

}
