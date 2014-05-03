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
import javax.servlet.http.HttpSession;

import beans.AlbumBean;
import beans.Cart;
import beans.TrackBean;

import da.DBManager;

/**
 * Servlet implementation class ViewCart
 * 
 * @author Brendan
 */
@WebServlet(name = "ViewCart", urlPatterns = { "/view_cart","/ViewCart" })
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCart() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/client/view_cart.jsp";
		int id;
		HttpSession session = request.getSession();
		
		Cart cart = (Cart)session.getAttribute("Cart");

		
		if(request.getParameter("removed") != null)
		{		
			if(request.getParameter("removeTrack") != null)
			{
				id = Integer.parseInt(request.getParameter("removeTrack"));
				TrackBean ttr = dbm.getTrackById(id).get(0);
				ArrayList<TrackBean> ts = cart.getTracks();
				ts.remove(ts.indexOf(ttr));
				cart.setTracks(ts);
			}
			else{
				id = Integer.parseInt(request.getParameter("removeAlbum"));
				AlbumBean atr = dbm.getAlbumById(id).get(0);
				ArrayList<AlbumBean> as = cart.getAlbum();
				as.remove(as.indexOf(atr));
				cart.setAlbum(as);
			}
			
		}
		
		request.setAttribute("tracksInCart", cart.getTracks());
		request.setAttribute("albumsInCart", cart.getAlbum());
		session.setAttribute("Cart", cart);
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
