package main.Util;

import main.Main;

import static java.lang.Math.sqrt;

public class Color extends Vec3{
    public Color() {
        e = new double[]{0,0,0};
    }
    public Color(double e0, double e1, double e2){
        e = new double[]{e0,e1,e2};
    }
    public static double linearToGamma(double linearComponent)
    {
        return sqrt(linearComponent);
    }
    public static void write(int x,int y,Vec3 col, int samples_per_pixel){
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
        Main.app.set(x,y,avgCol.rgb());
    }

    public static void write(int x,int y,int col){
        Main.app.set(x,y,col);
    }
}
