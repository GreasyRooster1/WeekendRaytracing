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

        Material material_ground = new Lambertian(color(0.8, 0.8, 0.0));
        Material material_center = new Lambertian(color(0.1, 0.2, 0.5));
        Material material_left   = new Dielectric(1.5);
        Material material_right  = new Metal(color(0.8, 0.6, 0.2), 0.2);

        world.add(new Sphere(point3( 0.0, -100.5, -1.0), 100.0, material_ground));
        world.add(new Sphere(point3( 0.0,    0.0, -1.0),   0.5, material_center));
        world.add(new Sphere(point3(-1.0,    0.0, -1.0),   0.5, material_left));
        world.add(new Sphere(point3(-1.0,    0.0, -1.0),  -0.4, material_left));
        world.add(new Sphere(point3( 1.0,    0.0, -1.0),   0.5, material_right));

        Camera cam = new Camera();

        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth  = Main.app.width;
        cam.samplesPerPixel=100; // 10 for fast rendering, 100 for antialiasing rendering
        cam.maxDepth=50;

        cam.vfov     = 20;
        cam.lookfrom = vec3(-2,2,1);
        cam.lookat   = vec3(0,0,-1);
        cam.vup      = vec3(0,1,0);

        cam.defocusAngle = 10.0;
        cam.focusDist = 3.5;

        cam.render(world);
    }



}
