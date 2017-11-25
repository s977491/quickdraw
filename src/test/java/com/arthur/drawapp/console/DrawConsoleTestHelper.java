package com.arthur.drawapp.console;

/**
 * Created by laia on 11/25/2017.
 */
public class DrawConsoleTestHelper {

    public static boolean verifyCanvas(String[] result, IDrawConsole drawConsole) {
        for (int y = 0; y <=5; ++y) {
            for (int x = 0; x <=21; ++x) {
                char expect = result[y].charAt(x);
                if (expect != drawConsole.getColor(x, y))
                    return false;
            }
        }
        return true;
    }
}
