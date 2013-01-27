package com.vjlab.fxml;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.vjlab.ioc.GuiceControllerFactory;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 21/01/13
 * Time: 21:49
 */
public class FXMLUtil {

    @Inject
    Injector injector;

    @Inject
    Provider<FXMLLoader> fxmlLoaderProvider;

    public FXMLLoader load(String url, ResourceBundle resourceBundle) throws IOException {
        return load(FXMLUtil.class.getClassLoader().getResource(url), resourceBundle);
    }

    public FXMLLoader load(URL url, ResourceBundle resourceBundle) throws IOException {
        FXMLLoader loader = fxmlLoaderProvider.get();
        loader.setControllerFactory(new GuiceControllerFactory(injector));
        loader.setLocation(url);
        loader.setResources(resourceBundle);
        loader.load();
        return loader;
    }

}
