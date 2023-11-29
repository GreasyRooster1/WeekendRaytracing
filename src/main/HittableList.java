package main;

import main.Util.Interval;
import main.Util.Ray;

import static processing.core.PApplet.append;

public class HittableList extends Hittable{
    //is every object in the scene combined, so we only need to evaluate a single hit in the rayColor function
    public Hittable[] objects = {};

    public void clear(){
        objects = new Hittable[0];
    }
    public void add(Hittable hittable){
        objects = (Hittable[]) append(objects,hittable);
    }

    //checks if anything is being hit
    @Override
    public HitRecord hit(Ray ray, Interval interval, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        double closest_so_far = interval.max;

        for (Hittable object : objects) {
            if (object.hit(ray, new Interval(interval.min, closest_so_far), tempRec).hitAnything) {
                rec.hitAnything = true;
                closest_so_far = tempRec.t;
                rec = tempRec;
            }
        }
        return rec;
    }
}
