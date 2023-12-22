package main.Util;

import static main.Util.Vec3.add;
import static main.Util.Vec3.mult;

public class Ray {
    Vec3 orig;
    Vec3 dir;
    public Ray(Point3 origin, Vec3 direction) {
        orig = origin;
        dir=direction;
    }
    public Ray(Vec3 origin, Vec3 direction) {
        orig = origin;
        dir = direction;
    }

    public Ray() {
        orig = new Vec3(0,0,0);
        dir = new Vec3(0,0,0);
    }

    public Vec3 origin() { return orig; }
    public Vec3 direction() { return dir; }
    public Vec3 directionNormalized() { return dir.normalized(); }

    public Vec3 at(double t){
        return add(orig,(mult(t,dir)));
    }

    public Ray set(Ray r){
        orig = r.orig;
        dir = r.dir;
        return this;
    }

}
