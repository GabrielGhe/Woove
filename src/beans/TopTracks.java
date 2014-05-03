package beans;

import java.io.Serializable;

public class TopTracks implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4985673533888014808L;
	private String title;
	private String artist;
	private String album;
	private String imagePath;
	private int inventory_number;
	
	

	public TopTracks(){}
	
	public TopTracks(String title, String artist, String album, String imagePath, int inventory_number) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.imagePath = imagePath;
		this.inventory_number = inventory_number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public int getInventory_number() {
		return inventory_number;
	}

	public void setInventory_number(int inventory_number) {
		this.inventory_number = inventory_number;
	}
}
