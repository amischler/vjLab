package com.vjlab.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/01/13
 * Time: 18:49
 */
public class MediaTrack extends LocationTrack {

    private final ObjectProperty<Media> media = new SimpleObjectProperty<Media>();

    public MediaTrack() {
        locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (s2 != null) {
                    media.set(new Media(s2));
                }
            }
        });
    }

    public Media getMedia() {
        return media.get();
    }

    public void setMedia(Media media1) {
        this.media.set(media1);
    }

    public ObjectProperty<Media> mediaProperty() {
        return media;
    }



}
