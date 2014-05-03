package beans;

import java.io.Serializable;

public class AdBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1906956002982570951L;
	private String link, imgPath;
	private int id;
	private int active;
	public AdBean(){
		this(0,"","",0);
	}
	
	public AdBean(int id, String link, String imgPath, int active){
		this.id=id;
		this.link=link;
		this.imgPath=imgPath;
		this.active=active;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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