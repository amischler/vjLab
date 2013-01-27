package com.vjlab.controller;

import com.vjlab.model.LocationTrack;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 21/01/13
 * Time: 21:41
 */
public class LocationTrackPropertiesController implements Initializable, Controller<LocationTrack> {

    @FXML
    GridPane root;

    @FXML
    TextField locationTextField;

    @FXML
    Button openButton;

    private LocationTrack current;

    @Override
    public Node getNode() {
        return root;
    }

    @Override
    public void configure(LocationTrack locationTrack) {
        current = locationTrack;
        locationTextField.textProperty().bindBidirectional(locationTrack.locationProperty());
    }

    @Override
    public void unconfigure() {
        locationTextField.textProperty().unbindBidirectional(current.locationProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File file = new FileChooser().showOpenDialog(getNode().getScene().getWindow());
                current.setLocation(file.toURI().toString());
            }
        });
    }

}
