package main;

import main.Util.Interval;
import main.Util.Ray;

public abstract class Hittable {

    //as a result of java's pas-by-value system, the HitRecord must be passed around.
    public abstract HitRecord hit(Ray ray, Interval interval, HitRecord rec);
}
