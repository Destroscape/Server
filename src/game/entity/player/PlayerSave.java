package game.entity.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import engine.util.Misc;
import game.Config;

public class PlayerSave {

	/**
	 * Tells us whether or not the player exists for the specified name.
	 * 
	 * @param name
	 * @return
	 */
	public static boolean playerExists(String name) {
		File file = new File("./Data/characters/" + name + ".txt");
		return file.exists();
	}

	/**
	 * Tells use whether or not the specified name has the friend added.
	 * 
	 * @param name
	 * @param friend
	 * @return
	 */
	public static boolean isFriend(String name, String friend) {
		long nameLong = Misc.playerNameToInt64(friend);
		long[] friends = getFriends(name);
		if (friends != null && friends.length > 0) {
			for (int index = 0; index < friends.length; index++) {
				if (friends[index] == nameLong) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a characters friends in the form of a long array.
	 * 
	 * @param name
	 * @return
	 */
	public static long[] getFriends(String name) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean end = false;
		int readMode = 0;
		BufferedReader file = null;
		boolean file1 = false;
		long[] readFriends = new long[200];
		long[] friends = null;
		int totalFriends = 0;
		try {
			file = new BufferedReader(new FileReader("./Data/characters/"
					+ name + ".txt"));
			file1 = true;
		} catch (FileNotFoundException fileex1) {
		}

		if (file1) {
			new File("./Data/characters/" + name + ".txt");
		} else {
			return null;
		}
		try {
			line = file.readLine();
		} catch (IOException ioexception) {
			return null;
		}
		while (end == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (readMode) {
				case 0:
					if (token.equals("character-friend")) {
						readFriends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
						totalFriends++;
					}
					break;
				}
			} else {
				if (line.equals("[FRIENDS]")) {
					readMode = 0;
				} else if (line.equals("[EOF]")) {
					try {
						file.close();
					} catch (IOException ioexception) {
					}
				}
			}
			try {
				line = file.readLine();
			} catch (IOException ioexception1) {
				end = true;
			}
		}
		try {
			if (totalFriends > 0) {
				friends = new long[totalFriends];
				for (int index = 0; index < totalFriends; index++) {
					friends[index] = readFriends[index];
				}
				return friends;
			}
			file.close();
		} catch (IOException ioexception) {
		}
		return null;
	}

	/**
	 * Loading
	 **/
	public static int loadGame(final Player p, final String playerName,
			String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		boolean File1 = false;

		try {
			/*if (Config.BETA_MODE == true) {
				characterfile = new BufferedReader(new FileReader("./Data/betacharacters/" + playerName + ".txt"));
				File1 = true;
			} else {*/
			characterfile = new BufferedReader(new FileReader(
					"./Data/characters/" + playerName + ".txt"));
			File1 = true;
			//}
		} catch (final FileNotFoundException fileex1) {
		}
		if (File1) {
			// new File ("./characters/"+playerName+".txt");
		} else {
			Misc.println(playerName + ": character file not found.");
			p.newPlayer = false;
			return 0;
		}
		try {
			line = characterfile.readLine();
		} catch (final IOException ioexception) {
			Misc.println(playerName + ": error loading file.");
			return 3;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			final int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {

				case 1:
					if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2)) {
							playerPass = token2;
						} else {
							return 3;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						p.heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						p.teleportToX = Integer.parseInt(token2) <= 0 ? 3210
								: Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						p.teleportToY = Integer.parseInt(token2) <= 0 ? 3424
								: Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						p.playerRights = Integer.parseInt(token2);
					} else if (token.equals("wantsPIN")) {
						p.wantsPIN = Integer.parseInt(token2);
					} else if (token.equals("bankPin")) {
						p.bankPin = token2;
					} else if (token.equals("setPin")) {
						p.setPin = Boolean.parseBoolean(token2);
					} else if (token.equals("xpTitle")) {
						p.xpTitle = token2;
					} else if (token.equals("xpLock")) {
						p.xpLock = Boolean.parseBoolean(token2);
					} else if (token.equals("doubleXP")) {
						p.doubleXP = Long.parseLong(token2);
					} else if (token.equals("tutorial-progress")) {
						p.tutorial = Integer.parseInt(token2);
					} else if (token.equals("finishedBeg")) {
						p.finishedBeg = Boolean.parseBoolean(token2);
					} else if (token.equals("crystal-bow-shots")) {
						p.crystalBowArrowCount = Integer.parseInt(token2);
					} else if (token.equals("skull-timer")) {
						p.skullTimer = Integer.parseInt(token2);
					} else if (token.equals("play-time")) {
						p.pTime = Integer.parseInt(token2);
					} else if (token.equals("splitChat")) {
						p.splitChat = Boolean.parseBoolean(token2);
						/**
						 * Saved Teleports
						 */
					} else if (token.equals("savedTele1")) {
						p.savedTele1 = Boolean.parseBoolean(token2);
					} else if (token.equals("savedTele2")) {
						p.savedTele2 = Boolean.parseBoolean(token2);
					} else if (token.equals("savedTele3")) {
						p.savedTele3 = Boolean.parseBoolean(token2);
					} else if (token.equals("savedTele4")) {
						p.savedTele4 = Boolean.parseBoolean(token2);
					} else if (token.equals("savedTele5")) {
						p.savedTele5 = Boolean.parseBoolean(token2);
					} else if (token.equals("teleOneX")) {
						p.teleOneX = Integer.parseInt(token2);
					} else if (token.equals("teleOneY")) {
						p.teleOneY = Integer.parseInt(token2);
					} else if (token.equals("teleOneH")) {
						p.teleOneH = Integer.parseInt(token2);
					} else if (token.equals("teleTwoX")) {
						p.teleTwoX = Integer.parseInt(token2);
					} else if (token.equals("teleTwoY")) {
						p.teleTwoY = Integer.parseInt(token2);
					} else if (token.equals("teleTwoH")) {
						p.teleTwoH = Integer.parseInt(token2);
					} else if (token.equals("teleThreeX")) {
						p.teleThreeX = Integer.parseInt(token2);
					} else if (token.equals("teleThreeY")) {
						p.teleThreeY = Integer.parseInt(token2);
					} else if (token.equals("teleThreeH")) {
						p.teleThreeH = Integer.parseInt(token2);
					} else if (token.equals("teleFourX")) {
						p.teleFourX = Integer.parseInt(token2);
					} else if (token.equals("teleFourY")) {
						p.teleFourY = Integer.parseInt(token2);
					} else if (token.equals("teleFourH")) {
						p.teleFourH = Integer.parseInt(token2);
					} else if (token.equals("teleFiveX")) {
						p.teleFiveX = Integer.parseInt(token2);
					} else if (token.equals("teleFiveY")) {
						p.teleFiveY = Integer.parseInt(token2);
					} else if (token.equals("teleFiveH")) {
						p.teleFiveH = Integer.parseInt(token2);
					} else if (token.equals("tele1")) {
						p.tele1 = token2;
					} else if (token.equals("tele2")) {
						p.tele2 = token2;
					} else if (token.equals("tele3")) {
						p.tele3 = token2;
					} else if (token.equals("tele4")) {
						p.tele4 = token2;
					} else if (token.equals("tele5")) {
						p.tele5 = token2;
						/**
						 * Saved Teleports End
						 */
						/**
						 * Loyalty Program
						 */
					} else if (token.equals("loyaltyPoints")) {
						p.loyaltyPoints = Integer.parseInt(token2);
					} else if (token.equals("currentDay")) {
						p.currentDay = Integer.parseInt(token2);
					} else if (token.equals("currentMonth")) {
						p.currentMonth = Integer.parseInt(token2);
					} else if (token.equals("currentYear")) {
						p.currentYear = Integer.parseInt(token2);
						/**
						 * Loyalty End
						 */
						/**
						 * Kill Streak
						 */
					} else if (token.equals("currentStreak")) {
						p.currentStreak = Integer.parseInt(token2);
					} else if (token.equals("highestStreak")) {
						p.highestStreak = Integer.parseInt(token2);
						/**
						 * Kill Streak End
						 */
						/**
						 * Tool Belt
						 */
					} else if (token.equals("hatchetInTools")) {
						p.hatchet = Integer.parseInt(token2);
						/**
						 * Tool Belt End
						 */
					} else if (token.equals("Agrith")) {
						p.Agrith = Boolean.parseBoolean(token2);
					} else if (token.equals("Flambeed")) {
						p.Flambeed = Boolean.parseBoolean(token2);
					} else if (token.equals("Karamel")) {
						p.Karamel = Boolean.parseBoolean(token2);
					} else if (token.equals("Dessourt")) {
						p.Dessourt = Boolean.parseBoolean(token2);
					} else if (token.equals("Culin")) {
						p.Culin = Boolean.parseBoolean(token2);	
					} else if (token.equals("magic-book")) {
						p.playerMagicBook = Integer.parseInt(token2);
					} else if (token.equals("ability")) {
						p.playerAbility = Integer.parseInt(token2);
					} else if (token.equals("currentClass")) {
						p.currentClass = Integer.parseInt(token2);
					} else if (token.equals("meleeClassLvl")) {
						p.meleeClassLvl = Integer.parseInt(token2);
					} else if (token.equals("rangedClassLvl")) {
						p.rangedClassLvl = Integer.parseInt(token2);
					} else if (token.equals("magicClassLvl")) {
						p.magicClassLvl = Integer.parseInt(token2);
					} else if (token.equals("lvlPotential")) {
						p.lvlPotential = Integer.parseInt(token2);
					} else if (token.equals("weaponKills")) {
						p.weaponKills = Integer.parseInt(token2);
					} else if (token.equals("weaponDeaths")) {
						p.weaponDeaths = Integer.parseInt(token2);
					} else if (token.equals("kills")) {
						p.kills = Integer.parseInt(token2);
					} else if (token.equals("deaths")) {
						p.deaths = Integer.parseInt(token2);//TODO
					} else if (token.equals("dfscount")) {
						p.dfsCount = Integer.parseInt(token2);
					} else if (line.startsWith("ppsLeft")) {
						p.ppsLeft = Integer.parseInt(token2);
					} else if (token.equals("brother-info")) {
						p.barrowsNpcs[Integer.parseInt(token3[0])][1] = Integer
								.parseInt(token3[1]);
					} else if (token.equals("special-amount")) {
						p.specAmount = Double.parseDouble(token2);
					} else if (token.equals("selected-coffin")) {
						p.randomCoffin = Integer.parseInt(token2);
					} else if (token.equals("toggleLogin")) {
						p.toggleLogin = Boolean.parseBoolean(token2);
					} else if (token.equals("Curses")) {
						p.curses = Boolean.parseBoolean(token2);
					} else if (token.equals("Prayer")) {
						p.prayer = Double.parseDouble(token2);
					} else if (token.equals("DPoints")) {
						p.hasDoublePoints = Boolean.parseBoolean(token2);
					} else if (token.equals("isJailed")) {
						p.isJailed = Boolean.parseBoolean(token2);
					} else if (token.equals("receivedBeta")) {
						p.hasTesting = Boolean.parseBoolean(token2);
					} else if (token.equals("barrows-killcount")) {
						p.pkPoints = Integer.parseInt(token2);
					} else if (token.equals("brother-kills")) {
						p.brotherKills = Integer.parseInt(token2);
					} else if (token.equals("teleblock-length")) {
						p.teleBlockDelay = System.currentTimeMillis();
						p.teleBlockLength = Integer.parseInt(token2);
					} else if (token.equals("skillsPrestiged")) {
						p.skillsPrestiged = Integer.parseInt(token2);
 					} else if (token.equals("x2Points")) {
						p.x2Points = Boolean.parseBoolean(token2);
					} else if (token.equals("prestigePoints")) {
						p.prestigePoints = Integer.parseInt(token2);
					} else if (token.equals("pc-points")) {
						p.pcPoints = Integer.parseInt(token2);
					} else if (token.equals("votePoints")) {
						p.votePoints = Integer.parseInt(token2);
					} else if (token.equals("donatorPoints")) {
						p.donatorPoints = Integer.parseInt(token2);
					} else if (token.equals("totalDonation")) {
						p.totalDonation = Integer.parseInt(token2);
					} else if (token.equals("chestChance")) {
						p.chestChance = Integer.parseInt(token2);
 					} else if (token.equals("membership")) {
						p.membership = Boolean.parseBoolean(token2);
 					} else if (token.equals("unMembership")) {
						p.unMembership = Boolean.parseBoolean(token2);
					} else if (token.equals("startdate")) {
						p.startDate = Integer.parseInt(token2);
					} else if (token.equals("lastLogin")) { //last logged in from?
						for (int j = 0; j < token3.length; j++) {
							p.lastLogin[j] = Integer.parseInt(token3[j]);						
						}
					} else if (token.equals("memberShipDays")) { // memberShipDays left?
						p.memberShipDays = Integer.parseInt(token2);
					} else if (token.equals("DTokens")) {
						p.dTokens = Integer.parseInt(token2);
					} else if (token.equals("slayerTask")) {
						p.slayerTask = Integer.parseInt(token2);
					} else if (token.equals("energy")) {
						p.playerEnergy = Integer.parseInt(token2);
					} else if (token.equals("effskill")) {
						p.effigySkill = Integer.parseInt(token2);
					} else if (token.equals("efflvl")) {
						p.effigySkillLvl = Integer.parseInt(token2);
					} else if (token.equals("PKPoints")) {
						p.pkPoints = Integer.parseInt(token2);
					} else if (token.equals("Familiar-Decr")) {
						p.decreaseTime = Integer.parseInt(token2);
					} else if (token.equals("AGSStatus")) {
						p.aGoodStartStatus = Integer.parseInt(token2);
					} else if (token.equals("loyalty-title")) {
						p.loyaltyTitle = Integer.parseInt(token2);

					} else if (token.equals("slayerPoints")) {
						p.slayerPoints = Integer.parseInt(token2);
					} else if (token.equals("TaskType")) {
						p.taskType = Integer.parseInt(token2);

					} else if (token.equals("TKVStatus")) {
						p.TKVStatus = Integer.parseInt(token2);
					} else if (token.equals("pouch")) {
						for (int j = 0; j < token3.length; j++) {
							p.pouch[j] = Integer.parseInt(token3[j]);
					}
					} else if (token.equals("questPoints")) {
						p.questPoints = Integer.parseInt(token2);
					} else if (token.equals("theStryke")) {
						p.theStryke = Integer.parseInt(token2);
					} else if (token.equals("lunarDiplomacy")) {
						p.lunarDiplomacy = Integer.parseInt(token2);
					} else if (token.equals("dTreasure")) {
						p.DT = Integer.parseInt(token2);
					} else if (token.equals("santasHelp")) {
						p.santasHelp = Integer.parseInt(token2);
					} else if (token.equals("Familiar-Spec")) {
						p.summoningSpecialPoints = Integer.parseInt(token2);
					} else if (token.equals("Familiar-ID")) {
						p.familiarID = Integer.parseInt(token2);
					} else if (token.equals("Familiar-Minutes")) {
						p.familiarMinutes = Integer.parseInt(token2);
					} else if (token.equals("Familiar-Seconds")) {
						p.familiarSeconds = Integer.parseInt(token2);
					} else if (line.startsWith("totalstored")) {
						p.totalstored = Integer.parseInt(token2);
					} else if (token.equals("taskAmount")) {
						p.taskAmount = Integer.parseInt(token2);
					} else if (token.equals("magePoints")) {
						p.magePoints = Integer.parseInt(token2);
					} else if (token.equals("autoRet")) {
						p.autoRet = Integer.parseInt(token2);
					} else if (token.equals("xpMaster")) {
						p.xpMaster = Integer.parseInt(token2);
 					} else if (token.equals("achieved")) {
						for (int j = 0; j < token3.length; j++) {
							p.achieved[j] = Boolean.parseBoolean(token3[j]);						
						}
					} else if (token.equals("achievement")) {
						for (int j = 0; j < token3.length; j++) {
							p.achievement[j] = Integer.parseInt(token3[j]);						
						}
					}  else if (token.equals("points")) {
						p.achievementPoints = Integer.parseInt(token2);
					//}
					} else if (token.equals("barrowskillcount")) {
						p.barrowsKillCount = Integer.parseInt(token2);
					} else if (token.equals("flagged")) {
						p.accountFlagged = Boolean.parseBoolean(token2);
					} else if (token.equals("wave")) {
						p.waveId = Integer.parseInt(token2);
					} else if (token.equals("void")) {
						for (int j = 0; j < token3.length; j++) {
							p.voidStatus[j] = Integer.parseInt(token3[j]);
						}
					} else if (token.equals("killcount")) {
						for (int j = 0; j < token3.length; j++) {
							p.godwarsKillCount[j] = Integer.parseInt(token3[j]);
						}

					} else if (token.equals("bind1")) {
						p.bind1 = Integer.parseInt(token2);
					} else if (token.equals("bind2")) {
						p.bind2 = Integer.parseInt(token2);
					} else if (token.equals("bind3")) {
						p.bind3 = Integer.parseInt(token2);
					} else if (token.equals("bind4")) {
						p.bind4 = Integer.parseInt(token2);
					} else if (token.equals("itemsBounded")) {
						p.itemsBounded = Integer.parseInt(token2);
					} else if (token.equals("pouches")){
							String[] a = token2.split("_");
							if(!a[0].equals("") && a[0] != null){
								p.smallPouchAmt = Integer.parseInt(a[0]);
								p.smallPouch = a[1];
								p.mediumPouchAmt = Integer.parseInt(a[2]);
								p.mediumPouch = a[3];
								p.largePouchAmt = Integer.parseInt(a[4]);
								p.largePouch = a[5];
								p.giantPouchAmt = Integer.parseInt(a[6]);
								p.giantPouch = a[7];
					}
					} else if (token.equals("gwkc")) {
						p.killCount = Integer.parseInt(token2);
					} else if (token.equals("fightMode")) {
						p.fightMode = Integer.parseInt(token2);
					} else if (token.equals("mute-end")) {
						p.muteEnd = Long.parseLong(token2);
					} else if (token.equals("ban-start")) {
						p.banStart = Long.parseLong(token2);
					} else if (token.equals("ban-end")) {
						p.banEnd = Long.parseLong(token2);
					} else if (token.equals("lastclanchat")) {
						p.lastClanChat = token2;
					} else if (token.equals("effectrestore")) {
						p.effectRestore = Integer.parseInt(token2);
					} else if (token.equals("setTask")) {
						p.setTask = Integer.parseInt(token2);
					} else if (token.equals("skillTask")) {
						p.skillTask = Integer.parseInt(token2);
					} else if (token.equals("skillTask1")) {
						p.skillTask1 = Integer.parseInt(token2);
					} else if (token.equals("skillTask2")) {
						p.skillTask2 = Integer.parseInt(token2);
					} else if (token.equals("skillAmount")) {
						p.skillAmount = Integer.parseInt(token2);
					} else if (token.equals("skillAmount1")) {
						p.skillAmount1 = Integer.parseInt(token2);
					} else if (token.equals("skillAmount2")) {
						p.skillAmount2 = Integer.parseInt(token2);
					} else if (token.equals("ia")) {
						p.ia = Integer.parseInt(token2);
					} else if (token.equals("ia1")) {
						p.ia1 = Integer.parseInt(token2);
					} else if (token.equals("ia2")) {
						p.ia2 = Integer.parseInt(token2);
					} else if (token.equals("tid")) {
						p.tid = Integer.parseInt(token2);
					} else if (token.equals("tid1")) {
						p.tid1 = Integer.parseInt(token2);
					} else if (token.equals("tid2")) {
						p.tid2 = Integer.parseInt(token2);
					} else if (token.equals("skillPoints")) {
						p.skillPoints = Integer.parseInt(token2);
					} else if (token.equals("totNPCKO")) {
						p.totalNPCKO = Integer.parseInt(token2);
					} else if (token.equals("completeAchievements")) {
						p.completeAchievements = Integer.parseInt(token2);
					} else if (token.equals("THMATT")) {
						p.THMATT = Integer.parseInt(token2);
					} else if (token.equals("THMDEF")) {
						p.THMDEF = Integer.parseInt(token2);
					} else if (token.equals("THMSTR")) {
						p.THMSTR = Integer.parseInt(token2);
					} else if (token.equals("THMHP")) {
						p.THMHP = Integer.parseInt(token2);
					} else if (token.equals("THMRANG")) {
						p.THMRANG = Integer.parseInt(token2);
					} else if (token.equals("THMPRAY")) {
						p.THMPRAY = Integer.parseInt(token2);
					} else if (token.equals("THMMAGE")) {
						p.THMMAGE = Integer.parseInt(token2);
					}
					break;

				case 3:
					if (token.equals("character-equip")) {
						p.playerEquipment[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.playerEquipmentN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						p.playerAppearance[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						p.playerLevel[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.playerXP[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						p.playerItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.playerItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						p.bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						p.bankingItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankingItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					} else if (token.equals("character-bank1")) {
						p.bankItems1[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItems1N[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					} else if (token.equals("character-bank2")) {
						p.bankItems2[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItems2N[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					} else if (token.equals("character-bank3")) {
						p.bankItems3[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItems3N[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					} else if (token.equals("character-bank4")) {
						p.bankItems4[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItems4N[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					} else if (token.equals("character-bank5")) {
						p.bankItems5[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItems5N[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					} else if (token.equals("character-bank6")) {
						p.bankItems6[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItems6N[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					} else if (token.equals("character-bank7")) {
						p.bankItems7[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItems7N[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					} else if (token.equals("character-bank8")) {
						p.bankItems8[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						p.bankItems8N[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						p.friends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				case 9:
					/*
					 * if (token.equals("character-ignore")) {
					 * ignores[Integer.parseInt(token3[0])] =
					 * Long.parseLong(token3[1]); }
					 */
					break;
				case 12:
					if (token.equals("chaotic")) {
						p.chaoticDegrade[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					break;
				case 20:
					if (token.equals("stored")) {
						p.bobItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {
					ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {
					ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {
					ReadMode = 3;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {
					ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {
					ReadMode = 6;
				} else if (line.equals("[BANK]")) {
					ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 9;
				} else if (line.equals("[STORED]")) {
					ReadMode = 20;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (final IOException ioexception) {
					}
					return 1;
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
		return 13;
	}

	/**
	 * Saving
	 **/
	public static boolean saveGame(final Player p) {
		if (!p.saveFile || p.newPlayer || !p.saveCharacter) {
			// System.out.println("first");
			return false;
		}
		if (p.playerName == null || PlayerHandler.players[p.playerId] == null) {
			// System.out.println("second");
			return false;
		}
		p.playerName = p.playerName2;
		int tbTime = (int) (p.teleBlockDelay - System.currentTimeMillis() + p.teleBlockLength);
		if (tbTime > 300000 || tbTime < 0) {
			tbTime = 0;
		}
		BufferedWriter characterfile = null;
		try {
			if (Config.BETA_MODE == true) {
				characterfile = new BufferedWriter(new FileWriter(
						"./Data/betacharacters/" + p.playerName + ".txt"));
			} else {
				characterfile = new BufferedWriter(new FileWriter(
						"./Data/characters/" + p.playerName + ".txt"));
			}
			/* ACCOUNT */
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(p.playerName, 0, p.playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(p.playerPass, 0, p.playerPass.length());
			//characterfile.write(Misc.basicEncrypt(p.playerPass), 0, Misc.basicEncrypt(p.playerPass).length());
			characterfile.newLine();
			characterfile.newLine();
			/* CHARACTER */
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(p.heightLevel), 0, Integer
					.toString(p.heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(p.absX), 0,
					Integer.toString(p.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(p.absY), 0,
					Integer.toString(p.absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(p.playerRights), 0, Integer
					.toString(p.playerRights).length());
			characterfile.newLine();
			characterfile.write("wantsPIN = ", 0, 11);
			characterfile.write(Integer.toString(p.wantsPIN), 0, Integer
					.toString(p.wantsPIN).length());
			characterfile.newLine();
			characterfile.write("bankPin = ", 0, 10);
			characterfile.write(p.bankPin, 0, p.bankPin.length());
			characterfile.newLine();
			characterfile.write("setPin = ", 0, 9);
			characterfile.write(Boolean.toString(p.setPin), 0, Boolean.toString(p.setPin).length());
			characterfile.newLine();
			characterfile.write("xpTitle = ", 0,
					"xpTitle = ".length());
			characterfile.write(p.xpTitle, 0, p.xpTitle.length());
			characterfile.newLine();
			characterfile.write("xpLock = ", 0, 9);
			characterfile.write(Boolean.toString(p.xpLock), 0, Boolean.toString(p.xpLock).length());
			characterfile.newLine();
			characterfile.write("doubleXP = ", 0, 11);
			characterfile.write(Long.toString(p.doubleXP), 0, Long.toString(p.doubleXP).length());
			characterfile.newLine();
			characterfile.write("UUID = ", 0, 7);
			characterfile.write(Player.UUID, 0, Player.UUID.length());
			characterfile.newLine();
			characterfile.write("mute-end = ", 0, 11);
			characterfile.write(Long.toString(p.muteEnd), 0,
					Long.toString(p.muteEnd).length());
			characterfile.newLine();
			characterfile.write("ban-start = ", 0, 12);
			characterfile.write(Long.toString(p.banStart), 0,
					Long.toString(p.banStart).length());
			characterfile.newLine();
			characterfile.write("ban-end = ", 0, 10);
			characterfile.write(Long.toString(p.banEnd), 0,
					Long.toString(p.banEnd).length());
			characterfile.newLine();
			characterfile.write("crystal-bow-shots = ", 0, 20);
			characterfile.write(Integer.toString(p.crystalBowArrowCount), 0,
					Integer.toString(p.crystalBowArrowCount).length());
			characterfile.newLine();
			characterfile.write("finishedBeg = ", 0, 14);
			characterfile.write(Boolean.toString(p.finishedBeg), 0, Boolean.toString(p.finishedBeg).length());
			characterfile.newLine();
			characterfile.write("pouch = ", 0, 8);
			characterfile.write(p.pouch[0] + "\t" + p.pouch[1] + "\t"
					+ p.pouch[2] + "\t" + p.pouch[3]);
			characterfile.newLine();
			characterfile.write("skull-timer = ", 0, 14);
			characterfile.write(Integer.toString(p.skullTimer), 0, Integer
					.toString(p.skullTimer).length());
			characterfile.newLine();
			characterfile.write("play-time = ", 0, 12);
			characterfile.write(Integer.toString(p.pTime), 0, Integer.toString(p.pTime).length());
			characterfile.newLine();
			characterfile.write("splitChat = ", 0, 12);
			characterfile.write(Boolean.toString(p.splitChat), 0, Boolean
					.toString(p.splitChat).length());
			characterfile.newLine();
			/**
			 * Saved Teleports
			 */
			characterfile.write("savedTele1 = ", 0, 13);
			characterfile.write(Boolean.toString(p.savedTele1), 0, Boolean.toString(p.savedTele1).length());
			characterfile.newLine();
			characterfile.write("savedTele2 = ", 0, 13);
			characterfile.write(Boolean.toString(p.savedTele2), 0, Boolean.toString(p.savedTele2).length());
			characterfile.newLine();
			characterfile.write("savedTele3 = ", 0, 13);
			characterfile.write(Boolean.toString(p.savedTele3), 0, Boolean.toString(p.savedTele3).length());
			characterfile.newLine();
			characterfile.write("savedTele4 = ", 0, 13);
			characterfile.write(Boolean.toString(p.savedTele4), 0, Boolean.toString(p.savedTele4).length());
			characterfile.newLine();
			characterfile.write("savedTele5 = ", 0, 13);
			characterfile.write(Boolean.toString(p.savedTele5), 0, Boolean.toString(p.savedTele5).length());
			characterfile.newLine();
			characterfile.write("teleOneX = ", 0, 11);
			characterfile.write(Integer.toString(p.teleOneX), 0,
					Integer.toString(p.teleOneX).length());
			characterfile.newLine();
			characterfile.write("teleOneY = ", 0, 11);
			characterfile.write(Integer.toString(p.teleOneY), 0,
					Integer.toString(p.teleOneY).length());
			characterfile.newLine();
			characterfile.write("teleOneH = ", 0, 11);
			characterfile.write(Integer.toString(p.teleOneH), 0,
					Integer.toString(p.teleOneH).length());
			characterfile.newLine();
			characterfile.write("teleTwoX = ", 0, 11);
			characterfile.write(Integer.toString(p.teleTwoX), 0,
					Integer.toString(p.teleTwoX).length());
			characterfile.newLine();
			characterfile.write("teleTwoY = ", 0, 11);
			characterfile.write(Integer.toString(p.teleTwoY), 0,
					Integer.toString(p.teleTwoY).length());
			characterfile.newLine();
			characterfile.write("teleTwoH = ", 0, 11);
			characterfile.write(Integer.toString(p.teleTwoH), 0,
					Integer.toString(p.teleTwoH).length());
			characterfile.newLine();
			characterfile.write("teleThreeX = ", 0, 13);
			characterfile.write(Integer.toString(p.teleThreeX), 0,
					Integer.toString(p.teleThreeX).length());
			characterfile.newLine();
			characterfile.write("teleThreeY = ", 0, 13);
			characterfile.write(Integer.toString(p.teleThreeY), 0,
					Integer.toString(p.teleThreeY).length());
			characterfile.newLine();
			characterfile.write("teleThreeH = ", 0, 13);
			characterfile.write(Integer.toString(p.teleThreeH), 0,
					Integer.toString(p.teleThreeH).length());
			characterfile.newLine();
			characterfile.write("teleFourX = ", 0, 12);
			characterfile.write(Integer.toString(p.teleFourX), 0,
					Integer.toString(p.teleFourX).length());
			characterfile.newLine();
			characterfile.write("teleFourY = ", 0, 12);
			characterfile.write(Integer.toString(p.teleFourY), 0,
					Integer.toString(p.teleFourY).length());
			characterfile.newLine();
			characterfile.write("teleFourH = ", 0, 12);
			characterfile.write(Integer.toString(p.teleFourH), 0,
					Integer.toString(p.teleFourH).length());
			characterfile.newLine();
			characterfile.write("teleFiveX = ", 0, 12);
			characterfile.write(Integer.toString(p.teleFiveX), 0,
					Integer.toString(p.teleFiveX).length());
			characterfile.newLine();
			characterfile.write("teleFiveY = ", 0, 12);
			characterfile.write(Integer.toString(p.teleFiveY), 0,
					Integer.toString(p.teleFiveY).length());
			characterfile.newLine();
			characterfile.write("teleFiveH = ", 0, 12);
			characterfile.write(Integer.toString(p.teleFiveH), 0,
					Integer.toString(p.teleFiveH).length());
			characterfile.newLine();
			characterfile.write("tele1 = ", 0,
					"tele1 = ".length());
			characterfile.write(p.tele1, 0, p.tele1.length());
			characterfile.newLine();
			characterfile.write("tele2 = ", 0,
					"tele2 = ".length());
			characterfile.write(p.tele2, 0, p.tele2.length());
			characterfile.newLine();
			characterfile.write("tele3 = ", 0,
					"tele3 = ".length());
			characterfile.write(p.tele3, 0, p.tele3.length());
			characterfile.newLine();
			characterfile.write("tele4 = ", 0,
					"tele4 = ".length());
			characterfile.write(p.tele4, 0, p.tele4.length());
			characterfile.newLine();
			characterfile.write("tele5 = ", 0,
					"tele5 = ".length());
			characterfile.write(p.tele5, 0, p.tele5.length());
			characterfile.newLine();
			/**
			 * Saved Teleports End
			 */
			/**
			 * Loyalty Program
			 */
			characterfile.write("loyaltyPoints = ", 0, 16);
			characterfile.write(Integer.toString(p.loyaltyPoints), 0, Integer
					.toString(p.loyaltyPoints).length());
			characterfile.newLine();
			characterfile.write("currentDay = ", 0, 13);
			characterfile.write(Integer.toString(p.currentDay), 0, Integer
					.toString(p.currentDay).length());
			characterfile.newLine();
			characterfile.write("currentMonth = ", 0, 15);
			characterfile.write(Integer.toString(p.currentMonth), 0, Integer
					.toString(p.currentMonth).length());
			characterfile.newLine();
			characterfile.write("currentYear = ", 0, 14);
			characterfile.write(Integer.toString(p.currentYear), 0, Integer
					.toString(p.currentYear).length());
			characterfile.newLine();
			/**
			 * Loyalty End
			 */
			/**
			 * Kill Streak
			 */
			characterfile.write("currentStreak = ", 0, 16);
			characterfile.write(Integer.toString(p.currentStreak), 0, Integer.toString(p.currentStreak).length());
			characterfile.newLine();
			characterfile.write("highestStreak = ", 0, 16);
			characterfile.write(Integer.toString(p.highestStreak), 0, Integer.toString(p.highestStreak).length());
			characterfile.newLine();
			/**
			 * Tool Belt
			 */
			characterfile.write("hatchetInTools = ", 0, 17);
			characterfile.write(Integer.toString(p.hatchet), 0, Integer.toString(p.hatchet).length());
			characterfile.newLine();
			/**
			 * Tool Belt End
			 */
			characterfile.write("Agrith = ", 0, 9);
			characterfile.write(Boolean.toString(p.Agrith), 0, Boolean.toString(p.Agrith).length());
			characterfile.newLine();
			characterfile.write("Flambeed = ", 0, 11);
			characterfile.write(Boolean.toString(p.Flambeed), 0, Boolean.toString(p.Flambeed).length());
			characterfile.newLine();
			characterfile.write("Karamel = ", 0, 10);
			characterfile.write(Boolean.toString(p.Karamel), 0, Boolean.toString(p.Karamel).length());
			characterfile.newLine();
			characterfile.write("Dessourt = ", 0, 11);
			characterfile.write(Boolean.toString(p.Dessourt), 0, Boolean.toString(p.Dessourt).length());
			characterfile.newLine();
			characterfile.write("Culin = ", 0, 8);
			characterfile.write(Boolean.toString(p.Culin), 0, Boolean.toString(p.Culin).length());
			characterfile.newLine();
			characterfile.write("magic-book = ", 0, 13);
			characterfile.write(Integer.toString(p.playerMagicBook), 0, Integer
					.toString(p.playerMagicBook).length());
			characterfile.newLine();
			for (int b = 0; b < p.barrowsNpcs.length; b++) {
				characterfile.write("brother-info = ", 0, 15);
				characterfile.write(Integer.toString(b), 0, Integer.toString(b)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(
						p.barrowsNpcs[b][1] <= 1 ? Integer.toString(0)
								: Integer.toString(p.barrowsNpcs[b][1]), 0,
								Integer.toString(p.barrowsNpcs[b][1]).length());
				characterfile.newLine();
			}
			characterfile.write("special-amount = ", 0, 17);
			characterfile.write(Double.toString(p.specAmount), 0, Double
					.toString(p.specAmount).length());
			characterfile.newLine();
			characterfile.write("selected-coffin = ", 0, 18);
			characterfile.write(Integer.toString(p.randomCoffin), 0, Integer
					.toString(p.randomCoffin).length());
			characterfile.newLine();
			characterfile.write("barrows-killcount = ", 0, 20);
			characterfile.write(Integer.toString(p.barrowsKillCount), 0,
					Integer.toString(p.barrowsKillCount).length());
			characterfile.newLine();
			characterfile.write("teleblock-length = ", 0, 19);
			characterfile.write(Integer.toString(tbTime), 0,
					Integer.toString(tbTime).length());
			characterfile.newLine();
			characterfile.write("skillsPrestiged = ", 0, 18);
			characterfile.write(Integer.toString(p.skillsPrestiged), 0, Integer.toString(p.skillsPrestiged).length());
			characterfile.newLine();
			characterfile.write("prestigePoints = ", 0, 17);
			characterfile.write(Integer.toString(p.prestigePoints), 0, Integer.toString(p.prestigePoints).length());
			characterfile.newLine();
			characterfile.write("x2Points = ", 0, 11);
			characterfile.write(Boolean.toString(p.x2Points), 0, Boolean.toString(p.x2Points).length());
			characterfile.newLine();
			characterfile.write("pc-points = ", 0, 12);
			characterfile.write(Integer.toString(p.pcPoints), 0, Integer
					.toString(p.pcPoints).length());

			characterfile.newLine();
			characterfile.write("votePoints = ", 0, 13);
			characterfile.write(Integer.toString(p.votePoints), 0, Integer
					.toString(p.votePoints).length());

			characterfile.newLine();
			characterfile.write("donatorPoints = ", 0, 16);
			characterfile.write(Integer.toString(p.donatorPoints), 0, Integer
					.toString(p.donatorPoints).length());

			characterfile.newLine();
			characterfile.write("totalDonation = ", 0, 16);
			characterfile.write(Integer.toString(p.totalDonation), 0, Integer
					.toString(p.totalDonation).length());

			characterfile.newLine();
			characterfile.write("chestChance = ", 0, 14);
			characterfile.write(Integer.toString(p.chestChance), 0, Integer
					.toString(p.chestChance).length());

			characterfile.newLine();

			characterfile.write("membership = ", 0, 13);
			characterfile.write(Boolean.toString(p.membership), 0, Boolean.toString(p.membership).length());
			characterfile.newLine();
			characterfile.write("unMembership = ", 0, 15);
			characterfile.write(Boolean.toString(p.unMembership), 0, Boolean.toString(p.unMembership).length());
			characterfile.newLine();
			characterfile.write("startdate = ", 0, 12);
			characterfile.write(Integer.toString(p.startDate), 0, Integer.toString(p.startDate).length());
			characterfile.newLine();

			characterfile.write("lastLogin = ", 0, 12);
			String toSend = p.lastLogin[0] + "\t" + p.lastLogin[1] + "\t" + p.lastLogin[2];
			characterfile.write(toSend);
			characterfile.newLine();
			characterfile.write("memberShipDays = ", 0, 17);
			characterfile.write(Integer.toString(p.memberShipDays), 0, Integer.toString(p.memberShipDays).length());
			characterfile.newLine();

			characterfile.write("slayerPoints = ", 0, 15);
			characterfile.write(Integer.toString(p.slayerPoints), 0, Integer
					.toString(p.slayerPoints).length());
			characterfile.newLine();
			characterfile.write("taskType = ", 0, 11);
			characterfile.write(Integer.toString(p.taskType), 0, Integer
					.toString(p.taskType).length());

			characterfile.newLine();
			characterfile.write("slayerTask = ", 0, 13);
			characterfile.write(Integer.toString(p.slayerTask), 0, Integer
					.toString(p.slayerTask).length());
			characterfile.newLine();
			characterfile.write("taskAmount = ", 0, 13);
			characterfile.write(Integer.toString(p.taskAmount), 0, Integer
					.toString(p.taskAmount).length());
			characterfile.newLine();
			characterfile.write("brotherkills = ", 0, 15);
			characterfile.write(Integer.toString(p.brotherKills), 0, Integer
					.toString(p.brotherKills).length());
			characterfile.newLine();
			characterfile.write("magePoints = ", 0, 13);
			characterfile.write(Integer.toString(p.magePoints), 0, Integer
					.toString(p.magePoints).length());
			characterfile.newLine();
			characterfile.write("autoRet = ", 0, 10);
			characterfile.write(Integer.toString(p.autoRet), 0, Integer
					.toString(p.autoRet).length());
			characterfile.newLine();
			characterfile.write("toggleLogin = ", 0, 14);
			characterfile.write(Boolean.toString(p.toggleLogin), 0, Boolean
					.toString(p.toggleLogin).length());
			characterfile.newLine();
			characterfile.write("Curses = ", 0, 9);
			characterfile.write(Boolean.toString(p.curses), 0, Boolean
					.toString(p.curses).length());
			characterfile.newLine();
			characterfile.write("Prayer = ", 0, 9);
			characterfile.write(Double.toString(p.prayer), 0, Double
					.toString(p.prayer).length());
			characterfile.newLine();
			characterfile.write("DPoints = ", 0, 10);
			characterfile.write(Boolean.toString(p.hasDoublePoints), 0, Boolean
					.toString(p.hasDoublePoints).length());
			characterfile.newLine();
			characterfile.write("isJailed = ", 0, 11);
			characterfile.write(Boolean.toString(p.isJailed), 0, Boolean
					.toString(p.isJailed).length());
			characterfile.newLine();
			characterfile.write("receivedBeta = ", 0, 15);
			characterfile.write(Boolean.toString(p.hasTesting), 0, Boolean.toString(p.hasTesting).length());
			characterfile.newLine();
			characterfile.write("barrowskillcount = ", 0, 19);
			characterfile.write(Integer.toString(p.barrowsKillCount), 0,
					Integer.toString(p.barrowsKillCount).length());
			characterfile.newLine();
			characterfile.write("ability = ", 0, 10);
			characterfile.write(Integer.toString(p.playerAbility), 0, Integer
					.toString(p.playerAbility).length());
			characterfile.newLine();
			characterfile.write("currentClass = ", 0, 15);
			characterfile.write(Integer.toString(p.currentClass), 0, Integer
					.toString(p.currentClass).length());
			characterfile.newLine();
			characterfile.write("meleeClassLvl = ", 0, 16);
			characterfile.write(Integer.toString(p.meleeClassLvl), 0, Integer
					.toString(p.meleeClassLvl).length());
			characterfile.newLine();
			characterfile.write("rangedClassLvl = ", 0, 17);
			characterfile.write(Integer.toString(p.rangedClassLvl), 0, Integer
					.toString(p.rangedClassLvl).length());
			characterfile.newLine();
			characterfile.write("magicClassLvl = ", 0, 16);
			characterfile.write(Integer.toString(p.magicClassLvl), 0, Integer
					.toString(p.magicClassLvl).length());
			characterfile.newLine();
			characterfile.write("lvlPotential = ", 0, 15);
			characterfile.write(Integer.toString(p.lvlPotential), 0, Integer
					.toString(p.lvlPotential).length());
			characterfile.newLine();
			characterfile.write("weaponKills = ", 0, 14);
			characterfile.write(Integer.toString(p.weaponKills), 0, Integer
					.toString(p.weaponKills).length());
			characterfile.newLine();
			characterfile.write("weaponDeaths = ", 0, 15);
			characterfile.write(Integer.toString(p.weaponDeaths), 0, Integer
					.toString(p.weaponDeaths).length());
			characterfile.newLine();
			characterfile.write("kills = ", 0, 8);
			characterfile.write(Integer.toString(p.kills), 0, Integer.toString(p.kills).length());
			characterfile.newLine();
			characterfile.write("deaths = ", 0, 9);
			characterfile.write(Integer.toString(p.deaths), 0, Integer.toString(p.deaths).length());
			characterfile.newLine();
			characterfile.write("dfscount = ", 0, 11);
			characterfile.write(Integer.toString(p.dfsCount), 0, Integer.toString(p.dfsCount).length());
			characterfile.newLine();
			characterfile.write("ppsLeft = ", 0, 10);
			characterfile.write(Integer.toString(p.ppsLeft), 0, Integer
					.toString(p.ppsLeft).length());
			characterfile.newLine();
			characterfile.write("loyalty-title = ", 0, 16);
			characterfile.write(Integer.toString(p.loyaltyTitle), 0, Integer
					.toString(p.loyaltyTitle).length());
			characterfile.newLine();
			characterfile.write("energy = ", 0, 9);
			characterfile.write(Integer.toString(p.playerEnergy), 0, Integer
					.toString(p.playerEnergy).length());
			characterfile.newLine();
			characterfile.write("effskill = ", 0, 11);
			characterfile.write(Integer.toString(p.effigySkill), 0, Integer
					.toString(p.effigySkill).length());
			characterfile.newLine();
			characterfile.write("efflvl = ", 0, 9);
			characterfile.write(Integer.toString(p.effigySkillLvl), 0, Integer
					.toString(p.effigySkillLvl).length());
			characterfile.newLine();
			characterfile.write("flagged = ", 0, 10);
			characterfile.write(Boolean.toString(p.accountFlagged), 0, Boolean
					.toString(p.accountFlagged).length());
			characterfile.newLine();
			characterfile.write("wave = ", 0, 7);
			characterfile.write(Integer.toString(p.waveId), 0, Integer
					.toString(p.waveId).length());
			characterfile.newLine();
			characterfile.write("Familiar-Decr = ", 0, 16);
			characterfile.write(Integer.toString(p.decreaseTime), 0, Integer
					.toString(p.decreaseTime).length());
			characterfile.newLine();
			characterfile.write("Familiar-Spec = ", 0, 16);
			characterfile.write(Integer.toString(p.summoningSpecialPoints), 0,
					Integer.toString(p.summoningSpecialPoints).length());
			characterfile.newLine();
			characterfile.write("Familiar-ID = ", 0, 14);
			characterfile.write(Integer.toString(p.familiarID), 0, Integer
					.toString(p.familiarID).length());
			characterfile.newLine();
			characterfile.write("Familiar-Minutes = ", 0, 19);
			characterfile.write(Integer.toString(p.familiarMinutes), 0, Integer
					.toString(p.familiarMinutes).length());
			characterfile.newLine();
			characterfile.write("Familiar-Seconds = ", 0, 19);
			characterfile.write(Integer.toString(p.familiarSeconds), 0, Integer
					.toString(p.familiarSeconds).length());
			characterfile.newLine();
			characterfile.write("totalstored = ", 0, 14);
			characterfile.write(Integer.toString(p.totalstored), 0, Integer
					.toString(p.totalstored).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("bind1 = ", 0, 8);
			characterfile.write(Integer.toString(p.bind1), 0,
					Integer.toString(p.bind1).length());
			characterfile.newLine();
			characterfile.write("bind2 = ", 0, 8);
			characterfile.write(Integer.toString(p.bind2), 0,
					Integer.toString(p.bind2).length());
			characterfile.newLine();
			characterfile.write("bind3 = ", 0, 8);
			characterfile.write(Integer.toString(p.bind3), 0,
					Integer.toString(p.bind3).length());
			characterfile.newLine();
			characterfile.write("bind4 = ", 0, 8);
			characterfile.write(Integer.toString(p.bind4), 0,
					Integer.toString(p.bind4).length());
			characterfile.newLine();
			characterfile.write("itemsBound = ", 0, 13);
			characterfile.write(Integer.toString(p.itemsBounded), 0, Integer
					.toString(p.itemsBounded).length());
			characterfile.newLine();
			characterfile.write("pouches = ", 0, 10);
			String toAdd = ""+p.smallPouchAmt+"_"+p.smallPouch+"_"+p.mediumPouchAmt+"_"+p.mediumPouch+"_"+
			p.largePouchAmt+"_"+p.largePouch+"_"+p.giantPouchAmt+"_"+p.giantPouch;
			characterfile.write(toAdd, 0, toAdd.length());
			characterfile.newLine();
			characterfile.write("gwkc = ", 0, 7);
			characterfile.write(Integer.toString(p.killCount), 0, Integer
					.toString(p.killCount).length());
			characterfile.newLine();
			characterfile.write("fightMode = ", 0, 12);
			characterfile.write(Integer.toString(p.fightMode), 0, Integer
					.toString(p.fightMode).length());
			characterfile.newLine();
			characterfile.write("PKPoints = ", 0, 11);
			characterfile.write(Integer.toString(p.pkPoints), 0, Integer
					.toString(p.pkPoints).length());
			characterfile.newLine();
			characterfile.write("DTokens = ", 0, 10);
			characterfile.write(Integer.toString(p.dTokens), 0, Integer
					.toString(p.dTokens).length());
			characterfile.newLine();
			characterfile.write("AGSStatus = ", 0, 12);
			characterfile.write(Integer.toString(p.aGoodStartStatus), 0,
					Integer.toString(p.aGoodStartStatus).length());
			characterfile.newLine();
			characterfile.write("TKVStatus = ", 0, 12);
			characterfile.write(Integer.toString(p.TKVStatus), 0, Integer
					.toString(p.TKVStatus).length());
			characterfile.newLine();
			characterfile.write("questPoints = ", 0, 14);
			characterfile.write(Integer.toString(p.questPoints), 0, Integer
					.toString(p.questPoints).length());
			characterfile.newLine();
			characterfile.write("lunarDiplomacy = ", 0, 17); 
			characterfile.write(Integer.toString(p.lunarDiplomacy), 0, Integer
					.toString(p.lunarDiplomacy).length());
			characterfile.newLine();
			characterfile.write("dTreasure = ", 0, 12); 
			characterfile.write(Integer.toString(p.DT), 0, Integer
					.toString(p.DT).length());
			characterfile.newLine();
			characterfile.write("theStryke = ", 0, 12); 
			characterfile.write(Integer.toString(p.theStryke), 0, Integer
					.toString(p.theStryke).length());
			characterfile.newLine();
			characterfile.write("santasHelp = ", 0, 13); 
			characterfile.write(Integer.toString(p.santasHelp), 0, Integer
					.toString(p.santasHelp).length());
			characterfile.newLine();

			characterfile.write("void = ", 0, 7);
			final String toWrite = p.voidStatus[0] + "\t" + p.voidStatus[1]
					+ "\t" + p.voidStatus[2] + "\t" + p.voidStatus[3] + "\t"
					+ p.voidStatus[4];
			characterfile.write(toWrite);
			characterfile.newLine();

			characterfile.write("killcount = ", 0, 12);
			final String toWrite1 = p.godwarsKillCount[0] + "\t"
					+ p.godwarsKillCount[1] + "\t" + p.godwarsKillCount[2]
							+ "\t" + p.godwarsKillCount[3];
			characterfile.write(toWrite1);
			characterfile.newLine();
			characterfile.write("lastclanchat = ", 0,
					"lastclanchat = ".length());
			characterfile.write(p.lastClanChat, 0, p.lastClanChat.length());
			characterfile.newLine();
			characterfile.write("setTask = ", 0, 10); 
			characterfile.write(Integer.toString(p.setTask), 0, Integer
					.toString(p.setTask).length());
			characterfile.newLine();
			characterfile.write("effectrestore = ", 0, 16); 
			characterfile.write(Integer.toString(p.effectRestore), 0, Integer
					.toString(p.effectRestore).length());
			characterfile.newLine();
			characterfile.write("skillTask = ", 0, 12); 
			characterfile.write(Integer.toString(p.skillTask), 0, Integer
					.toString(p.skillTask).length());
			characterfile.newLine();
			characterfile.write("skillTask1 = ", 0, 13); 
			characterfile.write(Integer.toString(p.skillTask), 0, Integer
					.toString(p.skillTask).length());
			characterfile.newLine();
			characterfile.write("skillTask2 = ", 0, 13); 
			characterfile.write(Integer.toString(p.skillTask), 0, Integer
					.toString(p.skillTask).length());
			characterfile.newLine();
			characterfile.write("skillAmount = ", 0, 14); 
			characterfile.write(Integer.toString(p.skillAmount), 0, Integer
					.toString(p.skillAmount).length());
			characterfile.newLine();
			characterfile.write("skillAmount1 = ", 0, 15); 
			characterfile.write(Integer.toString(p.skillAmount1), 0, Integer
					.toString(p.skillAmount1).length());
			characterfile.newLine();
			characterfile.write("skillAmount2 = ", 0, 15); 
			characterfile.write(Integer.toString(p.skillAmount2), 0, Integer
					.toString(p.skillAmount2).length());
			characterfile.newLine();
			characterfile.write("ia = ", 0, 5); 
			characterfile.write(Integer.toString(p.ia), 0, Integer
					.toString(p.ia).length());
			characterfile.newLine();
			characterfile.write("ia1 = ", 0, 6); 
			characterfile.write(Integer.toString(p.ia1), 0, Integer
					.toString(p.ia1).length());
			characterfile.newLine();
			characterfile.write("ia2 = ", 0, 6); 
			characterfile.write(Integer.toString(p.ia2), 0, Integer
					.toString(p.ia2).length());
			characterfile.newLine();
			characterfile.write("tid = ", 0, 6); 
			characterfile.write(Integer.toString(p.tid), 0, Integer
					.toString(p.tid).length());
			characterfile.newLine();
			characterfile.write("tid1 = ", 0, 7); 
			characterfile.write(Integer.toString(p.tid1), 0, Integer
					.toString(p.tid1).length());
			characterfile.newLine();
			characterfile.write("tid2 = ", 0, 7); 
			characterfile.write(Integer.toString(p.tid2), 0, Integer
					.toString(p.tid2).length());
			characterfile.newLine();
			characterfile.write("skillPoints = ", 0, 14); 
			characterfile.write(Integer.toString(p.skillPoints), 0, Integer
					.toString(p.skillPoints).length());
			characterfile.newLine();
			characterfile.write("totNPCKO = ", 0, 11); 
			characterfile.write(Integer.toString(p.totalNPCKO), 0, Integer
					.toString(p.totalNPCKO).length());
			characterfile.newLine();
			characterfile.write("completeAchievements = ", 0, 22); 
			characterfile.write(Integer.toString(p.completeAchievements), 0, Integer
					.toString(p.completeAchievements).length());
			characterfile.newLine();
			characterfile.write("THMATT = ", 0, 9); 
			characterfile.write(Integer.toString(p.THMATT), 0, Integer.toString(p.THMATT).length());
			characterfile.newLine();
			characterfile.write("THMDEF = ", 0, 9); 
			characterfile.write(Integer.toString(p.THMDEF), 0, Integer.toString(p.THMDEF).length());
			characterfile.newLine();
			characterfile.write("THMSTR = ", 0, 9); 
			characterfile.write(Integer.toString(p.THMSTR), 0, Integer.toString(p.THMSTR).length());
			characterfile.newLine();
			characterfile.write("THMHP = ", 0, 8); 
			characterfile.write(Integer.toString(p.THMHP), 0, Integer.toString(p.THMHP).length());
			characterfile.newLine();
			characterfile.write("THMRANG = ", 0, 10); 
			characterfile.write(Integer.toString(p.THMRANG), 0, Integer.toString(p.THMRANG).length());
			characterfile.newLine();
			characterfile.write("THMPRAY = ", 0, 10); 
			characterfile.write(Integer.toString(p.THMPRAY), 0, Integer.toString(p.THMPRAY).length());
			characterfile.newLine();
			characterfile.write("THMMAGE = ", 0, 10); 
			characterfile.write(Integer.toString(p.THMMAGE), 0, Integer.toString(p.THMMAGE).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.newLine();

		//*****************//
		//** ACHIEVEMENT **//
		//*****************//

			characterfile.write("[ACHIEVEMENT]", 0, 13);
			characterfile.newLine();
			characterfile.write("xpMaster = ", 0, 11); 
			characterfile.write(Integer.toString(p.xpMaster), 0, Integer
					.toString(p.xpMaster).length());
			characterfile.newLine();
			characterfile.write("achieved = ", 0, 11);
			String toWritee = "";
			for(int i1 = 0; i1 < p.achieved.length; i1++) {
				toWritee += p.achieved[i1] +"\t";
			}
			characterfile.write(toWritee);
			characterfile.newLine();
			characterfile.write("achievement = ", 0, 14);
			String toWritee1 = "";
			for(int i1 = 0; i1 < p.achievement.length; i1++) {
				toWritee1 += p.achievement[i1] +"\t";
			}
			characterfile.write(toWritee1);
			characterfile.newLine();
			characterfile.write("points = ", 0, 9);
			characterfile.write(Integer.toString(p.achievementPoints), 0, Integer.toString(p.achievementPoints).length());
			characterfile.newLine();

			/* EQUIPMENT */
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < p.playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipment[i]), 0,
						Integer.toString(p.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipmentN[i]), 0,
						Integer.toString(p.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/* LOOK */
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.playerAppearance.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerAppearance[i]), 0,
						Integer.toString(p.playerAppearance[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* SKILLS */
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerLevel[i]), 0,
						Integer.toString(p.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerXP[i]), 0, Integer
						.toString(p.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* ITEMS */
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < p.playerItems.length; i++) {
				if (p.playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItems[i]), 0,
							Integer.toString(p.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItemsN[i]), 0,
							Integer.toString(p.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* BANK */
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.bankItems.length; i++) {
				if (p.bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems[i]), 0, Integer.toString(p.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItemsN[i]), 0, Integer.toString(p.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems1.length; i++) {
				if (p.bankItems1[i] > 0) {
					characterfile.write("character-bank1 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems1[i]), 0, Integer.toString(p.bankItems1[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems1N[i]), 0, Integer.toString(p.bankItems1N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems2.length; i++) {
				if (p.bankItems2[i] > 0) {
					characterfile.write("character-bank2 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems2[i]), 0, Integer.toString(p.bankItems2[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems2N[i]), 0, Integer.toString(p.bankItems2N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems3.length; i++) {
				if (p.bankItems3[i] > 0) {
					characterfile.write("character-bank3 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems3[i]), 0, Integer.toString(p.bankItems3[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems3N[i]), 0, Integer.toString(p.bankItems3N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems4.length; i++) {
				if (p.bankItems4[i] > 0) {
					characterfile.write("character-bank4 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems4[i]), 0, Integer.toString(p.bankItems4[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems4N[i]), 0, Integer.toString(p.bankItems4N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems5.length; i++) {
				if (p.bankItems5[i] > 0) {
					characterfile.write("character-bank5 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems5[i]), 0, Integer.toString(p.bankItems5[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems5N[i]), 0, Integer.toString(p.bankItems5N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems6.length; i++) {
				if (p.bankItems6[i] > 0) {
					characterfile.write("character-bank6 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems6[i]), 0, Integer.toString(p.bankItems6[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems6N[i]), 0, Integer.toString(p.bankItems6N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems7.length; i++) {
				if (p.bankItems7[i] > 0) {
					characterfile.write("character-bank7 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems7[i]), 0, Integer.toString(p.bankItems7[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems7N[i]), 0, Integer.toString(p.bankItems7N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems8.length; i++) {
				if (p.bankItems8[i] > 0) {
					characterfile.write("character-bank8 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems8[i]), 0, Integer.toString(p.bankItems8[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems8N[i]), 0, Integer.toString(p.bankItems8N[i]).length());
					characterfile.newLine();
				}
			}

			characterfile.newLine();
			/* FRIENDS */
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < p.friends.length; i++) {
				if (p.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write("" + p.friends[i]);
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/* Storeditems */
			characterfile.write("[STORED]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.bobItems.length; i++) {
				characterfile.write("stored = ", 0, 9);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.bobItems[i]), 0, Integer
						.toString(p.bobItems[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[DEGRADEABLES]", 0, 14);
			characterfile.newLine();
			for (int i = 1; i < p.chaoticDegrade.length; i++) {
				characterfile.write("chaotic = ", 0, 10);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.chaoticDegrade[i]), 0, Integer.toString(p.chaoticDegrade[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* IGNORES */

			/*
			 * characterfile.write("[IGNORES]", 0, 9); characterfile.newLine();
			 * for (int i = 0; i < ignores.length; i++) { if (ignores[i] > 0) {
			 * characterfile.write("character-ignore = ", 0, 19);
			 * characterfile.write(Integer.toString(i), 0,
			 * Integer.toString(i).length()); characterfile.write("	", 0, 1);
			 * characterfile.write(Long.toString(ignores[i]), 0,
			 * Long.toString(ignores[i]).length()); characterfile.newLine(); } }
			 * characterfile.newLine();
			 */
			/* EOF */
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (final IOException ioexception) {
			Misc.println(p.playerName + ": error writing file.");
			return false;
		}
		return true;
	}

}
