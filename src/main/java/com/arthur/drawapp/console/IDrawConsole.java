package com.arthur.drawapp.console;

/**
 * Created by laia on 11/24/2017.
 */
public interface IDrawConsole {
    boolean createCanvas(int w, int h);

    boolean drawLine(int x1, int y1, int x2, int y2);

    boolean drawRect(int x1, int y1, int x2, int y2);

    boolean bucketFill(int x, int y, char color);

    char getColor(int i, int y);

    char getLineColor();

    void clearCanvas();

    char getBlankColor();

    void printCanvas();

    String getCmd();
}
