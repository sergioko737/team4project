package com.jbcteam4.androidgame.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jbcteam4.androidgame.AppPreferences;

import java.util.Random;

public class Tube {

    public static final int TUBE_WIDTH = 48;

    private static final int FLUCTUATION = 130;
    private static int TubeGap = 120;     // 120 standart - 70 difficult
    private static final int LOWEST_OPENING = 120;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private Rectangle boundsTop, boundsBot;

    public static void setTubeGap (int aGap) {
        TubeGap = aGap;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Tube(float x) {
        topTube = new Texture(AppPreferences.getPrefToptube());
        bottomTube = new Texture(AppPreferences.getPrefBottomtube());
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TubeGap + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TubeGap - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TubeGap + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TubeGap - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}