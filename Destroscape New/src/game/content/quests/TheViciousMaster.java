package game.content.quests;

import game.entity.player.Player;

public class TheViciousMaster {


	public static void getDuringInterface(Player c) {
		switch (c.TVM) {
		case 1:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("The Vicious Master", 8144);
			c.getPA().sendFrame126("", 8147);
			c.getPA().sendFrame126("", 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("", 8150);
			c.getPA().showInterface(8134);
			break;	
		}
	}

	public static void startInt(Player c) {
		c.getQuest().clearQuestInterface();
		 c.getPA().sendFrame126("The Vicious Master", 8144);
		c.getPA().sendFrame126(
				"To start this quest, you must speak to the @or1@Quester.",
				8147);
		c.getPA().sendFrame126(
				"You can find him behind the general store.", 8148);
		c.getPA().sendFrame126("In edgeville", 8149);
		c.getPA().sendFrame126("", 8149);
		c.getPA().sendFrame126("Requirements:", 8151);
		if(c.playerLevel[11] >= 50) {
			c.getPA().sendFrame126("@gre@50 Firemaking", 8152);
                } else {
			c.getPA().sendFrame126("@red@50 Firemaking", 8152);
                }
		if(c.playerLevel[6] >= 50) {
			c.getPA().sendFrame126("@gre@50 Magic", 8153);
                } else {
			c.getPA().sendFrame126("@red@50 Magic", 8153);
                }
		if(c.playerLevel[17] >= 53) {
			c.getPA().sendFrame126("@gre@53 Thieving", 8154);
                } else {
			c.getPA().sendFrame126("@red@53 Thieving", 8154);
                }
		c.getPA().sendFrame126("And being able to kill High level bosses", 8155);
		c.getPA().showInterface(8134);
	}

	public static void endInt(Player c) {
		c.getQuest().clearQuestInterface();
		c.getPA().sendFrame126("The Vicious Master", 8144);
		c.getPA().sendFrame126("@red@   QUEST COMPLETE", 8145);
		c.getPA().sendFrame126("", 8146);
		c.getPA().sendFrame126("", 8147);
		c.getPA().sendFrame126("", 8148);
		c.getPA().sendFrame126("", 8149);
		c.getPA().sendFrame126("", 8150);
		c.getPA().sendFrame126("", 8151);
		c.getPA().showInterface(8134);
	}
}