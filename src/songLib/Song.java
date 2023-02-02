package songLib;

public class Song implements Comparable<Song>{
	private String name;
	private String artist;
	private String album;
	private String year;
	
	public Song(String name, String artist, String album, String year) {
		this.name=name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public Song(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String toString() {
		return (name + "," + " " + artist);
	}

	@Override
	public int compareTo(Song o) {
		// TODO Auto-generated method stub
		String nameSongAdj = name;
		nameSongAdj = nameSongAdj.replaceAll(",", "");
		nameSongAdj = nameSongAdj.replaceAll("\\s", "");
		nameSongAdj = nameSongAdj.toLowerCase();
		String oSongName = o.name;
		oSongName = oSongName.replaceAll(",", "");
		oSongName = oSongName.replaceAll("\\s", "");
		oSongName = oSongName.toLowerCase();
		
		String nameArtistAdj = name;
		nameArtistAdj = nameArtistAdj.replaceAll(",", "");
		nameArtistAdj = nameArtistAdj.replaceAll("\\s", "");
		nameArtistAdj = nameArtistAdj.toLowerCase();
		String oArtistName = o.name;
		oArtistName = oArtistName.replaceAll(",", "");
		oArtistName = oArtistName.replaceAll("\\s", "");
		oArtistName = oArtistName.toLowerCase();
		
		
		int ret = nameSongAdj.compareTo(oSongName);
		if (ret == 0){
			ret = nameArtistAdj.compareTo(oArtistName);
		}
		return ret;
	}

	

}