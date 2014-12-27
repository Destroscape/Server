package game.sql;

import java.sql.*;
import game.entity.player.Player;

public class Highscores {
	public static Connection con;
	public static Statement stm;
        public static boolean connected;
	
	public static String Host = "jdbc:mysql://localhost/server";
	public static String User = "root";
	public static String Pass = "";
	
    public static void process() {
        try
        {
            Class.forName(Driver).newInstance();
	    Connection con = DriverManager.getConnection(Host, User, Pass);
	    stm = con.createStatement();
            connected = true;
        }
        catch(Exception e)
        {
            connected = false;
            e.printStackTrace();
        }
    }

    public static ResultSet query(String s)
        throws SQLException
    {
        if(s.toLowerCase().startsWith("select"))
        {
            ResultSet resultset = stm.executeQuery(s);
            return resultset;
        }
        try
        {
            stm.executeUpdate(s);
            return null;
        }
        catch(Exception e)
        {
            destroy();
        }
        process();
        return null;
    }

 public static void destroy() {
        try
        {
            if(stm != null)
            stm.close();
            if(con!= null)
            con.close();
            connected = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

 public static boolean save(Player c) {
        try
        {
			if(con == null)
				Highscores.process();
			query("DELETE FROM hs_users WHERE username='"+c.playerName+"'");
			
			String values = "";
			double total = 0;
			
			for(int i=0; i < 24; i++)
			{
				if(i < c.playerXP.length)
				{
					total += c.playerXP[i];
					values += (i == 0 ? "'" : ",'") + c.playerXP[i] + "'";
				}
			}
			
			query("INSERT INTO hs_users (username,xptitle,overall_xp,attack_xp,defence_xp,strength_xp,constitution_xp,ranged_xp,prayer_xp,magic_xp,cooking_xp,woodcutting_xp,fletching_xp,fishing_xp,firemaking_xp,crafting_xp,smithing_xp,mining_xp,herblore_xp,agility_xp,thieving_xp,slayer_xp,farming_xp,runecrafting_xp,hunter_xp,summoning_xp,dungeoneering_xp) VALUES('"+c.playerName+"','"+c.xpTitle+"','"+ (int)total +"',"+values+")");	
            
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
       return true;
    }
	public static String Driver = "com.mysql.jdbc.Driver";
}