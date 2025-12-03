import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Playlist Manager Application.
 */
public class PlaylistApp extends Application {
    private ArrayList<String> playlist = new ArrayList<>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create components to add.
        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);

        Label promptLabel = new Label();
        promptLabel.setText("Enter song title");

        TextField songInputBox = new TextField();
        songInputBox.setMaxWidth(150);
        songInputBox.setPromptText("Song name"); 
        
        TextArea messageBox = new TextArea();
        messageBox.setEditable(false);

        Button submissionBtn = new Button();
        submissionBtn.setText("Add Song");
        Button loadFileBtn = new Button("Load Songs From File");
        Button searchBtn = new Button("Search Song");

        // Set up reactions (aka callbacks).
        submissionBtn.setOnAction(event -> onAddSong(songInputBox, messageBox));
        loadFileBtn.setOnAction(event -> onLoadSongs(messageBox));
        searchBtn.setOnAction(event -> onSearchSong(songInputBox, messageBox));

        // Add components to the content box.
        contentBox.getChildren().add(promptLabel);
        contentBox.getChildren().add(songInputBox);
        contentBox.getChildren().add(submissionBtn);
        contentBox.getChildren().add(loadFileBtn);
        contentBox.getChildren().add(searchBtn);
        contentBox.getChildren().add(messageBox);

        // Set up the window and display it.
        Scene scene = new Scene(contentBox, 300, 300);
        stage.setScene(scene);
        stage.setTitle("Playlist Manager");
        stage.show();
    }

    /**
     * Handle the submission of a songs
     * @param inputBox  The TextField where the user types the song title
     * @param outputBox The TextArea where the submitted song titles are displayed
     */

    // Methods

    // Method to 
    void onAddSong(TextField inputBox, TextArea outputBox) {
        String song = inputBox.getText();
            if (!song.isEmpty()) {
                playlist.add(song);
                inputBox.clear();
                outputBox.setText(String.join("\n", playlist));
            }
        }

    // Loading method for songs from a file into playlist
    void onLoadSongs(TextArea outputBox) {
        playlist.clear();
        try {
            File file = new File("songs.txt"); // File name
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                playlist.add(scanner.nextLine()); // Add each line as a song
            }
            scanner.close();
            outputBox.setText(String.join("\n", playlist)); // Display loaded songs
        } catch (FileNotFoundException e) {
            outputBox.setText("Error: songs.txt not found.");
        }
    }

    // Search method for playlist
    void onSearchSong(TextField inputBox, TextArea outputBox) {
        String searchTerm = inputBox.getText();
        inputBox.clear();
        if (searchTerm.isEmpty()) {
            outputBox.setText("Please enter a song name to search.");
            return;
        }
        if (playlist.contains(searchTerm)) {
            outputBox.setText("Found: " + searchTerm);
        } else {
            outputBox.setText("Not found: " + searchTerm);
        }
    }
}
