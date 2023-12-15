package main;

import main.Util.Ray;
import main.Util.Vec3;

public abstract class Material {
    public Vec3 emitted(double u, double v, Vec3 p){
        return new Vec3();
    }
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered){
        return false;
    }
}
