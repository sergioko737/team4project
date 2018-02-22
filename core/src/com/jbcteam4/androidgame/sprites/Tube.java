package com.jbcteam4.androidgame.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jbcteam4.androidgame.AppPreferences;

import java.util.Random;

/**
 * The type Tube.
 */
public class Tube {

    /**
     * The constant TUBE_WIDTH.
     */
    public static final int TUBE_WIDTH = 48;

    private static final int FLUCTUATION = 130;
    private static  int TubeGap = 120;
    private static final int LOWEST_OPENING = 120;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private Rectangle boundsTop, boundsBot;

    /**
     * Sets tube gap.
     *
     * @param aGap the a gap
     */
    public static void setTubeGap (int aGap) {
        TubeGap = aGap;
    }

    /**
     * Gets top tube.
     *
     * @return the top tube
     */
    public Texture getTopTube() {
        return topTube;
    }

    /**
     * Gets bottom tube.
     *
     * @return the bottom tube
     */
    public Texture getBottomTube() {
        return bottomTube;
    }

    /**
     * Gets pos top tube.
     *
     * @return the pos top tube
     */
    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    /**
     * Gets pos bot tube.
     *
     * @return the pos bot tube
     */
    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    /**
     * Instantiates a new Tube.
     *
     * @param x the x
     */
    public Tube(float x) {
        topTube = new Texture(AppPreferences.getPrefToptube());
        bottomTube = new Texture(AppPreferences.getPrefBottomtube());
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TubeGap + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TubeGap - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    /**
     * Reposition.
     *
     * @param x the x
     */
    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TubeGap + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TubeGap - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    /**
     * Collides boolean.
     *
     * @param player the player
     * @return the boolean
     */
    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    /**
     * Dispose.
     */
    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}