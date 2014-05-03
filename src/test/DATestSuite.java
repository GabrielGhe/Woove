/**
 * @author 0836605
 */
package test;

import com.mysql.jdbc.MysqlDataTruncation;

import da.DataInput;

public class DATestSuite {
	public static void main(String[] args){
		UnitTest test = new UnitTest();
		DataInput data = new DataInput();
		System.out.println("----- Beginning Test Cycle -----");
		//1
		System.out.println("*Creating Database Tables");
		
			test.testTableCreation();
			System.out.println("\tDatabase Tables Created");
		//6
		System.out.println("1. Commencing data insertion");
		/*
			data.insertData();
			System.out.println("\tDatabase Populated");
			
			test.testInsertAlbum();
			System.out.println("\tinsertAlbum Completed");
			
			test.testInsertTrack();
			System.out.println("\tinsertTrack Completed");
			
			test.testInsertClient();
			System.out.println("\tinsertClient Completed");
			
			test.testInsertInvoice();
			System.out.println("\tinsertInvoice Completed");
			
			test.testInsertInvoiceDetails();
			System.out.println("\tinsertInvoiceDetails Completed");
			
			test.testInsertReview();
			System.out.println("\tinsertReview Completed");
			*/
		//29
		System.out.println("2. Commencing data retrieval");
			
			test.testGetAlbumByArtist();
			System.out.println("\tgetAlbumByArtist Completed");
			
			test.testGetAlbumByName();
			System.out.println("\tgetAlbumByName Completed");
			
			test.testGetAlbumById();
			System.out.println("\tgetAlbumById Completed");
			
			test.testGetAlbums();
			System.out.println("\tgetAlbums Completed");
			
			test.testGetClientsByEmailAndPass();
			System.out.println("\tgetClientsByEmailAndPass Completed");
			
			test.testGetClientsById();
			System.out.println("\tgetClientsById Completed");
			
			test.testGetZeroClients();
			System.out.println("\tgetZeroClients Completed");
			
			test.testGetClients();
			System.out.println("\tgetClients Completed");
			
			test.testGetInvoices();
			System.out.println("\tgetInvoices Completed");
			
			test.testGetInvoicesByArtist();
			System.out.println("\tgetInvoicesByArtist Completed");
			
			test.testGetInvoicesByClient();
			System.out.println("\tgetInvoicesByClient Completed");
			
			test.testGetInvoicesByRange();
			System.out.println("\tgetInvoicesByRange Completed");
			
			test.testGetInvoicesDetailsByTrack();
			System.out.println("\tgetInvoicesDetailsByTrack Completed");
			
			test.testGetInvoiceDetails();
			System.out.println("\tgetInvoiceDetails Completed");
			
			test.testGetReviews();
			System.out.println("\tgetReviews Completed");
			
			test.testGetTracksByArtist();
			System.out.println("\tgetTracksByArtist Completed");
			
			test.testGetTracksByGenre();
			System.out.println("\tgetTracksByGenre Completed");
			
			test.testGetTracksById();
			System.out.println("\tgetTracksById Completed");
			
			test.testGetTracksByName();
			System.out.println("\tgetTracksByName Completed");
			
			test.testGetTracksPub();
			System.out.println("\tgetTracksPub Completed");
				
			test.testGetTrackByIdPub();
			System.out.println("\tgetTrackByIdPub Completed");
			
			test.testGetTrackByArtistPub();
			System.out.println("\tgetTrackByArtistPub Completed");
			
			test.testGetTrackByIdPubZero();
			System.out.println("\tgetTrackByIdPubZero Completed");
						
			test.testGetTopAlbums();
			System.out.println("\tgetSalesByAlbum Completed");
			
			test.testGetTrackByNamePub();
			System.out.println("\tgetTrackByNamePub Completed");

			test.testGetTopAlbums();
			System.out.println("\tgetTopAlbums Completed");
	
			test.testGetZeroTracksPub();
			System.out.println("\tgetZeroTracksPub Completed");
			
			test.testGetZeroTracks();
			System.out.println("\tgetZeroTracks Completed");
			
			test.testGetTracks();
			System.out.println("\tgetTracks Completed");
			
			test.testGetTopClients();
			System.out.println("\tGetTopClients Completed");
			
			test.testGetTopSellers();
			System.out.println("\tGetTopSellers Completed");
			
			test.testGettracksOnSale();
			System.out.println("\tGetTracksOnSale Completed");
			/*
		//6
		System.out.println("3. Commencing data update");
			test.testUpdateInvoiceDetail();
			System.out.println("\tupdateInvoicesDetails Completed");
					
			test.testUpdateInvoice();
			System.out.println("\tupdateInvoice Completed");
			
			test.testUpdateReview();
			System.out.println("\tupdateReview Completed");
			
			test.testUpdateClient();
			System.out.println("\tupdateClient Completed");
			
			test.testUpdateTrack();
			System.out.println("\tupdateTrack Completed");
			
			test.testUpdateAlbum();
			System.out.println("\tupdateAlbum Completed");
			
		//6
		
		System.out.println("4. Commencing data deleting");
			test.testDeleteInvoiceDetails();
			System.out.println("\tdeleteInvoiceDetails Completed");
	
			test.testDeleteInvoice();
			System.out.println("\tdeleteInvoice Completed");
			
			test.testDeleteReview();
			System.out.println("\tdeleteReview Completed");
			
			test.testDeleteClient();
			System.out.println("\tdeleteClient Completed");
			
			test.testDeleteTrack();
			System.out.println("\tdeleteTrack Completed");
			
			test.testDeleteAlbum();
			System.out.println("\tdeleteAlbum Completed");
			
		System.out.println("5. Commencing ad/rss/survey CRUD -D testing");
			test.testInsertAd();
			System.out.println("\tinsertAd Completed");
			
			test.testInsertRSS();
			System.out.println("\tinsertRSS Completed");
			
			test.testInsertSurvey();
			System.out.println("\tinsertSurvey Completed");
			
			test.testGetAds();
			System.out.println("\tgetAd Completed");
			
			test.testGetRSS();
			System.out.println("\tgetRSS Completed");
			
			test.testGetSurveys();
			System.out.println("\tgetSurveys Completed");
			
			test.testUpdateAd();
			System.out.println("\tupdateAd Completed");
			
			test.testUpdateRSS();
			System.out.println("\tupdateRSS Completed");
			
			test.testUpdateSurvey();
			System.out.println("\tupdateSurvey Completed");
			*/
		System.out.println("----- Test Cycle Successfully Completed -----");
	}
}