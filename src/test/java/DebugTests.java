//package test.java;
//
//import main.Util.Vec3;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.Random;
//
//import static java.lang.Math.*;
//import static main.Util.Vec3.*;
//import static processing.core.PApplet.norm;
//import static processing.core.PApplet.println;
//
//public class DebugTests {
//    @Test
//    public void testAdd(){
//        Vec3 v1_0 = new Vec3(0,0,0);
//        Vec3 v1_1 = new Vec3(1,2,3);
//        Vec3 v1_2 = v1_0.add(v1_1);
//        Vec3 v1_3 = v1_1.add(v1_0);
//
//        Assert.assertEquals(v1_2,new Vec3(1,2,3));
//        Assert.assertEquals(v1_3,new Vec3(1,2,3));
//        Assert.assertEquals(v1_3,v1_2);
//
//        Vec3 v2_0 = new Vec3(-1,-2,-3);
//        Vec3 v2_1 = new Vec3(1,2,3);
//        Vec3 v2_2 = v1_0.add(v1_1);
//        Vec3 v2_3 = v1_1.add(v1_0);
//
//        Assert.assertEquals(v2_2,new Vec3(0,0,0));
//        Assert.assertEquals(v2_3,new Vec3(0,0,0));
//        Assert.assertEquals(v2_3,v2_2);
//    }
//
//    @Test
//    public void testRefract(){
//        //for (int i = 0; i < 9999; i++) {
//        //Random r = new Random();
//        //Vec3 uv = new Vec3(r.nextDouble(), r.nextDouble(), r.nextDouble());
//        //Vec3 n = new Vec3(r.nextDouble(), r.nextDouble(), r.nextDouble());
//        //[0.7310258042274864, 0.33948373727270553, -0.5919054533292571]
//        //[-0.08353942563887906, 0.8899921102066161, 0.44825797051909855]
//        Vec3 uv = normalize(new Vec3(1,1,0));
//        Vec3 n = new Vec3(0,-1,0);
//        double etaiOverEtat = 1;
//        double cosTheta = min(dot(uv.invert(), n), 1.0);
//        Vec3 rOutPerp = mult(etaiOverEtat, add(uv, mult(cosTheta, n)));
//        Vec3 rOutParallel = mult(-sqrt(abs(1.0 - rOutPerp.length_squared())), n);
//        Vec3 out = add(rOutPerp, rOutParallel);
//
//        println("uv: ", Arrays.toString(uv.e));
//        println("n: ", Arrays.toString(n.e));
//        println("cosTheta: ", cosTheta);
//        println("rOutPerp: ", Arrays.toString(rOutPerp.e));
//        println("rOutParallel: ", Arrays.toString(rOutParallel.e));
//        println("out: ", Arrays.toString(out.e));
//        Assert.assertArrayEquals(out.e, uv.e, 0);
//        //}
//    }
//}
