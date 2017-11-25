package com.arthur.drawapp.cmd;

import com.arthur.drawapp.application.state.AppStateInitial;
import com.arthur.drawapp.application.state.MockAppStateInitial;
import com.arthur.drawapp.console.IDrawConsole;
import com.arthur.drawapp.module.SimpleModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by laia on 11/25/2017.
 */
public class SimpleCmdFactoryTest extends TestCase {
    private ICmdFactory cmdFactory;
    private MockAppStateInitial testState;
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(
                Modules.override(new SimpleModule()).with(
                        new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(AppStateInitial.class).to(MockAppStateInitial.class);
                            }
                        }));
        cmdFactory =  injector.getInstance(ICmdFactory.class);
        testState = (MockAppStateInitial) injector.getInstance(AppStateInitial.class);
    }
    public void testInvalidCmd() throws Exception {
        ICmd cmd;
        cmd = cmdFactory.createCmd("C 20 4 1");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("C 20");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("C");
        assertTrue(cmd == null);

        cmd = cmdFactory.createCmd("D");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("L 1");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("L a");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("L 1 2");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("L 1 2 4");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("R 1");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("R 1 2");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("R 1 b");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("R 1 2 4");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("B");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("B 1");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("B 1 2");
        assertTrue(cmd == null);
        cmd = cmdFactory.createCmd("B a b");
        assertTrue(cmd == null);
    }
    public void testValidCreateCanvasCmd() throws Exception {
        String sCmd = "C 20 4";

        ICmd cmd = cmdFactory.createCmd(sCmd);
        assertTrue(cmd instanceof CmdCreateCanvas);
        CmdCreateCanvas cmdCreateCanvas = (CmdCreateCanvas) cmd;
        assertEquals(cmdCreateCanvas.getWidth(), 20);
        assertEquals(cmdCreateCanvas.getHeight(), 4);

        cmd.execute();
        assertTrue("cmd called correctly", testState.isCalledCreateCanvas());
    }
    public void testValidDrawLineCmd() throws Exception {
        String sCmd = "L 6 3 6 4";

        ICmd cmd = cmdFactory.createCmd(sCmd);
        assertTrue(cmd instanceof CmdDrawLine);
        CmdDrawLine cmdDrawLine = (CmdDrawLine) cmd;
        assertEquals(cmdDrawLine.getX1(), 6);
        assertEquals(cmdDrawLine.getY1(), 3);
        assertEquals(cmdDrawLine.getX2(), 6);
        assertEquals(cmdDrawLine.getY2(), 4);
        cmd.execute();
        assertTrue("cmd called correctly", testState.isCalledDrawLine());
    }
    public void testValidDrawRectCmd() throws Exception {
        String sCmd = "R 1 3 6 4";

        ICmd cmd = cmdFactory.createCmd(sCmd);
        assertTrue(cmd instanceof CmdDrawRect);
        CmdDrawRect cmdDrawRect = (CmdDrawRect) cmd;
        assertEquals(cmdDrawRect.getX1(), 1);
        assertEquals(cmdDrawRect.getY1(), 3);
        assertEquals(cmdDrawRect.getX2(), 6);
        assertEquals(cmdDrawRect.getY2(), 4);
        cmd.execute();
        assertTrue("cmd called correctly", testState.isCalledDrawRect());
    }
    public void testValidBucketFillCmd() throws Exception {
        String sCmd = "B 10 3 o";

        ICmd cmd = cmdFactory.createCmd(sCmd);
        assertTrue(cmd instanceof CmdBucketFill);
        CmdBucketFill cmdBucketFill = (CmdBucketFill) cmd;
        assertEquals(cmdBucketFill.getX(), 10);
        assertEquals(cmdBucketFill.getY(), 3);
        assertEquals(cmdBucketFill.getColor(), 'o');
        cmd.execute();
        assertTrue("cmd called correctly", testState.isCalledBucketFill());
    }
    public void testValidQuitCmd() throws Exception {
        String sCmd = "Q";

        ICmd cmd = cmdFactory.createCmd(sCmd);
        assertTrue(cmd instanceof CmdQuit);
        cmd.execute();
        assertTrue("cmd called correctly", testState.isCalledQuit());

    }

}