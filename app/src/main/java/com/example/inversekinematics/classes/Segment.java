package com.example.inversekinematics.classes;

/**
 * Represents one segment of snake body and connects segments together
 * <p>
 * Structured for easy usage of inverse kinematics
 *
 * @author Pavel Mikulas
 * @version %I%, %G%
 */
public class Segment {
    /**
     * Starting-Point of the Segment
     */
    private Vector a;
    /**
     * End-Point of the Segment
     */
    private Vector b = new Vector();
    /**
     * Angle of rotation of the Segment
     */
    private double angle;
    /**
     * Length of the Segment
     */
    private double len;

    /**
     * Constructs a new Segment from Cartesian Coordinates and length
     * and calculates the end-point of the Segment
     *
     * @param x   x coordinate
     * @param y   y coordinate
     * @param len length of the Segment
     */
    public Segment(double x, double y, double len) {
        this.a = new Vector(x, y);
        this.len = len;

        calculateEnd();
    }

    /**
     * Constructs a new Segment from a Vector and length
     * and calculates the end-point of the Segment
     * @param u position Vector
     * @param len length of the Segment
     */
    public Segment(Vector u, double len) {
        this.a = new Vector(u);
        this.len = len;

        calculateEnd();
    }

    /**
     * Construct a new Segment at the end-point of parent Segment, give it the same
     * length as its parent and calculate its end-point
     *
     * @param parent parenting Segment
     */
    public Segment(Segment parent) {
        this.a = new Vector(parent.b);
        this.len = parent.len;
        calculateEnd();
    }

    /**
     * Calculates the end-point of the segment in polar coordinates based on the length
     * and angle of rotation
     */
    private void calculateEnd() {
        // Polar to cartesian
        double dx = len * Math.cos(angle);
        double dy = len * Math.sin(angle);

        b.setX(a.getX() + dx);
        b.setY(a.getY() + dy);
    }

    /**
     * Makes the segment follow certain coordinate
     */
    public void follow(double targetX, double targetY) {
        double dy = targetY - a.getY();
        double dx = targetX - a.getX();
        angle = Math.atan2(dy, dx);

        Vector target = new Vector(targetX, targetY);
        Vector dir = Vector.sub(target, a);

        dir.setMag(len);
        dir.mult(-1);

        a = Vector.add(target, dir);
    }

    /**
     * Recalculates the end-point of given segment
     */
    public void update() {
        calculateEnd();
    }

    /**
     * Getter for the starting-point of the segment
     *
     * @return Starting-Point of the Segment
     */
    public Vector getA() {
        return a;
    }

    /**
     * Getter for the end-point of the segment
     *
     * @return End-Point of the Segment
     */
    public Vector getB() {
        return b;
    }

    /**
     * Calculates the center of this segment
     *
     * @return Center of the Segment
     */
    public Vector getCenter() {
        return new Vector((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
    }
}
