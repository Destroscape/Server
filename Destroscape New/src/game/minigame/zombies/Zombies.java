package game.minigame.zombies;

import game.Config;
import game.entity.npc.NPC;
import game.entity.player.Player;
import game.Server;
import engine.util.Misc;


@SuppressWarnings({ "unused" })
public class Zombies {

	Player p;

	public Zombies(Player c) {
		this.p = c;
	}

	/*
	 * Finds Zombies
	 */
	public int FindZombie() {
		for (int i = 0; i < p.Zombiez.length; i++) {
			if (p.Zombiez[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public void KillAllZombies() {
		for (int i = 0; i < p.Zombiez.length; i++) {
			if (p.Zombiez[i] != null) {
				p.Zombiez[i].isDead = true;
				p.Zombiez[i] = null;
			}
		}
	}

	/*
	 * Removes from array
	 */
	public void HandleZombieDeath(NPC n) {
		for (int i = 0; i < p.Zombiez.length; i++) {
			if (p.Zombiez[i] != null) {
				if (p.Zombiez[i] == n) {
					p.Zombiez[i] = null;

					if (GetZombieCount() <= 0) {
						p.Zombiewave += 1;
						spawnWave(p.Zombiewave);
					}

				}
			}
		}
	}

	/**
	 * 
	 * lol
	 */
	public static int POINTS = 0;

	/**
	 * The wave the game is on.
	 */
	public static int WAVE = 0;

	/**
	 * How many zombies killed.
	 */
	public static int ZOMBIES = 0;

	/**
	 * Is the game started or not.
	 */
	public static boolean STARTED;
	/**
	 * How many waves are left.
	 */
	public static int WAVESLEFT = 18;

	public static void interfaceSend(Player c) {
		if (STARTED = true) {
			c.getPA().sendFrame126("", 21120);
			c.getPA().sendFrame126("@gre@Points: @red@" + POINTS, 21121);
			// c.getPA().sendFrame126("" , 21122);
			c.getPA().sendFrame126("@gre@Wave: @red@" + Integer.toString(WAVE),
					21122);
			c.getPA().sendFrame126(
					"@gre@Waves Left: @red@" + Integer.toString(WAVESLEFT),
					21123);
			c.getPA().walkableInterface(21119);
		} else if (!STARTED) {
			resetInterface();
		} else
			return;
	}

	public static boolean playerInGame(Player p) {
		return p.absX > 3239 && p.absX < 9352 && p.absY > 3557 && p.absY < 9376;
		}

	public static void resetInterface() {
		WAVESLEFT = 18;
		WAVE = 0;
		ZOMBIES = 0;
		STARTED = false;
		POINTS = 0;

	}

	private int GetZombieCount() {
		int count = 0;
		for (int i = 0; i < p.Zombiez.length; i++) {
			if (p.Zombiez[i] != null) {
				count += 1;
				// ZOMBIES += 1;
				POINTS += 10 + Misc.random(5000);
			}
		}
		// p.sendMessage("Zombies left:"+count);
		return count;

	}

	public static int[][] Waves = {
			{ 73 },
			{ 73, 73 },
			{ 73, 73, 73 },
			{ 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73 },
			{ 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
					73, 73, 73, 73, 73, 73, 73, 73, 73 },

	};

	// public final int X_SPAWN = 2860;
	// public final int Y_SPAWN = 3725;

	public final int X_SPAWN = 3248;
	public final int Y_SPAWN = 9364;

	private void spawnWave(int wave) {
		this.KillAllZombies();
		WAVE += 1;
		WAVESLEFT -= 1;
		if (wave >= Waves.length) {
			exitGame(true, p);
			return;
		}

		for (int i = 0; i < Waves[wave].length; i++) {
			int npcId = Waves[wave][i];
			int modifier = Misc.random(wave);
			if (modifier == 0) {
				modifier = 1;
			}
			if (npcId > 1) {
				int H = p.heightLevel;
				int hp = getHp(npcId);
				int max = getMax(npcId);
				int atk = getAtk(npcId);
				int def = getDef(npcId);
				Server.npcHandler.SpawnZombieNPC(p, npcId,
						X_SPAWN + Misc.random(7), Y_SPAWN + Misc.random(8),
						p.heightLevel, 1, 80, 5, 50, 5, true, false);
			}
		int wavesLeft = Waves.length - wave;
		}
	}

	private int[] RARES = { 6583, 4083, 4063, 4025, 4026, 4030, 4030 };

	private int[] SUPERRARES = { 21547, 21557, 21549, 21559, 21539, 21543,
			21553, 21563, 21545, 21555, 21565, 21551, 21561, 21541 };

	public void exitGame(boolean won, Player c) {
		this.KillAllZombies();
		resetInterface();
		c.getPA().closeAllWindows();
		if (p == null) {
			return;
		}
		p.teleportToX = Config.EDGEVILLE_X;
		p.teleportToY = Config.EDGEVILLE_Y;
		p.heightLevel = 0;
		int PKpointreward = 15 + Misc.random(25);
		int wonRare = Misc.random(15);
		int wonArm = Misc.random(50);
		if (won && wonRare != 0 && wonRare != 1) {
			p.sendMessage("You have been awared  points.");
			p.sendMessage("Keep playing for a chance to win rares 1 in 15 chance");
		} else if (won && wonRare == 0) {
			//p.PkPoints += PKpointreward;
			//p.getItems().addItem(RARES[Misc.random(RARES.length - 1)], 1, "");
			p.sendMessage("You have been awared points. You won a rare");
		} else if (!won) {
			resetInterface();
			p.sendMessage("@red@You have lost, you get no reward!");
			KillAllZombies();
		} else if (won && wonRare == 1) {
			//p.getItems().addItem(this.p.getPA().randomBarrows(), 1, "");
			p.sendMessage("You have been awared points. You won some Barrow's");
		}

		if (won && wonArm == 0) {
			int rare = SUPERRARES[Misc.random(SUPERRARES.length - 1)];
			p.sendMessage("You won a rare!");
			//Server.yellToAll("[@mag@MINIGAME@bla@]: @gre@" + p.playerName
					//+ " @bla@won a @gre@" + p.getItems().getItemName(rare)
					//+ " @bla@from the Zombies minigame");
			//p.getItems().addItem(rare, 1, "Zombies Minigame brid arm reward");
		} else if (won && wonArm != 0) {
			p.sendMessage("You needed Rare Random to be 0 to win a brid arm your's was @gre@"
					+ wonArm + "");
			p.sendMessage("It is random you can still win Npc transformation items and barrows!");
		}

		KillAllZombies();
		p.Zombiewave = 0;
		resetInterface();
	}

	public void startGame() {
		p.Zombiewave = 0;
		p.teleportToX = X_SPAWN;
		p.teleportToY = Y_SPAWN;
		p.heightLevel = (p.playerId * 4) + 4;
		spawnWave(0);
		//p.getCombat().resetPrayers();
		STARTED = true;
	}

	public static int getHp(int npcId) {
		switch (npcId) {
		case 73:
			return 30;
		}
		return 100;
	}

	public static int getMax(int npcId) {
		switch (npcId) {
		case 73:
			return 14;
		}
		return 5;
	}

	public static int getAtk(int npcId) {
		switch (npcId) {
		case 73:
			return 100;
		}
		return 100;
	}

	public static int getDef(int npcId) {
		switch (npcId) {
		case 73:
			return 100;
		}
		return 100;
	}

}