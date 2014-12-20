package game.combat.melee;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class MeleeExtras {

	public static void handleDragonFireShield(final Player c) {
		if (PlayerHandler.players[c.playerIndex].playerLevel[3] <= 0) {
			return;
		}
		if (c.playerIndex > 0 && PlayerHandler.players[c.playerIndex] != null) {
			if (c.dfsCount < 40) {
				c.sendMessage("My shield hasn't finished charging.");
				return;
			}
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = PlayerHandler.players[c.playerIndex].getX();
			final int oY = PlayerHandler.players[c.playerIndex].getY();
			final int offX = (pY - oY) * -1;
			final int offY = (pX - oX) * -1;
			final int damage = Misc.random(25) + 5;
			c.dfsCount = 0;
			c.startAnimation(6696);
			c.gfx0(1165);
			c.attackTimer += 3;
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50,
							10, 1166, 25, 27, c.playerIndex - 1, 0);
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 3);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					PlayerHandler.players[c.playerIndex].gfx100(1167);
					PlayerHandler.players[c.playerIndex].playerLevel[3] -= damage;
					if (!c.getHitUpdateRequired()) {
						c.setHitDiff(damage);
						c.setHitUpdateRequired(true);
					} else if (!c.getHitUpdateRequired2()) {
						c.setHitDiff2(damage);
						c.setHitUpdateRequired2(true);
					}
					PlayerHandler.players[c.playerIndex].updateRequired = true;
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 3);
		}
	}

	public static void handleDragonFireShieldNPC(final Player c) {
		if (NPCHandler.npcs[c.npcIndex].HP <= 0) {
			return;
		}
		if (c.npcIndex > 0 && NPCHandler.npcs[c.npcIndex] != null) {
			if (c.dfsCount < 1) {
				c.sendMessage("I think i have to charge my shield before doing this.");
				return;
			}
			if (NPCHandler.npcs[c.npcIndex].HP <= 1) {
				return;
			}
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.npcIndex].getX();
			final int nY = NPCHandler.npcs[c.npcIndex].getY();
			final int offX = (pY - nY) * -1;
			final int offY = (pX - nX) * -1;
			final int damage = Misc.random(25) + 5;
			c.dfsCount -= 1;
			c.startAnimation(6696);
			c.gfx0(1165);
			c.attackTimer += 3;
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 50, 1166, 31, 35, - c.npcIndex  - 1, 30);
	//NPCHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
	//NPCHandler.npcs[c.npcIndex].updateRequired = true;
	//NPCHandler.npcs[c.npcIndex].hitDiff2 = damage;
	//NPCHandler.npcs[c.npcIndex].HP -= damage;
	//NPCHandler.npcs[c.npcIndex].gfx100(1167);
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 3);
			/*CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					//NPCHandler.npcs[c.npcIndex].gfx100(1167);
					NPCHandler.npcs[c.npcIndex].handleHitMask(damage, 0, 2);
					NPCHandler.npcs[c.npcIndex].HP -= damage;
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 3);*/
		}
	}

	public static void addCharge(Player c) {
		if (c.playerEquipment[c.playerShield] != 11283) {
			return;
		}
		c.dfsCount++;
		if (c.dfsCount >= 40) {
			c.dfsCount = 40;
		}
		if (c.dfsCount == 39) {
			c.sendMessage("Your dragon fireshield has finished charging!");
		}
	}

	public static void appendVengeanceNPC(Player c, int otherPlayer, int damage) {
		if (damage <= 0)
			return;
		if (c.npcIndex > 0 && NPCHandler.npcs[c.npcIndex] != null) {
			c.forcedText = "Taste vengeance!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			c.vengOn = false;
			if ((NPCHandler.npcs[c.npcIndex].HP - damage) > 0) {
				damage = (int) (damage * 0.75);
				if (damage > NPCHandler.npcs[c.npcIndex].HP) {
					damage = NPCHandler.npcs[c.npcIndex].HP;
				}
				NPCHandler.npcs[c.npcIndex].HP -= damage;
				NPCHandler.npcs[c.npcIndex].handleHitMask(damage, 0, 2);
			}
		}
		c.updateRequired = true;
	}

	public static void appendVengeance(Player c, int otherPlayer, int damage) {
		if (damage <= 0)
			return;
		Player o = PlayerHandler.players[otherPlayer];
		o.forcedText = "Taste vengeance!";
		o.forcedChatUpdateRequired = true;
		o.updateRequired = true;
		o.vengOn = false;
		if ((o.playerLevel[3] - damage) > 0) {
			damage = (int) (damage * 0.75);
			if (damage > c.playerLevel[3]) {
				damage = c.playerLevel[3];
			}
			if (!c.getHitUpdateRequired()) {
				c.setHitDiff(damage);
				c.setHitUpdateRequired(true);
			} else if (!c.getHitUpdateRequired2()) {
				c.setHitDiff2(damage);
				c.setHitUpdateRequired2(true);
			}
			c.playerLevel[3] -= damage;
			c.getPA().refreshSkill(3);
		}
		c.updateRequired = true;
	}

	public static void applyRecoilNPC(Player c, int damage, int i) {
		if (damage > 0 && c.playerEquipment[c.playerRing] == 2550) {
			int recDamage = damage / 10 + 1;
			NPCHandler.npcs[c.npcIndex].HP -= recDamage;
			NPCHandler.npcs[c.npcIndex].handleHitMask(recDamage, 0, 1);
			removeRecoil(c);
			c.recoilHits += damage;
		}
	}

	public static void applyRecoil(Player c, int damage, int i) {
		if (damage > 0
				&& PlayerHandler.players[i].playerEquipment[c.playerRing] == 2550) {
			int recDamage = damage / 10 + 1;
			if (!c.getHitUpdateRequired()) {
				c.setHitDiff(recDamage);
				c.setHitUpdateRequired(true);
			} else if (!c.getHitUpdateRequired2()) {
				c.setHitDiff2(recDamage);
				c.setHitUpdateRequired2(true);
			}
			c.dealDamage(recDamage);
			c.updateRequired = true;
			removeRecoil(c);
			c.recoilHits += damage;
		}
	}

	public static void removeRecoil(Player c) {
		if (c.recoilHits >= 400) {
			c.getItems().removeItem(2550, c.playerRing);
			c.getItems().deleteItem(2550, c.getItems().getItemSlot(2550), 1);
			c.sendMessage("Your ring of recoil shaters!");
			c.recoilHits = 0;
		} else {
			c.recoilHits++;
		}
	}

	public static void graniteMaulSpecial(Player c) {
		if (c.playerIndex > 0) {
			Player o = PlayerHandler.players[c.playerIndex];
			if (c.goodDistance(c.getX(), c.getY(), o.getX(), o.getY(), c
					.getCombat().getRequiredDistance())) {
				if (c.getCombat().checkReqs()) {
					if (c.getCombat().checkSpecAmount(4153)) {
						boolean hit = Misc.random(c.getCombat()
								.calculateMeleeAttack()) > Misc.random(o
								.getCombat().calculateMeleeDefence());
						int damage = 0;
						if (hit)
							damage = Misc.random(c.getCombat()
									.calculateMeleeMaxHit());
						if (o.prayerActive[18]
								&& System.currentTimeMillis()
										- o.protMeleeDelay > 1500)
							damage *= .6;
						if (o.playerLevel[3] - damage <= 0) {
							damage = o.playerLevel[3];
						}
						if (o.playerLevel[3] > 0) {
							o.handleHitMask(damage, 0, 0);
							c.startAnimation(1667);
							o.gfx100(337);
							o.dealDamage(damage);
						}
					}
				}
			}
		} else if (c.npcIndex > 0) {
			int x = NPCHandler.npcs[c.npcIndex].absX;
			int y = NPCHandler.npcs[c.npcIndex].absY;
			if (c.goodDistance(c.getX(), c.getY(), x, y, 2)) {
				if (c.getCombat().checkReqs()) {
					if (c.getCombat().checkSpecAmount(4153)) {
						int damage = Misc.random(c.getCombat()
								.calculateMeleeMaxHit());
						if (NPCHandler.npcs[c.npcIndex].HP - damage < 0) {
							damage = NPCHandler.npcs[c.npcIndex].HP;
						}
						if (NPCHandler.npcs[c.npcIndex].HP > 0) {
							NPCHandler.npcs[c.npcIndex].HP -= damage;
							NPCHandler.npcs[c.npcIndex].handleHitMask(damage,
									0, 1);
							//c.setAnimation(Animation.create(1667));
							c.startAnimation(1667);
							c.gfx100(337);
						}
					}
				}
			}
		}
	}
}