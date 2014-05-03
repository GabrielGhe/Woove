package da;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import beans.RSSItem;

public class RSSParser {

	private String feed;
	private ArrayList<RSSItem> items = new ArrayList<RSSItem>();

	public RSSParser() {
		feed = "";
	}

	public RSSParser(String feed) {
		this.feed = feed;
		parseFeed();
	}

	private void parseFeed() {
		XmlReader reader = null;
		try {
			URL url = new URL(feed);
			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				SyndEntry entry = (SyndEntry) i.next();
				items.add(new RSSItem(entry.getTitle(),entry.getLink()));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} finally {
				try {
					if (reader != null)
						reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public ArrayList<RSSItem> getRSSItem(){
		return items;
	}
	
	public void setFeed(String feed){
		this.feed=feed;
		parseFeed();
	}
}