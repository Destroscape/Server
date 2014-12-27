package game.skill.prayer;

import game.entity.player.Player;
import game.skill.SkillHandler;

public class Constants {

	/**
	 * Prayer level
	 */

	protected static final int PRAYER = 5;
	
	/**
	 * Prayer XP Rate
	 */
	
	protected static final int PRAYER_XP = SkillHandler.PRAYER_XP;

	/**
	 * Checks if item is bone
	 * 
	 * @param c
	 *            Client
	 * @param item
	 *            Bone
	 * @return If it is a bone
	 */

	public static boolean playerBones(final Player c, final int item) {
		for (int i = 0; i < BONES.length; i++) {
			if (item == BONES[i][0])
				return item == BONES[i][0];
		}
		return false;
	}

	/**
	 * Data for bones
	 */

	public static int[][] BONES = { { 526, 5 }, // NPC BONES
			{ 528, 5 }, // BURNT BONES
			{ 530, 5 }, // BAT BONES
			{ 2859, 5 }, // WOLF BONES
			{ 3179, 5 }, // MONKEY BONES
			{ 3180, 5 }, // MONKEY BONES
			{ 3181, 5 }, // MONKEY BONES
			{ 3182, 5 }, // MONKEY BONES
			{ 3183, 5 }, // MONKEY BONES
			{ 3185, 5 }, // MONKEY BONES
			{ 3186, 5 }, // MONKEY BONES
			{ 3187, 5 }, // MONKEY BONES
			{ 532, 30 }, // BIG BONES
			{ 534, 30 }, // BABY DRAGON BONES
			{ 536, 120 }, // DRAGON BONES
			{ 2530, 5 }, // PLAYER BONES
			{ 3123, 25 }, // SHAIKAHAN BONES
			{ 3125, 23 }, // JOGRE BONES
			{ 3127, 25 }, // BURNT JOGRE BONES
			{ 4812, 82 }, // ZOGRE BONES
			{ 4830, 84 }, // FAYGR BONES
			{ 4832, 96 }, // RAURG BONES
			{ 4834, 140 }, // OURG BONES
			{ 6729, 145 }, // DAGANNOTH BONES
			{ 6812, 50 }, // WYVERN BONES
			{ 18830, 180 }, // FROST BONES
			{ 15410, 250 }, // Ancient Bones
	};

}
