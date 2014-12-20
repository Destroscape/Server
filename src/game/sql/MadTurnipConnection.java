package game.sql;

import java.sql.*;
import game.entity.player.Player;

public class MadTurnipConnection extends Thread {

	public static Connection con = null;
	public static Statement stm;

	public static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost/server", "root", "");
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
	}
	
	public MadTurnipConnection(){
		
	}
	
	public void run() {
		while(true) {		
			try {
				if(con == null)
					createConnection(); 
				else
					ping();
				Thread.sleep(10000);//10 seconds
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void ping(){
		try {
			String query = "SELECT * FROM donation WHERE username = 'null'";
			query(query);
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
	}
	
	public static void addDonateItems(final Player c,final String name){
		if(con == null){
			if(stm != null){
				try {
					stm = con.createStatement();
				} catch(Exception e){
					con = null;
					stm = null;
					//put a sendmessage here telling them to relog in 30 seconds
					return;
				}
			} else {
				//put a sendmessage here telling them to relog in 30 seconds
				return;
			}
		}
		new Thread(){
			@Override
			public void run()
			{
				try {
					String name2 = name.replaceAll(" ","_");
					String query = "SELECT * FROM donation WHERE username = '"+name2+"'";
					ResultSet rs = query(query);
					boolean b = false;
					while(rs.next()){
						int prod = Integer.parseInt(rs.getString("productid"));
						int price = Integer.parseInt(rs.getString("price"));
						if(prod == 1 && price == 10){//Donator
							c.playerRights = 4;	
							c.membership = true;
							c.unMembership = true;
							c.donatorPoints += 10;
							//c.donatorPoints += 20; //x2
							c.sendMessage("Relog safely for this to take place.");
							c.totalDonation += 10;
							b = true;
						}
						if(prod == 2 && price == 25){//Super Donator
							c.playerRights = 5;
							c.membership = true;
							c.unMembership = true;
							c.sendMessage("Relog safely for this to take place.");
							c.totalDonation += 25;
							c.donatorPoints += 25;
							//c.donatorPoints += 50; //x2
							b = true;
						}
						if(prod == 3 && price == 50) {// Extreme donator
							c.playerRights = 6;	
							c.membership = true;
							c.unMembership = true;
							c.donatorPoints += 50;
							//c.donatorPoints += 100; //x2
							c.sendMessage("Relog safely for this to take place.");
							c.totalDonation += 50;
							b = true;
						}
						if(prod == 4 && price == 100){ //Phat Set
							c.getItems().addItemToBank(1038, 1);//Red
							c.getItems().addItemToBank(1040, 1);//Yellow
							c.getItems().addItemToBank(1042, 1);//Blue
							c.getItems().addItemToBank(1044, 1);//Green
							c.getItems().addItemToBank(1046, 1);//Purple
							c.getItems().addItemToBank(1048, 1);//White
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 100;
							c.donatorPoints += 100;
							b = true;
						}
						if(prod == 5 && price == 25){ //Red Phat
							c.getItems().addItemToBank(1038, 1);//Red
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 25;
							c.donatorPoints += 25;
							b = true;
						}
						if(prod == 6 && price == 24){ //Blue Phat
							c.getItems().addItemToBank(1042, 1);//Blue
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 24;
							c.donatorPoints += 24;
							b = true;
						}
						if(prod == 7 && price == 23){ //Yellow Phat
							c.getItems().addItemToBank(1040, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 23;
							c.donatorPoints += 23;
							b = true;
						}
						if(prod == 8 && price == 22){ //Green Phat
							c.getItems().addItemToBank(1044, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 22;
							c.donatorPoints += 22;
							b = true;
						}
						if(prod == 9 && price == 21){ //Purple Phat
							c.getItems().addItemToBank(1046, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 21;
							c.donatorPoints += 21;
							b = true;
						}
						if(prod == 10 && price == 20){ //White Phat
							c.getItems().addItemToBank(1048, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 20;
							c.donatorPoints += 20;
							b = true;
						}
						if(prod == 11 && price == 38){ //H'ween set
							c.getItems().addItemToBank(1053, 1); //Green
							c.getItems().addItemToBank(1055, 1); //Blue
							c.getItems().addItemToBank(1057, 1); //Red
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 38;
							c.donatorPoints += 38;
							b = true;
						}
						if(prod == 12 && price == 16){ //H'ween
							c.getItems().addItemToBank(1057, 1); //Red
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 16;
							c.donatorPoints += 16;
							b = true;
						}
						if(prod == 13 && price == 15){ //H'ween
							c.getItems().addItemToBank(1055, 1); //Blue
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 15;
							c.donatorPoints += 15;
							b = true;
						}
						if(prod == 14 && price == 12){ //H'ween
							c.getItems().addItemToBank(1053, 1); //Green
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 12;
							c.donatorPoints += 12;
							b = true;
						}
						if(prod == 15 && price == 20){ //Santa hat
							c.getItems().addItemToBank(1050, 1);//Santa
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 20;
							c.donatorPoints += 20;
							b = true;
						}
						if(prod == 16 && price == 30){ //Torva set
							c.getItems().addItemToBank(20135, 1);
							c.getItems().addItemToBank(20139, 1);
							c.getItems().addItemToBank(20143, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 22;
							c.donatorPoints += 22;
							b = true;
						}
						if(prod == 17 && price == 30){ //Virtus set
							c.getItems().addItemToBank(20159, 1);
							c.getItems().addItemToBank(20163, 1);
							c.getItems().addItemToBank(20167, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 22;
							c.donatorPoints += 22;
							b = true;
						}
						if(prod == 18 && price == 30){ //Pernix set
							c.getItems().addItemToBank(20147, 1);
							c.getItems().addItemToBank(20151, 1);
							c.getItems().addItemToBank(20155, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 22;
							c.donatorPoints += 22;
							b = true;
						}
						if(prod == 19 && price == 15){ //Bandos set
							c.getItems().addItemToBank(11724, 1);
							c.getItems().addItemToBank(11726, 1);
							c.getItems().addItemToBank(11728, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 15;
							c.donatorPoints += 15;
							b = true;
						}
						if(prod == 20 && price == 32){ //Godsword set
							c.getItems().addItemToBank(11694, 1);
							c.getItems().addItemToBank(11696, 1);
							c.getItems().addItemToBank(11698, 1);
							c.getItems().addItemToBank(11700, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 32;
							c.donatorPoints += 32;
							b = true;
						}
						if(prod == 21 && price == 8){ //Bandos GS
							c.getItems().addItemToBank(11696, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 8;
							c.donatorPoints += 8;
							b = true;
						}
						if(prod == 22 && price == 8){ //Zamorak GS
							c.getItems().addItemToBank(11700, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 8;
							c.donatorPoints += 8;
							b = true;
						}
						if(prod == 23 && price == 8){ //Saradoming GS
							c.getItems().addItemToBank(11698, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 8;
							c.donatorPoints += 8;
							b = true;
						}
						if(prod == 24 && price == 14){ //Armadyl GS
							c.getItems().addItemToBank(11694, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 14;
							c.donatorPoints += 14;
							b = true;
						}
						if(prod == 25 && price == 21){ //X2 XP Ring
							c.getItems().addItemToBank(19771, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 21;
							c.donatorPoints += 21;
							//c.donatorPoints += 40; //x2
							b = true;
						}
						if(prod == 26 && price == 20){ //Salve Amulet
							c.getItems().addItemToBank(4081, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 20;
							c.donatorPoints += 20;
							//c.donatorPoints += 40; //x2
							b = true;
						}
						if(prod == 27 && price == 22){//X2 Points
							c.x2Points = true;
							c.totalDonation += 22;
							c.donatorPoints += 22;
							//c.donatorPoints += 44; //x2
							b = true;
						}
						if(prod == 28 && price == 10){//TYL Box
							c.getItems().addItemToBank(19040, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 10;
							c.donatorPoints += 10;
							//c.donatorPoints += 20; //x2
							b = true;
						}
						if(prod == 29 && price == 15){ //Armadyl Set
							c.getItems().addItemToBank(11718, 1);
							c.getItems().addItemToBank(11720, 1);
							c.getItems().addItemToBank(11722, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 15;
							c.donatorPoints += 15;
							b = true;
						}
						if(prod == 30 && price == 20){ //Vesta Set
							c.getItems().addItemToBank(13887, 1);
							c.getItems().addItemToBank(13893, 1);
							//c.getItems().addItemToBank(13, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 20;
							c.donatorPoints += 20;
							b = true;
						}
						if(prod == 31 && price == 20){ //Statius Set
							c.getItems().addItemToBank(13884, 1);
							c.getItems().addItemToBank(13890, 1);
							c.getItems().addItemToBank(13896, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 20;
							c.donatorPoints += 20;
							b = true;
						}
						if(prod == 32 && price == 20){ //Morrigans Set
							c.getItems().addItemToBank(13870, 1);
							c.getItems().addItemToBank(13873, 1);
							c.getItems().addItemToBank(13876, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 20;
							c.donatorPoints += 20;
							b = true;
						}
						if(prod == 33 && price == 20){ //Zuriel Set
							c.getItems().addItemToBank(13858, 1);
							c.getItems().addItemToBank(13861, 1);
							c.getItems().addItemToBank(13864, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 20;
							c.donatorPoints += 20;
							b = true;
						}
						if(prod == 34 && price == 10){ //VLS
							c.getItems().addItemToBank(13899, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 10;
							c.donatorPoints += 10;
							b = true;
						}
						if(prod == 35 && price == 10){ //SWH
							c.getItems().addItemToBank(13902, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 10;
							c.donatorPoints += 10;
							b = true;
						}
						if(prod == 36 && price == 15){ //200K Dtokens
							c.dTokens += 200000;
							c.totalDonation += 15;
							c.donatorPoints += 15;
							b = true;
						}
						if(prod == 37 && price == 55){ //1M Dtokens
							c.dTokens += 1000000;
							c.totalDonation += 55;
							c.donatorPoints += 55;
							b = true;
						}
						if(prod == 38 && price == 6){ //15 Prestige tokens
							c.prestigePoints += 15;
							c.totalDonation += 6;
							c.donatorPoints += 6;
							b = true;
						}
						if(prod == 39 && price == 45){ //150 Prestige tokens
							c.prestigePoints += 150;
							c.totalDonation += 45;
							c.donatorPoints += 45;
							b = true;
						}
						if(prod == 40 && price == 15){ //Ancient Bones
							c.getItems().addItemToBank(15410, 500);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 15;
							c.donatorPoints += 15;
							b = true;
						}
						if(prod == 41 && price == 15){ //Magic Logs
							c.getItems().addItemToBank(1513, 2000);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 15;
							c.donatorPoints += 15;
							b = true;
						}
						if(prod == 42 && price == 3){ //Dragon Axe
							c.getItems().addItemToBank(6739, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 3;
							c.donatorPoints += 3;
							b = true;
						}
						if(prod == 43 && price == 15){ //Inferno Adze
							c.getItems().addItemToBank(13661, 1);
							c.sendMessage("*@red@ Your item(s) has been added to your bank.");
							c.totalDonation += 15;
							c.donatorPoints += 15;
							b = true;
						}
					}
					if(b){
						query("DELETE FROM `donation` WHERE `username` = '"+name2+"';");
					}
				} catch (Exception e) {
					e.printStackTrace();
					con = null;
					stm = null;
				}
			}
		}.start();
	}
	
	public static ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = stm.executeQuery(s);
				return rs;
			} else {
				stm.executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
			stm = null;
		}
		return null;
	}
}