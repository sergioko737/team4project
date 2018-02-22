package com.jbcteam4.androidgame.loader;

/**
 * Created by student on 18.2.13.
 */
//TODO deside if we need this asset manager


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * The type My asset manager.
 */
public class MyAssetManager {

    /**
     * The Manager.
     */
    public final AssetManager manager = new AssetManager();

    /**
     * The Playing song.
     */
// Music
    public final String playingSong = "music.mp3";

    /**
     * The Skin.
     */
// Skin
    public final String skin = "skin/glassy-ui.json";

    /**
     * Queue add skin.
     */
    public void queueAddSkin(){
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter("skin/glassy-ui.atlas");
        manager.load(skin, Skin.class, params);

    }

    /**
     * Queue add music.
     */
    public void queueAddMusic(){
        manager.load(playingSong, Music.class);
    }
}
