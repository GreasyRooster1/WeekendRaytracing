package main;

import main.Util.Interval;
import main.Util.Ray;

public abstract class Hittable {

    //as a result of java's pass-by-value system, the HitRecord must be passed around.
    public abstract boolean hit(Ray ray, Interval interval, HitRecord rec);
}
