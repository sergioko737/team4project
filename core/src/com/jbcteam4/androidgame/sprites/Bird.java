package com.jbcteam4.androidgame.sprites;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.math.Rectangle;
        import com.badlogic.gdx.math.Vector3;
        import com.jbcteam4.androidgame.AppPreferences;


/**
 * The type Bird.
 */
public class Bird {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Sound flap;
    private Texture texture;

    /**
     * Instantiates a new Bird.
     *
     * @param x the x
     * @param y the y
     */
    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        texture = new Texture(AppPreferences.getPrefBirdAnimation());
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() /3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Gets bird.
     *
     * @return the bird
     */
    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    /**
     * Update.
     *
     * @param dt the dt
     */
    public void update(float dt){
        birdAnimation.update(dt);
        if (position.y > 0)
            velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        position.add(MOVEMENT * dt, velosity.y, 0);
        if (position.y < 0)
            position.y = 0;

        velosity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);


    }

    /**
     * Jump.
     */
    public void jump(){
        velosity.y = 220;
        if (AppPreferences.isSoundFXEnabled()) {
            flap.play(AppPreferences.getSoundFXVolume());
        }

    }

    /**
     * Get bounds rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getBounds(){
        return bounds;
    }


    /**
     * Dispose.
     */
    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}