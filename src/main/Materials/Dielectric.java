package main.Materials;

import main.HitRecord;
import main.Material;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Common.color;
import static main.Util.Common.vec3;
import static main.Util.Vec3.*;

public class Dielectric extends Material {
    private double ir;

    public Dielectric(double _ir){
        ir = _ir;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        attenuation.set(color(1,1,1));
        double refractionRatio = rec.front_face ? (1.0/ir) : ir;

        Vec3 direction = normalize(rIn.direction());
        Vec3 refracted = refract(direction,rec.normal,refractionRatio);
        // Vec3 refracted = rIn.direction();

        scattered.set(new Ray(rec.p,mult(refracted, rIn.direction().length())));
        return true;
    }
}
