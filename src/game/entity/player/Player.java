package game.entity.player;

import engine.event.CycleEvent;
import engine.event.CycleEventHandler;
import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.event.TaskScheduler;
import engine.network.HostList;
import engine.util.ISAACRandomGen;
import engine.util.Misc;
import engine.util.Stream;
import game.Config;
import game.Server;
import game.combat.CombatHandler;
import game.combat.magic.Magic;
import game.combat.magic.NonCombatSpells;
import game.combat.prayer.Curse;
import game.combat.prayer.Prayer;
import game.content.achievement.*;
import game.content.Food;
import game.content.LoyaltyProgram;
import game.content.DwarfMultiCannon;
import game.content.PriceChecker;
import game.content.QuickCurses;
import game.content.QuickPrayers;
import game.content.Sound;
import game.content.TeleTabCreation;
import game.content.TradeLog;
import game.content.clan.Clan;
import game.content.partyroom.PartyRoom;
import game.content.quests.QuestHandler;
import game.entity.Entity;
import game.entity.UpdateFlags.UpdateFlag;
import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.utilities.DrawInterface;
import game.item.Item;
import game.item.ItemAssistant;
import game.item.ItemDegradation;
import game.minigame.bountyhunter.BountyHunter;
import game.minigame.fightpits.FightPits;
import game.minigame.pestcontrol.PestControl;
import game.minigame.weapongame.WeaponGame;
import game.minigame.zombies.Zombies;
import game.minigame.skillingtasks.SkillingGame;
import game.net.packets.DialogueHandler;
import game.net.packets.Packet;
import game.net.packets.PacketHandler;
import game.net.packets.StaticPacketBuilder;
import game.net.packets.TradeAndDuel;
import game.shop.ShopAssistant;
import game.skill.SkillInterfaces;
import game.skill.SkillingTools;
import game.skill.dungeoneering.DungHandler;
import game.skill.dungeoneering.Dungeoneering;
import game.skill.farming.Farming;
import game.skill.herblore.PotionMixing;
import game.skill.herblore.Potions;
import game.skill.smithing.Smithing;
import game.skill.smithing.SmithingInterface;
import game.skill.summoning.BoB;
import game.skill.summoning.Summoning;
import game.skill.summoning.SummoningSpecials;
import game.sql.HighscoresHandler;
import game.sql.Highscores;
import game.sql.MadTurnipConnection;
import game.object.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.apache.mina.common.IoSession;

public class Player extends Entity {

	private final String[] randomMessages = {
		"pie", "kebab", "chocolate", "bagel", "triangle",
		"square", "bread"
	};
	
	private final int[][] responseButtons = {
		{ 63013, 0 }, { 63014, 1 }, { 63015, 2 }, { 63009, 3 },
		{ 63010, 4 }, { 63011, 5 }, { 63012, 6 }
	};
	
	public void executeRandom() {
		getPA().sendFrame126("", 16131);
		getPA().showInterface(16135);
		foodType = Misc.random(6);
		getPA().sendFrame126("Please select the " + randomMessages[foodType] + " for a cash reward!", 16145);
	}
	
	public void checkResponse(int button) {
		for (int i = 0; i < responseButtons.length; i ++) {
			if (responseButtons[i][0] == button) {
				if (responseButtons[i][1] == foodType) {
					sendMessage("Congratulations! You've completed the random event.");
					getPA().closeAllWindows();
					getItems().addItem(995, Misc.random(2000));
					return;
				}
				
				/**
				 * You can add other things here, such as a teleport or something.
				 * This is where it fails and they click the wrong food type.
				 */
				this.getPA().spellTeleport(3087, 3504, 0);
				sendMessage("@red@As you did not complete the random event you are being teleported home.");
			}
		}
	}
	
	public int foodType = -1;
	public long lastRandom;

	public String bankPin = "";
	public int attempts = 3, wantsPIN;
	public boolean setPin = false;
	private Pins pins = new Pins(this);
	public Pins getBankPin() {
		return pins;
	}

	public boolean settingUpCannon, hasCannon, cannonIsShooting, setUpBase,
	setUpStand, setUpBarrels, setUpFurnace;
	public int cannonBalls, cannonBaseX, cannonBaseY, cannonBaseH, rotation,
	cannonID;
	public Objects oldCannon;
	private final DwarfMultiCannon cannon = new DwarfMultiCannon(this);
	public DwarfMultiCannon getCannon() {
		return this.cannon;
	}
	/**
	 * PVP variables
	 */
	public boolean isPVPActive = false;
	public boolean isWildActive = false;
	public int safeTimer = 0;

	public int altarPrayed = 1;
	public boolean quickCurseActive, quickPray, quickCurse, choseQuickPro, quickPrayersOn;
	public boolean[] quickCurses = new boolean[QuickCurses.MAX_CURSES];
	public boolean[] quickPrayers = new boolean[QuickPrayers.MAX_PRAYERS];

	public int achievementPoints, xpMaster, pTime, totalNPCKO, completeAchievements, THMATT, THMDEF, THMSTR, THMHP, THMRANG, THMPRAY, THMMAGE;
	public boolean hasAchieved;
	public int[] achievement = new int[AchievementManager.MAX_ACHIEVEMENTS];
	public boolean[] achieved = new boolean[AchievementManager.MAX_ACHIEVEMENTS];

	//Skilltask
	private SkillingGame sg = new SkillingGame(this);
	public SkillingGame getSkillingGame() {
		return sg;
	}

	public boolean x2Points;

	//Membership
	public int startDate, memberDays;
	public boolean membership;
	public boolean unMembership;

	//Members
	private MembersCredit memberscredit = new MembersCredit(this);
	public MembersCredit getMembersCredit() {
		return memberscredit;
	}
public int[] lastLogin = new int[3];
public int memberShipDays = 0;
public String lastIp;
	
	//Zombies
    public int Zombiewave = 0;
    public NPC[] Zombiez = new NPC[500];	
    public Zombies ZombieGame = new Zombies(this);
    public Zombies getZombieGame() {
        return ZombieGame;
    }	
   public boolean inZombieGame() {
       
      if(heightLevel == ((playerId*4) + 4)) {
          return true;
      } else {
          return false;
      }
      
   }	


private void loginScreen() {
	getPA().sendFrame126("Welcome to Destroscape", 15257);
	getPA().sendFrame126("Your ip: "+ connectedFrom, 15258);
	getPA().sendFrame126("More stuff here", 15259);
	getPA().sendFrame126("Please register at our forum!", 15260);
	getPA().sendFrame126("All of our information can be found there!", 15261);
	if (this.membership == true) {
		getPA().sendFrame126("You have <col=255>"+memberShipDays+"</col> days left of membership.", 15262);
	}
	if (this.membership == false) {
		getPA().sendFrame126("You are currently not a member.", 15262);
	}
	if (this.unMembership == true) {
		getPA().sendFrame126("You have @gre@UNLIMITED @yel@days left of membership.", 15262);
	}
	getPA().sendFrame126("CLICK HERE TO PLAY", 15263);
	getPA().sendFrame126("Security is a major thing, keep your account safe!", 15270); 
	getPA().showInterface(15244);
}

	/**
	 * Bank Tab Variables
	 */
	public int bankingItems[] = new int[Config.BANK_SIZE];
	public int bankingItemsN[] = new int[Config.BANK_SIZE];
	public int bankingTab = 0;// -1 = bank closed
	public int bankItems1[] = new int[Config.BANK_SIZE];
	public int bankItems1N[] = new int[Config.BANK_SIZE];
	public int bankItems2[] = new int[Config.BANK_SIZE];
	public int bankItems2N[] = new int[Config.BANK_SIZE];
	public int bankItems3[] = new int[Config.BANK_SIZE];
	public int bankItems3N[] = new int[Config.BANK_SIZE];
	public int bankItems4[] = new int[Config.BANK_SIZE];
	public int bankItems4N[] = new int[Config.BANK_SIZE];
	public int bankItems5[] = new int[Config.BANK_SIZE];
	public int bankItems5N[] = new int[Config.BANK_SIZE];
	public int bankItems6[] = new int[Config.BANK_SIZE];
	public int bankItems6N[] = new int[Config.BANK_SIZE];
	public int bankItems7[] = new int[Config.BANK_SIZE];
	public int bankItems7N[] = new int[Config.BANK_SIZE];
	public int bankItems8[] = new int[Config.BANK_SIZE];
	public int bankItems8N[] = new int[Config.BANK_SIZE];
	public String searchTerm = "";
	public boolean searchingBank = false;

	/**
	 * Loyalty Variables
	 */
	public int currentDay, currentMonth, currentYear, 
	loyaltyPoints, loyaltyPointsReceived, loyaltyTitle;

	public static boolean protMelee = true;
	public static boolean protMage;
	public static boolean protRange;
	public static int protMeleeTimer = 16;
	public static int protRangeTimer;
	public static int protMageTimer;
	public long tdDelay;
	public int npcId2 = 0;
	public boolean isNpc;

	public long doubleXP = -1;


public boolean isFading = false;
	/**
	 * WeaponGame
	 */

	public int currentClass = 0;

	public int meleeClassLvl = 1;
	public int rangedClassLvl = 1;
	public int magicClassLvl = 1;

	public int weaponKills = 0;
	public int weaponDeaths = 0;

	public int lvlPotential = 0;

	public boolean canLeaveDeath = false;

	/**
	 * Bounty Hunter Variables
	 */
	public String targetName;
	public int playerTarget, cantLeavePenalty, enterTime;

	public boolean inWeaponGame() {
		if (this.absX >= 1622 && this.absX <= 1705 && this.absY >= 5655 && this.absY <= 5733)
			return true;
		return false;
	}

	public boolean inDeathChamber() {
		if (this.absX >= 1666 && this.absX <= 1668 && this.absY >= 5703 && this.absY <= 5705)
			return true;
		if (this.absX >= 1650 && this.absX <= 1652 && this.absY >= 5702 && this.absY <= 5704)
			return true;
		if (this.absX >= 1655 && this.absX <= 1657 && this.absY >= 5682 && this.absY <= 5684)
			return true;
		if (this.absX >= 1675 && this.absX <= 1677 && this.absY >= 5687 && this.absY <= 5689)
			return true;
		return false;
	}

	public long lastRoll;
	public int diceItem;
	public int page;

	/**
	 * Effigy
	 */

	public int effigySkill = 9;
	public int effigySkillLvl = 99;

	/**
	 * Agility
	 */

	public boolean doingAgility = false;

	/**
	 * Obstacle Variables
	 */

	public boolean finishedLog = false;
	public boolean finishedNet1 = false;
	public boolean finishedBranch1 = false;
	public boolean finishedRope = false;
	public boolean finishedBranch2 = false;
	public boolean finishedNet2 = false;
	public boolean finishedPipe = false;

	public boolean finishedBarbRope = false;
	public boolean finishedBarbLog = false;
	public boolean finishedBarbNet = false;
	public boolean finishedBarbLedge = false;
	public boolean finishedBarbStairs = false;
	public boolean finishedBarbWall1 = false;
	public boolean finishedBarbWall2 = false;
	public boolean finishedBarbWall3 = false;

	public boolean finishedWildPipe = false;
	public boolean finishedWildRope = false;
	public boolean finishedWildStone = false;
	public boolean finishedWildLog = false;
	public boolean finishedWildRocks = false;

	/**
	 * Jail
	 */

	public boolean isJailed = false;

	/**
	 * Poitns
	 */
	public int votePoints = 0;
	public int donatorPoints = 0;
	public int totalDonation = 0;
	public int chestChance = 0;

	/**
	 * Curse Prayers
	 **/

	public int leechEnergyDelay, soulSplitDelay, leechAttackDelay,
	attackMultiplier, rangedMultiplier, leechRangedDelay,
	leechDefenceDelay, defenceMultiplier, leechMagicDelay,
	magicMultiplier, leechStrengthDelay, strengthMultiplier,
	leechSpecialDelay;

	public final int[] CURSE_DRAIN_RATE = { 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500 };
	public final int[] CURSE_LEVEL_REQUIRED = { 50, 50, 52, 54, 56, 59, 62, 65,
			68, 71, 74, 76, 78, 80, 82, 84, 86, 89, 92, 95 };
	public final int[] CURSE = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19 };
	public final String[] CURSE_NAME = { "Protect Item", "Sap Warrior",
			"Sap Ranger", "Sap Mage", "Sap Spirit", "Berserker",
			"Deflect Summoning", "Deflect Magic", "Deflect Missiles",
			"Deflect Melee", "Leech Attack", "Leech Ranged", "Leech Magic",
			"Leech Defence", "Leech Strength", "Leech Energy",
			"Leech Special Attack", "Wrath", "Soul Split", "Turmoil" };
	public final int[] CURSE_GLOW = { 610, 611, 612, 613, 614, 615, 616, 617,
			618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629 };
	public final int[] CURSE_HEAD_ICONS = { -1, -1, -1, -1, -1, -1, 12, 10, 11,
			9, -1, -1, -1, -1, -1, -1, -1, 16, 17, -1 };
	public boolean[] curseActive = { false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false };

	//Where did you rip curses from

	/**
	 * End of curse prayers
	 * */

	public int[] price = new int[20];
	public int[] priceN = new int[20];
	public int total;
	public boolean isChecking;

	public long lastSpear1;
	public int tzKekSpawn = 0, tzKekTimer = 0;
	public long mageWalkDelay, meleeWalkDelay, rangeWalkDelay;

	public boolean skillCapeEquipped() {
		for (int i = 10639; i < 12171; i++) {
			for (int j = 9747; j < 9814; j++) {
				if (playerEquipment[playerCape] == i
						|| playerEquipment[playerCape] == j
						|| playerEquipment[playerCape] == 10662
						|| playerEquipment[playerCape] == 9949
						|| playerEquipment[playerCape] == 10646
						|| playerEquipment[playerCape] == 9948
						|| playerEquipment[playerCape] == 12170
						|| playerEquipment[playerCape] == 19710) {
					return true;
				}
			}
		}
		return false;
	}

	public int slayerHelmAffect;

	public void poisonProcess() {
		Player p = PlayerHandler.players[playerId];
		if (virusTimer > -1) {
			virusTimer--;
			if (virusTimer == 0 && virusDamage > 0) {
				forcedChat("*Cough*");
				playerLevel[3] -= (playerLevel[3] - virusDamage < 0 ? playerLevel[3]
						: virusDamage);
				setHitDiff(virusDamage);
				setHitUpdateRequired(true);
				updateRequired = true;
				p.getPA().refreshSkill(3);
				virusTimer = 10;
				virusDamage--;
				if (virusDamage == 0) {
					virusTimer = -1;
					p.sendMessage("The smoke clouds around you dissipate.");
				}
			}
		}
	}

	/**
	 * Skill Info Data
	 */
	public int[][] skillButtons = {
			{106071,0}, //Attack
			{106077,1}, //Defence
			{106074,2}, //Strength
			{106072,3}, //Hitpoints
			{106080,4}, //Range
			{106083,5}, //Prayer
			{106086,6}, //Magic
			{106082,7}, //Cooking
			{106088,8}, //Woodcutting
			{106087,9}, //Fletching
			{106079,10}, //Fishing
			{106085,11}, //Firemaking
			{106084,12}, //Crafting
			{106076,13}, //Smithing
			{106073,14}, //Mining
			{106078,15}, //Herblore
			{106075,16}, //Agility
			{106081,17}, //Thieving
			{106090,18}, //Slayer
			{106095,19}, //Farming
			{106089,20}, //Runecrafting
			{106096,21}, //Hunting
			{106097,22}, //Summoning
			{106098,23}, //Dungeoneering
	};

	/**
	 * prayer variables
	 * 
	 * @return
	 */
	public double prayer = 1.0; //nope o.o

	public int maxPrayer() {
		int max = getLevelForXP(playerXP[5]) /* 10 */;
		return max;
	}

	public boolean prayerDraining;
	public boolean soulsplit;
	public boolean curses;
	public int[] chaoticDegrade =  new int[ItemDegradation.chaoticBrokenIds.length];
	public int formerWeapon;

	public int getMaxPrayer() {
		return getLevelForXP(playerXP[5]) /* 10 */;
	}

	// prayer
	public boolean[] prayerActive = new boolean[46];
	public final int[] PRAYER_DRAIN_RATE = { 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500 };
	public final int[] PRAYER_LEVEL_REQUIRED = { 1, 4, 7, 8, 9, 10, 13, 16, 19,
			22, 25, 26, 27, 28, 31, 34, 37, 40, 43, 44, 45, 46, 49, 52, 60, 70 };
	public final int[] PRAYER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
	public final String[] PRAYER_NAME = { "Thick Skin", "Burst of Strength",
			"Clarity of Thought", "Sharp Eye", "Mystic Will", "Rock Skin",
			"Superhuman Strength", "Improved Reflexes", "Rapid Restore",
			"Rapid Heal", "Protect Item", "Hawk Eye", "Mystic Lore",
			"Steel Skin", "Ultimate Strength", "Incredible Reflexes",
			"Protect from Magic", "Protect from Missiles",
			"Protect from Melee", "Eagle Eye", "Mystic Might", "Retribution",
			"Redemption", "Smite", "Chivalry", "Piety" };
	public final int[] PRAYER_GLOW = { 83, 84, 85, 601, 602, 86, 87, 88, 89,
			90, 91, 603, 604, 92, 93, 94, 95, 96, 97, 605, 606, 98, 99, 100,
			607, 608 };
	public final int[] PRAYER_HEAD_ICONS = { -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, 2, 1, 0, -1, -1, 3, 5, 4, -1, -1 };

	// {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3,2,1,4,6,5};
	public boolean hasProtectItem() {
		return prayerActive[10] || prayerActive[26];
	}

	public boolean[] getDuelRules() {
		return duelRules;
	}

	private boolean[] duelRules = new boolean[22];
	public Clan clan;
	public String lastClanChat = "help";
	public String duelName;

	/**
	 * Byte Declarations
	 */
	public int virusTimer = -1, virusDamage = 0;
	public byte poisonMask = 0,
			playerInListBitmap[] = new byte[Config.MAX_PLAYERS + 7 >> 3],
			npcInListBitmap[] = new byte[NPCHandler.maxNPCs + 7 >> 3],
			cachedPropertiesBitmap[] = new byte[Config.MAX_PLAYERS + 7 >> 3];

	private byte chatText[] = new byte[4096], chatTextSize = 0;

	/**
	 * Int Declarations
	 */

	public int rangeEndGFX, boltDamage, teleotherType, playerTradeWealth,
	demonCount, stageT, dfsCount, effectRestore, recoilHits, playerDialogue,
	floorTimeMinutes = 0, floorTimeSeconds = 0, floorTimeHours = 0,
	floor = 0, boneId, complexity = 0, hasJustCalled = 0, doingHerb,
	newHerb, newItem, newXp, log, herbAmount, woodcuttingTree,
	doAmount, randomBarrows = 0, playerEnergy = 100, dream,
	bobSlotCount, aGoodStartStatus, TKVStatus, pkPoints,
	leatherType = -1, summoningSpecialPoints = 60,
	timeBetweenSpecials = 0, specRestoreSeconds = 0, antiqueSelect = 0,
	sumSpecTimer = 0, specHitTimer = 0, decreaseTime = 0, bind1 = -1,
	bind2 = -1, bind3 = -1, bind4 = -1, itemsBounded = 0,
	clawDelay = 0, destroy = 0, droppedItem = -1, gnomeObsticle = 0,
	wildObsticle = 0, questPoints, theStryke, lunarDiplomacy, DT, santasHelp, clickDelay = 0, chatTimeDelay = 0, barbObsticle = 0,
	previousDamage, familiarIndex, familiarID = -1, familiarMinutes,
	familiarSeconds, WillKeepAmt1, WillKeepAmt2, WillKeepAmt3,
	WillKeepAmt4, WillKeepItem1, WillKeepItem2, WillKeepItem3,
	WillKeepItem4, WillKeepItem1Slot, WillKeepItem2Slot, brotherKills,
	WillKeepItem3Slot, WillKeepItem4Slot, EquipStatus, summoning = 22,
	saveDelay, event, playerKilled, dTokens, totalPlayerDamageDealt,
	killedBy, lastChatId = 1, privateChat, friendSlot = 0, dialogueId,
	randomCoffin, newLocation, specEffect, specBarId, attackLevelReq,
	defenceLevelReq, strengthLevelReq, rangeLevelReq, magicLevelReq,
	followId, skullTimer, nextChat = 0, nextQChat = 0, talkingNpc = -1,
	dialogueAction = 0, autocastId, followDistance, followId2,
	barrageCount = 0, delayedDamage = 0, fletchAmount, taskType,
	playerAbility, delayedDamage2 = 0, pcPoints = 0, magePoints = 0,
	slayerPoints, desertTreasure = 0, lastArrowUsed = -1, clanId = -1,
	autoRet = 0, pcDamage = 0, makeTimes, xInterfaceId = 0,
	xRemoveId = 0, xRemoveSlot = 0, tzhaarToKill = 0, tzhaarKilled = 0,
	waveId, frozenBy = 0, poisonDamage = 0, teleAction = 0,
	bonusAttack = 0, lastNpcAttacked = 0, killCount = 0, teleGrabItem,
	teleGrabX, teleGrabY, duelCount, underAttackBy, underAttackBy2,
	wildLevel, teleTimer, respawnTimer, saveTimer = 0, teleBlockLength,
	poisonDelay, barrowsKillCount, dungKills = 0, easierTask = 0, dungDeaths = 0,
	reduceSpellId, waitTime, slayerTask, taskAmount, prayerId = -1,
	headIcon = -1, bountyIcon = 0, duelTimer, duelTeleX, duelTeleY,
	duelSlot, duelSpaceReq, duelOption, duelingWith, duelStatus,
	headIconPk = -1, headIconHints, specMaxHitIncrease, freezeDelay,
	freezeTimer = -6, killerId, playerIndex, currentArrow,
	oldPlayerIndex, lastWeaponUsed, projectileStage,
	crystalBowArrowCount, playerMagicBook, teleGfx, teleEndAnimation,
	teleHeight, teleX, teleY, rangeItemUsed, killingNpcIndex,
	totalDamageDealt, oldNpcIndex, fightMode, attackTimer, npcIndex,
	npcClickIndex, npcType, kbdKills, castingSpellId, oldSpellId,
	spellId, hitDelay, bowSpecShot, dbowDelay = 0, clickNpcType,
	clickObjectType, objectId, anticheattimer, duelLevel, objectX,
	objectY, objectXOffset, objectYOffset, objectDistance, pItemX,
	pItemY, pItemId, myShopId, tradeStatus, absX, absY, currentX,
	currentY, heightLevel, playerSE = 0x328, tradeWith, attackAnim,
	animationRequest = -1, animationWaitCycles, combatLevel, apset,
	actionID, wearItemTimer, wearId, wearSlot, interfaceId,
	XremoveSlot, XinterfaceID, XremoveID, Xamount, tutorial = 15,
	itemUsing, treeX, treeY, rockX, rockY, fishTimer = 0, smeltType,
	smeltAmount, smeltTimer = 0, pitsStatus = 0, playerId = -1,
	playerRights, playerItems[] = new int[28],
	playerItemsN[] = new int[28], logID, events,
	bankItems[] = new int[Config.BANK_SIZE],
	bankItemsN[] = new int[Config.BANK_SIZE], playerStandIndex = 0x328,
	playerTurnIndex = 0x337, playerWalkIndex = 0x333,
	playerTurn180Index = 0x334, playerTurn90CWIndex = 0x335,
	playerTurn90CCWIndex = 0x336, playerRunIndex = 0x338,
	playerHat = 0, playerCape = 1, playerAmulet = 2, playerWeapon = 3,
	playerChest = 4, playerShield = 5, playerLegs = 7, playerHands = 9,
	playerFeet = 10, playerRing = 12, playerArrows = 13,
	playerAttack = 0, playerDefence = 1, playerStrength = 2,
	playerHitpoints = 3, playerRanged = 4, playerPrayer = 5,
	playerMagic = 6, playerCooking = 7, playerWoodcutting = 8,
	playerFletching = 9, playerFishing = 10, playerFiremaking = 11,
	playerCrafting = 12, playerSmithing = 13, playerMining = 14,
	playerHerblore = 15, playerAgility = 16, playerThieving = 17,
	playerSlayer = 18, playerFarming = 19, playerRunecrafting = 20,
	playerHunter = 21, playerSummoning = 22, playerDungeoneering = 23,
	playerListSize = 0, npcListSize = 0, mapRegionX, mapRegionY,
	playerSEW = 0x333, playerSER = 0x334,
	walkingQueueX[] = new int[walkingQueueSize],
	walkingQueueY[] = new int[walkingQueueSize], wQueueReadPtr = 0,
	wQueueWritePtr = 0, teleportToX = -1, teleportToY = -1, dir1 = -1,
	dir2 = -1, poimiX = 0, poimiY = 0, DirectionCount = 0, hitDiff2,
	hitDiff = 0, mask100var1 = 0, mask100var2 = 0, face = -1,
	FocusPointX = -1, FocusPointY = -1, newWalkCmdSteps = 0, stringu,
	isC, kills, deaths, currentSkillInfo = -1, skillsPrestiged = 0, 
	prestigePoints = 0, prestigeTokens = 0, instantKills = 0, presChestAmt = 0, 
	currentPage = 0, crystalKeyAmt = 0, jailed, targetKill, 
	rogueKill, publicCrater, bountyCash, pickupPenalty, leavePenalty, 
	enterBHTime, bountyHunterTarget, hatchet, pickAxe, tools, RFDToKill = 0, RFDKilled = 0,
	RFDtoKill = 0, RFDkilled = 0, clawDamage, clawIndex, clawType = 0,
	specRestore = 0, lastRep, reputationPoints, questButton, teleOneX, teleOneY, teleOneH,
	teleTwoX, teleTwoY, teleTwoH, teleThreeX, teleThreeY, teleThreeH, teleFourX, teleFourY,
	teleFourH, teleFiveX, teleFiveY, teleFiveH, dragonkinSkill = 0,
	currentStreak, highestStreak, skillTask, skillTask1, skillTask2, skillAmount, skillAmount1,
	skillAmount2, tid, tid1, tid2, ia, ia1, ia2, setTask, skillPoints, charmingImpCollect, charmingImpXP,
	sgsEffect;

	private int chatTextColor = 0, chatTextEffects = 0,
			newWalkCmdX[] = new int[walkingQueueSize],
			newWalkCmdY[] = new int[walkingQueueSize];

	protected int travelBackX[] = new int[walkingQueueSize],
			travelBackY[] = new int[walkingQueueSize], numTravelBackSteps = 0;

	/**
	 * Static Integer Declarations
	 */

	public static final int maxPlayerListSize = Config.MAX_PLAYERS,
			maxNPCListSize = NPCHandler.maxNPCs, walkingQueueSize = 50;

	/**
	 * Array Integer & String Declarations
	 */

	public Map<String, Integer> killedPlayers = new HashMap<String, Integer>();
	public ArrayList<Integer> attackedPlayers = new ArrayList<Integer>();
	public ArrayList<String> lastKilledPlayers = new ArrayList<String>();
	public ArrayList<String> playerNames = new ArrayList<String>();

	/**
	 * Dimentional Array Declarations
	 */

	public int[] cookingProp = new int[7], cookingCoords = new int[2],
			miningProp = new int[6], fletchingProp = new int[10],
			clawHit = new int[4], voidStatus = new int[5],
			itemKeptId = new int[4], pouches = new int[4],
			playerBonus = new int[12], fishingProp = new int[24],
			farm = new int[2], playerEquipment = new int[14],
			playerEquipmentN = new int[14], playerLevel = new int[25],
			playerXP = new int[25], damageTaken = new int[Config.MAX_PLAYERS],
			onInterface = {34200,34222,34242}, party = new int[8], partyN = new int[8];

	public int playerAppearance[] = new int[13];

	public final int[] POUCH_SIZE = { 3, 6, 9, 12 };

	/**
	 * Essence Pouch Variables
	 */
	public int 
		mediumPouchCapacity,largePouchCapacity,giantPouchCapacity,smallPouchAmt = 0,
		mediumPouchAmt = 0,largePouchAmt = 0,giantPouchAmt = 0,mediumPouchDecay, 
		largePouchDecay, giantPouchDecay;
	public String 
		smallPouch = "null",mediumPouch = "null",largePouch = "null",giantPouch = "null";

	public final int[] BOWS = { 9185, 18357, 839, 845, 847, 851, 855, 859, 841,
			843, 849, 853, 857, 861, 4212, 4214, 4215, 11235, 15701, 15702,
			15703, 15704, 4216, 4217, 4218, 4219, 4220, 4221, 4222, 4223, 6724,
			4734, 4934, 4935, 4936, 4937, 15241, 20171 };

	public final int[] ARROWS = { 882, 884, 886, 888, 890, 892, 4740, 11212,
			9140, 9141, 4142, 9143, 9144, 9240, 9241, 9242, 9243, 9244, 9245, 15243, 9342 };

	public final int[] NO_ARROW_DROP = { 4212, 4214, 4215, 4216, 4217, 4218,
			4219, 4220, 4221, 4222, 4223, 4734, 4934, 4935, 4936, 4937, 15243, 20171, 11230, 3093 };

	public final int[] OTHER_RANGE_WEAPONS = { 863, 864, 865, 866, 867, 868,
			869, 806, 807, 808, 809, 810, 811, 825, 826, 827, 828, 829, 830,
			800, 801, 802, 803, 804, 805, 6522, 3093, 11230 };

	public final int[] REDUCE_SPELL_TIME = { 250000, 250000, 250000, 500000,
			500000, 500000 };

	public final int[] REDUCE_SPELLS = { 1153, 1157, 1161, 1542, 1543, 1562 };

	public final int[] DUEL_RULE_ID = { 1, 2, 16, 32, 64, 128, 256, 512, 1024,
			4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 2097152,
			8388608, 16777216, 67108864, 134217728 };

	public boolean[] playerSkilling = new boolean[20],
			invSlot = new boolean[28], equipSlot = new boolean[14],
			duelRule = new boolean[22];

	public boolean[] canUseReducingSpell = { true, true, true, true, true, true };

	public long friends[] = new long[200], ignores[] = new long[200];

	public long[] reduceSpellDelay = new long[6];

	public int[][] playerSkillProp = new int[20][15];

	/**
	 * Double Declarations
	 */

	public double getstr, getatt, getdef, crossbowDamage, specAmount = 0,
			specAccuracy = 1, specDamage = 1, prayerPoint = 1.0;

	/**
	 * Boolean Declarations
	 */

	public boolean lostDuel, multiAttacking, rangeEndGFXHeight,
	defaultWealthTransfer, updateInventory, oldSpec, stopPlayerPacket,
	playerBFishing, playerisSmelting, wcClick,
	finishedBarbarianTraining, ignoreDefence, secondFormAutocast,
	usingArrows, prayerX, usingOtherRangeWeapons, usingCross, magicDef,
	spellSwap, patchCleaned, hasBankPin, recoverysSet,
	protectItem = false, usingbook = false, sarabook = false,
	zambook = false, guthbook = false, bossDead = false,
	hasRecievedRewards = false, buyingX, hasActivated = false,
	hasSentMessage, playerFletch, playerIsFletching, fletchLevelReq,
	hasOpened = false, playerIsMining, playerIsFiremaking,
	playerIsCooking, canUseAltar = true, playerIsWoodcutting,
	indream = false, isBanking = false, playerStun = false,
	isOnInterface, finishedBeg = false, isDungReady = false,
	haveAdded = false, inDuelScreen, playerIsNPC, isPartyRoom,
	secondHerb = false, herbloreI = false, below459 = true,
	agilityEmote = false, isStringing, isArrowing, trainingPrayer,
	hasSpawnedBoss = false, hasBeenClicked = false, asdf = false,
	usingClaws, hasFamiliar, makingFamiliarAttack,
	isBankingWithPackYak, familiarTransformed, familiarEvent,
	dungEvent, initialized = false, disconnected = false,
	ruleAgreeButton = false, RebuildNPCList = false, isActive = false,
	isKicked = false, isSkulled = false, friendUpdate = false,
	newPlayer = false, hasMultiSign = false, saveCharacter = false,
	mouseButton = false, splitChat = false, chatEffects = true,
	acceptAid = false, nextDialogue = false, autocasting = false,
	usedSpecial = false, mageFollow = false, dbowSpec = false, hcSpec = false,
	craftingLeather = false, properLogout = false, secDbow = false,
	maxNextHit = false, ssSpec = false, vengOn = false,
	addStarter = false, accountFlagged = false, msbSpec = false,
	canChangeAppearance = false, mageAllowed, hasClickedDung = false,
	usingPrayer, duelRequested, doubleHit, usingSpecial,
	npcDroppingItems, usingRangeWeapon, usingBow, usingMagic,
	castingMagic, magicFailed, oldMagicFailed, usedRingOfStone = false,
	forcedChatUpdateRequired, inDuel, tradeAccepted, goodTrade,
	inTrade, tradeRequested, tradeResetNeeded, tradeConfirmed,
	patchRaked, seedWatered, seedPlanted, tradeConfirmed2, canOffer,
	acceptTrade, acceptedTrade, isRunning2 = true, takeAsNote,
	saveFile = false, usingGlory = false, isOperate,
	antiFirePot = false, stopPlayerSkill, isWc, wcing, isMining,
	mining, fishing = false, playerIsFishing, smeltInterface,
	patchCleared, killedAdmin, inPits = false, bankNotes = false,
	isMoving, walkingToItem, isShopping, updateShop,
	updateRequired = true, isRunning = false, didTeleport = false,
	mapRegionDidChange = false, createItems = false,
	appearanceUpdateRequired = true, hitUpdateRequired2,
	hitUpdateRequired = false, isDead = false, dungClick1 = false,
	dungClick2 = false, dungClick3 = false, dungClick4 = false,
	dungClick5 = false, logBalance, obstacleNetUp, treeBranchUp,
	balanceRope, treeBranchDown, obstacleNetOver, usedEasterRing = false,
	hasOverloadEffect = false, isResting = false, hasKilledDharok,
	hasKilledAhrim, hasKilledTorag, hasKilledVerac, hasKilledKaril,
	hasKilledGuthan, inMoneyBank = false, customYell, canYell = true, 
	prestige = false, doubleExp = false, raiseDropRate = false, 
	raiseCharmRate = false, undrainPrayer = false, instantKill = false,
	raisePresRate = false, raiseCrysRate = false,
	inPartyRoom = false, Culin, Flambeed, Agrith, Dessourt, Karamel,
	toggleLogin, savedTele1, savedTele2, savedTele3, savedTele4,
	savedTele5, hasTesting, SSPLIT = false, craftingTeletabs, hasKilledMax = false,
	smashedGargoyle = false, usingTeleCrystal = false, xpLock;

	private boolean chatTextUpdateRequired = false,
			newWalkCmdIsRunning = false;

	public boolean[] isSkilling = new boolean[25];
	public boolean[] turn = new boolean[4];

	protected boolean mask100update = false, faceUpdateRequired = false;

	/**
	 * String Declarations
	 */

	public String getLine, clanName, properName, connectedFrom = "",
			globalMessage = "", playerName = null, playerName2 = null,
			playerPass = null, xpTitle = "none", forcedText = "null", yellTag = null, 
			currentPanel = "main", lastReported = "", tele1 = "Teleport 1", tele2 = "Teleport 2",
			tele3 = "Teleport 3", tele4 = "Teleport 4", tele5 = "Teleport 5";

	public static String UUID = "";

	/**
	 * Long Declarations
	 */

	public long lastButton, lunarDelay = 0, lastRunDrain, lastRunRecovery,
			lastCast = 0, lastHighscore = 0, lastPray = 0, lastVote = 0,
			lastDonate = 0, callFamiliarDelay, specialAttackDelay,
			lastPlayerMove, lastPoison, lastPoisonSip, poisonImmune, lastSpear,
			lastProtItem, dfsDelay, lastVeng, lastYell, teleGrabDelay,
			protMageDelay, protMeleeDelay, protRangeDelay, lastAction,
			lastThieve, lastLockPick, alchDelay, specDelay = System
			.currentTimeMillis(), duelDelay, teleBlockDelay,
			godSpellDelay, singleCombatDelay, singleCombatDelay2, reduceStat,
			restoreStatsDelay, logoutDelay, buryDelay, foodDelay, potDelay,
			restoreDelay, stopPrayerDelay, prayerDelay, muteEnd, banStart,
			banEnd, lastFire, lastPlant, hunterDelay, teleTimer2, tabTimer, cashStored = 0,
			doubleExpTime = 0, dropRateTime = 0, undrainPrayerTime = 0,
			charmRateTime = 0, lastSpecRestore, lastGWDKC, lastBank, lastHelp = 0, lastReport = 0,
			lastVecna = 0, lastPrayed = 0, toggledPvP = 0;

	/**
	 * Class Declarations
	 */

	public PlayerHandler handler = null;
	public NPC npcList[] = new NPC[Player.maxNPCListSize];
	private TradeLog tradeLog = new TradeLog(this);
	public Player playerList[] = new Player[Player.maxPlayerListSize];
	protected static Stream playerProps;

	static {
		Player.playerProps = new Stream(new byte[100]);
	}

	public int getLocalX() {
		return getX() - 8 * getMapRegionX();
	}

	public int getLocalY() {
		return getY() - 8 * getMapRegionY();
	}

	public int[] pouch = { 0, 0, 0, 0 };

	/**
	 * Dungeoneering
	 * 
	 * @param c
	 *            Cleint c
	 * @return Amount of XP received
	 */

	public int getAbsX() {
		return absX;
	}

	public int getAbsY() {
		return absY;
	}

	public int addDungXP(final Player c) {
		int XP = 0;
		XP += ((c.floor * 4000) * (c.complexity));
		//XP += ((c.floor * 1000) * (c.complexity));
		if (c.floor == 1) {
			if (c.dungDeaths > 3) {
				XP -= ((3 * (300 * c.floor))) / (c.complexity + 1);
			} else {
				XP -= ((c.dungDeaths * (200 * c.floor)) / (c.complexity));
			}
		}
		if (c.floor == 2) {
			if (c.dungDeaths > 4) {
				XP -= ((4 * (300 * c.floor))) / (c.complexity + 1);
				XP *= 1.17;
			} else {
				XP -= ((c.dungDeaths * (200 * c.floor)) / (c.complexity));
				XP *= 1.17;
			}
		}
		if (c.floor == 3) {
			if (c.dungDeaths > 5) {
				XP -= ((5 * (300 * c.floor))) / (c.complexity + 1);
				XP *= 1.33;
			} else {
				XP -= ((c.dungDeaths * (200 * c.floor)) / (c.complexity));
				XP *= 1.33;
			}
		}
		if (c.floor == 4) {
			if (c.dungDeaths > 6) {
				XP -= ((6 * (300 * c.floor))) / (c.complexity + 1);
				XP *= 1.45;
			} else {
				XP -= ((c.dungDeaths * (200 * c.floor)) / (c.complexity));
				XP *= 1.45;
			}
		}
		if (c.floor == 5) {
			if (c.dungDeaths > 7) {
				XP -= ((7 * (300 * c.floor))) / (c.complexity + 1);
				XP *= 2.25;
			} else {
				XP -= ((c.dungDeaths * (200 * c.floor)) / (c.complexity));
				XP *= 2.25;
			}
		}
		XP *= c.complexity;
		XP *= 1.5;
		return XP;
	}

	public int[] questState = { 0 };

	public int addTokens(final Player c) {
			return x2Points ? addDungXP(c) / 5 : addDungXP(c) / 10;
	}

	public boolean cwSafe() {
		if (heightLevel != 1)
			return false;
		if (absX >= 2423 && absX <= 2431 && absY >= 3072 && absY <= 3080) {
			return true;
		} else if (absX >= 2368 && absX <= 2376 && absY >= 3127 && absY <= 3135) {
			return true;
		}
		return false;
	}

	public boolean canBind(final Player c) {
		if (bind4 > -1 && bind3 > -1 && bind2 > -1 && bind1 > -1) {
			c.sendMessage("You have reached the highest number of bounded items already, which is 4.");
			return false;
		} else if (bind4 == -1 && bind3 > -1 && bind2 > -1 && bind1 > -1
				|| bind4 > -1 && bind3 == -1 && bind2 > -1 && bind1 > -1
				|| bind4 > -1 && bind3 > -1 && bind2 == -1 && bind1 > -1
				|| bind4 > -1 && bind3 > -1 && bind2 > -1 && bind1 == -1) {
			if (c.getPA().getLevelForDungXP(c.playerXP[23]) < 120) {
				c.sendMessage("You must have a dungeoneering level of 120, to bind a fourth item.");
				return false;
			} else {
				return true;
			}
		} else if (bind4 == -1 && bind3 == -1 && bind2 > -1 && bind1 > -1
				|| bind4 == -1 && bind3 > -1 && bind2 == -1 && bind1 > -1
				|| bind4 == -1 && bind3 > -1 && bind2 > -1 && bind1 == -1
				|| bind4 > -1 && bind3 == -1 && bind2 == -1 && bind1 > -1
				|| bind4 > -1 && bind3 == -1 && bind2 > -1 && bind1 == -1
				|| bind4 > -1 && bind3 > -1 && bind2 == -1 && bind1 == -1) {
			if (c.getPA().getLevelForDungXP(c.playerXP[23]) < 100) {
				c.sendMessage("You must have a dungeoneering level of at least 100, to bind a third item.");
				return false;
			} else {
				return true;
			}
		} else if (bind4 == -1 && bind3 == -1 && bind2 == -1 && bind1 > -1
				|| bind4 == -1 && bind3 == -1 && bind2 > -1 && bind1 == -1
				|| bind4 == -1 && bind3 > -1 && bind2 == -1 && bind1 == -1
				|| bind4 > -1 && bind3 == -1 && bind2 == -1 && bind1 == -1) {
			if (c.getPA().getLevelForDungXP(c.playerXP[23]) < 50) {
				c.sendMessage("You must have a dungeoneering level of at least 50, to bind a second item.");
				return false;
			} else {
				return true;
			}
		} else if (bind4 == -1 && bind3 == -1 && bind2 == -1 && bind1 == -1) {
			return true;
		}
		return false;
	}

	public boolean inCorp() {
		return absX >= 2886 && absX <= 2913 && absY >= 4379 && absY <= 4410;
	}

	public boolean hasDoublePoints;

	public boolean inDonatorZone() {
		return absX >= 2111 && absX <= 2157 && absY >= 4893 && absY <= 4927;
	}

	public boolean inStart() {
		return absX >= 2259 && absX <= 2275 && absY >= 4742 && absY <= 4767;
	}

	public boolean inPrime() {
		return absX >= 2884 && absX <= 2940 && absY >= 4548 && absY <= 4604;
	}

	public boolean inDung() {
		if (absX > 2748 && absX < 2832 && absY > 9164 && absY < 9227 /* Floor 1 */
				|| absX > 2821 && absX < 2880 && absY > 9535 && absY < 9674 /*
				 * Floor
				 * 2
				 */
				|| absX > 2881 && absX < 2950 && absY > 9448 && absY < 9554 /*
				 * Floor
				 * 3
				 */
				|| absX > 2439 && absX < 2626 && absY > 9475 && absY < 9580 /*
				 * Floor
				 * 4
				 */
				|| absX > 2343 && absX < 2480 && absY > 9648 && absY < 9850 /*
				 * Floor
				 * 5
				 */) {
			return true;
		}
		return false;
	}

	public boolean checkEmpty(final Player c) {
		for (int e = 0; e < c.playerEquipment.length; e++) {
			if (e != c.playerRing) {
				if (c.playerEquipment[e] > -1) {
					c.sendMessage("You must remove all of your equipment, before entering a dungeon.");
					return false;
				}
			} else if (e == c.playerRing) {
				if (c.playerEquipment[e] != 15707) {
					c.sendMessage("You must be wearing the ring of kinship to Enter.");
					return false;
				}
			}
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] != 0) {
				c.sendMessage("You must empty your backpack in order to enter. Also, equip your ring of kinship!");
				return false;
			}
		}
		if (c.familiarID > 0) {
			c.sendMessage("You cannot have a familiar summoned, before entering the dungeons.");
			return false;
		}
		return true;
	}

	public boolean canOpenDoor(final Player c) {
		switch (c.floor) {
		case 1:
			if (c.dungKills >= 5) {
				return true;
			} else {
				c.sendMessage("You must defeat all of the monsters on this floor, before entering the boss room.");
			}
			break;
		case 2:
			if (c.dungKills >= 10) {
				return true;
			} else {
				c.sendMessage("You must defeat all of the monsters on this floor, before entering the boss room.");
			}
			break;
		case 3:
			if (c.dungKills >= 13) {
				return true;
			} else {
				c.sendMessage("You must defeat all of the monsters on this floor, before entering the boss room.");
			}
			break;
		case 4:
			if (c.dungKills >= 14) {
				return true;
			} else {
				c.sendMessage("You must defeat all of the monsters on this floor, before entering the boss room.");
				c.sendMessage("Remember, search the floor in every way possible.");
			}
			break;
		case 5:
			if (c.dungKills >= 16) {
				return true;
			} else {
				c.sendMessage("You must defeat all of the monsters on this floor, before entering the boss room.");
			}
			break;
		}
		return false;
	}

	public boolean Area(final int x1, final int x2, final int y1, final int y2) {
		return (absX >= x1 && absX <= x2 && absY >= y1 && absY <= y2);
	}

	public boolean inBank() {
		return Area(3090, 3099, 3487, 3500) || Area(3089, 3090, 3492, 3498)
				|| Area(3248, 3258, 3413, 3428) || Area(3179, 3191, 3432, 3448)
				|| Area(2944, 2948, 3365, 3374) || Area(2942, 2948, 3367, 3374)
				|| Area(2944, 2950, 3365, 3370) || Area(3008, 3019, 3352, 3359)
				|| Area(3017, 3022, 3352, 3357) || Area(3203, 3213, 3200, 3237)
				|| Area(3212, 3215, 3200, 3235) || Area(3215, 3220, 3202, 3235)
				|| Area(3220, 3227, 3202, 3229) || Area(3227, 3230, 3208, 3226)
				|| Area(3226, 3228, 3230, 3211) || Area(3227, 3229, 3208, 3226);
	}

	/**
	 * Distance Bewteen Object
	 * 
	 * @param objectX
	 *            Objects Location on Plane X
	 * @param objectY
	 *            Objects Location on Plane Y
	 * @param playerX
	 *            Players Location on Plane X
	 * @param playerY
	 *            Players Location on Plane Y
	 * @param distance
	 *            Players Distance from Object
	 * @return Returns False by Default
	 */

	public boolean WithinDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX + i) == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if ((objectX - i) == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX
						&& ((objectY + j) == playerY
						|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}

	public final int fletchAmount(final int button) {
		switch (button) {
		case 34185:
		case 34189:
		case 34170:
		case 34174:
		case 34193:
		case 34217:
		case 34205:
		case 34209:
		case 34213:
			return 1;
		case 34192:
		case 34184:
		case 34188:
		case 34169:
		case 34216:
		case 34173:
		case 34204:
		case 34208:
		case 34212:
			return 5;
		case 34191:
		case 34183:
		case 34187:
		case 34168:
		case 34215:
		case 34172:
		case 34203:
		case 34207:
		case 34211:
			return 10;
		case 34190:
		case 34182:
		case 34186:
		case 34214:
		case 34167:
		case 34171:
		case 34202:
		case 34206:
		case 34210:
			return 27;
		}
		return 0;
	}

	/**
	 * Magic Spells
	 */

	public final int[][] MAGIC_SPELLS = {
			// example {magicId, level req, animation, startGFX, projectile Id,
			// endGFX, maxhit, exp gained, rune 1, rune 1 amount, rune 2, rune 2
			// amount, rune 3, rune 3 amount, rune 4, rune 4 amount}

			// Modern Spells
			// Modern Spells
			{1152,1,14221,0,2699,2700,2,5,556,1,558,1,0,0,0,0}, //wind strike
			{1154,5,14220,2701,2703,2708,4,7,555,1,556,1,558,1,0,0}, // water strike
			{1156,9,14222,2713,2718,2723,6,9,557,2,556,1,558,1,0,0},// earth strike
			{1158,13,14223,2728,2729,2737,8,11,554,3,556,2,558,1,0,0}, // fire strike
			{1160,17,14221,0,2699,2700,9,13,556,2,562,1,0,0,0,0}, // wind bolt
			{1163,23,14220,2702,2704,2709,10,16,556,2,555,2,562,1,0,0}, // water bolt
			{1166,29,14222,2714,2719,2724,11,20,556,2,557,3,562,1,0,0}, // earth bolt
			{1169,35,14223,2728,2731,2738,12,22,556,3,554,4,562,1,0,0}, // fire bolt
			{1172,41,14221,0,2699,2700,13,25,556,3,560,1,0,0,0,0}, // wind blast
			{1175,47,14220,2702,2705,2710,14,28,556,3,555,3,560,1,0,0}, // water blast
			{1177,53,14222,2715,2720,2725,15,31,556,3,557,4,560,1,0,0}, // earth blast
			{1181,59,14223,2728,2733,2739,16,35,556,4,554,5,560,1,0,0}, // fire blast
			{1183,62,14221,0,2699,2700,17,36,556,5,565,1,0,0,0,0}, // wind wave
			{1185,65,14220,2702,2706,2711,18,37,556,5,555,7,565,1,0,0},  // water wave
			{1188,70,14222,2715,2721,2726,19,40,556,5,557,7,565,1,0,0}, // earth wave
			{1189,75,14223,2728,2735,2740,20,42,556,5,554,7,565,1,0,0}, // fire wave
			{1153,3,716,102,103,104,0,13,555,3,557,2,559,1,0,0},  // confuse
			{1157,11,716,105,106,107,0,20,555,3,557,2,559,1,0,0},  // weaken
			{1161,19,716,108,109,110,0,29,555,2,557,3,559,1,0,0}, // curse
			{1542,66,729,167,168,169,0,76,557,5,555,5,566,1,0,0}, // vulnerability
			{1543,73,729,170,171,172,0,83,557,8,555,8,566,1,0,0}, // enfeeble
			{1562,80,729,173,174,107,0,90,557,12,555,12,556,1,0,0},  // stun
			{1572,20,711,177,178,181,0,30,557,3,555,3,561,2,0,0}, // bind
			{1582,50,711,177,178,180,2,60,557,4,555,4,561,3,0,0}, // snare
			{1592,79,711,177,178,179,4,90,557,5,555,5,561,4,0,0}, // entangle
			{1171,39,724,145,146,147,15,25,556,2,557,2,562,1,0,0},  // crumble undead
			{1539,50,708,87,88,89,25,42,554,5,560,1,0,0,0,0}, // iban blast
			{12037,50,1576,327,328,329,19,30,560,1,558,4,0,0,0,0}, // magic dart
			{1190,60,811,0,0,76,20,60,554,2,565,2,556,4,0,0}, // sara strike
			{1191,60,811,0,0,77,20,60,554,1,565,2,556,4,0,0}, // cause of guthix
			{1192,60,811,0,0,78,20,60,554,4,565,2,556,1,0,0}, // flames of zammy
			{12445,85,10503,1841,1842,1843,0,65,563,1,562,1,560,1,0,0}, // teleblock

			// Ancient Spells
			{ 12939, 50, 1978, 0, 384, 385, 13, 30, 560, 2, 562, 2, 554, 1,
				556, 1 }, // smoke rush
				{ 12987, 52, 1978, 0, 378, 379, 14, 31, 560, 2, 562, 2, 566, 1,
					556, 1 }, // shadow rush
					{ 12901, 56, 1978, 0, 0, 373, 15, 33, 560, 2, 562, 2, 565, 1, 0, 0 }, // blood
					// rush
					{ 12861, 58, 1978, 0, 360, 361, 16, 34, 560, 2, 562, 2, 555, 2, 0,
						0 }, // ice rush
						{ 12963, 62, 1979, 0, 0, 389, 19, 36, 560, 2, 562, 4, 556, 2, 554,
							2 }, // smoke burst
							{ 13011, 64, 1979, 0, 0, 382, 20, 37, 560, 2, 562, 4, 556, 2, 566,
								2 }, // shadow burst
								{ 12919, 68, 1979, 0, 0, 376, 21, 39, 560, 2, 562, 4, 565, 2, 0, 0 }, // blood
								// burst
								{ 12881, 70, 1979, 0, 0, 363, 22, 40, 560, 2, 562, 4, 555, 4, 0, 0 }, // ice
								// burst
								{ 12951, 74, 1978, 0, 386, 387, 23, 42, 560, 2, 554, 2, 565, 2,
									556, 2 }, // smoke blitz
									{ 12999, 76, 1978, 0, 380, 381, 24, 43, 560, 2, 565, 2, 556, 2,
										566, 2 }, // shadow blitz
										{ 12911, 80, 1978, 0, 374, 375, 25, 45, 560, 2, 565, 4, 0, 0, 0, 0 }, // blood
										// blitz
										{ 12871, 82, 1978, 366, 0, 367, 26, 46, 560, 2, 565, 2, 555, 3, 0,
											0 }, // ice blitz
											{ 12975, 86, 1979, 0, 0, 391, 27, 48, 560, 4, 565, 2, 556, 4, 554,
												4 }, // smoke barrage
												{ 13023, 88, 1979, 0, 0, 383, 28, 49, 560, 4, 565, 2, 556, 4, 566,
													3 }, // shadow barrage
													{ 12929, 92, 1979, 0, 0, 377, 29, 51, 560, 4, 565, 4, 566, 1, 0, 0 }, // blood
													// barrage
													{ 12891, 94, 1979, 0, 0, 369, 30, 52, 560, 4, 565, 2, 555, 6, 0, 0 }, // ice
													// barrage

													{ -1, 80, 811, 301, 0, 0, 0, 0, 554, 3, 565, 3, 556, 3, 0, 0 }, // charge
													{ -1, 21, 712, 112, 0, 0, 0, 10, 554, 3, 561, 1, 0, 0, 0, 0 }, // low
													// alch
													{ -1, 55, 713, 113, 0, 0, 0, 20, 554, 5, 561, 1, 0, 0, 0, 0 }, // high
													// alch

													{ -1, 33, 728, 142, 143, 144, 0, 35, 556, 1, 563, 1, 0, 0, 0, 0 }, // telegrab

													{ -1, 0, 10518, -1,2718,2723, 33, 35, -1, -1, -1, -1, 0, 0, 0, 0 }, // polypore

													{ -1, 0, 10546, 457, 1019, -1, 33, 35, 21773, 1, -1, -1, 0, 0, 0, 0 } // armadyl
													// storm

	};

	public int[] autocastIds = { 51133, 32, 51185, 33, 51091, 34, 24018, 35,
			51159, 36, 51211, 37, 51111, 38, 51069, 39, 51146, 40, 51198, 41,
			51102, 42, 51058, 43, 51172, 44, 51224, 45, 51122, 46, 51080, 47,
			7038, 0, 7039, 1, 7040, 2, 7041, 3, 7042, 4, 7043, 5, 7044, 6,
			7045, 7, 7046, 8, 7047, 9, 7048, 10, 7049, 11, 7050, 12, 7051, 13,
			7052, 14, 7053, 15, 47019, 27, 47020, 25, 47021, 12, 47022, 13,
			47023, 14, 47024, 15 };

	/**
	 * Barrows
	 */

	public int[][] barrowsNpcs = { { 2030, 0 }, // verac
			{ 2029, 0 }, // toarg
			{ 2028, 0 }, // karil
			{ 2027, 0 }, // guthan
			{ 2026, 0 }, // dharok
			{ 2025, 0 }, // ahrim
			{ 2032, 0 }, { 2034, 0 }, { 2033, 0 }, { 2031, 0 }, { 2035, 0 }, };

	public Player(final int _playerId) {
		playerId = _playerId;
		playerRights = Config.START_PLAYER_RIGHT;
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}
		for (int i = 0; i < playerLevel.length; i++) {
			if (i == 3) {
				playerLevel[i] = 10;
			} else {
				playerLevel[i] = 1;
			}
		}
		for (int i = 0; i < playerXP.length; i++) {
			if (i == 3) {
				playerXP[i] = 1300;
			} else {
				playerXP[i] = 0;
			}
		}
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			bankItems[i] = 0;
		}
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			bankItemsN[i] = 0;
		}
		playerAppearance[0] = 0; // gender
		playerAppearance[1] = 0; // head
		playerAppearance[2] = 25; // Torso
		playerAppearance[3] = 29; // arms
		playerAppearance[4] = 35; // hands
		playerAppearance[5] = 39; // legs
		playerAppearance[6] = 44; // feet
		playerAppearance[7] = 14; // beard
		playerAppearance[8] = 7; // hair colour
		playerAppearance[9] = 8; // torso colour
		playerAppearance[10] = 9; // legs colour
		playerAppearance[11] = 5; // feet colour
		playerAppearance[12] = 0; // skin colour
		apset = 0;
		actionID = 0;
		playerEquipment[playerHat] = -1;
		playerEquipment[playerCape] = -1;
		playerEquipment[playerAmulet] = -1;
		playerEquipment[playerChest] = -1;
		playerEquipment[playerShield] = -1;
		playerEquipment[playerLegs] = -1;
		playerEquipment[playerHands] = -1;
		playerEquipment[playerFeet] = -1;
		playerEquipment[playerRing] = -1;
		playerEquipment[playerArrows] = -1;
		playerEquipment[playerWeapon] = -1;
		heightLevel = Config.START_HEIGHT_LEVEL;
		teleportToX = Config.START_LOCATION_X;
		teleportToY = Config.START_LOCATION_Y;

		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	public void addNewNPC(final NPC npc, final Stream str,
			final Stream updateBlock) {
		final int id = npc.npcId;
		npcInListBitmap[id >> 3] |= 1 << (id & 7);
		npcList[npcListSize++] = npc;
		str.writeBits(14, id);
		int z = npc.absY - absY;
		if (z < 0) {
			z += 32;
		}
		str.writeBits(5, z);
		z = npc.absX - absX;
		if (z < 0) {
			z += 32;
		}
		str.writeBits(5, z);
		str.writeBits(1, 0);
		str.writeBits(14, npc.npcType);
		final boolean savedUpdateRequired = npc.updateRequired;
		npc.updateRequired = true;
		npc.appendNPCUpdateBlock(updateBlock);
		npc.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1);
	}

	public void addNewPlayer(final Player plr, final Stream str,
			final Stream updateBlock) {
		final int id = plr.playerId;
		playerInListBitmap[id >> 3] |= 1 << (id & 7);
		playerList[playerListSize++] = plr;
		str.writeBits(11, id);
		str.writeBits(1, 1);
		final boolean savedFlag = plr.isAppearanceUpdateRequired();
		final boolean savedUpdateRequired = plr.updateRequired;
		plr.setAppearanceUpdateRequired(true);
		plr.updateRequired = true;
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.setAppearanceUpdateRequired(savedFlag);
		plr.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1);
		int z = plr.absY - absY;
		if (z < 0) {
			z += 32;
		}
		str.writeBits(5, z);
		z = plr.absX - absX;
		if (z < 0) {
			z += 32;
		}
		str.writeBits(5, z);
	}

	public void addToWalkingQueue(final int x, final int y) {
		final int next = (wQueueWritePtr + 1) % walkingQueueSize;
		if (next == wQueueWritePtr) {
			return;
		}
		walkingQueueX[wQueueWritePtr] = x;
		walkingQueueY[wQueueWritePtr] = y;
		wQueueWritePtr = next;
	}

	public void appendAnimationRequest(final Stream str) {
		str.writeWordBigEndian(animationRequest == -1 ? 65535
				: animationRequest);
		str.writeByteC(animationWaitCycles);
	}

	public void appendFaceUpdate(final Stream str) {
		str.writeWordBigEndian(face);
	}

	public void appendForcedChat(final Stream str) {
		str.writeString(forcedText);
	}

	public int calculateMaxLifePoints() {
		int lifePoints = getLevelForXP(playerXP[3]);//The normal hp
		int torvaHelm = 20135;//Torva Legs id
		int torvaBody = 20139;//Torva Body id
		int torvaLegs = 20143;//Torva Helm id
		int pernixHelm = 20147;//Pernix Chaps id
		int pernixBody = 20151;//Pernix Body id
		int pernixLegs = 20155;//Pernix Cowl id
		int virtusHelm = 20159;//Virtus Robe bottom id
		int virtusBody = 20163;//Virtus Robe top id
		int virtusLegs = 20167;//Virtus Mask id
		if (playerEquipment[playerLegs] == torvaLegs || playerEquipment[playerLegs] == pernixLegs || playerEquipment[playerLegs] == virtusLegs)
			lifePoints += 13;
		if (playerEquipment[playerChest] == torvaBody || playerEquipment[playerChest] == pernixBody || playerEquipment[playerChest] == virtusBody)
			lifePoints += 20;
		if (playerEquipment[playerHat] == torvaHelm || playerEquipment[playerHat] == pernixHelm || playerEquipment[playerHat] == virtusHelm)
			lifePoints += 7;
		return lifePoints;
	}

	/**
	 * Hit Update
	 */

	public void appendHit(Player c2, int damage, int mask, int icon,
			int damageMask, boolean maxHit) {
		c2.hitMask = mask;
		c2.hitIcon = icon;
		c2.updateRequired = true;
		c2.handleHitMask(damage, mask, icon);
		c2.dealDamage(damage);
		c2.getPA().refreshSkill(3);
	}

	protected void appendHitUpdate(final Stream str) {
		// synchronized (this) {
		str.writeByte(getHitDiff()); // What the perseon got 'hit' for
		str.writeByteS(hitMask);
		if (playerLevel[3] <= 0) {
			playerLevel[3] = 0;
			isDead = true;
		}
		str.writeByte(hitIcon);
		//str.writeByte(0);
		str.writeByteC(playerLevel[3]); // Their current hp, for HP bar
		str.writeByte(calculateMaxLifePoints()); // Their max hp, for HP
		// bar
		// }
		// System.out.println("hitUpdate1: " +hitDiff);
	}

	protected void appendHitUpdate2(final Stream str) {
		// synchronized (this) {
		str.writeByte(hitDiff2); // What the perseon got 'hit' for
		str.writeByteS(hitMask2);
		if (playerLevel[3] <= 0) {
			playerLevel[3] = 0;
			isDead = true;
		}
		str.writeByte(hitIcon2);
		//str.writeByte(0);
		str.writeByte(playerLevel[3]); // Their current hp, for HP bar
		str.writeByteC(calculateMaxLifePoints()); // Their max hp, for HP
		// bar
		// }
	}

	public void appendMask100Update(final Stream str) {
		str.writeWordBigEndian(mask100var1);
		str.writeDWord(mask100var2);
	}

	// [0] - Bandos | [1] - Zamorak | [2] - Saradomin | [3] - Armadyl

	public int[] godwarsKillCount = { 0, 0, 0, 0 };

	protected void appendPlayerAppearance(final Stream str) {
		Player.playerProps.currentOffset = 0;
		Player.playerProps.writeByte(playerAppearance[0]);
		Player.playerProps.writeByte(headIcon);
		Player.playerProps.writeByte(headIconPk);
		//Player.playerProps.writeByte(headIconHints);
		if (isNpc == false) {
			if (playerEquipment[playerHat] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerHat]);
			} else {
				Player.playerProps.writeByte(0);
			}
			if (playerEquipment[playerCape] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerCape]);
			} else {
				Player.playerProps.writeByte(0);
			}
			if (playerEquipment[playerAmulet] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerAmulet]);
			} else {
				Player.playerProps.writeByte(0);
			}
			if (playerEquipment[playerWeapon] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerWeapon]);
			} else {
				Player.playerProps.writeByte(0);
			}
			if (playerEquipment[playerChest] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerChest]);
			} else {
				Player.playerProps.writeWord(0x100 + playerAppearance[2]);
			}
			if (playerEquipment[playerShield] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerShield]);
			} else {
				Player.playerProps.writeByte(0);
			}
			if (!Item.isFullBody(playerEquipment[playerChest])) {
				Player.playerProps.writeWord(0x100 + playerAppearance[3]);
			} else {
				Player.playerProps.writeByte(0);
			}
			if (playerEquipment[playerLegs] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerLegs]);
			} else {
				Player.playerProps.writeWord(0x100 + playerAppearance[5]);
			}
			if (!Item.isFullHelm(playerEquipment[playerHat])
					&& !Item.isFullMask(playerEquipment[playerHat])) {
				Player.playerProps.writeWord(0x100 + playerAppearance[1]);
			} else {
				Player.playerProps.writeByte(0);
			}
			if (playerEquipment[playerHands] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerHands]);
			} else {
				Player.playerProps.writeWord(0x100 + playerAppearance[4]);
			}
			if (playerEquipment[playerFeet] > 1) {
				Player.playerProps.writeWord(0x200 + playerEquipment[playerFeet]);
			} else {
				Player.playerProps.writeWord(0x100 + playerAppearance[6]);
			}
			if (playerAppearance[0] != 1
					&& !Item.isFullMask(playerEquipment[playerHat])) {
				Player.playerProps.writeWord(0x100 + playerAppearance[7]);
			} else {
				Player.playerProps.writeByte(0);
			}
		} else {
			playerProps.writeWord(-1);
			playerProps.writeWord(npcId2);
		}


		Player.playerProps.writeByte(playerAppearance[8]);
		Player.playerProps.writeByte(playerAppearance[9]);
		Player.playerProps.writeByte(playerAppearance[10]);
		Player.playerProps.writeByte(playerAppearance[11]);
		Player.playerProps.writeByte(playerAppearance[12]);
		Player.playerProps.writeWord(playerStandIndex); // standAnimIndex
		Player.playerProps.writeWord(playerTurnIndex); // standTurnAnimIndex
		Player.playerProps.writeWord(playerWalkIndex); // walkAnimIndex
		Player.playerProps.writeWord(playerTurn180Index); // turn180AnimIndex
		Player.playerProps.writeWord(playerTurn90CWIndex); // turn90CWAnimIndex
		Player.playerProps.writeWord(playerTurn90CCWIndex); // turn90CCWAnimIndex
		Player.playerProps.writeWord(playerRunIndex); // runAnimIndex
		Player.playerProps.writeQWord(Misc.playerNameToInt64(playerName));
		int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5);
		int ran = (int) ((getLevelForXP(playerXP[4])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));
		combatLevel = 0;
		if (this.inWild()) {
			if (ran > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						//+ ((calculateMaxLifePoints()) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[4])) * 0.4875));
			} else if (mag > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						//+ ((calculateMaxLifePoints()) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[6])) * 0.4875));
			} else {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						//+ ((calculateMaxLifePoints()) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[0])) * 0.325)
						+ ((getLevelForXP(playerXP[2])) * 0.325));
			}

		} else {
			if (ran > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						//+ ((calculateMaxLifePoints()) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[4])) * 0.4875) + ((getLevelForXP(playerXP[22])) * 0.125));
			} else if (mag > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						//+ ((calculateMaxLifePoints()) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[6])) * 0.4875) + ((getLevelForXP(playerXP[22])) * 0.125));
			} else {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						//+ ((calculateMaxLifePoints()) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[0])) * 0.325)
						+ ((getLevelForXP(playerXP[2])) * 0.325) + ((getLevelForXP(playerXP[22])) * 0.125));
			}

		}
		Player.playerProps.writeByte(combatLevel); // combat level
		playerProps.writeWord(loyaltyTitle);
		str.writeByteC(playerProps.currentOffset);
		str.writeBytes(playerProps.buffer, playerProps.currentOffset, 0);
	}

	protected void appendPlayerChatText(final Stream str) {
		str.writeWordBigEndian(((getChatTextColor() & 0xFF) << 8)
				+ (getChatTextEffects() & 0xFF));
		str.writeByte(playerRights);
		str.writeByteC(getChatTextSize());
		str.writeBytes_reverse(getChatText(), getChatTextSize(), 0);
	}

	public void appendPlayerUpdateBlock(final Stream str) {
		if (!updateRequired && !isChatTextUpdateRequired()) {
			return;
		}
		int updateMask = 0;
		if (mask100update) {
			updateMask |= 0x100;
		}
		if (animationRequest != -1) {
			updateMask |= 8;
		}
		if (forcedChatUpdateRequired) {
			updateMask |= 4;
		}
		if (isChatTextUpdateRequired()) {
			updateMask |= 0x80;
		}
		if (isAppearanceUpdateRequired()) {
			updateMask |= 0x10;
		}
		if (faceUpdateRequired) {
			updateMask |= 1;
		}
		if (FocusPointX != -1) {
			updateMask |= 2;
		}
		if (isHitUpdateRequired()) {
			updateMask |= 0x20;
		}

		if (hitUpdateRequired2) {
			updateMask |= 0x200;
		}

		if (updateMask >= 0x100) {
			updateMask |= 0x40;
			str.writeByte(updateMask & 0xFF);
			str.writeByte(updateMask >> 8);
		} else {
			str.writeByte(updateMask);
		}
		if (mask100update) {
			appendMask100Update(str);
		}
		if (animationRequest != -1) {
			appendAnimationRequest(str);
		}
		if (forcedChatUpdateRequired) {
			appendForcedChat(str);
		}
		if (isChatTextUpdateRequired()) {
			appendPlayerChatText(str);
		}
		if (faceUpdateRequired) {
			appendFaceUpdate(str);
		}
		if (isAppearanceUpdateRequired()) {
			appendPlayerAppearance(str);
		}
		if (FocusPointX != -1) {
			appendSetFocusDestination(str);
		}
		if (isHitUpdateRequired()) {
			appendHitUpdate(str);
		}
		if (hitUpdateRequired2) {
			appendHitUpdate2(str);
		}
	}

	private void appendSetFocusDestination(final Stream str) {
		str.writeWordBigEndianA(FocusPointX);
		str.writeWordBigEndian(FocusPointY);
	}

	public boolean arenas() {
		if (absX > 3331 && absX < 3391 && absY > 3242 && absY < 3260) {
			return true;
		}
		return false;
	}

	public void assignAutocast(final int button) {
		for (int j = 0; j < autocastIds.length; j++) {
			if (autocastIds[j] == button) {
				Player c = PlayerHandler.players[playerId];
				autocasting = true;
				autocastId = autocastIds[j + 1];
				c.getPA().sendFrame36(108, 1);
				c.setSidebarInterface(0, 328);
				c = null;
				break;
			}
		}
	}

	public void clearUpdateFlags() {
		updateRequired = false;
		setChatTextUpdateRequired(false);
		setAppearanceUpdateRequired(false);
		setHitUpdateRequired(false);
		hitUpdateRequired2 = false;
		forcedChatUpdateRequired = false;
		mask100update = false;
		animationRequest = -1;
		FocusPointX = -1;
		FocusPointY = -1;
		poisonMask = -1;
		faceUpdateRequired = false;
		face = 65535;
	}

	public void dealDamage(final int damage) {
		if (teleTimer <= 0) {
			playerLevel[3] -= damage;
		} else {
			if (hitUpdateRequired) {
				hitUpdateRequired = false;
			}
			if (hitUpdateRequired2) {
				hitUpdateRequired2 = false;
			}
		}
	}

	public int maxHitPoints = getLevelForXP(playerXP[3]) * 10;

	public void destruct1() {
		PlayerSave.saveGame(this);
		playerListSize = 0;
		for (int i = 0; i < Player.maxPlayerListSize; i++) {
			playerList[i] = null;
		}
		if (clan != null) {
			clan.removeMember(this);
		}
		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	public int distanceToPoint(final int pointX, final int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2)
				+ Math.pow(absY - pointY, 2));
	}

	public void faceUpdate(final int index) {
		face = index;
		faceUpdateRequired = true;
		updateRequired = true;
	}

	public void forcedChat(final String text) {
		forcedText = text;
		forcedChatUpdateRequired = true;
		updateRequired = true;
		setAppearanceUpdateRequired(true);
	}
	
	public boolean pernixZaryte() {
		return playerEquipment[playerHat] == 20147
				&& playerEquipment[playerLegs] == 20155
				&& playerEquipment[playerChest] == 20151
				&& playerEquipment[playerWeapon] == 20151;
	}

	public boolean fullVoidMage() {
		return playerEquipment[playerHat] == 11663
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842;
	}
	
	

	public boolean fullVoidMelee() {
		return playerEquipment[playerHat] == 11665
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842 
				|| playerEquipment[playerHat] == 11665
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerShield] == 19712 //Void Deflecter
				&& playerEquipment[playerHands] == 8842
				|| playerEquipment[playerHat] == 11665
				&& playerEquipment[playerHands] == 8842
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerShield] == 19712
				|| playerEquipment[playerHat] == 11665
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerShield] == 19712;
	}

	public boolean fullVoidRange() {
		return playerEquipment[playerHat] == 11664
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842;
	}

	public byte[] getChatText() {
		return chatText;
	}

	public int getChatTextColor() {
		return chatTextColor;
	}

	public int getChatTextEffects() {
		return chatTextEffects;
	}

	public byte getChatTextSize() {
		return chatTextSize;
	}

	public int getHitDiff() {
		return hitDiff;
	}

	public boolean getHitUpdateRequired() {
		return hitUpdateRequired;
	}

	public boolean getHitUpdateRequired2() {
		return hitUpdateRequired2;
	}

	public int getId() {
		return playerId;
	}

	public int getLevelForXP(final int exp) {
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 99;
	}

	public int getMapRegionX() {
		return mapRegionX;
	}

	public int getMapRegionY() {
		return mapRegionY;
	}

	public int[] getNewWalkCmdX() {
		return newWalkCmdX;
	}

	public int[] getNewWalkCmdY() {
		return newWalkCmdY;
	}

	public void getNextPlayerMovement() {
		mapRegionDidChange = false;
		didTeleport = false;
		dir1 = dir2 = -1;
		if (teleportToX != -1 && teleportToY != -1) {
			mapRegionDidChange = true;
			if (mapRegionX != -1 && mapRegionY != -1) {
				final int relX = teleportToX - mapRegionX * 8, relY = teleportToY
						- mapRegionY * 8;
				if (relX >= 2 * 8 && relX < 11 * 8 && relY >= 2 * 8
						&& relY < 11 * 8) {
					mapRegionDidChange = false;
				}
			}
			if (mapRegionDidChange) {
				mapRegionX = (teleportToX >> 3) - 6;
				mapRegionY = (teleportToY >> 3) - 6;
			}
			currentX = teleportToX - 8 * mapRegionX;
			currentY = teleportToY - 8 * mapRegionY;
			absX = teleportToX;
			absY = teleportToY;
			resetWalkingQueue();
			teleportToX = teleportToY = -1;
			didTeleport = true;
		} else {
			dir1 = getNextWalkingDirection();
			if (dir1 == -1) {
				return;
			}
			if (isRunning) {
				dir2 = getNextWalkingDirection();
			}
			int deltaX = 0, deltaY = 0;
			if (currentX < 2 * 8) {
				deltaX = 4 * 8;
				mapRegionX -= 4;
				mapRegionDidChange = true;
			} else if (currentX >= 11 * 8) {
				deltaX = -4 * 8;
				mapRegionX += 4;
				mapRegionDidChange = true;
			}
			if (currentY < 2 * 8) {
				deltaY = 4 * 8;
				mapRegionY -= 4;
				mapRegionDidChange = true;
			} else if (currentY >= 11 * 8) {
				deltaY = -4 * 8;
				mapRegionY += 4;
				mapRegionDidChange = true;
			}
			if (mapRegionDidChange) {
				currentX += deltaX;
				currentY += deltaY;
				for (int i = 0; i < walkingQueueSize; i++) {
					walkingQueueX[i] += deltaX;
					walkingQueueY[i] += deltaY;
				}
			}
		}
	}

	public int maxEnergy = 100;
	public boolean woodCutting;

	public int getNextWalkingDirection() {
		if (wQueueReadPtr == wQueueWritePtr)
			return -1;
		int dir;
		do {
			dir = Misc.direction(currentX, currentY,
					walkingQueueX[wQueueReadPtr], walkingQueueY[wQueueReadPtr]);
			if (dir == -1)
				wQueueReadPtr = (wQueueReadPtr + 1) % walkingQueueSize;
			else if ((dir & 1) != 0) {
				println_debug("Invalid waypoint in walking queue!");
				resetWalkingQueue();
				return -1;
			}
		} while ((dir == -1) && (wQueueReadPtr != wQueueWritePtr));
		if (dir == -1)
			return -1;
		dir >>= 1;
				currentX += Misc.directionDeltaX[dir];
				currentY += Misc.directionDeltaY[dir];
				absX += Misc.directionDeltaX[dir];
				absY += Misc.directionDeltaY[dir];
				if (isRunning()) {
					Player c = this;
					if (playerEnergy > 0) {
						if (System.currentTimeMillis() > c.getPA().getAgilityRunDrain()
								+ lastRunDrain) {
							playerEnergy--;
							lastRunDrain = System.currentTimeMillis();
							c.getPA().sendFrame126(playerEnergy + "%", 149);
						}
					} else {
						isRunning2 = false;
						c.getPA().sendFrame36(173, 0);
					}
				}
				return dir;
	}

	public String getSpellName(final int id) {
		switch (id) {
		case 0:
			return "Air Strike";
		case 1:
			return "Water Strike";
		case 2:
			return "Earth Strike";
		case 3:
			return "Fire Strike";
		case 4:
			return "Air Bolt";
		case 5:
			return "Water Bolt";
		case 6:
			return "Earth Bolt";
		case 7:
			return "Fire Bolt";
		case 8:
			return "Air Blast";
		case 9:
			return "Water Blast";
		case 10:
			return "Earth Blast";
		case 11:
			return "Fire Blast";
		case 12:
			return "Air Wave";
		case 13:
			return "Water Wave";
		case 14:
			return "Earth Wave";
		case 15:
			return "Fire Wave";
		case 32:
			return "Shadow Rush";
		case 33:
			return "Smoke Rush";
		case 34:
			return "Blood Rush";
		case 35:
			return "Ice Rush";
		case 36:
			return "Shadow Burst";
		case 37:
			return "Smoke Burst";
		case 38:
			return "Blood Burst";
		case 39:
			return "Ice Burst";
		case 40:
			return "Shadow Blitz";
		case 41:
			return "Smoke Blitz";
		case 42:
			return "Blood Blitz";
		case 43:
			return "Ice Blitz";
		case 44:
			return "Shadow Barrage";
		case 45:
			return "Smoke Barrage";
		case 46:
			return "Blood Barrage";
		case 47:
			return "Ice Barrage";
		default:
			return "Select Spell";
		}
	}

	public int getX() {
		return absX;
	}

	public int getY() {
		return absY;
	}

	public void gfx0(final int gfx) {
		this.mask100var1 = gfx;
		this.mask100var2 = 65536;
		this.mask100update = true;
		this.updateRequired = true;
	}

	public void gfx100(final int gfx) {
		this.mask100var1 = gfx;
		this.mask100var2 = 6553600;
		this.mask100update = true;
		this.updateRequired = true;
	}

	public void gfx(int gfx, int height) {
		mask100var1 = gfx;
		mask100var2 = 65536 * height;
		mask100update = true;
		updateRequired = true;
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

	public int getCombatType() {
		if (usingBow) {
			return 1;
		} else if (usingMagic) {
			return 2;
		} else {
			return 0;
		}
	}

	public int hitMask, hitIcon, hitMask2, hitIcon2;

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

	/**
	 * Locations / Areas
	 * 
	 * @param x
	 *            Lowest X
	 * @param y
	 *            Lowest Y
	 * @param x1
	 *            Highest X
	 * @param y1
	 *            Highest Y
	 * @return
	 */

	public boolean inArea(final int x, final int y, final int x1, final int y1) {
		if (absX > x && absX < x1 && absY < y && absY > y1) {
			return true;
		}
		return false;
	}

	public boolean inBarrows() {
		if (absX > 3520 && absX < 3598 && absY > 9653 && absY < 9750) {
			return true;
		}
		return false;
	}

	public boolean inDuelArena() {
		if (absX > 3322 && absX < 3394 && absY > 3195 && absY < 3291
				|| absX > 3311 && absX < 3323 && absY > 3223 && absY < 3248) {
			return true;
		}
		return false;
	}

	public boolean inPitsRoom() {
		if (absX > 2392 && absY > 5168 && absX < 2405 && absY < 5176) {
			return true;
		}
		return false;
	}

	public boolean inFightCaves() {
		return absX >= 2360 && absX <= 2445 && absY >= 5045 && absY <= 5125;
	}
	
	public boolean inZombieCaves() {
		return absX >= 3240 && absX <= 3256 && absY >= 9353 && absY <= 9376;
	}	

	public boolean inPartyRoom() {
		return absX > PartyRoom.PARTY_AREA[0] && absX < PartyRoom.PARTY_AREA[2]
				&& absY > PartyRoom.PARTY_AREA[1]
						&& absY < PartyRoom.PARTY_AREA[3];

	}

	public boolean inFightPits() {
		return absX >= 2368 && absX <= 2427 && absY >= 5121 && absY <= 5168;
	}

	public boolean inMulti() {
		if (absX > 3193 && absX < 3279 && absY > 9272 && absY < 9343
				|| absX >= 2711 && absX <= 2830 && absY >= 2750 && absY <= 2815 //Ape atoll
				|| absX >= 3136 && absX <= 3327 && absY >= 3519 && absY <= 3607 
				|| absX >= 3151 && absX <= 3198 && absY >= 2954 && absY <= 2999
				|| absX >= 2636 && absX <= 2681 && absY >= 10062 && absY <= 10104
				|| absX >= 3190 && absX <= 3327 && absY >= 3648 && absY <= 3839
				|| absX >= 3200 && absX <= 3390 && absY >= 3840 && absY <= 3967
				|| absX >= 2992 && absX <= 3007 && absY >= 3912 && absY <= 3967
				|| absX >= 2946 && absX <= 2959 && absY >= 3816 && absY <= 3831
				|| absX >= 3008 && absX <= 3199 && absY >= 3856 && absY <= 3903
				|| absX >= 2908 && absX <= 2941 && absY >= 5186 && absY <= 5220 //NEX
				|| absX >= 3400 && absX <= 3600 && absY >= 9800
				&& absY <= 10100 || absX >= 3008 && absX <= 3071
				&& absY >= 3600 && absY <= 3711 || absX >= 3072 && absX <= 3327
				&& absY >= 3608 && absY <= 3647 || absX >= 2624 && absX <= 2690
				&& absY >= 2550 && absY <= 2619 || absX >= 2371 && absX <= 2422
				&& absY >= 5062 && absY <= 5117 || absX >= 2896 && absX <= 2927
				&& absY >= 3595 && absY <= 3630 || absX >= 2892 && absX <= 2932
				&& absY >= 4435 && absY <= 4464 || absX >= 2256 && absX <= 2287
				&& absY >= 4680 && absY <= 4711 || absX >= 2516 && absX <= 2595
				&& absY >= 4926 && absY <= 5003 || inCorp() || inGodWars() || inZombieGame()) {
			return true;
		}
		return false;
	}

	public boolean inPcBoat() {
		return absX >= 2660 && absX <= 2663 && absY >= 2638 && absY <= 2643;
	}

	public boolean inPcGame() {
		return absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619;
	}

	public boolean inRFD() {
		return absX >= 1885 && absX <= 1913 && absY >= 5340 && absY <= 5369;
	}

	public boolean inPirateHouse() {
		return absX >= 3038 && absX <= 3044 && absY >= 3949 && absY <= 3959;
	}

	public boolean inMinigame() {
		return (this.inGodWarsBoss() || this.inFightPits() || this.inRFD() || this.inFightCaves() || this.inDung() || this.inBarrows());
	}

	public boolean inMembersArea() {
		return absX >= 2627 && absX <= 2646 && absY >= 4908 && absY <= 4926;
	}

	public boolean inPvP() {
		if(!isPVPActive) {
			headIconPk = -1;
			return false;
		}
		return(!inSafeZone());

	}


	public boolean inSafeZone() {
		return(inEdgevilleBank() || inWestVarrockBank() || inEastVarrockBank() ||
				inWestFaladorBank() || inEastFaladorBank() || inCamelotBank() ||
				inCatherbyBank() || inNorthArdougneBank() ||inSouthArdougneBank() ||
				inGrandExchange());
	}

	public boolean inGrandExchange() {
		return(distanceToPoint(3165, 3490) <= 16);
	}

	public boolean inEdgevilleBank() {
		return(absX > 3090 && absX < 3099 && absY > 3487 && absY < 3500);
	}

	public boolean inWestVarrockBank() {
		return (absX > 3181 && absX < 3200 && absY > 3431 && absY < 3447);
	} 

	public boolean inEastVarrockBank() {
		return(absX > 3249 && absX < 3258 && absY > 3418 && absY < 3425);
	}

	public boolean inWestFaladorBank() {
		return(absX > 2941 && absX < 2948 && absY > 3367 && absY < 3374 || absX >= 2948 && absX <= 2949 && absY >= 3368 && absY <= 3369);
	}

	public boolean inEastFaladorBank() {
		return(absX > 3008 && absX < 3019 && absY > 3354 && absY < 3359);
	}

	public boolean inCamelotBank() {
		return(absX > 2720 && absX < 2731 && absY > 3489 && absY < 3494 || absX > 2723 && absX < 2728 && absY > 3486 && absY < 3490);
	}

	public boolean inCatherbyBank() {
		return(absX > 2805 && absX < 2813 && absY > 3437 && absY < 3442);
	}

	public boolean inNorthArdougneBank() {
		return(absX > 2611 && absX < 2622 && absY > 3329 && absY <3336);
	}

	public boolean inSouthArdougneBank() {
		return(absX > 2648 && absX < 2657 && absY > 3279 && absY < 3288);
	}

	public boolean inChaosTunnels() {
		return(absX > 3135 && absX < 3328 && absY > 5432 && absY < 5568);
	}

	public boolean inFunPk() {
		return(absX > 2582 && absX < 2607 && absY > 3152 && absY < 3172);
	}

	public boolean inWild() {
		if (absX > 2942 && absX < 3392 && absY > 3523 && absY < 3966
				|| absX > 2941 && absX < 3392 && absY > 9918 && absY < 10366
				|| this.inFightPits()
				|| this.inFunPk()) {
			//isWildActive = true;
			return true;
		} else {
			//isWildActive = false;
			return false;
		}

	}

	public boolean inLobby() {
		if (absX > 2832 && absX < 2851 && absY > 4311 && absY < 4323) {
			return true;
		}
		return false;
	}

	public boolean inClanWarsGame() {
		return absX < 3265 && absY < 3326 && absX > 3712 && absY > 3838;
	}

	public boolean inClanWarsLobby() {
		return absX > 3264 && absX < 3279 && absY < 3672 && absY > 3695;
	}

	public boolean inWarriorsGuild() {
		return absX >= 2835 && absX <= 2877 && absY >= 3532 && absY <= 3559;
	}

	public boolean inGodWars() {
		if (heightLevel == 2 || heightLevel == 1 || heightLevel == 0) {
			if (absX > 2782 && absX < 2960 && absY > 5224 && absY < 5378) {
				return true;
			}
		}
		return false;
	}

	public boolean inGodWarsBoss() {
		if (heightLevel == 2 || heightLevel == 1 || heightLevel == 0) {
			if (absX > 2886 && absX < 2908 && absY > 5255 && absY < 5277) {
				return true;
			} else if (absX > 2863 && absX < 2879 && absY > 5351 && absY < 5372) {
				return true;
			} else if (absX > 2917 && absX < 2947 && absY > 5316 && absY < 5332) {
				return true;
			} else if (absX > 2822 && absX < 2843 && absY > 5295 && absY < 5309) {
				return true;
			}
		}
		return false;
	}

	public boolean noSummon() {
		if (absX > 11111 && absX < 22222 && absY > 111111 && absY < 222222) {
			return true;
		}
		/*
		 * if (absX > 2604 && absX < 2686 && absY > 3277 && absY < 3328) {
		 * return true; }
		 */
		return false;
	}

	public boolean isAppearanceUpdateRequired() {
		return appearanceUpdateRequired;
	}

	public boolean bountySafe1() {
		return absX >= 3173 && absX <= 3193 && absY >= 3669 && absY <= 3701;
	}

	public boolean bountySafe2() {
		return absX >= 3154 && absX <= 3173 && absY >= 3669 && absY <= 3690;
	}

	public boolean bountySafe3() {
		return absX >= 3145 && absX <= 3167 && absY >= 3662 && absY <= 3668;
	}

	public boolean bountySafe4() {
		return absX >= 3143 && absX <= 3154 && absY >= 3669 && absY <= 3675;
	}

	public boolean bountySafe5() {
		return absX >= 3136 && absX <= 3145 && absY >= 3652 && absY <= 3662;
	}

	public boolean isAutoButton(final int button) {
		for (int j = 0; j < autocastIds.length; j += 2) {
			if (autocastIds[j] == button) {
				return true;
			}
		}
		return false;
	}

	public boolean isChatTextUpdateRequired() {
		return chatTextUpdateRequired;
	}

	public boolean isHitUpdateRequired() {
		return hitUpdateRequired;
	}

	public boolean isNewWalkCmdIsRunning() {
		return newWalkCmdIsRunning;
	}

	public void postProcessing() {
		if (this.newWalkCmdSteps > 0) {
			final int firstX = getNewWalkCmdX()[0], firstY = getNewWalkCmdY()[0];
			int lastDir = 0;
			boolean found = false;
			this.numTravelBackSteps = 0;
			int ptr = this.wQueueReadPtr;
			int dir = Misc.direction(this.currentX, this.currentY, firstX,
					firstY);
			if (dir != -1 && (dir & 1) != 0) {
				do {
					lastDir = dir;
					if (--ptr < 0) {
						ptr = walkingQueueSize - 1;
					}
					travelBackX[this.numTravelBackSteps] = this.walkingQueueX[ptr];
					travelBackY[this.numTravelBackSteps++] = this.walkingQueueY[ptr];
					dir = Misc.direction(this.walkingQueueX[ptr],
							this.walkingQueueY[ptr], firstX, firstY);
					if (lastDir != dir) {
						found = true;
						break;
					}

				} while (ptr != wQueueWritePtr);
			} else {
				found = true;
			}
			if (!found) {
				println_debug("Fatal: couldn't find connection vertex! Dropping packet.");
			} else {
				this.wQueueWritePtr = this.wQueueReadPtr;
				addToWalkingQueue(currentX, currentY);
				if (dir != -1 && (dir & 1) != 0) {
					for (int i = 0; i < numTravelBackSteps - 1; i++) {
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
					final int wayPointX2 = travelBackX[numTravelBackSteps - 1], wayPointY2 = travelBackY[numTravelBackSteps - 1];
					int wayPointX1, wayPointY1;
					if (numTravelBackSteps == 1) {
						wayPointX1 = currentX;
						wayPointY1 = currentY;
					} else {
						wayPointX1 = travelBackX[numTravelBackSteps - 2];
						wayPointY1 = travelBackY[numTravelBackSteps - 2];
					}
					dir = Misc.direction(wayPointX1, wayPointY1, wayPointX2,
							wayPointY2);
					if (dir == -1 || (dir & 1) != 0) {
						println_debug("Fatal: The walking queue is corrupt! wp1=("
								+ wayPointX1
								+ ", "
								+ wayPointY1
								+ "), "
								+ "wp2=("
								+ wayPointX2
								+ ", "
								+ wayPointY2
								+ ")");
					} else {
						dir >>= 1;
					found = false;
					int x = wayPointX1, y = wayPointY1;
					while (x != wayPointX2 || y != wayPointY2) {
						x += Misc.directionDeltaX[dir];
						y += Misc.directionDeltaY[dir];
						if ((Misc.direction(x, y, firstX, firstY) & 1) == 0) {
							found = true;
							break;
						}
					}
					if (!found) {
						println_debug("Fatal: Internal error: unable to determine connection vertex!"
								+ "  wp1=("
								+ wayPointX1
								+ ", "
								+ wayPointY1
								+ "), wp2=("
								+ wayPointX2
								+ ", "
								+ wayPointY2
								+ "), "
								+ "first=("
								+ firstX + ", " + firstY + ")");
					} else {
						addToWalkingQueue(wayPointX1, wayPointY1);
					}
					}
				} else {
					for (int i = 0; i < numTravelBackSteps; i++) {
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
				}
				for (int i = 0; i < newWalkCmdSteps; i++) {
					addToWalkingQueue(getNewWalkCmdX()[i], getNewWalkCmdY()[i]);
				}
			}
			isRunning = isNewWalkCmdIsRunning()
					|| (playerEnergy > 0 && isRunning2);
		}
	}

	public boolean isRunning() {
		return isNewWalkCmdIsRunning() || (isRunning2 && isMoving);
	}

	public void preProcessing() {
		newWalkCmdSteps = 0;
	}

	public void println(final String str) {
		System.out.println("[player-" + playerId + "]: " + str);
	}

	public void println_debug(final String str) {
		System.out.println("[player-" + playerId + "]: " + str);
	}

	public void putInCombat(final int attacker) {
		underAttackBy = attacker;
		logoutDelay = System.currentTimeMillis();
		singleCombatDelay = System.currentTimeMillis();
	}

	public void resetWalkingQueue() {
		wQueueReadPtr = wQueueWritePtr = 0;

		for (int i = 0; i < walkingQueueSize; i++) {
			walkingQueueX[i] = currentX;
			walkingQueueY[i] = currentY;
		}
	}

	public boolean samePlayer() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (j == playerId) {
				continue;
			}
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].playerName
						.equalsIgnoreCase(playerName)) {
					disconnected = true;
					return true;
				}
			}
		}
		return false;
	}

	public void setAppearanceUpdateRequired(
			final boolean appearanceUpdateRequired) {
		this.appearanceUpdateRequired = appearanceUpdateRequired;
	}

	public void setChatText(final byte chatText[]) {
		this.chatText = chatText;
	}

	public void setChatTextColor(final int chatTextColor) {
		this.chatTextColor = chatTextColor;
	}

	public void setChatTextEffects(final int chatTextEffects) {
		this.chatTextEffects = chatTextEffects;
	}

	public void setChatTextSize(final byte chatTextSize) {
		this.chatTextSize = chatTextSize;
	}

	public void setChatTextUpdateRequired(final boolean chatTextUpdateRequired) {
		this.chatTextUpdateRequired = chatTextUpdateRequired;
	}

	public void setHitDiff(final int hitDiff) {
		this.hitDiff = hitDiff;
	}

	public void setHitDiff2(final int hitDiff2) {
		this.hitDiff2 = hitDiff2;
	}

	public void setHitUpdateRequired(final boolean hitUpdateRequired) {
		this.hitUpdateRequired = hitUpdateRequired;
	}

	public void setHitUpdateRequired2(final boolean hitUpdateRequired2) {
		this.hitUpdateRequired2 = hitUpdateRequired2;
	}

	public void setInStreamDecryption(final ISAACRandomGen inStreamDecryption) {
	}

	public void setNewWalkCmdIsRunning(final boolean newWalkCmdIsRunning) {
		this.newWalkCmdIsRunning = newWalkCmdIsRunning;
	}

	public void setNewWalkCmdX(final int newWalkCmdX[]) {
		this.newWalkCmdX = newWalkCmdX;
	}

	public void setNewWalkCmdY(final int newWalkCmdY[]) {
		this.newWalkCmdY = newWalkCmdY;
	}

	public void setOutStreamDecryption(final ISAACRandomGen outStreamDecryption) {
	}

	/**
	 * Animations
	 **/
	public void startAnimation(final int animId) {
		animationRequest = animId;
		animationWaitCycles = 0;
		updateRequired = true;
	}

	public void startAnimation(final int animId, final int time) {
		animationRequest = animId;
		animationWaitCycles = time;
		updateRequired = true;
	}

	public void stopMovement() {
		if (teleportToX <= 0 && teleportToY <= 0) {
			teleportToX = absX;
			teleportToY = absY;
		}
		newWalkCmdSteps = 0;
		getNewWalkCmdX()[0] = getNewWalkCmdY()[0] = travelBackX[0] = travelBackY[0] = 0;
		getNextPlayerMovement();
	}

	public void turnPlayerTo(final int pointX, final int pointY) {
		FocusPointX = 2 * pointX + 1;
		FocusPointY = 2 * pointY + 1;
		updateRequired = true;
	}

	public void updatePlayerMovement(final Stream str) {
		if (dir1 == -1) {
			if (updateRequired || isChatTextUpdateRequired()) {

				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
		} else if (dir2 == -1) {
			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
			str.writeBits(1, updateRequired || isChatTextUpdateRequired() ? 1
					: 0);
		} else {
			str.writeBits(1, 1);
			str.writeBits(2, 2);
			str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
			str.writeBits(3, Misc.xlateDirectionToClient[dir2]);
			str.writeBits(1, updateRequired || isChatTextUpdateRequired() ? 1
					: 0);
		}
	}

	public void updateshop(final int i) {
		final Player p = PlayerHandler.players[playerId];
		p.getShops().resetShop(i);
	}

	public void updateThisPlayerMovement(final Stream str) {
		if (mapRegionDidChange) {
			str.createFrame(73);
			str.writeWordA(mapRegionX + 6);
			str.writeWord(mapRegionY + 6);
		}
		if (didTeleport) {
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);
			str.writeBits(2, 3);
			str.writeBits(2, heightLevel);
			str.writeBits(1, 1);
			str.writeBits(1, updateRequired ? 1 : 0);
			str.writeBits(7, currentY);
			str.writeBits(7, currentX);
			return;
		}
		if (dir1 == -1) {
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			isMoving = false;
			if (updateRequired) {
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
			if (DirectionCount < 50) {
				DirectionCount++;
			}
		} else {
			DirectionCount = 0;
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);
			if (dir2 == -1) {
				isMoving = true;
				str.writeBits(2, 1);
				str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
				if (updateRequired) {
					str.writeBits(1, 1);
				} else {
					str.writeBits(1, 0);
				}
			} else {
				isMoving = true;
				str.writeBits(2, 2);
				str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
				str.writeBits(3, Misc.xlateDirectionToClient[dir2]);
				if (updateRequired) {
					str.writeBits(1, 1);
				} else {
					str.writeBits(1, 0);
				}
			}
		}
	}

	public boolean wearing2h() {
		final Player c = this;
		final String s = c.getItems().getItemName(
				c.playerEquipment[c.playerWeapon]);
		if (s.contains("2h")) {
			return true;
		} else if (s.contains("godsword")) {
			return true;
		}
		return false;
	}

	public boolean withinDistance(Player otherPlr) {
		if (heightLevel != otherPlr.heightLevel)
			return false;
		int deltaX = otherPlr.absX - absX, deltaY = otherPlr.absY - absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public boolean withinDistance(NPC npc) {
		if (heightLevel != npc.heightLevel)
			return false;
		if (npc.needRespawn == true)
			return false;
		int deltaX = npc.absX - absX, deltaY = npc.absY - absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public int constitution = playerLevel[3];

	public Player getClient(int index) {
		return (PlayerHandler.players[index]);
	}

	public boolean validClient(int index) {
		Player p = PlayerHandler.players[index];
		if ((p != null) && !p.disconnected) {
			return true;
		}
		return false;
	}

	public static void interfaceText(final Player c) {
		c.getPA().sendFrame126("The Airport", 809);
		c.getPA().sendFrame126("Ardougne", 810);
		c.getPA().sendFrame126("The Mountains", 811);
		c.getPA().sendFrame126("Karamja", 812);
		c.getPA().sendFrame126("The Desert", 813);
		c.getPA().sendFrame126("Wilderness", 12338);
		c.getPA().sendFrame126("", 12339);
	}

	public static void monsterText(final Player c) {
		// first row
		c.getPA().sendFrame126("Rock Crabs", 22428);
		c.getPA().sendFrame126("Desert Training", 22429);
		c.getPA().sendFrame126("Corporeal Beast", 22430);
		c.getPA().sendFrame126("Tormented Demons", 22431);
		c.getPA().sendFrame126("Nex", 22432);
		c.getPA().sendFrame126("", 22433);
		c.getPA().sendFrame126("", 22434);
		c.getPA().sendFrame126("", 22435);
		c.getPA().sendFrame126("", 22436);
		// second row
		c.getPA().sendFrame126("Dagonnoth Cavern", 22437);
		c.getPA().sendFrame126("Glacor Cavern", 22438);
		c.getPA().sendFrame126("Jadinko Lair", 22439);
		c.getPA().sendFrame126("Frost Dragon Cave", 22440);
		c.getPA().sendFrame126("", 22441);
		c.getPA().sendFrame126("", 22442);
		c.getPA().sendFrame126("", 22443);
		c.getPA().sendFrame126("", 22444);
		// third row
		c.getPA().sendFrame126("Knights Hall", 22445);
		c.getPA().sendFrame126("@red@Revenant Dungeon", 22446);
		c.getPA().sendFrame126("Slayer Tower", 22447);
		c.getPA().sendFrame126("Brimhaven Dungeon", 22448);
		c.getPA().sendFrame126("Taverly Dungeon", 22449);
		c.getPA().sendFrame126("Godwars Dungeon", 22450);
		c.getPA().sendFrame126("Polypore Dungeon", 22451);
		c.getPA().sendFrame126("", 22452);
	}

	public void clearPlayersInterface() {
		for (int i = 8147; i < 8348; i++) {
			getPA().sendFrame126("", i);
		}
	};

	public static void shopText(final Player c) {
		// first row
		c.getPA().sendFrame126("General Store", 26428);
		c.getPA().sendFrame126("Weapon Shop", 26429);
		c.getPA().sendFrame126("Voting Rewards", 26430);
		c.getPA().sendFrame126("Summoning 1", 26431);
		c.getPA().sendFrame126("Summoning 2", 26432);
		c.getPA().sendFrame126("Dung. Rewards", 26433);
		c.getPA().sendFrame126("Armour Shop", 26434);
		c.getPA().sendFrame126("Magic Supplies", 26435);
		c.getPA().sendFrame126("Ranging Supplies", 26436);
		// second row
		c.getPA().sendFrame126("Supply Shop", 26437);
		c.getPA().sendFrame126("Skilling Shop", 26438);
		c.getPA().sendFrame126("Slayer Shop", 26439);
		c.getPA().sendFrame126("Outfit Shop", 26440);
		c.getPA().sendFrame126("Skillcape Shop", 26441);
		c.getPA().sendFrame126("Donator Shop", 26442);
		c.getPA().sendFrame126("", 26443);
		c.getPA().sendFrame126("", 26444);
		// third row
		c.getPA().sendFrame126("", 26445);
		c.getPA().sendFrame126("", 26446);
		c.getPA().sendFrame126("", 26447);
		c.getPA().sendFrame126("", 26448);
		c.getPA().sendFrame126("", 26449);
		c.getPA().sendFrame126("", 26450);
		c.getPA().sendFrame126("", 26451);
		c.getPA().sendFrame126("", 26452);
	}

	public void smuggleStop() {
		if (inDung())
			return;
		for (int i = 0; i < DungHandler.allArmour.length; i++) {
			if (getItems().playerHasItem(DungHandler.allArmour[i])) {
				getItems().deleteEquipment();
				getItems().deleteItem(DungHandler.allArmour[i], 523523);
			}
		}
	}

	public void resetRanks() {
		for (int i = 0; i < 10; i++) {
			ranks[i] = 0;
			rankPpl[i] = "";
		}
	}

	public void highscores() {
		getPA().sendFrame126("Server Name Highscores", 6399); // Title
		for (int i = 0; i < 10; i++) {
			if (ranks[i] > 0) {
				getPA().sendFrame126(
						"Rank " + (i + 1) + ": " + rankPpl[i]
								+ "- Total Level: " + ranks[i], 6402 + i);
			}
		}
		getPA().showInterface(6308);
		flushOutStream();
		resetRanks();
	}

	public int playerRank = 0;
	public static int[] ranks = new int[11];
	public static String[] rankPpl = new String[11];

	public byte buffer[] = null;
	public boolean isLoggingOutNow = false;
	public boolean summon;
	public int totalstored;
	public boolean usingBoB;
	public int[] bobItems = new int[30];
	public int[] bobItemsN = new int[30];
	public Stream inStream = null, outStream = null;
	private IoSession session;
	public long lastAutoSave = System.currentTimeMillis();
	public Curse curse = new Curse(this);
	private final ItemAssistant itemAssistant = new ItemAssistant(this);
	private final ShopAssistant shopAssistant = new ShopAssistant(this);
	private final TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private final PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private final CombatHandler combatHandler = new CombatHandler(this);
	private SkillInterfaces skillInterfaces = new SkillInterfaces(this);
	private final BoB boB = new BoB(this);
	private final Farming farming = new Farming(this);
	private final ActionHandler actionHandler = new ActionHandler(this);
	private final Magic magic = new Magic(this);
	private final NonCombatSpells nonCombatSpells = new NonCombatSpells(this);
	private SkillingTools skillingTools = new SkillingTools();
	private final TeleTabCreation teleTabCreation = new TeleTabCreation(this);
	private static final TaskScheduler scheduler = new TaskScheduler();
	private DrawInterface DrawInterface = new DrawInterface(this);
	private final DialogueHandler dialogueHandler = new DialogueHandler(this);
	private final SummoningSpecials summoningSpecials = new SummoningSpecials(this);
	private final QuestHandler questHandler = new QuestHandler(this);
	private final Queue<Packet> queuedPackets = new LinkedList<Packet>();
	private final Potions potions = new Potions(this);
	private final PotionMixing potionMixing = new PotionMixing(this);
	private final Food food = new Food(this);

	/**
	 * Skill instances
	 */
	private final Smithing smith = new Smithing(this);
	private final SmithingInterface smithInt = new SmithingInterface(this);

	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;
	public int returnCode = 2;

	public Farming getFarming() {
		return farming;
	}

	public Curse getCurse() {
		return curse;
	}

	public static final int PACKET_SIZES[] = { 
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, // 0
		0, 0, 0, 0, 4, 0, 6, 2, 2, 0, // 10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, // 20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2, // 30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, // 40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 12, // 50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0, // 60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, // 70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0, // 80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, // 90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0, // 100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0, // 110
		1, 0, 6, 0, 0, 0, -1, /* 0 */-1, 2, 6, // 120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2, // 130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0, // 140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0, // 150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0, // 160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1, // 180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, // 190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0, // 200
		4, 0, 0, /* 0 */4, 7, 8, 0, 0, 10, 0, // 210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, // 220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0, // 230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4, // 240
		0, 0, 6, 6, 0, 0, 0 // 250
	};

	public int packetSize = 0, packetType = -1;

	public Player(IoSession session2, int _playerId) {
		this(_playerId);
		this.session = session2;
		// synchronized (this) {
		outStream = new Stream(new byte[Config.BUFFER_SIZE]);
		outStream.currentOffset = 0;

		inStream = new Stream(new byte[Config.BUFFER_SIZE]);
		inStream.currentOffset = 0;
		buffer = new byte[Config.BUFFER_SIZE];
		// }
	}

	public void resetShaking() {
		shakeScreen(1, 0, 0, 0);
	}

	public void shakeScreen(int verticleAmount, int verticleSpeed,
			int horizontalAmount, int horizontalSpeed) {
		outStream.createFrame(35); // Creates frame 35.
		outStream.writeByte(verticleAmount);
		outStream.writeByte(verticleSpeed);
		outStream.writeByte(horizontalAmount);
		outStream.writeByte(horizontalSpeed);
	}

public void camera1(int Xcoords, int Ycoords, int Height, int turnSpeed, int movementSpeed) //Toggle Cam
{
outStream.createFrame(166);	
//final int Xcoords = Xcoords / 64;
		
//final int Ycoords = Ycoords / 64; 
outStream.writeByte(Xcoords / 64);
outStream.writeByte(Ycoords / 64); 
outStream.writeWord(Height); 
outStream.writeByte(turnSpeed); 
outStream.writeByte(movementSpeed); // 0 - 99
}
public void camera2(int Xcoords, int Ycoords, int Height, int turnSpeed, int movementSpeed) //Toggle Cam
{
outStream.createFrame(177); 		
//final int Xcoords = Xcoords / 64;
		
//final int Ycoords = Ycoords / 64;
		
outStream.writeByte(Xcoords / 64);
outStream.writeByte(Ycoords / 64); 
outStream.writeWord(Height); 
outStream.writeByte(turnSpeed); 
outStream.writeByte(movementSpeed);// 0 - 99
}

public void cameraReset()
{
outStream.createFrame(107);//Resets Camera/CutScene Used for things such as the Wise Old Man robbing Draynor Bank
}

	public void ResetKeepItems() {
		WillKeepItem1 = 0;
		WillKeepItem1Slot = 0;
		WillKeepItem2 = 0;
		WillKeepItem2Slot = 0;
		WillKeepItem3 = 0;
		WillKeepItem3Slot = 0;
		WillKeepItem4 = 0;
		WillKeepItem4Slot = 0;
		WillKeepAmt1 = 0;
		WillKeepAmt2 = 0;
		WillKeepAmt3 = 0;
	}

	public void pvpLevels() {
		if (combatLevel < 15) {
			int lowLevel = 3;
			int highLevel = combatLevel + 12;
			if(inPvP()) {
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21302);
			} else if(inSafeZone() && safeTimer <= 0) {
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21202);
			} else if(inSafeZone() && safeTimer > 0){
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21402);
			}
		}
		if (combatLevel > 15 && combatLevel < 126) {
			int lowLevel = combatLevel - 12;
			int highLevel = combatLevel + 12;
			if(inPvP()) {
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21302);
			} else if(inSafeZone() && safeTimer <= 0) {
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21202);
			} else if(inSafeZone() && safeTimer > 0){
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21402);
			}
		}
		if (combatLevel > 126) {
			int lowLevel = combatLevel - 12;
			int highLevel = 138;
			if(inPvP()) {
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21302);
			} else if(inSafeZone() && safeTimer <= 0) {
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21202);
			} else if(inSafeZone() && safeTimer > 0){
				getPA().sendFrame126(lowLevel + " - " + highLevel, 21402);
			}
		}
	}

	public void correctCoordinates() {
		if (inPcGame()) {
			getPA().movePlayer(2657, 2639, 0);
		}

	}

	public void destruct() {
		isLoggingOutNow = true;
		if (session == null) {
			return;
		}
		this.getCannon().pickUpCannon();
		if (underAttackBy > 0 || underAttackBy2 > 0)
			return;
		if (clan != null) {
			clan.removeMember(this);
		}
		CycleEventHandler.getSingleton().stopEvents(this);
		// PlayerSaving.getSingleton().requestSave(playerId);
		if (familiarID > 0) {
			Summoning.dismissFamiliar(this, false, true);
		}
		if (FightPits.getState(this) != null) {
			FightPits.removePlayer(this, true);
		}
		if (this.inDung()) {
			Dungeoneering.abandonDung(this);
		}
		if (inPcGame() || inPcBoat()) {
			PestControl.removePlayerGame(this);
			getPA().movePlayer(2440, 3089, 0);
		}
        if (this.inZombieGame()) {
            this.ZombieGame.exitGame(false, this);
        }		
		/*
		 * if (clanId >= 0) { Server.clanChat.leaveClan(playerId, clanId); }
		 */
		Misc.println("[DEREGISTERED]: " + Misc.optimizeText(playerName) + "");
		Misc.println("Players Online: "
				+ ((PlayerHandler.getPlayerCount()) - 1));
		PriceChecker.clearConfig(this);
		HostList.getHostList().remove(session);
		disconnected = true;
		if (disconnected == true) {
			saveCharacter = true;
		}
		session.close();
		session = null;
		inStream = null;
		outStream = null;
		isActive = false;
		buffer = null;
		this.destruct1();
	}

	public void flushOutStream() {
		if (disconnected || outStream.currentOffset == 0) {
			return;
		}
		// synchronized (this) {
		final StaticPacketBuilder out = new StaticPacketBuilder().setBare(true);
		final byte[] temp = new byte[outStream.currentOffset];
		System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
		out.addBytes(temp);
		session.write(out.toPacket());
		outStream.currentOffset = 0;
		// }
	}

	public ActionHandler getActions() {
		return actionHandler;
	}

	public Magic getMagic() {
		return magic;
	}

	public NonCombatSpells getNonCombatSpells() {
		return nonCombatSpells;
	}

	public SkillingTools getSkillTools() {
		return skillingTools;
	}

	public TeleTabCreation getTabCreation() {
		return teleTabCreation;
	}

	public DrawInterface drawInterface() {
		return DrawInterface;
	}

	public CombatHandler getCombat() {
		return combatHandler;
	}

	public DialogueHandler getDH() {
		return dialogueHandler;
	}

	public TradeLog getTradeLog() {
		return tradeLog;
	}

	public QuestHandler getQuest() {
		return questHandler;
	}

	public SummoningSpecials getSSpec() {
		return summoningSpecials;
	}

	public Food getFood() {
		return food;
	}

	public BoB getBOB() {
		return boB;
	}

	public Stream getInStream() {
		return inStream;
	}

	public int getPacketType() {
		return packetType;
	}

	public int getPacketSize() {
		return packetSize;
	}

	public Stream getOutStream() {
		return outStream;
	}

	public ItemAssistant getItems() {
		return itemAssistant;
	}

	public SkillInterfaces getSI() {
		return skillInterfaces;
	}

	public PlayerAssistant getPA() {
		return playerAssistant;
	}

	public Potions getPotions() {
		return potions;
	}

	public PotionMixing getPotMixing() {
		return potionMixing;
	}

	public IoSession getSession() {
		return session;
	}

	public ShopAssistant getShops() {
		return shopAssistant;
	}

	/**
	 * Skill Constructors
	 */
	public Smithing getSmithing() {
		return smith;
	}

	public SmithingInterface getSmithingInt() {
		return smithInt;
	}

	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}

	public void loginMessages() {
		if (this.toggleLogin == false) {
			return;
		} else if (this.toggleLogin == true) {
			switch(playerRights) {

			case 0:
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player c2 = (Player)PlayerHandler.players[j];
						c2.sendMessage("<col=0>[Player] " +Misc.optimizeText(this.playerName)+ " has just logged in.");
					}
				}
				break;

			case 1:
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player c2 = (Player)PlayerHandler.players[j];
						c2.sendMessage("<col=255>[Moderator] " +Misc.optimizeText(this.playerName)+ " has just logged in.");
					}
				}
				break;

			case 2:
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player c2 = (Player)PlayerHandler.players[j];
						c2.sendMessage("<col=255>[Administrator] " +Misc.optimizeText(this.playerName)+ " has just logged in.");
					}
				}
				break;

			case 3:
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player c2 = (Player)PlayerHandler.players[j];
						c2.sendMessage("<col=255>[Owner] " +Misc.optimizeText(this.playerName)+ " has just logged in.");
					}
				}
				break;

			case 4:
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player c2 = (Player)PlayerHandler.players[j];
						c2.sendMessage("<col=255>[Donator] " +Misc.optimizeText(this.playerName)+ " has just logged in.");
					}
				}
				break;

			case 5:
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Player c2 = (Player)PlayerHandler.players[j];
						c2.sendMessage("<col=255>[Super Donator] " +Misc.optimizeText(this.playerName)+ " has just logged in.");
					}
				}
				break;
			}
		}
	}

	public void initialize() {
		if (Config.BETA_MODE == true && this.hasTesting == false) {
			this.getPA().giveBetaAccount();
			this.sendMessage("BETA Account made, remember to report all bugs/issues <col=255>@ .com!");
			this.hasTesting = true;
		} else if (Config.BETA_MODE == true && this.hasTesting == true) {
			this.sendMessage("Remember to report all known bugs/issues <col=255>@ .com!!");
		}
	if (this.finishedBeg == false/* && this.xpTitle.equalsIgnoreCase("none")*/) {
		this.getDH().sendDialogues(2040, 949);
	}
	if (this.getPA().rewardBook()) {
		this.sendMessage(this.getPA().getTimeLeftForDX());
	}
	if (!this.xpTitle.equalsIgnoreCase("none")) {
		this.sendMessage("@lre@Your xp mode has been turned back to normal.");
		this.xpTitle = Config.XP_NONE;
		this.loyaltyTitle = -1;
	}
	if (this.finishedBeg == true && !this.inPrime()) {
		loginScreen();
	}
	if (this.inPrime()) {
			getPA().movePlayer(absX, absY, 1);
			this.sendMessage("You are in Prime, updating minimap..");
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer c) {
					getPA().movePlayer(absX, absY, 0);
					c.stop();
				}
			}, 500);
	}
			if (this.xpLock == true) {
				this.getPA().sendFrame126("@gre@Locked", 15226);
			} else {
				this.getPA().sendFrame126("@gre@Unlocked", 15226);
			}
		// UUID = RS2LoginProtocolDecoder.UUID;
		MadTurnipConnection.addDonateItems(this,playerName);
		//this.loginMessages();
		LoyaltyProgram.saveCurrentDate(this);
		this.getYellTag();
		//this.sendMessage("@red@We are currently running on <col=255>X2 XP</col> @red@Until sunday!");
	/*if (membership == true && unMembership == false) {
		getMembersCredit().printStatus(this);
	}*/
		this.isLoggingOutNow = false;
		this.outStream.createFrame(249);
		this.outStream.writeByteA(membership ? 1 : 0);
		this.outStream.writeWordBigEndianA(playerId);
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (j == this.playerId) {
				continue;
			}
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].playerName
						.equalsIgnoreCase(playerName)) {
					this.disconnected = true;
				}
			}
		}
		if (splitChat == false) {
			getPA().sendFrame36(502, 0);
			getPA().sendFrame36(287, 0);
		}
		if (splitChat == true) {
			getPA().sendFrame36(502, 1);
			getPA().sendFrame36(287, 1);
		}
		if (this.inDung()) {
			Dungeoneering.abandonDung(this);
		}
		if (inPcBoat()) {
			getPA().movePlayer(2657, 2639, 0);
		}
		if (this.inWeaponGame()) {
			WeaponGame.leaveCave(this);
		}
		this.sidebarSettings();
		monsterText(this);
		shopText(this);
		Prayer.closeAllPrayers(this);
		this.getPA().handleWeaponStyle();
		this.getPA().handleLoginText();
		this.getPA().sendFrame126(
				"@yel@Total Level: " + this.getPA().totalLevel(), 3984);
		this.accountFlagged = this.getPA().checkForFlags();
		this.getPA().setChatOptions(0, 0, 0); // reset private messaging options
		getPA().sendFrame126(":prayer:" + (altarPrayed == 1 ? "curses" : "prayers"), -1);
		this.reloadAllFrames();
		this.sendMessage(Config.WELCOME_MESSAGE);
		this.getPA().showOption(5, 0, "Trade With", 3);
		this.drawInterface().drawMainTab();
		this.getAllStarterItems();
		this.saveTimer = Config.SAVE_TIMER;
		this.saveCharacter = true;
		Misc.println("[REGISTERED]: " + Misc.optimizeText(this.playerName) + "");
		this.handler.updatePlayer(this, this.outStream);
		this.handler.updateNPC(this, outStream);
		this.flushOutStream();
		this.getPA().clearClanChat();
		this.getPA().resetFollow();
		if (inGodWars()) {
			this.getPA().sendFrame126("@cya@" + godwarsKillCount[0], 16217);
			this.getPA().sendFrame126("@cya@" + godwarsKillCount[3], 16216);
			this.getPA().sendFrame126("@cya@" + godwarsKillCount[2], 16218);
			this.getPA().sendFrame126("@cya@" + godwarsKillCount[1], 16219);
		}
		if (this.familiarID > 0) {
			Summoning.spawnFamiliar(this, this.familiarID);
			this.getPA().refreshBoB();
		}
		if (this.autoRet == 1) {
			this.getPA().sendFrame36(172, 1);
		} else {
			this.getPA().sendFrame36(172, 0);
		}
		if (this.playerMagicBook == 0 || this.playerMagicBook == 1) {
			this.setSidebarInterface(6, 1151); // modern
		} else if (this.playerMagicBook == 2) {
			this.setSidebarInterface(6, 12855); // ancient
			if (this.DT < 6) {
				this.setSidebarInterface(6, 1151); // modern
				sendMessage("Your ancient magic tab has been reset to normals.");
			}
		} else if (this.playerMagicBook == 3) {
			this.setSidebarInterface(6, 29999); // lunar
		}
		getPA().sendFrame126("Server Name Top 10", 6399);
		getPA().sendFrame126("Close Window", 6401);
		getPA().sendFrame126(" ", 6402);
		getPA().sendFrame126(" ", 6403);
		getPA().sendFrame126(" ", 6404);
		getPA().sendFrame126(" ", 6405);
		//getPA().sendFrame126(Config.SERVER_NAME, 640);
		getPA().sendFrame126(" ", 6406);
		getPA().sendFrame126(" ", 6407);
		getPA().sendFrame126(" ", 6408);
		getPA().sendFrame126(" ", 6409);
		getPA().sendFrame126(" ", 6410);
		getPA().sendFrame126(" ", 6411);
		getPA().sendFrame126(" ", 8578);
		getPA().sendFrame126(" ", 8579);
		getPA().sendFrame126(" ", 8580);
		getPA().sendFrame126(" ", 8581);
		getPA().sendFrame126(" ", 8582);
		getPA().sendFrame126(" ", 8583);
		getPA().sendFrame126(" ", 8584);
		getPA().sendFrame126(" ", 8585);
		getPA().sendFrame126(" ", 8586);
		getPA().sendFrame126(" ", 8587);
		getPA().sendFrame126(" ", 8588);
		getPA().sendFrame126(" ", 8589);
		getPA().sendFrame126(" ", 8590);
		getPA().sendFrame126(" ", 8591);
		getPA().sendFrame126(" ", 8592);
		getPA().sendFrame126(" ", 8593);
		getPA().sendFrame126(" ", 8594);
		getPA().sendFrame126(" ", 8595);
		getPA().sendFrame126(" ", 8596);
		getPA().sendFrame126(" ", 8597);
		getPA().sendFrame126(" ", 8598);
		getPA().sendFrame126(" ", 8599);
		getPA().sendFrame126(" ", 8600);
		getPA().sendFrame126(" ", 8601);
		getPA().sendFrame126(" ", 8602);
		getPA().sendFrame126(" ", 8603);
		getPA().sendFrame126(" ", 8604);
		getPA().sendFrame126(" ", 8605);
		getPA().sendFrame126(" ", 8606);
		getPA().sendFrame126(" ", 8607);
		getPA().sendFrame126(" ", 8608);
		getPA().sendFrame126(" ", 8609);
		getPA().sendFrame126(" ", 8610);
		getPA().sendFrame126(" ", 8611);
		getPA().sendFrame126(" ", 8612);
		getPA().sendFrame126(" ", 8613);
		getPA().sendFrame126(" ", 8614);
		getPA().sendFrame126(" ", 8615);
		getPA().sendFrame126(" ", 8616);
		getPA().sendFrame126(" ", 8617);
		getPA().setClanData();
		if (lastClanChat != null && lastClanChat.length() > 0) {
			Clan clan = Server.clanManager.getClan(lastClanChat);
			if (clan != null) {
				clan.addMember(this);
			}
		}
		for (int i = 0; i < 25; i++) {
			this.getPA()
			.setSkillLevel(i, this.playerLevel[i], this.playerXP[i]);
			this.getPA().refreshSkill(i);
		}
		if (this.inFightPits() || this.inPitsRoom()) {
			this.getPA().movePlayer(2399, 5177, 0);
		}
		clearPlayersInterface();
	}

	public void logout() {
		if (System.currentTimeMillis() - logoutDelay > 10000) {
			CycleEventHandler.getSingleton().stopEvents(this);
			outStream.createFrame(109);
			properLogout = true;
			isLoggingOutNow = true;
			if (clan != null) {
				clan.removeMember(this);
			}
			if (familiarID > 0) {
				Summoning.dismissFamiliar(this, false, true);
			}
			if (this.inDung()) {
				Dungeoneering.abandonDung(this);
			}
        if (this.inZombieGame()) {
            this.ZombieGame.exitGame(false, this);
        }			
			/*if (this.playerRights != 2 && this.playerRights != 3) {
				if (System.currentTimeMillis() - this.lastHighscore > 900000) {
					Highscores.save(this);
					this.lastHighscore = System.currentTimeMillis();
				}
			}
			if(!HighscoresHandler.inProcess) {
				//HighscoresHandler hs = new HighscoresHandler(this);
				//hs.start();
			}*/
		} else {
			sendMessage("You must wait a few seconds from being out of combat to logout.");
		}
	}

	public void walk(int EndX, int EndY, int Emote) {
		walkToEmote(Emote);
		getPA().walkTo2(EndX, EndY);
	}

	public void walkToEmote(int id) {
		isRunning2 = false;
		playerWalkIndex = id;
		getPA().requestUpdates(); // this was needed to make the agility work
	}

	public void stopEmote() {
		playerWalkIndex = 0x333;
		agilityEmote = false;
		isRunning2 = true;
		getPA().requestUpdates(); // this was needed to make the agility work
	}

	public int totalLevel() {
		int total = 0;
		for (int i = 0; i <= 20; i++) {
			total += getPA().getLevelForXP(playerXP[i]);
		}
		return total;
	}

	/**
	 * Sound
	 **/

	public int soundVolume = 10;

	/**
	 * Outputs a send packet which is built from the data params provided
	 * towards a connected user client channel.
	 * 
	 * @param id
	 *            The identification number of the sound.
	 * @param volume
	 *            The volume amount of the sound (1-100)
	 * @param delay
	 *            The delay (0 = immediately 30 = 1/2cycle 60=full cycle) before
	 *            the sound plays.
	 */
	public void sendSound(int id, int volume, int delay) {
		try {
			outStream.createFrameVarSize(174);
			outStream.writeWord(id);
			outStream.writeByte(volume);
			outStream.writeWord(delay);
			updateRequired = true;
			appearanceUpdateRequired = true;
			outStream.endFrameVarSize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Outputs a send packet which is built from the data params provided
	 * towards a connected user client channel.
	 * 
	 * @param id
	 *            The identification number of the sound.
	 * @param volume
	 *            The volume amount of the sound (1-100)
	 */
	public void sendSound(int id, int volume) {
		sendSound(id, volume, 0);
	}

	/**
	 * Outputs a send packet which is built from the data params provided
	 * towards a connected user client channel.
	 * 
	 * @param id
	 *            The identification number of the sound.
	 */
	public void sendSound(int id) {
		sendSound(id, 100);// pretty sure it's 100 just double check
		// otherwise it will be 1
	}

	/**
	 * Play sounds
	 * 
	 * @param SOUNDID
	 *            : ID
	 * @param delay
	 *            : SOUND DELAY
	 */
	public void playSound(int SOUNDID, int delay) {
		if (Config.SOUND) {
			if (soundVolume <= -1) {
				return;
			}
			/**
			 * Deal with regions We dont need to play this again because you are
			 * in the current region
			 */
			if (this != null) {
				if (soundVolume >= 0) {
					if (goodDistance(absX, absY, this.absX, this.absY, 2)) {
						System.out.println("Playing sound " + playerName
								+ ", Id: " + SOUNDID + ", Vol: " + soundVolume);
						getOutStream().createFrame(174);
						getOutStream().writeWord(SOUNDID);
						getOutStream().writeByte(soundVolume);
						getOutStream().writeWord(/* delay */0);
					}
				}
			}

		}
	}

	private Sound sound = new Sound(this);

	public int ppsLeft = 3000;
	public boolean ppsRefresh = false;

	public Sound getSound() {
		return sound;
	}

	public void obsticle(int Emote, int Req, int newX, int newY,
			final int agilityTimer, int amtEXP, String message) {
		if (playerLevel[16] >= Req) {
			agilityEmote = true;
			walk(newX, newY, Emote);
			getPA().addSkillXP(amtEXP, playerAgility);
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer c) {
					stopEmote();
					c.stop();
				}
			}, agilityTimer);
		} else {
			sendMessage("You Need " + Req + " Agility To Do This Obsticle");
		}
	}

	public void agilityDelay(int Emote, final int X, final int Y, final int H,
			int Req, int amtEXP, String message) {
		if (playerLevel[16] >= Req) {
			this.startAnimation(Emote);
			agilityEmote = true;
			getPA().addSkillXP(amtEXP, playerAgility);
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer c) {
					getPA().movePlayer(X, Y, H);
					agilityEmote = false;
					c.stop();
				}
			}, 1000);
		} else {
			sendMessage("You Need " + Req + " Agility To Do This Obsticle");
		}
	}

	public boolean isOwner() {
		return (playerName.equalsIgnoreCase("jlyons") || playerName.equalsIgnoreCase("lawless") || playerName.equalsIgnoreCase("luis")
				|| playerName.equalsIgnoreCase("jake")) ;
	}
	public int totalPlaytime(){
	return (pTime / 2);}
	public String getPlaytime(){
			int DAY = (totalPlaytime() / 86400);
			int HR = (totalPlaytime() / 3600) - (DAY * 24);
			int MIN = (totalPlaytime() / 60) - (DAY * 1440) - (HR * 60);
			return ("Days:"+DAY+" Hours:"+HR+" Minutes:"+MIN+"");
		}
	public String getSmallPlaytime(){
			int DAY = (totalPlaytime() / 86400);
			int HR = (totalPlaytime() / 3600) - (DAY * 24);
			int MIN = (totalPlaytime() / 60) - (DAY * 1440) - (HR * 60);
			return ("Day:"+DAY+"/Hr:"+HR+"/Min:"+MIN+"");
		}
	public void process() {
		if (PlayerHandler.updateRunning) {
			long start = System.currentTimeMillis();
		}
		if (PlayerHandler.kickAllPlayers) {
			System.exit(0);
		}
		if (System.currentTimeMillis() - this.lastAutoSave > 300000) {
			if (this != null) {
				PlayerSave.saveGame(this);
				this.lastAutoSave = System.currentTimeMillis();
				System.out.println("Saved");
			}
		}
		getPA().sendFrame126("Players Online: "+ PlayerHandler.getPlayerCount() +" ", 640); 
		getPA().refreshSkill(21);
		getPA().refreshSkill(22);
		getPA().refreshSkill(23);
		smuggleStop();
		if (System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			specDelay = System.currentTimeMillis();
			if (specAmount < 10) {
				specAmount++;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}

		if (this.playerEnergy < 100 && !this.isRunning()) {
			if (System.currentTimeMillis() > this.getPA()
					.getAgilityRunRestore() + this.lastRunRecovery) {
				this.playerEnergy++;
				this.lastRunRecovery = System.currentTimeMillis();
				this.getPA().sendFrame126(this.playerEnergy + "%", 149);
			}
		}
		if (this.smeltTimer > 0 && this.smeltType > 0) {
			this.smeltTimer--;
		} else if (this.smeltTimer == 0 && this.smeltType > 0) {
			this.getSmithing().smelt(this.smeltType);
		}
		if(pTime != 2147000000) {
			pTime++;
		}
		if(inPvP()) {
			safeTimer = 11;
		}
		if(safeTimer > 0 && !inPvP()) {
			safeTimer--;
			//sendMessage(safeTimer + " Seconds left.");
		}
		if(safeTimer >= 2) {
			if(isSkulled && isPVPActive) {
				headIconPk = 3;

			} else if(!isSkulled) {
				headIconPk = 2;
			}
			//headIconHints = 2;
			//getPA().createPlayerHints(10, playerId);
			getPA().requestUpdates();	
		}
		if (safeTimer <= 1 && !isPVPActive) {
			headIconPk = -1;
			getPA().requestUpdates();
		}

		if (followId > 0) {
			getPA().followPlayer();
		} else {
			getPA().sendFrame126("[FI]" + followId, 2000);
		}
		if (followId2 > 0) {
			getPA().followNpc();
		} else {
			getPA().sendFrame126("[FA]" + followId2, 2000);
		}
		if (System.currentTimeMillis() - this.singleCombatDelay > 3300) {
			this.underAttackBy = 0;
		}
		if (System.currentTimeMillis() - this.singleCombatDelay2 > 3300) {
			this.underAttackBy2 = 0;
		}
		// if (System.currentTimeMillis() - this.prayerDelay > 2000)
		// Prayer.handlePrayerDrain(this);
		/**
		 * This should not be called here cause in the first place it was in the
		 * event and calling it in process didnt work so should change it here
		 * can you make use of system current time millis in the event?
		 */
		this.poisonProcess();


		if (System.currentTimeMillis() - this.restoreStatsDelay > 60000) {
			this.restoreStatsDelay = System.currentTimeMillis();
			for (int level = 0; level < this.playerLevel.length; level++) {
				if (level != 23) {
					if (this.playerLevel[level] < this
							.getLevelForXP(this.playerXP[level])) {
						if (level != 5 && level != 22) { // prayer doesn't
							// restore
							this.playerLevel[level] += 1;
							this.getPA().setSkillLevel(level,
									this.playerLevel[level],
									this.playerXP[level]);
							this.getPA().refreshSkill(level);
						}
					} else if (this.playerLevel[level] > this
							.getLevelForXP(this.playerXP[level])) {
						this.playerLevel[level] -= 1;
						this.getPA().setSkillLevel(level,
								this.playerLevel[level], this.playerXP[level]);
						this.getPA().refreshSkill(level);
					}
				} else {
					if (this.playerLevel[23] < getPA().getLevelForDungXP(
							this.playerXP[23])) {
						this.playerLevel[23] += 1;
						this.getPA().setSkillLevel(23, this.playerLevel[23],
								this.playerXP[23]);
						this.getPA().refreshSkill(23);
					} else if (this.playerLevel[23] > getPA()
							.getLevelForDungXP(this.playerXP[23])) {
						this.playerLevel[23] -= 1;
						this.getPA().setSkillLevel(23, this.playerLevel[23],
								this.playerXP[23]);
						this.getPA().refreshSkill(23);
					}
				}
			}
		}
		this.getPA().showOption(4, 0, "Follow", 1);
		if (BountyHunter.inBH(this) && this.playerTarget >= 0) {
			BountyHunter.handleNewTarget(this);
		}
		if (BountyHunter.safeArea(this)) {
			headIconPk = -1;
			getPA().requestUpdates();
			getPA().walkableInterface(-1);	
		}
		if (this.inWild() && !this.inFunPk() && !BountyHunter.inBH(this) && !BountyHunter.safeArea(this)) {
			final int modY = this.absY > 6400 ? this.absY - 6400 : this.absY;
			this.wildLevel = (modY - 3520) / 8 + 1;
			this.getPA().sendFrame126("@yel@Level: " + this.wildLevel,
					199);
			if (!this.inFightPits()) {
				this.getPA().walkableInterface(197);
				
			}
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (this.inMulti()) {
					this.getPA().sendFrame126("@yel@Level: " + this.wildLevel,
							199);
				} else {
					this.getPA().sendFrame126("@yel@Level: " + this.wildLevel,
							199);
				}
			} else {
				this.getPA().multiWay(-1);
				this.getPA().sendFrame126("@yel@Level: " + this.wildLevel, 199);
			}
			this.getPA().showOption(3, 0, "Attack", 1);
		} else if (playerEquipment[playerWeapon] == 11951 && !isPVPActive == true) {
			getPA().showOption(3, 0, "Throw at", 1);
			getPA().walkableInterface(-1);
		} else if (BountyHunter.inBH(this)) {
			wildLevel = 138;
			if (playerTarget > 0) {
				getPA().sendFrame126(targetName, 25350);
			} else {
				getPA().sendFrame126("None", 25350);
			}
			if (cantLeavePenalty > 0 || pickupPenalty > 0) {
				getPA().sendFrame126("" + BountyHunter.getTime(this) + " Sec", 25352);
			} else {
				getPA().sendFrame126("", 25352);
			}
			getPA().showOption(3, 0, "Attack", 1);
			getPA().walkableInterface(25347);
			getPA().sendFrame126(""+ (cantLeavePenalty > 0 ? ("Can't leave for:") : pickupPenalty > 0 ? ("Pickup penalty:") : ("")) + "", 25351);
			headIconPk = BountyHunter.getPlayerSkull(this);
			getPA().requestUpdates();		
		} else if(inFunPk() && !this.inPvP()){
			getPA().walkableInterface(197);
			getPA().sendFrame126("@yel@FunPk", 199);
			getPA().showOption(3, 0, "Attack", 1);
			wildLevel = 55;
		} else if (this.inWeaponGame()) {
			this.getPA().walkableInterface(18100);
			this.getPA().showOption(3, 0, "Attack", 1);
		} else if (Zombies.playerInGame(this) && Zombies.STARTED == true) {
            Zombies.interfaceSend(this);
            this.getPA().showOption(3, 0, "null", 1);
            this.getPA().showOption(2, 0, "null", 1);		
		} else if (this.inDuelArena()) {
			if (familiarID > 0) {
				getPA().movePlayer(2612, 3344, 0);
				sendMessage("You cannot bring a familiar in here!");
			}
			this.getPA().walkableInterface(201);
			if (this.duelStatus == 5) {
				this.getPA().showOption(3, 0, "Attack", 1);
			} else {
				this.getPA().showOption(3, 0, "Challenge", 1);
			}
		} else if (this.inBarrows()) {
			// this.getPA().sendFrame99(true);
			this.getPA().sendFrame126("Kill Count: " + this.barrowsKillCount,
					4536);
			this.getPA().walkableInterface(4535);
		} else if (this.inPcBoat()) {
			this.getPA().walkableInterface(21119);
		} else if (this.inGodWars()) {
			this.getPA().walkableInterface(16210);
		} else if (this.inPcGame()) {
			this.getPA().walkableInterface(21100);
		} else if (this.inFightPits()) {
			this.wildLevel = 300;
			this.getPA().showOption(3, 0, "Attack", 1);
		} else if (this.absX == 2657 && this.absY == 2639) {
			this.getPA().walkableInterface(-1);
		} else if (this.inPits) {
			this.getPA().showOption(3, 0, "Attack", 1);
		} else if (this.getPA().inPitsWait()) {
			this.getPA().showOption(3, 0, "Null", 1);
		} else {
			this.getPA().showOption(3, 0, "Null", 1);
			//this.getPA().walkableInterface(-1);
			if(!hasAchieved) {
				getPA().walkableInterface(-1);
			}
		}
		if ((!inPvP() || !isPVPActive) && safeTimer > 0) {
			wildLevel = 12;
			this.getPA().showOption(3, 0, "Attack", 1);
			getPA().walkableInterface(21400);
			getPA().sendFrame126("" + safeTimer, 21403);
			pvpLevels();
		} else if (inPvP() && isPVPActive ) {
			wildLevel = 12;
			safeTimer = 11;
			this.getPA().showOption(3, 0, "Attack", 1);
			getPA().walkableInterface(21300);
			pvpLevels();
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inSafeZone() && isPVPActive) {
			getPA().walkableInterface(21200);
			this.getPA().showOption(3, 0, "Attack", 1);
			pvpLevels();
		}

		if (!this.hasMultiSign && this.inMulti()) {
			this.hasMultiSign = true;
			this.getPA().multiWay(1);
		}

		if (this.hasMultiSign && !this.inMulti()) {
			this.hasMultiSign = false;
			this.getPA().multiWay(-1);
		}

		if (this.skullTimer > 0) {
			this.skullTimer--;
			if (this.skullTimer == 1) {
				this.isSkulled = false;
				this.attackedPlayers.clear();
				this.headIconPk = -1;
				this.skullTimer = -1;
				this.getPA().requestUpdates();
			}
		}

		if (this.isDead && this.respawnTimer == -6) {
			this.getPA().applyDead();
		}

		if (this.respawnTimer == 7) {
			this.respawnTimer = -6;
			this.getPA().giveLife();
		} else if (this.respawnTimer == 12) {
			this.respawnTimer--;
			this.startAnimation(0x900);
			this.poisonDamage = -1;
		}

		if (this.respawnTimer > -6) {
			this.respawnTimer--;
		}

		if (this.freezeTimer > -6) {
			this.freezeTimer--;
			if (this.frozenBy > 0) {
				if (PlayerHandler.players[this.frozenBy] == null) {
					this.freezeTimer = -1;
					frozenBy = -1;
				} else if (!this.goodDistance(this.absX, this.absY,
						PlayerHandler.players[this.frozenBy].absX,
						PlayerHandler.players[frozenBy].absY, 20)) {
					this.freezeTimer = -1;
					this.frozenBy = -1;
				}
			}
		}

		if (this.hitDelay > 0) {
			this.hitDelay--;
		}

		if (this.teleTimer > 0) {
			this.teleTimer--;
			if (!this.isDead) {
				if (this.teleTimer == 1 && this.newLocation > 0) {
					this.teleTimer = 0;
					this.getPA().changeLocation();
				}
				if (this.teleTimer == 5) {
					this.teleTimer--;
					this.getPA().processTeleport();
				}
				if (this.teleTimer == 9 && this.teleGfx > 0) {
					this.teleTimer--;
					this.gfx100(teleGfx);
				}
			} else {
				this.teleTimer = 0;
			}
		}

		if (hitDelay == 1) {
			if (oldNpcIndex > 0) {
				getCombat().delayedHit(this, oldNpcIndex);
			}
			if (oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(this, oldPlayerIndex);
			}
		}

		if (this.attackTimer > 0) {
			this.attackTimer--;
		}

		if (this.attackTimer == 1) {
			if (this.npcIndex > 0 && this.clickNpcType == 0) {
				this.getCombat().attackNpc(this.npcIndex);
			}
			if (this.playerIndex > 0) {
				this.getCombat().attackPlayer(this.playerIndex);
			}
		} else if (this.attackTimer <= 0
				&& (this.npcIndex > 0 || this.playerIndex > 0)) {
			if (this.npcIndex > 0) {
				this.attackTimer = 0;
				this.getCombat().attackNpc(this.npcIndex);
			} else if (this.playerIndex > 0) {
				this.attackTimer = 0;
				this.getCombat().attackPlayer(this.playerIndex);
			}
		}

		if (this.inTrade && this.tradeResetNeeded) {
			final Player o = PlayerHandler.players[this.tradeWith];
			if (o != null) {
				if (o.tradeResetNeeded) {
					this.getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
	}

	public synchronized boolean processPacket(final Packet p) {
		// synchronized (this) {
		if (p == null) {
			return false;
		}
		this.inStream.currentOffset = 0;
		this.packetType = p.getId();
		this.packetSize = p.getLength();
		this.inStream.buffer = p.getData();
		if (this.packetType > 0) {
			// sendMessage("PacketType: " + packetType);
			PacketHandler.processPacket(this, this.packetType, this.packetSize);
		}
		this.timeOutCounter = 0;
		return true;
		// }
	}

	/*public synchronized boolean processQueuedPackets() {
		Packet p = null;
		synchronized (this.queuedPackets) {
			p = this.queuedPackets.poll();
		}
		if (p == null) {
			return false;
		}
		this.inStream.currentOffset = 0;
		this.packetType = p.getId();
		this.packetSize = p.getLength();
		this.inStream.buffer = p.getData();
		if (this.packetType > 0) {
			// sendMessage("PacketType: " + packetType);
			PacketHandler.processPacket(this, this.packetType, this.packetSize);
		}
		this.timeOutCounter = 0;
		return true;
	}*/

	public synchronized boolean processQueuedPackets() {
		Packet p = null;
		synchronized(queuedPackets) {
			p = queuedPackets.poll();
		}
		if(p == null) {
			return false;
		}
		inStream.currentOffset = 0;
		packetType = p.getId();
		packetSize = p.getLength();
		inStream.buffer = p.getData();
		if(packetType > 0) {
			//sendMessage("PacketType: " + packetType);
			PacketHandler.processPacket(this, packetType, packetSize);
		}
		timeOutCounter = 0;
		return true;
	}

	/**
	 * End of Skill Constructors
	 */

	public void queueMessage(final Packet arg1) {
		//synchronized (this.queuedPackets) {
			// if (arg1.getId() != 41)
			this.queuedPackets.add(arg1);
			// else
			// processPacket(arg1);
		//}
	}

	public void sendMessage(final String s) {
		// synchronized (this) {
		if (getOutStream() != null) {
			outStream.createFrameVarSize(253);
			outStream.writeString(s);
			outStream.endFrameVarSize();
		}
		// }
	}

	public void setSidebarInterface(final int menuId, final int form) {
		// synchronized (this) {
		if (getOutStream() != null) {
			outStream.createFrame(71);
			outStream.writeWord(form);
			outStream.writeByteA(menuId);
		}
		// }
	}

	public void setRights(final int i) {
		this.playerRights = i;
	}

	public void update() {
		// synchronized (this) {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
		// }
	}

	public void sidebarSettings() {
		this.setSidebarInterface(1, 3917);
		this.setSidebarInterface(2, 638);
		this.setSidebarInterface(3, 3213);
		this.setSidebarInterface(4, 1644);
		this.setSidebarInterface(5, curses ? 22500 : 5608);
		this.correctCoordinates();
		if (inFightCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				@Override
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave(PlayerHandler.players[playerId]);
					c.stop();
				}
			}, 10000);

		}
		this.setSidebarInterface(8, 5065);
		this.setSidebarInterface(9, 5715);
		this.setSidebarInterface(7, 18128);
		//this.setSidebarInterface(13, 17000);
		//this.setSidebarInterface(14, -1);
		//this.setSidebarInterface(14, 8000);
		// this.setSidebarInterface(15, 17000); //music
		this.setSidebarInterface(10, 2449); // logout X
		this.setSidebarInterface(11, 904); // wrench tab
		this.setSidebarInterface(12, 147); // emote
		this.setSidebarInterface(13, 962);
		//this.setSidebarInterface(16, 18017); // summon
		//this.setSidebarInterface(15, 8000);
		this.setSidebarInterface(15, -1);
		this.setSidebarInterface(16, -1); // summon
		this.setSidebarInterface(0, 2423); // fight tab
		this.setSidebarInterface(14, -1); // achievement
	}

	public void sidebarSettings2() {
		this.setSidebarInterface(1, -1);
		this.setSidebarInterface(2, -1);
		this.setSidebarInterface(3, -1);
		this.setSidebarInterface(4, -1);
		this.setSidebarInterface(5, -1);
		this.correctCoordinates();
		this.setSidebarInterface(7, -1);
		this.setSidebarInterface(8, -1);
		this.setSidebarInterface(9, -1);
		this.setSidebarInterface(10, -1);
		this.setSidebarInterface(11, -1); // wrench tab
		this.setSidebarInterface(12, -1); // run tab
		this.setSidebarInterface(13, -1);
		this.setSidebarInterface(0, -1);
		this.setSidebarInterface(14, 2449);
	}

	public void getAllStarterItems() {
		this.getItems().resetItems(3214);
		this.getItems()
		.sendWeapon(
				this.playerEquipment[this.playerWeapon],
				getItems().getItemName(
						this.playerEquipment[this.playerWeapon]));
		this.getItems().resetBonus();
		this.getItems().getBonus();
		this.getItems().writeBonus();
		this.getItems().setEquipment(this.playerEquipment[this.playerHat], 1,
				this.playerHat);
		this.getItems().setEquipment(this.playerEquipment[this.playerCape], 1,
				this.playerCape);
		this.getItems().setEquipment(this.playerEquipment[this.playerAmulet],
				1, this.playerAmulet);
		this.getItems().setEquipment(this.playerEquipment[this.playerArrows],
				this.playerEquipmentN[this.playerArrows], this.playerArrows);
		this.getItems().setEquipment(this.playerEquipment[this.playerChest], 1,
				this.playerChest);
		this.getItems().setEquipment(this.playerEquipment[this.playerShield],
				1, this.playerShield);
		this.getItems().setEquipment(this.playerEquipment[this.playerLegs], 1,
				this.playerLegs);
		this.getItems().setEquipment(this.playerEquipment[this.playerHands], 1,
				this.playerHands);
		this.getItems().setEquipment(this.playerEquipment[this.playerFeet], 1,
				this.playerFeet);
		this.getItems().setEquipment(this.playerEquipment[this.playerRing], 1,
				this.playerRing);
		this.getItems().setEquipment(this.playerEquipment[this.playerWeapon],
				this.playerEquipmentN[this.playerWeapon], this.playerWeapon);
		this.getCombat().getPlayerAnimIndex(
				getItems().getItemName(this.playerEquipment[this.playerWeapon])
				.toLowerCase());
		this.getPA().logIntoPM();
		this.getItems().addSpecialBar(this.playerEquipment[this.playerWeapon]);
	}

	public void reloadAllFrames() {
		getPA().sendFrame126(playerEnergy + "%", 149);
		getPA().sendFrame36(108, 0); // resets autocast button
		getPA().sendFrame36(172, 1);
		getPA().sendFrame107(); // reset screen
	}

	public int x1 = -1;
	public int y1 = -1;
	public int x2 = -1;
	public int y2 = -1;
	public int speed1 = -1;
	public int speed2 = -1;
	public int direction = -1;
	public boolean inEvent = false;
	public boolean openedDoor = false, kamfreenaDone;

	public void setForceMovement(final int x2, final int y2, boolean x1, boolean y1, final int speed1, final int speed2, final int direction,
			final int emote, final int x, final int y, final int time) {
		inEvent = true;
		this.x1 = currentX;
		this.y1 = currentY;
		this.x2 = x1 ? currentX + x2 : currentX - x2;
		this.y2 = y1 ? currentY + y2 : currentY - y2;
		this.speed1 = speed1;
		this.speed2 = speed2;
		this.direction = direction;
		this.startAnimation(emote);
		getUpdateFlags().flag(UpdateFlag.FORCE_MOVEMENT);
		final Player c = (Player) this;
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer even) {
				c.getPA().movePlayer(x, y, c.heightLevel);
				//getUpdateFlags().flag(UpdateFlag.APPEARANCE);
				inEvent = false;
				even.stop();
			}
		}, time);
	}

	/**
	 * Yelling System
	 */
	public String[] defaultYellTag = { "[<col=0000FF>Player</col>]", "<col=0000FF>[Ingame-Mod]</col>@cr1@", "<col=0000FF>[Staff]</col>@cr2@<col=0000FF>", "@or2@@cr2@[Owner]</col>@cr2@<col=255>", "[@red@Donator@bla@]@cr3@", 
			"[<col=00c130>Super Donator</col>]@cr4@", "[<col=00a8cc>Extreme Donator</col>]", "[@or2@Server Helper@bla@]" };

	public double getran, getmag;
	public int solProtect = 0;
	public boolean specOn = false;

	public void getYellTag() {
		if (customYell == true)
			return;
		for (int i = 0; i < 8; i++) {
			if (playerRights == i){
				yellTag = defaultYellTag[i];
				return;
			}
		}
	}

	public void saveGame() {
		PlayerSave.saveGame(this);
	}

	public Player getClient(String name) {
		name = name.toLowerCase();
		for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(validClient(i)) {
				Player client = getClient(i);
				if(client.playerName.toLowerCase().equalsIgnoreCase(name)) {
					return client;
				}
			}
		}
		return null;
	}

	public boolean validClient(String name) {
		return validClient(getClient(name));
	}

	public boolean validClient(Player client) {
		return (client != null && !client.disconnected);
	}

	public String getShortValue(long amount) {
		String value = "";
		if (amount < 1000)
			value = "(" + amount + ")GP";
		if (amount >= 1000 && amount < 1000000)
			value = "(" + amount / 1000 + "K)GP";
		if (amount >= 1000000 && amount < 1000000000)
			value = "(" + amount / 1000000 + "M)GP";
		if (amount >= 1000000000)
			value = "(" + amount / 1000000000 + "B)GP";
		return value;
	}

	public boolean hasSpace() {
		if (getItems().freeSlots() > 0) {
			return true;
		}
		PlayerAssistant.stopSkilling(this);
		sendMessage("You do not have enough inventory space.");
		return false;
	}

	public static TaskScheduler getTaskScheduler() {
		return scheduler;
	}

	public String getName() {
		return playerName;
	}

	/**
	 * Tool Belt Start
	 */
	public void hatchet(Player c, int itemId) {
		switch (itemId) {
		case 1351: //Bronze
			c.hatchet = 1;
			break;
		case 1349: //Iron
			c.hatchet = 2;
			break;
		case 1353: //Steel
			c.hatchet = 3;
			break;
		case 1361: //Black
			c.hatchet = 4;
			break;
		case 1355: //Mithril
			c.hatchet = 5;
			break;
		case 1357: //Adamant
			c.hatchet = 6;
			break;
		case 1359: //Rune
			c.hatchet = 7;
			break;
		case 6739: //Dragon
			c.hatchet = 8;
			break;
		}
		c.getItems().deleteItem(itemId, 1);
		c.sendMessage("You added a "+c.getItems().getItemName(itemId)+" to your tool belt");
	}

	public void pickAxes(Player c, int itemId) {
		switch (itemId) {
		case 1265: //Bronze
			c.pickAxe = 1;
			break;
		case 1267: //Iron
			c.pickAxe = 2;
			break;
		case 1269: //Steel
			c.pickAxe = 3;
			break;
		case 1273: //Mithril
			c.pickAxe = 4;
			break;
		case 1271: //Adamant
			c.pickAxe = 5;
			break;
		case 1275: //Rune
			c.pickAxe = 6;
			break;
		case 15259: //Dragon
			c.pickAxe = 7;
			break;
		}
		c.getItems().deleteItem(itemId, 1);
		c.sendMessage("You added a "+c.getItems().getItemName(itemId)+" to your tool belt");
	}

	public void generalTools(Player c, int itemId) {
		switch (itemId) {
		case 1755: //Chisel
			c.tools = 0;
			break;
		case 2347: //Hammer
			c.tools = 1;
			break;
		case 10010: //Butterfly Net
			c.tools = 2;
			break;
		}
		c.getItems().deleteItem(itemId, 1);
		c.sendMessage("You added a "+c.getItems().getItemName(itemId)+" to your tool belt");
	}
	/**
	 * Tool Belt End
	 */

	public CycleEvent skilling;

	public int barbarianKilled, barbarianToKill;

	public int TVM;

	public CycleEvent getSkilling() {
		return skilling;
	}

	public void stopSkilling() {
		if (skilling != null) {
			skilling.stop();
			skilling = null;
		}
	}
	
	public void snowball(final Player c) {
		Player o = (Player) PlayerHandler.players[playerIndex];
		final int oX = c.getX();
		final int oY = c.getY();
		final int pX = o.getX();
		final int pY = o.getY();
		final int offX = (oY - pY) * -1;
		final int offY = (oX - pX) * -1;
		c.getPA().createProjectile(oX, oY, offX, offY, 50, 90, 1281, 21, 21,
				getId() - 1, 65);
		startAnimation(7530);
		c.turnPlayerTo(pX, pY);
		EventManager.getSingleton().addEvent(new Event() {
			Player o = (Player) PlayerHandler.players[playerIndex];
			int snowballtime = 3;

			public void execute(EventContainer snowball) {

				if (snowballtime == 1) {
					o.gfx100(1282);
				}
				if (this == null || snowballtime <= 0) {
					snowball.stop();
					return;
				}
				if (snowballtime >= 0) {
					snowballtime--;
				}
			}
		}, 600);
		if (getItems().playerHasItem(11951, 1)) {
			getItems().deleteItem(11951, 1);
		} else {
			getItems().removeItem(playerEquipment[playerWeapon], playerWeapon);
			getItems().deleteItem(11951, 1);
		}
	}

	public void makesnow() {
		startAnimation(7528);
		gfx0(1284);
		getItems().addItem(11951, 28);
	}


}