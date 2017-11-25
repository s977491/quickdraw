package com.arthur.drawapp.application.state;

import com.arthur.drawapp.application.IApplication;
import com.arthur.drawapp.application.state.IAppState;
import com.arthur.drawapp.application.state.IAppStateMachine;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by laia on 11/23/2017.
 */
@Singleton
public class SimpleAppStateMachine implements IAppStateMachine {

    private IAppState currentState;
    @Inject
    @Getter
    private AppStateInitial initialState;
    @Inject
    @Getter
    private AppStateQuit quitState;
    @Inject
    @Getter
    private AppStateWithCanvas withCanvasState;

    @Inject
    private void init(){
        currentState = initialState;
    }

    @Override
    public IAppState getState() {
        return currentState;
    }

    @Override
    public void setState(IAppState state) {
        this.currentState = state;
    }

}
