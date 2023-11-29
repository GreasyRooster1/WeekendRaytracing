package main.Util;


/*---------------------------------------
translating from c++ to java is a great way to learn c++
this seems more intuitive now
-----------------------------------------*/


import main.Main;
import main.Renderer;

import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;

public class Vec3 {
    double[] e;

    public Vec3() {
        e = new double[]{0,0,0};
    }
    public Vec3(double e0, double e1, double e2){
        e = new double[]{e0,e1,e2};
    }

    public double x(){
        return e[0];
    }
    public double y(){
        return e[1];
    }
    public double z(){
        return e[2];
    }

    Vec3 invert(){
        return new Vec3(-e[0], -e[1], -e[2]);
    }
    public Vec3 sub(Vec3 v){
        return new Vec3(e[0]-v.x(), e[1]-v.y(), e[2]-v.z());
    }
    double getT(int i){
        return e[i];
    }

    public Vec3 add(Vec3 v) {
        e[0] += v.e[0];
        e[1] += v.e[1];
        e[2] += v.e[2];
        return this;
    }

    public Vec3 mult(double t) {
        e[0] *= t;
        e[1] *= t;
        e[2] *= t;
        return this;
    }

    public Vec3 div(double t) {
        return new Vec3(e[0]/t,e[1]/t,e[2]/t);
    }

    double length() {
        return sqrt(length_squared());
    }

    public double length_squared(){
        return e[0]*e[0] + e[1]*e[1] + e[2]*e[2];
    }
    public static Vec3 add(Vec3 u,Vec3 v) {
        return new Vec3(u.e[0] + v.e[0], u.e[1] + v.e[1], u.e[2] + v.e[2]);
    }

    public static Vec3 sub(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] - v.e[0], u.e[1] - v.e[1], u.e[2] - v.e[2]);
    }

    public static Vec3 mult(Vec3 u,Vec3 v) {
        return new Vec3(u.e[0] * v.e[0], u.e[1] * v.e[1], u.e[2] * v.e[2]);
    }

    public static Vec3 mult(double t, Vec3 v) {
        return new Vec3(t*v.e[0], t*v.e[1], t*v.e[2]);
    }

    public static Vec3 mult(Vec3 v, double t) {
        return mult(t,v);
    }

    public static Vec3 div(Vec3 v, double t) {
        return new Vec3(v.e[0]/t,v.e[1]/t,v.e[2]/t);
    }

    public static double dot(Vec3 u,Vec3 v) {
        return u.e[0] * v.e[0]
                + u.e[1] * v.e[1]
                + u.e[2] * v.e[2];
    }

    public static Vec3 cross(Vec3 u,Vec3 v) {
        return new Vec3(u.e[1] * v.e[2] - u.e[2] * v.e[1],
                u.e[2] * v.e[0] - u.e[0] * v.e[2],
                u.e[0] * v.e[1] - u.e[1] * v.e[0]);
    }

    public static Vec3 unit_vector(Vec3 v) {
        return normalize(v);
    }
    public static Vec3 normalize(Vec3 v) {
        return div(v,v.length());
    }

    public int rgb() {
        return Main.app.color((int) (e[0]*255d),(int) (e[1]*255d),(int) (e[2]*255d));
    }
}
