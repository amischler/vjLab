package com.vjlab.view;

import com.vjlab.model.MediaTrack;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 07/01/13
 * Time: 23:14
 */
public class MediaTrackSkin extends LocationTrackSkin<MediaTrack, MediaTrackControl> implements Skin<MediaTrackControl> {

    private Node node;
    private static Map<MediaTrack, ObjectProperty<MediaPlayer>> mediaTrackMediaPlayerMap = new HashMap<MediaTrack, ObjectProperty<MediaPlayer>>();

    private ChangeListener<MediaTrack> trackChangeListener;

    public MediaTrackSkin(MediaTrackControl trackControl) {
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
        final MediaView mediaView = new MediaView();
        trackChangeListener = new ChangeListener<MediaTrack>() {
            @Override
            public void changed(ObservableValue<? extends MediaTrack> observableValue, MediaTrack mediaTrack, final MediaTrack mediaTrack2) {
                if (mediaTrack != null) {
                    mediaView.mediaPlayerProperty().unbind();
                    mediaTrackMediaPlayerMap.get(mediaTrack).get().stop();
                    mediaTrackMediaPlayerMap.remove(mediaTrack);
                }
                if (mediaTrack2 != null) {
                    bindMediaPlayer(mediaTrack2);
                    mediaView.mediaPlayerProperty().bind(mediaTrackMediaPlayerMap.get(mediaTrack2));
                }
            }
        };
        getSkinnable().trackProperty().addListener(trackChangeListener);
        if (getSkinnable().getTrack() != null) {
            bindMediaPlayer(getSkinnable().getTrack());
            mediaView.mediaPlayerProperty().bind(mediaTrackMediaPlayerMap.get(getSkinnable().getTrack()));
        }
        return mediaView;
    }

    protected synchronized void bindMediaPlayer(final MediaTrack mediaTrack) {
        if (!mediaTrackMediaPlayerMap.containsKey(mediaTrack)) {
            mediaTrackMediaPlayerMap.put(mediaTrack, new SimpleObjectProperty<MediaPlayer>());
        }
        ObjectProperty<MediaPlayer> mediaPlayerObjectProperty = mediaTrackMediaPlayerMap.get(mediaTrack);
        if (mediaPlayerObjectProperty.get() == null || mediaPlayerObjectProperty.get().getMedia() != mediaTrack.getMedia()) {
            mediaPlayerObjectProperty.bind(new ObjectBinding<MediaPlayer>() {
                {
                    bind(mediaTrack.mediaProperty());
                }

                @Override
                protected MediaPlayer computeValue() {
                    if (mediaTrack.getMedia() == null)
                        return null;
                    MediaPlayer mediaPlayer1 = new MediaPlayer(mediaTrack.getMedia());
                    mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
                    mediaPlayer1.setVolume(0.0);
                    mediaPlayer1.play();
                    return mediaPlayer1;
                }
            });
        }
    }

    @Override
    public void dispose() {
        getSkinnable().trackProperty().removeListener(trackChangeListener);
    }
}
