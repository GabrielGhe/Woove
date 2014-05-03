package beans;

import java.io.Serializable;


public class TrackBeanWithSales implements Comparable<TrackBeanWithSales>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5701119116914241234L;
	private int inventoryNumber;
	private int albumNumber;
	private String title;
	private String artist;
	private String writer;
	private String trackLength;
	private int trackNumber;
	private String category;
	private String coverImgName;
	private float costPrice;
	private float listPrice;
	private float salePrice;
	private String dateEntered;
	private int removalStatus;
	private double totalSales;
	
	public TrackBeanWithSales(){}
	
	public TrackBeanWithSales(int inventory_number, int album_number, String title,
			String artist, String writer, String track_length,
			int track_number, String category, String cover_img_name,
			float cost_price, float list_price, float sale_price,
			String date_entered, int removal_status, double totalSales) {
		super();
		this.inventoryNumber = inventory_number;
		this.albumNumber = album_number;
		this.title = title;
		this.artist = artist;
		this.writer = writer;
		this.trackLength = track_length;
		this.trackNumber = track_number;
		this.category = category;
		this.coverImgName = cover_img_name;
		this.costPrice = cost_price;
		this.listPrice = list_price;
		this.salePrice = sale_price;
		this.dateEntered = date_entered;
		this.removalStatus = removal_status;
		this.totalSales = totalSales;
	}
	
	public int getInventoryNumber() {
		return inventoryNumber;
	}
	public void setInventory_number(int inventory_number) {
		this.inventoryNumber = inventory_number;
	}
	public int getAlbumNumber() {
		return albumNumber;
	}
	public void setAlbumNumber(int album_number) {
		this.albumNumber = album_number;
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
	public String getTrackLength() {
		return trackLength;
	}
	public void setTrackLength(String track_length) {
		this.trackLength = track_length;
	}
	public int getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(int track_number) {
		this.trackNumber = track_number;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCoverImgName() {
		return coverImgName;
	}
	public void setCoverImName(String cover_img_name) {
		this.coverImgName = cover_img_name;
	}
	public float getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(float cost_price) {
		this.costPrice = cost_price;
	}
	public float getListPrice() {
		return listPrice;
	}
	public void setListPrice(float list_price) {
		this.listPrice = list_price;
	}
	public float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(float sale_price) {
		this.salePrice = sale_price;
	}
	public String getDateEntered() {
		return dateEntered;
	}
	public void setDateEntered(String date_entered) {
		this.dateEntered = date_entered;
	}

	public int getRemovalStatus() {
		return removalStatus;
	}
	public void setRemovalStatus(int removal_status) {
		this.removalStatus = removal_status;
	}
	
	public double getTotalSales(){
		return totalSales;
	}
	
	public void setTotalSales(double totalSales){
		this.totalSales = totalSales;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null || getClass() != o.getClass())
			return false;
		
		TrackBeanWithSales t = (TrackBeanWithSales)o;
		
		if(this.inventoryNumber != t.inventoryNumber)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode(){
		return inventoryNumber;
	}

	@Override
	public String toString() {
		return "TrackBean [inventory_number=" + inventoryNumber
				+ ", album_number=" + albumNumber + ", title=" + title
				+ ", artist=" + artist + ", writer=" + writer
				+ ", track_length=" + trackLength + ", track_number="
				+ trackNumber + ", category=" + category + ", cover_img_name="
				+ coverImgName + ", cost_price=" + costPrice
				+ ", list_price=" + listPrice + ", sale_price=" + salePrice
				+ ", date_entered=" + dateEntered + ", removal_status=" 
				+ removalStatus + " totalSales = " + totalSales + "]";
	}

	@Override
	public int compareTo(TrackBeanWithSales o) {

		return this.title.compareTo(o.getTitle());
	}
	
	
}