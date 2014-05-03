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
import util.RegistrationValidation;
import da.DBManager;

/**
 * Servlet implementation class ClientRegister
 * 
 * @author Brendan & Kim
 */
@WebServlet(name = "ClientRegister", urlPatterns = { "/client_registration" })
public class ClientRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientRegister() {
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
		String url = "/jsp/client/client_register.jsp";

		RegistrationValidation regValidation = new RegistrationValidation();
		String errorString = "";
		String message = "";

		String email = request.getParameter("email");

		// verify if the email is already used?

		// get parameters from the form
		String title = request.getParameter("title");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String company = request.getParameter("companyname");
		String addressOne = request.getParameter("addressOne");
		String addressTwo = request.getParameter("addressTwo");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String country = "CA";
		String postalCode = request.getParameter("postalcode");
		String homePhone = request.getParameter("homephone");
		String cellPhone = request.getParameter("cellphone");
		String passwordOne = request.getParameter("passwordOne");
		String passwordTwo = request.getParameter("passwordTwo");

		// insert client info into bean
		ClientBean client = new ClientBean(0, title, firstName, lastName,
				company, addressOne, addressTwo, city, province, country,
				postalCode, homePhone, cellPhone, email, passwordOne, "", 0);

		// validate info
		errorString = regValidation.clientValidation(client, passwordTwo);
		// verify if there were any reported servlet errors
		if (errorString.equals("")) {
			client.setHomePhone(client.getHomePhone().replaceAll("[^\\d.]", ""));
			if(client.getCellPhone()!=null)
				client.setCellPhone(client.getCellPhone().replaceAll("[^\\d.]", ""));
			// if servlet error is empty then insert into db
			if (dbm.insertClient(client)) {
				message = "successful registration";
				client = dbm.getClientsByEmailAndPass(client.getEmail(),
						client.getPassword());
				HttpSession s = request.getSession();
				s.setAttribute("Client", client);
				url = "/view_download";
			}
			// else there was an error inserting into the db
			else {
				message = "unsuccessful registration";
				url = "/jsp/client/client_register.jsp";
			}
		}
		// else there was an error that occured while validation the info
		else {
			url = "/jsp/client/client_register.jsp";
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		request.setAttribute("client", client);
		request.setAttribute("message", message);
		dispatcher.forward(request, response);

	}
}
