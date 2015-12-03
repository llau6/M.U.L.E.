package MULE.Model;

/**
 * Created by elliekang on 10/1/15.
 */
import javafx.scene.media.MediaPlayer;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Responsible for loading sound media to be played using an id or key.
 * Contains all sounds for use later.
 *
 */
public class SoundManager {
    private ExecutorService soundPool = Executors.newFixedThreadPool(2);
    private Map<String, AudioClip> soundEffectsMap = new HashMap<>();
    private int soundNum;
    private MediaPlayer player;
    private boolean isPlaying;

    /**
     * Constructor to create a simple thread pool.
     *
     * @param numberOfThreads - number of threads to use media players in the map.
     */
    public SoundManager(int numberOfThreads, int soundSelection) throws MalformedURLException {
        soundPool = Executors.newFixedThreadPool(numberOfThreads);
        soundNum = soundSelection;
        isPlaying = false;
        //loadSoundEffects();
    }

    /**
     * Load a sound into a map to later be played based on the id.
     */
    public final void loadSoundEffects() throws MalformedURLException {
//        AudioClip sound = new AudioClip(url.toExternalForm());
//        soundEffectsMap.put(id, sound);

//        AudioClip sound1 = new AudioClip(new URL("MULE/gamemusic/intro.mp3").toExternalForm());
//        AudioClip sound2 = new AudioClip(new URL("MULE/gamemusic/coketown.mp3").toExternalForm());
//        AudioClip sound3 = new AudioClip(new URL("MULE/gamemusic/easthenesys.mp3").toExternalForm());
//        AudioClip sound4 = new AudioClip(new URL("MULE/gamemusic/henesys.mp3").toExternalForm());
//        AudioClip sound5 = new AudioClip(new URL("MULE/gamemusic/hunting.mp3").toExternalForm());
//        AudioClip sound6 = new AudioClip(new URL("MULE/gamemusic/lithharbor.mp3").toExternalForm());

        AudioClip sound1 = Applet.newAudioClip(new URL("MULE/gamemusic/intro.mp3"));
        AudioClip sound2 = Applet.newAudioClip(new URL("MULE/gamemusic/coketown.mp3"));
        AudioClip sound3 = Applet.newAudioClip(new URL("MULE/gamemusic/easthenesys.mp3"));
        AudioClip sound4 = Applet.newAudioClip(new URL("MULE/gamemusic/henesys.mp3"));
        AudioClip sound5 = Applet.newAudioClip(new URL("MULE/gamemusic/hunting.mp3"));
        AudioClip sound6 = Applet.newAudioClip(new URL("MULE/gamemusic/lithharbor.mp3"));

        soundEffectsMap.put("intro", sound1);
        soundEffectsMap.put("town", sound2);
        soundEffectsMap.put("east", sound3);
        soundEffectsMap.put("map", sound4);
        soundEffectsMap.put("hunt", sound5);
        soundEffectsMap.put("store", sound6);
    }

    /**
     * Lookup a name resource to play sound based on the id.
     *
     * @param id identifier for a sound to be played.
     */
    public void playSound(String id) {
        Runnable soundPlay = new Runnable() {
            @Override
            public void run() {
                soundEffectsMap.get(id).play();
            }
        };
        soundPool.execute(soundPlay);
    }

    public void playMusic() {
        if (soundNum == 2) {
            playSel("MULE/gamemusic/intro.mp3"); //splash set up
        } else if (soundNum == 3) {
            playSel("MULE/gamemusic/coketown.mp3"); //town
        } else if (soundNum == 4) {
            playSel("MULE/gamemusic/easthenesys.mp3"); //pub
        } else if (soundNum == 5) {
            playSel("MULE/gamemusic/henesys.mp3"); //map
        } else if (soundNum == 6) {
            playSel("MULE/gamemusic/hunting.mp3"); //wampus
        } else if (soundNum == 7) {
            playSel("MULE/gamemusic/lithharbor.mp3"); //store
        }
    }

    public void playSel(String path) {
        javafx.scene.media.Media media = new javafx.scene.media.Media(Paths.get(path).toUri().toString());
        player = new MediaPlayer(media);
        player.setAutoPlay(true);
        isPlaying = true;
    }

//    /**
//     * Stop all threads and media players.
//     */
//    public void shutdown() {
//        soundPool.shutdown();
//    }

    public void shutdown() {
        player.stop();
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}