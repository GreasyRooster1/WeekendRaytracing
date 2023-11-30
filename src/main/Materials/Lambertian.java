package main.Materials;

import main.HitRecord;
import main.Material;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Vec3.add;
import static main.Util.Vec3.randomNormalizedVector;

public class Lambertian extends Material {
    private Vec3 albedo;

    public Lambertian(Vec3 color){
        albedo = color;
    }

    @Override
    public HitRecord scatter(Ray rIn, HitRecord rec, Vec3 color, Vec3 attenuation, Ray scattered) {
        Vec3 scatterDirection = add(rec.normal,randomNormalizedVector());

        if(scatterDirection.nearZero()){
            scatterDirection = rec.normal;
        }

        //set() just acts as a pass-by-pointer equals operation
        scattered.set(new Ray(rec.p,scatterDirection));
        attenuation.set(albedo);

        rec.hitAnything=true;
        return rec;
    }
}