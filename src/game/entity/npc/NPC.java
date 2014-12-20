package game.entity.npc;

import engine.util.Misc;
import engine.util.Stream;
import game.clip.region.Region;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class NPC {
	int npcSize;
	public int npcId, index, followPlayer, npcType, npcslot;
	public int lastSummon;
	public boolean walkingHome, underAttack, summoner;
	public boolean needsToRespawn;
	public boolean summonedNPC;
	public int absX, absY;
	public int diliHits = 30, nexStage = 0, cooldown = 0, mustDie = 0;
	public int killNpc = 0;
	public boolean spec = false, extraHit = false;
	public long debugLong;
	public final int[][] fearShadow = new int[100][100];
	public final int[][] CONTAIN_THIS = new int[2][2];
	public int prayerUsed = 0;
	public int heightLevel;
	public int makeX, makeY, maxHit, defence, attack, moveX, moveY, direction,
	walkingType;
	public int spawnX, spawnY;
	public int transformId = -1;
	public boolean transformUpdateRequired;
	public int viewX, viewY;
	public int extraHitDelay = 0, kbdAttack, moleStage, troll, chaosEle,
			kQueen, mejKot, jad, kree, dragon, cHorror, zilyana, graardor,
			tsutsaroth, tarn, glod, deadCycle = 0, jadRange = -1, untransformTimer = 0;
	public int hitIcon, hitIcon2, hitMask, hitMask2;

	public String Graardor() {
		int quote = Misc.random(9);
		switch (quote) {
		case 0:
			return "Death to our enemies!";
		case 1:
			return "Brargh!";
		case 2:
			return "Break their bones!";
		case 3:
			return "For the glory of the Big High War God!";
		case 4:
			return "Split their skulls!";
		case 5:
			return "We feast on the bones of our enemies tonight!";
		case 6:
			return "CHAAARGE!";
		case 7:
			return "Crush them underfoot!";
		case 8:
			return "All glory to Bandos!";
		case 9:
			return "GRAAAAAAAAAR!";
		}
		return "";
	}

	public String Tsutsaroth() {
		int quote = Misc.random(8);
		switch (quote) {
		case 0:
			return "Attack them, you dogs!";
		case 1:
			return "Forward!";
		case 2:
			return "Death to Saradomin's dogs!";
		case 3:
			return "Kill them you cowards!";
		case 4:
			return "The Dark One will have their souls!";
		case 5:
			return "Zamorak curse them!";
		case 6:
			return "Rend them limb from limb!";
		case 7:
			return "No retreat!";
		case 8:
			return "Flay them all!!";
		}
		return "";
	}

	public String Zilyana() {
		int quote = Misc.random(9);
		switch (quote) {
		case 0:
			return "Death to the enemies of the light!";
		case 1:
			return "Slay the evil ones!";
		case 2:
			return "Saradomin lend me strength!";
		case 3:
			return "By the power of Saradomin!";
		case 4:
			return "May Saradomin be my sword!";
		case 5:
			return "Good will always triumph!";
		case 6:
			return "Forward! Our allies are with us!";
		case 7:
			return "Saradomin is with us!";
		case 8:
			return "In the name of Saradomin!";
		case 9:
			return "Attack! Find the Godsword!";
		}
		return "";
	}

	public String Kree() {
		int quote = Misc.random(6);
		switch (quote) {
		case 0:
			return "Attack with your talons!";
		case 1:
			return "Face the wratch of Armadyl";
		case 2:
			return "SCCCRREEEEEEEEEECHHHHH";
		case 3:
			return "KA KAW KA KAW";
		case 4:
			return "Fight my minions!";
		case 5:
			return "Good will always triumph!";
		case 6:
			return "Attack! Find the Godsword!";
		}
		return "";
	}

	public String Glod() {
		int talk = Misc.random(2);
		switch (talk) {
		case 1:
			return "Glod Angry!";
		case 2:
			return "Glod Bash!";
		}
		return "Glod Smash!";
	}

	/**
	 * attackType: 0 = melee, 1 = range, 2 = mage
	 */
	public int attackType, projectileId, endGfx, spawnedBy, hitDelayTimer, HP,
	MaxHP, hitDiff, animNumber, actionTimer, enemyX, enemyY;
	public boolean applyDead, isDead, needRespawn, respawns;
	public int freezeTimer, attackTimer, killerId, killedBy, oldIndex,
	underAttackBy;
	public long lastDamageTaken;
	public boolean randomWalk;
	public boolean dirUpdateRequired;
	public int npcIndex;
	public boolean summon;
	public int lastsummon;
	public int attackingnpc;
	public int underAttackBy2;
	public int oldIndexNPC;
	public int attacknpc;
	public boolean animUpdateRequired;
	public boolean hitUpdateRequired;
	public boolean updateRequired;
	public boolean forcedChatRequired;
	public boolean faceToUpdateRequired;
	public int firstAttacker;
	public String forcedText;

	/**
	 * Graphics
	 **/
	public int mask80var1 = 0;
	public int mask80var2 = 0;
	protected boolean mask80update = false;

	public void forceAnim(int number) {
		animNumber = number;
		animUpdateRequired = true;
		updateRequired = true;
	}

	public int getAbsX() {
		return absX;
	}

	public int getAbsY() {
		return absY;
	}

	/**
	 * Face
	 */
	public int FocusPointX = -1, FocusPointY = -1;
	public int face = 0;
	public long delay;
	public int hitDiff2 = 0;
	public boolean hitUpdateRequired2 = false, noDeathEmote;
	public boolean multiAttack;

	public NPC(final int _npcId, final int _npcType) {
		npcId = _npcId;
		npcType = _npcType;
		direction = -1;
		isDead = false;
		applyDead = false;
		actionTimer = 0;
		randomWalk = true;
	}

	public void dealDamage(int damage) {
		if (damage > HP) {
			damage = HP;
		}
		HP -= damage;
	}

	public void startAnimation(int animId) {
		animNumber = animId;
		animUpdateRequired = true;
		updateRequired = true;
	}

	public boolean inGodWarsBoss2() { // zammy
		if (heightLevel == 2 || heightLevel == 1 || heightLevel == 0) {
			if (absX > 2917 && absX < 2947 && absY > 5316 && absY < 5332) {
				return true;
			}
		}
		return false;
	}

	public boolean inGodWarsBoss1() {
		if (heightLevel == 2 || heightLevel == 1 || heightLevel == 0) {
			if (absX > 2822 && absX < 2843 && absY > 5295 && absY < 5309) {
				return true;
			}
		}
		return false;
	}

	public boolean inGodWarsBoss() {
		if (heightLevel == 2 || heightLevel == 1 || heightLevel == 0) {
			if (absX > 2863 && absX < 2879 && absY > 5351 && absY < 5372) {
				return true;
			}
		}
		return false;
	}

	public boolean inGodWarsBoss3() { // sara
		if (heightLevel == 2 || heightLevel == 1 || heightLevel == 0) {
			if (absX > 2886 && absX < 2908 && absY > 5255 && absY < 5277) {
				return true;
			}
		}
		return false;
	}

	public void facenpc(int npc) {
		face = npc;
		dirUpdateRequired = true;
		updateRequired = true;
	}

	public void faceNPC(int npc) {
		face = npc;
		dirUpdateRequired = true;
		updateRequired = true;
	}

	public void appendAnimUpdate(final Stream str) {
		str.writeWordBigEndian(animNumber);
		str.writeByte(1);
	}

	public void appendFaceEntity(final Stream str) {
		str.writeWord(face);
	}

	public void appendTransformUpdate(Stream str) {
		str.writeWordBigEndianA(transformId);
	}

	public void appendFaceToUpdate(final Stream str) {
		str.writeWordBigEndian(viewX);
		str.writeWordBigEndian(viewY);
	}

	public void appendHitUpdate(final Stream str) {
		if (HP <= 0) {
			isDead = true;
		}
		str.writeByteC(hitDiff);
		str.writeByteS(hitMask);
		str.writeByte(hitIcon);
		str.writeByteS(Misc.getCurrentHP(HP, MaxHP, 100));
		str.writeByteC(100);
	}

	public boolean animals() {
		switch (npcType) {
		case 5103:
		case 5104:
		case 5105:
			return true;
		default:
			return false;
		}
	}

	public void requestTransform(int id) {
		transformId = id;
		transformUpdateRequired = true;
		updateRequired = true;
	}

	public int mageDef() {
		switch (npcType) {
		case 2665:
			return 300;
		case 2882:
			return 350;
		case 2881:
			return 150;
		case 2700:
			return 320;
		case 2059:
			return 220;
		case 3137:
			return 0;
		case 908:
			return 35;
		case 909:
			return 50;
		case 910:
			return 70;
		case 911:
		case 3072:
			return 100;
		default:
			return 20;
		}
	}

	public static void appendDemon(Player p) {
		for (NPC demon : NPCHandler.npcs) {
			if (demon != null) {
				if (demon.npcType == 8349
						&& !Player.protMage
						&& !Player.protRange
						&& Player.protMelee) {
					if (p.usingMagic) {
						demon.requestTransform(8350);
						Player.protMageTimer = Misc.random(4) + 14;
						Player.protMage = true;
						Player.protMelee = false;
						Player.protRange = false;
						//demon.npcType = 8350;
					} else {
						demon.requestTransform(8351);
						Player.protRangeTimer = Misc.random(4) + 14;
						Player.protMage = false;
						Player.protMelee = false;
						Player.protRange = true;
						//demon.npcType = 8351;
					}
					demon.underAttack = true;
					demon.killerId = p.playerId;
					demon.facePlayer(p.playerId);
				} else if (demon.npcType == 8349 
						&& Player.protMage
						&& !Player.protRange
						&& !Player.protMelee) {
					if (p.usingRangeWeapon) {
						demon.requestTransform(8351);
						Player.protRangeTimer = Misc.random(4) + 14;
						Player.protMage = false;
						Player.protMelee = false;
						Player.protRange = true;
						//demon.npcType = 8351;
					} else {
						demon.requestTransform(8349);
						Player.protMeleeTimer = Misc.random(4) + 14;
						Player.protMage = false;
						Player.protMelee = true;
						Player.protRange = false;
						//demon.npcType = 8349;
					}
					demon.underAttack = true;
					demon.killerId = p.playerId;
					demon.facePlayer(p.playerId);
				} else if (demon.npcType == 8349 
						&& !Player.protMage
						&& !Player.protMelee
						&& Player.protRange) {
					if (p.usingMagic) {
						demon.requestTransform(8350);
						Player.protMageTimer = Misc.random(4) + 14;
						Player.protMage = true;
						Player.protMelee = false;
						Player.protRange = false;
						//demon.npcType = 8350;
					} else {
						demon.requestTransform(8349);
						Player.protMeleeTimer = Misc.random(4) + 14;
						Player.protMage = false;
						Player.protMelee = true;
						Player.protRange = false;
						//demon.npcType = 8349;
					}
					demon.underAttack = true;
					demon.killerId = p.playerId;
					demon.facePlayer(p.playerId);
				}
			}
		}
	}

	public static void appendSmound(Player p) {
		
		for (NPC smound : NPCHandler.npcs) {
			if (smound != null) {
				if(smound.npcType == 9462) {
					smound.requestTransform(9463);
				} else if (smound.npcType == 9464) {
					smound.requestTransform(9465);
				} else if (smound.npcType == 9466) {
					smound.requestTransform(9467);

				}
			}
		}

	}
	
	public static void appendSmound(NPC n) {
		for (int i = 0; i < NPCHandler.maxNPCs; i++) {
			
				if(n.npcType == 9462) {
					//n[i].requestTransform(5636);
				} else if (NPCHandler.npcs[i].npcType == 9464) {
					n.requestTransform(9465);
				} else if (NPCHandler.npcs[i].npcType == 9466) {
					n.requestTransform(9467);

				}
		}

	}

	public int getTotalNPCSize() {
		int dis = npcSize;
		if (spawnedBy <= 0) {
			if (killerId > 0) {
				try {
					Player c = PlayerHandler.players[killerId];
					if (c.absY < absY || c.absX < absX) {
						dis = 1;
					}
				} catch (Exception e) {
				}
			}
		} else {
			try {
				Player c1 = PlayerHandler.players[spawnedBy];
				if (c1.absY < absY || c1.absX < absX) {
					dis = 1;
				}
			} catch (Exception e) {
			}
		}
		return dis;
	}

	public void appendHitUpdate2(final Stream str) {
		if (HP <= 0) {
			isDead = true;
		}
		str.writeByteA(hitDiff2);
		str.writeByteC(hitMask2);
		str.writeByte(hitIcon2);
		str.writeByteA(HP);
		str.writeByte(MaxHP);
	}

	public void appendMask80Update(final Stream str) {
		str.writeWord(mask80var1);
		str.writeDWord(mask80var2);
	}

	public void appendNPCUpdateBlock(Stream str) {
		if (!updateRequired)
			return;
		int updateMask = 0;
		if (animUpdateRequired)
			updateMask |= 0x10;
		if (hitUpdateRequired2)
			updateMask |= 8;
		if (mask80update)
			updateMask |= 0x80;
		if (dirUpdateRequired)
			updateMask |= 0x20;
		if (forcedChatRequired)
			updateMask |= 1;
		if (hitUpdateRequired)
			updateMask |= 0x40;
		if (transformUpdateRequired)
			updateMask |= 2;
		if (FocusPointX != -1)
			updateMask |= 4;

		str.writeByte(updateMask);

		if (animUpdateRequired)
			appendAnimUpdate(str);
		if (hitUpdateRequired2)
			appendHitUpdate2(str);
		if (mask80update)
			appendMask80Update(str);
		if (dirUpdateRequired)
			appendFaceEntity(str);
		if (forcedChatRequired) {
			str.writeString(forcedText);
		}
		if (hitUpdateRequired)
			appendHitUpdate(str);
		if (transformUpdateRequired)
			appendTransformUpdate(str);
		if (FocusPointX != -1)
			appendSetFocusDestination(str);

	}

	private void appendSetFocusDestination(final Stream str) {
		str.writeWordBigEndian(FocusPointX);
		str.writeWordBigEndian(FocusPointY);
	}

	public void clearUpdateFlags() {
		updateRequired = false;
		forcedChatRequired = false;
		hitUpdateRequired = false;
		hitUpdateRequired2 = false;
		animUpdateRequired = false;
		dirUpdateRequired = false;
		transformUpdateRequired = false;
		mask80update = false;
		forcedText = null;
		moveX = 0;
		moveY = 0;
		direction = -1;
		FocusPointX = -1;
		FocusPointY = -1;
	}

	public boolean goodDistance(final int objectX, final int objectY,
			final int playerX, final int playerY, final int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if (objectX + i == playerX
						&& (objectY + j == playerY || objectY - j == playerY || objectY == playerY)) {
					return true;
				} else if (objectX - i == playerX
						&& (objectY + j == playerY || objectY - j == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX
						&& (objectY + j == playerY || objectY - j == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}

	public void otherNPC() {
		if (killerId > 0)
			return;
		/*
		 * if(npcType == 3150 || npcType == 3741 || npcType == 3751 || npcType
		 * == 4479 || npcType == 4492 || npcType == 6257 || npcType == 6255 ||
		 * npcType == 6218 || npcType == 6233 || npcType == 6212 || npcType ==
		 * 6258 || npcType == 6240 || npcType == 6245 || npcType == 6214) {
		 */
		for (int i = 0; i < NPCHandler.maxNPCs; i++) {
			try {
				NPC n = NPCHandler.npcs[i];
				if (npcType == 3150) {
					if (n.npcType == 3137) {
						if (n == null || killNpc == i) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(),
								10) || heightLevel != n.heightLevel) {
							return;
						}
						underAttack = true;
						killNpc = i;
						randomWalk = false;
						break;
					}
				} else if (npcType == 3751) {
					if (n.npcType >= 3777 && n.npcType <= 3780) {
						if (n == null || killNpc == i) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(), 5)
								|| heightLevel != n.heightLevel) {
							return;
						}
						if (!n.isDead) {
							underAttack = true;
							killNpc = i;
							randomWalk = false;
							break;
						}
					}
				} else if (npcType == 3741) {
					if (n.npcType == 3782) {
						if (n == null || killNpc == i) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(), 4)
								|| heightLevel != n.heightLevel) {
							return;
						}
						underAttack = true;
						killNpc = i;
						randomWalk = false;
						break;
					}
				} else if (npcType == 4479 || npcType == 4492) {
					if (n.npcType == (npcType == 4479 ? 4492 : 4479)) {
						if (n == null || killNpc > 0) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(),
								25) || heightLevel != n.heightLevel) {
							return;
						}
						if (!n.isDead) {
							if (!underAttack) {
								underAttack = true;
								killNpc = (npcType == 4479 ? npcId + 1
										: npcId - 1);
								randomWalk = false;
							}
						}
						break;
					}
				} else if (npcType == 6257 || npcType == 6255
						|| npcType == 6218) {
					if (n.npcType == (npcType == 6218 ? (Misc.random(1) == 0 ? 6257
							: 6255)
							: 6218)) {
						if (n == null || killNpc > 0) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(),
								15) || heightLevel != n.heightLevel) {
							return;
						}
						if (!n.isDead) {
							if (!underAttack) {
								underAttack = true;
								killNpc = i;
								randomWalk = false;
							}
						}
						break;
					}
				} else if (npcType == 6233 || npcType == 6212) {
					if (n.npcType == (npcType == 6212 ? 6233 : 6212)) {
						if (n == null || killNpc > 0) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(),
								15) || heightLevel != n.heightLevel) {
							return;
						}
						if (!n.isDead) {
							if (!underAttack) {
								underAttack = true;
								killNpc = i;
								randomWalk = false;
							}
						}
						break;
					}
				} else if (npcType == 6240) {
					if (n.npcType == 6258) {
						if (n == null || killNpc > 0) {
							return;
						}
						if (!n.isDead
								&& (goodDistance(getX(), getY(), n.getX(),
										n.getY(), 5) || heightLevel == n.heightLevel)) {
							if (!underAttack) {
								underAttack = true;
								killNpc = i;
								randomWalk = false;
							}
						} else {
							// i++;
						}
						break;
					}
				} else if (npcType == 6245 || npcType == 6214) {
					if (n.npcType == (npcType == 6245 ? 6214 : 6245)) {
						if (n == null || killNpc > 0) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(),
								15) || heightLevel != n.heightLevel) {
							return;
						}
						if (!n.isDead) {
							if (!underAttack) {
								underAttack = true;
								killNpc = i;
								randomWalk = false;
							}
						}
						break;
					}
				} else if (npcType == 6269) {
					if (n.npcType == 6210) {
						if (n == null || killNpc > 0) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(),
								15) || heightLevel != n.heightLevel) {
							return;
						}
						if (!n.isDead) {
							if (!underAttack) {
								underAttack = true;
								killNpc = i;
								randomWalk = false;
							}
						}
						break;
					}
				} else if (npcType == 6215 || npcType == 6271) {
					if (n.npcType == (npcType == 6271 ? 6215 : 6271)) {
						if (n == null || killNpc > 0) {
							return;
						}
						if (!goodDistance(getX(), getY(), n.getX(), n.getY(),
								15) || heightLevel != n.heightLevel) {
							return;
						}
						if (!n.isDead) {
							if (!underAttack) {
								underAttack = true;
								killNpc = i;
								randomWalk = false;
							}
						}
						break;
					}
				} else if (npcType == 6219) {
					if (n.npcType == 6258) {
						if (n.makeX >= 2888 && n.makeX <= 2899
								&& n.makeY >= 5292 && n.makeY <= 5300) {
							if (n == null || killNpc > 0) {
								return;
							}
							if (!goodDistance(getX(), getY(), n.getX(),
									n.getY(), 10)
									|| heightLevel != n.heightLevel) {
								return;
							}
							if (!n.isDead) {
								if (!underAttack) {
									underAttack = true;
									killNpc = i;
									randomWalk = false;
								}
							}
							break;
						}
					}
				}
			} catch (Exception e) {
			}
		}
		// }
	}

	public void facePlayer(int player) {
		face = player + 32768;
		dirUpdateRequired = true;
		updateRequired = true;
	}

	/**
	 * Text update
	 **/
	public void forceChat(final String text) {
		forcedText = text;
		forcedChatRequired = true;
		updateRequired = true;
	}

	public void getNextNPCMovement(final int i) {
		direction = -1;
		if (moveX != 0 || moveY != 0) {
			if (!walkingHome) {
				int step[] = Region.getNextStep(absX, absY, absX + moveX, absY
						+ moveY, heightLevel % 4, getNPCSize(), getNPCSize());
				moveX = step[0] - absX;
				moveY = step[1] - absY;
			}
		}
		if (NPCHandler.npcs[i].freezeTimer == 0 
				&& NPCHandler.npcs[i].npcType != 9462
				&& NPCHandler.npcs[i].npcType != 9464 
				&& NPCHandler.npcs[i].npcType != 9466) {
			direction = getNextWalkingDirection();
		}
	}

	public int getNextWalkingDirection() {
		int dir;
		dir = Misc.direction(absX, absY, absX + moveX, absY + moveY);
		if (dir == -1) {
			return -1;
		}
		dir >>= 1;
			absX += moveX;
			absY += moveY;
			return dir;
	}

	/**
	 * NPC Tile Clipping (uncomment and fix)
	 */
	/*
	 * private Tile currentTile;
	 * 
	 * public int getNextWalkingDirection() { currentTile = new Tile(absX +
	 * moveX, absY + moveY, heightLevel);
	 * if(!WalkingCheck.tiles.containsKey(currentTile.getH() << 28 |
	 * currentTile.getX() << 14 | currentTile.getY())){ int dir; dir =
	 * Misc.direction(absX, absY, (absX + moveX), (absY + moveY)); if (dir ==
	 * -1) return -1; dir >>= 1; absX += moveX; absY += moveY; return dir; }
	 * else if(WalkingCheck.tiles.get(currentTile.getH() << 28 |
	 * currentTile.getX() << 14 | currentTile.getY()) == true) { return -1; }
	 * else { return -1; } }
	 */

	public int getX() {
		return absX;
	}

	public int getY() {
		return absY;
	}

	public int getNPCSize() {
		return NPCSize.getNPCSize(npcType);
	}

	public void gfx0(final int gfx) {
		mask80var1 = gfx;
		mask80var2 = 65536;
		mask80update = true;
		updateRequired = true;
	}

	public void gfx100(final int gfx) {
		mask80var1 = gfx;
		mask80var2 = 6553600;
		mask80update = true;
		updateRequired = true;
	}

	/*
	 * public void handleHitMask(final int damage) { if (!hitUpdateRequired) {
	 * hitUpdateRequired = true; hitDiff = damage; } else if
	 * (!hitUpdateRequired2) { hitUpdateRequired2 = true; hitDiff2 = damage; }
	 * updateRequired = true; }
	 */

	public void handleHitMask(int damage, int mask, int icon) {
		if (!hitUpdateRequired) {
			hitUpdateRequired = true;
			hitDiff = damage;
			hitMask = mask;
			hitIcon = icon;
		} else if (!hitUpdateRequired2) {
			hitUpdateRequired2 = true;
			hitDiff2 = damage;
			hitMask2 = mask;
			hitIcon2 = icon;
		}
		updateRequired = true;
	}

	public void handleDeflect(int damage) {
		if (!hitUpdateRequired) {
			hitUpdateRequired = true;
			hitDiff = damage;
			hitMask = 0;
			hitIcon = 3;
		} else if (!hitUpdateRequired2) {
			hitUpdateRequired2 = true;
			hitDiff2 = damage;
			hitMask2 = 0;
			hitIcon2 = 3;
		}
		updateRequired = true;
	}

	public boolean inCorp() {
		return absX >= 2886 && absX <= 2916 && absY >= 4378 && absY <= 4409;
	}

	public boolean inGodWars() {
		if (heightLevel == 2 || heightLevel == 1 || heightLevel == 0) {
			if (absX > 2782 && absX < 2960 && absY > 5224 && absY < 5378) {
				return true;
			}
		}
		return false;
	}

	public boolean inZombieCaves() {
		return absX >= 3240 && absX <= 3256 && absY >= 9353 && absY <= 9376;
	}	

	public boolean inMulti() {
		if (absX >= 3136 && absX <= 3327 && absY >= 3519 && absY <= 3607
				|| absX >= 3190 && absX <= 3327 && absY >= 3648 && absY <= 3839
				|| absX >= 3200 && absX <= 3390 && absY >= 3840 && absY <= 3967
				|| absX >= 2992 && absX <= 3007 && absY >= 3912 && absY <= 3967
				|| absX > 3193 && absX < 3279 && absY > 9272 && absY < 9343
				|| absX >= 2908 && absX <= 2941 && absY >= 5186 && absY <= 5220 //NEX
				|| absX >= 2946 && absX <= 2959 && absY >= 3816 && absY <= 3831
				|| absX >= 3008 && absX <= 3199 && absY >= 3856 && absY <= 3903
				|| absX >= 3008 && absX <= 3071 && absY >= 3600 && absY <= 3711
				|| absX >= 3072 && absX <= 3327 && absY >= 3608 && absY <= 3647
				|| absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619
				|| absX >= 2371 && absX <= 2422 && absY >= 5062 && absY <= 5117
				|| absX >= 2896 && absX <= 2927 && absY >= 3595 && absY <= 3630
				|| absX >= 2892 && absX <= 2932 && absY >= 4435 && absY <= 4464
				|| absX >= 2256 && absX <= 2287 && absY >= 4680 && absY <= 4711
				|| absX >= 2516 && absX <= 2595 && absY >= 4926 && absY <= 5003
				|| absX >= 3151 && absX <= 3198 && absY >= 2954 && absY <= 2999
				|| absX >= 2711 && absX <= 2830 && absY >= 2750 && absY <= 2815 //Ape atoll
				|| absX >= 2636 && absX <= 2681 && absY >= 10062 && absY <= 10104
				|| absX >= 3400 && absX <= 3600 && absY >= 9800
				&& absY <= 10100 || absX >= 3538 && absX <= 3568 && absY >= 4942
				&& absY <= 4983 || inCorp() || inGodWars() || inZombieCaves()) {
			return true;
		}
		return false;
	}

	public boolean inWild() {
		if (absX > 2941 && absX < 3392 && absY > 3518 && absY < 3966
				|| absX > 2941 && absX < 3392 && absY > 9918 && absY < 10366) {
			return true;
		}
		return false;
	}

	public void turnNpc(final int i, final int j) {
		FocusPointX = 2 * i + 1;
		FocusPointY = 2 * j + 1;
		updateRequired = true;

	}

	public void updateNPCMovement(final Stream str) {
		if (direction == -1) {

			if (updateRequired) {

				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
		} else {
			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, Misc.xlateDirectionToClient[direction]);
			if (updateRequired) {
				str.writeBits(1, 1);
			} else {
				str.writeBits(1, 0);
			}
		}
	}

}
