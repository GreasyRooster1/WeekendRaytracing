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

        Material ground_material = new Lambertian(color(0.5, 0.5, 0.5));
        world.add(new Sphere(point3(0,-1000,0), 1000, ground_material));

        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                double choose_mat = Main.app.random(1);
                Vec3 center = new Vec3(a + 0.9*Main.app.random(1), 0.2, b + 0.9*Main.app.random(1));

                if (add(center, new Vec3(4, 0.2, 0)).length() > 0.9) {
                    Material sphereMaterial;

                    if (choose_mat < 0.8) {
                        // diffuse
                        Vec3 albedo = mult(Color.random(),Color.random());
                        sphereMaterial = new Lambertian(albedo);
                        world.add(new Sphere( center, 0.2, sphereMaterial));
                    } else if (choose_mat < 0.95) {
                        // metal
                        Vec3 albedo = Color.random(0.5f, 1);
                        double fuzz = Main.app.random(0, 0.5f);
                        sphereMaterial = new Metal(albedo, fuzz);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    } else {
                        // glass
                        sphereMaterial = new Dielectric(1.5);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    }
                }
            }
        }

        Material material1 = new Dielectric(1.5);
        world.add(new Sphere(point3(0, 1, 0), 1.0, material1));

        Material material2 = new Lambertian(color(0.4, 0.2, 0.1));
        world.add(new Sphere(point3(-4, 1, 0), 1.0, material2));

        Material material3 = new Metal(color(0.7, 0.6, 0.5), 0.0);
        world.add(new Sphere(point3(4, 1, 0), 1.0, material3));

        Camera cam = new Camera();

        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = Main.app.width;
        cam.samplesPerPixel = 10;
        cam.maxDepth = 50;

        cam.vfov = 20;
        cam.lookfrom = point3(13,2,3);
        cam.lookat = point3(0,0,0);
        cam.vup = vec3(0,1,0);

        cam.defocusAngle = 0.6;
        cam.focusDist = 10.0;

        cam.render(world);
    }



}
