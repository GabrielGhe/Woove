package servlet.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import beans.AdBean;
import beans.RSSFeed;
import beans.SurveyBean;
import beans.TrackBean;
import da.DBManager;

/**
 * Servlet implementation class ManageAds
 */
@WebServlet(name = "ManageAdsNewsSurvey", urlPatterns = {"/manage_adsNewsSurvey"})

public class ManageAdsNewsSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final boolean DEBUG = true;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAdsNewsSurvey() {
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
		String url = "/jsp/admin/manage_ads.jsp";

		ArrayList<AdBean> ads = dbm.getActiveAds();
		SurveyBean surveys = dbm.getActiveSurvey();
		RSSFeed rss =dbm.getActiveRSS();

		String adUrl1 = request.getParameter("adUrl1"),
			   adImg1 = request.getParameter("adImg1"),
			   adUrl2 = request.getParameter("adUrl2"),
			   adImg2 = request.getParameter("adImg2"),
			   rssUrl = request.getParameter("rssUrl"),
			   surveyQ = request.getParameter("surveyQ"),
			   surveyA1 = request.getParameter("surveyA1"),
			   surveyA2 = request.getParameter("surveyA2");
		ArrayList<RSSFeed> inactiveRss = dbm.getInactiveRSS();
		ArrayList<AdBean> inactiveAds  = dbm.getInactiveAds();
		ArrayList<SurveyBean> inactiveSurveys  = dbm.getInactiveSurveys();

		//have to modify jsp and fix this code to upload image to server
		if(adUrl1 != null && adImg1 != null){
			dbm.deactivateAd(ads.get(0).getId());
			dbm.insertAd(new AdBean(0,adUrl1,adImg1,1));
//			uploadFile(request);
		}
		
		if(adUrl2 != null && adImg2 != null){
			dbm.deactivateAd(ads.get(1).getId());
			dbm.insertAd(new AdBean(0,adUrl2,adImg2,1));
//			uploadFile(request);
		}
		
		if(rssUrl!=null){
			dbm.deactivateRSSFeed();
			dbm.insertRSS(new RSSFeed(0, rssUrl,1));
		}
		
		if(surveyQ!=null && surveyA1 != null && surveyA2 != null){
			dbm.deactivateSurveys();
			dbm.insertSurvey(new SurveyBean(1,surveyQ,surveyA1,surveyA2,"","",0,0,0,0,1));
		}
		
		request.setAttribute("inactiveRSS", inactiveRss);
		request.setAttribute("inactiveAds", inactiveAds);
		request.setAttribute("inactiveSurveys", inactiveSurveys);
		request.setAttribute("rss", rss);
		request.setAttribute("ads", ads);
		request.setAttribute("surveys", surveys);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void uploadFile(HttpServletRequest request){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println(isMultipart);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ServletConfig config = this.getServletConfig();
			String uploadDir = config.getInitParameter("images/ads");
			System.out.println("not in try");
			try {
				System.out.println("in try");
				List<FileItem> items = upload.parseRequest((RequestContext) request);
				System.out.println("in try, items:"+items);
				Iterator<FileItem> iterator = items.iterator();
				System.out.println("iterator:"+iterator);
				System.out.println("not in loop");
				while (iterator.hasNext()) {
					System.out.println("in loop");
					FileItem item = iterator.next();

					if (!item.isFormField()) {
						String fileName = item.getName();
						
						String root = getServletContext().getRealPath("/");
						File path = new File(root + "/" + uploadDir);
						
						if (!path.exists()) {
							boolean status = path.mkdirs();
							if (!status)
								throw new ServletException(
										"The upload directory at "
												+ path.getAbsolutePath()
												+ " cannot be created.");

						}

						File uploadedFile = new File(path + "/" + fileName);
						if (DEBUG)
							System.out.println(uploadedFile.getAbsolutePath());
						item.write(uploadedFile);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}