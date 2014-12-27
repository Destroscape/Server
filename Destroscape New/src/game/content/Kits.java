package game.content;

import game.entity.player.Player;

public class Kits {

	private static enum Ornaments {

		// | NAME | Ornament ID | Original Item ID | New Item ID |

		FURY(19333, 6585, 19335), 
		DRAGON_FULL_HELM_SPIKE(19354, 11335, 19341), 
		DRAGON_PLATELEGS_SPIKE(19356, 4087, 19343), 
		DRAGON_PLATEBODY_SPIKE(19358, 14479, 19342), 
		DRAGON_SQUARE_SHIELD_SPIKE(19360, 1187, 19345), 
		DRAGON_FULL_HELM_GOLD(19346, 11335, 19336), 
		DRAGON_PLATELEGS_GOLD(19348, 4087, 19338), 
		DRAGON_PLATEBODY_GOLD(19350, 14479, 19337), 
		DRAGON_SQUARE_SHIELD_GOLD(19352, 1187, 19340), 
		BLESSED_SPIRIT_SHIELD(13734, 13754, 13736), 
		ARCANE_SPIRIT_SHIELD(13746, 13736, 13738), 
		ELYSIAN_SPIRIT_SHIELD(13750, 13736, 13742), 
		SPECTRAL_SPIRIT_SHIELD(13752, 13736, 13744), 
		DIVINE_SPIRIT_SHIELD(13748, 13736, 13740),
		NECROMANCERS_FIRE_STAFF(15046, 1387, 21498),
		NECROMANCERS_WATER_STAFF(15046, 1383, 21499),
		NECROMANCERS_EARTH_STAFF(15046, 1385, 21501),
		NECROMANCERS_AIR_STAFF(15046, 1381, 21500);

		private int kitID, firstItem, secondItem;

		private Ornaments(final int kitID, final int firstItem,
				final int secondItem) {
			this.kitID = kitID;
			this.firstItem = firstItem;
			this.secondItem = secondItem;
		}

		private int getKitID() {
			return kitID;
		}

		private int getFirstItem() {
			return firstItem;
		}

		private int getSecondItem() {
			return secondItem;
		}

		private static Ornaments getKitID(final int kitID) {
			for (Ornaments o : values()) {
				if (o.getKitID() == kitID) {
					return o;
				}
			}
			return null;
		}
	}

	public static void thisItemOnItemHandle(final Player c, final int useWith,
			final int itemUsed) {
		for (Ornaments orn : Ornaments.values()) {
			if (useWith == orn.getKitID() && itemUsed == orn.getFirstItem()
					|| useWith == orn.getFirstItem()
					&& itemUsed == orn.getKitID()) {
				makeOrnament(c, orn.getKitID());
			}
		}
	}

	private static void makeOrnament(final Player c, int kitID) {
		Ornaments orn = Ornaments.getKitID(kitID);
		if (c.getItems().playerHasItem(orn.getKitID(), 1)
				&& c.getItems().playerHasItem(orn.getFirstItem(), 1)) {
			c.getItems().deleteItem(orn.getKitID(),
					c.getItems().getItemSlot(orn.getKitID()), 1);
			c.getItems().deleteItem(orn.getFirstItem(),
					c.getItems().getItemSlot(orn.getFirstItem()), 1);
			c.getItems().addItem(orn.getSecondItem(), 1);
		}
	}
}