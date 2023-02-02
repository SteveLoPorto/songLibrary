package songLib;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Label albumConfirm;

    @FXML
    private Label artistConfirm;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Label songConfirm;

    @FXML
    private Label yearConfirm;
    
	private Song newSong;
	
	private int index;
	
	public void setIndex(int index) {
		this.index = index;
	}
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

    @FXML
    void cancel(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("songList.fxml"));
		root = loader.load();
		
	
		
		//System.out.println(Controller.songLinkedList.peekFirst().toString());
		//Parent root = FXMLLoader.load(getClass().getResource("songList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void confirmDelete(ActionEvent event) throws IOException {
   
    	Controller.songLinkedList.remove(index);
		Controller.currentSong = Controller.songLinkedList.peekFirst();

    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("songList.fxml"));
		root = loader.load();
		
	
		
		//System.out.println(Controller.songLinkedList.peekFirst().toString());
		//Parent root = FXMLLoader.load(getClass().getResource("songList.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    	
    }

}

