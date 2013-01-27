package com.vjlab.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.vjlab.model.ImageTrack;
import com.vjlab.model.MediaTrack;
import com.vjlab.model.Track;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/01/13
 * Time: 19:17
 */
public class TrackControlFactoryImpl implements TrackControlFactory {

    private Map<Class, Provider<? extends TrackControl>> providerMap = new HashMap<Class, Provider<? extends TrackControl>>();

    @Inject
    public TrackControlFactoryImpl(Provider<ImageTrackControl> imageTrackControlProvider,
                                   Provider<MediaTrackControl> mediaTrackControlProvider) {
        providerMap.put(ImageTrack.class, imageTrackControlProvider);
        providerMap.put(MediaTrack.class, mediaTrackControlProvider);
    }

    @Override
    public TrackControl createTrackControl(Track track) {
        TrackControl trackControl = providerMap.get(track.getClass()).get();
        trackControl.setTrack(track);
        return trackControl;
    }

}
