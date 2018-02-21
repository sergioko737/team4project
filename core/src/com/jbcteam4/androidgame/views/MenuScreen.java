package com.jbcteam4.androidgame.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jbcteam4.androidgame.AppPreferences;
import com.jbcteam4.androidgame.FlappyStarter;


public class MenuScreen implements Screen {

    private FlappyStarter parent;
    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;
    private Texture texture;
    private TextButton newGame;
    private TextButton preferences;
    private TextButton exit;
    private Label credits;


    private Texture backGround;

    public MenuScreen(FlappyStarter flappyStarter){
        parent = flappyStarter;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        backGround = new Texture(AppPreferences.getPrefBackground());
        parent.assMan.queueAddSkin();
        parent.assMan.manager.finishLoading();
        skin = parent.assMan.manager.get("skin/glassy-ui.json");
        texture = new Texture(AppPreferences.getPrefBackground());

        newGame = new TextButton("New Game", skin);
        preferences = new TextButton(" Preferences ", skin);
        exit = new TextButton("Exit", skin);
        credits = new Label("Credits",skin);
        credits.setFontScale(2.3f);
        credits.setColor(Color.BLACK);

        //create buttons
        newGame = new TextButton("New Game", skin);
        preferences = new TextButton("Preferences", skin);
        exit = new TextButton("Exit", skin);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setOrigin(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);


        table.setFillParent(true);
        if(Gdx.graphics.getWidth() > 800){
            table.setTransform(true);
            table.scaleBy(1.1f);
        }


        //table.setDebug(true);
        stage.addActor(table);

        //add buttons to table
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row().pad(30, 0, 10, 0);
        table.add(exit).center();
        table.row().pad(130, 0, 10, 0);
        table.add(credits).center();

        // create button listeners
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(FlappyStarter.APPLICATION);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(FlappyStarter.PREFERENCES);
            }
        });

        credits.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(FlappyStarter.CREDITS);

            }
        });


    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.graphics.setContinuousRendering(false);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        //Set background image
        stage.getBatch().begin();

        stage.getBatch().draw(texture, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage.getBatch().end();



        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when teh screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Auto-generated method stub

    }

    @Override
    public void resume() {
        // Auto-generated method stub

    }

    @Override
    public void hide() {
        // Auto-generated method stub

    }

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
        backGround.dispose();
        skin.dispose();
        parent.dispose();

    }

}
