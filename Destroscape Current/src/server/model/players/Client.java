package server.model.players;

import java.util.LinkedList;
import java.util.Queue;
//import server.model.players.Highscores;
import server.util.MadTurnipConnection;
import java.util.concurrent.Future;
import java.net.URL;
import java.net.URL;
import java.net.MalformedURLException;
import server.model.players.ConnectedFrom;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import org.apache.mina.common.IoSession;
import server.Config;
import server.Server;
import java.net.URL;
import server.model.npcs.*;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import server.model.items.ItemAssistant;
import server.model.shops.ShopAssistant;
import server.net.HostList;
import server.net.Packet;
import server.net.StaticPacketBuilder;
import server.util.Misc;
//import server.util.PlayersOnline;
import server.model.players.skills.Summoning;
import server.util.Stream;
//import server.util.MadTurnipConnection;
import server.model.players.skills.*;
import server.event.EventManager;
import server.event.Event; 
import server.model.players.PlayerSave;
import server.model.players.PlayerHandler;
import server.event.EventContainer;
import server.model.npcs.NPC;
import server.model.minigames.WarriorsGuild;
import server.model.minigames.Gambling;

public class Client extends Player {
	public byte buffer[] = null;
 public int cannonTimer = 0; 
 	public int s;
	public Stream inStream = null, outStream = null;
	private IoSession session;
	public static PlayerSave save;
	public static Client cliento2;
		public int totalstored;
	public int currentDamage = 0;
	public int followPlayer;
	public int npcslot;
	public int summoningnpcid;
		public int timer;
	private TradeLog tradeLog = new TradeLog(this);
	private ItemAssistant itemAssistant = new ItemAssistant(this);
	private ShopAssistant shopAssistant = new ShopAssistant(this);
	private TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private CombatAssistant combatAssistant = new CombatAssistant(this);
	private ActionHandler actionHandler = new ActionHandler(this);
	private PlayerKilling playerKilling = new PlayerKilling(this);
	private DialogueHandler dialogueHandler = new DialogueHandler(this);
	private Potions potion = new Potions(this);
	private Queue<Packet> queuedPackets = new LinkedList<Packet>();
	private WarriorsGuild warriorsGuild = new WarriorsGuild();
	private PotionMixing potionMixing = new PotionMixing(this);
	private Food food = new Food(this);
	private Gambling gamble = new Gambling(this);
	/**
	 * Skill instances
	 */
	private Slayer slayer = new Slayer(this);
	private Runecrafting runecrafting = new Runecrafting(this);
	private Woodcutting woodcutting = new Woodcutting(this);
	private Mining mine = new Mining(this);
	public Agility ag = new Agility(this);
	private Cooking cooking = new Cooking(this);
	private Fishing fish = new Fishing(this);
	private Crafting crafting = new Crafting(this);
	private Smithing smith = new Smithing(this);
	private Prayer prayer = new Prayer(this);
	private Curse curse = new Curse(this);
	private Fletching fletching = new Fletching(this);
	private SmithingInterface smithInt = new SmithingInterface(this);
	private Farming farming = new Farming(this);
	private Thieving thieving = new Thieving(this);
	private Firemaking firemaking = new Firemaking(this);
	private Herblore herblore = new Herblore(this);
	public Summoning Summoning = new Summoning(this);
	private int somejunk;
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;
		public int dungRest = 0;
	public int returnCode = 2; 
	public int clawDamage;
	public int clawIndex;
	public int clawType = 0;
	private Future<?> currentTask;
	public boolean WildernessWarning = false;
		public boolean kamfreenaDone;
	public boolean inCyclops;
	public boolean spawned;
	public boolean officialClient = true;
	public boolean basket = false;
	public boolean slayerHelmetEffect;
	public String lastKilled = "";
	
	public void resetRanks() {
		for (int i = 0; i < 10; i++) {
			ranks[i] = 0;
			rankPpl[i] = "";
		}
}
	public void walk(int EndX, int EndY, int Emote) {
     walkToEmote(Emote);
    getPA().walkTo2(EndX, EndY);
}
        public void walkToEmote(int id) {
                isRunning2 = false;
        playerWalkIndex = id;
                getPA().requestUpdates(); //this was needed to make the agility work
    }

    public void stopEmote() {
        playerWalkIndex = 0x333;
                agilityEmote = false;
                getPA().requestUpdates(); //this was needed to make the agility work
    }
        public void obsticle(int Emote, int Req, int newX, int newY, final int agilityTimer, int amtEXP, String message) {
                if (playerLevel[16] >= Req) {
                        agilityEmote = true;
                        walk(newX, newY, Emote);
                        sendMessage(message);
                        getPA().addSkillXP(amtEXP, playerAgility);
                        EventManager.getSingleton().addEvent(new Event() {
                                public void execute(EventContainer c) {
                                        stopEmote();
                                        c.stop();
                                }
                        }, agilityTimer);
                } else {
                sendMessage("You Need " + Req + " Agility To Do This Obsticle");
                }        
        }        

        public void agilityDelay(int Emote, final int X, final int Y, final int H, int Req, int amtEXP, String message) {
                if (playerLevel[16] >= Req) {
                        sendMessage(message);
                        startAnimation(Emote);
                        agilityEmote = true;
                        getPA().addSkillXP(amtEXP, playerAgility);
                        EventManager.getSingleton().addEvent(new Event() {
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
	public void highscores() {
getPA().sendFrame126("  Massacred World Top Skillers", 6399);
		for(int i = 0; i < 10; i++) {
			if(ranks[i] > 0) {
				getPA().sendFrame126("Rank "+(i+1)+": "+rankPpl[i]+ "- Total Level: " +ranks[i], 6402+i);
			}
		}
		getPA().showInterface(6308);
		flushOutStream();
		resetRanks();
	}
	public int playerRank = 0;
	public static int[] ranks = new int[11];
	public static int torvaBoost = 50;
    public static String[] rankPpl = new String[11];
			
		public void applyFollowing()
	{
		if (follow2 > 0)
		{
			//Client p = Server.playerHandler.client[followId];
			Client p = (Client) Server.playerHandler.players[follow2];     
			if (p != null)
			{
				if (isDead)
				{
					follow(0, 3, 1);
					return;
				}
				if (!goodDistance(p.absX, p.absY, absX, absY, 25))
				{
					follow(0, 3, 1);
					return;
				}
			}
			else if (p == null)
			{
				follow(0, 3, 1);
			}
		}
		else if (follow2 > 0)
		{
			//Server.npcHandler.npcs.NPC npc = Server.npcHandler.npcs[followId2];
			if (Server.npcHandler.npcs[followId2] != null)
			{
				if (Server.npcHandler.npcs[followId2].isDead)
				{
					follow(0, 3, 1);
					return;
				}
				if (!goodDistance(Server.npcHandler.npcs[followId2].absX, Server.npcHandler.npcs[followId2].absY, absX, absY, 25))
				{
					follow(0, 3, 1);
					return;
				}
			}
			else if (Server.npcHandler.npcs[followId2] == null)
			{
				follow(0, 3, 1);
			}
		}
	}
	
	int[] dpitems = { 2675, 2671, 2673, 2667, 2665, 2663, 2659, 2657, 2655, 4151, 4151 }; /* Add all of the item IDS here */

	public int dpitems() {
			return dpitems[(int) (Math.random() * dpitems.length)];
	}
	int count = 0;
	public void dropparty(final Client c, final int size, final int amount, final int time) {
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer b) {
				if(count == 30) { /* Change the amount of items to be dropped here, currently 30. */
					b.stop();
					sendMessage("The drop party is now over, hope everyone enjoyed!");
				} else {
					for (int j = 0; j < Server.playerHandler.players.length; j++) {
						if (Server.playerHandler.players[j] != null) {
							Client p1 = (Client)Server.playerHandler.players[j];
							int d1 = 0;
							d1 = Misc.random(8);
							if(d1 == 0) {
								Server.itemHandler.createGroundItem(p1, dpitems(), absX-Misc.random(5), absY-Misc.random

(5), 1, j);
								count += 1;
							} else 	if(d1 == 1) {
								Server.itemHandler.createGroundItem(p1, dpitems(), absX-Misc.random(5), absY-Misc.random

(5), 1, j);
								count += 1;
							} else 	if(d1 == 2) {
								Server.itemHandler.createGroundItem(p1, dpitems(), absX+Misc.random(5), absY+Misc.random

(5), 1, j);
								count += 1;
							} else 	if(d1 == 3) {
								Server.itemHandler.createGroundItem(p1, dpitems(), absX+Misc.random(5), absY+Misc.random

(5), 1, j);
								count += 1;
							} else 	if(d1 == 4) {
								Server.itemHandler.createGroundItem(p1, dpitems(), absX-Misc.random(5), absY+Misc.random

(5), 1, j);
								count += 1;
							} else 	if(d1 == 5) {
								Server.itemHandler.createGroundItem(p1, dpitems(), absX-Misc.random(5), absY+Misc.random

(5), 1, j);
								count += 1;
							} else 	if(d1 == 6) {
								Server.itemHandler.createGroundItem(p1, dpitems(), absX+Misc.random(5), absY-Misc.random

(5), 1, j);
								count += 1;
							} else 	if(d1 == 7) {
								Server.itemHandler.createGroundItem(p1, dpitems(), absX+Misc.random(5), absY-Misc.random

(5), 1, j);
								count += 1;
							}
						}
					}
				}
			}
		},2500); //2.5 sec per item
	};

	public int followDistance = 0;

		public void follow(int slot, int type, int distance)
	{
		if (slot > 0 && slot == follow2 && type == 1 && follow2 > 0 && followDistance != distance && (/*usingOtherRangeWeapons || */usingBow || usingMagic))
                    return;
		else if (slot > 0 && slot == followId2 && type == 0 && followId2 > 0 && followDistance >= distance && distance != 1)
                    return;
		//else if (type == 3 && followId2 == 0 && follow2 == 0)
                    //return;
		outStream.createFrame(174);
		if (freezeTimer > 0) {
			outStream.writeWord(0);
		} else {
			outStream.writeWord(slot);
			if (type == 0) {
				follow2 = 0;
				followId2 = slot;
				faceUpdate(followId2);
			} else if (type == 1) {
				followId2 = 0;
				follow2 = slot;
				faceUpdate(32768 + follow2);
			} else if (type == 3) {
				followId2 = 0;
				follow2 = 0;
				followDistance = 0;
				faceUpdate(65535);
			}
			followDistance = distance;
		}
		outStream.writeByte(type);
		outStream.writeWord(distance);
	}
		public Client getClient(String name) {
		name = name.toLowerCase();
		for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(validClient(i)) {
				Client client = getClient(i);
				if(client.playerName.toLowerCase().equalsIgnoreCase(name)) {
					return client;
				}
			}
		}
		return null;
	}
	public Client getClient(int id) {
		return (Client) Server.playerHandler.players[id];
	}
	public boolean validClient(int id) {
		if (id < 0 || id > Config.MAX_PLAYERS) {
			return false;
		}
		return validClient(getClient(id));
	}
	public boolean validClient(String name) {
		return validClient(getClient(name));
	}
	public boolean validClient(Client client) {
		return (client != null && !client.disconnected);
	}
	public boolean validNpc(int index) {
		if (index < 0 || index >= Config.MAX_NPCS) {
			return false;
		}
		NPC n = getNpc(index);
		if (n != null) {
			return true;
		}
		return false;
	}
	public NPC getNpc(int index) {
		return ((NPC) Server.npcHandler.npcs[index]);
	}
	public void yell(String s) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (validClient(i)) {
				getClient(i).sendMessage(s);
			}
		}
	}
	public Client(IoSession s, int _playerId) {
		super(_playerId);
		this.session = s;
		synchronized(this) {
			outStream = new Stream(new byte[Config.BUFFER_SIZE]);
			outStream.currentOffset = 0;
		}
		inStream = new Stream(new byte[Config.BUFFER_SIZE]);
		inStream.currentOffset = 0;
		buffer = new byte[Config.BUFFER_SIZE];
	}
	public void frame1() // cancels all player and npc emotes within area!
    {
        for (Player p : PlayerHandler.players) {
            if (p != null) {
                Client c = (Client) p;
                c.outStream.createFrame(1);
            }
        }
        updateRequired = true;
        appearanceUpdateRequired = true;
    }
		/*
		public Client getClient(String name) {
		name = name.toLowerCase();
		for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(validClient(i)) {
				Client client = getClient(i);
				if(client.playerName.toLowerCase().equalsIgnoreCase(name)) {
					return client;
				}
			}
		}
		return null;
	}
	public Client getClient(int id) {
		return (Client) Server.playerHandler.players[id];
	}
	public boolean validClient(int id) {
		if (id < 0 || id > Config.MAX_PLAYERS) {
			return false;
		}
		return validClient(getClient(id));
	}
	public boolean validClient(String name) {
		return validClient(getClient(name));
	}
	public boolean validClient(Client client) {
		return (client != null && !client.disconnected);
	}
	*/
	public void wildyWarning() {
	getPA().sendFrame126("WARNING!", 6940);
        //Edit the below if you want to change the text, and delete the slashes.
	getPA().sendFrame126("Proceed with caution. If you go much further north you will enter the\nwilderness. This is a very dangerous area where other players can attack you!", 6939);
	getPA().sendFrame126("The further north you go the more dangerous it becomes, but there is more\ntreasure to be found.", 6941);
	getPA().sendFrame126("In the wilderness an indicator at the bottom-right of the screen\nwill show the current level of danger.", 6942);
	getPA().showInterface(1908);
	}
	public void degradeVls() {
if(playerEquipment[playerWeapon] == 13901 && vlsLeft < 1){
playerEquipment[playerWeapon] = -1;
playerEquipmentN[playerWeapon] = 0;
getItems().wearItem(-1, 1, 3);
sendMessage("Your Vesta longsword crumbles to dust!");
vlsLeft = 1000;
}
}
public void degradeVSpear() {
if(playerEquipment[playerWeapon] == 13907 && vSpearLeft < 1){
playerEquipment[playerWeapon] = -1;
playerEquipmentN[playerWeapon] = 0;
getItems().wearItem(-1, 1, 3);
sendMessage("Your Vesta spear crumbles to dust!");
vSpearLeft = 1000;
}
}
public void degradeStat() {
if(playerEquipment[playerWeapon] == 13904 && statLeft < 1){
playerEquipment[playerWeapon] = -1;
playerEquipmentN[playerWeapon] = 0;
getItems().wearItem(-1, 1, 3);
sendMessage("Your Statius warhammer crumbles to dust!");
statLeft = 1000;
}
}
public void degradeVTop() {//vesta top
if(playerEquipment[playerChest] == 13889 && vTopLeft < 1){
playerEquipment[playerChest] = -1;
playerEquipmentN[playerChest] = 0;
getItems().wearItem(-1, 1, playerChest);
sendMessage("Your Vesta chainbody crumbles to dust!");
vTopLeft = 1000;
}
}
public void degradeVLegs() {//vesta legs
if(playerEquipment[playerLegs] == 13895 && vLegsLeft < 1){
playerEquipment[playerLegs] = -1;
playerEquipmentN[playerLegs] = 0;
getItems().wearItem(-1, 1, playerLegs);
sendMessage("Your Vesta plateskirt crumbles to dust!");
vLegsLeft = 1000;
}
}
public void degradeSTop() {//statius top
if(playerEquipment[playerChest] == 13886 && sTopLeft < 1){
playerEquipment[playerChest] = -1;
playerEquipmentN[playerChest] = 0;
getItems().wearItem(-1, 1, playerChest);
sendMessage("Your Statius platebody crumbles to dust!");
sTopLeft = 1000;
}
}
public void degradeSLegs() {//statius legs
if(playerEquipment[playerLegs] == 13892 && sLegsLeft < 1){
playerEquipment[playerLegs] = -1;
playerEquipmentN[playerLegs] = 0;
getItems().wearItem(-1, 1, playerLegs);
sendMessage("Your Statius platelegs crumbles to dust!");
sLegsLeft = 1000;
}
}
public void degradeSHelm() {//statius helm
if(playerEquipment[playerHat] == 13898 && sHelmLeft < 1){
playerEquipment[playerHat] = -1;
playerEquipmentN[playerHat] = 0;
getItems().wearItem(-1, 1, playerHat);
sendMessage("Your Statius full helm crumbles to dust!");
sHelmLeft = 1000;
}
}
public void degradeZHood() {//zuriel hood
if(playerEquipment[playerHat] == 13866 && zHoodLeft < 1){
playerEquipment[playerHat] = -1;
playerEquipmentN[playerHat] = 0;
getItems().wearItem(-1, 1, playerHat);
sendMessage("Your Zuriel hood crumbles to dust!");
zHoodLeft = 1000;
}
}
public void degradeZTop() {//zuriel top
if(playerEquipment[playerChest] == 13860 && zTopLeft < 1){
playerEquipment[playerChest] = -1;
playerEquipmentN[playerChest] = 0;
getItems().wearItem(-1, 1, playerChest);
sendMessage("Your Zuriel robe top crumbles to dust!");
zTopLeft = 1000;
}
}
public void degradeZBottom() {//zuriel hood
if(playerEquipment[playerLegs] == 13863 && zBottomLeft < 1){
playerEquipment[playerLegs] = -1;
playerEquipmentN[playerLegs] = 0;
getItems().wearItem(-1, 1, playerLegs);
sendMessage("Your Zuriel robe bottom crumbles to dust!");
zBottomLeft = 1000;
}
}
public void degradeZStaff() {//zuriel staff
if(playerEquipment[playerWeapon] == 13870 && zStaffLeft < 1){
playerEquipment[playerWeapon] = -1;
playerEquipmentN[playerWeapon] = 0;
getItems().wearItem(-1, 1, 3);
sendMessage("Your Zuriel staff crumbles to dust!");
zStaffLeft = 1000;
}
}
public void degradeMBody() {//morrigans body
if(playerEquipment[playerChest] == 13872 && mBodyLeft < 1){
playerEquipment[playerChest] = -1;
playerEquipmentN[playerChest] = 0;
getItems().wearItem(-1, 1, playerChest);
sendMessage("Your Morrigans leather body crumbles to dust!");
mBodyLeft = 1000;
}
}
public void degradeMChaps() {//morrigans chaps
if(playerEquipment[playerLegs] == 13875 && mChapsLeft < 1){
playerEquipment[playerLegs] = -1;
playerEquipmentN[playerLegs] = 0;
getItems().wearItem(-1, 1, playerLegs);
sendMessage("Your Morrigans chaps crumbles to dust!");
mChapsLeft = 1000;
}
}
	
	
	public int maxstore = 0;

	public void storesummon(int npcType) {
		switch (npcType) {

		case 6807:

			if (lastsummon > 0) {
				for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
					if (Server.npcHandler.npcs[i] != null) {
						if (Server.npcHandler.npcs[i].summon == true) {
							if (Server.npcHandler.npcs[i].spawnedBy == getId()
									&& Server.npcHandler.npcs[i].npcId == npcslot) {
								sendMessage("You are now storing items inside your npc");
								Summoning().store();
							}
						}
					}
				}

			}
			break;

		}
	}

	public void firstslot() {
		for (summoningslot = 0; occupied[summoningslot] == true; summoningslot += 1) {

		}
	}

	public int summoningslot = 0;

	public int storeditems[] = new int[29];

	public boolean picking = false;

	public int amount[] = new int[29];
	public boolean occupied[] = new boolean[29];

	public boolean storing = false;

	
	public int attackingplayer;
	public int lastsummon;
	public boolean summon;
	
	
		public void jadSpawn() {
			//getPA().movePlayer(absX, absY, playerId * 4);
			getDH().sendDialogues(41, 2618);
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
				}, 10000);
	}
		/*	public void handCannonDestory() {
		cannonTimer = 0;
		int chance = playerLevel[playerFiremaking] * 5 + 25;
		if(specGfx)
			chance/=2;
		if(Misc.random(chance) == 1)
			EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer c) {
			if(cannonTimer <= 0) {
				gfx0(2140);
    			playerEquipment[playerWeapon] = -1;
    			sendMessage("Your hand cannon explodes!");
    			int damage = Misc.random(15) + 1;
				setHitDiff(damage);
				setHitUpdateRequired(true);
    			dealDamage(Misc.random(15) + 1);
    			updateRequired = true;
				getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
    			getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
    			getItems().resetBonus();
				getItems().getBonus();
				getItems().writeBonus();
				getPA().requestUpdates();getOutStream().createFrame(34);
				getOutStream().writeWord(6);
				getOutStream().writeWord(1688);
				getOutStream().writeByte(playerWeapon);
				getOutStream().writeWord(0);
				getOutStream().writeByte(0);
				updateRequired = true; 
				setAppearanceUpdateRequired(true);
				c.stop();
				} else {
					cannonTimer--;
				}
			}
		}, 500);
	}*/

	public boolean specGfx = false;
	public void handCannonSpec() {
		cannonTimer = 0;
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer c) {
				cannonTimer--;
				if(cannonTimer == 0) {
					gfx0(2141);
					specGfx = true;
				}
				if(cannonTimer == 1) {
					if (playerIndex > 0)
						getCombat().fireProjectilePlayer();
					else if (npcIndex > 0)
						getCombat().fireProjectileNpc();	
					c.stop();
				}
			}
		}, 25);
	}
	
		public void clearQInterface() {
		for(int iD = 29172; iD <= 29264;iD++){
			getPA().sendFrame126("", iD);
		}
		getPA().sendFrame126(" @whi@Your Information:", 29161); //1st section title
			getPA().sendFrame126("@whi@Name: "+playerName+"", 29162); //1rd section content
		getPA().sendFrame126(" @whi@Combat: "+getCombatLevel()+"", 29163); //2nd section title
	}
 public int specRestore = 0;
 
public int getCombatLevel() {
        int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5);
		int ran = (int) ((getLevelForXP(playerXP[4])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));
			if (ran > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[4])) * 0.4875)
						+ ((getLevelForXP(playerXP[22])) * 0.121212));
			} else if (mag > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[6])) * 0.4875)
						+ ((getLevelForXP(playerXP[22])) * 0.121212));
			} else {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[0])) * 0.325) 
						+ ((getLevelForXP(playerXP[2])) * 0.325)
						+ ((getLevelForXP(playerXP[22])) * 0.121212));
			}
		return combatLevel;
	}
	
public void HighAndLow(){
	if (combatLevel < 15){
			int Low = 3;
			int High = combatLevel + 12;
				getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);

						}
	if (combatLevel > 15 && combatLevel < 114){
			int Low = combatLevel - 12;
			int High = combatLevel + 12;
				getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);

						}
	if (combatLevel > 114){
			int Low = combatLevel - 12;
			int High = 138;
				getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);

						}
						}



	public void flushOutStream() {	
		if(disconnected || outStream.currentOffset == 0) return;
		synchronized(this) {	
			StaticPacketBuilder out = new StaticPacketBuilder().setBare(true);
			byte[] temp = new byte[outStream.currentOffset]; 
			System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
			out.addBytes(temp);
			session.write(out.toPacket());
			outStream.currentOffset = 0;
		}
       }
	
	public void sendClan(String name, String message, String clan, int rights) {
		outStream.createFrameVarSizeWord(217);
		outStream.writeString(name);
		outStream.writeString(message);
		outStream.writeString(clan);
		outStream.writeWord(rights);
		outStream.endFrameVarSize();
	}
	
	public static final int PACKET_SIZES[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 12, //50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0            //250
	};

	public void destruct() {
		synchronized (this) {
               PlayerSave.saveGame(this);
                if(disconnected == true) { 
                   saveCharacter = true;
                }
		if(disconnected == true){
	     getTradeAndDuel().declineTrade();
 }
		if(session == null) 
			return;
		Server.panel.removeEntity(playerName);		
		PlayerSave.saveGame(this);
		if (clanId >= 0)
			Server.clanChat.leaveClan(playerId, clanId);
		getPA().removeFromCW();
		if (inPits) {
		Server.fightPits.removePlayerFromPits(playerId);
		}
		Misc.println("[Logged out]: "+playerName+"");
		RefreshAllSkills();
		PlayerSave.saveGame(this);
                saveCharacter = true;
		HostList.getHostList().remove(session);
		disconnected = true;
		session.close();
		session = null;
		inStream = null;
		outStream = null;
		isActive = false;
		buffer = null;
		super.destruct();
	}
}
	
		public void mymessage() {
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer c) {
				int r3 = 0;
				r3 = Misc.random(6);
				if (r3 == 0) {
					sendMessage("<shad=15695415>[Destroscape</col>]: Type ::vote and ::claim to get rewarded!!");
				} else if (r3 == 1) {           
					sendMessage("<shad=15695415>[Destroscape</col>]: Donations are appreciated thanks =)");		
				} else if (r3 == 2) { 
					sendMessage("<shad=15695415>[Destroscape</col>]: Register, type ::register!");
				} else if (r3 == 3) {
					sendMessage("<shad=15695415>[Destroscape</col>]: Feel free to ask any staff for help, as long as they're not busy!");
				} else if (r3 == 4) {
					sendMessage("<shad=15695415>[Destroscape</col>]: Please report all bugs to a staff.");
				} else if (r3 == 5) {
					sendMessage("<shad=15695415>[Destroscape</col>]: Dont forget to click save in your quest tab!");
				}
			}
		}, 160000); //milisecondsservermessage
	};
	
	public void sendMessage(String s) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrameVarSize(253);
				outStream.writeString(s);
				outStream.endFrameVarSize();
			}
		}
	}
	
	public void sendDelayedMessage(String s,int secsUntilDisplay) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrameVarSize(253);
				outStream.writeString(s);
				outStream.endFrameVarSize();
			}
		}
	}
	public String[] qCS = { "Attack", "Strength", "Defence", "Ranged", "Prayer", "Magic", "Runecrafting"
						 , "Hitpoint", "Agility", "Herblore", "Thieving", "Crafting", "Fletching", "Slayer"
						 , "Mining", "Smithing", "Fishing", "Cooking", "Firemaking", "Woodcutting", "Farming" 
					};
	
	public int[][] qCAB = { {33206, 0}, 
							{33209, 2}, 
							{33212, 1}, 
							{33215, 4}, 
							{33218, 5}, 
							{33221, 6}, 
							{33224, 20}, 
							{33207, 3}, 
							{33210, 16}, 
							{33213, 15}, 
							{33216, 17}, 
							{33219, 12}, 
							{33222, 9}, 
							{47130, 18}, 
							{33208, 14}, 
							{33211, 13}, 
							{33214, 10}, 
							{33217, 7}, 
							{33220, 11}, 
							{33223, 8}, 
							{54104, 19}
				 };
public String qC = "[Quick Chat] ";
	public void setSidebarInterface(int menuId, int form) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrame(71);
				outStream.writeWord(form);
				outStream.writeByteA(menuId);
			}
		}
	}	
public void CatchimpNpc(String npcName, int Net, int npcId, int itemId, int AmtExp, int Req, int playerId) {
npcName = Server.npcHandler.getNpcListName(npcId);
	if (System.currentTimeMillis() - foodDelay >= 1500) { //anti spamm
		if (playerLevel[21] >= Req) { //first we check if he's high enough to catch
			if (playerEquipment[playerWeapon] == 10010 || playerEquipment[playerWeapon] == 11259) { //player got net?
				if (playerLevel[21] + Misc.random(10) >= Misc.random(20) + Req) { //catch chance
				if (Misc.random(1000) == 1) {
				sendMessage("You caught a GIGANTIC Impling and gained triple Experience!"); //looks like player got a net
				getItems().addItem(722, 1); //itemid is different so its defined in the method
				startAnimation(6999); //this always stays 6999, no need to change this
				getPA().addSkillXP(AmtExp*3, 21); //AmtExp is different so its defined in the method
				} else {
				sendMessage("You Catched an Impling!"); //looks like player got a net
				getItems().addItem(itemId, 1); //itemid is different so its defined in the method
				startAnimation(6999); //this always stays 6999, no need to change this
				getPA().addSkillXP(AmtExp, 21); //AmtExp is different so its defined in the method
				}
				} else {
				sendMessage("You Failed To Catch The Impling");
				startAnimation(6999);
				}
			} else { //player got net?
			sendMessage("You need to wear a butterfly net!"); //looks like he doesn't
			return;
			}	
		} else {
		sendMessage("You need atleast "+ Req +" Hunter To catch that Impling!");
		return;
		}
		foodDelay = System.currentTimeMillis();// we use food timer but it really doesn't mather, this is just used for anti-spamm :)
	}
}			


public void CatchHunterNpc(String npcName, int Net, int npcId, int itemId, int AmtExp, int Req, int playerId) {
npcName = Server.npcHandler.getNpcListName(npcId);
	if (System.currentTimeMillis() - foodDelay >= 1500) { //anti spamm
		if (playerLevel[21] >= Req) { //first we check if he's high enough to catch
			if (playerEquipment[playerWeapon] == 10010 || playerEquipment[playerWeapon] == 11259) { //player got net?
				if (playerLevel[21] + Misc.random(10) >= Misc.random(20) + Req) { //catch chance
				if (Misc.random(1000) == 1) {
				sendMessage("You caught a GIGANTIC butterfly and gained triple Experience!"); //looks like player got a net
				getItems().addItem(722, 1); //itemid is different so its defined in the method
				startAnimation(6999); //this always stays 6999, no need to change this
				getPA().addSkillXP(AmtExp*3, 21); //AmtExp is different so its defined in the method
				} else {
				sendMessage("You Caught a Butterfly!"); //looks like player got a net
				getItems().addItem(itemId, 1); //itemid is different so its defined in the method
				startAnimation(6999); //this always stays 6999, no need to change this
				getPA().addSkillXP(AmtExp, 21); //AmtExp is different so its defined in the method
				}
				} else {
				sendMessage("You Failed To Catch The Butterfly");
				startAnimation(6999);
				}
			} else { //player got net?
			sendMessage("You need to wear a butterfly net!"); //looks like he doesn't
			return;
			}	
		} else {
		sendMessage("You need atleast "+ Req +" Hunter To catch that Butterfly!");
		return;
		}
		foodDelay = System.currentTimeMillis();// we use food timer but it really doesn't mather, this is just used for anti-spamm :)
	}
}
	public void initialize() {
	Server.panel.addEntity(playerName);	
	
	if (playerRights >= 0){
	MadTurnipConnection.addDonateItems(this,playerName);
	}
	if (playerRights == 0) {
	getDH().sendDialogues(1336, 2244);
	}
	if (playerRights >= 0) {
	getDH().sendDialogues(1336, 2244);
	}
	//PlayersOnline.createCon();
//PlayersOnline.offline(this);
//PlayersOnline.online(this);
//PlayersOnline.destroyCon();
		synchronized (this) {

sendMessage("Alert## Welcome to Destroscape ## Remember to Vote on website Destroscape.com!!");
    			outStream.createFrame(249);
			outStream.writeByteA(1);		// 1 for members, zero for free
			outStream.writeWordBigEndianA(playerId);
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (j == playerId)
					continue;
				if (Server.playerHandler.players[j] != null) {
					if (Server.playerHandler.players[j].playerName.equalsIgnoreCase(playerName))
						disconnected = true;
				}
			}
			for (int i = 0; i < 25; i++) {
				getPA().setSkillLevel(i, playerLevel[i], playerXP[i]);
				getPA().refreshSkill(i);
			}
			for(int p = 0; p < PRAYER.length; p++) { // reset prayer glows 
				prayerActive[p] = false;
				getPA().sendFrame36(PRAYER_GLOW[p], 0);	
			}
			for(int p = 0; p < CURSE.length; p++) { // reset prayer glows 
				curseActive[p] = false;
				getPA().sendFrame36(CURSE_GLOW[p], 0);	
			}
				getPA().sendCrashFrame();
			getPA().handleWeaponStyle();
			getPA().handleLoginText();
			accountFlagged = getPA().checkForFlags();
			//getPA().sendFrame36(43, fightMode-1);
				getPA().sendFrame36(505, 0);
				getPA().sendFrame36(506, 0);
				getPA().sendFrame36(507, 0);
				getPA().sendFrame36(508, 1);
				getPA().sendFrame36(166,4);
			getPA().sendFrame36(108, 0);//resets autocast button
			getPA().sendFrame36(172, 1);
			getPA().sendFrame36(287, 1);
			getPA().sendFrame107(); // reset screen
			getPA().setChatOptions(0, 0, 0); // reset private messaging options
			setSidebarInterface(1, 31110);//718 Skilltab - EPIC - by Lawless
			//setSidebarInterface(1, 7101);//Remove the // in front of this, and comment out the 718 Skilltab above to use old one
			setSidebarInterface(2, 638);
			setSidebarInterface(3, 3213);
			setSidebarInterface(4, 1644);
			setSidebarInterface(5, 5608);
			getPA().totallevelsupdate();
			if(playerMagicBook == 0) {
				setSidebarInterface(6, 1151); //modern
			}
			if(playerMagicBook == 1){
				setSidebarInterface(6, 12855); // ancient
			}
			if(playerMagicBook == 2){
				setSidebarInterface(6, 16640);
			}
			if(altarPrayed == 0) {
			setSidebarInterface(5, 5608);
				} else {
			setSidebarInterface(5, 22500);
			}
			correctCoordinates();
			setSidebarInterface(7, 18128);		
			setSidebarInterface(8, 5065);
			setSidebarInterface(9, 5715);
			setSidebarInterface(10, 2449);
			//setSidebarInterface(11, 4445); // wrench tab
			setSidebarInterface(11, 904); // wrench tab
			setSidebarInterface(12, 147); // run tab
setSidebarInterface(13, 962); //music tab 6299 for lowdetail. 962 for highdetail
setSidebarInterface(14, 29265); //Staff tab by Lawless
setSidebarInterface(15, 17000);//blank
setSidebarInterface(16, 17011); //summon
			setSidebarInterface(0, 2423);
				clearQInterface();
	if(lastsummon > 0) {
				Summoning().SummonNewNPC(lastsummon);
				}
/*if(totalstored > 0) {
			Server.itemHandler.createGroundItem(storeditems[int storeditems], getX(), getY(), 1, getId());
			storeditems[int storeditems] = -1;
			}*/
			if(xpLock == true) {
			sendMessage("Your XP is <col=255>locked</col>, to unlock this click the Lock-XP tab.");
			} else {
			sendMessage("Your XP is currently <col=255>unlocked</col>, to lock this click the Lock-XP tab.");
			}
			if(inWarriorG() && heightLevel == 2) {
			getPA().movePlayer(2846, 3540, 2);
			}
			MadTurnipConnection.addDonateItems(this,playerName);
			if (playerName.equalsIgnoreCase("james")){
			   getPA().loadAnnouncements();
			   getPA().showOption(4, 0,"Stalk", 4);
			   getPA().showOption(5, 0,"Rape", 3);
			   getPA().showOption(6, 0,"Rape", 3);
			}
			getPA().loadAnnouncements();
			getPA().showOption(4, 0,"Follow", 4);
			getPA().showOption(5, 0,"Trade With", 3);
			safeTimer = 0;
			getItems().resetItems(3214);
			getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
			getItems().resetBonus();
			getItems().getBonus();
			getPA().sendFrame126("Combat Level: "+getCombatLevel()+"", 3983);
			getItems().writeBonus();
			getItems().setEquipment(playerEquipment[playerHat],1,playerHat);
			getItems().setEquipment(playerEquipment[playerCape],1,playerCape);
			getItems().setEquipment(playerEquipment[playerAmulet],1,playerAmulet);
			getItems().setEquipment(playerEquipment[playerArrows],playerEquipmentN[playerArrows],playerArrows);
			getItems().setEquipment(playerEquipment[playerChest],1,playerChest);
			getItems().setEquipment(playerEquipment[playerShield],1,playerShield);
			getItems().setEquipment(playerEquipment[playerLegs],1,playerLegs);
			getItems().setEquipment(playerEquipment[playerHands],1,playerHands);
			getItems().setEquipment(playerEquipment[playerFeet],1,playerFeet);
			getItems().setEquipment(playerEquipment[playerRing],1,playerRing);
			getItems().setEquipment(playerEquipment[playerWeapon],playerEquipmentN[playerWeapon],playerWeapon);
			getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
			getPA().logIntoPM();
			getItems().addSpecialBar(playerEquipment[playerWeapon]);
			saveTimer = Config.SAVE_TIMER;
			saveCharacter = true;
			Misc.println("[Logged in]: "+playerName+"");
			int size = playerListSize;
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
			getPA().clearClanChat();
			if (addStarter)
				getPA().addStarter();
		    if (newPlayer == true)
			getPA().showInterface(3559);
			canChangeAppearance = true;
			if (autoRet == 1)
				getPA().sendFrame36(172, 1);
			else
				getPA().sendFrame36(172, 0);
		}
        if (acceptAid) {
        acceptAid = false;
        getPA().sendFrame36(503, 0);
        getPA().sendFrame36(427, 0);

        } else
        
        acceptAid = true;
        getPA().sendFrame36(503, 1);
        getPA().sendFrame36(427, 1);
    }
public void RefreshAllSkills() {
                  for (int i = 0; i < 25; i++) {
                   getPA().setSkillLevel(i, playerLevel[i], playerXP[i]);
                   getPA().refreshSkill(i);
                  }
                 }
	public void update() {
		synchronized (this) {
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
		}
	}
	
	public boolean wearingArmor() {
    if(playerEquipment[playerHat] > 0)
        return true;
    else if (playerEquipment[playerChest] > 0)
        return true;
    else if(playerEquipment[playerLegs] > 0)
        return true;
    else if(playerEquipment[playerFeet] > 0)
        return true;
    else if(playerEquipment[playerWeapon] > 0)
        return true;
    else if(playerEquipment[playerCape] > 0)
        return true;
    else if(playerEquipment[playerArrows] > 0)
        return true;
    else if(playerEquipment[playerAmulet] > 0)
        return true;
    else if(playerEquipment[playerHands] > 0)
        return true;
    else if(playerEquipment[playerShield] > 0)
        return true;
    else if(playerEquipment[playerRing] > 0)
        return true;
    else
        return false;
}

public void givereward() {
         if (getItems().playerHasItem(11256)) {
              getItems().deleteItem(11256, 1);
			  getItems().addItem(Dragonimploot1(), 40000);
        }
        else
        sendMessage("You need 2 free slots.");
        }
		public void givereward1() {
         if (getItems().playerHasItem(11238)) {
              getItems().deleteItem(11238, 1);
             getItems().addItem(Dragonimploot1(), 1856);
        }
        else
        sendMessage("You need 2 free slots.");
        }	
		public void givereward2() {
         if (getItems().playerHasItem(11240)) {
              getItems().deleteItem(11240, 1);
             getItems().addItem(Dragonimploot2(), 1856);
        }
        else
        sendMessage("You need 2 free slots.");
        }
		public void givereward3() {
         if (getItems().playerHasItem(11242)) {
              getItems().deleteItem(11242, 1);
             getItems().addItem(Dragonimploot3(), 1548);
        }
        else
        sendMessage("You need 2 free slots.");
        }
		public void givereward4() {
         if (getItems().playerHasItem(11244)) {
              getItems().deleteItem(11244, 1);
             getItems().addItem(Dragonimploot4(), 1731);
        }
        else
        sendMessage("You need 2 free slots.");
        }
		public void givereward5() {
         if (getItems().playerHasItem(11246)) {
              getItems().deleteItem(11246, 1);
             getItems().addItem(Dragonimploot5(), 1900);
        }
        else
        sendMessage("You need 2 free slots.");
        }
		public void givereward6() {
         if (getItems().playerHasItem(11248)) {
              getItems().deleteItem(11248, 1);
             getItems().addItem(Dragonimploot6(), 2500);
        }
        else
        sendMessage("You need 2 free slots.");
        }
		public void givereward7() {
         if (getItems().playerHasItem(11250)) {
              getItems().deleteItem(11250, 1);
             getItems().addItem(Dragonimploot7(), 6900);
        }
        else
        sendMessage("You need 2 free slots.");
        }
			public void givereward8() {
         if (getItems().playerHasItem(11252)) {
              getItems().deleteItem(11252, 1);
             getItems().addItem(Dragonimploot8(), 15000);
        }
        else
        sendMessage("You need 2 free slots.");
        }
			public void givereward9() {
         if (getItems().playerHasItem(11254)) {
              getItems().deleteItem(11254, 25000);
             getItems().addItem(Dragonimploot9(), 20000);
        }
        else
        sendMessage("You need 2 free slots.");
        }
	public static int Dragonimploot9[] = {995};
  public static int Dragonimploot9() {
    return Dragonimploot9[(int) (Math.random() * Dragonimploot9.length)];
}
		
		
		
		
			public static int Dragonimploot8[] = {995};
  public static int Dragonimploot8() {
    return Dragonimploot8[(int) (Math.random() * Dragonimploot8.length)];
}
		
		
			public static int Dragonimploot7[] = {995};
  public static int Dragonimploot7() {
    return Dragonimploot7[(int) (Math.random() * Dragonimploot7.length)];
}
		
		
		
		
			public static int Dragonimploot6[] = {995};
  public static int Dragonimploot6() {
    return Dragonimploot6[(int) (Math.random() * Dragonimploot6.length)];
}
		
		
		
		
			public static int Dragonimploot5[] = {995};
  public static int Dragonimploot5() {
    return Dragonimploot5[(int) (Math.random() * Dragonimploot5.length)];
}
		
		
		
		
		
		
		public static int Dragonimploot4[] = {995};
  public static int Dragonimploot4() {
    return Dragonimploot4[(int) (Math.random() * Dragonimploot4.length)];
}	
		
	public static int Dragonimploot3[] = {995};
  public static int Dragonimploot3() {
    return Dragonimploot3[(int) (Math.random() * Dragonimploot3.length)];
}	
		
		
	public static int Dragonimploot2[] = {995};
  public static int Dragonimploot2() {
    return Dragonimploot2[(int) (Math.random() * Dragonimploot2.length)];
}



	
	public static int Dragonimploot1[] = {995};
  public static int Dragonimploot1() {
    return Dragonimploot1[(int) (Math.random() * Dragonimploot1.length)];
}


	
	public void logout() {
	//highscores start/
	//ConnectedFrom.addConnectedFrom(this, connectedFrom);
	//Highscores.process();
//Highscores.save(this);
//Highscores end
		synchronized (this) {
		if(System.currentTimeMillis() - logoutDelay > 10000) {
				outStream.createFrame(109);
				properLogout = true;
				PlayerSave.saveGame(this);
			if (lastsummon > 0) {
				for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
					if (Server.npcHandler.npcs[i] != null) {
						if (Server.npcHandler.npcs[i].summon == true) {
							if (Server.npcHandler.npcs[i].spawnedBy == getId()) {
								Server.npcHandler.npcs[i].isDead = true;
								Server.npcHandler.npcs[i].applyDead = true;
								Server.npcHandler.npcs[i].summon = false;
							}
						}
					}
				}
			}
				saveCharacter = true;
			} else {
				sendMessage("You must wait a few seconds from being out of combat before you can do this.");
			}
		}
	}
	public void SaveGame() {
		synchronized (this) {
				PlayerSave.saveGame(this);
		}
	}
	public int packetSize = 0, packetType = -1;
	public long saveGameDelay;

	public void process() {
	if (inWild() == true && WildernessWarning == false) {
			resetWalkingQueue();
			wildyWarning();
			WildernessWarning = true;
	}
	if(dungRest > 0) {
			dungRest --;
		}
	getPA().sendFrame126(""+dungPoints+"", 18071);
	FetchDice();
	int totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[16]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));;
	for (int d = 0; d <= 10; d++) {
		if (totalz >= ranks[d]) {
			if (d == 0) {
			if (d == 0) {
				playerRank = d+1;
				ranks[d] = totalz;
				rankPpl[d] = playerName;
			}else if (d < 10){
				if (totalz < ranks[d-1]) {
					playerRank = d+1;
					ranks[d] = totalz;
					rankPpl[d] = playerName;
				}
			}else{
				if (totalz < ranks[d-1]) {
					playerRank = 0;
				}
			}
		}
	}
}
		
	
	if(inWild()) {
                        safeTimer = 10;
                }
                 if(safeTimer > 0 && !inWild()) {
                       safeTimer--;
                }
			getPA().sendFrame126("@whi@Killcount", 29164);
			getPA().sendFrame126(" @gre@"+KC+"", 663);
			getPA().sendFrame126("@whi@Deathcount", 29165);
			getPA().sendFrame126("@gre@"+DC+"", 29166);
			getPA().sendFrame126("@whi@Your Rank" , 29167);
			if (playerRights == 0)
			getPA().sendFrame126("@gre@Player", 29168);
			else if (playerRights == 1)
			getPA().sendFrame126("@gre@Bronze Donator", 29168);
			else if (playerRights == 2)
			getPA().sendFrame126("@gre@Iron Donator", 29168);
			else if (playerRights == 3)
			getPA().sendFrame126("@gre@Adamant Donator", 29168);
			else if (playerRights == 4)
			getPA().sendFrame126("@gre@Rune Donator", 29168);
			else if (playerRights == 5)
			getPA().sendFrame126("@gre@Dragon Donator", 29168);
			else if (playerRights == 6)
			getPA().sendFrame126("@gre@Dicer", 29168);
			else if (playerRights == 7)
			getPA().sendFrame126("@gre@Veteran", 29168);
			else if (playerRights == 8)
			getPA().sendFrame126("@gre@Helper", 29168);
			else if (playerRights == 9)
			getPA().sendFrame126("@gre@Test-Moderator", 29168);
			else if (playerRights == 10)
			getPA().sendFrame126("@gre@Moderator", 29168);
			else if (playerRights == 11)
			getPA().sendFrame126("@gre@Administrator", 29168);
			else if (playerRights == 12)
			getPA().sendFrame126("@gre@Co-Owner", 29168);
			else if (playerRights == 13)
			getPA().sendFrame126("@gre@Owner", 29168);
			else if (playerRights == 14)
			getPA().sendFrame126("@gre@Developer", 29168);
			getPA().sendFrame126("@whi@Loyalty level:", 29169);
			if (loyaltyTitle == 0)
			getPA().sendFrame126("@gre@None", 29170);
			else if (loyaltyTitle == 1)
			getPA().sendFrame126("@gre@Scout", 29170);
			else if (loyaltyTitle == 2)
			getPA().sendFrame126("@gre@Corporal", 29170);
			else if (loyaltyTitle == 3)
			getPA().sendFrame126("@gre@Kolonel", 29170);
			else if (loyaltyTitle == 4)
			getPA().sendFrame126("@gre@General", 29170);
			else if (loyaltyTitle == 5)
			getPA().sendFrame126("@gre@Skiller", 29170);
			else if (loyaltyTitle == 6)
			getPA().sendFrame126("@gre@Lord", 29170);
			else if (loyaltyTitle == 7)
			getPA().sendFrame126("@gre@Lady", 29170);
			else if (loyaltyTitle == 8)
			getPA().sendFrame126("@gre@King", 29170);
			else if (loyaltyTitle == 9)
			getPA().sendFrame126("@gre@Queen", 29170);
			else if (loyaltyTitle == 10)
			getPA().sendFrame126("@gre@God", 29170);
			else if (loyaltyTitle == 11)
			getPA().sendFrame126("@gre@Godess", 29170);
			else if (loyaltyTitle == 12)
			getPA().sendFrame126("@gre@The Fallen", 29170);
			else if (loyaltyTitle == 13)
			getPA().sendFrame126("@gre@Bronze Donator", 29170);
			else if (loyaltyTitle == 14)
			getPA().sendFrame126("@gre@Iron Donator", 29170);
			else if (loyaltyTitle == 15)
			getPA().sendFrame126("@gre@Adamant Donator", 29170);
			else if (loyaltyTitle == 16)
			getPA().sendFrame126("@gre@Rune Donator", 29170);
			else if (loyaltyTitle == 17)
			getPA().sendFrame126("@gre@Dragon Donator", 29170);
			else if (loyaltyTitle == 18)
			getPA().sendFrame126("@gre@Dicer", 29170);
			else if (loyaltyTitle == 19)
			getPA().sendFrame126("@gre@Veteran", 29170);
			else if (loyaltyTitle == 20)
			getPA().sendFrame126("@gre@Helper", 29170);
			else if (loyaltyTitle == 21)
			getPA().sendFrame126("@gre@Trial Moderator", 29170);
			else if (loyaltyTitle == 22)
			getPA().sendFrame126("@gre@Moderator", 29170);
			else if (loyaltyTitle == 23)
			getPA().sendFrame126("@gre@Administrator", 29170);
			else if (loyaltyTitle == 24)
			getPA().sendFrame126("@gre@Co Owner", 29170);
			else if (loyaltyTitle == 25)
			getPA().sendFrame126("@gre@Owner", 29170);
			else if (loyaltyTitle == 26)
			getPA().sendFrame126("@gre@Developer", 29170);
			getPA().sendFrame126("@whi@Quests:", 29171);
			if(nomad == 0) {
				getPA().sendFrame126("The Ritual", 29172); //1rd section content
			} else if(nomad > 0 && nomad < 10) {
				getPA().sendFrame126("@yel@The Ritual", 29172);	
			} else { 
				getPA().sendFrame126("@gre@The Ritual", 29172);
			}
					//Different tab - Points handler
		getPA().sendFrame126("Your Points", 29266); //Title of tab
		getPA().sendFrame126("@gre@Slayer Points", 29295);
		getPA().sendFrame126("@gre@Dung Points", 29296);
		getPA().sendFrame126("@gre@Donator Points", 29297);
		getPA().sendFrame126("@gre@Level Points", 29298);
		getPA().sendFrame126("@gre@Vote Points", 29299);
		getPA().sendFrame126("@gre@Pest Control Points", 29300);
		getPA().sendFrame126("@gre@PK Points", 29301);
		getPA().sendFrame126("@red@Donator Chest Points", 29302);
		getPA().sendFrame126("@red@-", 29303);
		getPA().sendFrame126("@red@-", 29304);
		getPA().sendFrame126("@red@-", 29305);
//End of custom tab by Lawless
		if (getItems().updateInventory)
			getItems().updateInventory();

			if (SpeDelay > 0) {
			startAnimation(3170);//if loading 602 (3170)
			dealDamage(10);
			handleHitMask(10);
			SpeDelay -= 1;
			getPA().refreshSkill(3);	
		}
		
		

		if(trade11 > 0) {
			trade11--;
		}
                if(vestaDelay > 0) {
                   vestaDelay--;
                }
		if(gwdelay > 0) {
			gwdelay--;
		}
		if(summonSpec > 0) {
			summonSpec--;
		}
		if(clawDelay > 0) {
			clawDelay--;
		}
		if(clawDelay == 1) {
		    delayedDamage = clawDamage/4;
		    delayedDamage2 = (clawDamage/4)+1;
			if(clawType == 2) {
				getCombat().applyNpcMeleeDamage(clawIndex, 1, clawDamage/4);
			}
			if(clawType == 1) {
				getCombat().applyPlayerMeleeDamage(clawIndex, 1, clawDamage/4);
			}
			if(clawType == 2) {
				getCombat().applyNpcMeleeDamage(clawIndex, 2, (clawDamage/4) + 1);
			}
			if(clawType == 1) {
				getCombat().applyPlayerMeleeDamage(clawIndex, 2, (clawDamage/4) + 1);
			}
			clawDelay = 0;
			specEffect = 0;
			previousDamage = 0;
			usingClaws = false;
			clawType = 0;
		}

		if (wcTimer > 0) {
			wcTimer--;
		} else if (wcTimer == 0 && woodcut[0] > 0) {
			getWoodcutting().cutWood();
		} else if (miningTimer > 0 && mining[0] > 0) {
			miningTimer--;
		} else if (miningTimer == 0 && mining[0] > 0) {
			getMining().mineOre();
		} else  if (smeltTimer > 0 && smeltType > 0) {
			smeltTimer--;
		} else if (smeltTimer == 0 && smeltType > 0) {
			getSmithing().smelt(smeltType);
			getSmithing().smelt(smeltType);
		}


		if(System.currentTimeMillis() - saveGameDelay > Config.SAVE_TIMER && !disconnected) {
			saveCharacter = true; 
			saveGameDelay = System.currentTimeMillis();
		}
	
		
		if (System.currentTimeMillis() - lastPoison > 20000 && poisonDamage > 0) {
			int damage = poisonDamage/2;
			if (damage > 0) {
				if (!getHitUpdateRequired()) {
					setHitUpdateRequired(true);
					setHitDiff(damage);
					updateRequired = true;
					poisonMask = 1;
				} else if (!getHitUpdateRequired2()) {
					setHitUpdateRequired2(true);
					setHitDiff2(damage);
					updateRequired = true;
					poisonMask = 2;
				}
				lastPoison = System.currentTimeMillis();
				poisonDamage--;
				dealDamage(damage);
			} else {
				poisonDamage = -1;
				sendMessage("You are no longer poisoned.");
			}	
		}

		
		
		if(System.currentTimeMillis() - duelDelay > 800 && duelCount > 0) {
			if(duelCount != 1) {
				forcedChat(""+(--duelCount));
				duelDelay = System.currentTimeMillis();
			} else {
				damageTaken = new int[Config.MAX_PLAYERS];
				forcedChat("FIGHT!");
				duelCount = 0;
			}
		}
	
		if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT_WITH_RING && playerEquipment[playerRing] == 19669) {
			specDelay = System.currentTimeMillis();
				if(specAmount < 10) {
					specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		} else if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			specDelay = System.currentTimeMillis();
				if(specAmount < 10) {
					specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}
		
		if(clickObjectType > 0 && goodDistance(objectX + objectXOffset, objectY + objectYOffset, getX(), getY(), objectDistance)) {
			if(clickObjectType == 1) {
				getActions().firstClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 2) {
				getActions().secondClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 3) {
				getActions().thirdClickObject(objectId, objectX, objectY);
			}
		}
		
		if((clickNpcType > 0) && Server.npcHandler.npcs[npcClickIndex] != null) {			
			if(goodDistance(getX(), getY(), Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY(), 1)) {
				if(clickNpcType == 1) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().firstClickNpc(npcType);
				}
				if(clickNpcType == 2) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().secondClickNpc(npcType);
				}
				if(clickNpcType == 3) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().thirdClickNpc(npcType);
				}
			}
		}
		
		if(walkingToItem) {
			if(getX() == pItemX && getY() == pItemY || goodDistance(getX(), getY(), pItemX, pItemY,1)) {
				walkingToItem = false;
				Server.itemHandler.removeGroundItem(this, pItemId, pItemX, pItemY, true);
			}
		}
		
		if(followId > 0) {
			getPA().followPlayer(playerIndex);
		} else if (followId2 > 0) {
			getPA().followNpc();
		}
		getFishing().FishingProcess();
		getCombat().handlePrayerDrain();
		
		if(System.currentTimeMillis() - singleCombatDelay >  3300) {
			underAttackBy = 0;
		}
		if (System.currentTimeMillis() - singleCombatDelay2 > 3300) {
			underAttackBy2 = 0;
		}
		
		if (System.currentTimeMillis() - restoreStatsDelay >  60000) {
			restoreStatsDelay = System.currentTimeMillis();
			if (playerLevel[3] < 1)
				return;
			for (int level = 0; level < playerLevel.length; level++)  {
				if (playerLevel[level] < getLevelForXP(playerXP[level])) {
					if(level != 5 && level != 23) { // prayer doesn't restore
						playerLevel[level] += 1;
						getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
						getPA().refreshSkill(level);
					}
				} else if (playerLevel[level] > getLevelForXP(playerXP[level])) {
					if (level == 0 || level == 1 || level == 2 || level == 4 || level == 6) {
						if (hasOverloadBoost)
							continue;
					}
					playerLevel[level] -= 1;
					getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
					getPA().refreshSkill(level);
				}
			}
		}

		if(System.currentTimeMillis() - teleGrabDelay >  1550 && usingMagic) {
			usingMagic = false;
			if(Server.itemHandler.itemExists(teleGrabItem, teleGrabX, teleGrabY)) {
				Server.itemHandler.removeGroundItem(this, teleGrabItem, teleGrabX, teleGrabY, true);
			}
		}
		if(inWild() && !isInPbox() && !isInArd() && !isInFala() && !inFunPk()) {
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = (((modY - 3520) / 8) + 1);
			EarningPotential.checkPotential(this);
			getPA().walkableInterface(197);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
				} else {
					getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
			}
			getPA().showOption(3, 0, "Attack", 1);
			} else if (!inWild() && !inDuelArena() && safeTimer <= 0 && !inGWD() && !inPcBoat() && !inPcGame()){ //this makes it so attack option is visible on wild and challenge in duel =)
		getPA().showOption(3, 0, "View shop", 1);
getPA().walkableInterface(-1);
		} else if (!inWild() && safeTimer > 0){
			getPA().walkableInterface(197);
			wildLevel = (60);
			getPA().showOption(3, 0, "Attack", 1);
			getPA().sendFrame126("@or1@"+safeTimer, 199);
} else if(inPcBoat()) {
    getPA().walkableInterface(21005);
} else if(inFunPk()) {
                        getPA().walkableInterface(197);
                        getPA().sendFrame126("@yel@FunPk", 199);
                        getPA().showOption(3, 0, "Attack", 1);
                        wildLevel = 55;	
} else if(inPcGame()) {
    getPA().walkableInterface(21100);
		} else if (inDuelArena()) {
			getPA().walkableInterface(201);
			if(duelStatus == 5) {
				getPA().showOption(3, 0, "Attack", 1);
			} else {
				getPA().showOption(3, 0, "Challenge", 1);
			}
} else if (inFunPk()) {
			getPA().walkableInterface(197);
			getPA().sendFrame126("@yel@FunPk", 199);
			getPA().showOption(3, 0, "Attack", 1);
		} else if(inBarrows()){
			getPA().sendFrame99(2);
			getPA().sendFrame126("Kill Count: "+barrowsKillCount, 4536);
			getPA().walkableInterface(4535);

		} else if (InDung()){
				getPA().sendFrame126("@gre@Dungeoneering", 199);
				
		} else if(inGWD()){
		        getPA().GWKC();


		} else if(safeZone()){
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					getPA().sendFrame126("@gre@SafeZone", 199);
				} else {
					getPA().sendFrame126("@gre@SafeZone", 199);
				}
			} else {
				getPA().multiWay(-1);
					getPA().sendFrame126("@gre@SafeZone", 199);
						}
		

		} else if(isInFala()){
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = 12;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
				HighAndLow();
					} else {
					HighAndLow();
				}
				}
		} else if(isInPbox()){
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = 12;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
				HighAndLow();
					} else {
					HighAndLow();
				}
				}
		} else if(isInArd()){
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = 12;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
				HighAndLow();
					} else {
					HighAndLow();
				}
			} else {
				getPA().multiWay(-1);
			HighAndLow();}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inCwGame || inPits) {
			getPA().showOption(3, 0, "Attack", 1);		
		} else if (getPA().inPitsWait()) {
			getPA().showOption(3, 0, "Loading...", 1);
		}else if (!inCwWait) {
			getPA().sendFrame99(0);
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Loading...", 1);
		}
		
		if(!hasMultiSign && inMulti()) {
			hasMultiSign = true;
			getPA().multiWay(1);
		}
		
		if(hasMultiSign && !inMulti()) {
			hasMultiSign = false;
			getPA().multiWay(-1);
		}

		if(skullTimer > 0) {
			skullTimer--;
			if(skullTimer == 1) {
				isSkulled = false;
				attackedPlayers.clear();
				headIconPk = -1;
				skullTimer = -1;
				getPA().requestUpdates();
			}	
		}
		
		if(isDead && respawnTimer == -6) {
			getPA().applyDead();
		}
		if(specRestore > 0) {
                        specRestore --;
                }

		
		if(respawnTimer == 7) {
			respawnTimer = -6;
			getPA().giveLife();
		} else if(respawnTimer == 12) {
			respawnTimer--;
			startAnimation(836);
			poisonDamage = -1;
		}	
		
		if(respawnTimer > -6) {
			respawnTimer--;
		}
		if(freezeTimer > -6) {
			freezeTimer--;
			if (frozenBy > 0) {
				if (Server.playerHandler.players[frozenBy] == null) {
					freezeTimer = -1;
					frozenBy = -1;
				} else if (!goodDistance(absX, absY, Server.playerHandler.players[frozenBy].absX, Server.playerHandler.players[frozenBy].absY, 20)) {
					freezeTimer = -1;
					frozenBy = -1;
				}
			}
		}
		
		if(hitDelay > 0) {
			hitDelay--;
		}
		
		if(teleTimer > 0) {
			teleTimer--;
			if (!isDead) {
				if(teleTimer == 1 && newLocation > 0) {
					teleTimer = 0;
					getPA().changeLocation();
				}
				if(teleTimer == 5) {
					teleTimer--;
					getPA().processTeleport();
				}
				if(teleTimer == 9 && teleGfx > 0) {
					teleTimer--;
					gfx100(teleGfx);
				}
			} else {
				teleTimer = 0;
			}
		}	

		if(hitDelay == 1) {
			if(oldNpcIndex > 0) {
				getCombat().delayedHit(oldNpcIndex);
			}
			if(oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(oldPlayerIndex);				
			}		
		}
		
		if(attackTimer > 0) {
			attackTimer--;
		}
		
		if(attackTimer == 1){
			if(npcIndex > 0 && clickNpcType == 0) {
				getCombat().attackNpc(npcIndex);
			}
			if(playerIndex > 0) {
				getCombat().attackPlayer(playerIndex);
			}
		} else if (attackTimer <= 0 && (npcIndex > 0 || playerIndex > 0)) {
			if (npcIndex > 0) {
				attackTimer = 0;
				getCombat().attackNpc(npcIndex);
			} else if (playerIndex > 0) {
				attackTimer = 0;
				getCombat().attackPlayer(playerIndex);
			}
		}
		
		if(timeOutCounter > Config.TIMEOUT) {
			disconnected = true;
		}
		
		timeOutCounter++;
		
		if(inTrade && tradeResetNeeded){
			Client o = (Client) Server.playerHandler.players[tradeWith];
			if(o != null){
				if(o.tradeResetNeeded){
					getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
	}
	
	public void setCurrentTask(Future<?> task) {
		currentTask = task;
	}

	public Future<?> getCurrentTask() {
		return currentTask;
	}
	
			@SuppressWarnings("null")
	public void WalkTo(int x, int y) {
		newWalkCmdSteps = (Math.abs((x+y)));
		if (newWalkCmdSteps % 1 != 0) newWalkCmdSteps /= 1;
		if (++newWalkCmdSteps > walkingQueueSize) {
			println("Warning: WalkTo command contains too many steps (" + newWalkCmdSteps + ").");
			newWalkCmdSteps = 0;
		}
		int firstStepX = absX;
		firstStepX -= mapRegionX*8;

		for (int i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
		}
		newWalkCmdX[0] = newWalkCmdY[0];
		int firstStepY = absY;
		firstStepY -= mapRegionY*8;
		newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1));
		for (int q = 0; q < newWalkCmdSteps; q++) {
			newWalkCmdX[q] += firstStepX;
			newWalkCmdY[q] += firstStepY;
		}
	}
	public void FetchDice()
	{
		int rnd;
		String Message = "";
		if (cDice == 0 || (System.currentTimeMillis() - diceDelay <= 1000)) {
			return;
		}
		switch (cDice) {
		//Dice
			case 15096: rnd = Misc.random(19)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a twenty-sided die."); break;
			case 15094: rnd = Misc.random(11)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a twelve-sided die."); break;
			case 15092: rnd = Misc.random(9)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a ten-sided die."); break;
			case 15090: rnd = Misc.random(7)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on an eight-sided die."); break;
			case 15100: rnd = Misc.random(3)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a four-sided die."); break;
			case 15086: rnd = Misc.random(5)+1;	Message = ("rolled <col=16711680>"+ rnd +"</col> on a six-sided die."); break;
			case 15088: rnd = Misc.random(11)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on two six-sided dice."); break;
			case 15098: rnd = Misc.random(99)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on the percentile dice."); break;
		}
		sendMessage("You " + Message);
			if (clanDice){
				if (clanId >= 0) {
					Server.clanChat.messageToClan("Clan Chat channel-mate <col=16711680>"+playerName+"</col> "+Message, clanId);
				}
			}
		cDice = 0;
	}

	public void useDice(int itemId, boolean clan){
			if (System.currentTimeMillis() - diceDelay >= 3000) {
				sendMessage("Rolling...");
				startAnimation(11900);
				diceDelay = System.currentTimeMillis();
				cDice = itemId;
				clanDice = clan;
			switch (itemId) {
				//Gfx's
				case 15086: gfx0(2072); break;
				case 15088: gfx0(2074); break;
				case 15090: gfx0(2071); break;
				case 15092: gfx0(2070); break;
				case 15094: gfx0(2073); break;
				case 15096: gfx0(2068); break;
				case 15098: gfx0(2075); break;
				case 15100: gfx0(2069); break;
			}
		}

	}
public void fmwalkto(int i, int j)
    {
        newWalkCmdSteps = 0;
        if(++newWalkCmdSteps > 50)
            newWalkCmdSteps = 0;
        int k = absX + i;
        k -= mapRegionX * 8;
        newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
        int l = absY + j;
        l -= mapRegionY * 8;
	isRunning2 = false;
	isRunning = false;
        //for(this.i = 0; this.i < newWalkCmdSteps; this.i++)
       //{
            newWalkCmdX[0] += k;
            newWalkCmdY[0] += l;
        //}
	//lastWalk = System.currentTimeMillis();
	//walkDelay = 1;
        poimiY = l;
        poimiX = k;
    }
	public int tmpNWCY[] = new int[walkingQueueSize];
	public int tmpNWCX[] = new int[walkingQueueSize];
	public synchronized Stream getInStream() {
		return inStream;
	}
	
	public synchronized int getPacketType() {
		return packetType;
	}
	
	public synchronized int getPacketSize() {
		return packetSize;
	}
	
	public synchronized Stream getOutStream() {
		return outStream;
	}
	
	public ItemAssistant getItems() {
		return itemAssistant;
	}
		
	public PlayerAssistant getPA() {
		return playerAssistant;
	}
	
	public DialogueHandler getDH() {
		return dialogueHandler;
	}
	
	public TradeLog getTradeLog() {
		return tradeLog;
	}

	public WarriorsGuild getWarriorsGuild() {
		return warriorsGuild;
	}

	public ShopAssistant getShops() {
		return shopAssistant;
	}

	public Crafting getCrafting() {
		return crafting;
	}
	
	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}
	
	public CombatAssistant getCombat() {
		return combatAssistant;
	}
	
	public ActionHandler getActions() {
		return actionHandler;
	}
  
	public PlayerKilling getKill() {
		return playerKilling;
	}
	
	public IoSession getSession() {
		return session;
	}
	
	public Potions getPotions() {
		return potion;
	}
	
	public PotionMixing getPotMixing() {
		return potionMixing;
	}
	
	public Food getFood() {
		return food;
	}
	
	/**
	 * Skill Constructors
	 */
	public Slayer getSlayer() {
		return slayer;
	}
	
	public Runecrafting getRunecrafting() {
		return runecrafting;
	}
	
	public Woodcutting getWoodcutting() {
		return woodcutting;
	}
		public Summoning Summoning() {
		return Summoning;
	}
	public Mining getMining() {
		return mine;
	}
	
	public Cooking getCooking() {
		return cooking;
	}

	public Gambling getGamble() {
		return gamble;	
	}	
	
	public Agility getAgil() {
		return ag;
	}
	
	public Fishing getFishing() {
		return fish;
	}
	
	public Smithing getSmithing() {
		return smith;
	}
	
	public Farming getFarming() {
		return farming;
	}
	
	public Thieving getThieving() {
		return thieving;
	}
	
	public Herblore getHerblore() {
		return herblore;
	}
	
	public Firemaking getFiremaking() {
		return firemaking;
	}
	
	public SmithingInterface getSmithingInt() {
		return smithInt;
	}
	public Prayer getPrayer() { 
		return prayer;
	}

	public Curse getCurse() { 
		return curse;
	}

	public Fletching getFletching() { 
		return fletching;
	}

/**
* Gets the prospecting class.
* @return The prospecting class.
*/
public Prospecting getProspecting() {
		return prospecting;
	}
	
	
	/**
	 * End of Skill Constructors
	 */

	 /**
	 * Second skill instances.
	 */
	private Prospecting prospecting = new Prospecting();
	
	public void queueMessage(Packet arg1) {
		synchronized(queuedPackets) {
			//if (arg1.getId() != 41)
				queuedPackets.add(arg1);
			//else
				//processPacket(arg1);
		}
	}
	
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
			processPackets++;
		}
		timeOutCounter = 0;
		if(processPackets > Config.MAX_PROCESS_PACKETS) {
			return false;
		}
		return true;
		}
	
	public synchronized boolean processPacket(Packet p) {
		synchronized (this) {
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
	}


	
	public void correctCoordinates() {
		if (inPcGame()) {
			getPA().movePlayer(2657, 2639, 0);
		}
		if (inFightCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
				}, 10000);
		
		}
		
		if (inRFD()) {
			getPA().movePlayer(1899,5363, playerId * 4+2);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.rfd.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
				}, 10000);
		
		}
	
	}
	
}
