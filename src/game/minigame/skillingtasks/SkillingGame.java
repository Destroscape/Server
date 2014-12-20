package game.minigame.skillingtasks;

import game.Config;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import engine.util.Misc;
import engine.event.*;
import game.entity.player.Player;

/*
*
* Skilling tasks written by Matt. 16/12-13
*
*/

public class SkillingGame {

	private Player c;
		
	public SkillingGame(Player c) {
		this.c = c;
	}
	
	//FISHING
	public int[] fish = {317,//Shrimp
				327,//Sardine
				355,//Trout
				341,//Cod
				349,//Pike
				359,//Tuna
				377,//Lobster
				383,//Sharks
				389,//Manta Ray
				15272//Rocktail
				};
	public int[] fishReq = {1, 5, 20, 23, 25, 30, 40, 79, 85, 99};

	//MINING
	public int[] ores = {436, 438, 440, 447, 449, 451};
	public int[] oreReq = {1, 1, 15, 30, 50, 70, 85};

	//WOODCUTTING
	public int[] logs = {1511, 1519, 1517, 1515, 1513};
	public int[] logReq = {1, 30, 45, 60, 75};

	public void finishTask() {
		/*if(c.skillTask < 1) {
			c.sendMessage("You do not have a task!");
			c.getPA().removeAllWindows();
			return;
		}*/
		if (c.setTask == 1) {
				c.tid = c.skillTask + 1;
			if(c.getItems().playerHasItem(c.tid, c.ia) || c.getItems().playerHasItem(c.skillTask, c.ia)) {
				c.getItems().deleteItem(c.tid, c.ia);
				c.getItems().deleteItem(c.skillTask, c.ia);
				c.getDH().sendDialogues(5108, 580);
				c.startAnimation(858);
				giveReward();
		} else {
			c.sendMessage("You do not have the required materials!");
			sayTask();
			}
		}
		if (c.setTask == 2) {
			c.tid1 = c.skillTask1 + 1;
		if(c.getItems().playerHasItem(c.tid1, c.ia1) || c.getItems().playerHasItem(c.skillTask1, c.ia1)) {
			c.getItems().deleteItem(c.tid1, c.ia1);
			c.getItems().deleteItem(c.skillTask1, c.ia1);
			c.getDH().sendDialogues(5110, 580);
				c.startAnimation(858);
				giveReward();
		} else {
			c.sendMessage("You do not have the required materials!");
			sayTask();
			}
		}
		if (c.setTask == 3) {
			c.tid2 = c.skillTask2 + 1;
		if(c.getItems().playerHasItem(c.tid2, c.ia2) || c.getItems().playerHasItem(c.skillTask2, c.ia2)) {
			c.getItems().deleteItem(c.tid2, c.ia2);
			c.getItems().deleteItem(c.skillTask2, c.ia2);
			c.getDH().sendDialogues(5111, 580);
				c.startAnimation(858);
				giveReward();
		} else {
			c.sendMessage("You do not have the required materials!");
			sayTask();
			}
		}
		if (c.setTask == 4) {
			c.tid = c.skillTask + 1;
			c.tid1 = c.skillTask1 + 1;
		if(c.getItems().playerHasItem(c.tid, c.ia) || c.getItems().playerHasItem(c.skillTask, c.ia) || 
		   c.getItems().playerHasItem(c.tid1, c.ia1) || c.getItems().playerHasItem(c.skillTask1, c.ia1)) {
			c.getItems().deleteItem(c.tid, c.ia);
			c.getItems().deleteItem(c.skillTask, c.ia);
			c.getItems().deleteItem(c.tid1, c.ia1);
			c.getItems().deleteItem(c.skillTask1, c.ia1);
			c.getDH().sendDialogues(5112, 580);
				c.startAnimation(858);
				giveReward();
		} else {
			c.sendMessage("You do not have the required materials!");
			sayTask();
			}
		}
		if (c.setTask == 5) {
			c.tid1 = c.skillTask1 + 1;
			c.tid2 = c.skillTask2 + 1;
		if(c.getItems().playerHasItem(c.tid2, c.ia2) || c.getItems().playerHasItem(c.skillTask2, c.ia2) || 
		   c.getItems().playerHasItem(c.tid1, c.ia1) || c.getItems().playerHasItem(c.skillTask1, c.ia1)) {
			c.getItems().deleteItem(c.tid2, c.ia2);
			c.getItems().deleteItem(c.skillTask2, c.ia2);
			c.getItems().deleteItem(c.tid1, c.ia1);
			c.getItems().deleteItem(c.skillTask1, c.ia1);
			c.getDH().sendDialogues(5113, 580);
				c.startAnimation(858);
				giveReward();
		} else {
			c.sendMessage("You do not have the required materials!");
			sayTask();
			}

		}
	}

	public void sayTask() {
		if (c.setTask == 1) {
				c.getDH().sendDialogues(5103, 580);
		}
		if (c.setTask == 2) {
				c.getDH().sendDialogues(5104, 580);
		}
		if (c.setTask == 3) {
				c.getDH().sendDialogues(5105, 580);
		}
		if (c.setTask == 4) {
				c.getDH().sendDialogues(5106, 580);
		}
		if (c.setTask == 5) {
				c.getDH().sendDialogues(5107, 580);
		}
	}

	public void chooseTask() {
		int randomChoose = Misc.random(4);

		if (randomChoose == 0) {
			giveWCTask();
			c.setTask = 1;
		}
		if (randomChoose == 1) {
			giveMiningTask();
			c.setTask = 2;
		}
		if (randomChoose == 2) {
			giveFishTask();
			c.setTask = 3;
		}
		if (randomChoose == 3) {
			giveWCMineTask();
			c.setTask = 4;
		}
		if (randomChoose == 4) {
			giveMineFishTask();
			c.setTask = 5;
		}
	}

	public void giveWCTask() {
		int given;
		int random;
		
		if(c.skillTask == 0) {
			random = (int)(Math.random() * (logs.length - 1));
				given = logs[random];
				c.skillTask = given;
				c.setTask = 1;
				c.skillAmount = Misc.random(50) + 45;
				c.getDH().sendDialogues(5103, 580);
		} else {
			c.sendMessage("You already have a task!");
			return;
		}
		c.ia = c.skillAmount;
		c.tid = c.skillTask;
	}
	
	public void giveMiningTask() {
		int given;
		int random;
		
		if(c.skillTask1 == 0) {
			random = (int)(Math.random() * (ores.length - 1));
				given = ores[random];
				c.skillTask1 = given;
				c.skillAmount1 = Misc.random(40) + 54;
				c.getDH().sendDialogues(5104, 580);
		} else {
			c.sendMessage("You already have a task!");
			return;
		}
		c.ia1 = c.skillAmount1;
		c.tid1 = c.skillTask1;
	}
	
	public void giveFishTask() {
		int given;
		int random;
		if(c.skillTask2 == 0) {
			random = (int)(Math.random() * (fish.length - 1));
				given = fish[random];
				c.skillTask2 = given;
				c.skillAmount2 = Misc.random(50) + 55;
				c.getDH().sendDialogues(5105, 580);
		} else {
			c.sendMessage("You already have a task!");
			return;
		}
		c.ia2 = c.skillAmount2;
		c.tid2 = c.skillTask2;
	}

	public void giveWCMineTask() {
		int given;
		int given1;
		int random;
		int random1;

		if(c.skillTask == 0 && c.skillTask1 == 0) {
			random = (int)(Math.random() * (logs.length - 1));
				given = logs[random];
				c.skillAmount = Misc.random(40) + 55;
				c.skillTask = given;

			random1 = (int)(Math.random() * (ores.length - 1));
				given1 = ores[random1];
				c.skillAmount1 = Misc.random(55) + 55;
				c.skillTask1 = given1;
				c.getDH().sendDialogues(5106, 580);
		} else {
			c.sendMessage("You already have a task!");
			return;
		}
		c.ia = c.skillAmount;
		c.ia1 = c.skillAmount1;
		c.tid = c.skillTask;
		c.tid1 = c.skillTask1;
	}

	public void giveMineFishTask() {
		int given;
		int given1;
		int random;
		int random1;

		if(c.skillTask1 == 0 && c.skillTask2 == 0) {
			random = (int)(Math.random() * (fish.length - 1));
				given = fish[random];
				c.skillAmount2 = Misc.random(55) + 55;
				c.skillTask2 = given;

			random1 = (int)(Math.random() * (ores.length - 1));
				given1 = ores[random1];
				c.skillAmount1 = Misc.random(30) + 55;
				c.skillTask1 = given1;
				c.getDH().sendDialogues(5107, 580);
		} else {
			c.sendMessage("You already have a task!");
			return;
		}

		c.tid1 = c.skillTask1;
		c.ia1 = c.skillAmount1;
		c.ia2 = c.skillAmount2;
		c.tid2 = c.skillTask2;
	}


	
	public void giveReward() {
		int reward = 0;
		int rp = 0;
	if (c.setTask == 1) {
	if (c.x2Points == true) {
		reward = Misc.random(20000) + 50000;
		rp = 6;
	} else {
		reward = Misc.random(20000) + 50000;
		rp = 3;
	}
}
	if (c.setTask == 2) {
	if (c.x2Points == true) {
		reward = Misc.random(20000) + 50000;
		rp = 6;
	} else {
		reward = Misc.random(20000) + 50000;
		rp = 3;
	}
}
	if (c.setTask == 3) {
	if (c.x2Points == true) {
		reward = Misc.random(20000) + 50000;
		rp = 6;
	} else {
		reward = Misc.random(20000) + 50000;
		rp = 3;
	}
}
	if (c.setTask == 4) {
	if (c.x2Points == true) {
		reward = Misc.random(100000) + 50000;
		rp = 16;
	} else {
		reward = Misc.random(20000) + 50000;
		rp = 8;
	}
}
	if (c.setTask == 5) {
	if (c.x2Points == true) {
		reward = Misc.random(100000) + 50000;
		rp = 16;
	} else {
		reward = Misc.random(20000) + 50000;
		rp = 8;
	}
}
		
		c.getItems().addItem(995, reward);
		c.skillPoints += rp;
		c.skillAmount = 0;
		c.tid = 0;
		c.ia = 0;
		c.skillTask = 0;
		c.skillAmount1 = 0;
		c.tid1 = 0;
		c.ia1 = 0;
		c.skillTask1 = 0;
		c.skillAmount2 = 0;
		c.tid2 = 0;
		c.ia2 = 0;
		c.skillTask2 = 0;
		c.setTask = 0;
	}

	public int getskillTask() {
		return c.skillTask;
	}

	public int getskillAmount() {
		return c.skillAmount;
	}
	
	
}