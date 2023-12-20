package main.Objects;

import main.Util.Vec3;

public class PointLight{
    public Vec3 position;
    public Vec3 color;
    public PointLight(Vec3 pos, Vec3 col){
        position = pos;
        color = col;
    }
    PointLight(double x,double y,double z){
        position = new Vec3(x,y,z);
    }
}
