package main.Materials;

import main.HitRecord;
import main.Material;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Vec3.add;
import static main.Util.Vec3.randomNormalizedVector;

public class Dichroic extends Material {
    private Vec3 albedo;
    private Vec3 reflect;
    private Vec3 refract;

    public Dichroic(Vec3 _reflect,Vec3 _refract){
        reflect = _reflect;
        refract = _refract;
    }
    public Dichroic(Vec3 _albedo,Vec3 _reflect,Vec3 _refract){
        reflect = _reflect;
        refract = _refract;
        albedo = _albedo;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 scatterDirection = add(rec.normal,randomNormalizedVector());

        if(scatterDirection.nearZero()){
            scatterDirection = rec.normal;
        }

        //set() just acts as a pass-by-pointer equals operation
        scattered.set(new Ray(rec.collisionPoint,scatterDirection));
        attenuation.set(albedo);

        return true;
    }
}
