package com.jbcteam4.androidgame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jbcteam4.androidgame.AppPreferences;
import java.util.Random;

/**
 * The type Tube. Creates objects for top and bottom tubes and places the on the playground.
 */
public class Tube {

    /**
     * The constants for the playground objects
     */
    public static final int TUBE_WIDTH = 48;
    private static final int FLUCTUATION = 130;
    private static int TubeGap = 120;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private Rectangle boundsTop, boundsBot;

    /**
     * Sets tube gap.
     *
     * @param aGap gap size
     */
    public static void setTubeGap(int aGap) {
        TubeGap = aGap;
    }

    /**
     * @return the top tube texture
     */
    public Texture getTopTube() {
        return topTube;
    }

    /**
     * @return the bottom tube texture
     */
    public Texture getBottomTube() {
        return bottomTube;
    }

    /**
     * @return the position of the top tube
     */
    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    /**
     * @return the position of the bottom tube
     */
    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    /**
     * Instantiates a new Tube.
     *
     * @param x places new tube on fixed x-axis
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
     * Positions tubes on the playground
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
     * Collision detection
     *
     * @param player the player object bounds
     * @return the boolean
     */
    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }


    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}