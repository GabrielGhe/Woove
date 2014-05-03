/**
 * @author 0836605
 */
package servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.AlbumBean;
import beans.ClientBean;
import beans.TrackBean;
import beans.ZeroTrackBean;
import da.DBManager;

/**
 * Servlet implementation class ManageReports
 */
@WebServlet(name = "ManageReports", urlPatterns = {"/manage_reports"})
public class ManageReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageReports() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/admin/manage_reports.jsp";
		
		String selection = request.getParameter("selection"),
				startDate = request.getParameter("startDate"),
				endDate = request.getParameter("endDate"),
				search = request.getParameter("search");
		
		if(startDate==null || startDate.equals(""))
			startDate="2012-01-01";
		if(endDate==null || endDate.equals(""))
			endDate="2013-12-31";
		if(selection==null)
			selection="";
		
		ArrayList<String[]> topSellers = null;
		ArrayList<String[]> topClients = null;
		ArrayList<ZeroTrackBean> zeroTracks = null;
		ArrayList<ClientBean> zeroClients = null;
		if(selection != null && startDate != null && endDate != null && 
				(startDate.compareTo(endDate))<0)
			switch(selection){
				case("Total Sales"): //here
					ArrayList<String> total = dbm.getTotalSales(startDate, endDate);
					request.setAttribute("totalSales", total);
					break;
				case("Sales by Client"):
					if(search!=null){
						ArrayList<String> data = dbm.getSalesByClient(search,startDate, endDate);
						request.setAttribute("byClient", data);
					}
					break;
				case("Sales by Artist")://here
					if(search!=null){
						ArrayList<String> data = dbm.getSalesByArtist(search,startDate, endDate);
						System.out.println("data:" +data);
						request.setAttribute("byArtist", data);
					}
					break;
				case("Sales by Track"):
					if(search!=null){
						ArrayList<String> data = dbm.getSalesByTrack(search,startDate, endDate);
						request.setAttribute("byTrack", data);
					}
					break;
				case("Sales by Album"):
					if(search!=null){
						ArrayList<String> data = dbm.getSalesByAlbum(search,startDate, endDate);
						request.setAttribute("byAlbum", data);
					}
					break;
				case("Top Sellers"):
					topSellers = dbm.getTopSellers(startDate, endDate);
					request.setAttribute("topSellers", topSellers);
					break;
				case("Top Clients"):
					topClients = dbm.getTopClients(startDate, endDate);
					request.setAttribute("topClients", topClients);
					break;
				case("Zero Tracks"):
					zeroTracks = dbm.getZeroTracks(startDate, endDate);
					request.setAttribute("zeroTracks", zeroTracks);
					break;
				case("Zero Clients"):
					zeroClients = dbm.getZeroClients(startDate, endDate);
					request.setAttribute("zeroClients", zeroClients);
					break;
			}
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}