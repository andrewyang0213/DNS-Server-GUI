package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.openjfx.components.*;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    UserInput userInput = new UserInput();
    OutputDisplay outputDisplay = new OutputDisplay();
    // Create PipedOutputStreams to redirect the standard output and error streams
    PipedOutputStream outputStream = new PipedOutputStream();
    // Create PipedInputStreams to receive the redirected standard output and error
    // streams
    PipedInputStream inputStream = new PipedInputStream(outputStream);
    // Redirect the standard output and error streams to the PipedOutputStreams
    System.setOut(new PrintStream(outputStream));
    InputOutputHandler inputOutputHandler = new InputOutputHandler(userInput, outputDisplay, inputStream);
    Thread inputOutputThread = new Thread(inputOutputHandler);
    inputOutputThread.start();

    primaryStage.setTitle("DNS Server GUI");
    primaryStage.setScene(new Scene(createLayout(userInput, outputDisplay), 450, 250));
    primaryStage.show();
  }

  private VBox createLayout(UserInput userInput, OutputDisplay outputDisplay) {
    VBox root = new VBox(10);
    root.getChildren().add(userInput.getLayout());
    root.getChildren().add(outputDisplay.getLayout());
    return root;
  }
}
