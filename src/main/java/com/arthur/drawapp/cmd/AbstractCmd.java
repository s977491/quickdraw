package com.arthur.drawapp.cmd;

import com.arthur.drawapp.application.state.IAppState;
import com.arthur.drawapp.application.state.IAppStateMachine;
import com.google.inject.Inject;

/**
 * Created by laia on 11/25/2017.
 */
public abstract class AbstractCmd implements ICmd{
    @Inject
    protected IAppStateMachine fsm;

    protected IAppState getCurrentState() {
        return fsm.getState();
    }
}
