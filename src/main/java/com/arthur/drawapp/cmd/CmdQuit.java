package com.arthur.drawapp.cmd;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by laia on 11/25/2017.
 */
@Getter
@Setter
public class CmdQuit extends AbstractCmd{

    @Override
    public boolean execute() {
        return getCurrentState().quit();
    }
}
