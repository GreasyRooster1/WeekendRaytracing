package main.Materials;

import main.HitRecord;
import main.Main;
import main.Material;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Common.asNum;
import static main.Util.Common.vec3;
import static main.Util.Vec3.*;
import static processing.core.PApplet.println;
public class Phong extends Material {
    private Vec3 albedo;
    private double smoothness;
    private double specularProbability;

    public Phong(Vec3 color){
        albedo = color;
        smoothness = 0.5;
        specularProbability = 0.5;
    }
    public Phong(Vec3 color,double _smoothness){
        albedo = color;
        smoothness = _smoothness;
        specularProbability = 0.5;
    }
    public Phong(Vec3 color,double smooth,double sp){
        albedo = color;
        smoothness = smooth;
        specularProbability = sp;
    }

    public Phong(double r, double g, double b) {
        albedo = new Vec3(r,g,b);
    }
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 diffuseDirection = add(rec.normal,randomNormalizedVector());

        if(diffuseDirection.nearZero()){
            diffuseDirection = rec.normal;
        }
        boolean isSpecularBounce = specularProbability>= Main.app.random(1);
        Vec3 specularDirection = reflect(rIn.direction(),rec.normal);
        Vec3 scatterDir = Vec3.lerp(diffuseDirection,specularDirection, (float) smoothness*asNum(isSpecularBounce));


        //set() just acts as a pass-by-pointer equals operation
        scattered.set(new Ray(rec.collisionPoint,scatterDir));
        attenuation.set(lerp(albedo,vec3(1,1,1),asNum(isSpecularBounce)));

        return true;
    }
}
