package org.firstinspires.ftc.teamcode.util.motion;

public class Point
{
    private double x, y;
    private String name;

    public Point(double x, double y, String name)
    {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    double getX() { return x; }

    double getY() { return y; }

    double getDistance(Point other) { return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2)); }

    double getAngle(Point other) { return Math.atan2(y, x); }
}
