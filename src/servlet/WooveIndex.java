/**
 * @coauthor 0737019
 */

package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.Cookies;
import util.CookieUtil;
import beans.AdBean;
import beans.AlbumBean;
import beans.Cart;
import beans.ClientBean;
import beans.RSSFeed;
import beans.RSSItem;
import beans.SurveyBean;
import beans.TopAlbums;
import beans.TopTracks;
import beans.TrackBean;
import da.DBManager;
import da.RSSParser;

@WebServlet(name = "WooveIndex", urlPatterns = { "/jsp/woove_index" })
public class WooveIndex extends HttpServlet {

	private static final long serialVersionUID = 7012896268681725174L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/woove_index.jsp";
		Cart cart;
		ClientBean client;

		// getting top tracks
		ArrayList<RSSItem> feed = new ArrayList<RSSItem>();
		ArrayList<TrackBean> lastThree = new ArrayList<TrackBean>();
		ArrayList<TopTracks> topTracks = new ArrayList<TopTracks>();
		ArrayList<TopAlbums> topAlbums = new ArrayList<TopAlbums>();
		ArrayList<String[]> top = dbm.getTopSellers("2012-01-01", "2013-12-31");
		ArrayList<int[]> topA = dbm.getTopAlbums("2012-01-01", "2013-12-31");

		// creating top tracks which will contain title, album title, artist and
		// image
		int tSize = (top.size() >= 3) ? 3 : top.size();
		for (int trackT = 0; trackT < tSize; trackT++) {
			int invnum = Integer.parseInt(top.get(trackT)[0]);
			TrackBean track = dbm.getTrackById(invnum).get(0);
			ArrayList<AlbumBean> album = dbm.getAlbumById(track
					.getAlbum_number());
			TopTracks toAdd = new TopTracks(track.getTitle(), album.get(0)
					.getTitle(), track.getArtist(), track.getCover_img_name(),
					track.getInventory_number());
			topTracks.add(toAdd);
		}

		Cookie[] cookies = request.getCookies();
		String cookie = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++)
				if (cookies[i].getName().equals("lastThree"))
					cookie = cookies[i].getValue();

			// check for &, if none, only one track, therefore no need for split
			if (!cookie.equals("")) {
				if (cookie.indexOf("&") > -1) {
					String[] lastThreeTracks = cookie.split("&");
					for (String trackLast : lastThreeTracks) {
						String theId = trackLast.split("=")[1];
						lastThree.add(dbm.getTrackById(Integer.parseInt(theId))
								.get(0));
					}
				} else
					lastThree.add(dbm.getTrackById(
							Integer.parseInt(cookie.substring(cookie
									.indexOf("=") + 1))).get(0));
			}
		}

		// creating top albums which will contain title, artist and
		// image
		int aSize = (topA.size() >= 3) ? 3 : topA.size();
		for (int albumT = 0; albumT < aSize; albumT++) {
			AlbumBean album = dbm.getAlbumById(topA.get(albumT)[0]).get(0);
			TrackBean track = dbm.getTrackByAlbum(album.getAlbumID()).get(0);
			TopAlbums toAdd = new TopAlbums(album.getAlbumID(),
					album.getTitle(), album.getArtist(),
					track.getCover_img_name());
			topAlbums.add(toAdd);
		}

		// Create a session
		HttpSession session = request.getSession();

		synchronized (session) {

			// Only Active until the browser is closed
			session.setMaxInactiveInterval(-1);

			// If there is no cart, create one
			if (session.getAttribute("Cart") == null)
				cart = new Cart();
			else
				cart = (Cart) session.getAttribute("Cart");

			client = (ClientBean) session.getAttribute("Client");

			session.setAttribute("Cart", cart);
		}
		if (session.getAttribute("voted") == null) {
			String vote = request.getParameter("survey");
			if (vote != null) {
				SurveyBean survey = dbm.getActiveSurvey();
				switch (vote) {
				case "1":
					survey.setA1Count(survey.getA1Count() + 1);
					session.setAttribute("voted", true);
					break;
				case "2":
					survey.setA2Count(survey.getA2Count() + 1);
					session.setAttribute("voted", true);
					break;
				case "3":
					survey.setA3Count(survey.getA3Count() + 1);
					session.setAttribute("voted", true);
					break;
				case "4":
					survey.setA4Count(survey.getA4Count() + 1);
					session.setAttribute("voted", true);
					break;
				}
				dbm.updateSurvey(survey);
			}
		}
		if (dbm.getActiveRSS() != null) {
			RSSParser parser = new RSSParser(dbm.getActiveRSS().getLink());
			feed = parser.getRSSItem();
		}
		ArrayList<AdBean> ads = new ArrayList<AdBean>();
		ads = dbm.getActiveAds();

		SurveyBean survey = new SurveyBean();
		survey = dbm.getActiveSurvey();

		// Last 3 tracks for dispaly
		ArrayList<TrackBean> lastTh = dbm.getLastThreeTracksPub();
		ArrayList<TopTracks> lastThreeT = new ArrayList<TopTracks>();

		for (TrackBean tra : lastTh) {
			AlbumBean alb = dbm.getAlbumById(tra.getAlbum_number()).get(0);
			lastThreeT.add(new TopTracks(tra.getTitle(), alb.getTitle(), tra
					.getArtist(), tra.getCover_img_name(), tra
					.getInventory_number()));
		}

		// This is for the session getting the tracks with similar genre
		String genre = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++)
				if (cookies[i].getName().equals("genre"))
					genre = cookies[i].getValue();
		}

		if (genre.equals("")) {
			if (session.getAttribute("Client") != null)
				genre = client.getLastSearch();
		}

		// This is for the tracks given the genre cookie
		ArrayList<TrackBean> genreCookieTracksTmp = dbm
				.getTrackByGenrePub(genre);
		ArrayList<TrackBean> genreCookieTracks = new ArrayList<TrackBean>();

		int i = (int) (Math.random() * genreCookieTracksTmp.size());
		if (genreCookieTracksTmp.size() > 0)
			for (; genreCookieTracks.size() < 3; i = (int) (Math.random() * genreCookieTracksTmp
					.size()))
				if (!genreCookieTracks.contains(genreCookieTracksTmp.get(i)))
					genreCookieTracks.add(genreCookieTracksTmp.get(i));

		// This is for getting recommended tracks from genre in database
		ArrayList<TrackBean> genreTracksTmp = null;
		ArrayList<TrackBean> genreTracks = null;
		if (client != null) {
			if (!client.getLastSearch().equals("")) {
				genreTracksTmp = dbm.getTrackByGenrePub(client.getLastSearch());
				genreTracks = new ArrayList<TrackBean>();
				int j = (int) (Math.random() * genreTracksTmp.size());
				if (j > 0)
					for (; genreTracks.size() < 3; j = (int) (Math.random() * genreTracksTmp
							.size()))
						if (!genreTracks.contains(genreTracksTmp.get(j)))
							genreTracks.add(genreTracksTmp.get(j));
			}
		}

		// This is for the tracks on sale
		ArrayList<TrackBean> trackOnSaleTemp = dbm.getTracksOnSale();
		ArrayList<TrackBean> trackOnSale = new ArrayList<TrackBean>();
		ArrayList<AlbumBean> albumOnSaleTemp = dbm.getAlbumsOnSale();
		ArrayList<AlbumBean> albumOnSale = new ArrayList<AlbumBean>();
		Random random = new Random();
		int onSaleSize = 2;

		if (onSaleSize > trackOnSaleTemp.size())
			onSaleSize = trackOnSaleTemp.size();

		for (int x = 0; x < onSaleSize; x++) {
			int randomNum = random.nextInt(trackOnSaleTemp.size());
			trackOnSale.add(trackOnSaleTemp.remove(randomNum));
		}

		if (onSaleSize > albumOnSaleTemp.size())
			onSaleSize = albumOnSaleTemp.size();

		for (int x = 0; x < onSaleSize; x++) {
			int randomNum = random.nextInt(albumOnSaleTemp.size());
			albumOnSale.add(albumOnSaleTemp.remove(randomNum));
		}

		request.setAttribute("genreTracks", genreTracks);
		request.setAttribute("genreCookieTracks", genreCookieTracks);
		request.setAttribute("albumOnSale", albumOnSale);
		request.setAttribute("trackOnSale", trackOnSale);
		request.setAttribute("rssFeed", feed);
		// Changed from lastThree which are the cookies to lastThreeT which are
		// the 3 most recent tracks
		request.setAttribute("lastThreeT", lastThreeT);
		// lastThree which are cookies are not recentThree
		request.setAttribute("recentThree", lastThree);
		request.setAttribute("surveys", survey);
		request.setAttribute("ads", ads);
		request.setAttribute("topTracks", topTracks);
		request.setAttribute("topAlbums", topAlbums);
		// forward request and response to the view
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}