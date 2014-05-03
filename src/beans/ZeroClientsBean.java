package beans;

import java.io.Serializable;

public class ZeroClientsBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8609907012651799284L;
	private int id;
	private String name;
	
	public ZeroClientsBean(){
		this.id = 0;
		this.name = "";
	}
	
	public ZeroClientsBean(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
