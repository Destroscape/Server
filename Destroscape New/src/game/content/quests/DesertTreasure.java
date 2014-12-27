package game.content.quests;

import game.entity.player.Player;

public class DesertTreasure {


	public static void getDuringInterface(Player c) {
		switch (c.DT) {
		case 1:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Desert Treasure", 8144);
			c.getPA().sendFrame126("The quester told me an archaeologist was", 8147);
			c.getPA().sendFrame126("seeking for help about lost diamonds", 8148);
			c.getPA().sendFrame126("i should go find and talk to him about that", 8149);
			c.getPA().sendFrame126("Quester said he was located ontop of @or1@wolfs mountain", 8150);
			c.getPA().showInterface(8134);
			break;
			
		case 2:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Desert Treasure", 8144);
			c.getPA().sendFrame126("I've killed 1 of the 4 bosses for him!", 8147);
			c.getPA().sendFrame126("And i got the diamond from it.", 8148);
			c.getPA().sendFrame126("I should speak to him again.", 8149);
			c.getPA().showInterface(8134);
			break;	

		case 3:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Desert Treasure", 8144);
			c.getPA().sendFrame126("I've killed 2 of the 4 bosses for him!", 8147);
			c.getPA().sendFrame126("And i got the diamond from it.", 8148);
			c.getPA().sendFrame126("I should speak to him again.", 8149);
			c.getPA().showInterface(8134);
			break;		

		case 4:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Desert Treasure", 8144);
			c.getPA().sendFrame126("I've killed 3 of the 4 bosses for him!", 8147);
			c.getPA().sendFrame126("And i got the diamond from it.", 8148);
			c.getPA().sendFrame126("I should speak to him again.", 8149);
			c.getPA().showInterface(8134);
			break;		

		case 5:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Desert Treasure", 8144);
			c.getPA().sendFrame126("I've killed 4 of the 4 bosses for him!", 8147);
			c.getPA().sendFrame126("And i got the diamond from it.", 8148);
			c.getPA().sendFrame126("I should speak to him again.", 8149);
			c.getPA().showInterface(8134);
			break;	
		}
	}

	public static void startInt(Player c) {
		c.getQuest().clearQuestInterface();
		 c.getPA().sendFrame126("Desert Treasure", 8144);
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
		c.getPA().sendFrame126("Desert Treasure", 8144);
		c.getPA().sendFrame126("@red@   QUEST COMPLETE", 8145);
		c.getPA().sendFrame126("", 8146);
		c.getPA().sendFrame126("", 8147);
		c.getPA().sendFrame126("You recieved the following rewards:", 8148);
		c.getPA().sendFrame126("500K Coins", 8149);
		c.getPA().sendFrame126("300K Magic Experience", 8150);
		c.getPA().sendFrame126("And the ability to use the Ancient spellbook.", 8151);
		c.getPA().showInterface(8134);
	}
}