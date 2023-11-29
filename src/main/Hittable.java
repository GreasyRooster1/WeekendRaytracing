package main;

import main.Util.Ray;

public abstract class Hittable {

    //as a result of java's pas-by-value system, the HitRecord must be passed around.
    public abstract HitRecord hit(Ray ray, double rayTmin, double rayTmax, HitRecord rec);
}
