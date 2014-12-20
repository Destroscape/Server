package game.net.packets;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.Config;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.skill.hunter.ButterflyCatching;
import game.skill.hunter.Implings;

/*
 * Project Insanity - Evolved v.3
 * ClickNPC
 */

public class ClickNPC implements PacketType {

	public static final int ATTACK_NPC = 72, MAGE_NPC = 131, FIRST_CLICK = 155,
			SECOND_CLICK = 17, THIRD_CLICK = 21;

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		c.npcIndex = 0;
		c.npcClickIndex = 0;
		c.playerIndex = 0;
		c.clickNpcType = 0;
		c.getPA().resetFollow();
		switch (packetType) {

		/**
		 * Attack NPC melee or range
		 */
		case ATTACK_NPC:
			if (!c.mageAllowed) {
				c.mageAllowed = true;
				c.sendMessage("I can't reach that.");
				break;
			}
			c.npcIndex = c.getInStream().readUnsignedWordA();
			if (NPCHandler.npcs[c.npcIndex] == null) {
				c.npcIndex = 0;
				break;
			}
			if (NPCHandler.npcs[c.npcIndex].MaxHP == 0) {
				c.npcIndex = 0;
				break;
			}
			if (NPCHandler.npcs[c.npcIndex] == null) {
				break;
			}
			if (NPCHandler.npcs[c.npcIndex].npcType == 13447) {
				if (NPCHandler.npcs[c.npcIndex].nexStage == 0) {
					if (NPCHandler.nexCountDown == 0) {
						NPCHandler.nexCountDown = 25;
					}
					c.getPA().sendMp3("atlast");
					return;
				}
			}
			if (c.autocastId > 0) {
				c.autocasting = true;
			}
			if (!c.autocasting && c.spellId > 0) {
				c.spellId = 0;
			}
			c.faceUpdate(c.npcIndex);
			c.usingMagic = false;
			boolean usingBow = false;
			boolean usingOtherRangeWeapons = false;
			boolean usingArrows = false;
			final boolean usingCross = c.playerEquipment[c.playerWeapon] == 9185;
			final boolean usingChaoticCross = c.playerEquipment[c.playerWeapon] == 18357;
			if (c.playerEquipment[c.playerWeapon] >= 4214
					&& c.playerEquipment[c.playerWeapon] <= 4223) {
				usingBow = true;
			}
			for (final int bowId : c.BOWS) {
				if (c.playerEquipment[c.playerWeapon] == bowId) {
					usingBow = true;
					for (final int arrowId : c.ARROWS) {
						if (c.playerEquipment[c.playerArrows] == arrowId) {
							usingArrows = true;
						}
					}
				}
			}
			for (final int otherRangeId : c.OTHER_RANGE_WEAPONS) {
				if (c.playerEquipment[c.playerWeapon] == otherRangeId) {
					usingOtherRangeWeapons = true;
				}
			}
			if ((usingBow || c.autocasting)
					&& c.goodDistance(c.getX(), c.getY(),
							NPCHandler.npcs[c.npcIndex].getX(),
							NPCHandler.npcs[c.npcIndex].getY(), 7)) {
				c.stopMovement();
			}
			if (usingOtherRangeWeapons
					&& c.goodDistance(c.getX(), c.getY(),
							NPCHandler.npcs[c.npcIndex].getX(),
							NPCHandler.npcs[c.npcIndex].getY(), 4)) {
				c.stopMovement();
			}
			if (!usingCross && !usingChaoticCross && !usingArrows && usingBow
					&& c.playerEquipment[c.playerWeapon] < 4212
					&& c.playerEquipment[c.playerWeapon] > 4223 && !usingCross
					&& !usingChaoticCross) {
				c.sendMessage("You have run out of arrows!");
				break;
			}
			if (c.getCombat().correctBowAndArrows() < c.playerEquipment[c.playerArrows]
					&& Config.CORRECT_ARROWS
					&& usingBow
					&& !c.getCombat().usingCrystalBow()
					&& c.playerEquipment[c.playerWeapon] != 9185
					&& c.playerEquipment[c.playerWeapon] != 18357) {
				c.sendMessage("You can't use "
						+ c.getItems()
								.getItemName(c.playerEquipment[c.playerArrows])
								.toLowerCase()
						+ "s with a "
						+ c.getItems()
								.getItemName(c.playerEquipment[c.playerWeapon])
								.toLowerCase() + ".");
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (c.playerEquipment[c.playerWeapon] == 9185
					&& !c.getCombat().properBolts()) {
				c.sendMessage("You must use bolts with a crossbow.");
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (c.playerEquipment[c.playerWeapon] == 18357
					&& !c.getCombat().properBolts()) {
				c.sendMessage("You must use bolts with a crossbow.");
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (c.followId > 0) {
				c.getPA().resetFollow();
			}
			if (c.attackTimer <= 0) {
				c.getCombat().attackNpc(c.npcIndex);
				c.attackTimer++;
			}
			break;

		/**
		 * Attack NPC with magic
		 **/
		case MAGE_NPC:
			if (!c.mageAllowed) {
				c.mageAllowed = true;
				c.sendMessage("I can't reach that.");
				break;
			}
			// c.usingSpecial = false;
			// c.getItems().updateSpecialBar();
			c.npcIndex = c.getInStream().readSignedWordBigEndianA();
			final int castingSpellId = c.getInStream().readSignedWordA();
			c.usingMagic = false;
			if (NPCHandler.npcs[c.npcIndex] == null) {
				break;
			}
			if (NPCHandler.npcs[c.npcIndex].MaxHP == 0
					|| NPCHandler.npcs[c.npcIndex].npcType == 944) {
				c.sendMessage("You can't attack this npc.");
				break;
			}
			for (int i = 0; i < c.MAGIC_SPELLS.length; i++) {
				if (castingSpellId == c.MAGIC_SPELLS[i][0]) {
					c.spellId = i;
					c.usingMagic = true;
					break;
				}
			}
			if (castingSpellId == 1171) { // crumble undead
				for (final int npc : Config.UNDEAD_NPCS) {
					if (NPCHandler.npcs[c.npcIndex].npcType != npc) {
						c.sendMessage("You can only attack undead monsters with this spell.");
						c.usingMagic = false;
						c.stopMovement();
						break;
					}
				}
			}
			if (c.autocasting) {
				c.autocasting = false;
			}
			if (c.usingMagic) {
				if (c.goodDistance(c.getX(), c.getY(),
						NPCHandler.npcs[c.npcIndex].getX(),
						NPCHandler.npcs[c.npcIndex].getY(), 6)) {
					c.stopMovement();
				}
				if (c.attackTimer <= 0) {
					c.getCombat().attackNpc(c.npcIndex);
					c.attackTimer++;
				}
			}
			break;

		/**
		 * First Click Attack
		 */
		case FIRST_CLICK:
			c.npcClickIndex = c.inStream.readSignedWordBigEndian();
			c.npcType = NPCHandler.npcs[c.npcClickIndex].npcType;
			Implings.catchImp(c, c.npcClickIndex, c.npcType);
			ButterflyCatching.catchButterfly(c, c.npcClickIndex, c.npcType);
			if (c.goodDistance(NPCHandler.npcs[c.npcClickIndex].getX(),
					NPCHandler.npcs[c.npcClickIndex].getY(), c.getX(),
					c.getY(), 1)) {
				c.turnPlayerTo(NPCHandler.npcs[c.npcClickIndex].getX(),
						NPCHandler.npcs[c.npcClickIndex].getY());
				NPCHandler.npcs[c.npcClickIndex].facePlayer(c.playerId);
				c.getActions().firstClickNpc(c.npcType);
			} else {
				c.clickNpcType = 1;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(final CycleEventContainer container) {
						if (c.clickNpcType == 1
								&& NPCHandler.npcs[c.npcClickIndex] != null) {
							if (c.goodDistance(c.getX(), c.getY(),
									NPCHandler.npcs[c.npcClickIndex].getX(),
									NPCHandler.npcs[c.npcClickIndex].getY(), 1)) {
								c.turnPlayerTo(
										NPCHandler.npcs[c.npcClickIndex].getX(),
										NPCHandler.npcs[c.npcClickIndex].getY());
								NPCHandler.npcs[c.npcClickIndex]
										.facePlayer(c.playerId);
								c.getActions().firstClickNpc(c.npcType);
								container.stop();
							}
						}
						if (c.clickNpcType == 0 || c.clickNpcType > 1) {
							container.stop();
						}
					}

					@Override
					public void stop() {
						c.clickNpcType = 0;
					}
				}, 1);
			}
			break;

		/**
		 * Second Click Attack
		 */
		case SECOND_CLICK:
			c.npcClickIndex = c.inStream.readUnsignedWordBigEndianA();
			c.npcType = NPCHandler.npcs[c.npcClickIndex].npcType;
			c.faceUpdate(c.npcClickIndex);
			if (c.goodDistance(NPCHandler.npcs[c.npcClickIndex].getX(),
					NPCHandler.npcs[c.npcClickIndex].getY(), c.getX(),
					c.getY(), 1)) {
				c.turnPlayerTo(NPCHandler.npcs[c.npcClickIndex].getX(),
						NPCHandler.npcs[c.npcClickIndex].getY());
				NPCHandler.npcs[c.npcClickIndex].facePlayer(c.playerId);
				c.getActions().secondClickNpc(c.npcType);
				switch (c.npcType) {
				/**
				 * Airport NPC's
				 **/
				}
			} else {
				c.clickNpcType = 2;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(final CycleEventContainer container) {
						if (c.clickNpcType == 2
								&& NPCHandler.npcs[c.npcClickIndex] != null) {
							if (c.goodDistance(c.getX(), c.getY(),
									NPCHandler.npcs[c.npcClickIndex].getX(),
									NPCHandler.npcs[c.npcClickIndex].getY(), 1)) {
								c.turnPlayerTo(
										NPCHandler.npcs[c.npcClickIndex].getX(),
										NPCHandler.npcs[c.npcClickIndex].getY());
								NPCHandler.npcs[c.npcClickIndex]
										.facePlayer(c.playerId);
								c.getActions().secondClickNpc(c.npcType);
								container.stop();
							}
						}
						if (c.clickNpcType < 2 || c.clickNpcType > 2) {
							container.stop();
						}
					}

					@Override
					public void stop() {
						c.clickNpcType = 0;
					}
				}, 1);
			}
			break;

		/**
		 * Thrid Click Attack
		 */
		case THIRD_CLICK:
			c.npcClickIndex = c.inStream.readSignedWord();
			c.npcType = NPCHandler.npcs[c.npcClickIndex].npcType;
			c.faceUpdate(c.npcClickIndex);
			if (c.goodDistance(NPCHandler.npcs[c.npcClickIndex].getX(),
					NPCHandler.npcs[c.npcClickIndex].getY(), c.getX(),
					c.getY(), 1)) {
				c.turnPlayerTo(NPCHandler.npcs[c.npcClickIndex].getX(),
						NPCHandler.npcs[c.npcClickIndex].getY());
				NPCHandler.npcs[c.npcClickIndex].facePlayer(c.playerId);
				c.getActions().thirdClickNpc(c.npcType);
			} else {
				c.clickNpcType = 3;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(final CycleEventContainer container) {
						if (c.clickNpcType == 3
								&& NPCHandler.npcs[c.npcClickIndex] != null) {
							if (c.goodDistance(c.getX(), c.getY(),
									NPCHandler.npcs[c.npcClickIndex].getX(),
									NPCHandler.npcs[c.npcClickIndex].getY(), 1)) {
								c.turnPlayerTo(
										NPCHandler.npcs[c.npcClickIndex].getX(),
										NPCHandler.npcs[c.npcClickIndex].getY());
								NPCHandler.npcs[c.npcClickIndex]
										.facePlayer(c.playerId);
								c.getActions().thirdClickNpc(c.npcType);
								container.stop();
							}
						}
						if (c.clickNpcType < 3) {
							container.stop();
						}
					}

					@Override
					public void stop() {
						c.clickNpcType = 0;
					}
				}, 1);
			}
			break;
		}
	}

}
