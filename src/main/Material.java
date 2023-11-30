package main;

import main.Util.Ray;
import main.Util.Vec3;

public abstract class Material {
    public HitRecord scatter(Ray rIn, HitRecord rec, Vec3 color, Vec3 attenuation, Ray scattered){
        return rec;
    }
}
