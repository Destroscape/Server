package game.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import engine.util.Misc;
import game.entity.player.Player;

public class HighscoresHandler extends Thread {
	
	private String sqlhost = null, sqldb = null, sqlpass = null, sqluser = null;
	private Player p;
	private ConnectionManager cm;
	public static boolean inProcess = false;
	
	public HighscoresHandler(Player p) {
		inProcess = true;
		this.p = p;
		for (Databases d : Databases.values()) {
			sqlhost = d.getSqlHost();
			sqluser = d.getSqlUser();
			sqlpass = d.getSqlPass();
			sqldb = d.getSqlDb();
		}
	}
	
	public void run() {
		cm = new ConnectionManager(sqlhost, sqluser, sqlpass, sqldb);
		cm.createConnection();
		try {
			ResultSet rs = query("select * from hs where username='"+p.playerName+"'");
			while(rs.next()) {
				String query = generateUpdateQuery(p);
				try {	
					query(query);
					Misc.println("Saved highscores for "+p.playerName);
				} catch(Exception e) {
					e.printStackTrace();
				}
				inProcess = false;
				cm.destroyConnection();
				return;
			}
			query("insert into hs(username) values ('"+p.playerName+"')");
			Misc.println("Inserted "+p.playerName+" into the database...");
			cm.destroyConnection();
			HighscoresHandler hs = new HighscoresHandler(p);
			hs.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generate the update Query
	 * @param p2 - Player variable
	 * @return
	 */
	private String generateUpdateQuery(Player p2) {
		String query = "";
		query += "update hs set ";				
		for(int i = 0; i < 25; i++) {
			String lvl = "lvl_"+(i+1);
			String xp = "xp_"+(i+1);
			int level = p2.getLevelForXP(p2.playerXP[i]);
			int exp = p2.playerXP[i];
			if(level > 99 && i != 24) 
				level = 99;
			if(i == 24)
				query += lvl+"='"+level+"', "+xp+"='"+exp+"'";
			else
				query += lvl+"='"+level+"', "+xp+"='"+exp+"', ";
		}
		long total_exp = getTotalExp();
		long total_level = getTotalLevel();
		query += ", total_exp='"+total_exp+"', total_lvl ='"+total_level+"', rights='"+p.playerRights+"'";
		query += " where username='"+p.playerName+"'";
		return query;
	}
	/**
	 * Gets total Level and returns;
	 * @return
	 */
	private long getTotalLevel() {
		long total_lvl = 0L;
		for(int i = 0; i < 24; i++) {
			total_lvl += p.playerLevel[i];
		}
		return total_lvl;
	}

	/**
	 * Gets total exp and returns
	 * @return
	 */
	private long getTotalExp() {
		long total_xp = 0L;
		for(int i = 0; i < 24; i++) {
			total_xp += p.playerXP[i];
		}
		return total_xp;
	}

	private ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = cm.getStatement().executeQuery(s);
				return rs;
			} else {
				cm.getStatement().executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			cm.destroyConnection();
			cm.createConnection();
		}
		return null;
	}
	
}
