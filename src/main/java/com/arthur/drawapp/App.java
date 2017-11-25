package com.arthur.drawapp;

import com.arthur.drawapp.application.IApplication;
import com.arthur.drawapp.module.SimpleModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new SimpleModule());
        IApplication app = injector.getInstance(IApplication.class);
        app.run();

    }
}
