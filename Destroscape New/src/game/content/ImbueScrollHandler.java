package game.content;

import java.util.HashMap;

import engine.util.Misc;
import game.entity.player.Player;

public class ImbueScrollHandler {

	/**
	 * Scroll Id
	 */
	private static final int SCROLL = 18336;

	/**
	 * Data for the rings
	 */
	private static enum RingData {

		ONYX(6575, 15017), SEER(6731, 15018), ARCHER(6733, 15019), WARRIOR(
				6735, 15020), BERSERKER_RING(6737, 15220);

		private int ringId, imbuedRing;

		private RingData(final int ringId, final int imbuedRing) {
			this.ringId = ringId;
			this.imbuedRing = imbuedRing;
		}

		private int getRingId() {
			return ringId;
		}

		private int getImbuedRing() {
			return imbuedRing;
		}

		private String getRingName() {
			return Misc.optimizeText(toString().toLowerCase().replaceAll("_",
					" "));
		}

		public static HashMap<Integer, RingData> ringData = new HashMap<Integer, RingData>();

		static {
			for (final RingData r : RingData.values()) {
				RingData.ringData.put(r.getRingId(), r);
			}
		}
	}

	/**
	 * Creates the imbued ring
	 * 
	 * @param c
	 *            Client c
	 * @param ringID
	 *            Ring that player wants to imbue.
	 */

	private static void imbueRing(final Player c, final int ringId) {
		RingData ringData = RingData.ringData.get(ringId);
		if (c.getItems().playerHasItem(ringId, 1)
				&& c.getItems().playerHasItem(SCROLL, 1) && ringData != null) {
			c.getItems().deleteItem(SCROLL, 1);
			c.getItems().deleteItem(ringId, 1);
			c.getItems().addItem(ringData.getImbuedRing(), 1);
			c.sendMessage("You have imbued the " + ringData.getRingName() + ".");
		}
	}

	/**
	 * Checks which item was used on what
	 * 
	 * @param c
	 *            Client c
	 * @param useWith
	 *            Ring Used | Scroll Used
	 * @param itemUsed
	 *            Used With Scroll | Used With Ring.
	 */

	public static void scrollOnRing(final Player c, final int useWith,
			final int itemUsed) {
		for (RingData ringData : RingData.values()) {
			if (itemUsed == ringData.getRingId()
					|| useWith == ringData.getRingId()) {
				imbueRing(c, ringData.getRingId());
			}
		}
	}

}