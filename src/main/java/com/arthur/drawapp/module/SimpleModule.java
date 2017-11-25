package com.arthur.drawapp.module;

import com.arthur.drawapp.application.IApplication;
import com.arthur.drawapp.application.SimpleApplication;
import com.arthur.drawapp.application.state.IAppStateMachine;
import com.arthur.drawapp.application.state.SimpleAppStateMachine;
import com.arthur.drawapp.cmd.ICmdFactory;
import com.arthur.drawapp.cmd.SimpleCmdFactory;
import com.arthur.drawapp.console.IDrawConsole;
import com.arthur.drawapp.console.SimpleDrawConsole;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by laia on 11/23/2017.
 */
public class SimpleModule extends AbstractModule {

    public static final String APPLICATION_PROPERTIES = "application.properties";
    public static final String APPLICATION_OVERRIDE_PROPERTIES = "application.override.properties";

    protected void configure() {
        Properties defaults = new Properties();
        //defaults.setProperty("myprop", "default");
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            //File fileFromResource = new File();
            Properties props = new Properties(defaults);
            props.load(classLoader.getResourceAsStream(APPLICATION_PROPERTIES));

            //override by the outsidest
            File fileFromFolder = new File(APPLICATION_OVERRIDE_PROPERTIES);
            if (fileFromFolder.exists()) {
                props = new Properties(props);
                props.load(new FileInputStream(fileFromFolder));
            }

            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            System.err.println("cannot find " + APPLICATION_PROPERTIES);
            System.exit(1);
        }

        bind(IApplication.class).to(SimpleApplication.class);
        bind(IAppStateMachine.class).to(SimpleAppStateMachine.class);
        bind(IDrawConsole.class).to(SimpleDrawConsole.class);
        bind(ICmdFactory.class).to(SimpleCmdFactory.class);

        bind(InputStream.class)
                .annotatedWith(Names.named("CmdInput")).toInstance(System.in);
    }
}
