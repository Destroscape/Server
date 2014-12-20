package game.skill.summoning;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.Server;
import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class Summoning {

	public static boolean isNotRenewing = true;

	public static int emptySlot = -1;

	public static final int[][] PET_DATA = { { 1555, 761 }, { 1561, 768 },
			{ 1567, 774 }, { 1556, 762 }, { 1562, 769 }, { 1568, 775 },
			{ 1557, 763 }, { 1563, 770 }, { 1569, 776 }, { 1558, 764 },
			{ 1564, 771 }, { 1570, 777 }, { 1559, 765 }, { 1565, 772 },
			{ 1571, 778 }, { 1560, 766 }, { 1566, 773 }, { 1572, 779 },
			{ 7583, 3505 }, { 7582, 3504 }, { 7581, 3503 }, { 14089, 8217 },
			{ 14090, 8214 }, { 15092, 8216 }, { 7771, 3598 }, { 7772, -1 },
			{ 12522, 6969 }, { 12523, 6968 }, { 12720, 7259 }, { 12721, 7257 },
			{ 12722, 7260 }, { 12723, 7258 }, { 12518, 6964 }, { 12519, 6965 },
			{ 12712, 7249 }, { 12713, 7250 }, { 12714, 7251 }, { 12715, 7252 },
			{ 12514, 6960 }, { 12515, 6961 }, { 12704, 7241 }, { 12705, 7242 },
			{ 12706, 7243 }, { 12707, 7244 }, { 12516, 6962 }, { 12517, 6963 },
			{ 12708, 7245 }, { 12709, 7246 }, { 12710, 7247 }, { 12711, 7248 },
			{ 12520, 6966 }, { 12521, 6967 }, { 12716, 7253 }, { 12717, 7254 },
			{ 12718, 7255 }, { 12719, 7256 }, { 12512, 6958 }, { 12513, 6859 },
			{ 12700, 7237 }, { 12701, 7238 }, { 12702, 7239 }, { 12703, 7240 },
			{ 14652, 8619 }, { 14653, 8620 }, { 14654, 8621 }, { 14655, 8622 },
			{ 14651, 8624 }, { 15626, 9656 }, { 15627, 9657 }, { 15628, 9658 },
			{ 15629, 9659 }, { 15630, 9660 }, { 15631, 9661 }, { 15632, 9662 },
			{ 15633, 9663 }, { 15634, 9664 }, { 15635, 9665 }, { 15636, 9666 },
			{ 15637, 9667 }, { 15638, 9668 }, { 15639, 9669 }, { 12488, 6915 },
			{ 12489, 6916 }, { 12738, 7277 }, { 12742, 7281 }, { 12739, 7278 },
			{ 12743, 7282 }, { 12740, 7279 }, { 12744, 7283 }, { 12741, 7280 },
			{ 12745, 7284 }, { 12551, 7018 }, { 12548, 7015 }, { 12552, 7019 },
			{ 12549, 7016 }, { 12553, 7020 }, { 12550, 7017 }, { 14533, 8491 },
			{ 12481, 6908 }, { 12482, 6909 }, { 12763, 7313 }, { 12762, 7314 },
			{ 12765, 7316 }, { 12764, 7317 }, { 18671, 11411 },
			{ 18669, 11413 }, { 12500, 6947 }, { 12501, 6948 },
			{ 12746, 7293 }, { 12747, 7294 }, { 12748, 7295 }, { 12749, 7296 },
			{ 12750, 7297 }, { 12751, 7298 }, { 12752, 7299 }, { 12753, 7300 },
			{ 12484, 6911 }, { 12485, 6912 }, { 12724, 7261 }, { 12725, 7262 },
			{ 12726, 7263 }, { 12727, 7264 }, { 12728, 7265 }, { 12729, 7266 },
			{ 12730, 7267 }, { 12731, 7268 }, { 12732, 7269 }, { 12733, 7270 },
			{ 12490, 6919 }, { 12491, 6920 }, { 12754, 7301 }, { 12755, 7302 },
			{ 12756, 7303 }, { 12757, 7304 }, { 12758, 7305 }, { 12759, 7306 },
			{ 12760, 7307 }, { 12761, 7308 }, { 12503, 6949 }, { 12504, 6950 },
			{ 12505, 6951 }, { 12506, 6952 }, { 12507, 6953 }, { 12508, 6954 },
			{ 12509, 6955 }, { 12510, 6956 }, { 12511, 6957 }, { 13335, 7844 },
			{ 14627, 8578 }, { 14626, 8577 }, { 12486, 6913 }, { 12487, 6914 },
			{ 12734, 7271 }, { 12735, 7272 }, { 12736, 7273 }, { 12737, 7274 },
			{ 19894, 13089 }, { 19895, 13090 }, { 12498, 6945 },
			{ 12499, 6946 }, { 12766, 7319 }, { 12767, 7320 }, { 12768, 7321 },
			{ 12769, 7322 }, { 12770, 7323 }, { 12771, 7324 }, { 12772, 7325 },
			{ 12773, 7326 }, { 12774, 7327 }, { 12775, 7328 }, { 12492, 6922 },
			{ 12493, 6923 }, { 12496, 6942 }, { 12497, 6943 }, { 12682, 7210 },
			{ 12683, 7211 }, { 12684, 7212 }, { 12685, 7213 }, { 12686, 7214 },
			{ 12687, 7215 }, { 12688, 7216 }, { 12689, 7217 }, { 12690, 7218 },
			{ 12691, 7219 }, { 12692, 7220 }, { 12693, 7221 }, { 12694, 7222 },
			{ 12695, 7223 }, { 12696, 7224 }, { 12697, 7225 }, { 12698, 7226 },
			{ 12699, 7227 }, { 12469, 6900 }, { 12470, 6901 }, { 12471, 6902 },
			{ 12472, 6903 }, { 12473, 6904 }, { 12474, 6905 }, { 12475, 6906 },
			{ 12476, 6907 }, { 21512, 3604 } };

	protected static enum Familiar {
		SPIRIT_WOLF(12047, 1, 6, 1, 0.1, 26, 15, 12425, 6829, 10, 10, 0, 0,
				true), DREADFOWL(12043, 4, 4, 1, 0.1, 26, 16, 12445, 6825, 15,
				10, 0, 0, false), SPIRIT_SPIDER(12059, 10, 15, 2, 0.2, 25, 18,
				12428, 6841, 10, 15, 0, 0, false), THORNY_SNAIL(12019, 13, 16,
				2, 0.2, 26, 28, 12459, 6806, 20, 10, 3, 0, false), GRANITE_CRAB(
				12009, 16, 18, 2, 0.2, 26, 39, 12533, 6796, 15, 25, 0, 0, false), SPIRIT_MOSQUITO(
				12778, 17, 12, 2, 0.2, 32, 43, 12838, 7331, 25, 20, 0, 0, false), DESERT_WYRM(
				12049, 18, 19, 1, 0.4, 31, 47, 12460, 6831, 30, 20, 0, 0, true), SPIRIT_SCORPION(
				12055, 19, 17, 2, 0.9, 51, 67, 12432, 6837, 30, 30, 0, 0, false), SPIRIT_TZKIH(
				12808, 22, 18, 3, 1.1, 36, 63, 12839, 7361, 35, 20, 0, 0, false), ALBINO_RAT(
				12067, 23, 22, 1, 2.3, 37, 68, 12430, 6847, 30, 30, 0, 0, true), SPIRIT_KALPHITE(
				12063, 25, 22, 3, 2.5, 39, 77, 12446, 6994, 30, 35, 6, 0, true), COMPOST_MOUND(
				12091, 28, 24, 6, 0.6, 37, 95, 12440, 6871, 45, 45, 0, 0, true), GIANT_CHINCHOMPA(
				12800, 29, 31, 1, 2.5, 42, 97, 12834, 7353, 20, 40, 0, 0, false), VAMPIRE_BAT(
				12053, 31, 33, 4, 1.6, 44, 105, 12447, 6835, 25, 45, 0, 0,
				false), HONEY_BADGER(12065, 32, 25, 4, 1.6, 45, 110, 12433,
				6845, 30, 45, 0, 0, false), BEAVER(12021, 33, 27, 4, 0.7, 1,
				100, 12429, 6808, 35, 45, 0, 0, false), VOID_RAVAGER(12818, 34,
				27, 4, 0.7, 46, 99, 12443, 7370, 65, 50, 0, 0, false), VOID_SHIFTER(
				12814, 34, 27, 4, 0.7, 46, 99, 12443, 7367, 65, 50, 0, 0, false), VOID_TORCHER(
				12798, 34, 27, 4, 0.7, 46, 99, 12443, 7351, 65, 50, 0, 0, false), VOID_SPINNER(
				12780, 34, 27, 4, 0.7, 46, 99, 12443, 7333, 65, 50, 0, 0, false), BRONZE_MINOTAUR(
				12073, 36, 30, 3, 2.4, 50, 133, 12461, 6853, 75, 50, 0, 0, true), BULL_ANT(
				12087, 40, 30, 5, 0.6, 58, 154, 12431, 6867, 58, 58, 9, 0,
				false), MACAW(12071, 41, 31, 5, 0.8, 1, 1, 12422, 6851, 58, 58,
				0, 0, false), EVIL_TURNIP(12051, 42, 30, 5, 2.1, 62, 162,
				12448, 6833, 62, 60, 0, 0, false), SPIRIT_COCKATRICE(12095, 43,
				36, 5, 0.9, 62, 162, 12458, 6875, 62, 60, 0, 0, true), SPIRIT_GUTHATRICE(
				12097, 43, 36, 5, 0.9, 62, 162, 12458, 6877, 62, 60, 0, 0, true), SPIRIT_SARATRICE(
				12099, 43, 36, 5, 0.9, 62, 162, 12458, 6879, 62, 60, 0, 0, true), SPIRIT_ZAMATRICE(
				12101, 43, 36, 5, 0.9, 62, 162, 12458, 6881, 62, 60, 0, 0, true), SPIRIT_PENGATRICE(
				12103, 43, 36, 5, 0.9, 62, 162, 12458, 6883, 62, 60, 0, 0, true), SPIRIT_CORAXATRICE(
				12105, 43, 36, 5, 0.9, 62, 162, 12458, 6885, 62, 60, 0, 0, true), SPIRIT_VULATRICE(
				12107, 43, 36, 5, 0.9, 62, 162, 12458, 6887, 62, 60, 0, 0, true), IRON_MINOTAUR(
				12075, 46, 37, 9, 4.6, 70, 193, 12462, 6855, 70, 65, 0, 0, true), PYRELORD(
				12816, 46, 32, 5, 2.3, 70, 193, 12829, 7377, 70, 65, 0, 0,
				false), MAGPIE(12041, 47, 34, 5, 0.9, 1, 1, 12426, 6824, 1, 1,
				0, 0, false), BLOATED_LEECH(12061, 49, 34, 5, 2.4, 76, 211,
				12444, 6843, 75, 70, 0, 0, false), SPIRIT_TERRORBIRD(12007, 52,
				36, 6, 0.7, 62, 233, 12441, 6794, 75, 200, 12, 0, true), ABYSSAL_PARASITE(
				12035, 54, 30, 6, 1.1, 86, 248, 12454, 6818, 86, 80, 7, 0,
				false), SPIRIT_JELLY(12027, 55, 43, 6, 5.5, 88, 255, 12453,
				6992, 88, 85, 0, 0, false), STEEL_MINOTAUR(12077, 56, 46, 9,
				5.6, 90, 260, 12463, 6857, 90, 90, 0, 0, true), IBIS(12531, 56,
				38, 6, 1.1, 1, 1, 12424, 6991, 1, 1, 0, 0, false), SPIRIT_GRAAHK(
				12810, 57, 49, 6, 5.6, 93, 268, 12835, 7363, 100, 90, 0, 0,
				true), SPIRIT_KYATT(12812, 57, 49, 6, 5.7, 93, 268, 12836,
				7365, 100, 90, 0, 0, true), SPIRIT_LARUPIA(12784, 57, 49, 6,
				5.7, 93, 268, 12835, 7337, 100, 90, 0, 0, true), KARAMTHULHU_OVERLORD(
				12023, 58, 44, 6, 5.8, 95, 276, 12455, 6809, 110, 110, 0, 0,
				false), SMOKE_DEVIL(12085, 61, 48, 7, 3.1, 101, 300, 12468,
				6865, 120, 110, 0, 0, false), ABYSSAL_LURKER(12037, 62, 41, 9,
				1.9, 93, 308, 12427, 6820, 80, 110, 7, 0, false), SPIRIT_COBRA(
				12015, 63, 56, 6, 3.1, 105, 314, 12436, 6802, 105, 110, 0, 0,
				true), STRANGE_PLANT(12045, 64, 49, 3, 3.2, 107, 322, 12467,
				6827, 105, 100, 0, 0, false), MITHRIL_MINOTAUR(12079, 66, 55,
				9, 6.6, 112, 340, 12464, 6859, 112, 112, 0, 0, true), BARKER_TOAD(
				12123, 66, 8, 7, 1.0, 112, 340, 12452, 6889, 100, 100, 0, 0,
				false), WAR_TORTOISE(12031, 67, 43, 7, 0.7, 86, 348, 12439,
				6815, 88, 87, 18, 0, true), BUNYIP(12029, 68, 44, 7, 1.4, 70,
				400, 12438, 6813, 75, 72, 0, 0, true), FRUIT_BAT(12033, 69, 45,
				8, 1.4, 1, 1, 12423, 6817, 1, 1, 0, 0, false), RAVENOUS_LOCUST(
				12820, 70, 24, 4, 1.5, 120, 375, 12830, 7372, 133, 134, 0, 0,
				false), ARCTIC_BEAR(12057, 71, 28, 8, 1.1, 122, 381, 12451,
				6839, 136, 136, 0, 0, true), PHEONIX(14623, 72, 30, 8, 3.0,
				124, 153, 14622, 8575, 140, 140, 0, 0, true), OBSIDIAN_GOLEM(
				12792, 73, 55, 8, 7.3, 126, 406, 12826, 7345, 141, 141, 0, 0,
				true), GRANITE_LOBSTER(12069, 74, 47, 8, 3.7, 129, 418, 12449,
				6849, 141, 142, 0, 0, false), PRAYING_MANTIS(12011, 75, 69, 8,
				3.6, 131, 428, 12450, 6798, 145, 145, 0, 0, false), ADAMANT_MINOTAUR(
				12081, 76, 66, 8, 8.6, 133, 441, 12465, 6861, 146, 148, 0, 0,
				true), FORGE_REGENT(12782, 76, 45, 9, 1.5, 133, 441, 12841,
				7335, 150, 151, 0, 0, false), TALON_BEAST(12794, 77, 49, 9,
				3.8, 135, 454, 12831, 7347, 151, 160, 0, 0, true), GIANT_ENT(
				12013, 78, 49, 8, 1.6, 137, 467, 12457, 6800, 165, 140, 0, 0,
				true), FIRE_TITAN(12802, 79, 62, 9, 7.9, 139, 476, 12824, 7355,
				152, 152, 0, 0, true), MOSS_TITAN(12804, 79, 58, 9, 7.9, 139,
				476, 12824, 7357, 152, 152, 0, 0, true), ICE_TITAN(12806, 79,
				64, 9, 7.9, 139, 476, 12824, 7359, 152, 152, 0, 0, true), HYDRA(
				12025, 80, 49, 9, 1.6, 141, 490, 12442, 6811, 160, 155, 0, 0,
				true), SPIRIT_DAGONNOTH(12017, 83, 57, 9, 4.1, 148, 528, 12456,
				6804, 162, 161, 0, 0, true), LAVA_TITAN(12788, 83, 61, 9, 8.3,
				148, 528, 12837, 7341, 162, 161, 0, 0, true), SWAMP_TITAN(
				12776, 85, 56, 9, 4.2, 152, 556, 12832, 7329, 166, 161, 0, 0,
				true), RUNE_MINOTAUR(12083, 86, 151, 9, 8.6, 154, 570, 12466,
				6863, 170, 165, 0, 0, true), UNICORN_STALLION(12039, 88, 54, 9,
				1.8, 70, 100, 12434, 6822, 88, 88, 0, 0, true), GEYSER_TITAN(
				12786, 89, 69, 9, 8.9, 200, 620, 12833, 7339, 185, 180, 0, 0,
				true), WOLPERTINGER(12089, 92, 62, 10, 4.6, 210, 651, 12437,
				6869, 190, 190, 0, 0, false), ABYSSAL_TITAN(12796, 93, 32, 10,
				1.9, 215, 667, 12827, 7349, 200, 200, 0, 0, true), IRON_TITAN(
				12822, 95, 60, 10, 8.6, 220, 694, 12828, 7375, 205, 205, 0, 0,
				true), PACK_YAK(12093, 96, 58, 10, 4.8, 175, 710, 12435, 6873,
				200, 350, 30, 0, true), STEEL_TITAN(12790, 99, 64, 10, 4.9,
				230, 754, 12825, 7343, 210, 400, 0, 0, true), BOSS(12285, 1,
				99, 1, 1, 1, 1, 1, 1874, 1, 1, 1, 0, true);

		private int pouchID, levelReq, time, summonPointsNeeded, lifePoints,
				npcID, attackLevel, defenceLevel, bobSlotsNumber;

		private Familiar(int pouchID, int levelReq, int time,
				int summonPointsNeeded, double xp, int combatLevel,
				int lifePoints, int scrollID, int npcID, int attackLevel,
				int defenceLevel, int bobSlotsNumber, int familiarMax,
				boolean sizeOfFamiliar) {
			this.pouchID = pouchID;
			this.levelReq = levelReq;
			this.time = time;
			this.summonPointsNeeded = summonPointsNeeded;
			this.lifePoints = lifePoints;
			this.npcID = npcID;
			this.attackLevel = attackLevel;
			this.defenceLevel = defenceLevel;
			this.bobSlotsNumber = bobSlotsNumber;
		}

		private final int getPouchID() {
			return pouchID;
		}

		private final int getLevelRequired() {
			return levelReq;
		}

		private final int getTimer() {
			return time;
		}

		private final int getSummoningPointsRequired() {
			return summonPointsNeeded;
		}

		private final int getLifePoints() {
			return lifePoints;
		}

		protected final int getNPCID() {
			return npcID;
		}

		private final int getAttackLevel() {
			return attackLevel;
		}

		private final int getDefenceLevel() {
			return defenceLevel;
		}

		private final int getBoBSlotsNumber() {
			return bobSlotsNumber;
		}

		protected final String getFamiliarName() {
			return Misc.optimizeText(toString().toLowerCase().replaceAll("_",
					" "));
		}

		private static Familiar forID(final int ID) {
			for (Familiar f : values()) {
				if (f.getPouchID() == ID) {
					return f;
				}
			}
			return null;
		}

	}

	public static void spawnGFX(NPC n) {
		n.gfx0(1315);
	}

	public static void summonFamiliar(final Player player, final int pouchID) {
		if (player.teleTimer > 0) {
			player.sendMessage("You cannot summon a familiar when teleporting.");
			return;
		}
		if (player.inDuelArena()) {
			player.sendMessage("You cannot summon a familiar here.");
			return;
		}
		if (player.noSummon()) {
			player.sendMessage("You cannot summon a familiar in this area.");
			player.sendMessage("You must either go to the bank, or go out of Edgeville, to");
			player.sendMessage("complete this action.");
			return;
		}
		Familiar f = Familiar.forID(pouchID);
		if (!player.getItems().playerHasItem(pouchID))
			return;
		if (player.hasFamiliar) {
			player.sendMessage("You already have a familiar!");
			return;
		}
		if (f.getLevelRequired() > getLevel(player)) {
			player.sendMessage("You need a summoning level of "
					+ f.getLevelRequired()
					+ " or above to summon this familiar.");
			return;
		}
		if (player.playerLevel[player.summoning]
				- f.getSummoningPointsRequired() < 0) {
			player.sendMessage("Not enough summoning points, recharge at an obelisk.");
			return;
		}
		player.getItems().deleteItem(pouchID, 1);
		player.familiarMinutes = f.getTimer();
		player.familiarSeconds = 0;
		player.getPA().sendFrame126(
				Integer.toString(player.familiarMinutes) + ".0"
						+ Integer.toString(player.familiarSeconds), 18043);
		player.playerLevel[player.summoning] -= f.getSummoningPointsRequired();
		player.getPA().refreshSkill(player.summoning);
		spawnFamiliar(player, f.getNPCID());
		player.getPA().sendFrame106(16);
		new NPC(emptySlot, f.getNPCID());
	}

	public static void rechargeAtObelisk(Player c) {
		if (c.playerLevel[c.summoning] >= getLevel(c)) {
			c.sendMessage("You already have full summoning points.");
			return;
		}
		c.gfx0(1516);
		c.gfx0(1517);
		//c.setAnimation(Animation.create(8502));
		c.startAnimation(8502);
		c.playerLevel[c.summoning] = getLevel(c);
		c.getPA().refreshSkill(c.summoning);
		c.sendMessage("You recharge your summoning points.");
		c.getPA().sendFrame126(
				Integer.toString(c.playerLevel[c.summoning]) + "/"
						+ Integer.toString(getLevel(c)), 18045);
	}

	public static final int getLevel(Player player) {
		return player.getLevelForXP(player.playerXP[player.summoning]);
	}

	public static boolean isNow = false;
	public static int count = -1;

	public static void dismissFamiliar(final Player c, boolean dropStoredItems,
			final boolean clearSummonText) {
		if (!c.hasFamiliar) {
			return;
		}
		if (c.noSummon()) {
			c.sendMessage("You cannot dismiss a familiar in this area.");
			c.sendMessage("You must either go to the bank, or go out of Edgeville, to");
			c.sendMessage("complete this action.");
			return;
		}
		for (NPC n : NPCHandler.npcs) {
			if (n != null && n.index == c.familiarIndex) {
				c.hasFamiliar = false;
				if (c.isLoggingOutNow == false) {
					c.familiarIndex = -1;
					n.index = -1;
					c.familiarID = -1;
					for (int i = 0; i < c.bobItems.length; i += 1) {
						if (c.bobItems[i] >= 1) {
							Server.itemHandler.createGroundItem(c,
									c.bobItems[i], c.absX, c.absY, 1,
									c.playerId);
							c.bobItemsN[i] = 0;
							c.bobItems[i] = 0;
							c.getPA().refreshBoB(i);
						} else {
							c.bobItems[i] = 0;
						}
					}
				}
				n.absX = -1;
				n.absY = -1;
				n.makeX = -1;
				n.makeY = -1;
				n.summoner = false;
				n.summon = false;
				n.lastSummon = -1;
				n.isDead = true;
				c.getPA().sendFrame126(":follower:off", -1);
				n.applyDead = true;
				n.needsToRespawn = false;
				c.familiarEvent = false;
				n.summonedNPC = false;
				if (clearSummonText) {
					c.getPA().sendFrame126(
							Integer.toString(c.playerLevel[c.summoning]) + "/"
									+ Integer.toString(getLevel(c)), 18045);
					c.getPA().sendFrame75(4000, 18021);
					c.getPA().sendFrame106(3);
					// c.setSidebarInterface(16, -1);
				}
				n.npcslot = -1;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				FamiliarAbilities.stopAbilities(c);
				c.setSidebarInterface(16, -1);
				c.sendMessage("@red@Your familiar has now vanished.");
			}
		}
	}

	public static int sumPouchID() {
		for (Familiar f : Familiar.values()) {
			return f.getPouchID();
		}
		return -1;
	}

	public static void renewFamiliar(Player c) {
		isNotRenewing = false;
		if (!c.hasFamiliar) {
			c.sendMessage("You do not have a familiar to renew.");
			return;
		}
		if (c.noSummon()) {
			c.sendMessage("You cannot renew a familiar in this area.");
			c.sendMessage("You must either go to the bank, or go out of Edgeville, to");
			c.sendMessage("complete this action.");
			return;
		}
		for (Familiar f : Familiar.values()) {
			if (c.familiarID == f.getNPCID()) {
				if (!c.getItems().playerHasItem(f.getPouchID())) {
					c.sendMessage("You do not have the correct pouch to renew your familiar.");
					break;
				}
				if (c.playerLevel[c.summoning] - f.getSummoningPointsRequired() < 0) {
					c.sendMessage("Not enough summoning points, recharge at an obelisk.");
					return;
				}
				c.familiarMinutes = f.getTimer();
				c.familiarSeconds = 0;
				c.getItems().deleteItem(f.getPouchID(), 1);
				c.playerLevel[c.summoning] -= f.getSummoningPointsRequired();
				c.getPA().refreshSkill(c.summoning);
				c.getPA().sendFrame126(
						Integer.toString(c.playerLevel[c.summoning]) + "/"
								+ Integer.toString(getLevel(c)), 18045);
				c.sendMessage("Your familiar's timer has been reset and you use one of your pouches to do this.");
			}
		}
	}

	private static void refreshTabTimer(Player player) {
		if (player.familiarSeconds > 0)
			player.familiarSeconds--;
		 else {
			player.familiarMinutes--;
			player.familiarSeconds = 59;
		}
		if (player.familiarMinutes == 1 && player.familiarSeconds == 0)
			player.sendMessage("@red@Your familiar has 1 minute left before it disappears.");
		else if (player.familiarMinutes == 0 && player.familiarSeconds == 30)
			player.sendMessage("@red@Your familiar has 30 seconds left before it disappears.");
		}

	public static void summonRestore(final Player c) {

	}

	private static void startFamiliarEvent(final Player c, final NPC familiar) {
		if (c.familiarEvent)
			return;
		c.familiarEvent = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			boolean decreasing;
			boolean increasing = false;

			@Override
			public void execute(CycleEventContainer container) {
				if (!c.hasFamiliar) {
					decreasing = false;
					container.stop();
					return;
				}
				if (c.hasFamiliar && decreasing == false) {
					decreasing = true;
					c.decreaseTime = 90;
				}
				if (c.hasFamiliar && decreasing == true) {
					c.decreaseTime--;
				}
				if (c.hasJustCalled > 0) {
					c.hasJustCalled--;
				}
				if (c.decreaseTime <= 0) {
					if (c.playerLevel[c.summoning] > 0) {
						c.playerLevel[c.summoning] -= 1;
						c.getPA().refreshSkill(c.summoning);
						c.getPA().sendFrame126(
								Integer.toString(c.playerLevel[c.summoning])
										+ "/" + Integer.toString(getLevel(c)),
								18045);
						c.decreaseTime = 90;
					}
				}

				FamiliarAbilities.abilities(c);

				if (c.familiarID > 0 && increasing == false) {
					increasing = true;
				}
				if (c.familiarID > 0 && increasing == true) {
					c.sumSpecTimer++;
				}
				if (c.noSummon()) {
					if (c.absY > 3321 && c.absX > 2622) {
						c.getPA().movePlayer(c.absX + 2, c.absY + 2, 0);
					}
					if (c.absY > 3321 && c.absX < 2622) {
						c.getPA().movePlayer(c.absX - 2, c.absY + 2, 0);
					}
					if (c.absY < 3321 && c.absX > 2622) {
						c.getPA().movePlayer(c.absX + 2, c.absY - 2, 0);
					}
					if (c.absY < 3321 && c.absX < 2622) {
						c.getPA().movePlayer(c.absX - 2, c.absY - 2, 0);
					}
					c.sendMessage("You cannot access this area with a familiar.");
				}
				if (c.familiarID > 0 && c.sumSpecTimer >= 60) {
					c.summoningSpecialPoints += 15;
					c.sumSpecTimer = 0;
					if (c.summoningSpecialPoints >= 10) {
						c.getPA().sendFrame126(
								"" + c.summoningSpecialPoints + "/60", 18024);
					} else {
						c.getPA().sendFrame126(
								" " + c.summoningSpecialPoints + "/60", 18024);
					}
				}
				if (c.summoningSpecialPoints > 60) {
					c.summoningSpecialPoints = 60;
					if (c.summoningSpecialPoints >= 10) {
						c.getPA().sendFrame126(
								"" + c.summoningSpecialPoints + "/60", 18024);
					} else {
						c.getPA().sendFrame126(
								" " + c.summoningSpecialPoints + "/60", 18024);
					}
				}

				if (c.timeBetweenSpecials > 0) {
					c.timeBetweenSpecials--;
				} else {
					c.timeBetweenSpecials = 0;
				}

				if (c.specHitTimer > 0) {
					c.specHitTimer--;
					if (c.specHitTimer == 1) {
						c.getSSpec().initializeHit();
					}
				}

				if (c.familiarMinutes <= 0 && c.familiarSeconds <= 0) {
					dismissFamiliar(c, true, true);
					container.stop();
					return;
				}
				if (c.familiarSeconds < 10) {
					c.getPA().sendFrame126(
							Integer.toString(c.familiarMinutes) + ".0"
									+ Integer.toString(c.familiarSeconds),
							18043);
				} else {
					c.getPA().sendFrame126(
							Integer.toString(c.familiarMinutes) + "."
									+ Integer.toString(c.familiarSeconds),
							18043);
				}
				refreshTabTimer(c);

			}

			@Override
			public void stop() {
			}
		}, 1);
	}

	public static void doTheCall(Player c) {
		callFamiliar(c, NPCHandler.npcs[c.familiarIndex]);
	}

	public static void callFamiliar(Player c, NPC n) {
		if (c.noSummon()) {
			c.sendMessage("You cannot call a familiar in this area.");
			c.sendMessage("You must either go to the bank, or go out of Edgeville, to");
			c.sendMessage("complete this action.");
			return;
		}
		if (c.familiarID > 0) {
			if (c.hasJustCalled == 0) {
				c.hasJustCalled = 4;
				n.absX = -1;
				n.absY = -1;
				n.heightLevel = -1;
				n.makeX = -1;
				n.makeY = -1;
				n.isDead = true;
				n.applyDead = true;
				n.needsToRespawn = false;
				spawnFamiliar(c, c.familiarID);
			} else {
				return;
			}
		} else {
			c.sendMessage("You don't have a familiar to call!");
		}
	}

	public static void spawnFamiliar(Player c, int npcID) {
		for (int j = 0; j < NPCHandler.npcs.length; j++) {
			if (NPCHandler.npcs[j] == null)
				emptySlot = j;
		}
		if (emptySlot == -1)
			return;
		NPC familiar = new NPC(emptySlot, npcID);
		familiar.index = emptySlot;
		for (Familiar f : Familiar.values()) {
			if (f.getNPCID() == npcID) {
				familiar.absX = c.absX;
				familiar.absY = c.absY + 1;
				familiar.makeX = c.absX;
				familiar.makeY = c.absY + 1;
				familiar.heightLevel = c.heightLevel;
				familiar.gfx0(1315);
				c.getPA().sendFrame126(":follower:on", -1);
				c.getPA().sendFrame126(
						Integer.toString(c.playerLevel[c.summoning]) + "/"
								+ Integer.toString(getLevel(c)), 18045);
				c.getPA().sendFrame126(f.getFamiliarName(), 18028);
				if (c.summoningSpecialPoints >= 10) {
					c.getPA().sendFrame126(
							"" + c.summoningSpecialPoints + "/60", 18024);
				} else {
					c.getPA().sendFrame126(
							" " + c.summoningSpecialPoints + "/60", 18024);
				}
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.bobSlotCount = f.getBoBSlotsNumber();
				familiar.HP = f.getLifePoints();
				familiar.attack = f.getAttackLevel();
				familiar.defence = f.getDefenceLevel();
			}
		}
		c.familiarIndex = emptySlot;
		c.familiarID = familiar.npcType;
		c.hasFamiliar = true;
		familiar.summonedNPC = true;
		c.setSidebarInterface(16, 18017);
		c.getPA().sendFrame75(c.familiarID, 18021);
		familiar.facePlayer(c.getId());
		familiar.followPlayer = c.getId();
		familiar.npcslot = emptySlot;
		isNotRenewing = true;
		familiar.summon = true;
		familiar.summoner = true;
		familiar.spawnedBy = c.getId();
		NPCHandler.npcs[emptySlot] = familiar;
		familiar.updateRequired = true;
		startFamiliarEvent(c, familiar);

		int[][] data = { { 6887, 7765 }, { 6885, 7765 }, { 6883, 7765 },
				{ 6881, 7765 }, { 6879, 7765 }, { 6877, 7765 }, { 7359, 8188 },
				{ 7343, 8188 }, { 7357, 7951 }, { 7341, 7987 }, { 7355, 7829 },
				{ 7339, 7881 }, { 7329, 8225 }, { 6815, 8282 }, { 6829, 8298 },
				{ 6825, 7807 }, { 6841, 8163 }, { 6796, 8108 }, { 6994, 8516 },
				{ 7370, 8091 }, { 7351, 8238 }, { 7333, 8174 }, { 7353, 7754 },
				{ 6853, 8029 }, { 6855, 8029 }, { 6857, 8029 }, { 6859, 8029 },
				{ 6861, 8029 }, { 6863, 8029 }, { 6851, 8005 }, { 6794, 8231 },
				{ 7377, 8081 }, { 6806, 8141 }, { 7367, 8075 }, { 6818, 7669 },
				{ 6820, 7683 }, { 7349, 7688 }, { 6889, 7702 }, { 6843, 7711 },
				{ 6808, 7721 }, { 6867, 7894 }, { 6813, 7736 }, { 6871, 7773 },
				{ 6804, 7783 }, { 6831, 7794 }, { 6865, 7819 }, { 7375, 7839 },
				{ 6800, 7850 }, { 7335, 7870 }, { 6798, 7894 }, { 6847, 7906 },
				{ 7365, 7909 }, { 7337, 7909 }, { 7363, 7909 }, { 6845, 7929 },
				{ 6811, 7940 }, { 7372, 7997 }, { 7331, 8037 }, { 7347, 8045 },
				{ 7345, 8049 }, { 6873, 8058 }, { 6849, 8122 }, { 6802, 8157 },
				{ 6991, 8202 }, { 6827, 8216 }, { 6833, 8252 }, { 7361, 8260 },
				{ 6822, 8266 }, { 6835, 8279 }, { 6817, 8321 }, { 6839, 8522 },
				{ 6992, 8058 }, { 6809, 7969 }, { 6824, 8014 }, { 6875, 7765 },
				{ 6869, 8309 } };
		for (int i = 0; i < data.length; i++) {
			if (c.familiarID == data[i][0]) {
				NPCHandler.npcs[c.familiarIndex].startAnimation(data[i][1]);
				return;
			}
		}
	}
}