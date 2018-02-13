package com.jbcteam4.androidgame.loader;

/**
 * Created by student on 18.2.13.
 */


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class MyAssetManager {

    public final AssetManager manager = new AssetManager();

    // Textures
    public final String playerImage = "images/player.png";
    public final String enemyImage = "images/enemy.png";

    // Sounds
    public final String boingSound = "sounds/boing.wav";
    public final String pingSound = "sounds/ping.wav";

    // Music
    public final String playingSong = "music.mp3";

    // Skin
    public final String skin = "skin/glassy-ui.json";

    public void queueAddSkin(){
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter("skin/glassy-ui.atlas");
        manager.load(skin, Skin.class, params);

    }

    public void queueAddMusic(){
        manager.load(playingSong, Music.class);
    }

    public void queueAddSounds(){
        manager.load(boingSound, Sound.class);
        manager.load(pingSound, Sound.class);
    }

    public void queueAddImages(){
        manager.load(playerImage, Texture.class);
        manager.load(enemyImage, Texture.class);
    }

    // a small set of images used by the loading screen
    public void queueAddLoadingImages(){

    }
}
