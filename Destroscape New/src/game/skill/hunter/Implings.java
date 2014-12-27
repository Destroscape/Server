package game.skill.hunter;

import java.util.HashMap;

import engine.util.Misc;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class Implings {

	/**
	 * Handles all data for the implings
	 */

	private static enum Imps {

		BABY_IMPLING(1028, 17, 40, 11238), YOUNG_IMPLING(1029, 22, 80, 11240), GOURMET_IMPLING(
				1030, 28, 128, 11242), EARTH_IMPLING(1031, 36, 192, 11244), ESSENCE_IMPLING(
						1032, 42, 270, 11246), ECLECTIC_IMPLING(1033, 50, 304, 11248), NATURE_IMPLING(
								1034, 58, 368, 11250), MAGPIE_IMPLING(1035, 65, 500, 11252), NINJA_IMPLING(
										6063, 74, 550, 11254), DRAGON_IMPLING(6064, 83, 660, 11256);

		private int impId, level, xp, jar;

		private Imps(final int impId, final int level, final int xp,
				final int jar) {
			this.impId = impId;
			this.level = level;
			this.xp = xp;
			this.jar = jar;
		}

		private int getImpID() {
			return impId;
		}

		private int getLevel() {
			return level;
		}

		private int getXP() {
			return xp * 20;
		}

		private int getJar() {
			return jar;
		}

		private String getName() {
			return Misc.optimizeText(toString().toLowerCase().replaceAll("_",
					" "));
		}

		public static HashMap<Integer, Imps> implings = new HashMap<Integer, Imps>();

		static {
			for (final Imps impling : Imps.values()) {
				Imps.implings.put(impling.getImpID(), impling);
			}
		}

	}

	/**
	 * Checks if player can catch an imp
	 * 
	 * @param c
	 *            Client
	 * @param ID
	 *            NPC Being clicked on
	 * @return Returns whether or not player can catch the imp
	 */
	private static boolean canCatch(final Player c, final int ID,
			final int impId) {
		Imps i = Imps.implings.get(impId);
		if (i == null) {
			return false;
		}
		if (System.currentTimeMillis() - c.lastThieve < 2000) {
			return false;
		}
		c.lastThieve = System.currentTimeMillis();
		if (i.getLevel() > c.getLevelForXP(c.playerXP[Constants.HUNTER])) {
			c.sendMessage("You need a hunter level of at least " + i.getLevel()
					+ " to catch this impling.");
			return false;
		}
		if (!Constants.hasRegularNet(c) && !Constants.hasMagicNet(c)) {
			c.sendMessage("You must have a butterfly net wielded in order to catch an imp.");
			return false;
		}
		if (Constants.hasMagicNet(c) && c.getItems().freeSlots() <= 0) {
			c.sendMessage("You must have at least 1 free slot when catching an imp with that net!");
			return false;
		}
		if (!c.getItems().playerHasItem(Constants.JAR)
				&& !Constants.hasMagicNet(c)) {
			c.sendMessage("You must have at least 1 impling jar with you, in order to catch an imp.");
			return false;
		}
		return true;
	}

	/**
	 * Handles the actual catching
	 * 
	 * @param c
	 *            Client
	 * @param ID
	 *            Npc being clicked
	 */

	public static void catchImp(final Player c, final int ID, final int impId) {
		Imps i = Imps.implings.get(impId);
		if (i == null) {
			return;
		}
		if (!canCatch(c, ID, impId)) {
			return;
		}
		if (Misc.random(4) >= ((c.playerLevel[Constants.HUNTER] - 10) / 10) + 1) {
			c.sendMessage("You fail catching the " + i.getName() + "!");
			return;
		}
		if (NPCHandler.npcs[ID].isDead) {
			return;
		}
		c.getItems()
		.deleteItem(Constants.JAR, Constants.hasMagicNet(c) ? 0 : 1);
		c.getItems().addItem(i.getJar(), 1);
		if (c.playerEquipment[c.playerHands] == 13853) {
			c.getPA().addSkillXP(
					Constants.hasMagicNet(c) ? i.getXP() * 2 : i.getXP() * 2,
							Constants.HUNTER);
		} else {
			c.getPA().addSkillXP(
					Constants.hasMagicNet(c) ? i.getXP() * 2 : i.getXP(),
							Constants.HUNTER);
		}
		//c.setAnimation(Animation.create(6999));
		c.startAnimation(6999);
		NPCHandler.npcs[ID].isDead = true;
		NPCHandler.npcs[ID].updateRequired = true;
		c.sendMessage("You catch the " + i.getName() + "!");
	}

	/**
	 * Impling Loot Handling Below
	 */

	private final static int[] babyLoots = { 995, 113, 1742, 1208, 5293, 1437,
		313, 314, 1335 };
	private final static int[] babyAmount = { 275, 1, 4, 1, 3, 12, 7, 11, 1 };

	private final static int[] youngLoots = { 995, 2349, 1605, 1522, 526, 1177,
		556, 227, 225 };
	private final static int[] youngAmount = { 450, 1, 3, 8, 1, 1, 17, 1, 1 };

	private final static int[] gormLoots = { 995, 316, 323, 333, 352, 361, 373 };
	private final static int[] gormAmount = { 630, 5, 1, 1, 3, 1, 1 };

	private final static int[] earthLoots = { 995, 1520, 5293, 1605, 133, 557,
		1440, 1119 };
	private final static int[] earthAmount = { 850, 3, 6, 1, 1, 50, 1, 1 };

	private final static int[] essLoots = { 995, 555, 556, 557, 558, 559, 560,
		561, 562, 563, 564, 565, 566 };
	private final static int[] essAmount = { 1125, 25, 25, 25, 25, 25, 25, 25,
		25, 25, 25, 10 };

	private final static int[] eclecticLoots = { 995, 1617, 1734, 207, 2442,
		1726 };
	private final static int[] eclecticAmount = { 1350, 1, 45, 1, 1, 2 };

	private final static int[] natureLoots = { 995, 7945, 1462, 561, 444, 1065,
		1381 };
	private final static int[] natureAmount = { 1755, 5, 1, 11, 1, 1, 1 };

	private final static int[] magpieLoots = { 995, 1712, 892, 861, 1215, 1428,
		2437 };
	private final static int[] magpieAmount = { 2016, 1, 45, 1, 1, 1, 3 };

	private final static int[] ninjaLoots = { 995, 2481, 5300, 1334, 44, 53,
		1085 };
	private final static int[] ninjaAmount = { 2733, 1, 2, 2, 20, 40, 1 };

	private final static int[] dragonLoots = { 995, 4587, 1377, 11237, 11237,
		1434, 1616, 4131, 452, 9244 };
	private final static int[] dragonAmount = { 4267, 1, 1, 25, 17, 1, 5, 1,
		15, 50 };

	/**
	 * Handles looting for baby implings
	 * 
	 * @param c
	 *            Client
	 * @param jar
	 *            Imp Jar
	 */

	public static void lootJar(final Player c, final int jar) {
		int loot = 0;
		int[] type = null;
		int[] amount = null;
		if (!c.getItems().playerHasItem(jar)) {
			return;
		}
		if (c.getItems().freeSlots() < 1) {
			c.sendMessage("You must have at least 1 inventory slots open, when looting an impling.");
			return;
		}
		c.getItems().deleteItem(jar, 1);
		switch (jar) {
		case 11238:
			loot = (Misc.random(babyLoots.length - 1));
			type = babyLoots;
			amount = babyAmount;
			break;
		case 11240:
			loot = (Misc.random(youngLoots.length - 1));
			type = youngLoots;
			amount = youngAmount;
			break;
		case 11242:
			loot = (Misc.random(gormLoots.length - 1));
			type = gormLoots;
			amount = gormAmount;
			break;
		case 11244:
			loot = (Misc.random(earthLoots.length - 1));
			type = earthLoots;
			amount = earthAmount;
			break;
		case 11246:
			loot = (Misc.random(essLoots.length - 1));
			type = essLoots;
			amount = essAmount;
			break;
		case 11248:
			loot = (Misc.random(eclecticLoots.length - 1));
			type = eclecticLoots;
			amount = eclecticAmount;
			break;
		case 11250:
			loot = (Misc.random(natureLoots.length - 1));
			type = natureLoots;
			amount = natureAmount;
			break;
		case 11252:
			loot = (Misc.random(magpieLoots.length - 1));
			type = magpieLoots;
			amount = magpieAmount;
			break;
		case 11254:
			loot = (Misc.random(ninjaLoots.length - 1));
			type = ninjaLoots;
			amount = ninjaAmount;
			break;
		case 11256:
			loot = (Misc.random(dragonLoots.length - 1));
			type = dragonLoots;
			amount = dragonAmount;
			break;
		}
		c.getItems().addItem(type[loot], amount[loot]);
		if (Constants.breakJar(c)) {
			c.sendMessage("The impling jar shatters as you try to loot it.");
			return;
		}
		c.getItems().addItem(Constants.JAR, 1);
	}

}