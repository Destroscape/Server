package game.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import engine.util.Misc;
import game.entity.player.Player;

public class VoteManager {

	public static Connection con = null;
	public static Statement stmt;
	public static boolean rewarded = false;
	private static long lastUsed = System.currentTimeMillis();

	public static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost",
					"root", "");
			stmt = con.createStatement();
			Misc.println("Succesfully connected to VoteManager database.");
		} catch (Exception e) {
			Misc.println("Connection Problem");
		}
	}

	public static ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = stmt.executeQuery(s);
				return rs;
			} else {
				stmt.executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			destroyConnection();
			createConnection();
		}
		return null;
	}

	public static void destroyConnection() {
		try {
			stmt.close();
			con.close();
		} catch (Exception e) {
		}
	}

	public static Connection getConnection() throws Exception {
		if (con == null) {
			throw new Exception("connection is null");
		}
		if (System.currentTimeMillis() - lastUsed > 300000) {
			try {
				lastUsed = System.currentTimeMillis();
				con.close();
				createConnection();
			} catch (Exception e) {
				throw new Exception("error refreshing database connection");
			}
		}
		return con;
	}

	public static void checkStatus(Player c, String auth) {
		try {
			PreparedStatement ps = getConnection()
					.prepareStatement(
							"SELECT * FROM votes WHERE username = ? AND used = '1' LIMIT 1");
			ps.setString(1, c.playerName);
			ResultSet results = ps.executeQuery();
			if (results.next()) {
				c.sendMessage("You have already voted once today.");
			} else {
				ps.close();
				ps = getConnection()
						.prepareStatement(
								"SELECT * FROM votes WHERE authcode = ? AND used = '0' LIMIT 1");
				ps.setString(1, auth);
				results = ps.executeQuery();
				if (results.next()) {
					ps.close();
					ps = getConnection().prepareStatement(
							"UPDATE votes SET used = '1' WHERE authcode = ?");
					ps.setString(1, auth);
					ps.executeUpdate();
					c.getItems().addItem(995, 500000);
					c.getItems().addItem(386, 150);
					c.votePoints += 2;
					c.sendMessage("Thank you for voting.");
				} else {
					c.sendMessage("The auth code is not valid!");
				}
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}