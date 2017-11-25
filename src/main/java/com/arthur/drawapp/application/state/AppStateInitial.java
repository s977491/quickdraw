package com.arthur.drawapp.application.state;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by laia on 11/24/2017.
 */
@Singleton
public class AppStateInitial extends AbstractAppState {
    @Inject
    AppStateInitial(IAppStateMachine fsm) {
        super(fsm);
    }

    @Override
    public boolean printCanvas() {
        return false;
    }

    @Override
    public boolean createCanvas(int width, int height) {
        boolean success = drawConsole.createCanvas(width, height);
        if (success) {
            fsm.setState(fsm.getWithCanvasState());
        }
        return success;
    }

    @Override
    public boolean drawLine(int x1, int y1, int x2, int y2) {
        System.out.println("Canvas not ready");
        return false;
    }

    @Override
    public boolean drawRect(int x1, int y1, int x2, int y2) {
        System.out.println("Canvas not ready");
        return false;
    }

    @Override
    public boolean bucketFill(int x, int y, char color) {
        System.out.println("Canvas not ready");
        return false;
    }

}
