package beans;

import java.io.Serializable;

public class RSSFeed implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3867170991562018309L;
	private String link;
	private int id;
	private int active;
	
	public RSSFeed(){
		this(0,"",0);
	}
	
	public RSSFeed(int id, String link,int active){
		this.id=id;
		this.link=link;
		this.active=active;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}