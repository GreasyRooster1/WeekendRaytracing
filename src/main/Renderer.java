package main;

import main.Util.Color;
import main.Util.Point3;
import main.Util.Ray;
import main.Util.Vec3;
import processing.core.PApplet;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static main.Util.Vec3.*;
import static processing.core.PApplet.println;

public class Renderer {
    public static int width,height;
    public static double viewWidth,viewHeight;
    public static PApplet app;
    public static void setup() {
        app = Main.app;
    }

    //Hey I actually ended up using the quadratic formula, Ms. Smith!
    public static double hitSphere(Vec3 center,double radius, Ray ray){
        Vec3 oc = sub(ray.origin(),center);

        // using the formulas a=b⋅b, b=2b⋅(A−C), c=(A−C)⋅(A−C)−r2 we find the discriminant(s) for the quadratic, letting us find points of collision
        // update: simplified this formula
        double a = ray.direction().length_squared();
        double half_b = dot(oc, ray.direction());
        double c = oc.length_squared() - radius*radius;
        double discriminant = half_b*half_b - a*c;

        if (discriminant < 0) {
            return -1.0;
        } else {
            return (-half_b - sqrt(discriminant) ) / a;
        }
    }

    public static Vec3 rayColor(Ray r){
        //Red sphere
        double t = hitSphere(new Vec3(0,0,-1), 0.5d, r);
        if (t > 0.0) {
            //outward normal (C-P=N)
            Vec3 N = normalize(sub(r.at(t),new Vec3(0,0,-1)));
            return mult(0.5d,new Vec3(N.x()+1, N.y()+1, N.z()+1));
        }

        //Sky
        Vec3 rayDirection = normalize(r.direction());
        Color start = new Color(0.5, 0.7, 1.0);
        Color end = new Color(1.0, 1.0, 1.0);
        double a = 0.5*(rayDirection.y() + 1.0);
        return Vec3.add(Vec3.mult((1d-a),end),Vec3.mult(a,start));
    }

    public static void render(){
        double aspect_ratio = 1;    //16d/9d; causes image stretching for some reason
        width = app.width;

        // Calculate the image height, and ensure that it's at least 1.
        height = (int)(width / aspect_ratio);
        height = Math.max(height, 1);

        // Camera

        double focal_length = 1.0;
        viewHeight = 2.0;
        viewWidth = viewHeight * (double) (width/height);
        Point3 camera_center = new Point3(0, 0, 0);

        // Calculate the vectors across the horizontal and down the vertical viewport edges.
        Vec3 viewport_u = new Vec3(viewWidth, 0, 0);
        Vec3 viewport_v = new Vec3(0, -viewHeight, 0);

        // Calculate the horizontal and vertical delta vectors from pixel to pixel.
        Vec3 pixel_delta_u = viewport_u.div(width);
        Vec3 pixel_delta_v = viewport_v.div(height);

        // Calculate the location of the upper left pixel.

        Vec3 viewport_upper_left = sub(sub(sub(camera_center, new Vec3(0, 0, focal_length)),(viewport_u.div(2))),(viewport_v.div(2)));
        Vec3 pixel00_loc = add(viewport_upper_left,mult(0.5,add(pixel_delta_u,pixel_delta_v)));

        int iterationCount = 0;
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                Vec3 pixel_center = add(add(pixel00_loc,mult(i,pixel_delta_u)),mult(j,pixel_delta_v));
                Vec3 ray_direction = pixel_center.sub(camera_center);
                Ray r = new Ray(camera_center, ray_direction);


                Vec3 pixelColor = rayColor(r);
                Color.write(i,j,pixelColor);

                iterationCount++;
                updateProgressBar(iterationCount);
            }
        }
    }


    public static void updateProgressBar(int i){
        Main.progressBar.updateBar((height*width/i)*100);
    }
}
