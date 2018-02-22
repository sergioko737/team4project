package com.jbcteam4.androidgame.states;


        import com.badlogic.gdx.graphics.g2d.SpriteBatch;

        import java.util.Stack;


/**
 * The type Game state manager.
 */
public class GameStateManager {

    private Stack<State> states;

    /**
     * Instantiates a new Game state manager.
     */
    public GameStateManager(){
        states = new Stack<State>();
    }

    /**
     * Push.
     *
     * @param state the state
     */
    public void push(State state){
        states.push(state);
    }

    /**
     * Pop.
     */
    public void pop(){
        states.pop().dispose();
    }

    /**
     * Set.
     *
     * @param state the state
     */
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    /**
     * Update.
     *
     * @param dt the dt
     */
    public void update(float dt){
        states.peek().update(dt);
    }

    /**
     * Render.
     *
     * @param sb the sb
     */
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}