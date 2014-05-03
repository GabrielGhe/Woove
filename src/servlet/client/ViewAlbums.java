package servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AlbumBean;
import beans.Cart;
import beans.ClientBean;
import beans.ReviewBean;
import beans.TrackBean;

import da.DBManager;

/**
 * Servlet implementation class ViewAlbums
 * 
 * @author Brendan
 * @co-author 0737019 & 1312040
 * 
 */
@WebServlet("/view_Albums")
public class ViewAlbums extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAlbums() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/client/view_album.jsp";

		HttpSession session = request.getSession();
		Cart cart;
		
		
		String trackId;
		if(request.getParameter("album").indexOf(';')>-1)
			trackId = request.getParameter("album").substring(0,request.getParameter("album").indexOf(';'));
		else
			trackId= request.getParameter("album").trim();
		
		
		int albumID = Integer.parseInt(trackId);
		synchronized(session){
			cart = (Cart)session.getAttribute("Cart");
		
			//Check if there is a purchase
			if(request.getParameter("purchaseAlbum") != null)
			{
				ArrayList<AlbumBean> as = cart.getAlbum();
				AlbumBean a = dbm.getAlbumById(albumID).get(0);
				ArrayList<TrackBean> tracksInAlbum = dbm.getTrackByAlbum(a.getAlbumID());
				ArrayList<TrackBean> ts = cart.getTracks();
	
				//If the client already has track from the album remove them
				for(TrackBean tb:tracksInAlbum){
					if(ts.contains(tb))
						ts.remove(tb);
				}
				//If the album is already in the cart don't add it again
				if(!as.contains(a)){
					as.add(a);
					cart.setAlbum(as);
				}
				session.setAttribute("Cart", cart);
			}
		}
		
		
		AlbumBean album = dbm.getAlbumById(albumID).get(0);
		ArrayList<TrackBean> tracks = dbm.getTrackByAlbum(album.getAlbumID());
		ArrayList<AlbumBean> similar = dbm.getAlbumsByGenre(album.getGenre());
		Collections.shuffle(similar);
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		
			for(TrackBean tr: tracks){
				reviews.addAll(dbm.getReviewApprovedById(tr.getInventory_number()));
			}
			
		
		//Save the genre
		synchronized(session){
			if(session.getAttribute("Client") != null){
				ClientBean c = (ClientBean) session.getAttribute("Client");
				c.setLastSearch(album.getGenre());
				dbm.updateClient(c);
				session.setAttribute("Client", c);
			}
		}
		
		//Save the genre in a cookie
		Cookie cookie = new Cookie("genre",album.getGenre());
		cookie.setMaxAge(60*60*24*365*3);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		//Get Album rating
		float totalRating = 0;
		int numberOfTrack = 0;
		for(TrackBean tt: tracks){
			float rating=dbm.getTrackRanking(tt.getInventory_number());
			totalRating = totalRating + rating;
			if(rating != 0){
				numberOfTrack = numberOfTrack + 1;
			}
		}
		if(numberOfTrack != 0){
			float avgRating = totalRating / numberOfTrack;
			float halfStar = Math.round(avgRating) - avgRating;
			request.setAttribute("avgRating", avgRating);
			request.setAttribute("halfStar", halfStar);
		}
		else{
			float avgRating = 0;
			request.setAttribute("avgRating", avgRating);
		}
		
		
		request.setAttribute("reviews", reviews);
		request.setAttribute("tracksForAlbum", tracks);
		request.setAttribute("albumToView", album);
		request.setAttribute("similarAlbums", similar);
		
	
		// forward request and response to the view
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
