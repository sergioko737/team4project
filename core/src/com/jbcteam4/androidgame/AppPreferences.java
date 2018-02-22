package com.jbcteam4.androidgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


/**
 * The type AppPreferences. This class provides to application ability to store in local file
 * it's personal settings.
 */
public class AppPreferences {
    private static final String PREFS_NAME = "b2dtut";

    private static final String PREF_BIRD_AVATAR = "birdAvatar";
    private static final String PREF_BIRD_ANIMATION = "birdAnimation";
    private static final String PREF_BACKGROUND = "background";
    private static final String PREF_BACKGROUND_SELECTOR = "backgroundSelector";
    private static final String PREF_TOPTUBE = "toptube";
    private static final String PREF_BOTTOMTUBE = "bottomtube";

    private static final String PREF_MUSIC_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_VOLUME = "sound";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";

    private static final String PREF_HI_SCORE = "hiScore";

    /**
     * Provides access to Preference object (wraps up Android/SharedPreferences)
     *
     * @return the prefs
     */
    public static Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }


    /**
     * Game visual preferences
     *
     * @return the pref background
     */
    public static String getPrefBackground() {
        return getPrefs().getString(PREF_BACKGROUND, background[0]);
    }

    public static final String[] birds = {"birdDefault.png", "birdAccenture.png", "birdThird.png",
            "birdDefaultInactive.png", "birdAccentureInactive.png", "birdThirdInactive.png",
            "birdDefaultAnimation.png", "birdAccentureAnimation.png", "birdThirdAnimation.png"};

    public static final String[] background = {"bg-01.png", "bg-02.png", "bg-03.png"};
    public static final String[] topTube = {"toptube-01.png", "toptube-02.png", "toptube-03.png"};
    public static final String[] bottomTube = {"bottomtube-01.png", "bottomtube-02.png", "bottomtube-03.png"};

    /**
     * Gets pref bird avatar.
     *
     * @return the pref bird avatar
     */
    public static String getPrefBirdAvatar() {
        return getPrefs().getString(PREF_BIRD_AVATAR, birds[0]);
    }

    /**
     * Sets pref bird avatar.
     *
     * @param z the z
     */
    public static void setPrefBirdAvatar(int z) {
        getPrefs().putString(PREF_BIRD_AVATAR, birds[z]).flush();
    }

    /**
     * Gets pref bird animation.
     *
     * @return the pref bird animation
     */
    public static String getPrefBirdAnimation() {
        return getPrefs().getString(PREF_BIRD_ANIMATION, birds[6]);
    }

    /**
     * Sets pref bird animation.
     *
     * @param z the z
     */
    public static void setPrefBirdAnimation(int z) {
        getPrefs().putString(PREF_BIRD_ANIMATION, birds[z + 6]).flush();
    }

    /**
     * Sets pref background selector state to determine background image
     *
     * @param x the x
     */
    public static void setPrefBackgroundSelector(float x) {
        getPrefs().putFloat(PREF_BACKGROUND_SELECTOR, (int) x).flush();
    }

    /**
     * Gets pref background selector.
     *
     * @return the pref background selector
     */
    public static float getPrefBackgroundSelector() {
        return getPrefs().getFloat(PREF_BACKGROUND_SELECTOR, 0f);
    }

    /**
     * Sets pref background.
     *
     * @param z the z
     */
    public static void setPrefBackground(int z) {
        getPrefs().putString(PREF_BACKGROUND, background[z]).flush();
    }

    /**
     * Gets pref toptube.
     *
     * @return the pref toptube
     */
    public static String getPrefToptube() {
        return getPrefs().getString(PREF_TOPTUBE, topTube[0]);
    }

    /**
     * Sets pref toptube.
     *
     * @param z
     */
    public static void setPrefToptube(int z) {
        getPrefs().putString(PREF_TOPTUBE, topTube[z]).flush();
    }

    /**
     * Gets pref bottomtube.
     *
     * @return the pref bottomtube image
     */
    public static String getPrefBottomtube() {
        return getPrefs().getString(PREF_BOTTOMTUBE, bottomTube[0]);
    }

    /**
     * Sets pref bottomtube image
     *
     * @param z the z
     */
    public static void setPrefBottomtube(int z) {
        getPrefs().putString(PREF_BOTTOMTUBE, bottomTube[z]).flush();
    }

    /**
     * Music volume level and checkbox settings
     *
     * @return the boolean
     */
    public static boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    /**
     * Sets music enabled.
     *
     * @param musicEnabled the music enabled
     */
    public static void setMusicEnabled(boolean musicEnabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled).flush();
    }

    /**
     * Gets music volume.
     *
     * @return the music volume level
     */
    public static float getMusicVolume() {
        return getPrefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    /**
     * Sets music volume.
     *
     * @param volume the volume level
     */
    public static void setMusicVolume(float volume) {
        getPrefs().putFloat(PREF_MUSIC_VOLUME, volume).flush();


    }

    /**
     * SoundFX volume level and checkbox settings
     *
     * @return the boolean
     */
    public static boolean isSoundFXEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    /**
     * Sets sound fx enabled.
     *
     * @param soundFXEnabled the sound fx enabled
     */
    public static void setSoundFXEnabled(boolean soundFXEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundFXEnabled).flush();
    }

    /**
     * Gets sound fx volume.
     *
     * @return the sound fx volume level
     */
    public static float getSoundFXVolume() {
        return getPrefs().getFloat(PREF_SOUND_VOLUME, 0.5f);
    }

    /**
     * Sets sound fx volume.
     *
     * @param volume the volume level
     */
    public static void setSoundFXVolume(float volume) {
        getPrefs().putFloat(PREF_SOUND_VOLUME, volume).flush();
    }


    /**
     * High Scores
     *
     * @param newHiscore the new hiscore
     */
    public static void setPrefHiScore(int newHiscore) {
        getPrefs().putInteger(PREF_HI_SCORE, newHiscore).flush();
    }

    /**
     * @return the stored in preferences hi score value
     */
    public static int getPrefHiScore() {
        return getPrefs().getInteger(PREF_HI_SCORE, 3);
    }


}
