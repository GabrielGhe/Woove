package servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AlbumBean;
import beans.Cart;
import beans.ClientBean;
import beans.InvoiceDetailsBean;
import beans.TrackBean;

import da.DBManager;
import util.CardCheck;
import util.Rates;
import util.RegistrationValidation;

/**
 * Servlet implementation class ClientPayment
 * 
 * @author Brendan
 */
@WebServlet(name = "ClientPayment", urlPatterns = { "/client_payment" })
public class ClientPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CardCheck cardValidator;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientPayment() {
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
		String url = "/jsp/client/client_payment.jsp";
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("Cart");
		ClientBean c = (ClientBean) session.getAttribute("Client");
		String message = "";
		ArrayList<InvoiceDetailsBean> idbs = new ArrayList<InvoiceDetailsBean>();
		ArrayList<TrackBean> downloads = (ArrayList<TrackBean>) session
				.getAttribute("Downloads");
		int lastAlbum = 0;
		
		ArrayList<TrackBean> newCartTracks = new ArrayList<TrackBean>();
		ArrayList<AlbumBean> newCartAlbums = new ArrayList<AlbumBean>();

		float pst = 0.0F;
		float gst = 0.0F;
		float hst = 0.0F;

		float netTotal = 0.0F;
		float grossTotal = 0.0F;
		
		if(downloads == null)
			downloads = new ArrayList<TrackBean>();

		if (cart.getAlbum().size() > 0 || cart.getTracks().size() > 0) {
			// check if client is logged in
			if (session.getAttribute("Client") == null) {
				url = "/jsp/client/client_login.jsp";
				session.setAttribute("Checkout", true);
				message = "you must be logged in";
			}
			// else will verify the credit card num
			else if (request.getParameter("cardnum") != null) {

				// retrieve all the parameters
				String type = request.getParameter("card");
				String cardName = request.getParameter("cardname");
				String creditCardNum = request.getParameter("cardnum");
				String expirationMonth = request.getParameter("month");
				String expirationYear = request.getParameter("year");
				String ccSecurityCode = request.getParameter("securitycode");

				// verify cc information
				if (CardCheck.validateCard(cardName, creditCardNum,
						expirationMonth, expirationYear, ccSecurityCode)) {
					// verify cc #
					if (CardCheck.validateCard(creditCardNum, type)) {
						url = "/jsp/client/view_confirmation.jsp";

						request.setAttribute("CardName", cardName);
						request.setAttribute("CardNumber", creditCardNum);
						request.setAttribute("ExpiryDate", expirationMonth
								+ "/" + expirationYear);

						//Create the Invoice Details
						if (cart.getAlbum().size() > 0
								|| cart.getTracks().size() > 0) {
							float gstRate = Rates.getGST(c.getProvince());
							float pstRate = Rates.getPST(c.getProvince());
							float hstRate = Rates.getHST(c.getProvince());
							
							// Create an invoice detail for each track
							for (TrackBean b : cart.getTracks()) {

								if (!downloads.contains(b)) {
									AlbumBean a = dbm.getAlbumById(
											b.getAlbum_number()).get(0);
									idbs.add(new InvoiceDetailsBean(-1, 0, b
											.getInventory_number(), 0, a
											.getNumOfTracks(), (float) a
											.getListPrice(),
											(b.getSale_price() != 0 ? b
													.getSale_price() : b
													.getList_price()), 0));

									// Add price to net total
									netTotal += (b.getSale_price() != 0 ? b
											.getSale_price() : b
											.getList_price());
									
									newCartTracks.add(b);
								}
							}

							// Now create an invoice for every track on each
							// album
							if (cart.getAlbum().size() > 0) {
								for (AlbumBean a : cart.getAlbum()) {
									ArrayList<TrackBean> ts = dbm
											.getTrackByAlbum(a.getAlbumID());

									for (TrackBean t : ts)
										if (!downloads.contains(t)) {
											idbs.add((new InvoiceDetailsBean(
													-1, 0, 0, a.getAlbumID(), a
															.getNumOfTracks(),
													(float) a.getListPrice(), t
															.getList_price(), 0)));

											if (a.getAlbumID() != lastAlbum) {
												// Add price to net total only
												// once per album
												netTotal += (a.getSalePrice() != 0 ? a
														.getSalePrice() : a
														.getListPrice());
												lastAlbum = a.getAlbumID();
												newCartAlbums.add(a);
											}
										}
								}
							}
							// Get PST & GST
							gst = Math.round((netTotal * gstRate)*100.0F)/100.0F;
							pst = Math.round(((netTotal + gst) * pstRate)*100.0F)/100.0F;
							hst = Math.round((netTotal * hstRate)*100.0F)/100.0F;
							
							//Update the cart with the tracks that the 
							//client has never purchased
							cart.setAlbum(newCartAlbums);
							cart.setTracks(newCartTracks);

							grossTotal += gst + pst + hst + netTotal;
							
							session.setAttribute("Cart", cart);
							session.setAttribute("Invoice Details", idbs);
							session.setAttribute("GST", gst);
							session.setAttribute("HST", hst);
							session.setAttribute("PST", pst);
							session.setAttribute("Gross", grossTotal);
							session.setAttribute("Net", netTotal);
						}else
							url = "/view_cart";
						message = "payment successful";
					}
					// else there was an error with the cc #
					else {
						url = "/jsp/client/client_payment.jsp";
						message = "payment unsuccessful";
					}
				}
			}
			// else there was an error with the cc information
			else {
				url = "/jsp/client/client_payment.jsp";
				message = "payment unsuccessful";
			}
		} else
			url = "/view_cart";

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		request.setAttribute("message", message);
		
		dispatcher.forward(request, response);
	}

}
