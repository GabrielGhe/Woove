package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -691063736098947946L;
	private ArrayList<TrackBean> tracks;
	private ArrayList<AlbumBean> albums;
	
	public Cart()
	{
		tracks = new ArrayList<TrackBean>();
		albums = new ArrayList<AlbumBean>();
	}
	
	public Cart(ArrayList<TrackBean> tracks, ArrayList<AlbumBean> albums)
	{
		this.tracks = tracks;
		this.albums = albums;
	}

	public ArrayList<TrackBean> getTracks() {
		return tracks;
	}

	public void setTracks(ArrayList<TrackBean> tracks) {
		this.tracks = tracks;
	}

	public ArrayList<AlbumBean> getAlbum() {
		return albums;
	}

	public void setAlbum(ArrayList<AlbumBean> album) {
		this.albums = album;
	}
	
	
}
