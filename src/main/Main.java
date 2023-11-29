package main;

import processing.core.PApplet;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends PApplet {
    public static PApplet app;
    public static ProgressBar progressBar;
    public void settings() {
        double aspectRatio = 1; //16f/9f;
        int imageWidth = 1000;
        int imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = Math.max(imageHeight, 1);
        size(imageWidth, imageHeight);
    }

    public void setup(){
        app = this;
        progressBar = new ProgressBar();
        Renderer.setup();
    }

    public void draw(){
        Renderer.render();
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "main.Main" };
        PApplet.main(appletArgs);
    }
}