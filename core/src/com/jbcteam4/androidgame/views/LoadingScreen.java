package com.jbcteam4.androidgame.views;

import com.badlogic.gdx.Screen;
import com.jbcteam4.androidgame.FlappyStarter;

/**
 * Created by student on 18.2.13.
 */

public class LoadingScreen implements Screen {
    private FlappyStarter parent;
    public LoadingScreen(FlappyStarter flappyStarter) {
        parent = flappyStarter;

    }

    @Override
    public void show() {

    }
    // Change screen to menu screen
    @Override
    public void render(float delta) {
        parent.changeScreen(FlappyStarter.MENU);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
