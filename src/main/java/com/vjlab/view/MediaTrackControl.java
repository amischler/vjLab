package com.vjlab.view;

import com.vjlab.model.MediaTrack;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/01/13
 * Time: 18:51
 */
public class MediaTrackControl extends TrackControl<MediaTrack> {

    @Override
    protected String getUserAgentStylesheet() {
        return MediaTrackControl.class.getResource("MediaTrackControl.css").toString();
    }
}
