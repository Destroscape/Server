package game.combat;

import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class Specials {

	Player c;

	public static void handleGmaul(final Player c) {
		if (c.npcIndex > 0 && NPCHandler.npcs[c.npcIndex] != null) {
			if (c.goodDistance(c.getX(), c.getY(), NPCHandler.npcs[c.npcIndex]
					.getX(), NPCHandler.npcs[c.npcIndex].getY(), c.getCombat()
					.getRequiredDistance())) {
				if (c.getCombat().checkSpecAmount(4153)) {
					boolean hit = Misc.random(c.getCombat()
							.calculateMeleeAttack()) > Misc
							.random(NPCHandler.npcs[c.npcIndex].defence);
							int damage = 0;
							if (hit) {
								damage = Misc.random(c.getCombat()
										.calculateMeleeMaxHit());
								NPCHandler.npcs[c.npcIndex].HP -= damage;
								NPCHandler.npcs[c.npcIndex].hitDiff2 = damage;
								NPCHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
								NPCHandler.npcs[c.npcIndex].updateRequired = true;
								//c.setAnimation(Animation.create(1667));
								c.startAnimation(1667);
								c.gfx100(340);
							}
				}
			}
		} else if (c.playerIndex > 0) {
			final Player o = PlayerHandler.players[c.playerIndex];
			if (c.goodDistance(c.getX(), c.getY(), o.getX(), o.getY(), c
					.getCombat().getRequiredDistance())) {
				if (c.getCombat().checkReqs()) {
					if (c.getCombat().checkSpecAmount(4153)) {
						final boolean hit = Misc.random(c.getCombat()
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
									//c.setAnimation(Animation.create(1667));
									c.startAnimation(1667);
									o.gfx100(337);
									o.dealDamage(damage);
								}
					}
				}
			}
		}
	}

	public static boolean checkSpecAmount(Player c, int weapon) {
		if(c.playerEquipment[c.playerRing] == 19669) {
			c.specAmount += c.specAmount*0.1;
		}
		switch (weapon) {
		case 19780: // korasi
			if (c.specAmount >= 6) {
				c.specAmount -= 6;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 1249:
		case 1215:
		case 1231:
		case 5680:
		case 5698:
		case 1305:
		case 1434:
			if (c.specAmount >= 2.5) {
				c.specAmount -= 2.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 4151:
		case 11694:
		case 11698:
		case 4153:
		case 14484:
		case 10887:
		case 13905:
			if (c.specAmount >= 5) {
				c.specAmount -= 5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 3204:
		case 13902:
			if (c.specAmount >= 3) {
				c.specAmount -= 3;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 13899:
			if (c.specAmount >= 2.5) {
				c.specAmount -= 2.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 1377:
		case 11696:
		case 11730:
		case 15486:
			if (c.specAmount >= 10) {
				c.specAmount -= 10;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 4587:
		case 859:
		case 861:
		case 11235:
		case 11700:
		case 20171:
			if (c.specAmount >= 5.5) {
				c.specAmount -= 5.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		default:
			return true; // incase u want to test a weapon
		}
	}

	public static void activateSpecial(final Player c, int weapon, int i) {
		if (NPCHandler.npcs[i] == null && c.npcIndex > 0) {
			return;
		}
		if (PlayerHandler.players[i] == null && c.playerIndex > 0) {
			return;
		}
		c.doubleHit = false;
		c.specEffect = 0;
		c.projectileStage = 0;
		c.specMaxHitIncrease = 2;
		c.delayedDamage = Misc.random(c.getCombat().calculateMeleeMaxHit());
		c.delayedDamage2 = Misc.random(c.getCombat().calculateMeleeMaxHit());
		if (c.npcIndex > 0) {
			c.oldNpcIndex = i;
		} else if (c.playerIndex > 0) {
			c.oldPlayerIndex = i;
			PlayerHandler.players[i].underAttackBy = c.playerId;
			PlayerHandler.players[i].logoutDelay = System.currentTimeMillis();
			PlayerHandler.players[i].singleCombatDelay = System
					.currentTimeMillis();
			PlayerHandler.players[i].killerId = c.playerId;
		}
		if (c.playerIndex > 0) {
			c.getPA().followPlayer();
		} else if (c.npcIndex > 0) {
			c.getPA().followNpc();
		}
		switch (weapon) {
		case 19780: // korasi
			//c.setAnimation(Animation.create(14788));
			c.startAnimation(14788);
			c.gfx100(1729);
			c.specAccuracy = 9.00;
			c.specDamage = 2.25;
			// c.gfx100(1224);
			EventManager.getSingleton().addEvent(new Event() {
				int Time = 2;

				@Override
				public void execute(EventContainer KorasiSpec) {
					Player o = PlayerHandler.players[c.playerIndex];
					NPC n = NPCHandler.npcs[c.npcIndex];
					if (Time == 1) {
						if (c.npcIndex > 0) {
							n.gfx100(1247);
							c.getPA().npcMageDamage(
									(Misc.random(c.getCombat()
											.calculateMeleeMaxHit())));
							n.underAttack = true;
						} else if (c.playerIndex > 0) {
							o.gfx100(1247);
							c.getPA().applyPlayerMageDamage(
									(Misc.random(c.getCombat()
											.calculateMeleeMaxHit())));
						}
					}
					if (Time < 1) {
						KorasiSpec.stop();
						return;
					}
					if (Time > 0) {
						Time--;
					}
				}
			}, 290);
			break;
		case 10887: // barrelchest anchor
			c.gfx0(1027);
			//c.setAnimation(Animation.create(5870));
			c.startAnimation(5870);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.specDamage = 1.20;
			c.specAccuracy = 1.85;
			break;

		case 1305: // dragon long
			c.gfx100(2117);
			//c.setAnimation(Animation.create(12033));
			c.startAnimation(12033);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.specAccuracy = 1.10;
			c.specDamage = 1.20;
			break;

		case 1215: // dragon daggers
		case 1231:
		case 5680:
		case 5698:
			c.gfx100(252);
			//c.setAnimation(Animation.create(1062));
			c.startAnimation(1062);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.doubleHit = true;
			c.specAccuracy = 1.30;
			c.specDamage = 1.05;
			break;

		case 13899:
			//c.setAnimation(Animation.create(10502));
			c.startAnimation(10502);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.specAccuracy = 1.35;
			c.specDamage = 1.55;
			break;

		case 11730:
			if (NPCHandler.npcs[i] != null) {
				NPCHandler.npcs[i].gfx100(1194);
			}
			if (c.playerIndex > 0) {
				final Player o = PlayerHandler.players[c.playerIndex];
				o.gfx100(1194);
			}
			//c.setAnimation(Animation.create(11993));
			c.startAnimation(11993);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.doubleHit = true;
			c.ssSpec = true;
			c.specAccuracy = 1.30;
			break;

			/*case 14484: // Scott's Dragon claws
			c.startAnimation(10961);
			c.gfx0(1950);
			c.usingClaws = true;
			c.doubleHit = true;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
							.toLowerCase());
			break;*/

		case 14484: // Dragon claws
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer e) {	

					if (c.clawDelay > 0) {
						c.clawDelay--;
					}

					if (c.clawDelay == 1) {
						c.delayedDamage = c.clawDamage / 4;
						c.delayedDamage2 = (c.clawDamage / 4) + 1;
						if (c.clawType == 2) {
							c.getCombat().applyNpcMeleeDamage(c.clawIndex, 1,
									c.clawDamage / 4);
						}
						if (c.clawType == 1) {
							c.getCombat().applyPlayerMeleeDamage(c.clawIndex,
									1, c.clawDamage / 4);
						}
						if (c.clawType == 2) {
							c.getCombat().applyNpcMeleeDamage(c.clawIndex, 2,
									(c.clawDamage / 4) + 1);
						}
						if (c.clawType == 1) {
							c.getCombat().applyPlayerMeleeDamage(c.clawIndex,
									2, (c.clawDamage / 4) + 1);
						}
						c.clawDelay = 0;
						c.specEffect = 0;
						c.previousDamage = 0;
						c.usingClaws = false;
						c.clawType = 0;
						e.stop();
					}

				}
			},500);

			c.gfx0(1950);
			//c.setAnimation(Animation.create(10961));
			c.startAnimation(10961);
			c.specDamage = 1.45;
			c.specAccuracy = 5.55;
			c.clawDamage = 0;

			if (c.playerIndex > 0) {
				Player o = (Player) PlayerHandler.players[c.playerIndex];
				if (Misc.random(c.getCombat().calculateMeleeAttack() * 2) > Misc.random(o
						.getCombat().calculateMeleeDefence())) {
					c.clawDamage = Misc.random(c.getCombat().calculateMeleeMaxHit())
							+ (c.getCombat().calculateMeleeMaxHit() / 3);
				}
				c.clawIndex = c.playerIndex;
				c.clawType = 1;
			} else if (c.npcIndex > 0) {
				NPC n = NPCHandler.npcs[c.npcIndex];

				if (Misc.random(c.getCombat().calculateMeleeAttack()) > Misc
						.random(n.defence)) {
					c.clawDamage = Misc.random(c.getCombat().calculateMeleeMaxHit()
							+ Misc.random(2));
				}
				c.clawIndex = c.npcIndex;
				c.clawType = 2;
			}

			c.doubleHit = true;
			c.usingClaws = true;
			c.specEffect = 6;
			c.hitDelay = c.getCombat().getHitDelay(i, c.getItems()
					.getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			break;
		case 15241: // hand cannon spec!!
			c.usingBow = true;
			//c.hcSpec = true;
			c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			c.getItems().deleteArrow();	
			c.lastWeaponUsed = weapon;
			//c.setAnimation(Animation.create(12175));
			c.startAnimation(12175);
			c.hitDelay = 5;
			c.attackTimer-= 7;
			c.hitDelay = c.getCombat().getHitDelay(i, c.getItems()
					.getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			if (c.fightMode == 2)
			if (c.playerIndex > 0)
			c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			
			c.specAccuracy = 20;
			c.specDamage = 2.25;
			break;

		case 4151: // whip
			if (NPCHandler.npcs[i] != null) {
				NPCHandler.npcs[i].gfx100(2108);
			}
			if (c.playerIndex > 0) {
				final Player o = PlayerHandler.players[c.playerIndex];
				o.gfx100(2108);
			}
			c.specAccuracy = 1.10;
			//c.setAnimation(Animation.create(11971));
			c.startAnimation(11971);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			break;

		case 11694: // ags
			//c.setAnimation(Animation.create(11989));
			c.startAnimation(11989);
			c.specDamage = 1.55;
			c.specAccuracy = 5.85;
			c.gfx0(2113);
			c.hitDelay = c.getCombat().getHitDelay(i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			break;

		case 11700:
			//c.setAnimation(Animation.create(7070));
			c.startAnimation(7070);
			c.gfx0(1221);
			if (NPCHandler.npcs[i] != null) {
				NPCHandler.npcs[i].gfx0(2104);
			}
			if (c.playerIndex > 0) {
				final Player o = PlayerHandler.players[c.playerIndex];
				o.gfx0(2104);
			}
			c.specAccuracy = 1.25;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.specEffect = 2;
			break;

		case 11696:
			//c.setAnimation(Animation.create(11991));
			c.startAnimation(11991);
			c.gfx0(2114);
			c.specDamage = 1.10;
			c.specAccuracy = 1.5;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.specEffect = 3;
			break;

		case 13902: // statius hammer
			//c.setAnimation(Animation.create(2062));
			c.startAnimation(2062);
			c.gfx100(1223);
			c.specAccuracy = 1.1;
			c.specDamage = 1.25;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.specEffect = 5;
			break;

		case 11698: //sgs
			//c.setAnimation(Animation.create(7071));
			c.startAnimation(12019);
			c.gfx0(2109);
			c.specAccuracy = 1.25;
			c.specEffect = 4;
			c.sgsEffect = 1;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			break;

		case 1249: //d spear
			if (NPCHandler.npcs[i] != null) {
				NPCHandler.npcs[i].gfx100(80);
			}
			if (c.playerIndex > 0) {
				final Player o = PlayerHandler.players[c.playerIndex];
				o.gfx100(80);
			}
			//c.setAnimation(Animation.create(12017));
			c.startAnimation(12017);
			if (c.playerIndex > 0) {
				Player o = PlayerHandler.players[i];
				o.getPA().getSpeared(c.absX, c.absY);
			}
			break;

		case 3204: // d hally
			//c.gfx100(282);
			//c.setAnimation(Animation.create(1665));
			c.startAnimation(1665);
			if (NPCHandler.npcs[i] != null) {
				NPCHandler.npcs[i].gfx100(254);
			}
			if (c.playerIndex > 0) {
				final Player o = PlayerHandler.players[c.playerIndex];
				o.gfx100(254);
			}
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			if (NPCHandler.npcs[i] != null && c.npcIndex > 0) {
				if (!c.goodDistance(c.getX(), c.getY(),
						NPCHandler.npcs[i].getX(), NPCHandler.npcs[i].getY(), 1)) {
					c.doubleHit = true;
				}
			}
			if (PlayerHandler.players[i] != null && c.playerIndex > 0) {
				if (!c.goodDistance(c.getX(), c.getY(),
						PlayerHandler.players[i].getX(),
						PlayerHandler.players[i].getY(), 1)) {
					c.doubleHit = true;
					c.delayedDamage2 = Misc.random(c.getCombat()
							.calculateMeleeMaxHit());
				}
			}
			break;

		case 4153: // maul
			//c.setAnimation(Animation.create(1667));
			c.startAnimation(1667);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			/*
			 * if (c.playerIndex > 0) gmaulPlayer(i); else gmaulNpc(i);
			 */
			c.gfx100(337);
			break;

		case 4587: // dscimmy
			c.gfx100(2118);
			c.specEffect = 1;
			//c.setAnimation(Animation.create(12031));
			c.startAnimation(12031);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			break;

		case 1434: // mace
			//c.setAnimation(Animation.create(1060));
			c.startAnimation(1060);
			c.gfx100(251);
			c.specMaxHitIncrease = 3;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase()) + 1;
			c.specDamage = 1.35;
			c.specAccuracy = 1.15;
			break;

		case 859: // magic long
			c.usingBow = true;
			c.bowSpecShot = 3;
			c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			//c.setAnimation(Animation.create(426));
			c.startAnimation(426);
			c.gfx100(250);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			c.projectileStage = 1;
			if (c.fightMode == 2)
				c.attackTimer--;
			break;

		case 861: // magic short
			c.usingBow = true;
			c.bowSpecShot = 1;
			c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			//c.setAnimation(Animation.create(1074));
			c.startAnimation(1074);
			c.hitDelay = 3;
			c.projectileStage = 1;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			break;
		case 20171: // zaryte
			c.usingBow = true;
			//c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			//c.getItems().deleteArrow();
			//c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			//c.setAnimation(Animation.create(1074));
			c.startAnimation(426);
			c.gfx100(c.getCombat().getRangeStartGFX());
		
			c.hitDelay = 3;
			c.projectileStage = 1;
			c.specAccuracy = 2.00;
			c.specDamage = 2.15;
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			break;
		case 11235: // dark bow
			c.usingBow = true;
			c.dbowSpec = true;
			c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			if (c.playerIndex > 0) {
				c.getItems().dropArrowPlayer();
			} else if (c.npcIndex > 0) {
				c.getItems().dropArrowNpc();
			}
			c.lastWeaponUsed = weapon;
			c.hitDelay = 3;
			//c.setAnimation(Animation.create(426));
			c.startAnimation(426);
			c.projectileStage = 1;
			c.gfx100(c.getCombat().getRangeStartGFX());
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
					.toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			c.specAccuracy = 1.85;
			c.specDamage = 1.65;
			break;
		}
		if (!c.usingSpecial) {
			c.doubleHit = false;
			c.usingSpecial = false;
			c.getItems().updateSpecialBar();
			return;
		}
		if (!c.usingSpecial) {
			c.delayedDamage = Misc.random(c.getCombat().calculateMeleeMaxHit());
			c.delayedDamage2 = Misc
					.random(c.getCombat().calculateMeleeMaxHit());
			c.doubleHit = false;
		}
		c.usingSpecial = false;
		c.getItems().updateSpecialBar();
	}
}