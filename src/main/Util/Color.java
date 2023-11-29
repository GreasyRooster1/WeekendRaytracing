package main.Util;

import main.Main;

public class Color extends Vec3{
    public Color() {
        e = new double[]{0,0,0};
    }
    public Color(double e0, double e1, double e2){
        e = new double[]{e0,e1,e2};
    }
    public static void write(int x,int y,Vec3 col){
        Main.app.set(x,y,col.rgb());
    }
}
