package com.vjlab.view;

import com.vjlab.model.ImageTrack;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.image.ImageView;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 07/01/13
 * Time: 23:14
 */
public class ImageTrackSkin extends LocationTrackSkin<ImageTrack, ImageTrackControl> implements Skin<ImageTrackControl> {

    private Node node;
    private ChangeListener<? super String> locationListener;
    private ImageView imageView;
    private ChangeListener<ImageTrack> trackChangeListener;

    public ImageTrackSkin(ImageTrackControl trackControl) {
        super(trackControl);
    }

    @Override
    public Node getNode() {
        if (node == null) {
            node = createNode();
        }
        return node;
    }

    private Node createNode() {
        imageView = new ImageView();
        imageView.imageProperty().bind(getSkinnable().getTrack().imageProperty());
        trackChangeListener = new ChangeListener<ImageTrack>() {
            @Override
            public void changed(ObservableValue<? extends ImageTrack> observableValue, ImageTrack imageTrack, ImageTrack imageTrack2) {
                if (imageTrack != null) {
                    imageView.imageProperty().unbind();
                }
                if (imageTrack2 != null) {
                    imageTrack2.imageProperty().bind(imageTrack2.imageProperty());
                }
            }
        };
        getSkinnable().trackProperty().addListener(trackChangeListener);

        return imageView;
    }

    @Override
    public void dispose() {
        getSkinnable().trackProperty().removeListener(trackChangeListener);
    }
}
