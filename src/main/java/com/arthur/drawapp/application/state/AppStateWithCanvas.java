package com.arthur.drawapp.application.state;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by laia on 11/24/2017.
 */
@Singleton
public class AppStateWithCanvas extends AbstractAppState {
    @Inject
    AppStateWithCanvas(IAppStateMachine fsm) {
        super(fsm);
    }


    @Override
    public boolean printCanvas() {
        drawConsole.printCanvas();
        return true;
    }

    @Override
    public boolean createCanvas(int width, int height) {
        System.out.println("fail to create canvas again");
        return false;
    }

    @Override
    public boolean drawLine(int x1, int y1, int x2, int y2) {
        return drawConsole.drawLine(x1, y1, x2, y2);
    }

    @Override
    public boolean drawRect(int x1, int y1, int x2, int y2) {
        return drawConsole.drawRect(x1, y1, x2, y2);
    }

    @Override
    public boolean bucketFill(int x, int y, char color) {
        return drawConsole.bucketFill(x, y, color);
    }
}
