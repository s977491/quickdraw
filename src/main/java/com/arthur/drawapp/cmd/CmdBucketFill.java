package com.arthur.drawapp.cmd;

import com.arthur.drawapp.application.state.IAppState;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by laia on 11/25/2017.
 */
@Getter
@Setter
public class CmdBucketFill extends AbstractCmd {
    private char color;
    private int y;
    private int x;

    @Override
    public boolean execute() {
        return getCurrentState().bucketFill(x, y, color);
    }
}
