package com.vjlab.controller;

import com.google.inject.Inject;
import com.vjlab.model.ImageTrack;
import com.vjlab.model.MediaTrack;
import com.vjlab.model.Track;
import com.vjlab.view.TrackControlFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 07/01/13
 * Time: 22:31
 */
public class VJLabController implements Initializable {

    @FXML
    ListView<Track> trackListView;

    @FXML
    MenuButton addButton;

    @FXML
    Button deleteButton;

    @FXML
    Slider opacitySlider;

    @FXML
    Slider rotationSlider;

    @FXML
    VBox propertiesBox;

    @FXML
    StackPane previewPane;

    @FXML
    ChoiceBox<BlendMode> blendModeChoiceBox;

    @Inject
    ObservableList<Track> trackObservableList;

    @Inject
    ControllerProvider controllerProvider;

    @Inject
    TrackControlFactory trackControlFactory;

    private List<Controller> controllerList = new LinkedList<Controller>();

    @Override
    public void initialize(URL url, final ResourceBundle resourceBundle) {
        trackListView.setItems(trackObservableList);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Track track = new ImageTrack();
                trackObservableList.add(track);
            }
        });
        addButton.getItems().addAll(
                MenuItemBuilder.create().onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Track track = new ImageTrack();
                        trackObservableList.add(track);
                    }
                }).text(resourceBundle.getString("image-track")).build(),
                MenuItemBuilder.create().onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Track track = new MediaTrack();
                        trackObservableList.add(track);
                    }
                }).text(resourceBundle.getString("media-track")).build());
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                trackObservableList.remove(trackListView.getSelectionModel().getSelectedItem());
            }
        });
        blendModeChoiceBox.getItems().addAll(BlendMode.values());
        trackListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Track>() {
            @Override
            public void changed(ObservableValue<? extends Track> observableValue, Track track, Track track2) {
                if (track != null) {
                    for (Controller controller : controllerList) {
                        controller.unconfigure();
                        int index = propertiesBox.getChildren().indexOf(controller.getNode());
                        propertiesBox.getChildren().remove(index);
                        propertiesBox.getChildren().remove(index);
                        unconfigure(track);
                        previewPane.getChildren().clear();
                        previewPane.getChildren().add(new Label(resourceBundle.getString("no-track-selected")));
                    }
                    controllerList.clear();
                }
                if (track2 != null) {
                    for (Controller controller : controllerProvider.getControllers(track2)) {
                        controller.configure(track2);
                        propertiesBox.getChildren().add(0, controller.getNode());
                        propertiesBox.getChildren().add(1, new Separator());
                        controllerList.add(controller);
                        configure(track2);
                        previewPane.getChildren().clear();
                        previewPane.getChildren().add(trackControlFactory.createTrackControl(track2));
                    }
                }
            }
        });

    }

    private void configure(Track track2) {
        opacitySlider.valueProperty().bindBidirectional(track2.opacityProperty());
        rotationSlider.valueProperty().bindBidirectional(track2.rotateProperty());
        blendModeChoiceBox.getSelectionModel().select(track2.getBlendMode());
        track2.blendModeProperty().bind(blendModeChoiceBox.getSelectionModel().selectedItemProperty());
    }

    private void unconfigure(Track track) {
        opacitySlider.valueProperty().unbindBidirectional(track.opacityProperty());
        rotationSlider.valueProperty().unbindBidirectional(track.rotateProperty());
        track.blendModeProperty().unbind();
    }
}
