package game.entity.npc.animation;

import game.entity.npc.NPCHandler;

public class DeathAnimation extends NPCHandler {

	public static int handleEmote(int i) {
		if (npcs[i].npcType >= 3732 && npcs[i].npcType <= 3741) {
			return 3903;
		}
		if (npcs[i].npcType >= 3742 && npcs[i].npcType <= 3746) {
			return 3917;
		}
		if (npcs[i].npcType >= 3747 && npcs[i].npcType <= 3751) {
			return 3909;
		}
		if (npcs[i].npcType >= 3752 && npcs[i].npcType <= 3761) {
			return 3881;
		}
		if (npcs[i].npcType >= 3762 && npcs[i].npcType <= 3771) {
			return 3922;
		}
		if (npcs[i].npcType >= 3772 && npcs[i].npcType <= 3776) {
			return 3894;
		}
		switch (NPCHandler.npcs[i].npcType) {
		case 1457: //Monkey archer
			return 1384;
		case 1459: //Monkey guard
			return 1404;

		case 491:
			return 5542;
		case 78:
			return 4917;

		case 10837:
		case 10838:
		case 10840:
			return 13407;
		case 1961: //Mummy
			return 5555;
		case 4291:
			return 4653;
		case 5529:
			return 5784;
		case 7693:
			return 4691;
		case 2629:
			return 9234;
		
		case 14301:
			return -1;
		
		case 2455: //dagonath
		case 1341:
		case 2456:
			return 1342;
		
		case 1874: //snake
			return 278;
			/*
			 * strike wyrms
			 */
		case 9463:
		case 9465:
		case 9467:
			return 12792;

		case 6229:// aivansie liek npcs
		case 6232:
		case 6233:
		case 6239:
			return 6956;

		case 49:
			return 6564;

		case 1374:
			return 15480;

		case 13822: // jadinko male
			return 3232;

		case 1380: // pest queen
			return 14795;

		case 1379: // cliff
			return 1934;

		case 1381: // gano beast
			return 15473;

		case 8133:
			return 10385;
		case 13447:
			return 6951;

		case 5993:// Experiment No.2
			return 6512;

		case 6212:// Werewolf
		case 6213:// Werewolf
			return 6537;

		case 6271:// Ork
		case 6272:// Ork
		case 6273:// Ork
		case 6274:// Ork
			return 13892;

		case 6285:// Warped Terrorbird
		case 6293:// Warped Terrorbird
			return 7109;

		case 6296:// Warped Tortoise
		case 6297:// Warped Tortoise
			return 7091;

		case 5229:// Penance ranger
		case 5230:// Penance ranger
		case 5231:// Penance ranger
		case 5232:// Penance ranger
		case 5233:// Penance ranger
		case 5234:// Penance ranger
		case 5235:// Penance ranger
		case 5236:// Penance ranger
		case 5237:// Penance ranger
			return 5397;

		case 5247:// Penance Queen
			return 5412;

		case 75:// Zombie
		case 6763:// Dried Zombie
			return 5569;

		case 5248:// Queen Spawn
			return 5093;

		case 5452:// Icelord
		case 5453:// Icelord
		case 5454:// Icelord
		case 5455:// Icelord
			return 5726;

		case 5627:// Sorebones
		case 5628:// Sorebones
			return 5649;

		case 5691:// Undead Lumberjack
		case 5699:// Undead Lumberjack
		case 5707:// Undead Lumberjack
		case 5715:// Undead Lumberjack
		case 5723:// Undead Lumberjack
		case 5731:// Undead Lumberjack
		case 5739:// Undead Lumberjack
		case 5747:// Undead Lumberjack
			return 5972;

		case 5750:// Cave Bug
			return 6081;

		case 5906:// A doubt
			return 6315;

		case 3066:// Zombie Champion
			return 5580;

		case 3313:// Tanglefoot
			return 3263;

		case 4397:// Catablepon
		case 4398:// Catablepon
		case 4399:// Catablepon
			return 4270;

		case 4418:// Gorak
		case 6218:// Gorak
			return 4302;

		case 4527:// Suqah
			return 4389;

		case 4893:// Giant Lobster
			return 6267;

		case 4971:// Baby Roc
			return 5033;

		case 4972:// Giant Roc
			return 5037;

		case 5176:// Ogre Shaman
		case 5181:// Ogre Shaman
		case 5184:// Ogre Shaman
		case 5187:// Ogre Shaman
		case 5190:// Ogre Shaman
		case 5193:// Ogre Shaman
			return 361;

		case 5214:// Penance Fighter
		case 5215:// Penance Fighter
		case 5216:// Penance Fighter
		case 5217:// Penance Fighter
		case 5218:// Penance Fighter
		case 5219:// Penance Fighter
			return 5098;

		case 1831:// Cave Slime
			return 1792;

		case 907:// Kolodion
		case 910:// Kolodion
		case 2497:// Tribesman
			return 714;

		case 1676:// Experiment
			return 1628;

		case 10100:// Bulwark Beast
			return 13005;

		case 1265:// Experiment
			return 1314;

		case 1677:// Experiment
			return 1618;

		case 9464:// Ice Strykewyrm
		case 9466:// Desert Strykewyrm
		case 9462:// Jungle Strykewyrm
			return 12793;

		case 1678:// Experiment
			return 1611;

		case 8596:// Avatar Of Destruction
			return 11199;

		case 6645:// Revenant Cyclops
			return 7454;

		case 6998:// Revenant Dragon
			return 8593;

		case 13479:// Revenant Dark Beast
			return 7468;

		case 6647:// Revenant Demon
			return 7475;

		case 6688:// Revenant Hellhound
			return 7461;

		case 6622:// Revenant Pyrefiend
		case 6621:// Revenant Icefiend
			return 7484;

		case 6623:// Revenant Vampire
			return 7428;

		case 13480:// Revenant Knight
			return 7442;

		case 6615:// Revenant Ork
			return 7416;

		case 6606:// Revenant Icefiend
			return 7397;

		case 6605:// Revenant Goblin
			return 7448;

		case 6604:// Revenant Imp
			return 7408;

		case 10126:// Unholy Cursebearer
			return 13171;

		case 7480:// Tumeken's Shadow
			return 11629;

		case 110:// Fire Giant
		case 111:// Ice Giant
		case 112:// Moss Giant
		case 117:// Hill Giant
		case 6269:
			return 4653;

		case 1250:// Fiyr Shade
			return 1285;

		case 9172:// Aquanite
			return 12039;

		case 1158:// Kalphite Queen
			return 6228;

		case 2889:// Rock Lobster
			return 2862;

		case 2457:// Wallaski
			return 2367;

		case 8281:// Ballance Elemental
		case 8282:// Ballance Elemental
		case 8283:// Ballance Elemental
			return 10679;

		case 3497:// Gelatinoth Mother
		case 3498:// Gelatinoth Mother
		case 3499:// Gelatinoth Mother
		case 3500:// Gelatinoth Mother
		case 3501:// Gelatinoth Mother
		case 3502:// Gelatinoth Mother
			return 1342;

		case 8777:// Handcannonneer
			return 12181;

		case 5250:// Scarab Mage
			return 7616;

		case 7808:// Mummy Warrior
			return 5555;

		case 6753:// Mummy
			return 5555;

		case 7797:// Kurask Overlord
			return 9440;

		case 8324:// Elite Black Knight
			return 836;

		case 10815:// New Red Dragon
		case 10607:// New Green Dragon
		case 10224:// New Black Dragon
			return 13153;

		case 8528:// Nomad
			return 12694;

		case 8597:// Avatar Of Creation
		case 9437:// Decaying Avatar
			return 11204;

		case 1160:// Kalphite Queen
			return 6233;

		case 51:// Frost Dragon
			return 13153;

		case 7133:// Bork
			return 8756;

		case 7135:// Ork Legion
			return 8761;

		case 3340:// Giant Mole
			return 3310;

		case 8321:// Elite Dark Mage
			return 2304;

		case 5666:// Barrelchest
			return 5898;

		case 6247:// Commander Zilyana
			return 6965;

		case 6248:// Starlight
			return 6377;

		case 6250:// Growler
			return 7016;

		case 6252:// Bree
			return 7011;

		/*case 8133:// Corpreal Beast
			return 10050;*/

		case 8349:// Tormented Demon
		case 8350:
		case 8351:
			return 10924;

		case 6261:// Seargent Strongstack
		case 6263:// Seargent Steelwill
		case 6265:// Seargent Grimspike
			return 6156;

		case 6260:// General Graardor
			return 7062;

		case 2892:// Spinolyp
		case 2894:// Spinolyp
			return 2865;

		case 1612:// Banshee
			return 9450;

		case 6222:// Kree'ara
			return 6975;

		case 6223:// Wingman Skree
		case 6225:// Flockleader Geerin
		case 6227:// Flight Kilisa
			return 6956;

		case 2607:// Tzhaar-Xil
			return 9288;

		case 2627:// Tz-Kih
			return 9230;

		case 2630:// Tz-Kek
			return 9234;

		case 2631:// Tok-Xil
			return 9239;

		case 2738:// Tz-Kek
			return 9233;

		case 2741:// Yt-MejKot
			return 9247;

		case 2746:// Yt-Hurkot
			return 9257;

		case 2743:// Ket-Zek
			return 9269;

		case 2745:// TzTok-Jad
			return 9279;

		case 3200:// Chaos Elemental
			return 5446;

		case 2035:// Rat
			return 146;

		case 2033:// Rat
			return 141;

		case 2031:// Bloodveld
			return 2073;

		case 102:// Goblin
		case 100:// Goblin
		case 101:// Goblin
			return 6182;

		case 81:// Cow
			return 0x03E;

		case 41:// Chicken
			return 57;

		case 1338:// Dagannoth
		case 1340:// Dagannoth
		case 1342:// Dagannoth
			return 1342;

		case 2881:// Dagannoth Supreme
		case 2882:// Dagannoth Prime
		case 2883:// Dagannoth Rex
			return 2856;

		case 125:// Ice Warrior
			return 843;

		case 751:// Berserk Barbarian Spirit
			return 302;

		case 1626:// Turoth
		case 1627:// Turoth
		case 1628:// Turoth
		case 1629:// Turoth
		case 1630:// Turoth
		case 1631:// Turoth
		case 1632:// Turoth
			return 1597;

		case 1616:// Basilisk
			return 1548;

		case 1653:// Crawling Hand
			return 1590;

		case 82:// Lesser Demon
		case 83:// Greater Demon
		case 84:// Black Demon
		case 1472:// Jungle Demon
			return 67;

		case 1605:// Abberant Spectre
			return 1508;

		case 52:// Baby Blue Dragon
		case 1589:// Baby Red Dragon
		case 3376:// Baby Black Dragon
			return 14271;

		case 1610:// Gargoyle
		case 1611:// Gargoyle
			return -1;

		case 1618:// Bloodveld
		case 1619:// Bloodveld
			return 9131;

		case 1620:// Cockatrice
		case 1621:// Cockatrice
			return 1563;

		case 2783:// Dark Beast
			return 2733;

		case 1615:// Abyssal Demon
			return 1538;

		case 1624:// Dust Devil
			return 1558;

		case 1613:// Nechryael
			return 9488;

		case 1633:// Pyrefiend
		case 1634:// Pyrefiend
		case 1635:// Pyrefiend
		case 1636:// Pyrefiend
			return 1580;

		case 1648:// Crawling Hand
		case 1649:// Crawling Hand
		case 1650:// Crawling Hand
		case 1651:// Crawling Hand
		case 1652:// Crawling Hand
		case 1654:// Crawling Hand
		case 1655:// Crawling Hand
		case 1656:// Crawling Hand
		case 1657:// Crawling Hand
			return 9125;

		case 105:// Grizly Bear
		case 106:// Black Bear
			return 44;

		case 412:// Bat
			return 36;

		case 122:// Hobgoblin
		case 123:// Hobgoblin
			return 167;

		case 58:// Shadow Spider
		case 59:// Giant Spider
		case 60:// Giant Spider
		case 61:// Spider
		case 62:// Jungle Spider
		case 63:// Deadly Red Spider
		case 64:// Ice Spider
		case 134:// Poison Spider
			return 146;

		case 1153:// Kalphite Worker
		case 1154:// Kalphite Soldier
		case 1155:// Kalphite Guardian
		case 1156:// Kalphite Worker
		case 1157:// Kalphite Guardian
			return 1190;

		case 103:// Ghost
		case 104:// Ghost
			return 123;

		case 118:// Dwarf
		case 119:// Chaos Dwarf
			return 102;

		case 55:// Blue Dragon
		case 53:// Red Dragon
		case 54:// Black Dragon
		case 941:// Green Dragon
			return 12250;

		case 1590:// Bronze Dragon
		case 1591:// Iron Dragon
		case 1592:// Steel Dragon
		case 5363:// Mithril Dragon
			return 14248;

			/**
			 * Dungeoneering
			 */

		case 6142:
		case 6143:
		case 6144:
		case 6145:
		case 6150:
		case 6151:
		case 6152:
		case 6153:
			return -1;

		case 5085:
		case 5084:
		case 5083:
		case 5082:
			return 362;

		case 1028:
		case 1029:
		case 1031:
		case 1032:
		case 1033:
		case 1034:
		case 1035:
		case 6063:
		case 6064:
			return 6613;

		case 9727:
			return 13424;

		case 10218:
		case 10212:
			return 8078;

		case 10756:
			return 4673;

		case 10699:
			return 8570;

		case 10462:
		case 10465:
			return 11642;

		case 10167:
		case 10506:
		case 10698:
			return 5329;

		case 9916:
			return 13336;

		case 10802:
		case 10801:
			return 13062;

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
		case 9958:
		case 9959:
		case 9960:
		case 9961:
		case 9962:
		case 9963:
		case 9964:
			return 13715;
			// sara gwd
		case 2562:
			return 6965;
		case 2563:
			return 6377;
		case 86:
		case 10480:
			return 4935;
		case 2564:
			return 7016;
		case 2565:
			return 7011;
			// bandos gwd
		case 2551:
		case 2552:
		case 2553:
			return 6156;
		case 2550:
			return 7062;
		case 2558:
			return 3503;
		case 2559:
		case 2560:
		case 2561:
			return 6956;

		case 10707:
			return 4653;

		case 50:
			return 92;

		default:
			return 2304;
		}
	}

}
