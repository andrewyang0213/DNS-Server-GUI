package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

// run: mvn clean javafx:run

/**
 * JavaFX App
 */

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create the input text fields
    TextField inputTextField = new TextField();
    // Create the submit button

    Button submitButton = new Button("Submit");

    // Create the output text areas
    TextArea testResultArea = new TextArea();
    // Set the preferred width for the input text fields

    inputTextField.setPrefWidth(200);
    // Set the preferred width and height for the output text areas
    testResultArea.setPrefWidth(200);
    testResultArea.setPrefHeight(400);
    // Create a horizontal box to contain the input text fields and the submit
    // button
    HBox inputBox = new HBox(10, inputTextField, submitButton);

    // Create a vertical box to contain the input box and the output text areas
    VBox root = new VBox(10, inputBox, testResultArea);

    // Create the scene
    Scene scene = new Scene(root, 450, 250);
    // Set the title of the primary stage
    primaryStage.setTitle("DNS Server GUI");
    // Set the scene for the primary stage
    primaryStage.setScene(scene);
    // Show the primary stage
    primaryStage.show();

    // Create PipedOutputStreams to redirect the standard output and error streams
    PipedOutputStream outputStream = new PipedOutputStream();
    // Create PipedInputStreams to receive the redirected standard output and error
    // streams
    PipedInputStream inputStream = new PipedInputStream(outputStream);
    // Redirect the standard output and error streams to the PipedOutputStreams
    System.setOut(new PrintStream(outputStream));
    // Start the InputOutputHandler thread
    InputOutputHandler inputOutputHandler = new InputOutputHandler(inputTextField, submitButton,
        testResultArea, inputStream);
    Thread inputOutputHandlerThread = new Thread(inputOutputHandler);
    inputOutputHandlerThread.start();
  }
}
