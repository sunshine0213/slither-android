package com.example.inversekinematics.classes;

/**
 * Represents food at given position and gets assigned one snake that follows it
 *
 * @author Pavel Mikulas
 * @version %I%, %G%
 */
public class Food extends Vector {
    /**
     * Each Food can be assigned only one Snake as its consumer
     * <p>
     * After being set effectively final
     */
    private Snake consumer;

    /**
     * Construct new Food at position (0, 0)
     *
     * Constructor obsolete, only for backwards compatibility with Vector class
     */
    public Food() {
        this.setX(0);
        this.setY(0);
    }

    /**
     * Construct new Food at position (x, y)
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Food(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Construct new Food from a position Vector
     *
     * @param src position Vector
     */
    public Food(Vector src) {
        this.setX(src.getX());
        this.setY(src.getY());
    }

    /**
     * Check whether food has some snake assigned
     *
     * @return True if food was already assigned to some snake
     */
    public boolean isTaken() {
        return this.consumer != null;
    }

    /**
     * Check whether given food is paired with given snake
     *
     * @param snake
     * @return True if this foods consumer is given snake
     */
    public boolean isMine(Snake snake) {
        return this.consumer == snake;
    }

    /**
     * Assign given snake as this foods consumer
     *
     * Each food can be assigned a consumer only once
     * @param consumer Snake as a consumer
     */
    public void take(Snake consumer) {
        // Allow consumer to be only set once
        if (consumer != null) return;
        this.consumer = consumer;
    }

}
