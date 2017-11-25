package com.arthur.drawapp.application.state;

import com.arthur.drawapp.cmd.CmdCreateCanvas;
import com.arthur.drawapp.cmd.CmdQuit;
import com.arthur.drawapp.module.SimpleModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

/**
 * Created by laia on 11/24/2017.
 */
public class AppStateQuitTest extends TestCase {
    private Injector injector;
    private IAppStateMachine fsm;
    private IAppState testState;

    public void setUp() throws Exception {
        injector = Guice.createInjector(
                new SimpleModule(),
                new AbstractModule() {
            @Override
            protected void configure() {

            }
        });
        fsm =  injector.getInstance(IAppStateMachine.class);
        CmdQuit cmdQuit = injector.getInstance(CmdQuit.class);
        cmdQuit.execute();
        testState = fsm.getState();

    }

    public void testStateChange() throws Exception {
        assertFalse("cannot do anything", testState.drawLine(1,2, 6, 2));
        assertEquals("always no state change", fsm.getState(), fsm.getQuitState()); // no state change as invalid

        assertFalse("cannot do anything", testState.drawRect(1,2, 6, 2));
        assertEquals("always no state change", fsm.getState(), fsm.getQuitState());// no state change as invalid


        assertFalse("cannot do anything", testState.createCanvas(20, 4));
        assertEquals("always no state change", fsm.getState(), fsm.getQuitState());// no state change as invalid

        assertFalse("cannot do anything", testState.printCanvas());
        assertEquals("always no state change", fsm.getState(), fsm.getQuitState());// no state change as invalid

        assertTrue(testState.quit());
        assertEquals("always no state change", fsm.getState(), fsm.getQuitState());// no state change as invalid

    }
}