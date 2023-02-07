package org.openjfx.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class UserInput {
    private TextField inputTextField;
    private Button submitButton;

    public UserInput() {
        inputTextField = new TextField();
        inputTextField.setPrefWidth(200);
        submitButton = new Button("Submit");
    }

    public HBox getLayout() {
        return new HBox(10, inputTextField, submitButton);
    }

    public TextField getInputTextField() {
        return inputTextField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }
}
