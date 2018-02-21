package com.jbcteam4.androidgame.states;



        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Batch;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;    // adding Fonts
        import com.badlogic.gdx.graphics.Color;             // adding Colors
        import com.badlogic.gdx.math.Vector2;
        import com.badlogic.gdx.utils.Array;
        import com.jbcteam4.androidgame.FlappyStarter;
        import com.jbcteam4.androidgame.sprites.Bird;
        import com.jbcteam4.androidgame.AppPreferences;
        import com.jbcteam4.androidgame.sprites.Tube;


public class PlayState extends State {

    private static final int TUBE_SPACING = 125; // 125 standart
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30; // -30 standart

    private Bird bird;
    private Texture bg;
    private Texture live;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private FlappyStarter parent;
    private static BitmapFont font;
    private static BitmapFont font1;
    private static BitmapFont font2;

    private Array<Tube> tubes;

//    SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
//    int oldScore = prefs.getInt("highScore", 0);

    public int frames = 0;      // adding frames
    public static int score = 0;    // adding score field
    private static int liveScore = 0;
    public static int hiScore = AppPreferences.getPrefHiScore();    // adding hiscore
    public static int lives = 3;

    public PlayState(GameStateManager gsm) {

        super(gsm);

        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyStarter.WIDTH / 2, FlappyStarter.HEIGHT / 2);
        bg = new Texture(AppPreferences.getPrefBackground());
        ground = new Texture("ground.png");
        font = new BitmapFont();
        font1 = new BitmapFont();
        font2 = new BitmapFont();
        live = new Texture("live.png");

        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();
        font = new BitmapFont();
        font1 = new BitmapFont();

        for (int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    public static void setNewHiScore() {
        if (score > hiScore) {
            hiScore = score;
            AppPreferences.setPrefHiScore(score);
        }

    }


    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.collides(bird.getBounds())) {
                --lives;
                if (lives > 0){
                    score = score + liveScore;
                    gsm.set(new StillAlive(gsm));
                } else {
                    score = score + liveScore;
                    gsm.set(new GameOver(gsm));
                }

            }
        }
        frames++;
        liveScore = frames / 73;
//        score = score + liveScore;
        camera.update();

        if (liveScore > 40){
            Tube.setTubeGap(75);
        } else if (liveScore > 20){
            Tube.setTubeGap(95);
        }

    }

    @Override
    public void render(SpriteBatch sb) {


        font.setColor(Color.BLACK);         // font
        font.getData().setScale(2);         // font

        font2.setColor(Color.WHITE);         // font
        font2.getData().setScale(2);         // font

        font1.setColor(Color.GRAY);         // font
        font1.getData().setScale(2);         // font

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);

        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        font.draw(sb, String.valueOf(score + liveScore), camera.position.x - (camera.viewportWidth / 2) + 10, 43); // score output
//        font.draw(sb, String.valueOf(lives), camera.position.x - (camera.viewportWidth / 2) + 40, 43); // test lives output
        font1.draw(sb, "HiScore: " + String.valueOf(hiScore), camera.position.x - (camera.viewportWidth / 2) + 75, 43); // jumps output

        if (lives == 3) {
            sb.draw(live, camera.position.x - (camera.viewportWidth / 2) +5, camera.viewportHeight - 35);
            sb.draw(live, camera.position.x - (camera.viewportWidth / 2) +35, camera.viewportHeight - 35);
            sb.draw(live, camera.position.x - (camera.viewportWidth / 2) +65, camera.viewportHeight - 35);
        } else if (lives == 2) {
            sb.draw(live, camera.position.x - (camera.viewportWidth / 2) +5, camera.viewportHeight - 35);
            sb.draw(live, camera.position.x - (camera.viewportWidth / 2) +35, camera.viewportHeight - 35);
        } else if (lives == 1) {
            sb.draw(live, camera.position.x - (camera.viewportWidth / 2) +5, camera.viewportHeight - 35);
        }

        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        System.out.println("PlayState Disposed");

    }

    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}