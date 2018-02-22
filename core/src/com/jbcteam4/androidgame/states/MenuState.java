package com.jbcteam4.androidgame.states;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.jbcteam4.androidgame.AppPreferences;
        import com.jbcteam4.androidgame.FlappyStarter;

/**
 * The type Menu state. First screen with Preferences, Credits, New Game and Exit buttons.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    /**
     * Instantiates a new Menu state.
     *
     * @param gsm the gsm
     */
    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyStarter.WIDTH / 2, FlappyStarter.HEIGHT / 2);
        background = new Texture(AppPreferences.getPrefBackground());
        playBtn = new Texture("playbtn-01.png");
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
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y); // "Tap to play"
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}