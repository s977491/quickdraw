package com.arthur.drawapp.application.state;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by laia on 11/24/2017.
 */
@Singleton
public class AppStateQuit extends AbstractAppState {
    @Inject
    AppStateQuit(IAppStateMachine fsm) {
        super(fsm);
    }


    @Override
    public boolean printCanvas() {
        return false;
    }

    @Override
    public boolean createCanvas(int width, int height) {
        return false;
    }

    @Override
    public boolean drawLine(int x1, int y1, int x2, int y2) {
        return false;
    }

    @Override
    public boolean drawRect(int x1, int y1, int x2, int y2) {
        return false;
    }

    @Override
    public boolean bucketFill(int x, int y, char color) {
        return false;
    }
}
