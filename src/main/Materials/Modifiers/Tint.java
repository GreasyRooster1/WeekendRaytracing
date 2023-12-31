package main.Materials.Modifiers;

import main.HitRecord;
import main.Modifier;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Common.vec3;
import static main.Util.Vec3.mult;

public class Tint extends Modifier {
    public double r,g,b;
    public Tint(double _r, double _g, double _b){
        r=_r;
        g=_g;
        b=_b;
    }
    @Override
    public void modify(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        attenuation.set(mult(attenuation,vec3(r,g,b)));
    }
}
