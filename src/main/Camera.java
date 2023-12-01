package main;

import main.Util.*;

import static main.ProgressBar.updateProgressBar;
import static main.Util.Common.*;
import static main.Util.Vec3.*;

public class Camera {
    public double aspectRatio = Main.aspectRatio;
    public int imageWidth = 400;
    public int samplesPerPixel = 10;
    public int maxDepth = 10;
    private int imageHeight;
    private Point3 center;
    private Vec3 pixel00_loc;
    private Vec3 pixelDeltaU;
    private Vec3 pixelDeltaV;

    private void init(){
        imageHeight = (int)(imageWidth / aspectRatio);
        imageHeight = Math.max(imageHeight, 1);

        center = point3(0, 0, 0);

        // Determine viewport dimensions.
        double focal_length = 1.0;
        double viewport_height = 2.0;
        double viewport_width = viewport_height * ((double)(imageWidth)/imageHeight);

        // Calculate the vectors across the horizontal and down the vertical viewport edges.
        Vec3 viewport_u = vec3(viewport_width, 0, 0);
        Vec3 viewport_v = vec3(0, -viewport_height, 0);

        // Calculate the horizontal and vertical delta vectors from pixel to pixel.
        pixelDeltaU = viewport_u.div(imageWidth);
        pixelDeltaV = viewport_v.div(imageHeight);

        // Calculate the location of the upper left pixel.
        Vec3 viewport_upper_left = sub(sub(sub(center, new Vec3(0, 0, focal_length)),(viewport_u.div(2))),(viewport_v.div(2)));
        pixel00_loc = add(viewport_upper_left,mult(0.5,add(pixelDeltaU,pixelDeltaV)));
    }
    public void render(Hittable world){
        init();

        int iterationCount = 0;
        for (int j = 0; j < imageHeight; ++j) {
            for (int i = 0; i < imageWidth; ++i) {
                Vec3 pixelColor = new Color(0,0,0);
                for (int sample = 0; sample < samplesPerPixel; ++sample) {
                    Ray r = getRay(i, j);
                    pixelColor= pixelColor.add(rayColor(r,maxDepth, world));
                }
                Color.write(i,j,pixelColor,samplesPerPixel);
                iterationCount++;
                updateProgressBar(iterationCount,imageWidth*imageHeight);
            }
        }
    }

    private Vec3 rayColor(Ray r, int depth, Hittable world){
        if(depth<=0){
            return new Color(0,0,0);
        }
        HitRecord rec = new HitRecord();
        rec = world.hit(r, new Interval(0.0001, infinity), rec);
        if (rec.hitAnything) {
            Ray scattered = new Ray();
            Color attenuation = new Color();
            if(rec.mat.scatter(r,rec,attenuation,scattered)){
                return mult(attenuation,rayColor(scattered,depth-1,world));
            }
            return new Vec3(0,0,0);
        }

        //Sky
        Vec3 rayDirection = normalize(r.direction());
        Color start = new Color(0.5, 0.7, 1.0);
        Color end = new Color(1.0, 1.0, 1.0);
        double a = 0.5*(rayDirection.y() + 1.0);
        return Vec3.add(Vec3.mult((1d-a),end),Vec3.mult(a,start));
    }

    private Ray getRay(int i, int j){
        // Get a randomly sampled camera ray for the pixel at location i,j.

        Vec3 pixelCenter = add(add(pixel00_loc,mult(i,pixelDeltaU)),mult(j,pixelDeltaV));
        Vec3 pixelSample = add(pixelCenter,pixel_sample_square());

        Vec3 rayOrigin = center;
        Vec3 rayDirection = sub(pixelSample,rayOrigin);

        return new Ray((Point3) rayOrigin, rayDirection);
    }

    private Vec3 pixel_sample_square() {
        // Returns a random point in the square surrounding a pixel at the origin.
        double px = -0.5 + Main.app.random(1);
        double py = -0.5 + Main.app.random(1);
        return add(mult(px,pixelDeltaU),mult(py,pixelDeltaV));
    }

}
