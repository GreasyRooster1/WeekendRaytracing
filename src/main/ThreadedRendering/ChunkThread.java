package main.ThreadedRendering;

import main.Camera;
import main.Hittable;
import main.Main;
import main.Util.Color;
import main.Util.Interval;
import main.Util.Ray;
import main.Util.Vec3;

import javax.swing.plaf.synth.Region;

import static main.Camera.*;
import static main.ProgressBar.updateProgressBar;

public class ChunkThread extends Thread{
    public Hittable world;
    Interval region = new Interval(0,0);
    Camera cam;
    @Override
    public void run(){
        System.out.println("ChunkThread started on region "+region.min+", "+region.max);
        for (int j = (int) region.min; j <region.max; ++j) {
            for (int i = 0; i < Main.app.width; ++i) {
                Vec3 pixelColor = new Color(0,0,0);
                for (int sample = 0; sample < cam.samplesPerPixel; ++sample) {
                    Ray r = cam.getRay(i, j);
                    pixelColor= pixelColor.add(cam.rayColor(r,cam.maxDepth, world));
                }
                PixelCache.write(i,j,pixelColor,cam.samplesPerPixel);
            }
        }
        System.out.println("ChunkThread with region "+region.min+", "+region.max+" finished!");
        ThreadedRendering.finishChunk();
    }

    public void setRegion(Interval interval) {
        region = interval;
    }
}
