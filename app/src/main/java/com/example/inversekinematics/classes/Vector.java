package com.example.inversekinematics.classes;

/**
 * Vector represented by x and y Cartesian System Coordinates and basic vector operations
 *
 * @author Pavel Mikulas
 * @version %I%, %G%
 */
public class Vector {
    /**
     * x coordinate
     */
    private double x;
    /**
     * y coordinate
     */
    private double y;

    /**
     * Construct a new Vector at position (0, 0)
     */
    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construct a new Vector at position (x,y)
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct a new Vector as a copy of source Vector
     *
     * @param src source Vector
     */
    public Vector(Vector src) {
        this.x = src.getX();
        this.y = src.getY();
    }

    /**
     * Create a new Vector as a difference of two Vectors
     *
     * @param a minuend Vector
     * @param b subtrahend Vector
     * @return difference Vector
     */
    public static Vector sub(Vector a, Vector b) {
        return new Vector(a.getX() - b.getX(), a.getY() - b.getY());
    }

    /**
     * Create a new Vector as a sum of two Vectors
     *
     * @param a first summand Vector
     * @param b second summand Vector
     * @return sum of the Vectors
     */
    public static Vector add(Vector a, Vector b) {
        if (a == null && b == null) return null;
        if (a == null) return b;
        if (b == null) return a;
        return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
    }

    /**
     * Calculate the distance between the two coordinate Vectors
     *
     * @param a source Vector
     * @param b target Vector
     * @return the distance between the two Vectors
     */
    public static double dist(Vector a, Vector b) {
        Vector diff = new Vector(b.getX() - a.getX(), b.getY() - a.getY());
        return diff.getLen();
    }

    /**
     * Normalize the Vector
     */
    public void normalize() {
        double len = getLen();
        x = getX() / len;
        y = getY() / len;
    }

    /**
     * Multiply both elements of the Vector with given value
     * @param val
     */
    public void mult(double val) {
        this.x *= val;
        this.y *= val;
    }

    /**
     * Set the Vector magnitude to match given length
     * @param len
     */
    public void setMag(double len) {
        normalize();
        mult(len);
    }

    /**
     * Calculate the length of the Vector
     *
     * @return length of the Vector
     */
    public double getLen() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    /**
     * Get the Vector x-coordinate
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Set the Vector x-coordinate
     * @param x x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get the Vector y-coordinate
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Set the Vector y-coordinate
     * @param y y value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Compare the Vector element by element with another Vector for equality
     *
     * @param o other Vector to compare
     * @return True if Vectors have equal coordinates
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.getX(), getX()) == 0 &&
                Double.compare(vector.getY(), getY()) == 0;
    }

    /**
     * Represent the Vector with a string
     *
     * @return string representation of the Vector
     */
    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
