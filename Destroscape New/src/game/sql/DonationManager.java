package game.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import engine.util.Misc;
import game.entity.player.Player;

public class DonationManager {

	public static Connection con = null;
	public static Statement stmt;
	public static boolean rewarded = false;
	private static long lastUsed = System.currentTimeMillis();

	public static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/server",
					"root", "");
			stmt = con.createStatement();
			Misc.println("Succesfully connected to DonationManager database.");
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

	public static void addReward(Player c, int reward) {

		c.getPA().showInterface(23000);
		c.getPA().sendFrame126("Thanks for donating to the server!", 23003);

		switch (reward) {
		case 1:
			c.getPA().sendFrame126("Donator Rank", 23004);
			c.getPA().sendFrame126("Access to the Donator Zone", 23005);
			c.getPA().sendFrame126("A Donator Icon before your name", 23006);
			c.getPA().sendFrame126(
					"25 Donator Points to spend in the Donator Shop", 23007);
			c.donatorPoints += 25;
			c.setRights(5);
			break;

		case 2:
			c.getPA().sendFrame126("Extreme Donator Rank", 23004);
			c.getPA().sendFrame126("Access to the Extreme Donator Zone", 23005);
			c.getPA().sendFrame126("A Extreme Donator Icon before your name",
					23006);
			c.getPA().sendFrame126(
					"50 Donator Points to spend in the Donator Shop", 23007);
			c.donatorPoints += 50;
			c.setRights(6);
			break;

		}
		rewarded = true;
		c.sendMessage("Thanks for helping the server by donating!");
	}

	public static boolean checkStatus(Player c) {
		try {
			Statement s = getConnection().createStatement();
			ResultSet results = s
					.executeQuery("SELECT * FROM `status` WHERE `username` = '"
							+ c.playerName + "' AND `given` = '0' LIMIT 10;");
			while (results.next()) {
				if (results.getInt("item") >= 0) {
					addReward(c, results.getInt("item"));
					Statement st = getConnection().createStatement();
					st.executeUpdate("UPDATE `status` SET `given` = '10' WHERE `id`='"
							+ results.getInt("id") + "';");
					st.close();
				}
			}
			if (!rewarded) {
				c.sendMessage("It looks like you haven't donated. Please try again later.");
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}