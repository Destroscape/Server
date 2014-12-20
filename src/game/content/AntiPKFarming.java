package game.content;

import game.entity.player.Player;

public class AntiPKFarming {

	/**
	 * Adds the host of the killed player.
	 *@param client Player that saves the host.
	 *@param host	Host address of the killed player.
	 *@return True if the host is added to the players array.
	 */

	public static boolean addHostToList(Player p, String host) {
		if(p != null) {
			return p.lastKilledPlayers.add(host);
		}
		return false;
	}

	/**
	 * Checks if the host is already on the players array.
	 * @param client Player that is adding the killed players host.
	 * @param host Host address of the killed player.
	 * @return True if the host is on the players array.
	 */

	public static boolean hostOnList(Player p, String host) {
		if(p != null) {
			if(p.lastKilledPlayers.indexOf(host) >= KILL_WAIT_MAX) {
				removeHostFromList(p, host);
				return false;
			}
			return p.lastKilledPlayers.contains(host);
		}
		return false;
	}

	/**
	 * Removes the host from the players array.
	 * @param client Player that is removing the host.
	 * @param host Host that is being removed.
	 * @return True if host is successfully removed.
	 */

	public static boolean removeHostFromList(Player p, String host) {
		if(p != null) {
			return p.lastKilledPlayers.remove(host);
		}
		return false;
	}

	/*
	 * Amount of kills you have to wait before the host is deleted.
	 */

	public static final int KILL_WAIT_MAX = 3;

}