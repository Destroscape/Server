package game.content;

import game.entity.player.Player;

public class ProduceExchanging {

	/**
	 * All of the produce item ids
	 */
	private static final short[] PRODUCE = { 5504, 5970, 5972, 5974, 5980,
			5982, 5986, 6010, 6016, 199, 201, 203, 205, 207, 209, 211, 213,
			215, 217, 219, 225, 226, 592, 1942, 1957, 1965, 1982, 6012, 6014,
			6018, 6006, 5994, 5996, 5998, 6000, 6002, 1951, 753, 2126, 247,
			239, 240 };

	/**
	 * Checks if the item is produce
	 * 
	 * @param produceId
	 *            Produce item id
	 * @return whether or not it is a produce item
	 */
	private static boolean isProduce(final short produceId) {
		for (short produce : PRODUCE) {
			return produceId == PRODUCE[produce];
		}
		return false;
	}

	/**
	 * Exchanges the produce
	 * 
	 * @param player
	 *            The player
	 * @param produceId
	 *            Produce item id
	 */
	public static void exchangeProduce(final Player player,
			final short produceId) {
		for (short produce : PRODUCE) {
			final int produceToReplace = player.getItems().getItemAmount(
					PRODUCE[produce]);
			if (produceId == PRODUCE[produce]
					&& isProduce(produceId)
					&& player.getItems().playerHasItem(PRODUCE[produce],
							produceToReplace)) {
				player.getItems().deleteItem2(PRODUCE[produce],
						produceToReplace);
				player.getItems().addItem(PRODUCE[produce] + 1,
						produceToReplace);
				player.sendMessage("The Leprechaun exchanges the "
						+ player.getItems().getItemName(produceId)
						+ " for bank notes.");
			}
		}
	}

}