package com.jbcteam4.androidgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class AppPreferences {
    private static final String PREF_MUSIC_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_SOUND_VOL = "sound";
    private static final String PREFS_NAME = "b2dtut";
    private static final String PREF_HI_SCORE = "hiScore";


    private static final String PREF_BACKGROUND = "background";
    private static final String PREF_BIRD_AVATAR = "birdAvatar";
    private static final String PREF_TOPTUBE = "toptube";
    private static final String PREF_BOTTOMTUBE = "bottomtube";

    public static Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public static String[] background = {"bg.png", "bg-01.png", "bg-02.png"};

    /**
     * Music volume level and checkbox settings
     */

    public static boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public static void setMusicEnabled(boolean musicEnabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled).flush();
    }

    public static float getMusicVolume() {
        return getPrefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    public static void setMusicVolume(float volume) {
        getPrefs().putFloat(PREF_MUSIC_VOLUME, volume).flush();


    }

    /**
     * SoundFX volume level and checkbox settings
     */

    public static boolean isSoundFXEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public static void setSoundFXEnabled(boolean soundFXEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundFXEnabled).flush();
    }

    public static float getSoundFXVolume() {
        return getPrefs().getFloat(PREF_SOUND_VOL, 0.5f);
    }

    public static void setSoundFXVolume(float volume) {
        getPrefs().putFloat(PREF_SOUND_VOL, volume).flush();
    }


    /**
     * High Scores
     *
     * @param newHiscore
     */
    public static void setPrefHiScore(int newHiscore) {
        getPrefs().putInteger(PREF_HI_SCORE, newHiscore);
        getPrefs().flush();
    }

    public static int getPrefHiScore() {
        return getPrefs().getInteger(PREF_HI_SCORE, 3);
    }


    /**
     * Game visual preferences
     */

    public static String getPrefBackground() {
        return getPrefs().getString(PREF_BACKGROUND, "bg.png");
    }

    public static void setPrefBackground(String background) {
        getPrefs().putString(PREF_BACKGROUND, background).flush();
    }

    public static String getPrefBirdAvatar() {
        return getPrefs().getString(PREF_BIRD_AVATAR, "birdanimation.png");
    }

    public static void setPrefBirdAvatar(String birdAvatar){
        getPrefs().putString(PREF_BIRD_AVATAR, "birdAvatar").flush();
    }

    public static String getPrefToptube() {
        return getPrefs().getString(PREF_TOPTUBE, "toptube.png");
    }

    public static void setPrefToptube(String toptube) {
        getPrefs().putString(PREF_TOPTUBE, toptube).flush();
    }

    public static String getPrefBottomtube() {
        return getPrefs().getString(PREF_BOTTOMTUBE, "bottomtube.png");
    }

    public static void setPrefBottomtube(String bottomtube){
        getPrefs().putString(PREF_BOTTOMTUBE, "bottomtube.png");
    }


}
