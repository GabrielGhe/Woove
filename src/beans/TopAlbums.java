package beans;

import java.io.Serializable;

public class TopAlbums implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3302319529502078660L;
	private String albumTitle;
	private String artist;
	private String imagePath;
	private int albumID;
	
	public TopAlbums(){}
	
	public TopAlbums(int albumID, String albumTitle, String artist, String imagePath) {
		super();
		this.albumTitle = albumTitle;
		this.artist = artist;
		this.imagePath = imagePath;
		this.albumID = albumID;
	}
	
	public int getAlbumID() {
		return albumID;
	}

	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	
	
}
