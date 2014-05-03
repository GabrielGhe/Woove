package beans;

import java.io.Serializable;

public class InvoiceBean implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5970483902933565384L;
	private int sale_number;
	private String date;
	private int client_number;
	private float total_net_value;
	private float pst;
	private float gst;
	private float hst;
	private float total_gross_value;
	private int removalStatus;
	
	public int getRemovalStatus() {
		return removalStatus;
	}

	public void setRemovalStatus(int removalStatus) {
		this.removalStatus = removalStatus;
	}

	public InvoiceBean(){
		this.sale_number = 0;
		this.date = "";
		this.client_number = 0;
		this.total_net_value = 0.00F;
		this.pst = 0.00F;
		this.gst = 0.00F;
		this.hst = 0.00F;
		this.total_gross_value = 0.00F;
		this.removalStatus=0;
	}
	
	public InvoiceBean(int sale_number, String date, int client_number,
			float total_net_value, float pst, float gst, float hst,
			float total_gross_value, int removalStatus) {
		super();
		this.sale_number = sale_number;
		this.date = date;
		this.client_number = client_number;
		this.total_net_value = total_net_value;
		this.pst = pst;
		this.gst = gst;
		this.hst = hst;
		this.total_gross_value = total_gross_value;
		this.removalStatus=removalStatus;
	}

	public int getSale_number() {
		return sale_number;
	}
	public void setSale_number(int sale_number) {
		this.sale_number = sale_number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getClient_number() {
		return client_number;
	}
	public void setClient_number(int client_number) {
		this.client_number = client_number;
	}
	public float getTotal_net_value() {
		return total_net_value;
	}
	public void setTotal_net_value(float total_net_value) {
		this.total_net_value = total_net_value;
	}
	public float getPst() {
		return pst;
	}
	public void setPst(float pst) {
		this.pst = pst;
	}
	public float getGst() {
		return gst;
	}
	public void setGst(float gst) {
		this.gst = gst;
	}
	public float getHst() {
		return hst;
	}
	public void setHst(float hst) {
		this.hst = hst;
	}
	public float getTotal_gross_value() {
		return total_gross_value;
	}
	public void setTotal_gross_value(float total_gross_value) {
		this.total_gross_value = total_gross_value;
	}

	@Override
	public String toString() {
		return "InvoiceBean [sale_number=" + sale_number + ", date=" + date
				+ ", client_number=" + client_number + ", total_net_value="
				+ total_net_value + ", pst=" + pst + ", gst=" + gst + ", hst="
				+ hst + ", total_gross_value=" + total_gross_value
				+ ", removalStatus=" + removalStatus + "]";
	}	
}