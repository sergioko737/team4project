package com.jbcteam4.androidgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by student on 18.13.2.
 */

public class GamePreferences {

    private static final String PREFERENCES_NAME = "preferences";
    private static final String BACKGROUND_PREFERENCE = "background";
    private static final String BIRD_ANIMATION_PREFERENCE = "birdanimation";
    private static final String TOPTUBE_ASSET_PREFERENCE = "toptube";
    private static final String BOTTOMTUBE_ASSET_PREFERENCE = "bottomtube";
    private static final String MAX_SCORE_PREFERENCE = "maxScore";

/*    private Preferences getPreferences() {
        return Gdx.app.getPreferences(PREFERENCES_NAME);
    }*/

    Preferences prefs = Gdx.app.getPreferences(PREFERENCES_NAME).putString(BACKGROUND_PREFERENCE, "bg.png")
            .putString(BIRD_ANIMATION_PREFERENCE, "birdanimation.png")
            .putString(TOPTUBE_ASSET_PREFERENCE, "toptube.png")
            .putString(BOTTOMTUBE_ASSET_PREFERENCE, "bottomtube.png");


/*
    public void saveScore(int score){
        if (score > prefs.getInteger("maxScore")){
            prefs.putInteger(MAX_SCORE_PREFERENCE, score);
            prefs.flush();
        }
    }

    public void setBackgroundPreference(String background){
        prefs.putString(BACKGROUND_PREFERENCE, background);
        prefs.flush();
    }



    public void setBirdAnimationPreference(String birdAnimationPreference){
        prefs.putString(BIRD_ANIMATION_PREFERENCE, birdAnimationPreference);
        prefs.flush();
    }

    public void resetPreferences(){
        prefs.putString(BACKGROUND_PREFERENCE, "bg.png");
        prefs.putString(BIRD_ANIMATION_PREFERENCE, "birdanimation.png");
        prefs.putString(TOPTUBE_ASSET_PREFERENCE, "toptube.png");
        prefs.putString(BOTTOMTUBE_ASSET_PREFERENCE, "bottomtube.png");
        prefs.putInteger(MAX_SCORE_PREFERENCE, 0);
        prefs.flush();
    }

    public String getPreferencesValue(String name){
        return prefs.getString(name);
    }*/


}
