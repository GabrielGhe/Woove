package da;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import beans.AdBean;
import beans.AlbumBean;
import beans.ClientBean;
import beans.InvoiceBean;
import beans.InvoiceDetailsBean;
import beans.RSSFeed;
import beans.ReviewBean;
import beans.SurveyBean;
import beans.TrackBean;

public class DataInput {
		DBManager manager;
		String line = "";
		String[] row;
		
		public DataInput(){}
		
		public void insertData(){
			manager = new DBManager();
			//Albums insertion
			try {
				//School
//				File file = new File("H:\\Semester6\\Java\\g2w13\\albums.csv");
				//Home
//				File file = new File("H:\\School\\CompSci\\Semester6\\Web Development Project\\g2w13\\albums.csv");
//				Laptop
				File file = new File("C:\\GitHub\\Woove\\albums.csv");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line=reader.readLine())!=null){
					row=line.split("\t");
					manager.insertAlbum(new AlbumBean(0,row[1],row[2],row[3],row[4],
													  Integer.parseInt(row[5]),row[6],Double.parseDouble(row[7]),
													  Double.parseDouble(row[8]),Double.parseDouble(row[9]),
													  Integer.parseInt(row[10]), row[11], row[12]));
				}
				reader.close();
			} catch (IOException e) {e.printStackTrace();}
			
			//Tracks insertion
			try {
				//School
//				File file = new File("H:\\Semester6\\Java\\g2w13\\tracks.csv");
				//Home
//				File file = new File("H:\\School\\CompSci\\Semester6\\Web Development Project\\g2w13\\tracks.csv");
				//Laptop
				File file = new File("C:\\GitHub\\Woove\\tracks.csv");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line=reader.readLine())!=null){
					row=line.split("\t");
					manager.insertTrack(new TrackBean(0,Integer.parseInt(row[1]),row[2],row[3],row[4],
													  row[5],Integer.parseInt(row[6]),row[7],row[8],Float.parseFloat(row[9]),
													  Float.parseFloat(row[10]),Float.parseFloat(row[11]),row[12],Integer.parseInt(row[13]),Integer.parseInt(row[14])));
				}
				reader.close();
			} catch (IOException e) {e.printStackTrace();}
			
			//Clients insertion
			try {
				//School
//				File file = new File("H:\\Semester6\\Java\\g2w13\\clients.csv");
				//Home
//				File file = new File("H:\\School\\CompSci\\Semester6\\Web Development Project\\g2w13\\clients.csv");
				//Laptop
				File file = new File("C:\\GitHub\\Woove\\clients.csv");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line=reader.readLine())!=null){
					row=line.split("\t");
					manager.insertClient(new ClientBean(0,row[1],row[2],row[3],row[4],row[5],row[6],row[7],
													 row[8],row[9],row[10],row[11],row[12],row[13],row[14],"",0));
				}
				reader.close();
			} catch (IOException e){e.printStackTrace();}
			
			//Invoice insertion
			try {
				//School
//				File file = new File("H:\\Semester6\\Java\\g2w13\\invoices.csv");
				//Home
//				File file = new File("H:\\School\\CompSci\\Semester6\\Web Development Project\\g2w13\\invoices.csv");
				//Laptop
				File file = new File("C:\\GitHub\\Woove\\invoices.csv");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line=reader.readLine())!=null){
					row=line.split("\t");
					manager.insertInvoice(new InvoiceBean(0, row[1], Integer.parseInt(row[2]), Float.parseFloat(row[3]), Float.parseFloat(row[4]),
															Float.parseFloat(row[5]), Float.parseFloat(row[6]), Float.parseFloat(row[7]),0));
				}
				reader.close();
			} catch (IOException e){e.printStackTrace();}
			
			//InvoicesDetails insertion
			try {
				//School
//				File file = new File("H:\\Semester6\\Java\\g2w13\\invoice_details.csv");
				//Home
//				File file = new File("H:\\School\\CompSci\\Semester6\\Web Development Project\\g2w13\\invoice_details.csv");
				//Laptop
				File file = new File("C:\\GitHub\\Woove\\invoice_details.csv");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line=reader.readLine())!=null){
					row=line.split("\t");
					manager.insertInvoiceDetails(new InvoiceDetailsBean(0, Integer.parseInt(row[1]), Integer.parseInt(row[2]),
							Integer.parseInt(row[3]),Integer.parseInt(row[4]),Float.parseFloat(row[5]), Float.parseFloat(row[6]),0));
				}
				reader.close();
			} catch (IOException e){e.printStackTrace();}
			
			//Reviews insertion
			try {
				//School
//				File file = new File("H:\\Semester6\\Java\\g2w13\\reviews.csv");
				//Home
//				File file = new File("H:\\School\\CompSci\\Semester6\\Web Development Project\\g2w13\\reviews.csv");
				//Laptop
				File file = new File("C:\\GitHub\\Woove\\reviews.csv");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line=reader.readLine())!=null){
					row=line.split("\t");
					manager.insertReview(new ReviewBean(0, Integer.parseInt(row[1]), row[2], row[3],
							Integer.parseInt(row[4]), row[5], Integer.parseInt(row[6]),0));
				}
				reader.close();
			} catch (IOException e){e.printStackTrace();}
			
			manager.insertAd(new AdBean(0,"http://www.videolan.org/vlc/index.html","largeVLC.png",1));
			manager.insertAd(new AdBean(0,"http://www.winamp.com/","winamp.png",1));
			manager.insertRSS(new RSSFeed(0,"http://www.mtv.com/rss/news/news_full.jhtml",1));
			manager.insertSurvey(new SurveyBean(0,"Am I losing my mind?","Yes","Certainly","","",100,150,0,0,1));
	}
		public static void main (String[] args){
			DataInput app = new DataInput();
			app.insertData();
		}
}