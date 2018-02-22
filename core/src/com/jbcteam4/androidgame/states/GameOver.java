package com.jbcteam4.androidgame.states;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.g2d.Animation;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.jbcteam4.androidgame.FlappyStarter;
        import com.jbcteam4.androidgame.sprites.Tube;
        import com.jbcteam4.androidgame.GifDecoder;

/**
 * The Game over state. Shows screen with animation at the end of game. Here is HiScores and it changes, if current game score is larger.
 */
public class GameOver extends State {

    float elapsed;
    private Texture background;
    private Texture gameover;
    private BitmapFont font;
    private Animation<TextureRegion> animation;

    /**
     * Instantiates a new Game over.
     *
     * @param gsm the gsm
     */
    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyStarter.WIDTH / 2, FlappyStarter.HEIGHT / 2);
        background = new Texture("bg-go.png");
        gameover = new Texture("gameover.png");
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP_PINGPONG, Gdx.files.internal("giphy.gif").read());
        font = new BitmapFont();
        PlayState.lives = 3;
        PlayState.setNewHiScore();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            PlayState.score = 0;
            Tube.setTubeGap(120);
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
        elapsed += Gdx.graphics.getDeltaTime();                 //param for GIF animation
        Gdx.gl.glClearColor(1, 0, 0, 0);   //param for GIF animation
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);               //param for GIF animation
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(animation.getKeyFrame(elapsed), -7, 185);  //GIF animation
        sb.draw(gameover, camera.position.x - gameover.getWidth() / 2, camera.position.y - 75);
        font.draw(sb, String.valueOf("Your score: "+ PlayState.score), camera.position.x - (camera.viewportWidth / 2) + 33, 95); // score output
        font.draw(sb, String.valueOf("Hiscore: "+ PlayState.hiScore), camera.position.x - (camera.viewportWidth / 2) + 33, 60); // score output
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
    }
}