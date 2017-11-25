package com.arthur.drawapp.application.state;

import com.arthur.drawapp.cmd.CmdCreateCanvas;
import com.arthur.drawapp.module.SimpleModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

/**
 * Created by laia on 11/24/2017.
 */
public class AppStateWithCanvasTest extends TestCase {
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
        CmdCreateCanvas cmdCreateCanvas = injector.getInstance(CmdCreateCanvas.class);
        cmdCreateCanvas.setWidth(20);
        cmdCreateCanvas.setHeight(4);
        cmdCreateCanvas.execute();
        testState = fsm.getState();
    }

    public void testStateChange() throws Exception {
        assertTrue("can draw line", testState.drawLine(1,2, 6, 2));
        assertEquals(fsm.getState(), testState); // no state change as invalid

        assertTrue("can draw rect", testState.drawRect(1,2, 6, 2));
        assertEquals("no state change", fsm.getState(), testState);// no state change as invalid

        assertTrue("can bucket fill", testState.bucketFill(1,2, 'o'));
        assertEquals("no state change", fsm.getState(), testState);// no state change as invalid

        assertFalse("cannot create canvas", testState.createCanvas(20, 4));
        assertEquals("no state change", fsm.getState(), testState);// no state change as invalid

        assertTrue("can print canvas", testState.printCanvas());
        assertEquals("no state change", fsm.getState(), testState);// no state change as invalid

        assertTrue(testState.quit());
        assertEquals("become quite state", fsm.getState(), fsm.getQuitState());// no state change as invalid
    }

}