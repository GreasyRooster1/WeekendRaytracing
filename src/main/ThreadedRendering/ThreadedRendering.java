package main.ThreadedRendering;

import main.Camera;
import main.Hittable;
import main.Main;
import main.Util.Interval;

import static processing.core.PApplet.append;

public class ThreadedRendering {
    public static ChunkThread[] chunkThreads = {};
    public static int chunkAmount = 10;
    public static void start(Camera cam, Hittable world) {
        PixelCache.setup(cam.imageWidth,cam.imageHeight);
        for(int i=0;i<chunkAmount-1;i++){
            ChunkThread chunkThread = new ChunkThread();
            chunkThread.setRegion(new Interval(i* cam.imageWidth,(i+1)* Main.app.height));
            chunkThread.cam = cam;
            chunkThread.world = world;
            chunkThreads = (ChunkThread[]) append(chunkThreads,chunkThread);
        }
        for(ChunkThread c:chunkThreads){
            c.start();
        }
    }
}
