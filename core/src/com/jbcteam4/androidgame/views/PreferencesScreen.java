package com.jbcteam4.androidgame.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jbcteam4.androidgame.AppPreferences;
import com.jbcteam4.androidgame.FlappyStarter;

public class PreferencesScreen implements Screen {

    private FlappyStarter parent;
    private Stage stage;
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;
    private Label backgroundSelectorLabel;
    private Texture texture;
    private Drawable backgroundSelectorPreview;
    private Skin skin;

    public PreferencesScreen(FlappyStarter flappyStarter) {
        parent = flappyStarter;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        texture = new Texture(AppPreferences.getPrefBackground());
    }

    @Override
    public void show() {
        stage.clear();
       // preferences = new AppPreferences();
        Gdx.input.setInputProcessor(stage);

        Gdx.input.setCatchBackKey(true);


        // Create a table that fills the screen. Everything else will go inside
        // this table.
        Table table = new Table();
        table.setFillParent(true);
        if (Gdx.graphics.getWidth() > 800) {
            table.setTransform(true);
            table.scaleBy(3.0f);
        }
        table.setOrigin(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        // table.setDebug(true);
        stage.addActor(table);


        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));


        // CHANGE BACKGROUND SLIDER
        final Slider backgroundSelectorSlider = new Slider(0f, 2f, 1f, false, skin);
        backgroundSelectorSlider.setValue(0);

        backgroundSelectorSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                //AppPreferences.setSoundFXVolume(soundFXSlider.getValue());
                return false;
            }
        });

        final Image image = new Image(texture);

        // MUSIC SETTINGS
        // music volume
        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeMusicSlider.setValue(AppPreferences.getMusicVolume());

        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                AppPreferences.setMusicVolume(volumeMusicSlider.getValue());
                if (AppPreferences.isMusicEnabled()) {
                    parent.playingSong.setVolume(volumeMusicSlider.getValue());
                }

                return false;
            }
        });


        // music on/off
        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked(AppPreferences.isMusicEnabled());
        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                AppPreferences.setMusicEnabled(musicCheckbox.isChecked());
                if (!musicCheckbox.isChecked()) {
                    parent.playingSong.setVolume(0);
                } else {
                    parent.playingSong.setVolume(volumeMusicSlider.getValue());
                }
                return false;
            }
        });

        // Sound FX SETTINGS
        // sound FX volume
        final Slider soundFXSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundFXSlider.setValue(AppPreferences.getSoundFXVolume());

        soundFXSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                AppPreferences.setSoundFXVolume(soundFXSlider.getValue());
                if (AppPreferences.isMusicEnabled()) {
                    parent.playingSong.setVolume(soundFXSlider.getValue());
                }
                return false;
            }
        });

        // sound on/off
        final CheckBox soundFXCheckbox = new CheckBox(null, skin);
        soundFXCheckbox.setChecked(AppPreferences.isSoundFXEnabled());
        soundFXCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                AppPreferences.setSoundFXEnabled(soundFXCheckbox.isChecked());

                AppPreferences.setSoundFXVolume(soundFXSlider.getValue());

                return false;
            }
        });

        // return to main screen button
        final TextButton backButton = new TextButton("Back", skin, "small");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(FlappyStarter.MENU);

            }
        });

        backgroundSelectorLabel = new Label("Select playground", skin);
        titleLabel = new Label("Preferences", skin);
        titleLabel.setFontScale(1.5f);
        volumeMusicLabel = new Label("Music Volume", skin);
        volumeSoundLabel = new Label("Sound Volume", skin);
        musicOnOffLabel = new Label("ON/OFF", skin);
        soundOnOffLabel = new Label("ON/OFF", skin);

        table.add(titleLabel).colspan(2);
        table.row().pad(20, 0, 0, 10);
        table.add(backgroundSelectorLabel).right();
        table.row().pad(10, 0, 0, 10);
        table.add(backgroundSelectorSlider);
        table.row().pad(10, 0, 0, 10);
        table.add(volumeMusicLabel).center();
        table.add(musicOnOffLabel).center();
        table.row().pad(5, 0, 0, 10);
        table.add(volumeMusicSlider);
        table.add(musicCheckbox);
        table.row().pad(10, 0, 0, 10);
        table.add(volumeSoundLabel).center();
        table.add(soundOnOffLabel).center();
        table.row().pad(5, 0, 0, 10);
        table.add(soundFXSlider);
        table.add(soundFXCheckbox);
        table.row().pad(10, 0, 0, 10);

        table.add(backButton).colspan(2);

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.graphics.setContinuousRendering(false);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();

        stage.getBatch().draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        // change the stage's viewport when the screen size is changed
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
        // Auto-generated method stub
        stage.dispose();
        texture.dispose();
        skin.dispose();
        parent.dispose();

    }

}
