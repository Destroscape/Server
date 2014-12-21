package com.rsinterface;

import com.TextDrawingArea;

public class CustomInterfaces extends RSInterface {

	public static void unpackInterfaces(TextDrawingArea[] textDrawingAreas) {
		taskInterface(textDrawingAreas);
		newLamp(textDrawingAreas);
		newTrade(textDrawingAreas);
		skilllevel(textDrawingAreas);
		capeCustomiser(textDrawingAreas);
		//quickPrayers(textDrawingAreas);
		//quickCurses(textDrawingAreas);
		congrats(textDrawingAreas);
		monsterTele(textDrawingAreas);
		capeColor(textDrawingAreas);
		//runecraftingTele(textDrawingAreas);
		itemsKeptOnDeath(textDrawingAreas);
		itemsOnDeathDATA(textDrawingAreas);
		itemsOnDeath(textDrawingAreas);
		prayerTab(textDrawingAreas);
		optionTab(textDrawingAreas);
		sidebar0(textDrawingAreas);
		PVPInterface(textDrawingAreas);
		PVPInterface2(textDrawingAreas);
		PVPInterface3(textDrawingAreas);
		friendsTab(textDrawingAreas);
		ignoreTab(textDrawingAreas);
		Bank(textDrawingAreas);
		pouches(textDrawingAreas);
		formParty(textDrawingAreas);
		dungParty(textDrawingAreas);
		scrolls(textDrawingAreas);
		bobInterface(textDrawingAreas);
		pestPanel(textDrawingAreas);
		pestPanel2(textDrawingAreas);
		configureLunar(textDrawingAreas);
		constructLunar();
		pkingTab(textDrawingAreas);
		questTab(textDrawingAreas);
		SummonTab(textDrawingAreas);
		clanChatTab(textDrawingAreas);
		clanChatSetup(textDrawingAreas);
		emoteTab();
		cursesTab(textDrawingAreas);
		SkillTeleportInterface(textDrawingAreas);
		Shop(textDrawingAreas);
		shopOverview(textDrawingAreas);
		ancientMagicTab(textDrawingAreas);
		magicTab(textDrawingAreas);
		skillTab602(textDrawingAreas);
		GodWars(textDrawingAreas);
		EquipmentTab(textDrawingAreas);
		equipmentScreen(textDrawingAreas);
		weaponClass(textDrawingAreas);
		inWeapon(textDrawingAreas);
		bountyHunter(textDrawingAreas);
		moneyBank(textDrawingAreas);
		skillTabInfo(textDrawingAreas);
		LodestoneNetwork(textDrawingAreas);
		levelPvm(textDrawingAreas);
		novicePvm(textDrawingAreas);
		interPvm(textDrawingAreas);
		elitePvm(textDrawingAreas);
		bossPvm(textDrawingAreas);
		quickPrayers(textDrawingAreas);
		//quickCurses(textDrawingAreas);
		SettingsTab(textDrawingAreas);
		//priceChecker(textDrawingAreas);
		loyaltyTitles(textDrawingAreas);
		achievement(textDrawingAreas);
	}
	


	public static int x, y = 0;

	public static void achievement(TextDrawingArea[] tda) {
		final RSInterface tab = addTabInterface(23139);
		final RSInterface scroll = addTabInterface(23140);
		final String directory = "Interfaces/Achievement/achievement";
		addText(23130,
				"Here you can view your\\nachievements. You see\\nthe ones that are to be\\ncompleted and also the\\nones you've completed.\\nThe ones that are in\\nred are those you\\nhaven't completed yet\\n\\nYou earn achievement\\npoints by completing\\nachievements.",
				0xAF6A1B, false, true, 0, 0);
		addText(23131, "You currently have\\n147 achievement points.",
				0xAF6A1B, false, true, 0, 0);
		addText(23110, "Achievements", 0xAF6A1B, false, true, 0, 2);
		addButton(23132, 1, directory, "", 0, 3, 16, 15);
		tab.totalChildren(6);
		tab.child(0, 23138, 1, 1);
		tab.child(1, 23140, 160, 50);
		tab.child(2, 23130, 20, 80);
		tab.child(3, 23131, 20, 210);
		tab.child(4, 23132, 492, 4);
		tab.child(5, 23110, 28, 45);
		addButton(23138, 0, directory, "", 0, 0, 500, 500);
		final int ACHIEVEMENTS = 100;
		scroll.width = 300;
		scroll.height = 200;
		scroll.scrollMax = 2500;
		scroll.totalChildren(ACHIEVEMENTS);
		for (int i = 23141; i < 23141 + ACHIEVEMENTS; i++) {
			addClickableText(i, "", "Show", tda, 0, 0xff0000, false, true, 150);
			//addText(i, "" + i + "", 0xAF6A1A, false, true, 52, 1);
			scroll.child(i - 23141, i, x, y);
			x += 150;
			if (x >= 300) {
				y += 50;
				x = 0;
			}
		}
		final RSInterface tab1 = addTabInterface(23133);
		addButton(23134, 4, directory, "", 0, 0, 500, 500);
		addText(23135, "T A S K C O M P L E T E", 0xAF6A1A, false, true, 52, 2);
		addText(23136, "You completed the achievement\\nMuncher!", 0xAF6A1B,
				true, true, 0, 1);
		tab1.totalChildren(3);
		tab1.child(0, 23134, 158, 0);
		tab1.child(1, 23135, 193, 5);
		tab1.child(2, 23136, 254, 22);
	}
	
public static void taskInterface(TextDrawingArea[] TDA) { /** Interface by Cody' **/
	RSInterface Interface = addTabInterface(23800);
	setChildren(43, Interface);
	addSprite(23801, 1, "Interfaces/Task/TaskSystemInterface");
	addHover(23802, 3, 0, 23803, 1, "Interfaces/Task/taskButton", 18, 18, "Close");
	addHovered(23803, 2, "Interfaces/Task/taskButton", 16, 16, 23804);
	addHoverButton(23805, "Interfaces/Task/Task", 3, 20, 20, "Filter-sets", 0, 23806, 1);
	addHoveredButton(23806, "Interfaces/Task/Task", 5, 20, 20, 23807);
	addHoverButton(23808, "Interfaces/Task/Task", 4, 20, 20, "Filter-done", 0, 23809, 1);
	addHoveredButton(23809, "Interfaces/Task/Task", 6, 20, 20, 23810);
	addHoverButton(23811, "Interfaces/Task/Task", 23, 68, 54, "Summary for Skills", 0, 23812, 1);
	addHoveredButton(23812, "Interfaces/Task/Task", 22, 68, 54, 23813);
	addHoverButton(23814, "Interfaces/Task/Task", 32, 68, 54, "Summary for Quarter Centurion", 0, 23815, 1);
	addHoveredButton(23815, "Interfaces/Task/Task", 33, 68, 54, 23816);
	addHoverButton(23817, "Interfaces/Task/Task", 36, 68, 54, "Summary for Hi Ho, Silver", 0, 23818, 1);
	addHoveredButton(23818, "Interfaces/Task/Task", 37, 68, 54, 23819);
	addHoverButton(23820, "Interfaces/Task/Task", 38, 68, 54, "Summary for Grab The Cash", 0, 23821, 1);
	addHoveredButton(23821, "Interfaces/Task/Task", 39, 68, 54, 23822);
	addHoverButton(23823, "Interfaces/Task/Task", 40, 68, 54, "Summary for Intervention", 0, 23824, 1);
	addHoveredButton(23824, "Interfaces/Task/Task", 41, 68, 54, 23825);
	addHoverButton(23826, "Interfaces/Task/Task", 7, 14, 14, "Turn-on popups", 0, 23827, 1);
	addHoveredButton(23827, "Interfaces/Task/Task", 8, 14, 14, 23828);
	addHoverButton(23829, "Interfaces/Task/Task", 1, 68, 54, "Summary for Undefeatable", 0, 23830, 1);
	addHoveredButton(23830, "Interfaces/Task/Task", 2, 68, 54, 23831);
	addHoverButton(23832, "Interfaces/Task/Task", 24, 68, 54, "Summary for You Doity Rat", 0, 23833, 1);
	addHoveredButton(23833, "Interfaces/Task/Task", 25, 68, 54, 23834);
	addHoverButton(23835, "Interfaces/Task/Task", 26, 68, 54, "Summary for Can't Touch This", 0, 23836, 1);
	addHoveredButton(23836, "Interfaces/Task/Task", 27, 68, 54, 23837);
	addHoverButton(23838, "Interfaces/Task/Task", 28, 68, 54, "Summary for Ease of Access", 0, 23839, 1);
	addHoveredButton(23839, "Interfaces/Task/Task", 29, 68, 54, 23840);
	addHoverButton(23841, "Interfaces/Task/Task", 42, 68, 54, "Summary for Bovine Intervention", 0, 23842, 1);
	addHoveredButton(23842, "Interfaces/Task/Task", 43, 68, 54, 23843);
	addHoverButton(23844, "Interfaces/Task/Task", 44, 68, 54, "Summary for Armed and Dangerous", 0, 23845, 1);
	addHoveredButton(23845, "Interfaces/Task/Task", 45, 68, 54, 23846);
	addHoverButton(23847, "Interfaces/Task/Task", 46, 68, 54, "Summary for You Can Bank On Us", 0, 23848, 1);
	addHoveredButton(23848, "Interfaces/Task/Task", 47, 68, 54, 23849);
	addHoverButton(23850, "Interfaces/Task/Task", 48, 68, 54, "Summary for Air Craft", 0, 23851, 1);
	addHoveredButton(23851, "Interfaces/Task/Task", 49, 68, 54, 23852);
	addHoverButton(23853, "Interfaces/Task/Task", 50, 68, 54, "Summary for I Wonder If It'll Sprout", 0, 23854, 1);
	addHoveredButton(23854, "Interfaces/Task/Task", 51, 68, 54, 23855);
	addHoverButton(23856, "Interfaces/Task/Task", 30, 68, 54, "Summary for Always Be Prepared", 0, 23857, 1);
	addHoveredButton(23857, "Interfaces/Task/Task", 31, 68, 54, 23858);
	addText(23859, "Click on a Task to view its details", TDA, 0, 0xFFFFFF, false, true);
	addHoverButton(23860, "Interfaces/Task/Progress", 1, 0, 0, "", 0, 23861, 1);
	addHoveredButton(23861, "Interfaces/Task/Progress", 1, 0, 0, 23862);
	addText(23863, "Progress:  0 / 15", TDA, 0, 0xFFFFFF, false, true);
	setBounds(23801, 5, 4, 0, Interface); // Background
	setBounds(23802, 482, 7, 1, Interface); // Close
	setBounds(23803, 482, 7, 2, Interface);
	setBounds(23805, 458, 32, 3, Interface); // Task option 1
	setBounds(23806, 458, 32, 4, Interface);
	setBounds(23808, 481, 32, 5, Interface); // Task option 2
	setBounds(23809, 481, 32, 6, Interface);
	// Row one
	setBounds(23811, 20, 60, 7, Interface);
	setBounds(23812, 20, 60, 8, Interface);
	setBounds(23814, 120, 60, 9, Interface);
	setBounds(23815, 120, 60, 10, Interface);
	setBounds(23817, 220, 60, 11, Interface);
	setBounds(23818, 220, 60, 12, Interface);
	setBounds(23820, 320, 60, 13, Interface);
	setBounds(23821, 320, 60, 14, Interface);
	setBounds(23823, 420, 60, 15, Interface);
	setBounds(23824, 420, 60, 16, Interface);
	setBounds(23826, 9, 298, 17, Interface);
	setBounds(23827, 9, 298, 18, Interface);
	// Row 2
	setBounds(23829, 20, 145, 19, Interface);
	setBounds(23830, 20, 145, 20, Interface);
	setBounds(23832, 120, 145, 21, Interface);
	setBounds(23833, 120, 145, 22, Interface);
	setBounds(23835, 220, 145, 23, Interface);
	setBounds(23836, 220, 145, 24, Interface);
	setBounds(23838, 320, 145, 25, Interface);
	setBounds(23839, 320, 145, 26, Interface);
	setBounds(23841, 420, 145, 27, Interface);
	setBounds(23842, 420, 145, 28, Interface);
	// Row 3
	setBounds(23844, 120, 230, 29, Interface);
	setBounds(23845, 120, 230, 30, Interface);
	setBounds(23847, 220, 230, 31, Interface);
	setBounds(23848, 220, 230, 32, Interface);
	setBounds(23850, 320, 230, 33, Interface);
	setBounds(23851, 320, 230, 34, Interface);
	setBounds(23853, 420, 230, 35, Interface);
	setBounds(23854, 420, 230, 36, Interface);
	setBounds(23856, 20, 230, 37, Interface);
	setBounds(23857, 20, 230, 38, Interface);
	/** TEXT **/
	setBounds(23859, 340, 300, 39, Interface);
	/** INFO **/
	setBounds(23860, 10, 33, 40, Interface);
	setBounds(23861, 10, 33, 41, Interface);
	setBounds(23863, 27, 37, 42, Interface);
}	

public static void telePanel(TextDrawingArea[] TDA) {
	RSInterface Interface = addTabInterface(24600);
	setChildren(21, Interface);
	addSprite(24601, 3, "Interfaces/Teleport/Class/Background");
	addHoverButton(24602, "Interfaces/Teleport/Class/SPRITE", 1, 16, 16, "Close", 250, 24603, 3);
    	addHoveredButton(24603, "Interfaces/Teleport/Class/SPRITE", 2, 16, 16, 24604);
	addHoverButton(24605, "Interfaces/Teleport/Class/HOME", 1, 48, 52, "Teleport", 0, 24606, 1);
	addHoveredButton(24606, "Interfaces/Teleport/Class/HOMEHOVER", 0, 48, 52, 24607);
	addHoverButton(24608, "Interfaces/Teleport/Class/LUMB", 1, 48, 52, "Teleport", 0, 24609, 1);
	addHoveredButton(24609, "Interfaces/Teleport/Class/LUMBHOVER", 0, 48, 52, 24610);
	addHoverButton(24611, "Interfaces/Teleport/Class/VARROCK", 1, 48, 52, "Teleport", 0, 24612, 1);
	addHoveredButton(24612, "Interfaces/Teleport/Class/VARROCKHOVER", 0, 48, 52, 24613);
	addHoverButton(24614, "Interfaces/Teleport/Class/FALADOR", 1, 48, 52, "Teleport", 0, 24615, 1);
	addHoveredButton(24615, "Interfaces/Teleport/Class/FALADORHOVER", 0, 48, 52, 24616);
	addHoverButton(24617, "Interfaces/Teleport/Class/CORP", 1, 48, 52, "Teleport", 0, 24618, 1);
	addHoveredButton(24618, "Interfaces/Teleport/Class/CORPHOVER", 0, 48, 52, 24619);
	addHoverButton(24620, "Interfaces/Teleport/Class/GODWARS", 1, 48, 52, "Teleport", 0, 24621, 1);
	addHoveredButton(24621, "Interfaces/Teleport/Class/GODWARSHOVER", 0, 48, 52, 24622);
	addHoverButton(24623, "Interfaces/Teleport/Class/PVP", 1, 48, 52, "Teleport", 0, 24624, 1);
	addHoveredButton(24624, "Interfaces/Teleport/Class/PVPHOVER", 0, 48, 52, 24625);
	addHoverButton(24626, "Interfaces/Teleport/Class/JAD", 1, 48, 52, "Teleport", 0, 24627, 1);
	addHoveredButton(24627, "Interfaces/Teleport/Class/JADHOVER", 0, 48, 52, 24628);
	addHoverButton(24629, "Interfaces/Teleport/Class/SKILLS", 1, 48, 52, "Teleport", 0, 24630, 1);
	addHoveredButton(24630, "Interfaces/Teleport/Class/SKILLSHOVER", 0, 48, 52, 24631);
	setBounds(24601, 11, 23, 0, Interface);
	setBounds(24602, 475, 25, 1, Interface);
	setBounds(24603, 475, 25, 2, Interface);
	setBounds(24605, 60, 100, 3, Interface);
	setBounds(24606, 60, 100, 4, Interface);
	setBounds(24608, 60, 150, 5, Interface);
	setBounds(24609, 60, 150, 6, Interface);
	setBounds(24611, 60, 200, 7, Interface);
	setBounds(24612, 60, 200, 8, Interface);
	setBounds(24614, 230, 100, 9, Interface);
	setBounds(24615, 230, 100, 10, Interface);
	setBounds(24617, 230, 150, 11, Interface);
	setBounds(24618, 230, 150, 12, Interface);
	setBounds(24620, 230, 200, 13, Interface);
	setBounds(24621, 230, 200, 14, Interface);
	setBounds(24623, 400, 100, 15, Interface);
	setBounds(24624, 400, 100, 16, Interface);
	setBounds(24626, 400, 150, 17, Interface);
	setBounds(24627, 400, 150, 18, Interface);
	setBounds(24629, 400, 200, 19, Interface);
	setBounds(24630, 400, 200, 20, Interface);
}

	public static void SettingsTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(6299);
        	addSprite(37001, 1, "Interfaces/Switcher/Background");
		addButton(37046, 1, "Interfaces/Switcher/Top", 63, 24, "", 1);
		addText(37050, "Destroscape", tda, 0, 0x2961663, false, true);
		addText(37051, "x10 Hitting", tda, 0, 0xFFFFFF, false, true);
		addText(37052, "New Hitsplats", tda, 0, 0xFFFFFF, false, true);
		addText(37053, "New Hitpoints Bar", tda, 0, 0xFFFFFF, false, true);
		addText(37054, "New Context Menu", tda, 0, 0xFFFFFF, false, true);
		addText(37055, "Names Above Heads", tda, 0, 0xFFFFFF, false, true);
		addText(37056, "HP Above Heads", tda, 0, 0xFFFFFF, false, true);
		addText(37057, "Fog", tda, 0, 0xFFFFFF, false, true);
		addText(37058, "525", tda, 0, 0xFFFFFF, false, true);
		addText(37059, "", tda, 0, 0x67E300, false, true);
		addText(37060, "", tda, 0, 0x67E300, false, true);
		addText(37061, "", tda, 0, 0xF30021, false, true);
		addText(37062, "", tda, 0, 0x67E300, false, true);
		addText(37063, "", tda, 0, 0xF30021, false, true);
		addText(37064, "", tda, 0, 0xF30021, false, true);
		addText(37065, "", tda, 0, 0x67E300, false, true);
		addButton(37002, 1, "Interfaces/Switcher/SmallerTextBox", 63, 24, "Back", 1);
		addButton(37003, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37004, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37005, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37006, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37007, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37008, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37009, 1, "Interfaces/Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addHoverButton(37020, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Back", -1, 37021, 1);
		addHoveredButton(37021, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37022);
		addHoverButton(37023, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37024, 1);
		addHoveredButton(37024, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37025);
		addHoverButton(37026, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37027, 1);
		addHoveredButton(37027, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37028);
		addHoverButton(37029, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37030, 1);
		addHoveredButton(37030, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37031);
		addHoverButton(37033, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37034, 1);
		addHoveredButton(37034, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37035);
		addHoverButton(37036, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37037, 1);
		addHoveredButton(37037, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37038);
		addHoverButton(37039, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37040, 1);
		addHoveredButton(37040, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37042);
		addHoverButton(37043, "Interfaces/Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37044, 1);
		addHoveredButton(37044, "Interfaces/Switcher/SmallButton", 1, 200, 30, 37045);
       	        addHoverButton(37047, "Interfaces/Switcher/Back", 0, 16, 16, "Back", -1, 37048, 1);
       	        addHoveredButton(37048, "Interfaces/Switcher/Back", 1, 16, 16, 37049);
		tab.totalChildren(44);
		/*BUTTONS*/
       		tab.child(0, 37001, 0, 0);
       		tab.child(1, 37002, 5, 20);
       		tab.child(2, 37003, 5, 50);
       		tab.child(3, 37004, 5, 80);
       		tab.child(4, 37005, 5, 110);
       		tab.child(5, 37006, 5, 140);
       		tab.child(6, 37007, 5, 170);
       		tab.child(7, 37008, 5, 200);
       		tab.child(8, 37009, 5, 230);
		/*HOVER/HOVERED BUTTONS*/
		tab.child(9, 37020, 140, 20);
		tab.child(10, 37021, 140, 20);
		tab.child(11, 37023, 150, 50);
		tab.child(12, 37024, 150, 50);
		tab.child(13, 37026, 150, 80);
		tab.child(14, 37027, 150, 80);
		tab.child(15, 37029, 150, 110);
		tab.child(16, 37030, 150, 110);
		 tab.child(17, 37033, 150, 140);
		tab.child(18, 37034, 150, 140);
		tab.child(19, 37036, 150, 170);
		tab.child(20, 37037, 150, 170);
		tab.child(21, 37039, 150, 200);
		tab.child(22, 37040, 150, 200);
		tab.child(23, 37043, 150, 230);
		tab.child(24, 37044, 150, 230);
		/*TOP SPRITE*/
		tab.child(25, 37046, 35, 0);
		tab.child(26, 37047, 0, 0);
		tab.child(27, 37048, 0, 0);
		/*TEXT*/
		tab.child(43, 37050, 8, 23);
		tab.child(28, 37051, 8, 53);
		tab.child(29, 37052, 8, 83);
		tab.child(30, 37053, 8, 113);
		tab.child(31, 37054, 8, 143);
		tab.child(32, 37055, 8, 173);
		tab.child(33, 37056, 8, 203);
		tab.child(34, 37057, 8, 233);
		//dddd
		tab.child(35, 37058, 146, 23);
		tab.child(36, 37059, 159, 53);
		tab.child(37, 37060, 159, 83);
		tab.child(38, 37061, 159, 113);
		tab.child(39, 37062, 159, 143);
		tab.child(40, 37063, 159, 173);
		tab.child(41, 37064, 159, 203);
		tab.child(42, 37065, 159, 233);


	}
	
	public static void loyaltyTitles(TextDrawingArea[] TDA) {
		RSInterface tab = addScreenInterface(24000);
		addSprite(17209, 1, "Interfaces/Loyalty/Titles/BACKGROUND");
		addHoverButton(17210, "Interfaces/Loyalty/BACK", 1, 35, 39, "Back", -1, 17211, 1);
		addHoveredButton(17211, "Interfaces/Loyalty/BACKH", 0, 35, 39, 17251);	
		addHoverButton(17212, "Interfaces/Loyalty/BUY", 1, 21, 20, "Select", -1, 17213, 1);
		addHoveredButton(17213, "Interfaces/Loyalty/BUYH", 0, 21, 20, 17252);
		addHoverButton(17214, "Interfaces/Loyalty/BUY", 1, 21, 20, "Select", -1, 17215, 1);
		addHoveredButton(17215, "Interfaces/Loyalty/BUYH", 0, 21, 20, 17253);
		addHoverButton(17216, "Interfaces/Loyalty/BUY", 1, 21, 20, "Select", -1, 17217, 1);
		addHoveredButton(17217, "Interfaces/Loyalty/BUYH", 0, 21, 20, 17254);
		addHoverButton(17218, "Interfaces/Loyalty/BUY", 1, 21, 20, "Select", -1, 17219, 1);
		addHoveredButton(17219, "Interfaces/Loyalty/BUYH", 0, 21, 20, 17255);
		addHoverButton(17220, "Interfaces/Loyalty/BUY", 1, 21, 20, "Select", -1, 17221, 1);
		addHoveredButton(17221, "Interfaces/Loyalty/BUYH", 0, 21, 20, 17256);
		addHoverButton(17222, "Interfaces/Loyalty/BUY", 1, 21, 20, "Select", -1, 17223, 1);
		addHoveredButton(17223, "Interfaces/Loyalty/BUYH", 0, 21, 20, 17257);
		addHoverButton(17224, "Interfaces/Loyalty/BUY", 1, 21, 20, "Select", -1, 17225, 1);
		addHoveredButton(17225, "Interfaces/Loyalty/BUYH", 0, 21, 20, 17258);
		addHoverButton(17226, "Interfaces/Loyalty/BUY", 1, 21, 20, "Select", -1, 17227, 1);
		addHoveredButton(17227, "Interfaces/Loyalty/BUYH", 0, 21, 20, 17259);
		addText(17228, "", 0xff9b00, false, true, 38, TDA, 2); // Title	
		addText(17230, "", 0xff9b00, false, true, 20, TDA, 2); // Title	
		addText(17229, "", 0xff9b00, false, true, 20, TDA, 1); // Title	
		addText(17232, "", 0xff9b00, false, true, 20, TDA, 2); // Title	
		addText(17231, "", 0xff9b00, false, true, 20, TDA, 1); // Title	
		addText(17234, "", 0xff9b00, false, true, 20, TDA, 2); // Title	
		addText(17233, "", 0xff9b00, false, true, 20, TDA, 1); // Title	
		addText(17236, "", 0xff9b00, false, true, 20, TDA, 2); // Title	
		addText(17235, "", 0xff9b00, false, true, 20, TDA, 1); // Title	
		addText(17238, "", 0xff9b00, false, true, 20, TDA, 2); // Title	
		addText(17237, "", 0xff9b00, false, true, 20, TDA, 1); // Title	
		addText(17240, "", 0xff9b00, false, true, 20, TDA, 2); // Title	
		addText(17239, "", 0xff9b00, false, true, 20, TDA, 1); // Title	
		addText(17242, "", 0xff9b00, false, true, 20, TDA, 2); // Title	
		addText(17241, "", 0xff9b00, false, true, 20, TDA, 1); // Title	
		addText(17244, "", 0xff9b00, false, true, 20, TDA, 2); // Title	
		addText(17243, "", 0xff9b00, false, true, 20, TDA, 1); // Title	
		tab.totalChildren(36);
		tab.child(0, 17209, 5, 5);
		tab.child(1, 17210, 450, 225);
		tab.child(2, 17211, 450, 225);
		tab.child(3, 17212, 138, 100);
		tab.child(4, 17213, 138, 100);
		tab.child(5, 17214, 138, 170);
		tab.child(6, 17215, 138, 170);
		tab.child(7, 17216, 138, 235);
		tab.child(8, 17217, 138, 235);
		tab.child(9, 17218, 298, 100);
		tab.child(10, 17219, 298, 100);
		tab.child(11, 17220, 298, 170);
		tab.child(12, 17221, 298, 170);
		tab.child(13, 17222, 298, 235);
		tab.child(14, 17223, 298, 235);
		tab.child(15, 17224, 448, 100);
		tab.child(16, 17225, 448, 100);
		tab.child(17, 17226, 448, 170);
		tab.child(18, 17227, 448, 170);
		tab.child(19, 17228, 227, 24);
		tab.child(20, 17229, 85, 80);
		tab.child(21, 17230, 85, 98);
		tab.child(22, 17231, 85, 147);	
		tab.child(23, 17232, 85, 166);
		tab.child(24, 17233, 85, 211);
		tab.child(25, 17234, 85, 230);	
		tab.child(26, 17235, 245, 80);
		tab.child(27, 17236, 245, 96);
		tab.child(28, 17237, 245, 150);
		tab.child(29, 17238, 245, 167);
		tab.child(30, 17239, 245, 212);
		tab.child(31, 17240, 245, 230);
		tab.child(32, 17241, 395, 80);
		tab.child(33, 17242, 395, 95);
		tab.child(34, 17243, 395, 150);
		tab.child(35, 17244, 395, 167);
	}
	
	public static void priceChecker(TextDrawingArea[] tda) {
		RSInterface rsi = addTabInterface(43933);
		addSprite(18245, 1, "Interfaces/Checker/CHECK");
		addPriceChecker(18246);
		addHoverButton(18247, "SPRITE", 1, 21, 21, "Close", 250, 18247, 3);
		addHoveredButton(18248, "SPRITE", 3, 21, 21, 18248);
		rsi.totalChildren(67);
		rsi.child(0, 18245, 10, 20);//was 10 so + 10
		rsi.child(1, 18246, 100, 56);
		rsi.child(2, 18247, 472, 23);
		rsi.child(3, 18248, 472, 23);
		addText(18350, "Total value:", tda, 0, 0xFFFFFF, false, true);
		rsi.child(4, 18350, 225, 295);  // Open Text
		addText(18351, "0", tda, 0, 0xFFFFFF, true, true);
		rsi.child(5, 18351, 251, 306);
		addText(18352, "", tda, 0, 0xFFFFFF, false, true);
		rsi.child(6, 18352, 120, 150);
		addText(18353, "", tda, 0, 0xFFFFFF, true, true);
		rsi.child(7, 18353, 120, 85);
		addText(18354, "", tda, 0, 0xFFFFFF, true, true);
		rsi.child(8, 18354, 120, 95);
		addText(18355, "", tda, 0, 0xFFFFFF, true, true);
		rsi.child(9, 18355, 120, 105);
		addText(18356, "", tda, 0, 0xFFFFFF, true, true);
		rsi.child(10, 18356, 190, 85);
		addText(18357, "", tda, 0, 0xFFFFFF, true, true);
		rsi.child(11, 18357, 190, 95);
		addText(18358, "", tda, 0, 0xFFFFFF, true, true);
		rsi.child(12, 18358, 190, 105);
		addText(18359, "", tda, 0, 0xFFFFFF, true, true);
		rsi.child(13, 18359, 260, 85);
		addText(18360, "", tda, 0, 0xFFFFFF, true, true);
		rsi.child(14, 18360, 260, 95);
		addText(18361, "", tda, 0, 0xFFFFFF, true, true);rsi.child(15, 18361, 260, 105);
		addText(18362, "", tda, 0, 0xFFFFFF, true, true);rsi.child(16, 18362, 330, 85);
		addText(18363, "", tda, 0, 0xFFFFFF, true, true);rsi.child(17, 18363, 330, 95);
		addText(18364, "", tda, 0, 0xFFFFFF, true, true);rsi.child(18, 18364, 330, 105);
		addText(18365, "", tda, 0, 0xFFFFFF, true, true);rsi.child(19, 18365, 400, 85);
		addText(18366, "", tda, 0, 0xFFFFFF, true, true);rsi.child(20, 18366, 400, 95);
		addText(18367, "", tda, 0, 0xFFFFFF, true, true);rsi.child(21, 18367, 400, 105);
		addText(18368, "", tda, 0, 0xFFFFFF, true, true);rsi.child(22, 18368, 120, 145);
		addText(18369, "", tda, 0, 0xFFFFFF, true, true);rsi.child(23, 18369, 120, 155);
		addText(18370, "", tda, 0, 0xFFFFFF, true, true);rsi.child(24, 18370, 120, 165);
		addText(18371, "", tda, 0, 0xFFFFFF, true, true);rsi.child(25, 18371, 190, 145);
		addText(18372, "", tda, 0, 0xFFFFFF, true, true);rsi.child(26, 18372, 190, 155);
		addText(18373, "", tda, 0, 0xFFFFFF, true, true);rsi.child(27, 18373, 190, 165);
		addText(18374, "", tda, 0, 0xFFFFFF, true, true);rsi.child(28, 18374, 260, 145);
		addText(18375, "", tda, 0, 0xFFFFFF, true, true);rsi.child(29, 18375, 260, 155);
		addText(18376, "", tda, 0, 0xFFFFFF, true, true);rsi.child(30, 18376, 260, 165);
		addText(18377, "", tda, 0, 0xFFFFFF, true, true);rsi.child(31, 18377, 330, 145);
		addText(18378, "", tda, 0, 0xFFFFFF, true, true);rsi.child(32, 18378, 330, 155);
		addText(18379, "", tda, 0, 0xFFFFFF, true, true);rsi.child(33, 18379, 330, 165);
		addText(18380, "", tda, 0, 0xFFFFFF, true, true);rsi.child(34, 18380, 400, 145);
		addText(18381, "", tda, 0, 0xFFFFFF, true, true);rsi.child(35, 18381, 400, 155);
		addText(18382, "", tda, 0, 0xFFFFFF, true, true);rsi.child(36, 18382, 400, 165);
		addText(18383, "", tda, 0, 0xFFFFFF, true, true);rsi.child(37, 18383, 120, 205);
		addText(18384, "", tda, 0, 0xFFFFFF, true, true);rsi.child(38, 18384, 120, 215);
		addText(18385, "", tda, 0, 0xFFFFFF, true, true);rsi.child(39, 18385, 120, 225);
		addText(18386, "", tda, 0, 0xFFFFFF, true, true);rsi.child(40, 18386, 190, 205);
		addText(18387, "", tda, 0, 0xFFFFFF, true, true);rsi.child(41, 18387, 190, 215);
		addText(18388, "", tda, 0, 0xFFFFFF, true, true);rsi.child(42, 18388, 190, 225);
		addText(18389, "", tda, 0, 0xFFFFFF, true, true);rsi.child(43, 18389, 260, 205);
		addText(18390, "", tda, 0, 0xFFFFFF, true, true);rsi.child(44, 18390, 260, 215);
		addText(18391, "", tda, 0, 0xFFFFFF, true, true);rsi.child(45, 18391, 260, 225);
		addText(18392, "", tda, 0, 0xFFFFFF, true, true);rsi.child(46, 18392, 330, 205);
		addText(18393, "", tda, 0, 0xFFFFFF, true, true);rsi.child(47, 18393, 330, 215);
		addText(18394, "", tda, 0, 0xFFFFFF, true, true);rsi.child(48, 18394, 330, 225);
		addText(18395, "", tda, 0, 0xFFFFFF, true, true);rsi.child(49, 18395, 400, 205);
		addText(18396, "", tda, 0, 0xFFFFFF, true, true);rsi.child(50, 18396, 400, 215);
		addText(18397, "", tda, 0, 0xFFFFFF, true, true);rsi.child(51, 18397, 400, 225);    
		addText(18398, "", tda, 0, 0xFFFFFF, true, true);rsi.child(52, 18398, 120, 260);
		addText(18399, "", tda, 0, 0xFFFFFF, true, true);rsi.child(53, 18399, 120, 270);
		addText(18400, "", tda, 0, 0xFFFFFF, true, true);rsi.child(54, 18400, 120, 280);
		addText(18401, "", tda, 0, 0xFFFFFF, true, true);rsi.child(55, 18401, 190, 260);
		addText(18402, "", tda, 0, 0xFFFFFF, true, true);rsi.child(56, 18402, 190, 270);
		addText(18403, "", tda, 0, 0xFFFFFF, true, true);rsi.child(57, 18403, 190, 280);
		addText(18404, "", tda, 0, 0xFFFFFF, true, true);rsi.child(58, 18404, 260, 260);
		addText(18405, "", tda, 0, 0xFFFFFF, true, true);rsi.child(59, 18405, 260, 270);
		addText(18406, "", tda, 0, 0xFFFFFF, true, true);rsi.child(60, 18406, 260, 280);
		addText(18407, "", tda, 0, 0xFFFFFF, true, true);rsi.child(61, 18407, 330, 260);
		addText(18408, "", tda, 0, 0xFFFFFF, true, true);rsi.child(62, 18408, 330, 270);
		addText(18409, "", tda, 0, 0xFFFFFF, true, true);rsi.child(63, 18409, 330, 280);
		addText(18410, "", tda, 0, 0xFFFFFF, true, true);rsi.child(64, 18410, 400, 260);
		addText(18411, "", tda, 0, 0xFFFFFF, true, true);rsi.child(65, 18411, 400, 270);
		addText(18412, "", tda, 0, 0xFFFFFF, true, true);rsi.child(66, 18412, 400, 280);
	}

	/*public static void quickPrayers(TextDrawingArea[] TDA) {
		int i = 0;
		RSInterface localRSInterface = addTabInterface(20000);
		addSprite(15201, 3, "/Interfaces/QuickPrayer/Sprite");
		addText(15240, "Select your quick prayers:", TDA, 0, 16750623, false, true);
		//addHerpDerpSprite(17249, 0, "/Interfaces/QuickPrayer/Sprite", 50);
		int j = 15202;
		for (int k = 630;
				(j <= 17231) || (k <= 659); ++k) {
			addConfigButton(j, 15200, 2, 1, "/Interfaces/QuickPrayer/Sprite", 14, 15, "Select", 0, 1, k);
			j++;
		}
		addHoverButton(15241, "/Interfaces/QuickPrayer/Sprite", 4, 190, 24, "Confirm Selection", -1, 15242, 1);
		addHoveredButton(15242, "/Interfaces/QuickPrayer/Sprite", 5, 190, 24, 15243);
		setChildren(58, localRSInterface);
		/*setBounds(25001, 5, 28, i++, localRSInterface);
        setBounds(25003, 44, 28, i++, localRSInterface);
        setBounds(25005, 79, 31, i++, localRSInterface);
        setBounds(25007, 116, 30, i++, localRSInterface);
        setBounds(25009, 153, 29, i++, localRSInterface);
        setBounds(25011, 5, 68, i++, localRSInterface);
        setBounds(25013, 44, 67, i++, localRSInterface);
        setBounds(25015, 79, 69, i++, localRSInterface);
        setBounds(25017, 116, 70, i++, localRSInterface);
        setBounds(25019, 154, 70, i++, localRSInterface);
        setBounds(25021, 4, 104, i++, localRSInterface);
        setBounds(25023, 44, 107, i++, localRSInterface);
        setBounds(25025, 81, 105, i++, localRSInterface);
        setBounds(25027, 117, 105, i++, localRSInterface);
        setBounds(25029, 156, 107, i++, localRSInterface);
        setBounds(25031, 5, 145, i++, localRSInterface);
        setBounds(25033, 43, 144, i++, localRSInterface);
        setBounds(25035, 83, 144, i++, localRSInterface);
        setBounds(25037, 115, 141, i++, localRSInterface);
        setBounds(25039, 154, 144, i++, localRSInterface);
        setBounds(25041, 5, 180, i++, localRSInterface);
        setBounds(25043, 41, 178, i++, localRSInterface);
        setBounds(25045, 79, 183, i++, localRSInterface);
        setBounds(25047, 116, 178, i++, localRSInterface);
        setBounds(25049, 161, 180, i++, localRSInterface);
        setBounds(25051, 5, 217, i++, localRSInterface);*/
		/*setBounds(15249, 0, 25, i++, localRSInterface);
		setBounds(15201, 0, 22, i++, localRSInterface);
		setBounds(15201, 0, 237, i++, localRSInterface);
		setBounds(15202, 2, 25, i++, localRSInterface);
		setBounds(15203, 41, 25, i++, localRSInterface);
		setBounds(15204, 76, 25, i++, localRSInterface);
		setBounds(15205, 113, 25, i++, localRSInterface);
		setBounds(15206, 150, 25, i++, localRSInterface);
		setBounds(15207, 2, 65, i++, localRSInterface);
		setBounds(15208, 41, 65, i++, localRSInterface);
		setBounds(15209, 76, 65, i++, localRSInterface);
		setBounds(15210, 113, 65, i++, localRSInterface);
		setBounds(15211, 150, 65, i++, localRSInterface);
		setBounds(15212, 2, 102, i++, localRSInterface);
		setBounds(15213, 41, 102, i++, localRSInterface);
		setBounds(15214, 76, 102, i++, localRSInterface);
		setBounds(1215, 113, 102, i++, localRSInterface);
		setBounds(17216, 150, 102, i++, localRSInterface);
		setBounds(17217, 2, 141, i++, localRSInterface);
		setBounds(17218, 41, 141, i++, localRSInterface);
		setBounds(17219, 76, 141, i++, localRSInterface);
		setBounds(17220, 113, 141, i++, localRSInterface);
		setBounds(17221, 150, 141, i++, localRSInterface);
		setBounds(17222, 2, 177, i++, localRSInterface);
		setBounds(17223, 41, 177, i++, localRSInterface);
		setBounds(17224, 76, 177, i++, localRSInterface);
		setBounds(17225, 113, 177, i++, localRSInterface);
		setBounds(17226, 150, 177, i++, localRSInterface);
		setBounds(17227, 1, 211, i++, localRSInterface);
		setBounds(17240, 5, 5, i++, localRSInterface);
		setBounds(17241, 0, 237, i++, localRSInterface);
		setBounds(17242, 0, 237, i++, localRSInterface);
	}*/

	public static void quickPrayers(TextDrawingArea[] TDA) {
		int i = 0;
		RSInterface localRSInterface = addTabInterface(20000);
		addSprite(17201, 3, "/Interfaces/QuickPrayer/Sprite");
		addText(17240, "Select your quick prayers:", TDA, 0, 16750623, false, true);
		addTransparentSprite(17249, 0, "/Interfaces/QuickPrayer/Sprite", 50);
		int j = 17202;
		for (int k = 630; (j <= 17231) || (k <= 659); ++k) {
			addConfigButton(j, 17200, 2, 1, "/Interfaces/QuickPrayer/Sprite", 14, 15, "Select", 0, 1, k);
			j++;
		}
		addHoverButton(17241, "/Interfaces/QuickPrayer/Sprite", 4, 190, 24, "Confirm Selection", -1, 17242, 1);
		addHoveredButton(17242, "/Interfaces/QuickPrayer/Sprite", 5, 190, 24, 17243);
		setChildren(58, localRSInterface);
		setBounds(25001, 5, 28, i++, localRSInterface);
		setBounds(25003, 44, 28, i++, localRSInterface);
		setBounds(25005, 79, 31, i++, localRSInterface);
		setBounds(25007, 116, 30, i++, localRSInterface);
		setBounds(25009, 153, 29, i++, localRSInterface);
		setBounds(25011, 5, 68, i++, localRSInterface);
		setBounds(25013, 44, 67, i++, localRSInterface);
		setBounds(25015, 79, 69, i++, localRSInterface);
		setBounds(25017, 116, 70, i++, localRSInterface);
		setBounds(25019, 154, 70, i++, localRSInterface);
		setBounds(25021, 4, 104, i++, localRSInterface);
		setBounds(25023, 44, 107, i++, localRSInterface);
		setBounds(25025, 81, 105, i++, localRSInterface);
		setBounds(25027, 117, 105, i++, localRSInterface);
		setBounds(25029, 156, 107, i++, localRSInterface);
		setBounds(25031, 5, 145, i++, localRSInterface);
		setBounds(25033, 43, 144, i++, localRSInterface);
		setBounds(25035, 83, 144, i++, localRSInterface);
		setBounds(25037, 115, 141, i++, localRSInterface);
		setBounds(25039, 154, 144, i++, localRSInterface);
		setBounds(25041, 5, 180, i++, localRSInterface);
		setBounds(25043, 41, 178, i++, localRSInterface);
		setBounds(25045, 79, 183, i++, localRSInterface);
		setBounds(25047, 116, 178, i++, localRSInterface);
		setBounds(25049, 161, 180, i++, localRSInterface);
		//setBounds(18015, 4, 210, i++, localRSInterface);
		setBounds(25051, 5, 217, i++, localRSInterface);
		//setBounds(18061, 78, 212, i++, localRSInterface);
		//setBounds(18121, 116, 208, i++, localRSInterface);
		setBounds(17249, 0, 25, i++, localRSInterface);
		setBounds(17201, 0, 22, i++, localRSInterface);
		setBounds(17201, 0, 237, i++, localRSInterface);
		setBounds(17202, 2, 25, i++, localRSInterface);
		setBounds(17203, 41, 25, i++, localRSInterface);
		setBounds(17204, 76, 25, i++, localRSInterface);
		setBounds(17205, 113, 25, i++, localRSInterface);
		setBounds(17206, 150, 25, i++, localRSInterface);
		setBounds(17207, 2, 65, i++, localRSInterface);
		setBounds(17208, 41, 65, i++, localRSInterface);
		setBounds(17209, 76, 65, i++, localRSInterface);
		setBounds(17210, 113, 65, i++, localRSInterface);
		setBounds(17211, 150, 65, i++, localRSInterface);
		setBounds(17212, 2, 102, i++, localRSInterface);
		setBounds(17213, 41, 102, i++, localRSInterface);
		setBounds(17214, 76, 102, i++, localRSInterface);
		setBounds(17215, 113, 102, i++, localRSInterface);
		setBounds(17216, 150, 102, i++, localRSInterface);
		setBounds(17217, 2, 141, i++, localRSInterface);
		setBounds(17218, 41, 141, i++, localRSInterface);
		setBounds(17219, 76, 141, i++, localRSInterface);
		setBounds(17220, 113, 141, i++, localRSInterface);
		setBounds(17221, 150, 141, i++, localRSInterface);
		setBounds(17222, 2, 177, i++, localRSInterface);
		setBounds(17223, 41, 177, i++, localRSInterface);
		setBounds(17224, 76, 177, i++, localRSInterface);
		setBounds(17225, 113, 177, i++, localRSInterface);
		setBounds(17226, 150, 177, i++, localRSInterface);
		setBounds(17227, 1, 211, i++, localRSInterface);
		//setBounds(17228, 1, 211, i++, localRSInterface);
		//setBounds(17229, 75, 211, i++, localRSInterface);
		//setBounds(17230, 113, 211, i++, localRSInterface);
		setBounds(17240, 5, 5, i++, localRSInterface);
		setBounds(17241, 0, 237, i++, localRSInterface);
		setBounds(17242, 0, 237, i++, localRSInterface);
	}

	/*public static void quickCurses(TextDrawingArea[] TDA) {
		int frame = 0;
		RSInterface tab = addTabInterface(22000);
		addSprite(17201, 3, "/Interfaces/QuickPrayer/Sprite");
		addText(17235, "Select your quick curses:", TDA, 0, 16750623, false, true);
		addTransparentSprite(17249, 0, "/Interfaces/QuickPrayer/Sprite", 50);
		int j = 17202;
		for (int k = 630; (j <= 17222) || (k <= 656); k++) {
			addConfigButton(j, 17200, 2, 1, "/Interfaces/QuickPrayer/Sprite", 14, 15, "Select", 0, 1, k);
			j++;
		}
		setChildren(46, tab);
		setBounds(22504, 5, 8+17, frame++, tab);
		setBounds(22506, 44, 8+20, frame++, tab);
		setBounds(22508, 79, 11+19, frame++, tab);
		setBounds(22510, 116, 10+18, frame++, tab);
		setBounds(22512, 153, 9+20, frame++, tab);
		setBounds(22514, 5, 48+18, frame++, tab);
		setBounds(22516, 44, 47+21, frame++, tab);
		setBounds(22518, 79, 49+20, frame++, tab);	
		setBounds(22520, 116, 50+19, frame++, tab);
		setBounds(22522, 154, 50+20, frame++, tab);		
		setBounds(22524, 4, 84+21, frame++, tab);		
		setBounds(22526, 44, 87+19, frame++, tab);		
		setBounds(22528, 81, 85+20, frame++, tab);	
		setBounds(22530, 117, 85+20, frame++, tab);					
		setBounds(22532, 156, 87+18, frame++, tab);			
		setBounds(22534, 5, 125+19, frame++, tab);			
		setBounds(22536, 43, 124+19, frame++, tab);		
		setBounds(22538, 83, 124+20, frame++, tab);										
		setBounds(22540, 115, 125+21, frame++, tab);
		setBounds(22542, 154, 126+22, frame++, tab);
		setBounds(17249, 0, 25, frame++, tab);
		setBounds(17201, 0, 22, frame++, tab);
		setBounds(17201, 0, 237, frame++, tab);
		setBounds(17202, 2, 25, frame++, tab);
		setBounds(17203, 41, 25, frame++, tab);
		setBounds(17204, 76, 25, frame++, tab);
		setBounds(17205, 113, 25, frame++, tab);
		setBounds(17206, 150, 25, frame++, tab);
		setBounds(17207, 2, 65, frame++, tab);
		setBounds(17208, 41, 65, frame++, tab);
		setBounds(17209, 76, 65, frame++, tab);
		setBounds(17210, 113, 65, frame++, tab);
		setBounds(17211, 150, 65, frame++, tab);
		setBounds(17212, 2, 102, frame++, tab);
		setBounds(17213, 41, 102, frame++, tab);
		setBounds(17214, 76, 102, frame++, tab);
		setBounds(17215, 113, 102, frame++, tab);
		setBounds(17216, 150, 102, frame++, tab);
		setBounds(17217, 2, 141, frame++, tab);
		setBounds(17218, 41, 141, frame++, tab);
		setBounds(17219, 76, 141, frame++, tab);
		setBounds(17220, 113, 141, frame++, tab);
		setBounds(17221, 150, 141, frame++, tab);
		setBounds(17235, 5, 5, frame++, tab);
		setBounds(17241, 0, 237, frame++, tab);
		setBounds(17242, 0, 237, frame++, tab);
	}*/


	private static void addTransparentSprite(int id, int spriteId, String spriteName, int transparency) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.transparency = (byte) transparency;
		tab.hoverType = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName);
		tab.width = 512;
		tab.height = 334;
		tab.drawsTransparent = true;
	}

	public static void PVPInterface(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21200);
		addSprite(21201, 0, "Interfaces/PvP/NOTINWILD1");
		addText(21202, "", tda, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21201, 400, 285, 0,RSinterface);
		setBounds(21202, 444, 318, 1,RSinterface);
	}

	public static void PVPInterface2(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21300);
		addSprite(21301, 0, "Interfaces/PvP/INWILD1");
		addText(21302, "", tda, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21301, 400, 285, 0,RSinterface);
		setBounds(21302, 444, 318, 1,RSinterface);
	}

	public static void PVPInterface3(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21400);
		addSprite(21401, 0, "Interfaces/PvP/INCOUNT1");
		addText(21402, "", tda, 1, 0xff9040, true, true);
		addText(21403, "", tda,1,0xffffff, true, true);
		int last = 3;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21401, 400, 285, 0,RSinterface);
		setBounds(21402, 444, 318, 1,RSinterface);
		setBounds(21403, 412, 290, 2,RSinterface);
	}
	public static void novicePvm(TextDrawingArea[] tda) {
		RSInterface tab = addInterface(8040);
		addSprite(8001, 0, "Interfaces/NewTeles/MAIN");
		addText(8801, "Novice PvM Teleports", tda, 3, 0xff981f, true, true);
		addHoverButton(8017, "Interfaces/NewTeles/PVM/Novice/TELE", 0, 131, 26, "Teleport", 0, 8018, 1);//abracadabra
		addHoveredButton(8018, "Interfaces/NewTeles/PVM/Novice/TELE", 1, 155, 26, 8019);
		addHoverButton(8020, "Interfaces/NewTeles/PVM/Novice/TELE", 2, 131, 26, "Teleport", 0, 8021, 1);
		addHoveredButton(8021, "Interfaces/NewTeles/PVM/Novice/TELE", 3, 155, 26, 8022);
		addHoverButton(8023, "Interfaces/NewTeles/PVM/Novice/TELE", 4, 131, 26, "Teleport", 0, 8024, 1);
		addHoveredButton(8024, "Interfaces/NewTeles/PVM/Novice/TELE", 5, 155, 26, 8025);	
		addHoverButton(8026, "Interfaces/NewTeles/PVM/Novice/TELE", 6, 131, 26, "Teleport", 0, 8027, 1);
		addHoveredButton(8027, "Interfaces/NewTeles/PVM/Novice/TELE", 7, 155, 26, 8028);
		addHoverButton(8029, "Interfaces/NewTeles/MAGE", 0, 45, 35, "Back to SpellBook", 0, 8030, 1);
		addHoveredButton(8030, "Interfaces/NewTeles/MAGE", 1, 45, 35, 8031);
		addHoverButton(8032, "Interfaces/NewTeles/BACK", 0, 35, 39, "Back to Main Page", 0, 8033, 1);
		addHoveredButton(8033, "Interfaces/NewTeles/BACK", 1, 35, 39, 8034);			
		setChildren(14, tab);
		setBounds(8001, 0, 10, 0, tab);
		setBounds(8017, 35, 45, 1, tab);
		setBounds(8018, 35, 45, 2, tab);
		setBounds(8020, 35, 80, 3, tab);
		setBounds(8021, 35, 80, 4, tab);
		setBounds(8023, 35, 115, 5, tab);
		setBounds(8024, 35, 115, 6, tab);
		setBounds(8026, 35, 150, 7, tab);
		setBounds(8027, 35, 150, 8, tab);
		setBounds(8029, 110, 210, 10, tab);
		setBounds(8030, 110, 210, 11, tab);
		setBounds(8032, 60, 207, 12, tab);
		setBounds(8033, 60, 207, 13, tab);
		setBounds(8801, 103, 18, 9, tab);
	}


	public static void interPvm(TextDrawingArea[] tda) {
		RSInterface tab = addInterface(8811);
		addSprite(8812, 0, "Interfaces/NewTeles/MAIN");
		addText(8813, "Intermediate", tda, 3, 0xff981f, true, true);
		addText(8846, "PvM Teleports", tda, 3, 0xff981f, true, true);
		addHoverButton(18814, "Interfaces/NewTeles/PVM/Inter/TELE", 0, 131, 26, "Teleport", 0, 18815, 1);//abracadabra
		addHoveredButton(18815, "Interfaces/NewTeles/PVM/Inter/TELE", 1, 155, 26, 18816);
		addHoverButton(18817, "Interfaces/NewTeles/PVM/Inter/TELE", 2, 131, 26, "Teleport", 0, 18818, 1);
		addHoveredButton(18818, "Interfaces/NewTeles/PVM/Inter/TELE", 3, 155, 26, 18819);
		addHoverButton(18820, "Interfaces/NewTeles/PVM/Inter/TELE", 4, 131, 26, "Teleport", 0, 18821, 1);
		addHoveredButton(18821, "Interfaces/NewTeles/PVM/Inter/TELE", 5, 155, 26, 18922);
		addHoverButton(18823, "Interfaces/NewTeles/PVM/Inter/TELE", 6, 131, 26, "Teleport", 0, 18824, 1);
		addHoveredButton(18824, "Interfaces/NewTeles/PVM/Inter/TELE", 7, 155, 26, 18825);		
		addHoverButton(8029, "Interfaces/NewTeles/MAGE", 0, 45, 35, "Back to SpellBook", 0, 8030, 1);
		addHoveredButton(8030, "Interfaces/NewTeles/MAGE", 1, 45, 35, 8031);
		addHoverButton(8032, "Interfaces/NewTeles/BACK", 0, 35, 39, "Back to Main Page", 0, 8033, 1);
		addHoveredButton(8033, "Interfaces/NewTeles/BACK", 1, 35, 39, 8034);	
		setChildren(15, tab);
		//setBounds(8811, 0, 10, 0, tab);
		setBounds(8812, 0, 10, 0, tab);
		setBounds(8813, 103, 14, 1, tab);
		setBounds(8846, 103, 30, 10, tab);
		setBounds(18814, 35, 50, 2, tab);
		setBounds(18815, 35, 50, 3, tab);
		setBounds(18817, 35, 85, 4, tab);
		setBounds(18818, 35, 85, 5, tab);
		setBounds(18820, 35, 120, 6, tab);
		setBounds(18821, 35, 120, 7, tab);
		setBounds(18823, 35, 155, 8, tab);
		setBounds(18824, 35, 155, 9, tab);
		setBounds(8029, 110, 210, 14, tab);
		setBounds(8030, 110, 210, 11, tab);
		setBounds(8032, 60, 207, 12, tab);
		setBounds(8033, 60, 207, 13, tab);
	}	
	public static void elitePvm(TextDrawingArea[] tda) {
		RSInterface tab = addInterface(8120);
		addSprite(8001, 0, "Interfaces/NewTeles/MAIN");
		addText(8803, "Elite PvM Teleports", tda, 3, 0xff981f, true, true);
		addHoverButton(8081, "Interfaces/NewTeles/PVM/Elite/TELE", 0, 131, 26, "Teleport", 0, 8082, 1);//abracadabra
		addHoveredButton(8082, "Interfaces/NewTeles/PVM/Elite/TELE", 1, 155, 26, 8083);
		addHoverButton(8084, "Interfaces/NewTeles/PVM/Elite/TELE", 2, 131, 26, "Teleport", 0, 8085, 1);
		addHoveredButton(8085, "Interfaces/NewTeles/PVM/Elite/TELE", 3, 155, 26, 8086);
		addHoverButton(8087, "Interfaces/NewTeles/PVM/Elite/TELE", 4, 131, 26, "Teleport", 0, 8088, 1);
		addHoveredButton(8088, "Interfaces/NewTeles/PVM/Elite/TELE", 5, 155, 26, 8089);	
		addHoverButton(8090, "Interfaces/NewTeles/PVM/Elite/TELE", 6, 131, 26, "Teleport", 0, 8091, 1);//abracadabra
		addHoveredButton(8091, "Interfaces/NewTeles/PVM/Elite/TELE", 7, 155, 26, 8092);
		addHoverButton(8093, "Interfaces/NewTeles/PVM/Elite/TELE", 8, 131, 26, "Teleport", 0, 8094, 1);
		addHoveredButton(8094, "Interfaces/NewTeles/PVM/Elite/TELE", 9, 155, 26, 8095);
		addHoverButton(8096, "Interfaces/NewTeles/PVM/Elite/TELE", 10, 131, 26, "Teleport", 0, 8097, 1);
		addHoveredButton(8097, "Interfaces/NewTeles/PVM/Elite/TELE", 11, 155, 26, 8098);	
		addHoverButton(8029, "Interfaces/NewTeles/MAGE", 0, 45, 35, "Back to SpellBook", 0, 8030, 1);
		addHoveredButton(8030, "Interfaces/NewTeles/MAGE", 1, 45, 35, 8031);
		addHoverButton(8032, "Interfaces/NewTeles/BACK", 0, 35, 39, "Back to Main Page", 0, 8033, 1);
		addHoveredButton(8033, "Interfaces/NewTeles/BACK", 1, 35, 39, 8034);	
		setChildren(18, tab);
		setBounds(8001, 0, 10, 0, tab);
		setBounds(8081, 35, 40, 1, tab);
		setBounds(8082, 35, 40, 2, tab);
		setBounds(8084, 35, 68, 3, tab);
		setBounds(8085, 35, 68, 4, tab);
		setBounds(8087, 35, 96, 5, tab);
		setBounds(8088, 35, 96, 6, tab);
		setBounds(8090, 35, 124, 7, tab);
		setBounds(8091, 35, 124, 8, tab);
		setBounds(8093, 35, 152, 9, tab);
		setBounds(8094, 35, 152, 10, tab);
		setBounds(8096, 35, 180, 11, tab);
		setBounds(8097, 35, 180, 12, tab);
		setBounds(8803, 103, 18, 13, tab);
		setBounds(8029, 110, 210, 14, tab);
		setBounds(8030, 110, 210, 15, tab);
		setBounds(8032, 60, 207, 16, tab);
		setBounds(8033, 60, 207, 17, tab);
	}
	public static void bossPvm(TextDrawingArea[] tda) {
		RSInterface tab = addInterface(8560);
		addSprite(8001, 0, "Interfaces/NewTeles/MAIN");
		addText(28804, "Bosses PvM Teleports", tda, 3, 0xff981f, true, true);
		addHoverButton(28162, "Interfaces/NewTeles/PVM/Boss/TELE", 0, 131, 26, "Teleport", 0, 28163, 1);//abracadabra
		addHoveredButton(28163, "Interfaces/NewTeles/PVM/Boss/TELE", 1, 155, 26, 28164);
		addHoverButton(28165, "Interfaces/NewTeles/PVM/Boss/TELE", 2, 131, 26, "Teleport", 0, 28166, 1);
		addHoveredButton(28166, "Interfaces/NewTeles/PVM/Boss/TELE", 3, 155, 26, 28167);
		addHoverButton(28168, "Interfaces/NewTeles/PVM/Boss/TELE", 4, 131, 26, "Teleport", 0, 28169, 1);
		addHoveredButton(28169, "Interfaces/NewTeles/PVM/Boss/TELE", 5, 155, 26, 28170);
		addHoverButton(8029, "Interfaces/NewTeles/MAGE", 0, 45, 35, "Back to SpellBook", 0, 8030, 1);
		addHoveredButton(8030, "Interfaces/NewTeles/MAGE", 1, 45, 35, 8031);	
		addHoverButton(8032, "Interfaces/NewTeles/BACK", 0, 35, 39, "Back to Main Page", 0, 8033, 1);
		addHoveredButton(8033, "Interfaces/NewTeles/BACK", 1, 35, 39, 8034);			
		setChildren(12, tab);
		setBounds(8001, 0, 10, 0, tab);
		setBounds(28162, 35, 45, 1, tab);
		setBounds(28163, 35, 45, 2, tab);
		setBounds(28165, 35, 80, 3, tab);
		setBounds(28166, 35, 80, 4, tab);
		setBounds(28168, 35, 115, 5, tab);
		setBounds(28169, 35, 115, 6, tab);
		setBounds(28804, 103, 18, 7, tab);
		setBounds(8029, 110, 210, 8, tab);
		setBounds(8030, 110, 210, 9, tab);
		setBounds(8032, 60, 207, 10, tab);
		setBounds(8033, 60, 207, 11, tab);
	}
	public static void levelPvm(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(8000);
		addSprite(8001, 0, "Interfaces/NewTeles/MAIN");
		addText(8800, "PvM Teleports", tda, 3, 0xff981f, true, true);
		addHoverButton(8002, "Interfaces/NewTeles/PVM/LEVEL", 0, 131, 26, "Choose Novice", 0, 8003, 1);//abracadabra
		addHoveredButton(8003, "Interfaces/NewTeles/PVM/LEVEL", 1, 155, 26, 8004);
		addHoverButton(8006, "Interfaces/NewTeles/PVM/LEVEL", 2, 131, 26, "Choose Intermediate", 0, 8007, 1);
		addHoveredButton(8007, "Interfaces/NewTeles/PVM/LEVEL", 3, 155, 26, 8008);
		addHoverButton(8010, "Interfaces/NewTeles/PVM/LEVEL", 4, 131, 26, "Choose Elite", 0, 8011, 1);
		addHoveredButton(8011, "Interfaces/NewTeles/PVM/LEVEL", 5, 155, 26, 8012);
		addHoverButton(8014, "Interfaces/NewTeles/PVM/LEVEL", 6, 131, 26, "Choose Bosses", 0, 8015, 1);
		addHoveredButton(8015, "Interfaces/NewTeles/PVM/LEVEL", 7, 155, 26, 8016);
		addHoverButton(8029, "Interfaces/NewTeles/MAGE", 0, 45, 35, "Back to SpellBook", 0, 8030, 1);
		addHoveredButton(8030, "Interfaces/NewTeles/MAGE", 1, 45, 35, 8031);
		setChildren(12, tab);
		setBounds(8001, 0, 10, 0, tab);
		setBounds(8002, 35, 45, 1, tab);
		setBounds(8003, 35, 45, 2, tab);
		setBounds(8006, 35, 80, 3, tab);
		setBounds(8007, 35, 80, 4, tab);
		setBounds(8010, 35, 115, 5, tab);
		setBounds(8011, 35, 115, 6, tab);
		setBounds(8014, 35, 150, 7, tab);
		setBounds(8015, 35, 150, 8, tab);
		setBounds(8800, 103, 18, 9, tab);
		setBounds(8029, 145, 210, 10, tab);
		setBounds(8030, 145, 210, 11, tab);

	}

	public static void LodestoneNetwork(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(26000);
		addSprite(24201, 0, "Interfaces/Lodestones/Main");
		addHoverButton(24202, "Interfaces/Lodestones/Unlock", 14, 40, 40, "Lunar Isle", 250, 24203, 5);
		addHoveredButton(24203, "Interfaces/Lodestones/UnlockHover", 14, 50, 50, 24204);
		addHoverButton(24205, "Interfaces/Lodestones/Unlock", 0, 40, 40, "Lumbridge", 250, 24206, 5);
		addHoveredButton(24206, "Interfaces/Lodestones/UnlockHover", 0, 50, 50, 24207);
		addHoverButton(24208, "Interfaces/Lodestones/Unlock", 5, 40, 40, "Burthorpe", 250, 24209, 5);
		addHoveredButton(24209, "Interfaces/Lodestones/UnlockHover", 5, 50, 50, 24210);
		addHoverButton(24211, "Interfaces/Lodestones/Unlock", 13, 40, 40, "Bandit Camp", 250, 24212, 5);
		addHoveredButton(24212, "Interfaces/Lodestones/UnlockHover", 13, 50, 50, 24213);
		addHoverButton(24214, "Interfaces/Lodestones/Unlock", 4, 40, 40, "Taverley", 250, 24215, 5);
		addHoveredButton(24215, "Interfaces/Lodestones/UnlockHover", 4, 50, 50, 24216);
		addHoverButton(24217, "Interfaces/Lodestones/Unlock", 12, 40, 40, "Al Kharid", 250, 24218, 5);
		addHoveredButton(24218, "Interfaces/Lodestones/UnlockHover", 12, 50, 50, 24219);
		addHoverButton(24220, "Interfaces/Lodestones/Unlock", 1, 40, 40, "Varrock", 250, 24221, 5);
		addHoveredButton(24221, "Interfaces/Lodestones/UnlockHover", 1, 50, 50, 24222);
		addHoverButton(24223, "Interfaces/Lodestones/Unlock", 11, 40, 40, "Edgeville", 250, 24224, 5);
		addHoveredButton(24224, "Interfaces/Lodestones/UnlockHover", 11, 50, 50, 24225);
		addHoverButton(24226, "Interfaces/Lodestones/Unlock", 2, 40, 40, "Falador", 250, 24227, 5);
		addHoveredButton(24227, "Interfaces/Lodestones/UnlockHover", 2, 50, 50, 24228);
		addHoverButton(24229, "Interfaces/Lodestones/Unlock", 8, 40, 40, "Port Sarim", 250, 24230, 5);
		addHoveredButton(24230, "Interfaces/Lodestones/UnlockHover", 8, 50, 50, 24231);
		addHoverButton(24232, "Interfaces/Lodestones/Unlock", 9, 40, 40, "Draynor Village", 250, 24233, 5);
		addHoveredButton(24233, "Interfaces/Lodestones/UnlockHover", 9, 50, 50, 24234);
		addHoverButton(24235, "Interfaces/Lodestones/Unlock", 3, 40, 40, "Ardougne", 250, 24236, 5);
		addHoveredButton(24236, "Interfaces/Lodestones/UnlockHover", 3, 50, 50, 24237);
		addHoverButton(24238, "Interfaces/Lodestones/Unlock", 6, 40, 40, "Catherby", 250, 24239, 5);
		addHoveredButton(24239, "Interfaces/Lodestones/UnlockHover", 6, 50, 50, 24240);
		addHoverButton(24241, "Interfaces/Lodestones/Unlock", 10, 40, 40, "Yanille", 250, 24242, 5);
		addHoveredButton(24242, "Interfaces/Lodestones/UnlockHover", 10, 50, 50, 24243);
		addHoverButton(24244, "Interfaces/Lodestones/Unlock", 7, 40, 40, "Seers' Village", 250, 24245, 5);
		addHoveredButton(24245, "Interfaces/Lodestones/UnlockHover", 7, 50, 50, 24246);
		addHoverButton(24247, "Interfaces/Lodestones/Close", 0, 26, 26, "Seers' Village", 250, 24248, 3);
		addHoveredButton(24248, "Interfaces/Lodestones/Close", 1, 26, 26, 24249);
		tab.totalChildren(33);
		tab.child(0, 24201, 6, 18);
		tab.child(1, 24202, 30, 53);
		tab.child(2, 24203, 25, 48);
		tab.child(3, 24205, 302, 217);
		tab.child(4, 24206, 297, 212);
		tab.child(5, 24208, 230, 120);
		tab.child(6, 24209, 225, 115);
		tab.child(7, 24211, 300, 270);
		tab.child(8, 24212, 295, 265);
		tab.child(9, 24214, 230, 155);
		tab.child(10, 24215, 225, 150);
		tab.child(11, 24217, 340, 200);
		tab.child(12, 24218, 335, 195);
		tab.child(13, 24220, 322, 162);
		tab.child(14, 24221, 317, 157);
		tab.child(15, 24223, 275, 130);
		tab.child(16, 24224, 270, 125);
		tab.child(17, 24226, 256, 181);
		tab.child(18, 24227, 251, 176);
		tab.child(19, 24229, 260, 235);
		tab.child(20, 24230, 255, 230);
		tab.child(21, 24232, 287, 182);
		tab.child(22, 24233, 282, 177);
		tab.child(23, 24235, 145, 185);
		tab.child(24, 24236, 140, 180);
		tab.child(25, 24238, 200, 157);
		tab.child(26, 24239, 195, 153);
		tab.child(27, 24241, 135, 240);
		tab.child(28, 24242, 130, 235);
		tab.child(29, 24244, 172, 135);
		tab.child(30, 24245, 167, 130);
		tab.child(31, 24247, 480, 21);
		tab.child(32, 24248, 480, 21);
	}

	public static void moneyBank(TextDrawingArea[] tda){
		RSInterface tab = addTabInterface(9050);
		addSprite(9051, 0, "Interfaces/Backgrounds/BACKGROUND");
		addHoverButton(9052, "Interfaces/Buttons/BUTTON", 0, 72, 36, "Deposit", -1, 9053, 1);
		addHoveredButton(9053, "Interfaces/Buttons/BUTTON", 1, 72, 36, 9054);
		addHoverButton(9055, "Interfaces/Buttons/BUTTON", 0, 72, 36, "Withdraw", -1, 9056, 1);
		addHoveredButton(9056, "Interfaces/Buttons/BUTTON", 1, 72, 36, 9057);
		addHoverButton(9058, "Interfaces/DataTables/TABLE", 0, 205, 20, "Balance", -1, 9057, 1);
		addText(9059, "Money Bank", tda, 3, 0xff981f, true, true);
		addHoverButton(9060, "Interfaces/Buttons/BUTTON", 0, 72, 36, "Transfer", -1, 9061, 1);
		addHoveredButton(9061, "Interfaces/Buttons/BUTTON", 1, 72, 36, 9057);
		addText(9062, "Deposit", tda, 0, 0xff981f, true, true);
		addText(9063, "Withdraw", tda, 0, 0xff981f, true, true);
		addText(9064, "Transfer", tda, 0, 0xff981f, true, true);
		addHover(9065, 3, 0, 19066, 2, "Interfaces/Buttons/BUTTON", 24, 23, "Close Window");
		addHovered(9066, 3, "Interfaces/Buttons/BUTTON", 24, 23, 19357);
		addText(9067, "0123456789 GP", tda, 1, 0xff981f, true, true);
		addText(9068, "~Destroscape~", tda, 2, 0xff981f, true, true);
		tab.totalChildren(16);
		tab.child(0,9051,4,20);
		tab.child(1,9059,255,26);
		tab.child(2,9058,158,200);
		tab.child(3,9052,153,164);
		tab.child(4,9053,153,164);
		tab.child(5,9055,225,164);
		tab.child(6,9056,225,164);
		tab.child(7,9060,297,164);
		tab.child(8,9061,297,164);
		tab.child(9,9062,189,177);
		tab.child(10,9063,261,177);
		tab.child(11,9064,333,177);
		tab.child(12,9065,470,25);
		tab.child(13,9066,470,25);
		tab.child(14,9067,258,203);
		tab.child(15,9068,255,305);
	}

	public static void bountyHunter(TextDrawingArea[] tda) {
		RSInterface tab = addInterface(25347);
		tab.totalChildren(5);

		addText(25349, "Target:", 0xffcccc, false, true, -1, tda, 1);
		addText(25350, "", 0xff6666, true, true, -1, tda, 2);
		addText(25351, "Pickup penalty:", 0xff3333, false, true, -1, tda, 1);
		addText(25352, "180 Sec", 0xff6666, true, true, -1, tda, 2);

		addTransparentSprite(25348, 0, "Interfaces/bounty/bh");

		tab.child(0,25348,332,3);
		tab.child(1,25349,340,13);
		tab.child(2,25350,452,13);
		tab.child(3,25351,340,34);
		tab.child(4,25352,470,34);
	}

	public static void newLamp(TextDrawingArea [] tda) {
		RSInterface rsinterface = addTabInterface(52000);
		addSprite(52001, 1, "Interfaces/Lamp/lamp");
		addButton(52002, 1, "Interfaces/Lamp/X", "Close");	
		addHoverButton(52003, "Interfaces/Lamp/lamp", 2, 74, 22, "Confirm", -1, 52004, 1);
		addHoveredButton(52004, "Interfaces/Lamp/lamp", 3, 74, 22, 52005);
		addButton(52006, 1, "Interfaces/Lamp/attack", "Select Attack");
		addButton(52007, 1, "Interfaces/Lamp/str", "Select Strength");
		addButton(52008, 1, "Interfaces/Lamp/range", "Select Ranged");
		addButton(52009, 1, "Interfaces/Lamp/mage", "Select Magic");
		addButton(52010, 1, "Interfaces/Lamp/def", "Select Defence");
		addButton(52011, 1, "Interfaces/Lamp/craft", "Select Crafting");
		addButton(52012, 1, "Interfaces/Lamp/hp", "Select Hitpoints");
		addButton(52013, 1, "Interfaces/Lamp/pray", "Select Prayer");
		addButton(52014, 1, "Interfaces/Lamp/agility", "Select Agility");
		addButton(52015, 1, "Interfaces/Lamp/herb", "Select Herblore");
		addButton(52016, 1, "Interfaces/Lamp/thiev", "Select Thieving");
		addButton(52017, 1, "Interfaces/Lamp/fish", "Select Fishing");
		addButton(52018, 1, "Interfaces/Lamp/rc", "Select Runecraft");
		addButton(52019, 1, "Interfaces/Lamp/slay", "Select Slayer");
		addButton(52020, 1, "Interfaces/Lamp/farm", "Select Farming");
		addButton(52021, 1, "Interfaces/Lamp/mine", "Select Mining");
		addButton(52022, 1, "Interfaces/Lamp/smith", "Select Smithing");
		addButton(52023, 1, "Interfaces/Lamp/hunt", "Select Hunter");
		addButton(52024, 1, "Interfaces/Lamp/cook", "Select Cooking");
		addButton(52025, 1, "Interfaces/Lamp/fm", "Select Firemaking");
		addButton(52026, 1, "Interfaces/Lamp/wc", "Select Woodcutting");
		addButton(52027, 1, "Interfaces/Lamp/fletch", "Select Fletching");
		addButton(52028, 1, "Interfaces/Lamp/con", "Select Construction");
		addButton(52029, 1, "Interfaces/Lamp/summ", "Select Summoning");
		addButton(52030, 1, "Interfaces/Lamp/dung", "Select Dungeoneering");
		rsinterface.totalChildren(29);
		rsinterface.child(0, 52001, 0, 0);
		rsinterface.child(1, 52002, 431, 11);
		rsinterface.child(2, 52003, 352, 237);
		rsinterface.child(3, 52004, 352, 237);
		rsinterface.child(4, 52006, 60, 60);
		rsinterface.child(5, 52007, 119, 60);
		rsinterface.child(6, 52008, 172, 60);
		rsinterface.child(7, 52009, 230, 60);
		rsinterface.child(8, 52010, 288, 60);
		rsinterface.child(9, 52011, 328, 60);
		rsinterface.child(10, 52012, 380, 60);
		rsinterface.child(11, 52013, 55, 115);
		rsinterface.child(12, 52014, 113, 115);
		rsinterface.child(13, 52015, 168, 115);
		rsinterface.child(14, 52016, 228, 124);
		rsinterface.child(15, 52017, 281, 115);
		rsinterface.child(16, 52018, 330, 115);
		rsinterface.child(17, 52019, 383, 115);
		rsinterface.child(18, 52020, 55, 171);
		rsinterface.child(19, 52021, 113, 171);
		rsinterface.child(20, 52022, 168, 174);
		rsinterface.child(21, 52023, 228, 171);
		rsinterface.child(22, 52024, 281, 171);
		rsinterface.child(23, 52025, 330, 171);
		rsinterface.child(24, 52026, 383, 171);	
		rsinterface.child(25, 52027, 113, 227);
		rsinterface.child(26, 52028, 168, 227);
		rsinterface.child(27, 52029, 228, 228);
		rsinterface.child(28, 52030, 281, 227);			
	}

	public static void newTrade(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(3323);
		setChildren(19, Interface);
		addSprite(3324, 6, "Interfaces/Trade/TRADE");
		addHover(3442, 3, 0, 3325, 1, "Interfaces/Bank/BANK", 17, 17,
				"Close Window");
		addHovered(3325, 2, "Interfaces/Bank/BANK", 17, 17, 3326);
		addText(3417, "Trading With:", 0xFF981F, true, true, 52, TDA, 2);
		addText(3418, "Trader's Offer", 0xFF981F, false, true, 52, TDA, 1);
		addText(3419, "Your Offer", 0xFF981F, false, true, 52, TDA, 1);
		addText(3421, "Accept", 0x00C000, true, true, 52, TDA, 1);
		addText(3423, "Decline", 0xC00000, true, true, 52, TDA, 1);

		addText(3431, "Waiting For Other Player", 0xFFFFFF, true, true, 52,
				TDA, 1);
		addText(23504,
				"Wealth transfer: 2147,000,000 coins' worth to Zezimablud12",
				0xB9B855, true, true, -1, TDA, 0);
		addText(23505, "1 has\\n 28 free\\n inventory slots.", 0xFF981F, true,
				true, -1, TDA, 0);

		addText(23506,
				"Wealth transfer: 2147,000,000 coins' worth to Zezimablud12",
				0xB9B855, false, true, -1, TDA, 0);
		addText(23507, "Wealth transfer: 2147,000,000 coins' worth to me",
				0xB9B855, false, true, -1, TDA, 0);

		addHover(3420, 1, 0, 3327, 5, "Interfaces/Trade/TRADE", 65, 32,
				"Accept");
		addHovered(3327, 2, "Interfaces/Trade/TRADE", 65, 32, 3328);
		addHover(3422, 3, 0, 3329, 5, "Interfaces/Trade/TRADE", 65, 32,
				"Decline");
		addHovered(3329, 2, "Interfaces/Trade/TRADE", 65, 32, 3330);
		setBounds(3324, 0, 16, 0, Interface);
		setBounds(3442, 485, 24, 1, Interface);
		setBounds(3325, 485, 24, 2, Interface);
		setBounds(3417, 258, 25, 3, Interface);
		setBounds(3418, 355, 51, 4, Interface);
		setBounds(3419, 68, 51, 5, Interface);
		setBounds(3420, 223, 120, 6, Interface);
		setBounds(3327, 223, 120, 7, Interface);
		setBounds(3422, 223, 160, 8, Interface);
		setBounds(3329, 223, 160, 9, Interface);
		setBounds(3421, 256, 127, 10, Interface);
		setBounds(3423, 256, 167, 11, Interface);
		setBounds(3431, 256, 272, 12, Interface);
		setBounds(3415, 12, 64, 13, Interface);
		setBounds(3416, 321, 67, 14, Interface);

		setBounds(23505, 256, 67, 16, Interface);

		setBounds(23504, 255, 310, 15, Interface);
		setBounds(23506, 20, 310, 17, Interface);
		setBounds(23507, 380, 310, 18, Interface);

		Interface = addInterface(3443);
		setChildren(15, Interface);
		addSprite(3444, 3, "Interfaces/Trade/TRADE");
		addButton(3546, 2, "Interfaces/Trade/SHOP", 63, 24, "Accept", 1);
		addButton(3548, 2, "Interfaces/Trade/SHOP", 63, 24, "Decline", 3);
		addText(3547, "Accept", 0x00C000, true, true, 52, TDA, 1);
		addText(3549, "Decline", 0xC00000, true, true, 52, TDA, 1);
		addText(3450, "Trading With:", 0x00FFFF, true, true, 52, TDA, 2);
		addText(3451, "Yourself", 0x00FFFF, true, true, 52, TDA, 2);
		setBounds(3444, 12, 20, 0, Interface);
		setBounds(3442, 470, 32, 1, Interface);
		setBounds(3325, 470, 32, 2, Interface);
		setBounds(3535, 130, 28, 3, Interface);
		setBounds(3536, 105, 47, 4, Interface);
		setBounds(3546, 189, 295, 5, Interface);
		setBounds(3548, 258, 295, 6, Interface);
		setBounds(3547, 220, 299, 7, Interface);
		setBounds(3549, 288, 299, 8, Interface);
		setBounds(3557, 71, 87, 9, Interface);
		setBounds(3558, 315, 87, 10, Interface);
		setBounds(3533, 64, 70, 11, Interface);
		setBounds(3534, 297, 70, 12, Interface);
		setBounds(3450, 95, 289, 13, Interface);
		setBounds(3451, 95, 304, 14, Interface);
	}

	public static void skilllevel(TextDrawingArea[] tda) {
		@SuppressWarnings("unused")
		RSInterface text = interfaceCache[7202];
		RSInterface attack = interfaceCache[6247];
		RSInterface defence = interfaceCache[6253];
		RSInterface str = interfaceCache[6206];
		RSInterface hits = interfaceCache[6216];
		RSInterface rng = interfaceCache[4443];
		RSInterface pray = interfaceCache[6242];
		RSInterface mage = interfaceCache[6211];
		RSInterface cook = interfaceCache[6226];
		RSInterface wood = interfaceCache[4272];
		RSInterface flet = interfaceCache[6231];
		RSInterface fish = interfaceCache[6258];
		RSInterface fire = interfaceCache[4282];
		RSInterface craf = interfaceCache[6263];
		RSInterface smit = interfaceCache[6221];
		RSInterface mine = interfaceCache[4416];
		RSInterface herb = interfaceCache[6237];
		RSInterface agil = interfaceCache[4277];
		RSInterface thie = interfaceCache[4261];
		RSInterface slay = interfaceCache[12122];
		RSInterface farm = interfaceCache[5267];
		RSInterface rune = interfaceCache[4267];
		RSInterface cons = interfaceCache[7267];
		RSInterface hunt = interfaceCache[8267];
		RSInterface summ = addInterface(9267);
		RSInterface dung = addInterface(10267);
		addSprite(17878, 0, "Interfaces/skillchat/skill");
		addSprite(17879, 1, "Interfaces/skillchat/skill");
		addSprite(17880, 2, "Interfaces/skillchat/skill");
		addSprite(17881, 3, "Interfaces/skillchat/skill");
		addSprite(17882, 4, "Interfaces/skillchat/skill");
		addSprite(17883, 5, "Interfaces/skillchat/skill");
		addSprite(17884, 6, "Interfaces/skillchat/skill");
		addSprite(17885, 7, "Interfaces/skillchat/skill");
		addSprite(17886, 8, "Interfaces/skillchat/skill");
		addSprite(17887, 9, "Interfaces/skillchat/skill");
		addSprite(17888, 10, "Interfaces/skillchat/skill");
		addSprite(17889, 11, "Interfaces/skillchat/skill");
		addSprite(17890, 12, "Interfaces/skillchat/skill");
		addSprite(17891, 13, "Interfaces/skillchat/skill");
		addSprite(17892, 14, "Interfaces/skillchat/skill");
		addSprite(17893, 15, "Interfaces/skillchat/skill");
		addSprite(17894, 16, "Interfaces/skillchat/skill");
		addSprite(17895, 17, "Interfaces/skillchat/skill");
		addSprite(17896, 18, "Interfaces/skillchat/skill");
		addSprite(11897, 19, "Interfaces/skillchat/skill");
		addSprite(17898, 20, "Interfaces/skillchat/skill");
		addSprite(17899, 21, "Interfaces/skillchat/skill");
		addSprite(17900, 22, "Interfaces/skillchat/skill");
		addSprite(17901, 23, "Interfaces/skillchat/skill");
		addSprite(17902, 24, "Interfaces/skillchat/skill");
		setChildren(4, attack);
		setBounds(17878, 20, 30, 0, attack);
		setBounds(4268, 80, 15, 1, attack);
		setBounds(4269, 80, 45, 2, attack);
		setBounds(358, 95, 75, 3, attack);
		setChildren(4, defence);
		setBounds(17879, 20, 30, 0, defence);
		setBounds(4268, 80, 15, 1, defence);
		setBounds(4269, 80, 45, 2, defence);
		setBounds(358, 95, 75, 3, defence);
		setChildren(4, str);
		setBounds(17880, 20, 30, 0, str);
		setBounds(4268, 80, 15, 1, str);
		setBounds(4269, 80, 45, 2, str);
		setBounds(358, 95, 75, 3, str);
		setChildren(4, hits);
		setBounds(17881, 20, 30, 0, hits);
		setBounds(4268, 80, 15, 1, hits);
		setBounds(4269, 80, 45, 2, hits);
		setBounds(358, 95, 75, 3, hits);
		setChildren(4, rng);
		setBounds(17882, 20, 30, 0, rng);
		setBounds(4268, 80, 15, 1, rng);
		setBounds(4269, 80, 45, 2, rng);
		setBounds(358, 95, 75, 3, rng);
		setChildren(4, pray);
		setBounds(17883, 20, 30, 0, pray);
		setBounds(4268, 80, 15, 1, pray);
		setBounds(4269, 80, 45, 2, pray);
		setBounds(358, 95, 75, 3, pray);
		setChildren(4, mage);
		setBounds(17884, 20, 30, 0, mage);
		setBounds(4268, 80, 15, 1, mage);
		setBounds(4269, 80, 45, 2, mage);
		setBounds(358, 95, 75, 3, mage);
		setChildren(4, cook);
		setBounds(17885, 20, 30, 0, cook);
		setBounds(4268, 80, 15, 1, cook);
		setBounds(4269, 80, 45, 2, cook);
		setBounds(358, 95, 75, 3, cook);
		setChildren(4, wood);
		setBounds(17886, 20, 30, 0, wood);
		setBounds(4268, 80, 15, 1, wood);
		setBounds(4269, 80, 45, 2, wood);
		setBounds(358, 95, 75, 3, wood);
		setChildren(4, flet);
		setBounds(17887, 20, 30, 0, flet);
		setBounds(4268, 80, 15, 1, flet);
		setBounds(4269, 80, 45, 2, flet);
		setBounds(358, 95, 75, 3, flet);
		setChildren(4, fish);
		setBounds(17888, 20, 30, 0, fish);
		setBounds(4268, 80, 15, 1, fish);
		setBounds(4269, 80, 45, 2, fish);
		setBounds(358, 95, 75, 3, fish);
		setChildren(4, fire);
		setBounds(17889, 20, 30, 0, fire);
		setBounds(4268, 80, 15, 1, fire);
		setBounds(4269, 80, 45, 2, fire);
		setBounds(358, 95, 75, 3, fire);
		setChildren(4, craf);
		setBounds(17890, 20, 30, 0, craf);
		setBounds(4268, 80, 15, 1, craf);
		setBounds(4269, 80, 45, 2, craf);
		setBounds(358, 95, 75, 3, craf);
		setChildren(4, smit);
		setBounds(17891, 20, 30, 0, smit);
		setBounds(4268, 80, 15, 1, smit);
		setBounds(4269, 80, 45, 2, smit);
		setBounds(358, 95, 75, 3, smit);
		setChildren(4, mine);
		setBounds(17892, 20, 30, 0, mine);
		setBounds(4268, 80, 15, 1, mine);
		setBounds(4269, 80, 45, 2, mine);
		setBounds(358, 95, 75, 3, mine);
		setChildren(4, herb);
		setBounds(17893, 20, 30, 0, herb);
		setBounds(4268, 80, 15, 1, herb);
		setBounds(4269, 80, 45, 2, herb);
		setBounds(358, 95, 75, 3, herb);
		setChildren(4, agil);
		setBounds(17894, 20, 30, 0, agil);
		setBounds(4268, 80, 15, 1, agil);
		setBounds(4269, 80, 45, 2, agil);
		setBounds(358, 95, 75, 3, agil);
		setChildren(4, thie);
		setBounds(17895, 20, 30, 0, thie);
		setBounds(4268, 80, 15, 1, thie);
		setBounds(4269, 80, 45, 2, thie);
		setBounds(358, 95, 75, 3, thie);
		setChildren(4, slay);
		setBounds(17896, 20, 30, 0, slay);
		setBounds(4268, 80, 15, 1, slay);
		setBounds(4269, 80, 45, 2, slay);
		setBounds(358, 95, 75, 3, slay);
		setChildren(3, farm);
		setBounds(4268, 80, 15, 0, farm);
		setBounds(4269, 80, 45, 1, farm);
		setBounds(358, 95, 75, 2, farm);
		setChildren(4, rune);
		setBounds(17898, 20, 30, 0, rune);
		setBounds(4268, 80, 15, 1, rune);
		setBounds(4269, 80, 45, 2, rune);
		setBounds(358, 95, 75, 3, rune);
		setChildren(3, cons);
		setBounds(4268, 80, 15, 0, cons);
		setBounds(4269, 80, 45, 1, cons);
		setBounds(358, 95, 75, 2, cons);
		setChildren(3, hunt);
		setBounds(4268, 80, 15, 0, hunt);
		setBounds(4269, 80, 45, 1, hunt);
		setBounds(358, 95, 75, 2, hunt);
		setChildren(4, summ);
		setBounds(17901, 20, 30, 0, summ);
		setBounds(4268, 80, 15, 1, summ);
		setBounds(4269, 80, 45, 2, summ);
		setBounds(358, 95, 75, 3, summ);
		setChildren(4, dung);
		setBounds(17902, 20, 30, 0, dung);
		setBounds(4268, 80, 15, 1, dung);
		setBounds(4269, 80, 45, 2, dung);
		setBounds(358, 95, 75, 3, dung);
	}

	public static void capeCustomiser(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(29000); // Interface ID
		// Background here
		addSprite(29001, 1, "Interfaces/Customiser/BACKGROUND");

		// Buttons Begin
		addHoverButton(29002, "Interfaces/Customiser/SPRITE", 1, 144, 85,
				"Customise", -1, 29002, 1);
		addHoverButton(29003, "Interfaces/Customiser/SPRITE", 1, 82, 76,
				"Close", -1, 29003, 1);
		// Buttons End

		// Set Bounds Begin
		setChildren(3, Interface); // Number of sprites/buttons
		setBounds(29001, 10, 20, 0, Interface);
		setBounds(29002, 121, 133, 1, Interface);
		setBounds(29003, 477, 10, 2, Interface);
		// Set Bounds End
	}

	public static void congrats(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(23000); // Interface ID
		// Background here
		addSprite(23001, 1, "Interfaces/Congrats/BACKGROUND");
		// Buttons Begin
		addHoverButton(23002, "Interfaces/Congrats/SPRITE", 1, 132, 27,
				"Continue", -1, 23002, 1);
		addText(23003, "", TDA, 2, 0x000000, true, false);

		addText(23004, "", TDA, 2, 0x000000, true, false);
		addText(23005, "", TDA, 2, 0x000000, true, false);
		addText(23006, "", TDA, 2, 0x000000, true, false);
		addText(23007, "", TDA, 2, 0x000000, true, false);

		// Buttons End

		// Set Bounds Begin
		setChildren(7, Interface); // Number of sprites/buttons
		setBounds(23001, 1, 1, 0, Interface);
		setBounds(23002, 191, 269, 1, Interface);
		setBounds(23003, 261, 119, 2, Interface);
		setBounds(23004, 261, 164, 3, Interface);
		setBounds(23005, 261, 180, 4, Interface);
		setBounds(23006, 261, 196, 5, Interface);
		setBounds(23007, 261, 212, 6, Interface);
		// Set Bounds End
	}

	public static void monsterTele(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(22400); // Interface ID

		// Background here
		addSprite(22401, 1, "Interfaces/MonsterTele/BG");

		// Buttons Begin
		addHoverButton(22402, "Interfaces/MonsterTele/SPRITE", 1, 82, 76,
				"Close", -1, 22402, 1);

		addHoverButton(22403, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22403, 1);
		addHoverButton(22404, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22404, 1);
		addHoverButton(22405, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22405, 1);
		addHoverButton(22406, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22406, 1);
		addHoverButton(22407, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22407, 1);
		addHoverButton(22408, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22408, 1);
		addHoverButton(22409, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22409, 1);
		addHoverButton(22410, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22410, 1);
		addHoverButton(22411, "Interfaces/MonsterTele/SPRITE", 1, 158, 22,
				"Teleport", -1, 22411, 1);

		addHoverButton(22412, "Interfaces/MonsterTele/SPRITE", 1, 148, 21,
				"Teleport", -1, 22412, 1);
		addHoverButton(22413, "Interfaces/MonsterTele/SPRITE", 1, 148, 21,
				"Teleport", -1, 22413, 1);
		addHoverButton(22414, "Interfaces/MonsterTele/SPRITE", 1, 148, 21,
				"Teleport", -1, 22414, 1);
		addHoverButton(22415, "Interfaces/MonsterTele/SPRITE", 1, 148, 21,
				"Teleport", -1, 22415, 1);
		addHoverButton(22416, "Interfaces/MonsterTele/SPRITE", 1, 148, 21,
				"Teleport", -1, 22416, 1);
		addHoverButton(22417, "Interfaces/MonsterTele/SPRITE", 1, 148, 21,
				"Teleport", -1, 22417, 1);
		addHoverButton(22418, "Interfaces/MonsterTele/SPRITE", 1, 148, 21,
				"Teleport", -1, 22418, 1);
		addHoverButton(22419, "Interfaces/MonsterTele/SPRITE", 1, 148, 21,
				"Teleport", -1, 22419, 1);

		addHoverButton(22420, "Interfaces/MonsterTele/SPRITE", 1, 162, 22,
				"Teleport", -1, 22420, 1);
		addHoverButton(22421, "Interfaces/MonsterTele/SPRITE", 1, 162, 22,
				"Teleport", -1, 22421, 1);
		addHoverButton(22422, "Interfaces/MonsterTele/SPRITE", 1, 162, 22,
				"Teleport", -1, 22422, 1);
		addHoverButton(22423, "Interfaces/MonsterTele/SPRITE", 1, 162, 22,
				"Teleport", -1, 22423, 1);
		addHoverButton(22424, "Interfaces/MonsterTele/SPRITE", 1, 162, 22,
				"Teleport", -1, 22424, 1);
		addHoverButton(22425, "Interfaces/MonsterTele/SPRITE", 1, 162, 22,
				"Teleport", -1, 22425, 1);
		addHoverButton(22426, "Interfaces/MonsterTele/SPRITE", 1, 162, 22,
				"Teleport", -1, 22426, 1);
		addHoverButton(22427, "Interfaces/MonsterTele/SPRITE", 1, 162, 22,
				"Teleport", -1, 22427, 1);


		addText(22428, "Test", TDA, 2, 0xffffff, true, true);
		addText(22429, "Test", TDA, 2, 0xffffff, true, true);
		addText(22430, "Test", TDA, 2, 0xffffff, true, true);
		addText(22431, "Test", TDA, 2, 0xffffff, true, true);
		addText(22432, "Test", TDA, 2, 0xffffff, true, true);
		addText(22433, "Test", TDA, 2, 0xffffff, true, true);
		addText(22434, "Test", TDA, 2, 0xffffff, true, true);
		addText(22435, "Test", TDA, 2, 0xffffff, true, true);
		addText(22436, "Test", TDA, 2, 0xffffff, true, true);

		addText(22437, "Test", TDA, 2, 0xffffff, true, true);
		addText(22438, "Test", TDA, 2, 0xffffff, true, true);
		addText(22439, "Test", TDA, 2, 0xffffff, true, true);
		addText(22440, "Test", TDA, 2, 0xffffff, true, true);
		addText(22441, "Test", TDA, 2, 0xffffff, true, true);
		addText(22442, "Test", TDA, 2, 0xffffff, true, true);
		addText(22443, "Test", TDA, 2, 0xffffff, true, true);
		addText(22444, "Test", TDA, 2, 0xffffff, true, true);

		addText(22445, "Test", TDA, 2, 0xffffff, true, true);
		addText(22446, "Test", TDA, 2, 0xffffff, true, true);
		addText(22447, "Test", TDA, 2, 0xffffff, true, true);
		addText(22448, "Test", TDA, 2, 0xffffff, true, true);
		addText(22449, "Test", TDA, 2, 0xffffff, true, true);
		addText(22450, "Test", TDA, 2, 0xffffff, true, true);
		addText(22451, "Test", TDA, 2, 0xffffff, true, true);
		addText(22452, "Test", TDA, 2, 0xffffff, true, true);

		//addChar(22455);

		// Buttons End
		// Set Bounds Begin
		setChildren(52, Interface); // Number of sprites/buttons

		setBounds(22401, 0, 0, 0, Interface);
		setBounds(22402, 477, 10, 1, Interface);
		// +27
		setBounds(22403, 12, 85, 2, Interface);
		setBounds(22404, 12, 112, 3, Interface);
		setBounds(22405, 12, 139, 4, Interface);
		setBounds(22406, 13, 166, 5, Interface);
		setBounds(22407, 12, 193, 6, Interface);
		setBounds(22408, 12, 220, 7, Interface);
		setBounds(22409, 12, 247, 8, Interface);
		setBounds(22410, 12, 274, 9, Interface);
		setBounds(22411, 12, 301, 10, Interface);

		setBounds(22412, 179, 85, 11, Interface);
		setBounds(22413, 179, 112, 12, Interface);
		setBounds(22414, 179, 139, 13, Interface);
		setBounds(22415, 179, 166, 14, Interface);
		setBounds(22416, 179, 193, 15, Interface);
		setBounds(22417, 179, 220, 16, Interface);
		setBounds(22418, 179, 247, 17, Interface);
		setBounds(22419, 179, 274, 18, Interface);

		setBounds(22420, 336, 85, 19, Interface);
		setBounds(22421, 336, 112, 20, Interface);
		setBounds(22422, 336, 139, 21, Interface);
		setBounds(22423, 336, 166, 22, Interface);
		setBounds(22424, 336, 193, 23, Interface);
		setBounds(22425, 336, 220, 24, Interface);
		setBounds(22426, 336, 247, 25, Interface);
		setBounds(22427, 336, 274, 26, Interface);
		// +27
		setBounds(22428, 93, 84, 27, Interface);
		setBounds(22429, 93, 111, 28, Interface);
		setBounds(22430, 93, 138, 29, Interface);
		setBounds(22431, 93, 165, 30, Interface);
		setBounds(22432, 93, 192, 31, Interface);
		setBounds(22433, 93, 219, 32, Interface);
		setBounds(22434, 93, 246, 33, Interface);
		setBounds(22435, 93, 273, 34, Interface);
		setBounds(22436, 93, 300, 35, Interface);

		setBounds(22437, 257, 84, 36, Interface);
		setBounds(22438, 257, 111, 37, Interface);
		setBounds(22439, 257, 138, 38, Interface);
		setBounds(22440, 257, 165, 39, Interface);
		setBounds(22441, 257, 192, 40, Interface);
		setBounds(22442, 257, 219, 41, Interface);
		setBounds(22443, 257, 246, 42, Interface);
		setBounds(22444, 257, 273, 43, Interface);

		setBounds(22445, 424, 84, 44, Interface);
		setBounds(22446, 424, 111, 45, Interface);
		setBounds(22447, 424, 138, 46, Interface);
		setBounds(22448, 424, 165, 47, Interface);
		setBounds(22449, 424, 192, 48, Interface);
		setBounds(22450, 424, 219, 49, Interface);
		setBounds(22451, 424, 246, 50, Interface);
		setBounds(22452, 424, 273, 51, Interface);
		//setBounds(22455, 184, 276, 52, Interface);
		// Set Bounds End
	}

	public static void capeColor(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(14000); // Interface ID
		// Background here
		addSprite(14001, 2, "Interfaces/Customiser/BACKGROUND");
		// Buttons Begin
		addHoverButton(14002, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14002, 1);
		addHoverButton(14003, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14003, 1);
		addHoverButton(14004, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14004, 1);
		addHoverButton(14005, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14005, 1);
		addHoverButton(14006, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14006, 1);
		addHoverButton(14007, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14007, 1);
		addHoverButton(14008, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14008, 1);
		addHoverButton(14009, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14009, 1);
		addHoverButton(14010, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14010, 1);
		addHoverButton(14011, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Choose color", -1, 14011, 1);
		addHoverButton(14012, "Interfaces/Customiser/SPRITE", 1, 21, 22,
				"Close", -1, 14012, 1);

		// Buttons End

		// Set Bounds Begin
		setChildren(12, Interface); // Number of sprites/buttons
		setBounds(14001, 109, 97, 0, Interface);
		setBounds(14002, 155, 161, 1, Interface);
		setBounds(14003, 183, 161, 2, Interface);
		setBounds(14004, 213, 161, 3, Interface);
		setBounds(14005, 242, 161, 4, Interface);
		setBounds(14006, 271, 161, 5, Interface);
		setBounds(14007, 300, 161, 6, Interface);
		setBounds(14008, 329, 161, 7, Interface);
		setBounds(14009, 358, 161, 8, Interface);
		setBounds(14010, 155, 195, 9, Interface);
		setBounds(14011, 183, 195, 10, Interface);
		setBounds(14012, 398, 98, 11, Interface);
		// Set Bounds End
	}

	public static void runecraftingTele(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(27000); // Interface ID
		// Background here
		addSprite(27001, 1, "Interfaces/RunecraftingTele/BACKGROUND");

		// Buttons Begin
		addHoverButton(27002, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Air Altar", -1, 27002, 1);
		addHoverButton(27003, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Mind Altar", -1, 27003, 1);
		addHoverButton(27004, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Water Altar", -1, 27004, 1);
		addHoverButton(27005, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Earth Altar", -1, 27005, 1);
		addHoverButton(27006, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Fire Altar", -1, 27006, 1);
		addHoverButton(27007, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Cosmic Altar", -1, 27007, 1);
		addHoverButton(27008, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Chaos Altar", -1, 27008, 1);
		addHoverButton(27009, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Astral Altar", -1, 27009, 1);
		addHoverButton(27010, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Nature Altar", -1, 27010, 1);
		addHoverButton(27011, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Law Altar", -1, 27011, 1);
		addHoverButton(27012, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Death Altar", -1, 27012, 1);
		addHoverButton(27013, "Interfaces/RunecraftingTele/SPRITE", 1, 82, 76,
				"Teleport to @gre@Blood Altar", -1, 27013, 1);
		addHoverButton(27014, "Interfaces/RunecraftingTele/CLOSE", 1, 25, 25,
				"Close", -1, 27014, 1);
		// Buttons End

		// Set Bounds Begin
		setChildren(14, Interface); // Number of sprites/buttons
		setBounds(27001, 7, 6, 0, Interface);
		setBounds(27002, 45, 61, 1, Interface); // 105122 - Action Button IDs
		setBounds(27003, 155, 61, 2, Interface); // 105123
		setBounds(27004, 265, 61, 3, Interface); // 105124
		setBounds(27005, 388, 61, 4, Interface); // 105125
		setBounds(27006, 40, 145, 5, Interface); // 105126
		setBounds(27007, 158, 145, 6, Interface);// 105127
		setBounds(27008, 266, 145, 7, Interface); // 105128
		setBounds(27009, 385, 145, 8, Interface); // 105129
		setBounds(27010, 38, 227, 9, Interface);// 105130
		setBounds(27011, 158, 227, 10, Interface); // 105131
		setBounds(27012, 265, 227, 11, Interface); // 105132
		setBounds(27013, 385, 227, 12, Interface);// 105133
		setBounds(27014, 477, 10, 13, Interface); // 105134
		// Set Bounds End
	}

	public static void itemsOnDeathDATA(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(17115);
		addText(17109, "", 0xff981f, false, false, 0, tda, 0);
		addText(17110, "The normal amount of", 0xff981f, false, false, 0, tda,
				0);
		addText(17111, "items kept is three.", 0xff981f, false, false, 0, tda,
				0);
		addText(17112, "", 0xff981f, false, false, 0, tda, 0);
		addText(17113, "If you are skulled,", 0xff981f, false, false, 0, tda, 0);
		addText(17114, "you will lose all your", 0xff981f, false, false, 0,
				tda, 0);
		addText(17117, "items, unless an item", 0xff981f, false, false, 0, tda,
				0);
		addText(17118, "protecting prayer is", 0xff981f, false, false, 0, tda,
				0);
		addText(17119, "used.", 0xff981f, false, false, 0, tda, 0);
		addText(17120, "", 0xff981f, false, false, 0, tda, 0);
		addText(17121, "Item protecting prayers", 0xff981f, false, false, 0,
				tda, 0);
		addText(17122, "will allow you to keep", 0xff981f, false, false, 0,
				tda, 0);
		addText(17123, "one extra item.", 0xff981f, false, false, 0, tda, 0);
		addText(17124, "", 0xff981f, false, false, 0, tda, 0);
		addText(17125, "The items kept are", 0xff981f, false, false, 0, tda, 0);
		addText(17126, "selected by the server", 0xff981f, false, false, 0,
				tda, 0);
		addText(17127, "and include the most", 0xff981f, false, false, 0, tda,
				0);
		addText(17128, "expensive items you're", 0xff981f, false, false, 0,
				tda, 0);
		addText(17129, "carrying.", 0xff981f, false, false, 0, tda, 0);
		addText(17130, "", 0xff981f, false, false, 0, tda, 0);
		RSinterface.parentID = 17115;
		RSinterface.id = 17115;
		RSinterface.type = 0;
		RSinterface.atActionType = 0;
		RSinterface.contentType = 0;
		RSinterface.width = 130;
		RSinterface.height = 197;
		RSinterface.opacity = 0;
		RSinterface.hoverType = -1;
		RSinterface.scrollMax = 280;
		RSinterface.children = new int[20];
		RSinterface.childX = new int[20];
		RSinterface.childY = new int[20];
		RSinterface.children[0] = 17109;
		RSinterface.childX[0] = 0;
		RSinterface.childY[0] = 0;
		RSinterface.children[1] = 17110;
		RSinterface.childX[1] = 0;
		RSinterface.childY[1] = 12;
		RSinterface.children[2] = 17111;
		RSinterface.childX[2] = 0;
		RSinterface.childY[2] = 24;
		RSinterface.children[3] = 17112;
		RSinterface.childX[3] = 0;
		RSinterface.childY[3] = 36;
		RSinterface.children[4] = 17113;
		RSinterface.childX[4] = 0;
		RSinterface.childY[4] = 48;
		RSinterface.children[5] = 17114;
		RSinterface.childX[5] = 0;
		RSinterface.childY[5] = 60;
		RSinterface.children[6] = 17117;
		RSinterface.childX[6] = 0;
		RSinterface.childY[6] = 72;
		RSinterface.children[7] = 17118;
		RSinterface.childX[7] = 0;
		RSinterface.childY[7] = 84;
		RSinterface.children[8] = 17119;
		RSinterface.childX[8] = 0;
		RSinterface.childY[8] = 96;
		RSinterface.children[9] = 17120;
		RSinterface.childX[9] = 0;
		RSinterface.childY[9] = 108;
		RSinterface.children[10] = 17121;
		RSinterface.childX[10] = 0;
		RSinterface.childY[10] = 120;
		RSinterface.children[11] = 17122;
		RSinterface.childX[11] = 0;
		RSinterface.childY[11] = 132;
		RSinterface.children[12] = 17123;
		RSinterface.childX[12] = 0;
		RSinterface.childY[12] = 144;
		RSinterface.children[13] = 17124;
		RSinterface.childX[13] = 0;
		RSinterface.childY[13] = 156;
		RSinterface.children[14] = 17125;
		RSinterface.childX[14] = 0;
		RSinterface.childY[14] = 168;
		RSinterface.children[15] = 17126;
		RSinterface.childX[15] = 0;
		RSinterface.childY[15] = 180;
		RSinterface.children[16] = 17127;
		RSinterface.childX[16] = 0;
		RSinterface.childY[16] = 192;
		RSinterface.children[17] = 17128;
		RSinterface.childX[17] = 0;
		RSinterface.childY[17] = 204;
		RSinterface.children[18] = 17129;
		RSinterface.childX[18] = 0;
		RSinterface.childY[18] = 216;
		RSinterface.children[19] = 17130;
		RSinterface.childX[19] = 0;
		RSinterface.childY[19] = 228;
	}

	public static void itemsKeptOnDeath(TextDrawingArea[] tda) {
		RSInterface Interface = addInterface(22030);
		addSprite(22031, 1, "Interfaces/Death/SPRITE");
		addHoverButton(22032, "Interfaces/Death/SPRITE", 2, 17, 17, "Close",
				250, 22033, 3);
		addHoveredButton(22033, "Interfaces/Death/SPRITE", 3, 17, 17, 22034);
		addText(22035, "22035", tda, 0, 0xff981f, false, true);
		addText(22036, "22036", tda, 0, 0xff981f, false, true);
		addText(22037, "22037", tda, 0, 0xff981f, false, true);
		addText(22038, "22038", tda, 0, 0xff981f, false, true);
		addText(22039, "22039", tda, 0, 0xff981f, false, true);
		addText(22040, "22040", tda, 1, 0xffcc33, false, true);
		setChildren(9, Interface);
		setBounds(22031, 7, 8, 0, Interface);
		setBounds(22032, 480, 18, 1, Interface);
		setBounds(22033, 480, 18, 2, Interface);
		setBounds(22035, 348, 98, 3, Interface);
		setBounds(22036, 348, 110, 4, Interface);
		setBounds(22037, 348, 122, 5, Interface);
		setBounds(22038, 348, 134, 6, Interface);
		setBounds(22039, 348, 146, 7, Interface);
		setBounds(22040, 398, 297, 8, Interface);
	}

	public static void itemsOnDeath(TextDrawingArea[] tda) {
		RSInterface rsinterface = addInterface(17100);
		addSprite(17101, 2, "Interfaces/Equipment/SPRITE");
		addHover(17102, 3, 0, 10601, 1, "Interfaces/Equipment/SPRITE", 17, 17,
				"Close Window");
		addHovered(10601, 3, "Interfaces/Equipment/SPRITE", 17, 17, 10602);
		addText(17103, "Items Kept On Death", 0xff981f, false, true, 0, tda, 2);
		addText(17104, "Items you will keep on death (if not skulled):",
				0xff981f, false, true, 0, tda, 2);
		addText(17105, "Items you will lose on death (if not skulled):",
				0xff981f, false, true, 0, tda, 2);
		addText(17106, "Information", 0xff981f, false, true, 0, tda, 1);
		addText(17107, "Max items kept on death:", 0xff981f, false, true, 0,
				tda, 1);
		addText(17108, "~ 3 ~", 0xffcc33, false, true, 0, tda, 1);
		rsinterface.scrollMax = 0;
		rsinterface.interfaceShown = false;
		rsinterface.children = new int[12];
		rsinterface.childX = new int[12];
		rsinterface.childY = new int[12];

		rsinterface.children[0] = 17101;
		rsinterface.childX[0] = 7;
		rsinterface.childY[0] = 8;
		rsinterface.children[1] = 17102;
		rsinterface.childX[1] = 480;
		rsinterface.childY[1] = 17;
		rsinterface.children[2] = 17103;
		rsinterface.childX[2] = 185;
		rsinterface.childY[2] = 18;
		rsinterface.children[3] = 17104;
		rsinterface.childX[3] = 22;
		rsinterface.childY[3] = 50;
		rsinterface.children[4] = 17105;
		rsinterface.childX[4] = 22;
		rsinterface.childY[4] = 110;
		rsinterface.children[5] = 17106;
		rsinterface.childX[5] = 347;
		rsinterface.childY[5] = 47;
		rsinterface.children[6] = 17107;
		rsinterface.childX[6] = 349;
		rsinterface.childY[6] = 270;
		rsinterface.children[7] = 17108;
		rsinterface.childX[7] = 398;
		rsinterface.childY[7] = 298;
		rsinterface.children[8] = 17115;
		rsinterface.childX[8] = 348;
		rsinterface.childY[8] = 64;
		rsinterface.children[9] = 10494;
		rsinterface.childX[9] = 26;
		rsinterface.childY[9] = 74;
		rsinterface.children[10] = 10600;
		rsinterface.childX[10] = 26;
		rsinterface.childY[10] = 133;
		rsinterface.children[11] = 10601;
		rsinterface.childX[11] = 480;
		rsinterface.childY[11] = 17;
	}

	public static void prayerTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(5608);
		RSInterface currentPray = interfaceCache[687];
		currentPray.textColor = 0xFF981F;
		currentPray.textShadow = true;
		currentPray.message = "%1/%2";
		addSprite(5651, 0, "PRAYER");
		addPrayer(18000, 0, 601, 7, 0, "Sharp Eye");
		addPrayer(18002, 0, 602, 8, 1, "Mystic Will");
		addPrayer(18004, 0, 603, 25, 2, "Hawk Eye");
		addPrayer(18006, 0, 604, 26, 3, "Mystic Lore");
		addPrayer(18008, 0, 605, 43, 4, "Eagle Eye");
		addPrayer(18010, 0, 606, 44, 5, "Mystic Might");
		addPrayer(18012, 0, 607, 59, 6, "Chivalry");
		addPrayer(18014, 0, 608, 69, 7, "Piety");
		tab.totalChildren(54);
		/* Buttons/glows */
		tab.child(0, 5609, 6, 4);
		tab.child(1, 5610, 42, 4);
		tab.child(2, 5611, 78, 4);
		tab.child(3, 5612, 6, 40);
		tab.child(4, 5613, 42, 40);
		tab.child(5, 5614, 78, 40);
		tab.child(6, 5615, 114, 40);
		tab.child(7, 5616, 150, 40);
		tab.child(8, 5617, 6, 76);
		tab.child(9, 5618, 114, 76);
		tab.child(10, 5619, 150, 76);
		tab.child(11, 5620, 6, 112);
		tab.child(12, 5621, 42, 112);
		tab.child(13, 5622, 78, 112);
		tab.child(14, 5623, 114, 112);
		tab.child(15, 683, 42, 148);
		tab.child(16, 684, 78, 148);
		tab.child(17, 685, 114, 148);
		/* Sprites */
		tab.child(18, 5632, 8, 6);
		tab.child(19, 5633, 44, 6);
		tab.child(20, 5634, 80, 6);
		tab.child(21, 5635, 8, 42);
		tab.child(22, 5636, 44, 42);
		tab.child(23, 5637, 80, 42);
		tab.child(24, 5638, 116, 42);
		tab.child(25, 5639, 152, 42);
		tab.child(26, 5640, 8, 79);
		tab.child(27, 5641, 116, 78);
		tab.child(28, 5642, 152, 78);
		tab.child(29, 5643, 8, 114);
		tab.child(30, 5644, 44, 114);
		tab.child(31, 686, 80, 114);
		tab.child(32, 5645, 116, 114);
		tab.child(33, 5649, 44, 150);
		tab.child(34, 5647, 80, 150);
		tab.child(35, 5648, 116, 150);
		/* New prayers */
		tab.child(36, 18000, 114, 4);
		tab.child(37, 18001, 117, 8);
		tab.child(38, 18002, 150, 4);
		tab.child(39, 18003, 153, 7);
		tab.child(40, 18004, 42, 76);
		tab.child(41, 18005, 45, 80);
		tab.child(42, 18006, 78, 76);
		tab.child(43, 18007, 81, 79);
		tab.child(44, 18008, 150, 112);
		tab.child(45, 18009, 153, 116);
		tab.child(46, 18010, 6, 148);
		tab.child(47, 18011, 9, 151);
		tab.child(48, 18012, 150, 148);
		tab.child(49, 18013, 157, 151);
		tab.child(50, 18014, 6, 184);
		tab.child(51, 18015, 8, 194);
		/* Prayer icons & text */
		tab.child(52, 5651, 65, 242);
		tab.child(53, 687, 14, 244);
		String[] tools = { "Thick Skin", "Burst of Strength",
				"Clarity of Thought", "Rock Skin", "Superhuman Strength",
				"Improved Reflexes", "Rapid Restore", "Rapid Heal",
				"Protect Item", "Steel Skin", "Ultimate Strength",
				"Incredible Reflexes", "Protect from Magic",
				"Protect from Range", "Protect from Melee", "Retribution",
				"Redemption", "Smite" };
		int count = 0;
		for (int j = 5609; j <= 5623; j++) {
			RSInterface tab2 = interfaceCache[j];
			tab2.tooltip = "Activate @or2@" + tools[count++];
		}
		for (int j = 683; j <= 685; j++) {
			RSInterface tab2 = interfaceCache[j];
			tab2.tooltip = "Activate @or2@" + tools[count++];
		}
	}

	public static void optionTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(904);
		RSInterface energy = interfaceCache[149];
		energy.textColor = 0xff9933;
		addSprite(905, 9, "Interfaces/Options/SPRITE");
		addSprite(907, 18, "Interfaces/Options/SPRITE");
		addSprite(909, 29, "Interfaces/Options/SPRITE");
		addSprite(951, 32, "Interfaces/Options/SPRITE");
		addSprite(953, 33, "Interfaces/Options/SPRITE");
		addSprite(955, 34, "Interfaces/Options/SPRITE");
		addSprite(947, 36, "Interfaces/Options/SPRITE");
		addSprite(949, 35, "Interfaces/Options/SPRITE");
		// run button here
		addConfigButton(152, 904, 30, 31, "Interfaces/Options/SPRITE", 40, 40,
				"Toggle-run", 1, 5, 173);
		addConfigButton(906, 904, 10, 14, "Interfaces/Options/SPRITE", 32, 16,
				"Dark", 1, 5, 166);
		addConfigButton(908, 904, 11, 15, "Interfaces/Options/SPRITE", 32, 16,
				"Normal", 2, 5, 166);
		addConfigButton(910, 904, 12, 16, "Interfaces/Options/SPRITE", 32, 16,
				"Bright", 3, 5, 166);
		addConfigButton(912, 904, 13, 17, "Interfaces/Options/SPRITE", 32, 16,
				"Very Bright", 4, 5, 166);
		addConfigButton(930, 904, 19, 24, "Interfaces/Options/SPRITE", 26, 16,
				"Music Off", 4, 5, 168);
		addConfigButton(931, 904, 20, 25, "Interfaces/Options/SPRITE", 26, 16,
				"Music Level-1", 3, 5, 168);
		addConfigButton(932, 904, 21, 26, "Interfaces/Options/SPRITE", 26, 16,
				"Music Level-2", 2, 5, 168);
		addConfigButton(933, 904, 22, 27, "Interfaces/Options/SPRITE", 26, 16,
				"Music Level-3", 1, 5, 168);
		addConfigButton(934, 904, 23, 28, "Interfaces/Options/SPRITE", 24, 16,
				"Music Level-4", 0, 5, 168);
		addConfigButton(941, 904, 19, 24, "Interfaces/Options/SPRITE", 26, 16,
				"Sound Effects Off", 4, 5, 169);
		addConfigButton(942, 904, 20, 25, "Interfaces/Options/SPRITE", 26, 16,
				"Sound Effects Level-1", 3, 5, 169);
		addConfigButton(943, 904, 21, 26, "Interfaces/Options/SPRITE", 26, 16,
				"Sound Effects Level-2", 2, 5, 169);
		addConfigButton(944, 904, 22, 27, "Interfaces/Options/SPRITE", 26, 16,
				"Sound Effects Level-3", 1, 5, 169);
		addConfigButton(945, 904, 23, 28, "Interfaces/Options/SPRITE", 24, 16,
				"Sound Effects Level-4", 0, 5, 169);
		addConfigButton(913, 904, 30, 31, "Interfaces/Options/SPRITE", 40, 40,
				"Toggle-Mouse Buttons", 0, 5, 170);
		addConfigButton(915, 904, 30, 31, "Interfaces/Options/SPRITE", 40, 40,
				"Toggle-Chat Effects", 0, 5, 171);
		addConfigButton(957, 904, 30, 31, "Interfaces/Options/SPRITE", 40, 40,
				"Toggle-Split Private Chat", 1, 5, 287);
		addConfigButton(12464, 904, 30, 31, "Interfaces/Options/SPRITE", 40,
				40, "Toggle-Accept Aid", 0, 5, 427);
		tab.totalChildren(28);
		int x = 0;
		int y = 2;
		tab.child(0, 905, 13 + x, 10 + y);
		tab.child(1, 906, 48 + x, 18 + y);
		tab.child(2, 908, 80 + x, 18 + y);
		tab.child(3, 910, 112 + x, 18 + y);
		tab.child(4, 912, 144 + x, 18 + y);
		tab.child(5, 907, 14 + x, 55 + y);
		tab.child(6, 930, 49 + x, 61 + y);
		tab.child(7, 931, 75 + x, 61 + y);
		tab.child(8, 932, 101 + x, 61 + y);
		tab.child(9, 933, 127 + x, 61 + y);
		tab.child(10, 934, 151 + x, 61 + y);
		tab.child(11, 909, 13 + x, 99 + y);
		tab.child(12, 941, 49 + x, 104 + y);
		tab.child(13, 942, 75 + x, 104 + y);
		tab.child(14, 943, 101 + x, 104 + y);
		tab.child(15, 944, 127 + x, 104 + y);
		tab.child(16, 945, 151 + x, 104 + y);
		tab.child(17, 913, 15, 153);
		tab.child(18, 955, 19, 159);
		tab.child(19, 915, 75, 153);
		tab.child(20, 953, 79, 160);
		tab.child(21, 957, 135, 153);
		tab.child(22, 951, 139, 159);
		tab.child(23, 12464, 15, 208);
		tab.child(24, 949, 20, 213);
		tab.child(25, 152, 75, 208);
		tab.child(26, 947, 87, 212);
		tab.child(27, 149, 80, 231);
	}

	public static void sidebar0(TextDrawingArea[] tda) {
		/*
		 * Sidebar0a(id, id2, id3, "text1", "text2", "text3", "text4", str1x,
		 * str1y, str2x, str2y, str3x, str3y, str4x, str4y, img1x, img1y, img2x,
		 * img2y, img3x, img3y, img4x, img4y, tda);
		 */
		Sidebar0a(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block", 42, 75,
				127, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block", 43, 75,
				124, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block", 42, 75,
				125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block", 39,
				75, 121, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40,
				103, tda);
		Sidebar0a(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block", 40, 75,
				124, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 42, 75,
				125, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block", 41,
				75, 123, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40,
				103, tda);
		Sidebar0a(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block", 42, 75,
				125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		/*
		 * Sidebar0b(id, id2, "text1", "text2", "text3", "text4", str1x, str1y,
		 * str2x, str2y, str3x, str3y, str4x, str4y, img1x, img1y, img2x, img2y,
		 * img3x, img3y, img4x, img4y, tda);
		 */
		Sidebar0b(776, 779, "Reap", "Chop", "Jab", "Block", 42, 75, 126, 75,
				46, 128, 125, 128, 122, 103, 122, 50, 40, 103, 40, 50, tda);
		/*
		 * Sidebar0c(id, id2, id3, "text1", "text2", "text3", str1x, str1y,
		 * str2x, str2y, str3x, str3y, img1x, img1y, img2x, img2y, img3x, img3y,
		 * tda);
		 */
		Sidebar0c(425, 428, 7474, "Pound", "Pummel", "Block", 39, 75, 121, 75,
				42, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(1749, 1752, 7524, "Accurate", "Rapid", "Longrange", 33, 75,
				125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(1764, 1767, 7549, "Accurate", "Rapid", "Longrange", 33, 75,
				125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(4446, 4449, 7649, "Accurate", "Rapid", "Longrange", 33, 75,
				125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(5855, 5857, 7749, "Punch", "Kick", "Block", 40, 75, 129, 75,
				42, 128, 40, 50, 122, 50, 40, 103, tda);
		Sidebar0c(6103, 6132, 6117, "Bash", "Pound", "Block", 43, 75, 124, 75,
				42, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(8460, 8463, 8493, "Jab", "Swipe", "Fend", 46, 75, 124, 75,
				43, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(12290, 12293, 12323, "Flick", "Lash", "Deflect", 44, 75, 127,
				75, 36, 128, 40, 50, 40, 103, 122, 50, tda);
		/*
		 * Sidebar0d(id, id2, "text1", "text2", "text3", str1x, str1y, str2x,
		 * str2y, str3x, str3y, img1x, img1y, img2x, img2y, img3x, img3y, tda);
		 */
		Sidebar0d(328, 331, "Bash", "Pound", "Focus", 42, 66, 39, 101, 41, 136,
				40, 120, 40, 50, 40, 85, tda);

		RSInterface rsi = addInterface(19300);
		/* textSize(ID, wid, Size); */
		textSize(3983, tda, 0);
		/* addToggleButton(id, sprite, config, width, height, wid); */
		addToggleButton(150, 150, 172, 150, 44, "Auto Retaliate");

		rsi.totalChildren(2, 2, 2);
		rsi.child(0, 3983, 52, 25); // combat level
		rsi.child(1, 150, 21, 153); // auto retaliate

		rsi = interfaceCache[3983];
		rsi.centerText = true;
		rsi.textColor = 0xff981f;
	}

	public static void friendsTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(5065);
		RSInterface list = interfaceCache[5066];
		addText(5067, "Friends List", tda, 1, 0xff9933, true, true);
		addText(5070, "Add Friend", tda, 0, 0xff9933, false, true);
		addText(5071, "Delete Friend", tda, 0, 0xff9933, false, true);
		addSprite(16126, 4, "Interfaces/Friends/SPRITE");
		addSprite(16127, 8, "Interfaces/Friends/SPRITE");
		addHoverButton(5068, "Interfaces/Friends/SPRITE", 6, 72, 32,
				"Add Friend", 201, 5072, 1);
		addHoveredButton(5072, "Interfaces/Friends/SPRITE", 7, 72, 32, 5073);
		addHoverButton(5069, "Interfaces/Friends/SPRITE", 6, 72, 32,
				"Delete Friend", 202, 5074, 1);
		addHoveredButton(5074, "Interfaces/Friends/SPRITE", 7, 72, 32, 5075);
		tab.totalChildren(11);
		tab.child(0, 5067, 95, 4);
		tab.child(1, 16127, 0, 25);
		tab.child(2, 16126, 0, 221);
		tab.child(3, 5066, 0, 24);
		tab.child(4, 16126, 0, 22);
		tab.child(5, 5068, 15, 226);
		tab.child(6, 5072, 15, 226);
		tab.child(7, 5069, 103, 226);
		tab.child(8, 5074, 103, 226);
		tab.child(9, 5070, 25, 237);
		tab.child(10, 5071, 106, 237);
		list.height = 196;
		list.width = 174;
		for (int id = 5092, i = 0; id <= 5191 && i <= 99; id++, i++) {
			list.children[i] = id;
			list.childX[i] = 3;
			list.childY[i] = list.childY[i] - 7;
		}
		for (int id = 5192, i = 100; id <= 5291 && i <= 199; id++, i++) {
			list.children[i] = id;
			list.childX[i] = 131;
			list.childY[i] = list.childY[i] - 7;
		}
	}

	public static void ignoreTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(5715);
		RSInterface list = interfaceCache[5716];
		addText(5717, "Ignore List", tda, 1, 0xff9933, true, true);
		addText(5720, "Add Name", tda, 0, 0xff9933, false, true);
		addText(5721, "Delete Name", tda, 0, 0xff9933, false, true);
		addHoverButton(5718, "Interfaces/Friends/SPRITE", 6, 72, 32,
				"Add Name", 501, 5722, 1);
		addHoveredButton(5722, "Interfaces/Friends/SPRITE", 7, 72, 32, 5723);
		addHoverButton(5719, "Interfaces/Friends/SPRITE", 6, 72, 32,
				"Delete Name", 502, 5724, 1);
		addHoveredButton(5724, "Interfaces/Friends/SPRITE", 7, 72, 32, 5725);
		tab.totalChildren(11);
		tab.child(0, 5717, 95, 4);
		tab.child(1, 16127, 0, 25);
		tab.child(2, 16126, 0, 221);
		tab.child(3, 5716, 0, 24);
		tab.child(4, 16126, 0, 22);
		tab.child(5, 5718, 15, 226);
		tab.child(6, 5722, 15, 226);
		tab.child(7, 5719, 103, 226);
		tab.child(8, 5724, 103, 226);
		tab.child(9, 5720, 27, 237);
		tab.child(10, 5721, 108, 237);
		list.height = 196;
		list.width = 174;
		for (int id = 5742, i = 0; id <= 5841 && i <= 99; id++, i++) {
			list.children[i] = id;
			list.childX[i] = 3;
			list.childY[i] = list.childY[i] - 7;
		}
	}

/*private static void Bank(TextDrawingArea[] wid) {
		RSInterface Interface = addTabInterface(5292);
		setChildren(19, Interface);
		addSprite(5293, 0, "Bank/BANK");
		setBounds(5293, 13, 13, 0, Interface);
		addHover(5384, 3, 0, 5380, 1, "Bank/BANK", 17, 17, "Close Window");
		addHovered(5380, 2, "Bank/BANK", 17, 17, 5379);
		setBounds(5384, 476, 16, 3, Interface);
		setBounds(5380, 476, 16, 4, Interface);	
		addHover(5294, 4, 0, 5295, 3, "Bank/BANK", 114, 25, "Set A Bank PIN");
		addHovered(5295, 4, "Bank/BANK", 114, 25, 5296);
		setBounds(5294, 110, 285, 5, Interface);
		setBounds(5295, 110, 285, 6, Interface);
		addBankHover(21000, 4, 21001, 5, 8, "Bank/BANK", 35, 25, 304, 1, "Swap Withdraw Mode", 21002, 7, 6, "Bank/BANK", 21003, "Switch to insert items \nmode", "Switch to swap items \nmode.", 12, 20);
		setBounds(21000, 25, 285, 7, Interface);
		setBounds(21001, 10, 225, 8, Interface);
		addBankHover(21004, 4, 21005, 13, 15, "Bank/BANK", 35, 25, 0, 1, "Search", 21006, 14, 16, "Bank/BANK", 21007, "Click here to search your \nbank", "Click here to search your \nbank", 12, 20);
		setBounds(21004, 65, 285, 9, Interface);
		setBounds(21005, 50, 225, 10, Interface);
		addBankHover(21008, 4, 21009, 9, 11, "Bank/BANK", 35, 25, 115, 1, "Search", 21010, 10, 12, "Bank/BANK", 21011, "Switch to note withdrawal \nmode", "Switch to item withdrawal \nmode", 12, 20);
		setBounds(21008, 240, 285, 11, Interface);
		setBounds(21009, 225, 225, 12, Interface);
		addBankHover1(21012, 5, 21013, 17, "Bank/BANK", 35, 25, "Deposit carried tems", 21014, 18, "Bank/BANK", 21015, "Empty your backpack into\nyour bank", 0, 20);
		setBounds(21012, 375, 285, 13, Interface);
		setBounds(21013, 360, 225, 14, Interface);
		addBankHover1(21016, 5, 21017, 19, "Bank/BANK", 35, 25, "Deposit worn items", 21018, 20, "Bank/BANK", 21019, "Empty the items your are\nwearing into your bank", 0, 20);
		setBounds(21016, 415, 285, 15, Interface);
		setBounds(21017, 400, 225, 16, Interface);
		addBankHover1(21020, 5, 21021, 21, "Bank/BANK", 35, 25, "Deposit beast of burden inventory.", 21022, 22, "Bank/BANK", 21023, "Empty your BoB's inventory\ninto your bank", 0, 20);
		setBounds(21020, 455, 285, 17, Interface);
		setBounds(21021, 440, 225, 18, Interface);
		setBounds(5383, 170, 15, 1, Interface);
		setBounds(5385, -4, 34, 2, Interface);
		Interface = interfaceCache[5385];
		Interface.height = 247;
		Interface.width = 480;
		Interface = interfaceCache[5382];
		Interface.width = 10;
		Interface.invSpritePadX = 12;
		Interface.height = 35;
	}*/

	public static void Bank(TextDrawingArea[] wid) {
		RSInterface Interface = addTabInterface(5292);
		setChildren(38, Interface);
		addSprite(5293, 0, "Bank/BANK");
		setBounds(5293, 13, 13, 0, Interface);
		addHover(5384, 3, 0, 5380, 1, "Bank/BANK", 17, 17, "Close Window");
		addHovered(5380, 2, "Bank/BANK", 17, 17, 5379);
		setBounds(5384, 476, 16, 3, Interface);
		setBounds(5380, 476, 16, 4, Interface);
		addHover(5294, 4, 0, 5295, 3, "Bank/BANK", 114, 25, "Set A Bank PIN");
		addHovered(5295, 4, "Bank/BANK", 114, 25, 5296);
		setBounds(5294, 110, 285, 5, Interface);
		setBounds(5295, 110, 285, 6, Interface);
		addBankHover(22000, 4, 22001, 5, 8, "Bank/BANK", 35, 25, 304, 1, "Swap Withdraw Mode", 22002, 7, 6, "Bank/BANK", 22003, "Switch to insert items \nmode", "Switch to swap items \nmode.", 12, 20);
		setBounds(22000, 25, 285, 7, Interface);
		setBounds(22001, 10, 225, 8, Interface);
		addBankHover(22004, 4, 22005, 13, 15, "Bank/BANK", 35, 25, 0, 1, "Search", 22006, 14, 16, "Bank/BANK", 22007, "Click here to search your \nbank", "Click here to search your \nbank", 12, 20);
		setBounds(22004, 65, 285, 9, Interface);
		setBounds(22005, 50, 225, 10, Interface);
		addBankHover(22008, 4, 22009, 9, 11, "Bank/BANK", 35, 25, 115, 1, "Switch to note Withdrawal", 22010, 10, 12, "Bank/BANK", 22011, "Switch to note withdrawal \nmode", "Switch to item withdrawal \nmode", 12, 20);
		setBounds(22008, 240, 285, 11, Interface);
		setBounds(22009, 225, 225, 12, Interface);
		addBankHover1(22012, 5, 22013, 17, "Bank/BANK", 35, 25, "Deposit carried tems", 22014, 18, "Bank/BANK", 22015, "Empty your backpack into\nyour bank", 0, 20);
		setBounds(22012, 375, 285, 13, Interface);
		setBounds(22013, 360, 225, 14, Interface);
		addBankHover1(22016, 5, 22017, 19, "Bank/BANK", 35, 25, "Deposit worn items", 22018, 20, "Bank/BANK", 22019, "Empty the items your are\nwearing into your bank", 0, 20);
		setBounds(22016, 415, 285, 15, Interface);
		setBounds(22017, 400, 225, 16, Interface);
		addBankHover1(22020, 5, 22021, 21, "Bank/BANK", 35, 25, "Deposit beast of burden inventory.", 22022, 22, "Bank/BANK", 22023, "Empty your BoB's inventory\ninto your bank", 0, 20);
		setBounds(22020, 455, 285, 17, Interface);
		setBounds(22021, 440, 225, 18, Interface);
		setBounds(5383, 170, 15, 1, Interface);
		setBounds(5385, -4, 74, 2, Interface);
		addButton(22024, 0, "BANK/TAB", "Click here to view the full contents of your bank");
		setBounds(22024, 22, 36, 19, Interface);
		addButton(22025, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22025, 70, 36, 20, Interface);
		addButton(22026, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22026, 118, 36, 21, Interface);
		addButton(22027, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22027, 166, 36, 22, Interface);
		addButton(22028, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22028, 214, 36, 23, Interface);
		addButton(22029, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22029, 262, 36, 24, Interface);
		addButton(22030, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22030, 310, 36, 25, Interface);
		addButton(22031, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22031, 358, 36, 26, Interface);
		addButton(22032, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22032, 406, 36, 27, Interface);
		addText(22033, "134", wid, 0, 0xB4965A, true, false);
		setBounds(22033, 473, 42, 28, Interface);
		addText(22034, "496", wid, 0, 0xB4965A, true, false);
		setBounds(22034, 473, 57, 29, Interface);
		addBankItem(22035, false);
		setBounds(22035, 77, 39, 30, Interface);
		addBankItem(22036, false);
		setBounds(22036, 125, 39, 31, Interface);
		addBankItem(22037, false);
		setBounds(22037, 173, 39, 32, Interface);
		addBankItem(22038, false);
		setBounds(22038, 221, 39, 33, Interface);
		addBankItem(22039, false);
		setBounds(22039, 269, 39, 34, Interface);
		addBankItem(22040, false);
		setBounds(22040, 317, 39, 35, Interface);
		addBankItem(22041, false);
		setBounds(22041, 365, 39, 36, Interface);
		addBankItem(22042, false);
		setBounds(22042, 413, 39, 37, Interface);

		addText(27000, "0", 0xFF981F, false, true, 52, wid, 1);
		addText(27001, "0", 0xFF981F, false, true, 52, wid, 1);
		addText(27002, "0", 0xFF981F, false, true, 52, wid, 1);

		Interface = interfaceCache[5385];
		Interface.height = 206; //206
		Interface.width = 480;
		Interface = interfaceCache[5382];
		Interface.width = 10;
		Interface.invSpritePadX = 12;
		Interface.height = 35; //35
	}

	public static void addBankItem(int index, Boolean hasOption) {
		RSInterface rsi = interfaceCache[index] = new RSInterface();
		rsi.actions = new String[5];
		rsi.spritesX = new int[20];
		rsi.invStackSizes = new int[30];
		rsi.inv = new int[30];
		rsi.spritesY = new int[20];

		rsi.children = new int[0];
		rsi.childX = new int[0];
		rsi.childY = new int[0];

		rsi.hasExamine = false;

		rsi.invSpritePadX = 24;
		rsi.invSpritePadY = 24;
		rsi.height = 5;
		rsi.width = 6;
		rsi.parentID = 5292;
		rsi.id = index;
		rsi.type = 2;
	}

	public static void pouches(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addInterface(39700);
		addSprite(39701, 0, "Interfaces/Summoning/Pouches/SPRITE");
		addHover(39702, 3, 0, 39703, 1, "Interfaces/Summoning/Pouches/SPRITE",
				17, 17, "Close Window");
		addHovered(39703, 2, "Interfaces/Summoning/Pouches/SPRITE", 17, 17,
				39704);
		addButton(39705, 3, "Interfaces/Summoning/Pouches/SPRITE", "Pouches",
				27640, 1, 116, 20);
		addButton(39706, 4, "Interfaces/Summoning/Pouches/SPRITE", "Scrolls",
				27640, 1, 116, 20);
		addText(39707, "Summoning Pouch Creation", TDA, 2, 0xff981f, false,
				true);

		RSInterface scroll = addTabInterface(39710);
		scroll.width = 430;
		scroll.height = 230;
		scroll.scrollMax = 550;
		// scroll.newScroller = true;
		int lastId = 39710;
		int lastImage = 4;
		for (int i = 0; i < 78; i++) {
			addHover(lastId + 1, 1, 0, lastId + 2, lastImage + 1,
					"Interfaces/Summoning/Pouches/Sprite", 46, 50, "Infuse "
							+ pouchNames[i] + " pouch");
			addHovered(lastId + 2, lastImage + 2,
					"Interfaces/Summoning/Pouches/Sprite", 46, 50, lastId + 3);
			lastId += 3;
			lastImage += 2;
		}

		rsinterface.children = new int[7];
		rsinterface.childX = new int[7];
		rsinterface.childY = new int[7];

		rsinterface.children[0] = 39701;
		rsinterface.childX[0] = 14;
		rsinterface.childY[0] = 17;
		rsinterface.children[1] = 39702;
		rsinterface.childX[1] = 475;
		rsinterface.childY[1] = 30;
		rsinterface.children[2] = 39703;
		rsinterface.childX[2] = 475;
		rsinterface.childY[2] = 30;
		rsinterface.children[3] = 39705;
		rsinterface.childX[3] = 25;
		rsinterface.childY[3] = 30;
		rsinterface.children[4] = 39706;
		rsinterface.childX[4] = 128;
		rsinterface.childY[4] = 30;
		rsinterface.children[5] = 39707;
		rsinterface.childX[5] = 268;
		rsinterface.childY[5] = 30;
		rsinterface.children[6] = 39710;
		rsinterface.childX[6] = 35;
		rsinterface.childY[6] = 65;

		scroll.children = new int[156];
		scroll.childX = new int[156];
		scroll.childY = new int[156];

		int lastNumber = -1;
		int lastId2 = 39710;
		int lastX = -52;
		int lastY = 0;
		for (int i = 0; i < 78; i++) {
			scroll.children[lastNumber += 1] = lastId2 += 1;
			scroll.childX[lastNumber] = lastX += 52;
			scroll.childY[lastNumber] = lastY;
			scroll.children[lastNumber += 1] = lastId2 += 1;
			scroll.childX[lastNumber] = lastX;
			scroll.childY[lastNumber] = lastY;
			lastId2 += 1;
			if (lastX == 364) {
				lastX = -52;
				lastY += 55;
			}
		}
	}

	public static void formParty(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(27124);
		addSprite(27125, 0, "Interfaces/Dungeoneering/DUNG");
		addHoverButton(27129, "Interfaces/Dungeoneering/DUNG", 3, 180, 32,
				"Form", 250, 27130, 5);
		addHoveredButton(27130, "Interfaces/Dungeoneering/DUNG", 4, 180, 32,
				27131);
		addHoverButton(27132, "Interfaces/Dungeoneering/DUNG", 7, 52, 25,
				"Reset", 250, 27133, 5);
		addHoveredButton(27133, "Interfaces/Dungeoneering/DUNG", 8, 52, 25,
				27134);
		addHoverButton(27140, "Interfaces/Dungeoneering/DUNG", 15, 60, 18,
				"Change Floor", 250, 27141, 5);
		addHoveredButton(27141, "Interfaces/Dungeoneering/DUNG", 16, 60, 18,
				27142);
		addHoverButton(27143, "Interfaces/Dungeoneering/DUNG", 15, 60, 18,
				"Change Complexity", 250, 27144, 5);
		addHoveredButton(27144, "Interfaces/Dungeoneering/DUNG", 16, 60, 18,
				27145);
		addText(27135, " ", tda, 2, 0xffffff, false, true);
		addText(27136, " ", tda, 2, 0xffffff, false, true);
		addText(27137, "-", tda, 1, 0xffffff, false, true);
		addText(27138, "-", tda, 1, 0xffffff, false, true);
		int[][] data = { { 27125, 0, 0 }, { 27129, 5, 111 }, { 27130, 5, 111 },
				{ 27132, 132, 230 }, { 27133, 132, 230 }, { 27135, 99, 155 },
				{ 27136, 99, 183 }, { 27137, 113, 229 }, { 27138, 113, 245 },
				{ 27140, 121, 152 }, { 27141, 121, 152 }, { 27143, 121, 180 },
				{ 27144, 121, 180 } };
		tab.totalChildren(13);
		for (int i = 0; i < data.length; i++) {
			tab.child(i, data[i][0], data[i][1], data[i][2]);
		}
	}

	public static void dungParty(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(26224);
		addSprite(26225, 0, "Interfaces/Dungeoneering/DUNG");
		addHoverButton(26226, "Interfaces/Dungeoneering/DUNG", 10, 60, 18,
				"Change Floor", 250, 26227, 5);
		addHoveredButton(26227, "Interfaces/Dungeoneering/DUNG", 11, 60, 18,
				26228);
		addHoverButton(26229, "Interfaces/Dungeoneering/DUNG", 5, 180, 32,
				"Leave", 250, 26230, 5);
		addHoveredButton(26230, "Interfaces/Dungeoneering/DUNG", 6, 180, 32,
				26231);
		addHoverButton(26232, "Interfaces/Dungeoneering/DUNG", 7, 52, 25,
				"Reset", 250, 26233, 5);
		addHoveredButton(26233, "Interfaces/Dungeoneering/DUNG", 8, 52, 25,
				26234);
		addHoverButton(26246, "Interfaces/Dungeoneering/DUNG", 10, 60, 18,
				"Change Complexity", 250, 26247, 5);
		addHoveredButton(26247, "Interfaces/Dungeoneering/DUNG", 11, 60, 18,
				26248);
		addText(26235, "", tda, 1, 0xffffff, true, true);
		addText(26236, "", tda, 1, 0xffffff, true, true);
		addText(26237, "", tda, 1, 0xffffff, true, true);
		addText(26238, "", tda, 1, 0xffffff, true, true);
		addText(26239, "", tda, 1, 0xffffff, true, true);
		addText(26240, " ", tda, 2, 0xffffff, false, true);
		addText(26241, " ", tda, 2, 0xffffff, true, true);
		addText(26242, "-", tda, 1, 0xffffff, false, true);
		addText(26243, "-", tda, 1, 0xffffff, false, true);
		addText(26257, "@or2@Kills: ", tda, 2, 0xffffff, false, true);
		addText(26255, "@or2@Deaths: ", tda, 2, 0xffffff, false, true);
		addSprite(26751, 14, "Interfaces/Dungeoneering/DUNG");
		addSprite(26753, 17, "Interfaces/Dungeoneering/DUNG");
		addText(26251, "00:00:00", tda, 1, 0xffffff, false, true);
		int[][] data = { { 26225, 0, 0 }, { 26235, 95, 29 }, { 26236, 91, 44 },
				{ 26237, 91, 59 }, { 26238, 91, 75 }, { 26239, 91, 90 },
				{ 26240, 99, 155 }, { 26241, 103, 183 }, { 26242, 113, 229 },
				{ 26243, 113, 245 }, { 26751, 88, 67 }, { 26753, 110, 67 },
				{ 26251, 119, 70 }, { 26255, 9, 80 }, { 26257, 9, 58 },
				{ 26226, 121, 152 }, { 26227, 121, 152 }, { 26229, 5, 111 },
				{ 26230, 5, 111 }, { 26232, 132, 230 }, { 26233, 132, 230 },
				{ 26246, 121, 180 }, { 26247, 121, 180 }, };
		tab.totalChildren(23);
		for (int i = 0; i < data.length; i++) {
			tab.child(i, data[i][0], data[i][1], data[i][2]);
		}
	}

	public static void scrolls(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addInterface(38700);
		addButton(38701, 0, "Interfaces/Summoning/Scrolls/SPRITE", "Pouches",
				27640, 1, 116, 20);
		addButton(38702, 1, "Interfaces/Summoning/Scrolls/SPRITE", "Scrolls",
				27640, 1, 116, 20);

		RSInterface scroll = addTabInterface(38710);
		scroll.width = 430;
		scroll.height = 230;
		scroll.scrollMax = 550;
		// scroll.newScroller = true;
		int lastId = 38710;
		int lastImage = 4;
		for (int i = 0; i < 78; i++) {
			addHover(lastId + 1, 1, 0, lastId + 2, lastImage + 1,
					"Interfaces/Summoning/Scrolls/Sprite", 46, 50, "Select "
							+ scrollNames[i] + " scroll");
			addHovered(lastId + 2, lastImage + 2,
					"Interfaces/Summoning/Scrolls/Sprite", 46, 50, lastId + 3);
			lastId += 3;
			lastImage += 2;
		}

		rsinterface.children = new int[7];
		rsinterface.childX = new int[7];
		rsinterface.childY = new int[7];

		rsinterface.children[0] = 39701;
		rsinterface.childX[0] = 14;
		rsinterface.childY[0] = 17;
		rsinterface.children[1] = 39702;
		rsinterface.childX[1] = 475;
		rsinterface.childY[1] = 30;
		rsinterface.children[2] = 39703;
		rsinterface.childX[2] = 475;
		rsinterface.childY[2] = 30;
		rsinterface.children[3] = 38701;
		rsinterface.childX[3] = 25;
		rsinterface.childY[3] = 30;
		rsinterface.children[4] = 38702;
		rsinterface.childX[4] = 128;
		rsinterface.childY[4] = 30;
		rsinterface.children[5] = 39707;
		rsinterface.childX[5] = 268;
		rsinterface.childY[5] = 30;
		rsinterface.children[6] = 38710;
		rsinterface.childX[6] = 35;
		rsinterface.childY[6] = 65;

		scroll.children = new int[156];
		scroll.childX = new int[156];
		scroll.childY = new int[156];

		int lastNumber = -1;
		int lastId2 = 38710;
		int lastX = -52;
		int lastY = 0;
		for (int i = 0; i < 78; i++) {
			scroll.children[lastNumber += 1] = lastId2 += 1;
			scroll.childX[lastNumber] = lastX += 52;
			scroll.childY[lastNumber] = lastY;
			scroll.children[lastNumber += 1] = lastId2 += 1;
			scroll.childX[lastNumber] = lastX;
			scroll.childY[lastNumber] = lastY;
			lastId2 += 1;
			if (lastX == 364) {
				lastX = -52;
				lastY += 55;
			}
		}
	}

	public static void bobInterface(TextDrawingArea[] tda) {
		RSInterface rsi = addTabInterface(2700);
		addSprite(2701, 0, "Interfaces/Summoning/BoB/SPRITE");
		addBobStorage(2702);
		addHoverButton(2703, "Interfaces/Summoning/BoB/SPRITE", 1, 21, 21,
				"Close", 250, 2704, 3);
		addHoveredButton(2704, "Interfaces/Summoning/BoB/SPRITE", 2, 21, 21,
				2705);
		rsi.totalChildren(4);
		rsi.child(0, 2701, 90, 14);
		rsi.child(1, 2702, 106, 57);
		rsi.child(2, 2703, 431, 23);
		rsi.child(3, 2704, 431, 23);
	}

	public static void pestPanel(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21119);
		addText(21120, "Bitch how did you", 0x999999, false, true, 52, tda, 1);
		addText(21121, "come here?", 0x33cc00, false, true, 52, tda, 1);
		addText(21122, "(Need 5 to 25 players)", 0xFFcc33, false, true, 52,
				tda, 1);
		addText(21123, "Points", 0x33ccff, false, true, 52, tda, 1);
		int last = 4;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21120, 15, 12, 0, RSinterface);
		setBounds(21121, 15, 30, 1, RSinterface);
		setBounds(21122, 15, 48, 2, RSinterface);
		setBounds(21123, 15, 66, 3, RSinterface);
	}

	public static void pestPanel2(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21100);
		addSprite(21101, 0, "Interfaces/Pest Control/PEST1");
		addSprite(21102, 1, "Interfaces/Pest Control/PEST1");
		addSprite(21103, 2, "Interfaces/Pest Control/PEST1");
		addSprite(21104, 3, "Interfaces/Pest Control/PEST1");
		addSprite(21105, 4, "Interfaces/Pest Control/PEST1");
		addSprite(21106, 5, "Interfaces/Pest Control/PEST1");
		addText(21107, "", 0xCC00CC, false, true, 52, tda, 1);
		addText(21108, "", 0x0000FF, false, true, 52, tda, 1);
		addText(21109, "", 0xFFFF44, false, true, 52, tda, 1);
		addText(21110, "", 0xCC0000, false, true, 52, tda, 1);
		addText(21111, "250", 0x99FF33, false, true, 52, tda, 1);// w purp
		addText(21112, "250", 0x99FF33, false, true, 52, tda, 1);// e blue
		addText(21113, "250", 0x99FF33, false, true, 52, tda, 1);// se yel
		addText(21114, "250", 0x99FF33, false, true, 52, tda, 1);// sw red
		addText(21115, "200", 0x99FF33, false, true, 52, tda, 1);// attacks
		addText(21116, "0", 0x99FF33, false, true, 52, tda, 1);// knights hp
		addText(21117, "Time Remaining:", 0xFFFFFF, false, true, 52, tda, 0);
		addText(21118, "", 0xFFFFFF, false, true, 52, tda, 0);
		int last = 18;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21101, 361, 26, 0, RSinterface);
		setBounds(21102, 396, 26, 1, RSinterface);
		setBounds(21103, 436, 26, 2, RSinterface);
		setBounds(21104, 474, 26, 3, RSinterface);
		setBounds(21105, 3, 21, 4, RSinterface);
		setBounds(21106, 3, 50, 5, RSinterface);
		setBounds(21107, 371, 60, 6, RSinterface);
		setBounds(21108, 409, 60, 7, RSinterface);
		setBounds(21109, 443, 60, 8, RSinterface);
		setBounds(21110, 479, 60, 9, RSinterface);
		setBounds(21111, 362, 10, 10, RSinterface);
		setBounds(21112, 398, 10, 11, RSinterface);
		setBounds(21113, 436, 10, 12, RSinterface);
		setBounds(21114, 475, 10, 13, RSinterface);
		setBounds(21115, 32, 32, 14, RSinterface);
		setBounds(21116, 32, 62, 15, RSinterface);
		setBounds(21117, 8, 88, 16, RSinterface);
		setBounds(21118, 87, 88, 17, RSinterface);
	}

	public static void configureLunar(TextDrawingArea[] TDA) {
		constructLunar();
		homeTeleport();
		drawRune(30003, 1, "Fire");
		drawRune(30004, 2, "Water");
		drawRune(30005, 3, "Air");
		drawRune(30006, 4, "Earth");
		drawRune(30007, 5, "Mind");
		drawRune(30008, 6, "Body");
		drawRune(30009, 7, "Death");
		drawRune(30010, 8, "Nature");
		drawRune(30011, 9, "Chaos");
		drawRune(30012, 10, "Law");
		drawRune(30013, 11, "Cosmic");
		drawRune(30014, 12, "Blood");
		drawRune(30015, 13, "Soul");
		drawRune(30016, 14, "Astral");
		addLunar3RunesSmallBox(30017, 9075, 554, 555, 0, 4, 3, 30003, 30004,
				64, "Bake Pie", "Bake pies without a stove", TDA, 0, 16, 2);
		addLunar2RunesSmallBox(30025, 9075, 557, 0, 7, 30006, 65, "Cure Plant",
				"Cure disease on farming patch", TDA, 1, 4, 2);
		addLunar3RunesBigBox(30032, 9075, 564, 558, 0, 0, 0, 30013, 30007, 65,
				"Monster Examine",
				"Detect the combat statistics of a\\nmonster", TDA, 2, 2, 2);
		addLunar3RunesSmallBox(30040, 9075, 564, 556, 0, 0, 1, 30013, 30005,
				66, "NPC Contact", "Speak with varied NPCs", TDA, 3, 0, 2);
		addLunar3RunesSmallBox(30048, 9075, 563, 557, 0, 0, 9, 30012, 30006,
				67, "Cure Other", "Cure poisoned players", TDA, 4, 8, 2);
		addLunar3RunesSmallBox(30056, 9075, 555, 554, 0, 2, 0, 30004, 30003,
				67, "Humidify", "fills certain vessels with water", TDA, 5, 0,
				5);
		addLunar3RunesSmallBox(30064, 9075, 563, 557, 1, 0, 1, 30012, 30006,
				68, "Monster Teleports", "Goes to monster teleports.", TDA, 6,
				0, 5);
		addLunar3RunesBigBox(30075, 9075, 563, 557, 1, 0, 3, 30012, 30006, 69,
				"Minigame Teleports", "Goes to minigame teleports.", TDA, 7, 0,
				5);
		addLunar3RunesSmallBox(30083, 9075, 563, 557, 1, 0, 5, 30012, 30006,
				70, "PK Teleports", "Goes to PK teleports.", TDA, 8, 0, 5);
		addLunar3RunesSmallBox(30091, 9075, 564, 563, 1, 1, 0, 30013, 30012,
				70, "Cure Me", "Cures Poison", TDA, 9, 0, 5);
		addLunar2RunesSmallBox(30099, 9075, 557, 1, 1, 30006, 70,
				"Skilling Kit", "Get skilling tools!", TDA, 10, 0, 5);
		addLunar3RunesSmallBox(30106, 9075, 563, 555, 1, 0, 0, 30012, 30004,
				71, "Skill Teleports", "Goes to skill teleports.", TDA, 11, 0,
				5);
		addLunar3RunesBigBox(30114, 9075, 563, 555, 1, 0, 4, 30012, 30004, 72,
				"", "", TDA, 12, 0, 5);
		addLunar3RunesSmallBox(30122, 9075, 564, 563, 1, 1, 1, 30013, 30012,
				73, "Cure Group", "Cures Poison on players", TDA, 13, 0, 5);
		addLunar3RunesBigBox(30130, 9075, 564, 559, 1, 1, 4, 30013, 30008, 74,
				"Stat Spy",
				"Cast on another player to see their\\nskill levels", TDA, 14,
				8, 2);
		addLunar3RunesBigBox(30138, 9075, 563, 554, 1, 1, 2, 30012, 30003, 74,
				"", "", TDA, 15, 0, 5);
		addLunar3RunesBigBox(30146, 9075, 563, 554, 1, 1, 5, 30012, 30003, 75,
				"", "", TDA, 16, 0, 5);
		addLunar3RunesSmallBox(30154, 9075, 554, 556, 1, 5, 9, 30003, 30005,
				76, "Superglass Make", "Make glass without a furnace", TDA, 17,
				16, 2);
		addLunar3RunesSmallBox(30162, 9075, 563, 555, 1, 1, 3, 30012, 30004,
				77, "", "", TDA, 18, 0, 5);
		addLunar3RunesSmallBox(30170, 9075, 563, 555, 1, 1, 7, 30012, 30004,
				78, "", "", TDA, 19, 0, 5);
		addLunar3RunesBigBox(30178, 9075, 564, 559, 1, 0, 4, 30013, 30008, 78,
				"Dream", "Take a rest and restore hitpoints 3\\n times faster",
				TDA, 20, 0, 5);
		addLunar3RunesSmallBox(30186, 9075, 557, 555, 1, 9, 4, 30006, 30004,
				79, "String Jewellery", "String amulets without wool", TDA, 21,
				0, 5);
		addLunar3RunesLargeBox(30194, 9075, 557, 555, 1, 9, 9, 30006, 30004,
				80, "Stat Restore Pot\\nShare",
				"Share a potion with up to 4 nearby\\nplayers", TDA, 22, 0, 5);
		addLunar3RunesSmallBox(30202, 9075, 554, 555, 1, 6, 6, 30003, 30004,
				81, "Magic Imbue", "Combine runes without a talisman", TDA, 23,
				0, 5);
		addLunar3RunesBigBox(30210, 9075, 561, 557, 2, 1, 14, 30010, 30006, 82,
				"Fertile Soil",
				"Fertilise a farming patch with super\\ncompost", TDA, 24, 4, 2);
		addLunar3RunesBigBox(30218, 9075, 557, 555, 2, 11, 9, 30006, 30004, 83,
				"Boost Potion Share",
				"Shares a potion with up to 4 nearby\\nplayers", TDA, 25, 0, 5);
		addLunar3RunesSmallBox(30226, 9075, 563, 555, 2, 2, 9, 30012, 30004,
				84, "", "", TDA, 26, 0, 5);
		addLunar3RunesLargeBox(30234, 9075, 563, 555, 1, 2, 13, 30012, 30004,
				85, "", "", TDA, 27, 0, 5);
		addLunar3RunesSmallBox(30242, 9075, 557, 561, 2, 14, 0, 30006, 30010,
				85, "Plank Make", "Turn Logs into planks", TDA, 28, 16, 5);
		/******** Cut Off Limit **********/
		addLunar3RunesSmallBox(30250, 9075, 563, 555, 2, 2, 9, 30012, 30004,
				86, "", "", TDA, 29, 0, 5);
		addLunar3RunesSmallBox(30258, 9075, 563, 555, 2, 2, 14, 30012, 30004,
				87, "", "", TDA, 30, 0, 5);
		addLunar3RunesSmallBox(30266, 9075, 563, 555, 2, 2, 7, 30012, 30004,
				88, "", "", TDA, 31, 0, 5);
		addLunar3RunesBigBox(30274, 9075, 563, 555, 2, 2, 15, 30012, 30004, 89,
				"", "", TDA, 32, 0, 5);
		addLunar3RunesBigBox(
				30282,
				9075,
				563,
				561,
				2,
				1,
				0,
				30012,
				30010,
				90,
				"Energy Transfer",
				"Spend hitpoints and SA Energy to\\n give another player hitpoints and run energy",
				TDA, 33, 8, 2);
		addLunar3RunesBigBox(30290, 9075, 563, 565, 2, 2, 0, 30012, 30014, 91,
				"Heal Other",
				"Transfer up to 75% of hitpoints\\n to another player", TDA,
				34, 8, 2);
		addLunar3RunesBigBox(30298, 9075, 560, 557, 2, 1, 9, 30009, 30006, 92,
				"Vengeance Other",
				"Allows another player to rebound\\ndamage to an opponent",
				TDA, 35, 8, 2);
		addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9, 30009, 30006,
				93, "Vengeance", "Rebound damage to an opponent", TDA, 36, 0, 5);
		addLunar3RunesBigBox(30314, 9075, 565, 563, 3, 2, 5, 30014, 30012, 94,
				"Heal Group", "Transfer up to 75% of hitpoints to a group",
				TDA, 37, 0, 5);
		addLunar3RunesBigBox(30322, 9075, 564, 563, 2, 1, 0, 30013, 30012, 95,
				"Spellbook Swap",
				"Change to another spellbook for 1\\nspell cast", TDA, 38, 0, 5);
	}

	public static void constructLunar() {
		RSInterface Interface = addInterface(29999);
		Interface.totalChildren(80);
		setBounds(30000, 11, 10, 0, Interface);
		setBounds(30017, 40, 9, 1, Interface);
		setBounds(30025, 71, 12, 2, Interface);
		setBounds(30032, 103, 10, 3, Interface);
		setBounds(30040, 135, 12, 4, Interface);
		setBounds(30048, 165, 10, 5, Interface);
		setBounds(30056, 8, 38, 6, Interface);
		setBounds(30064, 39, 39, 7, Interface);
		setBounds(30075, 71, 39, 8, Interface);
		setBounds(30083, 103, 39, 9, Interface);
		setBounds(30091, 135, 39, 10, Interface);
		setBounds(30099, 165, 37, 11, Interface);
		setBounds(30106, 12, 68, 12, Interface);
		setBounds(30114, 42, 68, 13, Interface);
		setBounds(30122, 71, 68, 14, Interface);
		setBounds(30130, 103, 68, 15, Interface);
		setBounds(30138, 135, 68, 16, Interface);
		setBounds(30146, 165, 68, 17, Interface);
		setBounds(30154, 14, 97, 18, Interface);
		setBounds(30162, 42, 97, 19, Interface);
		setBounds(30170, 71, 97, 20, Interface);
		setBounds(30178, 101, 97, 21, Interface);
		setBounds(30186, 135, 98, 22, Interface);
		setBounds(30194, 168, 98, 23, Interface);
		setBounds(30202, 11, 125, 24, Interface);
		setBounds(30210, 42, 124, 25, Interface);
		setBounds(30218, 74, 125, 26, Interface);
		setBounds(30226, 103, 125, 27, Interface);
		setBounds(30234, 135, 125, 28, Interface);
		setBounds(30242, 164, 126, 29, Interface);
		setBounds(30250, 10, 155, 30, Interface);
		setBounds(30258, 42, 155, 31, Interface);
		setBounds(30266, 71, 155, 32, Interface);
		setBounds(30274, 103, 155, 33, Interface);
		setBounds(30282, 136, 155, 34, Interface);
		setBounds(30290, 165, 155, 35, Interface);
		setBounds(30298, 13, 185, 36, Interface);
		setBounds(30306, 42, 185, 37, Interface);
		setBounds(30314, 71, 184, 38, Interface);
		setBounds(30322, 104, 184, 39, Interface);
		setBounds(30001, 6, 184, 40, Interface);// hover
		setBounds(30018, 5, 176, 41, Interface);// hover
		setBounds(30026, 5, 176, 42, Interface);// hover
		setBounds(30033, 5, 163, 43, Interface);// hover
		setBounds(30041, 5, 176, 44, Interface);// hover
		setBounds(30049, 5, 176, 45, Interface);// hover
		setBounds(30057, 5, 176, 46, Interface);// hover
		setBounds(30065, 5, 176, 47, Interface);// hover
		setBounds(30076, 5, 163, 48, Interface);// hover
		setBounds(30084, 5, 176, 49, Interface);// hover
		setBounds(30092, 5, 176, 50, Interface);// hover
		setBounds(30100, 5, 176, 51, Interface);// hover
		setBounds(30107, 5, 176, 52, Interface);// hover
		setBounds(30115, 5, 163, 53, Interface);// hover
		setBounds(30123, 5, 176, 54, Interface);// hover
		setBounds(30131, 5, 163, 55, Interface);// hover
		setBounds(30139, 5, 163, 56, Interface);// hover
		setBounds(30147, 5, 163, 57, Interface);// hover
		setBounds(30155, 5, 176, 58, Interface);// hover
		setBounds(30163, 5, 176, 59, Interface);// hover
		setBounds(30171, 5, 176, 60, Interface);// hover
		setBounds(30179, 5, 163, 61, Interface);// hover
		setBounds(30187, 5, 176, 62, Interface);// hover
		setBounds(30195, 5, 149, 63, Interface);// hover
		setBounds(30203, 5, 176, 64, Interface);// hover
		setBounds(30211, 5, 163, 65, Interface);// hover
		setBounds(30219, 5, 163, 66, Interface);// hover
		setBounds(30227, 5, 176, 67, Interface);// hover
		setBounds(30235, 5, 149, 68, Interface);// hover
		setBounds(30243, 5, 176, 69, Interface);// hover

		setBounds(30251, 5, 5, 70, Interface);// hover
		setBounds(30259, 5, 5, 71, Interface);// hover
		setBounds(30267, 5, 5, 72, Interface);// hover
		setBounds(30275, 5, 5, 73, Interface);// hover
		setBounds(30283, 5, 5, 74, Interface);// hover
		setBounds(30291, 5, 5, 75, Interface);// hover
		setBounds(30299, 5, 5, 76, Interface);// hover
		setBounds(30307, 5, 5, 77, Interface);// hover
		setBounds(30323, 5, 5, 78, Interface);// hover
		setBounds(30315, 5, 5, 79, Interface);// hover
	}

	public static void pkingTab(TextDrawingArea[] tda) {
		RSInterface Interface = addTabInterface(5400);
		addSprite(5401, 0, "Interfaces/PkingTele/BACKGROUND");
		addHoverButton(5402, "Interfaces/PkingTele/INFO", 1, 16, 16,
				"Close Teleport Tab", -1, 5403, 1);
		addHoverButton(5404, "Interfaces/PkingTele/TELE", 0, 106, 38,
				"Teleport to Varrock Pk", -1, 5405, 1);
		addHoverButton(5406, "Interfaces/PkingTele/TELE", 1, 106, 38,
				"Teleport to Mage Bank", -1, 5407, 1);
		addHoverButton(5408, "Interfaces/PkingTele/TELE", 2, 106, 38,
				"Teleport to FunPk", -1, 5409, 1);
		addHoverButton(5410, "Interfaces/PkingTele/TELE", 3, 106, 38,
				"Teleport to East Dragons", -1, 5411, 1);
		addHoverButton(5412, "Interfaces/PkingTele/TELE", 4, 106, 38,
				"Teleport to the Gates", -1, 5413, 1);
		addHoverButton(5414, "Interfaces/PkingTele/TELE", 5, 106, 38,
				"Teleport to GDZ", -1, 5415, 1);
		setChildren(8, Interface);
		setBounds(5401, 6, 20, 0, Interface);
		setBounds(5402, 482, 23, 1, Interface);
		setBounds(5404, 31, 109, 2, Interface);
		setBounds(5406, 161, 109, 3, Interface);
		setBounds(5408, 288, 109, 4, Interface);
		setBounds(5410, 31, 239, 5, Interface);
		setBounds(5412, 159, 239, 6, Interface);
		setBounds(5414, 290, 239, 7, Interface);
	}

	public static void skillTabInfo(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(49500);
		RSInterface list = addTabInterface(26025);
		addText(540, "", tda, 2, 0xeb981f, false, true);
		addText(563, "", tda, 2, 0xff9900, false, true);
		// addText(682, "Quests:", tda, 2, 0xff9900, false, true);
		addSprite(26022, 4, "Interfaces/Quest/SPRITE");
		addText(26023, "", tda, 0, 0xeb981f, false, true);
		addSprite(26024, 5, "Interfaces/Quest/SPRITE");
		tab.totalChildren(6);
		tab.child(0, 540, 6, 5);
		tab.child(1, 26024, 0, 25);
		tab.child(2, 26025, 6, 24);
		tab.child(3, 26022, 0, 22);
		tab.child(4, 26022, 0, 249);
		tab.child(5, 26023, 4, 251);
		/* List/scrollbar */
		for (int i = 26026; i <= 26125; i++) {
			addClickableText(i, "", "Show", tda, 0, 0xff0000, false, true, 150);
		}
		list.totalChildren(101);
		list.child(0, 563, 2, 2);
		// list.child(101, 682, 2, 101);
		for (int id = 1, cid = 26026; id <= 100 && cid <= 26125; id++, cid++) {
			list.childY[1] = 5;
			list.child(id, cid, 9, list.childY[id - 1] + 14);
		}
		list.width = 168;
		list.height = 225;
		// list.scrollMax = 1320;
	}

	public static void questTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(638);
		RSInterface list = addTabInterface(16025);
		addText(640, "", tda, 2, 0xeb981f, false, true);
		addText(663, "", tda, 2, 0xff9900, false, true);
		// addText(682, "Quests:", tda, 2, 0xff9900, false, true);
		addSprite(16022, 4, "Interfaces/Quest/SPRITE");
		addText(16023, "", tda, 0, 0xeb981f, false, true);
		addSprite(16024, 5, "Interfaces/Quest/SPRITE");
		tab.totalChildren(6);
		tab.child(0, 640, 6, 5);
		tab.child(1, 16024, 0, 25);
		tab.child(2, 16025, 6, 24);
		tab.child(3, 16022, 0, 22);
		tab.child(4, 16022, 0, 249);
		tab.child(5, 16023, 4, 251);
		/* List/scrollbar */
		for (int i = 16026; i <= 16125; i++) {
			addClickableText(i, "", "Show", tda, 0, 0xff0000, false, true, 150);
		}
		list.totalChildren(101);
		list.child(0, 663, 2, 2);
		// list.child(101, 682, 2, 101);
		for (int id = 1, cid = 16026; id <= 100 && cid <= 16125; id++, cid++) {
			list.childY[1] = 5;
			list.child(id, cid, 9, list.childY[id - 1] + 14);
		}
		list.width = 168;
		list.height = 225;
		// list.scrollMax = 1320;
	}

	public static void SummonTab(TextDrawingArea[] wid) {
		final String dir = "Interfaces/Summoning/Tab/SPRITE";
		RSInterface rsi = addTabInterface(18017);
		addButton(18018, 0, dir, 143, 13, "Cast special", 1);
		addText(18019, "S P E C I A L  M O V E", wid, 0, WHITE_TEXT, false,
				false);
		addSprite(18020, 1, dir);
		configureHead(18021, 75, 55, 800, 40, 1800);
		addSprite(18022, 2, dir);
		addConfigButton(18023, 18017, 4, 3, dir, 30, 31, "Cast special", 0, 1,
				330);
		addText(18024, "", wid, 0, ORANGE_TEXT, false, true);
		addSprite(18025, 50, dir);
		addConfigButton(18026, 18017, 70, 60, dir, 29, 39, "", 0, 1, 333);
		addSprite(18027, 8, dir);
		addText(18028, "Familiar Name", wid, 2, ORANGE_TEXT, true, false);
		addHoverButton(18029, dir, 9, 38, 38, "Withdraw BoB", -1, 18030, 1);
		addHoveredButton(18030, dir, 10, 38, 38, 18031);
		addHoverButton(18032, dir, 11, 38, 38, "Renew familiar", -1, 18033, 1);
		addHoveredButton(18033, dir, 12, 38, 38, 18034);
		addHoverButton(18035, dir, 13, 38, 38, "Call follower", -1, 18036, 1);
		addHoveredButton(18036, dir, 14, 38, 38, 18037);
		addHoverButton(18038, dir, 15, 38, 38, "Dismiss familiar", -1, 18039, 1);
		addHoveredButton(18039, dir, 16, 38, 38, 18040);
		addSprite(18041, 17, dir);
		addSprite(18042, 18, dir);
		addText(18043, "", wid, 0, GREY_TEXT, false, true);
		addSprite(18044, 19, dir);
		addText(18045, "", wid, 0, GREY_TEXT, false, true);
		setChildren(24, rsi);
		setBounds(18018, 23, 10, 0, rsi);
		setBounds(18019, 43, 12, 1, rsi);
		setBounds(18020, 10, 32, 2, rsi);
		setBounds(18021, 63, 60, 3, rsi);
		setBounds(18022, 11, 32, 4, rsi);
		setBounds(18023, 14, 35, 5, rsi);
		setBounds(18024, 14, 69, 6, rsi);
		setBounds(18025, 138, 32, 7, rsi);
		setBounds(18026, 143, 36, 8, rsi);
		setBounds(18027, 12, 144, 9, rsi);
		setBounds(18028, 93, 146, 10, rsi);
		setBounds(18029, 23, 168, 11, rsi);
		setBounds(18030, 23, 168, 12, rsi);
		setBounds(18032, 75, 168, 13, rsi);
		setBounds(18033, 75, 168, 14, rsi);
		setBounds(18035, 23, 213, 15, rsi);
		setBounds(18036, 23, 213, 16, rsi);
		setBounds(18038, 75, 213, 17, rsi);
		setBounds(18039, 75, 213, 18, rsi);
		setBounds(18041, 130, 168, 19, rsi);
		setBounds(18042, 153, 170, 20, rsi);
		setBounds(18043, 148, 198, 21, rsi);
		setBounds(18044, 149, 213, 22, rsi);
		setBounds(18045, 145, 241, 23, rsi);
	}

	public static void clanChatTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(18128);
		addHoverButton(18129, "/Interfaces/Clan Chat/SPRITE", 6, 72, 32,
				"Join Chat", -1, 18130, 5);
		addHoveredButton(18130, "/Interfaces/Clan Chat/SPRITE", 7, 72, 32,
				18131);
		addHoverButton(18132, "/Interfaces/Clan Chat/SPRITE", 6, 72, 32,
				"Clan Setup", -1, 18133, 5);
		addHoveredButton(18133, "/Interfaces/Clan Chat/SPRITE", 7, 72, 32,
				18134);
		// addButton(18250, 0, "/Clan Chat/Lootshare", "Toggle lootshare");
		addText(18135, "Join Chat", tda, 0, 0xff9b00, true, true);
		addText(18136, "Clan Setup", tda, 0, 0xff9b00, true, true);
		addSprite(18137, 37, "/Interfaces/Clan Chat/SPRITE");
		addText(18138, "Clan Chat", tda, 1, 0xff9b00, true, true);
		addText(18139, "Talking in: Not in chat", tda, 0, 0xff9b00, false, true);
		addText(18140, "Owner: None", tda, 0, 0xff9b00, false, true);
		tab.totalChildren(13);
		tab.child(0, 16126, 0, 221);
		tab.child(1, 16126, 0, 59);
		tab.child(2, 18137, 0, 62);
		tab.child(3, 18143, 0, 62);
		tab.child(4, 18129, 15, 226);
		tab.child(5, 18130, 15, 226);
		tab.child(6, 18132, 103, 226);
		tab.child(7, 18133, 103, 226);
		tab.child(8, 18135, 51, 237);
		tab.child(9, 18136, 139, 237);
		tab.child(10, 18138, 95, 1);
		tab.child(11, 18139, 10, 23);
		tab.child(12, 18140, 25, 38);
		/* Text area */
		RSInterface list = addTabInterface(18143);
		list.totalChildren(100);
		for (int i = 18144; i <= 18244; i++) {
			addText(i, "", tda, 0, 0xffffff, false, true);
		}
		for (int id = 18144, i = 0; id <= 18243 && i <= 99; id++, i++) {
			interfaceCache[id].actions = new String[] { "Edit Rank", "Kick",
			"Ban" };
			list.children[i] = id;
			list.childX[i] = 5;
			for (int id2 = 18144, i2 = 1; id2 <= 18243 && i2 <= 99; id2++, i2++) {
				list.childY[0] = 2;
				list.childY[i2] = list.childY[i2 - 1] + 14;
			}
		}
		list.height = 158;
		list.width = 174;
		list.scrollMax = 1405;
	}

	public static void clanChatSetup(TextDrawingArea[] tda) {
		RSInterface rsi = addInterface(18300);
		rsi.totalChildren(12 + 15);
		int count = 0;
		/* Background */
		addSprite(18301, 1, "/Interfaces/Clan Chat/sprite");
		rsi.child(count++, 18301, 14, 18);
		/* Close button */
		addButton(18302, 0, "/Interfaces/Clan Chat/close", "Close");
		interfaceCache[18302].atActionType = 3;
		rsi.child(count++, 18302, 475, 26);
		/* Clan Setup title */
		addText(18303, "Clan Setup", tda, 2, 0xFF981F, true, true);
		rsi.child(count++, 18303, 256, 26);
		/* Setup buttons */
		String[] titles = { "Clan name:", "Who can enter chat?",
				"Who can talk on chat?", "Who can kick on chat?",
		"Who can ban on chat?" };
		String[] defaults = { "Chat Disabled", "Anyone", "Anyone", "Anyone",
		"Anyone" };
		String[] whoCan = { "Anyone", "Recruit", "Corporal", "Sergeant",
				"Lieutenant", "Captain", "General", "Only Me" };
		for (int index = 0, id = 18304, y = 50; index < titles.length; index++, id += 3, y += 40) {
			addButton(id, 2, "/Interfaces/Clan Chat/sprite", "");
			interfaceCache[id].atActionType = 0;
			if (index > 0) {
				interfaceCache[id].actions = whoCan;
			} else {
				interfaceCache[id].actions = new String[] { "Change title",
				"Delete clan" };
				;
			}
			addText(id + 1, titles[index], tda, 0, 0xFF981F, true, true);
			addText(id + 2, defaults[index], tda, 1, 0xFFFFFF, true, true);
			rsi.child(count++, id, 25, y);
			rsi.child(count++, id + 1, 100, y + 4);
			rsi.child(count++, id + 2, 100, y + 17);
		}
		/* Table */
		addSprite(18319, 5, "/Interfaces/Clan Chat/sprite");
		rsi.child(count++, 18319, 197, 70);
		/* Labels */
		int id = 18320;
		int y = 74;
		addText(id, "Ranked Members", tda, 2, 0xFF981F, false, true);
		rsi.child(count++, id++, 202, y);
		addText(id, "Banned Members", tda, 2, 0xFF981F, false, true);
		rsi.child(count++, id++, 339, y);
		/* Ranked members list */
		RSInterface list = addInterface(id++);
		int lines = 100;
		list.totalChildren(lines);
		String[] ranks = { "Demote", "Recruit", "Corporal", "Sergeant",
				"Lieutenant", "Captain", "General", "Owner" };
		list.childY[0] = 2;
		// System.out.println(id);
		for (int index = id; index < id + lines; index++) {
			addText(index, "", tda, 1, 0xffffff, false, true);
			interfaceCache[index].actions = ranks;
			list.children[index - id] = index;
			list.childX[index - id] = 2;
			list.childY[index - id] = (index - id > 0 ? list.childY[index - id
			                                                        - 1] + 14 : 0);
		}
		id += lines;
		list.width = 119;
		list.height = 210;
		list.scrollMax = (lines * 14) + 2;
		rsi.child(count++, list.id, 199, 92);
		/* Banned members list */
		list = addInterface(id++);
		list.totalChildren(lines);
		list.childY[0] = 2;
		// System.out.println(id);
		for (int index = id; index < id + lines; index++) {
			addText(index, "", tda, 1, 0xffffff, false, true);
			interfaceCache[index].actions = new String[] { "Unban" };
			list.children[index - id] = index;
			list.childX[index - id] = 0;
			list.childY[index - id] = (index - id > 0 ? list.childY[index - id
			                                                        - 1] + 14 : 0);
		}
		id += lines;
		list.width = 119;
		list.height = 210;
		list.scrollMax = (lines * 14) + 2;
		rsi.child(count++, list.id, 339, 92);
		/* Table info text */
		y = 47;
		addText(id, "You can manage both ranked and banned members here.", tda,
				0, 0xFF981F, true, true);
		rsi.child(count++, id++, 337, y);
		addText(id, "Right click on a name to edit the member.", tda, 0,
				0xFF981F, true, true);
		rsi.child(count++, id++, 337, y + 11);
		/* Add ranked member button */
		y = 75;
		addButton(id, 0, "/Interfaces/Clan Chat/plus", "Add ranked member");
		interfaceCache[id].atActionType = 5;
		rsi.child(count++, id++, 319, y);
		/* Add banned member button */
		addButton(id, 0, "/Interfaces/Clan Chat/plus", "Add banned member");
		interfaceCache[id].atActionType = 5;
		rsi.child(count++, id++, 459, y);

		/* Hovers */
		int[] clanSetup = { 18302, 18304, 18307, 18310, 18313, 18316, 18526,
				18527 };
		String[] names = { "close", "sprite", "sprite", "sprite", "sprite",
				"sprite", "plus", "plus" };
		int[] ids = { 1, 3, 3, 3, 3, 3, 1, 1 };
		for (int index = 0; index < clanSetup.length; index++) {
			rsi = interfaceCache[clanSetup[index]];
			rsi.disabledHover = imageLoader(ids[index],
					"/Interfaces/Clan Chat/" + names[index]);
		}
	}

	public static void emoteTab() {
		RSInterface tab = addTabInterface(147);
		RSInterface scroll = addTabInterface(148);
		tab.totalChildren(1);
		tab.child(0, 148, 0, 1);
		addButton(168, 0, "Interfaces/Emotes/EMOTE", "Yes", 41, 47);
		addButton(169, 1, "Interfaces/Emotes/EMOTE", "No", 41, 47);
		addButton(164, 2, "Interfaces/Emotes/EMOTE", "Bow", 41, 47);
		addButton(165, 3, "Interfaces/Emotes/EMOTE", "Angry", 41, 47);
		addButton(162, 4, "Interfaces/Emotes/EMOTE", "Think", 41, 47);
		addButton(163, 5, "Interfaces/Emotes/EMOTE", "Wave", 41, 47);
		addButton(13370, 6, "Interfaces/Emotes/EMOTE", "Shrug", 41, 47);
		addButton(171, 7, "Interfaces/Emotes/EMOTE", "Cheer", 41, 47);
		addButton(167, 8, "Interfaces/Emotes/EMOTE", "Beckon", 41, 47);
		addButton(170, 9, "Interfaces/Emotes/EMOTE", "Laugh", 41, 47);
		addButton(13366, 10, "Interfaces/Emotes/EMOTE", "Jump for Joy", 41, 47);
		addButton(13368, 11, "Interfaces/Emotes/EMOTE", "Yawn", 41, 47);
		addButton(166, 12, "Interfaces/Emotes/EMOTE", "Dance", 41, 47);
		addButton(13363, 13, "Interfaces/Emotes/EMOTE", "Jig", 41, 47);
		addButton(13364, 14, "Interfaces/Emotes/EMOTE", "Spin", 41, 47);
		addButton(13365, 15, "Interfaces/Emotes/EMOTE", "Headbang", 41, 47);
		addButton(161, 16, "Interfaces/Emotes/EMOTE", "Cry", 41, 47);
		addButton(11100, 17, "Interfaces/Emotes/EMOTE", "Blow kiss", 41, 47);
		addButton(13362, 18, "Interfaces/Emotes/EMOTE", "Panic", 41, 47);
		addButton(13367, 19, "Interfaces/Emotes/EMOTE", "Raspberry", 41, 47);
		addButton(172, 20, "Interfaces/Emotes/EMOTE", "Clap", 41, 47);
		addButton(13369, 21, "Interfaces/Emotes/EMOTE", "Salute", 41, 47);
		addButton(13383, 22, "Interfaces/Emotes/EMOTE", "Goblin Bow", 41, 47);
		addButton(13384, 23, "Interfaces", "Goblin Salute", 41, 47);
		addButton(667, 24, "Interfaces/Emotes/EMOTE", "Glass Box", 41, 47);
		addButton(6503, 25, "Interfaces/Emotes/EMOTE", "Climb Rope", 41, 47);
		addButton(6506, 26, "Interfaces/Emotes/EMOTE", "Lean On Air", 41, 47);
		addButton(666, 27, "Interfaces/Emotes/EMOTE", "Glass Wall", 41, 47);
		addButton(18464, 28, "Interfaces/Emotes/EMOTE", "Zombie Walk", 41, 47);
		addButton(18465, 29, "Interfaces/Emotes/EMOTE", "Zombie Dance", 41, 47);
		addButton(15166, 30, "Interfaces/Emotes/EMOTE", "Scared", 41, 47);
		addButton(18686, 32, "Interfaces/Emotes/EMOTE", "Skillcape Emote", 41,
				47);
		addConfigButton(154, 147, 32, 33, "EMOTE", 41, 47, "Skillcape Emote",
				0, 1, 700);
		scroll.totalChildren(33);
		scroll.child(0, 168, 10, 7);
		scroll.child(1, 169, 54, 7);
		scroll.child(2, 164, 98, 7);
		scroll.child(3, 165, 137, 7);
		scroll.child(4, 162, 9, 56);
		scroll.child(5, 163, 48, 56);
		scroll.child(6, 13370, 95, 56);
		scroll.child(7, 171, 137, 56);
		scroll.child(8, 167, 7, 105);
		scroll.child(9, 170, 51, 105);
		scroll.child(10, 13366, 95, 105);
		scroll.child(11, 13368, 139, 105);
		scroll.child(12, 166, 6, 154);
		scroll.child(13, 13363, 50, 154);
		scroll.child(14, 13364, 90, 154);
		scroll.child(15, 13365, 135, 154);
		scroll.child(16, 161, 8, 203);
		scroll.child(17, 11100, 51, 203);
		scroll.child(18, 13362, 99, 203);
		scroll.child(19, 13367, 137, 203);
		scroll.child(20, 172, 10, 253);
		scroll.child(21, 13369, 53, 253);
		scroll.child(22, 13383, 88, 253);
		scroll.child(23, 13384, 138, 253);
		scroll.child(24, 667, 2, 303);
		scroll.child(25, 6503, 49, 302);
		scroll.child(26, 6506, 93, 302);
		scroll.child(27, 666, 137, 302);
		scroll.child(28, 18464, 9, 352);
		scroll.child(29, 18465, 50, 352);
		scroll.child(30, 15166, 94, 352);
		scroll.child(32, 18686, 135, 352);
		scroll.child(31, 154, 5, 401);
		scroll.width = 173;
		scroll.height = 258;
		scroll.scrollMax = 403;
	}

	public static void cursesTab(TextDrawingArea[] TDA) {
		RSInterface Interface = addTabInterface(22500);
		int index = 0;
		setChildren(62, Interface);
		addText(22501, "99/99", 0xFF981F, false, false, -1, TDA, 1);
		/* Top Row */
		addPrayer(22503, 0, 83, 49, 7, "Protect Item", 22542);
		setBounds(22503, 2, 5, index, Interface);
		index++;// Glow
		setBounds(22504, 8, 8, index, Interface);
		index++;// Icon
		addPrayer(22505, 0, 84, 49, 4, "Sap Warrior", 22544);
		setBounds(22505, 40, 5, index, Interface);
		index++;// Glow
		setBounds(22506, 47, 12, index, Interface);
		index++;// Icon
		addPrayer(22507, 0, 85, 51, 5, "Sap Ranger", 22546);
		setBounds(22507, 76, 5, index, Interface);
		index++;// Glow
		setBounds(22508, 82, 11, index, Interface);
		index++;// Icon
		addPrayer(22509, 0, 101, 53, 3, "Sap Mage", 22548);
		setBounds(22509, 113, 5, index, Interface);
		index++;// Glow
		setBounds(22510, 116, 8, index, Interface);
		index++;// Icon
		addPrayer(22511, 0, 102, 55, 2, "Sap Spirit", 22550);
		setBounds(22511, 150, 5, index, Interface);
		index++;// Glow
		setBounds(22512, 155, 10, index, Interface);
		index++;// Icon
		/* 2nd Row */
		addPrayer(22513, 0, 86, 58, 18, "Berserker", 22552);
		setBounds(22513, 2, 45, index, Interface);
		index++;// Glow
		setBounds(22514, 9, 48, index, Interface);
		index++;// Icon
		addPrayer(22515, 0, 87, 61, 15, "Deflect Summoning", 22554);
		setBounds(22515, 39, 45, index, Interface);
		index++;// Glow
		setBounds(22516, 42, 47, index, Interface);
		index++;// Icon
		addPrayer(22517, 0, 88, 64, 17, "Deflect Magic", 22556);
		setBounds(22517, 76, 45, index, Interface);
		index++;// Glow
		setBounds(22518, 79, 48, index, Interface);
		index++;// Icon
		addPrayer(22519, 0, 89, 67, 16, "Deflect Missiles", 22558);
		setBounds(22519, 113, 45, index, Interface);
		index++;// Glow
		setBounds(22520, 116, 48, index, Interface);
		index++;// Icon
		addPrayer(22521, 0, 90, 70, 6, "Deflect Melee", 22560);
		setBounds(22521, 151, 45, index, Interface);
		index++;// Glow
		setBounds(22522, 154, 48, index, Interface);
		index++;// Icon
		/* 3rd Row */
		addPrayer(22523, 0, 91, 73, 9, "Leech Attack", 22562);
		setBounds(22523, 2, 82, index, Interface);
		index++;// Glow
		setBounds(22524, 6, 86, index, Interface);
		index++;// Icon
		addPrayer(22525, 0, 103, 75, 10, "Leech Ranged", 22564);
		setBounds(22525, 40, 82, index, Interface);
		index++;// Glow
		setBounds(22526, 42, 86, index, Interface);
		index++;// Icon
		addPrayer(22527, 0, 104, 77, 11, "Leech Magic", 22566);
		setBounds(22527, 77, 82, index, Interface);
		index++;// Glow
		setBounds(22528, 79, 86, index, Interface);
		index++;// Icon
		addPrayer(22529, 0, 92, 79, 12, "Leech Defence", 22568);
		setBounds(22529, 114, 83, index, Interface);
		index++;// Glow
		setBounds(22530, 119, 87, index, Interface);
		index++;// Icon
		addPrayer(22531, 0, 93, 81, 13, "Leech Strength", 22570);
		setBounds(22531, 153, 83, index, Interface);
		index++;// Glow
		setBounds(22532, 156, 86, index, Interface);
		index++;// Icon
		/* Bottom Row */
		addPrayer(22533, 0, 94, 83, 14, "Leech Energy", 22572);
		setBounds(22533, 2, 120, index, Interface);
		index++;// Glow
		setBounds(22534, 7, 125, index, Interface);
		index++;// Icon
		addPrayer(22535, 0, 95, 85, 19, "Leech Special Attack", 22574);
		setBounds(22535, 40, 120, index, Interface);
		index++;// Glow
		setBounds(22536, 45, 124, index, Interface);
		index++;// Icon
		addPrayer(22537, 0, 96, 88, 1, "Wrath", 22576);
		setBounds(22537, 78, 120, index, Interface);
		index++;// Glow
		setBounds(22538, 86, 124, index, Interface);
		index++;// Icon
		addPrayer(22539, 0, 97, 91, 8, "Soul Split", 22578);
		setBounds(22539, 114, 120, index, Interface);
		index++;// Glow
		setBounds(22540, 120, 125, index, Interface);
		index++;// Icon
		addPrayer(22541, 0, 105, 94, 0, "Turmoil", 22580);
		setBounds(22541, 151, 120, index, Interface);
		index++;// Glow
		setBounds(22542, 153, 127, index, Interface);
		index++;// Icon
		/* Prayer Icon/Text */
		addSprite(22502, 0, "Interfaces/Curses/ICON");
		setBounds(22501, 85, 241, index, Interface);
		index++;// Text
		setBounds(22502, 65, 241, index, Interface);
		index++;// Icon
		/* Tooltips/Hover Boxes */
		addTooltip(22543,
				"Level 50\nProtect Item\nKeep 1 extra item if you die", 100,
				150);
		addTooltip(
				22544,
				"Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincreasing to 20% over time",
				100, 150);
		addTooltip(
				22546,
				"Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%\nover time",
				100, 175);
		addTooltip(
				22548,
				"Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\nover time",
				100, 175);
		addTooltip(22550,
				"Level 56\nSap Spirit\nDrains enenmy special attack\nenergy",
				100, 175);
		addTooltip(22552, "Level 59\nBerserker\nBoosted stats last 15% longer",
				100, 175);
		addTooltip(
				22554,
				"Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, prevents the\nuse of a familiar's special\nattack, and can deflect some of\ndamage back to the attacker",
				125, 175);
		addTooltip(
				22556,
				"Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some of the\ndamage back to the attacker",
				100, 175);
		addTooltip(
				22558,
				"Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect some of the\ndamage back to the attacker",
				100, 175);
		addTooltip(
				22560,
				"Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of the\ndamage back to the attacker",
				100, 175);
		addTooltip(
				22562,
				"Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while draining\nenemy Attack by 10%,\nincreasing to 25% over time",
				100, 175);
		addTooltip(
				22564,
				"Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time,\nwhile draining enemy Ranged by\n10%, increasing to 25% over\ntime",
				113, 175);
		addTooltip(
				22566,
				"Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while draining\nenemy Magic by 10%, increasing\nto 25% over time",
				100, 175);
		addTooltip(
				22568,
				"Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time,\nwhile draining enemy Defence by\n10%, increasing to 25% over\ntime",
				113, 180);
		addTooltip(
				22570,
				"Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time,\nwhile draining enemy Strength by\n10%, increasing to 25% over\ntime",
				113, 180);
		addTooltip(
				22572,
				"Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own",
				113, 180);
		addTooltip(
				22574,
				"Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while increasing your\nown",
				113, 180);
		addTooltip(
				22576,
				"Level 89\nWrath\nInflicts damage to nearby\ntargets if you die",
				113, 180);
		addTooltip(
				22578,
				"Level 92\nSoul Split\n1/4 of damage dealt is\nalso removed from\nopponent's Prayer and added to\nyour Hitpoints",
				113, 180);
		addTooltip(
				22580,
				"Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nlevel, and Strength by 23% plus\n10% of enemy's level",
				113, 180);
		setBounds(22543, 10, 40, index, Interface);
		index++;
		setBounds(22544, 20, 40, index, Interface);
		index++;
		setBounds(22546, 20, 40, index, Interface);
		index++;
		setBounds(22548, 20, 40, index, Interface);
		index++;
		setBounds(22550, 20, 40, index, Interface);
		index++;
		setBounds(22552, 10, 80, index, Interface);
		index++;
		setBounds(22554, 10, 80, index, Interface);
		index++;
		setBounds(22556, 10, 80, index, Interface);
		index++;
		setBounds(22558, 10, 80, index, Interface);
		index++;
		setBounds(22560, 10, 80, index, Interface);
		index++;
		setBounds(22562, 10, 120, index, Interface);
		index++;
		setBounds(22564, 10, 120, index, Interface);
		index++;
		setBounds(22566, 10, 120, index, Interface);
		index++;
		setBounds(22568, 5, 120, index, Interface);
		index++;
		setBounds(22570, 5, 120, index, Interface);
		index++;
		setBounds(22572, 10, 160, index, Interface);
		index++;
		setBounds(22574, 10, 160, index, Interface);
		index++;
		setBounds(22576, 10, 160, index, Interface);
		index++;
		setBounds(22578, 10, 160, index, Interface);
		index++;
		setBounds(22580, 10, 160, index, Interface);
		index++;
	}

	public static void SkillTeleportInterface(
			TextDrawingArea[] paramArrayOfTextDrawingArea)
	/*       */{
		/* 3164 */RSInterface localRSInterface = addTabInterface(33000);
		/* 3165 */setChildren(40, localRSInterface);
		/*       */
		/* 3167 */addSprite(33001, 0, "Interfaces/SkillTeleport/SPRITE");
		/* 3168 */addHover(33002, 3, 0, 33003, 0,
				"Interfaces/SkillTeleport/EXIT", 21, 21, "Exit");
		/* 3169 */addHovered(33003, 1, "Interfaces/SkillTeleport/EXIT", 21, 21,
				33004);
		/* 3170 */addButton(33005, 0, "FISHING", "Teleport to Fishing", 33005,
				1, 110, 33);
		/* 3171 */addText(33006, "", 14929103, true, true, 115, 2);
		/* 3172 */addButton(33007, 0, "COOKING", "Teleport to Cooking", 33007,
				1, 110, 33);
		/* 3173 */addText(33008, "", 14929103, true, true, 115, 2);
		/* 3174 */addButton(33009, 0, "MINING", "Teleport to Mining", 33009, 1,
				110, 33);
		/* 3175 */addText(33010, "", 14929103, true, true, 115, 2);
		/* 3176 */addButton(33011, 0, "SMITHING", "Teleport to Smithing",
				33011, 1, 112, 33);
		/* 3177 */addText(33012, "", 14929103, true, true, 115, 2);
		/* 3178 */addButton(33013, 0, "WOODCUTTING", "Teleport to Woodcutting",
				33013, 1, 130, 33);
		/* 3179 */addText(33014, "", 14929103, true, true, 115, 2);
		/* 3180 */addButton(33015, 0, "FIREMAKING", "Teleport to Firemaking",
				33015, 1, 120, 33);
		/* 3181 */addText(33016, "", 14929103, true, true, 115, 2);
		/* 3182 */addButton(33017, 0, "FARMING", "Teleport to Farming", 33017,
				1, 110, 33);
		/* 3183 */addText(33018, "", 14929103, true, true, 115, 2);
		/* 3184 */addButton(33019, 0, "HERBLORE", "Teleport to Herblore",
				33019, 1, 110, 33);
		/* 3185 */addText(33020, "", 14929103, true, true, 115, 2);
		/* 3186 */addButton(33021, 0, "AGILITY", "Teleport to Agility", 33021,
				1, 110, 33);
		/* 3187 */addText(33022, "", 14929103, true, true, 115, 2);
		/* 3188 */addButton(33023, 0, "HUNTER", "Teleport to Hunter", 33023, 1,
				110, 33);
		/* 3189 */addText(33024, "", 14929103, true, true, 115, 2);
		/*       */
		/* 3191 */addButton(33025, 0, "RUNECRAFTING",
				"Teleport to Runecrafting", 33025, 1, 120, 33);
		/* 3192 */addText(33026, "", 14929103, true, true, 115, 2);
		/*       */
		/* 3194 */addButton(33027, 0, "FLETCHING", "Teleport to Fletching",
				33027, 1, 111, 33);
		/* 3195 */addText(33028, "", 14929103, true, true, 115, 2);
		/*       */
		/* 3197 */addButton(33029, 0, "CRAFTING", "Teleport to Crafting",
				33029, 1, 110, 33);
		/* 3198 */addText(33030, "", 14929103, true, true, 115, 2);
		/*       */
		/* 3200 */addButton(33031, 0, "SUMMONING", "Teleport to Summoning",
				33031, 1, 110, 33);
		/* 3201 */addText(33032, "", 14929103, true, true, 115, 2);
		/*       */
		/* 3203 */addButton(33035, 0, "DUNGEONEERING",
				"Teleport to Dungeoneering", 33035, 1, 120, 33);
		/* 3204 */addText(33036, "", 14929103, true, true, 115, 2);
		/*       */
		/* 3206 */addButton(33033, 0, "", "", 33033, 1, 120, 33);
		/* 3207 */addText(33034, "", 14929103, true, true, 115, 2);
		/*       */
		/* 3209 */addButton(33037, 0, "THIEVING", "Teleport to Thieving",
				33037, 1, 120, 33);
		/* 3210 */addText(33038, "", 14929103, true, true, 115, 2);
		/* 3211 */addButton(33039, 0, "SLAYER", "Teleport to Slayer", 33039, 1,
				120, 33);
		/* 3212 */addText(33040, "", 14929103, true, true, 115, 2);
		/*       */
		/* 3214 */// addText(33041, "Total Level: 2000", 14929103, true, true,
		// 115, 2);
		/*       */
		/* 3216 */setBounds(33001, 4, 2, 0, localRSInterface);
		/* 3217 */setBounds(33002, 477, 6, 1, localRSInterface);
		/* 3218 */setBounds(33003, 477, 6, 2, localRSInterface);
		/*       */
		/* 3220 */setBounds(33005, 50, 90, 3, localRSInterface);
		/* 3221 */setBounds(33006, 115, 100, 4, localRSInterface);
		/*       */
		/* 3223 */setBounds(33007, 50, 130, 5, localRSInterface);
		/* 3224 */setBounds(33008, 115, 140, 6, localRSInterface);
		/*       */
		/* 3226 */setBounds(33009, 50, 170, 7, localRSInterface);
		/* 3227 */setBounds(33010, 115, 180, 8, localRSInterface);
		/*       */
		/* 3229 */setBounds(33011, 50, 210, 9, localRSInterface);
		/* 3230 */setBounds(33012, 115, 220, 10, localRSInterface);
		/*       */
		/* 3232 */setBounds(33013, 160, 90, 11, localRSInterface);
		/* 3233 */setBounds(33014, 235, 100, 12, localRSInterface);
		/*       */
		/* 3235 */setBounds(33015, 160, 130, 13, localRSInterface);
		/* 3236 */setBounds(33016, 235, 140, 14, localRSInterface);
		/*       */
		/* 3238 */setBounds(33017, 160, 170, 15, localRSInterface);
		/* 3239 */setBounds(33018, 235, 180, 16, localRSInterface);
		/*       */
		/* 3241 */setBounds(33019, 160, 210, 17, localRSInterface);
		/* 3242 */setBounds(33020, 235, 220, 18, localRSInterface);
		/*       */
		/* 3244 */setBounds(33021, 285, 90, 19, localRSInterface);
		/* 3245 */setBounds(33022, 340, 100, 20, localRSInterface);
		/*       */
		/* 3247 */setBounds(33023, 50, 250, 21, localRSInterface);
		/* 3248 */setBounds(33024, 110, 260, 22, localRSInterface);
		/*       */
		/* 3250 */setBounds(33025, 160, 250, 23, localRSInterface);
		/* 3251 */setBounds(33026, 235, 260, 24, localRSInterface);
		/*       */
		/* 3253 */setBounds(33027, 285, 130, 25, localRSInterface);
		/* 3254 */setBounds(33028, 350, 140, 26, localRSInterface);
		/*       */
		/* 3256 */setBounds(33029, 285, 170, 27, localRSInterface);
		/* 3257 */setBounds(33030, 350, 180, 28, localRSInterface);
		/*       */
		/* 3259 */setBounds(33031, 285, 210, 29, localRSInterface);
		/* 3260 */setBounds(33032, 360, 220, 30, localRSInterface);
		/*       */
		/* 3262 */setBounds(33033, 285, 250, 31, localRSInterface);
		/* 3263 */setBounds(33034, 360, 260, 32, localRSInterface);
		/*       */
		/* 3265 */setBounds(33035, 395, 90, 33, localRSInterface);
		/* 3266 */setBounds(33036, 455, 110, 34, localRSInterface);
		/*       */
		/* 3268 */setBounds(33037, 399, 130, 35, localRSInterface);
		/* 3269 */setBounds(33038, 455, 145, 36, localRSInterface);
		/*       */
		/* 3271 */setBounds(33039, 399, 170, 37, localRSInterface);
		/* 3272 */setBounds(33040, 455, 183, 38, localRSInterface);
		/*       */
		/* 3274 */setBounds(33041, 100, 280, 39, localRSInterface);
	/*       */}

	/*       */

	public static void Shop(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addTabInterface(3824);
		setChildren(10, rsinterface);
		addSprite(3825, 0, "Interfaces/Shop/SHOP");
		addHover(3902, 3, 0, 3826, 1, "Interfaces/Shop/CLOSE", 17, 17,
				"Close Window");
		addHovered(3826, 2, "Interfaces/Shop/CLOSE", 17, 17, 3827);
		addText(19679, "", 0xff981f, false, true, 52, TDA, 1);
		addText(19680, "", 0xbf751d, false, true, 52, TDA, 1);
		addButton(19681, 2, "Interfaces/Shop/SHOP", 0, 0, "", 1);
		addSprite(19687, 1, "Interfaces/Shop/ITEMBG");
		addHover(46606, 1, 0, 46607, 0, "Interfaces/Minigame/Back", 16, 16,
				"Back to Shops");
		addHovered(46607, 1, "Interfaces/Minigame/Back", 16, 16, 46608);// 31,24
		setBounds(3825, 6, 8, 0, rsinterface);
		setBounds(3902, 478, 10, 1, rsinterface);
		setBounds(3826, 478, 10, 2, rsinterface);
		setBounds(3900, 26, 44, 3, rsinterface);
		setBounds(3901, 240, 11, 4, rsinterface);
		setBounds(19679, 42, 54, 5, rsinterface);
		setBounds(19680, 150, 54, 6, rsinterface);
		setBounds(19681, 129, 50, 7, rsinterface);
		setBounds(46606, 31, 12, 8, rsinterface);
		setBounds(46607, 31, 12, 9, rsinterface);
		rsinterface = interfaceCache[3900];
		setChildren(1, rsinterface);
		setBounds(19687, 6, 15, 0, rsinterface);
		rsinterface.invSpritePadX = 15;
		rsinterface.width = 10;
		rsinterface.height = 4;
		rsinterface.invSpritePadY = 25;
		rsinterface = addTabInterface(19682);
		addSprite(19683, 1, "Interfaces/Shop/SHOP");
		addText(19684, "Main Stock", 0xbf751d, false, true, 52, TDA, 1);
		addText(19685, "Store Info", 0xff981f, false, true, 52, TDA, 1);
		addButton(19686, 2, "Interfaces/Shop/SHOP", 95, 19, "Main Stock", 1);
		setChildren(7, rsinterface);
		setBounds(19683, 12, 12, 0, rsinterface);
		setBounds(3901, 240, 21, 1, rsinterface);
		setBounds(19684, 42, 54, 2, rsinterface);
		setBounds(19685, 150, 54, 3, rsinterface);
		setBounds(19686, 23, 50, 4, rsinterface);
		setBounds(3902, 471, 22, 5, rsinterface);
		setBounds(3826, 60, 85, 6, rsinterface);
	}

	public static void shopOverview(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(26400); // Interface ID
		// Background here
		addSprite(26401, 1, "Interfaces/ShopOverview/BG");

		// Buttons Begin
		addHoverButton(26402, "Interfaces/ShopOverview/SPRITE", 1, 82, 76,
				"Close", -1, 26402, 1);

		addHoverButton(26403, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26403, 1);
		addHoverButton(26404, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26404, 1);
		addHoverButton(26405, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26405, 1);
		addHoverButton(26406, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26406, 1);
		addHoverButton(26407, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26407, 1);
		addHoverButton(26408, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26408, 1);
		addHoverButton(26409, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26409, 1);
		addHoverButton(26410, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26410, 1);
		addHoverButton(26411, "Interfaces/ShopOverview/SPRITE", 1, 158, 22,
				"Open shop", -1, 26411, 1);

		addHoverButton(26412, "Interfaces/ShopOverview/SPRITE", 1, 148, 21,
				"Open shop", -1, 26412, 1);
		addHoverButton(26413, "Interfaces/ShopOverview/SPRITE", 1, 148, 21,
				"Open shop", -1, 26413, 1);
		addHoverButton(26414, "Interfaces/ShopOverview/SPRITE", 1, 148, 21,
				"Open shop", -1, 26414, 1);
		addHoverButton(26415, "Interfaces/ShopOverview/SPRITE", 1, 148, 21,
				"Open shop", -1, 26415, 1);
		addHoverButton(26416, "Interfaces/ShopOverview/SPRITE", 1, 148, 21,
				"Open shop", -1, 26416, 1);
		addHoverButton(26417, "Interfaces/ShopOverview/SPRITE", 1, 148, 21,
				"Open shop", -1, 26417, 1);
		addHoverButton(26418, "Interfaces/ShopOverview/SPRITE", 1, 148, 21,
				"Open shop", -1, 26418, 1);
		addHoverButton(26419, "Interfaces/ShopOverview/SPRITE", 1, 148, 21,
				"Open shop", -1, 26419, 1);

		addHoverButton(26420, "Interfaces/ShopOverview/SPRITE", 1, 162, 22,
				"Open shop", -1, 26420, 1);
		addHoverButton(26421, "Interfaces/ShopOverview/SPRITE", 1, 162, 22,
				"Open shop", -1, 26421, 1);
		addHoverButton(26422, "Interfaces/ShopOverview/SPRITE", 1, 162, 22,
				"Open shop", -1, 26422, 1);
		addHoverButton(26423, "Interfaces/ShopOverview/SPRITE", 1, 162, 22,
				"Open shop", -1, 26423, 1);
		addHoverButton(26424, "Interfaces/ShopOverview/SPRITE", 1, 162, 22,
				"Open shop", -1, 26424, 1);
		addHoverButton(26425, "Interfaces/ShopOverview/SPRITE", 1, 162, 22,
				"Open shop", -1, 26425, 1);
		addHoverButton(26426, "Interfaces/ShopOverview/SPRITE", 1, 162, 22,
				"Open shop", -1, 26426, 1);
		addHoverButton(26427, "Interfaces/ShopOverview/SPRITE", 1, 162, 22,
				"Open shop", -1, 26427, 1);

		addText(26428, "Test", TDA, 2, 0xffffff, true, true);
		addText(26429, "Test", TDA, 2, 0xffffff, true, true);
		addText(26430, "Test", TDA, 2, 0xffffff, true, true);
		addText(26431, "Test", TDA, 2, 0xffffff, true, true);
		addText(26432, "Test", TDA, 2, 0xffffff, true, true);
		addText(26433, "Test", TDA, 2, 0xffffff, true, true);
		addText(26434, "Test", TDA, 2, 0xffffff, true, true);
		addText(26435, "Test", TDA, 2, 0xffffff, true, true);
		addText(26436, "Test", TDA, 2, 0xffffff, true, true);

		addText(26437, "Test", TDA, 2, 0xffffff, true, true);
		addText(26438, "Test", TDA, 2, 0xffffff, true, true);
		addText(26439, "Test", TDA, 2, 0xffffff, true, true);
		addText(26440, "Test", TDA, 2, 0xffffff, true, true);
		addText(26441, "Test", TDA, 2, 0xffffff, true, true);
		addText(26442, "Test", TDA, 2, 0xffffff, true, true);
		addText(26443, "Test", TDA, 2, 0xffffff, true, true);
		addText(26444, "Test", TDA, 2, 0xffffff, true, true);

		addText(26445, "Test", TDA, 2, 0xffffff, true, true);
		addText(26446, "Test", TDA, 2, 0xffffff, true, true);
		addText(26447, "Test", TDA, 2, 0xffffff, true, true);
		addText(26448, "Test", TDA, 2, 0xffffff, true, true);
		addText(26449, "Test", TDA, 2, 0xffffff, true, true);
		addText(26450, "Test", TDA, 2, 0xffffff, true, true);
		addText(26451, "Test", TDA, 2, 0xffffff, true, true);
		addText(26452, "Test", TDA, 2, 0xffffff, true, true);

		// Buttons End
		// Set Bounds Begin
		setChildren(52, Interface); // Number of sprites/buttons

		setBounds(26401, 0, 0, 0, Interface);
		setBounds(26402, 477, 10, 1, Interface);
		// +27
		setBounds(26403, 12, 85, 2, Interface);
		setBounds(26404, 12, 112, 3, Interface);
		setBounds(26405, 12, 139, 4, Interface);
		setBounds(26406, 13, 166, 5, Interface);
		setBounds(26407, 12, 193, 6, Interface);
		setBounds(26408, 12, 220, 7, Interface);
		setBounds(26409, 12, 247, 8, Interface);
		setBounds(26410, 12, 274, 9, Interface);
		setBounds(26411, 12, 301, 10, Interface);

		setBounds(26412, 179, 85, 11, Interface);
		setBounds(26413, 179, 112, 12, Interface);
		setBounds(26414, 179, 139, 13, Interface);
		setBounds(26415, 179, 166, 14, Interface);
		setBounds(26416, 179, 193, 15, Interface);
		setBounds(26417, 179, 220, 16, Interface);
		setBounds(26418, 179, 247, 17, Interface);
		setBounds(26419, 179, 274, 18, Interface);

		setBounds(26420, 336, 85, 19, Interface);
		setBounds(26421, 336, 112, 20, Interface);
		setBounds(26422, 336, 139, 21, Interface);
		setBounds(26423, 336, 166, 22, Interface);
		setBounds(26424, 336, 193, 23, Interface);
		setBounds(26425, 336, 220, 24, Interface);
		setBounds(26426, 336, 247, 25, Interface);
		setBounds(26427, 336, 274, 26, Interface);
		// +27
		setBounds(26428, 93, 84, 27, Interface);
		setBounds(26429, 93, 111, 28, Interface);
		setBounds(26430, 93, 138, 29, Interface);
		setBounds(26431, 93, 165, 30, Interface);
		setBounds(26432, 93, 192, 31, Interface);
		setBounds(26433, 93, 219, 32, Interface);
		setBounds(26434, 93, 246, 33, Interface);
		setBounds(26435, 93, 273, 34, Interface);
		setBounds(26436, 93, 300, 35, Interface);

		setBounds(26437, 257, 84, 36, Interface);
		setBounds(26438, 257, 111, 37, Interface);
		setBounds(26439, 257, 138, 38, Interface);
		setBounds(26440, 257, 165, 39, Interface);
		setBounds(26441, 257, 192, 40, Interface);
		setBounds(26442, 257, 219, 41, Interface);
		setBounds(26443, 257, 246, 42, Interface);
		setBounds(26444, 257, 273, 43, Interface);

		setBounds(26445, 424, 84, 44, Interface);
		setBounds(26446, 424, 111, 45, Interface);
		setBounds(26447, 424, 138, 46, Interface);
		setBounds(26448, 424, 165, 47, Interface);
		setBounds(26449, 424, 192, 48, Interface);
		setBounds(26450, 424, 219, 49, Interface);
		setBounds(26451, 424, 246, 50, Interface);
		setBounds(26452, 424, 273, 51, Interface);

		// Set Bounds End
	}

	public static void magicTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(1151);
		RSInterface homeHover = addTabInterface(1196);
		RSInterface spellButtons = interfaceCache[12424];
		spellButtons.scrollMax = 0;
		spellButtons.height = 260;
		spellButtons.width = 190;
		int[] spellButton = { 1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249,
				1258, 1267, 1274, 1283, 1573, 1290, 1299, 1308, 1315, 1324,
				1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388, 1397, 1404,
				1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469,
				15878, 1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512,
				1521, 1530, 1544, 1553, 1563, 1593, 1635, 12426, 12436, 12446,
				12456, 6004, 18471 };
		tab.totalChildren(63);
		tab.child(0, 12424, 13, 24);
		for (int i1 = 0; i1 < spellButton.length; i1++) {
			int yPos = i1 > 34 ? 8 : 183;
			tab.child(1, 1195, 13, 24);
			tab.child(i1 + 2, spellButton[i1], 5, yPos);
			addButton(1195, 1, "Interfaces/Magic/Home",
					"Cast @gre@Home Teleport");
			RSInterface homeButton = interfaceCache[1195];
			homeButton.mOverInterToTrigger = 1196;
		}
		for (int i2 = 0; i2 < spellButton.length; i2++) {
			if (i2 < 60)
				spellButtons.childX[i2] = spellButtons.childX[i2] + 24;
			if (i2 == 6 || i2 == 12 || i2 == 19 || i2 == 35 || i2 == 41
					|| i2 == 44 || i2 == 49 || i2 == 51)
				spellButtons.childX[i2] = 0;
			spellButtons.childY[6] = 24;
			spellButtons.childY[12] = 48;
			spellButtons.childY[19] = 72;
			spellButtons.childY[49] = 96;
			spellButtons.childY[44] = 120;
			spellButtons.childY[51] = 144;
			spellButtons.childY[35] = 170;
			spellButtons.childY[41] = 192;
		}
		homeHover.isMouseoverTriggered = true;
		addText(1197, "Level 0: Home Teleport", tda, 1, 0xFE981F, true, true);
		RSInterface homeLevel = interfaceCache[1197];
		homeLevel.width = 174;
		homeLevel.height = 68;
		addText(1198, "A teleport which requires no", tda, 0, 0xAF6A1A, true,
				true);
		addText(18998, "runes and no required level that", tda, 0, 0xAF6A1A,
				true, true);
		addText(18999, "teleports you to the main land.", tda, 0, 0xAF6A1A,
				true, true);
		homeHover.totalChildren(4);
		homeHover.child(0, 1197, 3, 4);
		homeHover.child(1, 1198, 91, 23);
		homeHover.child(2, 18998, 91, 34);
		homeHover.child(3, 18999, 91, 45);
	}
    public static void ancientMagicTab(TextDrawingArea tda[])
    {
        RSInterface tab = addInterface(12855);
		addButton(12856, 1, "Interfaces/Magic/Home", "Cast @gre@Home Teleport");
        RSInterface homeButton = interfaceCache[12856];
        homeButton.mOverInterToTrigger = 1196;
        int itfChildren[] = {
            12856, 12939, 12987, 13035, 12901, 12861, 13045, 12963, 13011, 13053, 
            12919, 12881, 13061, 12951, 12999, 13069, 12911, 12871, 13079, 12975, 
            13023, 13087, 12929, 12891, 13095, 1196, 12940, 12988, 13036, 12902, 
            12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062, 12952, 13000, 
            13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096
        };
        tab.totalChildren(itfChildren.length);
        int i1 = 0;
        int xPos = 18;
        int yPos = 8;
        while(i1 < itfChildren.length) 
        {
            if(xPos > 175)
            {
                xPos = 18;
                yPos += 28;
            }
            if(i1 < 25)
                tab.child(i1, itfChildren[i1], xPos, yPos);
            if(i1 > 24)
            {
                yPos = i1 >= 41 ? 1 : 181;
                tab.child(i1, itfChildren[i1], 4, yPos);
            }
            i1++;
            xPos += 45;
        }
    }

	public static void skillTab602(TextDrawingArea[] TDA) {
		RSInterface skill = addInterface(3917);
		addText(27203, "99", 0xFFFF00, false, true, -1, TDA, 0);
		addText(27204, "99", 0xFFFF00, false, true, -1, TDA, 0);
		addText(27205, "99", 0xFFFF00, false, true, -1, TDA, 0);
		addText(27206, "99", 0xFFFF00, false, true, -1, TDA, 0);
		skill.totalChildren(4);
		skill.child(0, 27203, 158, 175);
		skill.child(1, 27204, 171, 186);
		skill.child(2, 27205, 158, 203);
		skill.child(3, 27206, 171, 214);
		String[] spriteNames = { "Attack", "HP", "Mine", "Strength", "Agility",
				"Smith", "Defence", "Herblore", "Fish", "Range", "Thief",
				"Cook", "Prayer", "Craft", "Fire", "Mage", "Fletch", "Wood",
				"Rune", "Slay", "Farm", "Hunter", "Summon", "Construction" };
		int[] buttons = { 27207, 27208, 27209, 27210, 27211, 27212, 27213,
				27214, 27215, 27216, 27217, 27218, 27219, 27220, 27221, 27222,
				27223, 27224, 27225, 27226, 27231, 27232, 27233, 27234 };
		int[] hovers = { 4040, 4076, 4112, 4046, 4082, 4118, 4052, 4088, 4124,
				4058, 4094, 4130, 4064, 4100, 4136, 4070, 4106, 4142, 4160,
				2832, 13917, 19005, 19006, 19007 };
		int[][] text = { { 1500, 1501 }, { 4004, 4005 }, { 4016, 4017 },
				{ 4028, 4029 }, { 4006, 4007 }, { 4018, 4019 }, { 4030, 4031 },
				{ 4008, 4009 }, { 4020, 4021 }, { 4032, 4033 }, { 4010, 4011 },
				{ 4022, 4023 }, { 4034, 4035 }, { 4012, 4013 }, { 4024, 4025 },
				{ 4036, 4037 }, { 4014, 4015 }, { 4026, 4027 }, { 4038, 4039 },
				{ 4152, 4153 }, { 12166, 12167 }, { 13926, 13927 },
				{ 18165, 18169 }, { 18166, 18170 }, { 18167, 18171 } };

		int[] icons = { 3965, 3966, 3967, 3968, 3969, 3970, 3971, 3972, 3973,
				3974, 3975, 3976, 3977, 3978, 3979, 3980, 3981, 3982, 4151,
				12165, 13925, 27227, 27228, 27229 };

		int[][] buttonCoords = { { 4, 4 }, { 66, 4 }, { 128, 4 }, { 4, 32 },
				{ 66, 32 }, { 128, 32 }, { 4, 60 }, { 66, 60 }, { 128, 60 },
				{ 4, 88 }, { 66, 88 }, { 128, 88 }, { 4, 116 }, { 66, 116 },
				{ 128, 116 }, { 4, 144 }, { 66, 144 }, { 128, 144 },
				{ 4, 172 }, { 66, 172 }, { 128, 172 }, { 4, 200 }, { 66, 200 },
				{ 128, 200 } };
		int[][] iconCoords = { { 6, 6 }, { 69, 7 }, { 131, 6 }, { 9, 34 },
				{ 68, 33 }, { 131, 36 }, { 9, 64 }, { 67, 63 }, { 131, 61 },
				{ 7, 91 }, { 68, 94 }, { 133, 90 }, { 6, 118 }, { 70, 120 },
				{ 130, 118 }, { 6, 147 }, { 69, 146 }, { 132, 146 },
				{ 6, 173 }, { 69, 173 }, { 130, 174 }, { 6, 202 }, { 69, 201 },
				{ 131, 202 } };
		int[][] textCoords = { { 9999, 9999, 9999, 9999 }, { 31, 7, 44, 18 },
				{ 93, 7, 106, 18 }, { 155, 7, 168, 18 }, { 31, 35, 44, 46 },
				{ 93, 35, 106, 46 }, { 155, 35, 168, 46 }, { 31, 63, 44, 74 },
				{ 93, 63, 106, 74 }, { 155, 63, 168, 74 }, { 31, 91, 44, 102 },
				{ 93, 91, 106, 102 }, { 155, 91, 168, 102 },
				{ 31, 119, 44, 130 }, { 93, 119, 106, 130 },
				{ 155, 119, 168, 130 }, { 31, 147, 44, 158 },
				{ 93, 147, 106, 158 }, { 155, 147, 168, 158 },
				{ 31, 175, 44, 186 }, { 93, 175, 106, 186 },
				{ 155, 175, 168, 186 }, { 32, 203, 45, 214 },
				{ 94, 203, 107, 214 }, { 156, 203, 169, 214 } };
		int[][] newText = { { 18165, 18166, 18167, 18168 },
				{ 18169, 18170, 18171, 18172 } };
		for (int i = 0; i < hovers.length; i++) {
			createSkillHover(hovers[i], 206 + i);
			addSkillButton(buttons[i]);
			addImage(icons[i], spriteNames[i]);
		}
		for (int i = 0; i < 4; i++) {
			addSkillText(newText[0][i], false, i + 21);
			addSkillText(newText[1][i], true, i + 21);
		}
		skill.children(icons.length + (text.length * 2) + hovers.length
				+ buttons.length + 1);
		int frame = 0;
		RSInterface totalLevel = interfaceCache[3984];
		RSInterface.addText(3984, "", TDA, 2, 0xFFFF00, false, true);
		totalLevel.totalChildren(1);
		skill.child(0, 3984, 39, 237);
		skill.child(frame, 3984, 39, 237);
		frame++;
		for (int i = 0; i < buttons.length; i++) {
			skill.child(frame, buttons[i], buttonCoords[i][0],
					buttonCoords[i][1]);
			frame++;
		}
		for (int i = 0; i < icons.length; i++) {
			skill.child(frame, icons[i], iconCoords[i][0], iconCoords[i][1]);
			frame++;
		}
		for (int i = 0; i < text.length; i++) {
			skill.child(frame, text[i][0], textCoords[i][0], textCoords[i][1]);
			frame++;
		}
		for (int i = 0; i < text.length; i++) {
			skill.child(frame, text[i][1], textCoords[i][2], textCoords[i][3]);
			frame++;
		}
		for (int i = 0; i < hovers.length; i++) {
			skill.child(frame, hovers[i], buttonCoords[i][0],
					buttonCoords[i][1]);
			frame++;
		}
	}

	public static void GodWars(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addInterface(16210);
		addText(16211, "NPC killcount", TDA, 0, 0xff9040, false);
		addText(16212, "Armadyl kills", TDA, 0, 0xff9040, false);
		addText(16213, "Bandos kills", TDA, 0, 0xff9040, false);
		addText(16214, "Saradomin kills", TDA, 0, 0xff9040, false);
		addText(16215, "Zamorak kills", TDA, 0, 0xff9040, false);
		addText(16216, "0", TDA, 0, 0x66FFFF, false);// armadyl
		addText(16217, "0", TDA, 0, 0x66FFFF, false);// bandos
		addText(16218, "0", TDA, 0, 0x66FFFF, false);// saradomin
		addText(16219, "0", TDA, 0, 0x66FFFF, false);// zamorak
		rsinterface.scrollMax = 0;
		rsinterface.children = new int[9];
		rsinterface.childX = new int[9];
		rsinterface.childY = new int[9];
		rsinterface.children[0] = 16211;
		rsinterface.childX[0] = -52 + 375 + 30;
		rsinterface.childY[0] = 7;
		rsinterface.children[1] = 16212;
		rsinterface.childX[1] = -52 + 375 + 30;
		rsinterface.childY[1] = 30;
		rsinterface.children[2] = 16213;
		rsinterface.childX[2] = -52 + 375 + 30;
		rsinterface.childY[2] = 44;
		rsinterface.children[3] = 16214;
		rsinterface.childX[3] = -52 + 375 + 30;
		rsinterface.childY[3] = 58;
		rsinterface.children[4] = 16215;
		rsinterface.childX[4] = -52 + 375 + 30;
		rsinterface.childY[4] = 73;

		rsinterface.children[5] = 16216;
		rsinterface.childX[5] = -52 + 460 + 60;
		rsinterface.childY[5] = 31;
		rsinterface.children[6] = 16217;
		rsinterface.childX[6] = -52 + 460 + 60;
		rsinterface.childY[6] = 45;
		rsinterface.children[7] = 16218;
		rsinterface.childX[7] = -52 + 460 + 60;
		rsinterface.childY[7] = 59;
		rsinterface.children[8] = 16219;
		rsinterface.childX[8] = -52 + 460 + 60;
		rsinterface.childY[8] = 74;
	}

	/**public static void EquipmentTab(TextDrawingArea[] tda) {
		RSInterface localRSInterface = interfaceCache[1644];
		addSprite(15101, 0, "Interfaces/Equipment/bl");
		addSprite(15102, 1, "Interfaces/Equipment/bl");
		addSprite(15109, 2, "Interfaces/Equipment/bl");
		addSprite(15110, 3, "Interfaces/Equipment/bl");
		removeSomething(15103);
		removeSomething(15104);
		localRSInterface.children[23] = 15101;
		localRSInterface.childX[23] = 40;
		localRSInterface.childY[23] = 205;
		localRSInterface.children[24] = 15102;
		localRSInterface.childX[24] = 110;
		localRSInterface.childY[24] = 205;
		localRSInterface.children[25] = 15109;
		localRSInterface.childX[25] = 39;
		localRSInterface.childY[25] = 240;
		localRSInterface.children[26] = 27650;
		localRSInterface.childX[26] = 0;
		localRSInterface.childY[26] = 0;
		localRSInterface = addInterface(27650);
		addSprite(19146, 1, "Interfaces/Equipment/aura");
		addHoverButton(15201, "Interfaces/Equipment/CUSTOM", 1, 40, 40,
				"Show Equipment Screen", 0, 15202, 1);
		addHoveredButton(15202, "Interfaces/Equipment/CUSTOM", 5, 40, 40, 15203);
		addHoverButton(15204, "Interfaces/Equipment/CUSTOM", 2, 40, 40,
				"Items Kept on Death", 0, 15205, 1);
		addHoveredButton(15205, "Interfaces/Equipment/CUSTOM", 4, 40, 40, 15206);
		addHoverButton(15207, "Interfaces/Equipment/CUSTOM", 13, 40, 40,
				"Toolbelt", 0, 15208, 1);
		addHoveredButton(15208, "Interfaces/Equipment/CUSTOM", 14, 40, 40,
				15209);
		addHoverButton(15215, "Interfaces/Equipment/CUSTOM", 11, 40, 40,
				"Customize", 0, 15216, 1);
		addHoveredButton(15216, "Interfaces/Equipment/CUSTOM", 12, 40, 40,
				15217);
		setChildren(9, localRSInterface);
		setBounds(19146, 37, 4, 0, localRSInterface);
		setBounds(15201, 7, 210, 1, localRSInterface);
		setBounds(15202, 7, 210, 2, localRSInterface);
		setBounds(15204, 52, 210, 3, localRSInterface);
		setBounds(15205, 52, 210, 4, localRSInterface);
		setBounds(15207, 97, 210, 5, localRSInterface);
		setBounds(15208, 97, 210, 6, localRSInterface);
		setBounds(15215, 142, 210, 7, localRSInterface);
		setBounds(15216, 142, 210, 8, localRSInterface);
	}**/

	public static void EquipmentTab(TextDrawingArea[] tda) {
		RSInterface tab = interfaceCache[1644];
		addHoverButton(15201, "Interfaces/Equipment/CUSTOM", 1, 40, 40, "Show Equipment Screen", 0, 15202, 1);
		addHoveredButton(15202, "Equipment/CUSTOM", 5, 40, 40, 15203);

		addHoverButton(15204, "Interfaces/Equipment/CUSTOM", 2, 40, 40, "Items Kept on Death", 0, 15205, 1);
		addHoveredButton(15205, "Equipment/CUSTOM", 4, 40, 40, 15206);

		addHoverButton(15207, "Interfaces/Equipment/CUSTOM", 3, 40, 40, "Experience Lock", 0, 15208, 1);
		addHoveredButton(15208, "Equipment/CUSTOM", 6, 40, 40, 15209);

		addText(15226, "Unlocked", tda, 0, 0x00FF00, true, true);
		//removeSomething(15204);
		//removeSomething(15203);
		/*tab.child(25, 15203, 75, 210);
		tab.child(23, 15201, 41, 210);
		tab.child(24, 15202, 110, 210);
		tab.child(26, 15204, 110, 212);*/

		tab.child(23, 15201, 21, 210);
		tab.child(1, 15226, 95, 250);
		tab.child(24, 15202, 21, 210);
		tab.child(25, 15204, 129, 210);
		tab.child(26, 15205, 129, 210);
		tab.child(0, 15207, 75, 210);
		tab.child(2, 15208, 75, 210);
	}

	public static void equipmentScreen(TextDrawingArea[] wid) {
		@SuppressWarnings("unused")
		RSInterface Interface = RSInterface.interfaceCache[1644];

		RSInterface tab = addTabInterface(15106);
		addSprite(15107, 7, "Interfaces/Equipment/CUSTOM");
		addHoverButton(15210, "Interfaces/Equipment/CUSTOM", 8, 21, 21,
				"Close", 250, 15211, 3);
		addHoveredButton(15211, "Interfaces/Equipment/CUSTOM", 9, 21, 21, 15212);
		addText(15111, "Equip Your Character...", wid, 2, 0xe4a146, false, true);
		addText(15112, "Attack bonus", wid, 2, 0xe4a146, false, true);
		addText(15113, "Defence bonus", wid, 2, 0xe4a146, false, true);
		addText(15114, "Other bonuses", wid, 2, 0xe4a146, false, true);
		for (int i = 1675; i <= 1684; i++) {
			textSize(i, wid, 1);
		}
		textSize(1686, wid, 1);
		textSize(1687, wid, 1);
		addChar(15125);
		tab.totalChildren(44);
		tab.child(0, 15107, 4, 20);
		tab.child(1, 15210, 476, 29);
		tab.child(2, 15211, 476, 29);
		tab.child(3, 15111, 14, 30);
		int Child = 4;
		int Y = 69;
		for (int i = 1675; i <= 1679; i++) {
			tab.child(Child, i, 20, Y);
			Child++;
			Y += 14;
		}
		tab.child(9, 1680, 20, 161);
		tab.child(10, 1681, 20, 177);
		tab.child(11, 1682, 20, 192);
		tab.child(12, 1683, 20, 207);
		tab.child(13, 1684, 20, 221);
		tab.child(14, 1686, 20, 262);
		tab.child(15, 15125, 170, 200);
		tab.child(16, 15112, 16, 55);
		tab.child(17, 1687, 20, 276);
		tab.child(18, 15113, 16, 147);
		tab.child(19, 15114, 16, 248);
		tab.child(20, 1645, 104 + 295, 149 - 52);
		tab.child(21, 1646, 399, 163);
		tab.child(22, 1647, 399, 163);
		tab.child(23, 1648, 399, 58 + 146);
		tab.child(24, 1649, 26 + 22 + 297 - 2, 110 - 44 + 118 - 13 + 5);
		tab.child(25, 1650, 321 + 22, 58 + 154);
		tab.child(26, 1651, 321 + 134, 58 + 118);
		tab.child(27, 1652, 321 + 134, 58 + 154);
		tab.child(28, 1653, 321 + 48, 58 + 81);
		tab.child(29, 1654, 321 + 107, 58 + 81);
		tab.child(30, 1655, 321 + 58, 58 + 42);
		tab.child(31, 1656, 321 + 112, 58 + 41);
		tab.child(32, 1657, 321 + 78, 58 + 4);
		tab.child(33, 1658, 321 + 37, 58 + 43);
		tab.child(34, 1659, 321 + 78, 58 + 43);
		tab.child(35, 1660, 321 + 119, 58 + 43);
		tab.child(36, 1661, 321 + 22, 58 + 82);
		tab.child(37, 1662, 321 + 78, 58 + 82);
		tab.child(38, 1663, 321 + 134, 58 + 82);
		tab.child(39, 1664, 321 + 78, 58 + 122);
		tab.child(40, 1665, 321 + 78, 58 + 162);
		tab.child(41, 1666, 321 + 22, 58 + 162);
		tab.child(42, 1667, 321 + 134, 58 + 162);
		tab.child(43, 1688, 50 + 297 - 2, 110 - 13 + 5);
		for (int i = 1675; i <= 1684; i++) {
			RSInterface rsi = interfaceCache[i];
			rsi.textColor = 0xe4a146;
			rsi.centerText = false;
		}
		for (int i = 1686; i <= 1687; i++) {
			RSInterface rsi = interfaceCache[i];
			rsi.textColor = 0xe4a146;
			rsi.centerText = false;
		}
	}

	public static void weaponClass(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(49100); // Interface ID
		// Background here
		addSprite(49101, 1, "Interfaces/WeaponClass/BG");

		// Buttons Begin
		addHoverButton(49102, "Interfaces/WeaponClass/SPRITE", 1, 20, 20,
				"Close", -1, 49102, 1);

		addHoverButton(49103, "Interfaces/WeaponClass/SPRITE", 1, 85, 91,
				"Choose Melee", -1, 49103, 1);
		addHoverButton(49104, "Interfaces/WeaponClass/SPRITE", 1, 85, 91,
				"Choose Ranged", -1, 49104, 1);
		addHoverButton(49105, "Interfaces/WeaponClass/SPRITE", 1, 85, 91,
				"Choose Magic", -1, 49105, 1);

		addHoverButton(49106, "Interfaces/WeaponClass/SPRITE", 1, 252, 37,
				"Enter Cave", -1, 49106, 1);

		addText(49107, "Current Class: None", TDA, 2, 0xffffff, true, true);
		addText(49108, "Class Level: 999", TDA, 2, 0xffffff, true, true);
		addText(49109, "Level Potential: 1000%", TDA, 2, 0xffffff, true, true);

		setChildren(9, Interface); // Number of sprites/buttons

		setBounds(49101, 5, 5, 0, Interface);

		setBounds(49102, 492, 17, 1, Interface); //close

		setBounds(49103, 56, 66, 2, Interface);
		setBounds(49104, 221, 66, 3, Interface);
		setBounds(49105, 377, 66, 4, Interface);

		setBounds(49106, 242, 295, 5, Interface);

		setBounds(49107, 264, 189, 6, Interface);
		setBounds(49108, 264, 212, 7, Interface);
		setBounds(49109, 264, 235, 8, Interface);


		//	setBounds(26402, 477, 10, 1, Interface);

		// Set Bounds End you want to show on the walkable interface? ye i see.
	}

	public static void inWeapon(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(18100); // Interface ID
		// Background here
		addSprite(18101, 1, "Interfaces/WeaponClass/SPRITE");

		addText(18102, "Class Level: 999", TDA, 2, 0xffffff, true, true);
		addText(18103, "Level Potential: 100%", TDA, 2, 0xffffff, true, true);

		setChildren(3, Interface); // Number of sprites/buttons

		setBounds(18101, 320, 1, 0, Interface);
		setBounds(18102, 444, 9, 1, Interface); //close
		setBounds(18103, 444, 30, 2, Interface);
	}

}
