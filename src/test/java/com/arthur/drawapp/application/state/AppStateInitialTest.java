package com.arthur.drawapp.application.state;

import com.arthur.drawapp.module.SimpleModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

/**
 * Created by laia on 11/24/2017.
 */
public class AppStateInitialTest extends TestCase {
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
        fsm.setState(fsm.getInitialState());
        testState = fsm.getState();
    }

    public void testStateChange() throws Exception {
        assertFalse("cannot draw or fill", testState.drawLine(1,2, 6, 2));
        assertEquals("no state change", fsm.getState(), testState); // no state change as invalid

        assertFalse("cannot draw or fill", testState.drawRect(1,2, 6, 2));
        assertEquals("no state change", fsm.getState(), testState);// no state change as invalid

        assertFalse("cannot bucket fill", testState.bucketFill(1,2, 'o'));
        assertEquals("no state change", fsm.getState(), testState);// no state change as invalid

        assertFalse("cannot print canvas", testState.printCanvas());
        assertEquals("no state change", fsm.getState(), testState);// no state change as invalid

        assertTrue(testState.createCanvas(20, 4));
        assertEquals("state change to with canvas", fsm.getState(), fsm.getWithCanvasState());// no state change as invalid

        fsm.setState(testState);

        assertTrue(testState.quit());
        assertEquals("state change to quit", fsm.getState(), fsm.getQuitState());// no state change as invalid
    }


}