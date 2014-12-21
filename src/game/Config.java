package game;

import engine.util.Misc;

public class Config {

	/** Debugging/Misc. Config **/
	public static boolean SERVER_DEBUG = false; // Enable's server debugging
												// mode for Developer+
	public static final boolean WORLD_LIST_FIX = false; // change to 'true' if
														// you want to stop that
														// world--8

	/** Server Messaging Config **/
	public static final String SERVER_NAME = "Destroscape";
	public static final String WELCOME_MESSAGE = "Welcome to @red@"+Config.SERVER_NAME+"!";
	public static int MESSAGE_DELAY = 6000; // delay with server message via
	public static final int CLIENT_UID = 2856789;
											// in-game text box.

	/**
	 * URLS
	 */

	public static final String HOMEPAGE_URL = "www.destroscape.com";
	public static final String FORUM_URL = "www.destroscape.com/forum";
	public static final String VOTE_URL = "www.destroscape.com/vote";
	public static final String DONATE_URL = "https://www.paypal.com/uk/cgi-bin/webscr?cmd=_flow&SESSION=RrsWkePycitasNSFf78lbCvBSGTk036oL7nblMz4lZM_LRjwbvRHMOKkB_m&dispatch=5885d80a13c0db1f8e263663d3faee8d66f31424b43e9a70645c907a6cbd8fb4";
	public static final String SUGGESTIONS_URL = "http://destroscape.com/index.php?/forum/14-suggestions/";
	public static final String UPDATES_URL = "www.destroscape.com/index.php?/forum/9-updates/";
	public static final String HIGHSCORES_URL = "www.destroscape.com/Highscores";


	/**
	 * Choose exp
	 */
	public static final String XP_NOVICE = "novice";
	public static final String XP_EXPERT = "expert";
	public static final String XP_LEGEND = "legend";
	public static final String XP_NONE = "none";


	/**
	 * Sound
	 */

	public static final boolean SOUND = true;

	/** Client Config **/
	public static final int CLIENT_VERSION = 317; // our client build
	public static final int CONNECTION_DELAY = 500; // how long one ip can keep
													// connecting.
	public static final int IPS_ALLOWED = 1; // how many ips are allowed
	public static final int TIMEOUT = 20; //
	public static final int CYCLE_TIME = 600; // how long it takes to complete
												// one cycle (600 default)
	public static final int BUFFER_SIZE = 10000; //
	public static final int[] IMMEDIATE_PROCESS_PACKET_IDS = { // Need to
																// implement
			72, 131, // Attack NPC
			73, 249, // Attack PLAYER
			122, // Click ITEM
			185, // Clicking BUTTONS
			214, // Move ITEMS
			41, // Wear ITEM
			145, // Remove ITEM
	};
	
	/**
	 * Yelling System
	 */
	public static String[] BADTAGS = {
		"add",
		"banned",
		"tags",
		"yourself",
		":awesomeface:",
		"owner",
		"Owner",
		"Mod",
		"mod",
		"Moderator",
		"moderator",
		"Admin",
		"admin",
		"Administrator",
		"administrator"
	};
	
	/** Beta Mode TOGGLE **/
	public static boolean BETA_MODE = false;

	/** Player Config **/
	public static final int MAX_PLAYERS = 2001; // change height according to
												// average amount of players
												// online, Peak average.
	public static final int SAVE_TIMER = 3600; // save every 30 minute
	// public static final boolean ALLOWPINS = true; // turn bank pin system
	// on/off
	public static final int ITEM_LIMIT = 25000; // item ID limit.
	public static final int MAXITEM_AMOUNT = Integer.MAX_VALUE; // maximum
																// Amount of one
																// item a player
																// can have
	public static final int BANK_SIZE = 352; // size of bank
	// public static final int POT_DELAY = 120; // potion consumption delay
	public static final int FOOD_DELAY = 180; // food consumption delay
	public static final boolean PRAYER_POINTS_REQUIRED = true; // you need
																// prayer points
																// to use prayer
	public static boolean IDLE_LOGOUT = true; // Do players log out when
												// IDLEing?

	/** Player Spawn Config **/
	public static final int START_LOCATION_X = 3087; // X Co-ordinate of
														// starting location
	public static final int START_LOCATION_Y = 3504; // Y Co-ordinate of
														// starting location
	public static final int START_HEIGHT_LEVEL = 0; // height level of starting
													// location
	public static final int START_PLAYER_RIGHT = 0; // player right new players
													// have when they first log
													// in
	// public static boolean CHARACTER_CREATION = true; // do players select
	// their look when they first log-in?
	public static final int RESPAWN_X = Config.HOME_X;
	public static final int RESPAWN_Y = Config.HOME_Y;
	public static final int DUELING_RESPAWN_X = 3362; // duel X
	public static final int DUELING_RESPAWN_Y = 3263; // duel Y
	public static final int RANDOM_DUELING_RESPAWN = +Misc.random(5); // random																		// co-ordinates

	/** NPC Config **/
	public static final int NPC_RANDOM_WALK_DISTANCE = 15; // the square created
															// , 3x3 so npc
															// can't move out of
															// that box when
															// randomly walking
	public static final int NPC_FOLLOW_DISTANCE = 10; // how far can the npc
														// follow you from it's
														// spawn point,
	public static final int[] UNDEAD_NPCS = { 90, 91, 92, 93, 94, 103, 104, 73,
			74, 75, 76, 77 }; // undead npcs

	/** Combat **/
	public static final int NO_TELEPORT_WILD_LEVEL = 20; // level you can't tele
															// on and above
	public static final int SKULL_TIMER = 2400; // how long does the skull last?
												// seconds x 2
	public static final boolean SINGLE_AND_MULTI_ZONES = true; // multi and
																// single zones?
	public static final boolean COMBAT_LEVEL_DIFFERENCE = true; // wildy levels
																// and combat
																// level
																// differences
																// matters
	public static final boolean itemRequirements = true; // attack, def, str,
															// range or magic
															// levels required
															// to wield weapons
															// or wear items?
	public static final boolean PRAYER_LEVEL_REQUIRED = true; // need prayer
																// level to use
																// different
																// prayers

	public static final boolean CORRECT_ARROWS = true; // correct arrows for
														// bows?
	public static final boolean CRYSTAL_BOW_DEGRADES = true; // magic rune
																// required?
	public final static int[] BOWS = { 9185, 18357, 839, 845, 847, 851, 855,
			859, 841, 843, 849, 853, 857, 861, 4212, 4214, 4215, 11235, 15701,
			15702, 15703, 15704, 4216, 4217, 4218, 4219, 4220, 4221, 4222,
			4223, 6724, 4734, 4934, 4935, 4936, 4937 , 20171};
	public final static int[] ARROWS = { 882, 884, 886, 888, 890, 892, 4740,
			11212, 9140, 9141, 4142, 9143, 9144, 9240, 9241, 9242, 9243, 9244,
			9245 };
	public final static int[] NO_ARROW_DROP = { 4212, 4214, 4215, 4216, 4217,
			4218, 4219, 4220, 4221, 4222, 4223, 4734, 4934, 4935, 4936, 4937, 20171 };
	public final static int[] OTHER_RANGE_WEAPONS = { 863, 864, 865, 866, 867,
			868, 869, 806, 807, 808, 809, 810, 811, 825, 826, 827, 828, 829,
			830, 800, 801, 802, 803, 804, 805, 6522, 3093,11230 };

	public static final int TELEBLOCK_DELAY = 500; // how long does teleblock
													// last for.
	public static final boolean MAGIC_LEVEL_REQUIRED = true; // need magic level
																// to cast spell
	public static final int GOD_SPELL_CHARGE = 300000; // how long does god
														// spell charge last?
	public static final boolean RUNES_REQUIRED = true; // magic rune required?
	public final static int[] REDUCE_SPELLS = { 1153, 1157, 1161, 1542, 1543,
			1562 };
	public final static int[] REDUCE_SPELL_TIME = { // how long does the other
													// player stay immune to the
													// spell
			250000, 250000, 250000, 500000, 500000, 500000 };

	   public static final int[] NON_ATTAKABLE_NPCS = new int[] { 
	        8923, 599, 2286, 555, 3021, 700, 541, 545, 557, 461, 6971, 550, 
	        556, 1597, 2282, 211, 246, 494, 2566, 3789, 1696, 495, 700, 2258, 
	        6971, 1035, 5082, 5083, 6063, 1028, 5084, 5085
	    }; 	

/** Item Config **/
	// what items can't be sold in any store
	public static final int[] ITEM_SELLABLE = { 560, 565, 566, 561, 9075, 7406, 7405, 7404, 10328, 10329, 19308, 19309, 19310, 19311, 19312, 19313, 19314, 19315, 19316, 19317, 
			19318, 19319, 19320, 19321, 19322, 
			18346, 2363, 2364, 18357, 10330, 10331, 10332, 10333,
			10334, 10335, 10336, 10337, 10338, 10339, 10340, 10341, 10342, 10343, 10344, 10345,
			10346, 10347, 10348, 10349, 10350, 10351, 10352, 10353,
			19669, 18359, 18363, 18361, 18334, 18335, 19893, 18333, 18347,
			18349, 18351, 18353, 18355, 3842, 3844, 3840, 8844, 8845, 8846,
			8847, 8848, 8849, 8850, 20072, 10551, 6570, 7462, 7461, 7460, 7459,
			7458, 7457, 7456, 7455, 7454, 8839, 8840, 8842, 11663, 11664,
			13263, 11665, 10499, 9798, 9804, 9780, 9795, 9792, 9774, 9771,
			9777, 9786, 9810, 9765, 995, 9748, 9754, 9751, 9769, 9757, 9760,
			9763, 9802, 12169, 12171, 12170, 9808, 9784, 9799, 9805, 9781,
			9796, 9793, 9775, 9772, 9778, 9787, 9811, 9766, 9749, 9755, 9752,
			9770, 9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806, 9782, 9797,
			9794, 9776, 9773, 9779, 9788, 9812, 9767, 9747, 9753, 9750, 9768,
			9756, 9759, 9762, 9801, 9807, 9783, 12158, 12159, 12160, 12161, 20747, 20767, 20751, 20768, 
			12162, 12163, 12164, 12165, 12166, 12167, 12168, 15492, 8839, 8840,
			8842, 11663, 11664, 12169, 12171, 11665, 10499, 15441, 15442,
			15443, 15444, 15701, 15702, 15703, 15704, 9747, 9753, 9750, 9768,
			9756, 9759, 9762, 9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792,
			9774, 9771, 9777, 9786, 9810, 9765, 9948, 12169, 15086, 15088, 15090,
			15092, 15094, 15096, 15098, 15100, 19712 };
	// what items can't be traded or staked to players
	public static final int[] ITEM_TRADEABLE = { 7406, 7405, 7404, 10328, 10329, 9747, 9753, 9750, 9768, 9756,
			9759, 9762, 9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792, 9774,
			9771, 9777, 9786, 9810, 9765, 9948, 12169, 18346, 18357, 19669,
			18359, 18363, 18361, 18334, 18335, 19893, 18333, 18347, 18349,
			18351, 18353, 18355, 3842, 3844, 3840, 8844, 8845, 8846, 8847,
			8848, 8849, 8850, 20072, 9798, 9804, 9780, 9795, 9792, 9774, 13263,
			9771, 9777, 9786, 9810, 9765, 7454, 8839, 8840, 8842, 11663, 11664,
			12169, 12171, 11665, 10499, 9748, 9754, 9751, 9769, 9757, 9760,
			9763, 9802, 9808, 9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772,
			12170, 9778, 9787, 9811, 9766, 9800, 9806, 9782,
			9797, 9794, 9776, 9773, 9779, 9788, 9812, 9767, 9749, 9755, 9752,
			9770, 9758, 9761, 9764, 9803, 9809, 9785, 10551, 6570, 7462, 7461,
			7460, 7459, 7458, 7457, 7456, 7455, 9747, 9753, 9750, 9768, 9756,
			9759, 9762, 9801, 9807, 9783, 1508, 15492, 15441, 15442, 15443,
			15444, 15701, 15702, 15703, 15704, 9948, 12169, 14001, 14002, 20747, 20767, 20751, 20768, 
			14003, 14004, 14005, 14006 ,14007, 14008, 14009 ,14010, 15086, 15088, 15090, 15092, 15094,
			15096, 15098, 15100, 19712, 6570};
	//18778,
	//18779, 18780, 18781, 18782,
	// what items can't be dropped on ground
	public static final int[] UNDROPPABLE_ITEMS = { 7406, 7405, 7404, 10328, 10329, 1508, 15086, 15088, 15090, 15092, 
		15094, 15096, 15098, 15100, 7454, 7456, 7458, 7460, 7462, 6570, 20747, 20767, 20751, 20768,  };
	// fun weapons for dueling
	public static final int[] FUN_WEAPONS = { 2460, 2461, 2462, 2463, 2464,
			2465, 2466, 2467, 2468, 2469, 2470, 2471, 2471, 2473, 2474, 2475,
			2476, 2477 };

	/** Slayer Config **/
	public static final int[][] SLAYER_TASKS = { { 1, 87, 90, 4, 5 }, // low
																		// tasks
			{ 6, 7, 8, 9, 10 }, // med tasks
			{ 11, 12, 13, 14, 15 }, // high tasks
			{ 1, 1, 15, 20, 25 }, // low reqs
			{ 30, 35, 40, 45, 50 }, // med reqs
			{ 60, 75, 80, 85, 90 } // high reqs
	};

	public static final int[] QuestInterface = { 8145, 8147, 8148, 8149, 8150,
			8151, 8152, 8153, 8154, 8155, 8156, 8157, 8158, 8159, 8160, 8161,
			8162, 8163, 8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171, 8172,
			8173, 8174, 8175, 8176, 8177, 8178, 8179, 8180, 8181, 8182, 8183,
			8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193, 8194,
			8195, 12174, 12175, 12176, 12177, 12178, 12179, 12180, 12181,
			12182, 12183, 12184, 12185, 12186, 12187, 12188, 12189, 12190,
			12191, 12192, 12193, 12194, 12195, 12196, 12197, 12198, 12199,
			12200, 12201, 12202, 12203, 12204, 12205, 12206, 12207, 12208,
			12209, 12210, 12211, 12212, 12213, 12214, 12215, 12216, 12217,
			12218, 12219, 12220, 12221, 12222, 12223 };

	/** Experience Config **/
	/* EXP rates */
	public static double BRAWLER = 0.5; // XP bonuses
	public static double SERVER_EXP_BONUS = 1; // XP bonuses
	public static boolean doubleEXPWeekend = false;
	public static final int MELEE_EXP_RATE = 1000; // damage * exp rate.
	public static final int RANGE_EXP_RATE = 900; // damage * exp rate.
	public static final int MAGIC_EXP_RATE = 900; // damage * exp rate.

	/** Teleport Config **/
	// amulet of glory
	public static final int HOME_X = 3087;
	public static final int HOME_Y = 3504;

	public static final int EDGEVILLE_X = 3087; // X
	public static final int EDGEVILLE_Y = 3500; // Y
	public static final String EDGEVILLE = ""; // name of teleport
	public static final int AL_KHARID_X = 3293; // X
	public static final int AL_KHARID_Y = 3174; // Y
	public static final String AL_KHARID = ""; // name of teleport
	public static final int KARAMJA_X = 2956; // X
	public static final int KARAMJA_Y = 3146; // Y
	public static final String KARAMJA = ""; // name of teleport
	public static final int MAGEBANK_X = 2538; // X
	public static final int MAGEBANK_Y = 4716; // Y
	public static final String MAGEBANK = ""; // name of teleport
	// ring of dueling
	// modern
	public static final int VARROCK_X = 3210;
	public static final int VARROCK_Y = 3424;
	public static final String VARROCK = "";
	public static final int LUMBY_X = 3222;
	public static final int LUMBY_Y = 3218;
	public static final String LUMBY = "";
	public static final int FALADOR_X = 2964;
	public static final int FALADOR_Y = 3378;
	public static final String FALADOR = "";
	public static final int CAMELOT_X = 2757;
	public static final int CAMELOT_Y = 3477;
	public static final String CAMELOT = "";
	public static final int ARDOUGNE_X = 2662;
	public static final int ARDOUGNE_Y = 3305;
	public static final String ARDOUGNE = "";
	public static final int WATCHTOWER_X = 3087;
	public static final int WATCHTOWER_Y = 3500;
	public static final String WATCHTOWER = "";
	public static final int TROLLHEIM_X = 3243;
	public static final int TROLLHEIM_Y = 3513;
	public static final String TROLLHEIM = "";
	// ancient
	public static final int PADDEWWA_X = 3098;
	public static final int PADDEWWA_Y = 9884;
	public static final int SENNTISTEN_X = 3322;
	public static final int SENNTISTEN_Y = 3336;
	public static final int KHARYRLL_X = 3492;
	public static final int KHARYRLL_Y = 3471;
	public static final int LASSAR_X = 3006;
	public static final int LASSAR_Y = 3471;
	public static final int DAREEYAK_X = 3161;
	public static final int DAREEYAK_Y = 3671;
	public static final int CARRALLANGAR_X = 3156;
	public static final int CARRALLANGAR_Y = 3666;
	public static final int ANNAKARL_X = 3288;
	public static final int ANNAKARL_Y = 3886;
	public static final int GHORROCK_X = 2977;
	public static final int GHORROCK_Y = 3873;
	// lunar

	/** Player - Item Transfer Options **/
	// Regular Player
	public static final boolean REGULAR_CAN_TRADE_ITEMS = true;
	public static final boolean REGULAR_CAN_SELL_ITEMS = true;
	public static final boolean REGULAR_DROP_ITEMS = true;
	// Donator
	public static final boolean DONATOR_CAN_TRADE_ITEMS = true;
	public static final boolean DONATOR_CAN_SELL_ITEMS = true;
	public static final boolean DONATOR_DROP_ITEMS = true;
	// Super Donator
	public static final boolean SUPDONATOR_CAN_TRADE_ITEMS = true;
	public static final boolean SUPDONATOR_CAN_SELL_ITEMS = true;
	public static final boolean SUPDONATOR_DROP_ITEMS = true;
	// Moderator
	public static final boolean MODERATOR_CAN_TRADE_ITEMS = true;
	public static final boolean MODERATOR_CAN_SELL_ITEMS = true;
	public static final boolean MODERATOR_DROP_ITEMS = true;
	// Developer
	public static final boolean DEVELOPER_CAN_TRADE_ITEMS = true;
	public static final boolean DEVELOPER_CAN_SELL_ITEMS = true;
	public static final boolean DEVELOPER_DROP_ITEMS = true;
	// Co-Onwer
	public static final boolean COOWNER_CAN_TRADE_ITEMS = true;
	public static final boolean COOWNER_CAN_SELL_ITEMS = true;
	public static final boolean COOWNER_DROP_ITEMS = true;
	// Owner
	public static final boolean OWNER_CAN_TRADE_ITEMS = true;
	public static final boolean OWNER_CAN_SELL_ITEMS = true;
	public static final boolean OWNER_DROP_ITEMS = true;
	public static final int INCREASE_SPECIAL_AMOUNT = 30000;
	
	public static final boolean PARTYROOM_DROP_DELAY = false;
	public static final boolean PARTYROOM_DISABLED = false;
	public static final boolean PARTYROOM_ADMINS_ONLY = true;
	
	public static final int[] ITEMS_KEPT_ON_DEATH = {15086,15088,15090,15092,15094,15096,15098,15100};
	
	public static final boolean DUNGEONEERING_ACCESSIBLE = true;

}
