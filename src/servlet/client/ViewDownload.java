package servlet.client;

import java.awt.Window;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AlbumBean;
import beans.ClientBean;
import beans.InvoiceBean;
import beans.InvoiceDetailsBean;
import beans.TrackBean;

import da.DBManager;

/**
 * Servlet implementation class ViewDownload
 * 
 * @author Brendan
 */
@WebServlet(name = "ViewDownload", urlPatterns = { "/view_download" })
public class ViewDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewDownload() {
		super();
		// TODO Auto-generated constructor stub
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
		String path = sc.getRealPath("/");
		String name = request.getParameter("downloadFile");
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/client/view_download.jsp";
		int lastAlbum = 0;
		
		if (name == null) {
			
			HttpSession session = request.getSession();
			ClientBean client = (ClientBean) session.getAttribute("Client");
			ArrayList<InvoiceBean> invoices = dbm.getInvoicesByClient(
					client.getClientID(), "1999-01-01", "3000-03-03");
			ArrayList<InvoiceDetailsBean> details = dbm.getInvoicesDetails();
			
			ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
			ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
			
			//Go through the invoices
			for (InvoiceBean ib : invoices) {
				
				for (InvoiceDetailsBean idb : details) {

					if (idb.getSale_number() == ib.getSale_number()) {	
						if (idb.getInventory_number() != 0) {
							TrackBean t = dbm.getTrackById(
									idb.getInventory_number()).get(0);
							t.setDate_entered(ib.getDate());
							t.setList_price(idb.getPrice_at_sale());
							tracks.add(t);
						} else {
							AlbumBean a = dbm.getAlbumById(
									idb.getAlbum_number()).get(0);
							if(a.getAlbumID() != lastAlbum){
							a.setDateEntered(ib.getDate());
							for(TrackBean t: dbm.getTrackByAlbum(a.getAlbumID())){
								t.setDate_entered(ib.getDate());
								tracks.add(t);
							}
							albums.add(a);
							lastAlbum = a.getAlbumID();
							}
						}
					}
				}
			}
			
			Collections.reverse(tracks);
			
			session.setAttribute("Downloads", tracks);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		//Download the tracks
		else{
			response.setContentType("application/octet-stream");
			response.setHeader("content-disposition", "attachment; filename="
					+ name);

			FileInputStream in = new FileInputStream(path + name);
			PrintWriter out = response.getWriter();

			int i = in.read();
			while (i != -1) {
				out.write(i);
				i = in.read();
			}
			in.close();
			out.close();
			
		}
	}

}
