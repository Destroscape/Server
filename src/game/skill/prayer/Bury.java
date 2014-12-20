package game.skill.prayer;

import game.entity.player.Player;

public class Bury {

	/**
	 * Burys the bone
	 * 
	 * @param c
	 *            Client
	 * @param i
	 *            ID
	 * @param slot
	 *            Slot ID
	 */

	public static void buryBones(final Player c, final int i, final int slot) {
		for (final int[] element : Constants.BONES) {
			if (i == element[0]) {
				if (System.currentTimeMillis() - c.buryDelay > 800) {
					c.getItems().deleteItem(element[0], slot, 1);
					if (c.playerEquipment[c.playerHands] == 13848) {
						c.getPA().addSkillXP((element[1]) * Constants.PRAYER_XP * 2,
								Constants.PRAYER);
					} else {
						c.getPA().addSkillXP((element[1]) * Constants.PRAYER_XP,
								Constants.PRAYER);
					}
					c.buryDelay = System.currentTimeMillis();
					//c.setAnimation(Animation.create(827));
					c.startAnimation(827);
					c.sendMessage("You bury the bones.");
				}
			}
		}
	}

}
