package org.openjfx;

import java.io.IOException;
import java.io.PipedInputStream;

import org.openjfx.components.OutputDisplay;
import org.openjfx.components.UserInput;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

class InputOutputHandler implements Runnable {
    private PipedInputStream inputStream;
    private Button submitButton;
    private TextField inputTextField;
    private TextArea testResultArea;

    InputOutputHandler(UserInput userInput, OutputDisplay outputDisplay) {
        this.inputTextField = userInput.getInputTextField();
        this.submitButton = userInput.getSubmitButton();
        this.testResultArea = outputDisplay.getTestResultArea();
    }

    @Override
    public void run() {

        // Add an event handler to the submit button to handle user input
        submitButton.setOnAction(event -> {
            // Get the user input from the input text fields
            String testInput = inputTextField.getText();
            // Clear the input text fields
            inputTextField.clear();

            var resultAppender = new OutputAppender(testResultArea);
            resultAppender.start(testInput);

        });

        // Continuously read data from the PipedInputStreams
        byte[] buffer2 = new byte[1024];
        try {
            while (inputStream.read(buffer2) != -1) {
                // Append the output to the respective output text areas
                if (inputStream.read(buffer2) != -1) {
                    testResultArea.appendText(new String(buffer2));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}