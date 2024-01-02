package main.Objects;

import main.HitRecord;
import main.Util.Interval;
import main.Util.Ray;
import main.Util.Vec3;

public abstract class Hittable {
    public Vec3 center;

    public abstract boolean hit(Ray ray, Interval interval, HitRecord rec);
}
