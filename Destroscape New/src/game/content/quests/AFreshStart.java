package game.content.quests;

import game.entity.player.Player;

public class AFreshStart {


	public static void getDuringInterface(Player c) {
		switch (c.theStryke) {
		case 1:
			c.getQuest().clearQuestInterface();
			 c.getPA().sendFrame126("The Strykewyrm", 8144);
			c.getPA().sendFrame126(
					"Looks like I need to bring this note to Hans.", 8147);
			c.getPA().sendFrame126(
					"As I recall, Captain Shanks said something about him",
					8148);
			c.getPA().sendFrame126(
					"being in lumbridge. I guess I can go look there.", 8149);
			c.getPA().showInterface(8134);
			break;

		case 5:
			c.getQuest().clearQuestInterface();
			 c.getPA().sendFrame126("The Strykewyrm", 8144);
			c.getPA().sendFrame126(
					"@str@Looks like I need to bring this note to Hans.", 8147);
			c.getPA()
					.sendFrame126(
							"@str@As I recall, Captain Shanks said something about him",
							8148);
			c.getPA().sendFrame126(
					"@str@being in lumbridge. I guess I can go look there.",
					8149);
			c.getPA().sendFrame126("I have given Hans the note. ", 8151);
			c.getPA()
					.sendFrame126("Now I have to get Hans 3 wolf bones.", 8152);
			c.getPA().sendFrame126(
					"He said I could find them on White Wolf Mountain.", 8153);
			c.getPA().showInterface(8134);
			break;
		}
	}

	public static void startInt(Player c) {
		c.getQuest().clearQuestInterface();
		 c.getPA().sendFrame126("The Strykewyrm", 8144);
		c.getPA().sendFrame126(
				"To start this quest, you must speak to the @yel@Quester.",
				8147);
		c.getPA().sendFrame126(
				"You can find the him behind the general store.", 8148);
		c.getPA().sendFrame126("In edgeville", 8149);
		c.getPA().sendFrame126("", 8149);
		c.getPA().sendFrame126("Requirements:", 8151);
		c.getPA().sendFrame126("Being able to kill a high level creature", 8152);
		c.getPA().showInterface(8134);
	}

	public static void endInt(Player c) {
		c.getQuest().clearQuestInterface();
		// c.getPA().sendFrame126("A Fresh Start", 8144);
		c.getPA().sendFrame126("@red@   QUEST COMPLETE", 8145);
		c.getPA().sendFrame126("", 8146);
		c.getPA().sendFrame126("", 8147);
		c.getPA().sendFrame126("You recieve the following rewards:", 8148);
		c.getPA().sendFrame126("10,000 Coins for reperation!", 8149);
		c.getPA().sendFrame126("15K XP in the skill of your choosing!", 8150);
		c.getPA().sendFrame126("Access on Captain Shanks Boat!", 8151);
		c.getPA().sendFrame126("Good Luck and welcome to Destroscape!", 8153);
		c.getPA().showInterface(8134);
	}
}