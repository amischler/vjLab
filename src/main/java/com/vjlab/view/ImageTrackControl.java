package com.vjlab.view;

import com.vjlab.model.ImageTrack;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/01/13
 * Time: 18:47
 */
public class ImageTrackControl extends TrackControl<ImageTrack> {

    @Override
    protected String getUserAgentStylesheet() {
        return ImageTrackControl.class.getResource("ImageTrackControl.css").toString();
    }
}
