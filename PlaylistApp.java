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
import java.io.PrintWriter;

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

        Label promptLabel = new Label("Enter song title");

        TextField songInputBox = new TextField();
        songInputBox.setMaxWidth(150);
        songInputBox.setPromptText("Song name"); 
        
        TextArea messageBox = new TextArea();
        messageBox.setEditable(false);

        // Buttons
        Button submissionBtn = new Button();
        submissionBtn.setText("Add Song");
        Button loadFileBtn = new Button("Load Songs From File");
        Button searchBtn = new Button("Search Song");
        Button addSongBtn = new Button("Add Song");
        Button removeBtn = new Button("Remove Song");
        Button saveBtn = new Button("Save Playlist");

        // Set up reactions (aka callbacks).
        addSongBtn.setOnAction(event -> onAddSong(songInputBox, messageBox));
        removeBtn.setOnAction(event -> onRemoveSong(songInputBox, messageBox));
        submissionBtn.setOnAction(event -> onAddSong(songInputBox, messageBox));
        loadFileBtn.setOnAction(event -> onLoadSongs(messageBox));
        saveBtn.setOnAction(event -> onSaveSongs());
        searchBtn.setOnAction(event -> onSearchSong(songInputBox, messageBox));

        // Add components to the content box.
        contentBox.getChildren().addAll(promptLabel, songInputBox, addSongBtn, removeBtn, loadFileBtn, saveBtn, searchBtn, messageBox);

        // Set up the window and display it.
        Scene scene = new Scene(contentBox, 300, 400);
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
            File file = new File("songs.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                playlist.add(scanner.nextLine());
            }
            scanner.close();
            outputBox.setText(String.join("\n", playlist));
        } catch (FileNotFoundException e) {
            outputBox.setText("Error: songs.txt not found.");
        }
    }

    void onSaveSongs() {
        try {
            PrintWriter writer = new PrintWriter("songs.txt");
            for (String song : playlist) {
                writer.println(song);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error saving playlist.");
        }
    }

    // Method for removing song
    void onRemoveSong(TextField inputBox, TextArea outputBox) {
        String song = inputBox.getText();
        if (playlist.remove(song)) {
            outputBox.setText(String.join("\n", playlist));
        } else {
            outputBox.setText("Song not found: " + song);
        }
        inputBox.clear();
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