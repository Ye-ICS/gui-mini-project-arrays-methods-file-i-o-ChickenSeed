import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

    /**
     * Handle the submission of a songs
     * @param inputBox  The TextField where the user types the song title
     * @param outputBox The TextArea where the submitted song titles are displayed
     */

public class MethodsPlaylist {

    // Add Song
    
    public static void onAddSong(TextField inputBox, TextArea outputBox, ArrayList<String> playlist) {
        String song = inputBox.getText();
        if (!song.isEmpty()) {
            playlist.add(song);
            inputBox.clear();
            outputBox.setText("Song added: " + song + "\n\nCurrent Playlist:\n" + String.join("\n", playlist));
        } else {
            outputBox.setText("Please enter a valid song name.");
        }
    }


    // Remove Song
    public static void onRemoveSong(TextField inputBox, TextArea outputBox, ArrayList<String> playlist) {
        String song = inputBox.getText().trim();
        if (playlist.remove(song)) {
            outputBox.setText(String.join("\n", playlist));
        } else {
            outputBox.setText("Song not found: " + song);
        }
        inputBox.clear();
    }

    // Load Songs from File
    public static void onLoadSongs(TextArea outputBox, ArrayList<String> playlist) {
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

    // Save Playlist to File
    public static void onSaveSongs(ArrayList<String> playlist) {
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

    // Search Song
    public static void onSearchSong(TextField inputBox, TextArea outputBox, ArrayList<String> playlist) {
        String searchTerm = inputBox.getText().trim();
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
