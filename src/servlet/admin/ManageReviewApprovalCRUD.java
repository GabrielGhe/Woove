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
import beans.ReviewBean;
import beans.TrackBean;
import da.DBManager;

/**
 * Servlet implementation class ManageTracks
 */
@WebServlet(name = "ManageReviewApprovalCRUD", urlPatterns = {"/manage_review_approvalCRUD_approve", "/manage_review_approvalCRUD_reject"})

public class ManageReviewApprovalCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageReviewApprovalCRUD() {
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
		String url = "/manage_review_approval";
		
		String servletPath = request.getServletPath();
		if("/manage_review_approvalCRUD_approve".equalsIgnoreCase(servletPath)){
			
			//this is for approving 1 review
			if(request.getParameter("review_id") != null){
				int id = Integer.parseInt(request.getParameter("review_id"));
				ReviewBean review = dbm.getReviewById(id).get(0);
				review.setApproval_status(1);
				dbm.updateReview(review);
			}
			
		}else if("/manage_review_approvalCRUD_reject".equalsIgnoreCase(servletPath)){
			//this is for rejecting 1 review
			if(request.getParameter("review_id") != null){
				int id = Integer.parseInt(request.getParameter("review_id"));
				ReviewBean review = new ReviewBean(id, 0, "", "", 0, "", 0,0);
				dbm.deleteReview(review);
			}
		}
		
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}