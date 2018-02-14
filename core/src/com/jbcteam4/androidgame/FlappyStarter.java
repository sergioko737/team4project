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


public class FlappyStarter extends Game {


    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;


    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;
    private AppPreferences preferences;
    public MyAssetManager assMan = new MyAssetManager();
    private Music playingSong;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
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

        playingSong.play();

    }

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

    public AppPreferences getPreferences() {
        return this.preferences;
    }

    @Override
    public void dispose() {
        playingSong.dispose();
        assMan.manager.dispose();
        Gdx.input.setInputProcessor(null);
    }

    public void setMusicVolume(float value){
        this.playingSong.setVolume(value);
    }

    public void onOffMusic(boolean state){
        if (!state){
            this.playingSong.setVolume(0);
        } else {
            this.playingSong.setVolume(getPreferences().getMusicVolume());
        }
    }

}
