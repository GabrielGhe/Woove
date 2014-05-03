

package servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import beans.TrackBean;
import beans.TrackBeanForTable;
import da.DBManager;

/**
 * Servlet implementation class SearchResult
 * @author 0737019
 */
@WebServlet(name = "SearchResult", urlPatterns = { "/search_result" })
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchResult() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/client/search_results.jsp";
		String search = request.getParameter("search");

		// gettings all the tracks and albums with HashSet to remove duplicates
		Set<AlbumBean> albumsToAdd = new HashSet<AlbumBean>();
		Set<TrackBean> tracksToAdd = new HashSet<TrackBean>();

		// adding everything to the Sets
		tracksToAdd.addAll(dbm.getTrackByNamePub(search));
		tracksToAdd.addAll(dbm.getTrackByArtist(search));
		albumsToAdd.addAll(dbm.getAlbumsByArtist(search));
		albumsToAdd.addAll(dbm.getAlbumsByName(search));

		// getting all the tracks
		ArrayList<TrackBeanForTable> tracks = new ArrayList<TrackBeanForTable>();
		for (TrackBean tt : tracksToAdd) {
			String title = (dbm.getAlbumById(tt.getAlbum_number()).get(0)).getTitle();
			float rating = dbm.getTrackRanking(tt.getInventory_number());	
			tracks.add(new TrackBeanForTable(tt, title, rating));
		}

		ArrayList<AlbumBeanForTable> albums = new ArrayList<AlbumBeanForTable>();
		for (AlbumBean aa : albumsToAdd) {
			AlbumRating rator = new AlbumRating(aa.getAlbumID(), dbm);
			albums.add(new AlbumBeanForTable(aa, rator.getRating()));
		}

		// determine if only 1 track or 1 album was retrieved doesn't work yet
		if (tracks.size() == 1 && albums.size() == 0) {
			url = "/view_Track?track=" + tracks.get(0).getInventory_number();
		} else if (tracks.size() == 0 && albums.size() == 1) {
			url = "/view_Albums?album=" + albums.get(0).getAlbumID();
		} else {
			request.setAttribute("tracks", tracks);
			request.setAttribute("albums", albums);
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}