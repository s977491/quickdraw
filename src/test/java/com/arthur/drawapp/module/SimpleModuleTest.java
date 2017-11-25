package com.arthur.drawapp.module;

import com.arthur.drawapp.console.IDrawConsole;
import com.arthur.drawapp.console.SimpleDrawConsole;
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
public class SimpleModuleTest extends TestCase {
    public static final String OVERRIDDEN = "Overridden";
    public static final char DEFAULT_LINE_COLOR = 'x';
    private MockDummy dummy;
    private IDrawConsole drawConsole;

    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new SimpleModule());
        dummy = injector.getInstance(MockDummy.class);
        drawConsole = injector.getInstance(IDrawConsole.class);
    }

    public void testConfigureOverride() throws Exception {
        assertTrue(OVERRIDDEN.equals(dummy.getTestOverride()));
    }
    public void testConfigureDefault() throws Exception {
        assertTrue(DEFAULT_LINE_COLOR == drawConsole.getLineColor());
    }

}