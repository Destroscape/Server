package game.content.quests;

import game.entity.player.Player;
import game.entity.player.PlayerAssistant;

public class LunarDiplomacy {


	public static void getDuringInterface(Player c) {
		switch (c.lunarDiplomacy) {
		case 1:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Lunar Diplomacy", 8144);
			c.getPA().sendFrame126("The quester told me the lunar people needs help", 8147);
			c.getPA().sendFrame126("with some kind of illness spooking around", 8148);
			c.getPA().sendFrame126("on the lunar isle, i should go there and", 8149);
			c.getPA().sendFrame126("talk to Meteora", 8150);
			c.getPA().showInterface(8134);
			break;

		case 2:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Lunar Diplomacy", 8144);
			c.getPA().sendFrame126("@gre@The quester told me the lunar people needs help", 8147);
			c.getPA().sendFrame126("@gre@with some kind of illness spooking around", 8148);
			c.getPA().sendFrame126("@gre@on the lunar isle, i should go there and", 8149);
			c.getPA().sendFrame126("@gre@talk to Meteora", 8150);
			c.getPA().sendFrame126("I went to the lunar isle and spoke to Meteora", 8151);
			c.getPA().sendFrame126("she told the about the issue they are having", 8152);
			c.getPA().sendFrame126("and i accepted to help them!", 8153);
			c.getPA().sendFrame126("she want me to get her villains medicine", 8154);
			c.getPA().sendFrame126("from the chemist in @or1@Draynor Village", 8155);
			c.getPA().showInterface(8134);
			break;

		case 3:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Lunar Diplomacy", 8144);
			c.getPA().sendFrame126("@gre@The quester told me the lunar people needs help", 8147);
			c.getPA().sendFrame126("@gre@with some kind of illness spooking around", 8148);
			c.getPA().sendFrame126("@gre@on the lunar isle, i should go there and", 8149);
			c.getPA().sendFrame126("@gre@talk to Meteora", 8150);
			c.getPA().sendFrame126("@gre@I went to the lunar isle and spoke to Meteora", 8151);
			c.getPA().sendFrame126("@gre@she told the about the issue they are having", 8152);
			c.getPA().sendFrame126("@gre@and i accepted to help them!", 8153);
			c.getPA().sendFrame126("@gre@she want me to get her villains medicine", 8154);
			c.getPA().sendFrame126("@gre@from the Chemist in @or1@Draynor Village", 8155);
			c.getPA().sendFrame126("I've asked the Chemist to brew a medicine for me", 8156);
			c.getPA().sendFrame126("And he kindly accepted if i brought him the ingredients", 8157);
			c.getPA().sendFrame126("", 8158);
			c.getPA().sendFrame126("Ingredients:", 8159);

		if(c.getItems().playerHasItem(592, 2)) {
			c.getPA().sendFrame126("@gre@x2 of Ashes", 8160);
                } else {
			c.getPA().sendFrame126("@red@x2 of Ashes", 8160);
                }
		if(c.getItems().playerHasItem(3024, 1)) {
			c.getPA().sendFrame126("@gre@1x Super restore (4)", 8161);
                } else {
			c.getPA().sendFrame126("@red@1x Super restore (4)", 8162);
                }
		if(c.getItems().playerHasItem(12155, 1)) {
			c.getPA().sendFrame126("@gre@1x Pouch", 8163);
                } else {
			c.getPA().sendFrame126("@red@1x Pouch", 8163);
                }
			c.getPA().showInterface(8134);
			break;

		case 4:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Lunar Diplomacy", 8144);
			c.getPA().sendFrame126("@gre@The quester told me the lunar people needs help", 8147);
			c.getPA().sendFrame126("@gre@with some kind of illness spooking around", 8148);
			c.getPA().sendFrame126("@gre@on the lunar isle, i should go there and", 8149);
			c.getPA().sendFrame126("@gre@talk to Meteora", 8150);
			c.getPA().sendFrame126("@gre@I went to the lunar isle and spoke to Meteora", 8151);
			c.getPA().sendFrame126("@gre@she told the about the issue they are having", 8152);
			c.getPA().sendFrame126("@gre@and i accepted to help them!", 8153);
			c.getPA().sendFrame126("@gre@she want me to get her villains medicine", 8154);
			c.getPA().sendFrame126("@gre@from the Chemist in @or1@Draynor Village", 8155);
			c.getPA().sendFrame126("@gre@I've asked the Chemist to brew a medicine for me", 8156);
			c.getPA().sendFrame126("@gre@And he kindly accepted if i brought him the ingredients", 8157);
			c.getPA().sendFrame126("The Chemist created a medicine for me when i brought", 8158);
			c.getPA().sendFrame126("him the required ingredients, now i should", 8159);
			c.getPA().sendFrame126("take this potion back to Meteora!", 8160);
			c.getPA().showInterface(8134);
			break;
		case 5:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Lunar Diplomacy", 8144);
			c.getPA().sendFrame126("@gre@The quester told me the lunar people needs help", 8147);
			c.getPA().sendFrame126("@gre@with some kind of illness spooking around", 8148);
			c.getPA().sendFrame126("@gre@on the lunar isle, i should go there and", 8149);
			c.getPA().sendFrame126("@gre@talk to Meteora", 8150);
			c.getPA().sendFrame126("@gre@I went to the lunar isle and spoke to Meteora", 8151);
			c.getPA().sendFrame126("@gre@she told the about the issue they are having", 8152);
			c.getPA().sendFrame126("@gre@and i accepted to help them!", 8153);
			c.getPA().sendFrame126("@gre@she want me to get her villains medicine", 8154);
			c.getPA().sendFrame126("@gre@from the Chemist in @or1@Draynor Village", 8155);
			c.getPA().sendFrame126("@gre@I've asked the Chemist to brew a medicine for me", 8156);
			c.getPA().sendFrame126("@gre@And he kindly accepted if i brought him the ingredients", 8157);
			c.getPA().sendFrame126("@gre@The Chemist created a medicine for me when i brought", 8158);
			c.getPA().sendFrame126("@gre@him the required ingredients, now i should", 8159);
			c.getPA().sendFrame126("@gre@take this potion back to Meteora!", 8160);
			c.getPA().sendFrame126("I gave the potion to Meteora, i should speak to here again!", 8161);
			c.getPA().showInterface(8134);
			break;
		case 6:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Lunar Diplomacy", 8144);
			c.getPA().sendFrame126("@gre@The quester told me the lunar people needs help", 8147);
			c.getPA().sendFrame126("@gre@with some kind of illness spooking around", 8148);
			c.getPA().sendFrame126("@gre@on the lunar isle, i should go there and", 8149);
			c.getPA().sendFrame126("@gre@talk to Meteora", 8150);
			c.getPA().sendFrame126("@gre@I went to the lunar isle and spoke to Meteora", 8151);
			c.getPA().sendFrame126("@gre@she told the about the issue they are having", 8152);
			c.getPA().sendFrame126("@gre@and i accepted to help them!", 8153);
			c.getPA().sendFrame126("@gre@she want me to get her villains medicine", 8154);
			c.getPA().sendFrame126("@gre@from the Chemist in @or1@Draynor Village", 8155);
			c.getPA().sendFrame126("@gre@I've asked the Chemist to brew a medicine for me", 8156);
			c.getPA().sendFrame126("@gre@And he kindly accepted if i brought him the ingredients", 8157);
			c.getPA().sendFrame126("@gre@The Chemist created a medicine for me when i brought", 8158);
			c.getPA().sendFrame126("@gre@him the required ingredients, now i should", 8159);
			c.getPA().sendFrame126("@gre@take this potion back to Meteora!", 8160);
			c.getPA().sendFrame126("@gre@ gave the potion to Meteora, i should speak to here again!", 8161);
			c.getPA().sendFrame126("Meteora used the medicine for all her villains and they", 8162);
			c.getPA().sendFrame126("Are now cured, but she needed help with the cause of this.", 8163);
			c.getPA().sendFrame126("I should talk to the adventurer in the <col=255>Chaos Dungeon</col>", 8164);
			c.getPA().showInterface(8134);
			break;
		case 7:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Lunar Diplomacy", 8144);
			c.getPA().sendFrame126("I should kill Bork", 8147);
			break;
		case 8:
			c.getQuest().clearQuestInterface();
			c.getPA().sendFrame126("Lunar Diplomacy", 8144);
			c.getPA().sendFrame126("@gre@The quester told me the lunar people needs help", 8147);
			c.getPA().sendFrame126("@gre@with some kind of illness spooking around", 8148);
			c.getPA().sendFrame126("@gre@on the lunar isle, i should go there and", 8149);
			c.getPA().sendFrame126("@gre@talk to Meteora", 8150);
			c.getPA().sendFrame126("@gre@I went to the lunar isle and spoke to Meteora", 8151);
			c.getPA().sendFrame126("@gre@she told the about the issue they are having", 8152);
			c.getPA().sendFrame126("@gre@and i accepted to help them!", 8153);
			c.getPA().sendFrame126("@gre@she want me to get her villains medicine", 8154);
			c.getPA().sendFrame126("@gre@from the Chemist in @or1@Draynor Village", 8155);
			c.getPA().sendFrame126("@gre@I've asked the Chemist to brew a medicine for me", 8156);
			c.getPA().sendFrame126("@gre@And he kindly accepted if i brought him the ingredients", 8157);
			c.getPA().sendFrame126("@gre@The Chemist created a medicine for me when i brought", 8158);
			c.getPA().sendFrame126("@gre@him the required ingredients, now i should", 8159);
			c.getPA().sendFrame126("@gre@take this potion back to Meteora!", 8160);
			c.getPA().sendFrame126("@gre@ gave the potion to Meteora, i should speak to here again!", 8161);
			c.getPA().sendFrame126("@gre@Meteora used the medicine for all her villains and they", 8162);
			c.getPA().sendFrame126("@gre@Are now cured, but she needed help with the cause of this.", 8163);
			c.getPA().sendFrame126("@gre@I should talk to the adventurer in the <col=255>Chaos Dungeon</col>", 8164);
			c.getPA().sendFrame126("I have killed bork, i should tell Meteora", 8165);
			c.getPA().showInterface(8134);
			break;
		}
	}

	public static void startInt(Player c) {
		c.getQuest().clearQuestInterface();
		 c.getPA().sendFrame126("Lunar Diplomacy", 8144);
		c.getPA().sendFrame126(
				"To start this quest, you must speak to the @yel@Quester.",
				8147);
		c.getPA().sendFrame126(
				"You can find him behind the general store in edgeville.", 8148);
		c.getPA().sendFrame126("", 8149);
		c.getPA().sendFrame126("", 8150);
		c.getPA().sendFrame126("Requirements:", 8151);

		if(c.playerLevel[12] >= 61) {
			c.getPA().sendFrame126("@gre@61 Crafting", 8152);
                } else {
			c.getPA().sendFrame126("@red@61 Crafting", 8152);
		}

		if(c.playerLevel[1] >= 40) {
			c.getPA().sendFrame126("@gre@41 Defence", 8153);
                } else {
			c.getPA().sendFrame126("@red@41 Defence", 8153);
                }

		if(c.playerLevel[11] >= 49) {
			c.getPA().sendFrame126("@gre@49 Firemaking", 8154);
                } else {
			c.getPA().sendFrame126("@red@49 Firemaking", 8154);
                }

		if(c.playerLevel[14] >= 60) {
			c.getPA().sendFrame126("@gre@60 Mining", 8155);
                } else {
			c.getPA().sendFrame126("@red@60 Mining", 8155);
                }

		if(c.playerLevel[15] >= 5) {
			c.getPA().sendFrame126("@gre@5 Herblore", 8156);
                } else {
			c.getPA().sendFrame126("@red@5 Herblore", 8156);
                }

		if(c.playerLevel[20] >= 14) {
			c.getPA().sendFrame126("@gre@14 Runecrafting", 8157);
                } else {
			c.getPA().sendFrame126("@red@14 Runecrafting", 8157);
                }

		if(c.playerLevel[6] >= 65) {
			c.getPA().sendFrame126("@gre@65 Magic", 8158);
                } else {
			c.getPA().sendFrame126("@red@65 Magic", 8158);
                }

		if(c.playerLevel[8] >= 55) {
			c.getPA().sendFrame126("@gre@55 Woodcutting", 8159);
                } else {
			c.getPA().sendFrame126("@red@55 Woodcutting", 8159);
                }

		c.getPA().sendFrame126("", 8160);
		c.getPA().showInterface(8134);
	}

	public static void endInt(Player c) {
		c.getQuest().clearQuestInterface();
		// c.getPA().sendFrame126("Lunar Diplomacy", 8144);
		c.getPA().sendFrame126("@red@   QUEST COMPLETE", 8145);
		c.getPA().sendFrame126("", 8146);
		c.getPA().sendFrame126("", 8147);
		c.getPA().sendFrame126("You recieved the following rewards:", 8148);
		c.getPA().sendFrame126("2M Coins", 8149);
		c.getPA().sendFrame126("300K Magic Experience", 8150);
		c.getPA().sendFrame126("And the ability to use the Lunar spellbook.", 8151);
		c.getPA().showInterface(8134);
	}
}