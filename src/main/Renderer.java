package main;

import processing.core.PApplet;

public class Renderer {
    public static int width,height;
    public static PApplet app;
    public static void setup() {
        app = Main.app;
        width = app.width;
        height = app.height;
    }

    public static void render(){
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                float r = (float) i /width;
                float g = (float) j /height;
                float b = 0;
                int col = app.color(r*255,g*255,b*255);
                app.set(i,j,col);
            }
        }
    }
}
