package main.ThreadedRendering;

public class PixelCache {
    public int[] image = {};

    public void setup(int w,int h){
        image = new int[w*h];
    }
}
