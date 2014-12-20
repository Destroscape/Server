package game.content.travel;

import game.entity.player.Player;

public class GnomeGlider {

	private static final int[][] GLIDER_DATA = { 
			{ 100000, 2672, 3711, 0, 1 }, // rock crabs
			{ 100001, 3435, 3514, 0, 2 }, // Slayer Tower
			{ 100002, 2712, 9564, 0, 3 }, // BrimHaven Dungeon
			{ 100003, 2881, 3394, 0, 4 }, // Taverly Dungeon
			{ 100004, 2660, 2660, 0, 5 }, // Pest Control
			{ 100005, 3565, 3316, 0, 6 }, // Barrows
			{ 100006, 3350, 3266, 0, 7 }, // Duel Arena
			{ 100007, 2445, 5173, 0, 8 }, // Tz-Haar Caves
			{ 100008, 3085, 3492, 0, 9 }, // Edgeville
			{ 100009, 3350, 3669, 0, 10 }, // East Dragons
			{ 100010, 2989, 3603, 0, 4 }, // West Dragons
			{ 100011, 2538, 4716, 0, 3 }, // Mage Bank
			{ 100012, 2881, 5310, 2, 7 }, // Godwars
			{ 100013, 2509, 4690, 0, 6 }, // Corp beast
			{ 100014, 2443, 10146, 0, 5 }, // Daggonth Kings
			{ 100015, 2744, 5064, 0, 8 }, // T Demons
			{ 100016, 2907, 9717, 0, 8 }, // Knights Hall
			{ 100017, 3240, 2772, 0, 4 }, // Desert Training location
			{ 100018, 3558, 9946, 0, 6 }, // revs
	};

	public static void flightButtons(final Player c, final int button) {
		for (int i = 0; i < GnomeGlider.getLength(); i++) {
			if (GnomeGlider.getButton(i) == button) {
				GnomeGlider.handleFlight(c, i);
			}
		}
	}

	public static int getButton(final int i) {
		return GnomeGlider.GLIDER_DATA[i][0];
	}

	public static int getH(final int i) {
		return GnomeGlider.GLIDER_DATA[i][3];
	}

	public static int getLength() {
		return GnomeGlider.GLIDER_DATA.length;
	}

	public static int getMove(final int i) {
		return GnomeGlider.GLIDER_DATA[i][4];
	}

	public static int getX(final int i) {
		return GnomeGlider.GLIDER_DATA[i][1];
	}

	public static int getY(final int i) {
		return GnomeGlider.GLIDER_DATA[i][2];
	}

	public static void handleFlight(final Player c, final int flightId) {
				c.getPA().spellTeleport(GnomeGlider.getX(flightId),
						GnomeGlider.getY(flightId), GnomeGlider.getH(flightId));
	}

}
