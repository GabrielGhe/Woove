package beans;

import java.io.Serializable;

public class RSSItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7787964742360827018L;
	private String title;
	private String link;
	
	
	public RSSItem(){
		title="";
		link="";
	}
	
	public RSSItem(String title, String link){
		this.title=title;
		this.link=link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "RSSItem [title=" + title + ", link=" + link + "]";
	}
}