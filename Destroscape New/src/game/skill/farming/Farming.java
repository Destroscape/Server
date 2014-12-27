package game.skill.farming;

import engine.util.Misc;
import game.entity.player.Player;
import game.skill.SkillHandler;

public class Farming extends SkillHandler{

	private Player c;

	private final static int[] VALID_SEEDS = { 5291, 5292, 5293, 5294, 5295,
		5296, 5297, 5298, 5299, 5300, 5301, 5302, 5303, 5304 };
	private final static int[] HERBS = { 199, 201, 203, 205, 207, 3049, 209,
		211, 213, 3051, 215, 2485, 217, 219 };
	private final static int[] SEED_PLANT_EXP = { 11, 14, 16, 22, 27, 34, 43,
		55, 69, 88, 107, 135, 171, 200 };
	private final static int[] HERB_EXPS = { 13, 15, 18, 24, 31, 39, 49, 62,
		78, 99, 120, 152, 192, 225 };
	private final static int[] FARMING_REQS = { 1, 14, 19, 26, 32, 38, 44, 50,
		56, 62, 67, 73, 79, 85 };

	private final static int[] SEED_PLANT_EXP2 = { 20, 25, 30, 35, 40, 45, 50,
		60, 75, 95, 120, 160, 200, 250 };
	private final static int[] HERB_EXPS2 = { 20, 30, 35, 40, 50, 65, 75, 80,
		100, 110, 140, 170, 210, 270 };
	/* all of our arrays are fine */

	/**
	 * Single integars
	 */
	private final static int PATCH_HERBS = 8143;
	private final static int PATCH_CLEAN = 8132;
	private final static int PATCH_WEEDS = 8151;
	private final static int WATER_CAN = 5340;
	private final static int RAKE = 5341;
	private final static int SEED_DIBBER = 5343;

	/*
	 * all of our static integers are fine * /* all booleans are false, cool
	 */

	private static final int SEED_BOX = 15367;

	private static final int[] SEEDS = { 5291, 5292, 5293, 5294, 5295, 5296,
		5297, 5298, 5299, 5300, 5301, 5302, 5303 };

	public static void openSeedBox(Player c, int item) {
		if (item == SEED_BOX) {
			c.getItems().deleteItem2(SEED_BOX, 1);
			for (int i = 0; i < SEEDS.length; i++) {
				if (c.getItems().freeSlots() < 1) {
					c.getItems().createGroundItem(SEEDS[i], c.absX, c.absY, 1);
				} else {
					c.getItems().addItem(SEEDS[i], 10);
				}
			}
			if (Misc.random(1) == 0) {
				if (c.getItems().freeSlots() < 1) {
					c.getItems().createGroundItem(5304, c.absX, c.absY, 1);
				} else {
					c.getItems().addItem(5304, 1);
					c.sendMessage("You were lucky and found a torstol seed!");
				}
			}
		}
	}

	/**
	 * Constructor
	 */
	public Farming(Player c) {
		this.c = c;
	}

	public void checkItemOnObject(int itemId) {
		for (int j = 0; j < VALID_SEEDS.length; j++) {
			if (itemId == VALID_SEEDS[j]) {
				plantSeed(VALID_SEEDS[j], HERBS[j], HERB_EXPS[j], j);
			} // so if you use valid seeds on the patch it does plantSeed, cool.
		}
		if (itemId == WATER_CAN && !c.seedWatered) {
			waterSeed(); // watering is called
		} else if (itemId == RAKE && !c.patchRaked) {
			rakePatch(); // raking is called
		}

	}

	private void plantSeed(int seedId, int herbId, int exp, int slot) {
		if (c.playerLevel[c.playerFarming] < FARMING_REQS[slot]) {
			c.sendMessage("You require a farming level of "
					+ FARMING_REQS[slot] + " to farm this seed.");
		} else if (!c.seedPlanted && c.patchRaked
				&& c.getItems().playerHasItem(seedId, 1)
				&& c.getItems().playerHasItem(SEED_DIBBER, 1)) {
			c.getItems()
			.deleteItem(seedId, c.getItems().getItemSlot(seedId), 1);
			if (c.isPVPActive == true) {
				c.getPA().addSkillXP(
						SEED_PLANT_EXP2[slot] * FARMING_XP,
						c.playerFarming);
			} else {
				c.getPA().addSkillXP(
						SEED_PLANT_EXP[slot] * FARMING_XP,
						c.playerFarming);
			}
			//c.setAnimation(Animation.create(2291));
			c.startAnimation(2291);
			c.getPA().refreshSkill(c.playerFarming);
			int herbAmount = Misc.random(5) + 3;
			c.farm[0] = herbId;
			c.farm[1] = herbAmount;
			c.sendMessage("You have planted the seed. Now just water it, and it will grow.");
			c.seedPlanted = true;
		} else {
			if (c.patchRaked) {
				return;
			} else {
				c.sendMessage("You must rake the patch or you have already planted a seed.");
			}
		}
	}

	private void waterSeed() {
		if (c.seedPlanted && !c.seedWatered) {
			//c.setAnimation(Animation.create(2293));
			c.startAnimation(2293);
			updateHerbPatch();
			c.seedWatered = true;
		} else {
			c.sendMessage("You have to plant a seed before you can water the patch.");
		}
	}

	/* seed watering, cool */
	public int getExp() {
		for (int j = 0; j < HERBS.length; j++) {
			if (HERBS[j] == c.farm[0]) {
				return HERB_EXPS[j];
			}
		}
		return 0;
	}

	public int getExp2() {
		for (int j = 0; j < HERBS.length; j++) {
			if (HERBS[j] == c.farm[0]) {
				return HERB_EXPS2[j];
			}
		}
		return 0;
	}

	/* this works awesome!!! */

	private void cleanPatch() {
		if (!c.patchCleaned) {
			c.getPA().object(PATCH_CLEAN, 2813, 3463, -1, 10);
			c.patchCleaned = true;
		} else {
			c.sendMessage("You have already cleaned the patch.");
		}
	}

	/* cleans off the patch during raking, awesome */

	public void updateHerbPatch() {
		if (c.farm[0] > 0 && c.farm[1] > 0) {
			c.getPA().object(PATCH_HERBS, 2813, 3463, -1, 10);
		} else {
			c.getPA().object(PATCH_WEEDS, 2813, 3463, -1, 10);
			c.patchRaked = false;
			c.patchCleaned = false;
			c.seedWatered = false;
			c.seedPlanted = false;
		}
	}

	/* updates to herb or weed. the clean patch should be part too but fuck it */
	private void rakePatch() { // /step three, once you've planted and watered
		// you can rake the patch clean to pick your
		// herbs.
		if (!c.patchRaked && System.currentTimeMillis() - c.waitTime > 2000) {
			//c.setAnimation(Animation.create(2273));
			c.startAnimation(2273);
			cleanPatch(); // calls the clear patch.
			if (c.isPVPActive == true) {
				c.getPA().addSkillXP(16 * FARMING_XP,
						c.playerFarming);
			} else {
				c.getPA().addSkillXP(10 * FARMING_XP,
						c.playerFarming);
			}
			c.patchRaked = true;
			c.sendMessage("You can now grab a seed dibber, and plant your seed.");
		} else {
			c.sendMessage("You must plant and water a seed before you can rake here!");
		}
	}

	public void pickHerb() { // / Final step, picks the herbs from the grown
		// herb patch.
		if (c.farm[0] > 0 && c.farm[1] > 0) {
			if (System.currentTimeMillis() - c.waitTime > 2000) {
				if (c.getItems().addItem(c.farm[0], 1)) {
					if (c.isPVPActive == true) {
						c.getPA().addSkillXP(getExp2() * FARMING_XP,
								c.playerFarming);
					} else {
						c.getPA().addSkillXP(getExp() * FARMING_XP,
								c.playerFarming);
					}
					c.farm[1]--;
					if (c.farm[1] == 0) {
						c.farm[0] = -1;
					}
					//c.setAnimation(Animation.create(2286));
					c.startAnimation(2286);
					c.sendMessage("You pick a herb.");
					updateHerbPatch(); // finish, lets try this.
				}
			}
		}
	}
}