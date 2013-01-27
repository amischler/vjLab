package com.vjlab.controller;

import com.google.inject.ImplementedBy;
import com.vjlab.model.Track;

import java.util.List;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 21/01/13
 * Time: 21:37
 */
@ImplementedBy(ControllerProviderImpl.class)
public interface ControllerProvider {

    /**
     * Get all edition controllers for this track.
     *
     * @param track
     * @return
     */
    public List<Controller> getControllers(Track track);

}
