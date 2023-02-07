package org.openjfx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

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

    public void start(Runnable command) {
        new Thread(() -> {
            try (PipedInputStream pis = new PipedInputStream();
                 PipedOutputStream pos = new PipedOutputStream(pis);
                 PrintStream ps = new PrintStream(pos, true)) {
                System.setOut(ps);
                command.run();
                BufferedReader reader = new BufferedReader(new InputStreamReader(pis));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    final String text = line;
                    Platform.runLater(() -> outputBox.appendText(text + "\n"));
                }
            } catch (Exception e) {
                final String errorMessage = e.toString();
                Platform.runLater(() -> outputBox.appendText(errorMessage + "\n"));
            }
        }).start();
    }
}

