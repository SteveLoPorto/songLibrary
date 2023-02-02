//
package songLib;

import java.io.*;
import java.util.*;
import java.util.jar.Attributes.Name;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import songLib.Controller;

public class SongLib extends Application 
	{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// set up FXML loader
		try {
			Controller.loadSongs();
			FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(getClass().getResource("songList.fxml"));
		
		
		// load the fxml
		AnchorPane root = (AnchorPane)loader.load();

		// get the controller (Do NOT create a new Controller()!!)
		// instead, get it through the loader
		Scene scene = new Scene(root, 623, 384);
		
		primaryStage.setScene(scene);
		
		primaryStage.show(); 
		
		primaryStage.setOnCloseRequest(event -> logout(primaryStage));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void logout(Stage primaryStage) {

		try {
			FileWriter csvWriter = new FileWriter("Resources/songDB.csv");
			int index = 0;
			while (Controller.songLinkedList.get(index) != null) {
			Song currentSong = Controller.songLinkedList.get(index);
			String songName = currentSong.getName();
			String artist = currentSong.getArtist();
			String album = currentSong.getAlbum();
			String year = currentSong.getYear();
			String entry = songName + "|" + artist + "|" + album + "|" + year;
			if(index == 0) {
				csvWriter.write(entry);
			}else {
				csvWriter.write("\n" + entry);

			}
			index++;
			csvWriter.flush();
			}
			csvWriter.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		launch(args);
	}

}
