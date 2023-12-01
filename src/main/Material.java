package main;

import main.Util.Ray;
import main.Util.Vec3;

public abstract class Material {
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered){
        return false;
    }
}
