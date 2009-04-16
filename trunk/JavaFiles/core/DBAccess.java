/**********************************************************
* File: core.DBAccess.java
* Author: Alex Bassett
* Date Created: 4/13/2009
*
* Description: Handles connecting and disconnecting from the
* 				HealthPack mySQL database. This class requires 
* 				that the application server have the Connector/J
* 				driver installed on the Application server.
*
* Edited  : 4/16/2009 by Alex Bassett
* Changes : added comments
*
**********************************************************/
package core;

import java.sql.*;

// constructor
public class DBAccess {

	/* is true when there is an active connection
	 * to the database open. There should never be
	 * more than one active connection at a time.
	 */
	private boolean connected;
	
	/* Connection class contains information about
	 * the driver, login and other info about the
	 * database, and methods for communicating with
	 * the database.
	 */
	public Connection connection;
	
	public DBAccess() {
		connected = false;
		connection = null;
	}

	/**
	 * Sets up a connection to the database and sets the
	 * connecton variable for calling classes to use.
	 * 
	 * @return true when the database successfully connects,
	 * false otherwise.
	 * @author Alex Bassett
	 */
	public boolean connect() {
		/* check if a connection doesn't already exist
		 * if one does, disconnect before starting a new
		 * connection and print an error message
		 */
		if (connected) {
			System.err.println("Call to connect() when database is already connected");
			disconnect();
		}
		
		/* attempt to connect to the database */
        try {
            String userName = "cmsc345";
            String password = "alpha6823";
            String url = "jdbc:mysql://windurst.lib.umbc.edu:3306/cmsc345";
            connection = DriverManager.getConnection (url, userName, password);
            connected = true;
        } catch (Exception e) {
            System.err.println ("Cannot connect to database server");
            System.err.println (e.toString());
            connected = false;
        }
		return connected;
	}

	/**
	 * Terminates the connection to the database.
	 * 
	 * @return true when the database successfully disconnects,
	 * false otherwise.
	 * @author Alex Bassett
	 */
	public boolean disconnect() {
		/* ensure there is a connection to close */
		if (!connected) {
			System.err.println("Call to disconnect() when no connection exists");
			return connected;
		}
		try { connection.close(); }
        catch (Exception e) { /* ignore close errors */ }
		return connected;
	}
}