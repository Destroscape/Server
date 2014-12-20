package game.sql;

/**
 * @author Shaqattack118
 */
public enum Databases {

	/**
	 * Enums: Name(<host>, <database>, <user>, <pass>)
	 */
	BaseWebsite("localhost", "server", "root", "");
	
	private String DB_HOST = null, DB_USER = null, DB_PASS = null, DB_DB = null;
	
	/**
	 * Set all variables needed for Databases
	 * @param host
	 * @param database
	 * @param user
	 * @param password
	 */
	Databases(String host, String database, String user, String password) {
		setSqlDb(database);
		setSqlHost(host);
		setSqlUser(user);
		setSqlPass(password);
	}
	public String getSqlHost() {
		return DB_HOST;
	}
	public void setSqlHost(String dB_HOST) {
		DB_HOST = dB_HOST;
	}
	public String getSqlUser() {
		return DB_USER;
	}
	public void setSqlUser(String dB_USER) {
		DB_USER = dB_USER;
	}
	public String getSqlPass() {
		return DB_PASS;
	}
	public void setSqlPass(String dB_PASS) {
		DB_PASS = dB_PASS;
	}
	public String getSqlDb() {
		return DB_DB;
	}
	public void setSqlDb(String dB_DB) {
		DB_DB = dB_DB;
	}
}
