package com.jbcteam4.androidgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.jbcteam4.androidgame.loader.MyAssetManager;
import com.jbcteam4.androidgame.views.EndScreen;
import com.jbcteam4.androidgame.views.LoadingScreen;
import com.jbcteam4.androidgame.views.MainScreen;
import com.jbcteam4.androidgame.views.MenuScreen;
import com.jbcteam4.androidgame.views.PreferencesScreen;


/**
 * The type Flappy starter.
 */
public class FlappyStarter extends Game {

    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 480;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 800;


    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;
    private AppPreferences preferences;
    /**
     * The Ass man.
     */
    public MyAssetManager assMan = new MyAssetManager();
    public Music playingSong;

    /**
     * The constant MENU.
     */
    public final static int MENU = 0;
    /**
     * The constant PREFERENCES.
     */
    public final static int PREFERENCES = 1;
    /**
     * The constant APPLICATION.
     */
    public final static int APPLICATION = 2;
    /**
     * The constant ENDGAME.
     */
    public final static int ENDGAME = 3;

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        preferences = new AppPreferences();
        setScreen(loadingScreen);

        // tells our asset manger that we want to load the images set in loadImages method
        assMan.queueAddMusic();
        // tells the asset manager to load the images and wait until finished loading.
        assMan.manager.finishLoading();
        // loads the 2 sounds we use
        playingSong = assMan.manager.get("music.mp3");
        if(preferences.isMusicEnabled()) {
            System.out.println("TOBEREMOVED startup if condition music config engaged" + preferences.isMusicEnabled());
            playingSong.setVolume(preferences.getMusicVolume());
        } else{
            System.out.println("TOBEREMOVED startup else condition music config engaged" + preferences.isMusicEnabled());
            playingSong.setVolume(0);
        }
        playingSong.play();

    }

    /**
     * Change screen.
     *
     * @param screen the screen
     */
    public void changeScreen(int screen) {

        switch (screen) {
            case MENU:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if (mainScreen == null) mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case ENDGAME:
                if (endScreen == null) endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
        }
    }

    /**
     * Gets preferences.
     *
     * @return the preferences
     */
    public AppPreferences getPreferences() {
        return this.preferences;
    }

    @Override
    public void dispose() {
        playingSong.dispose();
        assMan.manager.dispose();
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Set music volume.
     *
     * @param value the value
     */
    public void setMusicVolume(float value){
        this.playingSong.setVolume(value);
    }

    /**
     * On off music.
     *
     * @param state the state
     */
    public void onOffMusic(boolean state){
        if (!state){
            this.playingSong.setVolume(0);
        } else {
            this.playingSong.setVolume(getPreferences().getMusicVolume());
        }
    }

}
