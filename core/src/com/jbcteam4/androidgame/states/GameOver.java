package com.jbcteam4.androidgame.states;


        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.jbcteam4.androidgame.AppPreferences;
        import com.jbcteam4.androidgame.FlappyStarter;
        import com.jbcteam4.androidgame.states.PlayState;



public class GameOver extends State {

    private Texture background;
    private Texture gameover;
    BitmapFont font;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyStarter.WIDTH / 2, FlappyStarter.HEIGHT / 2);
        background = new Texture("bg-01.png");
        gameover = new Texture("gameover.png");
        font = new BitmapFont();
        PlayState.setNewHiScore();
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
        sb.draw(gameover, camera.position.x - gameover.getWidth() / 2, camera.position.y);
        font.draw(sb, String.valueOf("Your score: "+ PlayState.score), camera.position.x - (camera.viewportWidth / 2) + 33, 75); // score output
        font.draw(sb, String.valueOf("Hiscore: "+ PlayState.hiScore), camera.position.x - (camera.viewportWidth / 2) + 33, 40); // score output

        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();

        System.out.println("GameOver Disposed");

    }
}