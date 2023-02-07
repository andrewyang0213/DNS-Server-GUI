package org.openjfx.components;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class OutputDisplay {
    private TextArea testResultArea;

    public OutputDisplay() {
        testResultArea = new TextArea();
        testResultArea.setPrefWidth(200);
        testResultArea.setPrefHeight(400);
    }

    public TextArea getTestResultArea() {
        return testResultArea;
    }

    public VBox getLayout() {
        return new VBox(testResultArea);
    }
}