package main;

import main.Objects.HittableList;
import main.Util.Ray;
import main.Util.Vec3;

import static main.Util.Vec3.dot;

public class HitRecord {

    // this seems unintuitive, but this class just contains all the data for a hit
    // in practice this actually makes things easier
    public Vec3 collisionPoint;
    public Vec3 normal;
    public Material material;
    public double distanceToObject;
    public boolean front_face;

    public HittableList world;
    //public boolean hitAnything;


    public void set_face_normal(Ray r, Vec3 outward_normal) {
        // Sets the hit record normal vector.
        // NOTE: the parameter `outward_normal` is assumed to have unit length.

        front_face = dot(r.direction(), outward_normal) < 0;
        normal = front_face ? outward_normal : outward_normal.invert();
    }

    public void set(HitRecord t) {
        collisionPoint = t.collisionPoint;
        normal=t.normal;
        material = t.material;
        this.distanceToObject = t.distanceToObject;
        front_face = t.front_face;
    }
}
