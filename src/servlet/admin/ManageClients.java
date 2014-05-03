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

import beans.ClientBean;
import beans.ClientBeanWithSales;

import da.DBManager;

/**
 * Servlet implementation class ManageClients
 */
@WebServlet(name = "ManageClients", urlPatterns = { "/manage_clients" })
public class ManageClients extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageClients() {
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
		String url = "/jsp/admin/manage_clients.jsp";
		ArrayList<ClientBean> clients = new ArrayList<ClientBean>();
		ArrayList<ClientBeanWithSales> salesClients = new ArrayList<ClientBeanWithSales>();
		double totalSales = 0.0;

		String search = request.getParameter("search");
		if (search != null && !search.equals("")) {
			totalSales = dbm
					.getTotalSalesByClient(clients.get(0).getClientID());
			clients.addAll(dbm.getClientsById(Integer.parseInt(search)));
			salesClients.add(new ClientBeanWithSales(clients.get(0)
					.getClientID(), clients.get(0).getTitle(), clients.get(0)
					.getFirstName(), clients.get(0).getLastName(), clients.get(
					0).getCompanyName(), clients.get(0).getAddress1(), clients
					.get(0).getAddress2(), clients.get(0).getCity(), clients
					.get(0).getProvince(), clients.get(0).getCountry(), clients
					.get(0).getPostalCode(), clients.get(0).getHomePhone(),
					clients.get(0).getCellPhone(), clients.get(0).getEmail(),
					clients.get(0).getPassword(), totalSales));
		} else {
			clients = dbm.getClients();
			for (int i = 0; i < clients.size(); i++) {
				totalSales = dbm.getTotalSalesByClient(clients.get(i)
						.getClientID());

				salesClients.add(new ClientBeanWithSales(clients.get(i)
						.getClientID(), clients.get(i).getTitle(), clients.get(
						i).getFirstName(), clients.get(i).getLastName(),
						clients.get(i).getCompanyName(), clients.get(i)
								.getAddress1(), clients.get(i).getAddress2(),
						clients.get(i).getCity(), clients.get(i).getProvince(),
						clients.get(i).getCountry(), clients.get(i)
								.getPostalCode(),
						clients.get(i).getHomePhone(), clients.get(i)
								.getCellPhone(), clients.get(i).getEmail(),
						clients.get(i).getPassword(), totalSales));

			}
		}

		request.setAttribute("clients", salesClients);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}