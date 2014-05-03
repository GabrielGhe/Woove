package beans;

import java.io.Serializable;

public class TrackBeanForTable extends TrackBean implements Serializable {

	private static final long serialVersionUID = 5758674451767419121L;
	private String album;
	private float rating;

	public TrackBeanForTable() {
		super();
		album = "";
		rating = 0.00F;
	}

	public TrackBeanForTable(int inventory_number, int album_number,
			String title, String artist, String writer, String track_length,
			int track_number, String category, String cover_img_name,
			float cost_price, float list_price, float sale_price,
			String date_entered, int selling_state, int removal_status,
			String album, float rating) {

		super(inventory_number, album_number, title, artist, writer,
				track_length, track_number, category, cover_img_name,
				cost_price, list_price, sale_price, date_entered,
				selling_state, removal_status);
		this.album = album;
		this.rating = rating;
	}

	public TrackBeanForTable(TrackBean t, String album, float rating) {
		super(t.getInventory_number(), t.getAlbum_number(), t.getTitle(), t
				.getArtist(), t.getWriter(), t.getTrack_length(), t
				.getTrack_number(), t.getCategory(), t.getCover_img_name(), t
				.getCost_price(), t.getList_price(), t.getSale_price(), t
				.getDate_entered(), t.getSelling_state(), t.getRemoval_status());
		this.album = album;
		this.rating = rating;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
