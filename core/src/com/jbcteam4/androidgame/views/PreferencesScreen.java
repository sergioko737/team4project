package com.jbcteam4.androidgame.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * The type Preferences screen.
 */
public class PreferencesScreen implements Screen {

    private FlappyStarter parent;
    private Stage stage;
    /**
     * The Texture.
     */
    public Texture texture;
    private Skin skin;
    private ImageButton ava1;
    private ImageButton ava2;
    private ImageButton ava3;

    /**
     * Instantiates a new Preferences screen.
     *
     * @param flappyStarter the flappy starter
     */
    public PreferencesScreen(FlappyStarter flappyStarter) {
        parent = flappyStarter;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        texture = new Texture(AppPreferences.getPrefBackground());
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
            table.scaleBy(3.0f);
        }
        table.setOrigin(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        // table.setDebug(true);
        stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // CHANGE AVATAR BUTTONS
        Texture avaTexture01 = new Texture(Gdx.files.internal("birdDefault.png"));
        TextureRegion avaTextureRegion01 = new TextureRegion(avaTexture01);
        TextureRegionDrawable avaTextureRegionDrawable = new TextureRegionDrawable(avaTextureRegion01);

        ava1 = new ImageButton(avaTextureRegionDrawable);


        Texture avaTexture02 = new Texture(Gdx.files.internal("birdAccenture.png"));
        TextureRegion avaTextureRegion02 = new TextureRegion(avaTexture02);
        TextureRegionDrawable avaTextureRegionDrawable02 = new TextureRegionDrawable(avaTextureRegion02);

        ava2 = new ImageButton(avaTextureRegionDrawable02);

        Texture avaTexture03 = new Texture(Gdx.files.internal("birdDefault.png"));
        TextureRegion avaTextureRegion03 = new TextureRegion(avaTexture03);
        TextureRegionDrawable avaTextureRegionDrawable03 = new TextureRegionDrawable(avaTextureRegion03);

        ava3 = new ImageButton(avaTextureRegionDrawable03);

        ava1.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                ava1.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[0]))));
                ava2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[4]))));
                ava3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[5]))));
                AppPreferences.setPrefBirdAvatar(0);
                AppPreferences.setPrefBirdAnimation(0);
                return false;
            }
        });

        ava2.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                ava1.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[3]))));
                ava2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[1]))));
                ava3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[5]))));
                AppPreferences.setPrefBirdAvatar(1);
                AppPreferences.setPrefBirdAnimation(1);
                return false;
            }
        });

        ava3.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                ava1.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[3]))));
                ava2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[4]))));
                ava3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AppPreferences.birds[2]))));
                AppPreferences.setPrefBirdAvatar(2);
                AppPreferences.setPrefBirdAnimation(2);
                return false;
            }
        });

        // CHANGE BACKGROUND SLIDER
        final Slider backgroundSelectorSlider = new Slider(0f, 2f, 1f, false, skin);
        backgroundSelectorSlider.setValue(AppPreferences.getPrefBackgroundSelector());

        backgroundSelectorSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                AppPreferences.setPrefBackground((int) backgroundSelectorSlider.getValue());
                AppPreferences.setPrefToptube((int) backgroundSelectorSlider.getValue());
                AppPreferences.setPrefBottomtube((int) backgroundSelectorSlider.getValue());
                AppPreferences.setPrefBackgroundSelector(backgroundSelectorSlider.getValue());
                MenuScreen.texture.dispose();
                MenuScreen.texture = new Texture(AppPreferences.getPrefBackground());
                texture.dispose();
                texture = new Texture(AppPreferences.getPrefBackground());
                return false;
            }
        });


        // MUSIC VOLUME SLIDER
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

        Label titleLabel = new Label("Preferences", skin);
        titleLabel.setFontScale(1.5f);
        Label changeAvatarLabel = new Label("Select avatar", skin);
        Label backgroundSelectorLabel = new Label("Select playground", skin);
        Label volumeMusicLabel = new Label("Music Volume", skin);
        Label volumeSoundLabel = new Label("Sound Volume", skin);
        Label musicOnOffLabel = new Label("ON/OFF", skin);
        Label soundOnOffLabel = new Label("ON/OFF", skin);

        //table.debug();
        table.add(titleLabel).colspan(3).center();
        table.row().pad(20, 0, 0, 0);
        table.add(changeAvatarLabel).colspan(3).center();
        table.row().pad(20, 0, 0, 0);
        table.add(ava1).center().colspan(1);
        table.add(ava2).center().colspan(1);
        table.add(ava3).center().colspan(1);
        table.row().pad(20, 0, 0, 0);
        table.add(backgroundSelectorLabel).center().colspan(3);
        table.row().pad(10, 0, 0, 0);
        table.add(backgroundSelectorSlider).colspan(3).center();
        table.row().pad(10, 0, 0, 0);
        table.add(volumeMusicLabel).center().colspan(2);
        table.add(musicOnOffLabel).center();
        table.row().pad(5, 10, 0, 10);
        table.add(volumeMusicSlider).colspan(2);
        table.add(musicCheckbox);
        table.row().pad(10, 10, 0, 10);
        table.add(volumeSoundLabel).center().colspan(2);
        table.add(soundOnOffLabel).center();
        table.row().pad(5, 10, 0, 10);
        table.add(soundFXSlider).colspan(2);
        table.add(soundFXCheckbox);
        table.row().pad(10, 10, 0, 10);
        table.add(backButton).colspan(3);

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
