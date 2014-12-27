package game.skill.summoning;

import game.entity.player.Player;

public class PouchCreation {

	private static final int ANIMATION = 725, GFX = 1207;
	private static final int POUCH = 12155, SHARD = 12183;

	/**
	 * Methods
	 **/

	private static final String[][] summoningPouchData = {
		// Pouch id, pouch charm, item1, Shardamount, LVL, Spec scroll
		{ "Spirit wolf pouch", "Gold Charm", "Wolf bones", "7", "1",
			"Howl scroll", "5" },
			{ "Dreadfowl pouch", "Gold Charm", "Raw chicken", "8", "4",
				"Dreadfowl strike scroll", "9" },
				{ "Spirit spider pouch", "Gold Charm", "Spider carcass", "8", "10",
					"Egg spawn scroll", "13" },
					{ "Thorny Snail pouch", "Gold Charm", "Thin snail meat", "9", "13",
						"Slime spray scroll", "13" },
						{ "Granite Crab pouch", "Gold Charm", "Iron ore", "7", "16",
							"Stony shell scroll", "22" },
							{ "Spirit Mosquito pouch", "Gold Charm", "Proboscis", "1", "17",
								"Pester scroll", "47" },
								{ "Desert wyrm pouch", "Green Charm", "Bucket of sand", "45", "18",
									"Electric lash scroll", "31" },
									{ "Spirit Scorpion pouch", "Crimson Charm", "Bronze claws", "57",
										"19", "Venom shot scroll", "83" },
										{ "Spirit tz-kih pouch", "crimson charm", "Obsidian charm", "64",
											"22", "Fireball assault scroll", "97" },
											{ "Albino rat pouch", "Blue Charm", "Raw rat meat", "75", "23",
												"Cheese feast scroll", "202" },
												{ "Spirit kalphite pouch", "blue Charm", "potato cactus", "51",
													"25", "Sandstorm scroll", "220" },
													{ "Compost mound pouch", "Green charm", "compost", "47", "28",
														"Generate compost scroll", "50" },
														{ "Giant chinchompa pouch", "Blue Charm", "Chinchompa", "84", "29",
															"Explode scroll", "255" },
															{ "Vampire bat pouch", "Crimson Charm", "Vampire dust", "81", "31",
																"Vampire touch scroll", "136" },
																{ "Honey badger pouch", "Crimson Charm", "Honeycomb", "84", "32",
																	"Insane ferocity scroll", "141" },
																	{ "Beaver pouch", "Green Charm", "Willow logs", "72", "33",
																		"Multichop scroll", "58" },
																		{ "Void ravager pouch", "green Charm", "Ravager Charm", "74", "34",
																			"Call to arms scroll", "60" },
																			{ "Void shifter pouch", "blue charm", "Shifter charm", "74", "34",
																				"Call to arms scroll", "60" },
																				{ "void spinner pouch", "blue Charm", "spinner Charm", "74", "34",
																					"Call to arms scroll", "60" },
																					{ "Void Torcher pouch", "blue Charm", "Torcher Charm", "74", "34",
																						"Call to arms scroll", "60" },
																						{ "Bronze minotaur pouch", "Blue Charm", "Bronze bar", "102", "36",
																							"Bronze bull rush scroll", "317" },
																							{ "Bull ant pouch", "gold Charm", "Marigolds", "11", "40",
																								"Unburden scroll", "53" },
																								{ "Macaw pouch", "green Charm", "Clean guam", "78", "41",
																									"Herbcall scroll", "72" },
																									{ "Evil turnip pouch", "crimson Charm", "Carved evil turnip",
																										"104", "42", "Evil flames scroll", "185" },
																										{ "Spirit Cockatrice pouch", "Green Charm", "Bird Egg", "88", "43",
																											"Petrifying gaze scroll", "75" },
																											{ "Spirit Guthatrice pouch", "Green Charm", "Bird Egg", "88", "43",
																												"Petrifying gaze scroll", "75" },
																												{ "Spirit Saratrice pouch", "Green Charm", "Bird Egg", "88", "43",
																													"Petrifying gaze scroll", "75" },
																													{ "Spirit Zamatrice pouch", "Green Charm", "Bird Egg", "88", "43",
																														"Petrifying gaze scroll", "75" },
																														{ "Spirit Pengatrice pouch", "Green Charm", "Bird Egg", "88", "43",
																															"Petrifying gaze scroll", "75" },
																															{ "Spirit Coraxatrice pouch", "Green Charm", "Bird Egg", "88",
																																"43", "Petrifying gaze scroll", "75" },
																																{ "Spirit Vulatrice pouch", "Green Charm", "Bird Egg", "88", "43",
																																	"Petrifying gaze scroll", "75" },
																																	{ "Iron minotaur pouch", "Blue Charm", "Iron bar", "125", "46",
																																		"Iron bull rush scroll", "405" },
																																		{ "Pyrelord pouch", "Crimson Charm", "Lava rune", "111", "46",
																																			"Immense heat scroll", "202" },
																																			{ "Magpie pouch", "green Charm", "Gold ring", "88", "47",
																																				"Thieving fingers scroll", "83" },
																																				{ "Bloated leech pouch", "Crimson Charm", "Raw beef", "117", "49",
																																					"Blood drain scroll", "215" },
																																					{ "Spirit terrorbird pouch", "Gold Charm", "Raw bird meat", "12",
																																						"52", "Tireless run scroll", "68" },
																																						{ "Abyssal parasite pouch", "green Charm", "Abyssal charm", "106",
																																							"54", "Abyssal drain scroll", "95" },
																																							{ "Spirit jelly pouch", "blue Charm", "Jug of water", "151", "55",
																																								"Dissolve scroll", "484" },
																																								{ "Steel minotaur pouch", "blue Charm", "steel bar", "141", "56",
																																									"Steel bull rush scroll", "493" },
																																									{ "Ibis pouch", "green Charm", "Harpoon", "109", "56",
																																										"Fish rain scroll", "99" },
																																										{ "Spirit Kyatt pouch", "blue Charm", "Kyatt fur", "153", "57",
																																											"Ambush scroll", "502" },
																																											{ "Spirit larupia pouch", "blue Charm", "larupia fur", "155", "57",
																																												"Rending scroll", "502" },
																																												{ "Spirit Graahk pouch", "blue Charm", "graahk fur", "154", "57",
																																													"Goad scroll", "502" },
																																													{ "Karamthulhu overlord pouch", "blue Charm", "Fishbowl", "144",
																																														"58", "Doomsphere scroll", "510" },
																																														{ "Smoke devil pouch", "Crimson Charm", "Goat horn dust", "141",
																																															"61", "Dust cloud scroll", "268" },
																																															{ "Abyssal lurker pouch", "green Charm", "Abyssal charm", "119",
																																																"62", "Abyssal stealth scroll", "110" },
																																																{ "Spirit cobra pouch", "Crimson Charm", "Snake over-cooked",
																																																	"116", "63", "Ophidian incubation scroll", "277" },
																																																	{ "Stranger plant pouch", "Crimson Charm", "Bagged plant 1", "128",
																																																		"64", "Poisonous blast scroll", "282" },
																																																		{ "Mithril minotaur pouch", "Blue Charm", "Mithril bar", "152",
																																																			"66", "Mith bull rush scroll", "581" },
																																																			{ "Barker toad pouch", "Gold Charm", "Swamp toad", "11", "66",
																																																				"Toad bark scroll", "87" },
																																																				{ "War tortoise pouch", "Gold Charm", "Tortoise shell", "1", "67",
																																																					"Testudo scroll", "59" },
																																																					{ "Bunyip pouch", "Green Charm", "Raw shark", "110", "68",
																																																						"Swallow whole scroll", "119" },
																																																						{ "Fruit bat pouch", "Green Charm", "Banana", "130", "69",
																																																							"Fruitfall scroll", "121" },
																																																							{ "Ravenous Locust pouch", "Crimson Charm", "Pot of Flour", "79",
																																																								"70", "Famine scroll", "132" },
																																																								{ "Arctic bear pouch", "Gold Charm", "Polar kebbit fur", "14",
																																																									"71", "Arctic blast scroll", "93" },
																																																									{ "Phoenix pouch", "Crimson Charm", "Phoenix Quill", "165", "72",
																																																										"Rise from the ashes scroll", "301" },
																																																										{ "Obsidian Golem pouch", "Blue Charm", "Obsidian Charm", "195",
																																																											"73", "Volcanic strength scroll", "642" },
																																																											{ "Granite lobster pouch", "Crimson Charm", "Granite (500g)",
																																																												"166", "74", "Crushing claw scroll", "326" },
																																																												{ "Praying mantis pouch", "Crimson Charm", "Flowers", "168", "75",
																																																													"Mantis strike scroll", "330" },
																																																													{ "Forge Regent pouch", "Green Charm", "Ruby harvest", "141", "76",
																																																														"Inferno scroll", "134" },
																																																														{ "Adamant minotaur pouch", "Blue Charm", "Adamant Bar", "144",
																																																															"76", "Addy bull rush scroll", "669" },
																																																															{ "Talon Beast pouch", "Crimson Charm", "Talon Beast charm", "174",
																																																																"77", "Deadly claw scroll", "1015" },
																																																																{ "Giant ent pouch", "Green Charm", "Willow branch", "124", "78",
																																																																	"Acorn missile scroll", "137" },
																																																																	{ "Fire titan pouch", "Blue Charm", "Fire talisman", "198", "79",
																																																																		"Titan's con. scroll", "695" },
																																																																		{ "Moss titan pouch", "Blue Charm", "Earth talisman", "202", "79",
																																																																			"Titan's con. scroll", "695" },
																																																																			{ "Ice titan pouch", "Blue Charm", "Water talisman", "198", "79",
																																																																				"Titan's con. scroll", "695" },
																																																																				{ "Hydra pouch", "Green Charm", "Water orb", "128", "80",
																																																																					"Regrowth scroll", "141" },
																																																																					{ "Spirit dagannoth pouch", "Crimson Charm", "Dagannoth hide", "1",
																																																																						"83", "Spike shot scroll", "365" },
																																																																						{ "Lava titan pouch", "Blue Charm", "Obsidian Charm", "219", "83",
																																																																							"Ebon thunder scroll", "730" },
																																																																							{ "Swamp titan pouch", "Crimson charm", "Swamp lizard", "150", "85",
																																																																								"Swamp plague scroll", "374" },
																																																																								{ "Rune minotaur pouch", "Blue Charm", "Rune bar", "1", "86",
																																																																									"Rune bull rush scroll", "757" },
																																																																									{ "Unicorn stallion pouch", "green Charm", "Unicorn Horn", "140",
																																																																										"88", "Healing aura scroll", "154" },
																																																																										{ "Geyser titan pouch", "blue Charm", "Water talisman", "222",
																																																																											"89", "Boil scroll", "783" },
																																																																											{ "Wolpertinger pouch", "crimson Charm", "Cooked rabbit", "203",
																																																																												"92", "Magic focus scroll", "405" },
																																																																												{ "Abyssal titan pouch", "green Charm", "Abyssal charm", "113",
																																																																													"93", "Essence shipment scroll", "163" },
																																																																													{ "Iron titan pouch", "crimson Charm", "Iron platebody", "198",
																																																																														"95", "Iron within scroll", "418" },
																																																																														{ "Pack yak pouch", "Crimson Charm", "Yak-hide", "211", "96",
																																																																															"Winter storage scroll", "422" },
																																																																															{ "Steel titan pouch", "Blue Charm", "Steel platebody", "178",
																																																																																"99", "Steel of legends scroll", "435" },

	};

	/**
	 * Handles Items You Need To Create A Pouch
	 **/

	public static boolean NEED(Player c, int i) {
		try {
			return c.getItems().playerHasItem(POUCH)
					&& c.getItems().playerHasItem(SHARD,
							Integer.parseInt(summoningPouchData[i][3]))
							&& c.getItems().playerHasItem(
									c.getItems().getItemId(summoningPouchData[i][1]))
									&& c.getItems().playerHasItem(
											c.getItems().getItemId(summoningPouchData[i][2]));
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}

	private static boolean hasPouch(Player c, int i) {
		try {
			return c.getItems().playerHasItem(
					c.getItems().getItemId(summoningPouchData[i][0]), 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}

	/**
	 * Handles Making The Pouch
	 **/

	public static void makeSummoningPouch(Player c, int buttonId) {
		try {
			int i = (buttonId - 155031) / 3;
			if (NEED(c, i)) {
				if (c.getLevelForXP(c.playerXP[22]) >= Integer
						.parseInt(summoningPouchData[i][4])) {
					c.getItems().deleteItem2(POUCH, 1);
					c.getItems().deleteItem2(SHARD,
							Integer.parseInt(summoningPouchData[i][3]));
					c.getItems()
					.deleteItem2(
							c.getItems().getItemId(
									summoningPouchData[i][1]), 1);
					c.getItems()
					.deleteItem2(
							c.getItems().getItemId(
									summoningPouchData[i][2]), 1);
					c.getItems()
					.addItem(
							c.getItems().getItemId(
									summoningPouchData[i][0]), 1);
					if (c.isPVPActive == true) {
						c.getPA()
						.addSkillXP(
								Integer.parseInt(summoningPouchData[i][6]) * 20 * 2,
								22);
					} else {
						c.getPA()
						.addSkillXP(
								Integer.parseInt(summoningPouchData[i][6]) * 20,
								22);
					}
				} else {
					c.sendMessage("You need a Summoning level of "
							+ summoningPouchData[i][4] + ".");
				}
			} else {
				c.sendMessage("You need 1 " + summoningPouchData[i][1] + ", 1 "
						+ summoningPouchData[i][2] + ", "
						+ summoningPouchData[i][3]
								+ " shards, and 1 pouch to make this.");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
	}

	public static void makeMorePouches(final Player c, int button) {
		switch (button) {
		case 156000:
			if (NEED(c, 75)) {
				if (c.getLevelForXP(c.playerXP[22]) >= Integer
						.parseInt(summoningPouchData[75][4])) {
					c.getItems().deleteItem2(POUCH, 1);
					c.getItems().deleteItem2(SHARD,
							Integer.parseInt(summoningPouchData[75][3]));
					c.getItems().deleteItem2(
							c.getItems().getItemId(summoningPouchData[75][1]),
							1);
					c.getItems().deleteItem2(
							c.getItems().getItemId(summoningPouchData[75][2]),
							1);
					c.getItems().addItem(
							c.getItems().getItemId(summoningPouchData[75][0]),
							1);
					if (c.isPVPActive == true) {
						c.getPA().addSkillXP(
								Integer.parseInt(summoningPouchData[75][6]) * 20 * 2,
								22);
					} else {
						c.getPA().addSkillXP(
								Integer.parseInt(summoningPouchData[75][6]) * 20,
								22);
					}
				} else {
					c.sendMessage("You need a Summoning level of "
							+ summoningPouchData[75][4] + ".");
				}
			} else {
				c.sendMessage("You need 1 " + summoningPouchData[75][1]
						+ ", 1 " + summoningPouchData[75][2] + ", "
						+ summoningPouchData[75][3]
								+ " shards, and 1 pouch to make this.");
			}
			break;
		case 156003:
			if (NEED(c, 76)) {
				if (c.getLevelForXP(c.playerXP[22]) >= Integer
						.parseInt(summoningPouchData[76][4])) {
					c.getItems().deleteItem2(POUCH, 1);
					c.getItems().deleteItem2(SHARD,
							Integer.parseInt(summoningPouchData[76][3]));
					c.getItems().deleteItem2(
							c.getItems().getItemId(summoningPouchData[76][1]),
							1);
					c.getItems().deleteItem2(
							c.getItems().getItemId(summoningPouchData[76][2]),
							1);
					c.getItems().addItem(
							c.getItems().getItemId(summoningPouchData[76][0]),
							1);
					if (c.isPVPActive == true) {
						c.getPA().addSkillXP(
								Integer.parseInt(summoningPouchData[76][6]) * 20 * 2,
								22);
					} else {
						c.getPA().addSkillXP(
								Integer.parseInt(summoningPouchData[76][6]) * 20,
								22);
					}
				} else {
					c.sendMessage("You need a Summoning level of "
							+ summoningPouchData[76][4] + ".");
				}
			} else {
				c.sendMessage("You need 1 " + summoningPouchData[76][1]
						+ ", 1 " + summoningPouchData[76][2] + ", "
						+ summoningPouchData[76][3]
								+ " shards, and 1 pouch to make this.");
			}
			break;
		case 156006:
			if (NEED(c, 77)) {
				if (c.getLevelForXP(c.playerXP[22]) >= Integer
						.parseInt(summoningPouchData[77][4])) {
					c.getItems().deleteItem2(POUCH, 1);
					c.getItems().deleteItem2(SHARD,
							Integer.parseInt(summoningPouchData[77][3]));
					c.getItems().deleteItem2(
							c.getItems().getItemId(summoningPouchData[77][1]),
							1);
					c.getItems().deleteItem2(
							c.getItems().getItemId(summoningPouchData[77][2]),
							1);
					c.getItems().addItem(
							c.getItems().getItemId(summoningPouchData[77][0]),
							1);
					if (c.isPVPActive == true) {
						c.getPA().addSkillXP(
								Integer.parseInt(summoningPouchData[77][6]) * 20 * 2,
								22);
					} else {
						c.getPA().addSkillXP(
								Integer.parseInt(summoningPouchData[77][6]) * 20,
								22);
					}
				} else {
					c.sendMessage("You need a Summoning level of "
							+ summoningPouchData[77][4] + ".");
				}
			} else {
				c.sendMessage("You need 1 " + summoningPouchData[77][1]
						+ ", 1 " + summoningPouchData[77][2] + ", "
						+ summoningPouchData[77][3]
								+ " shards, and 1 pouch to make this.");
			}
			break;
		}
	}

	private static final void specs(final Player c) {
		c.gfx0(GFX);
		//c.setAnimation(Animation.create(ANIMATION));
		c.startAnimation(ANIMATION);
	}

	public static void makeSummoningScroll(Player c, int button) {
		try {
			int i = (button - 151055) / 3;
			if (hasPouch(c, i)) {
				if (c.getLevelForXP(c.playerXP[22]) >= Integer
						.parseInt(summoningPouchData[i][4])) {
					c.getItems()
					.deleteItem2(
							c.getItems().getItemId(
									summoningPouchData[i][0]), 1);
					c.getItems().addItem(
							c.getItems().getItemId(summoningPouchData[i][5]),
							10);
					if (c.isPVPActive == true) {
						c.getPA()
						.addSkillXP(
								Integer.parseInt(summoningPouchData[i][6]) / 10 * 2,
								22);
					} else {
						c.getPA()
						.addSkillXP(
								Integer.parseInt(summoningPouchData[i][6]) / 10,
								22);
					}
					c.getPA().refreshSkill(22);
					specs(c);
					c.sendMessage("You have created 10 "
							+ summoningPouchData[i][5] + "");
				} else {
					c.sendMessage("You need a Summoning level of "
							+ summoningPouchData[i][4] + ".");
				}
			} else {
				c.sendMessage("You need 1 " + summoningPouchData[i][0]
						+ " to create the scrolls.");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
	}

	public static void makeSummoningScroll2(Player c, int button) {
		try {
			int i = (button - 152000) / 3 + 67;
			if (hasPouch(c, i)) {
				if (c.getLevelForXP(c.playerXP[22]) >= Integer
						.parseInt(summoningPouchData[i][4])) {
					c.getItems()
					.deleteItem2(
							c.getItems().getItemId(
									summoningPouchData[i][0]), 1);
					c.getItems().addItem(
							c.getItems().getItemId(summoningPouchData[i][5]),
							10);
					if (c.isPVPActive == true) {
						c.getPA()
						.addSkillXP(
								Integer.parseInt(summoningPouchData[i][6]) / 10 * 2,
								22);
					} else {
						c.getPA()
						.addSkillXP(
								Integer.parseInt(summoningPouchData[i][6]) / 10,
								22);
					}
					c.getPA().refreshSkill(22);
					specs(c);
					c.sendMessage("You have created 10 "
							+ summoningPouchData[i][5] + "");
				} else {
					c.sendMessage("You need a Summoning level of "
							+ summoningPouchData[i][4] + ".");
				}
			} else {
				c.sendMessage("You need 1 " + summoningPouchData[i][0]
						+ " to create the scrolls.");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
	}

}