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
import beans.ClientBean;
import beans.TrackBean;
import da.DBManager;

/**
 * Servlet implementation class ManageTracks
 */
@WebServlet(name = "ManageTracksAndAlbumsCRUD", urlPatterns = {"/manage_albumsTracksCrud_au",
															   "/manage_albumsTracksCrud_ad",
															   "/manage_albumsTracksCrud_tu",
															   "/manage_albumsTracksCrud_td"})


public class ManageTracksAndAlbumsCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageTracksAndAlbumsCRUD() {
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
		String url = "/manage_albumsTracks";
		
		String servletPath = request.getServletPath();
		if("/manage_albumsTracksCrud_tu".equalsIgnoreCase(servletPath)){
			
			//this is for inserting and updating tracks
			if(request.getParameter("fid") != null){
				
				//caching all the parameters
				int id = Integer.parseInt(request.getParameter("fid"));
				int albumId = Integer.parseInt(request.getParameter("ftai"));
				String title = (String) request.getParameter("fttt");
				String artist = (String) request.getParameter("fta");
				String songwriter = (String) request.getParameter("fts");
				String length = (String) request.getParameter("ftl");
				int trackNum = Integer.parseInt(request.getParameter("fttn"));
				String genre = (String) request.getParameter("ftg");
				String albumCover = (String) request.getParameter("ftac");
				float costP = Float.parseFloat(request.getParameter("ftcp"));
				float listP = Float.parseFloat(request.getParameter("ftlp"));
				float saleP = Float.parseFloat(request.getParameter("ftsp"));
				String dateEnt = (String) request.getParameter("ftde");
				int removal = Integer.parseInt(request.getParameter("ftr"));
				
				//make sure everything is set before adding
				if(		albumId >= 0 && 
						songwriter != null || songwriter.trim() != "" && 
						length != null || length.trim() != "" && 
						genre != null || genre.trim() != "" && 
						albumCover != null || albumCover.trim() != "" &&
						costP >= 0.00F &&
						listP >= 0.00F &&
						saleP >= 0.00F &&
						dateEnt != null || dateEnt.trim() != ""){					
					TrackBean track = new TrackBean(id, albumId, title, artist, songwriter, length, trackNum, genre, albumCover,
							costP, listP, saleP, dateEnt,0, removal);
					
					if(id > 0){
						//update track bean
						dbm.updateTrack(track);
					}else{
						//insert track bean
						dbm.insertTrack(track);
					}
				}
			}
			
		}else if("/manage_albumsTracksCrud_td".equalsIgnoreCase(servletPath)){
			//this is for deleting tracks
			if(request.getParameter("fid") != null){
				
				//caching all the parameters
				int id = Integer.parseInt(request.getParameter("fid"));
				int albumId = Integer.parseInt(request.getParameter("ftai"));
				String title = (String) request.getParameter("fttt");
				String artist = (String) request.getParameter("fta");
				String songwriter = (String) request.getParameter("fts");
				String length = (String) request.getParameter("ftl");
				int trackNum = Integer.parseInt(request.getParameter("fttn"));
				String genre = (String) request.getParameter("ftg");
				String albumCover = (String) request.getParameter("ftac");
				float costP = Float.parseFloat(request.getParameter("ftcp"));
				float listP = Float.parseFloat(request.getParameter("ftlp"));
				float saleP = Float.parseFloat(request.getParameter("ftsp"));
				String dateEnt = (String) request.getParameter("ftde");
				int removal = Integer.parseInt(request.getParameter("ftr"));
				
				//make sure everything is set before adding
				if(		albumId >= 0 &&
						songwriter != null || songwriter.trim() != "" &&
						length != null || length.trim() != "" &&
						genre != null || genre.trim() != "" &&
						albumCover != null || albumCover.trim() != "" &&
						costP >= 0 &&
						listP >= 0 &&
						saleP >= 0 &&
						dateEnt != null || dateEnt.trim() != ""){					
					TrackBean track = new TrackBean(id, albumId, title, artist, songwriter, length, trackNum, genre, albumCover,
							costP, listP, saleP, dateEnt,0, 1);
					dbm.updateTrack(track);
				}
			}
		}else if("/manage_albumsTracksCrud_au".equalsIgnoreCase(servletPath)){
			//this is for inserting and updating albums
			
			if(request.getParameter("fai") != null){
				//caching all the parameters
				int id = Integer.parseInt(request.getParameter("fai"));
				String title = (String) request.getParameter("faat");
				String release = (String) request.getParameter("fare");
				String artist = (String) request.getParameter("faa");
				String label = (String) request.getParameter("fal");
				int tracks = Integer.parseInt(request.getParameter("fat"));
				String dateEnt = (String) request.getParameter("fade");
				float costP = Float.parseFloat(request.getParameter("facp"));
				float listP = Float.parseFloat(request.getParameter("falp"));
				float saleP = Float.parseFloat(request.getParameter("fasp"));
				int removal = Integer.parseInt(request.getParameter("far"));
				String genre = (String) request.getParameter("fagre");
				String imgName = (String) request.getParameter("faimg");
				//make sure everything is set before adding
				if(		title != null || title.trim() != "" &&
						release != null || release.trim() != "" &&
						artist != null || artist.trim() != "" &&
						label != null || label.trim() != "" &&
						tracks >= 1 &&
						costP >= 0 &&
						listP >= 0 &&
						saleP >= 0 &&
						dateEnt != null || dateEnt.trim() != ""){					
					AlbumBean album = new AlbumBean(id, title, release, artist, label, tracks, dateEnt, costP, listP, saleP, removal,imgName, genre);

					if(id > 0){
						//update Album bean
						dbm.updateAlbum(album);
					}else{
						//insert Album bean
						dbm.insertAlbum(album);
					}
				}
			}
			
		}else if("/manage_albumsTracksCrud_ad".equalsIgnoreCase(servletPath)){
			//this is for deleting albums
			
			if(request.getParameter("fai") != null){
				
				//caching all the parameters
				int id = Integer.parseInt(request.getParameter("fai"));
				String title = (String) request.getParameter("faat");
				String release = (String) request.getParameter("fare");
				String artist = (String) request.getParameter("faa");
				String label = (String) request.getParameter("fal");
				int tracks = Integer.parseInt(request.getParameter("fat"));
				String dateEnt = (String) request.getParameter("fade");
				float costP = Float.parseFloat(request.getParameter("facp"));
				float listP = Float.parseFloat(request.getParameter("falp"));
				float saleP = Float.parseFloat(request.getParameter("fasp"));
				int removal = Integer.parseInt(request.getParameter("far"));
				String genre = (String) request.getParameter("fagre");
				String imgName = (String) request.getParameter("faimg");
				
				//make sure everything is set before adding
				if(		title != null || title.trim() != "" &&
						release != null || release.trim() != "" &&
						artist != null || artist.trim() != "" &&
						label != null || label.trim() != "" &&
						tracks >= 1 &&
						costP >= 0.00F &&
						listP >= 0.00F &&
						saleP >= 0.00F &&
						dateEnt != null || dateEnt.trim() != ""){					
					AlbumBean album = new AlbumBean(id, title, release, artist, label, tracks, dateEnt, costP, listP, saleP, 1, genre, imgName);
					
					//update Album bean
					dbm.updateAlbum(album);
				}
			}
			
		}
		
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}