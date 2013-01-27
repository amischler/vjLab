package com.vjlab.view;

import com.google.inject.Inject;
import com.vjlab.model.Track;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 07/01/13
 * Time: 22:34
 */
public class RenderingStage extends Stage {

    Map<Track, TrackControl> trackMap = new HashMap<Track, TrackControl>();

    @Inject
    ObservableList<Track> tracks;

    @Inject
    TrackControlFactory trackControlFactory;

    private final StackPane stackPane = new StackPane();

    public void init() {
        setTitle("Rendering");
        setWidth(320);
        setHeight(180);
        setScene(new Scene(stackPane));
        tracks.addListener(new ListChangeListener<Track>() {
            @Override
            public void onChanged(Change<? extends Track> change) {
                while (change.next()) {
                    for (Track track : change.getAddedSubList()) {
                        trackMap.put(track, trackControlFactory.createTrackControl(track));
                        stackPane.getChildren().add(trackMap.get(track));
                    }
                    for (Track track : change.getRemoved()) {
                        stackPane.getChildren().remove(trackMap.get(track));
                        trackMap.remove(track);
                    }
                }
            }
        });
    }

}
