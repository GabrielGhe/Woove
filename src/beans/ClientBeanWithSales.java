package beans;

import java.io.Serializable;

public class ClientBeanWithSales implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4769128987815986361L;
	private int clientID;
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private String companyName;
	private String address1;
	private String address2;
	private String city;
	private String province;
	private String country;
	private String postalCode;
	private String homePhone;
	private String cellPhone;
	private String password;
	private double totalSales;
	
	public ClientBeanWithSales()
	{
		this.clientID = 0;
		this.title = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.companyName = "";
		this.address1 = "";
		this.address2 = "";
		this.city = "";
		this.province = "";
		this.country = "";
		this.postalCode = "";
		this.homePhone = "";
		this.cellPhone = "";
		this.password = "";
		this.totalSales = 0.0;
	}
	
	public ClientBeanWithSales(int clientID, String title, String firstName, String lastName,
			String companyName, String address1, String address2,
			String city, String province, String country, String postalCode,
			String homePhone, String cellPhone, String email,String password,
			double totalSales) {
		
		this.clientID = clientID;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.companyName = companyName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.country = country;
		this.postalCode = postalCode;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
		this.password = password;
		this.totalSales = totalSales;
	}
	
	public int getClientID() {
		return clientID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	public double getTotalSales() {
		return totalSales;
	}


	@Override
	public String toString() {
		return "ClientBeanWithSales [clientID=" + clientID + ", title=" + title
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", companyName=" + companyName
				+ ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", province=" + province + ", country="
				+ country + ", postalCode=" + postalCode + ", homePhone="
				+ homePhone + ", cellPhone=" + cellPhone + ", password="
				+ password + ", Totalsales=" + totalSales + "]";
	}
	
}
