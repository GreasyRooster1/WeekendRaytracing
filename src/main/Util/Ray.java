package main.Util;

import static main.Util.Vec3.mult;

public class Ray {
    Point3 orig;
    Vec3 dir;
    public Ray(Point3 origin, Vec3 direction) {
        orig = origin;
        dir=direction;
    }

    public Point3 origin() { return orig; }
    public Vec3 direction() { return dir; }

    public Point3 at(double t){
        return (Point3) orig.add(mult(t,dir));
    }

}
