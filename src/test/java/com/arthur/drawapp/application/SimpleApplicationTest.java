package com.arthur.drawapp.application;

import com.arthur.drawapp.application.state.AppStateInitial;
import com.arthur.drawapp.application.state.MockAppStateInitial;
import com.arthur.drawapp.cmd.ICmdFactory;
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

import static com.arthur.drawapp.console.DrawConsoleTestHelper.verifyCanvas;

/**
 * Created by laia on 11/25/2017.
 */
public class SimpleApplicationTest extends TestCase {
    private IApplication application;
    private String TestString =
                                    "R 14 1 18 3" + System.lineSeparator()///invalid
                                    + "C 20 4" + System.lineSeparator()
                                    + "Q a"+ System.lineSeparator()///invalid
                                    + "C" + System.lineSeparator()///invalid
                                    + "C 1" + System.lineSeparator()///invalid
                                    + "L a 2 6 2" + System.lineSeparator()///invalid
                                    + "L 1 2 6 2" + System.lineSeparator()
                                    + "L 1 " + System.lineSeparator()///invalid
                                    + "L 1 2" + System.lineSeparator()///invalid
                                    + "L 1 2 3" + System.lineSeparator()///invalid
                                    + "L 1 2 6 4" + System.lineSeparator()///invalid
                                    + "L 6 3 6 1" + System.lineSeparator()///invalid
                                    + "L 6 3 1 3" + System.lineSeparator() ///invalid
                                    + "L 6 3 6 4" + System.lineSeparator()
                                    + "R 14 1 18 3" + System.lineSeparator()
                                    + "R " + System.lineSeparator()///invalid
                                    + "R 18 1 14 3" + System.lineSeparator()///invalid
                                    + "R 14 3 18 1" + System.lineSeparator()///invalid
                                    + "R 18 3 14 1" + System.lineSeparator()///invalid
                                    + "B 10 3 o" + System.lineSeparator()
                                    + "B 10 " + System.lineSeparator()
                                    + "B 10 5 o" + System.lineSeparator()
                                    + "B 20 5 o" + System.lineSeparator()
                                    + "Q";
    private IDrawConsole drawConsole;


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
        application = injector.getInstance(IApplication.class);
        drawConsole =  injector.getInstance(IDrawConsole.class);
    }

    public void testIntegration() throws Exception {
        application.run();
        String result[] =  {"----------------------",
                "|oooooooooooooxxxxxoo|",
                "|xxxxxxooooooox   xoo|",
                "|     xoooooooxxxxxoo|",
                "|     xoooooooooooooo|",
                "----------------------"};
        assertTrue(verifyCanvas(result, drawConsole));
    }
}