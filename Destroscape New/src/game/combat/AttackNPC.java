package game.combat;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.event.EventManager;
import engine.event.Event;
import engine.event.EventContainer;
import engine.util.Misc;
import game.Config;
import game.Server;
import game.combat.range.RangeData;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.item.ItemDegradation;
import game.minigame.pestcontrol.PestControl;
import game.skill.slayer.Slayer;

public class AttackNPC {
		public static int strBonus;

	public static void addNPCHit(final int i, final Player c) {
		if (c.projectileStage == 0 && !c.usingMagic) {
			c.stopPlayerSkill = false;
			if (!c.usingClaws) {
				c.getCombat().applyNpcMeleeDamage(i, 1,
						Misc.random(c.getCombat().calculateMeleeMaxHit()));
				if (c.doubleHit) {
					if (!c.oldSpec) {
						c.getCombat().applyNpcMeleeDamage(
								i,
								2,
								Misc.random(c.getCombat()
										.calculateMeleeMaxHit()));
					} else {
						CycleEventHandler.getSingleton().addEvent(c,
								new CycleEvent() {
							@Override
							public void execute(
									CycleEventContainer container) {
								c.getCombat()
								.applyNpcMeleeDamage(
										i,
										2,
										Misc.random(c
												.getCombat()
												.calculateMeleeMaxHit()));
								container.stop();
							}

							@Override
							public void stop() {

							}
						}, 1);
					}
				}
			} else {
				c.getPA().hitDragonClaws(c,
						Misc.random(c.getCombat().calculateMeleeMaxHit()));
			}
		}
	}

	public static boolean kalphite1(int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 1158:
			return true;
		}
		return false;
	}

	public static boolean kalphite2(int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 1160:
			return true;
		}
		return false;
	}

	public static void applyNpcMeleeDamage(Player c, int i, int damageMask,
			int damage) {
		if (!c.usingClaws) {
			damage = Misc.random(c.getCombat().calculateMeleeMaxHit());
		}
		c.previousDamage = damage;
		boolean fullVeracsEffect = c.getPA().fullVeracs()
				&& Misc.random(3) == 1;
		if (NPCHandler.npcs[i].HP - damage < 0) {
			damage = NPCHandler.npcs[i].HP;
		}
		if (NPCHandler.npcs[i].npcType == 5666) {
			damage = damage / 4;
			if (damage < 0) {
				damage = 0;
			}
		}

		if (!fullVeracsEffect) {
			if (Misc.random(NPCHandler.npcs[i].defence) > 10 + Misc.random(c
					.getCombat().calculateMeleeAttack())) {
				damage = 0;
			} else if (NPCHandler.npcs[i].npcType == 2882
					|| NPCHandler.npcs[i].npcType == 2883) {
				damage = 0;
			}
		}

		boolean guthansEffect = false;
		if (c.getPA().fullGuthans()) {
			if (Misc.random(3) == 1) {
				guthansEffect = true;
			}
		}

		if (c.playerEquipment[c.playerWeapon] == 4718
				&& c.playerEquipment[c.playerHat] == 4716
				&& c.playerEquipment[c.playerChest] == 4720
				&& c.playerEquipment[c.playerLegs] == 4722) {
			damage = Misc.random(c.getCombat().calculateMeleeMaxHit());
		}
		if (c.slayerHelmAffect == 1) {
			damage *= 1.15;
		} else if (c.slayerHelmAffect == 2) {
			damage *= 1.2;
		}

		if (NPCHandler.npcs[i].npcType == 8349 && Player.protMelee) {
			damage = 0;
		}
		/*if (NPCHandler.npcs[i].npcType == 8349 && c.demonCount == 3) {

				NPC.appendDemon(c);
				c.demonCount = 0;
			} else if (c.demonCount < 3) {
				c.demonCount++;
		}*/

		if (NPCHandler.npcs[i].npcType == 13447) {
			if (NPCHandler.npcs[i].nexStage == 2
					|| NPCHandler.npcs[i].nexStage == 4
					|| NPCHandler.npcs[i].nexStage == 6
					|| NPCHandler.npcs[i].nexStage == 8) {
				damage = 0;
			}
		}

		if (NPCHandler.npcs[i].HP - damage < 0) {
			damage = NPCHandler.npcs[i].HP;
		}
		if (c.fightMode == 3) {
			if (c.isPVPActive == true && !c.inSafeZone()) {
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3 * 2), 0);
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3 * 2), 1);
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3 * 2), 2);
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3 * 2), 3);
			} else {
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 0);
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 1);
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 2);
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 3);
			}
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
		} else {
			if (c.isPVPActive == true && !c.inSafeZone()) {
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE * 2), c.fightMode);
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3 * 2), 3);
			} else {
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE), c.fightMode);
				c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 3);
			}
			c.getPA().refreshSkill(c.fightMode);
			c.getPA().refreshSkill(3);
		}
		ItemDegradation.chaoticDegrade(c, c.getCombat().getAttackDelay(c.getItems().getItemName(c.formerWeapon).toLowerCase()));
		if (damage > 0 && PestControl.isInGame(c)) {
			if (NPCHandler.npcs[i].npcType == 6142) {
				c.pcDamage += damage;
				PestControl.portalHealth[0] -= damage;
			}
			if (NPCHandler.npcs[i].npcType == 6143) {
				c.pcDamage += damage;
				PestControl.portalHealth[1] -= damage;
			}
			if (NPCHandler.npcs[i].npcType == 6144) {
				c.pcDamage += damage;
				PestControl.portalHealth[2] -= damage;
			}
			if (NPCHandler.npcs[i].npcType == 6145) {
				c.pcDamage += damage;
				PestControl.portalHealth[3] -= damage;
			}
		}
		if (damage > 0) {
		if (c.soulSplitDelay <= 0) {
			c.getCombat().applynpcSoulSplit(i, damage);
			}
		}
		if (damage > 0 && guthansEffect) {
			c.playerLevel[3] += damage;
			if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
				c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			c.getPA().refreshSkill(3);
			NPCHandler.npcs[i].gfx0(398);
		}

		NPCHandler.npcs[i].underAttack = true;
		c.killingNpcIndex = c.npcIndex;
		c.lastNpcAttacked = i;

		switch (c.specEffect) {
		case 4:
			if (damage > 0) {
			if (c.sgsEffect == 1) {
				//if (c.playerLevel[5] + damage > c.getLevelForXP(c.playerXP[5]))
				//	if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]));
				if (c.playerLevel[3] + damage > c.getLevelForXP(c.playerXP[3]))
					if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]));
					else
						c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
						//c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
				else
				//c.playerLevel[5] += damage;
				//	c.getPA().refreshSkill(5);
				c.playerLevel[3] += damage;
					c.getPA().refreshSkill(3);
				c.sgsEffect = 0;
				}
			}
			break;
		}

		switch (damageMask) {
		case 1:
			NPCHandler.npcs[i].hitDiff = damage;
			NPCHandler.npcs[i].HP -= damage;
			c.totalDamageDealt += damage;
			NPCHandler.npcs[i].hitMask = (damage > c.getCombat()
					.calculateMeleeMaxHit() * .9 ? 1 : 0);// search me
			NPCHandler.npcs[i].hitIcon = 0;
			NPCHandler.npcs[i].hitUpdateRequired = true;
			NPCHandler.npcs[i].updateRequired = true;
			break;

		case 2:
			NPCHandler.npcs[i].hitDiff2 = damage;
			NPCHandler.npcs[i].HP -= damage;
			c.totalDamageDealt += damage;
			NPCHandler.npcs[i].hitUpdateRequired2 = true;
			NPCHandler.npcs[i].updateRequired = true;
			c.doubleHit = false;
			break;

		}
	}

	public static void delayedHit(final Player c, final int i) {
		if (NPCHandler.npcs[i] != null) {
			if (NPCHandler.npcs[i].isDead) {
				c.npcIndex = 0;
				return;
			}

			NPCHandler.npcs[i].facePlayer(c.playerId);

			if (NPCHandler.npcs[i].underAttackBy > 0
					&& Server.npcHandler.getsPulled(i)) {
				NPCHandler.npcs[i].killerId = c.playerId;
			} else if (NPCHandler.npcs[i].underAttackBy < 0
					&& !Server.npcHandler.getsPulled(i)) {
				NPCHandler.npcs[i].killerId = c.playerId;
			}

			c.lastNpcAttacked = i;

			c.getCombat().addNPCHit(i, c);
			c.getCombat().addCharge();

			if (!c.castingMagic && c.projectileStage > 0) { // range hit damage
				int damage = Misc.random(c.getCombat().rangeMaxHit());
				int damage2 = -1;
				if (c.lastWeaponUsed == 11235 || c.bowSpecShot == 1)
					damage2 = Misc.random(c.getCombat().rangeMaxHit());
			 if (c.lastWeaponUsed == 3093) {
				 c.ignoreDefence = true;
				damage = NPCHandler.npcs[i].HP;
				c.getCombat().resetPlayerAttack();
		}
				if (c.playerEquipment[3] == 9185
						|| c.playerEquipment[3] == 18357) {
					if (Misc.random(10) == 1) {
						if (damage > 0) {
							c.boltDamage = damage;
							c.getCombat().crossbowSpecial(c, i);
							damage *= c.crossbowDamage;
						}
					}
				}
				if (Misc.random(NPCHandler.npcs[i].defence) > Misc
						.random(10 + c.getCombat().calculateRangeAttack())
						&& !c.ignoreDefence) {
					damage = 0;
				} else if (NPCHandler.npcs[i].npcType == 2881
						|| NPCHandler.npcs[i].npcType == 2883
						&& !c.ignoreDefence) {
					damage = 0;
				} else if (c.lastWeaponUsed == 3093) {
						damage = NPCHandler.npcs[i].HP;
				}
				if (c.lastWeaponUsed == 11235 || c.bowSpecShot == 1) {
					if (Misc.random(NPCHandler.npcs[i].defence) > Misc
							.random(10 + c.getCombat().calculateRangeAttack()))
						damage2 = 0;
				}
				if (c.dbowSpec) {
					NPCHandler.npcs[i].gfx100(c.lastArrowUsed == 11212 ? 1100
							: 1103);
					if (damage < 8)
						damage = 8;
					if (damage2 < 8)
						damage2 = 8;
					c.dbowSpec = false;
				}
				if (c.slayerHelmAffect == 1) {
					damage *= 1.15;
				} else if (c.slayerHelmAffect == 2) {
					damage *= 1.2;
				}

				if (NPCHandler.npcs[i].npcType == 8349 && Player.protRange) {
					damage = 0;
				}
				/*if (NPCHandler.npcs[i].npcType == 8349 && c.demonCount == 3) {

					NPC.appendDemon(c);
					c.demonCount = 0;
				} else if (c.demonCount < 3) {
					c.demonCount++;
			}*/

				if (NPCHandler.npcs[i].HP - damage < 0) {
					damage = NPCHandler.npcs[i].HP;
				}
				if (damage2 > 0) {
					if (damage == NPCHandler.npcs[i].HP
							&& NPCHandler.npcs[i].HP - damage2 > 0) {
						damage2 = 0;
					}
				}
				if (c.fightMode == 3) {
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE / 3),
							4);
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE / 3),
							1);
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE / 3),
							3);
					c.getPA().refreshSkill(1);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(4);
				} else {
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE), 4);
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE / 3),
							3);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(4);
				}
				if (damage > 0 && PestControl.isInGame(c)) {
					if (NPCHandler.npcs[i].npcType == 6142) {
						c.pcDamage += damage;
						PestControl.portalHealth[0] -= damage;
					}
					if (NPCHandler.npcs[i].npcType == 6143) {
						c.pcDamage += damage;
						PestControl.portalHealth[1] -= damage;
					}
					if (NPCHandler.npcs[i].npcType == 6144) {
						c.pcDamage += damage;
						PestControl.portalHealth[2] -= damage;
					}
					if (NPCHandler.npcs[i].npcType == 6145) {
						c.pcDamage += damage;
						PestControl.portalHealth[3] -= damage;
					}
				}
	
		if (damage > 0) {
		if (c.soulSplitDelay <= 0) {
			c.getCombat().applynpcSoulSplit(i, damage);
			}
		}
				boolean dropArrows = true;

				for (int noArrowId : c.NO_ARROW_DROP) {
					if (c.lastWeaponUsed == noArrowId) {
						dropArrows = false;
						break;
					}
				}
				if (dropArrows) {
					c.getItems().dropArrowNpc();
					if (c.playerEquipment[3] == 11235) {
						c.getItems().dropArrowNpc();
					}
				}
				NPCHandler.npcs[i].hitMask = (damage > (c.getCombat()
						.rangeMaxHit() * .9) ? 1 : 0);
				NPCHandler.npcs[i].hitIcon = 1;
				if (Server.npcHandler.getNPCs()[i].attackTimer < 9)
					// NPCHandler.startAnimation(c.getCombat().npcDefenceAnim(i),
					// i);
					c.rangeEndGFX = RangeData.getRangeEndGFX(c);

				if ((c.playerEquipment[3] == 10034 || c.playerEquipment[3] == 10033)) {
					for (int j = 0; j < NPCHandler.npcs.length; j++) {
						if (NPCHandler.npcs[j] != null
								&& NPCHandler.npcs[j].MaxHP > 0) {
							int nX = NPCHandler.npcs[j].getX();
							int nY = NPCHandler.npcs[j].getY();
							int pX = NPCHandler.npcs[i].getX();
							int pY = NPCHandler.npcs[i].getY();
							if ((nX - pX == -1 || nX - pX == 0 || nX - pX == 1)
									&& (nY - pY == -1 || nY - pY == 0 || nY
									- pY == 1)) {
								if (NPCHandler.npcs[i].inMulti()) {
									Player p = PlayerHandler.players[c.playerId];
									c.getCombat().appendMutliChinchompa(j);
									Server.npcHandler.attackPlayer(p, j);
								}
							}
						}
					}
				}
				if (!c.multiAttacking) {
					NPCHandler.npcs[i].underAttack = true;
					NPCHandler.npcs[i].hitDiff = damage;
					NPCHandler.npcs[i].HP -= damage;
					if (damage2 > -1) {
						NPCHandler.npcs[i].hitDiff2 = damage2;
						NPCHandler.npcs[i].HP -= damage2;
						c.totalDamageDealt += damage2;
						NPCHandler.npcs[i].hitMask2 = (damage2 > (c.getCombat()
								.rangeMaxHit() * .9) ? 1 : 0);
						NPCHandler.npcs[i].hitIcon2 = 1;
					}
				}
				c.ignoreDefence = false;
				c.multiAttacking = false;

				if (c.rangeEndGFX > 0) {
					if (c.rangeEndGFXHeight) {
						NPCHandler.npcs[i].gfx100(c.rangeEndGFX);
					} else {
						NPCHandler.npcs[i].gfx0(c.rangeEndGFX);
					}
				}

				if (c.killingNpcIndex != c.oldNpcIndex) {
					c.totalDamageDealt = 0;
				}
				c.killingNpcIndex = c.oldNpcIndex;
				c.totalDamageDealt += damage;
				NPCHandler.npcs[i].hitUpdateRequired = true;
				if (damage2 > -1)
					NPCHandler.npcs[i].hitUpdateRequired2 = true;
				NPCHandler.npcs[i].updateRequired = true;

			} else if (c.projectileStage > 0) { // magic hit damage
				if (NPCHandler.npcs[i].HP <= 0) {
					return;
				}
				if (c.spellSwap) {
					c.spellSwap = false;
					c.setSidebarInterface(6, 16640);
					c.playerMagicBook = 2;
					c.gfx0(-1);
				}
				int damage = 0;
				c.usingMagic = true;
				if (c.fullVoidMage()
						&& c.playerEquipment[c.playerWeapon] == 8841) {
					damage = Misc.random(c.getCombat().magicMaxHit() + 10);
				} else {
					damage = Misc.random(c.getCombat().magicMaxHit());
				}
				if (c.getCombat().godSpells()) {
					if (System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
						damage += Misc.random(10);
					}
				}
				boolean magicFailed = false;
				if (Misc.random(NPCHandler.npcs[i].defence) > 10 + Misc
						.random(c.getCombat().mageAtk())) {
					damage = 0;
					magicFailed = true;
				} else if (NPCHandler.npcs[i].npcType == 2881
						|| NPCHandler.npcs[i].npcType == 2882) {
					damage = 0;
					magicFailed = true;
				}
				for (int j = 0; j < NPCHandler.npcs.length; j++) {
					if (NPCHandler.npcs[j] != null
							&& NPCHandler.npcs[j].MaxHP > 0) {
						int nX = NPCHandler.npcs[j].getX();
						int nY = NPCHandler.npcs[j].getY();
						int pX = NPCHandler.npcs[i].getX();
						int pY = NPCHandler.npcs[i].getY();
						if ((nX - pX == -1 || nX - pX == 0 || nX - pX == 1)
								&& (nY - pY == -1 || nY - pY == 0 || nY - pY == 1)) {
							if (c.getCombat().multis()
									&& NPCHandler.npcs[i].inMulti()) {
								Player p = PlayerHandler.players[c.playerId];
								c.getCombat().appendMultiBarrageNPC(j,
										c.magicFailed);
								Server.npcHandler.attackPlayer(p, j);
							}
						}
					}
				}
				if (c.slayerHelmAffect == 1) {
					damage *= 1.15;
				} else if (c.slayerHelmAffect == 2) {
					damage *= 1.2;
				}
				if (NPCHandler.npcs[i].npcType == 8349 && Player.protMage) {
					damage = 0;
				}
				/*if (NPCHandler.npcs[i].npcType == 8349 && c.demonCount == 3) {

					NPC.appendDemon(c);
					c.demonCount = 0;
				} else if (c.demonCount < 3) {
					c.demonCount++;
			}*/
				if (NPCHandler.npcs[i].HP - damage < 0) {
					damage = NPCHandler.npcs[i].HP;
				}
				if (c.magicDef) {
					c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 2),
							1);
					c.getPA().refreshSkill(1);
				}
				c.getPA().addSkillXP(
						(c.MAGIC_SPELLS[c.oldSpellId][7] + damage
								* Config.MAGIC_EXP_RATE), 6);
				c.getPA().addSkillXP(
						(c.MAGIC_SPELLS[c.oldSpellId][7] + damage
								* Config.MAGIC_EXP_RATE / 3), 3);
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(6);
				if (damage > 0 && PestControl.isInGame(c)) {
					if (NPCHandler.npcs[i].npcType == 6142) {
						c.pcDamage += damage;
						PestControl.portalHealth[0] -= damage;
					}
					if (NPCHandler.npcs[i].npcType == 6143) {
						c.pcDamage += damage;
						PestControl.portalHealth[1] -= damage;
					}
					if (NPCHandler.npcs[i].npcType == 6144) {
						c.pcDamage += damage;
						PestControl.portalHealth[2] -= damage;
					}
					if (NPCHandler.npcs[i].npcType == 6145) {
						c.pcDamage += damage;
						PestControl.portalHealth[3] -= damage;
					}
				}

		if (damage > 0) {
		if (c.soulSplitDelay <= 0) {
			c.getCombat().applynpcSoulSplit(i, damage);
			}
		}
				if (c.getCombat().getEndGfxHeight() == 100 && !magicFailed) { // end
					// GFX
					NPCHandler.npcs[i].gfx100(c.MAGIC_SPELLS[c.oldSpellId][5]);
				} else if (!magicFailed) {
					NPCHandler.npcs[i].gfx0(c.MAGIC_SPELLS[c.oldSpellId][5]);
				}

				if (magicFailed) {
					NPCHandler.npcs[i].gfx100(85);
				}
				if (!magicFailed) {
					int freezeDelay = c.getCombat().getFreezeTime();// freeze
					if (freezeDelay > 0 && NPCHandler.npcs[i].freezeTimer == 0) {
						NPCHandler.npcs[i].freezeTimer = freezeDelay;
					}
					switch (c.MAGIC_SPELLS[c.oldSpellId][0]) {
					case 12901:
					case 12919: // blood spells
					case 12911:
					case 12929:
						int heal = Misc.random(damage / 2);
						if (c.playerLevel[3] + heal >= c.getPA().getLevelForXP(
								c.playerXP[3])) {
							c.playerLevel[3] = c.getPA().getLevelForXP(
									c.playerXP[3]);
						} else {
							c.playerLevel[3] += heal;
						}
						c.getPA().refreshSkill(3);
						break;
					}

				}

				NPCHandler.npcs[i].underAttack = true;
				if (c.getCombat().magicMaxHit() != 0) {
					if (!c.multiAttacking) {
						NPCHandler.npcs[i].hitDiff = damage;
						NPCHandler.npcs[i].HP -= damage;
						NPCHandler.npcs[i].hitMask = (damage > (c.MAGIC_SPELLS[c.oldSpellId][6] * .9) ? 1
								: 0);
						NPCHandler.npcs[i].hitIcon = 2;
						NPCHandler.npcs[i].hitUpdateRequired = true;
						c.totalDamageDealt += damage;
					}
				}
				c.multiAttacking = false;
				c.killingNpcIndex = c.oldNpcIndex;
				NPCHandler.npcs[i].updateRequired = true;
				c.usingMagic = false;
				c.castingMagic = false;
				c.oldSpellId = 0;
			}
		}
		if (c.bowSpecShot <= 0) {
			c.oldNpcIndex = 0;
			c.projectileStage = 0;
			c.doubleHit = false;
			c.lastWeaponUsed = 0;
			c.bowSpecShot = 0;
		}
		if (c.bowSpecShot >= 2) {
			c.bowSpecShot = 0;
		}
		if (c.bowSpecShot == 1) {
			c.getCombat().fireProjectileNpc();
			c.hitDelay = 2;
			c.bowSpecShot = 0;
		}
	}

	public static void attackNpc(Player c, int i) {
		if (NPCHandler.npcs[i] != null) {
			strBonus = c.playerBonus[10];
			if (NPCHandler.npcs[i].npcType == 8133) {
				if (!c.goodDistance(NPCHandler.npcs[i].absX,NPCHandler.npcs[i].absY,c.absX,c.absY, 4))
					return;
			}
		}
		if (c.playerEquipment[c.playerWeapon] == 22494 && c.playerLevel[6] > 80
				&& c.ppsLeft >= 1 && c.spellId == 0 && !c.autocasting) {
			c.autocasting = true;
			c.spellId = 52;
			c.autocastId = 52;
			c.usingMagic = true;
			c.ppsRefresh = true;
		}
		if (c.playerEquipment[c.playerWeapon] == 21777 && c.playerLevel[6] > 80
				&& c.spellId == 0 && !c.autocasting) {
			c.autocasting = true;
			c.spellId = 53;
			c.autocastId = 53;
			c.usingMagic = true;
		}
		boolean cant = false;
		int[] godwars = { 6265, 6248, 6250, 6252, 6263, 6261, 6260, 6222, 6227,
				6225, 6223, 6203, 6206, 6204, 6208, 6247 };
		if (NPCHandler.npcs[i] != null) {
			if (NPCHandler.npcs[i].isDead || NPCHandler.npcs[i].MaxHP <= 0) {
				c.usingMagic = false;
				c.faceUpdate(0);
				c.npcIndex = 0;
				return;
			}
			if (!c.inGodWarsBoss()) {
				for (int a = 0; a < godwars.length; a++) {
					if (NPCHandler.npcs[i].npcType == godwars[a]) {
						cant = true;
					}
				}
				if (cant == true)
					return;
			}
			if (NPCHandler.npcs[i].npcType == 13447) {
				if (NPCHandler.npcs[i].nexStage == 0) {
					c.turnPlayerTo(NPCHandler.npcs[i].absX,
							NPCHandler.npcs[i].absY);
					return;
				}
			}
			if (Server.npcHandler.checkSlayerHelm(c, i) == 1) {
				c.slayerHelmAffect = 1;
			} else if (Server.npcHandler.checkSlayerHelm(c, i) == 2) {
				c.slayerHelmAffect = 2;
			} else {
				c.slayerHelmAffect = 0;
			}
			if (NPCHandler.npcs[i].npcType == 1610 || NPCHandler.npcs[i].npcType == 1611) {
				if (c.smashedGargoyle == false) {
					c.sendMessage("You need to smash the Gargoyle with a hammer before attacking it.");
					c.faceUpdate(0);
					c.npcIndex = 0;
					return;
				}
			}
			if (NPCHandler.npcs[i].npcType == 13454) {
				if (!Server.npcHandler.nexRoom[3]) {
					NPCHandler.npcs[i].gfx0(247);
					c.sendMessage("The avatar is not weak enough to damage this minion.");
					c.faceUpdate(0);
					c.npcIndex = 0;
					return;
				}
			}
			if (NPCHandler.npcs[i].npcType == 13451) {
				if (!Server.npcHandler.nexRoom[0]) {
					NPCHandler.npcs[i].gfx0(247);
					c.sendMessage("The avatar is not weak enough to damage this minion.");
					c.faceUpdate(0);
					c.npcIndex = 0;
					return;
				}
			}
			if (NPCHandler.npcs[i].npcType == 13452) {
				if (!Server.npcHandler.nexRoom[1]) {
					NPCHandler.npcs[i].gfx0(247);
					c.sendMessage("The avatar is not weak enough to damage this minion.");
					c.faceUpdate(0);
					c.npcIndex = 0;
					return;
				}
			}
			if (NPCHandler.npcs[i].npcType == 13453) {
				if (!Server.npcHandler.nexRoom[2]) {
					NPCHandler.npcs[i].gfx0(247);
					c.sendMessage("The avatar is not weak enough to damage this minion.");
					c.faceUpdate(0);
					c.npcIndex = 0;
					return;
				}
			}
			if (c.respawnTimer > 0) {
				c.npcIndex = 0;
				return;
			}
			if (NPCHandler.npcs[i].underAttackBy > 0
					&& NPCHandler.npcs[i].underAttackBy != c.playerId
					&& !NPCHandler.npcs[i].inMulti()) {
				c.npcIndex = 0;
				c.sendMessage("This monster is already in combat.");
				return;
			}
			if ((c.underAttackBy > 0 || c.underAttackBy2 > 0)
					&& c.underAttackBy2 != i && !c.inMulti()) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("I am already under attack.");
				return;
			}

			if (NPCHandler.npcs[c.npcIndex].npcType == 9462 || 
			    NPCHandler.npcs[c.npcIndex].npcType == 9463 || 
			    NPCHandler.npcs[c.npcIndex].npcType == 9464 || 
			    NPCHandler.npcs[c.npcIndex].npcType == 9465 || 
			    NPCHandler.npcs[c.npcIndex].npcType == 9466 || 
			    NPCHandler.npcs[c.npcIndex].npcType == 9467) {
			if (c.theStryke < 3) {
				c.getPA().sendStatement("@blu@I can't seem to attack this one.");
				c.npcIndex = 0;
				c.getCombat().resetPlayerAttack();
				return;
				}

			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 1648) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 5 to attack this creature.");
				return;
			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 1618) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 15 to attack this creature.");
				return;
			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 1643) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 45 to attack this creature.");
				return;
			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 1618) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 50 to attack this creature.");
				return;
			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 1624) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 65 to attack this creature.");
				return;
			}
			if (NPCHandler.npcs[i].spawnedBy != c.playerId && NPCHandler.npcs[i].spawnedBy > 0) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("This monster was not spawned for you.");
				return;
			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 1610) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 75 to attack this creature.");
				return;
			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 1613) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 80 to attack this creature.");
				return;
			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 1615) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 85 to attack this creature.");
				return;
			}
			if (!Slayer.canSlay(c, i) && NPCHandler.npcs[c.npcIndex].npcType == 2783) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You need a slayer level of 90 to attack this creature.");
				return;
			}
			if (c.getX() == NPCHandler.npcs[i].getX()
					&& c.getY() == NPCHandler.npcs[i].getY()) {
				c.getPA().walkTo(0, 1);
			}	
			c.followId2 = i;
			c.followId = 0;
			if (c.attackTimer <= 0) {
				c.usingBow = false;
				c.usingArrows = false;
				c.usingOtherRangeWeapons = false;
				c.usingCross = c.playerEquipment[c.playerWeapon] == 9185
						|| c.playerEquipment[c.playerWeapon] == 18357;
				c.bonusAttack = 0;
				c.rangeItemUsed = 0;
				c.projectileStage = 0;
				if (c.autocasting) {
					c.spellId = c.autocastId;
					c.usingMagic = true;
				}
				if (c.spellId > 0) {
					c.usingMagic = true;
				}
				c.attackTimer = c.getCombat().getAttackDelay(
						c.getItems()
						.getItemName(c.playerEquipment[c.playerWeapon])
						.toLowerCase());
				c.specAccuracy = 1.0;
				c.specDamage = 1.0;
				if (!c.usingMagic) {
					for (int bowId : c.BOWS) {
						if (c.playerEquipment[c.playerWeapon] == bowId) {
							c.usingBow = true;
							for (int arrowId : c.ARROWS) {
								if (c.playerEquipment[c.playerArrows] == arrowId) {
									c.usingArrows = true;
								}
							}
						}
					}

					for (int otherRangeId : c.OTHER_RANGE_WEAPONS) {
						if (c.playerEquipment[c.playerWeapon] == otherRangeId) {
							c.usingOtherRangeWeapons = true;
						}
					}
				}
				if ((!c.goodDistance(c.getX(), c.getY(),
						NPCHandler.npcs[i].getX(), NPCHandler.npcs[i].getY(), 2) && (c
								.getCombat().usingHally()
								&& !c.usingOtherRangeWeapons
								&& !c.usingBow && !c.usingMagic))
								|| (!c.goodDistance(c.getX(), c.getY(),
										NPCHandler.npcs[i].getX(),
										NPCHandler.npcs[i].getY(), 4) && (c.usingOtherRangeWeapons
												&& !c.usingBow && !c.usingMagic))
												|| (!c.goodDistance(c.getX(), c.getY(),
														NPCHandler.npcs[i].getX(),
														NPCHandler.npcs[i].getY(), 1) && (!c.usingOtherRangeWeapons
																&& !c.getCombat().usingHally() && !c.usingBow && !c.usingMagic))
																|| ((!c.goodDistance(c.getX(), c.getY(),
																		NPCHandler.npcs[i].getX(),
																		NPCHandler.npcs[i].getY(), 8) && (c.usingBow || c.usingMagic)))) {
					c.attackTimer = 2;
					return;
				}

				if (!c.usingCross
						&& !c.usingArrows
						&& c.usingBow
						&& (c.playerEquipment[c.playerWeapon] < 4212 || c.playerEquipment[c.playerWeapon] > 4223) 
						&& c.playerEquipment[c.playerWeapon] != 20171 ) {
					c.sendMessage("You have run out of arrows!");
					c.stopMovement();
					c.npcIndex = 0;
					return;
				}
				if (c.getCombat().correctBowAndArrows() < c.playerEquipment[c.playerArrows]
						&& Config.CORRECT_ARROWS
						&& c.usingBow
						&& !c.getCombat().usingCrystalBow()
						&& c.playerEquipment[c.playerWeapon] != 9185
						&& c.playerEquipment[c.playerWeapon] != 18357) {
					c.sendMessage("You can't use "
							+ c.getItems()
							.getItemName(
									c.playerEquipment[c.playerArrows])
									.toLowerCase()
									+ "s with a "
									+ c.getItems()
									.getItemName(
											c.playerEquipment[c.playerWeapon])
											.toLowerCase() + ".");
					c.stopMovement();
					c.npcIndex = 0;
					return;
				}

				if ((c.playerEquipment[c.playerWeapon] == 9185 || c.playerEquipment[c.playerWeapon] == 18357)
						&& !c.getCombat().properBolts()) {
					c.sendMessage("You must use bolts with a crossbow.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return;
				}
				if (c.usingBow
						|| c.castingMagic
						|| c.usingOtherRangeWeapons
						|| (c.getCombat().usingHally() && c.goodDistance(
								c.getX(), c.getY(), NPCHandler.npcs[i].getX(),
								NPCHandler.npcs[i].getY(), 2))) {
					c.stopMovement();
				}
				if (!c.getCombat().checkMagicReqs(c.spellId)) {
					c.stopMovement();
					c.npcIndex = 0;
					return;
				}
				c.faceUpdate(i);
				NPCHandler.npcs[i].underAttackBy = c.playerId;
				NPCHandler.npcs[i].lastDamageTaken = System.currentTimeMillis();
				if (c.usingSpecial && !c.usingMagic) {
					if (c.getCombat().checkSpecAmount(
							c.playerEquipment[c.playerWeapon])) {
						c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
						c.lastArrowUsed = c.playerEquipment[c.playerArrows];
						c.getCombat().activateSpecial(
								c.playerEquipment[c.playerWeapon], i);
						return;
					} else {
						c.sendMessage("You don't have the required special energy to use this attack.");
						c.usingSpecial = false;
						c.getItems().updateSpecialBar();
						c.npcIndex = 0;
						return;
					}
				}
				c.specMaxHitIncrease = 0;
				if (c.playerLevel[3] > 0 && !c.isDead
						&& NPCHandler.npcs[i].MaxHP > 0) {
					if (!c.usingMagic) {
						c.startAnimation(c
								.getCombat()
								.getWepAnim(
										c.getItems()
										.getItemName(
												c.playerEquipment[c.playerWeapon])
												.toLowerCase()));
						if (Server.npcHandler.getNPCs()[i].attackTimer < 9) {
							// NPCHandler.startAnimation(c.getCombat().npcDefenceAnim(i),
							// i);
						}
					} else {
						c.startAnimation(c.MAGIC_SPELLS[c.spellId][2]);
					}
				}
				c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
				c.lastArrowUsed = c.playerEquipment[c.playerArrows];

				if (!c.usingBow && !c.usingMagic && !c.usingOtherRangeWeapons) { // melee
					for (int ji = 0; ji < NPCHandler.armaGodKC.length; ji++) {
						if (NPCHandler.npcs[i].npcType == NPCHandler.armaGodKC[ji]) {
							c.sendMessage("You must use a range or mage weapon on this monster.");
							c.npcIndex = 0;
							return;
						}
					} // hit
					// delay
					c.followId2 = NPCHandler.npcs[i].npcId;
					c.getPA().followNpc();
					c.hitDelay = c.getCombat().getHitDelay(
							i,
							c.getItems()
							.getItemName(
									c.playerEquipment[c.playerWeapon])
									.toLowerCase());
					c.projectileStage = 0;
					c.oldNpcIndex = i;
				}

				if (c.usingBow && !c.usingOtherRangeWeapons && !c.usingMagic
						|| c.usingCross) { // range hit delay
					if (c.usingCross)
						c.usingBow = true;
					if (c.fightMode == 2)
						c.attackTimer--;
					c.followId2 = NPCHandler.npcs[i].npcId;
					c.getPA().followNpc();
					c.lastArrowUsed = c.playerEquipment[c.playerArrows];
					c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
					c.gfx100(c.getCombat().getRangeStartGFX());
					c.hitDelay = c.getCombat().getHitDelay(
							i,
							c.getItems()
							.getItemName(
									c.playerEquipment[c.playerWeapon])
									.toLowerCase());
					c.projectileStage = 1;
					c.oldNpcIndex = i;
					if (c.playerEquipment[c.playerWeapon] >= 4212
							&& c.playerEquipment[c.playerWeapon] <= 4223) {
						c.rangeItemUsed = c.playerEquipment[c.playerWeapon];
						c.crystalBowArrowCount++;
						c.lastArrowUsed = 0;
					} else {
						c.rangeItemUsed = c.playerEquipment[c.playerArrows];
						c.getItems().deleteArrow();
						if (c.playerEquipment[3] == 11235) {
							c.getItems().deleteArrow();
						}
					}
					c.getCombat().fireProjectileNpc();
				}

				if (c.usingOtherRangeWeapons && !c.usingMagic && !c.usingBow) {
					c.followId2 = NPCHandler.npcs[i].npcId;
					c.getPA().followNpc();
					c.rangeItemUsed = c.playerEquipment[c.playerWeapon];
					c.getItems().deleteEquipment();
					c.gfx100(c.getCombat().getRangeStartGFX());
					c.lastArrowUsed = 0;
					c.hitDelay = c.getCombat().getHitDelay(
							i,
							c.getItems()
							.getItemName(
									c.playerEquipment[c.playerWeapon])
									.toLowerCase());
					c.projectileStage = 1;
					c.oldNpcIndex = i;
					if (c.fightMode == 2)
						c.attackTimer--;
					c.getCombat().fireProjectileNpc();
				}

				if (c.usingMagic) { // magic hit delay
					int pX = c.getX();
					int pY = c.getY();
					int nX = NPCHandler.npcs[i].getX();
					int nY = NPCHandler.npcs[i].getY();
					int offX = (pY - nY) * -1;
					int offY = (pX - nX) * -1;
					c.castingMagic = true;
					c.projectileStage = 2;
					c.stopMovement();
					if (c.MAGIC_SPELLS[c.spellId][3] > 0) {
						if (c.getCombat().getStartGfxHeight() == 100) {
							c.gfx0(c.MAGIC_SPELLS[c.spellId][3]);
						} else {
							c.gfx0(c.MAGIC_SPELLS[c.spellId][3]);
						}
					}
					if (c.MAGIC_SPELLS[c.spellId][4] > 0) {
						c.getPA().createPlayersProjectile(pX, pY, offX, offY,
								50, 78, c.MAGIC_SPELLS[c.spellId][4],
								c.getCombat().getStartHeight(),
								c.getCombat().getEndHeight(), i + 1, 50);
					}
					c.hitDelay = c.getCombat().getHitDelay(
							i,
							c.getItems()
							.getItemName(
									c.playerEquipment[c.playerWeapon])
									.toLowerCase());
					c.oldNpcIndex = i;
					c.oldSpellId = c.spellId;
					c.spellId = 0;
					if (!c.autocasting)
						c.npcIndex = 0;
				}
				if (Misc.random(15) == 0) {
					c.getCombat().npcleechAttack(0);
				}
				if (Misc.random(15) == 0) {
					c.getCombat().npcleechDefence(0);
				}
				if (Misc.random(15) == 0) {
					c.getCombat().npcleechStrength(0);
				}
				if (Misc.random(20) == 0) {
					c.getCombat().npcleechSpecial(0);
				}
				if (Misc.random(15) == 0) {
					c.getCombat().npcleechRanged(0);
				}
				if (Misc.random(15) == 0) {
					c.getCombat().npcleechMagic(0);
				}

				if (System.currentTimeMillis() - c.foodDelay < 2700) {
					return;
				} else if (c.playerEquipment[c.playerWeapon] == 22494
						&& c.ppsLeft > 0) {
					c.foodDelay = System.currentTimeMillis();
					c.ppsLeft--;
				} else if (c.ppsLeft == 0) {
					c.getPA().ppsDegrade();
				} else if (c.ppsLeft < 10) {
					c.sendMessage("You have " + c.ppsLeft + " charges left");
				}

				if (System.currentTimeMillis() - c.tdDelay < 800) {
					return;
				} else if (NPCHandler.npcs[i].npcType == 8349
						&& Player.protMelee 
						&& !Player.protMage
						&& !Player.protRange
						&& Player.protMeleeTimer > 0) {
					c.tdDelay = System.currentTimeMillis();
					Player.protMeleeTimer--;
					//c.sendMessage("melee going down" + Player.protMeleeTimer);
				}
				if (System.currentTimeMillis() - c.tdDelay < 800) {
					return;
				} else if (NPCHandler.npcs[i].npcType == 8349
						&& !Player.protMelee 
						&& !Player.protMage
						&& Player.protRange
						&& Player.protRangeTimer > 0) {
					c.tdDelay = System.currentTimeMillis();
					Player.protRangeTimer--;
					//c.sendMessage("range going down");
				}



				if (System.currentTimeMillis() - c.tdDelay < 800) {
					return;
				} else if (NPCHandler.npcs[i].npcType == 8349
						&& !Player.protMelee 
						&& Player.protMage
						&& !Player.protRange
						&& Player.protMageTimer > 0) {
					c.tdDelay = System.currentTimeMillis();
					Player.protMageTimer--;
					//c.sendMessage("mage going down");
				}
				if (System.currentTimeMillis() - c.tdDelay < 800) {
					return;
				} else if (NPCHandler.npcs[i].npcType == 8349 
						&& Player.protRangeTimer == 0
						&& Player.protMageTimer == 0
						&& Player.protMeleeTimer == 0
						&& Player.protMage
						&& !Player.protMelee
						&& !Player.protRange) {
					Player.protMelee = true;
					Player.protMage = false;
					Player.protRange = false;
					c.sendMessage("The demon has changed its protection prayers!");
					//NPC.appendDemon(c);
					NPCHandler.npcs[c.npcIndex].requestTransform(8349);
					Player.protMageTimer = 0;
					Player.protRangeTimer = 0;
					Player.protMeleeTimer = Misc.random(4) + 6;
				} else if (NPCHandler.npcs[i].npcType == 8349 
						&& Player.protRangeTimer == 0
						&& Player.protMageTimer == 0
						&& Player.protMeleeTimer == 0
						&& !Player.protMage
						&& Player.protMelee
						&& !Player.protRange) {
					Player.protMage = false;
					Player.protMelee = false;
					//NPC.appendDemon(c);
					c.sendMessage("The demon has changed its protection prayers!");
					NPCHandler.npcs[c.npcIndex].requestTransform(8351);
					Player.protMageTimer = 0;
					Player.protMeleeTimer = 0;
					Player.protRange = true;
					Player.protRangeTimer = Misc.random(4) + 14;
				} else if (NPCHandler.npcs[i].npcType == 8349 
						&& Player.protRangeTimer == 0
						&& Player.protMageTimer == 0
						&& Player.protMeleeTimer == 0
						&& !Player.protMage
						&& !Player.protMelee
						&& Player.protRange) {
					Player.protMage = true;
					Player.protMelee = false;
					Player.protRange = false;
					c.sendMessage("The demon has changed its protection prayers!");
					//NPC.appendDemon(c);
					NPCHandler.npcs[c.npcIndex].requestTransform(8350);
					Player.protRangeTimer = 0;
					Player.protMeleeTimer = 0;
					Player.protMageTimer = Misc.random(4) + 14;
				}
				/*if (NPCHandler.npcs[i].npcType == 8349 && c.demonCount == 3) {

					NPC.appendDemon(c);
					c.demonCount = 0;
				} else if (c.demonCount < 3) {
					c.demonCount++;
			}*/


				if (c.usingBow && Config.CRYSTAL_BOW_DEGRADES) { // crystal bow
					// degrading
					if (c.playerEquipment[c.playerWeapon] == 4212) { // new
						// crystal
						// bow
						// becomes
						// full
						// bow
						// on
						// the
						// first
						// shot
						c.getItems().wearItem(4214, 1, 3);
					}

					if (c.crystalBowArrowCount >= 250) {
						switch (c.playerEquipment[c.playerWeapon]) {

						case 4223: // 1/10 bow
							c.getItems().wearItem(-1, 1, 3);
							c.sendMessage("Your crystal bow has fully degraded.");
							if (!c.getItems().addItem(4207, 1)) {
								Server.itemHandler.createGroundItem(c, 4207,
										c.getX(), c.getY(), 1, c.getId());
							}
							c.crystalBowArrowCount = 0;
							break;

						default:
							c.getItems().wearItem(
									++c.playerEquipment[c.playerWeapon], 1, 3);
							c.sendMessage("Your crystal bow degrades.");
							c.crystalBowArrowCount = 0;
							break;
						}
					}
				}
			}
		}
	}
}