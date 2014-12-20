package game.entity.npc.animation;

import engine.util.Misc;
import game.entity.npc.NPCHandler;

public class AttackAnimation extends NPCHandler {

	public static int handleEmote(int i) {
		if (npcs[i].npcType >= 3732 && npcs[i].npcType <= 3741) {
			return 3901;
		}
		if (npcs[i].npcType >= 3742 && npcs[i].npcType <= 3746) {
			return 3915;
		}
		if (npcs[i].npcType >= 3747 && npcs[i].npcType <= 3751) {
			return 3908;
		}
		if (npcs[i].npcType >= 3752 && npcs[i].npcType <= 3761) {
			return 3880;
		}
		if (npcs[i].npcType >= 3762 && npcs[i].npcType <= 3771) {
			return 3920;
		}
		if (npcs[i].npcType >= 3772 && npcs[i].npcType <= 3776) {
			return 3896;
		}
		switch (NPCHandler.npcs[i].npcType) {

		case 1457: //Monkey archer
			return 1394;
		case 1459: //Monkey guard
			return 1402;
		
		case 1913: //Kamil
			if (npcs[i].attackType == 2)
				return 1979;
			if (npcs[i].attackType == 0)
				return 390;
		case 1914: //Dessous
			return 1979;
		case 1977: //Fareed
			if (npcs[i].attackType == 2)
				return 1979;
			if (npcs[i].attackType == 0)
				return 390;
		case 1974: //Damis
		case 1975:
			return 407;

		case 181: //Chaos Druids
			if (npcs[i].attackType == 2)
				return 717;
			if (npcs[i].attackType == 0)
				return 422;

		case 10837:
		case 10838:
		case 10840:
			return 13398;
		
		case 3373://Max
			if (npcs[i].attackType == 4)
				return 1062;
			else
				return 422;
		
		case 5529:
			return 5782;
		case 78:
			return 4915;
			
		case 491:
			return 5540;
		
		case 10707:
			return 4652;
		
		case 2455: //dagonath
		case 1341:
		case 2456:
			return 1341;
			
		case 1961:
			return 5549;
		
		case 1874: //snake
			return 275;

		case 1618:
			return 9132;

		case 1374:
			return 15477;
		case 13822:
			return 3214;
		case 14301: // glacor
			return 10902;
		case 1379: // cliff
			return 1932;
		case 1381:// gano beast
			return 15466;
		case 1380: // pest queen
			return 14792;

			/**
			 * Dungeoneering
			 */

		case 5993:// Experiment No.2
			return 6513;

		case 13447:
			if (npcs[i].nexStage == 1 || npcs[i].nexStage == 2) {
				switch (npcs[i].glod) {
				case 1:
					return 6987;
				case 2:
					return 6986;
				}
			} else if (npcs[i].nexStage == 3 || npcs[i].nexStage == 4) {
				switch (npcs[i].glod) {
				case 1:
					return 6987;
				case 2:
					return 6355;
				case 3:
					return 6984;
				}
			} else if (npcs[i].nexStage == 5 || npcs[i].nexStage == 6) {
				switch (npcs[i].glod) {
				case 1:
				case 2:
					return 6987;
				case 3:
					return 6948;
				}
			} else if (npcs[i].nexStage == 7 || npcs[i].nexStage == 8) {
				switch (npcs[i].glod) {
				case 1:
				case 2:
				case 3:
					return 6987;
				}
			} else if (npcs[i].nexStage == 9) {
				switch (npcs[i].glod) {
				case 1:
					return 6987;
				}
			}
			return 6354;

		case 6212:// Werewolf
		case 6213:// Werewolf
			return 6536;

		case 13479:// Revenant Dark Beast
			return 7467;

		case 6271:// Ork
		case 6272:// Ork
		case 6273:// Ork
		case 6274:// Ork
			return 13891;

		case 6285:// Warped Terrorbird
		case 6293:// Warped Terrorbird
			return 7108;

		case 6296:// Warped Tortoise
		case 6297:// Warped Tortoise
			return 7093;

		case 7343: //Steel titan
			return 8183;

		case 5229:// Penance ranger
		case 5230:// Penance ranger
		case 5231:// Penance ranger
		case 5232:// Penance ranger
		case 5233:// Penance ranger
		case 5234:// Penance ranger
		case 5235:// Penance ranger
		case 5236:// Penance ranger
		case 5237:// Penance ranger
			return 5396;

		case 5247:// Penance Queen
			return 5411;

		case 75:// Zombie
		case 6763:// Dried Zombie
			return 5578;

		case 5248:// Queen Spawn
			return 5092;

		case 5452:// Icelord
		case 5453:// Icelord
		case 5454:// Icelord
		case 5455:// Icelord
			return 5725;

		case 5627:// Sorebones
		case 5628:// Sorebones
			return 5647;

		case 5691:// Undead Lumberjack
		case 5699:// Undead Lumberjack
		case 5707:// Undead Lumberjack
		case 5715:// Undead Lumberjack
		case 5723:// Undead Lumberjack
		case 5731:// Undead Lumberjack
		case 5739:// Undead Lumberjack
		case 5747:// Undead Lumberjack
			return 5970;

		case 5750:// Cave Bug
			return 6079;

		case 5906:// A doubt
			return 6310;

		case 3066:// Zombie Champion
			return 5581;

		case 3313:// Tanglefoot
			return 3262;

		case 4397:// Catablepon
		case 4398:// Catablepon
		case 4399:// Catablepon
			return 4273;

		case 4418:// Gorak
		case 6218:// Gorak
			return 4300;

		case 4463:// Vampire Juvenate
		case 4464:// Vampire Juvenate
		case 4465:// Vampire Juvenate
			return 7183;

		case 4527:// Suqah
			return 4387;

		case 4893:// Giant Lobster
			return 6261;

		case 4971:// Baby Roc
			return 5031;

		case 4972:// Giant Roc
			return 5024;

		case 5176:// Ogre Shaman
		case 5181:// Ogre Shaman
		case 5184:// Ogre Shaman
		case 5187:// Ogre Shaman
		case 5190:// Ogre Shaman
		case 5193:// Ogre Shaman
			return 359;

		case 5214:// Penance Fighter
		case 5215:// Penance Fighter
		case 5216:// Penance Fighter
		case 5217:// Penance Fighter
		case 5218:// Penance Fighter
		case 5219:// Penance Fighter
			return 5097;

		case 1831:// Cave Slime
			return 1793;

		case 907:// Kolodion
		case 910:// Kolodion
		case 2497:// Tribesman
			return 729;
			
		case 2629:
			return 9233;

		/*case 8133:
			switch (npcs[i].glod) {
			case 1:
				return 10058;
			case 2:
			case 3:
			case 4:
				return 10053;
			}
			return 10057;*/

		case 1676:// Experiment
			return 1626;

		case 10100:// Bulwark Beast
			return 13001;

		case 1677:// Experiment
			return 1616;

		case 1678:// Experiment
			return 1612;

		case 2361:// Elf Warrior
		case 2362:// Elf Warrior
		case 1183:// Elf Warrior
			return 426;

		case 1605:// Abberant Spectre
			return 1507;

		case 1612:// Banshee
			return 9449;

		case 1620:// Cockatrice
		case 1621:// Cockatrice
			return 1562;

		case 3835:// Kalphite Queen
			if (npcs[i].attackType == 0)
				return 6241;
			else
				return 6240;
		

		case 2881:// Dagannoth Supreme
			return 2855;

		case 2882:// Dagannoth Prime
			return 2854;

		case 2883:// Dagannoth Rex
			return 2853;

		case 3200:// Chaos Elemental
			return 5443;

		case 6261:// Sergeant Strongstack
		case 6263:// Sergeant Steelwill
		case 6265:// Sergeant Grimspike
			return 6154;

		case 6222:// Kree'arra
			return 6976;
			

		case 6225:// Flockleader Geerin
			return 6953;

		case 6223:// Wingman Skree
			return 6952;

		case 6227:// Flight Kilisa
			return 6954;

		case 6247:// Commander Zilyana
			return 6964;

		case 6248:// Starlight
			return 6376;

		case 6250:// Growler
			return 7018;

		case 6252:// Bree
			return 7009;

		case 8281:// Ballance Elemental
			return 10680;

		case 8282:// Ballance Elemental
			return 10669;

		case 8283:// Ballance Elemental
			return 10681;

		case 8597:// Avatar Of Creation
		case 9437:// Decaying Avatar
			return 11202;

		case 8596:// Avatar Of Destruction
			return 11197;

		case 3497:// Gelatinnoth Mother
		case 3498:// Gelatinnoth Mother
		case 3499:// Gelatinnoth Mother
		case 3500:// Gelatinnoth Mother
		case 3501:// Gelatinnoth Mother
		case 3502:// Gelatinnoth Mother
			return 1341;

		case 10126:// Unholy Cursebearer
			return 13169;

		case 6604:// Revenant Imp
			return 7407;

		case 6605:// Revenant Goblin
			return 7449;

		case 6606:// Revenant Icefiend
			return 7397;

		case 6615:// Revenant Ork
			return 7411;

		case 13480:// Revenant Knight
			return 7441;

		case 6623:// Revenant Vampire
			return 7441;

		case 6622:// Revenant Icefiend
		case 6621:// Revenant Pyrefiend
			return 7481;

		case 6645:// Revenant Cyclops
			return 7453;

		case 6688:// Revenant Hellhound
			return 7460;

		case 6647:// Revenant Demon
			return 7474;

		case 6998:// Revenant Dragon
			return 8589;

		case 8528:// Nomad
			return 12697;

		case 51:// Frost Dragon
			return 13155;

		case 1158:// Kalphite Queen
			return 6231;

		case 50:// King Black Dragon
			return 81;

		case 7133:// Bork
			return 8754;

		case 5666:// Barrelchest
			if (npcs[i].attackType == 0)
				return 5894;
			else
				return 5895;

		case 3847:// Sea Troll Queen
			return 3992;

		case 3340:// Giant Mole
			if (npcs[i].attackType == 7)
				return 3311;
			else if (npcs[i].attackType == 0) // melee
				return 3312;

		case 8349:// Tormented Demon
		case 8350:
		case 8351:
			if (npcs[i].attackType == 2) // mage
				return 10918;
			else if (npcs[i].attackType == 1)// range
				return 10919;
			else if (npcs[i].attackType == 0)// melee
				return 10922;

		case 8133:// Corpreal Beast
			if (npcs[i].attackType == 2) // mage
				return 10066;
			else if (npcs[i].attackType == 1) // range
				return 10053;
			else if (npcs[i].attackType == 0) // melee
				return 10057; // melee
			else
				return 10058;

			// Summoning
		case 7342:// Lava Titan
		case 7340:// Geyser Titan
			return 7879;

		case 7344:// Steel Titan
			return 8190;

		case 7346:// Obsidian Golem
			return 8048;

		case 7348:// Talon Beast
			return 5989;

		case 7350:// Abyssal Titan
			return 7693;

		case 6795:// Spirit Terrorbird
			return 1010;

		case 7336:// Forge Regent
			return 7871;

		case 7354:// Giant Chinchompa
			return 7755;

		case 7355:// Fire Titan
			return 7834;

		case 7358:// Moss Titan
			return 7844;

		case 7359:// Ice Titan
			return 8183;

		case 7362:// Spirit Tz-Kih
			return 8257;

		case 7364:// Spirit Graahk
		case 7366:// Spirit Kyatt
			return 5228;

		case 7374:// Ravenous Locust
			return 7994;

		case 7376:// Iron Titan
			return 7946;

		case 7330:// Swamp Titan
			return 8223;

		case 7332:// Spirit Mosquito
			return 8032;

		case 7338:// Spirit Larupia
			return 5228;

		case 6797:// Granite Crab
			return 8104;

		case 6799:// Praying Mantis
			return 8069;

		case 6801:// Giant Ent
			return 7853;

		case 6803:// Spirit Cobra
			return 8159;

		case 6805:// Spirit Dagannoth
			return 7786;

		case 6807:// Thorny Snail
			return 8148;

		case 6810:// Kuramthulu Overlord
			return 7970;

		case 6812:// Hydra
			return 7935;

		case 6814:// Bunyip
			return 7741;

		case 6816:// War Tortoise
			return 8288;

		case 6819:// Abyssal Parasite
			return 7667;

		case 6821:// Abyssal Lurker
			return 7680;

		case 6823:// Unicorn Stallion
			return 6376;

		case 6826:// Dreadfowl
			return 5387;

		case 6828:// Stranger Plant
			return 8208;

		case 6830:// Spirit Wolf
			return 8292;

		case 6832:// Desert Wyrm
			return 7795;

		case 6834:// Evil Turnip
			return 8248;

		case 6836:// Vampire Bat
			return 8275;

		case 6838:// Spirit Scorpion
			return 6254;

		case 6856:// Iron Minotaur
			return 4921;

		case 6858:// Steel Minotaur
			return 5327;

		case 6860:// Mithril Minotaur
		case 6862:// Adamant Minotaur
		case 6864:// Rune Minotaur
			return 7656;

		case 6868:// Bull Ant
			return 7896;

		case 6870:// Wolpertinger
			return 8303;

		case 6872:// Compost Mound
			return 7769;

		case 6874:// Pack Yak
			return 5782;

		case 6890:// Barker Toad
			return 7260;

			// Minigames
		case 2627:// Tz-Kih
			return 9232;

		case 2630:// Tz-Kek
			return 9233;

		case 2631:// Tok-Xil
			return 9245;

		case 2741:// Yt-MejKot
			return 9252;

		case 2746:// Yt-Hurkot
			return 9252;

		case 2607:// Tzhaar-Xil
			return 9286;

		case 2743:// Ket-Zek
			return 9267;

		case 7368:// Void Shifter
		case 7369:// Void Shifter
			return 8130;

		case 7371:// Void Ravager
			return 8093;

		case 7352:// Void Torcher
			return 8234;

		case 7334:// Void Spinner
			return 8172;

		case 2028:// Karil
			return 2075;

		case 2025:// Ahrim
			return 729;

		case 2026:// Dharok
			return 2067;

		case 2027:// Guthan
			return 2080;

		case 2029:// Torag
			return 0x814;

		case 2030:// Verac
			return 2062;

		case 5228:// Penance Runner
			return 5228;

		case 2031:// Bloodworm
			return 2070;

			// Training & Misc
		case 1640:// Jelly
			return 8575;

		case 8321:// Elite Dark Mage
			return 10516;

		case 1250:// Fiyr Shade
			return 1284;

		case 9172:// Aquanite
			return 12042;

		case 10815:// New Red Dragon
		case 10607:// New Green Dragon
		case 10224:// New Black Dragon
			return 13151;

		case 8777:// Chaos Dwarf Hancannoeer
			return 12141;

		case 8324:// Elite Black Knight
			return 13053;

		case 7797:// Kurask Overlord
			return 9439;

		case 6753:// Mummy
			return 5554;

		case 5250:// Scarab Mage
			return 7621;

		case 7808:// Mummy Warrior
			return 5554;

		case 7135:// Ork Legion
			return 8760;

		case 2892:// Spinolyp
		case 2894:// Spinolyp
			return 2868;

		case 123:// Hobgoblin
		case 122:// Hobgoblin
			return 164;

		case 2037:// Skeleton
			return 5485;

		case 2457:// Wallaski
			return 2365;

		case 6270:// Cyclops
		case 6269:// Ice cyclops
		case 4291:// Cyclops
		case 4292:// Ice cyclops
			return 4652;

		case 6219:// Spiritual Warrior
		case 6255:// Spiritual Warrior
			return 14740;

		case 13:// Wizard
			return 711;

		case 103:// Ghost
		case 655:// Tree Spirit
			return 123;

		case 1624:// Dust Devil
			return 1557;

		case 1648:// Crawling Hand
			return 9125;

		case 2783:// Dark Beast
			return 2731;

		case 1615:// Abyssal Demon
			return 1537;

		case 1613:// Nechryael
			return 9487;

		case 1610:// Gargoyle
		case 1611:// Gargoyle
			return 9454;

		case 1643:// Infernal Mage
			return 7195;

		case 1616:// Basilisk
			return 1546;

		case 90:// Skeleton
			return 5485;


		case 55:// Blue Dragon
		case 53:// Red Dragon
		case 54:// Black Dragon
		case 941:// Green Dragon
			return 12259;

		case 1590:// Bronze Dragon
		case 1591:// Iron Dragon
		case 1592:// Steel Dragon
		case 5363:// Mithril Dragon
			return 14246;

		case 124:// Earth Warrior
			return 390;

		case 803:// Monk
			return 422;

		case 52:// Baby Blue Dragon
		case 1589:// Baby Red Dragon
			return 14270;

		case 58:// Shadow Spider
		case 59:// Giant Spider
		case 60:// Giant Spider
		case 61:// Spider
		case 62:// Jungle Spider
		case 63:// Deadly Red Spider
		case 64:// Ice Spider
		case 134:// Poison Spider
			return 143;

		case 105:// Bear
		case 106:// Bear
			return 41;

		case 412:// Bat
			return 30;

		case 2033:// Giant Rat
			return 138;

		case 102:// Goblin
		case 100:// Goblin
		case 101:// Goblin
			return 6184;

		case 81:// Cow
			return 0x03B;

		case 41:// Chicken
			return 55;

		case 19:// White Knight
		case 21:// Hero //TODO
		case 125:// Ice Warrior
		case 9:// Guard
		case 32:// Guard
		case 20:// Paladin
		case 4278:
		case 4279:
		case 4280:
		case 4281:
		case 4282:
		case 4284:
			return 15071;

		case 1338:// Dagannoth
		case 1340:// Dagannoth
		case 1342:// Dagannoth
			return 1341;

		case 110:// Fire Giant
		case 111:// Ice Giant
		case 112:// Moss Giant
		case 117:// Hill Giant
			return 4652;

		case 2452:// Giant Rock Crab
			return 1312;

		case 2889:// Rock Lobster
			return 2859;

		case 118:// Dwarf
		case 119:// Chaos Dwarf
			return 99;

		case 82:// Lesser Demon
		case 83:// Greater Demon
		case 84:// Black Demon
		case 1472:// Jungle Demon
			return 64;

		case 1267:// Rock Crab
		case 1265:// Rock Crab
			return 1312;

		case 1153:// Kalphite Worker
		case 1154:// Kalphite Soldier
		case 1155:// Kalphite Guardian
		case 1156:// Kalphite Worker
		case 1157:// Kalphite Guardian
			return 1184;

		case 6142:
		case 6143:
		case 6144:
		case 6145:
		case 6150:
		case 6151:
		case 6152:
		case 6153:
			return -1;

		case 2035:
			return 8165;

		case 9727:
			return 13425;

		case 10218:
		case 10212:
			return 8080;

		case 10756:
			return 4666;

		case 10462:
		case 10465:
			return 11639;

		case 10699:
			return 8569;

		case 10167:
		case 10506:
		case 10698:
			return 5327;

		case 9916:
			return 13337;

		case 10802:
		case 10801:
			return 13061;

			/**
			 * End of Dungeoneering
			 */

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
			return 13717;

		case 6260:
			if (npcs[i].attackType == 0)
				return 7060;
			else
				return 7063;
		case 3495:
			if (npcs[i].attackType == 0)
				return 2075;
			else
				return 1979;
			
		case 7693:
			int attackStyle1 = Misc.random(5);
			if (attackStyle1 > 0 && attackStyle1 < 3)
				return 4685;
				else if (attackStyle1 > 3 && attackStyle1 < 6)
					return 4687;
					

		case 9462:
		case 9466:
		case 9464:
			if (npcs[i].attackType == 0)
				return 12791;
				else if (npcs[i].attackType == 2)
					return 12794;
				else if (npcs[i].attackType == 1)
					return 12796;
			

		/*case 9467:
		case 9466:
			if (npcs[i].attackType == 0)
				return 12791;
				else if (npcs[i].attackType == 1)
					return 12794;

		case 9465:
			if (npcs[i].attackType == 0)
			return 12791;
			else if (npcs[i].attackType == 1)
				return 12794;*/
				

			/**
			 * Familiars Attack Emote
			 **/

		case 6825:
			return 7810;
		case 6806:
			return 8143;
		case 6841:
			return 8165;
		case 6796:
			return 8107;
		case 7331:
			return 8032;
		case 6831:
			return 7795;
		case 7361:
			return 8257;
		case 6847:
			return 7905;
		case 6837:
			return 6261;
		case 6994:
			return 6223;
		case 6871:
			return 7769;
		case 7353:
			return 7755;
		case 6835:
			return 8275;
		case 6845:
			return 7928;
		case 7370:
			return 8086;
		case 7367:
			return 8131;
		case 7351:
			return 8235;
		case 7333:
			return 8172;
		case 6853:
		case 6855:
		case 6857:
		case 6859:
		case 6861:
		case 6863:
			return 8024;
		case 6867:
			return 7896;
		case 6851:
			return 8010;
		case 6833:
			return 8248;
		case 7377:
			return 8080;
		case 6824:
			return 8010;
		case 6843:
			return 7657;
		case 6818:
			return 7672;
		case 6992:
			return 8569;
		case 6991:
			return 8200;
		case 6875:
			// the cocks
		case 6877:
		case 6879:
		case 6881:
		case 6883:
		case 6885:
		case 6887:
			return 7762;
		case 7363:
		case 7365:
		case 7337:
			return 7913;
		case 6809:
			return 7970;
		case 6865:
			return 7816;
		case 6820:
			return 7680;
		case 6802:
			return 8152;
		case 6827:
			return 8211;
		case 6889:
			return 7700;
		case 6815:
			return 8286;
		case 7372:
			return 7994;
		case 6839:
			return 8524;
		case 8575:
			return 11093;
		case 7345:
			return 8050;
		case 6849:
			return 8112;
		case 6798:
			return 8064;
		case 7335:
			return 7866;
		case 7347:
			return 5989;
		case 6800:
			return 7853;
		case 6811:
			return 7935;
		case 6804:
			return 7786;
		case 7341:
			return 7980;
		case 7329:
			return 8222;
		case 7375:
			return 7844;

		case 6873:
			return 5782;
		case 3101:
			// Melee
			return 10058;
		case 3102:
			// Range
		case 3103:
			// Mage
			return 10057;
			
		case 93:
			return 5499;
		case 4283:
			return 7048;
			// GODWARS
		case 3247:
			// Hobgoblin
			return 6184;

		case 6229:
			// Spirtual Warrior arma
			return 6953;

		case 49:// hell hound
			return 6562;

		case 6220:
			// Spirtual Ranger
		case 6256:
			// Spirtual Ranger
			return 426;

		case 6257:
			// Spirtual Mage
		case 6221:
		case 6367:
		case 6368:
		case 188:
			// Spirtual Mage
			return 811;

		case 6276:
			// Spirtual Ranger
		case 6278:
		case 7138:
			// Spirtual Mage
			return 4320;

		case 6230:
			// Spirtual Ranger
		case 6233:
			// Aviansie
		case 6239:
			// Aviansie
		case 6232:
			// Aviansie
			return 6953;

		case 6254:
			// Saradomin Priest
			return 440;
		case 6258:
			// Saradomin Knight
			return 7048;
		case 6231:
			// Spirtual Mage
			return 6952;

		case 3248:
			// HellHound
			return 6579;
			// ENDS
		case 6204:
			return 64;
		case 6208:
			return 6947;
		case 6206:
			return 6945;
		case 3494:
			return 1750;
		case 3491:
			return 1979;
		case 3496:
			if (npcs[i].attackType == 0)
				return 3508;
			else
				return 3507;
		case 6203:
			if (npcs[i].attackType == 0)
				return 6945;
			else
				return 6947; // end
		case 3493:
			if (npcs[i].attackType == 0)
				return 3501;
			else
				return 3502; // end
			// bandos gwd
			// end of gwd
			// arma gwd

		case 6822:
			return 6376;
		case 7339:
			return 7879;
		case 6869:
			return 8303;
		case 7349:
			return 7694;

		case 86:
		case 10480:
			return 4933;

		case 2745:
			if (npcs[i].attackType == 2)
				return 9300;
			else if (npcs[i].attackType == 1)
				return 9276;
			else if (npcs[i].attackType == 0)
				return 9277;

		default:
			return 433;
		}
	}

}
