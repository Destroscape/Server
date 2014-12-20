package game.skill.hunter;

import java.util.HashMap;

import engine.util.Misc;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class ButterflyCatching {

	/**
	 * Butterfly Catching
	 */

	private static enum Butterfly {

		RUBY_HARVEST(5085, 1, 30, 10020), 
		SAPPHIRE_GLACIALIS(5084, 25, 75, 10018), 
		SNOWY_KNIGHT(5083, 50, 225, 10016), 
		BLACK_WARLOCK(5082, 75, 750, 10014);

		private int butterfly, level, xp, jar;

		private Butterfly(final int butterfly, final int level, final int xp,
				final int jar) {
			this.butterfly = butterfly;
			this.level = level;
			this.xp = xp;
			this.jar = jar;
		}

		private int getButterfly() {
			return butterfly;
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

		public static HashMap<Integer, Butterfly> butterflys = new HashMap<Integer, Butterfly>();

		static {
			for (final Butterfly b : Butterfly.values()) {
				Butterfly.butterflys.put(b.getButterfly(), b);
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
			final int butterflyId) {
		Butterfly b = Butterfly.butterflys.get(butterflyId);
		if (b == null) {
			return false;
		}
		if (System.currentTimeMillis() - c.hunterDelay < 2000) {
			return false;
		}
		c.hunterDelay = System.currentTimeMillis();
		if (b.getLevel() > c.getLevelForXP(c.playerXP[Constants.HUNTER])) {
			c.sendMessage("You need a hunter level of at least " + b.getLevel()
					+ " to catch this butterfly.");
			return false;
		}
		if (!Constants.hasRegularNet(c) && !Constants.hasMagicNet(c)) {
			c.sendMessage("You must have a butterfly net wielded in order to catch a butterfly.");
			return false;
		}
		if (Constants.hasMagicNet(c) && c.getItems().freeSlots() <= 0) {
			c.sendMessage("You must have at least 1 free slot when catching a butterfly with that net!");
			return false;
		}
		if (!c.getItems().playerHasItem(Constants.BUTTERFLY_JAR)
				&& !Constants.hasMagicNet(c)) {
			c.sendMessage("You must have at least 1 butterfly jar with you, in order to catch a butterfly.");
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
	 * @param butterfly
	 *            Butterfly id
	 */

	public static void catchButterfly(final Player c, final int ID,
			final int butterflyId) {
		Butterfly b = Butterfly.butterflys.get(butterflyId);
		if (b == null) {
			return;
		}
		if (!canCatch(c, ID, butterflyId)) {
			return;
		}
		if (Misc.random(20) >= c.playerLevel[Constants.HUNTER]
				&& !(c.playerLevel[Constants.HUNTER] <= 10)) {
			c.sendMessage("You fail catching the " + b.getName() + "!");
			return;
		}
		if (NPCHandler.npcs[ID].isDead) {
			return;
		}
		c.getItems().deleteItem(Constants.BUTTERFLY_JAR,
				Constants.hasMagicNet(c) ? 0 : 1);
		c.getItems().addItem(b.getJar(), 1);
		if (c.playerEquipment[c.playerHands] == 13853) {
			c.getPA().addSkillXP(Constants.hasMagicNet(c) ? b.getXP() * 2 : b.getXP() * 2, Constants.HUNTER);
		} else {
			c.getPA().addSkillXP(Constants.hasMagicNet(c) ? b.getXP() * 2 : b.getXP(), Constants.HUNTER);
		}
		//c.setAnimation(Animation.create(6999));
		c.startAnimation(6999);
		NPCHandler.npcs[ID].isDead = true;
		NPCHandler.npcs[ID].updateRequired = true;
		c.sendMessage("You catch the " + b.getName() + "!");
	}

	/**
	 * Handles the release of the butterfly
	 * 
	 * @param c
	 *            Client
	 * @param jar
	 *            Jar ID
	 */

	public static void releaseButterfly(final Player c, final int jar) {
		if (!c.getItems().playerHasItem(jar)) {
			return;
		}
		c.getItems().deleteItem2(jar, 1);
		if (Constants.breakJar(c)) {
			c.sendMessage("The butterfly jar shatters as you release the creature inside!");
			return;
		}
		c.getItems().addItem(Constants.BUTTERFLY_JAR, 1);
	}

}
