package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.openjfx.components.*;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UserInput userInput = new UserInput();
        OutputDisplay outputDisplay = new OutputDisplay();
        InputOutputHandler inputOutputHandler = new InputOutputHandler(userInput, outputDisplay);
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






