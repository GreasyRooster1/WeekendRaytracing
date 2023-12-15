package main.ThreadedRendering;

public class PixelCache {
    public static int[] image = {};

    public static void setup(int w, int h){
        image = new int[w*h];
    }
}
