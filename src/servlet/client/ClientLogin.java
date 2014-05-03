package servlet.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ClientBean;

import da.DBManager;

/**
 * Servlet implementation class ClientLogin
 * 
 * @author Brendan
 */
@WebServlet(name = "ClientLogin", urlPatterns = { "/client_login" })
public class ClientLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientLogin() {
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
		String url = "/jsp/client/client_login.jsp";
		HttpSession session = request.getSession();
		ClientBean client=(ClientBean) session.getAttribute("Client");

		if (request.getParameter("logout") == null) {
			// Check if there is a client logged in, If not then go through the
			// log in process
			if (client == null) {

				// Check Parameters
				if (request.getParameter("loginname") != null
						&& request.getParameter("password") != null) {
					String email = (String) request.getParameter("loginname");
					String password = (String) request.getParameter("password");

					// Get the client info from the database
					client = dbm.getClientsByEmailAndPass(
							email.trim(), password.trim());

					// If the client exists then send them to the download page
					if (client != null) {
						synchronized (session) {
							session.setAttribute("Client", client);
						}
						if(client.getUserStatus() == 1)
							url="/welcome_admin";
						else
							url = "/view_download";
					}

				}
			}
			// Else the client is already logged in, send them to the download or management page
			// page
			else if(client.getUserStatus() == 1)
				url="/welcome_admin";
			else
				url = "/view_download";

			if (session.getAttribute("Checkout") != null) {
				url = "/client_payment";
				session.setAttribute("Checkout", null);
			}
		} else{
			session.setAttribute("Client", null);
			url = "/jsp/client/client_login.jsp";
		}
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
