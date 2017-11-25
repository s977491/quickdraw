package com.arthur.drawapp.application;

import com.arthur.drawapp.application.state.IAppStateMachine;
import com.arthur.drawapp.cmd.ICmd;
import com.arthur.drawapp.cmd.ICmdFactory;
import com.arthur.drawapp.console.IDrawConsole;
import com.google.inject.Inject;

/**
 * Created by laia on 11/23/2017.
 */
public class SimpleApplication implements IApplication {

    @Inject
    protected IAppStateMachine fsm;
    @Inject
    protected IDrawConsole drawConsole;
    @Inject
    protected ICmdFactory cmdFactory;

    public void run() {
        while (fsm.getState() != fsm.getQuitState()) {
            fsm.getState().printCanvas();

            String cmdString = drawConsole.getCmd();
            ICmd cmd = cmdFactory.createCmd(cmdString);
            if (cmd != null)
                cmd.execute();
            else {
                System.out.println("Invalid command");
            }
        }
    }
}
