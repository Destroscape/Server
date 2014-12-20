package game.entity.player;

import java.util.ArrayList;

/*
 * Project Insanity - Evolved v.3
 * PlayerSaving.java
 */

public class PlayerSaving implements Runnable {

	private final ArrayList<Integer> requests = new ArrayList<Integer>();
	private Thread thread;
	private static PlayerSaving singleton;
	private static long lastGroupSave;
	private static final int SAVE_TIMER = 300000;

	public static PlayerSaving getSingleton() {
		return PlayerSaving.singleton;
	}

	public static void initialize() {
		PlayerSaving.singleton = new PlayerSaving();
		PlayerSaving.singleton.thread = new Thread(PlayerSaving.singleton);
		PlayerSaving.singleton.thread.start();
	}

	public static boolean saveRequired() {
		return System.currentTimeMillis() - PlayerSaving.lastGroupSave > PlayerSaving.SAVE_TIMER;
	}

	public synchronized void requestSave(final int i) {
		if (!requests.contains(i)) {
			requests.add(i);
			notify();
		}
	}

	@Override
	public synchronized void run() {
		while (true) {
			try {
				Thread.sleep(300000);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveRequests() {
		int totalSave = 0;
		for (final int id : requests) {
			if (PlayerHandler.players[id] != null) {
				PlayerSave.saveGame(PlayerHandler.players[id]);
				totalSave++;
			}
		}
		System.out.println("Saved a total of: " + totalSave + " games.");
		requests.clear();
	}

}
