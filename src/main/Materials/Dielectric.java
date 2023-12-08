package main.Materials;

import main.HitRecord;
import main.Main;
import main.Material;
import main.Util.Ray;
import main.Util.Vec3;

import static java.lang.Math.*;
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

        //this shouldn't be necessary, yet here we are
        Vec3 hitPointNormal = rec.front_face ? rec.normal: rec.normal.invert();

        double cosTheta = min(dot(direction, hitPointNormal), 1.0);
        double sinTheta = sqrt(1.0 - cosTheta*cosTheta);

        boolean cannotRefract = refractionRatio * sinTheta > 1.0;
        Vec3 scatterDirection = new Vec3();

        if (cannotRefract || reflectance(cosTheta, refractionRatio) > Main.app.random(1)) {
            scatterDirection = reflect(direction, hitPointNormal);
        }else {
            scatterDirection = refract(direction, hitPointNormal, refractionRatio);
        }

        scattered.set(new Ray(rec.p,scatterDirection));
        return true;
    }
    private static double reflectance(double cosine, double ref_idx) {
        // Use Schlick's approximation for reflectance.
        double r0 = (1-ref_idx) / (1+ref_idx);
        r0 = r0*r0;
        return r0 + (1-r0)*pow((1 - cosine),5);
    }
}
