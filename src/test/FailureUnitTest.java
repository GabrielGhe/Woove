/**
 * @author 0836605
 */
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import beans.AlbumBean;
import beans.ClientBean;
import beans.InvoiceBean;
import beans.InvoiceDetailsBean;
import beans.ReviewBean;
import beans.TrackBean;

import da.DBManager;

public class FailureUnitTest {

	DBManager dbm = null;
	
	@Test
	public void testInsertAlbumFail(){
		dbm = new DBManager();
		assertFalse(dbm.insertAlbum(new AlbumBean(0, "derpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderp", "", "", "", 0, "", 0, 0, 0, 0,"","")));
	}
	
	@Test
	public void testInsertTrackFail(){
		dbm = new DBManager();
		assertFalse(dbm.insertTrack(new TrackBean(0, 0, "derpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderp", "", "", "", 0, "", "", 0, 0, 0, "", 0, 0)));
	}
	
	@Test
	public void testInsertInvoiceFail(){
		dbm = new DBManager();
		assertFalse(dbm.insertInvoice(new InvoiceBean(0, "derpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderp", 0, 0, 0, 0, 0, 0,0)));
	}
	
	@Test
	public void testInsertInvoiceDetailsFail(){
		dbm = new DBManager();
		assertFalse(dbm.insertInvoiceDetails(new InvoiceDetailsBean(0, -1, 0, 0, 0, 0, 0,0)));
	}
	
	@Test
	public void testInsertClientFail(){
		dbm = new DBManager();
		assertFalse(dbm.insertClient(new ClientBean(0, "", "", "derpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderp", "", "", "", "", "", "", "", "", "", "", "", "",0)));
	}
	
	@Test
	public void testInsertReviewFail(){
		dbm = new DBManager();
		assertFalse(dbm.insertReview(new ReviewBean(0, 0, "", "derpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderpderp", 0, "", 0,0)));
	}
	
	@Test
	public void testUpdateAlbumFail(){
		dbm = new DBManager();
		assertFalse(dbm.updateAlbum(new AlbumBean(-1, "2012-01-01", "", "", "", 0, "", 0, 0, 0, 0,"","")));
	}
	
	@Test
	public void testUpdateTrackFail(){
		dbm = new DBManager();
		assertFalse(dbm.updateTrack(new TrackBean(-1, 0, "2012-01-01", "", "", "", 0, "", "", 0, 0, 0, "", 0, 0)));
	}
	
	@Test
	public void testUpdateInvoiceFail(){
		dbm = new DBManager();
		assertFalse(dbm.updateInvoice(new InvoiceBean(-1, "2012-01-01", 0, 0, 0, 0, 0, 0,0)));
	}
	
	@Test
	public void testUpdateInvoiceDetailsFail(){
		dbm = new DBManager();
		assertFalse(dbm.updateInvoiceDetail(new InvoiceDetailsBean(-1, -1, 0, 0, 0, 0, 0,0)));
	}
	
	@Test
	public void testUpdateClientFail(){
		dbm = new DBManager();
		assertFalse(dbm.updateClient(new ClientBean(-1, "", "", "2012-01-01", "", "", "", "", "", "", "", "", "", "", "", "",0)));
	}
	
	@Test
	public void testUpdateReviewFail(){
		dbm = new DBManager();
		assertFalse(dbm.updateReview(new ReviewBean(-1, 0, "", "2012-01-01", 0, "", 0,0)));
	}
}