package com.jbcteam4.androidgame.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbcteam4.androidgame.AppPreferences;
import com.jbcteam4.androidgame.FlappyStarter;
import com.jbcteam4.androidgame.states.PlayState;

/**
 * The type Still alive.
 */
public class StillAlive extends State {

    private Texture background;
    private Texture tryagain;
    private BitmapFont font;

    /**
     * Instantiates a new Still alive.
     *
     * @param gsm the gsm
     */
    public StillAlive(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyStarter.WIDTH / 2, FlappyStarter.HEIGHT / 2);
        background = new Texture(AppPreferences.getPrefBackground());
        tryagain = new Texture("tryagain.png");
        font = new BitmapFont();


//        PlayState.setNewHiScore();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        font.setColor(Color.WHITE);         // font
        font.getData().setScale(2);         // font

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(tryagain, camera.position.x - tryagain.getWidth() / 2, camera.position.y + 40);
        font.draw(sb, String.valueOf("Your score: "+ PlayState.score), camera.position.x - (camera.viewportWidth / 2) + 33, 75); // score output
        font.draw(sb, String.valueOf("Lives left: "+ PlayState.lives), camera.position.x - (camera.viewportWidth / 2) + 50, 320); // Lives Left

        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        tryagain.dispose();

        System.out.println("StillAlive Disposed");

    }
}