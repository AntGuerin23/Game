package cegepst.engine.resources;


import cegepst.engine.entities.StaticEntity;
import cegepst.mainGame.miscellaneous.other.Resource;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    public static void playLoop(AudioInputStream inputStream, float volume) {
        play(inputStream, true, volume, null);
    }

    public static void playStoppableLoop(AudioInputStream inputStream, float volume, SoundStopper entity) {
        play(inputStream, true, volume, entity);
    }

    public static void playOnce(AudioInputStream inputStream, float volume) {
        play(inputStream, false, volume, null);
    }

    private static synchronized void play(AudioInputStream inputStream, boolean loop, float volumeValue, SoundStopper entity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    FloatControl volume= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(volumeValue);
                    if (loop) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    clip.start();
                    while (entity != null && clip.isOpen()) {
                        System.out.print(""); //N'arrête pas le thread sans ça ????????????? étrange
                        if (entity.stopSound()) {
                            clip.close();
                            clip.stop();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
