package main;

import main.Materials.Dielectric;
import main.Materials.Lambertian;
import main.Materials.Metal;
import main.Util.*;
import processing.core.PApplet;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static main.Util.Common.*;
import static main.Util.Vec3.*;
import static processing.core.PApplet.println;

public class Renderer {
    public static PApplet app;
    public static void setup() {
        app = Main.app;
    }
    public static void render(){
        HittableList world = new HittableList();

        Material floorMaterial = new Lambertian(0.8, 0.8, 0.0);
        Material centerMaterial = new Lambertian(0.85, 0.3, 0.5);
        Material leftMaterial = new Dielectric(1d);
        //Material rightMaterial = new Metal(0.8, 0.6, 0.2,0.9);
        Material rightMaterial = new Metal(1,1, 1,0.9);

        world.add(new Sphere(point3(0,0,-1), 0.5, centerMaterial));
        world.add(new Sphere(point3(1,0,-1), 0.5, leftMaterial));
        world.add(new Sphere(point3(-1,0,-1), 0.5, rightMaterial));
        world.add(new Sphere(point3(0,-100.5,-1), 100, floorMaterial));

        Camera cam = new Camera();

        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth  = Main.app.width;
        cam.samplesPerPixel=100; // 10 for fast rendering, 100 for antialiasing rendering
        cam.maxDepth=5;

        cam.render(world);
    }



}
