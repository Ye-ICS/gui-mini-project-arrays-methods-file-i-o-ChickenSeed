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
        // Create components
        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);

        Label promptLabel = new Label("Enter song title");

        TextField songInputField = new TextField();
        songInputField.setMaxWidth(150);
        songInputField.setPromptText("Song name"); 
        
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        // Buttons
        Button submissionBtn = new Button();
        submissionBtn.setText("Add Song");
        Button loadFileBtn = new Button("Load Songs From File");
        Button searchBtn = new Button("Search Song");
        Button addSongBtn = new Button("Add Song");
        Button removeBtn = new Button("Remove Song");
        Button saveBtn = new Button("Save Playlist");

        // Set up reactions (aka callbacks).
        addSongBtn.setOnAction(event -> MethodsPlaylist.onAddSong(songInputField, outputArea, playlist));
        removeBtn.setOnAction(event -> MethodsPlaylist.onRemoveSong(songInputField, outputArea, playlist));
        loadFileBtn.setOnAction(event -> MethodsPlaylist.onLoadSongs(outputArea, playlist));
        saveBtn.setOnAction(event -> MethodsPlaylist.onSaveSongs(playlist, outputArea));
        searchBtn.setOnAction(event -> MethodsPlaylist.onSearchSong(songInputField, outputArea, playlist));

        // Add components to the content box.
        contentBox.getChildren().addAll(promptLabel, songInputField, addSongBtn, removeBtn, loadFileBtn, saveBtn, searchBtn, outputArea);

        // Set up the window and display it.
        Scene scene = new Scene(contentBox, 300, 400);
        stage.setScene(scene);
        stage.setTitle("Playlist Manager");
        stage.show();
    }
}