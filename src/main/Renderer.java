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
    public static boolean threadedRenderingEnabled;

    public static void setup() {
        app = Main.app;
    }
    public static void render(){
        threadedRenderingEnabled = false;
        Worlds.multiColorLight();
    }



}
