package main.Util;


/*---------------------------------------
translating from c++ to java is a great way to learn c++
this seems more intuitive now
-----------------------------------------*/


import main.Main;
import main.Renderer;
import processing.core.PApplet;

import static java.lang.Math.*;
import static main.Util.Common.vec3;

public class Vec3 {
    public double[] e;

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

    public Vec3 invert(){
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

    public double length() {
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
    public static Vec3 sub(Vec3 u, double v) {
        return new Vec3(u.e[0] - v, u.e[1] - v, u.e[2] - v);
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
    public static Vec3 add(double i,Vec3 v) {
        return new Vec3(v.x()+i,v.y()+i,v.z()+i);
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
    public Vec3 normalized() {
        return normalize(this);
    }

    public int rgb() {
        return Main.app.color((int) (e[0]*255d),(int) (e[1]*255d),(int) (e[2]*255d));
    }

    public static Vec3 random() {
        return new Vec3(Main.app.random(1),Main.app.random(1),Main.app.random(1));
    }

    public static Vec3 random(float min, float max) {
        return new Vec3(Main.app.random(min,max),Main.app.random(min,max),Main.app.random(min,max));
    }
    public static Vec3 randomInSphere(){
        while (true) {
            Vec3 p = Vec3.random(-1,1);
            if (p.length_squared() < 1) {
                return p;
            }
        }
    }
    public static Vec3 randomNormalizedVector() {
        return unit_vector(randomInSphere());
    }

    public static Vec3 randomOnHemisphere(Vec3 normal) {
        Vec3 on_unit_sphere = randomNormalizedVector();
        if (dot(on_unit_sphere, normal) > 0.0) { // In the same hemisphere as the normal
            return on_unit_sphere;
        } else {
            return on_unit_sphere.invert();
        }
    }

    public Vec3 set(Vec3 vec){
        e[0]=vec.x();
        e[1]=vec.y();
        e[2]=vec.z();
        return this;
    }

    public boolean nearZero() {
        // Return true if the vector is close to zero in all dimensions.
        double s = 1e-8;
        return (abs(e[0]) < s) && (abs(e[1]) < s) && (abs(e[2]) < s);
    }

    public static Vec3 reflect(Vec3 v,Vec3 normal){
        return sub(v,mult(2*dot(v,normal),normal));
    }

    public static Vec3 refract(Vec3 uv,Vec3 n,double etaiOverEtat){
        double cosTheta = min(dot(uv.invert(), n), 1.0);
        Vec3 rOutPerp =  mult(etaiOverEtat,add(uv,mult(cosTheta,n)));
        Vec3 rOutParallel = mult(-sqrt(abs(1.0 - rOutPerp.length_squared())),n);
        return add(rOutPerp,rOutParallel);
    }

    public static Vec3 randomInUnitDisk() {
        while (true) {
            Vec3 p = vec3(Main.app.random(-1,1), Main.app.random(-1,1), 0);
            if (p.length_squared() < 1)
                return p;
        }
    }

    public static Vec3 lerp(Vec3 a,Vec3 b,float t){
        return new Vec3(PApplet.lerp((float) a.x(), (float) b.x(),t),PApplet.lerp((float) a.y(), (float) b.y(),t),PApplet.lerp((float) a.z(), (float) b.z(),t));
    }
}
