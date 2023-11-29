package main;

import main.Util.Point3;
import main.Util.Ray;
import main.Util.Vec3;

import static java.lang.Math.sqrt;
import static main.Util.Vec3.*;

public class Sphere extends Hittable{
    //this form of programming reminds me of mvcode
    private Point3 center;
    private double radius;
    Sphere(Point3 _center, double _radius){
        center = _center;
        radius = _radius;
    }
    //Hey I actually ended up using the quadratic formula, Ms. Smith!
    @Override
    public HitRecord hit(Ray ray, double rayTmin, double rayTmax, HitRecord rec) {
        Vec3 oc = sub(ray.origin(),center);

        // using the formulas a=b⋅b, b=2b⋅(A−C), c=(A−C)⋅(A−C)−r2 we find the discriminant(s) for the quadratic, letting us find points of collision
        // update: simplified this formula
        double a = ray.direction().length_squared();
        double half_b = dot(oc, ray.direction());
        double c = oc.length_squared() - radius*radius;

        double discriminant = half_b*half_b - a*c;
        if (discriminant < 0) {
            //rec.hitAnything = false;
            return rec;
        }
        double sqrtd = sqrt(discriminant);

        // Find the nearest root that lies in the acceptable range.
        double root = (-half_b - sqrtd) / a;
        if (root <= rayTmin || rayTmax <= root) {
            root = (-half_b + sqrtd) / a;
            if (root <= rayTmin || rayTmax <= root){
                //rec.hitAnything = false;
                return rec;
            }
        }

        rec.t = root;
        rec.p = ray.at(rec.t);
        Vec3 outward_normal = div(sub(rec.p,center),radius);
        rec.set_face_normal(ray, outward_normal);
        rec.normal = div(sub(rec.p,center),radius);

        rec.hitAnything = true;
        return rec;
    }
}
