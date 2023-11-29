package main;

import main.Util.Ray;

public abstract class Hittable {
    public abstract boolean hit(Ray ray, double rayTmin, double rayTmax, HitRecord rec);
}
