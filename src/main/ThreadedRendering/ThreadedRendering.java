package main.ThreadedRendering;

import main.Camera;
import main.Objects.Hittable;
import main.Util.Interval;
import main.Util.TimeKeeper;

import static java.lang.Math.floor;
import static processing.core.PApplet.append;
import static processing.core.PApplet.println;

public class ThreadedRendering {
    public static ChunkThread[] chunkThreads = {};
    public static int chunkAmount = 50;
    private static int chunksFinished = 0;
    public static void start(Camera cam, Hittable world) {
        PixelCache.setup(cam.imageWidth,cam.imageHeight);
        println(cam.imageWidth,cam.imageHeight);
        for(int i=0;i<chunkAmount;i++){
            ChunkThread chunkThread = new ChunkThread();
            chunkThread.setRegion(new Interval(floor(i * ((double) cam.imageHeight /chunkAmount)), floor((i + 1) * ((double) cam.imageHeight /chunkAmount))));
            chunkThread.cam = cam;
            chunkThread.world = world;
            chunkThreads = (ChunkThread[]) append(chunkThreads,chunkThread);
        }
        for(ChunkThread c:chunkThreads){
            c.start();
        }
    }

    public static void finishChunk() {
        chunksFinished++;
        checkDone();
    }

    public static void checkDone(){
        if(chunksFinished>=chunkAmount){
            PixelCache.pushToDisplay();
            println("rendering done!");
            TimeKeeper.endTimeKeep();
        }
    }
}
