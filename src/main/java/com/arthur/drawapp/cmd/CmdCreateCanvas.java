package com.arthur.drawapp.cmd;

import com.arthur.drawapp.application.state.IAppState;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by laia on 11/25/2017.
 */

@Getter
@Setter
public class CmdCreateCanvas extends AbstractCmd{
    private int width;
    private int height;

    @Override
    public boolean execute() {
        return getCurrentState().createCanvas(width, height);
    }
}
