import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Template JavaFX application.
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    

    @Override
    public void start(Stage stage) {
        // BorderPane: Main Layout
        BorderPane root = new BorderPane();

        // Top: Game title
        Label titleLabel = new Label("Connect 4");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        // Center: Game Board
        FlowPane boardPane = new FlowPane(); 
        boardPane.setPrefWrapLength(350);
        boardPane.setAlignment(Pos.CENTER);
        root.setCenter(boardPane);

        // Bottom: Control Buttons
        HBox controlBox = new HBox(10); 
        controlBox.setAlignment(Pos.CENTER);
        Button resetBtn = new Button("Reset Game");
        controlBox.getChildren().add(resetBtn);
        root.setBottom(controlBox);

        // Scene
        Scene scene = new Scene(root, 400, 450);
        stage.setScene(scene);
        stage.setTitle("Connect 4");
        stage.show();
    }

    /**
     * Handle the submission of a thought.
     * @param inputBox  The TextField where the user types their thought.
     * @param outputBox The TextArea where the submitted thoughts are displayed.
     */

    void onSubmitThought(TextField inputBox, TextArea outputBox) {
        String text = inputBox.getText();
        inputBox.clear();
        System.out.println("Interesting thought: " + text);
        outputBox.appendText("Interesting thought: " + text + "\n");
    }
}

