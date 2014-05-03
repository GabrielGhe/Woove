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

import beans.InvoiceBean;
import beans.InvoiceDetailsBean;

import da.DBManager;

/**
 * Servlet implementation class ViewInvoice
 */
@WebServlet(name = "ManageInvoiceDetails", urlPatterns = { "/manage_invoiceDetails" })
public class ManageInvoiceDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageInvoiceDetails() {
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
		DBManager dbm = new DBManager(sc.getRealPath("/DBConfig.properties"));	
		
		//removing invoice detail
		if(request.getParameter("remove") != null && request.getParameter("remove") != ""){
			int remove = Integer.parseInt(request.getParameter("remove"));
			InvoiceDetailsBean detail = dbm.getInvoicesDetailsById(remove).get(0);
			dbm.deleteInvoiceDetails(detail);
		}
		
		
		ArrayList<InvoiceDetailsBean> invoice_details = new ArrayList<InvoiceDetailsBean>();
		String strId = request.getParameter("invoice");
		
		if (strId != null && !strId.equals("")) {
			int id = Integer.parseInt(strId);
			
			
			if (id > 0) {
				invoice_details.addAll(dbm.getInvoicesDetailsByInvoice(id));
				InvoiceBean invoice = dbm.getInvoicesById(id).get(0);
				request.setAttribute("invoice", invoice);
			}

		}
		
		request.setAttribute("invoiceDetails", invoice_details);
		String url = "/jsp/admin/manage_invoiceDetails.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}