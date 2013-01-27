package com.vjlab.view;

import com.vjlab.model.Track;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 07/01/13
 * Time: 23:10
 */
public abstract class TrackControl<TRACK extends Track> extends Control {

    public final static Logger logger = LoggerFactory.getLogger(TrackControl.class);

    private final ObjectProperty<TRACK> track = new SimpleObjectProperty<TRACK>();

    protected TrackControl() {
        trackProperty().addListener(new ChangeListener<TRACK>() {
            @Override
            public void changed(ObservableValue<? extends TRACK> observableValue, TRACK track, TRACK track2) {
                if (track != null) {
                    rotateProperty().unbind();
                    opacityProperty().unbind();
                    blendModeProperty().unbind();
                }
                if (track2 != null) {
                    rotateProperty().bind(track2.rotateProperty());
                    opacityProperty().bind(track2.opacityProperty());
                    blendModeProperty().bind(track2.blendModeProperty());
                }
            }
        });
    }

    public TRACK getTrack() {
        return track.get();
    }

    public void setTrack(TRACK track1) {
        this.track.set(track1);
    }

    public ObjectProperty<TRACK> trackProperty() {
        return track;
    }

}
