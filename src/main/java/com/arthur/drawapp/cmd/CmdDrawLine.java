package com.arthur.drawapp.cmd;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by laia on 11/25/2017.
 */
@Getter
@Setter

public class CmdDrawLine extends AbstractCmd{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    @Override
    public boolean execute() {
        return getCurrentState().drawLine(x1, y1, x2, y2);
    }
}
