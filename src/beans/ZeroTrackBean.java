package beans;

import java.io.Serializable;

public class ZeroTrackBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2600124604335073261L;
	private int inventory_number;
	private String title;
	private String artist;
	private float price;
	
	public ZeroTrackBean(){
		this.inventory_number = 0;
		this.title = "";
		this.artist = "";
		this.price = 0;
	}
	
	public ZeroTrackBean(int inventory_number, String title, String artist, float price){
		this.inventory_number = inventory_number;
		this.title = title;
		this.artist = artist;
		this.price = price;
	}
	
	public int getInventory_number() {
		return inventory_number;
	}

	public void setInventory_number(int inventory_number) {
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
