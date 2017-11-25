package com.arthur.drawapp.application.state;

/**
 * Created by laia on 11/23/2017.
 */
public interface IAppStateMachine {

    IAppState getState();
    void setState(IAppState state);

    IAppState getInitialState();
    IAppState getWithCanvasState();
    IAppState getQuitState();

//    default void createCanvas(int w, int h) {
//        getState().createCanvas(w, h);
//    }
//    default void drawLine(int x1, int y1, int x2, int y2) {
//        getState().drawLine(x1, y1, x2, y2);
//    }
//    default void drawRect(int x1, int y1, int x2, int y2) {
//        getState().drawRect(x1, y1, x2, y2);
//    }
//    default void quit() {
//        getState().quit();
//    }

}