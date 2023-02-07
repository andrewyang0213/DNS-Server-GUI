package org.openjfx;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class OutputAppender {
    private TextArea outputBox;

    // constructor to initialize the outputBox
    public OutputAppender(TextArea outputBox) {
        this.outputBox = outputBox;
    }

    // method to start the process of appending the output to the outputBox
    public void start(String command) {
        new Thread(() -> {
            try {
                // Executing the command
                Process process = Runtime.getRuntime().exec(command);
                // Reading the output from the process
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String line;
                // Appending the output line by line to the outputBox
                while ((line = reader.readLine()) != null) {
                    final String text = line;
                    Platform.runLater(() -> outputBox.appendText(text + "\n"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

