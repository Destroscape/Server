package game.entity.player;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.event.Task;
import engine.network.Connection;
import engine.util.Misc;
import game.Config;
import game.Server;
import game.combat.magic.NonCombatSpells;
import game.combat.prayer.Prayer;
import game.content.AntiPKFarming;
import game.content.clan.Clan;
import game.content.achievement.*;
import game.entity.npc.NPCHandler;
import game.entity.npc.NPC;
import game.item.Item;
import game.minigame.barrows.Chest;
import game.minigame.bountyhunter.BountyHunter;
import game.minigame.fightpits.FightPits;
import game.minigame.weapongame.WeaponGame;
import game.minigame.zombies.Zombies;
import game.skill.SkillHandler;
import game.skill.SkillingTools;
import game.skill.dungeoneering.Dungeoneering;
import game.skill.summoning.Summoning;
import game.content.achievement.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PlayerAssistant {

	public void sendMp3(String mp3) {
		// synchronized (c) {
		sendFrame126(":mp3:" + mp3 + "", 24000);
		// }
	}

	public NPC getNpcWithinDistance(final Player player, final int tiles) {
		NPC npc = null;
		final int myX = player.cannonBaseX;
		final int myY = player.cannonBaseY;
		boolean status = true;
		for (final NPC n : NPCHandler.npcs) {
			if (n == null) {
				continue;
			}
			if (player.WithinDistance(n.getX(), n.getY(), player.getX(),
					player.getY(), tiles)) {
				if (n.isDead && n.heightLevel != player.heightLevel && n.isDead
						&& n.HP == 0 && n.npcType == 1266 && n.npcType == 1268) {
					continue;
				}
				for (final int element : Config.NON_ATTAKABLE_NPCS) {
					if (element == n.npcType) {
						status = false;
						break;
					}
				}
				if (!status) {
					return null;
				}
				if (npc != null) {
					break;
				}
				final int theirX = n.absX;
				final int theirY = n.absY;
				switch (player.rotation) {
				case 1: // north
					if (theirY > myY && theirX >= myX - 1 && theirX <= myX + 1) {
						npc = n;
					}
					break;
				case 2: // north-east
					if (theirX >= myX + 1 && theirY >= myY + 1) {
						npc = n;
					}
					break;
				case 3: // east
					if (theirX > myX && theirY >= myY - 1 && theirY <= myY + 1) {
						npc = n;
					}
					break;
				case 4: // south-east
					if (theirY <= myY - 1 && theirX >= myX + 1) {
						npc = n;
					}
					break;
				case 5: // south
					if (theirY < myY && theirX >= myX - 1 && theirX <= myX + 1) {
						npc = n;
					}
					break;
				case 6: // south-west
					if (theirX <= myX - 1 && theirY <= myY - 1) {
						npc = n;
					}
					break;
				case 7: // west
					if (theirX < myX && theirY >= myY - 1 && theirY <= myY + 1) {
						npc = n;
					}
					break;
				case 8: // north-west
					if (theirX <= myX - 1 && theirY >= myY + 1) {
						npc = n;
					}
					break;
				}
			}
		}
		return npc;
	}

	public void createPlayersObjectAnim(int X, int Y, int animationID, int tileObjectType, int orientation) {
		if (c == null) {
			return;
		}
		try {
			c.outStream.createFrame(85);
			c.outStream.writeByteC(Y - (c.mapRegionY * 8));
			c.outStream.writeByteC(X - (c.mapRegionX * 8));
			int x = 0;
			int y = 0;
			c.outStream.createFrame(160);
			c.outStream.writeByteS(((x & 7) << 4) + (y & 7));//tiles away - could just send 0
			c.outStream.writeByteS((tileObjectType << 2) + (orientation & 3));
			c.outStream.writeWordA(animationID);// animation id
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void objectAnim(int X, int Y, int animationID, int tileObjectType, int orientation) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				Player person = (Player) PlayerHandler.players[i];
				if (person != null && person.distanceToPoint(X, Y) <= 25) {
					person.getPA().createPlayersObjectAnim(X, Y, animationID, tileObjectType, orientation);
				}
			}
		}
	}



	/**
	 * Some things for special attack
	 */

	public void npcMageDamage(int damage) {
		int i = c.npcIndex;
		c.previousDamage = damage;
		/*
		 * if (Misc.random(NPCHandler.npcs[i].defence) > 10+
		 * Misc.random(mageAtk()) + bonusAttack) { damage/=2; } else if
		 * (NPCHandler.npcs[i].npcType == 2881 || NPCHandler.npcs[i].npcType ==
		 * 2882) { damage/=2; }
		 */
		if (NPCHandler.npcs[i].HP - damage < 0) {
			damage = NPCHandler.npcs[i].HP;
		}
		c.getPA().addSkillXP((damage * Config.MAGIC_EXP_RATE), 6);
		c.getPA().addSkillXP((damage * Config.MAGIC_EXP_RATE / 3), 3);
		c.getPA().refreshSkill(3);
		c.getPA().refreshSkill(6);
		if (damage > 0) {
			if (NPCHandler.npcs[i].npcType >= 3777
					&& NPCHandler.npcs[i].npcType <= 3780) {
				c.pcDamage += damage;
			}
		}
		NPCHandler.npcs[i].underAttack = true;
		c.killingNpcIndex = c.npcIndex;
		c.lastNpcAttacked = i;
		NPCHandler.npcs[i].hitDiff = damage;
		NPCHandler.npcs[i].HP -= damage;
		NPCHandler.npcs[i].hitUpdateRequired = true;
		NPCHandler.npcs[i].hitIcon = 2;
		c.totalDamageDealt += damage;
		NPCHandler.npcs[i].updateRequired = true;
	}

	public void applyPlayerMageDamage(int damage) {
		int i = c.playerIndex;
		c.previousDamage = damage;
		Player o = PlayerHandler.players[i];
		if (o == null) {
			return;
		}
		if (PlayerHandler.players[i].playerLevel[3] - damage < 0) {
			damage = PlayerHandler.players[i].playerLevel[3];
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
		PlayerHandler.players[i].logoutDelay = System.currentTimeMillis();
		PlayerHandler.players[i].underAttackBy = c.playerId;
		PlayerHandler.players[i].killerId = c.playerId;
		PlayerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
		PlayerHandler.players[i].hitIcon = 2;
		if (c.killedBy != PlayerHandler.players[i].playerId)
			c.totalPlayerDamageDealt = 0;
		c.killedBy = PlayerHandler.players[i].playerId;
		PlayerHandler.players[i].dealDamage(damage);
		PlayerHandler.players[i].damageTaken[c.playerId] += damage;
		c.totalPlayerDamageDealt += damage;
		PlayerHandler.players[i].updateRequired = true;
		o.getPA().refreshSkill(3);
		PlayerHandler.players[i].hitIcon = 2;
		PlayerHandler.players[i].handleHitMask(damage, 0, 2);
	}

        public void movePlayerFade(final int x, final int y, final int h) {
            c.isFading = true;
            c.resetWalkingQueue();
            c.getPA().showInterface(18433);
            Server.getTaskScheduler().schedule(new Task() {
                int tick = 12;
                public void execute() {
                    if (tick == 6) {
                        c.teleportToX = x;
                        c.teleportToY = y;
                        c.heightLevel = h;
                        requestUpdates();    
                    }
                    if(tick <= 0) {
                        c.isFading = false;
                        this.stop();
                    }
                    if (tick > 0)
                        tick--;
                }
            });
        }

	/**
	 * Gets the players clan.
	 * 
	 * @return
	 */

	public void destroyBindInterface(int itemId) {
		itemId = c.droppedItem;
		String itemName = c.getItems().getItemName(c.droppedItem);
		String[][] info = {
				{ "Are you sure you want to destroy this item?", "14174" },
				{ "Yes.", "14175" }, { "No.", "14176" }, { "", "14177" },
				{ "This item will no longer be binded to you", "14182" },
				{ "after you destroy this ancient item.", "14183" },
				{ itemName, "14184" } };// make some kind of c.getItemInfo
		sendFrame34(itemId, 0, 14171, 1);
		for (int i = 0; i < info.length; i++)
			sendFrame126(info[i][0], Integer.parseInt(info[i][1]));
		sendFrame164(14170);
	}

	public void destroyBind(int itemId) {
		itemId = c.droppedItem;
		String itemName = c.getItems().getItemName(itemId);
		c.getItems().deleteItem(itemId, c.getItems().getItemSlot(itemId),
				c.playerItemsN[c.getItems().getItemSlot(itemId)]);
		c.sendMessage("Your " + itemName
				+ " vanishes as you drop it on the ground.");
		removeAllWindows();
		if (c.bind1 == c.droppedItem) {
			c.bind1 = -1;
			return;
		} else if (c.bind2 == c.droppedItem) {
			c.bind2 = -1;
			return;
		} else if (c.bind3 == c.droppedItem) {
			c.bind3 = -1;
			return;
		} else if (c.bind4 == c.droppedItem) {
			c.bind4 = -1;
			return;
		}
	}

	public void sendFrame34a(int frame, int item, int slot, int amount) {
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(item + 1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}

	public Clan getClan() {
		if (Server.clanManager.clanExists(c.playerName)) {
			return Server.clanManager.getClan(c.playerName);
		}
		return null;
	}

	public void showOption(int i, int l, String s) {
		if (c.getOutStream() != null) {
			if (!optionType.equalsIgnoreCase(s)) {
				optionType = s;
				c.getOutStream().createFrameVarSize(104);
				c.getOutStream().writeByteC(i);
				c.getOutStream().writeByteA(l);
				c.getOutStream().writeString(s);
				c.getOutStream().endFrameVarSize();
				c.flushOutStream();
			}
		}
	}

	public int fireProtection() {
		int prot = 0;
		switch (c.playerEquipment[c.playerShield]) {
		case 1540:
			prot++;
			break;
		case 11283:
			prot += 2;
			break;
		case 15730:
			prot += 3;
			break;
		}
		return prot;
	}

	public void ditchJump(final Player c, final int x, final int y) {
		c.getPA().walkTo(x, y);
		c.isRunning2 = false;
		c.playerWalkIndex = 6132;
		c.getPA().requestUpdates();
	}

	public void resetDitchJump(final Player c) {
		c.isRunning2 = true;
		c.getPA().sendFrame36(173, 1);
		c.getCombat().getPlayerAnimIndex(
				c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
				.toLowerCase());
		c.getPA().requestUpdates();
	}

	public void globalObject(int objectId, int objectX, int objectY, int face,
			int objectType, int h) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			Player c = PlayerHandler.players[i];
			if (c == null || c.disconnected)
				continue;
			if (c.heightLevel != h)
				continue;
			object(objectId, objectX, objectY, face, objectType);
		}
	}

	public void sendClan(String name, String message, String clan, int rights) {
		if(rights >= 3)
			rights--;
		c.outStream.createFrameVarSizeWord(217);
		c.outStream.writeString(name);
		c.outStream.writeString(Misc.formatPlayerName(message));
		c.outStream.writeString(clan);
		c.outStream.writeWord(rights);
		c.outStream.endFrameVarSize();
	}

	/**
	 * Sets the clan information for the player's clan.
	 */
	public void setClanData() {
		boolean exists = Server.clanManager.clanExists(c.playerName);
		if (!exists || c.clan == null) {
			sendFrame126("Join chat", 18135);
			sendFrame126("Talking in: Not in chat", 18139);
			sendFrame126("Owner: None", 18140);
		}
		if (!exists) {
			sendFrame126("Chat Disabled", 18306);
			String title = "";
			for (int id = 18307; id < 18317; id += 3) {
				if (id == 18307) {
					title = "Anyone";
				} else if (id == 18310) {
					title = "Anyone";
				} else if (id == 18313) {
					title = "General+";
				} else if (id == 18316) {
					title = "Only Me";
				}
				sendFrame126(title, id + 2);
			}
			for (int index = 0; index < 100; index++) {
				sendFrame126("", 18323 + index);
			}
			for (int index = 0; index < 100; index++) {
				sendFrame126("", 18424 + index);
			}
			return;
		}
		Clan clan = Server.clanManager.getClan(c.playerName);
		sendFrame126(clan.getTitle(), 18306);
		String title = "";
		for (int id = 18307; id < 18317; id += 3) {
			if (id == 18307) {
				title = clan.getRankTitle(clan.whoCanJoin)
						+ (clan.whoCanJoin > Clan.Rank.ANYONE
								&& clan.whoCanJoin < Clan.Rank.OWNER ? "+" : "");
			} else if (id == 18310) {
				title = clan.getRankTitle(clan.whoCanTalk)
						+ (clan.whoCanTalk > Clan.Rank.ANYONE
								&& clan.whoCanTalk < Clan.Rank.OWNER ? "+" : "");
			} else if (id == 18313) {
				title = clan.getRankTitle(clan.whoCanKick)
						+ (clan.whoCanKick > Clan.Rank.ANYONE
								&& clan.whoCanKick < Clan.Rank.OWNER ? "+" : "");
			} else if (id == 18316) {
				title = clan.getRankTitle(clan.whoCanBan)
						+ (clan.whoCanBan > Clan.Rank.ANYONE
								&& clan.whoCanBan < Clan.Rank.OWNER ? "+" : "");
			}
			sendFrame126(title, id + 2);
		}
		if (clan.rankedMembers != null) {
			for (int index = 0; index < 100; index++) {
				if (index < clan.rankedMembers.size()) {
					sendFrame126("<clan=" + clan.ranks.get(index) + ">"
							+ clan.rankedMembers.get(index), 18323 + index);
				} else {
					sendFrame126("", 18323 + index);
				}
			}
		}
		if (clan.bannedMembers != null) {
			for (int index = 0; index < 100; index++) {
				if (index < clan.bannedMembers.size()) {
					sendFrame126(clan.bannedMembers.get(index), 18424 + index);
				} else {
					sendFrame126("", 18424 + index);
				}
			}
		}
	}

	private final Player c;
	public int CraftInt, Dcolor, FletchInt;
	public int mapStatus = 0;

	/**
	 * Show option, attack, trade, follow etc
	 **/
	public String optionType = "null";
	public static int Pots[] = {};

	public PlayerAssistant(final Player Player) {
		c = Player;
	}

	public void createProjectile3(int casterY, int casterX, int offsetY,
			int offsetX, int gfxMoving, int StartHeight, int endHeight,
			int speed, int AtkIndex) {
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				Player p = PlayerHandler.players[i];
				if (p.WithinDistance(c.absX, c.absY, p.absX, p.absY, 60)) {
					if (p.heightLevel == c.heightLevel) {
						if (PlayerHandler.players[i] != null
								&& !PlayerHandler.players[i].disconnected) {
							p.outStream.createFrame(85);
							p.outStream
							.writeByteC((casterY - (p.mapRegionY * 8)) - 2);
							p.outStream
							.writeByteC((casterX - (p.mapRegionX * 8)) - 3);
							p.outStream.createFrame(117);
							p.outStream.writeByte(50);
							p.outStream.writeByte(offsetY);
							p.outStream.writeByte(offsetX);
							p.outStream.writeWord(AtkIndex);
							p.outStream.writeWord(gfxMoving);
							p.outStream.writeByte(StartHeight);
							p.outStream.writeByte(endHeight);
							p.outStream.writeWord(51);
							p.outStream.writeWord(speed);
							p.outStream.writeByte(16);
							p.outStream.writeByte(64);
						}
					}
				}
			}
		}
	}

	public void getDragonClawHits(Player c, int i) {
		c.clawHit[0] = i;
		c.clawHit[1] = c.clawHit[0] / 2;
		c.clawHit[2] = c.clawHit[1] / 2;
		c.clawHit[3] = (c.clawHit[1] - c.clawHit[2]);
	}

	public void itemOnInterface(int interfaceChild, int zoom, int itemId) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(246);
			c.getOutStream().writeWordBigEndian(interfaceChild);
			c.getOutStream().writeWord(zoom);
			c.getOutStream().writeWord(itemId);
			c.flushOutStream();
		}
	}

	public void hitDragonClaws(final Player c, int damage) {
		if (!c.usingClaws) {
			return;
		}
		if (c.clawHit[0] <= 0) {
			getDragonClawHits(c, damage);
		}
		if (c.npcIndex > 0) {
			c.getCombat().applyNpcMeleeDamage(c.npcIndex, 1, c.clawHit[0]);
			c.getCombat().applyNpcMeleeDamage(c.npcIndex, 2, c.clawHit[1]);
		} else if (c.playerIndex > 0) {
			c.getCombat()
			.applyPlayerMeleeDamage(c.playerIndex, 1, c.clawHit[0]);
			c.getCombat()
			.applyPlayerMeleeDamage(c.playerIndex, 2, c.clawHit[1]);
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.npcIndex > 0) {
					c.getCombat().applyNpcMeleeDamage(c.npcIndex, 1,
							c.clawHit[2]);
					c.getCombat().applyNpcMeleeDamage(c.npcIndex, 2,
							c.clawHit[3]);
				} else if (c.playerIndex > 0) {
					c.getCombat().applyPlayerMeleeDamage(c.playerIndex, 1,
							c.clawHit[2]);
					c.getCombat().applyPlayerMeleeDamage(c.playerIndex, 2,
							c.clawHit[3]);
				}
				resetDragonHits(c);
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public void resetDragonHits(Player c) {
		for (int i = 0; i < 4; i++) {
			c.clawHit[i] = -1;
		}
		c.usingClaws = false;
	}

	public boolean isFilledVial(final Player c, final int item) {
		switch (item) {
		case 6685:
		case 6687:
		case 6689:
		case 6691:
		case 2436:
		case 145:
		case 147:
		case 149:
		case 2440:
		case 157:
		case 159:
		case 161:
		case 2444:
		case 169:
		case 171:
		case 173:
		case 2432:
		case 133:
		case 135:
		case 137:
		case 113:
		case 115:
		case 117:
		case 119:
		case 2428:
		case 121:
		case 123:
		case 125:
		case 2442:
		case 163:
		case 165:
		case 167:
		case 3024:
		case 3026:
		case 3028:
		case 3030:
		case 12140:
		case 12142:
		case 12144:
		case 12146:
		case 10925:
		case 10927:
		case 10929:
		case 10931:
		case 2434:
		case 139:
		case 141:
		case 143:
		case 2446:
		case 175:
		case 177:
		case 179:
		case 2448:
		case 181:
		case 183:
		case 185:
		case 15312:
		case 15313:
		case 15314:
		case 15315:
		case 15308:
		case 15309:
		case 15310:
		case 15311:
		case 15316:
		case 15317:
		case 15318:
		case 15319:
		case 15324:
		case 15325:
		case 15326:
		case 15327:
		case 15320:
		case 15321:
		case 15322:
		case 15323:
		case 15328:
		case 15329:
		case 15330:
		case 15331:
		case 15300:
		case 15301:
		case 15302:
		case 15303:
		case 15332:
		case 15333:
		case 15334:
		case 15335:
			return true;
		}
		return false;
	}

	public void handleObjectRegion(int objectId, int minX, int minY, int maxX,
			int maxY) {
		for (int i = minX; i < maxX + 1; i++) {
			for (int j = minY; j < maxY + 1; j++) {
				c.getPA().object(objectId, i, j, -1, 10);
			}
		}
	}

	public boolean itemUsedInRegion(int minX, int maxX, int minY, int maxY) {
		return (c.objectX >= minX && c.objectX <= maxX)
				&& (c.objectY >= minY && c.objectY <= maxY);
	}

	public void createPlayersProjectile(int x, int y, int offX, int offY,
			int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int lockon, int time, int h) {
		// synchronized (c) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			Player p = PlayerHandler.players[i];
			if (p != null) {
				if (c.heightLevel != h)
					continue;
				Player person = p;
				if (person != null) {
					if (c.heightLevel != h)
						continue;
					if (person.getOutStream() != null) {
						if (person.distanceToPoint(x, y) <= 25) {
							person.getPA().createProjectile(x, y, offX, offY,
									angle, speed, gfxMoving, startHeight,
									endHeight, lockon, time);
						}
					}
				}
			}
		}
		// }
	}

	public void createPlayersProjectile2(int x, int y, int offX, int offY,
			int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int lockon, int time, int slope, int h) {
		// synchronized (c) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			Player p = PlayerHandler.players[i];
			if (p != null) {
				if (c.heightLevel != h)
					continue;
				Player person = p;
				if (person != null) {
					if (c.heightLevel != h)
						continue;
					if (person.getOutStream() != null) {
						if (person.distanceToPoint(x, y) <= 25) {
							person.getPA().createProjectile2(x, y, offX, offY,
									angle, speed, gfxMoving, startHeight,
									endHeight, lockon, time, slope);
						}
					}
				}
			}
		}
		// }
	}

	public void antiqueLamp() {
		c.getPA().showInterface(2808);
	}

	public boolean addSkillXP(int amount, final int skill) {
		if (amount + c.playerXP[skill] < 0 || c.playerXP[skill] > 200000000) {
			if (c.playerXP[skill] > 200000000) {
				c.playerXP[skill] = 200000000;
			}
			return false;
		}

	if (c.playerXP[0] > 199999999 && c.THMATT == 0) {
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                   Player c2 = (Player)Server.playerHandler.players[j];
          c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just reached 200M Attack XP!");
			c.THMATT = 1;
                    }
               }
        }
	if (c.playerXP[1] > 199999999 && c.THMDEF == 0) {
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                   Player c2 = (Player)Server.playerHandler.players[j];
          c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just reached 200M Defence XP!");
			c.THMDEF = 1;
                    }
               }
        }  
	if (c.playerXP[2] > 199999999 && c.THMSTR == 0) {
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                   Player c2 = (Player)Server.playerHandler.players[j];
          c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just reached 200M Strength XP!");
			c.THMSTR = 1;
                    }
               }
        }
	if (c.playerXP[3] > 199999999 && c.THMHP == 0) {
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                   Player c2 = (Player)Server.playerHandler.players[j];
          c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just reached 200M Hitpoints XP!");
			c.THMHP = 1;
                    }
               }
        }
	if (c.playerXP[4] > 199999999 && c.THMRANG == 0) {
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                   Player c2 = (Player)Server.playerHandler.players[j];
          c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just reached 200M Ranging XP!");
			c.THMRANG = 1;
                    }
               }
        }
	if (c.playerXP[5] > 199999999 && c.THMPRAY == 0) {
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                   Player c2 = (Player)Server.playerHandler.players[j];
          c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just reached 200M Prayer XP!");
			c.THMPRAY = 1;
                    }
               }
        }
	if (c.playerXP[6] > 199999999 && c.THMMAGE == 0) {
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                   Player c2 = (Player)Server.playerHandler.players[j];
          c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just reached 200M Magic XP!");
			c.THMMAGE = 1;
                    }
               }
        }

		if (c.playerXP[skill] > 199999999 && c.xpMaster == 0) {
			AchievementManager.increase(c, Achievements.XP_MASTER);
			c.xpMaster = 1;
		}
		if (c.xpLock == true) {
   			 return false;
		}
		if (c.playerEquipment[c.playerRing] == 19771 || rewardBook()) {
			amount *= Config.SERVER_EXP_BONUS * 2;
		} else {
			amount *= Config.SERVER_EXP_BONUS;
		}
		amount *= c.inDonatorZone() ? 2 : 1;
		final int oldLevel = getLevelForXP(c.playerXP[skill]);
		final int oldLevel1 = getLevelForDungXP(c.playerXP[23]);
		c.playerXP[skill] += amount;
		if (skill != 23) {
			if (oldLevel < getLevelForXP(c.playerXP[skill])) {
				if (c.playerLevel[skill] < c.getLevelForXP(c.playerXP[skill])
						&& skill != 3) {
					c.playerLevel[skill] = c.getLevelForXP(c.playerXP[skill]);
				}
				levelUp(skill);
				c.gfx100(199);
				requestUpdates();
			}
		} else {
			if (oldLevel1 < getLevelForDungXP(c.playerXP[23])) {
				if (c.playerLevel[23] < getLevelForDungXP(c.playerXP[23])) {
					c.playerLevel[23] = getLevelForDungXP(c.playerXP[23]);
				}
				levelUp(23);
				c.gfx100(199);
				requestUpdates();
			}
		}
		setSkillLevel(skill, c.playerLevel[skill], c.playerXP[skill]);
		refreshSkill(skill);
		return true;
	}

	/**
	 * Summoning Stat Bonus Methods
	 */

	public int getDefBoost() {
		int increaseBy = 0;
		increaseBy = c.getLevelForXP(c.playerXP[1]) + 4;
		if (c.playerLevel[1] + increaseBy > c.getLevelForXP(c.playerLevel[1])
				+ increaseBy + 1) {
			return c.getLevelForXP(c.playerXP[1]) + increaseBy
					- c.playerLevel[1];
		}
		c.getPA().refreshSkill(1);
		return increaseBy;
	}

	//public long doubleXP = -1;
	public boolean rewardBook() {
		if (c.doubleXP == -1)
			return false;
		else
			return (System.currentTimeMillis() - c.doubleXP <= 3600000);
	}
	public String getTimeLeftForDX() {
		long millis = System.currentTimeMillis() - c.doubleXP;
		int minutes = (int) ((millis/ 1000) / 60);
		return "You have <col=255>"+(60-minutes)+"</col> minutes left on <col=255>Double XP!</col>";
	}
	public String getTimeLeftForDouble() {
		long millis = System.currentTimeMillis() - c.doubleXP;
		int minutes = (int) ((millis/ 1000) / 60);
		return ""+(60-minutes)+" minutes left.";
	}

	public void addStarter() {
		if (!Connection
				.hasRecieved1stStarter(PlayerHandler.players[c.playerId].connectedFrom)) {
			//c.getPA().showInterface(3559);
			//c.canChangeAppearance = true;
			c.getItems().addItem(995, 500000);
			c.getItems().addItem(556, 300);
			c.getItems().addItem(558, 300);
			c.getItems().addItem(559, 300);
			c.getItems().addItem(1323, 1);
			c.getItems().addItem(1333, 1);
			c.getItems().addItem(841, 1);
			c.getItems().addItem(882, 300);
			c.getItems().addItem(7947, 1000);
			c.getItems().addItem(1067, 1);
			c.getItems().addItem(1115, 1);
			c.getItems().addItem(1153, 1);
			c.getItems().addItem(1731, 1);
			c.getItems().addItem(3105, 1);
			c.getItems().addItem(11126, 1);
			c.getItems().addItem(4411, 1);
			Connection
			.addIpToStarterList1(PlayerHandler.players[c.playerId].connectedFrom);
			Connection
			.addIpToStarter1(PlayerHandler.players[c.playerId].connectedFrom);
			c.sendMessage("You have recieved 1 out of 2 starter packages on this IP address.");
		} else if (Connection
				.hasRecieved1stStarter(PlayerHandler.players[c.playerId].connectedFrom)
				&& !Connection
				.hasRecieved2ndStarter(PlayerHandler.players[c.playerId].connectedFrom)) {
			//c.getPA().showInterface(3559);
			//c.canChangeAppearance = true;
			c.getItems().addItem(995, 500000);
			c.getItems().addItem(556, 300);
			c.getItems().addItem(558, 300);
			c.getItems().addItem(559, 300);
			c.getItems().addItem(1323, 1);
			c.getItems().addItem(1333, 1);
			c.getItems().addItem(841, 1);
			c.getItems().addItem(882, 300);
			c.getItems().addItem(7947, 1000);
			c.getItems().addItem(1067, 1);
			c.getItems().addItem(1115, 1);
			c.getItems().addItem(1153, 1);
			c.getItems().addItem(1731, 1);
			c.getItems().addItem(3105, 1);
			c.getItems().addItem(11126, 1);
			c.getItems().addItem(4411, 1);
			c.sendMessage("You have recieved 2 out of 2 starter packages on this IP address.");
			Connection
			.addIpToStarterList2(PlayerHandler.players[c.playerId].connectedFrom);
			Connection
			.addIpToStarter2(PlayerHandler.players[c.playerId].connectedFrom);
		} else if (Connection
				.hasRecieved1stStarter(PlayerHandler.players[c.playerId].connectedFrom)
				&& Connection
				.hasRecieved2ndStarter(PlayerHandler.players[c.playerId].connectedFrom)) {
			c.sendMessage("You have already recieved 2 starters!");
		}

	}

	public void giveBetaAccount() {
		int items[] = {1079, 1127, 10828, 4587,
				6524, 1712, 3105, 6568, 11118, 2489,
				2495, 2501, 2491, 2497, 2503, 3749, 15126, 861, 
				892, 9185, 9144, 10499, 6328, 7398, 7399,
				7400, 6914, 2579, 6889, 18334, 554, 555, 556, 557, 
				558, 559, 560, 561, 562, 563, 564, 565, 1215, 1434, 385, 
				157, 145, 163, 169, 3024, 6685, 2347, 1755, 301, 311, 
				10010, 10012, 1359, 1275, 15707, 1436, 4155, 946, 
				1515, 1519, 227, 207, 231, 5295, 5343, 952, 5340, 
				590, 2361, 440, 2363, 1733, 1734, 12155, 15262, 
				12158, 12160, 12163, 8007, 8008, 8009, 8010, 8011, 3853};
		for(int i = 0; i < items.length; i++) {
			c.getItems().addItemToBank(items[i], 1000);
		}
		c.sendMessage("Testing skills added to bank: successful!");
		for (int i = 0; i < 24; i++) {
			c.playerLevel[i] = 75;
			c.playerXP[i] = c.getPA().getXPForLevel(76);
			c.getPA().refreshSkill(i);    
			c.getPA().requestUpdates();
		}
		c.sendMessage("All stats 75: successful!");
	}

	public double getAgilityRunRestore() {
		return 3520 - (c.playerLevel[16] * 10);
	}

	public double getAgilityRunDrain() {
		return 700 + (c.playerLevel[16] * 4.5);
	}

	public void Frame34(int frame, int item, int slot, int amount) {
		if (c.getOutStream() != null && c != null) {
			c.outStream.createFrameVarSizeWord(34);
			c.outStream.writeWord(frame);
			c.outStream.writeByte(slot);
			c.outStream.writeWord(item + 1);
			c.outStream.writeByte(255);
			c.outStream.writeDWord(amount);
			c.outStream.endFrameVarSizeWord();
		}

	}

	public int antiFire() {
		int toReturn = 0;
		if (c.antiFirePot) {
			toReturn++;
		}
		if (c.playerEquipment[c.playerShield] == 1540 || c.prayerActive[16]
				|| c.playerEquipment[c.playerShield] == 11284
				|| c.playerEquipment[c.playerShield] == 11283) {
			toReturn++;
		}
		if (c.playerEquipment[c.playerShield] == 1540 || c.prayerActive[16]
				|| c.playerEquipment[c.playerShield] == 11284
				|| c.playerEquipment[c.playerShield] == 11283) {
			toReturn++;
		}
		if (c.playerEquipment[c.playerShield] == 1540 || c.prayerActive[16]
				|| c.playerEquipment[c.playerShield] == 11284
				|| c.playerEquipment[c.playerShield] == 11283) {
			toReturn++;
		}
		return toReturn;
	}

	public int totalLevel() {
		int total = 0;
		for (int i = 0; i <= 22; i++) {
			total += getLevelForXP(c.playerXP[i]);
		}
		total += getLevelForDungXP(c.playerXP[23]);
		return total;
	}

	public int xpTotal() {
		int xp = 0;
		for (int i = 0; i <= 23; i++) {
			xp += c.playerXP[i];
		}
		return xp;
	}

	public void destroyInterface1(int itemId) {
		itemId = c.droppedItem;
		String itemName = c.getItems().getItemName(c.droppedItem);
		String[][] info = {
				{ "Are you sure you want to drop this item?", "14174" },
				{ "Yes.", "14175" }, { "No.", "14176" }, { "", "14177" },
				{ "This item will no longer be binded to you", "14182" },
				{ "after you destroy this ancient item.", "14183" },
				{ itemName, "14184" } };// make some kind of c.getItemInfo
		sendFrame34(itemId, 0, 14171, 1);
		for (int i = 0; i < info.length; i++)
			sendFrame126(info[i][0], Integer.parseInt(info[i][1]));
		sendFrame164(14170);
	}

	public void destroyItem1(int itemId) {
		itemId = c.droppedItem;
		String itemName = c.getItems().getItemName(itemId);
		c.getItems().deleteItem(itemId, c.getItems().getItemSlot(itemId),
				c.playerItemsN[c.getItems().getItemSlot(itemId)]);
		c.sendMessage("Your " + itemName
				+ " vanishes as you drop it on the ground.");
		removeAllWindows();
		if (c.bind1 == c.droppedItem) {
			c.bind1 = -1;
			return;
		} else if (c.bind2 == c.droppedItem) {
			c.bind2 = -1;
			return;
		} else if (c.bind3 == c.droppedItem) {
			c.bind3 = -1;
			return;
		} else if (c.bind4 == c.droppedItem) {
			c.bind4 = -1;
			return;
		}
	}

	public void destroyInterface(int itemId) {
		itemId = c.droppedItem;
		String itemName = c.getItems().getItemName(c.droppedItem);
		String[][] info = {
				{ "Are you sure you want to drop this item?", "14174" },
				{ "Yes.", "14175" }, { "No.", "14176" }, { "", "14177" },
				{ "This item is very expensive and if you drop it", "14182" },
				{ "you might forget to pick it up and leave it.", "14183" },
				{ itemName, "14184" } };// make some kind of c.getItemInfo
		sendFrame34(itemId, 0, 14171, 1);
		for (int i = 0; i < info.length; i++)
			sendFrame126(info[i][0], Integer.parseInt(info[i][1]));
		sendFrame164(14170);
	}

	public void appendPoison(final int damage) {

	}

	public void removeAllItems() {
		for (int i = 0; i < c.playerItems.length; i++) {
			c.playerItems[i] = 0;
		}
		for (int i = 0; i < c.playerItemsN.length; i++) {
			c.playerItemsN[i] = 0;
		}
		c.getItems().resetItems(3214);
	}

	public void removeEquipment() {
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] > 0 && c.playerEquipmentN[i] > 0) {
				c.getItems().deleteItem(c.playerEquipment[i],
						c.playerEquipmentN[i]);
			}
			c.getItems().replaceEquipment(i, -1);
		}
			c.getItems().addItem(15707, 1);
		c.getItems().resetAnims();
	}

	public void destroyItem(int itemId) {
		itemId = c.droppedItem;
		String itemName = c.getItems().getItemName(itemId);
		c.getItems().deleteItem(itemId, c.getItems().getItemSlot(itemId),
				c.playerItemsN[c.getItems().getItemSlot(itemId)]);
		c.sendMessage("Your " + itemName
				+ " vanishes as you drop it on the ground.");
		removeAllWindows();
		c.itemsBounded--;
	}

	public void dropItem(int itemId) {
		itemId = c.droppedItem;
		Server.itemHandler.createGroundItem(c, itemId, c.absX, c.absY,
				c.playerItemsN[c.getItems().getItemSlot(itemId)], c.getId());
		c.getItems().deleteItem(itemId, c.getItems().getItemSlot(itemId),
				c.playerItemsN[c.getItems().getItemSlot(itemId)]);
		removeAllWindows();
	}

	/**
	 * Beast of Burden
	 **/

	public void refreshBoB() {
		for (int k = 0; k < 30; k++) {
			if (c.bobItems[k] > 0)
				c.bobItemsN[k] = 1;
			if (c.bobItems[k] > 0) {
				Frame34(2702, c.bobItems[k], k, c.bobItemsN[k]);
			}
			if (c.bobItems[k] <= 0) {
				Frame34(2702, -1, k, c.bobItemsN[k]);
			}
		}
		c.getItems().resetItems(5064);
	}

	/**
	 * Shifts and re-arranges the familiar's inventory.
	 */

	public void refreshBoB(int index) {
		c.getPA().Frame34(2702,
				c.bobItems[index] > 0 ? c.bobItemsN[index] : -1, index,
						c.bobItemsN[index] > 0 ? 1 : -1);
		c.getItems().resetItems(5064);
	}

	public void removeBoBItems(int slot, int amount) {
		if (c.getItems().freeSlots() > 0) {
			c.getItems().addItem(c.bobItems[slot], amount);
			c.bobItemsN[slot] = 0;
			c.bobItems[slot] = 0;
			c.getItems().resetTempItems();
			c.getItems().resetBank();
			refreshBoB(slot);
			c.totalstored -= slot;
		} else {
			c.getItems().resetTempItems();
			c.getItems().resetBank();
			c.sendMessage("Not enough space in your inventory.");
			return;
		}
	}

	public void removeBoBItemsToBank(int slot, int amount) {
		if (c.getItems().freeSlots() > 0) {
			c.getItems().addItemToBank(c.bobItems[slot], amount);
			c.bobItemsN[slot] = 0;
			c.bobItems[slot] = 0;
			c.getItems().resetTempItems();
			c.getItems().resetBank();
			refreshBoB(slot);
			c.totalstored -= slot;
		} else {
			c.getItems().resetTempItems();
			c.getItems().resetBank();
			c.sendMessage("Not enough space in your bank.");
			return;
		}
	}

	public void takeFromBoB(int slot, int amount) {
		int itemId = c.bobItems[slot];
		int invSlotCount = c.getItems().freeSlots();
		if (amount > invSlotCount)
			amount = invSlotCount;
		if (invSlotCount < 1) {
			c.sendMessage("Not enough inventory space.");
			return;
		}
		bobWithdrawLoop(itemId, amount);
		refreshBoB();
	}

	public void dropitems() {
		if (c.familiarID > 0) {
			for (int i = 0; i < 30; i += 1) {
				Server.itemHandler.createGroundItem(c, c.bobItems[i], c.absX,
						c.absY, 1, c.playerId);
				c.bobItems[i] = -1;
				refreshBoB(i);

			}
			c.familiarID = -1;
			c.sendMessage("Your BoB items have drop on the floor");
		}
	}

	private void bobWithdrawLoop(int itemId, int amount) {
		int inventoryAmount = 0;
		for (int i = 0; i < c.bobItems.length; i++) {
			if (c.bobItems[i] == itemId) {
				inventoryAmount++;
				if (inventoryAmount <= amount) {
					c.getItems().addItem(itemId, c.bobItemsN[i]);
					c.bobItems[i] = 0;
					c.bobItemsN[i] = 0;
				}
			}
		}
	}

	public void addToBoB(int slot, int amount) {
		int itemId = c.playerItems[slot] - 1;
		int invItemCount = c.getItems().getItemCount(itemId);
		if (itemId == -1) {
			return;
		}
		if (Item.itemStackable[itemId]) {
			c.sendMessage("You cannot store stackable items!");
			return;
		}
		if (c.getQuest().questItem(itemId)) {
			c.sendMessage("You cannot store this item.");
			return;
		}
		if (amount > openSlots())
			amount = openSlots();
		if (amount > invItemCount)
			amount = invItemCount;
		if (!bobSlotsAvailable()) {
			c.sendMessage("Your familiar's load is too heavy to add more items!");
			return;
		}
		bobDepositLoop(itemId, amount);
		refreshBoB();
	}

	private void bobDepositLoop(int itemId, int amount) {
		int inventoryAmount = 0;
		for (int i = 0; i < c.bobSlotCount; i++) {
			if (c.bobItemsN[i] <= 0) {
				inventoryAmount++;
				if (inventoryAmount <= amount) {
					c.getItems().deleteItem(itemId,
							c.getItems().getItemSlot(itemId), 1);
					c.bobItems[i] = itemId;
					c.bobItemsN[i] = itemId;
				}
			}
		}
	}

	public boolean bobSlotsAvailable() {
		for (int i = 0; i < c.bobSlotCount; i++) {
			if (c.bobItemsN[i] <= 0)
				return true;
		}
		return false;
	}

	public int openSlots() {
		int slotCount = 0;
		for (int i = 0; i < c.bobSlotCount; i++) {
			if (c.bobItemsN[i] <= 0)
				slotCount++;
		}
		return slotCount;
	}

	/**
	 * End of Beast of Burden
	 **/

	/**
	 * Dieing
	 **/
	public void applyDead() {
		c.respawnTimer = 15;
		c.isDead = false;
		if (c.duelStatus != 6) {
			// c.killerId = c.getCombat().getKillerId(c.playerId);
			c.killerId = findKiller();
			final Player o = PlayerHandler.players[c.killerId];
			if (o != null) {
				if (c.killerId != c.playerId) {
					o.sendMessage(this.killMessages());
					c.currentStreak = 0;
					o.currentStreak += 1;
					o.kills += 1;
					c.deaths += 1;
					//c.getItems().dropAllItemsPVP(); // PvP drops
					if(o.currentStreak >= 5)
						o.getPA().sendYell(1, 255, o.playerName , this.killStreakMessages());
					if(o.currentStreak > o.highestStreak) {
						o.sendMessage("Congratulations, your Highest killing streak has increased!");								
						o.highestStreak = o.currentStreak;
					}
					if(!AntiPKFarming.hostOnList(o, c.connectedFrom)) {
						AntiPKFarming.addHostToList(o, c.connectedFrom);
						if (c.currentStreak >= 5 && c.currentStreak <= 10) {
							o.pkPoints += 4;
							o.sendMessage("You received 4 PK Points because you're really on a Kill Streak!");
						} else if (c.currentStreak >= 10) {
							o.pkPoints += 6;
							o.sendMessage("You received 6 PK Points because you're unstoppable!");
						} else {
							o.pkPoints += 2;
							o.sendMessage("You received 2 PK Points for defeating "+ Misc.optimizeText(c.playerName) +".");
						}
					} else {
						o.sendMessage("You've recently fought "+ Misc.optimizeText(c.playerName) +", so you didn't receive any PK Points");
					}
				}
				if (BountyHunter.inBH(c)) {
					deadAtBounty(o);
				}
				c.isPVPActive = false;
				if (c.inWeaponGame()) {
					WeaponGame.handleKill(o);
				}
				if (o.duelStatus == 5) {
					o.duelStatus++;
				}
			}
		}
		if (c.npcIndex > 0) {
			NPCHandler.npcs[c.npcIndex].killerId = 0;
			NPCHandler.npcs[c.npcIndex].underAttack = false;
		}
		c.faceUpdate(0);
		c.npcIndex = 0;
		c.playerIndex = 0;
		c.stopMovement();
		if (c.duelStatus <= 4) {
			c.sendMessage("Oh dear you are dead!");
		c.currentStreak = 0;
			//c.sendMessage(this.deathMessages());
		} else if (c.duelStatus != 6) {
			c.sendMessage("You have lost the duel!");
			PlayerSave.saveGame(c);
		}
		if (c.familiarID > 0)
			c.getPA().sendFrame126(":follower:off", -1);
		Summoning.dismissFamiliar(c, true, true);
		resetDamageDone();
		c.specAmount = 10;
		c.getItems().addSpecialBar(c.playerEquipment[c.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		if (c.curses) {
			c.getCurse().resetCurse();
		}
		resetFollowers();
		c.attackTimer = 10;
		removeAllWindows();
		c.tradeResetNeeded = true;
		c.getPA().sendFrame126(":quicks:off", -1);
	}

	public String killMessages() {
		int killMsgs = Misc.random(9);
		switch(killMsgs) {
		case 0: 
			return "<col=250>With a crushing blow, you defeat "+Misc.optimizeText(c.playerName)+".";
		case 1: 
			return "<col=250>It's a humiliating defeat for "+Misc.optimizeText(c.playerName)+".";
		case 2: 
			return "<col=250>"+Misc.optimizeText(c.playerName)+" didn't stand a chance against you.";
		case 3: 
			return "<col=250>You've defeated "+Misc.optimizeText(c.playerName)+".";
		case 4: 
			return "<col=250>"+Misc.optimizeText(c.playerName)+" regrets the day they met you in combat.";
		case 5: 
			return "<col=250>It's all over for "+Misc.optimizeText(c.playerName)+".";
		case 6: 
			return "<col=250>"+Misc.optimizeText(c.playerName)+" falls before your might.";
		case 7: 
			return "<col=250>Can anyone defeat you? Certainly not "+Misc.optimizeText(c.playerName)+".";
		case 8: 
			return "<col=250>You were clearly a better fighter than "+Misc.optimizeText(c.playerName)+".";
		}
		return optionType;
	}

	public String deathMessages() {
		Player o = (Player) PlayerHandler.players[c.killerId];
		int deathMsgs = Misc.random(5);
		switch(deathMsgs) {
		case 0: 
			return "<col=250>Oh dear, you were defeated by "+Misc.optimizeText(o.playerName)+".";
		case 1: 
			return "<col=250>Maybe one day you'll stand a chance against "+Misc.optimizeText(o.playerName)+".";
		case 2: 
			return "<col=250>"+Misc.optimizeText(o.playerName)+" has shown you no mercy!";
		case 3: 
			return "<col=250>You bowed down to "+Misc.optimizeText(o.playerName)+"!";
		case 4: 
			return "<col=250>Your defeat to "+Misc.optimizeText(o.playerName)+" will forever scar you.";
		}
		return optionType;
	}

	public String killStreakMessages() {
		Player o = (Player) PlayerHandler.players[c.killerId];
		int streakMsgs = Misc.random(5);
		switch(streakMsgs) {
		case 0: 
			return "is on a Kill Streak of " + o.currentStreak + "!";
		case 1: 
			return "killed "+ Misc.optimizeText(c.playerName) +" and is on a Kill Streak of "+ o.currentStreak +"!";
		case 2: 
			return "is Unstoppable because they're on a "+ o.currentStreak +" Kill Streak!";
		case 3: 
			return "is currently Undefeated with a Kill Streak of "+ o.currentStreak +"!";
		case 4: 
			return "is on a "+ c.currentStreak +" Kill Streak after wiping the floor with "+ Misc.optimizeText(c.playerName) +"";
		}
		return optionType;
	}

	public void sendYell(int colorCode, int color, String tag, String data) {
		String colorType = "col";
		int image = -1;
		@SuppressWarnings("unused")
		String playertag = "";
		if (colorCode == 1)
			colorType = "col";
		if (colorCode == 2)
			colorType = "shad";
		if (c.playerRights == 0)
			image = -1;
		if (c.playerRights == 1)
			image = 0;
		if (c.playerRights == 2 || c.playerRights == 3)
			image = 1;
		if (c.playerName.equalsIgnoreCase("jlyons") || c.playerName.equalsIgnoreCase("lawless") || c.playerName.equalsIgnoreCase("luis")
				|| c.playerName.equalsIgnoreCase("jake"))
			image = 1;
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player c2 = (Player) PlayerHandler.players[j];
				c2.sendMessage("<" + colorType + "=" + color + "><img=" + image
						+ ">[Server]"
						+ Misc.optimizeText(c.playerName) + ": "
						+ Misc.optimizeText(data) + "");
			}
		}
	}

	public void deadAtBounty(Player o) {
		if (c.playerTarget == o.playerId && c.playerId != o.playerTarget) {
			o.sendMessage("You killed " + Misc.optimizeText(c.playerName) + ". He was hunting you!");
			BountyHunter.resetFlags(c);
			o.playerTarget = 0;
			BountyHunter.decreaseEnterTime(c, 200);
		} else if (c.playerId == o.playerTarget) {
			o.sendMessage("You killed your target! 5 million gold has been placed in your bank!");
			o.getPA().createPlayerHints(-1, -1);
			BountyHunter.resetFlags(c);
			o.playerTarget = 0;
			BountyHunter.decreaseEnterTime(c, 200);
			BountyHunter.handleNewTarget(o);
			o.getItems().addItemToBank(995, 5000000);
			BountyHunter.resetFlags(c);
			o.playerTarget = 0;
			BountyHunter.decreaseEnterTime(c, 200);
			BountyHunter.handleNewTarget(o);
		} else {
			BountyHunter.handlePickupPenalty(o);
			o.sendMessage("You killed " + Misc.optimizeText(c.playerName) + ". He was not your target, so your Rogue PvP rating increases!");
			o.sendMessage("This means you get the pick-up penalty; pick anything up and you can't leave!");
			BountyHunter.decreaseEnterTime(c, 200);
			BountyHunter.resetFlags(c);
			Player target = (Player) PlayerHandler.players[c.playerTarget];
			BountyHunter.handleNewTarget(target);
		}
	}

	/**
	 * Vengeance
	 */
	public void castVeng() {
		if (c.playerLevel[6] < 94) {
			c.sendMessage("You need a magic level of 94 to cast this spell.");
			return;
		}
		if (c.playerLevel[1] < 40) {
			c.sendMessage("You need a defence level of 40 to cast this spell.");
			return;
		}
		if (!c.getItems().playerHasItem(9075, 4)
				|| !c.getItems().playerHasItem(557, 10)
				|| !c.getItems().playerHasItem(560, 2)) {
			c.sendMessage("You don't have the required runes to cast this spell.");
			return;
		}
		if (System.currentTimeMillis() - c.lastCast < 30000) {
			c.sendMessage("You can only cast vengeance every 30 seconds.");
			return;
		}
		if (c.vengOn) {
			c.sendMessage("You already have vengeance casted.");
			return;
		}
		//c.setAnimation(Animation.create(4410));
		c.startAnimation(4410);
		c.gfx100(726);
		c.getItems().deleteItem2(9075, 4);
		c.getItems().deleteItem2(557, 10);// For these you need to change to
		// deleteItem(item, itemslot,
		// amount);.
		c.getItems().deleteItem2(560, 2);
		addSkillXP(112, 6);
		refreshSkill(6);
		c.vengOn = true;
		c.lastCast = System.currentTimeMillis();
	}

	/**
	 * Location change for digging, levers etc
	 **/
	public void changeLocation() {
		switch (c.newLocation) {
		case 1:
			movePlayer(3578, 9706, -1);
			break;
		case 2:
			movePlayer(3568, 9683, -1);
			break;
		case 3:
			movePlayer(3557, 9703, -1);
			break;
		case 4:
			movePlayer(3556, 9718, -1);
			break;
		case 5:
			movePlayer(3534, 9704, -1);
			break;
		case 6:
			movePlayer(3546, 9684, -1);
			break;
		}
		c.newLocation = 0;
	}

	public boolean checkForFlags() {
		final int[][] itemsToCheck = { { 995, 100000000 }, { 35, 5 },
				{ 667, 5 }, { 2402, 5 }, { 746, 5 }, { 4151, 150 },
				{ 565, 100000 }, { 560, 100000 }, { 555, 300000 },
				{ 11235, 10 }, { 15701, 10 }, { 15702, 10 }, { 15703, 10 },
				{ 15704, 10 } };
		for (final int[] element : itemsToCheck) {
			if (element[1] < c.getItems().getTotalCount(element[0])) {
				return true;
			}
		}
		return false;
	}

	public boolean checkForPlayer(final int x, final int y) {
		for (final Player p : PlayerHandler.players) {
			if (p != null) {
				if (p.getX() == x && p.getY() == y) {
					return true;
				}
			}
		}
		return false;
	}

	public void checkObjectSpawn(int objectId, int objectX, int objectY,
			int face, int objectType) {
		if (c.distanceToPoint(objectX, objectY) > 60)
			return;
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(objectY - (c.getMapRegionY() * 8));
			c.getOutStream().writeByteC(objectX - (c.getMapRegionX() * 8));
			c.getOutStream().createFrame(101);
			c.getOutStream().writeByteC((objectType << 2) + (face & 3));
			c.getOutStream().writeByte(0);

			if (objectId != -1) { // removing
				c.getOutStream().createFrame(151);
				c.getOutStream().writeByteS(0);
				c.getOutStream().writeWordBigEndian(objectId);
				c.getOutStream().writeByteS((objectType << 2) + (face & 3));
			}
			c.flushOutStream();
		}
	}

	public void checkPouch(final int i) {
		if (i < 0) {
			return;
		}
		c.sendMessage("This pouch has " + c.pouches[i] + " rune ess in it.");
	}

	public void clearClanChat() {
		c.clanId = -1;
		c.getPA().sendFrame126("Talking in: ", 18139);
		c.getPA().sendFrame126("Owner: ", 18140);
		for (int j = 18144; j < 18244; j++) {
			c.getPA().sendFrame126("", j);
		}
	}

	public void closeAllWindows() {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(219);
			c.flushOutStream();
			c.isBanking = false;
			c.usingBoB = false;
		}
		// }
	}

	public void createObjectHints(final int x, final int y, final int height,
			final int pos) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(254);
			c.getOutStream().writeByte(pos);
			c.getOutStream().writeWord(x);
			c.getOutStream().writeWord(y);
			c.getOutStream().writeByte(height);
			c.flushOutStream();
		}
		// }
	}

	public void createPlayerHints(final int type, final int id) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(254);
			c.getOutStream().writeByte(type);
			c.getOutStream().writeWord(id);
			c.getOutStream().write3Byte(0);
			c.flushOutStream();
		}
		// }
	}

	// projectiles for everyone within 25 squares
	public void createPlayersProjectile(final int x, final int y,
			final int offX, final int offY, final int angle, final int speed,
			final int gfxMoving, final int startHeight, final int endHeight,
			final int lockon, final int time) {
		// synchronized(c) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			final Player p = PlayerHandler.players[i];
			if (p != null) {
				final Player person = p;
				if (person != null) {
					if (person.getOutStream() != null) {
						if (person.distanceToPoint(x, y) <= 25) {
							if (c.heightLevel == c.heightLevel + 0) {
								person.getPA().createProjectile(x, y, offX,
										offY, angle, speed, gfxMoving,
										startHeight, endHeight, lockon, time);
							}
						}
					}
				}
			}
		}
		// }
	}

	public void createPlayersProjectile2(final int x, final int y,
			final int offX, final int offY, final int angle, final int speed,
			final int gfxMoving, final int startHeight, final int endHeight,
			final int lockon, final int time, final int slope) {
		// synchronized(c) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			final Player p = PlayerHandler.players[i];
			if (p != null) {
				final Player person = p;
				if (person != null) {
					if (person.getOutStream() != null) {
						if (person.distanceToPoint(x, y) <= 25) {
							person.getPA().createProjectile2(x, y, offX, offY,
									angle, speed, gfxMoving, startHeight,
									endHeight, lockon, time, slope);
						}
					}
				}
			}
		}
		// }
	}

	// creates gfx for everyone
	public void createPlayersStillGfx(final int id, final int x, final int y,
			final int height, final int time) {
		// synchronized(c) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			final Player p = PlayerHandler.players[i];
			if (p != null) {
				final Player person = p;
				if (person != null) {
					if (person.getOutStream() != null) {
						if (person.distanceToPoint(x, y) <= 25) {
							person.getPA().stillGfx(id, x, y, height, time);
						}
					}
				}
			}
		}
		// }
	}

	/**
	 * Creating projectile
	 **/
	public void createProjectile(final int x, final int y, final int offX,
			final int offY, final int angle, final int speed,
			final int gfxMoving, final int startHeight, final int endHeight,
			final int lockon, final int time) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(y - c.getMapRegionY() * 8 - 2);
			c.getOutStream().writeByteC(x - c.getMapRegionX() * 8 - 3);
			c.getOutStream().createFrame(117);
			c.getOutStream().writeByte(angle);
			c.getOutStream().writeByte(offY);
			c.getOutStream().writeByte(offX);
			c.getOutStream().writeWord(lockon);
			c.getOutStream().writeWord(gfxMoving);
			c.getOutStream().writeByte(startHeight);
			c.getOutStream().writeByte(endHeight);
			c.getOutStream().writeWord(time);
			c.getOutStream().writeWord(speed);
			c.getOutStream().writeByte(16);
			c.getOutStream().writeByte(64);
			c.flushOutStream();
		}
		// }
	}

	public void createProjectile2(final int x, final int y, final int offX,
			final int offY, final int angle, final int speed,
			final int gfxMoving, final int startHeight, final int endHeight,
			final int lockon, final int time, final int slope) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(y - c.getMapRegionY() * 8 - 2);
			c.getOutStream().writeByteC(x - c.getMapRegionX() * 8 - 3);
			c.getOutStream().createFrame(117);
			c.getOutStream().writeByte(angle);
			c.getOutStream().writeByte(offY);
			c.getOutStream().writeByte(offX);
			c.getOutStream().writeWord(lockon);
			c.getOutStream().writeWord(gfxMoving);
			c.getOutStream().writeByte(startHeight);
			c.getOutStream().writeByte(endHeight);
			c.getOutStream().writeWord(time);
			c.getOutStream().writeWord(speed);
			c.getOutStream().writeByte(slope);
			c.getOutStream().writeByte(64);
			c.flushOutStream();
		}
		// }
	}

	/**
	 * Ladder Handeling
	 **/

	public void moveAction(final int x, final int y, final int h,
			final String type) {
		if (type.equals("up")) {
			//c.setAnimation(Animation.create(828));
			c.startAnimation(828);
		} else if (type.equals("down")) {
			//c.setAnimation(Animation.create(827));
			c.startAnimation(827);
		}
		EventManager.getSingleton().addEvent(new Event() {
			@Override
			public void execute(EventContainer p) {
				c.resetWalkingQueue();
				c.teleportToX = x;
				c.teleportToY = y;
				c.heightLevel = h;
				c.getPA().requestUpdates();
				p.stop();
			}
		}, 600);
	}

	/**
	 * Show an arrow icon on the selected player.
	 * 
	 * @Param i - Either 0 or 1; 1 is arrow, 0 is none.
	 * @Param j - The player/Npc that the arrow will be displayed above.
	 * @Param k - Keep this set as 0
	 * @Param l - Keep this set as 0
	 */
	public void drawHeadicon(final int i, final int j, final int k, final int l) {
		// synchronized(c) {
		c.outStream.createFrame(254);
		c.outStream.writeByte(i);

		if (i == 1 || i == 10) {
			c.outStream.writeWord(j);
			c.outStream.writeWord(k);
			c.outStream.writeByte(l);
		} else {
			c.outStream.writeWord(k);
			c.outStream.writeWord(l);
			c.outStream.writeByte(j);
		}
		// }
	}

	public void emptyPouch(final int i) {
		if (i < 0) {
			return;
		}
		int toAdd = c.pouches[i];
		if (toAdd > c.getItems().freeSlots()) {
			toAdd = c.getItems().freeSlots();
		}
		if (toAdd > 0) {
			c.getItems().addItem(1436, toAdd);
			c.pouches[i] -= toAdd;
		}
	}

	public void fillPouch(final int i) {
		if (i < 0) {
			return;
		}
		int toAdd = c.POUCH_SIZE[i] - c.pouches[i];
		if (toAdd > c.getItems().getItemAmount(1436)) {
			toAdd = c.getItems().getItemAmount(1436);
		}
		if (toAdd > c.POUCH_SIZE[i] - c.pouches[i]) {
			toAdd = c.POUCH_SIZE[i] - c.pouches[i];
		}
		if (toAdd > 0) {
			c.getItems().deleteItem(1436, toAdd);
			c.pouches[i] += toAdd;
		}
	}

	public int findKiller() {
		int killer = c.playerId;
		int damage = 0;
		for (int j = 0; j < Config.MAX_PLAYERS; j++) {
			if (PlayerHandler.players[j] == null) {
				continue;
			}
			if (j == c.playerId) {
				continue;
			}
			if (c.goodDistance(c.absX, c.absY, PlayerHandler.players[j].absX,
					PlayerHandler.players[j].absY, 40)
					|| c.goodDistance(c.absX, c.absY + 9400,
							PlayerHandler.players[j].absX,
							PlayerHandler.players[j].absY, 40)
							|| c.goodDistance(c.absX, c.absY,
									PlayerHandler.players[j].absX,
									PlayerHandler.players[j].absY + 9400, 40)) {
				if (c.damageTaken[j] > damage) {
					damage = c.damageTaken[j];
					killer = j;
				}
			}
		}
		return killer;
	}

	public void fixAllBarrows() {
		int totalCost = 0;
		final int cashAmount = c.getItems().getItemAmount(995);
		for (int j = 0; j < c.playerItems.length; j++) {
			boolean breakOut = false;
			for (final int[] brokenBarrow : c.getItems().brokenBarrows) {
				if (c.playerItems[j] - 1 == brokenBarrow[1]) {
					if (totalCost + 80000 > cashAmount) {
						breakOut = true;
						c.sendMessage("You have run out of money.");
						break;
					} else {
						totalCost += 80000;
					}
					c.playerItems[j] = brokenBarrow[0] + 1;
				}
			}
			if (breakOut) {
				break;
			}
		}
		if (totalCost > 0) {
			c.getItems().deleteItem(995, c.getItems().getItemSlot(995),
					totalCost);
		}
	}

	public void followNpc() {
		if (NPCHandler.npcs[c.followId2] == null
				|| NPCHandler.npcs[c.followId2].isDead) {
			c.followId2 = 0;
			return;
		}
		if (c.freezeTimer > 0 || c.isDead || c.playerLevel[3] <= 0)
			return;

		int otherX = NPCHandler.npcs[c.followId2].getX();
		int otherY = NPCHandler.npcs[c.followId2].getY();
		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 8);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 4);
		boolean sameSpot = c.absX == otherX && c.absY == otherY;
		if (!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId2 = 0;
			return;
		}

		if ((c.usingBow || c.mageFollow || (c.npcIndex > 0 && c.autocastId > 0))
				&& bowDistance && !sameSpot) {
			return;
		}

		if (c.getCombat().usingHally() && hallyDistance && !sameSpot) {
			return;
		}

		if (c.usingRangeWeapon && rangeWeaponDistance && !sameSpot) {
			return;
		}

		c.faceUpdate(c.followId2);// try again
		sendFrame126("[FA]" + c.followId2, 2000);
	}

	/**
	 * Player Following gonna test this on mine again just a sec
	 **/
	public void followPlayer() {// not sure as it doesnt happen on mine
		if (PlayerHandler.players[c.followId] == null
				|| PlayerHandler.players[c.followId].isDead) {
			c.followId = 0;
			return;
		}
		if (c.freezeTimer > 0 || c.isDead || c.playerLevel[3] <= 0)
			return;

		int otherX = PlayerHandler.players[c.followId].getX();
		int otherY = PlayerHandler.players[c.followId].getY();
		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 8);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 4);
		boolean sameSpot = c.absX == otherX && c.absY == otherY;
		if (!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId = 0;
			return;
		}

		if ((c.usingBow || c.mageFollow || (c.playerIndex > 0 && c.autocastId > 0))
				&& bowDistance && !sameSpot) {
			return;
		}

		if (c.getCombat().usingHally() && hallyDistance && !sameSpot) {
			return;
		}

		if (c.usingRangeWeapon && rangeWeaponDistance && !sameSpot) {
			return;
		}

		c.faceUpdate(c.followId + 32768);
		sendFrame126("[FI]" + c.followId, 2000);
	}

	/**
	 * Reseting animations for everyone
	 **/
	public void frame1() {
		// synchronized(c) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				final Player person = PlayerHandler.players[i];
				if (person != null) {
					if (person.getOutStream() != null && !person.disconnected) {
						if (c.distanceToPoint(person.getX(), person.getY()) <= 25) {
							person.getOutStream().createFrame(1);
							person.flushOutStream();
							person.getPA().requestUpdates();
						}
					}
				}
			}
		}
		// }
	}

	public boolean fullGuthans() {
		return c.playerEquipment[c.playerHat] == 4724
				&& c.playerEquipment[c.playerChest] == 4728
				&& c.playerEquipment[c.playerLegs] == 4730
				&& c.playerEquipment[c.playerWeapon] == 4726;
	}

	public boolean fullVeracs() {
		return c.playerEquipment[c.playerHat] == 4753
				&& c.playerEquipment[c.playerChest] == 4757
				&& c.playerEquipment[c.playerLegs] == 4759
				&& c.playerEquipment[c.playerWeapon] == 4755;
	}

	public int getLevelForXP(final int exp) {
		int points = 0;
		int output = 0;
		if (exp > 13034430) {
			return 99;
		}
		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}

	//
	public int getLevelForDungXP(final int exp) {
		int points = 0;
		int output = 0;
		if (exp > 104273166) {
			return 120;
		}
		for (int lvl = 1; lvl <= 120; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}

	public int getMove(final int place1, final int place2) {
		if (System.currentTimeMillis() - c.lastSpear < 4000) {
			return 0;
		}
		if (place1 - place2 == 0) {
			return 0;
		} else if (place1 - place2 < 0) {
			return 1;
		} else if (place1 - place2 > 0) {
			return -1;
		}
		return 0;
	}

	public int getNpcId(final int id) {
		for (int i = 0; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] != null) {
				if (NPCHandler.npcs[i].npcId == id) {
					return i;
				}
			}
		}
		return -1;
	}

	public int getRunningMove(final int i, final int j) {
		if (j - i > 2) {
			return 2;
		} else if (j - i < -2) {
			return -2;
		} else {
			return j - i;
		}
	}

	public void getSpeared(final int otherX, final int otherY) {
		int x = c.absX - otherX;
		int y = c.absY - otherY;
		if (x > 0) {
			x = 1;
		} else if (x < 0) {
			x = -1;
		}
		if (y > 0) {
			y = 1;
		} else if (y < 0) {
			y = -1;
		}
		moveCheck(x, y);
		c.lastSpear = System.currentTimeMillis();
	}

	public int getWearingAmount() {
		int count = 0;
		for (final int element : c.playerEquipment) {
			if (element > 0) {
				count++;
			}
		}
		return count;
	}

	public int getXPForLevel(final int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}
public void dungemote(final Player c) {
			    EventManager.getSingleton().addEvent(new Event() {
				int dungtime = 13;
				 public void execute(EventContainer dung) {
				   if (dungtime == 13) {
				       c.gfx0(2442);
					c.startAnimation(13190);
				    }
				   if (dungtime == 12) {
					c.npcId2 = 11228;
					 c.isNpc = true;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13192);
				    }
				   if (dungtime == 8) {
					c.npcId2 = 11227;
					 c.isNpc = true;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13193);
				    }
				   if (dungtime == 5) {
				       c.gfx0(2442);
				    }
				   if (dungtime == 4) {
					c.npcId2 = 11229;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13194);
				    }
				   if (dungtime == 0) {
					c.isNpc = false;
					 c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				    }
				   if (c == null || dungtime <= 0) {
				       dung.stop();
                                                                         return; 
				    }
				   if (dungtime >= 0) {
					dungtime--;
				    }
				}
			    }, 600);
			}

	public void giveLife() {
		c.isDead = false;
		c.faceUpdate(-1);
		c.freezeTimer = 0;
		for (int count = 0; count < c.godwarsKillCount.length; count++) {
			c.godwarsKillCount[count] = 0;
		}
		sendFrame126("@cya@" + c.godwarsKillCount[0], 16217);
		sendFrame126("@cya@" + c.godwarsKillCount[3], 16216);
		sendFrame126("@cya@" + c.godwarsKillCount[2], 16218);
		sendFrame126("@cya@" + c.godwarsKillCount[1], 16219);
		if (c.duelStatus <= 4 && !c.getPA().inPitsWait() && !c.inPcGame()
				&& !c.inFightPits() && !c.inZombieCaves()) { // if we are not in
				
			// a duel we must be
			// in wildy so
			// remove items
			if (!c.inFightPits() && !c.inFightCaves() && !c.inDung()
					&& !c.inPcGame() && !c.inWeaponGame() && !c.inFunPk()) {
				c.getItems().resetKeepItems();
				if (!c.isSkulled) { // what items to keep
					c.getItems().keepItem(0, true);
					c.getItems().keepItem(1, true);
					c.getItems().keepItem(2, true);
				}
				if (c.prayerActive[10]
						&& System.currentTimeMillis() - c.lastProtItem > 700) {
					c.getItems().keepItem(3, true);
				}
				if (c.inZombieGame()) {
					c.ZombieGame.exitGame(false, c);	
				}
				for(int item = 0; item < Config.ITEMS_KEPT_ON_DEATH.length; item++) {
					int itemId = Config.ITEMS_KEPT_ON_DEATH[item];
					int itemAmount = c.getItems().getItemAmount(itemId) + c.getItems().getWornItemAmount(itemId);
					if(c.getItems().playerHasItem(itemId) || c.getItems().isWearingItem(itemId)) {
						c.sendMessage("You kept "+itemAmount+" "+c.getItems().getItemName(itemId).toLowerCase()+", it was transferred to your bank.");
						c.getItems().addItemToBank(itemId, itemAmount);
					}
				}
				c.getItems().dropAllItemsPVP(); // PvP drops
				c.getItems().dropAllItems();
				c.getItems().deleteAllItems(); // delete all items

				if (!c.isSkulled) { // add the kept items once we finish
					// deleting and dropping them
					for (int i1 = 0; i1 < 3; i1++) {
						if (c.itemKeptId[i1] > 0) {
							c.getItems().addItem(c.itemKeptId[i1], 1);
						}
					}
				}
				if (c.prayerActive[10]) { // if we have protect items
					if (c.itemKeptId[3] > 0) {
						c.getItems().addItem(c.itemKeptId[3], 1);
					}
				}
				c.getItems().resetKeepItems();
			} else if (c.inDung()) {
				c.dungDeaths++;
				c.getPA().sendFrame126("@or2@Deaths: " + c.dungDeaths + "",
						26255);
			}
		}
		if (c.familiarID > 0) {
			Summoning.dismissFamiliar(c, true, true);
		}
		Prayer.closeAllPrayers(c);
		c.prayer = c.maxPrayer();
		for (int i = 0; i < 23; i++) {
			c.playerLevel[i] = getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		c.playerLevel[23] = getLevelForDungXP(c.playerXP[23]);
		if (c.floor > 0 && c.inDung()) {
			Dungeoneering.deathRespawn(c);
		} else if (c.pitsStatus == 1) {
			movePlayer(2399, 5173, 0);
		} else if (c.inPcGame()) {
			movePlayer(2656, 2608, 0);
		} else if (c.inFightCaves()) {
			c.getPA().resetTzhaar();
		} else if (c.inFightPits()) {
			FightPits.handleDeath(c);
		} else if (c.inWeaponGame()) {
			WeaponGame.handleDeath(c);
		} else if (c.duelStatus <= 4) { // if we are not in a duel repawn to
			// wildy
			if (!c.inFunPk()) {
				movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
			} else {
				movePlayer(2608, 3150, 0);
			}
			c.isSkulled = false;
			c.skullTimer = 0;
			c.attackedPlayers.clear();
		} else { // we are in a duel, respawn outside of arena
			final Player o = PlayerHandler.players[c.duelingWith];
			if (o != null) {
				o.getPA().createPlayerHints(10, -1);
				if (o.duelStatus == 6) {
					o.getTradeAndDuel().duelVictory();
				}
			}
			// movePlayer(Config.DUELING_RESPAWN_X+Misc.random(Config.RANDOM_DUELING_RESPAWN),
			// Config.DUELING_RESPAWN_Y+Misc.random(Config.RANDOM_DUELING_RESPAWN),
			// 0);
			c.getPA().movePlayer(
					Config.DUELING_RESPAWN_X
					+ Misc.random(Config.RANDOM_DUELING_RESPAWN),
					Config.DUELING_RESPAWN_Y
					+ Misc.random(Config.RANDOM_DUELING_RESPAWN), 0);
			if (c.duelStatus != 6) { // if we have won but have died, don't
				// reset the duel status.
				c.getTradeAndDuel().resetDuel();
			}
		}
		// PlayerSaving.getSingleton().requestSave(c.playerId);
		PlayerSave.saveGame(c);
		c.getCombat().resetPlayerAttack();
		resetAnimation();
		//c.setAnimation(Animation.create(65535));
		c.startAnimation(65535);
		frame1();
		resetTb();
		c.isSkulled = false;
		c.attackedPlayers.clear();
		c.headIconPk = -1;
		c.skullTimer = -1;
		c.damageTaken = new int[Config.MAX_PLAYERS];
		c.getPA().requestUpdates();
		removeAllWindows();
		c.tradeResetNeeded = true;
		if (!c.inPcGame() || !c.inFightCaves() || !c.inFightPits() || !c.inWeaponGame()) {
			c.getPA().walkableInterface(-1);
		}
	}

	public void handleAlt(final int id) {
		if (!c.getItems().playerHasItem(id)) {
			c.getItems().addItem(id, 1);
		}
	}

	public void handleGlory(final int gloryId) {
		c.sendMessage("You rub the amulet...");
		c.getDH().sendOption4("Edgeville", "Al Kharid", "Karamja", "Mage Bank");
		c.usingGlory = true;
	}

	public void handleGodBooks(final int bookId) {
		if (bookId == 3840) {
			c.getDH().sendOption4("partnership", "blessing", "last rights",
					"preach");
			c.usingbook = true;
			c.sarabook = true;
		} else if (bookId == 3842) {
			c.getDH().sendOption4(" Wedding Ceremony", "blessing",
					"last rights", "preach");
			c.usingbook = true;
			c.zambook = true;
		} else if (bookId == 3844) {
			c.getDH().sendOption4(" Wedding Ceremony", "blessing",
					"last rights", "preach");
			c.usingbook = true;
			c.guthbook = true;
		}
	}

	public void handleLoginText() {
		/**
		 * Modern
		 */
		//c.getPA().sendFrame126("Monster Teleports", 1300); // varrock
		//c.getPA().sendFrame126("Opens Monster teleports", 1301); // varrock
		// description
		//c.getPA().sendFrame126("Minigame Teleports", 1325); // lumbridge
		//c.getPA().sendFrame126("Opens Minigame teleports", 1326); // lumbridge
		// description
		//c.getPA().sendFrame126("PK Teleports", 1350); // falador
		//c.getPA().sendFrame126("Opens PKing teleports", 1351); // falador
		// description
		//c.getPA().sendFrame126("Skill Teleport", 1382); // camelot
		//c.getPA().sendFrame126("Teleports you to the skilling area", 1383); // camelot
		// description
		//c.getPA().sendFrame126("Ardougne Teleport", 1415); // ardougne
		//c.getPA().sendFrame126("Teleports you to Ardougne", 1416); // ardougne
		// description
		//c.getPA().sendFrame126("Empty", 1454); // watchtower
		//c.getPA().sendFrame126("", 1455); // watchtower description
		//c.getPA().sendFrame126("Empty", 7457); // trollheim
		//c.getPA().sendFrame126("", 7458); // trollheim description
		//c.getPA().sendFrame126("", 18472); // ape atoll
		//c.getPA().sendFrame126("", 18473); // ape atoll description

		//c.getPA().sendFrame126("Monster Teleports", 13037);
		//c.getPA().sendFrame126("Opens Monster teleports", 13038);

		//c.getPA().sendFrame126("Minigame Teleports", 13047);
		//c.getPA().sendFrame126("Opens Minigame teleports", 13048);

		//c.getPA().sendFrame126("PK Teleports", 13055);
		//c.getPA().sendFrame126("Opens PK teleports", 13056);

		//c.getPA().sendFrame126("Skill Teleports", 13063);
		//c.getPA().sendFrame126("Teleports you to the skilling area", 13064);

		//c.getPA().sendFrame126("", 13071); // ardougne
		//c.getPA().sendFrame126("", 13072); // ardougne
		//c.getPA().sendFrame126("", 13081); // ardougne
		//c.getPA().sendFrame126("", 13082); // ardougne
		//c.getPA().sendFrame126("", 13089); // ardougne
		//c.getPA().sendFrame126("", 13090); // ardougne
		//c.getPA().sendFrame126("", 13097); // ardougne
		//c.getPA().sendFrame126("", 13098); // ardougne

		// banking text
		c.getPA().sendFrame126("The Bank of "+Config.SERVER_NAME+" - Deposit Box", 7421);
		c.getPA().sendFrame126("The Bank of "+Config.SERVER_NAME+"", 5383);
		// log out text
		c.getPA().sendFrame126("When you are ready to leave", 2450);
		c.getPA().sendFrame126(""+Config.SERVER_NAME+", use the", 2451);
		c.getPA().sendFrame126("button below to logout safely.", 2452);
		// Log in text
		c.getPA().sendFrame126("Welcome to "+Config.SERVER_NAME+" - Character Design", 3649);
		c.getPA().sendFrame126("Welcome to "+Config.SERVER_NAME+"", 15259);

	}

	public void handleStatus(final int i, final int i2, final int i3) {
		// Sanity u so dum
	}

	public void handleWeaponStyle() {
		if (c.fightMode == 0) {
			c.getPA().sendFrame36(43, c.fightMode);
		} else if (c.fightMode == 1) {
			c.getPA().sendFrame36(43, 3);
		} else if (c.fightMode == 2) {
			c.getPA().sendFrame36(43, 1);
		} else if (c.fightMode == 3) {
			c.getPA().sendFrame36(43, 2);
		}
	}

	public boolean inPitsWait() {
		return c.getX() <= 2404 && c.getX() >= 2394 && c.getY() <= 5175
				&& c.getY() >= 5169;
	}

	public boolean isInPM(final long l) {
		for (final long friend : c.friends) {
			if (friend != 0) {
				if (l == friend) {
					return true;
				}
			}
		}
		return false;
	}

	public void levelUp(final int skill) {
		/*
		 * if(Config.SOUND) { c.sendSound(c.getSound().LEVELUP, 100); }
		 */
		sendFrame126("@yel@Total Level: " + totalLevel(), 3984);
		switch (skill) {
		case 0:
			sendFrame126(
					"Congratulations! You've just advanced a Attack level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + "!", 4269);
			c.sendMessage("Congratulations! You've just advanced a attack level.");
			sendFrame164(6247);    
			if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Attack!");
                    			}
                		}
            		}
			break;

		case 1:
			sendFrame126(
					"Congratulations! You've just advanced a Defence level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Defence level.");
			sendFrame164(6253);    
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Defence!");
                    			}
                		}
            		}
			break;

		case 2:
			sendFrame126(
					"Congratulations! You've just advanced a Strength level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Strength level.");
			sendFrame164(6206);
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Strength!");
                    			}
                		}
            		}
			break;

		case 3:
			sendFrame126(
					"Congratulations! You've just advanced a Hitpoints level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Hitpoints level.");
			sendFrame164(6216);	 
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Hitpoints!");
                    			}
                		}
            		}
			break;

		case 4:
			sendFrame126(
					"Congratulations! You've just advanced a Ranged level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Ranging level.");
			sendFrame164(4443);	
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Ranged!");
                    			}
                		}
            		}
			break;

		case 5:
			sendFrame126(
					"Congratulations! You've just advanced a Prayer level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Prayer level.");
			sendFrame164(6242);	
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Prayer!");
                    			}
                		}
            		}
			break;

		case 6:
			sendFrame126(
					"Congratulations! You've just advanced a Magic level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Magic level.");
			sendFrame164(6211);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Magic!");
                    			}
                		}
            		}
			break;

		case 7:
			sendFrame126(
					"Congratulations! You've just advanced a Cooking level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Cooking level.");
			sendFrame164(6226);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Cooking!");
                    			}
                		}
            		}
			break;

		case 8:
			sendFrame126(
					"Congratulations! You've just advanced a Woodcutting level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Woodcutting level.");
			sendFrame164(4272);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Woodcutting!");
                    			}
                		}
            		}
			break;

		case 9:
			sendFrame126(
					"Congratulations! You've just advanced a Fletching level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Fletching level.");
			sendFrame164(6231);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Fletching!");
                    			}
                		}
            		}
			break;

		case 10:
			sendFrame126(
					"Congratulations! You've just advanced a Fishing level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Fishing level.");
			sendFrame164(6258);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Fishing!");
                    			}
                		}
            		}
			break;

		case 11:
			sendFrame126(
					"Congratulations! You've just advanced a Firemaking level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Firemaking level.");
			sendFrame164(4282);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Firemaking!");
                    			}
                		}
            		}
			break;

		case 12:
			sendFrame126(
					"Congratulations! You've just advanced a Crafting level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Crafting level.");
			sendFrame164(6263);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Crafting!");
                    			}
                		}
            		}
			break;

		case 13:
			sendFrame126(
					"Congratulations! You've just advanced a Smithing level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Smithing level.");
			sendFrame164(6221);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Smithing!");
                    			}
                		}
            		}
			break;

		case 14:
			sendFrame126(
					"Congratulations! You've just advanced a Mining level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Mining level.");
			sendFrame164(4416);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Mining!");
                    			}
                		}
            		}
			break;

		case 15:
			sendFrame126(
					"Congratulations! You've just advanced a Herblore level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Herblore level.");
			sendFrame164(6237);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Herblore!");
                    			}
                		}
            		}
			break;

		case 16:
			sendFrame126(
					"Congratulations! You've just advanced a Agility level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Agility level.");
			sendFrame164(4277);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Agility!");
                    			}
                		}
            		}
			break;

		case 17:
			sendFrame126(
					"Congratulations! You've just advanced a Thieving level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Thieving level.");
			sendFrame164(4261);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Thieving!");
                    			}
                		}
            		}
			break;

		case 18:
			sendFrame126(
					"Congratulations! You've just advanced a Slayer level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Slayer level.");
			sendFrame164(12122);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Slayer!");
                    			}
                		}
            		}
			break;

		case 19:
			sendFrame126(
					"Congratulations! You've just advanced a Farming level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Farming level.");
			sendFrame164(5267);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Farming!");
                    			}
                		}
            		}
			break;

		case 20:
			sendFrame126(
					"Congratulations, you just advanced a Runecrafting level!",
					4268);
			sendFrame126("Your runecrafting level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations, you just advanced a Runecrafting level.");
			sendFrame164(4267);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Runecrafting!");
                    			}
                		}
            		}
			break;

		case 21:
			sendFrame126(
					"Congratulations! You've just advanced a Hunter level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a runecrafting level.");
			sendFrame164(8267);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Hunter!");
                    			}
                		}
            		}
			break;

		case 22:
			sendFrame126(
					"Congratulations! You've just advanced a Summoning level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Summoning level.");
			sendFrame164(9267);			
				if(getLevelForXP(c.playerXP[skill]) == 99) {
                		for (int j = 0; j < Server.playerHandler.players.length; j++) {
                    		 if (Server.playerHandler.players[j] != null) {
                        		Player c2 = (Player)Server.playerHandler.players[j];
                        		c2.sendMessage("[<col=255>SERVER</col>]<col=255> " + c.playerName + " " + "just advanced to 99 Summoning!");
                    			}
                		}
            		}
			break;

		case 24:
			sendFrame126(
					"Congratulations! You've just advanced a Dungeoneering level!",
					4268);
			sendFrame126("You have now reached level "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations! You've just advanced a Dungeoneering level.");
			sendFrame164(10267);
			break;
		}
		c.dialogueAction = 0;
		c.nextChat = 0;
		sendFrame126("Click here to continue", 358);
	}

	public void loadPM(final long playerName, int world) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			if (world != 0) {
				world += 9;
			} else if (!Config.WORLD_LIST_FIX) {
				world += 1;
			}
			c.getOutStream().createFrame(50);
			c.getOutStream().writeQWord(playerName);
			c.getOutStream().writeByte(world);
			c.flushOutStream();
		}
		// }
	}

	/**
	 * Private Messaging
	 **/
	public void logIntoPM() {
		setPrivateMessaging(2);
		for (int i1 = 0; i1 < Config.MAX_PLAYERS; i1++) {
			final Player p = PlayerHandler.players[i1];
			if (p != null && p.isActive) {
				final Player o = p;
				if (o != null) {
					o.getPA().updatePM(c.playerId, 1);
				}
			}
		}
		boolean pmLoaded = false;
		for (final long friend : c.friends) {
			if (friend != 0) {
				for (int i2 = 1; i2 < Config.MAX_PLAYERS; i2++) {
					final Player p = PlayerHandler.players[i2];
					if (p != null && p.isActive
							&& Misc.playerNameToInt64(p.playerName) == friend) {
						final Player o = p;
						if (o != null) {
							if (c.playerRights >= 3
									|| p.privateChat == 0
									|| p.privateChat == 1
									&& o.getPA()
									.isInPM(Misc
											.playerNameToInt64(c.playerName))) {
								loadPM(friend, 1);
								pmLoaded = true;
							}
							break;
						}
					}
				}
				if (!pmLoaded) {
					loadPM(friend, 0);
				}
				pmLoaded = false;
			}
			for (int i1 = 1; i1 < Config.MAX_PLAYERS; i1++) {
				final Player p = PlayerHandler.players[i1];
				if (p != null && p.isActive) {
					final Player o = p;
					if (o != null) {
						o.getPA().updatePM(c.playerId, 1);
					}
				}
			}
		}
	}

	/**
	 * Magic on items
	 **/
	public void magicOnItems(final int slot, final int itemId, final int spellId) {
		if (!c.getItems().playerHasItem(itemId, 1, slot)) {
			return;
		}
		switch (spellId) {

		case 1173:
			c.getNonCombatSpells().superHeatItem(itemId);
			break;
		case 1162:
		case 1178:
			NonCombatSpells.playerAlching(c, spellId, itemId, slot);
			break;
		case 1155: // Lvl-1 enchant sapphire
		case 1165: // Lvl-2 enchant emerald
		case 1176: // Lvl-3 enchant ruby
		case 1180: // Lvl-4 enchant diamond
		case 1187: // Lvl-5 enchant dragonstone
		case 6003: // Lvl-6 enchant onyx
			c.getMagic().enchantItem(itemId, spellId);
			break;
		}
	}

	public void moveCheck(final int xMove, final int yMove) {
		movePlayer(c.absX + xMove, c.absY + yMove, c.heightLevel);
	}

	public void movePlayer(final int x, final int y, final int h) {
		if (c.isJailed) {
			c.sendMessage("You are jailed please appeal at the forums.");
			c.sendMessage(""+Config.FORUM_URL);
			return;
		}

                //c.freezeTimer = 3;
		c.resetWalkingQueue();
		c.teleportToX = x;
		c.teleportToY = y;
		c.heightLevel = h;
		requestUpdates();
	}

	/**
	 * MulitCombat icon
	 * 
	 * @param i1
	 *            0 = off 1 = on
	 */
	public void multiWay(final int i1) {
		// synchronized(c) {
		c.outStream.createFrame(61);
		c.outStream.writeByte(i1);
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
		// }
	}

	/**
	 * Objects, add and remove
	 **/
	public void object(final int objectId, final int objectX,
			final int objectY, final int face, final int objectType) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(objectY - c.getMapRegionY() * 8);
			c.getOutStream().writeByteC(objectX - c.getMapRegionX() * 8);
			c.getOutStream().createFrame(101);
			c.getOutStream().writeByteC((objectType << 2) + (face & 3));
			c.getOutStream().writeByte(0);

			if (objectId != -1) { // removing
				c.getOutStream().createFrame(151);
				c.getOutStream().writeByteS(0);
				c.getOutStream().writeWordBigEndian(objectId);
				c.getOutStream().writeByteS((objectType << 2) + (face & 3));
			}
			c.flushOutStream();
		}
		// }
	}

	private void objectToRemove(final int X, final int Y) {
		object(-1, X, Y, 10, 10);
	}

	private void objectToRemove2(final int X, final int Y) {
		object(-1, X, Y, -1, 0);
	}

	/**
	 * Open bank
	 **/

	public static void itemOnInterface(Player c, int frame, int slot, int id, int amount)
	{
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(id + 1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}

	public void otherBank(Player c, Player o) {
		if(o == c || o == null || c == null)
		{
			return;
		}

		int [] bankTabItems = new int[Config.BANK_SIZE*9];
		System.arraycopy( o.bankItems, 0, bankTabItems, 0, o.bankItems.length);
		System.arraycopy( o.bankItems1, 0, bankTabItems, o.bankItems.length, o.bankItems1.length );
		System.arraycopy( o.bankItems2, 0, bankTabItems, o.bankItems1.length, o.bankItems2.length );
		System.arraycopy( o.bankItems3, 0, bankTabItems, o.bankItems2.length, o.bankItems3.length );
		System.arraycopy( o.bankItems4, 0, bankTabItems, o.bankItems3.length, o.bankItems4.length );
		System.arraycopy( o.bankItems5, 0, bankTabItems, o.bankItems4.length, o.bankItems5.length );
		System.arraycopy( o.bankItems6, 0, bankTabItems, o.bankItems5.length, o.bankItems6.length );
		System.arraycopy( o.bankItems7, 0, bankTabItems, o.bankItems6.length, o.bankItems7.length );
		System.arraycopy( o.bankItems8, 0, bankTabItems, o.bankItems7.length, o.bankItems8.length );

		int [] bankTabItemsN = new int[Config.BANK_SIZE*9];
		System.arraycopy( o.bankItemsN, 0, bankTabItemsN, 0, o.bankItemsN.length);
		System.arraycopy( o.bankItems1N, 0, bankTabItemsN, o.bankItemsN.length, o.bankItems1N.length );
		System.arraycopy( o.bankItems2N, 0, bankTabItemsN, o.bankItems1N.length, o.bankItems2N.length );
		System.arraycopy( o.bankItems3N, 0, bankTabItemsN, o.bankItems2N.length, o.bankItems3N.length );
		System.arraycopy( o.bankItems4N, 0, bankTabItemsN, o.bankItems3N.length, o.bankItems4N.length );
		System.arraycopy( o.bankItems5N, 0, bankTabItemsN, o.bankItems4N.length, o.bankItems5N.length );
		System.arraycopy( o.bankItems6N, 0, bankTabItemsN, o.bankItems5N.length, o.bankItems6N.length );
		System.arraycopy( o.bankItems7N, 0, bankTabItemsN, o.bankItems6N.length, o.bankItems7N.length );
		System.arraycopy( o.bankItems8N, 0, bankTabItemsN, o.bankItems7N.length, o.bankItems8N.length );

		/* backup player A main tab */
		int[] backupItems = Arrays.copyOf(c.bankItems, c.bankItems.length);
		int[] backupItemsN = Arrays.copyOf(c.bankItemsN, c.bankItemsN.length);
		/* fill player A's main tab with player B's main tab */
		c.bankItems = Arrays.copyOf(bankTabItems, bankTabItems.length);
		c.bankItemsN = Arrays.copyOf(bankTabItemsN, bankTabItemsN.length);
		openUpBank(0);
		/* restore player A main tab */
		c.bankItems =  Arrays.copyOf(backupItems, backupItems.length);
		c.bankItemsN =  Arrays.copyOf(backupItemsN, backupItemsN.length);

	}

	public int getTotalCount(int itemID) {
		int count = 0;
		for (int j = 0; j < c.playerItems.length; j++) {
			if (Item.itemIsNote[itemID + 1]) {
				if (itemID + 2 == c.playerItems[j])
					count += c.playerItemsN[j];
			}
			if (!Item.itemIsNote[itemID + 1]) {
				if (itemID + 1 == c.playerItems[j]) {
					count += c.playerItemsN[j];
				}
			}
		}
		for (int j = 0; j < c.bankItems.length; j++) {
			if (c.bankItems[j] == itemID + 1)
				count += c.bankItemsN[j];
		}
		for (int j = 0; j < c.bankItems1.length; j++) {
			if (c.bankItems1[j] == itemID + 1)
				count += c.bankItems1N[j];
		}
		for (int j = 0; j < c.bankItems2.length; j++) {
			if (c.bankItems2[j] == itemID + 1)
				count += c.bankItems2N[j];
		}
		for (int j = 0; j < c.bankItems3.length; j++) {
			if (c.bankItems3[j] == itemID + 1)
				count += c.bankItems3N[j];
		}
		for (int j = 0; j < c.bankItems4.length; j++) {
			if (c.bankItems4[j] == itemID + 1)
				count += c.bankItems4N[j];
		}
		for (int j = 0; j < c.bankItems5.length; j++) {
			if (c.bankItems5[j] == itemID + 1)
				count += c.bankItems5N[j];
		}
		for (int j = 0; j < c.bankItems6.length; j++) {
			if (c.bankItems6[j] == itemID + 1)
				count += c.bankItems6N[j];
		}
		for (int j = 0; j < c.bankItems7.length; j++) {
			if (c.bankItems7[j] == itemID + 1)
				count += c.bankItems7N[j];
		}
		for (int j = 0; j < c.bankItems8.length; j++) {
			if (c.bankItems8[j] == itemID + 1)
				count += c.bankItems8N[j];
		}
		return count;
	}

	public int tempItems[] = new int[Config.BANK_SIZE];
	public int tempItemsN[] = new int[Config.BANK_SIZE];
	public int tempItemsT[] = new int[Config.BANK_SIZE];
	public int tempItemsS[] = new int[Config.BANK_SIZE];
	public void searchBank(Player c, String searchTerm)
	{

		if(searchTerm.equals("N/A")) {
			//c.sendMessage("N/A");
			openUpBank(c.bankingTab);
			return;
		}
		c.bankingTab = -1;
		sendFrame126(Integer.toString(-1), 27002);
		sendFrame126("1", 27000);
		tempItems = new int[Config.BANK_SIZE];
		tempItemsN = new int[Config.BANK_SIZE];
		tempItemsT = new int[Config.BANK_SIZE];
		tempItemsS = new int[Config.BANK_SIZE];
		for (int n = 0; n < 9; n++) {
			if (n == 0)
				updateResult(n, c.bankItems, c.bankItemsN);
			else if (n == 1)
				updateResult(n,c.bankItems1, c.bankItems1N);
			else if (n == 2)
				updateResult(n, c.bankItems2, c.bankItems2N);
			else if (n == 3)
				updateResult(n, c.bankItems3, c.bankItems3N);
			else if (n == 4)
				updateResult(n, c.bankItems4, c.bankItems4N);
			else if (n == 5)
				updateResult(n, c.bankItems5, c.bankItems5N);
			else if (n == 6)
				updateResult(n, c.bankItems6, c.bankItems6N);
			else if (n == 7)
				updateResult(n, c.bankItems7, c.bankItems7N);
			else if (n == 8)
				updateResult(n, c.bankItems8, c.bankItems8N);
		}
		c.getItems().resetItems(5064);
		c.getItems().resetBank(tempItems, tempItemsN);
	}



	public int bankingCount ()
	{
		int total = 0;
		for (int i = 0; i < c.bankingItems.length; i++)
			if (c.bankingItems[i] > 0)
				total++;
		return total;
	}

	/**
	 * Open bank
	 **/
	public void openUpBank(int tab) {
		if (c.wantsPIN == 1) {
			if(c.getBankPin().getFullPin().equalsIgnoreCase("")){
				c.getBankPin().open();
			        return;
			}	
		}	
		if(c.searchTerm == "FLAG")
			return;
		if (tab != -1)
			c.searchTerm = "N/A";
		if (c.takeAsNote)
			sendFrame36(115, 1);
		else
			sendFrame36(115, 0);

		if (c.inWild() && !c.isOwner() && !BountyHunter.safeArea(c)) {
			c.sendMessage("You can't bank in the wilderness!");
			return;
		}

		if (c.getOutStream() != null && c != null) {
			c.isBanking = true;
			//System.out.println("tab_"+tab);
			c.bankingTab = tab;
			sendTabs();
			c.getPA().sendFrame126(Integer.toString(getBankItems(-1)), 22033);//Send total Bank Items
			c.getPA().sendFrame126(Integer.toString(Config.BANK_SIZE), 22034);//Send max Bank Items - 496

			//Setting appropriate displayed items for current tab
			//System.out.println(c.bankingTab);
			if(c.bankingTab == 0) {
				c.bankingItems = c.bankItems;
				c.bankingItemsN = c.bankItemsN;
			}
			if(c.bankingTab == 1) {
				c.bankingItems = c.bankItems1;
				c.bankingItemsN = c.bankItems1N;
			}
			if(c.bankingTab == 2) {
				c.bankingItems = c.bankItems2;
				c.bankingItemsN = c.bankItems2N;
			}
			if(c.bankingTab == 3) {
				c.bankingItems = c.bankItems3;
				c.bankingItemsN = c.bankItems3N;
			}
			if(c.bankingTab == 4) {
				c.bankingItems = c.bankItems4;
				c.bankingItemsN = c.bankItems4N;
			}
			if(c.bankingTab == 5) {
				c.bankingItems = c.bankItems5;
				c.bankingItemsN = c.bankItems5N;
			}
			if(c.bankingTab == 6) {
				c.bankingItems = c.bankItems6;
				c.bankingItemsN = c.bankItems6N;
			}
			if(c.bankingTab == 7) {
				c.bankingItems = c.bankItems7;
				c.bankingItemsN = c.bankItems7N;
			}
			if(c.bankingTab == 8) {
				c.bankingItems = c.bankItems8;
				c.bankingItemsN = c.bankItems8N;
			}
			if (c.searchTerm == "N/A") {
				c.getPA().sendFrame126("The Bank of "+ Config.SERVER_NAME +"", 5383);
				sendFrame36(0, 0);
			}
			c.getItems().resetItems(5064);
			c.getItems().rearrangeBank();
			c.getItems().resetBank();
			c.getItems().resetTempItems();
			c.getOutStream().createFrame(248);
			c.getOutStream().writeWordA(5292);
			c.getOutStream().writeWord(5063);
			c.flushOutStream();
		}
	}

	public int getBankItems(int tab) {
		int ta = 0,tb = 0,tc = 0,td = 0,te = 0,tf = 0,tg = 0,th = 0, ti = 0;
		for (int i = 0; i < c.bankItems.length; i++)
			if (c.bankItems[i] > 0)
				ta++;
		for (int i = 0; i < c.bankItems1.length; i++)
			if (c.bankItems1[i] > 0)
				tb++;
		for (int i = 0; i < c.bankItems2.length; i++)
			if (c.bankItems2[i] > 0)
				tc++;
		for (int i = 0; i < c.bankItems3.length; i++)
			if (c.bankItems3[i] > 0)
				td++;
		for (int i = 0; i < c.bankItems4.length; i++)
			if (c.bankItems4[i] > 0)
				te++;
		for (int i = 0; i < c.bankItems5.length; i++)
			if (c.bankItems5[i] > 0)
				tf++;
		for (int i = 0; i < c.bankItems6.length; i++)
			if (c.bankItems6[i] > 0)
				tg++;
		for (int i = 0; i < c.bankItems7.length; i++)
			if (c.bankItems7[i] > 0)
				th++;
		for (int i = 0; i < c.bankItems8.length; i++)
			if (c.bankItems8[i] > 0)
				ti++;
		if(tab == 0)
			return ta;
		if(tab == 1)
			return tb;
		if(tab == 2)
			return tc;
		if(tab == 3)
			return td;
		if(tab == 4)
			return te;
		if(tab == 5)
			return tf;
		if(tab == 6)
			return tg;
		if(tab == 7)
			return th;
		if(tab == 8)
			return ti;
		return ta+tb+tc+td+te+tf+tg+th+ti;//return total

	}

	public int getTabCount(){
		//count tabs
		int tabs = 0;
		if(!checkEmpty(c.bankItems1)) tabs++; if(!checkEmpty(c.bankItems2)) tabs++; if(!checkEmpty(c.bankItems3)) tabs++; if(!checkEmpty(c.bankItems4)) tabs++;
		if(!checkEmpty(c.bankItems5)) tabs++; if(!checkEmpty(c.bankItems6)) tabs++; if(!checkEmpty(c.bankItems7)) tabs++; if(!checkEmpty(c.bankItems8)) tabs++;
		return tabs;
	}

	public boolean checkEmpty(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] != 0)
				return false;
		}
		return true;
	}

	public void sendTabs()
	{
		//remove empty tab
		boolean moveRest = false;
		if(checkEmpty(c.bankItems1)) { //tab 1 empty
			c.bankItems1 = Arrays.copyOf(c.bankItems2, c.bankingItems.length);
			c.bankItems1N = Arrays.copyOf(c.bankItems2N, c.bankingItems.length);
			c.bankItems2 = new int[Config.BANK_SIZE];
			c.bankItems2N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if(checkEmpty(c.bankItems2) || moveRest) { 
			c.bankItems2 = Arrays.copyOf(c.bankItems3, c.bankingItems.length);
			c.bankItems2N = Arrays.copyOf(c.bankItems3N, c.bankingItems.length);
			c.bankItems3 = new int[Config.BANK_SIZE];
			c.bankItems3N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if(checkEmpty(c.bankItems3) || moveRest) { 
			c.bankItems3 = Arrays.copyOf(c.bankItems4, c.bankingItems.length);
			c.bankItems3N = Arrays.copyOf(c.bankItems4N, c.bankingItems.length);
			c.bankItems4 = new int[Config.BANK_SIZE];
			c.bankItems4N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if(checkEmpty(c.bankItems4) || moveRest) { 
			c.bankItems4 = Arrays.copyOf(c.bankItems5, c.bankingItems.length);
			c.bankItems4N = Arrays.copyOf(c.bankItems5N, c.bankingItems.length);
			c.bankItems5 = new int[Config.BANK_SIZE];
			c.bankItems5N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if(checkEmpty(c.bankItems5) || moveRest) { 
			c.bankItems5 = Arrays.copyOf(c.bankItems6, c.bankingItems.length);
			c.bankItems5N = Arrays.copyOf(c.bankItems6N, c.bankingItems.length);
			c.bankItems6 = new int[Config.BANK_SIZE];
			c.bankItems6N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if(checkEmpty(c.bankItems6) || moveRest) { 
			c.bankItems6 = Arrays.copyOf(c.bankItems7, c.bankingItems.length);
			c.bankItems6N = Arrays.copyOf(c.bankItems7N, c.bankingItems.length);
			c.bankItems7 = new int[Config.BANK_SIZE];
			c.bankItems7N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if(checkEmpty(c.bankItems7) || moveRest) { 
			c.bankItems7 = Arrays.copyOf(c.bankItems8, c.bankingItems.length);
			c.bankItems7N = Arrays.copyOf(c.bankItems8N, c.bankingItems.length);
			c.bankItems8 = new int[Config.BANK_SIZE];
			c.bankItems8N = new int[Config.BANK_SIZE];
		}
		if(c.bankingTab > getTabCount())
			c.bankingTab = getTabCount();
		c.getPA().sendFrame126(Integer.toString(getTabCount()), 27001);
		c.getPA().sendFrame126(Integer.toString(c.bankingTab), 27002);
		PlayerAssistant.itemOnInterface(c, 22035, 0, getInterfaceModel(0, c.bankItems1, c.bankItems1N), 1);
		PlayerAssistant.itemOnInterface(c, 22036, 0, getInterfaceModel(0, c.bankItems2, c.bankItems2N), 1);
		PlayerAssistant.itemOnInterface(c, 22037, 0, getInterfaceModel(0, c.bankItems3, c.bankItems3N), 1);
		PlayerAssistant.itemOnInterface(c, 22038, 0, getInterfaceModel(0, c.bankItems4, c.bankItems4N), 1);
		PlayerAssistant.itemOnInterface(c, 22039, 0, getInterfaceModel(0, c.bankItems5, c.bankItems5N), 1);
		PlayerAssistant.itemOnInterface(c, 22040, 0, getInterfaceModel(0, c.bankItems6, c.bankItems6N), 1);
		PlayerAssistant.itemOnInterface(c, 22041, 0, getInterfaceModel(0, c.bankItems7, c.bankItems7N), 1);
		PlayerAssistant.itemOnInterface(c, 22042, 0, getInterfaceModel(0, c.bankItems8, c.bankItems8N), 1);
		//tells client to update
		c.getPA().sendFrame126("1", 27000);
	}

	public int getInterfaceModel(int slot, int[] array, int[] arrayN) {
		int model = array[slot]-1;
		if (model == 995) {
			if (arrayN[slot] > 9999) {
				model = 1004;
			} else if (arrayN[slot] > 999) {
				model = 1003;
			} else if (arrayN[slot] > 249) {
				model = 1002;
			} else if (arrayN[slot] > 99) {
				model = 1001;
			}  else if (arrayN[slot] > 24) {
				model = 1000;
			} else if (arrayN[slot] > 4) {
				model = 999;
			} else if (arrayN[slot] > 3) {
				model = 998;
			} else if (arrayN[slot] > 2) {
				model = 997;
			} else if (arrayN[slot] > 1) {
				model = 996;
			}
		}
		return model;
	}

	public void updateResult(int tab, int[] array, int[] arrayN)
	{
		c.searchTerm = c.searchTerm.toLowerCase().replaceAll(" ", "_");
		//c.sendMessage("searching with " + searchTerm);
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if(arrayN[i] > 0) {
				String itemName = (c.getItems().getItemName(array[i] - 1)).toLowerCase().replaceAll(" ", "_");
				if(itemName.indexOf(c.searchTerm) != -1) {
					//c.sendMessage("found" + c.bankItems[i]);
					tempItemsT[index] = tab;
					tempItemsS[index] = i;
					tempItems[index] = array[i];
					tempItemsN[index++] = arrayN[i];
				} else {
					//c.sendMessage("nah, " + itemName + " cant be " + searchTerm);
				}
			}
		}
	}

	public void displayItemOnInterface(int frame, int item, int slot, int amount)
	{
		//synchronized (c){
		if (c.getOutStream() != null && c != null)
		{
			c.outStream.createFrameVarSizeWord(34);
			c.outStream.writeWord(frame);
			c.outStream.writeByte(slot);
			c.outStream.writeWord(item + 1);
			c.outStream.writeByte(255);
			c.outStream.writeDWord(amount);
			c.outStream.endFrameVarSizeWord();
		}
		//}
	}

	/**
	 * Drink AntiPosion Potions
	 * 
	 * @param itemId
	 *            The itemId
	 * @param itemSlot
	 *            The itemSlot
	 * @param newItemId
	 *            The new item After Drinking
	 * @param healType
	 *            The type of poison it heals
	 */
	public void potionPoisonHeal(final int itemId, final int itemSlot,
			final int newItemId, final int healType) {
		c.attackTimer = c.getCombat().getAttackDelay(
				c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
				.toLowerCase());
		if (c.duelRule[5]) {
			c.sendMessage("Potions has been disabled in this duel!");
			return;
		}
		if (!c.isDead && System.currentTimeMillis() - c.foodDelay > 2000) {
			if (c.getItems().playerHasItem(itemId, 1, itemSlot)) {
				c.sendMessage("You drink the "
						+ c.getItems().getItemName(itemId).toLowerCase() + ".");
				c.foodDelay = System.currentTimeMillis();
				// Actions
				if (healType == 1) {
					// Cures The Poison
				} else if (healType == 2) {
					// Cures The Poison + protects from getting poison again
				}
				//c.setAnimation(Animation.create(0x33D));
				c.startAnimation(0x33D);
				c.getItems().deleteItem(itemId, itemSlot, 1);
				c.getItems().addItem(newItemId, 1);
				requestUpdates();
			}
		}
	}

	public void processTeleport() {
		c.teleportToX = c.teleX;
		c.teleportToY = c.teleY;
		c.heightLevel = c.teleHeight;
		if (c.teleEndAnimation > 0) {
			//c.setAnimation(Animation.create(c.teleEndAnimation));
			c.startAnimation(c.teleEndAnimation);
		}
	}

	public int randomPots() {
		return PlayerAssistant.Pots[(int) (Math.random() * PlayerAssistant.Pots.length)];
	}

	public void crabSpecBonus(int stat, boolean sup) {
		boostStat1(stat, sup);
	}

	public void scorpSpecBonus(int stat, boolean sup) {
		boostStat(stat, sup);
	}

	public int getBoostStat(int skill, boolean sup) {
		int increaseBy = 0;
		if (sup) {
			increaseBy = (int) (c.getLevelForXP(c.playerXP[skill]) * .20);
		} else {
			increaseBy = (int) (c.getLevelForXP(c.playerXP[skill]) * .06);
		}
		if (c.playerLevel[skill] + increaseBy > c
				.getLevelForXP(c.playerXP[skill]) + increaseBy) {
			return c.getLevelForXP(c.playerXP[skill]) + increaseBy
					- c.playerLevel[skill];
		}
		return increaseBy;
	}

	public int getBoostStat1(int skill, boolean sup) {
		int increaseBy = 0;
		if (sup) {
			increaseBy = (int) (c.getLevelForXP(c.playerXP[skill]) * .10);
		} else {
			increaseBy = (int) (c.getLevelForXP(c.playerXP[skill]) * .06);
		}
		if (c.playerLevel[skill] + increaseBy > c
				.getLevelForXP(c.playerXP[skill]) + increaseBy) {
			return c.getLevelForXP(c.playerXP[skill]) + increaseBy
					- c.playerLevel[skill];
		}
		return increaseBy;
	}

	public void boostStat(int skillID, boolean sup) {
		c.playerLevel[skillID] += getBoostStat(skillID, sup);
		c.getPA().refreshSkill(skillID);
	}

	public void boostStat1(int skillID, boolean sup) {
		c.playerLevel[skillID] += getBoostStat1(skillID, sup);
		c.getPA().refreshSkill(skillID);
	}

	public void refreshAll() {
		refreshSkill(0);
		refreshSkill(1);
		refreshSkill(2);
		refreshSkill(3);
		refreshSkill(4);
		refreshSkill(5);
		refreshSkill(6);
		refreshSkill(7);
		refreshSkill(8);
		refreshSkill(9);
		refreshSkill(10);
		refreshSkill(11);
		refreshSkill(12);
		refreshSkill(13);
		refreshSkill(14);
		refreshSkill(15);
		refreshSkill(16);
		refreshSkill(17);
		refreshSkill(18);
		refreshSkill(19);
		refreshSkill(20);
		refreshSkill(21);
		refreshSkill(22);
		refreshSkill(23);
		refreshSkill(24);
	}

	public void refreshSkill(int i) {
		switch (i) {
		case 22:

			sendFrame126(c.playerLevel[22] + "", 1500);
			sendFrame126(getLevelForXP(c.playerXP[22]) + "", 1501);
			sendFrame126("@yel@" + c.playerLevel[22] + "", 18166);
			sendFrame126("@yel@" + getLevelForXP(c.playerXP[22]) + "", 18170);
			break;
		case 21:
			sendFrame126("@yel@" + c.playerLevel[21] + "", 18165);
			sendFrame126("@yel@" + getLevelForXP(c.playerXP[21]) + "", 18169);
			break;

		case 0:
			sendFrame126(c.playerLevel[0] + "", 4004);
			sendFrame126(getLevelForXP(c.playerXP[0]) + "", 4005);
			sendFrame126(c.playerXP[0] + "", 4044);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[0]) + 1) + "",
					4045);
			break;

		case 1:
			sendFrame126(c.playerLevel[1] + "", 4008);
			sendFrame126(getLevelForXP(c.playerXP[1]) + "", 4009);
			sendFrame126(c.playerXP[1] + "", 4056);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[1]) + 1) + "",
					4057);
			break;

		case 2:
			sendFrame126(c.playerLevel[2] + "", 4006);
			sendFrame126(getLevelForXP(c.playerXP[2]) + "", 4007);
			sendFrame126(c.playerXP[2] + "", 4050);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[2]) + 1) + "",
					4051);
			break;

		case 3:
			sendFrame126(c.playerLevel[3] + "", 4016);
			sendFrame126(getLevelForXP(c.playerXP[3]) + "", 4017);
			sendFrame126(c.playerXP[3] + "", 4080);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[3]) + 1) + "",
					4081);
			break;

		case 4:
			sendFrame126(c.playerLevel[4] + "", 4010);
			sendFrame126(getLevelForXP(c.playerXP[4]) + "", 4011);
			sendFrame126(c.playerXP[4] + "", 4062);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[4]) + 1) + "",
					4063);
			break;

		case 5:
			sendFrame126((int)c.prayer + "", 4012);
			sendFrame126(c.getMaxPrayer() + "", 4013);
			sendFrame126(c.playerLevel[5] + "", 4068);
			sendFrame126(c.getPA().getLevelForXP(c.playerXP[5]) + "", 4069);
			sendFrame126((int)c.prayer + "/" + c.getMaxPrayer(), c.curses ? 22501
					: 687);// Prayer frame
			break;

		case 6:
			sendFrame126(c.playerLevel[6] + "", 4014);
			sendFrame126(getLevelForXP(c.playerXP[6]) + "", 4015);
			sendFrame126(c.playerXP[6] + "", 4074);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[6]) + 1) + "",
					4075);
			break;

		case 7:
			sendFrame126(c.playerLevel[7] + "", 4034);
			sendFrame126(getLevelForXP(c.playerXP[7]) + "", 4035);
			sendFrame126(c.playerXP[7] + "", 4134);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[7]) + 1)
					+ "", 4135);
			break;

		case 8:
			sendFrame126(c.playerLevel[8] + "", 4038);
			sendFrame126(getLevelForXP(c.playerXP[8]) + "", 4039);
			sendFrame126(c.playerXP[8] + "", 4146);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[8]) + 1) + "",
					4147);
			break;

		case 9:
			sendFrame126(c.playerLevel[9] + "", 4026);
			sendFrame126(getLevelForXP(c.playerXP[9]) + "", 4027);
			sendFrame126(c.playerXP[9] + "", 4110);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[9]) + 1) + "",
					4111);
			break;

		case 10:
			sendFrame126(c.playerLevel[10] + "", 4032);
			sendFrame126(getLevelForXP(c.playerXP[10]) + "", 4033);
			sendFrame126(c.playerXP[10] + "", 4128);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[10]) + 1) + "",
					4129);
			break;

		case 11:
			sendFrame126(c.playerLevel[11] + "", 4036);
			sendFrame126(getLevelForXP(c.playerXP[11]) + "", 4037);
			sendFrame126(c.playerXP[11] + "", 4140);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[11]) + 1) + "",
					4141);
			break;

		case 12:
			sendFrame126(c.playerLevel[12] + "", 4024);
			sendFrame126(getLevelForXP(c.playerXP[12]) + "", 4025);
			sendFrame126(c.playerXP[12] + "", 4104);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[12]) + 1) + "",
					4105);
			break;

		case 13:
			sendFrame126(c.playerLevel[13] + "", 4030);
			sendFrame126(getLevelForXP(c.playerXP[13]) + "", 4031);
			sendFrame126(c.playerXP[13] + "", 4122);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[13]) + 1) + "",
					4123);
			break;

		case 14:
			sendFrame126("" + c.playerLevel[14] + "", 4028);
			sendFrame126("" + getLevelForXP(c.playerXP[14]) + "", 4029);
			sendFrame126(c.playerXP[14] + "", 4116);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[14]) + 1) + "",
					4117);
			break;

		case 15:
			sendFrame126(c.playerLevel[15] + "", 4020);
			sendFrame126(getLevelForXP(c.playerXP[15]) + "", 4021);
			sendFrame126(c.playerXP[15] + "", 4092);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[15]) + 1) + "",
					4093);
			break;

		case 16:
			sendFrame126(c.playerLevel[16] + "", 4018);
			sendFrame126(getLevelForXP(c.playerXP[16]) + "", 4019);
			sendFrame126(c.playerXP[16] + "", 4086);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[16]) + 1) + "",
					4087);
			break;

		case 17:
			sendFrame126(c.playerLevel[17] + "", 4022);
			sendFrame126(getLevelForXP(c.playerXP[17]) + "", 4023);
			sendFrame126(c.playerXP[17] + "", 4098);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[17]) + 1) + "",
					4099);
			break;

		case 18:
			sendFrame126(c.playerLevel[18] + "", 12166);
			sendFrame126(getLevelForXP(c.playerXP[18]) + "", 12167);
			sendFrame126(c.playerXP[18] + "", 12171);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[18]) + 1) + "",
					12172);
			break;

		case 19:
			sendFrame126(c.playerLevel[19] + "", 13926);
			sendFrame126(getLevelForXP(c.playerXP[19]) + "", 13927);
			sendFrame126(c.playerXP[19] + "", 13921);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[19]) + 1) + "",
					13922);
			break;

		case 20:
			sendFrame126(c.playerLevel[20] + "", 4152);
			sendFrame126(getLevelForXP(c.playerXP[20]) + "", 4153);
			sendFrame126(c.playerXP[20] + "", 4157);
			sendFrame126(getXPForLevel(getLevelForXP(c.playerXP[20]) + 1) + "",
					4159);
			break;

		case 23:
			int[] lvlStrings = { 25652, 25852, 25952, 25252, -1, 25452,
					25612, 25712, 25812, 25912, 24352, 24452, 24552, 24652,
					24752, 24852, 24952 };
			for (int s = 0; s < lvlStrings.length; s++) {
				sendFrame126(c.playerLevel[23] + "/"
						+ getLevelForXP(c.playerXP[23]), lvlStrings[s]);
			}
			sendFrame126("@yel@" + c.playerLevel[23] + "", 18167);
			sendFrame126("@yel@" + getLevelForDungXP(c.playerXP[23]) + "",
					18171);
			break;

		}
	}

	public void removeAllWindows() {
		c.usingBoB = false;
		if (c.getOutStream() != null && c != null) {
			c.getPA().resetVariables();
			c.getOutStream().createFrame(219);
			c.flushOutStream();
		}
	}

	public void removeObject(final int x, final int y) {
		object(-1, x, x, 10, 10);
	}

	public void removeObjects() {
		objectToRemove(2638, 4688);
		objectToRemove2(2635, 4693);
		objectToRemove2(2634, 4693);
	}

	public void requestUpdates() {
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}

	/**
	 * reseting animation
	 **/
	public void resetAnimation() {
		c.getCombat().getPlayerAnimIndex(
				c.getItems().getItemName(c.playerEquipment[c.playerWeapon])
				.toLowerCase());
		//c.setAnimation(Animation.create(c.playerStandIndex));
		c.startAnimation(c.playerStandIndex);
		requestUpdates();
	}

	public void resetAutocast() {
		c.autocastId = -1;
		c.autocasting = false;
		c.setSidebarInterface(0, 328);
		c.getPA().sendFrame36(108, 0);
		c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon],
				c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
	}

	public void resetBarrows() {
		c.barrowsNpcs[0][1] = 0;
		c.barrowsNpcs[1][1] = 0;
		c.barrowsNpcs[2][1] = 0;
		c.barrowsNpcs[3][1] = 0;
		c.barrowsNpcs[4][1] = 0;
		c.barrowsNpcs[5][1] = 0;
		c.brotherKills = 0;
		c.barrowsKillCount = 0;
		c.randomCoffin = Misc.random(3) + 1;
	}

	public void resetDamageDone() {
		for (int i = 0; i < PlayerHandler.players.length; i++) {
			if (PlayerHandler.players[i] != null) {
				PlayerHandler.players[i].damageTaken[c.playerId] = 0;
			}
		}
	}

	public void resetFollow() {
		c.followId = 0;
		c.followId2 = 0;
		c.mageFollow = false;
		c.outStream.createFrame(174);
		c.outStream.writeWord(0);
		c.outStream.writeByte(0);
		c.outStream.writeWord(1);
	}

	public void resetFollowers() {
		for (final Player player : PlayerHandler.players) {
			if (player != null) {
				if (player.followId == c.playerId) {
					final Player c = player;
					c.getPA().resetFollow();
				}
			}
		}
	}

	public void resetTb() {
		c.teleBlockLength = 0;
		c.teleBlockDelay = 0;
	}

	public void resetTzhaar() {
		c.waveId = -1;
		c.tzhaarToKill = -1;
		c.tzhaarKilled = -1;
		movePlayer(2438, 5168, 0);
	}

	public void enterCaves() {
		c.getPA().movePlayer(2413, 5117, c.playerId * 4);
		c.waveId = 0;
		c.tzhaarToKill = -1;
		c.tzhaarKilled = -1;
		EventManager.getSingleton().addEvent(new Event() {
			@Override
			public void execute(EventContainer e) {
				Server.fightCaves.spawnNextWave(PlayerHandler.players[c.playerId]);
				e.stop();
			}
		}, 10000);
	}

	public void resetRFD() {
		c.waveId = -1;
		c.RFDtoKill = -1;
		c.RFDkilled = -1;	
		c.getPA().movePlayer(3218,9620,0);
	}

	public static final int[][] A_DATA = { { 14892, 50000 }, { 14891, 100000 },
		{ 14890, 200000 }, { 14889, 300000 }, { 14888, 400000 },
		{ 14887, 500000 }, { 14886, 750000 }, { 14885, 1000000 },
		{ 14884, 1300000 }, { 14883, 1500000 }, { 14882, 1700000 },
		{ 14881, 2000000 }, { 14880, 2300000 }, { 14879, 2500000 },
		{ 14878, 2700000 }, { 14877, 3000000 }, { 14876, 3500000 } };

	public static void sellArtifacts(Player p) {
		for (int x = 0; x < A_DATA.length; x++) {
			if (p.getItems().playerHasItem(A_DATA[x][0])) {
				int value = A_DATA[x][1] * p.getItems().getItemAmount(A_DATA[x][0]);
				p.getItems().addItem(995, A_DATA[x][1] * p.getItems().getItemAmount(A_DATA[x][0]));
				p.getItems().deleteItem(A_DATA[x][0], p.getItems().getItemAmount(A_DATA[x][0]));
				p.sendMessage("You have sold your artifact for " + value + " gold!");
				p.nextChat = 0;
			}
		}
	}

	public void enterRFD() {
		if (c.Culin == true) {
			c.sendMessage("You have already finished this minigame!");
			return;
		}
		if (c.Agrith == true && c.Flambeed == false) {
			c.waveId = 1;
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			Server.RFD.spawnNextWave(c);
			return;
		}
		if (c.Flambeed == true && c.Karamel == false) {
			c.waveId = 2;
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			Server.RFD.spawnNextWave(c);
			return;
		}
		if (c.Karamel == true && c.Dessourt == false) {
			c.waveId = 3;
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			Server.RFD.spawnNextWave(c);
			return;
		}
		if (c.Dessourt == true && c.Culin == false) {
			c.waveId = 4;
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			Server.RFD.spawnNextWave(c);
			return;
		}
		if (c.Agrith == false) {
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			c.waveId = 0;
			c.RFDToKill = -1;
			c.RFDKilled = -1;
			Server.RFD.spawnNextWave(c);
		}
	}

	public void resetVariables() {
		c.usingGlory = false;
		c.smeltInterface = false;
		c.smeltType = 0;
		c.smeltAmount = 0;
	}

	public void sendFrame106(final int sideIcon) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(106);
			c.getOutStream().writeByteC(sideIcon);
			c.flushOutStream();
			requestUpdates();
		}
		// }
	}

	public void sendFrame107() {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(107);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame126(final String s, final int id) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSizeWord(126);
			c.getOutStream().writeString(s);
			c.getOutStream().writeWordA(id);
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame164(final int Frame) {//that is right
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(164);
			c.getOutStream().writeWordBigEndian_dup(Frame);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame171(final int MainFrame, final int SubFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(171);
			c.getOutStream().writeByte(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame185(final int Frame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(185);
			c.getOutStream().writeWordBigEndianA(Frame);
		}
		// }
	}

	public void sendFrame200(final int MainFrame, final int SubFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(200);
			c.getOutStream().writeWord(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame201(final int MainFrame, final int SubFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(201);
			c.getOutStream().writeWord(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame246(final int MainFrame, final int SubFrame,
			final int SubFrame2) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(246);
			c.getOutStream().writeWordBigEndian(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.getOutStream().writeWord(SubFrame2);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame248(final int MainFrame, final int SubFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(248);
			c.getOutStream().writeWordA(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame34(final int id, final int slot, final int column,
			final int amount) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.outStream.createFrameVarSizeWord(34); // init item to smith screen
			c.outStream.writeWord(column); // Column Across Smith Screen
			c.outStream.writeByte(4); // Total Rows?
			c.outStream.writeDWord(slot); // Row Down The Smith Screen
			c.outStream.writeWord(id + 1); // item
			c.outStream.writeByte(amount); // how many there are?
			c.outStream.endFrameVarSizeWord();
		}
		// }
	}

	public void sendFrame36(final int id, final int state) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(36);
			c.getOutStream().writeWordBigEndian(id);
			c.getOutStream().writeByte(state);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame70(final int i, final int o, final int id) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(70);
			c.getOutStream().writeWord(i);
			c.getOutStream().writeWordBigEndian(o);
			c.getOutStream().writeWordBigEndian(id);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame75(int npcid, int id) {
		// synchronized (c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(75);
			c.getOutStream().writeWordBigEndianA(npcid);
			c.getOutStream().writeWordBigEndianA(id);
			c.flushOutStream();
		}
		// }
	}

	public void sendFrame87(int id, int state) {
		if (c.getOutStream() != null) {
			c.getOutStream().createFrame(87);
			c.getOutStream().writeWordBigEndian_dup(id);
			c.getOutStream().writeDWord_v1(state);
			c.flushOutStream();
		}
	}

	public void sendFrame99(boolean s) {
		c.getOutStream().createFrame(99);
		c.getOutStream().writeByte(s ? 2 : 0);
	}

	public void sendLink(final String s) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSizeWord(187);
			c.getOutStream().writeString(s);
		}
		// }
	}

	public void sendPM(final long name, final int rights,
			final byte[] chatmessage, final int messagesize) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSize(196);
			c.getOutStream().writeQWord(name);
			c.getOutStream().writeDWord(c.lastChatId++);
			c.getOutStream().writeByte(rights);
			c.getOutStream().writeBytes(chatmessage, messagesize, 0);
			c.getOutStream().endFrameVarSize();
			c.flushOutStream();
			Misc.textUnpack(chatmessage, messagesize);
			Misc.longToPlayerName(name);
		}
		// }
	}

	public void sendStatement(final String s) {
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}

	public void setChatOptions(final int publicChat, final int privateChat,
			final int tradeBlock) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(206);
			c.getOutStream().writeByte(publicChat);
			c.getOutStream().writeByte(privateChat);
			c.getOutStream().writeByte(tradeBlock);
			c.flushOutStream();
		}
		// }
	}

	public void setPrivateMessaging(final int i) { // friends and ignore list
		// status
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(221);
			c.getOutStream().writeByte(i);
			c.flushOutStream();
		}
		// }
	}

	public void setSkillLevel(final int skillNum, final int currentLevel,
			final int XP) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(134);
			c.getOutStream().writeByte(skillNum);
			c.getOutStream().writeDWord_v1(XP);
			c.getOutStream().writeByte(currentLevel);
			c.flushOutStream();
		}
		// }
	}

	public void showInterface(final int interfaceid) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(97);
			c.getOutStream().writeWord(interfaceid);
			c.flushOutStream();
		}
		// }
	}

	public void showOption(final int i, final int l, final String s, final int a) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			if (!optionType.equalsIgnoreCase(s)) {
				optionType = s;
				c.getOutStream().createFrameVarSize(104);
				c.getOutStream().writeByteC(i);
				c.getOutStream().writeByteA(l);
				c.getOutStream().writeString(s);
				c.getOutStream().endFrameVarSize();
				c.flushOutStream();
			}
		}
		// }
	}

	public void vecnaSkull() {		
		if(System.currentTimeMillis() - c.lastVecna < 420000) {
			c.sendMessage("You can only use this once every 7 minutes.");
			return;
		}
		c.gfx100(725);
		c.playerLevel[6] = c.playerLevel[6] + 6; //adds the stat
		refreshSkill(6);
		c.getCombat().resetPlayerAttack(); 
		c.stopMovement();
		c.lastVecna = System.currentTimeMillis();	
	}

	/**
	 * Teleporting
	 **/
	public void spellTeleport(final int x, final int y, final int height) {
		if (c.isJailed)
			return;
        if (c.inZombieGame()) {
            c.ZombieGame.exitGame(false, c);
        }			

                        c.freezeTimer = 3;
		c.getPA().startTeleport(x, y, height,
				c.playerMagicBook == 1 ? "ancient" : "modern");

			c.dialogueAction = 0;
			c.teleAction = 0;
	}

	public void startTeleport(final int x, final int y, final int height,
			final String teleportType) {
		if (c.isJailed) {
			c.sendMessage("You are jailed please apeal at the forums.");
			c.sendMessage(""+Config.FORUM_URL);
			return;
		}
        if (c.inZombieGame()) {
            c.ZombieGame.exitGame(false, c);
        }		
                        c.freezeTimer = 3;
		if (c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if (c.inPcGame() || c.inPcBoat() || c.inPitsRoom() || c.inStart() || c.inWeaponGame() || FightPits.getState(c) != null 
				|| c.inDung() || c.inRFD()) {
			c.sendMessage("You can't teleport from this location!");
			return;
		}
		if (!c.doingAgility && !BountyHunter.safeArea(c) && c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL && !c.inFunPk() && !c.usingTeleCrystal == true) {
			c.sendMessage("You can't teleport above level "
					+ Config.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
			return;
		}
		if (System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if (Chest.inBarrows(c)) {
			c.resetShaking();
		}
		if (!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6) {
			if (c.playerIndex > 0 || c.npcIndex > 0) {
				c.getCombat().resetPlayerAttack();
			}
			c.resetShaking();
			c.stopMovement();
			removeAllWindows();
			for (int count = 0; count < c.godwarsKillCount.length; count++) {
				c.godwarsKillCount[count] = 0;
			}
			c.getPA().sendFrame126("@cya@0", 16217);
			c.getPA().sendFrame126("@cya@0", 16216);
			c.getPA().sendFrame126("@cya@0", 16218);
			c.getPA().sendFrame126("@cya@0", 16219);
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
			/*
			 * if (Config.SOUND) { c.sendSound(c.getSound().TELEPORT); }
			 */
			if (teleportType.equalsIgnoreCase("modern")) {
				if (c.inLobby()) {
					c.setSidebarInterface(13, 27124);
				}
				c.getTradeAndDuel().declineTrade();
				//c.setAnimation(Animation.create(8939));
				c.startAnimation(8939);
				c.teleTimer = 9;
				c.gfx0(1576);
				c.teleEndAnimation = 8941;
				c.usingTeleCrystal = false;
			}
			if (teleportType.equalsIgnoreCase("dung")) {
				c.getTradeAndDuel().declineTrade();
				//c.setAnimation(Animation.create(13652));
				c.startAnimation(13652);
				c.teleTimer = 16;
				c.gfx0(2602);
				c.usingTeleCrystal = false;
				c.teleEndAnimation = 13654;
			}
			if (teleportType.equalsIgnoreCase("ancient")) {
				if (c.inLobby()) {
					Dungeoneering.leaveParty(c);
					c.setSidebarInterface(13, 27124);
				}
				c.getTradeAndDuel().declineTrade();
				//c.setAnimation(Animation.create(9599));
				c.startAnimation(9599);
				c.teleGfx = 0;
				c.teleTimer = 9;
				c.teleEndAnimation = 65535;
				c.usingTeleCrystal = false;
				c.gfx0(1681);
			}
			if (teleportType.equalsIgnoreCase("lunar")) {
				//c.setAnimation(Animation.create(9606));
				c.startAnimation(9606);
				c.teleGfx = 0;
				c.teleTimer = 13;
				c.teleEndAnimation = 9013;
				c.usingTeleCrystal = false;
				c.gfx0(1685);
			}
			if (teleportType.equalsIgnoreCase("agility")) {
				//c.setAnimation(Animation.create(2750));
				c.startAnimation(2750);
				c.teleGfx = 0;
				c.teleTimer = 6;
				c.usingTeleCrystal = false;
				c.teleEndAnimation = 2588;
			}
		}
	}

	public void startTeleport2(final int x, final int y, final int height) {
		if (c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
        if (c.inZombieGame()) {
            c.ZombieGame.exitGame(false, c);
        }		
                        c.freezeTimer = 3;
		if (System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if (c.inRFD()) {
			c.sendMessage("You can't teleport out of this minigame.");
			return;
		}
		if (!c.isDead && c.teleTimer == 0) {
			c.stopMovement();
			removeAllWindows();
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
			//c.setAnimation(Animation.create(2140));
			c.startAnimation(2140);
			c.teleTimer = 11;
			c.teleGfx = 1577;
			c.teleEndAnimation = 8941;
		}
	}

	/**
	 * GFX
	 */
	public void stillGfx(final int id, final int x, final int y,
			final int height, final int time) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(y - c.getMapRegionY() * 8);
			c.getOutStream().writeByteC(x - c.getMapRegionX() * 8);
			c.getOutStream().createFrame(4);
			c.getOutStream().writeByte(0);
			c.getOutStream().writeWord(id);
			c.getOutStream().writeByte(height);
			c.getOutStream().writeWord(time);
			c.flushOutStream();
		}
		// }
	}

	public void stopDiagonal(final int otherX, final int otherY) {
		if (c.freezeDelay > 0) {
			return;
		}
		c.newWalkCmdSteps = 1;
		final int xMove = otherX - c.getX();
		int yMove = 0;
		if (xMove == 0) {
			yMove = otherY - c.getY();
			/*
			 * if (!clipHor) { yMove = 0; } else if (!clipVer) { xMove = 0; }
			 */
		}
		int k = c.getX() + xMove;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		int l = c.getY() + yMove;
		l -= c.mapRegionY * 8;

		for (int n = 0; n < c.newWalkCmdSteps; n++) {
			c.getNewWalkCmdX()[n] += k;
			c.getNewWalkCmdY()[n] += l;
		}

	}

	@SuppressWarnings("unused")
	public void updatePM(final int pID, final int world) { // used for private
		// chat updates
		final Player p = PlayerHandler.players[pID];
		if (p == null || p.playerName == null || p.playerName.equals("null")) {
			return;
		}
		final Player o = p;
		if (o == null) {
			return;
		}
		final long l = Misc
				.playerNameToInt64(PlayerHandler.players[pID].playerName);

		if (p.privateChat == 0) {
			for (final long friend : c.friends) {
				if (friend != 0) {
					if (l == friend) {
						loadPM(l, world);
						return;
					}
				}
			}
		} else if (p.privateChat == 1) {
			for (final long friend : c.friends) {
				if (friend != 0) {
					if (l == friend) {
						if (o.getPA().isInPM(
								Misc.playerNameToInt64(c.playerName))) {
							loadPM(l, world);
							return;
						} else {
							loadPM(l, 0);
							return;
						}
					}
				}
			}
		} else if (p.privateChat == 2) {
			for (final long friend : c.friends) {
				if (friend != 0) {
					if (l == friend && c.playerRights < 3) {
						loadPM(l, 0);
						return;
					}
				}
			}
		}
	}

	public void useCharge() {
		final int[][] glory = { { 1712, 1710, 3 }, { 1710, 1708, 2 },
				{ 1708, 1706, 1 }, { 1706, 1704, 1 } };
		for (final int[] element : glory) {
			if (c.itemUsing == element[0]) {
				if (c.isOperate) {
					c.playerEquipment[c.playerAmulet] = element[1];
				} else {
					c.getItems().deleteItem(element[0], 1);
					c.getItems().addItem(element[1], 1);
				}
				if (element[2] > 1) {
					c.sendMessage("Your amulet has " + element[2]
							+ " charges left.");
				} else {
					c.sendMessage("Your amulet has " + element[2]
							+ " charge left.");
				}
			}
		}
		c.getItems().updateSlot(c.playerAmulet);
		c.isOperate = false;
		c.itemUsing = -1;
	}

	public void vengMe() {
		if (System.currentTimeMillis() - c.lastVeng > 30000) {
			if (c.getItems().playerHasItem(557, 10)
					&& c.getItems().playerHasItem(9075, 4)
					&& c.getItems().playerHasItem(560, 2)) {
				c.vengOn = true;
				c.lastVeng = System.currentTimeMillis();
				//c.setAnimation(Animation.create(4410));
				c.startAnimation(4410);
				c.gfx100(726);
				c.getItems().deleteItem(557, c.getItems().getItemSlot(557), 10);
				c.getItems().deleteItem(560, c.getItems().getItemSlot(560), 2);
				c.getItems()
				.deleteItem(9075, c.getItems().getItemSlot(9075), 4);
			} else {
				c.sendMessage("You do not have the required runes to cast this spell. (9075 for astrals)");
			}
		} else {
			c.sendMessage("You must wait 30 seconds before casting this again.");
		}
	}

	public void walkableInterface(final int id) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(208);
			c.getOutStream().writeWordBigEndian_dup(id);
			c.flushOutStream();
		}
		// }
	}

	public void walkTo(final int i, final int j) {
		c.newWalkCmdSteps = 0;
		if (++c.newWalkCmdSteps > 50) {
			c.newWalkCmdSteps = 0;
		}
		int k = c.getX() + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		int l = c.getY() + j;
		l -= c.mapRegionY * 8;

		for (int n = 0; n < c.newWalkCmdSteps; n++) {
			c.getNewWalkCmdX()[n] += k;
			c.getNewWalkCmdY()[n] += l;
		}
	}

	public void walkTo2(final int i, final int j) {
		if (c.freezeDelay > 0) {
			return;
		}
		c.newWalkCmdSteps = 0;
		if (++c.newWalkCmdSteps > 50) {
			c.newWalkCmdSteps = 0;
		}
		int k = c.getX() + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		int l = c.getY() + j;
		l -= c.mapRegionY * 8;

		for (int n = 0; n < c.newWalkCmdSteps; n++) {
			c.getNewWalkCmdX()[n] += k;
			c.getNewWalkCmdY()[n] += l;
		}
	}

	public void walkToCheck(final int i, final int j) {
		if (c.freezeDelay > 0) {
			return;
		}
		c.newWalkCmdSteps = 0;
		if (++c.newWalkCmdSteps > 50) {
			c.newWalkCmdSteps = 0;
		}
		int k = c.getX() + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		int l = c.getY() + j;
		l -= c.mapRegionY * 8;

		for (int n = 0; n < c.newWalkCmdSteps; n++) {
			c.getNewWalkCmdX()[n] += k;
			c.getNewWalkCmdY()[n] += l;
		}
	}

	public void sendString(String s, int id) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSizeWord(126);
			c.getOutStream().writeString(s);
			c.getOutStream().writeWordA(id);
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public void ppsDegrade() {
		if (c.playerEquipment[c.playerWeapon] == 22494 && c.ppsLeft < 1) {
			c.playerEquipment[c.playerWeapon] = -1;
			c.playerEquipmentN[c.playerWeapon] = 0;
			c.getItems().wearItem(-1, 1, 3);
			c.getItems().addItem(22496, 1);
			c.autocasting = false;
			c.usingMagic = false;
			c.autocastId = -1;
			c.sendMessage("Your Polypore staff has no more charges and degrades! ");
		}
	}

	public void moveOnLogout(int x, int y, int h) {
		c.resetWalkingQueue();
		c.teleportToX = x;
		c.teleportToY = y;
		c.heightLevel = h;
		c.currentX = c.teleportToX - 8 * c.mapRegionX;
		c.currentY = c.teleportToY - 8 * c.mapRegionY;
		c.absX = c.teleportToX;
		c.absY = c.teleportToY;
		requestUpdates();
	}

	public void handleSteppingStone(Player c, int objectX, int objectY) {
		int x = objectX - c.getX();
		int y = objectY - c.getY();
		if (c.distanceToPoint(objectX, objectY) == 1) {
			walkTo(x, y);
		} else
			c.sendMessage("I can't reach that.");
	}

	public void serverMessage(String message) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player all = PlayerHandler.players[j];
				all.sendMessage(message);
			}
		}
	}

	public String getTotalAmount(Player c, int j) {
		if (j >= 10000 && j < 10000000) {
			return j / 1000 + "K";
		} else if (j >= 10000000 && j <= 2147483647) {
			return j / 1000000 + "M";
		} else {
			return "" + j + " gp";
		}
	}

	public int backupItems[] = new int[Config.BANK_SIZE];
	public int backupItemsN[] = new int[Config.BANK_SIZE];

	public int backupInvItems[] = new int[Config.BANK_SIZE];
	public int backupInvItemsN[] = new int[Config.BANK_SIZE];



	public void otherInv(Player c, Player o) {
		if (o == c || o == null || c == null)
			return;
		for (int i = 0; i < o.playerItems.length; i++) {
			backupInvItems[i] = c.playerItems[i];
			c.playerItemsN[i] = c.playerItemsN[i];
			c.playerItemsN[i] = o.playerItemsN[i];
			c.playerItems[i] = o.playerItems[i];
		}
		c.getItems().updateInventory();
		for (int i = 0; i < o.playerItems.length; i++) {
			c.playerItemsN[i] = backupInvItemsN[i];
			c.playerItems[i] = backupInvItems[i];
		}
	}

	/**
	 * Logging methods
	 */

	public void writeChatLog(String data) {
		Calendar Cal = new GregorianCalendar();
		int MONTH = Cal.get(Calendar.MONTH);
		int DAY = Cal.get(Calendar.DAY_OF_MONTH);
		int HOUR = Cal.get(Calendar.HOUR_OF_DAY);
		int MIN = Cal.get(Calendar.MINUTE);
		int YEAR = Cal.get(Calendar.YEAR);
		String filePath = "./Data/Logs/Chat/" + c.playerName + ".txt";
		BufferedWriter bw = null;

		try 
		{				
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[Date: "+DAY+"-"+MONTH+"-"+YEAR+"]" + "-" + "[Time: "+HOUR+":"+MIN+"]" + "- " + data + " ");
			bw.newLine();
			bw.flush();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		} 
		finally 
		{
			if (bw != null)
			{
				try 
				{
					bw.close();
				} 
				catch (IOException ioe2) 
				{
				}
			}
		}
	}

	public void writeCommandLog(String data) {
		Calendar Cal = new GregorianCalendar();
		int MONTH = Cal.get(Calendar.MONTH);
		int DAY = Cal.get(Calendar.DAY_OF_MONTH);
		int HOUR = Cal.get(Calendar.HOUR_OF_DAY);
		int MIN = Cal.get(Calendar.MINUTE);
		int YEAR = Cal.get(Calendar.YEAR);
		String filePath = "./Data/Logs/Command/" + c.playerName + ".txt";
		BufferedWriter bw = null;

		try 
		{				
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[Date: "+DAY+"-"+MONTH+"-"+YEAR+"]" + "-" + "[Time: "+HOUR+":"+MIN+"]" + "- " + data + " ");
			bw.newLine();
			bw.flush();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		} 
		finally 
		{
			if (bw != null)
			{
				try 
				{
					bw.close();
				} 
				catch (IOException ioe2) 
				{
				}
			}
		}
	}

	public void writePMLog(String data) {
		Calendar Cal = new GregorianCalendar();
		int MONTH = Cal.get(Calendar.MONTH);
		int DAY = Cal.get(Calendar.DAY_OF_MONTH);
		int HOUR = Cal.get(Calendar.HOUR_OF_DAY);
		int MIN = Cal.get(Calendar.MINUTE);
		int YEAR = Cal.get(Calendar.YEAR);
		String filePath = "./Data/Logs/PM/" + c.playerName + ".txt";
		BufferedWriter bw = null;

		try 
		{				
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[Date: "+DAY+"-"+MONTH+"-"+YEAR+"]" + "-" + "[Time: "+HOUR+":"+MIN+"]" + "- " + data + " ");
			bw.newLine();
			bw.flush();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		} 
		finally 
		{
			if (bw != null)
			{
				try 
				{
					bw.close();
				} 
				catch (IOException ioe2) 
				{
				}
			}
		}
	}

	public void globalMessage(int color, String data) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player c2 = (Player) PlayerHandler.players[j];
				if (c2 != null)
					c2.sendMessage("<img=1><col=" + color + ">[Server]:" + data);
			}
		}
	}

	public static void addPoints(Player c, String type, int amt) {
		if(type.equals("prestige"))
			c.prestigePoints += amt;
	}

	public String getSkillName(int i) {
		if (i < 0 || i > 24)
			return "";
		if (i == 0)
			return "Attack";
		if (i == 1)
			return "Defence";
		if (i == 2)
			return "Strength";
		if (i == 3)
			return "Hitpoints";
		if (i == 4)
			return "Range";
		if (i == 5)
			return "Prayer";
		if (i == 6)
			return "Magic";
		if (i == 7)
			return "Cooking";
		if (i == 8)
			return "Woodcutting";
		if (i == 9)
			return "Fletching";
		if (i == 10)
			return "Fishing";
		if (i == 11)
			return "Firemaking";
		if (i == 12)
			return "Crafting";
		if (i == 13)
			return "Smithing";
		if (i == 14)
			return "Mining";
		if (i == 15)
			return "Herblore";
		if (i == 16)
			return "Agility";
		if (i == 17)
			return "Thieving";
		if (i == 18)
			return "Slayer";
		if (i == 19)
			return "Farming";
		if (i == 20)
			return "Runecrafting";
		if (i == 21)
			return "Hunter";
		if (i == 22)
			return "Summoning";
		if (i == 23)
			return "Dungeoneering";
		return "";
	}

	public static void stopSkilling(Player c) {
		//if(c.isCrafting && !c.needsCraftDelay)
		//Crafting.resetCrafting(c);
		//if (c.fishing)
		//Fishing.resetVariables(c);
		//if (c.isCrafting)
		//Crafting.resetCrafting(c);
		//if (c.mining[0] > 0)
		//Mining.resetMining(c);
		//c.smeltInterface = false;
		//c.smeltType = 0;
		//c.smeltAmount = 0;
		//if (c.fletchItem > 0)
		//Fletching.resetFletching(c);
		//CycleEventHandler.getSingleton().stopEvents(c, c.fletchingEventId);
		//CycleEventHandler.getSingleton().stopEvents(c, c.miningEventId);
	}

	/**
	 * Starts Prestige Ability Cycle
	 */
	public static void startAbilityCycle(final Player c) {
		Player.getTaskScheduler().schedule(new Task(2, true) {
			protected void execute() {
				if(c.disconnected || c == null)
					stop();
				if(c.doubleExp) {
					c.doubleExpTime++;
					if(c.doubleExpTime >= 3600) {
						c.sendMessage("<col=250>You stop recieving double exp.");
						c.doubleExpTime = 0;
						c.doubleExp = false;
						c.saveGame();
					}
				}
				if(c.raiseDropRate) {
					c.dropRateTime++;
					if(c.dropRateTime >= 3600) {
						c.sendMessage("<col=250>You stop recieving higher drop rates.");
						c.dropRateTime = 0;
						c.raiseDropRate = false;
						c.saveGame();
					}
				}
				if(c.undrainPrayer) {
					c.undrainPrayerTime++;
					if(c.undrainPrayerTime >= 1800) {
						c.sendMessage("<col=250>Your stop recieving undraining prayer.");
						c.undrainPrayerTime = 0;
						c.undrainPrayer = false;
						c.saveGame();
					}
				}
				if(c.raiseCharmRate) {
					c.charmRateTime++;
					if(c.charmRateTime >= 7200) {
						c.sendMessage("<col=250>You stop recieving higher charm rates.");
						c.charmRateTime = 0;
						c.raiseCharmRate = false;
						c.saveGame();
					}
				}
				if(!c.doubleExp && !c.raiseDropRate && !c.raiseCharmRate && !c.undrainPrayer)
					stop();
			}
		});
	}

	public void sendError(String s) {
		sendError(s, true);
	}

	public void sendError(String s, boolean i) {
		String icon = i ? "<yell=1>" : "";
		String clr = "";
		c.sendMessage(icon + "" + clr + "" + s);
	}

	public int getCarriedWealth() { 
		int toReturn = 0;         
		for (int i = 0; i < c.playerEquipment.length; i++) {             
			if (c.playerEquipment[i] > 0 && Server.itemHandler.ItemList[c.playerEquipment[i]] != null)
				toReturn += (Server.itemHandler.ItemList[c.playerEquipment[i]].ShopValue * c.playerEquipmentN[i]);         
		}
		for (int i = 0; i < c.playerItems.length; i++) {             
			if (c.playerItems[i] > 0 && Server.itemHandler.ItemList[c.playerItems[i]] != null)                 
				toReturn += (Server.itemHandler.ItemList[c.playerItems[i]].ShopValue * c.playerItemsN[i]);         
		}         
		return toReturn;               
	}

	public static void sendQC(Player c, int skillId) {
		String skillName = c.getPA().getSkillName(skillId);
		c.forcedText = "[QC] My " + skillName + " level is  "
				+ c.getPA().getLevelForXP(c.playerXP[skillId]) + ".";
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
	}

	public static void dfsCreate(final Player c, final int i) {
		int visage = 11286;
		int antishield = 1540;
		int dfs = 11283;
		int dfsxp = 5000;
		int hammer = 2347;
		if (!c.getItems().playerHasItem(hammer, 1)) {
			c.getDH().sendStatement("You don't have a hammer with you!");
			return;
		} else {
			switch (i) {
			case 11286: // visage
				if (c.getItems().playerHasItem(antishield, 1)) {
					if (c.getLevelForXP(c.playerXP[13]) >= 90) {
						//c.setAnimation(Animation.create(898));
						c.startAnimation(898);
						c.getItems().deleteItem(visage, 1);
						c.getItems().deleteItem(antishield, 1);
						c.getItems().addItem(dfs, 1);
						c.getPA().addSkillXP(dfsxp * SkillHandler.SMITHING_XP, 13);
						c.getPA().refreshSkill(13);
						c.sendMessage("You successfully make a Dragonfire Shield!");
					} else {
						c.getDH().sendStatement("You require a Smithing level of 90 to make this item.");
					}

				} else {
					c.getDH().sendStatement("You lack the required items to make a Dragonfire Shield.");
				}
				return;
			case 1540: // antishield
				if (c.getItems().playerHasItem(visage, 1)) {
					if (c.getLevelForXP(c.playerXP[13]) >= 90) {
						//c.setAnimation(Animation.create(898));
						c.startAnimation(898);
						c.getItems().deleteItem(visage, 1);
						c.getItems().deleteItem(antishield, 1);
						c.getItems().addItem(dfs, 1);
						c.getPA().addSkillXP(dfsxp * SkillHandler.SMITHING_XP, 13);
						c.getPA().refreshSkill(13);
						c.sendMessage("You successfully make a Dragonfire Shield!");
					} else {
						c.getDH().sendStatement("You require a Smithing level of 90 to make this item.");
					}
				} else {
					c.getDH().sendStatement("You lack the required items to make a Dragonfire Shield.");
				}
				return;
			}
		}
	}

	public void resetSkills() {
		c.playerIsFishing = false;
		c.craftingTeletabs = false;
		c.trainingPrayer = false;
		CycleEventHandler.getSingleton().stopEvent(c.getSkilling());
		//c.doingWoodcutting = false;
		for (int i = 0; i < c.playerSkilling.length; i++) {
			c.playerSkilling[i] = false;
		}
	}

}
