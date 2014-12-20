package game.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author Shaqattack118
 * ConnectionManager - Handle all connections(Destroying, Creating etc)
 */
public class ConnectionManager {

	private Connection con;
	private Statement stmt;
	private String DB_HOST = null, DB_USER = null, DB_PASS = null, DB_DB = null;
	
	public ConnectionManager(String h, String u, String p, String db) {
		DB_HOST = h;
		DB_USER = u;
		DB_PASS = p;
		DB_DB = db;
	}
	
	/**
	 * Creates the connection and the statement
	 */
	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conx = DriverManager.getConnection("jdbc:mysql://"+DB_HOST+"/"+DB_DB, DB_USER, DB_PASS);
			setConnection(conx);
			Statement stmtx = con.createStatement();
			setStatement(stmtx);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Destroys the connection and the statement
	 */
	public void destroyConnection() {
		try {
			setConnection(null);
			setStatement(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Grabs the current statement made with the database saan.
	 * @return stmt - Returns the statement saan.
	 */
	public Statement getStatement() {
		return stmt;
	}
	
	/**
	 * Set the statement saan.
	 * @param stmt - Statement: SQL Statement
	 */
	public void setStatement(Statement stmt) {
		this.stmt = stmt;
	}
	/**
	 * Get the connection made form database saan.
	 * @return con - Connection: Returns the connection
	 */
	public Connection getConnection() {
		return con;
	}
	/**
	 * Set the connection to something saan
	 * @param con - Connection: Connection saan.
	 */
	public void setConnection(Connection con) {
		this.con = con;
	}
	
}
