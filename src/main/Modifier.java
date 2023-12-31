package main;

import main.Util.Ray;
import main.Util.Vec3;

public abstract class Modifier {
    public void modify(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered){

    }
    public boolean preModify(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered){
        return false;
    }
    public boolean postModify(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered){

        return false;
    }
}
