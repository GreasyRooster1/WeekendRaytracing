package main.ThreadedRendering;

import main.Main;
import main.Util.Color;
import main.Util.Vec3;

import java.util.Arrays;

import static main.Util.Color.linearToGamma;
import static processing.core.PApplet.floor;
import static processing.core.PApplet.println;

public class PixelCache {
    public static int[] image = {};

    public static void setup(int w, int h){
        image = new int[w*h];

        //set to black
        Arrays.fill(image, 0);
    }

    public static void write(int x, int y, Vec3 col, int samples_per_pixel){
        double r = col.x();
        double g = col.y();
        double b = col.z();

        // Divide the color by the number of samples.
        double scale = 1.0 / samples_per_pixel;
        r *= scale;
        g *= scale;
        b *= scale;
        r=linearToGamma(r);
        g = linearToGamma(g);
        b = linearToGamma(b);
        Vec3 avgCol = new Vec3(r,g,b);
        image[x+y* Main.app.width] = avgCol.rgb();
    }

    public static void pushToDisplay(){
        Main.app.loadPixels();
        for (int i = 0; i < image.length; i++) {
            //println(i%Main.app.width,floor((float) i /Main.app.height));
            //Color.write(i%Main.app.width,floor((float) i /Main.app.height),image[i]);
            Main.app.pixels[i]=image[i];
        }
        Main.app.updatePixels();
        Main.app.loop();
    }
}
