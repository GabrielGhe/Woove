/**
 * @author 0737019
 */

package beans;

import java.io.Serializable;

public class TopClientsBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1180457774126099017L;
	private int id;
	private String name;
	private float total;
	
	public TopClientsBean(){
		this.id = 0;
		this.name = "";
		this.total = 0;
	}
	
	public TopClientsBean(int id, String name, float total){
		this.id = id;
		this.name = name;
		this.total = total;
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	
}
