package game.combat.prayer;

import engine.util.Misc;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class PrayerEffects {

	Player c;

	/*
	 * Combat types
	 */
	static int MELEE = 0, RANGE = 1, MAGE = 2;

	/**
	 * Prayer multiplier by combatType
	 * 
	 * @param player
	 *            player
	 * @param combatType
	 *            combat type..
	 * @return multiplier
	 */
	public static double getPrayerMultiplier(Player player, int combatType) {
		if (player.prayerActive[16] && combatType == MAGE
				|| player.prayerActive[17] && combatType == RANGE
				|| player.prayerActive[18] && combatType == MELEE)// protect
																	// from
																	// magic
			return 0.4;
		return 1;
	}

	/**
	 * Current Smite
	 */

	public static void applySmite(Player c, int index, int damage) {
		if (!c.prayerActive[23] && !c.curseActive[18])
			return;
		if (damage <= 0)
			return;
		if (PlayerHandler.players[index] != null) { 
			Player c2 = (Player)PlayerHandler.players[index];
			if(c.curseActive[18] && !c.prayerActive[23] && c.playerLevel[3] <= 99) {
				int ssheal = (int)(damage/5);
				if(c.playerLevel[3] + ssheal >= c.getPA().getLevelForXP(c.playerXP[3])) {
					c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				} else {
					c.playerLevel[3] += ssheal;
				}
				c.getPA().refreshSkill(3);
			}
			if (c2.playerLevel[5] <= 0) {
				c2.playerLevel[5] = 0;
				Prayer.closeAllPrayers(c2);
			}
			c2.getPA().refreshSkill(5);
		}
	}

	/**
	 * Handles the smite effect f
	 * 
	 * @param player
	 *            player receiving the damage
	 * @param victim
	 *            player sending the damage
	 * @param damage
	 *            damage sended
	 */
	public static void appendSmite(Player player, Player victim, int damage) {
		int drain = damage / 4;
		if (player.prayerActive[23] && damage > 0 && victim.prayer > 0) {
			if (victim.prayer - drain < 0)
				victim.prayer = 0;
			victim.prayer -= drain;
		}
		victim.getPA().refreshSkill(victim.playerPrayer);
	}

	/**
	 * Handles the redemption effect
	 * 
	 * @param player
	 *            player having the effect
	 */
	public static void appendRedemption(Player player) {
		double heal = (player.getMaxPrayer() * 2.5) / 10;
		player.gfx0(436);
		player.prayer = 0;
		player.constitution += heal;
		player.sendMessage("Healed " + heal);
		player.getPA().refreshSkill(3);
		player.getPA().refreshSkill(5);
	}

	/**
	 * Handles retribution and wrath prayer
	 * 
	 * @param player
	 *            player sending the prayer
	 * @param wrath
	 *            if it's wrath or regular retribution
	 */
	public static void appendRetribution(Player player, boolean wrath) {
		int damage = Misc.random(player.getMaxPrayer() / 10);
		int index = 0;
		for (Player p : PlayerHandler.players) {
			if (p != null)
				if (p.playerIndex == player.playerId)
					index = p.playerId;
		}
		player.gfx0(wrath ? 0 : 437);
		/*
		 * if (player.inMulti()) {
		 * 
		 * } else {
		 */
		PlayerHandler.players[index].dealDamage(damage);
		PlayerHandler.players[index].handleHitMask(damage, 0, 2);
		// }
	}

	/**
	 * Soulsplit prayers done in two parts.
	 */

	public static void appendSoulSplit(Player player, Player victim,
			int combatType) {
		if (player.soulsplit
				|| !player.goodDistance(player.absX, player.absY, victim.absX,
						victim.absY, 1) && combatType == 0
				|| !player.prayerActive[44])
			return;
		player.soulsplit = true;
		int pX = player.getX(), pY = player.getY(), oX = victim.getX(), oY = victim
				.getY(), offX = (pY - oY) * -1, offY = (pX - oX) * -1;

		player.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 80,
				2263, 43, 31, -player.playerIndex - 1, 0);
	}

	/**
	 * Handles the soul split prayer
	 * 
	 * @param player
	 *            player sending the soulsplit
	 * @param victim
	 *            prayer receiving the soulsplit
	 */
	public static void finishSoulSplit(Player player, Player victim, int damage) {
		if (player.prayerActive[44]) {
			int pX = victim.getX(), pY = victim.getY(), oX = player.getX(), oY = player
					.getY(), offX = (pY - oY) * -1, offY = (pX - oX) * -1;
			victim.gfx0(2264);
			victim.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 80,
					2263, 43, 31, -victim.playerIndex - 1, 0);
			int heal = damage / 4;
			int drain = damage / 4;
			if (player.constitution + heal > player.getPA().getLevelForXP(
					player.playerXP[3]))
				player.constitution = player.getPA().getLevelForXP(
						player.playerXP[3]);
			else
				player.constitution += heal;
			if (victim.prayer - drain < 0)
				victim.prayer = 0;
			else
				victim.prayer -= damage / 4;
			player.getPA().refreshSkill(3);
			victim.getPA().refreshSkill(5);
			player.soulsplit = false;
		}
	}

	/**
	 * Deflecting prayers
	 * 
	 * @param c2
	 * @param damage
	 * @param icon
	 */
	public static void appendDeflect(Player c2, Player c, int damage, int icon) {
		//c2.setAnimation(Animation.create(12573));
		c2.startAnimation(12573);
		switch (icon) {
		case 0:
			if (c2.prayerActive[35])
				c2.gfx0(2230);
			break;
		case 1:
			if (c2.prayerActive[34])
				c2.gfx0(2229);
			break;
		case 2:
			if (c2.prayerActive[33])
				c2.gfx0(2228);
			break;
		}
		// new Hit(c2, c, damage / 10, 3, 0);
	}

}
