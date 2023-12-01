package main.Materials;

import main.HitRecord;
import main.Material;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Vec3.*;

public class Metal extends Material {
    private Vec3 albedo;
    public double fuzz = 0;
    public Metal(Vec3 _col){
        albedo = _col;
    }
    public Metal(Vec3 _col,double _fuzz){
        albedo = _col;
        fuzz = _fuzz;
    }
    public Metal(double r, double g, double b) {
        albedo = new Vec3(r,g,b);
    }
    public Metal(double r, double g, double b,double _fuzz) {
        albedo = new Vec3(r,g,b);
        fuzz = _fuzz;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 reflected = reflect(normalize(rIn.direction()),rec.normal);
        scattered.set(new Ray(rec.p,add(reflected,mult(fuzz,randomNormalizedVector()))));
        attenuation.set(albedo);
        return (dot(scattered.direction(), rec.normal) > 0);
    }
}
