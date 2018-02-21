package com.jbcteam4.androidgame.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jbcteam4.androidgame.FlappyStarter;
import com.jbcteam4.androidgame.states.GameOver;
import com.jbcteam4.androidgame.states.GameStateManager;
import com.jbcteam4.androidgame.states.MenuState;
import com.jbcteam4.androidgame.states.PlayState;

/**
 * Created by student on 18.2.13.
 */

public class MainScreen implements Screen {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Flappy Riga";

    private GameStateManager gsm;
    private SpriteBatch batch;
    FlappyStarter parent;

    private Music music;
    private Stage stage;




    public MainScreen(FlappyStarter flappyStarter) {
        parent = flappyStarter;
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.setContinuousRendering(true);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);

      if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            System.out.println("Back button pressed");
            parent.changeScreen(FlappyStarter.MENU);
            PlayState.score = 0;
            PlayState.lives = 3;
            gsm.set(new PlayState(gsm));

        }
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
