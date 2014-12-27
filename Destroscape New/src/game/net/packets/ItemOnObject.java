package game.net.packets;

import game.entity.player.Player;
import game.skill.cooking.Cooking;
import game.skill.crafting.JewelryMaking;
import game.skill.prayer.Altar;
import game.skill.prayer.Constants;

public class ItemOnObject implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {

		c.getInStream().readUnsignedWord();
		final int objectId = c.getInStream().readSignedWordBigEndian();
		final int objectY = c.getInStream().readSignedWordBigEndianA();
		c.getInStream().readUnsignedWord();
		final int objectX = c.getInStream().readSignedWordBigEndianA();
		final int itemId = c.getInStream().readUnsignedWord();
		if (!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		c.objectX = objectX;
		c.objectY = objectY;
		UseItem.ItemonObject(c, objectId, objectX, objectY, itemId);
		c.turnPlayerTo(objectX, objectY);
		switch (objectId) {
		case 409:
			if (Constants.playerBones(c, itemId)) {
				Altar.bonesOnAltar(c, itemId);
			}
			break;

		case 8151:
		case 8389:
		case 8132:
		case 7848: // /flower patch catherby
			c.getFarming().checkItemOnObject(itemId);
			break;

		case 11666:
		case 2781:
			if (itemId == 2357) {
				JewelryMaking.jewelryInterface(c);
			}
			break;

		case 12269:
		case 2732:
		case 114:
		case 9374:
		case 2728:
		case 25465:
		case 11404:
		case 11405:
		case 11406:
			if (c.goodDistance(c.getX(), c.getY(), c.objectX, c.objectY, 1)) {
			Cooking.cookThisFood(c, itemId, objectId);
			}
			break;

		case 878:
		case 884:
		case 3359:
		case 3485:
		case 4004:
		case 4005:
		case 5086:
		case 11661:
		case 6097:
			if (itemId == 1925) {
				c.getItems().deleteItem(1925, 1);
				c.getItems().addItem(1929, 1);
				c.sendMessage("You fill the bucket with water.");
				c.startAnimation(1064, 1);
			} else if (itemId == 229) {
				c.getItems().deleteItem(229, 1);
				c.getItems().addItem(227, 1);
				c.sendMessage("You fill the vial with water.");
				c.startAnimation(1064, 1);
			}
			break;
		}
	}

}
