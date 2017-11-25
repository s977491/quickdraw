package com.arthur.drawapp.application.state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;

/**
 * Created by laia on 11/25/2017.
 */
@Singleton
public class MockAppStateInitial extends AppStateInitial {

    @Getter
    private boolean calledQuit;
    @Getter
    private boolean calledCreateCanvas;
    @Getter
    private boolean calledDrawLine;
    @Getter
    private boolean calledDrawRect;
    @Getter
    private boolean calledBucketFill;
    @Getter
    private boolean calledPrintCanvas;


    @Inject
    MockAppStateInitial() {
        super(null);
    }

    public void reset() {
        calledPrintCanvas = false;
        calledCreateCanvas = false;
        calledDrawLine = false;
        calledDrawRect = false;
        calledBucketFill = false;
        calledQuit = false;
    }
    @Override
    public boolean printCanvas() {
        calledPrintCanvas = true;
        return false;
    }

    @Override
    public boolean createCanvas(int width, int height) {
        calledCreateCanvas = true;
        return false;
    }

    @Override
    public boolean drawLine(int x1, int y1, int x2, int y2) {
        calledDrawLine = true;
        return false;
    }

    @Override
    public boolean drawRect(int x1, int y1, int x2, int y2) {
        calledDrawRect = true;
        return false;
    }

    @Override
    public boolean quit() {
        calledQuit = true;
        return true;
    }

    @Override
    public boolean bucketFill(int x, int y, char color) {
        calledBucketFill = true;
        return false;
    }
}
