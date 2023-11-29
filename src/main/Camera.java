package main;

import main.Util.*;

import static main.ProgressBar.updateProgressBar;
import static main.Util.Common.*;
import static main.Util.Vec3.*;

public class Camera {
    public double aspectRatio = 1d;
    public int imageWidth = 400;
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
                Vec3 pixel_center = add(add(pixel00_loc,mult(i,pixelDeltaU)),mult(j,pixelDeltaV));
                Vec3 ray_direction = pixel_center.sub(center);
                Ray r = new Ray(center, ray_direction);


                Vec3 pixelColor = rayColor(r,world);
                Color.write(i,j,pixelColor);

                iterationCount++;
                updateProgressBar(iterationCount,imageWidth*imageHeight);
            }
        }
    }

    public Vec3 rayColor(Ray r, Hittable world){
        //Red sphere
        HitRecord rec = new HitRecord();
        rec = world.hit(r, new Interval(0, infinity), rec);
        if (rec.hitAnything) {
            return mult(0.5,add(rec.normal,color(1,1,1)));
        }

        //Sky
        Vec3 rayDirection = normalize(r.direction());
        Color start = new Color(0.5, 0.7, 1.0);
        Color end = new Color(1.0, 1.0, 1.0);
        double a = 0.5*(rayDirection.y() + 1.0);
        return Vec3.add(Vec3.mult((1d-a),end),Vec3.mult(a,start));
    }

}
