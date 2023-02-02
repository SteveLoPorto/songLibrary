package songLib;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML         
    private Button submitButton;
	private ToggleButton modeToggle;
	private Text modeText;
	private Button editButton;
	@FXML
    private Button deleteButton;

	@FXML
	Text errorMessage;
	@FXML
	 TextField year;
	@FXML
	 TextField album;
	@FXML
	 TextField artist;
	@FXML
	 TextField songName;
	@FXML
	 private ListView<Song> songList;
	
	private static String filepath = "Resources/songDB.csv";


	 static LinkedList<Song> songLinkedList = new LinkedList();
	 
	 static Song currentSong;
	

	//Adding song to list
	 
	 public static void loadSongs() throws IOException {
			songLinkedList.clear();
			File songsFile = new File(filepath);
			if(songsFile.exists()) {
			FileReader reader = new FileReader(filepath);
			BufferedReader csvReader = new BufferedReader(reader);
			String row;
				while ((row = csvReader.readLine()) != null) {
					try {
						String[] placeHolder = {"","","",""}; 
						String[] data = row.split("|");
						for(int i = 0; i < data.length; i++) {
						placeHolder[i] = data[i];
						}
						songLinkedList.add(new Song(placeHolder[0], placeHolder[1], placeHolder[2], placeHolder[3]));
					} catch (Exception e) {
					}

				}
				csvReader.close();
			}else{
				songsFile.createNewFile();
			}

		}
	 
	 
	 public void addSongButton(ActionEvent event) {
		 year.clear();
		 album.clear();
		 artist.clear();
		 songName.clear();
		 errorMessage.setText("");
		 songList.getSelectionModel().clearSelection();
		 
	 }
	 
	 public void submit(ActionEvent event) throws IOException {
		
		//Gets entered values
		String songNameEntered = songName.getText();
		String artistEntered = artist.getText();
		String albumEntered = album.getText();
		String yearEntered = year.getText();
		Song newSong = new Song(songNameEntered, artistEntered, albumEntered, yearEntered);
		
		if(songNameEntered == "" || artistEntered == "") {
			errorMessage.setText("*Must Enter Both Song and Artist*");
		}else if (songNameEntered.contains("|") || artistEntered.contains("|") || albumEntered.contains("|") || yearEntered.contains("|")) {
				errorMessage.setText("*Input Fields Cannot Contain a Vertical Bar (|)*");
		}else if(contains(newSong) >= 0) {
			//System.out.println("contains");
			
			errorMessage.setText("*This song already exists in library*");
		}else {					
			FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmPage.fxml"));
			root = loader.load();
			
			   
			SceneController sceneController = loader.getController(); 
			sceneController.setSong(newSong);
			sceneController.setSongName(songNameEntered);
			sceneController.setArtist(artistEntered);
			sceneController.setAlbum(albumEntered);
			sceneController.setYear(yearEntered);
			
			
			
			//Switch to confirm
			//Parent root = FXMLLoader.load(getClass().getResource("confirmPage.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	}
	 
	 public void delete(ActionEvent event) throws IOException {
			
		String songNameEntered = songName.getText();
		String artistEntered = artist.getText();
		String albumEntered = album.getText();
		String yearEntered = year.getText();
		Song newSong = new Song(songNameEntered, artistEntered, albumEntered, yearEntered);
		
		

				
		if(songNameEntered == "" || artistEntered == "") {
			errorMessage.setText("*Nothing is selected to delete*");
		}else if (songNameEntered.contains("|") || artistEntered.contains("|") || albumEntered.contains("|") || yearEntered.contains("|")) {
			errorMessage.setText("*Input Fields Cannot Contain a Vertical Bar (|)*");
		}else if(contains(newSong)==-1) {
			errorMessage.setText("*THIS SONG DOES NOT EXIST*");
			
			
		}else {
		
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteConfirmPage.fxml"));
		root = loader.load();
		
		
		   
		DeleteController deleteController = loader.getController(); 
		deleteController.setIndex(songLinkedList.indexOf(currentSong));
		deleteController.setSong(currentSong);
		deleteController.setSongName(songNameEntered);
		deleteController.setArtist(artistEntered);
		deleteController.setAlbum(albumEntered);
		deleteController.setYear(yearEntered);
		
		
		
		//Switch to confirm
		//Parent root = FXMLLoader.load(getClass().getResource("confirmPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
		}
	 }
		
	 
	 public void edit(ActionEvent event) throws IOException {
		
		String songNameEntered = songName.getText();
		String artistEntered = artist.getText();
		String albumEntered = album.getText();
		String yearEntered = year.getText();
		
		Song newSong = new Song(songNameEntered, artistEntered, albumEntered, yearEntered);

		
		if(songNameEntered == "" || artistEntered == "") {
			errorMessage.setText("*Nothing is selected to edit*");
		}else if (songNameEntered.contains("|") || artistEntered.contains("|") || albumEntered.contains("|") || yearEntered.contains("|")) {
			errorMessage.setText("*Input Fields Cannot Contain a Vertical Bar (|)*");
		}else {
		
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("editPageConfirm.fxml"));
		root = loader.load();
		
		
		   
		EditController editController = loader.getController(); 
		editController.setIndex(songLinkedList.indexOf(currentSong));
		editController.setSong(newSong);
		editController.setSongName(songNameEntered);
		editController.setArtist(artistEntered);
		editController.setAlbum(albumEntered);
		
		editController.setYear(yearEntered);
		
		
		
		//Switch to confirm
		//Parent root = FXMLLoader.load(getClass().getResource("confirmPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
		}
	 }
			
		 

		 
	 
	 
	 
	 public static void addToSongList(Song newSong) {
		 songLinkedList.add(newSong);
		 songLinkedList.sort(null);
	 }

	public int contains(Song addedSong) {
		
			String newSong = addedSong.toString().replaceAll(",", "");
			newSong = newSong.replaceAll("\\s","");
			newSong = newSong.toLowerCase();
			//System.out.println("New Song "+ newSong);
			
			
			Song[] songArray = songLinkedList.toArray(new Song[0]);
			
			
			for(int i = 0; i < songArray.length; i++) {
				
				String inSong = songArray[i].toString();
				inSong = inSong.replaceAll(",", "");
				inSong = inSong.replaceAll("\\s", "");
				inSong = inSong.toLowerCase();
				//System.out.println("Old Song "+inSong);
				if (inSong.compareTo(newSong) == 0){
					return i;
				}
			}
			return -1;

			
		}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		songLinkedList.sort(null);
		Song[] songArray = songLinkedList.toArray(new Song[0]);
		songList.getItems().addAll(songArray);
		
		
		if(currentSong == null) {
			if(songLinkedList.peekFirst() != null) {
			currentSong = songLinkedList.peekFirst();
			}
		}
		
		if(currentSong != null) {
		songList.getSelectionModel().select(currentSong);
		songName.setText(currentSong.getName());
		artist.setText(currentSong.getArtist());
		album.setText(currentSong.getAlbum());
		year.setText(currentSong.getYear());
		}
		

		songList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {

			@Override
			public void changed(ObservableValue<? extends Song> arg0, Song arg1, Song arg2) {
				// TODO Auto-generated method stub
				try {
					errorMessage.setText("");
					currentSong = songList.getSelectionModel().getSelectedItem();
					songName.setText(currentSong.getName());
					artist.setText(currentSong.getArtist());
					album.setText(currentSong.getAlbum());
					year.setText(currentSong.getYear());
					//System.out.print(currentSong.getName());
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			
				
			}
		
		
		});
		
		
		
		
		// TODO Auto-generated method stub
		
	}





	

	


}


	

/*
	
	public static void addToSongList(Song newSong) {
		
		String addedSong = newSong.getName().concat(newSong.getArtist()).replaceAll("\\s", "");
		System.out.println("This is addedSong: " + addedSong);
		if(songLinkedList.peekFirst() == null) {
			Controller.songLinkedList.add(newSong);
		}else{
			Song lastSong = songLinkedList.getLast();
			int lastIndex = songLinkedList.indexOf(lastSong);
			
			for(int i=0; i <= lastIndex; i++) {

				String compSong = songLinkedList.get(i).getName().concat(songLinkedList.get(i).getArtist()).replaceAll("\\s", "");
				System.out.println(compSong);
				int charPostion = 0;
				boolean isEqual = false;
				
				do {
				
				if(isEqual == true) {	
					
					if((charPostion > (compSong.length()-1))  && (charPostion > (addedSong.length()-1))) {
						songLinkedList.set(i, newSong);
						break;
					//comp is empty and new song still has things
					}else if((charPostion > (compSong.length()-1)) && (charPostion <= (addedSong.length()-1))) {
						if(i==lastIndex) {
							songLinkedList.add(newSong);
							}
						break;
					//comp has more letters left and song is empty 
					}else if( (charPostion <= (compSong.length()-1)) && (charPostion > (addedSong.length()-1))) {
						songLinkedList.add(i, newSong);
						break;
					}
				}
				
				char charOfComp = compSong.charAt(charPostion);
				char charOfNew = addedSong.charAt(charPostion);

				
				if(charOfComp > charOfNew) {
					songLinkedList.add(i, newSong);
					break;
					
				}else if(charOfComp < charOfNew) {
					if(i==lastIndex) {
					songLinkedList.add(newSong);

					}
					break;
					
				}else if(charOfComp == charOfNew) {
					isEqual = true;
					charPostion++;
				}		
					
				}while(isEqual == true);			
				
			}
			
			
			
		}
		

		
		
		
		
		
	}
	
*/
