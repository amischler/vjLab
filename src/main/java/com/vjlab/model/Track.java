package com.vjlab.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.effect.BlendMode;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 07/01/13
 * Time: 22:35
 */
public abstract class Track {

    private final DoubleProperty opacity = new SimpleDoubleProperty();

    private final DoubleProperty rotate = new SimpleDoubleProperty();

    private final ObjectProperty<BlendMode> blendMode = new SimpleObjectProperty<BlendMode>();

    public BlendMode getBlendMode() {
        return blendMode.get();
    }

    public void setBlendMode(BlendMode blendMode) {
        this.blendMode.set(blendMode);
    }

    public ObjectProperty<BlendMode> blendModeProperty() {
        return blendMode;
    }

    public double getOpacity() {
        return opacity.get();
    }

    public void setOpacity(double opacity1) {
        this.opacity.set(opacity1);
    }

    public DoubleProperty opacityProperty() {
        return opacity;
    }

    public double getRotate() {
        return rotate.get();
    }

    public DoubleProperty rotateProperty() {
        return rotate;
    }

    public void setRotate(double rotation1) {
        this.rotate.set(rotation1);
    }

}
