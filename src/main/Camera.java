package main;

import main.Objects.Hittable;
import main.ThreadedRendering.ThreadedRendering;
import main.Util.*;

import static java.lang.Math.tan;
import static main.ProgressBar.updateProgressBar;
import static main.Util.Common.*;
import static main.Util.Vec3.*;
import static processing.core.PApplet.dist;

public class Camera {
    public double aspectRatio = Main.aspectRatio;
    public int imageWidth = 400;
    public int samplesPerPixel = 10;
    public int maxDepth = 10;
    public int imageHeight;
    private Vec3 center;
    private Vec3 pixel00_loc;
    private Vec3 pixelDeltaU;
    private Vec3 pixelDeltaV;
    double vfov = 90;
    Vec3 lookfrom = point3(0,0,-1);  // Point camera is looking from
    Vec3 lookat = point3(0,0,0);   // Point camera is looking at
    Vec3 vup = vec3(0,1,0);
    private Vec3 u, v, w;
    public double defocusAngle = 0;  // Variation angle of rays through each pixel
    public double focusDist = 10;
    private Vec3   defocusDiskU;  // Defocus disk horizontal radius
    private Vec3   defocusDiskV;
    public double skyIntensity = 1;

    private void init(){
        imageHeight = (int)(imageWidth / aspectRatio);
        imageHeight = Math.max(imageHeight, 1);

        center = lookfrom;

        // Determine viewport dimensions.
        double theta = degrees_to_radians(vfov);
        double h = tan(theta/2);
        double viewport_height = 2 * h * focusDist;
        double viewport_width = viewport_height * ((double)(imageWidth)/imageHeight);

        // Calculate the vectors across the horizontal and down the vertical viewport edges.
        w = normalize(sub(lookfrom,lookat));
        u = normalize(cross(vup,w));
        v = cross(w,u);

        Vec3 viewport_u = mult(viewport_width,u);
        Vec3 viewport_v = mult(viewport_height,v.invert());

        // Calculate the horizontal and vertical delta vectors from pixel to pixel.
        pixelDeltaU = viewport_u.div(imageWidth);
        pixelDeltaV = viewport_v.div(imageHeight);

        // Calculate the location of the upper left pixel.
        Vec3 viewport_upper_left = sub(sub(sub(center,mult(focusDist,w)),div(viewport_u,2)), div(viewport_v,2));
        pixel00_loc = add(viewport_upper_left,mult(0.5,add(pixelDeltaU,pixelDeltaV)));

        double defocusRadius = focusDist * tan(degrees_to_radians(defocusAngle / 2));
        defocusDiskU = mult(u,defocusRadius);
        defocusDiskV = mult(v,defocusRadius);
    }
    public void render(Hittable world){
        init();
        if(Renderer.threadedRenderingEnabled){
            ThreadedRendering.start(this,world);
            return;
        }

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
        TimeKeeper.endTimeKeep();
    }

    public Vec3 rayColor(Ray r, int depth, Hittable world){
        if(depth<=0){
            return new Color(0,0,0);
        }
        HitRecord rec = new HitRecord();
        if (world.hit(r, new Interval(0.01, infinity), rec)) {
            Ray scattered = new Ray();
            Color attenuation = new Color();
            Vec3 colorFromEmission = rec.mat.emitted(0,0, rec.p);
            if(!rec.mat.scatter(r,rec,attenuation,scattered)){
                return colorFromEmission;
            }
            Vec3 colorFromScatter =  mult(attenuation,rayColor(scattered,depth-1,world));
            return add(colorFromEmission,colorFromScatter);
        }

        //Sky
        Vec3 rayDirection = normalize(r.direction());
        Color start = new Color(0.5, 0.7, 1.0);
        Color end = new Color(1.0, 1.0, 1.0);
        double a = 0.5*(rayDirection.y() + 1.0);
        return mult(Vec3.add(Vec3.mult((1d-a),end),Vec3.mult(a,start)),skyIntensity);
    }

    public Ray getRay(int i, int j){
        // Get a randomly sampled camera ray for the pixel at location i,j.

        Vec3 pixelCenter = add(add(pixel00_loc,mult(i,pixelDeltaU)),mult(j,pixelDeltaV));
        Vec3 pixelSample = add(pixelCenter,pixel_sample_square());

        Vec3 rayOrigin = (defocusAngle <= 0) ? center : defocusDiskSample();
        Vec3 rayDirection = sub(pixelSample,rayOrigin);

        return new Ray(rayOrigin, rayDirection);
    }

    private Vec3 pixel_sample_square() {
        // Returns a random point in the square surrounding a pixel at the origin.
        double px = -0.5 + Main.app.random(1);
        double py = -0.5 + Main.app.random(1);
        return add(mult(px,pixelDeltaU),mult(py,pixelDeltaV));
    }

    Vec3 defocusDiskSample() {
        // Returns a random point in the camera defocus disk.
        Vec3 p = randomInUnitDisk();
        return add(add(center,mult(p.e[0],defocusDiskU)),mult(p.e[1],defocusDiskV));
    }

    public void setFocusObject(Hittable hittable){
        focusDist = dist((float) hittable.center.x(), (float) hittable.center.y(), (float) hittable.center.z(), (float) lookfrom.x(), (float) lookfrom.y(), (float) lookfrom.z());
    }
    public void setFocusObject(Vec3 center){
        focusDist = dist((float) center.x(), (float) center.y(), (float) center.z(), (float) lookfrom.x(), (float) lookfrom.y(), (float) lookfrom.z());
    }

}
