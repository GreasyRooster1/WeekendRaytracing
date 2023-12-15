package main.Materials;

import main.Material;
import main.Util.Vec3;

public class Emission extends Material {
    public Vec3 color = new Vec3(1,1,1);
    public Emission(Vec3 _col){
        color = _col;
    }
    @Override
    public Vec3 emitted(double u, double v, Vec3 p) {
        return color;
    }
}
