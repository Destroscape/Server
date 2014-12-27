package game.combat.melee;

import game.Config;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class MeleeRequirements {

	public static int getKillerId(Player c, int playerId) {
		int oldDamage = 0;
		int killerId = 0;
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].killedBy == playerId) {
					if (PlayerHandler.players[i]
							.withinDistance(PlayerHandler.players[playerId])) {
						if (PlayerHandler.players[i].totalPlayerDamageDealt > oldDamage) {
							oldDamage = PlayerHandler.players[i].totalPlayerDamageDealt;
							killerId = i;
						}
					}
					PlayerHandler.players[i].totalPlayerDamageDealt = 0;
					PlayerHandler.players[i].killedBy = 0;
				}
			}
		}
		return killerId;
	}

	public static int getCombatDifference(int combat1, int combat2) {
		if (combat1 > combat2) {
			return (combat1 - combat2);
		}
		if (combat2 > combat1) {
			return (combat2 - combat1);
		}
		return 0;
	}

	public static boolean checkReqs(Player c) {
		Player c2 = (Player) PlayerHandler.players[c.playerIndex];
		if (c.playerEquipment[c.playerWeapon] == 11951) {
			if (!c2.inWild() && !c2.inDuelArena()) {
				c.snowball(c);
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return false;
			}
		}
		if (PlayerHandler.players[c.playerIndex] == null) {
			return false;
		}
		if (c.playerIndex == c.playerId)
			return false;
		if (PlayerHandler.players[c.playerIndex].inDuelArena()
				&& c.duelStatus != 5 && !c.usingMagic) {
			if (c.arenas() || c.duelStatus == 5) {
				c.sendMessage("You can't challenge inside the arena!");
				return false;
			}
			c.getTradeAndDuel().requestDuel(c.playerIndex);
			return false;
		}
		if (c.duelStatus == 5
				&& PlayerHandler.players[c.playerIndex].duelStatus == 5) {
			if (PlayerHandler.players[c.playerIndex].duelingWith == c.getId()) {
				return true;
			} else {
				c.sendMessage("This isn't your opponent!");
				return false;
			}
		}
		if(!PlayerHandler.players[c.playerIndex].inPvP() && PlayerHandler.players[c.playerIndex].safeTimer <= 0 && PlayerHandler.players[c.playerIndex].inSafeZone() && !c.inFunPk()) {
			c.sendMessage("@red@That player is in a safe zone!");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if(c.inFunPk() && !PlayerHandler.players[c.playerIndex].inFunPk()) {
			c.sendMessage("@red@Don't even try that.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
			/*if(!c.isPVPActive && c.safeTimer <= 1 && !c.inFunPk()) {
				c.sendMessage("@red@You do not have PVP active!");
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return false;
			}
			if(!PlayerHandler.players[c.playerIndex].isPVPActive && PlayerHandler.players[c.playerIndex].safeTimer <= 1 && !c.inFunPk()) {
				c.sendMessage("@red@That player does not have PVP active!");
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return false;
			}*/
		if(c.inSafeZone() && c.safeTimer <= 1 && !c.inFunPk()) {
			c.sendMessage("@red@You are in a safe zone!");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if (PlayerHandler.players[c.playerIndex].safeTimer <= 0 && !PlayerHandler.players[c.playerIndex].inWild() && !c.inWeaponGame()) {
			c.sendMessage("Your opponent is not in the wilderness.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if (c.safeTimer <= 0 && !c.inWild() && !c.inWeaponGame() && !c.inFunPk()) {
			c.sendMessage("You are not in the wilderness.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if (Config.COMBAT_LEVEL_DIFFERENCE) {
			if (c.inWild()) {
				int combatDif1 = getCombatDifference(c.combatLevel,
						PlayerHandler.players[c.playerIndex].combatLevel);
				if ((combatDif1 > c.wildLevel || combatDif1 > PlayerHandler.players[c.playerIndex].wildLevel)) {
					c.sendMessage("Your combat level difference is too great to attack that player here.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
			} else if (c.inWeaponGame()) {
				int myCB = c.combatLevel;
				int pCB = PlayerHandler.players[c.playerIndex].combatLevel;
				if ((myCB > pCB + 38) || (myCB < pCB - 38)) {
					c.sendMessage("You can only fight players in your combat range!");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
			} else {
				int myCB = c.combatLevel;
				int pCB = PlayerHandler.players[c.playerIndex].combatLevel;
				if ((myCB > pCB + 12) || (myCB < pCB - 12)) {
					c.sendMessage("You can only fight players in your combat range!");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
			}
		}
		if (Config.SINGLE_AND_MULTI_ZONES) {
			if (!PlayerHandler.players[c.playerIndex].inMulti()) { // single
																	// combat
																	// zones
				if (PlayerHandler.players[c.playerIndex].underAttackBy != c.playerId
						&& PlayerHandler.players[c.playerIndex].underAttackBy != 0) {
					c.sendMessage("That player is already in combat.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
				if (PlayerHandler.players[c.playerIndex].playerId != c.underAttackBy
						&& c.underAttackBy != 0 || c.underAttackBy2 > 0) {
					c.sendMessage("You are already in combat.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
			}
		}
		return true;
	}

	public static int getRequiredDistance(Player c) {
		if (c.followId > 0 && c.freezeTimer <= 0 && !c.isMoving)
			return 2;
		else if (c.followId > 0 && c.freezeTimer <= 0 && c.isMoving) {
			return 3;
		} else {
			return 1;
		}
	}
}