package com.jbcteam4.androidgame.states;


        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.math.Vector3;


/**
 * The type State.
 */
public abstract class State {

    /**
     * The Camera.
     */
    protected OrthographicCamera camera;
    /**
     * The Mouse.
     */
    protected Vector3 mouse;
    /**
     * The Gsm.
     */
    protected GameStateManager gsm;

    /**
     * Instantiates a new State.
     *
     * @param gsm the gsm
     */
    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    /**
     * Handle input.
     */
    protected abstract void handleInput();

    /**
     * Update.
     *
     * @param dt the dt
     */
    public abstract void update(float dt);

    /**
     * Render.
     *
     * @param sb the sb
     */
    public abstract void render(SpriteBatch sb);

    /**
     * Dispose.
     */
    public abstract void dispose();
}