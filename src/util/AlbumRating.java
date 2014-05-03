package util;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import beans.TrackBean;

import da.DBManager;

public class AlbumRating {
	private int count;
	private float rating;
	private int albumId;
	private DBManager dbm;
	
	public AlbumRating(){
		count=0;
		rating=0.00F;
	}
	
	public AlbumRating(int albumId, DBManager dbm){
		this();
		this.dbm=dbm;
		if(albumId>0)
			this.albumId=albumId;
		
	}
	
	private float calculateRating(){
		ArrayList<TrackBean> tracks = dbm.getTrackByAlbum(albumId);
		tracks.size();
		for(int i=0;i<tracks.size();i++)
			if(dbm.getTrackRanking(tracks.get(i).getInventory_number())!=0.00F){
				rating+=dbm.getTrackRanking(tracks.get(i).getInventory_number());
				count++;
			}
		if(rating!=0.00F)
			rating/=count;
		return rating;
	}
	
	public void setAlbumId(int albumId){
		this.albumId=albumId;
	}
	
	public float getRating(){
		return albumId>0?calculateRating():null;
	}
	
	public static void main(String[] args){
		AlbumRating rating  = new AlbumRating(4, new DBManager());
		System.out.println("rating for album #4:"+rating.getRating());
	}
}