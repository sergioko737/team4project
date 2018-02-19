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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    private AppPreferences preferences;
    private Texture texture;
    Skin skin;

    public PreferencesScreen(FlappyStarter flappyStarter) {
        parent = flappyStarter;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        texture = new Texture("bg.png");
        // temporary until we have asset manager in
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
    }

    public AppPreferences getPreferences() {
        return this.preferences;
    }

    @Override
    public void show() {
        stage.clear();
        preferences = new AppPreferences();
        Gdx.input.setInputProcessor(stage);

        Gdx.input.setCatchBackKey(true);


        // Create a table that fills the screen. Everything else will go inside
        // this table.
        Table table = new Table();
        table.setFillParent(true);
        if(Gdx.graphics.getWidth() >800)
        {
         table.setTransform(true);
         table.scaleBy(3.3f);
        }
        table.setOrigin(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        // table.setDebug(true);
        stage.addActor(table);




        // music volume
        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);

        volumeMusicSlider.setValue(preferences.getMusicVolume());


        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                preferences.getPrefs().putFloat("volume",volumeMusicSlider.getValue()).flush();
                if (preferences.isMusicEnabled()) {
                    parent.playingSong.setVolume(volumeMusicSlider.getValue());
                }

                System.out.println("TOBEREMOVED sound music slider engaged " + preferences.getMusicVolume());
                return false;
            }
        });

        // sound volume
        final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);

        soundMusicSlider.setValue(preferences.getSoundVolume());

        soundMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                preferences.getPrefs().putFloat("sound", soundMusicSlider.getValue()).flush();
                System.out.println("TOBEREMOVED sound fx slider engaged " + soundMusicSlider.getValue());
                return false;
            }
        });

        // music on/off
        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked(preferences.isMusicEnabled());
        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                preferences.getPrefs().putBoolean("music.enabled", musicCheckbox.isChecked()).flush();
                if (!musicCheckbox.isChecked()){
                    parent.playingSong.setVolume(0);
                } else {
                    parent.playingSong.setVolume(volumeMusicSlider.getValue());
                }
               /* boolean enabled = musicCheckbox.isChecked();
                parent.onOffMusic(enabled);*/

                System.out.println("TOBEREMOVED music checkbox checked " + musicCheckbox.isChecked());
                return false;
            }
        });

        // sound on/off
        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.setChecked(parent.getPreferences().isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getPreferences().setSoundEffectsEnabled(enabled);
                System.out.println("TOBEREMOVED sound checkbox checked " + enabled);
                System.out.println("TOBEREMOVED get music volume " + parent.getPreferences().getMusicVolume());
                System.out.println("TOBEREMOVED get music volume " + preferences.getMusicVolume());
                return false;
            }
        });

        // return to main screen button
        final TextButton backButton = new TextButton("Back", skin, "small");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(FlappyStarter.MENU);
                System.out.println("TOBEREMOVED Back button pressed");

            }
        });

        titleLabel = new Label("Preferences", skin);
        titleLabel.setFontScale(1.5f);
        volumeMusicLabel = new Label("Music Volume", skin);
        volumeSoundLabel = new Label("Sound Volume", skin);
        musicOnOffLabel = new Label("Music ON/OFF", skin);
        soundOnOffLabel = new Label("Sound Effect ON/OFF", skin);

        table.add(titleLabel).colspan(2);
        table.row().pad(20, 0, 0, 10);
        table.add(volumeMusicLabel).center();
        table.row().pad(5, 0, 0, 10);
        table.add(volumeMusicSlider);
        table.row().pad(10, 0, 0, 10);
        table.add(musicOnOffLabel).center();
        table.row().pad(5, 0, 0, 10);
        table.add(musicCheckbox);
        table.row().pad(10, 0, 0, 10);
        table.add(volumeSoundLabel).center();
        table.row().pad(5, 0, 0, 10);
        table.add(soundMusicSlider);
        table.row().pad(10, 0, 0, 10);
        table.add(soundOnOffLabel).center();
        table.row().pad(5, 0, 0, 10);
        table.add(soundEffectsCheckbox);
        table.row().pad(30, 0, 0, 10);
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
