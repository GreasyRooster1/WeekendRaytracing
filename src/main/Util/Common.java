package main.Util;

import processing.core.PApplet;

public class Common {
    public static final double infinity = Double.POSITIVE_INFINITY;
    public static final double pi = 3.1415926535897932385d;

// Utility Functions

    public static double degrees_to_radians(double degrees) {
        return degrees * pi / 180.0;
    }

    public static Color color(double r,double g,double b){
        return new Color(r,g,b);
    }
    public static Vec3 vec3(double x,double y,double z){
        return new Vec3(x,y,z);
    }
    public static Point3 point3(double x,double y,double z){
        return new Point3(x,y,z);
    }

    public static double dist(Vec3 u,Vec3 v){
        return PApplet.dist((float) u.x(),(float) u.y(),(float) u.z(),(float) v.x(),(float) v.y(),(float) v.z());
    }

    public static float asNum(boolean b){
        return b ? 1:0;
    }
}
