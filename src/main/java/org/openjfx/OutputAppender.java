package org.openjfx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class OutputAppender {
    private TextArea outputBox;

    public OutputAppender(TextArea outputBox) {
        this.outputBox = outputBox;
    }

    public void start(String command) {
        new Thread(() -> {
            try {
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String line;
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

