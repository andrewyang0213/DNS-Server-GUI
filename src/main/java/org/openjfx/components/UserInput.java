package org.openjfx.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class UserInput {
    private TextField inputTextField;
    private Button submitButton;

    // constructor to initialize the TextField and Button
    public UserInput() {
        inputTextField = new TextField();
        inputTextField.setPrefWidth(200);
        submitButton = new Button("Submit");
    }

    // method to return the layout of the UserInput
    public HBox getLayout() {
        return new HBox(10, inputTextField, submitButton);
    }

    // getter method for inputTextField
    public TextField getInputTextField() {
        return inputTextField;
    }

    // getter method for submitButton
    public Button getSubmitButton() {
        return submitButton;
    }
}
