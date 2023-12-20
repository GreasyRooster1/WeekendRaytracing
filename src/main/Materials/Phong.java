package main.Materials;

import main.HitRecord;
import main.Material;
import main.Objects.PointLight;
import main.Util.Interval;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Common.dist;
import static main.Util.Vec3.*;

public class Phong extends Material {
    private Vec3 albedo;

    public Phong(Vec3 color){
        albedo = color;
    }

    public Phong(double r, double g, double b) {
        albedo = new Vec3(r,g,b);
    }
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 scatterDirection = add(rec.normal,randomNormalizedVector());

        if(scatterDirection.nearZero()){
            scatterDirection = rec.normal;
        }

        Vec3 specularTotal = new Vec3();
        for(PointLight light:rec.world.lights){

            Vec3 directionToLight = sub(rec.collisionPoint,light.position).normalize();
            double lightDistance = dist(rec.collisionPoint,light.position);

            if(!rec.world.hitSomething(new Ray(rec.collisionPoint,directionToLight), new Interval(0,lightDistance))){
                double lightIntensity = dot( reflect(directionToLight, rec.normal).invert() ,rIn.direction());
                specularTotal.add(mult(lightIntensity,light.color));
            }
        }
        Vec3 specular = specularTotal;

        //set() just acts as a pass-by-pointer equals operation
        scattered.set(new Ray(rec.collisionPoint,scatterDirection));
        attenuation.set(add(albedo,specular));

        return true;
    }
}
