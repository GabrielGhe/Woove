/**
 * @coauthor 0737019
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
import beans.InvoiceBean;
import beans.TrackBean;

import da.DBManager;

/**
 * Servlet implementation class ViewInvoice
 */
@WebServlet(name = "ManageSales", urlPatterns = {"/manage_Sales", "/manage_Sales_t", "/manage_Sales_a"})
public class ManageSales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageSales() {
        super();
        // TODO Auto-generated constructor stub
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
		
		
		String servletPath = request.getServletPath();
		if("/manage_Sales_t".equalsIgnoreCase(servletPath)){
			
			//this is for deleting tracks
			if(request.getParameter("fid") != null && request.getParameter("ftsp") != null){
				
				//caching all the parameters
				int id = Integer.parseInt(request.getParameter("fid"));
				float saleP = Float.parseFloat(request.getParameter("ftsp"));
				if(id > 0){
					TrackBean track = dbm.getTrackById(id).get(0);
					track.setSale_price(saleP);
					dbm.updateTrack(track);
				}
			}			
		}else if("/manage_Sales_a".equalsIgnoreCase(servletPath)){
			//this is for deleting albums
			
			if(request.getParameter("fai") != null && request.getParameter("fasp") != null){
				
				//caching all the parameters
				int id = Integer.parseInt(request.getParameter("fai"));
				float saleP = Float.parseFloat(request.getParameter("fasp"));
				
				if(id > 0){
					AlbumBean album = dbm.getAlbumById(id).get(0);
					album.setSalePrice(saleP);
					dbm.updateAlbum(album);
				}
			}
		}
		
		
		ArrayList<InvoiceBean> invoices = dbm.getInvoices();
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
		
		String search = request.getParameter("search");
		
		//Needs to change to HashMaps to avoid duplicates.
		if(search!=null){
			tracks.addAll(dbm.getTrackByArtist(search));
			tracks.addAll(dbm.getTrackByName(search));
			tracks.addAll(dbm.getTrackByGenre(search));
			albums.addAll(dbm.getAlbumsByArtist(search));
			albums.addAll(dbm.getAlbumsByName(search));
			albums.addAll(dbm.getAlbumsByGenre(search));	
		}else{
			 tracks = dbm.getTracks();
			 albums = dbm.getAlbums();
		}
		
		//if the suffix is nothing, just get everything
		
		request.setAttribute("tracks", tracks);
		request.setAttribute("albums", albums);
		
		request.setAttribute("invoices", invoices);
		String url = "/jsp/admin/manage_sales.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
