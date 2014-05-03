/**
 * @author 0737019
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
import beans.AlbumBeanWithSales;
import beans.TrackBean;
import beans.TrackBeanWithSales;
import da.DBManager;

/**
 * Servlet implementation class ManageTracks
 */
@WebServlet(name = "ManageTracksAndAlbums", urlPatterns = {"/manage_albumsTracks"})

public class ManageTracksAndAlbums extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageTracksAndAlbums() {
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
		String url = "/jsp/admin/manage_tracks.jsp";
		
		
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
		
		ArrayList<TrackBeanWithSales> salesTracks = new ArrayList<TrackBeanWithSales>();
		ArrayList<AlbumBeanWithSales> salesAlbums = new ArrayList<AlbumBeanWithSales>();
		double totalSales = 0.0;
		
		String search = request.getParameter("search");
		
		//Needs to change to HashMaps to avoid duplicates.
		if(search!=null){
			tracks.addAll(dbm.getTrackByArtist(search));
			tracks.addAll(dbm.getTrackByName(search));
			tracks.addAll(dbm.getTrackByGenre(search));
			albums.addAll(dbm.getAlbumsByArtist(search));
			albums.addAll(dbm.getAlbumsByName(search));
			albums.addAll(dbm.getAlbumsByGenre(search));
			
			//
			totalSales = dbm.getTotalSalesByClient(tracks.get(0).getInventory_number());
			salesTracks.add(new TrackBeanWithSales(tracks.get(0).getInventory_number(), tracks.get(0).getAlbum_number(),
					tracks.get(0).getTitle(), tracks.get(0).getArtist(), tracks.get(0).getWriter(), tracks.get(0).getTrack_length(), 
					tracks.get(0).getTrack_number(), tracks.get(0).getCategory(), tracks.get(0).getCover_img_name(), 
					tracks.get(0).getCost_price(), tracks.get(0).getList_price(), tracks.get(0).getSale_price(), tracks.get(0).getDate_entered(), 
					tracks.get(0).getRemoval_status(), totalSales  ));
			
			totalSales = dbm.getTotalSalesByClient(albums.get(0).getAlbumID());
			salesAlbums.add(new AlbumBeanWithSales(albums.get(0).getAlbumID(), albums.get(0).getTitle(), albums.get(0).getArtist(),
					albums.get(0).getGenre(), albums.get(0).getNumOfTracks(), albums.get(0).getReleaseDate(), albums.get(0).getListPrice(),
					albums.get(0).getSalePrice(), albums.get(0).getCostPrice(), albums.get(0).getLabel(), albums.get(0).getRemovalStatus(), 
					albums.get(0).getDateEntered(), albums.get(0).getImgName(), totalSales)); 
			
		}else{
			 tracks = dbm.getTracks();
			 albums = dbm.getAlbums();
			 
			for(int i=0; i<tracks.size(); i++ ){
				totalSales = dbm.getTotalSalesByClient(tracks.get(i).getInventory_number());
				salesTracks.add(new TrackBeanWithSales(tracks.get(i).getInventory_number(), tracks.get(i).getAlbum_number(),
						tracks.get(i).getTitle(), tracks.get(i).getArtist(), tracks.get(i).getWriter(), tracks.get(i).getTrack_length(), 
						tracks.get(i).getTrack_number(), tracks.get(i).getCategory(), tracks.get(i).getCover_img_name(), 
						tracks.get(i).getCost_price(), tracks.get(i).getList_price(), tracks.get(i).getSale_price(), tracks.get(i).getDate_entered(), 
						tracks.get(i).getRemoval_status(), totalSales  ));
			}
			
			for(int i=0; i<albums.size(); i++ ){
				totalSales = dbm.getTotalSalesByClient(albums.get(i).getAlbumID());
				salesAlbums.add(new AlbumBeanWithSales(albums.get(i).getAlbumID(), albums.get(i).getTitle(), albums.get(i).getArtist(),
						albums.get(i).getGenre(), albums.get(i).getNumOfTracks(), albums.get(i).getReleaseDate(), albums.get(i).getListPrice(),
						albums.get(i).getSalePrice(), albums.get(i).getCostPrice(), albums.get(i).getLabel(), albums.get(i).getRemovalStatus(), 
						albums.get(i).getDateEntered(), albums.get(i).getImgName(), totalSales)); 
			}
	
		}
		
		
		
		request.setAttribute("tracks", salesTracks);
		request.setAttribute("albums", salesAlbums);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}