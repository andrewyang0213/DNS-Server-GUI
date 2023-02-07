package org.openjfx.components;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class OutputDisplay {
    private TextArea testResultArea;

    // constructor to initialize the TextArea
    public OutputDisplay() {
        testResultArea = new TextArea();
        testResultArea.setPrefWidth(200);
        testResultArea.setPrefHeight(400);
    }

    // getter method for testResultArea
    public TextArea getTestResultArea() {
        return testResultArea;
    }

    // method to return the layout of the OutputDisplay
    public VBox getLayout() {
        return new VBox(testResultArea);
    }
}