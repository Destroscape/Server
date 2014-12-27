package game.entity.npc.animation;

import game.Server;
import game.combat.CombatData;

public class BlockAnimation extends CombatData {

	public static int handleEmote(int i) {
		switch (Server.npcHandler.getNPCs()[i].npcType) {
		case 1459: //Monkey guard
			return 1403;
		case 491:
			return 5541;
		case 10837:
		case 10838:
		case 10840:
			return 13406;
		case 78:
			return 4916;
		case 1961: //Mummy
			return 5550;
		case 100:
			return 6189;
		case 101:
			return 6183;
		case 3835:
			return 6232;
		case 2037:
			return 5489;
		case 5529:
			return 5783;
		case 5219:
		case 5218:
			return 5096;
		case 5235:
			return 5395;
		case 10127:
			return 13170;
		case 10057:
			return 10818;
		case 5904:
			return 6330;
		case 5903:
			return 6346;
		case 9463:
		case 9465:
		case 9467:
			return 12792;
		case 6624:
			return 7413;
		case 6619:
			return 7443;
		case 6649:
			return 7469;
		case 6646:
			return 7462;
		case 3836:
			return 6237;
		case 2783:
			return 2732;
		case 8133:
			return 10058;
		case 10141:
			return 13601;
		case 8349:
			return 10923;
		case 9947:
			return 13771;
		case 6260:
			return 7061;
		case 6261:
		case 6263:
		case 6265:
			return 6155;
		case 6222:
			return 6974;
		case 6223:
		case 6225:
		case 6227:
			return 6952;
		case 6203:
			return 6944;
		case 6204:
		case 6206:
		case 6208:
			return 65;
		case 6247:
			return 6966;
		case 6248:
			return 6375;
		case 6250:
			return 7017;
		case 6252:
			return 4311;
		case 6229:
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
			return 6952;
		case 6267:
			return 360;
		case 6268:
			return 2933;
		case 6269:
		case 6270:
			return 4651;
		case 6271:
		case 6272:
		case 6273:
		case 6274:
			return 4322;
		case 6275:
			return 165;
		case 6276:
		case 6277:
		case 6278:
			return 4322;
		case 6279:
		case 6280:
			return 6183;
		case 6281:
			return 6136;
		case 6282:
			return 6189;
		case 6283:
			return 6183;
		case 6210:
			return 6578;
		case 6211:
			return 170;
		case 6212:
		case 6213:
			return 6538;
		case 6215:
			return 1550;
		case 6216:
		case 6217:
			return 1581;
		case 6218:
			return 4301;
		case 6258:
			return 2561;
		case 10775:
			return 13154;
		case 113:
			return 129;
		case 114:
			return 360;
		case 3058:
			return 2937;
		case 3061:
			return 2961;
		case 3063:
			return 2937;
		case 3065:
			return 2720;
		case 3066:
			return 2926;
		case 1265:
			return 1313;
		case 118:
			return 100;
		case 2263:
			return 2181;
		case 82:
		case 84:
		case 752:
		case 3064:
		case 83: // lesser
			return 65;
		case 3347:
		case 3346:
			return 3325;
		case 1192:
			return 1244;
		case 3062:
			return 2953;
		case 3060:
			return 2964;
		case 2892: // Spinolyp
		case 2894: // Spinolyp
		case 2896: // Spinolyp
			return 2869;
		case 1624:
			return 1555;
		case 3200:
			return 5444;
		case 1354:
		case 1341:
		case 2455:// dagannoth
		case 2454:
		case 2456:
			return 1340;
		case 127:
			return 186;
		case 119:
			return 100;
		case 2881: // supreme
		case 2882: // prime
		case 2883: // rex
			return 2852;
		case 3452:// penance queen
			return 5413;
		case 2745:
			return 2653;
		case 2743:
			return 2645;
		case 1590:// metal dragon
		case 1591:
		case 1592:
			return 14249;
		case 2598:
		case 2599:
		case 2600:
		case 2610:
		case 2601:
		case 2602:
		case 2603:
		case 2606:// tzhaar-xil
		case 2591:
		case 2604:// tzhar-hur
			return 2606;
		case 66:
		case 67:
		case 168:
		case 169:
		case 162:
		case 68:// gnomes
			return 193;
		case 160:
		case 161:
			return 194;
		case 163:
		case 164:
			return 193;
		case 438:
		case 439:
		case 440:
		case 441:
		case 442: // Tree spirit
		case 443:
			return 95;
		case 391:
		case 392:
		case 393:
		case 394:
		case 395:// river troll
		case 396:
			return 285;
		case 9462:
		case 9464:
		case 9466:
			return 12792;
		case 413:
		case 414:
		case 415:
		case 416:
		case 417:// rock golem
		case 418:
			return 154;

		case 1153:
		case 1154:
		case 1155:
		case 1156:
		case 1157:
		case 1158: // kalphite
			return 1186;
		case 1160: // kalphite
			return 1179;
		case 2734:
		case 2627:// tzhaar
			return 2622;
		case 2630:
		case 2629:
		case 2736:
		case 2738:
			return 2626;
		case 2631:
		case 2632:
			return 2629;
		case 2741:
			return 2635;

		case 908:
			return 129;
		case 909:
			return 147;
		case 911:
			return 65;

		case 1633: // pyrefiend
		case 3406:
			return 1581;

		case 1612:// banshee
			return 1525;

		case 1648:
		case 1649:
		case 1650:
		case 1651:
		case 1652:
		case 1653:
		case 1654:
		case 1655:
		case 1656:
		case 1657:// crawling hand
			return 1591;

		case 1604:
		case 1605:
		case 1606:
		case 1607:// aberrant specter
			return 1509;

		case 1618:
		case 1619:// bloodveld
			return 1550;

		case 1643:
		case 1644:
		case 1645:
		case 1646:
		case 1647:// infernal mage
			return 430;

		case 1613:// nechryael
			return 1529;

		case 1610:
		case 1611:// gargoyle
			return 1519;

		case 1615:// abyssal demon
			return 1537;

		case 1770:
		case 1771:
		case 1772:
		case 1773:
		case 2678:
		case 2679:
		case 1774:
		case 1775:
		case 1776:// goblins
			return 312;

		case 132: // monkey
			return 221;

		case 1030:
		case 1031:
		case 1032:
		case 1033:
		case 1034:
		case 1035: // wolfman
			return 6538;

		case 1456:// monkey archer
			return 1395;

		case 108:// scorpion
		case 1477:
			return 247;
		case 107:
		case 144:
			return 6255;

		case 1125:// dad
			return 285;

		case 1096:
		case 1097:
		case 1098:
		case 1942:
		case 1101:// troll
		case 1106:
			return 285;
		case 1095:
			return 285;

		case 123:
		case 122:// hobgoblin
			return 165;

		case 49:// hellhound
		case 142:
		case 95:
			return 6578;

		case 1593:
		case 152:
		case 45:
		case 1558: // wolf
		case 1954:
			return 76;

		case 89:
			return 6375;
		case 133: // unicorns
			return 290;

		case 105:// bear
			return 4921;

		case 74:
		case 75:
		case 76:
			return 5574;

		case 73:
		case 751: // zombie
		case 77:
			return 300;

		case 60:
		case 64:
		case 59:
		case 61:
		case 63:
		case 134:
		case 2035: // spider
		case 62:
		case 1009:
			return 147;

		case 103:
		case 749:
		case 104:
		case 655:
			return 124;

		case 1585:
		case 110: // giant
			return 4671;
		case 111:
			return 4671;
		case 117:
		case 116:
		case 112:
			return 4651;

		case 50: // kbd
			return 89;

		case 55:// Blue Dragon
		case 53:// Red Dragon
		case 54:// Black Dragon
		case 941:// Green Dragon
			return 12251;

		case 742:
		case 1589:
		case 52:
			return 89;
		case 2889:
			return 2860;
		case 81: // cow
		case 397:
			return 5850;

		case 708: // imp
			return 170;

		case 86:
		case 87:
			return 139;
		case 47:// rat
			return 2706;
		case 2457:
			return 2366;
		case 128: // snake
		case 1479:
			return 276;

		case 1017:
		case 2693:
		case 41: // chicken
			return 55;

		case 90:
		case 91:
		case 92:
		case 93: // skeleton
			return 261;
		case 1:
			return 424;
		default:
			return -1;
		}
	}

}
