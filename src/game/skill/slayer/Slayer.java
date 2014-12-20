package game.skill.slayer;

import engine.util.Misc;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.skill.SkillHandler;
import game.content.achievement.*;

public class Slayer extends SkillHandler {

	public static SlayerTask getEasyTask(Player c) {
		SlayerTask task = null;
		do {
			task = EasyTask.values()[(int) (Math.random() * EasyTask.values().length)];
		} while (task.getReq() > c.playerLevel[c.playerSlayer]);
		String description = task.getDesc();
		c.slayerTask = task.getId();
		c.taskAmount = Misc.random(40) + 13;
		c.taskType = 1;
		c.sendMessage("You have been assigned to kill " + c.taskAmount + " "
				+ description + ".");
		return task;
	}

	public static SlayerTask getMediumTask(Player c) {
		SlayerTask task = null;
		do {
			task = MediumTask.values()[(int) (Math.random() * MediumTask
					.values().length)];
		} while (task.getReq() > c.playerLevel[c.playerSlayer]);
		String description = task.getDesc();
		c.slayerTask = task.getId();
		c.taskAmount = Misc.random(40) + 13;
		c.taskType = 2;
		c.sendMessage("You have been assigned to kill " + c.taskAmount + " "
				+ description + ".");
		return task;
	}

	public static SlayerTask getHardTask(Player c) {
		SlayerTask task = null;
		do {
			task = HardTask.values()[(int) (Math.random() * HardTask.values().length)];
		} while (task.getReq() > c.playerLevel[c.playerSlayer]);
		String description = task.getDesc();
		c.slayerTask = task.getId();
		c.taskAmount = Misc.random(40) + 13;
		c.taskType = 3;
		c.sendMessage("You have been assigned to kill " + c.taskAmount + " "
				+ description + ".");
		return task;
	}

	public static SlayerTask getBossTask(Player c) {
		SlayerTask task = null;
		do {
			task = BossTask.values()[(int) (Math.random() * BossTask.values().length)];
		} while (task.getReq() > c.playerLevel[c.playerSlayer]);
		String description = task.getDesc();
		c.slayerTask = task.getId();
		c.taskAmount = Misc.random(8) + 1;
		c.taskType = 4;
		c.sendMessage("You have been assigned to kill " + c.taskAmount + " "
				+ description + ".");
		return task;
	}

	public static void giveTask(final Player c) {
		int randomTask;
		if (c.taskAmount > 0 && c.slayerTask > 0) {
			c.sendMessage("You still have to slay <col=255>" + c.taskAmount + "</col> of <col=255>"+ NPCHandler.getNpcListName(c.slayerTask).replaceAll("_", " ") + "</col>.");
			return;
		}
		if (c.getLevelForXP(c.playerXP[c.playerSlayer]) < 45) {
			getEasyTask(c);
		} else if (c.getLevelForXP(c.playerXP[c.playerSlayer]) < 65) {
			getMediumTask(c);
		} else {
		switch(Misc.random(10)){
		case 1:
		case 2:
			getBossTask(c);
		break;
		case 0:
		case 3:	
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			getHardTask(c);
		break;
			}
		}
		if (c.currentPanel.equals("statistics")) {
			c.getPA().sendFrame126("@whi@Slayer Task: @gre@"+ NPCHandler.getNpcListName(c.slayerTask).replaceAll("_", " ") + "", 16030);
			c.getPA().sendFrame126("@whi@Amount Left: @gre@" + c.taskAmount + "", 16031);
		}
	}

	public static boolean canSlay(Player c, int i) {
		for (EasyTask e : EasyTask.values()) {
			if (c.playerLevel[c.playerSlayer] < e.getReq()
					&& NPCHandler.npcs[i].npcType == e.getId())
				return false;
		}
		for (MediumTask m : MediumTask.values()) {
			if (c.playerLevel[c.playerSlayer] < m.getReq()
					&& NPCHandler.npcs[i].npcType == m.getId())
				return false;
		}
		for (HardTask h : HardTask.values()) {
			if (c.playerLevel[c.playerSlayer] < h.getReq()
					&& NPCHandler.npcs[i].npcType == h.getId())
				return false;
		}
		for (BossTask h : BossTask.values()) {
			if (c.playerLevel[c.playerSlayer] < h.getReq()
					&& NPCHandler.npcs[i].npcType == h.getId())
				return false;
		}
		return true;
	}

	public static void appendSlayerExperience(int i) {
		Player c = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
		if (c != null) {
			if (c.slayerTask == NPCHandler.npcs[i].npcType) {
				c.taskAmount--;
				if (c.currentPanel.equals("statistics")) {
					c.getPA().sendFrame126("@whi@Amount Left: @gre@" + c.taskAmount + "", 16031);
				}
			c.getPA().addSkillXP(NPCHandler.npcs[i].MaxHP * SLAYER_XP, c.playerSlayer);

				if (c.taskAmount <= 0) {
					c.slayerPoints += c.x2Points ? 30 : 15;
					c.taskType = -1;
					c.slayerTask = -1;
					c.easierTask = 0;
					c.sendMessage("You have completed your slayer task! You now have "
							+ c.slayerPoints + " slayer points.");
					AchievementManager.increase(c, Achievements.THE_SLAYER);
					if (c.currentPanel.equals("statistics")) {
						c.getPA().sendFrame126("@whi@Slayer Points: @gre@" + c.slayerPoints + "", 16029);
						c.getPA().sendFrame126("@whi@Slayer Task: @gre@"+ NPCHandler.getNpcListName(c.slayerTask).replaceAll("_", " ") + "", 16030);
					}
				}
			}
		}
	}

	interface SlayerTask {
		public int getId();

		public int getReq();

		public String getDesc();
	}

	static enum EasyTask implements SlayerTask {
		ROCK_CRAB(1265, 1, "Rock Crabs"), //*
		AL_KHARID_WARRIOR(18, 1, "Al-Kharid Warriors"), //*
		CHAOS_DRUID(181, 1, "Chaos Druids"), //*
		CRAWLING_HAND(1648, 5, "Crawling hands"),
		CHAOS_DWARF(119, 1, "Chaos Dwarfs"), //*
		HILL_GIANT(117, 1, "Hill Giants"), //*
		YAKS(5529, 1, "Yaks"), 
		GHOST(103, 1, "Ghosts"), //*
		GIANT_BAT(78, 1, "Giant bats"),//*
		BANSHEE(1612, 15, "Banshees"); //*

		private int id, req;
		private String desc;

		EasyTask(int id, int req, String desc) {
			this.id = id;
			this.req = req;
			this.desc = desc;
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public int getReq() {
			return req;
		}

		@Override
		public String getDesc() {
			return desc;
		}

	}

	static enum MediumTask implements SlayerTask {
		MOSS_GIANT(112, 1, "Moss Giants"), //*
		ICE_WARRIOR(125, 1, "Ice Warriors"), 
		BABY_BLUE_DRAGON(52, 1, "Baby Blue Dragons"), //*
		INFERNAL_MAGE(1643, 45, "Infernal Mages"), 
		BLOODVELD(1618, 50, "Bloodvelds"), //*
		GREEN_DRAGON(941, 1, "Green Dragons"), //*
		MUMMY(1961, 1, "Mummys"), 
		CAVE_BUGS(1832, 1, "Cave bugs"), 
		DAGANNOTHS(1341, 1, "Dagannoth's (Lvl 92)"), //*
		LESSER_DEMON(82, 1, "Lesser Demons"), //*
		BABY_BLACK_DRAG(3376, 1, "Bby. Blk Dragons"), 
		GIANT_CAVE_BUGS(5750, 1, "Giant Cave Bugs"), 
		HELL_HOUND(49, 1, "Hell Hounds"); //*

		private int id, req;
		private String desc;

		MediumTask(int id, int req, String desc) {
			this.id = id;
			this.req = req;
			this.desc = desc;
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public int getReq() {
			return req;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	}

	static enum HardTask implements SlayerTask {
		DUST_DEVIL(1624, 65, "Dust Devils"), 
		FIRE_GIANTS(110, 1, "Fire Giants"), //*
		MOSS_GIANTS(112, 1, "Moss Giants"), //*
		GARGOYLE(1610, 75, "Gargoyles"), //*
		NECHRYAEL(1613, 80, "Nechryaels"), //*
		ZAMORAK_WARRIORS(6363, 1, "Zamorak Warriors"), 
		ZAMORAK_RANGERS(6365, 1, "Zamorak Rangers"), 
		ZAMORAK_MAGERS(6367, 1, "Zamorak Magers"), 
		ABYSSAL_DEMON(1615, 85, "Abyssal Demons"), //*
		DARK_BEAST(2783, 90, "Dark Beasts"), //*
		BLUE_DRAGON(55, 1, "Blue Dragons"), //*
		BLACK_DEMON(84, 1, "Black Demons"), //*
		TORMENTED_DEMONS(8349, 1, "Tormented Demons"), 
		RED_DRAGON(53, 1, "Red Dragons"), 
		BLACK_DRAGON(54, 1, "Black Dragons"), 
		BRONZE_DRAGON(1590, 1, "Bronze Dragons"), 
		IRON_DRAGON(1591, 1, "Iron Dragons"), 
		STEEL_DRAGON(1592, 1, "Steel Dragons"),
		GANODERMIC_RUNT(1374, 1, "Gano. Runts"),  
		JADINKO_MALE(13822, 1, "Mut. Jadinko Males"), 
		ICE_STRYKEWYRM(9463, 1, "Ice Strykewyrms"), 
		DESERT_STRYKEWYRM(9465, 1, "Desert Strykewyrms"), 
		JUNGLE_STRYKEWYRM(9467, 1, "Jungle Strykewyrms"), 
		GLACOR(14301, 1, "Glacors"); 

		private int id, req;
		private String desc;

		HardTask(int id, int req, String desc) {
			this.id = id;
			this.req = req;
			this.desc = desc;
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public int getReq() {
			return req;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	}

	static enum BossTask implements SlayerTask {
		DAGANNOTH_SUPREME(2881, 1, "Dag. Supreme"), 
		DAGANNOTH_PRIME(2882, 1, "Dag. Prime"),
		KRIL_TSUTSAROTH(6203, 1, "K' Tsutsaroth"),
		KREE_ARRA(6222, 1, "Kree'Arra"), 
		DAGANNOTH_REX(2883, 1, "Dag. Rex"), 
		GENERAL_GRAARDOR(6260, 1, "Gen. Graardor"), 
		CHAOS_ELEMENTAL(3200, 1, "Chaos ele."),
		NEX(13447, 1, "Nex"),
		COM_ZILYANA(6247, 1, "Com. Zilyana"),
		KBD(50, 1, "King Blk. Drag");

		private int id, req;
		private String desc;

		BossTask(int id, int req, String desc) {
			this.id = id;
			this.req = req;
			this.desc = desc;
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public int getReq() {
			return req;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	}

}