package servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.MailConfiguration;
import mail.MailSend;

import beans.AlbumBean;
import beans.Cart;
import beans.ClientBean;
import beans.InvoiceBean;
import beans.InvoiceDetailsBean;
import beans.TrackBean;

import util.Rates;

import da.DBManager;

/**
 * Servlet implementation class ViewConfirmation
 * 
 * @author Brendan
 */
@WebServlet(name = "ViewConfirmation", urlPatterns = { "/view_confirmation" })
public class ViewConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewConfirmation() {
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
		String url = "/jsp/client/client_invoice.jsp";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // This is to
																	// format
																	// the date
		HttpSession session = request.getSession();
		ClientBean c = (ClientBean) session.getAttribute("Client");
		Cart cart = (Cart) session.getAttribute("Cart");
		Date date = new Date();
		ArrayList<InvoiceDetailsBean> idbs = (ArrayList<InvoiceDetailsBean>) session.getAttribute("Invoice Details");
		

			// This is to do the invoices
			
			//Get the tax rates depending on where the client lives
			float pst = (float) session.getAttribute("PST");
			float gst = (float) session.getAttribute("GST");
			float hst = (float) session.getAttribute("HST");

			float netTotal = (float) session.getAttribute("Net");
			float grossTotal = (float) session.getAttribute("Gross");

			if(grossTotal != 0){
			// Create the invoice
			InvoiceBean ib = new InvoiceBean(-1, sdf.format(date),
					c.getClientID(), netTotal, pst, gst, hst, grossTotal, 0);
			dbm.insertInvoice(ib);

			// Get the last invoice inserted to get the sale number
			ArrayList<InvoiceBean> invoices = dbm.getInvoicesByClient(
					c.getClientID(), "2012-01-01", "2053-12-31");
			ib = invoices.get(invoices.size() - 1);
			
			for(InvoiceDetailsBean idb: idbs)
			{
				idb.setSale_number(ib.getSale_number());
				dbm.insertInvoiceDetails(idb);
			}

			 // Send email here
			 MailConfiguration config = new MailConfiguration();
			 MailSend sender = new MailSend(config);
			
			 sender.sendMail(cart, ib, c);

			// Send the old cart with the items that were purchased
			session.setAttribute("ConfCart", cart);
			request.setAttribute("Invoice", ib);
			}
			else
				url = "/view_cart";
			
			// Empty the cart
			cart = new Cart();
			session.setAttribute("Cart", cart);

		// Forward to the page
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
