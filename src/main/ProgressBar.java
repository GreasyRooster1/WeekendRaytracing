package main;

import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JPanel {

    JProgressBar pbar;

    static final int MY_MINIMUM = 0;

    static final int MY_MAXIMUM = 100;

    public ProgressBar() {

        // initialize Progress Bar

        pbar = new JProgressBar();
        pbar.setMinimum(MY_MINIMUM);
        pbar.setMaximum(MY_MAXIMUM);
        // add to JPanel
        add(pbar);


        JFrame frame = new JFrame("Progress Bar");
        Rectangle r = frame.getBounds();
        setLocation(Main.app.windowX+Main.app.width/2-r.width,Main.app.windowY-60);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);

    }

    public void updateBar(int newValue) {
        pbar.setValue(newValue);
    }
    public static void updateProgressBar(int i,int m){
        Main.progressBar.updateBar((i/m));
    }
}
