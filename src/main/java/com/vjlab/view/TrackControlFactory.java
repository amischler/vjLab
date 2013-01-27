package com.vjlab.view;

import com.google.inject.ImplementedBy;
import com.vjlab.model.Track;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/01/13
 * Time: 19:16
 */
@ImplementedBy(TrackControlFactoryImpl.class)
public interface TrackControlFactory {

    public TrackControl createTrackControl(Track track);

}
