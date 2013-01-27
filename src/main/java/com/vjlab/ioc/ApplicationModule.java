package com.vjlab.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.vjlab.model.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 07/01/13
 * Time: 22:37
 */
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public ObservableList<Track> provideTracks() {
        return FXCollections.observableArrayList();
    }

}
