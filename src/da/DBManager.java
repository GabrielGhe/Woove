package da;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import beans.AdBean;
import beans.AlbumBean;
import beans.ClientBean;
import beans.DBConfigBean;
import beans.InvoiceBean;
import beans.InvoiceDetailsBean;
import beans.RSSFeed;
import beans.ReviewBean;
import beans.SurveyBean;
import beans.TrackBean;
import beans.ZeroTrackBean;

/**
 * Database Manager used to open a connection to a database, perform create,
 * read, update, and delete procedures, and then close the database connection.
 * 
 * @author 0836605
 * @co-author 0737019
 */
public class DBManager {
	private Logger logger = Logger.getLogger(getClass().getName());
	private Connection connection = null;
	private DBProperties props = null;
	private DBConfigBean configs = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/**
	 * 0-param constructor instantiating the ContactTableModel, MailTableModel,
	 * FolderTbaleModel, MailProperties, and MailConfigBean.
	 */
	public DBManager() {
		props = new DBProperties();
		configs = props.loadProperties();
		checkTableExistence();
	}

	/**
	 * 
	 * @param realPath
	 */
	public DBManager(String realPath) {
		props = new DBProperties();
		props.setRealPath(realPath);
		configs = props.loadProperties();
		checkTableExistence();
	}

	/**
	 * Connection function used for unit testing.
	 */
	private void unitTestConnect() {
		try {
			// Creating the connection url consisting of protocols, server, port
			// number, and database name.
			String url = "jdbc:mysql://" + configs.getServer() + ":"
					+ configs.getPort() + "/" + configs.getDatabase();
			String user = configs.getLogin();
			String password = configs.getPassword();
			// Creating a connection to the database using the connetion url,
			// user name, and password
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unable to connect to database!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unexpected error!", e);
		}
	}

	/**
	 * Connection method used to access the database specified in the
	 * MailProperties.
	 */
	private void connect() {
		try {
			// Obtain environment naming context
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// Look up data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/woove");
			// Allocate and use a connection from the pool
			connection = ds.getConnection();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unable to connect to database!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unexpected error!", e);
		}
	}

	/**
	 * Opens a connection to the database, calls connect()
	 */
	public void openConnection() {
//		connect();
		unitTestConnect();
	}

	/**
	 * Checks if the tables exist in the database
	 */
	private void checkTableExistence() {
		Statement statement;
		ResultSet tables;
		DatabaseMetaData meta;

		try {
			openConnection();
			meta = connection.getMetaData();
			tables = meta.getTables(null, null, "clients", null);
			// Checks if table 'clients' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table clients("
								+ "client_id			int unsigned not null primary key auto_increment,"
								+ "title 				varchar(4) not null,"
								+ "first_name 			varchar(25) not null,"
								+ "last_name 			varchar(25) not null,"
								+ "company_name		varchar(50) not null,"
								+ "address_1			varchar(512) not null,"
								+ "address_2			varchar(512) default '',"
								+ "city				varchar(32) not null,"
								+ "province			varchar(3) not null,"
								+ "country				varchar(2) not null,"
								+ "postal_code			varchar(6) not null,"
								+ "home_phone			varchar(11) not null,"
								+ "cell_phone			varchar(11) default '',"
								+ "email				varchar(128) unique not null,"
								+ "password			varchar(16) not null,"
								+ "last_search			varchar(64),"
								+ "user_status			tinyint not null" + ")");
				statement.close();
			}

			tables = meta.getTables(null, null, "invoices", null);
			// Checks if table 'invoices' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table invoices("
								+ "sale_number			int unsigned not null primary key auto_increment,"
								+ "date_of_sale		date not null,"
								+ "client_id		int unsigned not null,"
								+ "total_net_value		float(5,2) not null,"
								+ "pst					float(5,2) not null,"
								+ "gst					float(5,2) not null,"
								+ "hst					float(5,2) not null,"
								+ "total_gross_value	float(5,2) not null,"
								+ "removal_status		tinyint not null,"
								+ "foreign key (client_id) references clients(client_id)"
								+ ")");
				statement.close();
			}

			tables = meta.getTables(null, null, "albums", null);
			// Checks if table 'albums' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table albums("
								+ "album_number		int unsigned not null primary key auto_increment,"
								+ "title				varchar(128) not null,"
								+ "release_date		date not null,"
								+ "artist				varchar(50) not null,"
								+ "label				varchar(128) not null,"
								+ "track_num			int unsigned not null,"
								+ "date_entered		date not null,"
								+ "cost_price			float(4,2) not null,"
								+ "list_price			float(4,2) not null,"
								+ "sale_price			float(4,2) not null,"
								+ "removal_status		tinyint not null,"
								+ "genre				varchar(50) not null,"
								+ "cover_img_name		varchar(50) not null" + ")");
				statement.close();
			}

			tables = meta.getTables(null, null, "tracks", null);
			// Checks if table 'tracks' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table tracks("
								+ "inventory_number	int unsigned not null primary key auto_increment,"
								+ "album_number		int unsigned,"
								+ "title				varchar(128) not null,"
								+ "artist				varchar(75) not null,"
								+ "writer				varchar(256) not null,"
								+ "track_length		varchar(50) not null,"
								+ "track_number		int unsigned not null,"
								+ "category			varchar(50) not null,"
								+ "cover_img_name		varchar(50) not null,"
								+ "cost_price			float(4,2) not null,"
								+ "list_price			float(4,2) not null,"
								+ "sale_price			float(4,2) not null,"
								+ "date_entered		date not null,"
								+ "selling_state		int unsigned not null,"
								+ "removal_status		tinyint not null,"
								+ "foreign key (album_number) references albums (album_number)"
								+ ")");
				statement.close();
			}

			tables = meta.getTables(null, null, "reviews", null);
			// Checks if table 'reviews' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table reviews("
								+ "_id					int unsigned not null primary key auto_increment,"
								+ "inventory_number	int unsigned,"
								+ "date_of_review		date not null,"
								+ "name_of_client		varchar(128) not null,"
								+ "client_id			int unsigned not null,"
								+ "rating				tinyint(1) unsigned not null,"
								+ "review				varchar(2000) not null,"
								+ "approval_status	tinyint(1) not null,"
								+ "foreign key (inventory_number) references tracks(inventory_number)"
								+ ")");
				statement.close();
			}

			tables = meta.getTables(null, null, "invoices_details", null);
			// Checks if table 'invoices_details' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table invoices_details("
								+ "_id					int unsigned not null primary key auto_increment,"
								+ "sale_number			int unsigned not null,"
								+ "inventory_number		int unsigned default null,"
								+ "album_number			int unsigned default null,"
								+ "tracks_in_album		int unsigned not null,"
								+ "price_of_album		float(5,2) not null,"
								+ "price_at_sale		float(5,2) unsigned not null,"
								+ "removal_status		tinyint not null,"
								+ "foreign key (sale_number) references invoices(sale_number),"
								+ "foreign key (inventory_number) references tracks(inventory_number),"
								+ "foreign key (album_number) references albums(album_number)"
								+ ")");
				statement.close();
			}

			tables = meta.getTables(null, null, "ads", null);
			// Checks if table 'ads' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table ads("
								+ "id		int unsigned not null primary key auto_increment,"
								+ "link	varchar(256) not null,"
								+ "imgPath	varchar(256) not null,"
								+ "active	tinyint not null" + ");");
				statement.close();
			}

			tables = meta.getTables(null, null, "rss", null);
			// Checks if table 'rss' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table rss("
								+ "id		int unsigned not null primary key auto_increment,"
								+ "link	varchar(256) not null,"
								+ "active	tinyint not null" + ");");
				statement.close();
			}

			tables = meta.getTables(null, null, "survey", null);
			// Checks if table 'survey' exists
			if (!tables.next()) {
				statement = connection.createStatement();
				statement
						.execute("create table survey("
								+ "id				int unsigned not null primary key auto_increment,"
								+ "question		varchar(256) not null,"
								+ "answer_1		varchar(256) not null,"
								+ "answer_2		varchar(256) not null,"
								+ "answer_3		varchar(256) not null,"
								+ "answer_4		varchar(256) not null,"
								+ "answer_1count	int unsigned not null,"
								+ "answer_2count	int unsigned not null,"
								+ "answer_3count	int unsigned not null,"
								+ "answer_4count	int unsigned not null,"
								+ "active	tinyint not null" + ");");
				statement.close();
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error creating database tables!", e);
		} finally {
			close();
		}
	}

	/**
	 * Method used to close the connection to the database.
	 */
	private void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unable to close connection to database!",
					e);
		}
	}

	/**
	 * @return ArrayList containing the names of the genres in the database.
	 */
	public ArrayList<String> getGenres() {
		ArrayList<String> genres = new ArrayList<String>();
		Statement statement = null;
		String query = "Select distinct category from tracks";

		try {
			openConnection();
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(query);
			while (results.next())
				genres.add(results.getString(1));
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Problem retrieving records.", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}

		return genres;
	}

	/**
	 * Inserts a new TrackBean into the database.
	 * 
	 * @param track
	 *            value to be inserted into the database.
	 * @return true if the TrackBean was successfully inserted into the
	 *         database, orelse false.
	 */
	public boolean insertTrack(TrackBean track) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			openConnection();
			statement = connection
					.prepareStatement("INSERT INTO tracks "
							+ "(inventory_number, album_number, title, artist, writer, track_length, "
							+ "track_number, category, cover_img_name, cost_price, list_price, sale_price, "
							+ "date_entered, selling_state, removal_status) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setInt(2, track.getAlbum_number());
			statement.setString(3, track.getTitle());
			statement.setString(4, track.getArtist());
			statement.setString(5, track.getWriter());
			statement.setString(6, track.getTrack_length());
			statement.setInt(7, track.getTrack_number());
			statement.setString(8, track.getCategory());
			statement.setString(9, track.getCover_img_name());
			statement.setFloat(10, track.getCost_price());
			statement.setFloat(11, track.getList_price());
			statement.setFloat(12, track.getSale_price());
			statement.setString(13, track.getDate_entered());
			statement.setInt(14, track.getSelling_state());
			statement.setInt(15, track.getRemoval_status());

			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			e.printStackTrace();
			// logger.log(Level.WARNING, "Unable to insert track!",
			// e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Returns all the tracks in the database
	 * 
	 * @return list of tracks
	 */
	public ArrayList<TrackBean> getTracks() {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from tracks");
			while (resultSet.next()) {
				tracks.add(new TrackBean(resultSet.getInt(1), resultSet
						.getInt(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getString(5), resultSet
						.getString(6), resultSet.getInt(7), resultSet
						.getString(8), resultSet.getString(9), resultSet
						.getFloat(10), resultSet.getFloat(11), resultSet
						.getFloat(12), resultSet.getString(13), resultSet
						.getInt(14), resultSet.getInt(15)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return tracks;
	}

	/**
	 * @return ArrayList containing all tracks on sale.
	 */
	public ArrayList<TrackBean> getTracksOnSale() {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from tracks where removal_status = 0 AND sale_price > 0.00");
			while (resultSet.next()) {
				tracks.add(new TrackBean(resultSet.getInt(1), resultSet
						.getInt(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getString(5), resultSet
						.getString(6), resultSet.getInt(7), resultSet
						.getString(8), resultSet.getString(9), resultSet
						.getFloat(10), resultSet.getFloat(11), resultSet
						.getFloat(12), resultSet.getString(13), resultSet
						.getInt(14), resultSet.getInt(15)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return tracks;
	}

	/**
	 * @return ArrayList containing all albums on sale.
	 */
	public ArrayList<AlbumBean> getAlbumsOnSale() {
		ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from albums where removal_status = 0 AND sale_price > 0.00");
			while (resultSet.next()) {
				albums.add(new AlbumBean(resultSet.getInt(1), resultSet
						.getString(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getString(5), resultSet
						.getInt(6), resultSet.getString(7), resultSet
						.getDouble(8), resultSet.getDouble(9), resultSet
						.getDouble(10), resultSet.getInt(11), resultSet
						.getString(12), resultSet.getString(13)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return albums;
	}
	
	/**
	 * Gets the client rating of the desired track.
	 * @param id identifying the track to get the rating for.
	 * @return average rating of the track.
	 */
	public float getTrackRanking(int id) {
		float rating = 0.00F;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (id > 0) {
			try {
				openConnection();
				statement = connection
						.prepareStatement("Select avg(rating) from tracks tt, reviews rt where tt.inventory_number = ? AND tt.inventory_number = rt.inventory_number AND approval_status =1");
				// inserting values into PreparedStatement
				statement.setInt(1, id);

				// execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				if (resultSet.next())
					rating = resultSet.getFloat(1);
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return rating;
	}

	/**
	 * Returns last 3 the tracks added to the database
	 * 
	 * @return list of last three tracks added to the database
	 */
	public ArrayList<TrackBean> getLastThreeTracksPub() {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from tracks Where removal_status = 0 ORDER BY date_entered DESC LIMIT 3");
			while (resultSet.next()) {
				tracks.add(new TrackBean(resultSet.getInt(1), resultSet
						.getInt(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getString(5), resultSet
						.getString(6), resultSet.getInt(7), resultSet
						.getString(8), resultSet.getString(9), resultSet
						.getFloat(10), resultSet.getFloat(11), resultSet
						.getFloat(12), resultSet.getString(13), resultSet
						.getInt(14), resultSet.getInt(15)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return tracks;
	}

	/**
	 * Returns all the tracks in the database that are available to be sold.
	 * 
	 * @return list of tracks which can be sold.
	 */
	public ArrayList<TrackBean> getTracksPub() {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from tracks Where removal_status = 0");
			while (resultSet.next()) {
				tracks.add(new TrackBean(resultSet.getInt(1), resultSet
						.getInt(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getString(5), resultSet
						.getString(6), resultSet.getInt(7), resultSet
						.getString(8), resultSet.getString(9), resultSet
						.getFloat(10), resultSet.getFloat(11), resultSet
						.getFloat(12), resultSet.getString(13), resultSet
						.getInt(14), resultSet.getInt(15)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return tracks;
	}

	/**
	 * Get the tracks that have the specified name
	 * 
	 * @param track name
	 * @return the list of tracks with the entered name
	 */
	public ArrayList<TrackBean> getTrackByName(String name) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (name != null && !name.equals("")) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where title like ?");
				statement.setString(1, "%" + name + "%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Get the tracks that have the specified name
	 * 
	 * @param track name
	 * @return the list of tracks with the entered name which are available to be sold
	 */
	public ArrayList<TrackBean> getTrackByNamePub(String name) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (name != null && !name.equals("")) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where title like ? AND removal_status = 0");
				statement.setString(1, "%" + name +"%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Get the Track that has the specified id
	 * 
	 * @param id of track to retrieve
	 * @return track which has the id entered
	 */
	public ArrayList<TrackBean> getTrackById(int id) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (id >= 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where inventory_number = ?");
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
			
				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Get the Tracks that has the specified album id
	 * 
	 * @param id of album to search by
	 * @return tracks containing the specified album id
	 */
	public ArrayList<TrackBean> getTrackByAlbum(int album) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (album >= 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where album_number = ?");
				//inserting values into PreparedStatement
				statement.setInt(1, album);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Get the Track that has the specified id if it's public
	 * 
	 * @param id of track to retrieve
	 * @return track with the id specified that is available to sell.
	 */
	public ArrayList<TrackBean> getTrackByIdPub(int id) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (id >= 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where inventory_number = ? AND removal_status = 0");
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Get albums by the genre
	 * 
	 * @param genre to search by
	 * @return albums matching genre
	 */
	public ArrayList<AlbumBean> getAlbumsByGenre(String genre) {
		ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (genre != null && !genre.equals("")) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from albums where genre like ?");
				statement.setString(1, "%" + genre + "%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					albums.add(new AlbumBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getInt(6), resultSet.getString(7), resultSet
							.getDouble(8), resultSet.getDouble(9), resultSet
							.getDouble(10), resultSet.getInt(11), resultSet
							.getString(12), resultSet.getString(13)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return albums;
	}

	/**
	 * Get the Track that have the specified category
	 * 
	 * @param category to search by
	 * @return list of tracks matching the genre
	 */
	public ArrayList<TrackBean> getTrackByGenre(String category) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (category != null && !category.equals("")) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where category like ?");
				statement.setString(1, "%" + category + "%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				

				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Get the Tracks that have the specified category and are available to sell
	 * 
	 * @param category to search
	 * @return list of tracks matching category and available to sell
	 */
	public ArrayList<TrackBean> getTrackByGenrePub(String category) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (category != null && !category.equals("")) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where category like ? AND removal_status = 0");
				statement.setString(1, "%" + category + "%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Get the Tracks that have the specified artist
	 * 
	 * @param artist to search by
	 * @return list of tracks by the artist
	 */
	public ArrayList<TrackBean> getTrackByArtist(String artist) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (artist != null && !artist.equals("")) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where artist like ?");
				statement.setString(1, "%" + artist +"%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Get the Tracks that have the specified artist
	 * 
	 * @param artist to search by
	 * @return list of tracks batching the artist
	 */
	public ArrayList<TrackBean> getTrackByArtistPub(String artist) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (artist != null && !artist.equals("")) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where artist like ? AND removal_status = 0");
				statement.setString(1, "%" + artist +"%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Returns all the tracks in the database that have not been purchased
	 * 
	 * @return list of tracks
	 */
	public ArrayList<ZeroTrackBean> getZeroTracks(String start, String end) {
		ArrayList<ZeroTrackBean> tracks = new ArrayList<ZeroTrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (start != null && !start.equals("") && end != null
				&& !end.equals("")) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select inventory_number, title, artist, list_price from tracks where inventory_number NOT IN (Select invoices_details.inventory_number "
						+ "from invoices_details, invoices Where invoices_details.inventory_number is not null AND invoices_details.sale_number = "
						+ "invoices.sale_number AND invoices_details.inventory_number is not NULL AND invoices.date_of_sale BETWEEN  date(?) AND date(?))");
				statement.setString(1, start);
				statement.setString(2, end);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					tracks.add(new ZeroTrackBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getString(3), resultSet
							.getFloat(4)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Returns all the tracks in the database that have not been purchased
	 * 
	 * @return list of tracks
	 */
	public ArrayList<TrackBean> getZeroTracksPub(String start, String end) {
		ArrayList<TrackBean> tracks = new ArrayList<TrackBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (start != null && !start.equals("") && end != null
				&& !end.equals("")) {
			try {			
				openConnection();
				statement = connection.prepareStatement("Select * from tracks where inventory_number NOT IN (Select invoices_details.inventory_number "
						+ "from invoices_details, invoices Where invoices_details.inventory_number is not null AND invoices_details.sale_number = "
						+ "invoices.sale_number AND invoices.date_of_sale BETWEEN  date(?) AND date(?)) AND tracks.removal_status = 0");
				statement.setString(1, start);
				statement.setString(2, end);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					tracks.add(new TrackBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getFloat(10), resultSet.getFloat(11), resultSet
							.getFloat(12), resultSet.getString(13), resultSet
							.getInt(14), resultSet.getInt(15)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return tracks;
	}

	/**
	 * Updates a track given a Track bean
	 * 
	 * @param track TrackBean containing values to update to database
	 * @return valid boolean value if it worked
	 */
	public boolean updateTrack(TrackBean track) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			String query = "UPDATE tracks " + "SET album_number="
					+ track.getAlbum_number() + ", artist='"
					+ track.getArtist() + "', writer='" + track.getWriter()
					+ "', track_length='" + track.getTrack_length()
					+ "', title='" + track.getTitle() + "', category='"
					+ track.getCategory() + "', cover_img_name='"
					+ track.getCover_img_name() + "', list_price="
					+ track.getList_price() + ", cost_price="
					+ track.getCost_price() + ", sale_price="
					+ track.getSale_price() + ", selling_state="
					+ track.getSelling_state() + ", removal_status="
					+ track.getRemoval_status() + ", track_number="
					+ track.getTrack_number() + " WHERE inventory_number="
					+ track.getInventory_number();
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected Error updating track.",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Delete a track from the DB
	 * 
	 * @param track TrackBean to delete from the database
	 * @return valid boolean value that says if it deleted the track properly
	 */
	public boolean deleteTrack(TrackBean track) {
		boolean valid = true;
		// Query to delete a track
		String query = "DELETE From tracks WHERE inventory_number="
				+ track.getInventory_number();
		PreparedStatement statement = null;

		try {
			openConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected error deleting track.", e);
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	// -------------------------------------- ALBUM
	// ------------------------------------------//
	/**
	 * Inserts an album
	 * 
	 * @param album AlbumBean yo insert into the database
	 * @return valid boolean value if it worked
	 */
	public boolean insertAlbum(AlbumBean album) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			openConnection();
			statement = connection
					.prepareStatement("INSERT INTO albums "
							+ "(album_number, title, release_date, artist, label, "
							+ "track_num, date_entered, cost_price, list_price, sale_price, "
							+ "removal_status, genre, cover_img_name) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setString(2, album.getTitle());
			statement.setString(3, album.getReleaseDate());
			statement.setString(4, album.getArtist());
			statement.setString(5, album.getLabel());
			statement.setInt(6, album.getNumOfTracks());
			statement.setString(7, album.getDateEntered());
			statement.setDouble(8, album.getCostPrice());
			statement.setDouble(9, album.getListPrice());
			statement.setDouble(10, album.getSalePrice());
			statement.setInt(11, album.getRemovalStatus());
			statement.setString(12, album.getImgName());
			statement.setString(13, album.getGenre());

			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records < 1)
				valid = false;
		} catch (SQLException e) {
			// e.printStackTrace();
			logger.log(Level.WARNING, "Unable to insert album!", e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Returns all the albums in the database
	 * 
	 * @return list of albums
	 */
	public ArrayList<AlbumBean> getAlbums() {
		ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from albums");

			while (resultSet.next()) {
				albums.add(new AlbumBean(resultSet.getInt(1), resultSet
						.getString(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getString(5), resultSet
						.getInt(6), resultSet.getString(7), resultSet
						.getDouble(8), resultSet.getDouble(9), resultSet
						.getDouble(10), resultSet.getInt(11), resultSet
						.getString(12), resultSet.getString(13)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return albums;
	}

	/**
	 * Get the Ablums that have the specified name
	 * 
	 * @param name
	 * @return list of albums
	 */
	public ArrayList<AlbumBean> getAlbumsByName(String name) {
		ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (name != null && !name.equals("")) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from albums where title like ?");
				statement.setString(1, "%" + name + "%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					albums.add(new AlbumBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getInt(6), resultSet.getString(7), resultSet
							.getDouble(8), resultSet.getDouble(9), resultSet
							.getDouble(10), resultSet.getInt(11), resultSet
							.getString(12), resultSet.getString(13)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return albums;
	}

	/**
	 * Get the Ablum that has the specified id
	 * 
	 * @param id
	 * @return album
	 */
	public ArrayList<AlbumBean> getAlbumById(int id) {
		ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (id >= 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from albums where album_number = ?");
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					albums.add(new AlbumBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getInt(6), resultSet.getString(7), resultSet
							.getDouble(8), resultSet.getDouble(9), resultSet
							.getDouble(10), resultSet.getInt(11), resultSet
							.getString(12), resultSet.getString(13)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return albums;
	}

	/**
	 * Get the Ablums that have the specified artist
	 * 
	 * @param artist
	 * @return list of albums
	 */
	public ArrayList<AlbumBean> getAlbumsByArtist(String artist) {
		ArrayList<AlbumBean> albums = new ArrayList<AlbumBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (artist != null && !artist.equals("")) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from albums where artist like ?");
				statement.setString(1, "%" + artist + "%");
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					albums.add(new AlbumBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getInt(6), resultSet.getString(7), resultSet
							.getDouble(8), resultSet.getDouble(9), resultSet
							.getDouble(10), resultSet.getInt(11), resultSet
							.getString(12), resultSet.getString(13)));
				}
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem retrieving records.", e);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return albums;
	}

	/**
	 * Updates an Album
	 * 
	 * @param album
	 *            AlbumBean
	 * @return valid boolean value if it worked
	 */
	public boolean updateAlbum(AlbumBean album) {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String update = "UPDATE albums " + "SET title='"
					+ album.getTitle() + "', artist='" + album.getArtist()
					+ "', label='" + album.getLabel() + "'" + ", track_num="
					+ album.getNumOfTracks() + ", cost_price="
					+ album.getCostPrice() + ", list_price="
					+ album.getListPrice() + ", sale_price="
					+ album.getSalePrice() + ", date_entered='"
					+ album.getDateEntered() + "'" + ", release_date='"
					+ album.getReleaseDate() + "'" + ", removal_status="
					+ album.getRemovalStatus() + ", genre='" + album.getGenre()
					+ "', " + "cover_img_name='" + album.getImgName() + "'"
					+ " WHERE album_number=" + album.getAlbumID();
			openConnection();
			statement = connection.prepareStatement(update);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected Error updating contact.",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Delete an album from the DB
	 * 
	 * @param album
	 * @return
	 */
	public boolean deleteAlbum(AlbumBean album) {
		boolean valid = true;
		// Query to delete a album
		String query = "DELETE From albums WHERE album_number="
				+ album.getAlbumID();
		PreparedStatement statement = null;
		try {
			openConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected error deleting album.", e);
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	// --------------------------------- CLIENT
	// ----------------------------------- //

	/**
	 * Insert a client
	 * 
	 * @param client
	 *            ClientBean
	 * @return valid boolean value if it worked
	 */
	public boolean insertClient(ClientBean client) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			openConnection();
			statement = connection
					.prepareStatement("INSERT INTO clients "
							+ "(client_id, title, first_name, last_name, company_name, "
							+ "address_1, address_2, city, province, country, "
							+ "postal_code, home_phone, cell_phone, email, password, last_search,user_status) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setString(2, client.getTitle());
			statement.setString(3, client.getFirstName());
			statement.setString(4, client.getLastName());
			statement.setString(5, client.getCompanyName());
			statement.setString(6, client.getAddress1());
			statement.setString(7, client.getAddress2());
			statement.setString(8, client.getCity());
			statement.setString(9, client.getProvince());
			statement.setString(10, client.getCountry());
			statement.setString(11, client.getPostalCode());
			statement.setString(12, client.getHomePhone());
			statement.setString(13, client.getCellPhone());
			statement.setString(14, client.getEmail());
			statement.setString(15, client.getPassword());
			statement.setString(16, client.getLastSearch());
			statement.setInt(17, client.getUserStatus());

			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records < 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Unable to insert client!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Returns all the clients in the database
	 * 
	 * @return list of clients
	 */
	public ArrayList<ClientBean> getClients() {
		ArrayList<ClientBean> clients = new ArrayList<ClientBean>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from clients");

			while (resultSet.next()) {
				clients.add(new ClientBean(resultSet.getInt(1), resultSet
						.getString(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getString(5), resultSet
						.getString(6), resultSet.getString(7), resultSet
						.getString(8), resultSet.getString(9), resultSet
						.getString(10), resultSet.getString(11), resultSet
						.getString(12), resultSet.getString(13), resultSet
						.getString(14), resultSet.getString(15), resultSet
						.getString(16), resultSet.getInt(17)));

			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return clients;
	}

	/**
	 * Gets top clients
	 * 
	 * @param start
	 *            date string beginning
	 * @param end
	 *            date string ending
	 * @return ArrayList<String[]>
	 */
	public ArrayList<String[]> getTopClients(String start, String end) {
		String query;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<String[]> clients = new ArrayList<String[]>();

		try {			
			openConnection();
			statement = connection.prepareStatement("select client_id, first_name, last_name, sum(total_gross_value) from invoices join clients using(client_id) "
					+ "where date_of_sale between date(?) AND date(?) group by client_id "
					+ "order by sum(total_gross_value) desc");
			statement.setString(1, start);
			statement.setString(2, end);
			
			//execute statement obtaining the number of inserted rows
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				String[] item = new String[4];
				item[0] = "" + resultSet.getInt(1);
				item[1] = resultSet.getString(2);
				item[2] = resultSet.getString(3);
				item[3] = resultSet.getString(4);
				clients.add(item);
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return clients;
	}

	public ArrayList<int[]> getTopAlbums(String start, String end) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<int[]> albums = new ArrayList<int[]>();

		try {
			openConnection();
			statement = connection.prepareStatement("select album_number, count(album_number) from invoices_details join invoices using(sale_number) "
					+ "where album_number is not null AND invoices.date_of_sale between date(?) AND date(?) "
					+ "group by album_number order by count(album_number) desc");
			statement.setString(1, start);
			statement.setString(2, end);
			
			//execute statement obtaining the number of inserted rows
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int[] item = new int[2];
				item[0] = resultSet.getInt(1);
				item[1] = resultSet.getInt(2);
				albums.add(item);
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return albums;
	}

	/**
	 * Gets top tracks
	 * 
	 * @param start
	 *            date string beginning
	 * @param end
	 *            date string ending
	 * @return ArrayList<String[]>
	 */
	public ArrayList<String[]> getTopSellers(String start, String end) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<String[]> tracks = new ArrayList<String[]>();

		try {			
			openConnection();
			statement = connection.prepareStatement("select inventory_number,title,artist,list_price,sum(idt.price_at_sale) "
					+ "from invoices_details idt "
					+ "join invoices using(sale_number) "
					+ "join tracks using(inventory_number) "
					+ "where invoices.date_of_sale between date(?) AND date(?) "
					+ "group by inventory_number "
					+ "order by sum(idt.price_at_sale) desc");
			statement.setString(1, start);
			statement.setString(2, end);
			
			
			//execute statement obtaining the number of inserted rows
			resultSet = statement.executeQuery();
			
			
			while (resultSet.next()) {
				String[] item = new String[5];
				item[0] = "" + resultSet.getInt(1);
				item[1] = resultSet.getString(2);
				item[2] = resultSet.getString(3);
				item[3] = "" + resultSet.getFloat(4);
				item[4] = "" + resultSet.getFloat(5);
				tracks.add(item);
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return tracks;
	}

	/**
	 * Get the Clients that have the specified id
	 * 
	 * @param id
	 * @return list of Clients
	 */
	public ArrayList<ClientBean> getClientsById(int id) {
		ArrayList<ClientBean> clients = new ArrayList<ClientBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (id > 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from clients where client_id = ?");
				
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					clients.add(new ClientBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getString(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getString(10), resultSet.getString(11), resultSet
							.getString(12), resultSet.getString(13), resultSet
							.getString(14), resultSet.getString(15), resultSet
							.getString(16), resultSet.getInt(17)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return clients;
	}

	/**
	 * Get the Clients that have the specified email and password
	 * 
	 * @param id
	 * @return list of Clients
	 */
	public ClientBean getClientsByEmailAndPass(String email, String password) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ClientBean client = null;

		if (email != null && !email.equals("") && password != null
				&& !password.equals("")) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from clients where email =? && password = ?");
				
				//inserting values into PreparedStatement
				statement.setString(1, email);
				statement.setString(2, password);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				if (resultSet.next())
					client = new ClientBean(resultSet.getInt(1),
							resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getString(5),
							resultSet.getString(6), resultSet.getString(7),
							resultSet.getString(8), resultSet.getString(9),
							resultSet.getString(10), resultSet.getString(11),
							resultSet.getString(12), resultSet.getString(13),
							resultSet.getString(14), resultSet.getString(15),
							resultSet.getString(16), resultSet.getInt(17));

			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return client;
	}

	/**
	 * Returns all the clients in the database that have not purchased anything
	 * 
	 * @return list of clients
	 */
	public ArrayList<ClientBean> getZeroClients(String start, String end) {
		ArrayList<ClientBean> clients = new ArrayList<ClientBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (start != null && !start.equals("") && end != null
				&& !end.equals("")) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from clients Where client_id "
						+ "NOT IN (Select invoices.client_id from invoices, clients "
						+ "where invoices.client_id = clients.client_id AND invoices.date_of_sale BETWEEN "
						+ "date(?) AND date(?))");
				statement.setString(1, start);
				statement.setString(2, end);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					clients.add(new ClientBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5), resultSet
							.getString(6), resultSet.getString(7), resultSet
							.getString(8), resultSet.getString(9), resultSet
							.getString(10), resultSet.getString(11), resultSet
							.getString(12), resultSet.getString(13), resultSet
							.getString(14), resultSet.getString(15), resultSet
							.getString(16), resultSet.getInt(17)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return clients;
	}

	/**
	 * Updates a client
	 * 
	 * @param client
	 *            ClientBean
	 * @return valid boolean value if it worked
	 */
	public boolean updateClient(ClientBean client) {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE Clients " + "SET title='"
					+ client.getTitle() + "', first_name='"
					+ client.getFirstName() + "', last_name='"
					+ client.getLastName() + "', company_name='"
					+ client.getCompanyName() + "', address_1='"
					+ client.getAddress1() + "'" + ", address_2='"
					+ client.getAddress2() + "', city='" + client.getCity()
					+ "', province='" + client.getProvince() + "'"
					+ ", postal_code='" + client.getPostalCode() + "', email='"
					+ client.getEmail() + "'" + ", country='"
					+ client.getCountry() + "', home_phone='"
					+ client.getHomePhone() + "', cell_phone='"
					+ client.getCellPhone() + "', last_search='"
					+ client.getLastSearch() + "', password='"
					+ client.getPassword() + "'" + "WHERE client_id="
					+ client.getClientID();
			openConnection();
			statement = connection.prepareStatement(query);

			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected Error updating client.",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Delete a client from the DB
	 * 
	 * @param client
	 * @return
	 */
	public boolean deleteClient(ClientBean client) {
		boolean valid = true;
		// Query to delete a client
		String query = "DELETE From clients WHERE client_id="
				+ client.getClientID();
		PreparedStatement statement = null;

		try {
			openConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected error deleting client.", e);
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	// ----------------------------------- INVOICE
	// ----------------------------//
	/**
	 * Insert Invoice
	 * 
	 * @param invoice
	 *            InvoiceBean
	 * @return valid boolean value if it worked
	 */
	public boolean insertInvoice(InvoiceBean invoice) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			openConnection();
			statement = connection
					.prepareStatement("INSERT INTO invoices "
							+ "(sale_number, date_of_sale, client_id, total_net_value, pst, "
							+ "gst, hst, total_gross_value,removal_status) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)");
			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setString(2, invoice.getDate());
			statement.setInt(3, invoice.getClient_number());
			statement.setFloat(4, invoice.getTotal_net_value());
			statement.setFloat(5, invoice.getGst());
			statement.setFloat(6, invoice.getPst());
			statement.setFloat(7, invoice.getHst());
			statement.setFloat(8, invoice.getTotal_gross_value());
			statement.setInt(9, invoice.getRemovalStatus());

			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records < 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Unable to insert invoice!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Returns all the invoices in the database
	 * 
	 * @return list of invoices
	 */
	public ArrayList<InvoiceBean> getInvoices() {
		ArrayList<InvoiceBean> invoices = new ArrayList<InvoiceBean>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from invoices");
			
			while (resultSet.next()) {
				invoices.add(new InvoiceBean(resultSet.getInt(1), resultSet
						.getString(2), resultSet.getInt(3), resultSet
						.getFloat(4), resultSet.getFloat(5), resultSet
						.getFloat(6), resultSet.getFloat(7), resultSet
						.getFloat(8), resultSet.getInt(9)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return invoices;
	}

	/**
	 * Returns all the invoices in the database given range
	 * 
	 * @return list of invoices
	 */
	public ArrayList<InvoiceBean> getInvoicesByRange(String start, String end) {
		ArrayList<InvoiceBean> invoices = new ArrayList<InvoiceBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (start != null && !start.equals("") && end != null
				&& !end.equals("")) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from invoices where date_of_sale BETWEEN date(?) AND date(?)");
				statement.setString(1, start);
				statement.setString(2, end);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					invoices.add(new InvoiceBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getInt(3), resultSet
							.getFloat(4), resultSet.getFloat(5), resultSet
							.getFloat(6), resultSet.getFloat(7), resultSet
							.getFloat(8), resultSet.getInt(9)));
				}

			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return invoices;
	}

	/**
	 * Returns all the invoices given the date range and client number in the
	 * database
	 * 
	 * @return list of invoices
	 */
	public ArrayList<InvoiceBean> getInvoicesByClient(int clientNum,
			String start, String end) {
		ArrayList<InvoiceBean> invoices = new ArrayList<InvoiceBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (start != null && !start.equals("") && end != null
				&& !end.equals("") && clientNum >= 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from invoices where client_id = ? AND date_of_sale BETWEEN "
						+ "date(?) AND date(?)");
				
				//inserting values into PreparedStatement
				statement.setInt(1, clientNum);
				statement.setString(2, start);
				statement.setString(3, end);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					invoices.add(new InvoiceBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getInt(3), resultSet
							.getFloat(4), resultSet.getFloat(5), resultSet
							.getFloat(6), resultSet.getFloat(7), resultSet
							.getFloat(8), resultSet.getInt(9)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return invoices;
	}

	/**
	 * Returns all the invoices given the date range and sale number in the
	 * database
	 * 
	 * @return list of invoices
	 */
	public ArrayList<InvoiceBean> getInvoicesById(int id) {
		ArrayList<InvoiceBean> invoices = new ArrayList<InvoiceBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (id > 0) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from invoices where sale_number = ?");
				
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					invoices.add(new InvoiceBean(resultSet.getInt(1), resultSet
							.getString(2), resultSet.getInt(3), resultSet
							.getFloat(4), resultSet.getFloat(5), resultSet
							.getFloat(6), resultSet.getFloat(7), resultSet
							.getFloat(8), resultSet.getInt(9)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return invoices;
	}

	/**
	 * Updates an invoice
	 * 
	 * @param invoice
	 *            InvoiceBean
	 * @return valid boolean value if it worked
	 */
	public boolean updateInvoice(InvoiceBean invoice) {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE Invoices " + "SET date_of_sale='"
					+ invoice.getDate() + "' ,total_net_value="
					+ invoice.getTotal_net_value() + ", total_gross_value="
					+ invoice.getTotal_gross_value() + ", pst="
					+ invoice.getPst() + ", gst=" + invoice.getGst() + ", hst="
					+ invoice.getHst() + " WHERE sale_number="
					+ invoice.getSale_number();
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected Error updating invoice.",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Delete an invoice from the DB
	 * 
	 * @param invoice
	 *            The invoice to be deleted
	 * @return
	 */
	public boolean deleteInvoice(InvoiceBean invoice) {
		boolean valid = true;
		// Query to delete an invoice
		String query = "DELETE From invoices WHERE sale_number="
				+ invoice.getSale_number();
		PreparedStatement statement = null;

		try {
			openConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected error deleting invoice.", e);
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	// --------------------------- INVOICE DETAILS ----------------------- //

	/**
	 * Insert Invoice detail
	 * 
	 * @param details
	 *            InvoiceDetailBean
	 * @return valid boolean value if it worked
	 */
	public boolean insertInvoiceDetails(InvoiceDetailsBean details) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			openConnection();
			statement = connection
					.prepareStatement("INSERT INTO invoices_details "
							+ "(_id, sale_number, inventory_number, album_number, tracks_in_album, price_of_album, price_at_sale,removal_status) "
							+ " VALUES (?,?,?,?,?,?,?,?)");
			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setInt(2, details.getSale_number());
			if (details.getInventory_number() == 0)
				statement.setString(3, null);
			else
				statement.setInt(3, details.getInventory_number());
			if (details.getAlbum_number() == 0)
				statement.setString(4, null);
			else
				statement.setInt(4, details.getAlbum_number());
			statement.setInt(5, details.getTracks_in_album());
			statement.setFloat(6, details.getPrice_of_album());
			statement.setFloat(7, details.getPrice_at_sale());
			statement.setInt(8, details.getRemoval_status());
			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records < 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Unable to insert invoice_details!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Returns all the invoices details in the database
	 * 
	 * @return list of invoices details
	 */
	public ArrayList<InvoiceDetailsBean> getInvoicesDetails() {
		ArrayList<InvoiceDetailsBean> invoicesDetails = new ArrayList<InvoiceDetailsBean>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from invoices_details");
			while (resultSet.next()) {
				invoicesDetails.add(new InvoiceDetailsBean(resultSet.getInt(1),
						resultSet.getInt(2), resultSet.getInt(3), resultSet
								.getInt(4), resultSet.getInt(5), resultSet
								.getFloat(6), resultSet.getFloat(7), 0));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return invoicesDetails;
	}

	/**
	 * Returns all the invoices details given date range and track number in the
	 * database
	 * 
	 * @return list of invoices details
	 */
	public ArrayList<InvoiceDetailsBean> getInvoicesDetailsByTrack(int track,
			String start, String end) {
		ArrayList<InvoiceDetailsBean> invoicesDetails = new ArrayList<InvoiceDetailsBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (start != null && !start.equals("") && end != null
				&& !end.equals("") && track >= 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select invoices_details.* from invoices_details, invoices "
						+ "where invoices_details.sale_number"
						+ " = invoices.sale_number "
						+ " AND invoices_details.inventory_number = ? AND invoices.date_of_sale BETWEEN"
						+ " date(?) AND date(?);");
				
				//inserting values into PreparedStatement
				statement.setInt(1, track);
				statement.setString(2, start);
				statement.setString(3, end);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				resultSet.beforeFirst();
				while (resultSet.next()) {
					invoicesDetails.add(new InvoiceDetailsBean(resultSet
							.getInt(1), resultSet.getInt(2), resultSet
							.getInt(3), 0, resultSet.getInt(4), resultSet
							.getFloat(5), resultSet.getFloat(6), 0));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return invoicesDetails;
	}

	/**
	 * Returns all the invoices details given the invoice number
	 * 
	 * @return list of invoices details
	 */
	public ArrayList<InvoiceDetailsBean> getInvoicesDetailsByInvoice(
			int sale_number) {
		ArrayList<InvoiceDetailsBean> invoicesDetails = new ArrayList<InvoiceDetailsBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (sale_number > 0) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from invoices_details where sale_number = ?");
				
				//inserting values into PreparedStatement
				statement.setInt(1, sale_number);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				resultSet.beforeFirst();
				while (resultSet.next()) {
					invoicesDetails.add(new InvoiceDetailsBean(resultSet
							.getInt(1), resultSet.getInt(2), resultSet
							.getInt(3), resultSet.getInt(4), resultSet
							.getInt(5), resultSet.getFloat(6), resultSet
							.getFloat(7), resultSet.getInt(8)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return invoicesDetails;
	}

	/**
	 * Returns all the invoices details given the invoice number
	 * 
	 * @return list of invoices details
	 */
	public ArrayList<InvoiceDetailsBean> getInvoicesDetailsById(int id) {
		ArrayList<InvoiceDetailsBean> invoicesDetails = new ArrayList<InvoiceDetailsBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (id > 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from invoices_details where _id = ?");
				
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				resultSet.beforeFirst();
				while (resultSet.next()) {
					invoicesDetails.add(new InvoiceDetailsBean(resultSet
							.getInt(1), resultSet.getInt(2), resultSet
							.getInt(3), 0, resultSet.getInt(4), resultSet
							.getFloat(5), resultSet.getFloat(6), 0));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return invoicesDetails;
	}

	/**
	 * Returns all the invoices details in the database that have an artist
	 * 
	 * @return list of invoices details
	 */
	public ArrayList<InvoiceDetailsBean> getInvoicesByArtist(String artist,
			String start, String end) {
		ArrayList<InvoiceDetailsBean> invoicesDetails = new ArrayList<InvoiceDetailsBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (start != null && !start.equals("") && end != null
				&& !end.equals("") && artist != null && !artist.equals("")) {
			try {
				openConnection();
				statement = connection.prepareStatement("SELECT invoices_details.* from invoices_details, invoices, tracks where invoices.sale_number "
						+ "= invoices_details.sale_number AND invoices_details.inventory_number = tracks.inventory_number "
						+ "AND tracks.artist = ? AND invoices.date_of_sale BETWEEN "
						+ "date(?) AND date(?)");
				
				//inserting values into PreparedStatement
				statement.setString(1, artist);
				statement.setString(2, start);
				statement.setString(3, end);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					invoicesDetails.add(new InvoiceDetailsBean(resultSet
							.getInt(1), resultSet.getInt(2), resultSet
							.getInt(3), resultSet.getInt(4), resultSet
							.getInt(5), resultSet.getFloat(6), resultSet
							.getFloat(7), 0));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING,
						"Problem retrieving invoices by artist!", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return invoicesDetails;
	}

	/**
	 * Updates an invoice details
	 * 
	 * @param idb
	 *            InvoiceDetailsBean
	 * @return valid boolean value if it worked
	 */
	public boolean updateInvoiceDetail(InvoiceDetailsBean idb) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			String query = "UPDATE Invoices_Details "
					+ "SET sale_number="
					+ idb.getSale_number()
					+ ", inventory_number="
					+ idb.getInventory_number()
					+ ", album_number= "
					+ (idb.getAlbum_number() == 0 ? null : idb
							.getAlbum_number()) + ", price_at_sale="
					+ idb.getPrice_at_sale() + "WHERE _id=" + idb.getId();
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"Unexpected Error updating invoice_details!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Deletes an InvoiceDetails record
	 * 
	 * @param idb
	 *            InvoiceDetailsBean
	 * @return valid boolean value if it worked
	 */
	public boolean deleteInvoiceDetails(InvoiceDetailsBean idb) {
		boolean valid = true;
		// Query to delete an invoice detail
		String query = "DELETE From invoices_details WHERE _id="
				+ idb.getId();
		PreparedStatement statement = null;

		try {
			openConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"Unexpected error deleting invoice_details!", e);
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	// ---------------------- REVIEWS ---------------------- //
	/**
	 * Insert a review
	 * 
	 * @param review
	 *            ReviewBean
	 * @return valid boolean value if it worked
	 */
	public boolean insertReview(ReviewBean review) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			// create the SQL PreparedStatement.
			openConnection();
			statement = connection
					.prepareStatement("INSERT INTO reviews "
							+ "(_id, inventory_number, date_of_review, name_of_client, client_id, rating, "
							+ "review, approval_status) "
							+ "VALUES (?,?,?,?,?,?,?,?)");
			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setInt(2, review.getInventory_number());
			statement.setString(3, review.getDate());
			statement.setString(4, review.getName_of_client());
			statement.setInt(5, review.getClient_id());
			statement.setInt(6, review.getRating());
			statement.setString(7, review.getReview());
			statement.setInt(8, 0);

			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records < 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Unable to insert review!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Returns all the reviews in the database
	 * 
	 * @return list of reviews
	 */
	public ArrayList<ReviewBean> getReviews() {
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from reviews");
			while (resultSet.next()) {
				reviews.add(new ReviewBean(resultSet.getInt(1), resultSet
						.getInt(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getInt(6), resultSet
						.getString(7), resultSet.getInt(8), resultSet.getInt(5)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return reviews;
	}

	/**
	 * Get the Reviews that have the specified id
	 * 
	 * @param id
	 * @return ReviewBean
	 */
	public ArrayList<ReviewBean> getReviewById(int id) {
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (id > 0) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from reviews Where _id = ?");
				
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					reviews.add(new ReviewBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getInt(6), resultSet
							.getString(7), resultSet.getInt(8), resultSet
							.getInt(5)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return reviews;
	}

	/**
	 * Returns all the reviews in the database that have not been approved
	 * 
	 * @return list of reviews
	 */
	public ArrayList<ReviewBean> getReviewsUnapproved() {
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from reviews Where approval_status = 0");
			while (resultSet.next()) {
				reviews.add(new ReviewBean(resultSet.getInt(1), resultSet
						.getInt(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getInt(6), resultSet
						.getString(7), resultSet.getInt(8), resultSet.getInt(5)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return reviews;
	}

	/**
	 * Get the Reviews that have the specified id
	 * 
	 * @param id
	 * @return ReviewBean
	 */
	public ArrayList<ReviewBean> getReviewApprovedById(int id) {
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (id > 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select * from reviews Where approval_status > 0 AND  inventory_number = ?");
				
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					reviews.add(new ReviewBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getInt(6), resultSet
							.getString(7), resultSet.getInt(8), resultSet
							.getInt(5)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return reviews;
	}

	/**
	 * Updates a Review
	 * 
	 * @param review
	 *            ReviewBean
	 * @return
	 */
	public boolean updateReview(ReviewBean review) {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE Reviews " + "SET date_of_review='"
					+ review.getDate() + "', name_of_client='"
					+ review.getName_of_client() + "', rating="
					+ review.getRating() + ", review='" + review.getReview()
					+ "', approval_status=" + review.getApproval_status()
					+ " WHERE _id=" + review.getId();
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected Error updating review!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Delete a review from the DB
	 * 
	 * @param review
	 * @return
	 */
	public boolean deleteReview(ReviewBean review) {
		boolean valid = true;
		String query = "DELETE From reviews WHERE _id=" + review.getId();
		PreparedStatement statement = null;

		try {
			openConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected error deleting review!", e);
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	// ============ Ads, RSS, Survey CRUD -D ============\\

	/**
	 * Inserts an ad into the database.
	 * @param ad to be inserted into the database
	 * @return boolean indicating if ad was successfully added to the database
	 */
	public boolean insertAd(AdBean ad) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			// create the SQL PreparedStatement.
			openConnection();
			statement = connection.prepareStatement("INSERT INTO ads "
					+ "(id, link, imgPath,active) " + "VALUES (?,?,?,?)");
			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setString(2, ad.getLink());
			statement.setString(3, ad.getImgPath());
			statement.setInt(4, ad.getActive());
			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records < 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Unable to insert review!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Inserts an rss feed into the database.
	 * @param rss feed to be inserted into the database
	 * @return boolean indicating if rss feed was successfully added to the database
	 */
	public boolean insertRSS(RSSFeed rss) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			// create the SQL PreparedStatement.
			openConnection();
			statement = connection.prepareStatement("INSERT INTO rss "
					+ "(id, link,active) " + "VALUES (?,?,?)");
			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setString(2, rss.getLink());
			statement.setInt(3, rss.getActive());
			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records < 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Unable to insert review!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Inserts a survey into the database.
	 * @param survey to be inserted into the database
	 * @return boolean indicating if survey was successfully added to the database
	 */
	public boolean insertSurvey(SurveyBean survey) {
		boolean valid = true;
		PreparedStatement statement = null;
		try {
			// create the SQL PreparedStatement.
			openConnection();
			statement = connection.prepareStatement("INSERT INTO survey "
					+ "(id, question, answer_1, answer_2, answer_3, "
					+ "answer_4, answer_1count, answer_2count, answer_3count, "
					+ "answer_4count,active) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			// inserting values into PreparedStatement
			statement.setString(1, null);
			statement.setString(2, survey.getQ());
			statement.setString(3, survey.getA1());
			statement.setString(4, survey.getA2());
			statement.setString(5, survey.getA3());
			statement.setString(6, survey.getA4());
			statement.setInt(7, survey.getA1Count());
			statement.setInt(8, survey.getA2Count());
			statement.setInt(9, survey.getA3Count());
			statement.setInt(10, survey.getA4Count());
			statement.setInt(11, survey.getActive());
			// execute statement obtaining the number of inserted rows
			int records = statement.executeUpdate();
			// if more than one row is affected, something failed, setting valid
			// to false
			if (records < 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Unable to insert review!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Retrieve all inactive ads from the database.
	 * @return ArrayList of all inactive ads.
	 */
	public ArrayList<AdBean> getInactiveAds() {
		ArrayList<AdBean> ads = new ArrayList<AdBean>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from ads where active=0");
			while (resultSet.next()) {
				ads.add(new AdBean(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getInt(4)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return ads;
	}

	/**
	 * Retrieve all inactive rss feeds from the database.
	 * @return ArrayList of all inactive rss feeds.
	 */
	public ArrayList<RSSFeed> getInactiveRSS() {
		ArrayList<RSSFeed> rss = new ArrayList<RSSFeed>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from rss where active=0");
			while (resultSet.next()) {
				rss.add(new RSSFeed(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getInt(3)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return rss;
	}

	/**
	 * Retrieve all inactive surveys from the database.
	 * @return ArrayList of all inactive surveys.
	 */
	public ArrayList<SurveyBean> getInactiveSurveys() {
		ArrayList<SurveyBean> survey = new ArrayList<SurveyBean>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from survey where active = 0");
			while (resultSet.next()) {
				survey.add(new SurveyBean(resultSet.getInt(1), resultSet
						.getString(2), resultSet.getString(3), resultSet
						.getString(4), resultSet.getString(5), resultSet
						.getString(6), resultSet.getInt(7),
						resultSet.getInt(8), resultSet.getInt(9), resultSet
								.getInt(10), resultSet.getInt(11)));
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return survey;
	}

	/**
	 * Updates an add in the database.
	 * @param ad AdBean containing the values to update to the database
	 * @return boolean indicating whether the ad was successfully updated or not.
	 */
	public boolean updateAd(AdBean ad) {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE ads " + "SET link='" + ad.getLink()
					+ "', imgPath='" + ad.getImgPath() + "' " + " WHERE id="
					+ ad.getId();
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected Error updating review!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Updates an rss feed in the database.
	 * @param rss RSSFeed containing the values to update to the database
	 * @return boolean indicating whether the rss was successfully updated or not.
	 */
	public boolean updateRSS(RSSFeed rss) {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE rss " + "SET link='" + rss.getLink()
					+ "' " + " WHERE id=" + rss.getId();
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected Error updating review!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Updates a survey in the database.
	 * @param survey SurveyFeed containing the values to update to the database
	 * @return boolean indicating whether the survey was successfully updated or not.
	 */
	public boolean updateSurvey(SurveyBean survey) {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE survey " + "SET question='"
					+ survey.getQ() + "', answer_1='" + survey.getA1() + "', "
					+ "answer_2='" + survey.getA2() + "', answer_3='"
					+ survey.getA3() + "', " + "answer_4='" + survey.getA4()
					+ "', answer_1count=" + survey.getA1Count() + ", "
					+ "answer_2count=" + survey.getA2Count()
					+ ", answer_3count=" + survey.getA3Count() + ", "
					+ "answer_4count=" + survey.getA4Count() + " WHERE id="
					+ survey.getId();
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected Error updating review!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	/**
	 * Get total sales to date between an indicated date range
	 * @param startDate lower end of the date range.
	 * @param endDate higher end of the date range.
	 * @return total of sales between indicated range.
	 */
	public ArrayList<String> getTotalSales(String startDate, String endDate) {
		String query = "Select sum(total_gross_value) gross from invoices";
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<String> item = new ArrayList<String>();
		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if (resultSet.next())
				item.add(resultSet.getString(1));
			if (item.size() != 0)
				item.add("0.00");
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return item;
	}

	/**
	 * Get total purchases by client to date between an indicated date range
	 * @param client client to search by.
	 * @param startDate lower end of the date range.
	 * @param endDate higher end of the date range.
	 * @return total of sales between indicated range.
	 */
	public ArrayList<String> getSalesByClient(String client, String startDate,
			String endDate) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<String> item = new ArrayList<String>();
		try {
			openConnection();
			statement = connection.prepareStatement("select sum(it.total_gross_value) " +
					"from (select distinct sale_number from invoices_details idt join invoices using(sale_number) " +
					"join clients using(client_id) where invoices.date_of_sale between date(?) AND date(?) " +
					"AND clients.first_name = ?) as dt join invoices it using(sale_number)");
			statement.setString(1, startDate);
			statement.setString(2, endDate);
			statement.setString(3, client);
			
			//execute statement obtaining the number of inserted rows
			resultSet = statement.executeQuery();
			if (resultSet.next())
				item.add("" + resultSet.getFloat(1));
			if (item.size() == 0)
				item.add("0.00");
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return item;
	}

	/**
	 * This method will return the amount of total purchases by client
	 * 
	 * @param clientId the id of the client
	 * @return - the total sales by client
	 */
	public double getTotalSalesByClient(int clientId) {
		String query = "SELECT sum(invoices_details.price_at_sale) "
				+ "FROM invoices_details join invoices using(sale_number) join clients using(client_id)"
				+ "WHERE clients.client_id = ?";
		
		double totalSales = 0.0;
		try {
			openConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, clientId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				do {
					totalSales += resultSet.getDouble(1);
				} while (resultSet.next());
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			close();
		}
		return totalSales;
	}

	/**
	 * This method will return the amount of total sales by the track
	 * 
	 * 
	 * @param track
	 *            - the name of the track
	 * @return - the total sales by that track
	 */
	public double getTotalSalesByTrack(String trackName) {
		double totalSales = 0.0;

		String query = "SELECT sum(invoices_details.price_at_sale) FROM "
				+ "invoices_details join invoices using(sale_number) join tracks using(inventory_number)"
				+ "WHERE inventory_number is not null AND tracks.title = ?";
		
		try {
			openConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, trackName);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				do {
					totalSales += resultSet.getDouble(1);
				} while (resultSet.next());
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			close();
		}

		return totalSales;

	}

	/**
	 * This method will return the amount of total sales by the album
	 * 
	 * 
	 * @param track
	 *            - the name of the album
	 * @return - the total sales by that album
	 */
	public double getTotalSalesByAlbum(String albumName) {
		double totalSales = 0.0;

		String query = "SELECT sum(invoices_details.price_of_album)"
				+ "FROM invoices_details join invoices using(sale_number) join albums using(album_number)"
				+ "WHERE album_number is not null AND albums.title = ?";

		try {
			openConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, albumName);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				do {
					totalSales += resultSet.getDouble(1);
				} while (resultSet.next());
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			close();
		}

		return totalSales;

	}

	/**
	 * Get total purchases by artist to date between an indicated date range
	 * @param artist artist to search by.
	 * @param startDate lower end of the date range.
	 * @param endDate higher end of the date range.
	 * @return total of sales between indicated range.
	 */
	public ArrayList<String> getSalesByArtist(String artist, String startDate,
			String endDate) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<String> item = new ArrayList<String>();
		try {
			openConnection();
			statement = connection.prepareStatement("select sum(idt.price_at_sale) "
					+ "from invoices_details idt, invoices it, tracks tt, albums at "
					+ "where  idt.sale_number = it.sale_number AND "
					+ "((idt.inventory_number = tt.inventory_number AND "
					+ "idt.inventory_number IS NOT NULL) OR (idt.album_number = at.album_number AND "
					+ "idt.album_number IS NOT null)) AND "
					+ "it.date_of_sale between date(?) AND "
					+ "date(?) AND (tt.artist = ? OR at.artist = ?)");
			statement.setString(1, startDate);
			statement.setString(2, endDate);
			statement.setString(3, artist);
			statement.setString(4, artist);
			//execute statement obtaining the number of inserted rows
			resultSet = statement.executeQuery();
			if (resultSet.next())
				item.add(resultSet.getString(1));
			if (item.size() == 0)
				item.add("0.00");
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return item;
	}

	/**
	 * Get total purchases by track to date between an indicated date range
	 * @param track track to search by.
	 * @param startDate lower end of the date range.
	 * @param endDate higher end of the date range.
	 * @return total of sales between indicated range.
	 */
	public ArrayList<String> getSalesByTrack(String track, String startDate,
			String endDate) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<String> item = new ArrayList<String>();
		try {
			openConnection();
			statement = connection.prepareStatement("select sum(invoices_details.price_at_sale) from "
					+ "invoices_details join invoices using(sale_number) join tracks using(inventory_number)where "
					+ "inventory_number is not null AND invoices.date_of_sale between "
					+ "date(?) AND date(?) "
					+ "AND tracks.title = ?");
			statement.setString(1, startDate);
			statement.setString(2, endDate);
			statement.setString(3, track);
			
			//execute statement obtaining the number of inserted rows
			resultSet = statement.executeQuery();
			
			if (resultSet.next())
				item.add("" + resultSet.getFloat(1));
			if (item.size() == 0)
				item.add("0.00");
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return item;
	}

	/**
	 * Get total purchases by client to date between an indicated date range
	 * @param client client to search by.
	 * @param startDate lower end of the date range.
	 * @param endDate higher end of the date range.
	 * @return total of sales between indicated range.
	 */
	public ArrayList<String> getSalesByAlbum(String album, String startDate,
			String endDate) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<String> item = new ArrayList<String>();
		try {
			openConnection();
			statement = connection.prepareStatement("select sum(dt.price_of_album) from " +
					"(select distinct sale_number, idt.price_of_album from " +
					"invoices_details idt join " +
					"invoices it using(sale_number) join " +
					"albums a using(album_number) " +
					"where album_number is not null AND " +
					"it.date_of_sale between date(?) AND date(?) AND " +
					"a.title = ?) dt");
			statement.setString(1, startDate);
			statement.setString(2, endDate);
			statement.setString(3, album);
			
			//execute statement obtaining the number of inserted rows
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				item.add("" + resultSet.getFloat(1));
				if (item.size() == 0)
					item.add("0.00");
				else if (item.get(0) == null)
					item.add(0, "0.00");
			}
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving records.", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return item;
	}

	public ArrayList<AdBean> getActiveAds() {
		ArrayList<AdBean> ads = new ArrayList<AdBean>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from ads where active=1");
			while (resultSet.next())
				ads.add(new AdBean(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getInt(4)));
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return ads;
	}

	public SurveyBean getActiveSurvey() {
		SurveyBean survey = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from survey where active=1");
			if (resultSet.next())
				survey = new SurveyBean(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getInt(7),
						resultSet.getInt(8), resultSet.getInt(9),
						resultSet.getInt(10), resultSet.getInt(11));
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return survey;
	}

	public RSSFeed getActiveRSS() {
		RSSFeed rss = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("Select * from rss where active=1");
			if (resultSet.next())
				rss = new RSSFeed(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getInt(3));
		} catch (SQLException sqlex) {
			logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return rss;
	}

	/**
	 * Returns all the reviews in the database given the inventory number
	 * 
	 * @return list of reviews
	 */
	public ArrayList<ReviewBean> getReviewsApprovedByInv(int id) {
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (id > 0) {
			try {
				openConnection();
				statement = connection.prepareStatement("Select * from reviews where approval_status > 0 AND inventory_number = ?");
				
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					reviews.add(new ReviewBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getInt(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getInt(8)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return reviews;
	}

	public boolean deactivateAd(int id) {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE ads SET active = 0 where id=" + id;
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"Unexpected error deactivating ad with id " + id + "!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	public boolean deactivateRSSFeed() {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE rss SET active = 0 where active=1";
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"Unexpected error deactivating rss feeds!", e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	public boolean deactivateSurveys() {
		PreparedStatement statement = null;
		boolean valid = true;
		try {
			String query = "UPDATE survey SET active = 0 where active=1";
			openConnection();
			statement = connection.prepareStatement(query);
			int records = statement.executeUpdate();
			if (records != 1)
				valid = false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Unexpected error deactivating surveys!",
					e.getMessage());
			valid = false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problem closing statement.", e);
			}
			if (connection != null)
				close();
		}
		return valid;
	}

	public ArrayList<ReviewBean> getTrackRating(int id) {
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (id > 0) {
			try {				
				openConnection();
				statement = connection.prepareStatement("Select avg(rating) from reviews rt, tracks tt"
						+ " where rt.approval_status = 0 AND tt.inventory_number = rt.inventory_number "
						+ "AND tt.inventory_number= ?");
				
				//inserting values into PreparedStatement
				statement.setInt(1, id);
				
				//execute statement obtaining the number of inserted rows
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					reviews.add(new ReviewBean(resultSet.getInt(1), resultSet
							.getInt(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getInt(5), resultSet
							.getString(6), resultSet.getInt(7), resultSet
							.getInt(8)));
				}
			} catch (SQLException sqlex) {
				logger.log(Level.WARNING, "Problem retrieving reviews!", sqlex);
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problem closing statement.", e);
				}
				if (connection != null)
					close();
			}
		}
		return reviews;
	}

}