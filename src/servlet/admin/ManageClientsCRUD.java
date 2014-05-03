/**
 * @author 0737019
 */

package servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ClientBean;

import da.DBManager;

/**
 * Servlet implementation class ManageClients
 */
@WebServlet(name = "ManageClientsCRUD", urlPatterns = {"/manage_clientscrud_a", "/manage_clientscrud_u", "/manage_clientscrud_d"})
public class ManageClientsCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageClientsCRUD() {
        super();
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
		String url = "/manage_clients"; 
		
		String servletPath = request.getServletPath();
		if(request.getParameter("fid") != null){
			int id = 0;
			String title = "";
			String lastName= "";
			String firstName = "";
			String company = "";
			String address1 = "";
			String address2 = "";
			String city = "";
			String province = "";
			String country = "";
			String postal = "";
			String hphone = "";
			String cphone = "";
			String email = "";
			String password = "";
			//see which pattern was used to get here
			if("/manage_clientscrud_u".equalsIgnoreCase(servletPath)){
				id = Integer.parseInt(request.getParameter("fid"));
				title = (String) request.getParameter("ftitle");
				lastName= (String) request.getParameter("fln");
				firstName = (String) request.getParameter("ffn");
				company = (String) request.getParameter("fcn");
				address1 = (String) request.getParameter("fadd");
				address2 = (String) request.getParameter("fadd2");
				city = (String) request.getParameter("fcity");
				province = (String) request.getParameter("fprov");
				country = (String) request.getParameter("fcountry");
				postal = (String) request.getParameter("fpc");
				hphone = (String) request.getParameter("fhp");
				cphone = (String) request.getParameter("fcp");
				email = (String) request.getParameter("femail");
				password = (String) request.getParameter("fpwd");
				

				//make sure everything is set before adding
				if(		title != null || title.trim() != "" &&
						lastName != null || lastName.trim() != "" &&
						firstName != null || firstName.trim() != "" &&
						company != null || company.trim() != "" &&
						address1 != null || address1.trim() != "" &&
						address2 != null || address2.trim() != "" &&
						city != null || city.trim() != "" &&
						province != null || province.trim() != "" &&
						country != null || country.trim() != "" &&
						postal != null || postal.trim() != "" &&
						hphone != null || hphone.trim() != "" &&
						cphone != null || cphone.trim() != "" &&
						password != null || password.trim() != "" &&
						email != null || cphone.trim() != "")
				if(id > 0){
					//update client bean
					ClientBean client = new ClientBean(id, title, lastName, firstName, company, address1,
							address2, city, province, country, postal, hphone, cphone, email, password, null,0);
					dbm.updateClient(client);
				}
				}
			
			if("/manage_clientscrud_a".equalsIgnoreCase(servletPath)){
				id = Integer.parseInt(request.getParameter("fid"));
				title = (String) request.getParameter("ftitle");
				lastName= (String) request.getParameter("fln");
				firstName = (String) request.getParameter("ffn");
				company = (String) request.getParameter("fcn");
				address1 = (String) request.getParameter("fadd");
				address2 = (String) request.getParameter("fadd2");
				city = (String) request.getParameter("fcity");
				province = (String) request.getParameter("fprov");
				country = (String) request.getParameter("fcountry");
				postal = (String) request.getParameter("fpc");
				hphone = (String) request.getParameter("fhp");
				cphone = (String) request.getParameter("fcp");
				email = (String) request.getParameter("femail");
				password = (String) request.getParameter("fpwd");
				

				//make sure everything is set before adding
				if(		title != null || title.trim() != "" && title.length() <= 4 &&
						lastName != null || lastName.trim() != "" && title.length() <= 25 &&
						firstName != null || firstName.trim() != "" && title.length() <= 25 &&
						company != null || company.trim() != "" && title.length() <= 50 &&
						address1 != null || address1.trim() != "" &&
						address2 != null || address2.trim() != "" &&
						city != null || city.trim() != "" && title.length() <= 32 &&
						province != null || province.trim() != "" && title.length() <= 3 &&
						country != null || country.trim() != "" && title.length() <= 2 &&
						postal != null || postal.trim() != "" && title.length() <= 6 &&
						hphone != null || hphone.trim() != "" && title.length() <= 11 &&
						cphone != null || cphone.trim() != "" && title.length() <= 11 &&
						password != null || password.trim() != "" && title.length() <= 16 &&
						email != null || cphone.trim() != "")
				if(id < 0){
					//insert client bean
					ClientBean client = new ClientBean(id, title, lastName, firstName, company, address1,
							address2, city, province, country, postal, hphone, cphone, email, password, null,0);
					dbm.insertClient(client);
				}
		    }else if("/manage_clientscrud_d".equalsIgnoreCase(servletPath)){
		    	//delete client
		    	id = Integer.parseInt(request.getParameter("fid"));
		    	ClientBean client = dbm.getClientsById(id).get(0);
		    	client.setPassword("@@@@@@@@@@@@@");
		    	dbm.updateClient(client);
		    }
		}
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}