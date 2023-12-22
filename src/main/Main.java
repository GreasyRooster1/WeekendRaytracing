package main;

import processing.core.PApplet;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends PApplet {
    public static PApplet app;
    public static ProgressBar progressBar;
    public static double aspectRatio = 16d/9d;

    public void settings() {
        randomSeed(4587634);
        int imageWidth = 1000;
        int imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = Math.max(imageHeight, 1);
        size(imageWidth, imageHeight);
    }

    public void setup(){
        app = this;
        progressBar = new ProgressBar();
        Renderer.setup();
        noLoop();
        Renderer.render();
        redraw();
    }

    public void draw(){
        noLoop();
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "main.Main" };
        PApplet.main(appletArgs);

    }
}