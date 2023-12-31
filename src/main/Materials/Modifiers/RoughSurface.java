package main.Materials.Modifiers;

import main.HitRecord;
import main.Modifier;
import main.Util.Ray;
import main.Util.Vec3;

import java.util.Random;

import static main.Util.Vec3.add;
import static main.Util.Vec3.sub;

public class RoughSurface extends Modifier {
    double fac;
    public RoughSurface(double _fac){
        fac = _fac;
    }
    @Override
    @Deprecated
    public boolean preModify(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 normal = rec.normal;
        double rand = new Random(add(1d, rec.normal.normalized()).div(0.5f).rgb()).nextDouble();
        //rec.normal.set(add())
        return false;
    }
}
