package main.Objects;

import main.HitRecord;
import main.Material;
import main.Util.Interval;
import main.Util.Ray;
import main.Util.Vec3;
import processing.core.PApplet;

import static java.lang.Math.sqrt;
import static main.Util.Vec3.*;
import static processing.core.PApplet.dist;

public class Sphere extends Hittable{
    //this form of programming reminds me of mvcode

    private double radius;
    private Material mat;
    public Sphere(Vec3 _center, double _radius, Material _material){
        center = _center;
        radius = _radius;
        mat = _material;
    }

    //Hey, I actually ended up using the quadratic formula, Ms. Smith!
    @Override
    public boolean hit(Ray ray, Interval interval, HitRecord rec) {
        Vec3 oc = sub(ray.origin(),center);

        // using the formulas a=b⋅b, b=2b⋅(A−C), c=(A−C)⋅(A−C)−r2 we find the discriminant(s) for the quadratic, letting us find points of collision
        // update: simplified this formula
        double a = ray.direction().length_squared();
        double half_b = dot(oc, ray.direction());
        double c = oc.length_squared() - radius*radius;

        double discriminant = half_b*half_b - a*c;
        if (discriminant < 0) {
            //rec.hitAnything = false;
            return false;
        }
        double sqrtd = sqrt(discriminant);

        // Find the nearest root that lies in the acceptable range.
        double root = (-half_b - sqrtd) / a;
        if (!interval.surrounds(root)) {
            root = (-half_b + sqrtd) / a;
            if (!interval.surrounds(root)){
                //rec.hitAnything = false;
                return false;
            }
        }
//        Vec3 collisionPoint = ray.at(root);
//        if(dist(collisionPoint.x(),collisionPoint.y(),collisionPoint.z(),center.x(),center.y(),center.z())<radius-0.0000001d){
//            return false;
//        }

        rec.distanceToObject = root;
        rec.collisionPoint = ray.at(rec.distanceToObject);
        Vec3 outward_normal = div(sub(rec.collisionPoint,center),radius);
        rec.set_face_normal(ray, outward_normal);
        rec.normal = div(sub(rec.collisionPoint,center),radius);
        rec.material = mat;
        return true;
    }

    private float dist(double x, double y, double z, double x1, double y1, double z1) {
        return PApplet.dist((float) x,(float) y,(float) z,(float) x1,(float) y1,(float) z1);
    }
}
