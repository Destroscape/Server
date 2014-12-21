package game.entity.player.utilities;

import game.Config;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class DrawInterface {
	Player c;

	public DrawInterface(Player c) {
		this.c = c;
	}

	public DrawInterface() {
	}

	/**
	 * @param Quest Tab Format
		c.getPA().sendFrame126("", 663);
		c.getPA().sendFrame126("", 16023);
		c.getPA().sendFrame126("", 16027);
		c.getPA().sendFrame126("", 16028);
		c.getPA().sendFrame126("", 16029);
		c.getPA().sendFrame126("", 16030);
		c.getPA().sendFrame126("", 16031);
		c.getPA().sendFrame126("", 16032);
		c.getPA().sendFrame126("", 16033);
		c.getPA().sendFrame126("", 16034);
		c.getPA().sendFrame126("", 16035);
		c.getPA().sendFrame126("", 16036);
		c.getPA().sendFrame126("", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("", 16040);
	 */
	/**
	 * @param Skill Info Format
		c.getPA().sendFrame126("", 563);
		c.getPA().sendFrame126("", 26023);
		c.getPA().sendFrame126("", 26027);
		c.getPA().sendFrame126("", 26028);
		c.getPA().sendFrame126("", 26029);
		c.getPA().sendFrame126("", 26030);
		c.getPA().sendFrame126("", 26031);
		c.getPA().sendFrame126("", 26032);
		c.getPA().sendFrame126("", 26033);
		c.getPA().sendFrame126("", 26034);
		c.getPA().sendFrame126("", 26035);
		c.getPA().sendFrame126("", 26036);
		c.getPA().sendFrame126("", 26037);
		c.getPA().sendFrame126("", 26038);
		c.getPA().sendFrame126("", 26039);
		c.getPA().sendFrame126("", 26040);
	 */
	
	public void drawPrestigeGuide() {
		c.getPA().showInterface(8134);
		c.getPA().sendFrame126("@red@Prestige Skill Guide", 8144);
		c.getPA().sendFrame126("@blu@What is Prestige?", 8145);
		c.getPA().sendFrame126("Prestige is a function used to reset your mastered skill.", 8147);
		c.getPA().sendFrame126("Doing this, you'll be given two options of either obtaining", 8148);
		c.getPA().sendFrame126("a prestige rank for the skill you're prestiging", 8149);
		c.getPA().sendFrame126("or prestige points that can be redeemed", 8150);
		c.getPA().sendFrame126("at the prestige shop or the prestige chest.", 8151);
		c.getPA().sendFrame126("@blu@What's the point of gaining Prestige Ranks?", 8152);
		c.getPA().sendFrame126("Each rank you obtain from prestiging will be assorted", 8153);
		c.getPA().sendFrame126("towards a skill you choose through choosing", 8154);
		c.getPA().sendFrame126("a prestige class.", 8155);
		c.getPA().sendFrame126("@blu@What are the Prestige Classes?", 8156);
		c.getPA().sendFrame126("There are currently 5 prestige classes", 8157);
		c.getPA().sendFrame126("1) Supreme Accuracy", 8158);
		c.getPA().sendFrame126("2) Essential Power", 8159);
		c.getPA().sendFrame126("3) Strong Will'd", 8160);
		c.getPA().sendFrame126("4) Eye of Robin", 8161);
		c.getPA().sendFrame126("5) Cryptic Might", 8162);
		c.getPA().sendFrame126("@blu@What are the class benefits?", 8163);
		c.getPA().sendFrame126("Choosing the Supreme Accuracy class", 8164);
		c.getPA().sendFrame126("will benefit your Attack Skill", 8165);
		c.getPA().sendFrame126("Choosing the Essential Power class", 8166);
		c.getPA().sendFrame126("will benefit your Strength Skill", 8167);
		c.getPA().sendFrame126("Choosing the Strong Will'd class", 8168);
		c.getPA().sendFrame126("will benefit your Defence Skill", 8169);
		c.getPA().sendFrame126("Choosing the Eye of Robin class", 8170);
		c.getPA().sendFrame126("will benefit your Range Accuracy", 8171);
		c.getPA().sendFrame126("Choosing the Cryptic Might class", 8172);
		c.getPA().sendFrame126("will benefit your Magic Accuracy", 8173);
		int line = 8174;
		line++;
		c.getPA().sendFrame126("", line);
	}
	
	public void drawRankTab(Player c) { //TODO
		c.getPA().sendFrame126("Rank Menu", 663);
		c.getPA().sendFrame126("", 16023);
		c.getPA().sendFrame126("@whi@Player: @gre@OBTAINED", 16027);
		c.getPA().sendFrame126("@whi@Donator: @red@NOT OBTAINED", 16028);
		c.getPA().sendFrame126("@whi@Super Donator: @red@NOT OBTAINED", 16029);
		c.getPA().sendFrame126("", 16030);
		c.getPA().sendFrame126("", 16031);
		c.getPA().sendFrame126("", 16032);
		c.getPA().sendFrame126("", 16033);
		c.getPA().sendFrame126("", 16034);
		c.getPA().sendFrame126("", 16035);
		c.getPA().sendFrame126("", 16036);
		c.getPA().sendFrame126("", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("Go Back", 16040);
	}

	public void drawToolBelt(Player c) {
		c.getPA().sendFrame126("@red@Tool Belt", 8144);
		switch (c.hatchet) {
		case 0:
			c.getPA().sendFrame126("Hatchet = @red@None", 8145);
			break;
		case 1:
			c.getPA().sendFrame126("Hatchet = @gre@Bronze", 8145);
			break;
		case 2:
			c.getPA().sendFrame126("Hatchet = @gre@Iron", 8145);
			break;
		case 3:
			c.getPA().sendFrame126("Hatchet = @gre@Steel", 8145);
			break;
		case 4:
			c.getPA().sendFrame126("Hatchet = @gre@Black", 8145);
			break;
		case 5:
			c.getPA().sendFrame126("Hatchet = @gre@Mithril", 8145);
			break;
		case 6:
			c.getPA().sendFrame126("Hatchet = @gre@Adamant", 8145);
			break;
		case 7:
			c.getPA().sendFrame126("Hatchet = @gre@Rune", 8145);
			break;
		case 8:
			c.getPA().sendFrame126("Hatchet = @gre@Dragon", 8145);
			break;
		}
		c.getPA().sendFrame126("Hammer = @red@False", 8147);
		c.getPA().sendFrame126("Chisel = @red@False", 8148);
		switch (c.pickAxe) {
		case 0:
			c.getPA().sendFrame126("Pickaxe = @red@None", 8149);
			break;
		case 1:
			c.getPA().sendFrame126("Pickaxe = @gre@Bronze", 8149);
			break;
		case 2:
			c.getPA().sendFrame126("Pickaxe = @gre@Iron", 8149);
			break;
		case 3:
			c.getPA().sendFrame126("Pickaxe = @gre@Steel", 8149);
			break;
		case 4:
			c.getPA().sendFrame126("Pickaxe = @gre@Mithril", 8149);
			break;
		case 5:
			c.getPA().sendFrame126("Pickaxe = @gre@Adamant", 8149);
			break;
		case 6:
			c.getPA().sendFrame126("Pickaxe = @gre@Rune", 8149);
			break;
		case 7:
			c.getPA().sendFrame126("Pickaxe = @gre@Dragon", 8149);
			break;
		}
		c.getPA().sendFrame126("Needle = @red@False", 8150);
		c.getPA().sendFrame126("Butterfly Net = @red@False", 8151);
		c.getPA().sendFrame126("Lobster Cage = @red@False", 8152);
		c.getPA().sendFrame126("Small Fishing Net = @red@False", 8153);
		c.getPA().sendFrame126("Big Fishing Net = @red@False", 8154);
		c.getPA().sendFrame126("Harpoon = @red@False", 8155);
		c.getPA().sendFrame126("Knife = @red@False", 8156);
		c.getPA().sendFrame126("Tinder box = @red@False", 8157);
		c.getPA().sendFrame126("", 8158);
		c.getPA().sendFrame126("", 8159);
		c.getPA().sendFrame126("", 8160);
		c.getPA().sendFrame126("", 8161);
		c.getPA().sendFrame126("", 8162);
		c.getPA().sendFrame126("", 8163);
		c.getPA().sendFrame126("", 8164);
		c.getPA().sendFrame126("", 8165);
		c.getPA().sendFrame126("", 8166);
		c.getPA().sendFrame126("", 8167);
		c.getPA().sendFrame126("", 8168);

	}

	public void drawMainTab() {
		c.currentPanel = "main";
		c.getPA().sendFrame126(""+Config.SERVER_NAME+"", 663);
		c.getPA().sendFrame126("", 16023);
		c.getPA().sendFrame126("@whi@Server Updates", 16027);
		c.getPA().sendFrame126("@whi@Statistics Menu", 16028);
		c.getPA().sendFrame126("@whi@Quest Menu", 16029);
		c.getPA().sendFrame126("@whi@Saved Teleports", 16030);
		c.getPA().sendFrame126("@whi@Prestige Menu", 16031);
		c.getPA().sendFrame126("@whi@Achievements", 16032);
		c.getPA().sendFrame126("@whi@Total Info", 16033);
		if (c.membership == true) {
			c.getPA().sendFrame126("@gre@Membership Options", 16034);
		}
		if (c.membership == false) {
			c.getPA().sendFrame126("@red@Membership Options", 16034);
		}
		c.getPA().sendFrame126("", 16035);
		c.getPA().sendFrame126("@or1@-Config-", 16036);
		c.getPA().sendFrame126("@whi@Call For Staff", 16037);
		c.getPA().sendFrame126("@whi@Redirects", 16038);
		c.getPA().sendFrame126("@whi@Carried Wealth", 16039);
		c.getPA().sendFrame126("", 16040);
	}

	public void redirectTab() {
		c.getPA().sendFrame126("Redirects", 663);
		c.getPA().sendFrame126("", 16023);
		c.getPA().sendFrame126("@whi@Open Forum", 16027);
		c.getPA().sendFrame126("@whi@Open Vote", 16028);
		c.getPA().sendFrame126("@whi@Open Store", 16029);
		c.getPA().sendFrame126("@whi@Create A Suggestion", 16030);
		c.getPA().sendFrame126("@whi@Open Highscores", 16031);
		c.getPA().sendFrame126("", 16032);
		c.getPA().sendFrame126("", 16033);
		c.getPA().sendFrame126("", 16034);
		c.getPA().sendFrame126("", 16035);
		c.getPA().sendFrame126("", 16036);
		c.getPA().sendFrame126("", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("Go Back", 16040);
	}

	public void totalTab(Player c) {
		c.getPA().sendFrame126("Total Information", 663);
		c.getPA().sendFrame126("", 16023);
		c.getPA().sendFrame126("@or2@Playtime:", 16027);
		c.getPA().sendFrame126("@whi@" + c.getPlaytime() + "", 16028);
		c.getPA().sendFrame126("@or2@NPC Kills", 16029);
		c.getPA().sendFrame126("@whi@" + c.totalNPCKO + "", 16030);
		c.getPA().sendFrame126("@or2@Total Donations", 16031);
		c.getPA().sendFrame126("@whi@" + c.totalDonation + "@gre@$", 16032);
		c.getPA().sendFrame126("@or2@Double XP Time (Reward Book)", 16033);
		if (c.getPA().rewardBook()) {
			c.getPA().sendFrame126("@whi@"+c.getPA().getTimeLeftForDouble()+"", 16034);
		} else {
			c.getPA().sendFrame126("@whi@None", 16034);
		}
		c.getPA().sendFrame126("", 16035);
		c.getPA().sendFrame126("", 16036);
		c.getPA().sendFrame126("", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("@or2@Go Back", 16040);
	}

	public void memberShipTab() {
		c.getPA().sendFrame126("Membership Options", 663);
		c.getPA().sendFrame126("", 16023);
		c.getPA().sendFrame126("@whi@Members Only Area", 16027);
		c.getPA().sendFrame126("@whi@Cancel Slayer Task @gre@(FREE)", 16028);
		c.getPA().sendFrame126("@whi@Main Teleports", 16029);
		c.getPA().sendFrame126("@whi@Members Slayer Dungeon", 16030);
		c.getPA().sendFrame126("@whi@Godwars KC +20", 16031);
		c.getPA().sendFrame126("", 16032);
		c.getPA().sendFrame126("", 16033);
		c.getPA().sendFrame126("", 16034);
		c.getPA().sendFrame126("", 16035);
		c.getPA().sendFrame126("", 16036);
		c.getPA().sendFrame126("", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("@or2@Go Back", 16040);
	}

	public void drawPointsTab() {
		c.getPA().sendFrame126("Statistics", 663);
		c.getPA().sendFrame126("            Player Killing Points: " + c.pkPoints + "", 16023);
		c.getPA().sendFrame126("@whi@Voting Points: @gre@" + c.votePoints + "", 16027);
		c.getPA().sendFrame126("@whi@Pest Control Points: @gre@" + c.pcPoints + "", 16028);
		c.getPA().sendFrame126("@whi@Slayer Points: @gre@" + c.slayerPoints + "", 16029);
		if (c.slayerTask == 0) {
			c.getPA().sendFrame126("@whi@Slayer task: @red@None", 16030);
		} else {
			c.getPA().sendFrame126("@whi@Slayer Task: @gre@"+ NPCHandler.getNpcListName(c.slayerTask).replaceAll("_", " ") + "", 16030);
		}
		c.getPA().sendFrame126("@whi@Amount Left: @gre@" + c.taskAmount + "", 16031);
		c.getPA().sendFrame126("@whi@Dungeoneering Tokens: @gre@" + c.dTokens + "", 16032);
		c.getPA().sendFrame126("@whi@Loyalty Points: @gre@"+ c.loyaltyPoints +"", 16033);
		c.getPA().sendFrame126("@whi@Kills: @gre@"+ c.kills +"", 16034);
		c.getPA().sendFrame126("@whi@Deaths: @gre@"+ c.deaths +"", 16035);
		c.getPA().sendFrame126("@whi@Current Streak: @gre@"+ c.currentStreak +"", 16036);
		c.getPA().sendFrame126("@whi@Highest Streak: @gre@"+ c.highestStreak +"", 16037);
		c.getPA().sendFrame126("@whi@Donator Points: @gre@"+ c.donatorPoints +"", 16038);
		c.getPA().sendFrame126("@whi@Skill Points: @gre@"+ c.skillPoints +"", 16039);
		c.getPA().sendFrame126("@or2@Go Back", 16040);
		c.getQuest().getStatusFrame(c);
	}

	public void drawQuestTab() {
		c.getPA().sendFrame126("Quest Menu", 663);
		c.getPA().sendFrame126("                  Quest Points: " + c.questPoints + "", 16023);
		if (c.theStryke >= 1 && c.theStryke <= 2) {
			 c.getPA().sendFrame126("@yel@The Strykewyrm", 16027);
		} else if (c.theStryke == 3) {
			 c.getPA().sendFrame126("@gre@The Strykewyrm", 16027);
		} else {
			 c.getPA().sendFrame126("The Strykewyrm", 16027);
		}
		if (c.lunarDiplomacy >= 1 && c.lunarDiplomacy <= 8) {
			 c.getPA().sendFrame126("@yel@Lunar Diplomacy", 16028);
		} else if (c.lunarDiplomacy == 9) {
			 c.getPA().sendFrame126("@gre@Lunar Diplomacy", 16028);
		} else {
			 c.getPA().sendFrame126("Lunar Diplomacy", 16028);
		}
		if (c.DT >= 1 && c.DT <= 5) {
			 c.getPA().sendFrame126("@yel@Desert Treasure", 16029);
		} else if (c.DT == 6) {
			 c.getPA().sendFrame126("@gre@Desert Treasure", 16029);
		} else {
			 c.getPA().sendFrame126("Desert Treasure", 16029);
		}
		c.getPA().sendFrame126("", 16030);
		c.getPA().sendFrame126("", 16031);
		c.getPA().sendFrame126("", 16032);
		c.getPA().sendFrame126("", 16033);
		c.getPA().sendFrame126("", 16034);
		c.getPA().sendFrame126("", 16035);
		c.getPA().sendFrame126("", 16036);
		c.getPA().sendFrame126("", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("@or2@Go Back", 16040);
	}

	public void drawSavedTeleports() {
		c.getPA().sendFrame126("Saved Teleports", 663);
		c.getPA().sendFrame126("", 16023);
		if (c.savedTele1 == true) {
			c.getPA().sendFrame126("@whi@~@gre@"+ c.tele1 +"@whi@~", 16027);
			c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleOneX +" @whi@Y: @gre@"+ c.teleOneY +"", 16028);
		} else {
			c.getPA().sendFrame126("@whi@~@red@Teleport 1@whi@~", 16027);
			c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16028);
		}
		if (c.savedTele2 == true) {
			c.getPA().sendFrame126("@whi@~@gre@"+ c.tele2 +"@whi@~", 16029);
			c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleTwoX +" @whi@Y: @gre@"+ c.teleTwoY +"", 16030);
		} else {
			c.getPA().sendFrame126("@whi@~@red@Teleport 2@whi@~", 16029);
			c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16030);
		}
		if (c.savedTele3 == true) {
			c.getPA().sendFrame126("@whi@~@gre@"+ c.tele3 +"@whi@~", 16031);
			c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleThreeX +" @whi@Y: @gre@"+ c.teleThreeY +"", 16032);
		} else {
			c.getPA().sendFrame126("@whi@~@red@Teleport 3@whi@~", 16031);
			c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16032);
		}
		if (c.savedTele4 == true) {
			c.getPA().sendFrame126("@whi@~@gre@"+ c.tele4 +"@whi@~", 16033);
			c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleFourX +" @whi@Y: @gre@"+ c.teleFourY +"", 16034);
		} else {
			c.getPA().sendFrame126("@whi@~@red@Teleport 4@whi@~", 16033);
			c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16034);
		}
		if (c.savedTele5 == true) {
			c.getPA().sendFrame126("@whi@~@gre@"+ c.tele5 +"@whi@~", 16035);
			c.getPA().sendFrame126("@whi@X: @gre@"+ c.teleFiveX +" @whi@Y: @gre@"+ c.teleFiveY +"", 16036);
		} else {
			c.getPA().sendFrame126("@whi@~@red@Teleport 5@whi@~", 16035);
			c.getPA().sendFrame126("@whi@X: @red@Not Set @whi@Y: @red@Not Set", 16036);
		}
		c.getPA().sendFrame126("", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("@or2@Go Back", 16040);
	}

	public void drawPrestigePanel() {
		c.getPA().sendFrame126("Prestige Menu", 663);
		c.getPA().sendFrame126("              Prestige Points: " + c.prestigePoints + "", 16023);
		c.getPA().sendFrame126("@whi@Skills Prestiged: @gre@"+ c.skillsPrestiged +"", 16027);
		c.getPA().sendFrame126("@whi@Combat Prestige", 16028);
		c.getPA().sendFrame126("@whi@Max Prestige", 16029);
		c.getPA().sendFrame126("@whi@What is Prestige?", 16030);
		c.getPA().sendFrame126("", 16031);
		c.getPA().sendFrame126("", 16032);
		c.getPA().sendFrame126("", 16033);
		c.getPA().sendFrame126("", 16034);
		c.getPA().sendFrame126("", 16035);
		c.getPA().sendFrame126("", 16036);
		c.getPA().sendFrame126("", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("@or2@Go Back", 16040);
	}

	public void drawSkillInfo(Player c,int skillID) { //TODO
		String skillName = c.getPA().getSkillName(skillID);
		int skillLevel = c.getPA().getLevelForXP(c.playerXP[skillID]);
		int currentExp = c.playerXP[skillID];
		int nextLevel = c.getPA().getXPForLevel(skillLevel+1);
		int expNeeded = (int)c.getPA().getXPForLevel(skillLevel+1) - currentExp;
		c.getPA().sendFrame126("Skill Info", 563);
		c.getPA().sendFrame126("", 26023);
		c.getPA().sendFrame126("@whi@Skill: "+skillName+"", 26027);
		c.getPA().sendFrame126("@whi@Current Exp: "+currentExp+"", 26028);
		if(skillLevel < 99){
			c.getPA().sendFrame126("@whi@Next Level: "+nextLevel+"", 26029);
			c.getPA().sendFrame126("@whi@Exp Needed: "+expNeeded+"", 26030);
		} else {
			c.getPA().sendFrame126("@whi@Next Level: @gre@Mastered", 26029);
			c.getPA().sendFrame126("@whi@Prestige Skill", 26030);
		}
		c.getPA().sendFrame126("@whi@Quick Chat Level", 26031);
		c.getPA().sendFrame126("", 26032);
		c.getPA().sendFrame126("", 26033);
		c.getPA().sendFrame126("", 26034);
		c.getPA().sendFrame126("", 26035);
		c.getPA().sendFrame126("", 26036);
		c.getPA().sendFrame126("", 26037);
		c.getPA().sendFrame126("", 26038);
		c.getPA().sendFrame126("", 26039);
		c.getPA().sendFrame126("@or2@Go Back", 26040);
		c.setSidebarInterface(1, 49500);
		c.currentSkillInfo = skillID;
	}

	public void drawStaffList() {
		c.getPA().sendFrame126("Staff List", 663);
		c.getPA().sendFrame126("", 16023);
		c.getPA().sendFrame126("@cya@-Owner", 16027);
		c.getPA().sendFrame126("@gre@Lawless", 16028);
		c.getPA().sendFrame126("@gre@ ", 16029);
		c.getPA().sendFrame126("@cya@-Developers-", 16030);
		c.getPA().sendFrame126("@gre@Luis", 16031);
		c.getPA().sendFrame126("@gre@Simple Lyons", 16032);
		c.getPA().sendFrame126("@gre@Jake", 16033);
		c.getPA().sendFrame126("@cya@-Administrators-", 16034);
		c.getPA().sendFrame126("@gre@Timmy", 16035);
		c.getPA().sendFrame126("@cya@-Mod-", 16036);
		c.getPA().sendFrame126("@red@", 16037);
		c.getPA().sendFrame126("", 16038);
		c.getPA().sendFrame126("", 16039);
		c.getPA().sendFrame126("@or2@Go Back", 16040);
	}

	public void drawLoyaltyTitles() {
		c.getPA().sendFrame126("Titles", 17228);
		c.getPA().sendFrame126("Lord", 17230);
		c.getPA().sendFrame126("10 Points", 17229);
		c.getPA().sendFrame126("Baron", 17232);
		c.getPA().sendFrame126("10 Points", 17231);
		c.getPA().sendFrame126("Sir", 17234);
		c.getPA().sendFrame126("15 Points", 17233);
		c.getPA().sendFrame126("Duke", 17236);
		c.getPA().sendFrame126("15 Points", 17235);
		c.getPA().sendFrame126("Hybrid", 17238);
		c.getPA().sendFrame126("20 Points", 17237);
		c.getPA().sendFrame126("Count", 17240);
		c.getPA().sendFrame126("25 Points", 17239);
		c.getPA().sendFrame126("Noob", 17242);
		c.getPA().sendFrame126("30 Points", 17241);
		c.getPA().sendFrame126("l337", 17244);
		c.getPA().sendFrame126("50 Points", 17243);
	}

}