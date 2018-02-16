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
import com.jbcteam4.androidgame.states.GameStateManager;
import com.jbcteam4.androidgame.states.MenuState;

/**
 * Created by student on 18.2.13.
 */

public class MainScreen implements Screen {

    private GameStateManager gsm;
    private SpriteBatch batch;
    FlappyStarter parent;

    private Music music;
    private Stage stage;

   // private MainScreen parent;


    public MainScreen(FlappyStarter flappyStarter) {
        parent = flappyStarter;


        batch = new SpriteBatch();
        gsm = new GameStateManager();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);


        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            parent.changeScreen(FlappyStarter.MENU);
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
