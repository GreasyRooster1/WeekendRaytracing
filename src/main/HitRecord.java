package main;

import main.Util.Point3;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Vec3.dot;

public class HitRecord {

    // this seems unintuitive, but this class just contains all the data for a hit
    // in practice this actually makes things easier
    public Vec3 p;
    public Vec3 normal;
    public Material mat;
    public double t;
    public boolean front_face;
    public boolean hitAnything;


    void set_face_normal(Ray r, Vec3 outward_normal) {
        // Sets the hit record normal vector.
        // NOTE: the parameter `outward_normal` is assumed to have unit length.

        front_face = dot(r.direction(), outward_normal) < 0;
        normal = front_face ? outward_normal : outward_normal.invert();
    }
}
