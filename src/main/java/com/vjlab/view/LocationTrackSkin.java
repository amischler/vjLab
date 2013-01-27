package com.vjlab.view;

import com.vjlab.model.LocationTrack;
import javafx.scene.control.Skin;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/01/13
 * Time: 18:52
 */
public abstract class LocationTrackSkin<TRACK extends LocationTrack, CONTROL extends TrackControl<TRACK>> implements Skin<CONTROL> {

    private final CONTROL control;

    public LocationTrackSkin(CONTROL control) {
        this.control = control;
    }

    @Override
    public CONTROL getSkinnable() {
        return control;
    }

}
