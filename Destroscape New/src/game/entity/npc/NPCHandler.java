package game.entity.npc;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import engine.world.VirtualWorld;
import game.Config;
import game.Server;
import game.combat.AttackPlayer;
import game.content.AncientEffigies;
import game.entity.Graphic;
import game.entity.npc.animation.AttackAnimation;
import game.entity.npc.animation.DeathAnimation;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.content.achievement.*;
import game.minigame.barrows.Barrows;
import game.minigame.fightcaves.FightCaves;
import game.minigame.warriorsguild.WarriorsGuild;
import game.skill.dungeoneering.DungHandler;
import game.skill.slayer.Slayer;
import game.minigame.rfd.RFD;
import game.skill.summoning.Summoning;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NPCHandler {
	public static int maxNPCs = 17000;
	public static int maxListedNPCs = 17000;
	public static int maxNPCDrops = 22000;
	public static NPC npcs[] = new NPC[NPCHandler.maxNPCs];
	public int random;

	public int checkSlayerHelm(Player c, int i) {
		if (c.slayerTask == npcs[i].npcType
				&& c.playerEquipment[c.playerHat] == 13263) {
			return 1;
		}
		if (c.slayerTask == npcs[i].npcType
				&& c.playerEquipment[c.playerHat] == 15492) {
			return 2;
		}
		return 0;
	}

	static int BurrowAnim = 12976, TransformID = 9462,
			BurrowUpAnim = 11788, IStrykeWyrmID = 9463, BurrowWait;


	private void tzhaarDeathHandler(int i) {
		if (isFightCaveNpc(i) && npcs[i].npcType != FightCaves.TZ_KEK)
			killedTzhaar(i);
		if (npcs[i].npcType == FightCaves.TZ_KEK_SPAWN) {
			int p = npcs[i].killerId;
			if (PlayerHandler.players[p] != null) {
				Player c = PlayerHandler.players[p];
				c.tzKekSpawn += 1;
				if (c.tzKekSpawn == 2) {
					killedTzhaar(i);
					c.tzKekSpawn = 0;
				}
			}
		}

		if (npcs[i].npcType == FightCaves.TZ_KEK) {
			int p = npcs[i].killerId;
			if (PlayerHandler.players[p] != null) {
				Player c = PlayerHandler.players[p];
				FightCaves.tzKekEffect(c, i);
			}
		}
	}

	private void killedTzhaar(int i) {
		final Player c2 = PlayerHandler.players[npcs[i].spawnedBy];
		c2.tzhaarKilled++;
		if (c2.tzhaarKilled == c2.tzhaarToKill) {
			c2.waveId++;
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer c) {
					if (c2 != null) {
						Server.fightCaves.spawnNextWave(c2);
					}
					c.stop();
				}
			}, 7500);

		}
	}

	public void handleMaxDeath(int i) {
		Player p = PlayerHandler.players[npcs[i].spawnedBy];
		p.getDH().sendDialogues(152, 3373);
		p.getPA().movePlayer(2720, 3471, 0);
		p.hasKilledMax = true;
	}

	public void handleBorkDeath(int i) {
		Player p = PlayerHandler.players[npcs[i].spawnedBy];
	}

	public void handleJadDeath(int i) {
		Player p = PlayerHandler.players[npcs[i].spawnedBy];
		p.getItems().addItem(6570, 1);
		p.getItems().addItem(6529, 3000);
		p.getDH().sendDialogues(69, 2617);
		p.getPA().resetTzhaar();
		p.waveId = 300;
	}

	public void handleRFDDeath(int i) {
		Player p = (Player)PlayerHandler.players[npcs[i].spawnedBy];
		p.sendMessage("Congratulations you have completed the RFD minigame!");
		p.getPA().resetRFD();
		p.waveId = 300;
	}
	
   public void SpawnZombieNPC(final Player c, final int npcType, final int x,
			final int y, final int heightLevel, final int WalkingType,
			final int HP, final int maxHit, final int attack,
			final int defence, final boolean attackPlayer,
			final boolean headIcon) {
      int slot = -1;

      for(int newNPC = 1; newNPC < maxNPCs; ++newNPC) {
         if(npcs[newNPC] == null) {
            slot = newNPC;
            break;
         }
      }

      if(slot != -1) {
		 NPC newNPC = new NPC(slot, npcType);

         newNPC.absX = x;
         newNPC.absY = y;
         newNPC.makeX = x;
         newNPC.makeY = y;
         newNPC.heightLevel = c.heightLevel;
		 newNPC.walkingType = WalkingType;
         newNPC.HP = HP;
         newNPC.MaxHP = HP;
         newNPC.spawnedBy = c.getId();
         newNPC.respawns = false;
         newNPC.forceChat("Braainz!");
         c.Zombiez[c.getZombieGame().FindZombie()] = newNPC;
         npcs[slot] = newNPC;
		 newNPC.attack = attack;
      }
   }	

	/**
	 * Emotes
	 **/

	public void appendBandosKC(int i) {
		Player c = PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			for (int j : bandosGodKC) {
				if (npcs[i].npcType == j) {
					c.godwarsKillCount[0]++;
					c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[0],
							16217);
					break;
				}
			}
		}
	}

	public void appendArmadylKC(int i) {
		Player c = PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			for (int j : armaGodKC) {
				if (npcs[i].npcType == j) {
					c.godwarsKillCount[3]++;
					c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[3],
							16216);
					break;
				}
			}
		}
	}

	public void appendSaradominKC(int i) {
		Player c = PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			for (int j : saraGodKC) {
				if (npcs[i].npcType == j) {
					c.godwarsKillCount[2]++;
					c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[2],
							16218);
					break;
				}
			}
		}
	}

	public static int[] godwarsBoss = {};

	public static int[] zamGodKC = { 6203, 6206, 6204, 6208, 6210, 6211, 6212,
		6214, 6218, 49, 82, 83, 84, 94, 92, 75, 78, 912, 6220 };
	public static int[] saraGodKC = { 6247, 6252, 6250, 6248, 6254, 6255, 6256,
		6257, 6258, 6259, 96, 97, 111, 125, 913 };
	public static int[] armaGodKC = { 6222, 6227, 6225, 6223, 6229, 6230, 6231,
		6232, 6233, 6234, 6235, 6236, 6237, 6238, 6239, 6240, 6241, 6242,
		6243, 6244, 6245, 6246, 275, 274 };
	public static int[] bandosGodKC = { 6260, 6265, 6263, 6261, 6271, 6272,
		6273, 6274, 6275, 6268, 122, 6279, 6280, 6281, 6282, 6283, 6269,
		6270, 6276, 6277, 6278 };

	public void appendZamorakKC(int i) {
		Player c = PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			for (int j : zamGodKC) {
				if (npcs[i].npcType == j) {
					c.godwarsKillCount[1]++;
					c.getPA().sendFrame126("@cya@" + c.godwarsKillCount[1],
							16219);
					break;
				}
			}
		}
	}

	public static void followPlayer(Player c, int i, int playerId) {
		if (PlayerHandler.players[playerId] == null) {
			return;
		}
		int[] noMove = { 1457, 7770, 6142, 6143, 6144, 6145, 2894, 2896, 6006, 6007,
				6008, 5666, 1944, 1946, 13454, 13451, 5631, 13452, 9462,9464,9467 };
		for (int no = 0; no < noMove.length; no++) {
			if (npcs[i].npcType == noMove[no]) {
				return;
			}
		}
		if (PlayerHandler.players[playerId].respawnTimer > 0) {
			npcs[i].facePlayer(0);
			npcs[i].randomWalk = true;
			npcs[i].underAttack = false;
			return;
		}

		if (!followPlayer(i)) {
			npcs[i].facePlayer(playerId);
			return;
		}

		if (retaliates(i)) {
			return;
		}

		if (!goodDistance(npcs[i].getX(), npcs[i].getY(),
				PlayerHandler.players[playerId].getX(),
				PlayerHandler.players[playerId].getY(), 1)
				&& npcs[i].npcType == 10127 && npcs[i].attackType == 0) {
			npcs[i].attackType = 2;
			return;
		}

		int playerX = PlayerHandler.players[playerId].absX;
		int playerY = PlayerHandler.players[playerId].absY;
		if (npcs[i].getNPCSize() == 1) {
			if (npcs[i].absX == playerX && npcs[i].absY == playerY
					&& npcs[i].npcType != 5808) {
				int o = Misc.random(3);
				switch (o) {
				case 0:
					npcs[i].moveX = GetMove(npcs[i].absX, playerX);
					npcs[i].moveY = GetMove(npcs[i].absY,
							playerY + npcs[i].getNPCSize());
					break;

				case 1:
					npcs[i].moveX = GetMove(npcs[i].absX, playerX);
					npcs[i].moveY = GetMove(npcs[i].absY,
							playerY - npcs[i].getNPCSize());
					break;

				case 2:
					npcs[i].moveX = GetMove(npcs[i].absX,
							playerX + npcs[i].getNPCSize());
					npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					break;

				case 3:
					npcs[i].moveX = GetMove(npcs[i].absX,
							playerX - npcs[i].getNPCSize());
					npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					break;
				}
			} else if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
					c.getY(), distanceRequired(i))) {
				return;
			}
		} else if (npcs[i].getNPCSize() > 1) {
			if (goodDistance(c.absX, c.absY, npcs[i].absX, npcs[i].absY,
					getDistance(c, i))) {
				int o = Misc.random(3);
				switch (o) {
				case 0:
					npcs[i].moveX = GetMove(npcs[i].absX, playerX);
					npcs[i].moveY = GetMove(npcs[i].absY,
							playerY + npcs[i].getNPCSize());
					break;

				case 1:
					npcs[i].moveX = GetMove(npcs[i].absX, playerX);
					npcs[i].moveY = GetMove(npcs[i].absY,
							playerY - npcs[i].getNPCSize());
					break;

				case 2:
					npcs[i].moveX = GetMove(npcs[i].absX,
							playerX + npcs[i].getNPCSize());
					npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					break;

				case 3:
					npcs[i].moveX = GetMove(npcs[i].absX,
							playerX - npcs[i].getNPCSize());
					npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					break;
				}
			} else if (!goodDistance(c.absX, c.absY, npcs[i].absX,
					npcs[i].absY, getDistance(c, i))
					&& goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
							c.getY(), distanceRequired(i))) {
				return;
			}
		}
		npcs[i].randomWalk = false;
		if ((npcs[i].spawnedBy > 0)
				|| (isFightCaveNpc(i))
				|| (familiar(i))
				|| ((npcs[i].absX < npcs[i].makeX + Config.NPC_FOLLOW_DISTANCE)
						&& (npcs[i].absX > npcs[i].makeX
								- Config.NPC_FOLLOW_DISTANCE)
								&& (npcs[i].absY < npcs[i].makeY
										+ Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absY > npcs[i].makeY
												- Config.NPC_FOLLOW_DISTANCE))) {
			if (npcs[i].heightLevel == c.heightLevel) {
				if (PlayerHandler.players[playerId] != null && npcs[i] != null) {
					npcs[i].facePlayer(playerId);
					if (npcs[i].getNPCSize() == 1) {
						if (playerY < npcs[i].absY) {
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY + 1);
						} else if (playerY > npcs[i].absY) {
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY - 1);
						} else if (playerX < npcs[i].absX) {
							npcs[i].moveX = GetMove(npcs[i].absX, playerX + 1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
						} else if (playerX > npcs[i].absX) {
							npcs[i].moveX = GetMove(npcs[i].absX, playerX - 1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
						}
					} else {
						if (playerY < npcs[i].absY) {
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY
									+ npcs[i].getNPCSize() - 1);
						} else if (playerY > npcs[i].absY) {
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY
									- npcs[i].getNPCSize());
						} else if (playerX < npcs[i].absX) {
							npcs[i].moveX = GetMove(npcs[i].absX, playerX
									+ npcs[i].getNPCSize() - 1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
						} else if (playerX > npcs[i].absX) {
							npcs[i].moveX = GetMove(npcs[i].absX, playerX
									- npcs[i].getNPCSize());
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
						}
					}
					npcs[i].getNextNPCMovement(i);
					npcs[i].updateRequired = true;
				}
			}
		} else {
			npcs[i].facePlayer(0);
			npcs[i].randomWalk = true;
			npcs[i].underAttack = false;
		}
	}

	public int followThatDistance(int i) {
		switch (npcs[i].npcType) {
		case 2890:
			return 25;
		case 13479:
			return 10;
		case 6203:
		case 6208:
		case 6204:
		case 6206:
		case 6247:
		case 6252:
		case 6250:
		case 6248:
		case 6829:
		case 6260:
		case 6265:
		case 6263:
		case 6261:
		case 6223:
		case 6225:
		case 6227:
		case 6222:
			return 20;
		case 3137:
		case 3150:
			return 17;
		case 5103:
		case 5104:
		case 7643:
		case 5105:
		case 7770:
			return 15;
		case 907:
		case 908:
		case 909:
		case 910:
		case 911:
			return 10;
		default:
			return Config.NPC_FOLLOW_DISTANCE;
		}
	}

	public static int sizeSubtraction(int i) {
		switch (npcs[i].getNPCSize()) {
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 2;
		case 5:
			return 3;
		case 6:
			return 3;

		default:
			return 0;
		}
	}

	public static int getDistance(Player c, int i) {
		if (!familiar(i)) {
			return 0;
		}
		int minus = npcs[i].getNPCSize();
		if (c.absY < npcs[i].absY || c.absX < npcs[i].absX) {
			minus = npcs[i].getNPCSize() + 1;
		} else if (c.absY >= npcs[i].absY || c.absX >= npcs[i].absX) {
			minus = npcs[i].getNPCSize() - sizeSubtraction(i);
		}
		return minus;
	}

	public static int getAttackEmote(int i) {
		return AttackAnimation.handleEmote(i);
	}

	public static String getNpcListName(int npcId) {
		if (npcId <= -1) {
			return "None";
		}
		if (NPCDefinitions.getDefinitions()[npcId] == null) {
			return "None";
		}
		return NPCDefinitions.getDefinitions()[npcId].getNpcName().replace("_",
				" ");
	}

	/**
	 * Npc names
	 **/

	public static String getNpcName(int npcId) {
		if (npcId <= -1) {
			return "None";
		}
		if (NPCDefinitions.getDefinitions()[npcId] == null) {
			return "None";
		}
		return NPCDefinitions.getDefinitions()[npcId].getNpcName();
	}

	public int[][] barrowsNpcs = { { 2030, 0 }, // verac
			{ 2029, 0 }, // toarg
			{ 2028, 0 }, // karil
			{ 2027, 0 }, // guthan
			{ 2026, 0 }, // dharok
			{ 2025, 0 }, // ahrim
			{ 2032, 0 }, { 2034, 0 }, { 2033, 0 }, { 2031, 0 }, { 2035, 0 }, };

	/**
	 * 
	 */
	private static void killedBarrow(final int i) {
		final Player c = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
		if (c != null) {
			if (npcs[i].npcType == 13451) {
				nexCountDown = 3;
			} else if (npcs[i].npcType == 13452) {
				nexCountDown = 3;
			} else if (npcs[i].npcType == 5631) {
				nexCountDown = 3;
			} else if (npcs[i].npcType == 13454) {
				nexCountDown = 3;
			}
			for (int o = 0; o < c.barrowsNpcs.length; o++) {
				if (NPCHandler.npcs[i].npcType == c.barrowsNpcs[o][0]) {
					c.barrowsNpcs[o][1] = 2;
					c.barrowsKillCount++;
				}
			}
		}
	}

	private static void killedBarrow2(final int i) {
		final Player c = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
		if (c != null) {
			if (barrowsBrother(i)) {
				c.brotherKills++;
			} else {
				return;
			}
		}
	}

	public boolean NvN(int i) {
		if (npcs[i].npcType == 4479 || npcs[i].npcType == 4492) {
			return true;
		}
		switch (npcs[i].npcType) {
		case 3751:
		case 3741:
			// sara vs gorak
		case 6257:
		case 6255:
		case 6218:
			// aviansie vs werewolf
		case 6233:
		case 6212:
			// knight vs aviansie
			// case 6258:
		case 6240:
			// aviansie vs vampire
		case 6245:
		case 6214:
			// hound vs cyclops
			// case 6210:
		case 6269:
			// ork vs bloodveld
		case 6215:
		case 6271:
			// zammy spirit vs knight
		case 6219:
			if (npcs[i].killerId > 0) {
				npcs[i].killNpc = 0;
				npcs[i].underAttackBy2 = 0;
				return false;
			}
			return true;
		case 3150:
			if (npcs[i].spawnedBy > 0) {
				return true;
			}
			return false;
		default:
			return false;
		}
	}

	public static boolean barrowsBrother(int i) {
		switch (npcs[i].npcType) {
		case 2030:
		case 2029:
		case 2028:
		case 2027:
		case 2026:
		case 2025:
			return true;
		}
		return false;
	}

	public static boolean familiar(int i) {
		switch (npcs[i].npcType) {
		case 6887:
		case 6885:
		case 6883:
		case 6881:
		case 6879:
		case 6877:
		case 6875:
		case 6829:
		case 6825:
		case 6841:
		case 6806:
		case 6796:
		case 7331:
		case 6831:
		case 6837:
		case 7361:
		case 6847:
		case 6994:
		case 6871:
		case 7353:
		case 6835:
		case 6845:
		case 6808:
		case 7370:
		case 7367:
		case 7351:
		case 7333:
		case 6853:
		case 6867:
		case 6851:
		case 6833:
		case 6855:
		case 7377:
		case 6824:
		case 6843:
		case 6794:
		case 6818:
		case 6992:
		case 6857:
		case 6991:
		case 7363:
		case 7365:
		case 7337:
		case 6809:
		case 6865:
		case 6820:
		case 6802:
		case 6827:
		case 6859:
		case 6889:
		case 6815:
		case 6813:
		case 6817:
		case 7372:
		case 6839:
		case 8575:
		case 7345:
		case 6849:
		case 6798:
		case 6861:
		case 7335:
		case 7347:
		case 6800:
		case 7355:
		case 7357:
		case 7359:
		case 6811:
		case 6804:
		case 7341:
		case 7329:
		case 6863:
		case 6822:
		case 7339:
		case 6869:
		case 7349:
		case 7375:
		case 6873:
		case 7343:
			return true;
		}
		return false;
	}

	/**
	 * Summon npc, barrows, etc
	 **/
	public static void spawnNpc(final Player c, final int npcType, final int x,
			final int y, final int heightLevel, final int WalkingType,
			final int HP, final int maxHit, final int attack,
			final int defence, final boolean attackPlayer,
			final boolean headIcon) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			// Misc.println("No Free Slot");
			return; // no free slot found
		}
		final NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		newNPC.spawnedBy = c.getId();
		if (headIcon) {
			c.getPA().drawHeadicon(1, slot, 0, 0);
		}
		if (attackPlayer) {
			newNPC.underAttack = true;
			if (c != null) {
				if (Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][1] != newNPC.npcType) {
					if (newNPC.npcType == 2025 || newNPC.npcType == 2026
							|| newNPC.npcType == 2027 || newNPC.npcType == 2028
							|| newNPC.npcType == 2029 || newNPC.npcType == 2030) {
						newNPC.forceChat("You dare disturb my rest!");
					}
				}
				if (Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][1] == newNPC.npcType) {
					newNPC.forceChat("You dare steal from us!");
				}

				newNPC.killerId = c.playerId;
			}
		}
		NPCHandler.npcs[slot] = newNPC;
	}

	public static void spawnNpc4(int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence) {
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		npcs[slot] = newNPC;
	}

	public NPCHandler() {
		for (int i = 0; i < maxNPCs; i++) {
			npcs[i] = null;
			NPCDefinitions.getDefinitions()[i] = null;
		}

		loadNPCList("./Data/CFG/NPC Config.cfg");
		loadAutoSpawn("./Data/CFG/NPC spawns.cfg");
		// System.out.println("NPC Spawns Loaded");
	}

	public void appendKillCount(final int i) {
		final Player c = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
		if (c != null) {
			final int[] kcMonsters = { 122, 49, 2558, 2559, 2560, 2561, 2550,
					2551, 2552, 2553, 2562, 2563, 2564, 2565 };
			for (final int j : kcMonsters) {
				if (NPCHandler.npcs[i].npcType == j) {
					if (c.killCount < 20) {
						c.killCount++;
						c.sendMessage("Killcount: " + c.killCount);
					} else {
						c.sendMessage("You already have 20 kill count");
					}
					break;
				}
			}
		}
	}

	/**
	 * Slayer Experience
	 **/

	public void applyDamage(final int i) {
		final Player c = PlayerHandler.players[NPCHandler.npcs[i].oldIndex];
		int b = Misc.random(2);
		if (NPCHandler.npcs[i] != null) {
			if (PlayerHandler.players[NPCHandler.npcs[i].oldIndex] == null) {
				return;
			}
			if (NPCHandler.npcs[i].isDead) {
				return;
			}
			if (multiAttacks(i) || npcs[i].multiAttack) {
				multiAttackDamage(i);
				return;
			}
			if (c.playerIndex <= 0 && c.npcIndex <= 0) {
				if (c.autoRet == 1) {
					c.npcIndex = i;
				}
			}
			if (c.attackTimer <= 3 || c.attackTimer == 0 && c.npcIndex == 0
					&& c.oldNpcIndex == 0) {
				//c.setAnimation(Animation.create(c.getCombat().getBlockEmote()));
				c.startAnimation(c.getCombat().getBlockEmote());
			}
			if (c.respawnTimer <= 0) {
				int deflectDamage = 0;
				int damage = 0;
				int familiarDamage = Misc.random(getFamiliarMaxHit(i));
				if (NPCHandler.npcs[i].attackType == 0) {
					damage = Misc.random(NPCHandler.npcs[i].maxHit);
					if (10 + Misc.random(c.getCombat().calculateMeleeDefence()) > Misc
							.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
					}
					if (c.prayerActive[18]) { // protect from melee
						damage = 0;
					}
					/*if (c.prayerActive[18] && (!(npcs[i].npcType == 2030)) && npcs[i].npcType != 3373) { // protect from melee
						damage = 0;
					}*/
					if (c.curseActive[9]) { // deflect melee
						damage = 0;
						deflectDamage = Misc.random(NPCHandler.npcs[i].maxHit) / 10;
						if (deflectDamage != 0) {
							//c.setAnimation(Animation.create(12573));
							c.startAnimation(12573);
							c.gfx0(2230);
							NPCHandler.npcs[i].handleDeflect(deflectDamage);
							NPCHandler.npcs[i].HP -= deflectDamage;
						}
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
				}

				if (NPCHandler.npcs[i].attackType == 1) { // range
			if (c.playerIndex <= 0 && c.npcIndex <= 0) {
				if (c.autoRet == 1) {
					c.npcIndex = i;
				}
			}
					damage = Misc.random(NPCHandler.npcs[i].maxHit);
					switch (npcs[i].npcType) {
					case 3073:
						damage = Misc.random(20);
						break;
					case 8349:
					case 8350:
					case 8351:
						damage = Misc.random(27);
						break;
						//case 9462:
						/*if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
								c.getY(), 1)) {
							damage = 30;

						} else {
							damage = 0;
						}
						break;*/
						//case 9464:
						/*if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
								c.getY(), 2)) {
							damage = 30;

						} else {
							damage = 0;
						}
						break;*/
						//case 9466:
						/*if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
								c.getY(), 2)) {
							damage = 30;

						} else {
							damage = 0;
						}
						break;*/

					}
					if (10 + Misc.random(c.getCombat().calculateRangeDefence()) > Misc
							.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
					}
					if (c.prayerActive[17]) { // protect from range
						damage = 0;
					}
					if (c.curseActive[8]) { // deflect range
						damage = 0;
						deflectDamage = Misc.random(NPCHandler.npcs[i].maxHit) / 10;
						if (deflectDamage != 0) {
							//c.setAnimation(Animation.create(12573));
							c.startAnimation(12573);
							c.gfx0(2229);
							NPCHandler.npcs[i].handleDeflect(deflectDamage);
							NPCHandler.npcs[i].HP -= deflectDamage;
						}
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
				}

				switch (npcs[i].npcType) {
				case 8133: // corp
				case 13447:
					damage = Misc.random(31);
					break;
					/*case 9462:
					if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
							c.getY(), 0)) {
						damage = 30;

					} else {
						damage = 0;
					}
					break;*/
					/*case 9464:
					if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
							c.getY(), 0)) {
						damage = 30;

					} else {
						damage = 0;
					}
					break;
				case 9466:
					if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
							c.getY(), 0)) {
						damage = 30;

					} else {
						damage = 0;
					}
					break;*/
				}

				if (NPCHandler.npcs[i].attackType == 2) { // magic
			if (c.playerIndex <= 0 && c.npcIndex <= 0) {
				if (c.autoRet == 1) {
					c.npcIndex = i;
				}
			}
					damage = Misc.random(NPCHandler.npcs[i].maxHit);
					boolean magicFailed = false;
					if (10 + Misc.random(c.getCombat().mageDef()) > Misc
							.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
						magicFailed = true;
					}
					switch (npcs[i].npcType) {
					/*case 13447:
					case 8133:
						damage = Misc.random(55);
						break;*/
					case 8349:
					case 8350:
					case 8351:
						damage = Misc.random(27);
						break;
					case 9462:
						damage = Misc.random(35);
					case 9464:
						damage = Misc.random(30);
					case 9466:
						damage = Misc.random(25);
						break;
					}

					if (c.prayerActive[12]) { // protect from magic
						damage = 0;
					}
					if (c.prayerActive[16]) { // protect from magic
							damage = 0;
							magicFailed = true;
					}
					if (c.curseActive[7]) { // deflect mage
						damage = 0;
						magicFailed = true;
						deflectDamage = Misc.random(NPCHandler.npcs[i].maxHit) / 10;
						if (deflectDamage != 0) {
							//c.setAnimation(Animation.create(12573));
							c.startAnimation(12573);
							c.gfx0(2228);
							NPCHandler.npcs[i].handleDeflect(deflectDamage);
							NPCHandler.npcs[i].HP -= deflectDamage;
						}
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
					if (NPCHandler.npcs[i].endGfx > 0
							&& (!magicFailed || isFightCaveNpc(i))) {
						c.gfx100(NPCHandler.npcs[i].endGfx);
					} else {
						c.gfx100(85);
					}
				}	
				if (npcs[i].attackType == 3) {
					int anti = c.getPA().antiFire();
					if(c.playerEquipment[c.playerShield] == 11284 && c.dfsCount <= 39){
						c.dfsCount += 1;
						c.gfx0(1164);
						c.startAnimation(6695);
						c.sendMessage("Your dragonfire Shield Absorbs the Dragon breath");
					}
					switch (anti) {
					case 0:
						damage = Misc.random(30) + 10;
						c.sendMessage("You are badly burnt by the dragon fire!");
						break;
					case 1:
						c.sendMessage("You deflect some of the dragon's fire.");
						damage = Misc.random(10);
						break;
					case 2:
						c.sendMessage("You deflect some of the dragon's fire.");
						damage = Misc.random(5);
						break;
					}
					if (c.playerLevel[3] - damage < 0)
						damage = c.playerLevel[3];
					c.setGraphic(Graphic.highGraphic(npcs[i].endGfx));
				}
				handleSpecialEffects(c, i, damage);
				if (c.vengOn && damage > 0) {

					c.getCombat().appendVengeanceNPC(i, damage);

				}
				c.logoutDelay = System.currentTimeMillis(); // logout delay
				c.setHitDiff(damage);
				if(npcs[i].attackType == 0 || npcs[i].attackType == 4) {// Melee
					c.handleHitMask(damage, 0, 0);
				}
				if (npcs[i].attackType == 1) {// Range
					c.handleHitMask(damage, 0, 1);
				}
				if (npcs[i].attackType == 2 || npcs[i].attackType == 3) {// Magic
					c.handleHitMask(damage, 0, 2);
				}
				if (familiar(i)) {
					if (b == 0) {
						c.hitDiff = familiarDamage;
						c.playerLevel[3] -= familiarDamage;
						c.getPA().refreshSkill(3);
						c.hitUpdateRequired = true;
						c.updateRequired = true;
					}
				} else {
					//c.handleHitMask(damage, 0, 2);
					c.playerLevel[3] -= damage;
					c.getPA().refreshSkill(3);
					FightCaves.tzKihEffect(c, i, damage);
					c.updateRequired = true;
					//c.setHitUpdateRequired(true);
				}
			}
		}
	}

	public void attackNPC(int i) {
		if (npcs[i] != null) {
			NPC n = NPCHandler.npcs[npcs[i].killNpc];
			if (goodDistance(npcs[i].getX(), npcs[i].getY(), n.getX(),
					n.getY(), distanceRequired(i))) {
				if (!n.isDead || n.deadCycle > 0) {
					if (npcs[i].npcType == 3150) {
					}
					if (npcs[i].npcType == 4479) {
						if (Misc.random(11) >= 10) {
							if (Misc.random(2) == 0) {
								npcs[i].forceChat("Red is strong! Green is weak!");
							} else if (Misc.random(2) == 1) {
								npcs[i].forceChat("Green must die! Red prevail!");
							} else if (Misc.random(2) == 2) {
								npcs[i].forceChat("Green is stupid!");
							}
						}
					} else if (npcs[i].npcType == 4492) {
						if (Misc.random(11) >= 10) {
							if (Misc.random(2) == 0) {
								npcs[i].forceChat("Green forever!");
							} else if (Misc.random(2) == 1) {
								npcs[i].forceChat("Red is ugly!");
							} else if (Misc.random(2) == 2) {
								npcs[i].forceChat("You stupid, red is stupid!");
							}
						}
					}
					npcs[i].faceNPC(npcs[i].killNpc);
					npcs[i].attackTimer = getNpcDelay(i);
					npcs[i].hitDelayTimer = getHitDelay(i);
					npcs[i].attackType = 0;
					loadSpellNPC(i);
					startAnimation(getAttackEmote(i), i);
				} else {
					n.underAttackBy2 = 0;
					n.underAttack = false;
					n.killNpc = 0;
					n.randomWalk = true;
				}
			}
		}
	}

	public int familiarMaxHit;

	public void npcEffect(Player c, int i, int damage) {
		if (npcs[i] != null) {
			NPC n = NPCHandler.npcs[npcs[i].killNpc];
			if (goodDistance(npcs[i].getX(), npcs[i].getY(), n.getX(),
					n.getY(), distanceRequired(i))) {
				if (!n.isDead || n.deadCycle > 0) {
		hitTargetPlayer(c, i, damage);
		switch (npcs[i].npcType) {
		case 6017:
		case 6018:
		case 6019:
		case 6020:
			if (npcs[i].glod == 4) {
				try {
					c.freezeTimer = 24;
					c.sendMessage("A magical force stops you from moving.");
					/*
					 * npcs[i].absX = 3095+Misc.random(7); npcs[i].absY =
					 * 5536+Misc.random(7); npcs[i].attackTimer += 4;
					 * npcs[i].gfx0(1577); npcs[i].updateRequired = true;
					 * c.getPA().requestUpdates();
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 1975:
			c.playerLevel[5] -= 10;
			if (c.playerLevel[5] < 0) {
				c.playerLevel[5] = 0;
			}
			break;
		case 5808:
			if (damage > 0) {
				c.sendMessage("The dark core creature steals some life from you for its master.");
				for (int npc = 0; npc < maxNPCs; npc++) {
					//NPC n = npcs[npc];
					try {
						if (n.npcType == 8133) {
							n.HP += damage;
							if (n.MaxHP < n.HP) {
								n.HP = n.MaxHP;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			break;

		case 3072:
		case 3073:
			if (npcs[i].dragon == 5) {
				c.playerLevel[5] -= (damage / 2);
				if (c.playerLevel[5] < 0) {
					c.playerLevel[5] = 0;
				}
			}
			break;

		case 8133:
			if (npcs[i].glod == 4) {
				int drain = 0;
				if (Misc.random(2) == 0) {
					drain = 6;
					if (c.playerLevel[drain] > 0)
						c.sendMessage("Your Magic has been slighty drained!");
				} else if (Misc.random(2) == 1) {
					drain = 5;
					if (c.playerLevel[drain] > 0)
						c.sendMessage("Your Prayer has been slighty drained!");
				} else if (Misc.random(2) == 2) {
					drain = 22;
					if (c.playerLevel[drain] > 0)
						c.sendMessage("Your Summoning has been slighty drained!");
				}
				if (c.playerLevel[drain] <= 0) {
					drain = 3;
					c.sendMessage("Your life has been slighty drained!");
				}
				c.playerLevel[drain] -= (damage / 2);
				if (c.playerLevel[drain] < 0) {
					c.playerLevel[drain] = 0;
				}
			}
			break;
		}
		if (npcs[i].npcType == 2894 || npcs[i].npcType == 2896) {
			if (c.playerLevel[5] > 0) {
				c.playerLevel[5]--;
				c.getPA().refreshSkill(5);
			}
		}

		if (npcs[i].npcType == 6247) {
			if (Misc.random(5) >= 3) {
				npcs[i].forceChat(npcs[i].Zilyana());
			}
			if (npcs[i].zilyana == 6 || npcs[i].zilyana == 5
					|| npcs[i].zilyana == 4) {
				damage = Misc.random(31);
				if (c.prayerActive[12]) {
					damage = damage / 3;
				}
				c.gfx100(1207);
			}
		}

		if (npcs[i].npcType == 2028) {
			if (Misc.random(4) >= 3) {
				c.gfx100(401);
				if (c.playerLevel[16] > 0) {
					c.playerLevel[16]--;
					c.getPA().refreshSkill(16);
				}
			}
		}
		if (npcs[i].npcType == 2025) {
			if (Misc.random(4) >= 3) {
				c.gfx100(400);
				if (c.playerLevel[2] > 0) {
					c.playerLevel[2]--;
					c.getPA().refreshSkill(2);
				}
			} else {
				c.gfx100(157);
			}
		}
		if (npcs[i].npcType == 2027) {
			if (Misc.random(4) >= 3) {
				c.gfx100(398);
				if (npcs[i].HP < npcs[i].MaxHP) {
					npcs[i].HP += damage;
				}
			}
		}
		if (npcs[i].npcType == 4353) {
			if (npcs[i].cHorror == 0) {
				if (c.playerEquipment[c.playerAmulet] == 8923) {
					damage = 0;
				} else {
					damage = 8;
					for (int s = 0; s < 7; s++) {
						if (s != 3 && s != 5) {
							c.playerLevel[s] -= 8;
							c.getPA().refreshSkill(i);
						}
					}
					c.sendMessage("You feel weakened..");
				}
			}
		}
		if (npcs[i].npcType == 2026) {
			int dhdamage = npcs[i].MaxHP - npcs[i].HP;
			if (!c.prayerActive[14] || !c.prayerActive[35]) {
				damage = Misc.random(25 + dhdamage);
			} else {
				damage = 0;
			}
		}
		if (npcs[i].npcType == 3261 || npcs[i].npcType == 3252
				|| npcs[i].npcType == 3253 || npcs[i].npcType == 199) {
			if (Misc.random(4) >= 3) {
				npcs[i].forceChat("YYEEEEEAAARRRRGGHHHH!");
			}
		}
		if (npcs[i].npcType == 2741) {
			if (npcs[i].mejKot == 0) {
				npcs[i].HP += 15;
				npcs[i].gfx100(444);
				return;
			}
		}
		if (npcs[i].npcType == 2627) {
			if (c.prayer > 9) {
				c.prayer -= 10;
				c.getPA().refreshSkill(5);
			} else {
				c.prayer = 0;
				c.getPA().refreshSkill(5);
			}
		}
		if (npcs[i].npcType == 1681) {
			if (c.playerLevel[5] > 0) {
				c.playerLevel[5] -= damage / 2;
				c.getPA().refreshSkill(5);
			}
		}
		if (npcs[i].npcType == 1975) {
			if (c.playerLevel[5] > 0) {
				c.playerLevel[5] -= 5;
				c.getPA().refreshSkill(5);
			}
		}
		if (npcs[i].npcType == 50) {
			switch (npcs[i].kbdAttack) {
			case 3:
				for (int i1 = 0; i1 < 6; i1++) {
					if (c.playerLevel[i1] > 0) {
						c.playerLevel[i1]--;
						c.getPA().refreshSkill(i1);
					}
				}
				c.sendMessage("You have been shocked!");
				break;
			case 2:
				if (c.freezeTimer < 0) {
					c.sendMessage("You have been frozen!");
					c.freezeTimer = 10;
				}
				break;
			}
		}
		if (npcs[i].npcType == 3200) {
			if (npcs[i].chaosEle == 1) {
				if (c.getItems().freeSlots() > 0) {
					c.getItems().removeItem(c.playerEquipment[c.playerWeapon],
							c.playerWeapon);
					return;
				} else {
					return;
				}
			}
			if (npcs[i].chaosEle == 0) {
				c.teleportToX = c.absX - Misc.random(7);
				c.teleportToY = c.absY - Misc.random(7);
				return;
			}
		}

		if (npcs[i].npcType == 2057) {
			if (Misc.random(5) >= 4) {
				npcs[i].forceChat(npcs[i].Glod());
			}
		}

		if (npcs[i].npcType == 6203) {
			if (npcs[i].tsutsaroth != 3) {
				if (Misc.random(5) >= 4) {
					npcs[i].forceChat(npcs[i].Tsutsaroth());
				}
			} else {
				if (c.prayerActive[14] || c.prayerActive[35]) {
					damage = Misc.random(49);
					npcs[i].forceChat("YARRRRRRR!");
					c.sendMessage("K'ril Tsutsaroth slams through your protection prayer, leaving you feeling drained.");
					c.playerLevel[5] -= damage / 2;
				}
			}
		}

		if (npcs[i].npcType == 6222) {
			switch (npcs[i].kree) {
			// case 1:
			case 2:
				c.gfx100(80);
				c.teleportToX = c.absX - 1;
				c.teleportToY = c.absY;
				break;
			}
		}
	}
}}}

	public void hitTargetPlayer(Player c, int i, int hit) {
		if (npcs[i] != null) {
			NPC n = NPCHandler.npcs[npcs[i].killNpc];
			if (goodDistance(npcs[i].getX(), npcs[i].getY(), n.getX(),
					n.getY(), distanceRequired(i))) {
				if (!n.isDead || n.deadCycle > 0) {
		npcEffect(c, i, hit);
		if (c.playerEquipment[c.playerShield] == 13740) {
			int formula = (int) (hit * 0.7);
			int pformula = (formula / 20);
			if (c.playerLevel[5] >= pformula) {
				c.playerLevel[5] -= pformula;
				hit *= 0.7;
			}
		}
		if (c.playerEquipment[c.playerShield] == 13742) {
			if (Misc.random(7) >= 5) {
				hit *= 0.75;
			}
		}
		if (c.playerLevel[3] - hit < 0) {
			hit = c.playerLevel[3];
		}
		if (c.autoRet > 0) {
			if (!familiar(i) && !npcs[i].animals()) {
				if (c.npcIndex == 0 && c.playerIndex == 0) {
					try {
						// c.attackTimer = 2;
						c.npcIndex = i;
						c.getCombat().attackNpc(c.npcIndex);
					} catch (Exception e) {
					}
				}
			}
		}
		if (c.vengOn && hit != 0) {
			int damage = (hit / 2) + (hit / 3);
			c.forcedChat("Taste vengeance!");
			c.vengOn = false;
			NPCHandler.npcs[i].hitDiff = damage;
			NPCHandler.npcs[i].HP -= damage;
			NPCHandler.npcs[i].hitUpdateRequired = true;
			NPCHandler.npcs[i].updateRequired = true;
		}
		if (npcs[i].npcType == 13447) {
			switch (npcs[i].nexStage) {
			case 1:
			case 2:
				if (npcs[i].glod == 2) {
					if (c.virusDamage == 0) {
						c.virusTimer = 10;
						c.virusDamage = 5;
						c.forcedChat("*Cough*");
					}
				}
				if (npcs[i].HP < 2400 && npcs[i].nexStage == 1) {
					npcs[i].forceChat("Fumus, don't fail me!");
					npcs[i].nexStage = 2;
					nexRoom[0] = true;
				}
				break;
			case 3:
			case 4:
				if (npcs[i].glod == 3) {
					for (int fs = 0; fs < npcs[i].fearShadow.length; fs++) {
						if (npcs[i].fearShadow[fs][0] == 0) {
							npcs[i].fearShadow[fs][0] = c.absX;
							npcs[i].fearShadow[fs][1] = c.absY;
							break;
						}
					}
					final int DMG = Misc.random(60);
					final Player c1 = c;
					final int I = i;
					EventManager.getSingleton().addEvent(new Event() {
						@Override
						public void execute(EventContainer e) {
							for (int ii = 0; ii < npcs[I].fearShadow.length; ii++) {
								if (npcs[I].fearShadow[ii][0] > 0) {
									if (c1.absX == npcs[I].fearShadow[ii][0]
											&& c1.absY == npcs[I].fearShadow[ii][1]) {
										c1.setHitDiff(DMG);
										c1.playerLevel[3] -= DMG;
										c1.getPA().refreshSkill(3);
										c1.updateRequired = true;
										c1.setHitUpdateRequired(true);
										c1.logoutDelay = 20;
										npcs[I].fearShadow[ii][0] = npcs[I].fearShadow[ii][1] = 0;
										return;
									} else {
										npcs[I].fearShadow[ii][0] = npcs[I].fearShadow[ii][1] = 0;
										return;
									}
								}
							}
							e.stop();
						}
					}, 1800);
					return;
				}
				if (npcs[i].HP < 1800 && npcs[i].nexStage == 3) {
					npcs[i].forceChat("Umbra, don't fail me!");
					npcs[i].nexStage = 4;
					nexRoom[1] = true;
				}
				break;
			case 5:
			case 6:
				if (npcs[i].glod == 2) {
					npcs[i].HP += hit;
					if (npcs[i].HP > npcs[i].MaxHP) {
						npcs[i].HP = npcs[i].MaxHP;
					}
					c.playerLevel[5] /= 2;
				}
				if (npcs[i].glod == 3) {
					for (int t = 0; t < 1 + Misc.random(2); t++) {
						newNPC3(7643, (npcs[i].absX + 1) + Misc.random(2),
								(npcs[i].absY + 1) + Misc.random(2), 0, 0, 101,
								15, 140, 10, 2);
					}
					npcs[i].cooldown = 30;
					return;
				}
				if (npcs[i].HP < 1200 && npcs[i].nexStage == 5) {
					npcs[i].forceChat("Cruor, don't fail me!");
					npcs[i].nexStage = 6;
					nexRoom[2] = true;
				}
				break;
			case 7:
			case 8:
				if (npcs[i].glod == 1) {
					if (c.freezeTimer < 0) {
						c.stopMovement();
						c.sendMessage("You have been frozen!");
						c.freezeTimer = 30;
					}
				} else if (npcs[i].glod == 2) {
					if (npcs[i].CONTAIN_THIS[0][0] > 0) {
						if (c.absX > npcs[i].CONTAIN_THIS[0][0]
								&& c.absX < npcs[i].CONTAIN_THIS[1][0]
										&& c.absY > npcs[i].CONTAIN_THIS[0][1]
												&& c.absY < npcs[i].CONTAIN_THIS[1][1]) {
							int iced = Misc.random(65);
							c.setHitDiff(iced);
							c.playerLevel[3] -= iced;
							c.getPA().refreshSkill(3);
							c.updateRequired = true;
							c.setHitUpdateRequired(true);
							c.logoutDelay = 20;
							return;
						} else {
							return;
						}
					}
				} else if (npcs[i].glod == 3) {
					c.stopMovement();
					int iced = Misc.random(60);
					c.setHitDiff(iced);
					c.playerLevel[3] -= iced;
					c.getPA().refreshSkill(3);
					c.updateRequired = true;
					c.setHitUpdateRequired(true);
					c.logoutDelay = 20;
					return;
				} else {
					return;
				}
				if (npcs[i].HP < 600 && npcs[i].nexStage == 7) {
					npcs[i].forceChat("Glacies, don't fail me!");
					npcs[i].nexStage = 8;
					nexRoom[3] = true;
				}
				break;
			case 9:
				if (npcs[i].prayerUsed == 1) {
					npcs[i].HP += hit / 5;
				}
				break;
			}
		}
		if (npcs[i].endGfx > 0) {
			if (getGfx0(npcs[i].endGfx)) {
				c.getPA().stillGfx(npcs[i].endGfx, c.absX, c.absY, 0, 0);
			} else {
				c.gfx100(npcs[i].endGfx);
			}
		}
		if (npcs[i].npcType == 8133 && npcs[i].attackType == 1
				|| npcs[i].attackType == 0 || npcs[i].attackType == 2) {
			for (int e = 1; e < Config.MAX_PLAYERS; e++) {
				int hits = 0;
				Player p = PlayerHandler.players[e];
				try {
					if (goodDistance(c.getX(), c.getY(), p.getX(), p.getY(), 25)) {
						if (hits == 6) {
							return;
						}
						hits++;
						int newHit = Misc.random(21);
						if (p.prayerActive[13] || p.prayerActive[34]) {
							newHit = 0;
						}
						p.setHitDiff(newHit);
						p.playerLevel[3] -= newHit;
						p.getPA().refreshSkill(3);
						p.updateRequired = true;
						p.setHitUpdateRequired(true);
						p.logoutDelay = 20;
						p.getPA().createPlayersProjectile(c.absX, c.absY,
								(c.absX - p.getX()) * -1,
								(c.absY - p.getY()) * -1, 50, 80, 1824, 41, 0,
								-p.getId() - 1, 10, c.heightLevel);
						// return;
					} else if (!goodDistance(c.getX(), c.getY(), p.getX(),
							p.getY(), 4)) {
						int newHit = Misc.random(20);
						if (p.prayerActive[13]) {
							newHit = 0;
						}
						c.hitDiff2 = newHit;
						c.playerLevel[3] -= newHit;
						c.getPA().refreshSkill(3);
						c.updateRequired = true;
						c.hitUpdateRequired2 = true;
						c.logoutDelay = 20;
						return;
					}
				} catch (Exception e1) {
				}
			}
		} else if (npcs[i].npcType == 8133) {
			if (Misc.random(70) >= 69) {
				newNPC3(5808, npcs[i].absX, npcs[i].absY, 0, 0, 25, 10, 800, 5,
						0);
			}
		}
		if (npcs[i].attackType == 0) {
			hit = (int) (hit * AttackPlayer.shieldEffect(c, 0));
		} else if (npcs[i].attackType == 1) {
			hit = (int) (hit * AttackPlayer.shieldEffect(c, 1));
		} else {
			hit = (int) (hit * AttackPlayer.shieldEffect(c, 2));
		}
		c.setHitDiff(hit);
		c.playerLevel[3] -= hit;
		c.getPA().refreshSkill(3);
		c.updateRequired = true;
		c.setHitUpdateRequired(true);
		c.logoutDelay = 20;
	}}}}

	public boolean getGfx0(int gfx) {
		switch (gfx) {
		case 382:
		case 369:
		case 2263:
			return true;
		default:
			return false;
		}
	}

	public int getFamiliarMaxHit(int i) {
		int abc = 2;
		if (Misc.random(abc) == 0) {
			switch (NPCHandler.npcs[i].npcType) {
			case 6829:
				return 4;
			case 6825:
				return 4;
			case 6841:
				return 3;
			case 6806:
				return 4;
			case 6796:
				return 4;
			case 7331:
				return 4;
			case 6831:
				return 4;
			case 6837:
				return 6;
			case 7361:
				return 5;
			case 6847:
				return 5;
			case 6994:
				return 5;
			case 6871:
				return 5;
			case 7353:
				return 5;
			case 6835:
				return 3;
			case 6845:
				return 5;
			case 6808:
				return 5;
			case 7370:
				return 6;
			case 7367:
				return 5;
			case 7351:
				return 6;
			case 7333:
				return 6;
			case 6853:
				return 6;
			case 6867:
				return 9;
			case 6851:
				return 0;
			case 6833:
				return 7;
			case 6855:
				return 8;
			case 7377:
				return 8;
			case 6824:
				return 0;
			case 6843:
				return 9;
			case 6794:
				return 8;
			case 6818:
				return 10;
			case 6992:
				return 10;
			case 6857:
				return 11;
			case 6991:
				return 0;
			case 7363:
				return 10;
			case 6875:
				return 7;
			case 6877:
				return 7;
			case 6879:
				return 7;
			case 6881:
				return 7;
			case 6883:
				return 7;
			case 6885:
				return 7;
			case 6887:
				return 7;
			case 7365:
				return 11;
			case 7337:
				return 11;
			case 6809:
				return 11;
			case 6865:
				return 10;
			case 6820:
				return 10;
			case 6802:
				return 12;
			case 6827:
				return 12;
			case 6859:
				return 13;
			case 6889:
				return 13;
			case 6815:
				return 10;
			case 6813:
				return 9;
			case 6817:
				return 0;
			case 7372:
				return 13;
			case 6839:
				return 13;
			case 8575:
				return 13;
			case 7345:
				return 14;
			case 6849:
				return 14;
			case 6798:
				return 15;
			case 6861:
				return 14;
			case 7335:
				return 14;
			case 7347:
				return 24;
			case 6800:
				return 15;
			case 7355:
				return 15;
			case 7357:
				return 15;
			case 7359:
				return 15;
			case 6811:
				return 16;
			case 6804:
				return 16;
			case 7341:
				return 17;
			case 7329:
				return 16;
			case 6863:
				return 19;
			case 6822:
				return 8;
			case 7339:
				return 22;
			case 6869:
				return 23;
			case 7349:
				return 23;
			case 7375:
				return 23;
			case 6873:
				return 18;
			case 7343:
				return 24;
			}
		} else {
			return 0;
		}
		return 0;
	}

	public int getNpcDeleteTime(int i) {
		if (familiar(i)) {
			return 0;
		}
		switch (npcs[i].npcType) {
		case 102:
		case 101:
			// goblin
		case 100:
		case 6279:
		case 4479:
		case 4492:
		case 7135:
		case 2630:
		case 2738:
		case 2741:
		case 2743:
		case 1813:
		case 1829:
			return 4;
		case 4353:
		case 5103:
		case 5104:
		case 5105:
		case 1266:
		case 2453:
		case 1615:
		case 6218:
		case 1343:
		case 1351:
		case 163:
		case 2890:
			return 2;
		case 5666:
			return 16;
		case 153:
		case 154:
		case 155:
		case 156:
		case 1028:
		case 1029:
		case 1030:
		case 1031:
		case 1032:
		case 1033:
		case 1034:
		case 1035:
		case 6053:
		case 6054:
		case 771:
		case 1944:
		case 1946:
			return 0;
		default:
			return 6;
		}
	}

	public void applyDamageNPC(int i) {
		NPC n = NPCHandler.npcs[npcs[i].killNpc];
		if (npcs[i] != null) {
			if (n == null) {
				return;
			}
			if (!n.isDead || n.deadCycle > 0) {
				int damage = Misc.random(npcs[i].maxHit);
				int familiarDamage = getFamiliarMaxHit(i);
				if (npcs[i].attackType == 0) {
					if (npcs[i].npcType == 7366) {
						if (npcs[i].spec) {
							damage *= 3;
							npcs[i].spec = false;
						}
					}
					if (npcs[i].npcType == 7364) {
						if (npcs[i].spec) {
							damage *= 2;
							npcs[i].spec = false;
						}
					}
					if (npcs[i].npcType == 7342) {
						if (npcs[i].spec) {
							damage += 5;
							npcs[i].spec = false;
						}
					}
					if (5 + Misc.random(n.defence) > Misc
							.random(npcs[i].attack)) {
						damage = 0;
					}
					if (npcs[i].npcType == 3751) {
						if (n.deadCycle < 1 && !n.isDead) {
							if (n.HP < n.MaxHP) {
								n.HP += 10 + Misc.random(5);
								if (n.HP > n.MaxHP) {
									n.HP = n.MaxHP;
								}
							}
							return;
						}
					}
				}
				if (npcs[i].attackType == 1) { // range
					if (15 + Misc.random(n.defence) > Misc
							.random(npcs[i].attack)) {
						damage = 0;
					}
				}

				if (npcs[i].attackType == 2) { // magic
					if (5 + Misc.random(n.mageDef()) > Misc
							.random(npcs[i].attack) && npcs[i].npcType != 3150) {
						n.gfx100(85);
						return;
					}
				}
				if (damage > n.HP) {
					n.deadCycle = 3;
					damage = n.HP;
				}
				if (npcs[i].endGfx > 0) {
					if (npcs[i].npcType == 3150) {
						n.gfx0(npcs[i].endGfx);
					} else {
						n.gfx100(npcs[i].endGfx);
					}
				}
				n.hitDiff = damage;
				n.HP -= damage;
				n.hitUpdateRequired = true;
				if (n.underAttackBy2 <= 0 && n.killNpc <= 0) {
					if (!familiar(i)) {
						n.underAttackBy2 = i;
						n.underAttack = true;
						n.killNpc = i;
						n.randomWalk = false;
					}
				}
				if (npcs[i].npcType == 7343) {
					if (npcs[i].spec) {
						n.hitDiff = (Misc.random(familiarDamage));
						n.HP -= n.hitDiff;
						n.hitUpdateRequired = true;
						n.hitDiff2 = (Misc.random(familiarDamage));
						n.HP -= n.hitDiff2;
						n.hitUpdateRequired2 = true;
					}
				}
				if (familiar(i)) {
					try {
						if (familiarDamage > n.HP) {
							familiarDamage = n.HP;
						}
						n.hitDiff = (Misc.random(familiarDamage));
						n.HP -= n.hitDiff;
						n.hitUpdateRequired = true;
						n.killedBy = npcs[i].spawnedBy;
					} catch (Exception e) {
					}
				}
				n.updateRequired = true;
			} else {
				n.underAttackBy2 = 0;
				n.underAttack = false;
				n.killNpc = 0;
				n.randomWalk = true;
			}
		}
	}

	public void attackPlayer(Player c, int i) {
		if (npcs[i] != null) {
			if (npcs[i].isDead)
				return;
			if (!npcs[i].inMulti() && npcs[i].underAttackBy > 0
					&& npcs[i].underAttackBy != c.playerId) {
				npcs[i].killerId = 0;
				return;
			}
			if (!npcs[i].inMulti()
					&& (c.underAttackBy > 0 || (c.underAttackBy2 > 0 && c.underAttackBy2 != i))) {
				npcs[i].killerId = 0;
				return;
			}
			if (npcs[i].heightLevel != c.heightLevel) {
				npcs[i].killerId = 0;
				return;
			}
			npcs[i].facePlayer(c.playerId);
			boolean special = false; // specialCase(c,i);
			if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
					c.getY(), distanceRequired(i)) || special) {
				if (c.respawnTimer <= 0) {
					if (npcs[i].npcType == 2862 || npcs[i].npcType == 6142
							|| npcs[i].npcType == 6143
							|| npcs[i].npcType == 6144
							|| npcs[i].npcType == 6145
							|| npcs[i].npcType == 1944
							|| npcs[i].npcType == 1946) {
						return;
					}
					if (npcs[i].deadCycle > 0 || npcs[i].isDead) {
						return;
					}
					if (!c.inMulti()
							&& (c.underAttackBy != 0 || (c.underAttackBy2 != 0 && c.underAttackBy2 != i))) {
						npcs[i].underAttack = false;
						npcs[i].killerId = 0;
						npcs[i].randomWalk = true;
						return;
					}

					c.singleCombatDelay = System.currentTimeMillis();
					npcs[i].facePlayer(c.playerId);
					npcs[i].attackTimer = getNpcDelay(i);
					npcs[i].hitDelayTimer = getHitDelay(i);
					npcs[i].attackType = 0;
					c.underAttackBy2 = i;
					if (special)
						loadSpell2(i);
					else
						loadSpell(c, i);
					if (npcs[i].npcType == 8133 || npcs[i].npcType == 5808) {
						try {
							if (!c.inCorp()) {
								return;
							}
						} catch (Exception e) {
						}
					} else if (npcs[i].npcType == 13447) {
						if (npcs[i].nexStage == 7 || npcs[i].nexStage == 8) {
							if (npcs[i].glod == 2) {
								npcs[i].CONTAIN_THIS[0][0] = npcs[i].absX - 2;
								npcs[i].CONTAIN_THIS[0][1] = npcs[i].absY - 2;
								npcs[i].CONTAIN_THIS[1][0] = npcs[i].absX + 2;
								npcs[i].CONTAIN_THIS[1][1] = npcs[i].absY + 2;
								int obx = 0, oby = 0;
								for (int s = 0; s < 4; s++) {
									c.getPA().globalObject(534,
											npcs[i].CONTAIN_THIS[0][0] + obx,
											npcs[i].CONTAIN_THIS[0][1], 0, 10,
											0);
									c.getPA().globalObject(534,
											npcs[i].CONTAIN_THIS[1][0] - obx,
											npcs[i].CONTAIN_THIS[1][1], 0, 10,
											0);
									c.getPA().globalObject(534,
											npcs[i].CONTAIN_THIS[1][0],
											npcs[i].CONTAIN_THIS[0][1] + oby,
											0, 10, 0);
									c.getPA().globalObject(534,
											npcs[i].CONTAIN_THIS[0][0],
											npcs[i].CONTAIN_THIS[1][1] - oby,
											0, 10, 0);
									obx++;
									oby++;
								}
								final Player c1 = c;
								final int I = i;
								EventManager.getSingleton().addEvent(
										new Event() {
											@Override
											public void execute(EventContainer e) {
												int obX = 0, obY = 0;
												for (int s = 0; s < 4; s++) {
													c1.getPA()
													.globalObject(
															-1,
															npcs[I].CONTAIN_THIS[0][0]
																	+ obX,
																	npcs[I].CONTAIN_THIS[0][1],
																	0, 10, 0);
													c1.getPA()
													.globalObject(
															-1,
															npcs[I].CONTAIN_THIS[1][0]
																	- obX,
																	npcs[I].CONTAIN_THIS[1][1],
																	0, 10, 0);
													c1.getPA()
													.globalObject(
															-1,
															npcs[I].CONTAIN_THIS[1][0],
															npcs[I].CONTAIN_THIS[0][1]
																	+ obY,
																	0, 10, 0);
													c1.getPA()
													.globalObject(
															-1,
															npcs[I].CONTAIN_THIS[0][0],
															npcs[I].CONTAIN_THIS[1][1]
																	- obY,
																	0, 10, 0);
													obX++;
													obY++;
												}
												npcs[I].CONTAIN_THIS[0][0] = 0;
												e.stop();
											}
										}, 4000);
							} else if (npcs[i].glod == 3) {
								c.getPA().globalObject(534, c.absX - 1,
										c.absY - 1, 0, 10, 0);
								c.getPA().globalObject(534, c.absX, c.absY - 1,
										0, 10, 0);
								c.getPA().globalObject(534, c.absX + 1,
										c.absY - 1, 0, 10, 0);
								c.getPA().globalObject(534, c.absX + 1, c.absY,
										0, 10, 0);
								c.getPA().globalObject(534, c.absX + 1,
										c.absY + 1, 0, 10, 0);
								c.getPA().globalObject(534, c.absX, c.absY + 1,
										0, 10, 0);
								c.getPA().globalObject(534, c.absX - 1,
										c.absY + 1, 0, 10, 0);
								c.getPA().globalObject(534, c.absX - 1, c.absY,
										0, 10, 0);
								final Player c1 = c;
								EventManager.getSingleton().addEvent(
										new Event() {
											@Override
											public void execute(EventContainer e) {
												c1.getPA().globalObject(-1,
														c1.absX - 1,
														c1.absY - 1, 0, 10, 0);
												c1.getPA().globalObject(-1,
														c1.absX, c1.absY - 1,
														0, 10, 0);
												c1.getPA().globalObject(-1,
														c1.absX + 1,
														c1.absY - 1, 0, 10, 0);
												c1.getPA().globalObject(-1,
														c1.absX + 1, c1.absY,
														0, 10, 0);
												c1.getPA().globalObject(-1,
														c1.absX + 1,
														c1.absY + 1, 0, 10, 0);
												c1.getPA().globalObject(-1,
														c1.absX, c1.absY + 1,
														0, 10, 0);
												c1.getPA().globalObject(-1,
														c1.absX - 1,
														c1.absY + 1, 0, 10, 0);
												c1.getPA().globalObject(-1,
														c1.absX - 1, c1.absY,
														0, 10, 0);
												e.stop();
											}
										}, 4000);
							}
						}
					}
					if (multiAttacks(i)) {
						multiAttackGfx(i, npcs[i].projectileId);
						startAnimation(getAttackEmote(i), i);
						npcs[i].oldIndex = c.playerId;
						return;
					}
					if (npcs[i].projectileId > 0) {
						int nX = NPCHandler.npcs[i].getX() + offset(i);
						int nY = NPCHandler.npcs[i].getY() + offset(i);
						int pX = c.getX();
						int pY = c.getY();
						int offX = (nY - pY) * -1;
						int offY = (nX - pX) * -1;
						c.getPA().createPlayersProjectile(nX, nY, offX, offY,
								50, getProjectileSpeed(i),
								npcs[i].projectileId, 43, 31, -c.getId() - 1,
								65);
					}
					c.underAttackBy2 = i;
					c.singleCombatDelay2 = System.currentTimeMillis();
					npcs[i].oldIndex = c.playerId;
					startAnimation(getAttackEmote(i), i);
					c.getPA().removeAllWindows();
				}
			}
		}
	}

	// id of bones dropped by npcs
	public int boneDrop(final int type) {
		switch (type) {
		case 1:
			// normal bones
		case 9:
		case 100:
		case 12:
		case 17:
		case 803:
		case 18:
		case 81:
		case 101:
		case 41:
		case 19:
		case 90:
		case 75:
		case 86:
		case 78:
		case 912:
		case 913:
		case 914:
		case 1648:
		case 1643:
		case 1618:
		case 1624:
		case 181:
		case 119:
		case 49:
		case 26:
		case 1341:
			return 526;
		case 117:
			return 532; // big bones
		case 50:
			// drags
		case 53:
		case 54:
		case 55:
		case 941:
		case 1590:
		case 1591:
		case 1592:
			return 536;
		case 84:
		case 1615:
		case 1613:
		case 82:
		case 3200:
			return 592;
		case 2881:
		case 2882:
		case 2883:
			return 6729;
		default:
			return -1;
		}
	}

	public static boolean checkClipping(int i) {

		/*
		 * for (int x = 0; x < size; x++) { for (int y = 0; y < size; y++) { if
		 * (!VirtualWorld.I(npc.heightLevel, npc.absX + x, npc.absY + y,
		 * npc.absX + npc.moveX, npc.absY + npc.moveY, 0)) return false; } }
		 */
		return true;
	}

	/**
	 * Distanced required to attack
	 **/
	public static int distanceRequired(int i) {
		try {
			switch (npcs[i].npcType) {
			case 1381:
				return 3;
				//case 14301:
				//case 13822:
				//return 2;
			case 13480:
			case 13479:
				return 8;
			case 1590: // dragons
			case 1591:
			case 941:
			case 48:
			case 51:
			case 52:
			case 53:
			case 1592:
				Player c = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY,
						1 + npcs[i].getNPCSize())) {
					npcs[i].dragon = Misc.random(2);
				} else {
					npcs[i].dragon = 2;
				}
				return 3 + npcs[i].getNPCSize();

			case 5346:
			case 5347:
			case 5348:
			case 5297:
			case 5298:
			case 5299:
			case 5300:
			case 1643:
				return 6;


			case 1457:
				return 8;

			case 13447:
				Player c11 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c11.absX,
						c11.absY, 1 + npcs[i].getTotalNPCSize())) {
					if (npcs[i].nexStage == 1 || npcs[i].nexStage == 2) {
						npcs[i].glod = Misc.random2(2);
					} else if ((npcs[i].nexStage == 3 || npcs[i].nexStage == 4)
							|| (npcs[i].nexStage == 5 || npcs[i].nexStage == 6)
							|| (npcs[i].nexStage == 7 || npcs[i].nexStage == 8)) {
						npcs[i].glod = Misc.random2(3);
					} else if (npcs[i].nexStage == 9) {
						npcs[i].glod = Misc.random(1);
					}
				} else {
					if (npcs[i].nexStage == 1 || npcs[i].nexStage == 2) {
						npcs[i].glod = 1 + Misc.random2(1);
					} else if ((npcs[i].nexStage == 3 || npcs[i].nexStage == 4)
							|| (npcs[i].nexStage == 5 || npcs[i].nexStage == 6)
							|| (npcs[i].nexStage == 7 || npcs[i].nexStage == 8)) {
						npcs[i].glod = 1 + Misc.random2(2);
					} else if (npcs[i].nexStage == 9) {
						npcs[i].glod = 1;
					}
				}
				if (npcs[i].cooldown > 0) {
					if (((npcs[i].nexStage == 1 || npcs[i].nexStage == 2) && npcs[i].glod == 2)
							|| ((npcs[i].nexStage == 3 || npcs[i].nexStage == 4) && npcs[i].glod == 2)
							|| ((npcs[i].nexStage == 5 || npcs[i].nexStage == 6)
									&& npcs[i].glod == 2 || npcs[i].glod == 3)
									|| ((npcs[i].nexStage == 7 || npcs[i].nexStage == 8) && (npcs[i].glod == 2 || npcs[i].glod == 3))) {
						npcs[i].glod = 1;
					}
				}
				return 6 + npcs[i].getNPCSize();

			case 907:
			case 908:
			case 909:
			case 910:
			case 911:
				return 6 + npcs[i].getNPCSize();

			case 3072:
				Player c1 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c1.absX, c1.absY,
						1 + npcs[i].getNPCSize())) {
					if (npcs[i].dragon != 7)
						npcs[i].dragon = Misc.random(6);
				} else {
					npcs[i].dragon = 2 + Misc.random(4);
				}
				return 8 + npcs[i].getNPCSize();

			case 5666:
			case 7770:
				Player c5 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c5.absX, c5.absY,
						1 + npcs[i].getNPCSize())) {
					npcs[i].glod = Misc.random(1);
				} else {
					npcs[i].glod = 1;
				}
				return 8 + npcs[i].getNPCSize();

			case 5810:
				Player c6 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c6.absX, c6.absY,
						1 + npcs[i].getNPCSize())) {
					npcs[i].glod = Misc.random(1);
				} else {
					npcs[i].glod = 1;
				}
			case 2606:
			case 2592:
				return 4 + npcs[i].getNPCSize();

			case 6021:
			case 6006:
			case 6007:
			case 6008:
				Player c8 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c8.absX, c8.absY,
						npcs[i].getNPCSize())) {
					npcs[i].glod = Misc.random(2);
				} else {
					npcs[i].glod = 1 + Misc.random(1);
				}
				return 8 + npcs[i].getNPCSize();

			case 6017:
			case 6018:
			case 6019:
			case 6020:
				Player c9 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c9.absX, c9.absY,
						1)) {
					npcs[i].glod = Misc.random(4);
				} else {
					npcs[i].glod = 1 + Misc.random(3);
				}
				if (npcs[i].glod == 4 && c9.freezeTimer > -6) {
					npcs[i].glod = 3;
				}
				return 8;

			case 6012:
			case 6009:
			case 6010:
			case 6011:
				npcs[i].glod = (Misc.random(10) >= 9 ? 1 : 0);
				return npcs[i].getNPCSize();

			case 8133:
				Player c7 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c7.absX, c7.absY,
						1 + npcs[i].getNPCSize())) {
					npcs[i].glod = Misc.random(4);
				} else {
					npcs[i].glod = 2 + Misc.random(2);
				}
				return 30 + npcs[i].getNPCSize();

			case 8349:
			case 8350:
			case 8351:
				Player c2 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, c2.absX, c2.absY,
						1 + npcs[i].getNPCSize())) {
					npcs[i].glod = Misc.random(4);
				} else {
					npcs[i].glod = 2 + Misc.random(2);
				}
				return 8 + npcs[i].getNPCSize();

			case 5808:
				return 0;

			case 2059:
				Player r = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, r.absX, r.absY,
						1 + npcs[i].getNPCSize())) {
					npcs[i].tarn = Misc.random(2);
				} else {
					npcs[i].tarn = 1 + Misc.random(1);
				}
				return 8 + npcs[i].getNPCSize();

			case 2057:
				Player r1 = PlayerHandler.players[npcs[i].killerId];
				if (goodDistance(npcs[i].absX, npcs[i].absY, r1.absX, r1.absY,
						1 + npcs[i].getNPCSize())) {
					npcs[i].glod = Misc.random(1);
				} else {
					npcs[i].glod = 1;
				}
				return 8 + npcs[i].getNPCSize();

			case 2457:
			case 6206:
			case 1351:
				return 4 + npcs[i].getNPCSize();

			case 1343:
				return 3 + npcs[i].getNPCSize();

			case 2025:
			case 2028:
			case 2631:
			case 1472:
			case 6208:
			case 6250:
			case 6252:
			case 6265:
			case 6263:
			case 6225:
			case 2183:
			case 2184:
			case 6223:
			case 3761:
			case 3771:
			case 2188:
			case 2190:
			case 6230:
			case 6231:
			case 6232:
			case 6233:
			case 6234:
			case 6235:
			case 6236:
			case 6237:
			case 6238:
			case 6239:
			case 6240:
			case 6241:
			case 6242:
			case 6243:
			case 6244:
			case 6245:
			case 6246:
				return 6 + npcs[i].getNPCSize();

			case 2881:
			case 2882:
			case 50:
			case 8596:
				return 8 + npcs[i].getNPCSize();

			case 2894:
			case 2896:
			case 1158:
			case 1160:
				return 12 + npcs[i].getNPCSize();

			case 2743:
			case 2745:
			case 5637:
			case 6222:
				return 9 + npcs[i].getNPCSize();

			case 3150:
				return 20;
			case 9462:
			case 9463:
			case 9464:
			case 9465:
			case 9466:
			case 9467:
				return 6 + npcs[i].getNPCSize();

			default:
				return npcs[i].getNPCSize();
			}
		} catch (Exception _e) {
		}
		return npcs[i].getNPCSize();
	}

	/**
	 * Big NPC
	 **/

	public void dropItems(final int i) {
		int npc = 0;
		final Player c = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
		if (c != null) {
			for (int o = 0; o < c.barrowsNpcs.length; o++) {
				if (NPCHandler.npcs[i].npcType == c.barrowsNpcs[o][0]) {
					c.barrowsNpcs[o][1] = 2; // 2 for dead
				}
			}

			if (npcs[i].npcType > 0 && !isDungNPC(npcs[i])) {
				int random2 = Misc.random(12);
				if (random2 == 11) {
				if (c.charmingImpCollect == 1) {
				if (c.charmingImpCollect == 1 && !c.getItems().playerHasItem(9952, 1)) {
					c.sendMessage("As you do not have the <col=255>Charming Imp</col> in your inventory.");
					c.sendMessage("He has <col=255>Canceled</col> hes services with you.");
					c.charmingImpCollect = 0;
					c.charmingImpXP = 0;
					return;
				}
					c.getItems().addItem(12158, 1);
					c.sendMessage("The Charming Imp found a <col=255>GOLD</col> Charm and put it in your inventory.");
				} else if (c.charmingImpXP == 1) {
					//GiveXP
				} else {
					Server.itemHandler.createGroundItem(c, 12158, npcs[i].absX,
							npcs[i].absY, 1, c.playerId); //Gold charm
					}
				}
				if (random2 == 7) {
				if (c.charmingImpCollect == 1) {
				if (c.charmingImpCollect == 1 && !c.getItems().playerHasItem(9952, 1)) {
					c.sendMessage("As you do not have the <col=255>Charming Imp</col> in your inventory.");
					c.sendMessage("He has <col=255>Canceled</col> hes services with you.");
					c.charmingImpCollect = 0;
					c.charmingImpXP = 0;
					return;
				}
					c.getItems().addItem(12159, 1);
					c.sendMessage("The Charming Imp found a <col=255>GREEN</col> Charm and put it in your inventory.");
				} else if (c.charmingImpXP == 1) {
					//GiveXP
				} else {
					Server.itemHandler.createGroundItem(c, 12159, npcs[i].absX,
							npcs[i].absY, 1, c.playerId);
					}
				}
				if (random2 == 5) {
				if (c.charmingImpCollect == 1) {
				if (c.charmingImpCollect == 1 && !c.getItems().playerHasItem(9952, 1)) {
					c.sendMessage("As you do not have the <col=255>Charming Imp</col> in your inventory.");
					c.sendMessage("He has <col=255>Canceled</col> hes services with you.");
					c.charmingImpCollect = 0;
					c.charmingImpXP = 0;
					return;
				}
					c.getItems().addItem(12160, 1);
					c.sendMessage("The Charming Imp found a <col=255>CRIMSON</col> Charm and put it in your inventory.");
				} else if (c.charmingImpXP == 1) {
					//GiveXP
				} else {
					Server.itemHandler.createGroundItem(c, 12160, npcs[i].absX,
							npcs[i].absY, 1, c.playerId);
					}
				}

				if (random2 == 2) {
				if (c.charmingImpCollect == 1) {
				if (c.charmingImpCollect == 1 && !c.getItems().playerHasItem(9952, 1)) {
					c.sendMessage("As you do not have the <col=255>Charming Imp</col> in your inventory.");
					c.sendMessage("He has <col=255>Canceled</col> hes services with you.");
					c.charmingImpCollect = 0;
					c.charmingImpXP = 0;
					return;
				}
					c.getItems().addItem(12163, 1);
					c.sendMessage("The Charming Imp found a <col=255>BLUE</col> Charm and put it in your inventory.");
				} else if (c.charmingImpXP == 1) {
					//GiveXP
				} else {
					Server.itemHandler.createGroundItem(c, 12163, npcs[i].absX,
							npcs[i].absY, 1, c.playerId);
					}
				}

			}
			switch(npcs[i].npcType) {

			case 1265:
			case 2452:
				AchievementManager.increase(c, Achievements.THE_ROCK);
				break;
			case 13447:
				AchievementManager.increase(c, Achievements.NEX_DESTROYER);
				break;
			case 6260:
				AchievementManager.increase(c, Achievements.GWD_WARRIOR);
				break;
			case 6247:
				AchievementManager.increase(c, Achievements.GWD_QUEEN);
				break;
			case 6203:
				AchievementManager.increase(c, Achievements.GWD_DARKER);
				break;
			case 6222:
				AchievementManager.increase(c, Achievements.GWD_RANGER);
				break;
			case 50:
				AchievementManager.increase(c, Achievements.KING_BLACK_KING);
				break;


			case 912:
			case 913:
			case 914:
				c.magePoints += 1;
				break;
			case 1610:
			case 1611:
				c.smashedGargoyle = false;
				break;
			case 8349:
				Player.protMelee = true;
				Player.protMage = false;
				Player.protRange = false;
				break;
			case 4279:
			case 4280:
			case 4281:
			case 4282:
			case 4283:
			case 4284:
				WarriorsGuild.getInstance().getDrop(c, npcs[i].absX, npcs[i].absY);
				break;

			case 4291:
			case 4292:
				WarriorsGuild.getInstance().killedCyclop(c, npcs[i].absX, npcs[i].absY);
				break;

			case 3493:
				c.Agrith = true;
				c.sendMessage("You can now access up to <col=255>"+ RFD.getGloves(c) +"");
				break;

			case 3494:
				c.Flambeed = true;
				c.sendMessage("You can now access up to <col=255>"+ RFD.getGloves(c) +"");
				break;

			case 3495:
				c.Karamel = true;
				c.sendMessage("You can now access up to <col=255>"+ RFD.getGloves(c) +"");
				break;

			case 3496:
				c.Dessourt = true;
				c.sendMessage("You can now access up to <col=255>"+ RFD.getGloves(c) +"");
				break;

			case 3491:
				c.Culin = true;
				c.sendMessage("You can now access up to <col=255>"+ RFD.getGloves(c) +"");
				break;
			}
			Slayer.appendSlayerExperience(i);
			c.totalNPCKO += 1;
			AchievementManager.increase(c, Achievements.THE_KILLER);

			for (npc = 0; npc < NPCDrops.NPC_DROPS.length; npc++) {
				if (NPCHandler.npcs[i].npcType == NPCDrops.NPC_DROPS[npc][0]) {
				/*if (c.playerEquipment[c.playerRing] == 2572 && Misc.random(1) == 0) {
					if (NPCDrops.NPC_DROPS[npc][3] >= 50 && NPCDrops.NPC_DROPS[npc][3] <= 350) {
						Server.itemHandler.createGroundItem(c,
								NPCDrops.NPC_DROPS[npc][1],
								NPCHandler.npcs[i].absX,
								NPCHandler.npcs[i].absY,
								NPCDrops.NPC_DROPS[npc][2], c.playerId);
						c.sendMessage("Your <col=255>Ring of Wealth glows brightly</col> and helps you with a drop.");
					}
				} else {*/
					if (Misc.random(NPCDrops.NPC_DROPS[npc][3]) == 0) {
						Server.itemHandler.createGroundItem(c,
								NPCDrops.NPC_DROPS[npc][1],
								NPCHandler.npcs[i].absX,
								NPCHandler.npcs[i].absY,
								NPCDrops.NPC_DROPS[npc][2], c.playerId);
					}
				}
			}
if (npcs[i].npcType > 0 && !isDungNPC(npcs[i])) {
	if (Misc.random(150) == 33) {
	int[] eClues = {
		2677, 2678, 2679, 2680, 2681, 2682, 2683, 2684,
	};
		Server.itemHandler.createGroundItem(c, eClues[Misc.random(7)], NPCHandler.npcs[i].absX, NPCHandler.npcs[i].absY, 1, c.playerId);
	}

	if (Misc.random(220) == 28) {
	int[] mClues = {
		2801, 2802, 2803, 2804, 2805,
	};
		Server.itemHandler.createGroundItem(c, mClues[Misc.random(4)], NPCHandler.npcs[i].absX, NPCHandler.npcs[i].absY, 1, c.playerId);
	}

	if (Misc.random(350) == 63) {
	int[] hClues = {
		2722, 2723, 2724, 2725, 2726,
	};
		Server.itemHandler.createGroundItem(c, hClues[Misc.random(4)], NPCHandler.npcs[i].absX, NPCHandler.npcs[i].absY, 1, c.playerId);
	}
			if (Misc.random(200) == 0) {
				Server.itemHandler.createGroundItem(c,
						18778,
						NPCHandler.npcs[i].absX,
						NPCHandler.npcs[i].absY,
						1,
						c.playerId);
				AncientEffigies.handleNewEffigySkill(c);
				c.sendMessage("You need a "+levelName[c.effigySkill]+" level of "+ c.effigySkillLvl +" to active this effigy.");
				c.getDH().sendDialogues(158, 0);
				AchievementManager.increase(c, Achievements.EFFIGY_LOCATOR);
			}
		}
	}
}

	public static final String levelName[] = {"Attack", "Defence", "Strength", "Hitpoints", "Ranged", "Prayer", "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting", "Smithing", "Mining","Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting","Hunter", "Summoning", "Construction", "Dungeoneering"};


	public int followDistance(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 2550:
		case 2551:
		case 2562:
		case 2563:
			return 8;
		case 6829:
			return 2;

		}
		return 0;

	}

	public static boolean followPlayer(int i) {
		int[] noMove = { 7770, 6142, 6143, 6144, 6145, 2894, 2896, 6006, 6007,
				6008, 5666, 1944, 1946, 13454, 13451, 5631, 13452, 9462 };
		switch (NPCHandler.npcs[i].npcType) {
		case 2892:
		case 2894:
			return false;
		}
		for (int no = 0; no < noMove.length; no++) {
			if (npcs[i].npcType == noMove[no]) {
				return false;
			}
		}
		return true;
	}

	public int getClosePlayer(int i) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (j == npcs[i].spawnedBy)
					return j;
				if (goodDistance(PlayerHandler.players[j].absX,
						PlayerHandler.players[j].absY, npcs[i].absX,
						npcs[i].absY, extraDistance(i) + distanceRequired(i))
						|| isFightCaveNpc(i) || isRFDNpc(i)) {
					if ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0)
							|| PlayerHandler.players[j].inMulti())
						if (npcs[i].heightLevel == PlayerHandler.players[j].heightLevel)
							if (npcs[i].npcType == 1266)
								npcs[i].requestTransform(1265);
					if (npcs[i].npcType == 2453)
						npcs[i].requestTransform(2452);
					if (npcs[i].npcType == 2890)
						npcs[i].requestTransform(2889);
					else if (npcs[i].npcType == 8133) {
						if (!PlayerHandler.players[j].inCorp())
							return 0;
					} else if (npcs[i].npcType == 5808) {
						if (!PlayerHandler.players[j].inCorp()) {
							return 0;
						}
					}
					return j;
				}
			}
		}
		return 0;
	}

	public int extraDistance(int i) {
		switch (npcs[i].npcType) {
		case 6203:
		case 6208:
		case 6204:
		case 6206:
		case 6247:
		case 6252:
		case 6250:
		case 6248:
		case 6260:
		case 6265:
		case 6263:
		case 6261:
		case 6223:
		case 6225:
		case 6227:
		case 6222:
		case 5808:
		case 7135:
			return 15;
		case 6021:
		case 6006:
		case 6007:
		case 6008:
		case 6017:
		case 6018:
		case 6019:
		case 6020:
		case 7772:
		case 13447:
		case 1457:
			return 8;
		case 3771:
		case 3761:
		case 3776:
		case 7643:
			return 4;
		case 2059:
		case 2057:
		case 8133:
			return 6;
		case 8349:
		case 8350:
		case 8351:
			if (npcs[i].attackType > 0) {
				return 6;
			} else {
				return 2;
			}
		case 2453:
		case 1266:
		case 2890:
			return 0;
		default:
			return 2;
		}
	}

	public int getCloseRandomPlayer(int i) {
		ArrayList<Integer> players = new ArrayList<Integer>();
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null
					&& (goodDistance(PlayerHandler.players[j].getAbsX(),
							PlayerHandler.players[j].getAbsY(),
							npcs[i].getAbsX(), npcs[i].getAbsY(),
							7 + followDistance(i)) || isFightCaveNpc(i) || isRFDNpc(i))
							&& ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0) || PlayerHandler.players[j]
									.inMulti())
									&& PlayerHandler.players[j].heightLevel == npcs[i].heightLevel) {
				players.add(j);
			}
		}
		return players.size() > 0 ? players
				.get(Misc.random(players.size() - 1)) : 0;
	}

	public int getCloseRandomPlayer1(int i) {
		ArrayList<Integer> players = new ArrayList<Integer>();
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null
					&& (goodDistance(PlayerHandler.players[j].getAbsX(),
							PlayerHandler.players[j].getAbsY(),
							npcs[i].getAbsX(), npcs[i].getAbsY(),
							50 + followDistance(i)) || isFightCaveNpc(i))
							&& ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0) || PlayerHandler.players[j]
									.inMulti())
									&& PlayerHandler.players[j].heightLevel == npcs[i].heightLevel) {
				if (PlayerHandler.players[j].inGodWarsBoss()) {
					players.add(j);
				}
			}
		}
		return players.size() > 0 ? players
				.get(Misc.random(players.size() - 1)) : 0;
	}

	public int getDeadEmote(final int i) {
		return DeathAnimation.handleEmote(i);
	}

	public int getHitDelay(int i) {
		switch (npcs[i].npcType) {
		case 6021:
		case 6006:
		case 6007:
		case 6008:
		case 6017:
		case 6018:
		case 6019:
		case 6020:
			return (npcs[i].glod > 0 ? 4 : 2);

		case 13447:

			if (npcs[i].nexStage == 1 || npcs[i].nexStage == 2) {
				switch (npcs[i].glod) {
				case 1:
				case 2:
					return 4;
				}
			} else if (npcs[i].nexStage == 3 || npcs[i].nexStage == 4) {
				switch (npcs[i].glod) {
				case 1:
				case 2:
					return 4;
				}
			} else if (npcs[i].nexStage == 5 || npcs[i].nexStage == 6) {
				switch (npcs[i].glod) {
				case 1:
				case 2:
					return 4;
				}
			} else if (npcs[i].nexStage == 7 || npcs[i].nexStage == 8) {
				switch (npcs[i].glod) {
				case 1:
					return 4;
				case 2:
				case 3:
					return 6;
				}
			} else if (npcs[i].nexStage == 9) {
				switch (npcs[i].glod) {
				case 1:
					return 4;
				}
			}
			return 2;

		case 5346:
		case 5347:
		case 5348:
		case 5297:
		case 5298:
		case 5299:
		case 5300:
			return 3;

		case 2745:
		case 5637:
			if (npcs[i].jad == 1) {
				return 6;
			}
			return 5;

		case 6260:
			npcs[i].graardor = Misc.random(5);
			if (npcs[i].graardor == 5) {
				return 3;
			}
			return 2;

		case 6203:
			npcs[i].tsutsaroth = Misc.random(4);
			if (npcs[i].tsutsaroth == 4) {
				return 3;
			}
			return 2;

		case 6247:
			npcs[i].zilyana = Misc.random(6);
			if (npcs[i].zilyana == 6 || npcs[i].zilyana == 5
					|| npcs[i].zilyana == 4) {
				return 6;
			}
			return 2;

		case 2025:
		case 1472:
		case 6222:
		case 2457:
		case 2894:
		case 2882:
		case 946:
		case 50:
		case 2631:
		case 2743:
		case 6208:
		case 6250:
		case 6263:
		case 6223:
			return 4;

		case 2028:
		case 2190:
		case 1343:
		case 1351:
		case 2496:
		case 2881:
		case 1158:
		case 1160:
		case 2059:
		case 6206:
		case 6252:
		case 6265:
		case 6225:
		case 2183:
		case 2184:
		case 2188:
		case 1590: // dragons
		case 1591:
		case 1592:
		case 2057:
		case 1643:
		case 6230:
		case 6231:
		case 6232:
		case 6233:
		case 6234:
		case 6235:
		case 6236:
		case 6237:
		case 6238:
		case 6239:
		case 6240:
		case 6241:
		case 6242:
		case 6243:
		case 6244:
		case 6245:
		case 6246:
			return 3;

		case 8133:
			switch (npcs[i].glod) {
			case 1:
				return 2;
			case 2:
				return 5;
			case 3:
				return 3;
			case 4:
				return 4;
			}
			return 3;

		case 3072:
		case 3073:
			if (npcs[i].dragon < 2) {
				return 2;
			}
			return 3;

		case 8349:
		case 8350:
		case 8351:
			return 2;
		case 9462:
		case 9464:
		case 9466:
		case 9463:
		case 9465:
		case 9467:
			if (NPCHandler.npcs[i].attackType == 1) {
				return 8;
			} else if (NPCHandler.npcs[i].attackType == 2 && BurrowWait >= 1) {
				return 4;
			} else if (NPCHandler.npcs[i].attackType == 0 && BurrowWait >= 1) {
				return 4;
			} else if (NPCHandler.npcs[i].attackType == 2 && BurrowWait > 1) {
				return 10;
			} else if (NPCHandler.npcs[i].attackType == 0 && BurrowWait > 1) {
				return 10;
			}

		default:
			return 2;
		}
	}

	public int getMaxHit(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 2558:
			if (NPCHandler.npcs[i].attackType == 2) {
				return 28;
			} else {
				return 68;
			}
		case 13447:
			return 50;
		case 6829:
			return 4;
		case 3373:
			return 45;
		case 2562:
			return 31;
		case 2550:
			return 36;
		case 9466:
			return 27;
		case 9462:
			return 38;
		case 9464:
			return 32;
		case 1913:
		case 1914:
		case 1977:
		case 1974:
		case 1975:
			return 15;
		case 1590:
			return 18;
		}
		return 1;
	}

	/**
	 * Npc Follow Player
	 **/

	public static int GetMove(final int Place1, final int Place2) {
		if (Place1 - Place2 == 0) {
			return 0;
		} else if (Place1 - Place2 < 0) {
			return 1;
		} else if (Place1 - Place2 > 0) {
			return -1;
		}
		return 0;
	}

	/**
	 * Attack delays
	 **/
	public int getNpcDelay(final int i) {
		switch (NPCHandler.npcs[i].npcType) {

		case 1381:
			return 7;

		case 14301:
		case 13822:
			return 7;

		case 2025:
		case 2028:
			return 7;

		case 2745:
			return 8;

		case 8133:
			return Misc.random(1) == 1 ? 5 : 6;

		case 8349:
		case 8350:
		case 8351:
			return 5;
		case 9462:
		case 9464:
		case 9466:
		case 9463:
		case 9465:
		case 9467:
			if (NPCHandler.npcs[i].attackType == 1) {
				return 8;
			} else if (NPCHandler.npcs[i].attackType == 2 && BurrowWait <= 1) {
				return 4;
			} else if (NPCHandler.npcs[i].attackType == 0 && BurrowWait <= 1) {
				return 4;
			} else if (NPCHandler.npcs[i].attackType == 2 && BurrowWait > 1) {
				return 10;
			} else if (NPCHandler.npcs[i].attackType == 0 && BurrowWait > 1) {
				return 10;
			}

		case 13447:
			if ((npcs[i].nexStage == 3 || npcs[i].nexStage == 4)
					&& npcs[i].glod == 3) {
				return 8;
			}
			if ((npcs[i].nexStage == 5 || npcs[i].nexStage == 6)
					&& npcs[i].glod == 3) {
				return 18;
			}
			if ((npcs[i].nexStage == 7 || npcs[i].nexStage == 8)
					&& (npcs[i].glod == 2 || npcs[i].glod == 3)) {
				return 8;
			}
			return 6;

		case 9948:
		case 9951:
		case 9916:
			return 6;

		case 10802:
		case 10801:
			return 5;

		case 2558:
		case 2559:
		case 2560:
		case 2561:
		case 2550:
			return 6;
			// saradomin gw boss
		case 2562:
			return 2;

		case 6260:
		case 6203:
		case 6222:
		case 6247:
			return 6;

			/**
			 * Familiars
			 **/

		case 6875:
		case 6877:
		case 6879:
		case 6881:
		case 6883:
		case 6885:
		case 6887:
		case 6829:
		case 6825:
		case 6841:
		case 6806:
		case 6796:
		case 7331:
		case 6831:
		case 6837:
		case 7361:
		case 6847:
		case 6994:
		case 6871:
		case 7353:
		case 6835:
		case 6845:
		case 6808:
		case 7370:
		case 7367:
		case 7351:
		case 7333:
		case 6853:
		case 6867:
		case 6851:
		case 6833:
		case 6855:
		case 7377:
		case 6824:
		case 6843:
		case 6794:
		case 6818:
		case 6992:
		case 6857:
		case 6991:
		case 7363:
		case 7365:
		case 7337:
		case 6809:
		case 6865:
		case 6820:
		case 6802:
		case 6827:
		case 6859:
		case 6889:
		case 6815:
		case 6813:
		case 6817:
		case 7372:
		case 6839:
		case 8575:
		case 7345:
		case 6849:
		case 6798:
		case 6861:
		case 7335:
		case 7347:
		case 6800:
		case 7355:
		case 7357:
		case 7359:
		case 6811:
		case 6804:
		case 7341:
		case 7329:
		case 6863:
		case 6822:
		case 7339:
		case 6869:
		case 7349:
		case 7375:
		case 6873:
		case 7343:
			return 7;
		default:
			return 4;
		}
	}

	/**
	 * Npc killer id?
	 **/

	public int getNpcKillerId(final int npcId) {
		int oldDamage = 0;
		int killerId = 0;
		for (int p = 1; p < Config.MAX_PLAYERS; p++) {
			if (PlayerHandler.players[p] != null) {
				if (PlayerHandler.players[p].lastNpcAttacked == npcId) {
					if (PlayerHandler.players[p].totalDamageDealt > oldDamage) {
						oldDamage = PlayerHandler.players[p].totalDamageDealt;
						killerId = p;
					}
					PlayerHandler.players[p].totalDamageDealt = 0;
				}
			}
		}
		return killerId;
	}

	public static int getNpcListHP(int npcId) {
		if (npcId <= -1) {
			return 0;
		}
		if (NPCDefinitions.getDefinitions()[npcId] == null) {
			return 0;
		}
		return NPCDefinitions.getDefinitions()[npcId].getNpcHealth();

	}

	public int getProjectileSpeed(int i) {
		switch (npcs[i].npcType) {
		case 6222:
		case 2745:
		case 5637:
			return 135;

		case 6225:
		case 6223:
		case 6203:
		case 2183:
		case 2188:
		case 2184:
		case 6260:
		case 6230:
		case 6231:
		case 6232:
		case 6233:
		case 6234:
		case 6235:
		case 6236:
		case 6237:
		case 6238:
		case 6239:
		case 6240:
		case 6241:
		case 6242:
		case 6243:
		case 6244:
		case 6245:
		case 6246:
			return 120;

		case 6208:
		case 6265:
		case 6263:
		case 6021:
		case 6006:
		case 6007:
		case 6008:
		case 6017:
		case 6018:
		case 6019:
		case 6020:
			return 110;

		case 1343:
		case 1351:
		case 2457:
		case 2894:
		case 2896:
		case 3761:
		case 3771:
		case 1643:
		case 2881:
		case 2882:
		case 3072:
		case 3073:
		case 1590: // dragons
		case 1591:
		case 1592:
		case 946:
		case 50:
		case 3200:
		case 2631:
		case 2743:
		case 1472:
		case 6250:
		case 8133:
		case 5297:
		case 5298:
		case 5299:
		case 5300:
		case 5346:
		case 8349:
		case 8350:
		case 13447:
		case 8351:
		case 5347:
		case 5348:
		case 8596:
			return 100;

		case 2025:
		case 6206:
		case 6252:
			return 90;

		case 2028:
		case 1158:
		case 1160:
		case 2059:
			return 85;

		default:
			return 70;
		}
	}

	/**
	 * Npc respawn time
	 **/
	public int getRespawnTime(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 1158:
		case 1160:
		case 6142:
		case 6143:
		case 6144:
		case 6145:
		case 6208:
		case 6204:
		case 6206:
		case 6252:
		case 6250:
		case 6248:
		case 6227:
		case 6225:
		case 6223:
		case 6265:
		case 6263:
		case 6261:
		case 3073:
		case 5808:
		case 7772:
		case 7135:
		case 13454:
		case 13451:
		case 5631:
		case 13452:
		case 7643:
			return -1;
		case 2881:
		case 2882:
		case 2883:
		case 2558:
		case 2559:
		case 2560:
		case 2561:
		case 2562:
		case 2563:
		case 2564:
		case 2550:
		case 2551:
		case 2552:
		case 2553:
		case 14301://Glacors
		case 13447://Nex
		case 3200:
		case 8596:
			return 100;
		case 8133:
		case 6054:
		case 8349:
		case 8350:
		case 8351:
		case 9463:
		case 9462:
		case 9465:
		case 9467:
		case 9464:
		case 9466:
			return 200;
		case 3777:
		case 3778:
		case 3779:
		case 3780:
			return 500;

		case 5085:
		case 5084:
		case 5083:
		case 5082:
			return 10;

		case 1028:
			return 15;
		case 1029:
		case 13822:
		case 13480:
		case 13479:
		case 51:
			return 20;
		case 1031:
			return 30;
		case 1032:
			return 35;
		case 5219:
		case 6150:
		case 6151:
		case 6152:
		case 6153:
			return 500;
		case 1033:
			return 50;
		case 1034:
			return 100;
		case 1035:

		case 6260:
		case 6247:
		case 6222:
		case 6203:
			return 150;
		case 6063:
			return 500;
		case 6064:
			return 1000;

		default:
			if (npcs[i].MaxHP > 100) {
				return npcs[i].MaxHP / 2;
			}
			return npcs[i].MaxHP;
		}
	}

	public boolean getsPulled(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 2550:
			if (NPCHandler.npcs[i].firstAttacker > 0) {
				return false;
			}
			break;
		}
		return true;
	}

	public int getStackedDropAmount(final int itemId, final int npcId) {
		switch (itemId) {
		case 995:
			switch (npcId) {
			case 1:
				return 50 + Misc.random(50);
			case 9:
				return 133 + Misc.random(100);
			case 1624:
				return 1000 + Misc.random(300);
			case 1618:
				return 1000 + Misc.random(300);
			case 1643:
				return 1000 + Misc.random(300);
			case 1610:
				return 1000 + Misc.random(1000);
			case 1613:
				return 1500 + Misc.random(1250);
			case 1615:
				return 3000;
			case 18:
				return 500;
			case 101:
				return 60;
			case 913:
			case 912:
			case 914:
				return 750 + Misc.random(500);
			case 1612:
				return 250 + Misc.random(500);
			case 1648:
				return 250 + Misc.random(250);
			case 90:
				return 200;
			case 82:
				return 1000 + Misc.random(455);
			case 52:
				return 400 + Misc.random(200);
			case 49:
				return 1500 + Misc.random(2000);
			case 1341:
				return 1500 + Misc.random(500);
			case 26:
				return 500 + Misc.random(100);
			case 20:
				return 750 + Misc.random(100);
			case 21:
				return 890 + Misc.random(125);
			case 117:
				return 500 + Misc.random(250);
			case 2607:
				return 500 + Misc.random(350);
			}
			break;
		case 11212:
			return 10 + Misc.random(4);
		case 565:
		case 561:
			return 10;
		case 560:
		case 563:
		case 562:
			return 15;
		case 555:
		case 554:
		case 556:
		case 557:
			return 20;
		case 892:
			return 40;
		case 886:
			return 100;
		case 6522:
			return 6 + Misc.random(5);

		}

		return 1;
	}

	public static boolean goodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		return ((objectX - playerX <= distance && objectX - playerX >= -distance) && (objectY
				- playerY <= distance && objectY - playerY >= -distance));
	}

	/*
	 * public boolean goodDistance(int objectX, int objectY, int playerX, int
	 * playerY, int distance) { return ((objectX-playerX < distance &&
	 * objectX-playerX > -distance) && (objectY-playerY < distance &&
	 * objectY-playerY > -distance)); }
	 */

	public void handleSpecialEffects(final Player c, final int i,
			final int damage) {
		int random = Misc.random(3);
		int random2 = Misc.random(6);
		int damage1 = 0;
		if (NPCHandler.npcs[i].npcType == 2892
				|| NPCHandler.npcs[i].npcType == 2894) {
			if (damage > 0) {
				if (c != null) {
					if (c.playerLevel[5] > 0) {
						c.playerLevel[5]--;
						c.getPA().refreshSkill(5);
						c.getPA().appendPoison(12);
					}
				}
			}
		}
		if (NPCHandler.npcs[i].npcType >= 9948
				&& NPCHandler.npcs[i].npcType <= 9964) {
			if (random == 0) {
				c.gfx100(2010);
				c.sendMessage("The cold breath of the beast weakens you.");
				if (NPCHandler.npcs[i].npcType == 9948) {
					damage1 = Misc.random(3) + 5;
				} else if (NPCHandler.npcs[i].npcType == 9951
						&& c.complexity == 2) {
					damage1 = Misc.random(10) + 5;
				} else if (NPCHandler.npcs[i].npcType == 9951
						&& c.complexity == 3) {
					damage1 = Misc.random(15) + 5;
				} else if (NPCHandler.npcs[i].npcType == 9951
						&& c.complexity == 4) {
					damage1 = Misc.random(20) + 5;
				}
				NPCHandler.npcs[i].startAnimation(13713);
				// c.handleHitMask(damage, 0, 0);
				c.playerLevel[3] -= damage1;
				c.getPA().refreshSkill(3);
				c.updateRequired = true;
			}
		}
		if (NPCHandler.npcs[i].npcType >= 9912
				&& NPCHandler.npcs[i].npcType <= 9928) {
			if (random == 0) {
				c.gfx100(2613);
				c.sendMessage("The magic of this creature weakens you.");
				if (NPCHandler.npcs[i].npcType == 9916) {
					damage1 = Misc.random(15);
				}
				c.handleHitMask(damage, 0, 2);
				c.playerLevel[3] -= damage1;
				c.getPA().refreshSkill(3);
				c.updateRequired = true;
			}
		}
		if (NPCHandler.npcs[i].npcType == 9727) {
			if (random2 == 0) {
				NPCHandler.npcs[i].gfx100(2600);
				NPCHandler.npcs[i].HP += Misc.random(c.complexity * 5);
				c.updateRequired = true;
			}
		}

		if (NPCHandler.npcs[i].npcType == 13479
				|| NPCHandler.npcs[i].npcType == 13480) {
			if (NPCHandler.npcs[i].HP < 100) {
				NPCHandler.npcs[i].HP += 2;
				c.updateRequired = true;
			}
		}
	}

	public boolean isReallyAggressive(int i) {
		switch (npcs[i].npcType) {
		case 6203:
		case 6208:
		case 6204:
		case 6206:
		case 6247:
		case 6252:
		case 6250:
		case 6248:
		case 6260:
		case 6265:
		case 6263:
		case 6261:
		case 6223:
		case 6225:
		case 6227:
		case 6222:
			return true;
		}
		return false;
	}

	public boolean isAggressive(int i) {
		switch (npcs[i].npcType) {
		case 1457:
		case 1459:
		case 73:
		case 9463:
		case 9465:
		case 9467:
		case 7134:
		case 1343:
		case 7772:
		case 1342:
		case 2892:
		case 3771:
		case 3761:
		case 8596:
		case 3776:
		case 2457:
		case 2890:
		case 2894:
		case 2896:
		case 2881:
		case 1976:
		case 1973:
		case 2882:
		case 2883:
		case 1590: // dragons
		case 1591:
		case 8133:
		case 5808:
		case 3072:
		case 7135:
		case 2627:
		case 2631:
		case 2738:
			//case 1266:
		case 2453:
		case 2743:
		case 2741:
		case 5637:
		case 2745:
		case 2606:
		case 2611:
		case 2599:
		case 2592:
		case 2630:
		case 3200:
		case 1158:
		case 1160:
		case 1155:
		case 1154:
		case 1156:
		case 1472:
		case 2059:
		case 2057:
		case 7770:
		case 7643:
		case 4685:
		case 4686:
		case 4687:
		case 5342:
		case 5346:
		case 5297:
		case 5298:
		case 5299:
		case 5300:
		case 5347:
		case 5348:
		case 5343:
		case 5344:
		case 5345:
		case 4942:
		case 4943:
		case 4944:
		case 4945:
		case 4694:
		case 4695:
		case 4696:
		case 5293:
		case 5294:
		case 5295:
		case 5296:
		case 4689:
		case 4690:
		case 4691:
		case 4692:
		case 5335:
		case 5336:
		case 5337:
		case 5338:
		case 6021:
		case 6006:
		case 6007:
		case 6008:
		case 6009:
		case 6010:
		case 6011:
		case 6012:
		case 6013:
		case 6014:
		case 6015:
		case 6016:
		case 6017:
		case 6018:
		case 6019:
		case 6020:
		case 1926:
			return true;
		}
		if (isRFDNpc(i)) {
			return true;
		}
		return false;
	}

	public static boolean isFightCaveNpc(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 2627:
		case 2630:
		case 2631:
		case 2741:
		case 2743:
		case 2745:
			return true;
		}
		return false;
	}

	public boolean isRFDNpc(int i) {
		switch (npcs[i].npcType) {
		case 3493:
		case 3494:
		case 3495:
		case 3496:
		case 3491:
			return true;		
		}
		return false;
	}

	public static boolean isDungBoss(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 9948:
		case 9949:
		case 9950:
		case 9951:
		case 9952:
		case 9953:
		case 9954:
		case 9955:
		case 9956:
		case 9957:
		case 9727:
		case 9958:
		case 9959:
		case 9960:
		case 9961:
		case 9962:
		case 9963:
		case 9964:
		case 9912:
		case 9913:
		case 9914:
		case 9915:
		case 9916:
		case 9917:
		case 9918:
		case 9919:
		case 9920:
		case 9921:
		case 9922:
		case 9923:
		case 9924:
		case 9925:
		case 9926:
		case 9927:
		case 9928:
			return true;
		}
		return false;
	}

	public void handleBossDrop(final int i) {
		if (NPCHandler.npcs[i].npcType >= 9948
				&& NPCHandler.npcs[i].npcType <= 9964) {
			final Player c = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
			if (c == null) {
				return;
			}
			if (!c.inDung()) {
				return;
			}
			c.getItems().addItem(DungHandler.gorgoniteItem(), 1);
		} else if ((NPCHandler.npcs[i].npcType >= 9912 && NPCHandler.npcs[i].npcType <= 9928)) {
			final Player c = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
			if (c == null) {
				return;
			}
			c.getItems().addItem(DungHandler.promethiumItem(), 1);
		} else if (NPCHandler.npcs[i].npcType == 9727) {
			final Player c = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
			if (c == null) {
				return;
			}
			c.getItems().addItem(DungHandler.primalItem(), 1);
		}
	}

	public static boolean isDungNPC(NPC npc){ 
		switch(npc.npcType){
		case 9948:
		case 9951:
		case 9916:
		case 9727:

		case 10167:

		case 10212:
		case 10218:
		case 10262:

		case 10308:

		case 10414:
		case 10415:
		case 10418:
		case 10419:
		case 10422:
		case 10423:
		case 10431:
		case 10442:
		case 10443:
		case 10445:
		case 10448:
		case 10450:
		case 10462:
		case 10465: 
		case 10480:

		case 10507:
		case 10520:
		case 10522:
		case 10531:
		case 10532:
		case 10534:

		case 10698:
		case 10699:

		case 10736:
		case 10737:
		case 10707:
		case 10738:
		case 10756:
		case 10780:

		case 10802:
		case 10801:
		case 10837:
		case 10838:
		case 10840:
			return true;

		default:
			return false;
		}
	}
	@SuppressWarnings("resource")
	public boolean loadAutoSpawn(final String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		} catch (final FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (final IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			final int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("spawn")) {
					newNPC(Integer.parseInt(token3[0]),
							Integer.parseInt(token3[1]),
							Integer.parseInt(token3[2]),
							Integer.parseInt(token3[3]),
							Integer.parseInt(token3[4]),
							getNpcListHP(Integer.parseInt(token3[0])),
							Integer.parseInt(token3[5]),
							Integer.parseInt(token3[6]),
							Integer.parseInt(token3[7]));

				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					try {
						characterfile.close();
					} catch (final IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (final IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (final IOException ioexception) {
		}
		return false;
	}

	@SuppressWarnings("resource")
	public boolean loadNPCList(final String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		} catch (final FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (final IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			final int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("npc")) {
					newNPCList(Integer.parseInt(token3[0]), token3[1],
							Integer.parseInt(token3[2]),
							Integer.parseInt(token3[3]));
				}
			} else {
				if (line.equals("[ENDOFNPCLIST]")) {
					try {
						characterfile.close();
					} catch (final IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (final IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (final IOException ioexception) {
		}
		return false;
	}

	public void loadSpell(final Player c, final int i) {
		switch (npcs[i].npcType) {
		case 6225:
		case 6230:
		case 6233:
		case 6234:
		case 6235:
		case 6236:
		case 6238:
		case 6241:
		case 6242:
		case 6243:
		case 6244:
		case 6245:
		case 2184:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1190;
			break;

		case 1457:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 26;
			break;

		case 1913: //Kamil 1913
			int randomer2 = Misc.random(2);
			if (randomer2 == 0) {
				npcs[i].projectileId = -1; //melee
				npcs[i].endGfx = -1;
				npcs[i].attackType = 0;	
			} else if (randomer2 == 1) {
				npcs[i].projectileId = 362; //Barrage
				npcs[i].attackType = 2;
				npcs[i].endGfx = 369;
				if (c.freezeTimer <= 0) {
					c.freezeTimer = 25;
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					c.sendMessage("You have been frozen.");
				}
			}
			break;

		case 1914: //Dessous -- 375
			npcs[i].projectileId = 350;
			npcs[i].attackType = 2;
			npcs[i].endGfx = 350;
			break;

		case 1977: //Fareed
			int randomer3 = Misc.random(2);
			if (randomer3 == 0) {
				npcs[i].projectileId = -1; //melee
				npcs[i].endGfx = -1;
				npcs[i].attackType = 0;	
			} else if (randomer3 == 1) {
				npcs[i].attackType = 2;
				npcs[i].endGfx = 375;
			}
			break;

		case 181:
			int randomer = Misc.random(2);
			if (randomer == 0) {
				npcs[i].projectileId = -1; //melee
				npcs[i].endGfx = -1;
				npcs[i].attackType = 0;	
			} else if (randomer == 1) {
				npcs[i].projectileId = 178; //snare
				npcs[i].attackType = 2;
				npcs[i].endGfx = 180;
				if (c.freezeTimer <= 0) {
					c.freezeTimer = 5;
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					c.sendMessage("You have been frozen.");
				}
			}
			break;

		case 3373:
			String[] fightMessages = {
					"You will never defeat me.", 
					"The Gods are in favor of I!", 
					"Is this all you've got...", 
					"Even my Grandmother could do better.", 
					"Your strength shall wither upon my fist!", 
			"Strength will not determine your destiny as you shall fall upon mine!"};
			int r10 = Misc.random(5);
			int r25 = Misc.random(25);
			if (r25 < 2) {
				npcs[i].attackType = 4;
				npcs[i].gfx100(252);
				npcs[i].animUpdateRequired = true;
				npcs[i].updateRequired = true;
				c.handleHitMask(r25, 0, 0);
				c.playerLevel[3] -= r25;
				c.getPA().refreshSkill(3);
				c.updateRequired = true;
			} else if (r25 == 15 && npcs[i].HP < 250) {
				npcs[i].attackType = 0;
				npcs[i].HP += 35;
				npcs[i].animNumber = 829;
				npcs[i].animUpdateRequired = true;
				npcs[i].updateRequired = true;
			} else if (r25 > 10 && r25 < 15) {
				npcs[i].attackType = 0;
				npcs[i].forceChat(fightMessages[r10]);
			}
			break;

		case 8596: //Avatar of destruction
			int Avat = Misc.random(20);
			if (Avat == 5) {
				c.prayer -= (c.prayer * .22);
				npcs[i].attackType = 2;
				c.sendMessage("@red@The Avatar drained your Prayer points!");
				c.getPA().refreshSkill(5);
			}
			if (Avat == 0) {
				for (int p = 0; p < c.PRAYER.length; p++) { // reset prayer
					// glows
					c.prayerActive[p] = false;
					c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);
				}
				for (int p = 0; p < c.curseActive.length; p++) {
					c.curseActive[p] = false;
					c.getPA().sendFrame36(c.CURSE_GLOW[p], 0);
				}
				c.getPA().sendFrame36(90, 0);
				c.headIcon = -1;
				c.getPA().requestUpdates();
				npcs[i].attackType = 0;
				npcs[i].endGfx = 271;
				c.sendMessage("@red@The Avatar knocked out your prayers.");
				//npcs[i].multiAttack = true;
			} else {
				npcs[i].attackType = 0;
			}
			break;

		case 6229:
		case 6232:
		case 6239:
		case 6237:
		case 6240:
		case 6246:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 35;
			break;

		case 6221:
			npcs[i].attackType = 2;
			npcs[i].endGfx = 78;
			break;

		case 6257:
			npcs[i].attackType = 2;
			npcs[i].endGfx = 76;
			break;

		case 6256:
		case 6220:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 326;
			break;

		case 7463:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 374;
			npcs[i].endGfx = 375;
			break;
		case 13479:
		case 13480:
			int b = Misc.random(4);
			if (b > 2) {
				npcs[i].attackType = 1;
			} else {
				npcs[i].attackType = 2;
			}
			npcs[i].projectileId = 1980;
			npcs[i].endGfx = 86;
			break;
		case 2606:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 442;
			npcs[i].endGfx = -1;
			break;
		case 2592:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 448;
			npcs[i].endGfx = 346;
			break;

		case 907:
		case 908:
		case 909:
		case 910:
		case 911:
			npcs[i].attackType = 2;
			npcs[i].endGfx = 76;
			break;

		case 2743:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 445;
			npcs[i].endGfx = 446;
			break;

		case 2741:
			if (npcs[i].HP <= npcs[i].MaxHP / 2) {
				if (Misc.random(5) >= 4) {
					npcs[i].mejKot = 0;
					return;
				}
			}
			npcs[i].mejKot = 1;
			break;

		case 5346:
		case 5347:
		case 5348:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 133;
			break;

		case 5297:
		case 5298:
		case 5299:
		case 5300:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 35;
			break;

		case 2631:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 443;
			break;

		case 2188:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1195;
			break;

		case 3071:
			npcs[i].attackType = 3;
			npcs[i].gfx100(499);
			break;

		case 1643:
			npcs[i].attackType = 2;
			npcs[i].gfx100(155);
			npcs[i].projectileId = 156;
			npcs[i].endGfx = 157;
			break;

		case 3072:
			npcs[i].endGfx = -1;
			if (npcs[i].dragon < 2) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			} else if (npcs[i].dragon == 2) {
				npcs[i].attackType = 3;
				npcs[i].projectileId = 393;
				npcs[i].multiAttack = true;
			} else if (npcs[i].dragon == 3) {
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1340;
				npcs[i].multiAttack = true;
			} else if (npcs[i].dragon == 4) {
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1406;
				npcs[i].multiAttack = true;
			} else if (npcs[i].dragon == 5) {
				npcs[i].gfx0(255);
				npcs[i].attackType = 2;
				npcs[i].projectileId = 434;
				npcs[i].endGfx = 435;
				npcs[i].multiAttack = true;
			} else if (npcs[i].dragon == 6) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			} else if (npcs[i].dragon == 7) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				npcs[i].multiAttack = true;
			}
			break;

		case 3073:
			if (npcs[i].dragon < 2) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			} else if (npcs[i].dragon == 2) {
				npcs[i].attackType = 3;
				npcs[i].projectileId = 393;
			} else if (npcs[i].dragon == 3) {
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1340;
			} else if (npcs[i].dragon == 4) {
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1288;
			}
			break;

		case 55:
		case 941:
		case 54:
		case 53:
		case 1590:
		case 1591:
		case 1592:
			npcs[i].dragon = Misc.random(2);
			if (npcs[i].dragon < 2) {
				npcs[i].attackType = 0;
			} else {
				npcs[i].attackType = 3;
				npcs[i].gfx0(1);
			}
			break;

		case 13447:// nex
			if (npcs[i].nexStage == 1 || npcs[i].nexStage == 2) {
				if (npcs[i].glod == 1) {
					npcs[i].multiAttack = true;
					npcs[i].attackType = 2;
					npcs[i].projectileId = 386;
					npcs[i].endGfx = 390;
				} else if (npcs[i].glod == 2) {
					c.getPA().sendMp3("virus");
					npcs[i].forceChat("Let the virus flow through you!");
					npcs[i].multiAttack = true;
					npcs[i].attackType = 0;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
					npcs[i].cooldown = 30;
				} else {
					npcs[i].attackType = 0;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
				}
			} else if (npcs[i].nexStage == 3 || npcs[i].nexStage == 4) {
				if (npcs[i].glod == 1) {
					npcs[i].multiAttack = true;
					npcs[i].attackType = 1;
					npcs[i].projectileId = 378;
					npcs[i].endGfx = -1;
				} else if (npcs[i].glod == 2) {
					c.getPA().sendMp3("darkenmy");
					npcs[i].forceChat("Darken my shadow!");
					npcs[i].multiAttack = true;
					npcs[i].attackType = 4;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
					npcs[i].cooldown = 30;
				} else if (npcs[i].glod == 3) {
					c.getPA().sendMp3("fearthe");
					npcs[i].forceChat("Fear the shadow!");
					npcs[i].multiAttack = true;
					npcs[i].attackType = 4;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = 382;
				} else {
					npcs[i].attackType = 0;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
				}
			} else if (npcs[i].nexStage == 5 || npcs[i].nexStage == 6) {
				if (npcs[i].glod == 1) {
					npcs[i].multiAttack = true;
					npcs[i].attackType = 2;
					npcs[i].projectileId = 374;
					npcs[i].endGfx = 376;
				} else if (npcs[i].glod == 2) {
					c.getPA().sendMp3("idemand");
					npcs[i].forceChat("I demand a blood sacrifice!");
					npcs[i].multiAttack = true;
					npcs[i].attackType = 2;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = 377;
					npcs[i].cooldown = 20;
				} else if (npcs[i].glod == 3) {
					npcs[i].forceChat("A siphon will solve this!");
					npcs[i].attackType = 2;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
				} else {
					npcs[i].attackType = 0;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
				}
			} else if (npcs[i].nexStage == 7 || npcs[i].nexStage == 8) {
				if (npcs[i].glod == 1) {
					npcs[i].multiAttack = true;
					npcs[i].attackType = 2;
					npcs[i].projectileId = 362;
					npcs[i].endGfx = 369;
				} else if (npcs[i].glod == 2) {
					npcs[i].forceChat("Contain this!");
					// npcs[i].multiAttack = true;
					npcs[i].attackType = 2;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
					npcs[i].cooldown = 16;
				} else if (npcs[i].glod == 3) {
					npcs[i].forceChat("Die now, in a prison of ice!");
					npcs[i].attackType = 2;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
					npcs[i].cooldown = 16;
				} else {
					npcs[i].attackType = 0;
					npcs[i].projectileId = -1;
					npcs[i].endGfx = -1;
				}
			} else if (npcs[i].nexStage == 9) {
				if (npcs[i].glod == 1) {
					npcs[i].multiAttack = true;
					npcs[i].attackType = 2;
					npcs[i].projectileId = 386;
					npcs[i].endGfx = 390;
				} else {
					npcs[i].attackType = 0;
					npcs[i].projectileId = (npcs[i].prayerUsed == 1 ? 2263 : -1);
					npcs[i].endGfx = (npcs[i].prayerUsed == 1 ? 2264 : -1);
				}
			}
			break;

		case 8133:
			switch (npcs[i].glod) {
			case 2:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1824;
				break;
			case 3:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1825;
				break;
			case 4:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1823;
				break;
			default:
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				break;
			}
			break;

		case 8349:
		case 8350:
		case 8351:
			switch (npcs[i].glod) {
			case 2:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1887;
				break;
			case 1:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1887;
				break;
			case 3:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1884;
				npcs[i].gfx0(1885);
				break;
			case 4:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1884;
				npcs[i].gfx0(1885);
				break;
			default:
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				npcs[i].gfx0(1886);
				break;
			}
			break;
		case 9462:
		case 9463:
			random = Misc.random(10);
			if (random <= 7) {
				npcs[i].attackType = 0;
			} else if (random == 10) {
				npcs[i].attackType = 1;
				StrykeWyrmBurrowI(c, i);
			} else {
				c.freezeTimer = 20;
				npcs[i].attackType = 2;
				c.sendMessage("The Strykewyrm used his Ice Bite and froze you!");
			}
			break;
		case 9466:
		case 9467:
			random = Misc.random(10);
			if (random <= 8) {
				npcs[i].attackType = 0;
			} else if(random == 9 && c.poisonDamage <= 0) {
				c.getPA().appendPoison(12);
				npcs[i].attackType = 2;
				c.sendMessage("The Strykewyrm used his Poison Bite and poisened you!");
			} else if (random == 10) {
				npcs[i].attackType = 1;
				StrykeWyrmBurrowJ(c, i);

			}
			break;
		case 9464:
		case 9465:
			random = Misc.random(10);
			if (random <= 7) {
				npcs[i].attackType = 0;
			} else if (random == 10) {
				npcs[i].attackType = 1;
				StrykeWyrmBurrowD(c, i);
			} else if (random > 7 && random <= 9) {
				c.prayer -= (c.prayer * .22);
				npcs[i].attackType = 2;
				c.sendMessage("The Strykewyrm drained your Prayer points!");
				c.getPA().refreshSkill(5);
			}
			break;

		case 5810:
			switch (npcs[i].glod) {
			case 1:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1406;
				break;
			default:
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				break;
			}
			break;

		case 2745:
		case 5637:
			npcs[i].jad = Misc.random(1);
			switch (npcs[i].jad) {
			case 0:
				npcs[i].gfx0(1626);
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1627;
				break;
			case 1:
				npcs[i].gfx0(1625);
				npcs[i].attackType = 1;
				npcs[i].projectileId = -1;
				break;
			}
			npcs[i].endGfx = 157;
			break;

		case 6222:
			npcs[i].kree = Misc.random(2);
			switch (npcs[i].kree) {
			case 0:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1196;
				break;
			case 1:
			case 2:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1197;
				break;
			}
			if (Misc.random(5) >= 3) {
				npcs[i].forceChat(npcs[i].Kree());
			}
			break;

		case 6203:
			if (Misc.random(5) >= 3) {
				npcs[i].forceChat(npcs[i].Tsutsaroth());
			}
			switch (npcs[i].tsutsaroth) {
			case 0:
			case 1:
			case 2:
			case 3:
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				break;
			case 4:
				npcs[i].attackType = 2;
				npcs[i].multiAttack = true;
				npcs[i].gfx0(1210);
				npcs[i].projectileId = 1211;
				break;
			}
			break;

		case 6260:
			switch (npcs[i].graardor) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				if (Misc.random(5) >= 4) {
					npcs[i].forceChat(npcs[i].Graardor());
				}
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				break;
			case 5:
				if (Misc.random(5) >= 3) {
					npcs[i].forceChat(npcs[i].Graardor());
				}
				npcs[i].attackType = 1;
				npcs[i].multiAttack = true;
				npcs[i].gfx0(1219);
				npcs[i].projectileId = 1200;
				break;
			}
			break;

		case 6247:
			switch (npcs[i].zilyana) {
			case 0:
			case 1:
			case 2:
			case 3:
				npcs[i].attackType = 0;
				break;
			case 4:
			case 5:
			case 6:
				npcs[i].attackType = 2;
				break;
			}
			break;

		case 1158:
			npcs[i].kQueen = Misc.random(1);
			switch (npcs[i].kQueen) {
			case 0:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 288;
				npcs[i].endGfx = -1;
				break;
			case 1:
				npcs[i].gfx0(278);
				npcs[i].attackType = 2;
				npcs[i].projectileId = 280;
				npcs[i].endGfx = 281;
				break;
			}
			break;

		case 1160:
			npcs[i].kQueen = Misc.random(1);
			switch (npcs[i].kQueen) {
			case 0:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 289;
				npcs[i].endGfx = -1;
				break;
			case 1:
				npcs[i].gfx100(279);
				npcs[i].attackType = 2;
				npcs[i].projectileId = 280;
				npcs[i].endGfx = 281;
				break;
			}
			break;

		case 3761:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 100;
			npcs[i].endGfx = 101;
			break;

		case 2190:
			npcs[i].attackType = 2;
			npcs[i].endGfx = 1187;
			break;

		case 3200:
			int chaosRandom = Misc.random(2);
			switch (chaosRandom) {
			case 0:
			npcs[i].attackType = 2;
				npcs[i].projectileId = 551;
				npcs[i].endGfx = 552;
				break;
			case 1:
			npcs[i].attackType = 1;
				npcs[i].projectileId = 554;
				npcs[i].endGfx = 555;
				break;
			case 2:
			npcs[i].attackType = 0;
				npcs[i].projectileId = 557;
				npcs[i].endGfx = 558;
				break;
			}
			break;

		case 2059:
			switch (npcs[i].tarn) {
			case 0:
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				npcs[i].endGfx = -1;
				break;
			case 1:
				npcs[i].attackType = 2;
				npcs[i].multiAttack = true;
				npcs[i].projectileId = 554;
				npcs[i].endGfx = 555;
				break;
			case 2:
				npcs[i].attackType = 1;
				npcs[i].multiAttack = true;
				npcs[i].projectileId = 475;
				npcs[i].endGfx = -1;
				break;
			}
			break;

		case 5666:
			switch (npcs[i].glod) {
			case 0:
				npcs[i].attackType = 0;
				npcs[i].endGfx = -1;
				break;
			case 1:
				npcs[i].attackType = 1;
				npcs[i].multiAttack = true;
				npcs[i].endGfx = 405;
				break;
			}
			c.playerLevel[5] -= 5;
			if (c.playerLevel[5] < 0) {
				c.playerLevel[5] = 0;
			}
			c.getPA().refreshSkill(5);
			for (int pr = 12; pr < 15; pr++) {
				if (c.prayerActive[pr]) {
					c.sendMessage("The Barrelchest keeps you from praying!");
					c.prayerActive[pr] = false;
					c.getPA().sendFrame36(c.PRAYER_GLOW[pr], 0);
					c.headIcon = -1;
					c.getPA().requestUpdates();
				}
			}
			break;

		case 7770:
			switch (npcs[i].glod) {
			case 0:
				npcs[i].attackType = 0;
				npcs[i].endGfx = -1;
				break;
			case 1:
				npcs[i].attackType = 1;
				npcs[i].multiAttack = true;
				npcs[i].endGfx = 405;
				break;
			}
			break;

		case 6021:
		case 6006:
		case 6007:
		case 6008:
			switch (npcs[i].glod) {
			case 0:
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				break;
			case 1:
				npcs[i].attackType = 1;
				npcs[i].multiAttack = true;
				npcs[i].projectileId = 1406;
				break;
			case 2:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 159;
				break;
			}
			break;

		case 6017:
		case 6018:
		case 6019:
		case 6020:
			switch (npcs[i].glod) {
			case 0:
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
				npcs[i].endGfx = -1;
				break;
			case 1:
			case 2:
			case 3:
				npcs[i].attackType = 2;
				// npcs[i].multiAttack = true;
				npcs[i].projectileId = 124;
				npcs[i].endGfx = -1;
				break;
			case 4:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 178;
				npcs[i].endGfx = 179;
				break;
			}
			break;

		case 2057:
			switch (npcs[i].glod) {
			case 0:
				npcs[i].attackType = 0;
				npcs[i].endGfx = -1;
				break;
			case 1:
				npcs[i].attackType = 1;
				npcs[i].multiAttack = true;
				npcs[i].endGfx = 405;
				break;
			}
			if (Misc.random(9) >= 8) {
				for (int pr = 12; pr < 15; pr++) {
					if (c.prayerActive[pr]) {
						c.sendMessage("Glod breaks through your prayer!");
						c.prayerActive[pr] = false;
						c.getPA().sendFrame36(c.PRAYER_GLOW[pr], 0);
						c.headIcon = -1;
						c.getPA().requestUpdates();
					}
				}
			}
			break;

		case 50: //KBD
			int random = Misc.random(4);
			if (random == 0) {
				npcs[i].projectileId = 393; //red
				npcs[i].endGfx = 430;
				npcs[i].attackType = 3;
			} else if (random == 1) {
				npcs[i].projectileId = 394; //green
				npcs[i].endGfx = 429;
				npcs[i].attackType = 3;
				if (c.playerLevel[5] > 0) {
					c.playerLevel[5]--;
					c.getPA().refreshSkill(5);
					c.getPA().appendPoison(30);
					c.getCombat().resetPlayerAttack();
				}
			} else if (random == 2) {
				npcs[i].projectileId = 395; //white
				npcs[i].endGfx = 431;
				npcs[i].attackType = 3;
			} else if (random == 3) {
				npcs[i].projectileId = 396; //blue
				npcs[i].endGfx = 428;
				npcs[i].attackType = 3;
				if (c.freezeTimer <= 0) {
					c.freezeTimer = 30;
					c.frozenBy = c.playerId;
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					c.sendMessage("You have been frozen.");
				}
			} else if (random == 4) {
				npcs[i].projectileId = -1; //melee
				npcs[i].endGfx = -1;
				npcs[i].attackType = 0;
			}
			break;

		case 51:
			int r5 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY, PlayerHandler.players[npcs[i].killerId].absX, PlayerHandler.players[npcs[i].killerId].absY, 2)){
				r5 = Misc.random(5);
			} else {
				r5 = Misc.random(3);
			}
			if (r5 == 0) {
				npcs[i].projectileId = 393; //red
				npcs[i].attackType = 3;
			} else if (r5 == 1) {
				npcs[i].projectileId = 394; //green
				npcs[i].attackType = 2;
			} else if (r5 == 2) {
				npcs[i].projectileId = 395; //white
				npcs[i].attackType = 2;
				if(c.freezeTimer <= 0) {
					c.freezeTimer = 19;
					c.sendMessage("You have been Frozen!");
				}
			} else if (r5 == 3) {
				npcs[i].projectileId = 396; //blue
				npcs[i].attackType = 2;
			} else if (r5 == 4) {
				npcs[i].projectileId = -1; //melee
				npcs[i].attackType = 0;	
			} else if (r5 == 5) {
				npcs[i].projectileId = -1; //melee
				npcs[i].attackType = 0;	
			}	
			break;

		case 3340:
			if (npcs[i].HP <= npcs[i].MaxHP / 2) {
				if (Misc.random(5) >= 4) {
					npcs[i].moleStage = 1;
					return;
				}
			}
			npcs[i].moleStage = 0;
			break;

		case 2025:
			npcs[i].attackType = 2;
			npcs[i].gfx100(155);
			npcs[i].projectileId = 156;
			break;

		case 6206:
			npcs[i].attackType = 1;
			npcs[i].gfx0(1208);
			npcs[i].projectileId = 1209;
			break;

		case 6208:
			npcs[i].attackType = 2;
			npcs[i].gfx0(1212);
			npcs[i].projectileId = 1213;
			break;

		case 1472:
			npcs[i].attackType = 2;
			int r = Misc.random(2);
			switch (r) {
			case 0:
				npcs[i].projectileId = 162;
				npcs[i].endGfx = 163;
				break;
			case 1:
				npcs[i].projectileId = 165;
				npcs[i].endGfx = 166;
				break;
			case 2:
				npcs[i].projectileId = 156;
				npcs[i].endGfx = 157;
				break;
			}
			break;

		case 6250:
			npcs[i].attackType = 2;
			npcs[i].gfx0(1184);
			npcs[i].projectileId = 1185;
			npcs[i].endGfx = 1186;
			break;

		case 6263:
			npcs[i].attackType = 2;
			npcs[i].gfx0(1202);
			npcs[i].projectileId = 1203;
			npcs[i].endGfx = 1204;
			break;

		case 2882:
		case 2457:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 162;
			npcs[i].endGfx = 163;
			break;

		case 2894:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 500;
			npcs[i].endGfx = 502;
			break;

		case 6223:
		case 6231:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1199;
			break;

		case 2028:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 27;
			break;

		case 6252:
			npcs[i].attackType = 1;
			npcs[i].gfx100(1187);
			npcs[i].projectileId = 1188;
			break;

		case 1343:
		case 1351:
		case 3771:
		case 2896:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 300;
			break;

		case 2881:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 475;
			break;

		case 6265:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1206;
			break;

		case 2183:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1191;
			break;
		}
	}

	/**
	 * load spell
	 **/
	public void loadSpell2(final int i) {
		NPCHandler.npcs[i].attackType = 3;
		final int random = Misc.random(3);
		if (random == 0) {
			NPCHandler.npcs[i].projectileId = 393; // red
			NPCHandler.npcs[i].endGfx = 430;
		} else if (random == 1) {
			NPCHandler.npcs[i].projectileId = 394; // green
			NPCHandler.npcs[i].endGfx = 429;
		} else if (random == 2) {
			NPCHandler.npcs[i].projectileId = 395; // white
			NPCHandler.npcs[i].endGfx = 431;
		} else if (random == 3) {
			NPCHandler.npcs[i].projectileId = 396; // blue
			NPCHandler.npcs[i].endGfx = 428;
		}
	}

	public void multiAttackDamage(final int i) {
		int max = getMaxHit(i);
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Player c = PlayerHandler.players[j];
				if (c.isDead || c.heightLevel != npcs[i].heightLevel)
					continue;
				if (PlayerHandler.players[j].goodDistance(c.absX, c.absY,
						npcs[i].absX, npcs[i].absY, 15)) {
					if (npcs[i].attackType == 2) {
						if (!c.prayerActive[16]) {
							if (Misc.random(500) + 200 > Misc.random(c
									.getCombat().mageDef())) {
								int dam = Misc.random(max);
								c.dealDamage(dam);
								c.handleHitMask(dam, 0, 2);
							} else {
								c.dealDamage(0);
								c.handleHitMask(0, 0, 2);
							}
						} else {
							if (npcs[i].npcType != 13447) {
								c.dealDamage(0);
								c.handleHitMask(0, 0, 2);
							}
							if (npcs[i].npcType == 13447) {
							if (Misc.random(500) + 200 > Misc.random(c
										.getCombat().mageDef())) {
									int dam = Misc.random(max) / 2;
									c.dealDamage(dam);
									c.handleHitMask(dam, 0, 2);
							}
							} else {
								if (Misc.random(500) + 200 > Misc.random(c
										.getCombat().mageDef())) {
									int dam = Misc.random(max) / 2;
									c.dealDamage(dam);
									c.handleHitMask(dam, 0, 2);
								} else {
									c.dealDamage(0);
									c.handleHitMask(0, 0, 2);
								}
							}
						}
					} else if (npcs[i].attackType == 1) {
						if (!c.prayerActive[17]) {
							int dam = Misc.random(max);
							if (Misc.random(500) + 200 > Misc.random(c
									.getCombat().calculateRangeDefence())) {
								c.dealDamage(dam);
								c.handleHitMask(dam, 0, 1);
							} else {
								c.dealDamage(0);
								c.handleHitMask(0, 0, 1);
							}
						} else {
							c.dealDamage(0);
							c.handleHitMask(0, 0, 1);
						}
					}
					if (npcs[i].endGfx > 0) {
						c.gfx0(npcs[i].endGfx);
					}
				}
				c.getPA().refreshSkill(3);
			}
		}
	}

	public void multiAttackGfx(final int i, final int gfx) {
		if (NPCHandler.npcs[i].projectileId < 0) {
			return;
		}
		for (final Player player : PlayerHandler.players) {
			if (player != null) {
				final Player c = player;
				if (c.heightLevel != NPCHandler.npcs[i].heightLevel) {
					continue;
				}
				if (player.goodDistance(c.absX, c.absY,
						NPCHandler.npcs[i].absX, NPCHandler.npcs[i].absY, 15)) {
					final int nX = NPCHandler.npcs[i].getX() + offset(i);
					final int nY = NPCHandler.npcs[i].getY() + offset(i);
					final int pX = c.getX();
					final int pY = c.getY();
					final int offX = (nY - pY) * -1;
					final int offY = (nX - pX) * -1;
					c.getPA().createPlayersProjectile(nX, nY, offX, offY, 50,
							getProjectileSpeed(i),
							NPCHandler.npcs[i].projectileId, 43, 31,
							-c.getId() - 1, 65);
				}
			}
		}
	}

	public boolean multiAttackingNPC(int i) {
		switch (npcs[i].npcType) {
		case 2881:
		case 2882:
		case 2883:
		case 1158:
		case 1160:
		case 13447:
		case 6222:
			return true;
		}
		return false;
	}

	public boolean multiAttacks(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 2558:
			return true;
		case 2562:
			if (NPCHandler.npcs[i].attackType == 2) {
				return true;
			}
		case 2550:
			if (NPCHandler.npcs[i].attackType == 1) {
				return true;
			}
		default:
			return false;
		}

	}

	public static void newNPC(final int npcType, final int x, final int y,
			final int heightLevel, final int WalkingType, final int HP,
			final int maxHit, final int attack, final int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if (slot == -1) {
			return; // no free slot found
		}

		final NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		NPCHandler.npcs[slot] = newNPC;
	}

	public void newNPCList(int npcType, String npcName, int combat, int HP) {
		NPCDefinitions newNPCList = new NPCDefinitions(npcType);
		newNPCList.setNpcName(npcName);
		newNPCList.setNpcCombat(combat);
		newNPCList.setNpcHealth(HP);
		NPCDefinitions.getDefinitions()[npcType] = newNPCList;
	}

	public void loadSpellNPC(int i) {
		switch (npcs[i].npcType) {
		case 3150:
			npcs[i].glod++;
			npcs[i].attackType = 2;
			switch (npcs[i].glod) {
			case 1:
				npcs[i].endGfx = 391;
				break;
			case 2:
				npcs[i].endGfx = 383;
				break;
			case 3:
				npcs[i].endGfx = 377;
				break;
			case 4:
				npcs[i].endGfx = 369;
				break;
			}
			break;
		case 3137:
			npcs[i].attackType = 2;
			npcs[i].endGfx = 78;
			break;
		default:
			npcs[i].attackType = 0;
			npcs[i].projectileId = -1;
			npcs[i].endGfx = -1;
			break;
		}
	}

	public int offset(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 2745:
		case 2743:
			return 1;
		}
		return 0;
	}

	public String[] voidKnightTalk = { "We must not fail!",
			"Take down the portals", "The Void Knights will not fall!",
			"Hail the Void Knights!", "We are beating these scum!" };

	@SuppressWarnings("unused")
	public void process() {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] == null)
				continue;
			npcs[i].clearUpdateFlags();
		}

		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				Player c = PlayerHandler.players[NPCHandler.npcs[i].spawnedBy];
				if (c != null
						&& c.familiarID > 0
						&& !PlayerHandler.players[npcs[i].spawnedBy]
								.goodDistance(
										npcs[i].getX(),
										npcs[i].getY(),
										PlayerHandler.players[npcs[i].spawnedBy]
												.getX(),
												PlayerHandler.players[npcs[i].spawnedBy]
														.getY(), 10)
														&& npcs[i].summon == true && !npcs[i].isDead) {
					npcs[i].isDead = true;
					npcs[i].applyDead = true;
					Summoning.spawnFamiliar(c, c.familiarID);
					npcs[i].underAttackBy2 = -1;
					npcs[i].dirUpdateRequired = true;
					npcs[i].getNextWalkingDirection();
				}

				if (c != null && npcs[i].actionTimer < 1
						&& npcs[i].summon == true) {
					if (c.playerIndex > 0) {
						Player o = PlayerHandler.players[c.playerIndex];
						if (o != null) {
							if (c.underAttackBy2 > 0 && o.inMulti()
									|| c.underAttackBy > 0 && o.inMulti()) {
								followPlayer(c, i, o.playerId);
								attackPlayer(o, i);
								npcs[i].index = o.playerId;
								npcs[i].actionTimer = 7;
							}
						}
					}
				}

				if (npcs[i].actionTimer > 0) {
					npcs[i].actionTimer--;
				}

				if (npcs[i].npcType == 13447) {
					if (nexCountDown > 0) {
						nexCountDown--;
						if (npcs[i].nexStage == 0) {
							if (nexCountDown == 24) {
								npcs[i].forceChat("AT LAST!");
							} else if (nexCountDown == 20) {
								npcs[i].forceChat("Fumus!");
								npcs[i].startAnimation(6987);
								npcs[i].turnNpc(2552, 4967);
							} else if (nexCountDown == 16) {
								npcs[i].forceChat("Umbra!");
								npcs[i].startAnimation(6987);
								npcs[i].turnNpc(2567, 4967);
							} else if (nexCountDown == 12) {
								npcs[i].forceChat("Cruor!");
								npcs[i].startAnimation(6987);
								npcs[i].turnNpc(2567, 4952);
							} else if (nexCountDown == 8) {
								npcs[i].forceChat("Glacies!");
								npcs[i].startAnimation(6987);
								npcs[i].turnNpc(2552, 4952);
							} else if (nexCountDown == 4) {
								npcs[i].forceChat("Fill my soul with smoke!");
								npcs[i].nexStage = 1;
							}
						} else if (npcs[i].nexStage == 2) {
							if (nexCountDown == 1) {
								npcs[i].forceChat("Darken my shadow!");
								npcs[i].nexStage = 3;
							}
						} else if (npcs[i].nexStage == 4) {
							if (nexCountDown == 1) {
								npcs[i].forceChat("Flood my lungs with blood!");
								npcs[i].nexStage = 5;
							}
						} else if (npcs[i].nexStage == 6) {
							if (nexCountDown == 1) {
								npcs[i].forceChat("Infuse me with the power of ice!");
								npcs[i].nexStage = 7;
							}
						} else if (npcs[i].nexStage == 8) {
							if (nexCountDown == 1) {
								npcs[i].forceChat("NOW, THE POWER OF ZAROS!");
								npcs[i].nexStage = 9;
								nexCountDown = 10;
								npcs[i].HP += 600;
								npcs[i].startAnimation(6326);
								NPCHandler.npcs[i].updateRequired = true;
							}
						}
					}
				}

				if (npcs[i].freezeTimer > 0) {
					npcs[i].freezeTimer--;
				}

				if (npcs[i].hitDelayTimer > 0) {
					npcs[i].hitDelayTimer--;
				}

				if (npcs[i].hitDelayTimer == 1) {
					if (npcs[i].killerId > 0) {
						applyDamage(i);
						if (npcs[i].extraHit && npcs[i].spec) {
							npcs[i].extraHitDelay = 3;
						}
					} else if (npcs[i].killNpc > 0) {
						applyDamageNPC(i);
						if (npcs[i].extraHit && npcs[i].spec) {
							npcs[i].extraHitDelay = 3;
						}
					}
					npcs[i].hitDelayTimer = 0;
				}
				if (npcs[i].extraHitDelay > 0) {
					npcs[i].extraHitDelay--;
					if (npcs[i].extraHitDelay == 1) {
						if (npcs[i].killerId > 0) {
							applyDamage(i);
						} else {
							applyDamageNPC(i);
						}
						npcs[i].extraHit = false;
						npcs[i].spec = false;
					}
				}

				/*if(npcs[i].killerId == 0 && !npcs[i].walkingHome && npcs[i].npcType == 1265) {
					npcs[i].untransformTimer--;
				}
				if(npcs[i].killerId == 0 && !npcs[i].walkingHome && npcs[i].npcType == 1265 && npcs[i].untransformTimer <= 0) {
					npcs[i].requestTransform(1266);
					npcs[i].npcType = 1266;
					npcs[i].walkingType = 0;
				}*/

				if (npcs[i].attackTimer > 0) {
					npcs[i].attackTimer--;
				}
                    

				if (System.currentTimeMillis() - npcs[i].lastDamageTaken > 5000)
					npcs[i].underAttackBy = 0;

				if (npcs[i].spawnedBy > 0) { // delete summons npc
					if (npcs[i].npcType != 520 && !isFightCaveNpc(i)) {
						if (PlayerHandler.players[npcs[i].spawnedBy] == null
								|| c.heightLevel != npcs[i].heightLevel
								|| (PlayerHandler.players[npcs[i].spawnedBy].respawnTimer > 0 && npcs[i].npcType != 2862)
								|| !PlayerHandler.players[npcs[i].spawnedBy]
										.goodDistance(
												npcs[i].getX(),
												npcs[i].getY(),
												PlayerHandler.players[npcs[i].spawnedBy]
														.getX(),
														PlayerHandler.players[npcs[i].spawnedBy]
																.getY(),
																followThatDistance(i))) {

							if (PlayerHandler.players[npcs[i].spawnedBy] != null) {
								for (int o = 0; o < PlayerHandler.players[npcs[i].spawnedBy].barrowsNpcs.length; o++) {
									if (npcs[i].npcType == PlayerHandler.players[npcs[i].spawnedBy].barrowsNpcs[o][0]) {
										PlayerHandler.players[npcs[i].spawnedBy].barrowsNpcs[o][1] = 0;

									}
								}
							}
							npcs[i].absX = 0;
							npcs[i].absY = 0;
							npcs[i] = null;
						}
					} else {
						try {
							if (npcs[i].heightLevel != c.heightLevel
									|| PlayerHandler.players[npcs[i].spawnedBy] == null) {
								npcs[i].absX = 0;
								npcs[i].absY = 0;
								npcs[i] = null;
							}
						} catch (Exception e) {
						}
					}
				}

				if (npcs[i] == null)
					continue;


  				/**
				 * Attacking player
				 **/
				if (isAggressive(i) && !npcs[i].isDead) {
					npcs[i].underAttack = true;
					npcs[i].killerId = getCloseRandomPlayer(i);
				} else if (isReallyAggressive(i) && !npcs[i].isDead) {
					npcs[i].underAttack = true;
					npcs[i].killerId = getCloseRandomPlayer1(i);
				}

				// if(NvN(i) && !npcs[i].underAttack && !npcs[i].isDead &&
				// npcs[i].killNpc <= 0 && npcs[i].killerId <= 0) {
				// npcs[i].otherNPC();
				// }

				if (npcs[i].spawnedBy > 0) {
					try {
						if (familiar(i)) {
							Player s = PlayerHandler.players[npcs[i].spawnedBy];
							if (s == null) {
								npcs[i].absX = 0;
								npcs[i].absY = 0;
								npcs[i] = null;
							}
							if (s.underAttackBy2 > 0 && s.inMulti()) {
								if (npcs[i].npcType != 6815
										|| npcs[1].npcType != 6808
										|| npcs[1].npcType != 6851) {
									if (Misc.random(8) >= 7) {
										if (npcs[i].killNpc <= 0) {
											npcs[i].randomWalk = true;
											npcs[i].killNpc = s.npcIndex;
											npcs[i].facePlayer(npcs[i].killNpc);
											npcs[i].underAttack = true;
										}
									}
								}
							} else if (s.underAttackBy > 0 && s.inMulti()) {
								if (npcs[i].npcType != 6815
										|| npcs[1].npcType != 6808
										|| npcs[1].npcType != 6851) {
									if (Misc.random(8) >= 7) {
										if (npcs[i].killerId <= 0) {
											npcs[i].randomWalk = true;
											npcs[i].killerId = s.underAttackBy;
											npcs[i].facePlayer(npcs[i].killerId);
											npcs[i].underAttack = true;
										}
									}
								}
							} else {
								if (Misc.random(8) >= 7) {
									if (npcs[i].killNpc > 0) {
										npcs[i].randomWalk = false;
										npcs[i].killNpc = 0;
										npcs[i].underAttack = false;
									}
									if (npcs[i].killerId > 0) {
										npcs[i].randomWalk = false;
										npcs[i].killerId = 0;
										npcs[i].underAttack = false;
									}
								}
								npcs[i].facePlayer(npcs[i].spawnedBy);
								followPlayer(s, i, s.playerId);
							}
						}
					} catch (Exception cc) {
					}
				}

				try {
					if (npcs[i].killNpc <= 0) {
						npcs[i].killNpc = 0;
						if (npcs[i].underAttack && !npcs[i].walkingHome) {
							if (!npcs[i].isDead && npcs[i].deadCycle == 0) {
								int p = npcs[i].killerId;
								Player c1 = PlayerHandler.players[p];
								if (c1 != null) {
									followPlayer(c1, i, c1.playerId);
									if (npcs[i] == null)
										continue;
									if (npcs[i].attackTimer == 0) {
										if (c1 != null
												&& npcs[i].npcType != 6808) {
											attackPlayer(c1, i);
										} else {
											npcs[i].killerId = 0;
											npcs[i].underAttack = false;
											npcs[i].facePlayer(0);
										}
									}
								} else {
									npcs[i].killerId = 0;
									npcs[i].underAttack = false;
									npcs[i].facePlayer(0);
								}
							}
						}
					} else {
						npcs[i].killerId = 0;
						if (npcs[i].underAttack && !npcs[i].walkingHome) {
							if (!npcs[i].isDead && npcs[i].deadCycle == 0) {
								int n = npcs[i].killNpc;
								NPC npc = NPCHandler.npcs[n];
								if (npc != null) {

									npcs[i].randomWalk = false;
									followNPC(i);
									if (npcs[i] == null)
										continue;
									if (npcs[i].attackTimer == 0
											&& npcs[i].npcType != 6808) {
										attackNPC(i);
									}
								} else {
									npcs[i].killNpc = 0;
									npcs[i].underAttack = false;
									npcs[i].faceNPC(0);
								}
							}
						}
					}
				} catch (Exception e) {
				}

				/**
				 * Random walking and walking home
				 **/
				if (npcs[i] == null)
					continue;
				if ((!npcs[i].underAttack || npcs[i].walkingHome)
						&& npcs[i].randomWalk && !npcs[i].isDead) {
					npcs[i].facePlayer(0);
					npcs[i].killerId = 0;
					if (npcs[i].spawnedBy == 0) {
						if ((npcs[i].absX > npcs[i].makeX
								+ Config.NPC_RANDOM_WALK_DISTANCE)
								|| (npcs[i].absX < npcs[i].makeX
										- Config.NPC_RANDOM_WALK_DISTANCE)
										|| (npcs[i].absY > npcs[i].makeY
												+ Config.NPC_RANDOM_WALK_DISTANCE)
												|| (npcs[i].absY < npcs[i].makeY
														- Config.NPC_RANDOM_WALK_DISTANCE)) {
							npcs[i].walkingHome = true;
						}
					}
					if (npcs[i].walkingHome && npcs[i].absX == npcs[i].makeX
							&& npcs[i].absY == npcs[i].makeY) {
						npcs[i].walkingHome = false;
					} else if (npcs[i].walkingHome) {
						npcs[i].moveX = GetMove(npcs[i].absX, npcs[i].makeX);
						npcs[i].moveY = GetMove(npcs[i].absY, npcs[i].makeY);
						npcs[i].getNextNPCMovement(i);
						npcs[i].updateRequired = true;
					}
					if (npcs[i].walkingType >= 0) {
						if (npcs[i].walkingType == 1) {
							if (Misc.random(3) == 1 && !npcs[i].walkingHome) {
								int MoveX = 0;
								int MoveY = 0;
								int Rnd = Misc.random(9);
								if (Rnd == 1) {
									MoveX = 1;
									MoveY = 1;
								} else if (Rnd == 2) {
									MoveX = -1;
								} else if (Rnd == 3) {
									MoveY = -1;
								} else if (Rnd == 4) {
									MoveX = 1;
								} else if (Rnd == 5) {
									MoveY = 1;
								} else if (Rnd == 6) {
									MoveX = -1;
									MoveY = -1;
								} else if (Rnd == 7) {
									MoveX = -1;
									MoveY = 1;
								} else if (Rnd == 8) {
									MoveX = 1;
									MoveY = -1;
								}

								if (MoveX == 1) {
									if (npcs[i].absX + MoveX < npcs[i].makeX + 1) {
										npcs[i].moveX = MoveX;
									} else {
										npcs[i].moveX = 0;
									}
								}

								if (MoveX == -1) {
									if (npcs[i].absX - MoveX > npcs[i].makeX - 1) {
										npcs[i].moveX = MoveX;
									} else {
										npcs[i].moveX = 0;
									}
								}

								if (MoveY == 1) {
									if (npcs[i].absY + MoveY < npcs[i].makeY + 1) {
										npcs[i].moveY = MoveY;
									} else {
										npcs[i].moveY = 0;
									}
								}

								if (MoveY == -1) {
									if (npcs[i].absY - MoveY > npcs[i].makeY - 1) {
										npcs[i].moveY = MoveY;
									} else {
										npcs[i].moveY = 0;
									}
								}

								int x = (npcs[i].absX + npcs[i].moveX);
								int y = (npcs[i].absY + npcs[i].moveY);
								if (VirtualWorld.I(npcs[i].heightLevel,
										npcs[i].absX, npcs[i].absY, x, y, 0))
									npcs[i].getNextNPCMovement(i);
								else {
									npcs[i].moveX = 0;
									npcs[i].moveY = 0;
								}
								npcs[i].updateRequired = true;
							}
						}
						switch (npcs[i].walkingType) {

						case 5:
							npcs[i].turnNpc(npcs[i].absX - 1, npcs[i].absY);
							break;

						case 4:
							npcs[i].turnNpc(npcs[i].absX + 1, npcs[i].absY);
							break;

						case 3:
							npcs[i].turnNpc(npcs[i].absX, npcs[i].absY - 1);
							break;
						case 2:
							npcs[i].turnNpc(npcs[i].absX, npcs[i].absY + 1);
							break;

						default:
							if (npcs[i].walkingType >= 0) {
								// npcs[i].turnNpc(npcs[i].absX, npcs[i].absY);
								// //makes it when npcs move they dont turn back
								// to one direction
							}
							break;
						}
					}
				}

				if (npcs[i].isDead) {
					if (npcs[i].actionTimer == 0 && !npcs[i].applyDead
							&& !npcs[i].needRespawn) {
						npcs[i].updateRequired = true;
						npcs[i].facePlayer(0);
						if (!npcs[i].animals()) {
							npcs[i].killedBy = getNpcKillerId(i);
						}
						if (npcs[i].npcType == 8133) {
							corpDead = true;
						}
						if (npcs[i].npcType == 13447) {
							nexDead = true;
						}
						if (isDungNPC(npcs[i])) {
							final Player c5 = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
							c5.dungKills++;
							c5.getPA().sendFrame126(
									"@or2@Kills: " + c5.dungKills + "", 26257);
						}
						if (isDungBoss(i)) {
							final Player c6 = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
							c6.bossDead = true;
						}
						final Player c7 = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
						npcs[i].animNumber = getDeadEmote(i); // dead emote
						npcs[i].animUpdateRequired = true;
						npcs[i].freezeTimer = 0;
						npcs[i].applyDead = true;
						killedBarrow(i);
						killedBarrow2(i);
						appendZamorakKC(i);
						appendBandosKC(i);
						appendSaradominKC(i);
						appendArmadylKC(i);
						npcs[i].actionTimer = getNpcDeleteTime(i); // delete
						// time
					} else if (npcs[i].actionTimer == 0 && npcs[i].applyDead
							&& !npcs[i].needRespawn) {
						npcs[i].needRespawn = true;
						npcs[i].actionTimer = getRespawnTime(i); // respawn time
						dropItems(i); // npc drops items!
						tzhaarDeathHandler(i);
						if (npcs[i].npcType == 2745) {
							handleJadDeath(i);
						}
						if (npcs[i].npcType == 3373) {
							handleMaxDeath(i);
						}
						if (npcs[i].npcType == 3491) {
							handleRFDDeath(i);
						}
						if (npcs[i].npcType == 7133) {
							final Player c5 = PlayerHandler.players[NPCHandler.npcs[i].killedBy];
							c5.getPA().movePlayer(3087, 3502, 0);
							c5.lunarDiplomacy = 8;
						}
						if (npcs[i].npcType == 13447) {
							npcs[i].requestTransform(5636);
							npcs[i].forceChat("Taste my wrath!");
							npcs[i].gfx0(2259);
						}
						if (isDungBoss(i)) {
							handleBossDrop(i);
						}		
						if (npcs[i].npcType == 1913) { // Kamil
						if (c.getItems().freeSlots() >= 1) {
							c.getItems().addItem(4671, 1);
						} else {
							c.sendMessage("The diamond was dropped on the ground, as you did not have enough inventory space.");
							Server.itemHandler.createGroundItem(c, 4671, c.getX(), c.getY(), 2, c.playerId);
							}
							c.sendMessage("You got the @or1@Ice Diamond");
							c.DT = 2;
						}
						if (npcs[i].npcType == 1914) { // Dessous
						if (c.getItems().freeSlots() >= 1) {
							c.getItems().addItem(4673, 1);
						} else {
							c.sendMessage("The diamond was dropped on the ground, as you did not have enough inventory space.");
							Server.itemHandler.createGroundItem(c, 4673, c.getX(), c.getY(), 0, c.playerId);
							}
							c.sendMessage("You got the @or1@Shadow Diamond");
							c.DT = 3;
						}
						if (npcs[i].npcType == 1977) { // Fareed
						if (c.getItems().freeSlots() >= 1) {
							c.getItems().addItem(4672, 1);
						} else {
							c.sendMessage("The diamond was dropped on the ground, as you did not have enough inventory space.");
							Server.itemHandler.createGroundItem(c, 4672, c.getX(), c.getY(), 0, c.playerId);
							}
							c.sendMessage("You got the @or1@Smoke Diamond");
							c.DT = 4;
						}
						if (npcs[i].npcType == 1974) { // Damis
						if (c.getItems().freeSlots() >= 1) {
							c.getItems().addItem(4670, 1);
						} else {
							c.sendMessage("The diamond was dropped on the ground, as you did not have enough inventory space.");
							Server.itemHandler.createGroundItem(c, 4670, c.getX(), c.getY(), 0, c.playerId);
							}
							c.sendMessage("You got the @or1@Blood Diamond");
							c.DT = 5;
						}						
						npcs[i].absX = npcs[i].makeX;
						npcs[i].absY = npcs[i].makeY;
						npcs[i].HP = npcs[i].MaxHP;
						npcs[i].animNumber = 0x328;
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
					} else if (npcs[i].actionTimer == 0 && npcs[i].needRespawn
							&& !isDungNPC(npcs[i])) {
						if (npcs[i].spawnedBy > 0) {
							npcs[i] = null;
						} else {
							if (npcs[i].npcType == 6260) {
								Player c22 = PlayerHandler.players[npcs[i].killedBy];
								for (NPC n : NPCHandler.npcs) {
									if (n != null && n.inGodWarsBoss()) {
										if (n.spawnedBy <= 0) {
											n.isDead = true;
											n.applyDead = true;
											n.updateRequired = true;
										}
									}
								}
								newNPC3(6265, 2868, 5364, 2, 1, 146, 23, 120,
										120, 1);
								newNPC3(6263, 2870, 5356, 2, 1, 127, 23, 150,
										90, 1);
								newNPC3(6261, 2873, 5366, 2, 1, 126, 22, 100,
										110, 1);
								//c22.canUseAltar = true;
							} else if (npcs[i].npcType == 6222) {
								Player c22 = PlayerHandler.players[npcs[i].killedBy];
								for (NPC n : NPCHandler.npcs) {
									if (n != null && n.inGodWarsBoss1()) {
										if (n.spawnedBy <= 0) {
											n.isDead = true;
											n.applyDead = true;
											n.updateRequired = true;
										}
									}
								}
								newNPC3(6227, 2828, 5299, 2, 1, 146, 23, 120,
										120, 2);
								newNPC3(6225, 2838, 5305, 2, 1, 127, 20, 150,
										90, 2);
								newNPC3(6223, 2835, 5299, 2, 1, 126, 19, 100,
										110, 2);
								//c22.canUseAltar = true;
							} else if (npcs[i].npcType == 6203) {
								Player c22 = PlayerHandler.players[npcs[i].killedBy];
								for (NPC n : NPCHandler.npcs) {
									if (n != null && n.inGodWarsBoss2()) {
										if (n.spawnedBy <= 0) {
											n.isDead = true;
											n.applyDead = true;
											n.updateRequired = true;
										}
									}
								}
								newNPC3(6206, 2921, 5328, 2, 1, 146, 23, 120,
										120, 2);
								newNPC3(6204, 2928, 5328, 2, 1, 127, 20, 150,
										90, 2);
								newNPC3(6208, 2932, 5321, 2, 1, 126, 19, 100,
										110, 2);
								//c22.canUseAltar = true;
							} else if (npcs[i].npcType == 6247) {
								Player c22 = PlayerHandler.players[npcs[i].killedBy];
								for (NPC n : NPCHandler.npcs) {
									if (n != null && n.inGodWarsBoss3()) {
										if (n.spawnedBy <= 0) {
											n.isDead = true;
											n.applyDead = true;
											n.updateRequired = true;
										}
									}
								}
								newNPC3(6252, 2901, 5261, 0, 1, 146, 23, 120,
										120, 2);
								newNPC3(6250, 2901, 5269, 0, 1, 127, 20, 150,
										90, 2);
								newNPC3(6248, 2892, 5268, 0, 1, 126, 19, 100,
										110, 2);
								//c22.canUseAltar = true;
							} else if (npcs[i].npcType == 13447) {
								newNPC3(13451, 2552, 4967, 0, 0, 600, 32, 120,
										100, 4);
								newNPC3(13452, 2567, 4967, 0, 0, 600, 32, 120,
										100, 4);
								newNPC3(5631, 2567, 4952, 0, 0, 600, 32, 120,
										100, 4);
								newNPC3(13454, 2552, 4952, 0, 0, 600, 32, 120,
										100, 4);
								for (int nr = 0; nr < nexRoom.length; nr++) {
									nexRoom[nr] = false;
								}
							}
							int old1 = npcs[i].npcType;
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].walkingType;
							int old6 = npcs[i].MaxHP;
							int old7 = npcs[i].maxHit;
							int old8 = npcs[i].attack;
							int old9 = npcs[i].defence;
							int old10 = npcs[i].npcSize;
							npcs[i] = null;
							newNPC3(old1, old2, old3, old4, old5, old6, old7,
									old8, old9, old10);
						}
					}
				}
			}
		}
	}

	public static void StrykeWyrmBurrowI(final Player c, final int i) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer e) {
				if (BurrowWait == 0) {
					NPCHandler.npcs[i].startAnimation(12796);
					BurrowWait++;
					//c.sendMessage(BurrowWait +"---2");
				} else if (BurrowWait == 2) {
					NPCHandler.npcs[i].requestTransform(9462);
					NPCHandler.npcs[i].npcType = 9463;
					resetPlayersInCombat(c.playerId);
					followPlayer(c, i, c.playerId);
					BurrowWait++;

				} 
				if (BurrowWait == 6) {
					NPCHandler.npcs[i].requestTransform(9463);
					NPCHandler.npcs[i].npcType = 9462;
					npcs[i].underAttack = false;
					//NPCHandler.npcs[i].startAnimation(13713);
					BurrowWait = 0;
					if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
							c.getY(), 1)) {
						c.dealDamage(c.playerLevel[3] / 3);
						c.handleHitMask(c.playerLevel[3] / 3, 0, 0);
						c.hitUpdateRequired = true;
						c.updateRequired = true;
						BurrowWait = 0;

					} else {
						c.dealDamage(0);
						c.handleHitMask(0, 0, 0);
						c.hitUpdateRequired = true;
						c.updateRequired = true;
						BurrowWait = 0;
					}
					e.stop();
				} 
				if (BurrowWait < 7) {
					BurrowWait++;
					//c.sendMessage(BurrowWait +" ----3");
					//followPlayer(c, i, c.playerId);
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 2);
	}
	public static void StrykeWyrmBurrowD(final Player c, final int i) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer e) {
				if (BurrowWait == 0) {
					NPCHandler.npcs[i].startAnimation(12796);
					BurrowWait++;
					//c.sendMessage(BurrowWait +"---2");
				} else if (BurrowWait == 2) {
					NPCHandler.npcs[i].requestTransform(9464);
					NPCHandler.npcs[i].npcType = 9465;
					resetPlayersInCombat(c.playerId);
					followPlayer(c, i, c.playerId);
					BurrowWait++;

				} 
				if (BurrowWait == 6) {
					NPCHandler.npcs[i].requestTransform(9465);
					NPCHandler.npcs[i].npcType = 9464;
					npcs[i].underAttack = false;
					//NPCHandler.npcs[i].startAnimation(13713);
					BurrowWait = 0;
					if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
							c.getY(), 1)) {
						c.dealDamage(c.playerLevel[3] / 3);
						c.handleHitMask(c.playerLevel[3] / 3, 0, 0);
						c.hitUpdateRequired = true;
						c.updateRequired = true;
						BurrowWait = 0;

					} else {
						c.dealDamage(0);
						c.handleHitMask(0, 0, 0);
						c.hitUpdateRequired = true;
						c.updateRequired = true;
						BurrowWait = 0;
					}
					e.stop();
				} 
				if (BurrowWait < 7) {
					BurrowWait++;
					//c.sendMessage(BurrowWait +" ----3");
					//followPlayer(c, i, c.playerId);
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 2);
	}
	public static void StrykeWyrmBurrowJ(final Player c, final int i) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer e) {
				if (BurrowWait == 0) {
					NPCHandler.npcs[i].startAnimation(12796);
					BurrowWait++;
					//c.sendMessage(BurrowWait +"---2");
				} else if (BurrowWait == 2) {
					NPCHandler.npcs[i].requestTransform(9466);
					NPCHandler.npcs[i].npcType = 9467;
					resetPlayersInCombat(c.playerId);
					followPlayer(c, i, c.playerId);
					BurrowWait++;

				} 
				if (BurrowWait == 6) {
					NPCHandler.npcs[i].requestTransform(9467);
					NPCHandler.npcs[i].npcType = 9466;
					npcs[i].underAttack = false;
					//NPCHandler.npcs[i].startAnimation(13713);
					BurrowWait = 0;
					if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
							c.getY(), 1)) {
						c.dealDamage(c.playerLevel[3] / 3);
						c.handleHitMask(c.playerLevel[3] / 3, 0, 0);
						c.hitUpdateRequired = true;
						c.updateRequired = true;
						BurrowWait = 0;

					} else {
						c.dealDamage(0);
						c.handleHitMask(0, 0, 0);
						c.hitUpdateRequired = true;
						c.updateRequired = true;
						BurrowWait = 0;
					}
					e.stop();
				} 
				if (BurrowWait < 7) {
					BurrowWait++;
					//c.sendMessage(BurrowWait +" ----3");
					//followPlayer(c, i, c.playerId);
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 2);
	}


	public void newNPC3(int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence,
			int size) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if (slot == -1)
			return; // no free slot found
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		newNPC.npcSize = size;
		npcs[slot] = newNPC;
		if (npcType == 1160) {
			npcs[slot].actionTimer = 4;
			npcs[slot].startAnimation(6237);
		} else if (npcType == 7135) {
			npcs[slot].actionTimer = 4;
			npcs[slot].startAnimation(8765);
		} else if (npcType == 8133) {
			corpDead = false;
		} else if (npcType == 13447) {
			nexDead = false;
		}
	}

	public static int nexCountDown = 0;
	public boolean[] nexRoom = new boolean[4];

	public boolean borkDead = false, corpDead = false, nexDead = false;

	/**
	 * Resets players in combat
	 */
	public static void resetPlayersInCombat(final int i) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].underAttackBy2 == i) {
					PlayerHandler.players[j].underAttackBy2 = 0;
				}
			}
		}
	}

	public static boolean retaliates(final int npcType) {
		return npcType >= 6142 && npcType <= 6145;
	}

	public void followNPC(int i) {
		NPC n = NPCHandler.npcs[npcs[i].killNpc];
		if (n == null) {
			return;
		}
		if (n.isDead || n.deadCycle > 0) {
			npcs[i].faceNPC(0);
			npcs[i].randomWalk = true;
			npcs[i].underAttack = false;
			return;
		}
		if (npcs[i].npcType == 3137
				|| goodDistance(npcs[i].getX(), npcs[i].getY(), n.getX(),
						n.getY(), distanceRequired(i))
						|| npcs[i].npcType == 3150) {
			return;
		}
		int npcX = n.absX;
		int npcY = n.absY;
		npcs[i].randomWalk = false;
		if (npcs[i].heightLevel == n.heightLevel) {
			if (n != null && npcs[i] != null) {
				if (npcY < npcs[i].absY) {
					npcs[i].moveX = GetMove(npcs[i].absX, npcX);
					npcs[i].moveY = GetMove(npcs[i].absY, npcY + 1);
				} else if (npcY > npcs[i].absY) {
					npcs[i].moveX = GetMove(npcs[i].absX, npcX);
					npcs[i].moveY = GetMove(npcs[i].absY, npcY - 1);
				} else if (npcX < npcs[i].absX) {
					npcs[i].moveX = GetMove(npcs[i].absX, npcX + 1);
					npcs[i].moveY = GetMove(npcs[i].absY, npcY);
				} else if (npcX > npcs[i].absX) {
					npcs[i].moveX = GetMove(npcs[i].absX, npcX - 1);
					npcs[i].moveY = GetMove(npcs[i].absY, npcY);
				}
				if (npcX == npcs[i].absX && npcY == npcs[i].absY) {
					switch (Misc.random(3)) {
					case 0:
						npcs[i].moveX = GetMove(npcs[i].absX, npcX);
						npcs[i].moveY = GetMove(npcs[i].absY, npcY + 1);
						break;

					case 1:
						npcs[i].moveX = GetMove(npcs[i].absX, npcX);
						npcs[i].moveY = GetMove(npcs[i].absY, npcY - 1);
						break;

					case 2:
						npcs[i].moveX = GetMove(npcs[i].absX, npcX + 1);
						npcs[i].moveY = GetMove(npcs[i].absY, npcY);
						break;

					case 3:
						npcs[i].moveX = GetMove(npcs[i].absX, npcX - 1);
						npcs[i].moveY = GetMove(npcs[i].absY, npcY);
						break;
					}
				}
				npcs[i].getNextNPCMovement(i);
				npcs[i].updateRequired = true;
			}
		}
	}

	public static void spawnBoss(final Player c, final int npcType,
			final int x, final int y, final int heightLevel,
			final int WalkingType, final int HP, final int maxHit,
			final int attack, final int defence, final boolean attackPlayer) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			// Misc.println("No Free Slot");
			return; // no free slot found
		}
		final NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		if (attackPlayer) {
			newNPC.underAttack = true;
			if (c != null) {
				newNPC.killerId = c.playerId;
			}
		}
		NPCHandler.npcs[slot] = newNPC;
	}

	public static void spawnNpc2(final int npcType, final int x, final int y,
			final int heightLevel, final int WalkingType, final int HP,
			final int maxHit, final int attack, final int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			// Misc.println("No Free Slot");
			return; // no free slot found
		}
		final NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		NPCHandler.npcs[slot] = newNPC;
	}

	public boolean specialCase(final Player c, final int i) { // responsible for
		// npcs that
		// much
		if (goodDistance(NPCHandler.npcs[i].getX(), NPCHandler.npcs[i].getY(),
				c.getX(), c.getY(), 8)
				&& !goodDistance(NPCHandler.npcs[i].getX(),
						NPCHandler.npcs[i].getY(), c.getX(), c.getY(),
						distanceRequired(i))) {
			return true;
		}
		return false;
	}

	public static void startAnimation(final int animId, final int i) {
		NPCHandler.npcs[i].animNumber = animId;
		NPCHandler.npcs[i].animUpdateRequired = true;
		NPCHandler.npcs[i].updateRequired = true;
	}

	public NPC[] getNPCs() {
		return npcs;
	}

	public void follownpc(int i, int playerId) {
		if (npcs[playerId] == null) {
			return;
		}

		if (!followPlayer(i)) {
			npcs[i].facePlayer(playerId);
			return;
		}

		if (!goodDistance(npcs[i].getX(), npcs[i].getY(),
				npcs[playerId].getX(), npcs[playerId].getY(), 1)
				&& npcs[i].npcType == 10127 && npcs[i].attackType == 0) {
			npcs[i].attackType = 2;
			return;
		}

		npcs[i].randomWalk = false;

		if (goodDistance(npcs[i].getX(), npcs[i].getY(), npcs[playerId].absX,
				npcs[playerId].absY, distanceRequired(i)))
			return;

		if ((npcs[i].spawnedBy > 0)
				|| ((npcs[i].absX < npcs[i].makeX + Config.NPC_FOLLOW_DISTANCE)
						&& (npcs[i].absX > npcs[i].makeX
								- Config.NPC_FOLLOW_DISTANCE)
								&& (npcs[i].absY < npcs[i].makeY
										+ Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absY > npcs[i].makeY
												- Config.NPC_FOLLOW_DISTANCE))) {
			if (npcs[i].heightLevel == npcs[playerId].heightLevel) {
				if (npcs[playerId] != null && npcs[i] != null) {
					if (npcs[playerId].absY < npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX,
								npcs[playerId].absX);
						npcs[i].moveY = GetMove(npcs[i].absY,
								npcs[playerId].absY);
					} else if (npcs[playerId].absY > npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX,
								npcs[playerId].absX);
						npcs[i].moveY = GetMove(npcs[i].absY,
								npcs[playerId].absY);
					} else if (npcs[playerId].absX < npcs[i].absX) {
						npcs[i].moveX = GetMove(npcs[i].absX,
								npcs[playerId].absX);
						npcs[i].moveY = GetMove(npcs[i].absY,
								npcs[playerId].absY);
					} else if (npcs[playerId].absX > npcs[i].absX) {
						npcs[i].moveX = GetMove(npcs[i].absX,
								npcs[playerId].absX);
						npcs[i].moveY = GetMove(npcs[i].absY,
								npcs[playerId].absY);
					} else if (npcs[playerId].absX == npcs[i].absX
							|| npcs[playerId].absY == npcs[i].absY) {
						int o = Misc.random(3);
						switch (o) {
						case 0:
							npcs[i].moveX = GetMove(npcs[i].absX,
									npcs[playerId].absX);
							npcs[i].moveY = GetMove(npcs[i].absY,
									npcs[playerId].absY + 1);
							break;

						case 1:
							npcs[i].moveX = GetMove(npcs[i].absX,
									npcs[playerId].absX);
							npcs[i].moveY = GetMove(npcs[i].absY,
									npcs[playerId].absY - 1);
							break;

						case 2:
							npcs[i].moveX = GetMove(npcs[i].absX,
									npcs[playerId].absX + 1);
							npcs[i].moveY = GetMove(npcs[i].absY,
									npcs[playerId].absY);
							break;

						case 3:
							npcs[i].moveX = GetMove(npcs[i].absX,
									npcs[playerId].absX - 1);
							npcs[i].moveY = GetMove(npcs[i].absY,
									npcs[playerId].absY);
							break;
						}
					}
					npcs[i].facePlayer(playerId);
					if (checkClipping(i))
						npcs[i].getNextNPCMovement(i);
					else {
						npcs[i].moveX = 0;
						npcs[i].moveY = 0;
					}
					npcs[i].facePlayer(playerId);
					npcs[i].updateRequired = true;
				}
			}
		} else {
			npcs[i].facePlayer(0);
			npcs[i].randomWalk = true;
			npcs[i].underAttack = false;
		}
	}

	public boolean switchesAttackers(final int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 2551:
		case 2552:
		case 2553:
		case 2559:
		case 2560:
		case 8133:
		case 2881:
		case 2882:
		case 2883:
		case 2561:
		case 2563:
		case 2564:
		case 2565:
		case 2892:
		case 2894:
			return true;
		}
		return false;
	}

}