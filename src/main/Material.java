package main;

import jdk.jfr.Experimental;
import main.Util.Ray;
import main.Util.Vec3;

import static processing.core.PApplet.append;

public abstract class Material {
    Modifier[] modifiers = {};
    public Vec3 emitted(double u, double v, Vec3 p){
        return new Vec3();
    }
    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered){
        return false;
    }
    public boolean scatterWithModifiers(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered){
        for(Modifier m:modifiers){
            if(m.preModify(rIn,rec,attenuation,scattered)){
                return true;
            }
        }
        boolean out = scatter(rIn,rec,attenuation,scattered);
        for(Modifier m:modifiers){
            if(m.postModify(rIn,rec,attenuation,scattered)){
                return true;
            }
        }
        return out;
    }
    public Vec3 emittedWithModifiers(double u, double v, Vec3 p){
        return emitted(u,v,p);
    }
    public Material addModifier(Modifier modifier){
        modifiers = (Modifier[]) append(modifiers,modifier);
        return this;
    }
}
