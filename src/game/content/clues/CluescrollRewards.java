package game.content.clues;

import game.entity.player.Player;
import game.content.clues.CluescrollDefinition;
import game.content.clues.Cluescrolls;
import engine.util.Misc;
import game.content.achievement.*;

public class CluescrollRewards {
	
	public static int lowLevelReward[] = {
			1077, 1089, 1107, 1125, 1131, 1129, 1133, 1511,
			1168, 1165, 1179, 1195, 1217, 1283, 1297, 1313, 1327, 1341, 1361,
			1367, 1426, 2633, 2635, 2637, 7388, 7386, 7392, 7390, 7396, 7394,
			2631, 7364, 7362, 7368, 7366, 2583, 2585, 2587, 2589, 2591, 2593,
			2595, 2597, 7332, 7338, 7350, 7356
	};
	public static int mediemLevelReward[] = {
			2599, 2601, 2603, 2605, 2607, 2609, 2611, 2613, 7334, 7340, 
			7346, 7352, 7358, 7319, 7321, 7323, 7325, 7327, 7372, 7370, 
			7380, 7378, 2645, 2647, 2649, 2577, 2579, 1073, 1091, 1099,
			1111, 1135, 1124, 1145, 1161, 1169, 1183, 1199, 1211, 1245, 
			1271, 1287, 1301, 1317, 1332, 1357, 1371, 1430, 6916, 6918, 
			6920, 6922, 6924, 10400, 10402, 10416, 10418, 10420, 10422, 10436, 
			10438, 10446, 10448, 10450, 10452, 10454, 10456, 6889
	};
	public static int highLevelReward[] = {
			1079, 1093, 1113, 1127, 1147, 1163, 1185, 1201, 1275, 1303,
			1319, 1333, 1359, 1373, 2491, 2497, 2503, 861, 859, 2581,
 			2577, 2651, 1079, 1093, 1113, 1127, 1147, 1163, 1185, 1201,
			1275, 1303, 1319, 1333, 1359, 1373, 2491, 2497, 2503, 861, 
			859, 2581, 2577, 2651, 2615, 2617, 2619, 2621, 2623, 2625, 
			2627, 2629, 2639, 2641, 2643, 2651, 2653, 2655, 2657, 2659, 
			2661, 2663, 2665, 2667, 2669, 2671, 2673, 2675, 7342, 7348, 
			7454, 7460, 7374, 7376, 7382, 7384, 7398, 7399, 7400, 3481, 
			3483, 3485, 3486, 3488, 1079, 1093, 1113, 1127, 1148, 1164, 
			1185, 1201, 1213, 1247, 1275, 1289, 1303, 1319, 1333, 1347, 
			1359, 1374, 1432, 2615, 2617, 2619, 2621, 2623, 10330, 10338, 
			10348, 10332, 10340, 10346, 10334, 10342, 10350, 10336, 10344, 10352, 
			10368, 10376, 10384, 10370, 10378, 10386, 10372, 10380, 10388, 10374, 
			10382, 10390, 10470, 10472, 10474, 10440, 10442, 10444, 6914
	};
	
	public static int lowLevelStacks[] = {
		995, 380, 561, 886, 
	};
	public static int mediumLevelStacks[] = {
		995, 374, 561, 563, 890,
	};
	public static int highLevelStacks[] = {
		995, 386, 561, 563, 560, 892
	};

	public static void addClueReward(Player c, int clueLevel) {
		int chanceReward = Misc.random(2);
		if(clueLevel == 0) {
			switch (chanceReward) {
				case 0: 
				displayReward(c, lowLevelReward[Misc.random(45)], 1, lowLevelReward[Misc.random(45)], 1, lowLevelStacks[Misc.random(3)], 1 + Misc.random(150)); 
				break;
				case 1: 
				displayReward(c, lowLevelReward[Misc.random(45)], 1, lowLevelStacks[Misc.random(3)], 1 + Misc.random(150), -1, 1); 
				break;
				case 2: 
				displayReward(c, lowLevelReward[Misc.random(45)], 1, lowLevelReward[Misc.random(45)], 1, -1, 1); 
				break;
			}
		} else if(clueLevel == 1) {
			switch (chanceReward) {
				case 0: 
				displayReward(c, mediemLevelReward[Misc.random(35)], 1, mediemLevelReward[Misc.random(68)], 1, mediumLevelStacks[Misc.random(4)], 1 + Misc.random(200));
				break;
				case 1: 
				displayReward(c, mediemLevelReward[Misc.random(60)], 1, mediumLevelStacks[Misc.random(4)], 1 + Misc.random(200), -1, 1); 
				break;
				case 2: 
				displayReward(c, mediemLevelReward[Misc.random(65)], 1, mediemLevelReward[Misc.random(30)], 1, -1, 1);
				break;
			}
		} else if(clueLevel == 2) {
			switch (chanceReward) {
				case 0: 
				displayReward(c, highLevelReward[Misc.random(138)], 1, highLevelReward[Misc.random(50)], 1, highLevelStacks[Misc.random(5)], 1 + Misc.random(350)); 
				break;
				case 1: 
				displayReward(c, highLevelReward[Misc.random(130)], 1, highLevelStacks[Misc.random(5)], 1 + Misc.random(350), -1, 1); 
				break;
				case 2: 
				displayReward(c, highLevelReward[Misc.random(100)], 1, highLevelReward[Misc.random(110)], 1, -1, 1); 
				break;
			}
		}
	}
	
	/*public static void GiveReward(final Player c) {
		int ClueID;
		for(int i = 0; i < Cluescrolls.ItemIDS.size(); i++) {
			ClueID = Cluescrolls.ItemIDS.get(i);
		if (ClueID == 2677 || ClueID == 2678 || ClueID == 2679 || ClueID == 2680 
		 || ClueID == 2681 || ClueID == 2682 || ClueID == 2683 || ClueID == 2684 
		 || ClueID == 2685 || ClueID == 2686 || ClueID == 2687 || ClueID == 2688) {
			addClueReward(c, 0); //Easy clues
			c.sendMessage("Easy..");
		}
		if (ClueID == 2801 || ClueID == 2802 || ClueID == 2803 || ClueID == 2804 
		 || ClueID == 2805 || ClueID == 2806 || ClueID == 2807 || ClueID == 2808 
		 || ClueID == 2809 || ClueID == 2810 || ClueID == 2811 || ClueID == 2812) {
			addClueReward(c, 1); //Medium clues
			c.sendMessage("Medium..");
		}
		if (ClueID == 2722 || ClueID == 2723 || ClueID == 2724 || ClueID == 2725 
		 || ClueID == 2726 || ClueID == 2727 || ClueID == 2728 || ClueID == 2729
		 || ClueID == 2730 || ClueID == 2731 || ClueID == 2732 || ClueID == 2733) {
			addClueReward(c, 2); //Hard clues
			c.sendMessage("Hard..");
		}
	}
}*/

	public static void GiveReward(final Player c) {
		int ClueID;
		for(int i = 0; i < Cluescrolls.ItemIDS.size(); i++) {
			ClueID = Cluescrolls.ItemIDS.get(i);
		if(ClueID == 2677 && CheckCoords(2677, c)) {
			addClueReward(c, 0); //Easy clues
			AchievementManager.increase(c, Achievements.PIECE_OF_CAKE);
		}
		if(ClueID == 2678 && CheckCoords(2678, c)) {
			addClueReward(c, 0); //Easy clues
			AchievementManager.increase(c, Achievements.PIECE_OF_CAKE);
		}
		if(ClueID == 2679 && CheckCoords(2679, c)) {
			addClueReward(c, 0); //Easy clues
			AchievementManager.increase(c, Achievements.PIECE_OF_CAKE);
		}
		if(ClueID == 2680 && CheckCoords(2680, c)) {
			addClueReward(c, 0); //Easy clues
			AchievementManager.increase(c, Achievements.PIECE_OF_CAKE);
		}
		if(ClueID == 2681 && CheckCoords(2681, c)) {
			addClueReward(c, 0); //Easy clues
			AchievementManager.increase(c, Achievements.PIECE_OF_CAKE);
		}
		if(ClueID == 2682 && CheckCoords(2682, c)) {
			addClueReward(c, 0); //Easy clues
			AchievementManager.increase(c, Achievements.PIECE_OF_CAKE);
		}
		if(ClueID == 2683 && CheckCoords(2683, c)) {
			addClueReward(c, 0); //Easy clues
			AchievementManager.increase(c, Achievements.PIECE_OF_CAKE);
		}
		if(ClueID == 2684 && CheckCoords(2684, c)) {
			addClueReward(c, 0); //Easy clues
			AchievementManager.increase(c, Achievements.PIECE_OF_CAKE);
		}
	//End of easy clues
		if(ClueID == 2801 && CheckCoords(2801, c)) {
			addClueReward(c, 1); 
			AchievementManager.increase(c, Achievements.NO_BIGGIE);
		}
		if(ClueID == 2802 && CheckCoords(2802, c)) {
			addClueReward(c, 1); 
			AchievementManager.increase(c, Achievements.NO_BIGGIE);
		}
		if(ClueID == 2803 && CheckCoords(2803, c)) {
			addClueReward(c, 1); 
			AchievementManager.increase(c, Achievements.NO_BIGGIE);
		}
		if(ClueID == 2804 && CheckCoords(2804, c)) {
			addClueReward(c, 1); 
			AchievementManager.increase(c, Achievements.NO_BIGGIE);
		}
		if(ClueID == 2805 && CheckCoords(2805, c)) {
			addClueReward(c, 1); 
			AchievementManager.increase(c, Achievements.NO_BIGGIE);
		}
	//End of Medium clues
		if(ClueID == 2722 && CheckCoords(2722, c)) {
			addClueReward(c, 2);
			AchievementManager.increase(c, Achievements.SCROLLER);
		}
		if(ClueID == 2723 && CheckCoords(2723, c)) {
			addClueReward(c, 2);
			AchievementManager.increase(c, Achievements.SCROLLER);
		}
		if(ClueID == 2724 && CheckCoords(2724, c)) {
			addClueReward(c, 2);
			AchievementManager.increase(c, Achievements.SCROLLER);
		}
		if(ClueID == 2725 && CheckCoords(2725, c)) {
			addClueReward(c, 2);
			AchievementManager.increase(c, Achievements.SCROLLER);
		}
		if(ClueID == 2726 && CheckCoords(2726, c)) {
			addClueReward(c, 2);
			AchievementManager.increase(c, Achievements.SCROLLER);
		}
	}
}
	public static boolean CheckCoords(int ItemID, final Player c) {
		return (CluescrollDefinition.cs[ItemID].GetCoordX() == c.absX &&
				CluescrollDefinition.cs[ItemID].GetCoordY() == c.absY);
	}
	
	public static void ShowHint(int ItemID, final Player c) {
		c.getPA().showInterface(6965);
		
		for(int i = 0; i < 4; i++) {
			String hint = "";
			
			if(i == 0) {
				hint = CluescrollDefinition.cs[ItemID].GetDes1();
			} else if(i == 1) {
				hint = CluescrollDefinition.cs[ItemID].GetDes2();
			} else if(i == 2) {
				hint = CluescrollDefinition.cs[ItemID].GetDes3();
			} else if(i == 3) {
				hint = CluescrollDefinition.cs[ItemID].GetDes4();
			}
			
			if(!hint.equals("#nothing")) {
				c.getPA().sendFrame126(hint, 6968+i);
			} else {
				c.getPA().sendFrame126("", 6968+i);			
			}
			
		}
		
		for (int j = 6972; j < 6976; j++) {			
			c.getPA().sendFrame126("", j);			
		}
	}
	
	public static void ShowReward(Player c, int ItemID, int ItemAmount) {
		c.getPA().showInterface(6960);
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(6963);
		c.getOutStream().writeWord(1);
		c.getOutStream().writeByte(ItemAmount);
		c.getOutStream().writeWordBigEndianA(ItemID + 1);
		c.getOutStream().endFrameVarSizeWord();   
		c.flushOutStream();
	}
	
	public static void FinalCheck(Player c) {
		int ClueID;
		if(c.getItems().freeSlots() <= 3) {
			c.sendMessage("You need atleast 3 free slots in your inventory.");
			return;
		}
		for(int i = 0; i < Cluescrolls.ItemIDS.size(); i++) {
			ClueID = Cluescrolls.ItemIDS.get(i);
			if(c.getItems().playerHasItem(ClueID)) {
				if(CheckCoords(ClueID, c)) {
					GiveReward(c);
					c.getItems().deleteItem(ClueID, 1);
				}
			}
		}
	}
	
	public static boolean isClue(int Itemid) {
		for(int i = 0; i < Cluescrolls.ItemIDS.size(); i++) {
			int ClueID = Cluescrolls.ItemIDS.get(i);
			if(Itemid == ClueID) {
				return true;
			}
		}
		return false;
	}

	public static void displayReward(Player c, int item, int amount, int item2, int amount2, int item3, int amount3) {
		int[] items = {
			item, item2, item3
		};
		int[] amounts = {
			amount, amount2, amount3
		};
		c.outStream.createFrameVarSizeWord(53);
		c.outStream.writeWord(6963);
		c.outStream.writeWord(items.length);
		for(int i = 0; i < items.length; i++) {
			if(c.playerItemsN[i] > 254) {
				c.outStream.writeByte(255);
				c.outStream.writeDWord_v2(amounts[i]);
			} else {
				c.outStream.writeByte(amounts[i]);
			}
			if(items[i] > 0) {
				c.outStream.writeWordBigEndianA(items[i] + 1);
			} else {
				c.outStream.writeWordBigEndianA(0);
			}
		}
		c.outStream.endFrameVarSizeWord();
		c.flushOutStream();
		c.getItems().addItem(item, amount);
		c.getItems().addItem(item2, amount2);
		c.getItems().addItem(item3, amount3);
		c.getPA().showInterface(6960);
		}	
	}