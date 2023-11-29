package main;

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
    public boolean hit(Ray ray, double rayTmin, double rayTmax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hit_anything = false;
        double closest_so_far = rayTmax;

        for (Hittable object : objects) {
            if (object.hit(ray, rayTmin, closest_so_far, tempRec)) {
                hit_anything = true;
                closest_so_far = tempRec.t;
                rec = tempRec;
            }
        }

        return hit_anything;
    }
}
