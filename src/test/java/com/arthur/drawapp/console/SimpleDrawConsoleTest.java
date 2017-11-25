package com.arthur.drawapp.console;

import com.arthur.drawapp.application.state.IAppState;
import com.arthur.drawapp.application.state.IAppStateMachine;
import com.arthur.drawapp.module.SimpleModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import junit.framework.TestCase;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.arthur.drawapp.console.DrawConsoleTestHelper.verifyCanvas;

/**
 * Created by laia on 11/24/2017.
 */
public class SimpleDrawConsoleTest extends TestCase {
    private IDrawConsole drawConsole;

    private static String TestString = "R 14 1 18 3";
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(
                Modules.override(new SimpleModule()).with(
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(InputStream.class)
                                .annotatedWith(Names.named("CmdInput")).toInstance(new ByteArrayInputStream(TestString.getBytes()));
                    }
                }));
        drawConsole =  injector.getInstance(IDrawConsole.class);
    }

    public void testInvalidCanvas() throws Exception {
        assertFalse(drawConsole.drawLine(6, 3, 6, 4));
        assertFalse(drawConsole.drawRect(14, 1, 18, 3));
        assertFalse(drawConsole.bucketFill(10, 3, 'o'));
    }

    public void testDrawLineInvalid() throws Exception {
        drawConsole.createCanvas(20, 4);
        assertFalse("line must be horizontal or vertial",
                drawConsole.drawLine(1, 1, 3, 3));
        assertFalse("line must be within bound x",
                drawConsole.drawLine(0, 1, 3, 1));
        assertFalse("line must be within bound x",
                drawConsole.drawLine(3, 1, 21, 1));
        assertFalse("line must be within bound y",
                drawConsole.drawLine(3, 0, 3, 1));
        assertFalse("line must be within bound y",
                drawConsole.drawLine(3, 1, 3, 8));
        assertFalse("line cannot be drawn vertically reverse way",
                drawConsole.drawLine(2, 4, 2, 1));
        assertFalse("line cannot be drawn horizontally reverse way",
                drawConsole.drawLine(20, 2, 1, 2));
    }

    public void testDrawLineValid() throws Exception {
        drawConsole.createCanvas(20, 4);
        {
            int x = 6;
            assertTrue("line can be draw here",
                    drawConsole.drawLine(x, 3, x, 4));

            String result[] =  {"----------------------",
                                "|                    |",
                                "|                    |",
                                "|     x              |",
                                "|     x              |",
                                "----------------------"};

            assertTrue(verifyCanvas(result, drawConsole));
        }
        drawConsole.clearCanvas();
        {
            int y = 2;
            assertTrue("line can be draw here",
                    drawConsole.drawLine(1, y, 6, y));

            String result[] =  {"----------------------",
                    "|                    |",
                    "|xxxxxx              |",
                    "|                    |",
                    "|                    |",
                    "----------------------"};

            assertTrue(verifyCanvas(result, drawConsole));
        }
        drawConsole.clearCanvas();
        {
            int x = 6;
            assertTrue("line can be draw here for y min and max",
                    drawConsole.drawLine(x, 1, x, 4));
            String result[] =  {"----------------------",
                    "|     x              |",
                    "|     x              |",
                    "|     x              |",
                    "|     x              |",
                    "----------------------"};

            assertTrue(verifyCanvas(result, drawConsole));




        }
        drawConsole.clearCanvas();
        {
            int y = 2;
            assertTrue("line can be draw here for x min and max",
                    drawConsole.drawLine(1, y, 20, y));
            String result[] =  {"----------------------",
                    "|                    |",
                    "|xxxxxxxxxxxxxxxxxxxx|",
                    "|                    |",
                    "|                    |",
                    "----------------------"};

            assertTrue(verifyCanvas(result, drawConsole));

        }
        drawConsole.clearCanvas();
        {
            assertTrue("line can be drawn as a point",
                    drawConsole.drawLine(1, 1, 1, 1));
            String result[] =  {"----------------------",
                    "|x                   |",
                    "|                    |",
                    "|                    |",
                    "|                    |",
                    "----------------------"};
            assertTrue(verifyCanvas(result, drawConsole));
        }
    }

    public void testDrawRectInvalid() throws Exception {
        drawConsole.createCanvas(20, 4);
        assertFalse("start point cannot be on the right of end point",
                drawConsole.drawRect(3, 1, 1, 3));
        assertFalse("start point cannot be down from end point ",
                drawConsole.drawRect(1, 3, 3, 1));
        assertFalse("start point cannot be on the bottom right from end point ",
                drawConsole.drawRect(3, 3, 1, 1));
        assertFalse("line must be within bound x",
                drawConsole.drawRect(0, 1, 3, 3));
        assertFalse("line must be within bound x",
                drawConsole.drawRect(1, 1, 21, 3));
        assertFalse("line must be within bound y",
                drawConsole.drawRect(1, 0, 3, 1));
        assertFalse("line must be within bound y",
                drawConsole.drawRect(1, 1, 3, 5));
    }

    public void testDrawRectValid() throws Exception {
        drawConsole.createCanvas(20, 4);
        {
            assertTrue("line can be draw here",
                    drawConsole.drawRect(1, 1, 20, 4));
            String result[] =  {"----------------------",
                    "|xxxxxxxxxxxxxxxxxxxx|",
                    "|x                  x|",
                    "|x                  x|",
                    "|xxxxxxxxxxxxxxxxxxxx|",
                    "----------------------"};

            assertTrue(verifyCanvas(result, drawConsole));
        }
        drawConsole.clearCanvas();
        {
            assertTrue("rect can be draw here",
                    drawConsole.drawRect(1, 1, 2, 2));
            for (int x = 3; x <= 20; ++x) {
                for (int y = 3; y <= 4; ++y){
                    assertTrue("rect not drawn here", drawConsole.getColor(x, y) == drawConsole.getBlankColor());
                }
            }
            for (int x = 1; x <= 2; ++x) {
                for (int y = 1; y <= 2; ++y){
                    assertTrue("rect should be drawn here", drawConsole.getColor(x, y) == drawConsole.getLineColor());
                }
            }
        }
    }

    public void testBucketFillInvalid() throws Exception {
        drawConsole.createCanvas(20, 4);
        assertFalse("line must be within bound x",
                drawConsole.bucketFill(0, 1, 'a'));
        assertFalse("line must be within bound x",
                drawConsole.bucketFill(21, 1, 'a'));
        assertFalse("line must be within bound y",
                drawConsole.bucketFill(3, 0, 'a' ));
        assertFalse("line must be within bound y",
                drawConsole.bucketFill(3, 8, 'a'));
    }

    public void testBucketFillValid() throws Exception {
        drawConsole.createCanvas(20, 4);
        drawConsole.drawLine(1, 2, 6, 2);
        drawConsole.drawLine(6, 3, 6, 4);
        drawConsole.drawRect(14, 1, 18, 3);
        drawConsole.bucketFill(10, 3, 'o');


        String result[] =  {"----------------------",
                            "|oooooooooooooxxxxxoo|",
                            "|xxxxxxooooooox   xoo|",
                            "|     xoooooooxxxxxoo|",
                            "|     xoooooooooooooo|",
                            "----------------------"};

        assertTrue(verifyCanvas(result, drawConsole));
    }

    public void testGetCmd() throws Exception {
        assertTrue("Should read string correctly", TestString.equals(drawConsole.getCmd()));
    }


}