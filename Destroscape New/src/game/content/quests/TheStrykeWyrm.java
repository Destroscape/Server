package game.content.quests;

import game.entity.player.Player;

public class TheStrykeWyrm {


	public static void getDuringInterface(Player c) {
		switch (c.theStryke) {
		case 1:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("The Strykewyrm", 8144);
			c.getPA().sendFrame126("The quester told me people were having problems", 8147);
			c.getPA().sendFrame126("with strykeworms that are invading various locations", 8148);
			c.getPA().sendFrame126("Such as the jungle, desert and snowy areas", 8149);
			c.getPA().sendFrame126("I should have a look at one!", 8150);
			c.getPA().showInterface(8134);
			break;

		case 2:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("The Strykewyrm", 8144);
			c.getPA().sendFrame126("@gre@The quester told me people were having problems", 8147);
			c.getPA().sendFrame126("@gre@with strykeworms that are invading various locations", 8148);
			c.getPA().sendFrame126("@gre@Such as the jungle, desert and snowy areas", 8149);
			c.getPA().sendFrame126("@gre@I should have a look at one!", 8150);
			c.getPA().sendFrame126("I have located the strykeworm", 8151);
			c.getPA().sendFrame126("I should go back and hear what quester has to say", 8152);
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
				"You can find him behind the general store.", 8148);
		c.getPA().sendFrame126("In edgeville", 8149);
		c.getPA().sendFrame126("", 8149);
		c.getPA().sendFrame126("No requirements.", 8151);
		c.getPA().showInterface(8134);
	}

	public static void endInt(Player c) {
		c.getQuest().clearQuestInterface();
		// c.getPA().sendFrame126("The Strykewyrm", 8144);
		c.getPA().sendFrame126("@red@   QUEST COMPLETE", 8145);
		c.getPA().sendFrame126("", 8146);
		c.getPA().sendFrame126("", 8147);
		c.getPA().sendFrame126("You recieved the following rewards:", 8148);
		c.getPA().sendFrame126("200K Coins", 8149);
		c.getPA().sendFrame126("And the ability to slay Strykewyrms.", 8151);
		c.getPA().showInterface(8134);
	}
}