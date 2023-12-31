package main.Materials.Modifiers;

import main.HitRecord;
import main.Main;
import main.Material;
import main.Modifier;
import main.Util.Ray;
import main.Util.Vec3;

public class Mix extends Modifier {
    Material material;
    double fac;
    public Mix(Material m, double f){
        material = m;
        fac=f;
    }

    @Override
    public boolean preModify(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        if(Main.app.random(0,1)>=fac){
            material.scatterWithModifiers(rIn,rec,attenuation,scattered);
            return true;
        }
        return false;
    }
}
