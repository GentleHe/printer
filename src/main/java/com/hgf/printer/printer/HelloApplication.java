package com.hgf.printer.printer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        Panel panel = new Panel("This is the title");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        Button button = new Button("Hello BootstrapFX");
        button.getStyleClass().setAll("btn","btn-danger");                     //(2)
        content.setCenter(button);

        content.setRight(new Button("AAA"));

        panel.setBody(content);

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)

        stage.setTitle("BootstrapFX");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("init");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("stop");
    }
}