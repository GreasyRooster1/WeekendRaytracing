package main.Objects;

import main.HitRecord;
import main.Util.Interval;
import main.Util.Ray;

import static processing.core.PApplet.append;

public class HittableList extends Hittable{
    //is every object in the scene combined, so we only need to evaluate a single hit in the rayColor function
    public Hittable[] objects = {};
    public PointLight[] lights = {};

    public void clear(){
        objects = new Hittable[0];
    }
    public void add(Hittable hittable){
        objects = (Hittable[]) append(objects,hittable);
    }
    public void addLight(PointLight light){
        lights = (PointLight[]) append(lights,light);
    }

    //checks if anything is being hit
    @Override
    public boolean hit(Ray ray, Interval interval, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closest_so_far = interval.max;

        for (Hittable object : objects) {
            if (object.hit(ray, new Interval(interval.min, closest_so_far), tempRec)) {
                hitAnything = true;
                closest_so_far = tempRec.t;
                rec.set(tempRec);
            }
        }
        return hitAnything;
    }
    public boolean hitSomething(Ray ray, Interval interval) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closest_so_far = interval.max;

        for (Hittable object : objects) {
            if (object.hit(ray, new Interval(interval.min, closest_so_far), tempRec)) {
                hitAnything = true;
                closest_so_far = tempRec.t;
            }
        }
        return hitAnything;
    }
}
