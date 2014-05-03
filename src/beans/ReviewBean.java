package beans;

import java.io.Serializable;

public class ReviewBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5449003816770238452L;
	private int id;
	private int inventory_number;
	private String date;
	private String name_of_client;
	private int rating;
	private String review;
	private int approval_status;
	private int client_id;
	
	public ReviewBean(){
		this.id = 0;
		this.inventory_number = 0;
		this.date = "";
		this.name_of_client = "";
		this.rating = 0;
		this.review = "";
		this.approval_status = 0;
		this.client_id=0;
	}
	
	public ReviewBean(int id, int inventory_number, String date,
			String name_of_client, int rating, String review,
			int approval_status, int client_id) {
		this();
		this.id = id;
		this.inventory_number = inventory_number;
		this.date = date;
		this.name_of_client = name_of_client;
		this.rating = rating;
		this.review = review;
		this.approval_status = approval_status;
		this.client_id=client_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInventory_number() {
		return inventory_number;
	}
	public void setInventory_number(int inventory_number) {
		this.inventory_number = inventory_number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName_of_client() {
		return name_of_client;
	}
	public void setName_of_client(String name_of_client) {
		this.name_of_client = name_of_client;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(int approval_status) {
		this.approval_status = approval_status;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int clientID) {
		this.client_id = clientID;
	}
}