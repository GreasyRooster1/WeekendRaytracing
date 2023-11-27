package main;

import processing.core.PApplet;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends PApplet {
    public static PApplet app;
    public void settings() {
        size(500, 500);
    }

    public void setup(){
        app = this;
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