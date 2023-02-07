package org.openjfx;

import java.io.IOException;
import java.io.PipedInputStream;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InputOutputHandler implements Runnable {

    private TextField inputTextField;
    private Button startButton;
    private Button submitButton;
    private TextArea outputTextArea1;
    private TextArea outputTextArea2;
    private PipedInputStream pipedInputStream1;
    private PipedInputStream pipedInputStream2;

    public InputOutputHandler(TextField inputTextField, Button startButton, Button submitButton,
            TextArea outputTextArea1, TextArea outputTextArea2, PipedInputStream pipedInputStream1,
            PipedInputStream pipedInputStream2) {
        this.inputTextField = inputTextField;
        this.startButton = startButton;
        this.submitButton = submitButton;
        this.outputTextArea1 = outputTextArea1;
        this.outputTextArea2 = outputTextArea2;
        this.pipedInputStream1 = pipedInputStream1;
        this.pipedInputStream2 = pipedInputStream2;
    }

    @Override
    public void run() {
        // Add an event handler to the submit button to handle user input
        startButton.setOnAction(event -> {
            /* var out1 = new OutputAppender(outputTextArea1);
            out1.start("cd target/classes/org/openjfx; java StartDNS"); */
            // Process the user inputs (e.g. in another program)
            // StartDNS.processInput();

            OutputAppender terminalOutput = new OutputAppender(outputTextArea1);
            terminalOutput.start(() -> StartDNS.processInput());

        });

        submitButton.setOnAction(event -> {
            // Get the user input from the input text fields
            String input2 = inputTextField.getText();
            // Clear the input text fields
            inputTextField.clear();

            var out2 = new OutputAppender(outputTextArea2);
            out2.start(input2);

        });

        // Continuously read data from the PipedInputStreams
        byte[] buffer1 = new byte[1024];
        byte[] buffer2 = new byte[1024];
        try {
            while (pipedInputStream1.read(buffer1) != -1 || pipedInputStream2.read(buffer2) != -1) {
                // Append the output to the respective output text areas
                if (pipedInputStream1.read(buffer1) != -1) {
                    outputTextArea1.appendText(new String(buffer1));
                }
                if (pipedInputStream2.read(buffer2) != -1) {
                    outputTextArea2.appendText(new String(buffer2));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}