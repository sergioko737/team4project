package com.jbcteam4.androidgame.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jbcteam4.androidgame.FlappyStarter;

/**
 * Created by student on 18.2.13.
 */

/**
 * Credit screen
 */

//some random peace of comment

public class CreditScreen implements Screen {

    private FlappyStarter parent;
    private Stage stage;
    private Texture texture;

    private Label sergeyLabel;
    private Label edgarLabel;
    private Label andrisLabel;
    private Label titleLabel;
    private Label okLabel;
    private Skin skin;


    public CreditScreen(FlappyStarter flappyStarter) {
        parent = flappyStarter;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        // texture = new Texture("bg-01.png");
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));


    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        Gdx.input.setCatchBackKey(true);


        // Create a table that fills the screen. Everything else will go inside
        // this table.
        Table table = new Table();
        table.setFillParent(true);
        if (Gdx.graphics.getWidth() > 800) {
            table.setTransform(true);
            table.scaleBy(3.3f);
        }
        table.setOrigin(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        //table.setDebug(true);
        stage.addActor(table);
        titleLabel = new Label("Created by:", skin);
        titleLabel.setFontScale(1.5f);
        sergeyLabel = new Label("Sergey", skin);
        edgarLabel = new Label("Edgar", skin);
        andrisLabel = new Label("Andris", skin);
        okLabel = new Label("Ok", skin);
        texture = new Texture("bg.png");


        okLabel.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(FlappyStarter.MENU);
            }
        });

        table.add(titleLabel).center();
        table.row().pad(30, 0, 0, 10);
        table.add(sergeyLabel).center();
        table.row().pad(5, 0, 0, 10);
        table.add(edgarLabel).center();
        table.row().pad(5, 0, 0, 10);
        table.add(andrisLabel).center();
        table.row().pad(100, 0, 0, 10);
        table.add(okLabel).center();

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.graphics.setContinuousRendering(false);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();

        // stage.getBatch().draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {

            parent.changeScreen(FlappyStarter.MENU);

        }

        // tell our stage to do actions and draw itself
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

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
        texture.dispose();
        stage.dispose();
        skin.dispose();
        parent.dispose();

    }
}
