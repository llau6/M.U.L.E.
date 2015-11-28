package MULE.Model;

/**
 * Created by elliekang on 10/1/15.
 */
import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Responsible for loading sound media to be played using an id or key.
 * Contains all sounds for use later.
 *
 * * User: cdea
 */
public class SoundManager {
    private ExecutorService soundPool = Executors.newFixedThreadPool(2);
    private Map<String, AudioClip> soundEffectsMap = new HashMap<>();

    /**
     * Constructor to create a simple thread pool.
     *
     * @param numberOfThreads - number of threads to use media players in the map.
     */
    public SoundManager(int numberOfThreads) {
        soundPool = Executors.newFixedThreadPool(numberOfThreads);
    }

    /**
     * Load a sound into a map to later be played based on the id.
     *
     * @param id  - The identifier for a sound.
     * @param url - The url location of the media or audio resource. Usually in src/main/resources directory.
     */
    public final void loadSoundEffects(String id, URL url) {
        AudioClip sound = new AudioClip(url.toExternalForm());
        soundEffectsMap.put(id, sound);
    }

    /**
     * Lookup a name resource to play sound based on the id.
     *
     * @param id identifier for a sound to be played.
     */
    public final void playSound(final String id) {
        Runnable soundPlay = new Runnable() {
            @Override
            public void run() {
                soundEffectsMap.get(id).play();
            }
        };
        soundPool.execute(soundPlay);
    }

//    public void playMusic() {
//        javafx.scene.media.Media media = new javafx.scene.media.Media(Paths.get("music.mp3").toUri().toString());
//        MediaPlayer player = new MediaPlayer(media);
//        player.setAutoPlay(true);
//    }

    /**
     * Stop all threads and media players.
     */
//    public void shutdown() {
//        soundPool.shutdown();
//    }

}