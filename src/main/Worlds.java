package main;

import main.Materials.Dielectric;
import main.Materials.Emission;
import main.Materials.Lambertian;
import main.Materials.Metal;
import main.Util.Color;
import main.Util.TimeKeeper;
import main.Util.Vec3;

import static main.Util.Common.*;
import static main.Util.Vec3.add;
import static main.Util.Vec3.mult;

public class Worlds {
    public static void ballsWorld(){
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
        cam.samplesPerPixel = 5;
        cam.maxDepth = 50;

        cam.vfov = 20;
        cam.lookfrom = point3(13,2,3);
        cam.lookat = point3(0,0,0);
        cam.vup = vec3(0,1,0);

        cam.defocusAngle = 0.6;
        cam.focusDist = 10.0;

        TimeKeeper.startTimeKeep();
        cam.render(world);
    }
    //im a material girl and i am in a:
    public static void materialWorld(){
        HittableList world = new HittableList();

        Material groundMaterial = new Lambertian(color(0.5, 0.5, 0.5));
        Material middleMaterial = new Dielectric(color(1,1,1),1.5f);
        Material rightMaterial = new Lambertian(color(0.4, 0.5, 0.8));
        Material leftMaterial = new Metal(color(1, 0.6, 0.7),0.1f);

        world.add(new Sphere(point3(0,-1000,0), 1000, groundMaterial));

        world.add(new Sphere(point3(0,1,0), 1, middleMaterial));
        world.add(new Sphere(point3(0,1,2), 1, leftMaterial));
        world.add(new Sphere(point3(0,1,-2), 1, rightMaterial));
        Camera cam = new Camera();

        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = Main.app.width;
        cam.samplesPerPixel = 50;
        cam.maxDepth = 50;

        cam.vfov = 20;
        cam.lookfrom = point3(13,7,6);
        cam.lookat = point3(0,0,0);
        cam.vup = vec3(0,1,0);

        cam.defocusAngle = 0.6;
        cam.setFocusObject(new Vec3(0,1,0));

        cam.skyIntensity = 0.5;

        TimeKeeper.startTimeKeep();
        cam.render(world);
    }
    public static void topLight(){
        HittableList world = new HittableList();

        Material groundMaterial = new Lambertian(color(0.5, 0.5, 0.5));
        Material middleMaterial = new Dielectric(color(1,1,1),1.5f);
        Material rightMaterial = new Lambertian(color(0.4, 0.5, 0.8));
        Material leftMaterial = new Metal(color(1, 0.6, 0.7),0.1f);
        Material lightMaterial = new Emission(color(4, 4,4));

        world.add(new Sphere(point3(0,-1000,0), 1000, groundMaterial));

        world.add(new Sphere(point3(0,1,0), 1, middleMaterial));
        world.add(new Sphere(point3(0,1,2), 1, leftMaterial));
        world.add(new Sphere(point3(0,1,-2), 1, rightMaterial));
        world.add(new Sphere(point3(0,3,0), 0.5f, lightMaterial));
        Camera cam = new Camera();

        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = Main.app.width;
        cam.samplesPerPixel = 1000;
        cam.maxDepth = 50;

        cam.vfov = 20;
        cam.lookfrom = point3(13,7,6);
        cam.lookat = point3(0,0,0);
        cam.vup = vec3(0,1,0);

        cam.defocusAngle = 0.6;
        cam.setFocusObject(new Vec3(0,1,0));

        cam.skyIntensity = 0;

        TimeKeeper.startTimeKeep();
        cam.render(world);
    }
}
