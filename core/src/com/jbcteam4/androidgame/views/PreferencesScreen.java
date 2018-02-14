package com.jbcteam4.androidgame.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
import com.jbcteam4.androidgame.FlappyStarter;

public class PreferencesScreen implements Screen {

    private FlappyStarter parent;
    private Stage stage;
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;


    public PreferencesScreen(FlappyStarter flappyStarter){
        parent = flappyStarter;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside
        // this table.
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // music volume
        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);

        volumeMusicSlider.setValue(parent.getPreferences().getMusicVolume());

        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                // updateVolumeLabel();
                return false;
            }
        });

        // sound volume
        final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundMusicSlider.setValue(parent.getPreferences().getSoundVolume());
        soundMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setSoundVolume(soundMusicSlider.getValue());
                // updateVolumeLabel();
                return false;
            }
        });

        // music on/off
        final CheckBox musicCheckbox = new CheckBox(null, skin, "default");
        musicCheckbox.setChecked(parent.getPreferences().isMusicEnabled());
     //   musicCheckbox.getCells().get(0).size(150, 150);
        musicCheckbox.getImageCell().width(150);
        musicCheckbox.getImageCell().height(150);

        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getPreferences().setMusicEnabled(enabled);
                return false;
            }
        });

        // sound on/off
        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin, "default");
        soundEffectsCheckbox.setChecked(parent.getPreferences().isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getPreferences().setSoundEffectsEnabled(enabled);
                return false;
            }
        });

        // return to main screen button
        final TextButton backButton = new TextButton("Back", skin, "default");
        backButton.setSize(200, 200);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(FlappyStarter.MENU);

            }
        });

        titleLabel = new Label( "Preferences", skin);
        titleLabel.setFontScale(7);
        volumeMusicLabel = new Label( "Music Volume", skin);
        volumeMusicLabel.setFontScale(4);
        volumeSoundLabel = new Label( "Sound Volume", skin);
        volumeSoundLabel.setFontScale(4);
        musicOnOffLabel = new Label( "Music", skin);
        musicOnOffLabel.setFontScale(4);
        soundOnOffLabel = new Label( "Sound Effect", skin);
        soundOnOffLabel.setFontScale(4);

        table.add(titleLabel).colspan(2);
        table.row().pad(70,0,0,10);
        table.add(volumeMusicLabel).bottom();
        table.row().pad(10,0,0,10);
        table.add(volumeMusicSlider);
        table.row().pad(40,0,0,10);
        table.add(musicOnOffLabel).left();
        table.add(musicCheckbox);
        table.row().pad(40,0,0,10);
        table.add(volumeSoundLabel).left();
        table.row().pad(10,0,0,10);
        table.add(soundMusicSlider);
        table.row().pad(40,0,0,10);
        table.add(soundOnOffLabel).left();
        table.add(soundEffectsCheckbox);
        table.row().pad(100,0,0,10);
        table.add(backButton).colspan(2);

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
