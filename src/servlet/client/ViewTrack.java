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
 * Servlet implementation class ViewTracks
 * 
 * @author Brendan, Chris
 * @co-author 0737019
 * @co-author = 1312040
 */
@WebServlet(name = "ViewTrack", urlPatterns = {"/view_Track"})
public class ViewTrack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTrack() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/client/view_track.jsp";
		HttpSession session = request.getSession();
		Cart cart;

		//This is to fix an error where jsessionid is appended to request.getParameter("track")
		int id=1;
		if(request.getParameter("track")!=null){
			if(!request.getParameter("track").toString().contains(";"))
				id = Integer.parseInt(request.getParameter("track").toString());
		}
		else if(request.getParameter("track")!=null){
			if(!request.getParameter("track").toString().contains(";"))
				id = Integer.parseInt(request.getParameter("track").toString());
			else
				id = Integer.parseInt(request.getParameter("track").toString().substring(0,request.getParameter("track").toString().indexOf(";")));
		}
		
		
		synchronized(session){
			cart = (Cart)session.getAttribute("Cart");
		
			//Check if there is a purchase
			if(request.getParameter("purchaseTrack") != null)
			{
				ArrayList<TrackBean> ts = cart.getTracks();
				ArrayList<AlbumBean> as = cart.getAlbum();
 				TrackBean t = dbm.getTrackById(id).get(0);
				AlbumBean a = dbm.getAlbumById(t.getAlbum_number()).get(0);
				
				//Check if the user has the album containing the track
				if(!as.contains(a))
				{
					if(!ts.contains(t))
					{
						ts.add(t);
						cart.setTracks(ts);
					}

				}
				session.setAttribute("Cart", cart);
			}
			
			
		}
		
		Cookie[] cookies = request.getCookies();
		String cookieStr = "trackId="+id;
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				//could replace with StringTokenizer
				if(cookies[i].getName().equals("lastThree")){
					String[] lastThree = cookies[i].getValue().split("&");
					if(lastThree.length != 0){
						if(lastThree.length==3)
							for(int j = 0; j<2; j++)
								cookieStr += "&"+lastThree[j];
						else
							for(int j = 0; j<lastThree.length; j++)
								cookieStr += "&"+lastThree[j];
					}
				}
			}
			Cookie cookie = new Cookie("lastThree",cookieStr);
			cookie.setMaxAge(60*60*24*365*3);
			cookie.setPath("/");
			response.addCookie(cookie);
		}

		//Get the track to be viewed and the similar track
		TrackBean track = dbm.getTrackById(id).get(0);
		ArrayList<TrackBean> similarTrack = dbm.getTrackByGenre(track.getCategory());
		Collections.shuffle(similarTrack);
		AlbumBean album = dbm.getAlbumById(track.getAlbum_number()).get(0);
		ArrayList<TrackBean> tracks = dbm.getTrackByAlbum(album.getAlbumID());
		ArrayList<ReviewBean> reviews = dbm.getReviewApprovedById(id);
		
		//Save the genre in a cookie
		Cookie cookie = new Cookie("genre",album.getGenre());
		cookie.setMaxAge(60*60*24*365*3);
		cookie.setPath("/");
		response.addCookie(cookie);
			
		//Add the tracks to the request object
		request.setAttribute("album", album);
		request.setAttribute("track", track);
		request.setAttribute("similarTracks", similarTrack);
		request.setAttribute("tracksForAlbum", tracks);
		request.setAttribute("reviews", reviews);
		
		//Save the genre
		synchronized(session){
			if(session.getAttribute("Client") != null){
				ClientBean c = (ClientBean) session.getAttribute("Client");
				c.setLastSearch(album.getGenre());
				dbm.updateClient(c);
				session.setAttribute("Client", c);
			}
		}
		
		
		//get the rating of the track
		
		float rating = dbm.getTrackRanking(id);
		float halfStar = Math.round(rating) - rating;
		request.setAttribute("rating", rating);
		request.setAttribute("halfStar", halfStar);
				
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
