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
    private Vec3 albedo = new Vec3(1,1,1);

    public Dielectric(double _ir){
        ir = _ir;
    }
    public Dielectric(double _ir,Vec3 _albedo){
        ir = _ir;
        albedo = _albedo;
    }
    public Dielectric(Vec3 _albedo,double _ir){
        ir = _ir;
        albedo = _albedo;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        attenuation.set(albedo);

        // Dielectric scattering: Determine the outward normal and the refraction indexes ratio
        Vec3 outwardNormal;
        double refIdxRatio;
        Vec3 unitDirection = normalize(rIn.direction());
        double dt = dot(unitDirection, rec.normal);
        if (dt > 0.f)
        {
            outwardNormal = rec.normal.invert();
            refIdxRatio = ir;
        }
        else
        {
            outwardNormal = rec.normal;
            refIdxRatio = 1.f / ir;
        }

        // Determine the reflection probability
        Vec3 refracted = new Vec3();
        double reflectProb = 1.f;
        if (getRefractedVector(unitDirection, outwardNormal, refIdxRatio, refracted))
        {
            // Compute the reflection probability
            if (dt > 0.f)
            {
                double discriminant = 1.f - ir * ir * (1.f - dt * dt);

                if (discriminant > 0.f)
                {
                    reflectProb = getSchlickApproximation(sqrt(discriminant), ir);
                }
            }
            else
            {
                reflectProb = getSchlickApproximation(-dt, ir);
            }
        }

        // Reflect or refract depending on the reflection probability
        if (reflectProb == 1.f || Main.app.random(1) < reflectProb)
        {
            Vec3 reflected = getReflectedVector(rIn.direction(), rec.normal);
            scattered.set(new Ray(rec.p, reflected));
        }
        else
        {
            scattered.set(new Ray(rec.p, refracted));
        }

        return true;
    }

    private Vec3 getReflectedVector(Vec3 direction, Vec3 normal) {
        return reflect(direction,normal);
    }

    private boolean getRefractedVector(Vec3 v,Vec3 n, double refIdxRatio,Vec3 refracted) {
        double dt = dot(v, n);
        double discriminant = 1.f - refIdxRatio * refIdxRatio * (1.f - dt * dt);
        if (discriminant > 0.f)
        {
            refracted.set(sub(mult(refIdxRatio,sub(v,mult(n,dt))),mult(n,sqrt(discriminant))));
            return true;
        }
        return false;
    }

    private static double getSchlickApproximation(double cosine, double refIdx) {
        // Use Schlick's approximation for reflectance.
        double r0 = pow((1.f - refIdx) / (1.f + refIdx), 2);
        return r0 + (1.f - r0) * pow(1.f - cosine, 5);
    }
}
