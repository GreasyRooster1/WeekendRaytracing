package main;

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
        Material matteMaterial = new Lambertian(0.7, 0.3, 0.3);
        Material metalMaterial = new Metal(0.8, 0.8, 0.8,0.1);
        Material goldMaterial = new Metal(0.8, 0.6, 0.2,0.9);

        world.add(new Sphere(point3(0,0,-1), 0.5, matteMaterial));
        world.add(new Sphere(point3(1,0,-1), 0.5, goldMaterial));
        world.add(new Sphere(point3(-1,0,-1), 0.5, metalMaterial));
        world.add(new Sphere(point3(0,-100.5,-1), 100, floorMaterial));

        Camera cam = new Camera();

        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth  = Main.app.width;
        cam.samplesPerPixel=400; // 10 for fast rendering, 100 for antialiasing rendering
        cam.maxDepth=50;

        cam.render(world);
    }



}
