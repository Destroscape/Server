package game.combat;

import game.Config;
import game.entity.npc.animation.BlockAnimation;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class CombatData {

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

	public static void resetPlayerAttack(Player c) {
		if (c.ppsRefresh) {
			c.autocasting = false;
			c.autocastId = -1;
			// c.spellId = -1;
			c.usingMagic = false;
		}
		c.usingMagic = false;
		c.npcIndex = 0;
		c.faceUpdate(0);
		c.playerIndex = 0;
		c.getPA().resetFollow();
	}

	public static boolean usingHally(Player c) {
		switch (c.playerEquipment[c.playerWeapon]) {
		case 3190:
		case 3192:
		case 3194:
		case 3196:
		case 3198:
		case 3200:
		case 3202:
		case 3204:
			return true;

		default:
			return false;
		}
	}

	public void weaponInfo(final Player c, int s, int w, int r) {
		c.playerStandIndex = s;
		c.playerWalkIndex = w;
		c.playerRunIndex = r;
	}

	public static void getPlayerAnimIndex(Player c, String weaponName) {
		c.playerStandIndex = 0x328;
		c.playerTurnIndex = 0x337;
		c.playerWalkIndex = 0x333;
		c.playerTurn180Index = 0x334;
		c.playerTurn90CWIndex = 0x335;
		c.playerTurn90CCWIndex = 0x336;
		c.playerRunIndex = 0x338;

		if (weaponName.contains("halberd") || weaponName.contains("hasta")
				|| weaponName.contains("guthan")
				|| weaponName.contains("sceptre")) {
			c.playerStandIndex = 809;
			c.playerWalkIndex = 1146;
			c.playerRunIndex = 1210;
			return;
		}
		if (weaponName.contains("sled")) {
			c.playerStandIndex = 1461;
			c.playerWalkIndex = 1468;
			c.playerRunIndex = 1467;
			return;
		}
		if (weaponName.contains("dharok")) {
			c.playerStandIndex = 0x811;
			c.playerWalkIndex = 2064;
			return;
		}
		if (weaponName.contains("ahrim")) {
			c.playerStandIndex = 809;
			c.playerWalkIndex = 1146;
			c.playerRunIndex = 1210;
			return;
		}
		if (weaponName.contains("verac")) {
			c.playerStandIndex = 1832;
			c.playerWalkIndex = 1830;
			c.playerRunIndex = 1831;
			return;
		}
		if (weaponName.contains("hand cannon")) {
			c.playerStandIndex = 12155;
			c.playerWalkIndex = 12154;
			c.playerRunIndex = 12154;
		}
		if (weaponName.contains("chaotic staff")) {
			// weaponInfo(808, 1210, 1146);
			c.playerStandIndex = 808;
			c.playerRunIndex = 1210;
			c.playerWalkIndex = 1146;
			c.playerTurnIndex = 1205;
			c.playerTurn180Index = 1206;
			c.playerTurn90CWIndex = 1207;
			c.playerTurn90CCWIndex = 1208;
			return;
		}
		if (weaponName.contains("staff") || weaponName.contains("rapier")
				|| weaponName.contains("wand") || weaponName.contains("banner")
				|| weaponName.contains("halberd")) {
			// weaponInfo(12010, 1146, 1210);
			c.playerStandIndex = 12010;
			c.playerWalkIndex = 1146;
			c.playerRunIndex = 1210;
			c.playerTurnIndex = 1205;
			c.playerTurn180Index = 1206;
			c.playerTurn90CWIndex = 1207;
			c.playerTurn90CCWIndex = 1208;
			return;
		}
		if (weaponName.contains("karil")) {
			c.playerStandIndex = 2074;
			c.playerWalkIndex = 2076;
			c.playerRunIndex = 2077;
			return;
		}
		if (weaponName.contains("2h sword") || weaponName.contains("godsword")
				|| weaponName.contains("saradomin sw") || weaponName.contains("katana")) {
			// weaponInfo(7047, 7046, 7039);
			c.playerStandIndex = 7047;
			c.playerWalkIndex = 7046;
			c.playerRunIndex = 7039;
			c.playerTurnIndex = 7040;
			c.playerTurn180Index = 7045;
			c.playerTurn90CWIndex = 7043;
			c.playerTurn90CCWIndex = 7044;
			return;
		}
		if (weaponName.contains("bow")) {
			c.playerStandIndex = 808;
			c.playerWalkIndex = 819;
			c.playerRunIndex = 824;
			return;
		}
		if (weaponName.contains("maul")) {
			c.playerStandIndex = 1662;
			c.playerWalkIndex = 1663;
			c.playerRunIndex = 1664;
			return;
		}

		if (weaponName.contains("whip")) {
			c.playerStandIndex = 11973;
			c.playerWalkIndex = 1660;
			c.playerRunIndex = 1661;
		}

		if (weaponName.contains("scimitar")) {
			c.playerStandIndex = 809;
		}

		switch (c.playerEquipment[c.playerWeapon]) {
		case 8004:
		case 7960:
			c.playerStandIndex = 2065;
			c.playerWalkIndex = 2064;
			break;
		case 6528:
			c.playerStandIndex = 0x811;
			c.playerWalkIndex = 2064;
			c.playerRunIndex = 1664;
			break;
		case 4153:
			c.playerStandIndex = 1662;
			c.playerWalkIndex = 1663;
			c.playerRunIndex = 1664;
			break;
		case 10887:
			c.playerStandIndex = 5869;
			c.playerWalkIndex = 5867;
			c.playerRunIndex = 5868;
			break;
		case 11694:
		case 11696:
		case 11730:
		case 11698:
		case 11700:
			c.playerStandIndex = 7047;
			c.playerWalkIndex = 7046;
			c.playerRunIndex = 7039;
			c.playerTurnIndex = 7044;
			c.playerTurn180Index = 7044;
			c.playerTurn90CWIndex = 7044;
			c.playerTurn90CCWIndex = 7044;
			break;
		case 1305:
			c.playerStandIndex = 809;
			break;
		}
	}

	public static int getWepAnim(final Player c, String weaponName) {
		if (c.playerEquipment[c.playerWeapon] <= 0) {
			switch (c.fightMode) {
			case 0:
				return 422;
			case 2:
				return 423;
			case 1:
				return 422;
			}
		}
		if (weaponName != null && !weaponName.equals("null")) {
			if (weaponName.contains("crossbow")) {
				return weaponName.contains("karil's crossbow") ? 2075
						: 4230;
			}
			if (weaponName.contains("bow")) {
				return 426;
			}
			if (weaponName.contains("torag's hammer")) {
				return 2068;
			}
			if (weaponName.contains("staff of light")) {
				switch (c.fightMode) {
				case 0:
					return 15072;
				case 1:
					return 15071;
				case 2:
					return 414;
				}
			}
			if (weaponName.contains("staff") || weaponName.contains("wand")) {
				return 419;
			}
			if (weaponName.contains("dart")) {
				return 6600;
			}
			if (weaponName.contains("knife")) {
				return 9055;
			}
			if (weaponName.contains("scimitar")
					|| weaponName.contains("korasi's sword")
					|| weaponName.contains("thok's sword")) {
				switch (c.fightMode) {
				case 2:
					return 15071;
				default:
					return 15072;
				}
			}
			if (weaponName.contains("battleaxe")) {
					return 15071;
			}
			if (weaponName.contains("granite mace")) {
				return 400;
			}
			if (weaponName.contains("mace")) {
				switch (c.fightMode) {
				case 2:
					return 400;
				default:
					return 401;
				}
			}
			if (weaponName.contains("hatchet")) {
				switch (c.fightMode) {
				case 2:
					return 401;
				default:
					return 395;
				}
			}
			if (weaponName.contains("warhammer")) {
				switch (c.fightMode) {
				default:
					return 401;
				}
			}
			if (weaponName.contains("claws")) {
				switch (c.fightMode) {
				case 2:
					return 1067;
				default:
					return 393;
				}
			}
			if (weaponName.contains("whip")) {
				switch (c.fightMode) {
				case 1:
					return 11969;
				case 2:
					return 11970;
				default:
					return 11968;
				}
			}
			if (weaponName.contains("anchor")) {
				switch (c.fightMode) {
				default:
					return 5865;
				}
			}
			if (weaponName.contains("tzhaar-ket-em")) {
				switch (c.fightMode) {
				default:
					return 401;
				}
			}
			if (weaponName.contains("tzhaar-ket-om")) {
				switch (c.fightMode) {
				default:
					return 13691;
				}
			}
			if (weaponName.contains("halberd")) {
				switch (c.fightMode) {
				case 1:
					return 440;
				default:
					return 428;
				}
			}
			if (weaponName.contains("zamorakian spear")) {
				switch (c.fightMode) {
				case 1:
					return 12005;
				case 2:
					return 12009;
				default:
					return 12006;
				}
			}
			if (weaponName.contains("spear")) {
				switch (c.fightMode) {
				case 1:
					return 440;
				case 2:
					return 429;
				default:
					return 428;
				}
			}
			if (weaponName.contains("flail")) {
				return 2062;
			}
			if (weaponName.contains("javelin")) {
				return 10501;
			}
			if (weaponName.contains("morrigan's throwing axe")) {
				return 10504;
			}
			if (weaponName.contains("pickaxe")) {
				switch (c.fightMode) {
				case 2:
					return 400;
				default:
					return 401;
				}
			}
			if (weaponName.contains("dagger")) {
				switch (c.fightMode) {
				case 2:
					return 377;
				default:
					return 376;
				}
			}
			if (weaponName.contains("longsword")
					|| weaponName.contains("light")
					|| weaponName.contains("excalibur")) {
				switch (c.fightMode) {
				case 2:
					return 12310;
				default:
					return 12311;
				}
			}
			if (weaponName.contains("rapier")
					|| weaponName.contains("brackish")) {
				switch (c.fightMode) {
				case 2:
					return 13048;
				default:
					return 13049;
				}
			}

			if (weaponName.contains("godsword")) {
				switch (c.fightMode) {
				case 2:
					return 11980;
				case 3:
					return 11981;
				default:
					return 11979;
				}
			}
			if (weaponName.contains("greataxe")) {
				switch (c.fightMode) {
				case 2:
					return 12003;
				default:
					return 12002;
				}
			}
			if (weaponName.contains("granite maul")) {
				switch (c.fightMode) {
				default:
					return 1665;
				}
			}
			if (weaponName.contains("2h sword")
					|| weaponName.equals("saradomin sword") || weaponName.contains("katana")) {
				switch (c.fightMode) {
				case 2:
					return 7048;
				case 3:
					return 7049;
				default:
					return 7041;
				}
			}
			switch (c.playerEquipment[c.playerWeapon]) {
			case 16405:// novite maul
			case 16174:
			case 16407:// Bathus maul
			case 16175:
			case 16409:// Maramaros maul
			case 16176:
			case 16411:// Kratonite maul
			case 16177:
			case 16413:// Fractite maul
			case 16178:
			case 18353:// chaotic maul
			case 16415:// Zephyrium maul
			case 16179:
			case 16417:// Argonite maul
			case 16180:
			case 16419:// Katagon maul
			case 16181:
			case 16421:// Gorgonite maul
			case 16182:
			case 16423:// Promethium maul
			case 16183:
			case 16425:// primal maul
			case 16184:
				return 2661; // maul
			case 13883: // morrigan thrown axe
				return 10504;
			case 15241:
				return 12174;
			default:

			}

		}
		return 422; // todo default emote

	}

	public static int getBlockEmote(Player c) {
		String shield = c.getItems()
				.getItemName(c.playerEquipment[c.playerShield]).toLowerCase();
		String weaponName = c.getItems()
				.getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase();
		if (shield.contains("defender"))
			return 4177;
		if (shield.contains("2h") || weaponName.contains("katana"))
			return 7050;
		if (shield.contains("book") && (weaponName.contains("wand")))
			return 420;
		if (shield.contains("shield"))
			return 1156;

		if (weaponName != null && !weaponName.equals("null")) {
			if (weaponName.contains("scimitar")
					|| weaponName.contains("korasi sword"))
				return 15074;
			if (weaponName.contains("whip"))
				return 11974;
			if (weaponName.contains("staff of light"))
				return 12806;
			if (weaponName.contains("longsword")
					|| weaponName.contains("darklight")
					|| weaponName.contains("silverlight")
					|| weaponName.contains("excalibur"))
				return 388;
			if (weaponName.contains("dagger"))
				return 378;
			if (weaponName.contains("rapier"))
				return 13038;
			if (weaponName.contains("pickaxe"))
				return 397;
			if (weaponName.contains("mace"))
				return 403;
			if (weaponName.contains("claws"))
				return 404;
			if (weaponName.contains("hatchet"))
				return 397;
			if (weaponName.contains("hand cannon"))
				return 1666;
			if (weaponName.contains("greataxe"))
				return 12004;
			if (weaponName.contains("wand"))
				return 415;
			if (weaponName.contains("staff"))
				return 420;
			if (weaponName.contains("warhammer")
					|| weaponName.contains("tzhaar-ket-em"))
				return 403;
			if (weaponName.contains("maul")
					|| weaponName.contains("tzhaar-ket-om"))
				return 1666;
			if (weaponName.contains("zamorakian spear"))
				return 12008;
			if (weaponName.contains("spear")
					|| weaponName.contains("halberd")
					|| weaponName.contains("hasta"))
				return 430;
			if (weaponName.contains("2h sword")
					|| weaponName.contains("godsword")
					|| weaponName.equals("saradomin sword"))
				return 7050;
		}


		return 424;
	}

	public static int getAttackDelay(Player c, String s) {
		if (c.usingMagic) {
			switch (c.MAGIC_SPELLS[c.spellId][0]) {
			case 12871: // ice blitz
			case 13023: // shadow barrage
			case 12891: // ice barrage
				return 5;

			default:
				return 5;
			}
		}
		if (c.playerEquipment[c.playerWeapon] == -1)
			return 4;// unarmed
		switch (c.playerEquipment[c.playerWeapon]) {
		case 11235:
			return 9;
		case 11730:
			return 4;
		case 6528:
			return 7;
		case 10033:
		case 10034:
			return 5;
		case 15241:
			return 9;
		}
		if (s.endsWith("greataxe"))
			return 7;
		else if (s.equals("torags hammers"))
			return 5;
		else if (s.equals("barrelchest anchor"))
			return 7;
		else if (s.equals("guthans warspear"))
			return 5;
		else if (s.equals("veracs flail"))
			return 5;
		else if (s.contains("rapier"))
			return 4;
		else if (s.contains("maul"))
			return 6;
		else if (s.equals("ahrims staff"))
			return 6;
		else if (s.contains("staff")) {
			if (s.contains("zamarok") || s.contains("guthix")
					|| s.contains("saradomian") || s.contains("slayer")
					|| s.contains("ancient"))
				return 4;
			else
				return 5;
		} else if (s.contains("bow")) {
			if (s.contains("composite") || s.equals("seercull"))
				return 5;
			else if (s.contains("aril"))
				return 4;
			else if (s.contains("Ogre"))
				return 8;
			else if (s.contains("short") || s.contains("hunt")
					|| s.contains("sword"))
				return 4;
			else if (s.contains("long") || s.contains("crystal"))
				return 6;
			else if (s.contains("'bow"))
				return 7;
			else if (s.contains("hand cannon"))
				return 7;
			else if (s.contains("crossbow"))
				return 7;

			return 5;
		} else if (s.contains("dagger"))
			return 4;
		else if (s.contains("godsword") || s.contains("2h") || s.contains("katana"))
			return 6;
		else if (s.contains("longsword"))
			return 5;
		else if (s.contains("sword"))
			return 4;
		else if (s.contains("scimitar"))
			return 4;
		else if (s.contains("mace"))
			return 5;
		else if (s.contains("battleaxe"))
			return 6;
		else if (s.contains("pickaxe"))
			return 5;
		else if (s.contains("thrownaxe"))
			return 5;
		else if (s.contains("axe"))
			return 5;
		else if (s.contains("warhammer"))
			return 6;
		else if (s.contains("2h"))
			return 7;
		else if (s.contains("spear"))
			return 5;
		else if (s.contains("claw"))
			return 4;
		else if (s.contains("halberd"))
			return 7;
		else if (s.equals("granite maul"))
			return 7;
		else if (s.equals("toktz-xil-ak"))// sword
			return 4;
		else if (s.equals("tzhaar-ket-em"))// mace
			return 5;
		else if (s.equals("tzhaar-ket-om"))// maul
			return 7;
		else if (s.equals("toktz-xil-ek"))// knife
			return 4;
		else if (s.equals("toktz-xil-ul"))// rings
			return 4;
		else if (s.equals("toktz-mej-tal"))// staff
			return 6;
		else if (s.contains("whip"))
			return 4;
		else if (s.contains("dart"))
			return 3;
		else if (s.contains("knife"))
			return 3;
		else if (s.contains("javelin"))
			return 6;
		return 5;
	}

	public static int getHitDelay(Player c, int i, String weaponName) {
		if (c.usingMagic) {
			switch (c.MAGIC_SPELLS[c.spellId][0]) {
			case 12891:
				return 4;
			case 12871:
				return 6;
			default:
				return 4;
			}
		} else {
			if (weaponName.contains("dart")) {
				return 3;
			}
			if (weaponName.contains("knife") || weaponName.contains("javelin")
					|| weaponName.contains("thrownaxe")) {
				return 3;
			}
			if (weaponName.contains("cross") || weaponName.contains("c'bow")) {
				return 4;
			}
			if (weaponName.contains("bow") && !c.dbowSpec) {
				return 4;
			} else if (c.dbowSpec) {
				return 4;
			//}
			/*if (weaponName.contains("hand cannon") && !c.hcSpec) {
				return 4;
			} else if (c.hcSpec) {
				return 4;*/

			}

			switch (c.playerEquipment[c.playerWeapon]) {
			case 6522: // Toktz-xil-ul
				return 3;
			case 10887:
				return 3;
			case 10034:
			case 10033:
				return 3;
			default:
				return 2;
			}
		}
	}

	public static int npcDefenceAnim(int i) {
		return BlockAnimation.handleEmote(i);
	}
}