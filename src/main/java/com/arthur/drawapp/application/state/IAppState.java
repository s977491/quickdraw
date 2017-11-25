package com.arthur.drawapp.application.state;

/**
 * Created by laia on 11/23/2017.
 */
public interface IAppState {

    IAppStateMachine getFsm();

    boolean printCanvas();

    boolean createCanvas(int width, int height);

    boolean drawLine(int x1, int y1, int x2, int y2);

    boolean drawRect(int x1, int y1, int x2, int y2);

    boolean bucketFill(int x, int y, char color);

    void setState(IAppState state);

    boolean quit();
}
