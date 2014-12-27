package server.world;

import server.*;
import server.util.*;
import server.model.items.*;
import server.model.players.*;

public class PublicEvent {
	public static String randomChar[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", };
	public static boolean firstEventInProgress = false;
	public static String firstString = "";
	public static int rewardId;
	public static int rewardAmount;
	public static int lastEventTimer = 0;
	public static boolean forceFirst = false;
	
	public static void forceFirst() {
		if(firstEventInProgress)
			return;
		forceFirst = true;
	}
	
	public static void processEntry(Client player, String command) {
		if(!firstEventInProgress)
			return;
		if(command.equals(firstString)) {
			firstEventInProgress = false;
			lastEventTimer = 0;
			player.getItems().addItem(rewardId, rewardAmount);
			if(rewardAmount == 1) {
				serverMessage("<shad=15007744><img=2>[React Trivia]" + player.playerName.substring(0,1).toUpperCase() + player.playerName.substring(1) + " has won a "+ItemAssistant.getItemName2(rewardId)+"!</shad><img=2>");
			} else {
				String extra = "s";
				if(ItemAssistant.getItemName2(rewardId).endsWith("s"))
					extra = "";
				serverMessage("<shad=15007744><img=2>[React Trivia]" + player.playerName.substring(0,1).toUpperCase() + player.playerName.substring(1) + " has won "+rewardAmount+" "+ItemAssistant.getItemName2(rewardId)+""+extra+"!</shad><img=2>");
			}
			rewardId = 0;
			rewardAmount = 0;
		}
	}
	
	public static String newFirstString() {
		String returns = "";
		while(returns.length() < 10)
			returns += randomChar[Misc.random(randomChar.length - 1)];
		return returns;
	}
	
	public static String constructFirstYell() {
		if(rewardAmount == 1)
			return "<shad=15007744><img=2>[React Trivia] The first person to type ::"+firstString+" will receive a "+ItemAssistant.getItemName2(rewardId)+"</shad>";
		if(ItemAssistant.getItemName2(rewardId).endsWith("s"))
			return "<shad=15007744><img=2>[React Trivia] The first person to type ::"+firstString+" will receive "+rewardAmount+" "+ItemAssistant.getItemName2(rewardId)+"</shad><img=2>";
		return "<shad=15007744><img=2>[React Trivia] The first person to type ::"+firstString+" will receive "+rewardAmount+" "+ItemAssistant.getItemName2(rewardId)+"s</shad><img=2>";
	}
	
	public static void generateReward() {
		int rewardLevel = Misc.random(50);
		if(rewardLevel < 40) {
			rewardLevel = 1;
		} else if(rewardLevel < 49) {
			rewardLevel = 2;
		} else if(rewardLevel == 50) {
			rewardLevel = 3;
		} else {
			rewardLevel = 1;
		}
		switch(rewardLevel) {
			case 1:
				switch(Misc.random(3)) {
					case 0:
						rewardId = 995;
						rewardAmount = (Misc.random(3) + 1) * 50000;
					break;
					case 1:
						rewardId = 533;
						rewardAmount = (Misc.random(14) + 1) * 10;
					break;
					case 2:
						rewardId = 1624;
						rewardAmount = (Misc.random(9) + 1) * 10;
					break;
					case 3:
						rewardId = 1740;
						rewardAmount = (Misc.random(19) + 1) * 10;
					break;
					
				}
			break;
			case 2:
				switch(Misc.random(3)) {
					case 0:
						rewardId = 995;
						rewardAmount = (Misc.random(15) + 5) * 50000;
					break;
					case 1:
						rewardId = 537;
						rewardAmount = (Misc.random(14) + 6) * 10;
					break;
					case 2:
						rewardId = 1622;
						rewardAmount = (Misc.random(10) + 10) * 10;
					break;
					case 3:
						rewardId = 386;
						rewardAmount = (Misc.random(80) + 20) * 10;
					break;
					
				}
			break;
			case 3:
				switch(Misc.random(5)) {
					case 0:
						rewardId = 995;
						rewardAmount = (Misc.random(50) + 10) * 100000;
					break;
					case 1:
						rewardId = 537;
						rewardAmount = (Misc.random(100) + 30) * 10;
					break;
					case 2:
						rewardId = 4151;
						rewardAmount = 1;
					break;
					case 3:
						rewardId = 1053;
						rewardAmount = 1;
					break;
					case 4:
						rewardId = 1055;
						rewardAmount = 1;
					break;
					case 5:
						rewardId = 1057;
						rewardAmount = 1;
					break;
				}
			break;
		}
	}
	
	public static void executeFirstEvent() {
		forceFirst = false;
		firstString = newFirstString();
		generateReward();
		firstEventInProgress = true;
		lastEventTimer = 600;
		serverMessage(constructFirstYell());
	}
	
	public static void process() {
		if((Misc.random(3600) == 0 || forceFirst) && !firstEventInProgress)
			executeFirstEvent();
		if(lastEventTimer > 0)
			lastEventTimer --;
		if(lastEventTimer == 0)
			firstEventInProgress = false;
	}
	
	public static void serverMessage(String s) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if(Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.sendMessage(s);
			}
		}
	}
	
}