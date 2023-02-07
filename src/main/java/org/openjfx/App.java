package org.openjfx;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.openjfx.components.OutputDisplay;
import org.openjfx.components.UserInput;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


// Run with: mvn clean javafx:run

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    
    // Create instances of UserInput and OutputDisplay
    UserInput userInput = new UserInput();
    OutputDisplay outputDisplay = new OutputDisplay();
    
    // Create datastreams to capture and redirect standard input and output to the GUI
    PipedOutputStream outputStream = new PipedOutputStream();
    PipedInputStream inputStream = new PipedInputStream(outputStream);
    System.setOut(new PrintStream(outputStream));

    // Create instance of InputOutputHandler and start the thread
    InputOutputHandler inputOutputHandler = new InputOutputHandler(userInput, outputDisplay, inputStream);
    Thread inputOutputThread = new Thread(inputOutputHandler);
    inputOutputThread.start();
  
  // Set up the primary stage
    primaryStage.setTitle("DNS Server GUI");
    primaryStage.setScene(new Scene(createLayout(userInput, outputDisplay), 450, 250));
    primaryStage.show();
  }

  // Method to create the layout for the GUI
  private VBox createLayout(UserInput userInput, OutputDisplay outputDisplay) {
    VBox root = new VBox(10);
    root.getChildren().add(userInput.getLayout());
    root.getChildren().add(outputDisplay.getLayout());
    return root;
  }
}
