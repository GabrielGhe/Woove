package servlet.admin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import beans.AdBean;
import beans.ClientBean;

import da.DBManager;

/**
 * Servlet implementation class ManageAds
 */
@WebServlet(name = "ManageAds", urlPatterns = { "/manage_ads" })
public class ManageAds extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ManageAds() {
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
		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));
		String url = "/jsp/admin/manage_ads.jsp";
		ClientBean client;
		String fileName=null;
		String linkUrl=null;
		boolean isUploaded = false;

		synchronized (session) {
			client = (ClientBean) session.getAttribute("client");
		}

		// verify that the client has admin status
		if (client != null && client.getUserStatus() == 1) {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);

				try {
					List<FileItem> items = upload.parseRequest((RequestContext) request);
					Iterator<FileItem> iterator = items.iterator();

					while (iterator.hasNext()) {
						FileItem item = iterator.next();

						if (!item.isFormField()) {

							fileName = item.getName();
							String root = getServletContext().getRealPath("/");
							File path = new File(root + "/images/ads");

							if (!path.exists()) {
								boolean status = path.mkdirs();
								if (!status)
									isUploaded = false;
							}

							String mimeType = getServletContext().getMimeType(
									fileName);
							// image/bmp, image/png, image/gif, image/jpeg
							if (mimeType.startsWith("image")) {
								File uploadedFile = new File(path + "/"
										+ fileName);
								item.write(uploadedFile);
								isUploaded = true;
							} else
								isUploaded = false;
						} else {
							if (item.getFieldName().equals("adUrl1"))
								linkUrl = item.getString();
						}
					}
				} catch (FileUploadException e) {
					isUploaded = false;
				} catch (Exception e) {
					isUploaded = false;
				}

				/*
				 * if(linkUrl!=null && isUploaded==true){ AdBean ad = new
				 * AdBean(1, linkUrl, fileName, 0); dbm.insertAd(ad); }
				 */

				// request.setAttribute("ad", ad);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);

			}
		} else {
			// else client is not admin, must redirect them to index
			response.sendRedirect("/jsp/woove_index");
		}
	}

}
