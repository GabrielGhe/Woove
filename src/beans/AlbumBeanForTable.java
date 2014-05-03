package beans;

import java.io.Serializable;

public class AlbumBeanForTable extends AlbumBean implements Serializable {
	private static final long serialVersionUID = -4252388729254013201L;
	private float rating;

	public AlbumBeanForTable() {
		super();
		rating = 0;
	}

	public AlbumBeanForTable(int albumID, String title, String releaseDate,
			String artist, String label, int numOfTracks, String dateEntered,
			double costPrice, double listPrice, double salePrice,
			int removalStatus, String genre, String imgName, float rating) {

		super(albumID, title, releaseDate, artist, label, numOfTracks,
				dateEntered, costPrice, listPrice, salePrice, removalStatus,
				genre, imgName);
		
		this.rating = rating;
	}
	
	public AlbumBeanForTable(AlbumBean al, float rating){
		super(al.getAlbumID(), al.getTitle(), al.getReleaseDate(), al.getArtist(), al.getLabel(), al.getNumOfTracks(),
				al.getDateEntered(), al.getCostPrice(), al.getListPrice(), al.getSalePrice(), al.getRemovalStatus(),
				al.getGenre(), al.getImgName());
		
		this.rating = rating;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	

}
