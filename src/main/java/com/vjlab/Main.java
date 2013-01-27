package com.vjlab;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.vjlab.fxml.FXMLUtil;
import com.vjlab.ioc.ApplicationModule;
import com.vjlab.ioc.ResourceModule;
import com.vjlab.view.RenderingStage;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 06/01/13
 * Time: 17:54
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final Injector injector = Guice.createInjector(new ApplicationModule(), new ResourceModule());
        Scene scene = new Scene(injector.getInstance(FXMLUtil.class).load("com/vjlab/view/vjLab.fxml", injector.getInstance(ResourceBundle.class)).<Parent>getRoot());
        stage.setScene(scene);
        stage.setWidth(640);
        stage.setHeight(480);
        stage.setTitle("vjLab");
        stage.show();
        RenderingStage renderingStage = injector.getInstance(RenderingStage.class);
        renderingStage.init();
        renderingStage.show();
    }

    public static void main(String... args) {
        Application.launch(args);
    }

}
