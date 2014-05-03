package beans;

import java.io.Serializable;

public class InvoiceDetailsBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1774475895535273690L;
	private int id;
	private int sale_number;
	private int inventory_number;
	private int album_number;
	private int tracks_in_album;
	private float price_of_album;
	private float price_at_sale;
	private int removal_status;
	
	public InvoiceDetailsBean(){}
	
	public InvoiceDetailsBean(int id, int sale_number, int inventory_number, int album_number, int tracks_in_album,
			float price_of_album, float price_at_sale, int removal_status) {
		super();
		this.id = id;
		this.sale_number = sale_number;
		this.inventory_number = inventory_number;
		this.album_number = album_number;
		this.tracks_in_album=tracks_in_album;
		this.price_of_album = price_of_album;
		this.price_at_sale = price_at_sale;
		this.removal_status=removal_status;
	}
	
	public int getAlbum_number() {
		return album_number;
	}

	public void setAlbum_number(int album_number) {
		this.album_number = album_number;
	}

	public int getTracks_in_album() {
		return tracks_in_album;
	}

	public void setTracks_in_album(int tracks_in_album) {
		this.tracks_in_album = tracks_in_album;
	}

	public float getPrice_of_album() {
		return price_of_album;
	}

	public void setPrice_of_album(float price_of_album) {
		this.price_of_album = price_of_album;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSale_number() {
		return sale_number;
	}
	public void setSale_number(int sale_number) {
		this.sale_number = sale_number;
	}
	public int getInventory_number() {
		return inventory_number;
	}
	public void setInventory_number(int inventory_number) {
		this.inventory_number = inventory_number;
	}
	public float getPrice_at_sale() {
		return price_at_sale;
	}
	public void setPrice_at_sale(float price_at_sale) {
		this.price_at_sale = price_at_sale;
	}
	
	@Override
	public String toString() {
		return "InvoiceDetailsBean [id=" + id + ", sale_number=" + sale_number
				+ ", inventory_number=" + inventory_number + ", album_number="
				+ album_number + ", tracks_in_album=" + tracks_in_album
				+ ", price_of_album=" + price_of_album + ", price_at_sale="
				+ price_at_sale + "]";
	}

	public int getRemoval_status() {
		return removal_status;
	}

	public void setRemoval_status(int removal_status) {
		this.removal_status = removal_status;
	}
	
	
}