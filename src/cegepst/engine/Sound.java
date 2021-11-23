package cegepst.engine;

import cegepst.viking.Resource;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    public void playLoop(Resource resource) {
        play(resource, true);
    }

    public void playOnce(Resource resource) {
        play(resource, false);
    }

    public static synchronized void play(Resource resource, boolean loop) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            getClass().getClassLoader().getResourceAsStream(resource.getPath()));
                    clip.open(inputStream);
                    if (loop) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
