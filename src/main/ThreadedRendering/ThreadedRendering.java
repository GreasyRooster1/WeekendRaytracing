package main.ThreadedRendering;

import static processing.core.PApplet.append;

public class ThreadedRendering {
    public ChunkThread[] chunkThreads = {};
    public int chunkAmount = 10;
    public void start() {
        for(int i=0;i<chunkAmount;i++){
            chunkThreads = (ChunkThread[]) append(chunkThreads,new ChunkThread());
        }
    }
}
