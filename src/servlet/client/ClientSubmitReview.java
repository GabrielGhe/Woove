package servlet.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * @author 0737019
 */
@WebServlet("/client_Submit_Review")
public class ClientSubmitReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientSubmitReview() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/view_Track";
		
		HttpSession session = request.getSession();
		Cart cart;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String trackId;
		if(request.getParameter("track").indexOf(';')>-1)
			trackId = request.getParameter("track").substring(0,request.getParameter("track").indexOf(';'));
		else
			trackId= request.getParameter("track");
		ClientBean client = (ClientBean) session.getAttribute("Client");
		int clientId = client.getClientID();
		String review = (String) request.getParameter("review");
		int rating = Integer.parseInt( request.getParameter("rating"));
		
		
		int id = Integer.parseInt(trackId);

		ReviewBean reviewBean = new ReviewBean(0, id, sdf.format(date) , client.getFirstName() + " " + client.getLastName(), rating, review, 42, clientId);
		
		dbm.insertReview(reviewBean);
		
		request.setAttribute("track", trackId);
		
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
