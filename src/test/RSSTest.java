/**
 * @author 0836605
 */

package test;
 
import da.RSSParser;
 
public class RSSTest {
 
  public static void main(String[] args) throws Exception {  
    RSSParser parser = new RSSParser("http://www.mtv.com/rss/news/news_full.jhtml");
    
//	RSSParser parser = new RSSParser(dbm.getRSS().get(0).getLink().toString());
    
    
    for(int i =0;i<parser.getRSSItem().size();i++)
    	System.out.println(parser.getRSSItem().get(i).toString());
  }
}