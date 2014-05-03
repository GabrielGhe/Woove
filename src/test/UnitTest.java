/**
 * @author 0836605
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import beans.*;
import da.*;

public class UnitTest {
private DBManager dbm = new DBManager();
	@Test
	public void testTableCreation() {
		dbm = new DBManager();
		dbm.openConnection();
	}
	
	//===========ad/rss/survey===========\\
	@Test
	public void testInsertAd() {
		dbm = new DBManager();
		assertTrue(dbm.insertAd(new AdBean(0, "fakeLink","fakeImgPath",0)));
	}
	
	@Test
	public void testInsertRSS() {
		dbm = new DBManager();
		assertTrue(dbm.insertRSS(new RSSFeed(0, "fakeLink",0)));
	}
	
	@Test
	public void testInsertSurvey() {
		dbm = new DBManager();
		assertTrue(dbm.insertSurvey(new SurveyBean(0, "fakeQ","fakeA1","fakeA2","fakeA3","fakeA4",0,0,0,0,0)));
	}
	
	@Test
	public void testGetAds(){
		dbm = new DBManager();
		assertTrue(dbm.getInactiveAds().get(0).getImgPath().equals("fakeImgPath"));
	}
	
	@Test
	public void testGetRSS(){
		dbm = new DBManager();
		assertTrue(dbm.getInactiveRSS().get(0).getLink().equals("fakeLink"));
	}
	
	
	@Test
	public void testGetSurveys(){
		dbm = new DBManager();
		assertTrue(dbm.getInactiveSurveys().get(0).getQ().equals("fakeQ"));
	}

	@Test
	public void testUpdateAd(){
		dbm = new DBManager();
		assertTrue(dbm.updateAd(new AdBean(1, "FAKE LINK","FAKE IMG PATH",0)));
	}
	
	@Test
	public void testUpdateRSS(){
		dbm = new DBManager();
		assertTrue(dbm.updateRSS(new RSSFeed(1, "FAKE LINK",0)));
	}
	
	@Test
	public void testUpdateSurvey(){
		dbm = new DBManager();
		assertTrue(dbm.updateSurvey(new SurveyBean(1, "FAKE Q","","","","",0,0,0,0,0)));
	}
	
	//6
	//----- Create -----//
	@Test
	public void testInsertAlbum() {
		dbm = new DBManager();
		assertTrue(dbm.insertAlbum(new AlbumBean(0, "", "2012-06-04", "", "", 0, "2012-06-04", 0, 0, 0, 0,"","")));
	}
	
	@Test
	public void testInsertTrack() {
		dbm = new DBManager();
		assertTrue(dbm.insertTrack(new TrackBean(0, 0, "", "", "", "", 0, "", "", 0, 0, 0, "2012-06-04", 0, 1)));
	}
	@Test
	public void testInsertClient() {
		dbm = new DBManager();
		assertTrue(dbm.insertClient(new ClientBean(0, "", "", "", "", "", "", "", "", "", "", "", "", "hurr@durr.com", "", "",0)));
	}
	@Test
	public void testInsertReview() {
		dbm = new DBManager();
		assertTrue(dbm.insertReview(new ReviewBean(0, 65, "2012-06-04", "", 0, "", 0,0)));
	}
	@Test
	public void testInsertInvoice() {
		dbm = new DBManager();
		assertTrue(dbm.insertInvoice(new InvoiceBean(0, "2012-06-04", 1, 0, 0, 0, 0, 0,0)));
	}
	@Test
	public void testInsertInvoiceDetails() {
		dbm = new DBManager();
		assertTrue(dbm.insertInvoiceDetails(new InvoiceDetailsBean(0, 1,19,0 ,0,0.00F, 0,0)));
	}
	
	//26
	//----- Read -----//
		//Albums
		@Test
		public void testGetAlbumById(){
			dbm = new DBManager();
			assertTrue(dbm.getAlbumById(1).get(0)!=null);
		}
		@Test
		public void testGetAlbumByArtist(){
			dbm = new DBManager();
			assertTrue(dbm.getAlbumsByArtist("Kno").get(0)!=null);
		}
		@Test
		public void testGetAlbumByName(){
			dbm = new DBManager();
			assertTrue(dbm.getAlbumsByName("Death is Silent")
					.get(0).getTitle().equals("Death is Silent"));
		}
		@Test
		public void testGetAlbums(){
			dbm = new DBManager();
			assertTrue(dbm.getAlbums().size()==36);
		}
		//Clients
		@Test
		public void testGetClientsByEmailAndPass(){
			dbm = new DBManager();
			//assertTrue(dbm.getClientsByEmailAndPass("random@email.com", "password1234"));
		}
		@Test
		public void testGetClientsById(){
			dbm = new DBManager();
			assertTrue(dbm.getClientsById(1).get(0).getClientID()==1);
		}
		@Test
		public void testGetClients(){
			dbm = new DBManager();
			assertTrue(dbm.getClients().size()==7);
		}
		@Test									
		public void testGetZeroClients(){
			dbm = new DBManager();
			assertTrue(dbm.getZeroClients("2012-01-01","2013-12-31").size()==2);
		}
		
		public void testGetTopClients(){
			dbm = new DBManager();
			ArrayList<String[]> result = dbm.getTopClients("2012-01-01","2013-12-31");
			assertTrue(result.get(0)[0].equals("2") &&
						result.get(0)[3].equals("7.17") &&
						result.get(1)[0].equals("3") &&
						result.get(1)[3].equals("5.69") &&
						result.get(2)[0].equals("4") &&
						result.get(2)[3].equals("4.55"));
		}
		
		public void testGetTopSellers(){
			dbm = new DBManager();
			ArrayList<String[]> result = dbm.getTopSellers("2012-01-01","2013-12-31");			
			assertTrue(result.get(0)[0].equals("7") &&
					result.get(0)[4].equals("3.27") &&
					result.get(1)[0].equals("75") &&
					result.get(1)[4].equals("1.98"));
		}
		
		//Tracks
		@Test
		public void testGetTracksPub(){
			dbm = new DBManager();
			assertTrue(dbm.getTracksPub().size()==105);
		}
		
		@Test
		public void testGetTrackByIdPub(){
			dbm = new DBManager();
			assertTrue(dbm.getTrackByIdPub(69).size()==1);
		}
		
		@Test
		public void testGetTrackByIdPubZero(){
			dbm = new DBManager();
			assertTrue(dbm.getTrackByIdPub(107).size()==0);
		}
		
		@Test
		public void testGetTrackByNamePub(){
			dbm = new DBManager();
			assertTrue(dbm.getTrackByNamePub("They Told Me").size()==1);
		}
		
		@Test
		public void testGetZeroTracksPub(){
			dbm = new DBManager();
			assertTrue(dbm.getZeroTracksPub("2012-01-01","2013-12-31").size()==90);
		}
		
		@Test
		public void testGetTrackByArtistPub(){
			dbm = new DBManager();
			assertTrue(dbm.getTrackByArtistPub("Kno").size()==13);
		}
		
		@Test
		public void testGetTracksById(){
			dbm = new DBManager();
			assertTrue(dbm.getTrackById(25).get(0).getInventory_number()==25);
		}
		@Test
		public void testGetTopAlbums(){
			dbm = new DBManager();
			ArrayList<int[]> result = dbm.getTopAlbums("2012-01-01","2013-12-31");
			assertTrue(result.get(0)[0]==2 &&
					   result.get(0)[1]==2 &&
					   result.get(1)[0]==1 &&
					   result.get(1)[1]==1);
		}
		@Test
		public void testGetTracksByArtist(){
			dbm = new DBManager();
			assertTrue(dbm.getTrackByArtist("kno").size()==13);
		}	
		@Test
		public void testGetTracksByGenre(){
			dbm = new DBManager();
			assertTrue(dbm.getTrackByGenre("Hip-Hop")
					.get(0).getCategory().equals("Hip-Hop"));
		}
		@Test
		public void testGetTracksByName(){
			dbm = new DBManager();
			assertTrue(dbm.getTrackByName("They Told Me")
					.get(0).getTitle().equals("They Told Me"));
		}
		@Test
		public void testGetTracks(){
			dbm = new DBManager();
			assertTrue(dbm.getTracks().size()==106);
		}
		@Test
		public void testGetZeroTracks(){
			dbm = new DBManager();
			assertTrue(dbm.getZeroTracks("2012-01-01","2013-12-31").size()==91);
		}
		//Invoices
		@Test
		public void testGetInvoices(){
			dbm = new DBManager();
			assertTrue(dbm.getInvoices().size()>0);
		}		
		@Test
		public void testGetInvoicesByRange(){
			dbm = new DBManager();
			assertTrue(dbm.getInvoicesByRange("2013-01-01","2013-12-31")
					.size()==5);
		}	
		@Test
		public void testGetInvoicesByArtist(){
			dbm = new DBManager();
			assertTrue(dbm.getInvoicesByArtist("ASAP Rocky","2012-01-01","2013-12-31")
					.size()==1);
		}		
		@Test
		public void testGetInvoicesByClient(){
			dbm = new DBManager();
			assertTrue(dbm.getInvoicesByClient(1,"2012-01-01","2013-12-31")
					.size()==1);
		}
		//Invoice Details
		@Test
		public void testGetInvoiceDetails(){
			dbm = new DBManager();
			assertTrue(dbm.getInvoicesDetails().size()==21);
		}
		@Test
		public void testGetInvoicesDetailsByTrack(){
			dbm = new DBManager();
			assertTrue(dbm.getInvoicesDetailsByTrack(1,"2012-01-01","2013-12-31").size()==1);
		}
		//Reviews
		@Test
		public void testGetReviews(){
			dbm = new DBManager();
			assertTrue(dbm.getInvoices().size()==5);
		}
		
	//6
	//----- Update -----/
	@Test
	public void testUpdateAlbum(){
		dbm = new DBManager();
		assertTrue(dbm.updateAlbum(new AlbumBean(25, "test album", "2020-12-31", "test album", "test album", 1, "2025-01-31", 1, 1, 1, 1,"","")));
	}
	@Test
	public void testUpdateClient(){
		dbm = new DBManager();
		assertTrue(dbm.updateClient(new ClientBean(1, "Mr.", "updated client", "updated client", "updated client", "updated client", "updated client", "updated client", "qc", "CA", "h4b1z6", "updated", "updated", "updated", "updated", "updated",0)));
	}
	@Test
	public void testUpdateInvoice(){
		dbm = new DBManager();
		assertTrue(dbm.updateInvoice(new InvoiceBean(1, "2013-01-30", 0, 0, 0, 0, 0, 0,0)));
	}
	@Test
	public void testUpdateInvoiceDetail(){
		dbm = new DBManager();
		assertTrue(dbm.updateInvoiceDetail(new InvoiceDetailsBean(1, 1, 1, 0, 0 ,0.00F, 25.75F,0)));
	}
	@Test
	public void testUpdateReview(){
		dbm = new DBManager();
		assertTrue(dbm.updateReview(new ReviewBean(1, 0, "2020-12-31", "updated review", 0, "updated reivew", 0,0)));
	}
	@Test
	public void testUpdateTrack(){

		dbm = new DBManager();
		assertTrue(dbm.updateTrack(new TrackBean(25, 1, "updated track", "updated track", "updated track", "updated track", 0, "updated track", "updated track", 0, 0, 0, "updated track", 0, 0)));
	}
	
	//6
	//----- Delete -----/
	@Test
	public void testDeleteInvoiceDetails() {
		dbm = new DBManager();
		InvoiceDetailsBean detail = new InvoiceDetailsBean(8, 0, 0,0,0,0.00F, 0,0);
		assertTrue(dbm.deleteInvoiceDetails(detail));
	}
	@Test
	public void testDeleteInvoice() {
		dbm = new DBManager();
		InvoiceBean invoice = new InvoiceBean(2, null, 0, 0, 0, 0, 0, 0,0);
		assertTrue(dbm.deleteInvoice(invoice));
	}
	@Test
	public void testDeleteTrack() {
		dbm = new DBManager();
		TrackBean track = new TrackBean(1, 0, null, null, null, null, 0, null, null, 0, 0, 0, null, 0, 0);
		dbm.deleteInvoiceDetails(new InvoiceDetailsBean(1, 0, 0,0,0,0.00F, 0,0));
		assertTrue(dbm.deleteTrack(track));
	}
	@Test
	public void testDeleteClient() {
		dbm = new DBManager();
		ClientBean client = new ClientBean(5, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,0);
		assertTrue(dbm.deleteClient(client));
	}
	@Test
	public void testDeleteAlbum() {
		dbm = new DBManager();
		AlbumBean album = new AlbumBean(1, null, null, null, null, 0, null, 0, 0, 0, 0,"","");
		assertTrue(dbm.deleteAlbum(album));
	}
	@Test
	public void testDeleteReview() {
		dbm = new DBManager();
		ReviewBean review = new ReviewBean(17, 0, null, null, 0, null, 0,0);
		assertTrue(dbm.deleteReview(review));
	}
	
	@Test
	public void testGettracksOnSale() {
		dbm = new DBManager();
		ReviewBean review = new ReviewBean(17, 0, null, null, 0, null, 0,0);
		assertTrue(dbm.getTracksOnSale().get(0).getInventory_number()==61);
	}
}