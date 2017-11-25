package com.arthur.drawapp.application.state;

import com.arthur.drawapp.console.IDrawConsole;
import com.google.inject.Inject;
import lombok.Getter;

/**
 * Created by laia on 11/24/2017.
 */

public abstract class AbstractAppState implements IAppState{
    @Getter
    protected IAppStateMachine fsm;

    @Inject
    protected IDrawConsole drawConsole;

    AbstractAppState(IAppStateMachine fsm){
        this.fsm = fsm;
    }

    @Override
    public void setState(IAppState state) {
        fsm.setState(state);
    }
    @Override
    public boolean quit() {
        fsm.setState(fsm.getQuitState());
        return true;
    }
}
