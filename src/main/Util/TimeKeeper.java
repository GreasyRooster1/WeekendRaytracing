package main.Util;

import static java.lang.System.*;
import static processing.core.PApplet.println;

public class TimeKeeper {
    static long startTime = System.currentTimeMillis();
    public static void startTimeKeep(){
        startTime = System.currentTimeMillis();
    }
    public static void endTimeKeep(){
        println(System.currentTimeMillis()-startTime+"ms elapsed");
    }
}
