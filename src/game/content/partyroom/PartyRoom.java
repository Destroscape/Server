package game.content.partyroom;

import java.util.ArrayList;

import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import engine.world.Tiles;
import game.Config;
import game.Server;
import game.entity.player.Player;
import game.entity.player.PlayerSave;
import game.item.GroundItem;
import game.item.Item;

public class PartyRoom {

	/**
	 * PartyCoordinates for set rectangle lowX, lowY, highX, highY
	 */
	public static final int[] PARTY_AREA = { 3036, 3371, 3055, 3385 };

	/**
	 * Party Balloon ID's and Their popped ID's
	 * 
	 * {normal_balloon_id, popping_balloon_id}
	 */
	public final int[][] BALLOON_IDS = { { 115, 123 }, { 116, 124 },
			{ 117, 125 }, { 118, 126 }, { 119, 127 }, { 120, 128 },
			{ 121, 129 }, { 122, 130 } };

	/**
	 * The PartyChest ID
	 */
	public final int CHEST = 26193;

	/**
	 * The PartyLever ID
	 */
	public final int LEVER = 26194;

	/**
	 * Last send Announcement
	 */
	private long lastAnnouncment;

	/**
	 * How long till nest Announcement in minutes
	 */
	private int announcmentFrequency = 5; // announcment frequency in minutes

	/**
	 * Last deposite that was made in Milliseconds
	 */
	private long lastDeposite;

	/**
	 * Items that are in PartyChest(Stacked)
	 */
	private static int[] roomItems = new int[50];
	/**
	 * Item Container Amount
	 */
	private static int[] roomItemsN = new int[50];

	/**
	 * List of all Valid Balloons
	 * 
	 * @List<Balloon>
	 */
	private ArrayList<Balloon> balloons = new ArrayList<Balloon>();

	/**
	 * List of all people who were in partyRoom when lever was pulled
	 * 
	 * @List<String>
	 */
	private ArrayList<String> contestants = new ArrayList<String>();


	/**
	 * List of all people who were in partyRoom when lever was pulled
	 * 
	 * @List<String>
	 */
	private ArrayList<Tiles> tiles = new ArrayList<Tiles>();

	/**
	 * Adds a Balloon to the list
	 * 
	 * @ArrayList<Balloon>
	 */
	public void add(Balloon balloon) {
		balloons.add(balloon);
	}

	/**
	 * Begins to drop all balloons in partyRoom with a random delay if
	 * <Boolean>DELAY_DROPS</Boolean> is TRUE
	 */
	public void appendDrops() {
		System.out.println("Starting drops....");
		loadTileList();
		int amount = getAmount();
		if (amount < 1) {
			return;
		}
		for (int x = 0; x < roomItems.length; x++) {
			if (roomItemsN[x] > 0) {
				add(getNewBalloon(roomItems[x], roomItemsN[x]));
			}
			roomItems[x] = 0;
			roomItemsN[x] = 0;
		}
		for (int x = 0; x < amount * 2; x++) {
			add(getEmptyBalloon());
		}
		dropBalloons();
	}

	/**
	 * Clear all Balloons in list and drops the left overs
	 */
	public void clearAllBalloons() {
		for (Balloon b : getBalloons()) {
			if (b == null)
				continue;
			b.destruct();
			if (!b.isEmpty()) {
				GroundItem item = new GroundItem(b.getItem(), b.getX(),
						b.getY(), b.getAmount(), 0, 100, "");
				Server.itemHandler.addItem(item);
			}
		}
		balloons.clear();
	}

	/**
	 * Clear Contestants from list
	 */
	public void clearContestants() {
		contestants.clear();
	}

	/**
	 * Deposit PartyItems into the chest
	 * 
	 * @param c
	 */
	public void deposit(Player c) {

		for (int x = 0; x < c.party.length; x++) {
			if (c.partyN[x] > 0) {
				if (Item.itemStackable[c.party[x]]) {
					int slot = inArray(roomItems, c.party[x]);
					if (slot < 0) {
						c.sendMessage("Theres not enough space left in the chest.");
						break;
					}
					if (roomItems[slot] != c.party[x]) {
						roomItems[slot] = c.party[x];
						roomItemsN[slot] = c.partyN[x];
					} else {
						roomItemsN[slot] += c.partyN[x];
					}
					c.party[x] = -1;
					c.partyN[x] = 0;
				} else {
					int left = c.partyN[x];
					for (int y = 0; y < left; y++) {
						int slot = inArray(roomItems, -2);
						if (slot < 0) {
							c.sendMessage("Theres not enough space left in the chest.");
							break;
						}
						roomItems[slot] = c.party[x];
						roomItemsN[slot] = 1;
						c.partyN[x]--;
					}
					if (c.partyN[x] <= 0)
						c.party[x] = -1;
				}
			}
		}
		// Prevents X-log dupe
		c.saveCharacter = true;
		c.saveFile = true;
		PlayerSave.saveGame(c);
		updateDeposit(c);
		updateGlobal(c);
	}

	/**
	 * Drops All Balloons in list
	 */
	public void dropBalloons() {
		for (Balloon b : getBalloons()) {
			b.drop(1000 + Misc.random(1500));
		}
		System.out.println("There were " + getFilledBalloons()
				+ " Filled balloons and  " + getEmptyBalloons()
				+ " Empty balloons added.");
	}

	/**
	 * Get's the amount of Stacked Items in the partyBox
	 * 
	 * @return<Integer>
	 */
	public int getAmount() {
		int amount = 0;
		for (int i = 0; i < roomItems.length; i++) {
			if (roomItems[i] > 0) {
				amount++;
			}
		}
		return amount;
	}

	/**
	 * Get balloon at set tile
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Balloon getBalloon(int x, int y) {
		for (Balloon b : getBalloons()) {
			if (b.getX() == x && b.getY() == y)
				return b;
		}
		return null;
	}

	/**
	 * Get all balloons in list
	 * 
	 * @return
	 */
	public ArrayList<Balloon> getBalloons() {
		return balloons;
	}

	/**
	 * Get Empty Balloon
	 * 
	 * @return
	 */
	public Balloon getEmptyBalloon() {
		Tiles tile = getNextTile();
		int index = Misc.random(BALLOON_IDS.length - 1);
		int x = tile.getX();
		int y = tile.getY();
		return new Balloon(BALLOON_IDS[index][0],
				BALLOON_IDS[index][1], x, y, -1, 0);
	}

	public int getEmptyBalloons() {
		int i = 0;
		for (Balloon b : getBalloons())
			if (b.isEmpty())
				i++;
		return i;
	}

	public int getFilledBalloons() {
		int i = 0;
		for (Balloon b : getBalloons())
			if (!b.isEmpty())
				i++;
		return i;
	}

	/**
	 * Get new balloon with item inside
	 * 
	 * @param item
	 * @param amount
	 * @return
	 */
	public Balloon getNewBalloon(int item, int amount) {
		Tiles tile = getNextTile();
		int index = Misc.random(BALLOON_IDS.length - 1);
		int x = tile.getX();
		int y = tile.getY();
		return new Balloon(BALLOON_IDS[index][0], BALLOON_IDS[index][1], x, y,
				item, amount);
	}

	/**
	 * Get next Tile for Balloon
	 * 
	 * @return tile
	 */
	public Tiles getNextTile() {
		Tiles tile = null;
		if (tiles.size() > 0) {
			tile = tiles.get(Misc.random(tiles.size()-1));
			tiles.remove(tile);
		}
		return tile;
	}

	public void loadTileList() {
		tiles.clear();
		for (int i = 0; i < PARTY_TILES.length; i++) {
			tiles.add(new Tiles(PARTY_TILES[i][0], PARTY_TILES[i][1]));
		}
	}

	/**
	 * Get time in Milliseconds till next announcement
	 * 
	 * @return time
	 */
	public int getTime() {
		return 60000 * announcmentFrequency;
	}

	/**
	 * Is variable in array
	 * 
	 * @param array
	 * @param target
	 * @return
	 */
	private int inArray(int[] array, int target) {
		int spare = -1;
		for (int x = 0; x < array.length; x++) {
			if (array[x] == target) {
				return x;
			} else if (spare == -1 && array[x] <= 0) {
				spare = x;
			}
		}
		return spare;
	}
	
	/**
	 * Checks to see if player is a valid contestant
	 * 
	 * @param name
	 * @return
	 */
	private boolean isContestant(String name) {
		for (String s : contestants) {
			if (name.equals(s))
				return true;
		}
		return false;
	}

	/**
	 * Sends ItemOnInterface packet to client
	 * 
	 * @param c
	 * @param frame
	 * @param slot
	 * @param id
	 * @param amount
	 */
	public static void itemOnInterface(Player c, int frame, int slot, int id,
			int amount) {
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(id + 1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}

	/**
	 * Offer Item into Party List
	 * 
	 * @param c
	 * @param id
	 * @param amount
	 */
	public void offerItem(Player c, int id, int amount) {
		int slot = inArray(c.party, id);
		for (int i : Config.ITEM_TRADEABLE) {
			if (i == id) {
				c.sendMessage("You can't deposit this item.");
				return;
			}
			if (id == 995) {
				c.sendMessage("You can't deposit coins!");
				return;
			}
		}
		if (c.getItems().getItemAmount(id) < amount) {
			amount = c.getItems().getItemAmount(id);
		}
		if (!c.getItems().playerHasItem(id, amount)) {
			c.sendMessage("You don't have that many items!");
			return;
		}
		if (slot == -1) {
			c.sendMessage("You cant deposit more than 8 items at once.");
			return;
		}
		c.getItems().deleteItem2(id, amount);
		if (c.party[slot] != id) {
			c.party[slot] = id;
			c.partyN[slot] = amount;
		} else {
			c.party[slot] = id;
			c.partyN[slot] += amount;
		}
		updateDeposit(c);
		lastDeposite = System.currentTimeMillis();
	}

	/**
	 * Opens the PartyChest interface
	 * 
	 */
	public static void open(Player c) {
		if (Config.PARTYROOM_DISABLED) {
			c.getPA().sendError("The Party Room has been disabled.");
			return;
		}
		c.inPartyRoom = true;
		updateGlobal(c);
		updateDeposit(c);
		c.getItems().resetItems(5064);
		c.getPA().sendFrame248(2156, 5063);
	}

	public void pendGameEnding() {
		EventManager.getSingleton().addEvent(new Event() {
			@Override
			public void execute(EventContainer c) {

				Server.partyRoom.clearAllBalloons();
				Server.partyRoom.clearContestants();

				c.stop(); // Keap this here!!!
			}
		}, getTime());
	}

	/**
	 * Places the Balloon ID to tile for all players in region
	 */
	public void placeObject(final int b, int x, int y, int face) {
		for (Player player : Server.playerHandler.getPlayers()) {
			if (player != null) {
				Player c = (Player) player;
				if (c.distanceToPoint(x, y) < 60)
					c.getPA().object(b, x, y, face, 10);
			}
		}
	}

	/**
	 * Pops balloon at tile
	 * 
	 * @param c
	 * @param x
	 * @param y
	 */
	public void popBalloon(Player c, int x, int y) {
		Balloon balloon = getBalloon(x, y);
		if (isContestant(c.getName())) {
			// c.sendMessage("You must wait for the next round to play.");
			// return;
		}
		if (balloon == null) {
			c.sendMessage("That balloon has already been popped.");
			placeObject(-1, x, y, 0);
			c.startAnimation(794);
			return;
		}
		balloon.pop(c);
	}

	/**
	 * Removes Ballool from list
	 * 
	 * @ArrayList<Balloon>
	 */
	public void remove(Balloon balloon) {
		balloons.remove(balloon);
	}

	/*
	 * Adds all players in partyroom to array
	 * 
	 * @ArrayList<String>
	 */
	public void setContestants() {
		for (int i = 0; i < Server.playerHandler.getPlayers().length; i++) {
			if (Server.playerHandler.getPlayers()[i] == null)
				continue;
			Player c = (Player) Server.playerHandler.getPlayers()[i];
			if (c.inArea(2730, 3462, 2743, 3475)) {
				contestants.add(c.getName());
				c.sendMessage("The game has started! Quick, pop the balloons!");
			}
		}
	}

	/**
	 * Starts the PartyRoom Timer for countdown. 3, 2, 1 - POP TEH BALLOONS
	 */
	@SuppressWarnings("unused")
	public void startTimer(Player p) {
		if (Config.PARTYROOM_DISABLED) {
			p.getPA().sendError("The Party Room has been disabled.");
			return;
		}
		if (Config.PARTYROOM_ADMINS_ONLY && p.playerRights != 3) {
			p.sendMessage("Only Administrators can pull this lever.");
			return;
		}
		if (getAmount() == 0) {
			p.getPA().sendError("There are no items in the chest.");
			return;
		}
		if (System.currentTimeMillis() - lastDeposite < 5000) {
			p.sendMessage("Someone is still placing items in the chest, please wait for them to finish.");
			return;
		}
		if (System.currentTimeMillis() - lastAnnouncment > getTime()
				|| getFilledBalloons() == 0) {
			clearAllBalloons();
			p.startAnimation(798);
			Server.partyRoom.appendDrops();
			lastAnnouncment = System.currentTimeMillis();
		} else {
			p.sendMessage("Please wait for the current round to finish.");
		}
	}

	/**
	 * Update everyones party interface
	 */
	public void updateAll() {
		for (int i = 0; i < Server.playerHandler.getPlayers().length; i++) {
			if (Server.playerHandler.getPlayers()[i] == null)
				continue;
			Player c = (Player) Server.playerHandler.getPlayers()[i];
			updateGlobal(c);
		}
	}

	/**
	 * Updates deposit interface for player
	 * 
	 * @param c
	 */
	public static void updateDeposit(Player c) {
		c.getItems().resetItems(5064);
		for (int i = 0; i < 8; i++) {
			if (c.partyN[i] <= 0)
				itemOnInterface(c, 2274, i, -1, 0);
			else
				itemOnInterface(c, 2274, i, c.party[i], c.partyN[i]);
		}
	}

	/**
	 * update global interface
	 * 
	 * @param c
	 */
	public static void updateGlobal(Player c) {
		for (int x = 0; x < roomItems.length; x++) {
			if (roomItemsN[x] <= 0)
				itemOnInterface(c, 2273, x, -1, 0);
			else
				itemOnInterface(c, 2273, x, roomItems[x], roomItemsN[x]);
		}
	}

	/**
	 * Withdraw your item from Party List
	 * 
	 * @param c
	 * @param slot
	 */
	public void withdrawItem(Player c, int slot) {
		if (c.party[slot] >= 0 && c.getItems().freeSlots() > 0) {
			c.getItems().addItem(c.party[slot], c.partyN[slot]);
			c.party[slot] = 0;
			c.partyN[slot] = 0;
		}
		updateDeposit(c);
		updateGlobal(c);
	}
	private final int[][] PARTY_TILES = {
			{3046, 3375}, {3039, 3371}, {3038, 3371}, {3037, 3371}, {3036, 3372}, {3037, 3372},
			{3038, 3372}, {3039, 3372}, {3040, 3372}, {3041, 3372}, {3042, 3372}, {3043, 3372},
			{3044, 3372}, {3045, 3372}, {3046, 3372}, {3047, 3372}, {3049, 3372}, {3050, 3372},
			{3051, 3372}, {3052, 3372}, {3053, 3372}, {3054, 3372}, {3055, 3372}, {3054, 3371},
			{3053, 3371}, {3052, 3371}, {3055, 3373}, {3054, 3373}, {3053, 3373}, {3052, 3373},
			{3051, 3373}, {3050, 3373}, {3049, 3373}, {3048, 3373}, {3047, 3373}, {3046, 3373},
			{3045, 3373}, {3044, 3373}, {3043, 3373}, {3042, 3373}, {3041, 3373}, {3040, 3373},
			{3039, 3373}, {3038, 3373}, {3037, 3373}, {3036, 3373}, {3036, 3374}, {3038, 3374},
			{3039, 3374}, {3040, 3374}, {3041, 3374}, {3042, 3374}, {3043, 3374}, {3044, 3374},
			{3045, 3374}, {3045, 3374}, {3046, 3374}, {3047, 3374}, {3048, 3374}, {3049, 3374},
			{3050, 3374}, {3051, 3374}, {3052, 3374}, {3053, 3374}, {3054, 3374}, {3055, 3374},
			{3054, 3375}, {3053, 3375}, {3052, 3375}, {3050, 3375}, {3049, 3375}, {3049, 3375},
			{3048, 3375}, {3047, 3375}, {3046, 3375}, {3045, 3375}, {3044, 3375}, {3043, 3375},
			{3042, 3375}, {3041, 3375}, {3039, 3375}, {3038, 3375}, {3037, 3375}, {3037, 3376},
			{3039, 3376}, {3040, 3376}, {3041, 3376}, {3042, 3376}, {3043, 3376}, {3044, 3376},
			{3045, 3376}, {3046, 3376}, {3047, 3376}, {3048, 3376}, {3049, 3376}, {3050, 3376},
			{3051, 3376}, {3052, 3376}, {3053, 3376}, {3054, 3377}, {3053, 3377}, {3052, 3377},
			{3051, 3377}, {3050, 3377}, {3049, 3377}, {3048, 3377}, {3047, 3377}, {3046, 3377},
			{3045, 3377}, {3044, 3377}, {3043, 3377}, {3042, 3377}, {3041, 3377}, {3040, 3377},
			{3039, 3377}, {3038, 3377}, {3038, 3378}, {3039, 3378}, {3040, 3378}, {3041, 3378},
			{3050, 3378}, {3051, 3378}, {3052, 3378}, {3053, 3378}, {3054, 3379}, {3053, 3379},
			{3052, 3379}, {3051, 3379}, {3050, 3379}, {3049, 3379}, {3048, 3379}, {3047, 3379},
			{3046, 3379}, {3045, 3379}, {3044, 3379}, {3043, 3379}, {3042, 3379}, {3041, 3379},
			{3040, 3379}, {3039, 3379}, {3038, 3379}, {3037, 3380}, {3039, 3380}, {3040, 3380},
			{3041, 3380}, {3042, 3380}, {3043, 3380}, {3044, 3380}, {3045, 3380}, {3046, 3380},
			{3047, 3380}, {3048, 3380}, {3049, 3380}, {3050, 3380}, {3051, 3380}, {3052, 3380},
			{3053, 3380}, {3054, 3381}, {3053, 3381}, {3052, 3381}, {3050, 3381}, {3049, 3381},
			{3048, 3381}, {3047, 3381}, {3046, 3381}, {3045, 3381}, {3044, 3381}, {3043, 3381},
			{3042, 3381}, {3041, 3381}, {3039, 3381}, {3038, 3381}, {3037, 3381}, {3036, 3382},
			{3037, 3382}, {3038, 3382}, {3039, 3382}, {3040, 3382}, {3041, 3382}, {3042, 3382},
			{3043, 3382}, {3044, 3382}, {3045, 3382}, {3046, 3382}, {3047, 3382}, {3048, 3382},
			{3049, 3382}, {3050, 3382}, {3051, 3382}, {3052, 3382}, {3053, 3382}, {3054, 3382},
			{3055, 3382}, {3055, 3383}, {3052, 3383}, {3051, 3383}, {3050, 3383}, {3049, 3383},
			{3048, 3383}, {3047, 3383}, {3046, 3383}, {3045, 3383}, {3044, 3383}, {3043, 3383},
			{3042, 3383}, {3041, 3383}, {3040, 3383}, {3036, 3383}, {3053, 3385}, {3039, 3385},
			{3039, 3384}, {3040, 3384}, {3041, 3384}, {3042, 3384}, {3043, 3384}, {3044, 3384},
			{3046, 3384}, {3047, 3384}, {3048, 3384}, {3048, 3384}, {3049, 3384}, {3050, 3384},
			{3051, 3384}, {3052, 3384}, {3052, 3385},
	};
}
