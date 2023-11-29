package main;

import main.Util.Point3;
import main.Util.Ray;
import main.Util.Vec3;

import static java.lang.Math.sqrt;
import static main.Util.Vec3.*;

public class Sphere extends Hittable{
    private Point3 center;
    private double radius;
    Sphere(Point3 _center, double _radius){
        center = _center;
        radius = _radius;
    }
    //this form of programming reminds me of mvcode
    @Override
    public boolean hit(Ray ray, double rayTmin, double rayTmax, HitRecord rec) {
        Vec3 oc = sub(ray.origin(),center);
        double a = ray.direction().length_squared();
        double half_b = dot(oc, ray.direction());
        double c = oc.length_squared() - radius*radius;

        double discriminant = half_b*half_b - a*c;
        if (discriminant < 0) return false;
        double sqrtd = sqrt(discriminant);

        // Find the nearest root that lies in the acceptable range.
        double root = (-half_b - sqrtd) / a;
        if (root <= rayTmin || rayTmax <= root) {
            root = (-half_b + sqrtd) / a;
            if (root <= rayTmin || rayTmax <= root)
                return false;
        }

        rec.t = root;
        rec.p = (Point3) ray.at(rec.t);
        Vec3 outward_normal = div(sub(rec.p,center),radius);
        rec.set_face_normal(ray, outward_normal);
        rec.normal = div(sub(rec.p,center),radius);

        return true;
    }
}
