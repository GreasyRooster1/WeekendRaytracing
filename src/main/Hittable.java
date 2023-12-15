package main;

import main.Util.Interval;
import main.Util.Ray;
import main.Util.Vec3;

public abstract class Hittable {
    public Vec3 center;

    //as a result of java's pass-by-value system, the HitRecord must be passed around.
    public abstract boolean hit(Ray ray, Interval interval, HitRecord rec);
}
