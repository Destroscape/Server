package game.entity.player;

public class PlayerKilling {

	private Player c;

	/**
	 * Constructor class
	 */

	public PlayerKilling(Player Client) {
		this.c = Client;
	}

	/**
	 * How many people you have to kill before getting points again for killing
	 * the same person.
	 */

	public final int NEEDED_KILLS = 3;

	/**
	 * Much easier to handle through Hashmap. Previous code was useless ~ Lester
	 * Knome ~
	 */

	public boolean addPlayer(String i) {
		Integer kill = c.killedPlayers.get(i);
		if (kill == null || kill <= 0) {
			kill = NEEDED_KILLS;
			c.killedPlayers.put(i, kill);
			return true;
		} else {
			c.killedPlayers.put(i, (kill -= 1));
			return false;
		}
	}

}
