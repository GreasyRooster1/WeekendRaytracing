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
//
//    @Test
//    public void refractExpectedTest(){
//        Vec3 uv = new Vec3(1,0,0);
//        Vec3 n = new Vec3(0,1,0);
//        double etaiOverEtat = 1;
//
//        double cosTheta = min(dot(uv.invert(), n), 1.0);
//        Vec3 rOutPerp =  mult(etaiOverEtat,add(uv,mult(cosTheta,n)));
//        Vec3 rOutParallel = mult(-sqrt(abs(1.0 - rOutPerp.length_squared())),n);
//
//        Assert.assertArrayEquals(add(rOutPerp,rOutParallel).e,uv.e,0);
//    }
//    @Test
//    public void refractActualTest(){
//        Vec3 uv = new Vec3(1,0,0);
//        Vec3 n = new Vec3(0,1,0);
//        double etaiOverEtat = 1;
//
//        Assert.assertArrayEquals(refract(uv,n,etaiOverEtat).e,uv.e,0);
//    }
//    @Test
//    public void refractActualMassTest(){
//        for (int i = 0; i < 10_000; i++) {
//            Vec3 uv = new Vec3(new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble());
//            Vec3 n = new Vec3(new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble());
//            double etaiOverEtat = 1;
//
//            Assert.assertArrayEquals(refract(uv,n,etaiOverEtat).e,uv.e,0);
//        }
//    }
//    @Test
//    public void refractExpectedMassTest(){
//        for (int i = 0; i < 10_000; i++) {
//            Vec3 uv = new Vec3(new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble());
//            Vec3 n = new Vec3(new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble());
//            println("uv: ",Arrays.toString(uv.e));
//            println("n: ",Arrays.toString(n.e));
//            double etaiOverEtat = 1;
//
//            double cosTheta = min(dot(uv.invert(), n), 1.0);
//            Vec3 rOutPerp =  mult(etaiOverEtat,add(uv,mult(cosTheta,n)));
//            Vec3 rOutParallel = mult(-sqrt(abs(1.0 - rOutPerp.length_squared())),n);
//            println("cosTheta: ",cosTheta);
//            println("rOutPerp: ",Arrays.toString(rOutPerp.e));
//            println("rOutParallel: ",Arrays.toString(rOutParallel.e));
//
//            println("final: ",Arrays.toString(add(rOutPerp,rOutParallel).e));
//
//            Assert.assertArrayEquals(add(rOutPerp,rOutParallel).e,uv.e,0);
//        }
//    }
//
//    @Test
//    public void refractExperimentMassTest() {
//        for (int i = 0; i < 10_000; i++) {
//            Vec3 incident = new Vec3(new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble());
//            Vec3 norm = new Vec3(new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble());
//            double n1 = 1;
//            double n2 = 1;
//            double n = n1/n2;
//            double cosI = -dot(norm,incident);
//            double sinT2 = n*n*(1.0-cosI*cosI);
//            if(sinT2>1.0) continue;
//            double cosT = sqrt(1.0-sinT2);
//            Vec3 out = add(mult(n,incident),mult((n*cosI-cosT),norm));
//
//            Assert.assertArrayEquals(out.e,incident.e,0);
//        }
//    }
//
//    @Test
//    public void invertVec3Test(){
//        Vec3 vec = new Vec3(0.3056584614476915, 0.06903903583561433, 0.5157064710000614);
//        Vec3 veci = new Vec3(-0.3056584614476915, -0.06903903583561433, -0.5157064710000614);
//        Assert.assertArrayEquals(veci.e,vec.invert().e,0);
//    }
//}
