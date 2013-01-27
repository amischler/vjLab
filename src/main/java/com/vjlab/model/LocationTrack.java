package com.vjlab.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/01/13
 * Time: 18:41
 */
public class LocationTrack extends Track {

    private StringProperty location = new SimpleStringProperty();

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location1) {
        this.location.set(location1);
    }

    public StringProperty locationProperty() {
        return location;
    }
}
