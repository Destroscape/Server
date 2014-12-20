package game.minigame.fightpits;

import engine.util.Misc;
import game.entity.player.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ArrowzFtw
 */
public class FightPits {

	/**
	 * @note States of minigames
	 */
	private static final String PLAYING = "PLAYING";
	private static final String WAITING = "WAITING";
	/**
	 * @note Current fight pits champion
	 */
	private static String pitsChampion = " ";
	/**
	 * @note Countdown for game to start
	 */
	private static int gameStartTimer = 80;
	/**
	 * @note Elapsed Game start time
	 */
	/*
	 * @note Game started or not?
	 */
	private static boolean gameStarted = false;
	/**
	 * @note Stores player and State
	 */
	private static Map<Player, String> playerMap = Collections
			.synchronizedMap(new HashMap<Player, String>());

	/**
	 * @note Where to spawn when pits game starts
	 */
	private static final int MINIGAME_START_POINT_X = 2392;
	private static final int MINIGAME_START_POINT_Y = 5139;
	/**
	 * @note Exit game area
	 */
	private static final int EXIT_GAME_X = 2399;
	private static final int EXIT_GAME_Y = 5169;
	/**
	 * @note Exit waiting room
	 */
	public static final int EXIT_WAITING_X = 2399;
	public static final int EXIT_WAITING_Y = 5177;
	/**
	 * @note Waiting room coordinates
	 */
	private static final int WAITING_ROOM_X = 2399;
	private static final int WAITING_ROOM_Y = 5175;

	/**
	 * @return HashMap Value
	 */
	public static String getState(Player c) {
		return playerMap.get(c);
	}

	private static final int TOKKUL_ID = 6529;

	/**
	 * @note Adds player to waiting room.
	 */
	public static void addPlayer(Player c) {
		playerMap.put(c, WAITING);
		c.getPA().movePlayer(WAITING_ROOM_X, WAITING_ROOM_Y, 0);
	}

	/**
	 * @note Starts the game and moves players to arena
	 */
	private static void enterGame(Player c) {
		playerMap.put(c, PLAYING);
		int teleportToX = MINIGAME_START_POINT_X + Misc.random(12);
		int teleportToY = MINIGAME_START_POINT_Y + Misc.random(12);
		c.getPA().movePlayer(teleportToX, teleportToY, 0);
		c.sendMessage("If you are the last one left, leave the arena for your reward.");
	}

	/**
	 * @note Removes player from pits if there in waiting or in game
	 */
	public static void handleDeath(Player c) {
		playerMap.remove(c);
		c.getPA().movePlayer(2399, 5177, 0);
		c.absX = 2399;
		c.absY = 5177;
		c.getPA().walkableInterface(-1);
	}

	public static void removePlayer(Player c, boolean forceRemove) {
		if (forceRemove) {
			c.getPA().movePlayer(EXIT_WAITING_X, EXIT_WAITING_Y, 0);
			playerMap.remove(c);
			return;
		}
		String state = playerMap.get(c);
		if (state == null) {
			c.getPA().movePlayer(EXIT_WAITING_X, EXIT_WAITING_Y, 0);
			return;
		}

		if (state.equals(PLAYING)) {
			if (getListCount(PLAYING) - 1 == 0 && !forceRemove) {
				pitsChampion = c.playerName;
				c.headIcon = 21;
				c.updateRequired = true;
				c.getItems().addItem(TOKKUL_ID, 1500 + Misc.random(500));
			}
			c.getPA().movePlayer(EXIT_GAME_X, EXIT_GAME_Y, 0);
		} else if (state.equals(WAITING)) {
			c.getPA().movePlayer(EXIT_WAITING_X, EXIT_WAITING_Y, 0);
			c.getPA().walkableInterface(-1);
		}
		playerMap.remove(c);

		if (state.equals(PLAYING)) {
			if (!forceRemove) {
				playerMap.put(c, WAITING);
			}
		}
	}

	/**
	 * @return Players playing fight pits
	 */
	public static int getListCount(String state) {
		int count = 0;
		for (String s : playerMap.values()) {
			if (state == s) {
				count++;
			}
		}
		return count;
	}

	/**
	 * @note Updates players
	 */
	private static void update() {
		for (Player c : playerMap.keySet()) {
			String status = playerMap.get(c);
			@SuppressWarnings("unused")
			boolean updated = status == WAITING ? updateWaitingRoom(c)
					: updateGame(c);
		}
	}

	/**
	 * @note Updates waiting room interfaces etc.
	 */
	public static boolean updateWaitingRoom(Player j) {
		j.getPA().sendFrame126("Next Game Begins In : " + gameStartTimer, 2805);
		j.getPA().sendFrame126(
				"Champion: JalYt-Ket-"
						+ Misc.optimizeText(pitsChampion).replaceAll("_", " "),
				2806);
		j.getPA().sendFrame36(560, 1);
		j.getPA().walkableInterface(2804);
		return true;
	}

	/**
	 * @note Updates players in game interfaces etc.
	 */
	public static boolean updateGame(Player c) {
		c.getPA().sendFrame126("Players Remaining: " + getListCount(PLAYING),
				2805);
		c.getPA().sendFrame126("Champion: JalYt-Ket-" + pitsChampion, 2806);
		c.getPA().sendFrame36(560, 1);
		c.getPA().walkableInterface(2804);
		return true;
	}

	/*
	 * @process 600ms Tick
	 */
	public static void process() {
		update();
		if (!gameStarted) {
			if (gameStartTimer > 0) {
				gameStartTimer--;
			} else if (gameStartTimer == 0) {
				if (getListCount(PLAYING) > 0) {

				} else {
					if (getListCount(WAITING) > 1) {
						beginGame();
					}
				}
				gameStartTimer = 80;
			}
		}
	}

	/**
	 * @note Starts game for the players in waiting room
	 */
	private static void beginGame() {
		for (Player c : playerMap.keySet()) {
			enterGame(c);
		}
	}
}