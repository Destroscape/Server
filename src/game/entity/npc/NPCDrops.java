package game.entity.npc;

import engine.util.Misc;
import game.skill.dungeoneering.Dungeoneering;

public class NPCDrops {

	public static final int[][] NPC_DROPS = {
		// {NPCID,ITEMID,ITEMAMMOUNT,RARITY}


		//Avatar of Destruction
		{ 8596, 15410, 1, 0 }, //Ancient Bones
		{ 8596, 4212, 1, 50 }, //Crystal Bow
		{ 8596, 2577, 1, 30 }, //Ranger Boots
		{ 8596, 2581, 1, 25 }, //Robin Hat
		{ 8596, 15126, 1, 15 }, //Amulet of ranging
		{ 8596, 21774, Misc.random(100), 8 }, //Dust of Armadyl
		{ 8596, 15017, 1, 35 }, //Onyx ring (i)
		{ 8596, 5304, Misc.random(20), 18 }, //Torstol Seed
		{ 8596, 21777, 1, 150 }, //Armadyl Battlestaff
		{ 8596, 9477, 1, 20 }, //Remote Deposit Box
		{ 8596, 19333, 1, 80 }, //Fury (or) Kit
		{ 8596, 18336, 1, 35 }, //Imbuedment Scroll
		{ 8596, 15367, 1, 10 }, //Seed Box
		{ 8596, 884, Misc.random(1000), 25 },
		{ 8596, 554, Misc.random(1200), 15 },
		{ 8596, 555, Misc.random(1300), 12 },
		{ 8596, 556, Misc.random(1500), 13 },
		{ 8596, 557, Misc.random(1500), 18 },
		{ 8596, 560, Misc.random(600), 17 },
		{ 8596, 561, Misc.random(350), 16 },
		{ 8596, 565, Misc.random(600), 17 },
		{ 8596, 6694, Misc.random(4), 5 }, //Crushed Bird's Nest
		{ 8596, 995, Misc.random(2000000), 6 }, //GP
		{ 8596, 5295, Misc.random(35), 13 }, //Ranarr Seed
		{ 8596, 15488, 1, 100 }, //Hexcrest
		{ 8596, 1516, Misc.random(300), 20 }, //Yew Logs
		{ 8596, 1514, Misc.random(120), 28 }, //Magic Logs
		{ 8596, 2360, Misc.random(10), 8 }, //Mithril Bars
		{ 8596, 15272, Misc.random(5), 11 }, //Rocktail

		//Chaos Elemental
		{ 3200, 1289, 1, 28 },
		{ 3200, 1377, 1, 30 },
		{ 3200, 7158, 1, 120 },
		{ 3200, 884, Misc.random(1000), 25 },
		{ 3200, 554, Misc.random(600), 15 },
		{ 3200, 555, Misc.random(650), 12 },
		{ 3200, 556, Misc.random(600), 13 },
		{ 3200, 557, Misc.random(600), 16 },
		{ 3200, 560, Misc.random(230), 40 },
		{ 3200, 561, Misc.random(300), 20 },
		{ 3200, 565, Misc.random(180), 45 },
		{ 3200, 987, 1, 145 },
		{ 3200, 985, 1, 150 },
		{ 1592, 536, 1, 0 }, //Dragon Bone
		
		//TODO Chaos Tunnel Npcs
		//Dagon' hai
		{ 7138, 14497, 1, 65 },
		{ 7138, 14499, 1, 60 },
		{ 7138, 14501, 1, 68 },
		{ 7138, 1333, 1, 20 },
		{ 7138, 554, Misc.random(25), 6 },
		{ 7138, 555, Misc.random(25), 7 },
		{ 7138, 556, Misc.random(25), 8 },
		{ 7138, 557, Misc.random(25), 10 },
		{ 7138, 558, Misc.random(25), 11 },
		{ 7138, 559, Misc.random(25), 12 },
		{ 7138, 560, Misc.random(25), 9 },
		{ 7138, 561, Misc.random(25), 8 },
		{ 7138, 562, Misc.random(25), 11 },
		{ 7138, 563, Misc.random(25), 6 },
		{ 7138, 564, Misc.random(25), 9 },
		{ 7138, 565, Misc.random(25), 14 },
		{ 7138, 995, Misc.random(15000), 10 },
		
		//TODO Bork
		
		//TODO Mage Arena Npcs
		
		//Steel Dragons
		{ 1592, 1149, 1, 65 }, //Dragon Med
		{ 1592, 4585, 1, 90 }, //Dragon Plateskirt
		{ 1592, 4087, 1, 105 }, //Dragon Platelegs
		{ 1592, 536, 1, 0 }, //Dragon Bone
		{ 1592, 2353, 5, 0 }, //Steel Bar
		{ 1592, 2366, 1, 60 }, //Dragon Sq. Left Half
		{ 1592, 2368, 1, 55 }, //Dragon Sq. Right Half
		{ 1592, 11286, 1, 131 }, //Draconic Visage
		{ 1592, 1317, 1, 15 }, //Adamant 2h
		{ 1592, 1371, 1, 16 }, //Adamant Battleaxe
		{ 1592, 1347, 1, 14 }, //Adamant Hatchet
		{ 1592, 1359, 1, 20 }, //Rune Hatchet
		{ 1592, 1432, 1, 19 }, //Rune Mace
		{ 1592, 1303, 1, 23 }, //Rune Longsword
		{ 1592, 1373, 1, 25 }, //Rune Battleaxe
		{ 1592, 1319, 1, 17 }, //Rune 2h
		{ 1592, 1183, 1, 30 }, //Adamant Sq Shield
		{ 1592, 1147, 1, 28 }, //Rune Med Helm
		{ 1592, 1163, 1, 26 }, //Rune Full Helm
		{ 1592, 1516, Misc.random(75), 35 }, //Yew Logs
		{ 1592, 1185, 1, 32 }, //Rune Sq Shield
		{ 1592, 1201, 1, 35 }, //Rune Kiteshield
		{ 1592, 811, Misc.random(20), 22 }, //Rune Dart
		{ 1592, 995, Misc.random(150000), 12 }, //GP
		{ 1592, 145, 1, 10 }, //Super Attack (3)
		{ 1592, 2363, 1, 36 }, //Rune Bar
		{ 1592, 161, 1, 13 }, //Super Strength (1)
		{ 1592, 868, Misc.random(40), 17 }, //Rune Knives
		{ 1592, 565, Misc.random(100), 20 }, //Blood Runes
		{ 1592, 9144, Misc.random(100), 26 }, //Runite Bolts
		{ 1592, 566, Misc.random(65), 31 }, //Soul Rune
		{ 1592, 561, Misc.random(100), 28 }, //Nature Rune
		{ 1592, 830, Misc.random(50), 27 }, //Rune Javelins
		{ 1592, 886, Misc.random(125), 50 }, //Steel Arrows
		{ 1592, 563, 50, 37 }, //Law Rune
		{ 1592, 562, 90, 36 }, //Chaos Rune
		{ 1592, 560, Misc.random(100), 33 }, //Death Rune
		{ 1592, 2361, 2, 65 }, //Adamant Bar
		{ 1592, 9431, 1, 30 }, //Runite Limb

		//Bronze dragons
		{ 1590, 1149, 1, 75 }, //Dragon Med
		{ 1590, 4585, 1, 105 }, //Dragon Plateskirt
		{ 1590, 4087, 1, 125 }, //Dragon Platelegs
		{ 1590, 536, 1, 0 }, //Dragon Bone
		{ 1590, 2349, 5, 0 }, //Bronze Bars
		{ 1590, 2366, 1, 40 }, //Dragon Sq. Left Half
		{ 1590, 2368, 1, 70 }, //Dragon Sq. Right Half
		{ 1590, 11286, 1, 165 }, //Draconic Visage
		{ 1590, 9431, 1, 20 }, //Runite Limbs
		{ 1590, 1213, 1, 15 }, //Rune Dagger
		{ 1590, 1359, 1, 16 }, //Rune Hatchet
		{ 1590, 1432, 1, 14 }, //Rune Mace
		{ 1590, 1199, 1, 7 }, //Adamant Kite
		{ 1590, 1163, 1, 18 }, //Rune Full Helm
		{ 1590, 811, Misc.random(20), 22 }, //Rune Dart
		{ 1590, 995, Misc.random(95000), 12 }, //GP
		{ 1590, 145, 1, 10 }, //Super Attack (3)
		{ 1590, 2363, 1, 36 }, //Rune Bar
		{ 1590, 161, 1, 13 }, //Super Strength (1)
		{ 1590, 868, Misc.random(20), 17 }, //Rune Knives
		{ 1590, 565, Misc.random(80), 20 }, //Blood Runes
		{ 1590, 9144, Misc.random(100), 26 }, //Runite Bolts
		{ 1590, 566, Misc.random(30), 31 }, //Soul Rune
		{ 1590, 561, Misc.random(50), 28 }, //Nature Rune
		{ 1590, 830, Misc.random(30), 27 }, //Rune Javelins
		
		//Iron Dragons
		{ 1591, 1149, 1, 75 }, //Dragon Med
		{ 1591, 4585, 1, 105 }, //Dragon Plateskirt
		{ 1591, 4087, 1, 125 }, //Dragon Platelegs
		{ 1591, 536, 1, 0 }, //Dragon Bone
		{ 1591, 2351, 5, 0 }, //Iron Bars
		{ 1591, 2366, 1, 40 }, //Dragon Sq. Left Half
		{ 1591, 2368, 1, 70 }, //Dragon Sq. Right Half
		{ 1591, 11286, 1, 155 }, //Draconic Visage
		{ 1591, 9431, 1, 20 }, //Runite Limbs
		{ 1591, 1213, 1, 15 }, //Rune Dagger
		{ 1591, 1359, 1, 16 }, //Rune Hatchet
		{ 1591, 1432, 1, 14 }, //Rune Mace
		{ 1591, 1199, 1, 7 }, //Adamant Kite
		{ 1591, 1163, 1, 18 }, //Rune Full Helm
		{ 1591, 811, Misc.random(20), 22 }, //Rune Dart
		{ 1591, 995, Misc.random(95000), 12 }, //GP
		{ 1591, 145, 1, 10 }, //Super Attack (3)
		{ 1591, 2363, 1, 36 }, //Rune Bar
		{ 1591, 161, 1, 13 }, //Super Strength (1)
		{ 1591, 868, Misc.random(20), 17 }, //Rune Knives
		{ 1591, 565, Misc.random(80), 20 }, //Blood Runes
		{ 1591, 9144, Misc.random(100), 26 }, //Runite Bolts
		{ 1591, 566, Misc.random(30), 31 }, //Soul Rune
		{ 1591, 561, Misc.random(50), 28 }, //Nature Rune
		{ 1591, 830, Misc.random(30), 27 }, //Rune Javelins

		//Red dragons
		{ 53, 1149, 1, 65 }, //Dragon Med
		{ 53, 4585, 1, 90 }, //Dragon Plateskirt
		{ 53, 4087, 1, 105 }, //Dragon Platelegs
		{ 53, 536, 1, 0 }, //Dragon Bone
		{ 53, 2353, 5, 0 }, //Steel Bar
		{ 53, 2366, 1, 60 }, //Dragon Sq. Left Half
		{ 53, 2368, 1, 55 }, //Dragon Sq. Right Half
		{ 53, 11286, 1, 135 }, //Draconic Visage
		{ 53, 1317, 1, 15 }, //Adamant 2h
		{ 53, 1371, 1, 16 }, //Adamant Battleaxe
		{ 53, 1347, 1, 14 }, //Adamant Hatchet
		{ 53, 1359, 1, 20 }, //Rune Hatchet
		{ 53, 1432, 1, 19 }, //Rune Mace
		{ 53, 1303, 1, 23 }, //Rune Longsword
		{ 53, 1373, 1, 25 }, //Rune Battleaxe
		{ 53, 1319, 1, 17 }, //Rune 2h
		{ 53, 1183, 1, 30 }, //Adamant Sq Shield
		{ 53, 1147, 1, 28 }, //Rune Med Helm
		{ 53, 1163, 1, 26 }, //Rune Full Helm
		{ 53, 1516, Misc.random(75), 35 }, //Yew Logs
		{ 53, 1185, 1, 32 }, //Rune Sq Shield
		{ 53, 1201, 1, 35 }, //Rune Kiteshield
		{ 53, 811, Misc.random(20), 22 }, //Rune Dart
		{ 53, 995, Misc.random(150000), 12 }, //GP
		{ 53, 145, 1, 10 }, //Super Attack (3)
		{ 53, 2363, 1, 36 }, //Rune Bar
		{ 53, 161, 1, 13 }, //Super Strength (1)
		{ 53, 868, Misc.random(40), 17 }, //Rune Knives
		{ 53, 565, Misc.random(100), 20 }, //Blood Runes
		{ 53, 9144, Misc.random(100), 26 }, //Runite Bolts
		{ 53, 566, Misc.random(65), 31 }, //Soul Rune
		{ 53, 561, Misc.random(100), 28 }, //Nature Rune
		{ 53, 830, Misc.random(50), 27 }, //Rune Javelins
		{ 53, 886, Misc.random(125), 50 }, //Steel Arrows
		{ 53, 563, 50, 37 }, //Law Rune
		{ 53, 562, 90, 36 }, //Chaos Rune
		{ 53, 560, Misc.random(100), 33 }, //Death Rune
		{ 53, 2361, 2, 65 }, //Adamant Bar
		{ 53, 9431, 1, 30 }, //Runite Limb
		
		//Black Dragons //TODO 54
		{ 54, 1149, 1, 65 }, //Dragon Med
		{ 54, 4585, 1, 90 }, //Dragon Plateskirt
		{ 54, 4087, 1, 105 }, //Dragon Platelegs
		{ 54, 536, 1, 0 }, //Dragon Bone
		{ 54, 2353, 5, 0 }, //Steel Bar
		{ 54, 2366, 1, 60 }, //Dragon Sq. Left Half
		{ 54, 2368, 1, 55 }, //Dragon Sq. Right Half
		{ 54, 11286, 1, 130 }, //Draconic Visage
		{ 54, 1317, 1, 15 }, //Adamant 2h
		{ 54, 1371, 1, 16 }, //Adamant Battleaxe
		{ 54, 1347, 1, 14 }, //Adamant Hatchet
		{ 54, 1359, 1, 20 }, //Rune Hatchet
		{ 54, 1432, 1, 19 }, //Rune Mace
		{ 54, 1303, 1, 23 }, //Rune Longsword
		{ 54, 1373, 1, 25 }, //Rune Battleaxe
		{ 54, 1319, 1, 17 }, //Rune 2h
		{ 54, 1183, 1, 30 }, //Adamant Sq Shield
		{ 54, 1147, 1, 28 }, //Rune Med Helm
		{ 54, 1163, 1, 26 }, //Rune Full Helm
		{ 54, 1516, Misc.random(75), 35 }, //Yew Logs
		{ 54, 1185, 1, 32 }, //Rune Sq Shield
		{ 54, 1201, 1, 35 }, //Rune Kiteshield
		{ 54, 811, Misc.random(20), 22 }, //Rune Dart
		{ 54, 995, Misc.random(150000), 12 }, //GP
		{ 54, 145, 1, 10 }, //Super Attack (3)
		{ 54, 2363, 1, 36 }, //Rune Bar
		{ 54, 161, 1, 13 }, //Super Strength (1)
		{ 54, 868, Misc.random(40), 17 }, //Rune Knives
		{ 54, 565, Misc.random(100), 20 }, //Blood Runes
		{ 54, 9144, Misc.random(100), 26 }, //Runite Bolts
		{ 54, 566, Misc.random(65), 31 }, //Soul Rune
		{ 54, 561, Misc.random(100), 28 }, //Nature Rune
		{ 54, 830, Misc.random(50), 27 }, //Rune Javelins
		{ 54, 886, Misc.random(125), 50 }, //Steel Arrows
		{ 54, 563, 50, 37 }, //Law Rune
		{ 54, 562, 90, 36 }, //Chaos Rune
		{ 54, 560, Misc.random(100), 33 }, //Death Rune
		{ 54, 2361, 2, 65 }, //Adamant Bar
		{ 54, 9431, 1, 30 }, //Runite Limb
		
		//Ice Strykewyrm
		{ 9463, 15486, 1, 90 }, //Staff of Light
		{ 9463, 1369, 1, 10 }, //Mithril Battleaxe
		{ 9463, 1369, 1, 10}, //Rune Battleaxe
		{ 9463, 565, Misc.random(100), 25 }, //Blood Rune
		{ 9463, 560, Misc.random(100), 20 }, //Death Rune
		{ 9463, 563, Misc.random(50), 30 }, //Law Rune
		{ 9463, 561, Misc.random(75), 35 }, //Nature Rune
		{ 9463, 1446, 1, 17 }, //Body Talisman
		{ 9463, 213, Misc.random(4), 14 }, //Kwuarm
		{ 9463, 207, Misc.random(4), 16 }, //Ranarr
		{ 9463, 1514, 5, 19 }, //Magic Logs
		{ 9463, 1437, 200, 22 }, //Rune Ess
		{ 9363, 2364, 1, 24 }, //Runite Bar
		{ 9363, 163, 1, 28 }, //Super Defence (3)
		{ 9363, 6694, Misc.random(4), 10 }, //Crushed Bird's Nest
		{ 9363, 995, Misc.random(120000), 12 }, //GP
		{ 9363, 5295, Misc.random(35), 13 }, //Ranarr Seed
		
		{ 9462, 15486, 1, 90 }, //Staff of Light
		{ 9462, 1369, 1, 10 }, //Mithril Battleaxe
		{ 9462, 1369, 1, 10 }, //Rune Battleaxe
		{ 9462, 565, Misc.random(100), 25 }, //Blood Rune
		{ 9462, 560, Misc.random(100), 20 }, //Death Rune
		{ 9462, 563, Misc.random(50), 30 }, //Law Rune
		{ 9462, 561, Misc.random(75), 35 }, //Nature Rune
		{ 9462, 1446, 1, 17 }, //Body Talisman
		{ 9462, 213, Misc.random(4), 14 }, //Kwuarm
		{ 9462, 207, Misc.random(4), 16 }, //Ranarr
		{ 9462, 1514, 5, 19 }, //Magic Logs
		{ 9462, 1437, 200, 22 }, //Rune Ess
		{ 9362, 2364, 1, 24 }, //Runite Bar
		{ 9362, 163, 1, 28 }, //Super Defence (3)
		{ 9362, 6694, Misc.random(4), 10 }, //Crushed Bird's Nest
		{ 9362, 995, Misc.random(120000), 12 }, //GP
		{ 9362, 5295, Misc.random(35), 13 }, //Ranarr Seed
		
		//Desert Strykewyrm 
		{ 9465, 15490, 1, 80 }, //Focus Sight
		{ 9465, 560, Misc.random(100), 20 }, //Death Runes
		{ 9465, 555, Misc.random(250), 25 }, //Water Runes
		{ 9465, 563, Misc.random(50), 15 }, //Law Runes
		{ 9465, 1454, 1, 22 }, //Cosmic Talisman
		{ 9465, 1359, 1, 25 }, //Rune Hatchet
		{ 9465, 1369, 1, 28 }, //Mithril Battleaxe
		{ 9465, 5299, Misc.random(20), 12 }, //Kwuarm Seeds
		{ 9465, 5303, Misc.random(15), 19 }, //Dwarf Weed Seeds
		{ 9465, 5302, Misc.random(10), 27 }, //Lantadyme Seeds
		{ 9465, 208, Misc.random(5), 24 }, //Ranarr Weed
		{ 9465, 995, Misc.random(135000), 17 }, //GP
		{ 9465, 373, 2, 10 }, //Swordfish
		{ 9465, 2362, 3, 12 }, //Adamant Bar
		{ 9465, 1516, 10, 16 }, //Yew Logs
		{ 9465, 1437, 120, 35 }, //Rune Ess
		{ 9465, 3139, Misc.random(10), 23 }, //Potato Cactus
		{ 9465, 163, 1, 8 }, //Super Def (3)
		{ 9465, 985, 1, 40 }, //Loop Half Key
		
		{ 9464, 15490, 1, 70 }, //Focus Sight
		{ 9464, 560, Misc.random(100), 20 }, //Death Runes
		{ 9464, 555, Misc.random(250), 25 }, //Water Runes
		{ 9464, 563, Misc.random(50), 15 }, //Law Runes
		{ 9464, 1454, 1, 22 }, //Cosmic Talisman
		{ 9464, 1359, 1, 25 }, //Rune Hatchet
		{ 9464, 1369, 1, 28 }, //Mithril Battleaxe
		{ 9464, 5299, Misc.random(20), 12 }, //Kwuarm Seeds
		{ 9464, 5303, Misc.random(15), 19 }, //Dwarf Weed Seeds
		{ 9464, 5302, Misc.random(10), 27 }, //Lantadyme Seeds
		{ 9464, 208, Misc.random(5), 24 }, //Ranarr Weed
		{ 9464, 995, Misc.random(135000), 17 }, //GP
		{ 9464, 373, 2, 10 }, //Swordfish
		{ 9464, 2362, 3, 12 }, //Adamant Bar
		{ 9464, 1516, 10, 16 }, //Yew Logs
		{ 9464, 1437, 120, 35 }, //Rune Ess
		{ 9464, 3139, Misc.random(10), 23 }, //Potato Cactus
		{ 9464, 163, 1, 8 }, //Super Def (3)
		{ 9464, 985, 1, 40 }, //Loop Half Key
		
		//Jungle Strykewyrm 
		{ 9467, 561, Misc.random(50), 10 }, //Nature Rune
		{ 9467, 563, Misc.random(70), 18 }, //Law Rune
		{ 9467, 560, Misc.random(100), 27 }, //Death Rune
		{ 9467, 208, Misc.random(5), 25 }, //Ranarr Weed
		{ 9467, 987, 1, 46 }, //Tooth Half Key
		{ 9467, 15488, 1, 100 }, //Hexcrest
		{ 9467, 1516, Misc.random(200), 20 }, //Yew Logs
		{ 9467, 1514, Misc.random(75), 28 }, //Magic Logs
		{ 9467, 2360, Misc.random(10), 8 }, //Mithril Bars
		{ 9467, 379, Misc.random(5), 11 }, //Lobster
		{ 9467, 1437, 40, 26 }, //Rune Ess
		{ 9467, 1357, 1, 14 }, //Adamant Hatchet
		{ 9467, 1369, 1, 17 }, //Mithril Battleaxe
		{ 9467, 1247, 1, 25 }, //Rune Spear
		{ 9467, 3202, 1, 40 }, //Rune Halberd
		{ 9467, 1615, 1, 35 }, //Dragonstone
		
		{ 9466, 561, Misc.random(50), 10 }, //Nature Rune
		{ 9466, 563, Misc.random(70), 18 }, //Law Rune
		{ 9466, 560, Misc.random(100), 27 }, //Death Rune
		{ 9466, 208, Misc.random(5), 25 }, //Ranarr Weed
		{ 9466, 987, 1, 46 }, //Tooth Half Key
		{ 9466, 15488, 1, 110 }, //Hexcrest
		{ 9466, 1516, Misc.random(200), 20 }, //Yew Logs
		{ 9466, 1514, Misc.random(75), 28 }, //Magic Logs
		{ 9466, 2360, Misc.random(10), 8 }, //Mithril Bars
		{ 9466, 379, Misc.random(5), 11 }, //Lobster
		{ 9466, 1437, 40, 26 }, //Rune Ess
		{ 9466, 1357, 1, 14 }, //Adamant Hatchet
		{ 9466, 1369, 1, 17 }, //Mithril Battleaxe
		{ 9466, 1247, 1, 25 }, //Rune Spear
		{ 9466, 3202, 1, 40 }, //Rune Halberd
		{ 9466, 1615, 1, 35 }, //Dragonstone
		
		//Vyrewatch
		{ 7693, 3385, 1, 45 }, //Splitbark Helm
		{ 7693, 3387, 1, 60 }, //Splitbark Body
		{ 7693, 3389, 1, 67 }, //Splitbark Boots
		{ 7693, 3391, 1, 73 }, //Splitbark Gloves
		{ 7693, 995, Misc.random(110000), 20 }, //GP
		{ 7693, 560, Misc.random(100), 11 }, //Death Runes
		{ 7693, 565, Misc.random(100), 15 }, //Blood Runes
		{ 7693, 554, Misc.random(500), 17 }, //Fire Runes
		{ 7693, 561, Misc.random(220), 26 }, //Nature Runes
		{ 7693, 1213, 1, 19 }, //Rune Dagger
		{ 7693, 208, 4, 25 }, //Ranarr
		{ 7693, 1111, 1, 30 }, //Adamant Chainbody
		{ 7693, 1093, 1, 35 }, //Rune Plateskirt
		{ 7693, 985, 1, 40 }, //Loop Half Key
		{ 7693, 987, 1, 70 }, //Tooth Half Key
		{ 7693, 1201, 1, 42 }, //Rune Kite
		{ 7693, 9143, Misc.random(500), 39 }, //Adamant Bolts
		{ 7693, 892, Misc.random(240), 19 }, //Rune Arrows
		{ 7693, 6686, Misc.random(250), 80 }, //Saradomin Brew
		{ 7693, 1147, 1, 22 }, //Rune Med Helm
		
		//Skeleton Thug
		{ 6107, 9419, 1, 25 }, //Mithril Grapple
		{ 6107, 886, Misc.random(240), 30 }, //Steel Arrows
		{ 6107, 1371, 1, 18 }, //Adamant Battleaxe
		{ 6107, 1333, 1, 20 }, //Rune Scimitar
		{ 6107, 808, Misc.random(70), 15 }, //Steel Darts
		{ 6107, 1391, 1, 30 }, //Battlestaff
		{ 6107, 865, Misc.random(125), 20 }, //Steel Knives
		{ 6107, 12929, 1, 65 }, //Rune Berserker Shield
		{ 6107, 12922, 1, 50 }, //Rune Spikeshield
		{ 6107, 12908, 1, 45 }, //Adamant Spike Shield
		{ 6107, 12915, 1, 40 }, //Adamant Berserker Shield
		{ 6107, 1185, 1, 25 }, //Rune square shield
		{ 6107, 1127, 1, 45 }, //Rune Platebody
		{ 6107, 1197, 1, 7 }, //Mithril Kite
		{ 6107, 1157, 1, 12 }, //Steel Full Helm
		{ 6107, 217, Misc.random(5), 16 }, //Grimy Dwarf Weed
		{ 6107, 995, Misc.random(60000), 19 }, //GP
		{ 6107, 1515, 1, 13 }, //Yew log
		{ 6107, 560, 30, 22 }, //Death Rune
		{ 6107, 565, 35, 24 }, //Blood Rune
		{ 6107, 2361, 1, 10 }, //Adamant Bar
		{ 6107, 20667, 1, 120 }, //Vecna Skull
		{ 6107, 15046, 1, 95 }, //Necromancer's Skull
		
		//Skeleton Hero
		{ 6103, 9419, 1, 25 }, //Mithril Grapple
		{ 6103, 886, Misc.random(240), 30 }, //Steel Arrows
		{ 6103, 1371, 1, 18 }, //Adamant Battleaxe
		{ 6103, 1333, 1, 20 }, //Rune Scimitar
		{ 6103, 808, Misc.random(70), 15 }, //Steel Darts
		{ 6103, 1391, 1, 30 }, //Battlestaff
		{ 6103, 865, Misc.random(125), 20 }, //Steel Knives
		{ 6103, 12929, 1, 65 }, //Rune Berserker Shield
		{ 6103, 12922, 1, 50 }, //Rune Spikeshield
		{ 6103, 12908, 1, 45 }, //Adamant Spike Shield
		{ 6103, 12915, 1, 40 }, //Adamant Berserker Shield
		{ 6103, 1185, 1, 25 }, //Rune square shield
		{ 6103, 1127, 1, 45 }, //Rune Platebody
		{ 6103, 1197, 1, 7 }, //Mithril Kite
		{ 6103, 1157, 1, 12 }, //Steel Full Helm
		{ 6103, 217, Misc.random(5), 16 }, //Grimy Dwarf Weed
		{ 6103, 995, Misc.random(60000), 19 }, //GP
		{ 6103, 1515, 1, 13 }, //Yew log
		{ 6103, 560, 30, 22 }, //Death Rune
		{ 6103, 565, 35, 24 }, //Blood Rune
		{ 6103, 2361, 1, 10 }, //Adamant Bar
		{ 6103, 20667, 1, 350 }, //Vecna Skull
		{ 6103, 15046, 1, 350 }, //Necromancer's Skull
		
		//Skeleton Brute
		{ 6104, 9419, 1, 25 }, //Mithril Grapple
		{ 6104, 886, Misc.random(240), 30 }, //Steel Arrows
		{ 6104, 1371, 1, 18 }, //Adamant Battleaxe
		{ 6104, 1333, 1, 20 }, //Rune Scimitar
		{ 6104, 808, Misc.random(70), 15 }, //Steel Darts
		{ 6104, 1391, 1, 30 }, //Battlestaff
		{ 6104, 865, Misc.random(125), 20 }, //Steel Knives
		{ 6104, 12929, 1, 65 }, //Rune Berserker Shield
		{ 6104, 12922, 1, 50 }, //Rune Spikeshield
		{ 6104, 12908, 1, 45 }, //Adamant Spike Shield
		{ 6104, 12915, 1, 40 }, //Adamant Berserker Shield
		{ 6104, 1185, 1, 25 }, //Rune square shield
		{ 6104, 1127, 1, 45 }, //Rune Platebody
		{ 6104, 1197, 1, 7 }, //Mithril Kite
		{ 6104, 1157, 1, 12 }, //Steel Full Helm
		{ 6104, 217, Misc.random(5), 16 }, //Grimy Dwarf Weed
		{ 6104, 995, Misc.random(60000), 19 }, //GP
		{ 6104, 1515, 1, 13 }, //Yew log
		{ 6104, 560, 30, 22 }, //Death Rune
		{ 6104, 565, 35, 24 }, //Blood Rune
		{ 6104, 2361, 1, 10 }, //Adamant Bar
		{ 6104, 1081, 1, 28 }, //Iron Plateskirt
		{ 6104, 1181, 1, 23 }, //Mithril Sq Shield
		{ 6104, 21508, 1, 110 }, //Necromancer Robe Top
		{ 6104, 21509, 1, 125 }, //Necromancer Hood
		{ 6104, 21510, 1, 135 }, //Necromancer Bottoms
		
		//Skeleton Warlord
		{ 6105, 9419, 1, 25 }, //Mithril Grapple
		{ 6105, 886, Misc.random(240), 30 }, //Steel Arrows
		{ 6105, 1371, 1, 18 }, //Adamant Battleaxe
		{ 6105, 1333, 1, 20 }, //Rune Scimitar
		{ 6105, 808, Misc.random(70), 15 }, //Steel Darts
		{ 6105, 1391, 1, 30 }, //Battlestaff
		{ 6105, 865, Misc.random(125), 20 }, //Steel Knives
		{ 6105, 12929, 1, 65 }, //Rune Berserker Shield
		{ 6105, 12922, 1, 50 }, //Rune Spikeshield
		{ 6105, 12908, 1, 45 }, //Adamant Spike Shield
		{ 6105, 12915, 1, 40 }, //Adamant Berserker Shield
		{ 6105, 1185, 1, 25 }, //Rune square shield
		{ 6105, 1127, 1, 45 }, //Rune Platebody
		{ 6105, 1197, 1, 7 }, //Mithril Kite
		{ 6105, 1157, 1, 12 }, //Steel Full Helm
		{ 6105, 217, Misc.random(5), 16 }, //Grimy Dwarf Weed
		{ 6105, 995, Misc.random(60000), 19 }, //GP
		{ 6105, 1515, 1, 13 }, //Yew log
		{ 6105, 560, 30, 22 }, //Death Rune
		{ 6105, 565, 35, 24 }, //Blood Rune
		{ 6105, 2361, 1, 10 }, //Adamant Bar
		{ 6105, 1081, 1, 28 }, //Iron Plateskirt
		{ 6105, 1181, 1, 23 }, //Mithril Sq Shield
		{ 6105, 21508, 1, 110 }, //Necromancer Robe Top
		{ 6105, 21509, 1, 125 }, //Necromancer Hood
		{ 6105, 21510, 1, 135 }, //Necromancer Bottoms

		//Frost dragon
		{ 51, 18830, 1, 0 }, //Frost bone
		{ 51, 4585, 1, 60 }, //D Skirt
		{ 51, 987, 1, 55 }, //Tooth Half Key
		{ 51, 5696, 1, 3 }, //Rune Dagger p++
		{ 51, 1319, 1, 5 }, //Rune 2h
		{ 51, 1371, 1, 7 }, //Adamant Battleaxe
		{ 51, 995, Misc.random(500000), 110 }, //GP
		{ 51, 561, Misc.random(100), 10 }, //Nature Rune
		{ 51, 1123, 1, 12 }, //Adamant Platebody
		{ 51, 1445, 2, 4 }, //Water Talisman
		{ 51, 208, Misc.random(50), 20 }, //Grimy Ranarr Weed
		{ 51, 892, Misc.random(150), 15 }, //Rune Arrows
		{ 51, 19293, 1, 180 }, //Frost Dragon Mask
		{ 51, 11286, 1, 125 }, //Draconic Visage

		//Nex
		//{ 13447, 20171 , 1 , 320}, //Zaryte
		{ 13447, 20135 , 1 , 250}, //Torva Helm
		{ 13447, 20139 , 1 , 265}, //Torva Body
		{ 13447, 20143 , 1 , 260}, //Torva Legs
		{ 13447, 20159 , 1 , 220},
		{ 13447, 20163 , 1 , 223},
		{ 13447, 20167 , 1 , 230},	
		{ 13447, 1514, 375, 50 }, //Magic Logs
		{ 13447, 20147 , 1 , 215},
		{ 13447, 20151 , 1 , 235},
		{ 13447, 20155 , 1 , 240},
		{ 13447, 9245 , 375 , 20},
		{ 13447, 6685, 10, 45 }, //Sara Brews
		{ 13447, 3024, 10, 18 }, //Super restores
		{ 13447, 220, Misc.random(50), 30 }, //Torstols
		{ 13447, 6686 , 10 + Misc.random(20) , 20},
		{ 13447, 3025 , 10 + Misc.random(20) , 20},
		{ 13447, 995, Misc.random(1200000), 3},
		{ 13447, 1437, Misc.random(4800), 25 }, //Rune Ess
		{ 13447, 1746, Misc.random(400), 20 }, //Green D'hide
		{ 13447, 452, 80, 30 }, //Runite Ore
		{ 13447, 454, Misc.random(2400), 40 }, //Coal
		{ 13447, 565, Misc.random(5000), 120 }, //Blood Rune
		{ 13447, 560, Misc.random(5000), 125 }, //Death Rune
		{ 13447, 9245, Misc.random(375), 180 }, //Onyx Bolts (e)
		{ 13447, 532, 1, 0 }, //Big Bones

		// jadinko male
		{ 13822, 21371, 1, 190 }, //Vine Whip
		{ 13822, 995, 350 + Misc.random(200000), 15 },
		{ 13822, 985, 1, 35 },
		{ 13822, 1215, 1, 15 },
		{ 13822, 19269, 1, 25 },
		{ 13822, 592, 1, 0 }, //Ashes
		{ 13822, 985, 1, 27 }, //Loop Half Key
		{ 13822, 989, 1, 200 }, //Crystal Key
		{ 13822, 208, Misc.random(20), 40 }, //Ranarr
		{ 13822, 19314, 1, 75 }, //Third age druidic wreath
		{ 13822, 19317, 1, 85 }, //Third age druidic top
		{ 13822, 19320, 1, 105 }, //Third age druidic bottom
		{ 13822, 6760, 1, 65 }, //Guthix Mjolnir
		{ 13822, 6762, 1, 70 }, //Saradomin Mjolnir
		{ 13822, 6764, 1, 95 }, //Zamorak Mjolnir
		{ 13822, 1520, Misc.random(750), 30 }, //Willow Logs
		{ 13822, 21369, 1, 200 }, //Whip Vine

		// glacor
		{ 14301, 561, 25 + Misc.random(40), 2 },
		{ 14301, 556, 25 + Misc.random(40), 2 },
		{ 14301, 565, 25 + Misc.random(40), 2 },
		{ 14301, 560, 25 + Misc.random(40), 2 },
		{ 14301, 995, 350 + Misc.random(130000), 5 },
		{ 14301, 532, 2, 0 },
		{ 14301, 1434, 1, 28 },
		{ 14301, 21790, 1, 180 },
		{ 14301, 21793, 1, 190 },
		{ 14301, 21787, 1, 180 }, //SteadFast
		{ 14301, 4694, Misc.random(50), 30 }, //Steam Rune
		{ 14301, 9143, Misc.random(100), 35 }, //Adamant Bolts
		{ 14301, 1434, 1, 75 }, //Dragon Mace
		{ 14301, 1373, 1, 20 }, //Rune Battleaxe
		{ 14301, 1303, 1, 28 }, //Rune Longsword
		{ 14301, 1149, 1, 50 }, //Dragon Med
		{ 14301, 1079, 1, 30 }, //Rune platelegs
		{ 14301, 1437, Misc.random(100), 25 }, //Rune Ess
		{ 14301, 452, Misc.random(3), 13 }, //Runite Ore
		{ 14301, 141, 1, 7 }, //Prayer Potion (2)
		{ 14301, 1752, Misc.random(10), 20 }, //Blue Dragonhide
		{ 14301, 384, Misc.random(10), 15 }, //Raw Shark
		{ 14301, 385, Misc.random(5), 40 }, //Shark
		{ 14301, 1444, 1, 54 }, //Water talisman

		// ganodermic beast
		{ 1381, 565, 25 + Misc.random(40), 17 }, //Blood Rune
		{ 1381, 560, 25 + Misc.random(40), 20 }, //Death Rune
		{ 1381, 995, 1000 + Misc.random(200000), 35 }, //GP
		{ 1381, 22494, 1, 150 }, //Polypore Staff
		{ 1381, 22482, 1, 140 }, //Gano Visor
		{ 1381, 22490, 1, 125 }, //Gano Poncho
		{ 1381, 22486, 1, 130 }, //Gano Leggings
		{ 1381, 5321, Misc.random(50), 20 }, //Watermelon Seed
		{ 1381, 985, 1, 40 }, //Tooth Half Key
		{ 1381, 208, Misc.random(20), 35 }, //Ranarr
		{ 1381, 987, 1, 70 }, //Loop Half Key
		{ 1381, 989, 1, 90 }, //Crystal Key
		{ 1381, 385, Misc.random(3), 20 }, //Shark
		{ 1381, 1780, 15, 35 }, //Flax
		{ 1381, 10976, 2, 25 }, //Long Bone
		{ 1381, 532, 1, 0 }, //Big Bone
		{ 1381, 219, 1, 15 }, //Torstol
		{ 1381, 1113, 1, 26 }, //Rune Chainbody
		{ 1381, 1147, 1, 20 }, //Rune Med Helm
		{ 1381, 1289, 1, 22 }, //Rune Sword
		{ 1381, 1623, 1, 11 }, //Uncut Sapphire
		{ 1381, 1631, 1, 65 }, //Uncut Dragonstone
		{ 1381, 1215, 1, 60 }, //Dragon Dagger
		{ 1381, 1247, 1, 45 }, //Rune Spear
		{ 1381, 1201, 1, 50 }, //Rune Kiteshield
		{ 1381, 9342, Misc.random(60), 55 }, //Onyx Bolts
		{ 1381, 892, Misc.random(200), 40 }, //Rune Arrows
		{ 1381, 1516, Misc.random(175), 35 }, //Yew Logs

		// ganodermic runt
		{ 1374, 565, 25 + Misc.random(40), 35 }, //Blood Rune
		{ 1374, 560, 25 + Misc.random(40), 47 }, //Death Rune
		{ 1374, 995, 1000 + Misc.random(3700), 2 }, //GP
		{ 1374, 22494, 1, 170 }, //Polypore Staff
		{ 1374, 22482, 1, 160 }, //Gano Visor
		{ 1374, 22490, 1, 145 }, //Gano Poncho
		{ 1374, 22486, 1, 150 }, //Gano Leggings
		{ 1374, 526, 1, 0 }, //Bones
		{ 1374, 1780, 20, 22 }, //Flax
		{ 1374, 985, 1, 40 }, //Loop Half Key
		{ 1374, 987, 1, 95 }, //Tooth Half Key
		{ 1374, 563, Misc.random(50), 20 }, //Law Rune
		{ 1374, 1522, Misc.random(100), 35 }, //Oak Logs
		{ 1374, 1333, 1, 17 }, //Rune Scimitar
		{ 1374, 1099, 1, 24 }, //Green D'hide Chaps
		{ 1374, 1135, 1, 31 }, //Green D'hide Body
		{ 1374, 208, Misc.random(10), 25 }, //Ranarr
		{ 1374, 1619, 1, 7 }, //Uncut Ruby
		{ 1374, 9242, Misc.random(50), 13 }, //Ruby Bolts (e)
		{ 1374, 890, Misc.random(100), 19 }, //Adamant Arrows

		// Men
		{ 1, 526, 1, 0 },
		{ 1, 995, 150, 3 },
		{ 2, 526, 1, 0 },
		{ 3, 526, 1, 0 },
		{ 3, 995, 150, 3 },

		// Wolf
		{ 96, 958, 1, 3 },
		{ 96, 2859, 1, 0 },

		//General Graador
		{ 6260, 532, 1, 0 },
		{ 6260, 561, 320 + Misc.random(120), 20 },
		{ 6260, 560, 210 + Misc.random(70), 15 },
		{ 6260, 995, 40000 + Misc.random(5000), 15 },
		{ 6260, 830, 100, 5 },
		{ 6260, 1201, 1, 10 },
		{ 6260, 1247, 1, 30 },
		{ 6260, 1303, 1, 3 },
		{ 6260, 1319, 1, 12 },
		{ 6260, 886, 500, 30 },
		{ 6260, 11704, 1, 135 },
		{ 6260, 11690, 1, 70 },
		{ 6260, 11728, 1, 130 },
		{ 6260, 11724, 1, 140 },
		{ 6260, 11726, 1, 125 },
		{ 6260, 1127, 1, 35 },
		{ 6260, 1514, 40, 28 },
		
		//Spiritual Shits
		{ 6229, 11732, 1, 90 },
		{ 6230, 11732, 1, 90 },
		{ 6231, 11732, 1, 90 },
		{ 6219, 11732, 1, 90 },
		{ 6220, 11732, 1, 90 },
		{ 6221, 11732, 1, 90 },
		{ 6255, 11732, 1, 90 },
		{ 6256, 11732, 1, 90 },
		{ 6257, 11732, 1, 90 },
		{ 6267, 11732, 1, 90 },
		{ 6276, 11732, 1, 90 },
		{ 6277, 11732, 1, 90 },
		{ 6278, 11732, 1, 90 },
		
		//Seargeant Strongstack
		{ 6261, 526, 1, 0 },
		{ 6261, 385, 2, 10 },
		{ 6261, 995, 13000 + Misc.random(15000), 5 },
		{ 6261, 11710, 1, 110 },
		{ 6261, 11712, 1, 140 },
		{ 6261, 11714, 1, 135 },
		{ 6261, 886, 320, 5 },
		{ 6261, 11724, 1, 120 },
		{ 6261, 11726, 1, 125 },
		{ 6261, 11728, 1, 130 },
		
		//Sergeant Steelwill
		{ 6263, 526, 1, 0 },
		{ 6263, 385, 2, 10 },
		{ 6263, 995, 13000 + Misc.random(15000), 5 },
		{ 6263, 11710, 1, 130 },
		{ 6263, 11712, 1, 125 },
		{ 6263, 11714, 1, 135 },
		{ 6263, 886, 320, 5 },
		{ 6263, 11724, 1, 150 }, //Bandos Chestplate
		{ 6263, 11726, 1, 145 }, //Bandos tasset
		{ 6263, 11728, 1, 76 }, //Bandos Boots
		{ 6263, 985, 1, 30 }, //Loop Half Key
		{ 6263, 987, 1, 50 }, //Tooth half Key
		{ 6263, 989, 1, 500 }, //Crystal Key

		//Desert Snake
		{ 1874, 6287, 1, 0 },
		{ 1874, 6322, 1, 14 },
		{ 1874, 6324, 1, 14 },
		{ 1874, 6326, 1, 7 },
		{ 1874, 6328, 1, 7 },
		{ 1874, 6330, 1, 7 },
		{ 1874, 995, 1400 + (Misc.random(9900)), 4 },
		{ 1874, 6384, 1, 5 },
		{ 1874, 6386, 1, 5 },

		//Bree
		{ 6252, 526, 1, 0 },
		{ 6252, 385, 2, 10 },
		{ 6252, 995, 13000 + Misc.random(15000), 5 },
		{ 6252, 11730, 1, 150 },
		{ 6252, 11710, 1, 160 },
		{ 6252, 11712, 1, 155 },
		{ 6252, 11714, 1, 145 },
		{ 6252, 886, 320, 5 },
		{ 6252, 985, 1, 40 }, //Loop Half Key
		{ 6252, 987, 1, 60 }, //Tooth Half Key
		{ 6252, 989, 1, 500 }, //Crystal Key
		
		//Starlight
		{ 6248, 526, 1, 0 },
		{ 6248, 385, 2, 10 },
		{ 6248, 995, 13000 + Misc.random(15000), 5 },
		{ 6248, 11730, 1, 170 },
		{ 6248, 11710, 1, 160 },
		{ 6248, 11712, 1, 165 },
		{ 6248, 11714, 1, 155 },
		{ 6248, 886, 320, 5 },
		{ 6252, 985, 1, 60 }, //Loop Half Key
		{ 6252, 987, 1, 75 }, //Tooth Half Key
		{ 6252, 989, 1, 50 }, //Crystal Key
		
		//Growler
		{ 6250, 526, 1, 0 },
		{ 6250, 385, 2, 10 },
		{ 6250, 995, 13000 + Misc.random(15000), 5 },
		{ 6250, 11730, 1, 135 },
		{ 6250, 11710, 1, 185 },
		{ 6250, 11712, 1, 120 },
		{ 6250, 11714, 1, 190 },
		{ 6250, 886, 320, 5 },
		{ 6250, 985, 1, 35 }, //Loop Half Key
		{ 6250, 987, 1, 55 }, //Tooth Half Key
		{ 6250, 989, 1, 60 }, //Crystal Key
		
		//Aviansies?
		{ 6227, 11722, 1, 100 },
		{ 6227, 11720, 1, 100 },
		{ 6227, 11718, 1, 100 },
		{ 6225, 11722, 1, 100 },
		{ 6225, 11720, 1, 175 },
		{ 6225, 11718, 1, 175 },
		{ 6223, 11722, 1, 172 },
		{ 6223, 11720, 1, 165 },
		{ 6223, 11718, 1, 165 },
		{ 6227, 995, 9500, 5 },
		{ 6227, 526, 1, 0 },
		{ 6225, 995, 9500, 5 },
		{ 6225, 526, 1, 0 },
		{ 6223, 995, 9500, 5 },
		{ 6223, 526, 1, 0 },

		//Pheasant
		{ 122, 526, 1, 0 },
		{ 122, 995, 875, 3 },
		{ 122, 590, 1, 1 },
		
		// Abyssal Demon
		{ 1615, 995, 5000, 7 },
		{ 1615, 1333, 1, 8 },
		{ 1615, 1247, 1, 8 },
		{ 1615, 830, 40, 9 },
		{ 1615, 1319, 1, 8 },
		{ 1615, 4587, 1, 11 },
		{ 1615, 1079, 1, 8 },
		{ 1615, 1147, 1, 6 },
		{ 1615, 1149, 1, 9 },
		{ 1615, 4151, 1, 50 },
		{ 1615, 592, 1, 0 },
		{ 1615, 987, 1, 45 }, //Tooth Half Key
		{ 1615, 985, 1, 50 }, //Loop Half Key
		{ 1615, 989, 1, 65 }, //Crystal Key

		//Renegade Knight
		{ 237, 526, 1, 0 },
		{ 237, 995, 1000, 8 },
		{ 237, 1283, 1, 5 },
		{ 237, 1301, 1, 10 },

		//Dags
		{ 1341, 526, 1, 0 },
		{ 1341, 6128, 1, 25 },
		{ 1341, 6129, 1, 25 },
		{ 1341, 6130, 1, 25 },
		{ 1341, 6145, 1, 14 },
		{ 1341, 6151, 1, 14 },
		{ 1341, 6155, 1, 4 },
		{ 1341, 385, 2, 6 },
		{ 1341, 995, 2500 + (Misc.random(9900)), 6 },
		{ 1341, 985, 1, 35 }, //Loop Half Key

		// Banshee
		{ 1612, 995, 5000, 9 },
		{ 1612, 1333, 1, 10 },
		{ 1612, 1247, 1, 8 },
		{ 1612, 830, 20, 9 },
		{ 1612, 592, 1, 0 },

		// Crawing Hand
		{ 1648, 526, 1, 0 },
		{ 1649, 526, 1, 0 },
		{ 1650, 526, 1, 0 },
		{ 1651, 526, 1, 0 },
		{ 1652, 526, 1, 0 },

		//KBD
		{ 50, 11335, 1, 135 },
		{ 50, 4087, 1, 25 },
		{ 50, 14479, 1, 60 },
		{ 50, 1187, 1, 15 },
		{ 50, 536, 1, 0 },
		{ 50, 7987, 1, 50 },
		{ 50, 1747, 1, 0 },
		{ 50, 1319, 1, 30 },
		{ 50, 1369, 1, 15 },
		{ 50, 1247, 1, 10 },
		{ 50, 1249, 1, 20 },
		{ 50, 565, 15, 20 },
		{ 50, 560, 7 + Misc.random(38), 45 },
		{ 50, 884, 690, 10 },
		{ 50, 556, 105, 5 },
		{ 50, 554, 105, 10 },
		{ 50, 557, 105, 5 },
		{ 50, 989, 1, 53 }, //Crystal Key
		{ 50, 985, 1, 45 }, //Loop Half Key
		{ 50, 987, 1, 48 }, //Tooth Half Key
		{ 50, 892, 45, 40 },
		{ 50, 1149, 1, 70 },
		{ 50, 2368, 1, 75 },
		{ 50, 1725, 1, 10 },
		{ 50, 995, 30500, 30 },
		{ 50, 454, 100, 22 },
		{ 50, 1516, 100, 50 },
		{ 50, 11286, 1, 115 },

		// Infernal Mage
		{ 1643, 526, 1, 0 },
		{ 1643, 4675, 1, 25 },
		{ 1643, 555, 50, 7 },
		{ 1643, 560, 20, 8 },
		{ 1643, 565, 20, 8 },
		{ 1643, 4089, 1, 9 },
		{ 1643, 4091, 1, 25 },
		{ 1643, 4093, 1, 25 },
		{ 1643, 4094, 1, 25 },
		{ 1643, 4101, 1, 25 },
		{ 1643, 4103, 1, 25 },
		{ 1643, 4111, 1, 25 },
		{ 1643, 4113, 1, 25 },
		{ 1644, 526, 1, 0 },
		{ 1644, 4675, 1, 25 },
		{ 1644, 555, 50, 7 },
		{ 1644, 560, 20, 8 },
		{ 1644, 565, 20, 8 },
		{ 1644, 4089, 1, 9 },
		{ 1644, 4091, 1, 25 },
		{ 1644, 4093, 1, 25 },
		{ 1644, 4094, 1, 25 },
		{ 1644, 4101, 1, 25 },
		{ 1644, 4103, 1, 25 },
		{ 1644, 4111, 1, 25 },
		{ 1644, 4113, 1, 25 },
		{ 1645, 526, 1, 0 },
		{ 1645, 4675, 1, 25 },
		{ 1645, 555, 50, 7 },
		{ 1645, 560, 20, 8 },
		{ 1645, 565, 20, 8 },
		{ 1645, 4089, 1, 9 },
		{ 1645, 4091, 1, 25 },
		{ 1645, 4093, 1, 25 },
		{ 1645, 4094, 1, 25 },
		{ 1645, 4101, 1, 25 },
		{ 1645, 4103, 1, 25 },
		{ 1645, 4111, 1, 25 },
		{ 1645, 4113, 1, 25 },
		{ 1646, 526, 1, 0 },
		{ 1646, 4675, 1, 25 },
		{ 1646, 555, 50, 7 },
		{ 1646, 560, 20, 8 },
		{ 1646, 565, 20, 8 },
		{ 1646, 4089, 1, 9 },
		{ 1646, 4091, 1, 25 },
		{ 1646, 4093, 1, 25 },
		{ 1646, 4094, 1, 25 },
		{ 1646, 4101, 1, 25 },
		{ 1646, 4103, 1, 25 },
		{ 1646, 4111, 1, 25 },
		{ 1646, 4113, 1, 25 },
		{ 1647, 526, 1, 0 },
		{ 1647, 4675, 1, 25 },
		{ 1647, 555, 50, 7 },
		{ 1647, 560, 20, 8 },
		{ 1647, 565, 20, 8 },
		{ 1647, 4089, 1, 9 },
		{ 1647, 4091, 1, 25 },
		{ 1647, 4093, 1, 25 },
		{ 1647, 4094, 1, 25 },
		{ 1647, 4101, 1, 25 },
		{ 1647, 4103, 1, 25 },
		{ 1647, 4111, 1, 25 },
		{ 1647, 4113, 1, 25 },

		// Bloodveld
		{ 1619, 995, 5000, 7 },
		{ 1619, 1333, 1, 12 },
		{ 1619, 1247, 1, 11 },
		{ 1619, 830, 40, 12 },
		{ 1619, 1319, 1, 14 },
		{ 1619, 4587, 1, 11 },
		{ 1619, 1079, 1, 13 },
		{ 1619, 1147, 1, 13 },
		{ 1619, 1149, 1, 12 },
		{ 1619, 592, 1, 0 },
		
		// corp
		{ 8133, 13754, 1, 75 },
		{ 8133, 13734, 1, 40 },
		{ 8133, 13746, 1, 115 },
		{ 8133, 13748, 1, 150 },
		{ 8133, 13750, 1, 135},
		{ 8133, 13752, 1, 95 },
		{ 8133, 890, Misc.random(1500), 7 },
		{ 8133, 9144, Misc.random(750), 10 },
		{ 8133, 4101, 1, 5 },
		{ 8133, 4103, 1, 3 },
		{ 8133, 1249, 1, 25 },
		{ 8133, 1405, 1, 15 },
		{ 8133, 563, Misc.random(250), 8 },
		{ 8133, 560, Misc.random(500), 17 },
		{ 8133, 564, Misc.random(400), 7 },
		{ 8133, 566, Misc.random(300), 7 },
		{ 8133, 2447, Misc.random(10), 3 },
		{ 8133, 9737, Misc.random(100), 8 },
		{ 8133, 7060, 8, 10 },
		{ 8133, 9737, Misc.random(200), 8 },
		{ 8133, 2362, Misc.random(350), 13 },
		{ 8133, 452, Misc.random(200), 14 },
		{ 8133, 450, Misc.random(350), 14 },
		{ 8133, 1617, Misc.random(1) == 1 ? 8 : 1, 10 },
		{ 8133, 1619, Misc.random(1) == 1 ? 8 : 1, 13 },
		{ 8133, 1621, Misc.random(1) == 1 ? 8 : 1, 24 },
		{ 8133, 1623, Misc.random(1) == 1 ? 8 : 1, 41 },
		{ 8133, 11133, 1, 25 },
		{ 8133, 995, Misc.random(1) == 1 ? 60000 : 20000, 5 },
		{ 8133, 1514, Misc.random(750), 14 },
		{ 8133, 384, Misc.random(700), 12 },
		{ 8133, 987, 1, 20 },
		{ 8133, 989, 1, 30 },
		{ 8133, 9245, 175, 50 },

		// DustDevil
		{ 1624, 995, 50000, 7 }, //GP
		{ 1624, 995, Misc.random(125000), 18 }, //GP
		{ 1624, 3140, 1, 150 }, //Dragon Chainbody
		{ 1624, 592, 1, 0 }, //Ashes
		{ 1624, 1333, 1, 9 }, //Rune Scimitar
		{ 1624, 1247, 1, 10 }, //Rune Spear
		{ 1624, 892, Misc.random(700), 35 }, //Rune Arrows
		{ 1624, 2489, 1, 13 }, //Red d'hide vambs
		{ 1624, 830, 5, 60 }, //Rune Javelins
		{ 1624, 1399, 1, 40 }, //Earth Battlestaff
		{ 1624, 554, Misc.random(300), 15 }, //Fire Rune
		{ 1624, 562, Misc.random(50), 25 }, //Chaos Rune
		{ 1624, 566, Misc.random(75), 22 }, //Soul Rune
		{ 1624, 563, Misc.random(100), 30 }, //Law Rune
		{ 1624, 200, Misc.random(20), 40 }, //Guam
		{ 1624, 202, Misc.random(15), 35 }, //Marrentil
		{ 1624, 208, Misc.random(12), 50 }, //Ranarr
		{ 1624, 218, Misc.random(15), 45 }, //Dwarf Weed
		{ 1624, 214, Misc.random(8), 40 }, //Kwuarm
		{ 1624, 2359, 1, 20 }, //Mithril Bar

		// Gargoyle
		{ 1610, 592, 1, 0 }, //Ashes
		{ 1610, 1333, 1, 9 }, //Rune Scimitar
		{ 1610, 4153, 1, 14 }, //Granite Maul
		{ 1610, 10564, 1, 21 }, //Granite Body
		{ 1610, 6809, 1, 59 }, //Granite Legs
		{ 1610, 3122, 1, 34 }, //Granite Shield
		{ 1610, 14679, 1, 40 }, //Granite Mace
		{ 1610, 10589, 1, 23 }, //Granite Helm
		{ 1610, 1353, 1, 24 }, //Steel Hatchet
		{ 1610, 1311, 1, 17 }, //Steel 2h
		{ 1610, 1163, 1, 18 }, //Rune Full Helm
		{ 1610, 4129, 1, 27 }, //Adamant Boots
		{ 1610, 2357, 1, 50 }, //Gold Bar
		{ 1610, 1437, Misc.random(175), 40 }, //Rune Ess
		{ 1610, 4101, 1, 33 }, //Dark Mystic Top
		{ 1610, 554, Misc.random(100), 25 }, //Fire Rune
		{ 1610, 985, 1, 70 }, //Loop Half Key
		// Gargoyle
		{ 1611, 592, 1, 0 }, //Ashes
		{ 1611, 1333, 1, 9 }, //Rune Scimitar
		{ 1611, 4153, 1, 14 }, //Granite Maul
		{ 1611, 10564, 1, 19 }, //Granite Body
		{ 1611, 6809, 1, 65 }, //Granite Legs
		{ 1611, 10589, 1, 25 }, //Granite Helm
		{ 1611, 14679, 1, 43 }, //Granite Mace
		{ 1611, 3122, 1, 20 }, //Granite Shield
		{ 1611, 113, 1, 7 }, //Strength Potion (4)
		{ 1611, 554, Misc.random(100), 27 }, //Fire Rune
		{ 1611, 4101, 1, 35 }, //Dark Mystic Top
		{ 1611, 2357, 1, 57 }, //Gold Bar
		{ 1611, 4129, 1, 33 }, //Adamant Boots
		{ 1611, 1353, 1, 17 }, //Steel Hatchet
		{ 1611, 1311, 1, 22 }, //Steel 2h
		{ 1611, 1163, 1, 28 }, //Rune Full Helm
		{ 1611, 987, 1, 80 }, //Tooth Half Key

		// Nechryeal
		{ 1613, 1163, 1, 25 }, //Rune Full Helm
		{ 1613, 592, 1, 0 }, //Bones
		{ 1613, 11732, 1, 90 }, //Dragon Boots
		{ 1613, 4131, 1, 75 }, //Rune Boots
		{ 1613, 1073, 1, 30 }, //Adamant Platelegs
		{ 1613, 1197, 1, 15 }, //Mithril Kiteshield
		{ 1613, 1185, 1, 25 }, //Rune Square Shield
		{ 1613, 1373, 1, 30 }, //Rune Battleaxe
		{ 1613, 1303, 1, 18 }, //Rune Longsword
		{ 1613, 1319, 1, 27 }, //Rune 2h
		{ 1613, 560, Misc.random(100), 15 }, //Death Rune
		{ 1613, 5299, Misc.random(50), 8 }, //Kwuarm Seed
		{ 1613, 5302, Misc.random(30), 17 }, //Lantadyme Seed
		{ 1613, 5303, Misc.random(20), 15 }, //Dwarf Weed Seed
		{ 1613, 995, Misc.random(100000), 60 }, //GP
		{ 1613, 995, Misc.random(50000), 35 }, //GP
		{ 1613, 2364, 5, 45 }, //Rune Bar
		{ 1613, 2358, 10, 20 }, //Gold Bar
		{ 1613, 1734, Misc.random(50), 25 }, //Thread
		{ 1613, 361, 5, 22 }, //Tuna
		{ 1613, 1093, 1, 48 }, //Rune Plateskirt
		{ 1613, 989, 1, 85 }, //Crystal Key

		//Balfrug Kreeyath
		{ 6208, 526, 1, 0 },
		{ 6208, 385, 2, 10 },
		{ 6208, 995, 13000 + Misc.random(15000), 5 },
		{ 6208, 11716, 1, 180 },
		{ 6208, 11710, 1, 170 },
		{ 6208, 11712, 1, 170 },
		{ 6208, 11714, 1, 170 },
		{ 6208, 886, 320, 5 },
		{ 6208, 1319, 1, 5 },

		//Tstanon Karlak
		{ 6204, 526, 1, 0 },
		{ 6204, 385, 2, 10 },
		{ 6204, 995, 13000 + Misc.random(15000), 5 },
		{ 6204, 11716, 1, 180 },
		{ 6204, 11710, 1, 170 },
		{ 6204, 11712, 1, 170 },
		{ 6204, 11714, 1, 170 },
		{ 6204, 886, 320, 5 },
		{ 6204, 1319, 1, 5 },

		//Zakl'n Gritch
		{ 6206, 526, 1, 0 },
		{ 6206, 385, 2, 10 },
		{ 6206, 995, 13000 + Misc.random(15000), 5 },
		{ 6206, 11716, 1, 180 },
		{ 6206, 11710, 1, 170 },
		{ 6206, 11712, 1, 170 },
		{ 6206, 11714, 1, 170 },
		{ 6206, 886, 320, 5 },
		{ 6206, 1319, 1, 5 },

		//K'ril Tsutsaroth
		{ 6203, 592, 1, 0 },
		{ 6203, 1201, 1, 30 },
		{ 6203, 1079, 1, 20 },
		{ 6203, 1185, 1, 10 },
		{ 6203, 1289, 1, 30 },
		{ 6203, 890, 295 + Misc.random(5), 5 },
		{ 6203, 892, 45, 25 },
		{ 6203, 1149, 1, 25 },
		{ 6203, 5698, 1, 30 },
		{ 6203, 995, 50000 + Misc.random(10000), 15 },
		{ 6203, 565, 100 + Misc.random(35), 20 },
		{ 6203, 560, 120 + Misc.random(20), 5 },
		{ 6203, 561, 65, 15 },
		{ 6203, 11708, 1, 180 },
		{ 6203, 11690, 1, 120 },

		//Sergeant Grimspike
		{ 6265, 11710, 1, 170 },
		{ 6265, 11712, 1, 170 },
		{ 6265, 11714, 1, 170 },
		{ 6265, 11710, 1, 170 },
		{ 6265, 11712, 1, 170 },
		{ 6265, 11714, 1, 170 },
		{ 6265, 11710, 1, 170 },
		{ 6265, 11712, 1, 170 },
		{ 6265, 11714, 1, 170 },
		{ 6265, 526, 1, 0 },
		{ 6265, 385, 2, 10 },
		{ 6265, 995, 13000 + Misc.random(15000), 5 },
		{ 6265, 11710, 1, 50 },
		{ 6265, 11712, 1, 50 },
		{ 6265, 11714, 1, 50 },
		{ 6265, 886, 320, 5 },
		{ 6265, 11724, 1, 100 },
		{ 6265, 11726, 1, 100 },
		{ 6265, 11728, 1, 100 },

		//Kree'arra
		{ 6222, 1201, 1, 25 },
		{ 6222, 1149, 1, 40 },
		{ 6222, 532, 1, 0 },
		{ 6222, 314, 1 + Misc.random(15), 0 },
		{ 6222, 9185, 1, 10 },
		{ 6222, 9244, 15 + Misc.random(5), 15 },
		{ 6222, 561, 167, 30 },
		{ 6222, 560, 41 + Misc.random(10), 30 },
		{ 6222, 11702, 1, 120 },
		{ 6222, 11690, 1, 90 },
		{ 6222, 11718, 1, 108 },
		{ 6222, 11720, 1, 98 },
		{ 6222, 11722, 1, 88 },

		//Command zilyana
		{ 6247, 526, 1, 0 },
		{ 6247, 11706, 1, 105 },
		{ 6247, 11690, 1, 80 },
		{ 6247, 11730, 1, 70 },
		{ 6247, 561, 210, 20 },
		{ 6247, 1149, 1, 40 },
		{ 6247, 560, 50 + Misc.random(40), 30 },
		{ 6247, 995, 30000 + Misc.random(50000), 20 },
		{ 6247, 1079, 1, 35 },

		// Dark Beast
		{ 2783, 995, 5000, 7 },
		{ 2783, 1333, 1, 8 },
		{ 2783, 1247, 1, 8 },
		{ 2783, 830, 40, 9 },
		{ 2783, 1319, 1, 8 },
		{ 2783, 4587, 1, 11 },
		{ 2783, 1079, 1, 8 },
		{ 2783, 1147, 1, 6 },
		{ 2783, 1149, 1, 9 },
		{ 2783, 11235, 1, 17 },
		{ 2783, 11212, 5, 14 },
		{ 2783, 526, 1, 0 },

		// Green Dragon
		{ 941, 536, 1, 0 },
		{ 941, 1753, 1, 0 },
		{ 941, 1333, 1, 9 },
		{ 941, 1247, 1, 10 },
		{ 941, 1319, 1, 11 },
		{ 941, 4587, 1, 12 },

		// Blue Dragon
		{ 55, 536, 1, 0 },
		{ 55, 1751, 1, 0 },
		{ 55, 1333, 1, 9 },
		{ 55, 1247, 1, 10 },
		{ 55, 1319, 1, 10 },
		{ 55, 4597, 1, 10 },

		// Skeleton
		{ 92, 526, 1, 0 },
		{ 92, 1247, 1, 8 },
		{ 92, 995, 5000, 7 },

		// Magic Axe
		{ 127, 1373, 1, 9 },
		{ 127, 1363, 1, 0 },

		// Lesser Demon //TODO
		{ 82, 592, 1, 0 },
		{ 82, 1333, 1, 9 },
		{ 82, 1247, 1, 7 },

		// Baby Blue Dragon
		{ 52, 534, 1, 0 },

		// Black Demon
		{ 84, 592, 1, 0 },
		{ 84, 1333, 1, 8 },
		{ 84, 1247, 1, 9 },
		{ 84, 5698, 1, 10 },
		{ 84, 4587, 1, 10 },

		// Hill Giant
		{ 117, 995, 5000, 9 },
		{ 117, 1333, 1, 9 },
		{ 117, 1247, 1, 9 },
		{ 117, 830, 40, 13 },
		{ 117, 1319, 1, 9 },
		{ 117, 4587, 1, 70 },
		{ 117, 1079, 1, 10 },
		{ 117, 1147, 1, 11 },
		{ 117, 1149, 1, 9 },
		{ 117, 532, 1, 0 },

		// Moss Giant
		{ 112, 995, 5000, 9 },
		{ 112, 1333, 1, 9 },
		{ 112, 1247, 1, 9 },
		{ 112, 830, 40, 9 },
		{ 112, 1319, 1, 9 },
		{ 112, 4587, 1, 50 },
		{ 112, 1079, 1, 9 },
		{ 112, 1147, 1, 9 },
		{ 112, 1149, 1, 9 },
		{ 112, 532, 1, 0 },

		// Fire Giant
		{ 110, 995, 5000, 12 },
		{ 110, 1333, 1, 13 },
		{ 110, 1247, 1, 13 },
		{ 110, 830, 40, 14 },
		{ 110, 1319, 1, 13 },
		{ 110, 4587, 1, 11 },
		{ 110, 1079, 1, 13 },
		{ 110, 1147, 1, 10 },
		{ 110, 1149, 1, 14 },
		{ 110, 532, 1, 0 },

		// Elf Warrior
		{ 1183, 526, 1, 0 },
		{ 1183, 4212, 1, 18 },

		// Dags
		{ 2881, 6155, 1, 0 },
		{ 2881, 6729, 1, 0 },
		{ 2881, 884, 1, 10 },
		{ 2881, 3748, 1, 30 },
		{ 2881, 3757, 1, 30 },
		{ 2881, 3758, 1, 30 },
		{ 2881, 1149, 1, 50 },
		{ 2881, 6739, 1, 100 },
		{ 2881, 985, 1, 65 },
		{ 2881, 3749, 1, 50 },
		{ 2881, 6733, 1, 55 },
		{ 2881, 6735, 1, 70 },
		{ 2881, 6133, 1, 75 },
		{ 2881, 6135, 1, 75 },
		//Dag
		{ 2882, 6155, 1, 0 },
		{ 2882, 6729, 1, 0 },
		{ 2882, 6562, 1, 60 },
		{ 2882, 1149, 1, 50 },
		{ 2882, 6739, 1, 70 },
		{ 2882, 555, Misc.random(65), 7 },
		{ 2882, 6731, 1, 40 },
		{ 2882, 3755, 1, 83 },
		{ 2882, 560, Misc.random(29), 25 },
		{ 2882, 6139, 1, 75 },
		{ 2882, 6141, 1, 75 },
		{ 2882, 1201, 1, 75 },
		{ 2882, 987, 1, 80 },
		{ 2882, 565, Misc.random(11), 68 },
		//Dag
		{ 2883, 6155, 1, 0 },
		{ 2883, 6729, 1, 0 },
		{ 2883, 157, 1, 25 },
		{ 2883, 141, 1, 32 },
		{ 2883, 149, 1, 19 },
		{ 2883, 985, 1, 30 },
		{ 2883, 1329, 1, 50 },
		{ 2883, 1315, 1, 30 },
		{ 2883, 1319, 1, 40 },
		{ 2883, 6739, 1, 100 },
		{ 2883, 3751, 1, 50 },
		{ 2883, 6737, 1, 85 },
		{ 2883, 3753, 1, 50 },

		// Tzhaar
		{ 2591, 6522, 20, 50 }, { 2591, 6523, 1, 80 },
		{ 2591, 6524, 1, 45 }, { 2591, 6525, 1, 80 },
		{ 2591, 6526, 1, 40 }, { 2591, 6527, 1, 80 },
		{ 2591, 6528, 1, 60 }, { 2592, 6522, 20, 80 },
		{ 2592, 6523, 1, 50 }, { 2592, 6524, 1, 80 },
		{ 2592, 6525, 1, 70 }, { 2592, 6526, 1, 80 },
		{ 2592, 6527, 1, 35 }, { 2592, 6528, 1, 80 },
		{ 2593, 6522, 20, 45 }, { 2593, 6523, 1, 80 },
		{ 2593, 6524, 1, 46 }, { 2593, 6525, 1, 80 },
		{ 2593, 6526, 1, 38 }, { 2593, 6527, 1, 80 },
		{ 2593, 6528, 1, 55 }, { 2594, 6522, 20, 80 },
		{ 2594, 6523, 1, 53 }, { 2594, 6524, 1, 80 },
		{ 2594, 6525, 1, 45 }, { 2594, 6526, 1, 80 },
		{ 2594, 6527, 1, 54 }, { 2594, 6528, 1, 80 },
		{ 2595, 6522, 20, 30 }, { 2595, 6523, 1, 80 },
		{ 2595, 6524, 1, 50 }, { 2595, 6525, 1, 80 },
		{ 2595, 6526, 1, 40 }, { 2595, 6527, 1, 80 },
		{ 2595, 6528, 1, 60 }, { 2596, 6522, 20, 80 },
		{ 2596, 6523, 1, 50 }, { 2596, 6524, 1, 80 },
		{ 2596, 6525, 1, 55 }, { 2596, 6526, 1, 80 },
		{ 2596, 6527, 1, 56 }, { 2596, 6528, 1, 80 },
		{ 2597, 6522, 20, 40 }, { 2597, 6523, 1, 80 },
		{ 2597, 6524, 1, 50 }, { 2597, 6525, 1, 80 },
		{ 2597, 6526, 1, 40 }, { 2597, 6527, 1, 80 },
		{ 2597, 6528, 1, 60 }, { 2598, 6522, 20, 80 },
		{ 2598, 6523, 1, 70 }, { 2598, 6524, 1, 80 },
		{ 2598, 6525, 1, 44 }, { 2598, 6526, 1, 80 },
		{ 2598, 6527, 1, 48 }, { 2598, 6528, 1, 80 },
		{ 2599, 6522, 20, 50 }, { 2599, 6523, 1, 80 },
		{ 2599, 6524, 1, 60 }, { 2599, 6525, 1, 80 },
		{ 2599, 6526, 1, 50 }, { 2599, 6527, 1, 80 },
		{ 2599, 6528, 1, 40 }, { 2600, 6522, 20, 80 },
		{ 2600, 6523, 1, 45 }, { 2600, 6524, 1, 80 },
		{ 2600, 6525, 1, 44 }, { 2600, 6526, 1, 80 },
		{ 2600, 6527, 1, 43 }, { 2600, 6528, 1, 80 },
		{ 2601, 6522, 20, 40 }, { 2601, 6523, 1, 80 },
		{ 2601, 6524, 1, 44 }, { 2601, 6525, 1, 80 },
		{ 2601, 6526, 1, 54 }, { 2601, 6527, 1, 80 },
		{ 2601, 6528, 1, 47 }, { 2602, 6522, 20, 80 },
		{ 2602, 6523, 1, 46 }, { 2602, 6524, 1, 80 },
		{ 2602, 6525, 1, 43 }, { 2602, 6526, 1, 80 },
		{ 2602, 6527, 1, 55 }, { 2602, 6528, 1, 80 },
		{ 2603, 6522, 20, 40 }, { 2603, 6523, 1, 80 },
		{ 2603, 6524, 1, 45 }, { 2603, 6525, 1, 80 },
		{ 2603, 6526, 1, 43 }, { 2603, 6527, 1, 80 },
		{ 2603, 6528, 1, 36 }, { 2604, 6522, 20, 80 },
		{ 2604, 6523, 1, 55 }, { 2604, 6524, 1, 80 },
		{ 2604, 6525, 1, 44 }, { 2604, 6526, 1, 80 },
		{ 2604, 6527, 1, 50 }, { 2604, 6528, 1, 80 },
		{ 2605, 6522, 20, 40 }, { 2605, 6523, 1, 80 },
		{ 2605, 6524, 1, 80 }, { 2605, 6525, 1, 80 },
		{ 2605, 6526, 1, 80 }, { 2605, 6527, 1, 80 },
		{ 2605, 6528, 1, 80 }, { 2605, 995, 1, 80 },
		{ 2606, 6522, 20, 80 }, { 2606, 6523, 1, 80 },
		{ 2606, 6524, 1, 80 }, { 2606, 6525, 1, 80 },
		{ 2606, 6526, 1, 80 }, { 2606, 6527, 1, 80 },
		{ 2606, 6528, 1, 80 }, { 2607, 6522, 20, 40 },
		{ 2606, 6571, 1, 105 }, { 2607, 6571, 1, 150 },
		{ 2607, 6523, 1, 21 }, { 2607, 6524, 1, 24 },
		{ 2607, 6525, 1, 24 }, { 2607, 6526, 1, 24 },
		{ 2607, 6527, 1, 27 }, { 2607, 6528, 1, 28 },
		{ 2607, 6529, Misc.random(200), 0 }, { 2607, 6529, Misc.random(1200), 10 },
		{ 2608, 6522, 20, 80 }, { 2608, 6523, 1, 50 },
		{ 2608, 6524, 1, 80 }, { 2608, 6525, 1, 80 },
		{ 2608, 6526, 1, 80 }, { 2608, 6527, 1, 80 },
		{ 2608, 6528, 1, 80 }, { 2609, 6522, 20, 80 },
		{ 2609, 6523, 1, 80 }, { 2609, 6524, 1, 80 },
		{ 2609, 6525, 1, 80 }, { 2609, 6526, 1, 80 },
		{ 2609, 6527, 1, 80 }, { 2609, 6528, 1, 80 }, { 2609, 995, 1, 80 },
		{ 2610, 6522, 20, 80 }, { 2610, 6523, 1, 80 },
		{ 2610, 6524, 1, 80 }, { 2610, 6525, 1, 80 },
		{ 2610, 6526, 1, 80 }, { 2610, 6527, 1, 80 },
		{ 2610, 6528, 1, 80 }, { 2610, 995, 1, 80 },
		{ 2611, 6522, 20, 80 }, { 2611, 6523, 1, 80 },
		{ 2611, 6524, 1, 80 }, { 2611, 6525, 1, 80 },
		{ 2611, 6526, 1, 80 }, { 2611, 6527, 1, 80 },
		{ 2611, 6528, 1, 80 }, { 2611, 995, 1, 80 },
		{ 2612, 6522, 20, 80 }, { 2612, 6523, 1, 80 },
		{ 2612, 6524, 1, 80 }, { 2612, 6525, 1, 80 },
		{ 2612, 6526, 1, 80 }, { 2612, 6527, 1, 80 },
		{ 2612, 6528, 1, 80 }, { 2612, 995, 1, 80 },
		{ 2613, 6522, 20, 80 }, { 2613, 6523, 1, 80 },
		{ 2613, 6524, 1, 80 }, { 2613, 6525, 1, 80 },
		{ 2613, 6526, 1, 80 }, { 2613, 6527, 1, 80 },
		{ 2613, 6528, 1, 80 }, { 2613, 995, 1, 80 },
		{ 2614, 6522, 20, 80 }, { 2614, 6523, 1, 80 },
		{ 2614, 6524, 1, 80 }, { 2614, 6525, 1, 80 },
		{ 2614, 6526, 1, 42 }, { 2614, 6527, 1, 80 },
		{ 2614, 6528, 1, 40 }, { 2614, 995, 1, 80 },
		{ 2615, 6522, 20, 50 }, { 2615, 6523, 1, 80 },
		{ 2615, 6524, 1, 72 }, { 2615, 6525, 1, 80 },
		{ 2615, 6526, 1, 50 }, { 2615, 6527, 1, 45 },
		{ 2615, 6528, 1, 46 }, { 2615, 995, 1000, 45 },
		{ 2616, 6522, 20, 64 }, { 2616, 6523, 1, 60 },
		{ 2616, 6524, 1, 65 }, { 2616, 6525, 1, 50 },
		{ 2616, 6526, 1, 70 }, { 2616, 6527, 1, 40 },
		{ 2616, 6528, 1, 50 }, { 2616, 995, 1, 50 },

		/**
		 * Dungeoneering Drops
		 */

		{ 10736, 526, 1, 0 }, { 10736, Dungeoneering.chooseDFF(), 1, 2 },
		{ 10736, 2434, 1, 1 }, { 10737, 526, 1, 0 },
		{ 10737, Dungeoneering.chooseDFF(), 1, 2 }, { 10737, 2434, 1, 1 },
		{ 10738, 526, 1, 0 }, { 10738, Dungeoneering.chooseDFF(), 1, 2 },
		{ 10738, 2434, 1, 1 }, { 10707, 532, 1, 0 },
		{ 10707, Dungeoneering.chooseDFF(), 1, 2 }, { 10707, 2440, 1, 1 },
		{ 10707, 2436, 1, 1 }, { 10480, 526, 1, 0 },
		{ 10480, Dungeoneering.chooseDFF(), 1, 2 }, { 10480, 2442, 1, 1 },
		{ 10480, Dungeoneering.noviteItem(), 1, 0 }, { 9948, 532, 1, 0 },

		{ 10837, 592, 1, 0 }, { 10837, Dungeoneering.chooseDFF(), 2, 2 },
		{ 10837, 2440, 1, 1 }, { 10837, 2436, 1, 1 }, { 10838, 592, 1, 0 },
		{ 10838, Dungeoneering.chooseDFF(), 2, 2 }, { 10838, 2434, 1, 1 },
		{ 10801, 526, 1, 0 },
		{ 10801, Dungeoneering.fractiteItem(), 1, 3 },
		{ 10801, Dungeoneering.chooseDFF(), 2, 1 }, { 10802, 526, 1, 0 },
		{ 10802, Dungeoneering.zephyriumItem(), 1, 3 },
		{ 10802, Dungeoneering.chooseDFF(), 2, 1 },

		{ 10167, 526, 1, 0 }, { 10167, Dungeoneering.chooseDFF(), 2, 2 },
		{ 10167, 2434, 1, 1 }, { 10462, 526, 1, 0 },
		{ 10462, Dungeoneering.chooseDFF(), 3, 2 }, { 10462, 2440, 1, 1 },
		{ 10462, 2434, 1, 1 }, { 10462, 2436, 1, 1 },
		{ 10699, 526, 1, 0 }, { 10699, Dungeoneering.chooseDFF(), 3, 2 },
		{ 10699, 2440, 1, 1 }, { 10699, 2434, 1, 1 },
		{ 10699, 2436, 1, 1 },

		{ 10756, 526, 1, 0 }, { 10756, Dungeoneering.chooseDFF(), 1, 2 },
		{ 10756, 2434, 1, 1 }, { 10465, 526, 1, 0 },
		{ 10465, Dungeoneering.chooseDFF(), 1, 2 }, { 10465, 2440, 1, 1 },
		{ 10465, 2434, 1, 1 }, { 10465, 2436, 1, 1 }, { 10218, 526, 1, 0 },
		{ 10218, Dungeoneering.chooseDFF(), Misc.random(2), 2 },
		{ 10218, 2442, 1, 1 }, { 10218, 2434, 2, 2 },
		{ 10218, Dungeoneering.zephyriumItem(), 1, 3 },
		{ 10698, 526, 1, 0 }, { 10698, Dungeoneering.chooseDFF(), 1, 2 },
		{ 10698, 2440, 1, 1 }, { 10698, 2434, 1, 1 },
		{ 10698, 2436, 1, 1 },

		{ 10212, 526, 1, 0 }, { 10212, Dungeoneering.chooseDFF(), 1, 2 },
		{ 10212, 2440, 1, 0 }, { 10212, 2434, 1, 1 },
		{ 10212, 18324, 1, 0 }, { 10262, 526, 1, 0 },
		{ 10262, Dungeoneering.chooseDFF(), 3, 2 }, { 10262, 2440, 1, 1 },
		{ 10262, 2434, 1, 1 }, { 10262, 2436, 1, 1 }, { 10308, 526, 1, 0 },
		{ 10308, Dungeoneering.chooseDFF(), 1, 2 }, { 10308, 2440, 1, 1 },
		{ 10308, 2434, 1, 1 }, { 10308, 2436, 1, 1 },

		{ 10840, 526, 1, 0 }, { 10840, Dungeoneering.chooseDFF(), 4, 2 },
		{ 10840, 2440, 1, 1 }, { 10840, 2434, 1, 1 },
		{ 10840, 2436, 1, 1 }, { 10507, 526, 1, 0 },
		{ 10507, Dungeoneering.chooseDFF(), 4, 2 }, { 10507, 2442, 1, 1 },
		{ 10507, 2434, 2, 2 },
		{ 10507, Dungeoneering.zephyriumItem(), 1, 3 },

		{ 10414, 526, 1, 0 }, { 10414, Dungeoneering.chooseDFF(), 1, 2 },
		{ 10414, 2434, 1, 1 }, { 10415, 526, 1, 0 },
		{ 10415, Dungeoneering.chooseDFF(), 2, 1 }, { 10415, 2440, 1, 1 },
		{ 10415, 2436, 1, 1 }, { 10418, 526, 1, 0 },
		{ 10418, Dungeoneering.chooseDFF(), 1, 2 }, { 10418, 2440, 1, 0 },
		{ 10418, 2434, 1, 1 }, { 10419, 526, 1, 0 },
		{ 10419, Dungeoneering.chooseDFF(), 3, 1 }, { 10419, 2440, 1, 1 },
		{ 10419, 2434, 1, 1 }, { 10419, 2436, 1, 1 }, { 10422, 526, 1, 0 },
		{ 10422, Dungeoneering.chooseDFF(), 1, 2 }, { 10422, 2440, 1, 1 },
		{ 10422, 2434, 1, 1 }, { 10422, 2436, 1, 1 }, { 10423, 526, 1, 0 },
		{ 10423, Dungeoneering.chooseDFF(), 2, 2 }, { 10423, 2442, 1, 1 },
		{ 10423, 2434, 2, 2 },
		{ 10423, Dungeoneering.zephyriumItem(), 1, 3 },

		{ 10442, 526, 1, 0 }, { 10442, Dungeoneering.chooseDFF(), 4, 2 },
		{ 10442, 2434, 1, 1 }, { 10443, 526, 1, 0 },
		{ 10443, Dungeoneering.chooseDFF(), 3, 1 }, { 10443, 2440, 1, 1 },
		{ 10443, 2436, 1, 1 }, { 10445, 526, 1, 0 },
		{ 10445, Dungeoneering.chooseDFF(), 2, 2 }, { 10445, 2440, 1, 0 },
		{ 10445, 2434, 1, 1 }, { 10445, 2440, 1, 0 },
		{ 10447, 526, 1, 0 },
		{ 10447, Dungeoneering.chooseDFF(), 3, 1 },
		{ 10447, 2440, 1, 1 },
		{ 10447, 2434, 1, 1 },
		{ 10447, 2436, 1, 1 },
		{ 10448, 526, 1, 0 },
		{ 10448, Dungeoneering.chooseDFF(), 2, 2 },
		{ 10448, 2440, 1, 1 },
		{ 10448, 2434, 1, 1 },
		{ 10448, 2436, 1, 1 },
		{ 10450, 526, 1, 0 },
		{ 10450, Dungeoneering.chooseDFF(), 4, 2 },
		{ 10450, 2442, 1, 1 },
		{ 10450, 2434, 2, 2 },
		{ 10450, Dungeoneering.marmarosItem(), 1, 3 },

		{ 10431, 526, 1, 0 },
		{ 10431, Dungeoneering.chooseDFF(), 8, 2 },
		{ 10431, 2434, 1, 1 },
		{ 10532, 526, 1, 0 },
		{ 10532, Dungeoneering.chooseDFF(), 6, 1 },
		{ 10532, 2440, 1, 1 },
		{ 10532, 2436, 1, 1 },
		{ 10531, 526, 1, 0 },
		{ 10531, Dungeoneering.chooseDFF(), 5, 2 },
		{ 10531, 2440, 1, 0 },
		{ 10531, 2434, 2, 1 },
		{ 10531, 2436, 1, 1 },
		{ 10534, 526, 1, 0 },
		{ 10534, Dungeoneering.chooseDFF(), 3, 1 },
		{ 10534, 2440, 1, 1 },
		{ 10534, 2434, 1, 1 },
		{ 10534, 2436, 1, 1 },
		{ 10520, 526, 1, 0 },
		{ 10520, Dungeoneering.chooseDFF(), 5, 2 },
		{ 10520, 2440, 1, 1 },
		{ 10520, 2434, 2, 1 },
		{ 10520, 2436, 1, 1 },
		{ 10522, 526, 1, 0 },
		{ 10522, Dungeoneering.chooseDFF(), 4, 2 },
		{ 10522, 2442, 1, 1 },
		{ 10522, 2434, 2, 2 },
		{ 10522, Dungeoneering.marmarosItem(), 1, 3 },

		// TDS

		{ 8349, 592, 1, 0 }, { 8350, 592, 1, 0 }, { 8351, 592, 1, 0 },

		{ 8349, 995, 250000, 4 }, { 8350, 995, 250000, 4 },
		{ 8351, 995, 250000, 4 },

		{ 8349, 385, 7, 5 }, { 8350, 385, 7, 5 }, { 8351, 385, 7, 5 },

		{ 8349, 560, 100, 3 }, { 8350, 560, 100, 3 },
		{ 8351, 560, 100, 3 },

		{ 8349, 565, 100, 3 }, { 8350, 565, 100, 3 },
		{ 8351, 565, 100, 3 },

		{ 8349, 562, 100, 3 }, { 8350, 562, 100, 3 },
		{ 8351, 562, 100, 3 },

		{ 8349, 1333, 1, 7 }, { 8350, 1333, 1, 7 }, { 8351, 1333, 1, 7 },

		{ 8349, 1185, 1, 9 }, { 8350, 1185, 1, 9 }, { 8351, 1185, 1, 9 },

		{ 8349, 4087, 1, 21 }, { 8350, 4087, 1, 21 },
		{ 8351, 4087, 1, 21 },

		{ 8349, 4585, 1, 21 }, { 8350, 4585, 1, 21 },
		{ 8351, 4585, 1, 21 },

		{ 8349, 14472, 1, 100 }, { 8350, 14472, 1, 100 },
		{ 8351, 14472, 1, 100 },

		{ 8349, 14474, 1, 100 }, { 8350, 14474, 1, 100 },
		{ 8351, 14474, 1, 100 },

		{ 8349, 14476, 1, 100 }, { 8350, 14476, 1, 100 },
		{ 8351, 14476, 1, 100 },

		{ 8349, 14484, 1, 150 }, { 8350, 14484, 1, 120 },
		{ 8351, 14484, 1, 150 },

		//Revenants
		{ 13479, 592, 1, 0 }, { 13479, 13858, 1, 220 },
		{ 13479, 13861, 1, 220 }, { 13479, 13864, 1, 220 },
		{ 13479, 13867, 1, 220 }, { 13479, 13870, 1, 220 },
		{ 13479, 13873, 1, 220 }, { 13479, 13876, 1, 220 },
		{ 13479, 13884, 1, 190 }, { 13479, 13887, 1, 320 },
		{ 13479, 13890, 1, 190 }, { 13479, 13893, 1, 150 },
		{ 13479, 13896, 1, 190 }, { 13479, 13899, 1, 170 },
		{ 13479, 13902, 1, 120 }, { 13479, 13905, 1, 150 },

		{ 13480, 592, 1, 0 }, { 13480, 13858, 1, 200 },
		{ 13480, 13861, 1, 220 }, { 13480, 13864, 1, 190 },
		{ 13480, 13867, 1, 190 }, { 13480, 13870, 1, 190 },
		{ 13480, 13873, 1, 220 }, { 13480, 13876, 1, 190 },
		{ 13480, 13884, 1, 190 }, { 13480, 13887, 1, 190 },
		{ 13480, 13890, 1, 220 }, { 13480, 13893, 1, 190 },
		{ 13480, 13896, 1, 220 }, { 13480, 13899, 1, 190 },
		{ 13480, 13902, 1, 220 }, { 13480, 13905, 1, 220 },

	};

}
