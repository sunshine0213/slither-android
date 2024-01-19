package com.example.inversekinematics.classes;

import com.example.inversekinematics.enums.SnakeState;
import com.example.inversekinematics.enums.SnakeType;

import java.util.List;

/**
 * Snake body representation as list of Segments with basic functionality such as extending
 * and enlarging
 *
 * @author Pavel Mikulas
 * @version %I%, %G%
 */
public class Snake {
    /**
     * Type of the snake
     */
    private final SnakeType type;
    /**
     * Segments of snake body
     */
    private List<Segment> parts;
    /**
     * Current state of the snake (Alive, Dead)
     */
    private SnakeState state;
    /**
     * Current snake body size
     */
    private float bodySize = 16;
    /**
     * Current snake score
     */
    private int score = 1;

    /**
     * Constructor for creating a new snake from list of Segments and making it alive
     *
     * @param parts List of Segments
     * @param type  Type of Snake (Aggressive, Passive, Hybrid)
     */
    public Snake(List<Segment> parts, SnakeType type) {
        this.parts = parts;
        this.type = type;
        state = SnakeState.ALIVE;
    }

    /**
     * Get current snake state
     *
     * @return Snake state (Alive, Dead)
     */
    public SnakeState getState() {
        return state;
    }

    /**
     * Get type of the snake
     *
     * @return Type of Snake (Aggressive, Passive, Hybrid)
     */
    public SnakeType getType() {
        return type;
    }

    /**
     * Get the head Segment from snakes body
     *
     * @return Head of the Snake
     */
    public Segment getHead() {
        return parts.get(parts.size() - 1);
    }

    /**
     * Get the size of the Snake
     *
     * @return Body size of the snake
     */
    public float getBodySize() {
        return bodySize;
    }

    /**
     * Get the body of the snake
     *
     * @return List of snake body segments
     */
    public List<Segment> getSnake() {
        return parts;
    }

    /**
     * Check for collision of snake with food and consume food if collision happens
     *
     * @param food List of Food on game screen
     * @return True if snake has consumed food
     */
    public boolean eat(List<Food> food) {
        Vector toRemove = null;

        // Check for collision with food
        for (Vector apple : food) {
            if (Vector.dist(apple, getHead().getCenter()) <= bodySize) {
                toRemove = apple;
            }
        }

        if (toRemove != null) {
            food.remove(toRemove);
            extend();
            return true;
        }

        return false;
    }

    /**
     * Extend the snakes body with every 2 pieces of food consumed
     *
     * Enlarge the snakes body size with every 5 pieces of food consumed
     *
     * With every food consumed add one point to the snakes score
     */
    private void extend() {
        score++;
        if (score % 2 == 0) {
            parts.add(0, new Segment(parts.get(0)));
        }

        // Maximum body size is 80
        if (score % 5 == 0){
            if (bodySize < 80) bodySize *= 1.02;
        }
    }

    /**
     * Set the state of the snake to Dead
     */
    public void kill() {
        this.state = SnakeState.DEAD;
    }
}
