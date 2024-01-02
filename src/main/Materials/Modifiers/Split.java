package main.Materials.Modifiers;

import main.HitRecord;
import main.Material;
import main.Modifier;
import main.Objects.Hittable;
import main.Util.Interval;
import main.Util.Ray;
import main.Util.Vec3;

import static java.lang.Math.pow;
import static main.Util.Vec3.*;

public class Split extends Modifier {
    Vec3 split;
    Material material;
    Split(Vec3 s){
        split = s;
    }
    public Split(Material mat){
        material = mat;
    }
    @Override
    public boolean preModify(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        if(rec.obj!=null) {
            if (rec.obj.center.y() < rec.collisionPoint.y()) {
                material.scatterWithModifiers(rIn, rec, attenuation, scattered);
                return true;
            }
            return false;
        }
        return false;
    }
}
