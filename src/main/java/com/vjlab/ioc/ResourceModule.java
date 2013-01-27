package com.vjlab.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.ResourceBundle;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 26/01/13
 * Time: 15:38
 */
public class ResourceModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    protected ResourceBundle providesResourceBundle() {
        return ResourceBundle.getBundle("vjlab");
    }
}
