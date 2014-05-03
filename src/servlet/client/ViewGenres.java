package servlet.client;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.AlbumRating;

import beans.AlbumBean;
import beans.AlbumBeanForTable;
import beans.TopAlbums;
import beans.TopTracks;
import beans.TrackBean;
import beans.TrackBeanForTable;

import da.DBManager;

/**
 * Servlet implementation class ViewGenres
 * 
 * @co-author Brendan 
 * @co-author 0737019
 */
@WebServlet(name = "ViewGenres", urlPatterns = {"/viewGenres"})
public class ViewGenres extends HttpServlet {
	private static final long serialVersionUID = -5742799306859117637L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ViewGenres() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/client/view_genres.jsp";
		String genre = (String) request.getParameter("genre").trim();
		
		ArrayList<TrackBean> tracksToAdd = dbm.getTrackByGenrePub(genre);
		ArrayList<TopTracks> topTrack = new ArrayList<TopTracks>();
		ArrayList<AlbumBean> albumsToAdd = new ArrayList<AlbumBean>();
		ArrayList<AlbumBean> allAlbums = dbm.getAlbums();
		ArrayList<String[]> top = dbm.getTopSellers("2012-01-01", "2013-12-31");
		ArrayList<int[]> topAlbums = dbm.getTopAlbums("2012-01-01", "2013-12-31");
		ArrayList<TopAlbums> albumsToDisplay = new ArrayList<TopAlbums>();	
		
		//Loop through top tracks
		for(String[] item: top)
		{
			String[] trck = item;
			
			if(trck[0] != null)
			{
				TrackBean track = dbm.getTrackById(Integer.parseInt(trck[0])).get(0);
				if(track.getCategory().equalsIgnoreCase(genre))
				{
					ArrayList<AlbumBean> album = dbm.getAlbumById(track
							.getAlbum_number());
					TopTracks toAdd = new TopTracks(track.getTitle(), album.get(0).getTitle(),
							track.getArtist(), track.getCover_img_name(), track.getInventory_number());
					topTrack.add(toAdd);
				}
				if(topTrack.size() == 3)
					break;
			}
		}
		
		
		//Loop through top albums
		for(int[] aItem: topAlbums)
		{
			AlbumBean a = dbm.getAlbumById(aItem[0]).get(0);	

			if(a.getGenre().equalsIgnoreCase(genre))
			{
				TrackBean t = dbm.getTrackByAlbum(a.getAlbumID()).get(0);
				albumsToDisplay.add(new TopAlbums(a.getAlbumID(), a.getTitle(), a.getArtist(),t.getCover_img_name()));
			}
			if(albumsToDisplay.size() == 3)
				break;
		}
		
		for(AlbumBean ab: allAlbums){
			if(ab.getGenre().equalsIgnoreCase(genre))
			{
				albumsToAdd.add(ab);
			}
		}
		
		//getting all the tracks
		ArrayList<TrackBeanForTable> tracks = new ArrayList<TrackBeanForTable>();
		for (TrackBean tt : tracksToAdd) {
			tracks.add(new TrackBeanForTable(tt, dbm
					.getAlbumById(tt.getAlbum_number()).get(0).getTitle(), dbm
					.getTrackRanking(tt.getInventory_number())));
		}

		ArrayList<AlbumBeanForTable> albums = new ArrayList<AlbumBeanForTable>();
		for(AlbumBean aa : albumsToAdd){
			AlbumRating rator = new AlbumRating(aa.getAlbumID(), dbm);
			albums.add(new AlbumBeanForTable(aa, rator.getRating()));
		}
		
		request.setAttribute("tracksByGenre", tracks);
		request.setAttribute("albumsByGenre", albums);
		request.setAttribute("topTracksByGenre", topTrack);
		request.setAttribute("albumsToDisplay", albumsToDisplay);
		request.setAttribute("genre", genre.substring(0,1).toUpperCase()+genre.substring(1));
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}
}
