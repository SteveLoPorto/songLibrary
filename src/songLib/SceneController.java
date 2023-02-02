package songLib;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController {
	
	@FXML
	private Button confirmButton;
	@FXML
	private Label songConfirm;
	@FXML
	private Label artistConfirm;
	@FXML
	private Label albumConfirm;
	@FXML
	private Label yearConfirm;
	
	private Song newSong;
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	 
	public void setSong(Song song) {
		this.newSong = song;
		
	}
	
	public void setSongName(String songName) {
		songConfirm.setText(songName);
		
	}
	
	public void setArtist(String artistName) {
		artistConfirm.setText(artistName);
	}

	public void setAlbum(String albumName) {
		albumConfirm.setText(albumName);
		
	}
	
	public void setYear(String year) {
		yearConfirm.setText(year);
		
	}
	
	public void confirm(ActionEvent event) throws IOException {
		

		
		Controller.addToSongList(newSong);
		Controller.currentSong = newSong;
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("songList.fxml"));
		root = loader.load();

		//System.out.println(Controller.songLinkedList.peekFirst().toString());
		//Parent root = FXMLLoader.load(getClass().getResource("songList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void confirmEdit(ActionEvent event) throws IOException {
		
		
		Controller.addToSongList(newSong);
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("songList.fxml"));
		root = loader.load();

		//System.out.println(Controller.songLinkedList.peekFirst().toString());
		//Parent root = FXMLLoader.load(getClass().getResource("songList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void cancel(ActionEvent event) throws IOException {
			
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("songList.fxml"));
		root = loader.load();
		
	
		
		//System.out.println(Controller.songLinkedList.peekFirst().toString());
		//Parent root = FXMLLoader.load(getClass().getResource("songList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToSongLib(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("songList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	
	
}
