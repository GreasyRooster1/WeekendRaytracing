package main;

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

        world.add(new Sphere(point3(0,0,-1), 0.5));
        world.add(new Sphere(point3(0,-100.5,-1), 100));

        Camera cam = new Camera();

        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth  = Main.app.width;
        cam.samplesPerPixel=100; // 10 for fast rendering, 100 for antialiasing rendering

        cam.render(world);
    }



}
