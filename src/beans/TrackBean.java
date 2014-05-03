package beans;

import java.io.Serializable;


public class TrackBean implements Comparable<TrackBean>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5701119116914241234L;
	private int inventory_number;
	private int album_number;
	private String title;
	private String artist;
	private String writer;
	private String track_length;
	private int track_number;
	private String category;
	private String cover_img_name;
	private float cost_price;
	private float list_price;
	private float sale_price;
	private String date_entered;
	private int selling_state;
	private int removal_status;
	
	public TrackBean(){}
	
	public TrackBean(int inventory_number, int album_number, String title,
			String artist, String writer, String track_length,
			int track_number, String category, String cover_img_name,
			float cost_price, float list_price, float sale_price,
			String date_entered, int selling_state, int removal_status) {
		super();
		this.inventory_number = inventory_number;
		this.album_number = album_number;
		this.title = title;
		this.artist = artist;
		this.writer = writer;
		this.track_length = track_length;
		this.track_number = track_number;
		this.category = category;
		this.cover_img_name = cover_img_name;
		this.cost_price = cost_price;
		this.list_price = list_price;
		this.sale_price = sale_price;
		this.date_entered = date_entered;
		this.selling_state = selling_state;
		this.removal_status = removal_status;
	}
	
	public int getInventory_number() {
		return inventory_number;
	}
	public void setInventory_number(int inventory_number) {
		this.inventory_number = inventory_number;
	}
	public int getAlbum_number() {
		return album_number;
	}
	public void setAlbum_number(int album_number) {
		this.album_number = album_number;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTrack_length() {
		return track_length;
	}
	public void setTrack_length(String track_length) {
		this.track_length = track_length;
	}
	public int getTrack_number() {
		return track_number;
	}
	public void setTrack_number(int track_number) {
		this.track_number = track_number;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCover_img_name() {
		return cover_img_name;
	}
	public void setCover_img_name(String cover_img_name) {
		this.cover_img_name = cover_img_name;
	}
	public float getCost_price() {
		return cost_price;
	}
	public void setCost_price(float cost_price) {
		this.cost_price = cost_price;
	}
	public float getList_price() {
		return list_price;
	}
	public void setList_price(float list_price) {
		this.list_price = list_price;
	}
	public float getSale_price() {
		return sale_price;
	}
	public void setSale_price(float sale_price) {
		this.sale_price = sale_price;
	}
	public String getDate_entered() {
		return date_entered;
	}
	public void setDate_entered(String date_entered) {
		this.date_entered = date_entered;
	}
	public int getSelling_state() {
		return selling_state;
	}
	public void setSelling_state(int selling_state) {
		this.selling_state = selling_state;
	}
	public int getRemoval_status() {
		return removal_status;
	}
	public void setRemoval_status(int removal_status) {
		this.removal_status = removal_status;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null || getClass() != o.getClass())
			return false;
		
		TrackBean t = (TrackBean)o;
		
		if(this.inventory_number != t.inventory_number)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode(){
		return inventory_number;
	}

	@Override
	public String toString() {
		return "TrackBean [inventory_number=" + inventory_number
				+ ", album_number=" + album_number + ", title=" + title
				+ ", artist=" + artist + ", writer=" + writer
				+ ", track_length=" + track_length + ", track_number="
				+ track_number + ", category=" + category + ", cover_img_name="
				+ cover_img_name + ", cost_price=" + cost_price
				+ ", list_price=" + list_price + ", sale_price=" + sale_price
				+ ", date_entered=" + date_entered + ", selling_state="
				+ selling_state + ", removal_status=" + removal_status + "]";
	}

	@Override
	public int compareTo(TrackBean o) {

		return this.title.compareTo(o.getTitle());
	}
	
	
}