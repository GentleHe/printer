package com.hgf.printer.component;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ComponentUtil {

    public static HBox getLabel(String label, String text) {
        HBox hBox = new HBox();
        hBox.getChildren().add(new Label(label));
        hBox.getChildren().add(new Text(text));
        return hBox;
    }
}
