package com.vjlab.controller;

import com.google.inject.Inject;
import com.vjlab.fxml.FXMLUtil;
import com.vjlab.model.LocationTrack;
import com.vjlab.model.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 21/01/13
 * Time: 21:47
 */
public class ControllerProviderImpl implements ControllerProvider {

    public final static Logger logger = LoggerFactory.getLogger(ControllerProviderImpl.class);

    @Inject
    FXMLUtil fxmlUtil;

    @Inject
    ResourceBundle resourceBundle;

    @Override
    public List<Controller> getControllers(Track track) {
        List<Controller> controllers = new LinkedList<Controller>();
        if (LocationTrack.class.isAssignableFrom(track.getClass())) {
            try {
                controllers.add(fxmlUtil.load("com/vjlab/view/LocationTrackProperties.fxml", resourceBundle).<Controller>getController());
            } catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }
        }
        return controllers;
    }

}
