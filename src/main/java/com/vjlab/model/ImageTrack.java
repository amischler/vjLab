package com.vjlab.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/01/13
 * Time: 18:49
 */
public class ImageTrack extends LocationTrack {

    private final ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

    public ImageTrack() {
        locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (s2 != null) {
                    imageProperty.set(new Image(s2));
                }
            }
        });
    }

    public Image getImage() {
        return imageProperty.get();
    }

    public void setImage(Image image1) {
        this.imageProperty.set(image1);
    }

    public ObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

}
