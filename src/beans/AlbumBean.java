package beans;

import java.io.Serializable;

public class AlbumBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8601910548843787422L;
	private int albumID;
	private String title;
	private String artist;
	private String genre;
	private int numOfTracks;
	private String releaseDate;
	private double listPrice;
	private double salePrice;
	private double costPrice;
	private String label;
	private int removalStatus;
	private String dateEntered;
	private String imgName;
	
	public AlbumBean()
	{
		albumID = 0;
		title = "";
		artist = "";
		genre = "";
		numOfTracks = 0;
		releaseDate = "";
		listPrice = 0;
		salePrice = 0;
		costPrice = 0;
		label = "";
		removalStatus = 0;
		dateEntered="";
		imgName="";
	}

	public AlbumBean(int albumID, String title, String releaseDate, String artist,
			String label, int numOfTracks, String dateEntered,
			double costPrice, double listPrice, double salePrice,
			int removalStatus, String genre, String imgName) {
	
		this.albumID = albumID;
		this.title = title;
		this.artist = artist;
		this.numOfTracks = numOfTracks;
		this.releaseDate = releaseDate;
		this.listPrice = listPrice;
		this.salePrice = salePrice;
		this.costPrice = costPrice;
		this.label = label;
		this.removalStatus = removalStatus;
		this.dateEntered=dateEntered;
		this.genre=genre;
		this.imgName=imgName;
	}

	public int getAlbumID() {
		return albumID;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNumOfTracks() {
		return numOfTracks;
	}

	public void setNumOfTracks(int numOfTracks) {
		this.numOfTracks = numOfTracks;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getRemovalStatus() {
		return removalStatus;
	}

	public void setRemovalStatus(int removalStatus) {
		this.removalStatus = removalStatus;
	}
	public String getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(String dateEntered) {
		this.dateEntered = dateEntered;
	}
	
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	
	
	@Override
	public String toString() {
		return "AlbumBean [albumID=" + albumID + ", title=" + title
				+ ", artist=" + artist + ", genre=" + genre + ", numOfTracks="
				+ numOfTracks + ", releaseDate=" + releaseDate + ", listPrice="
				+ listPrice + ", salePrice=" + salePrice + ", costPrice="
				+ costPrice + ", label=" + label + ", removalStatus="
				+ removalStatus + ", dateEntered=" + dateEntered + ", imgName="
				+ imgName + "]";
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == null || getClass() != o.getClass())
			return false;
		
		AlbumBean g = (AlbumBean)o;
		if(this.albumID != g.albumID)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode(){
		return albumID;
	}
}
