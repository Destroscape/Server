package game.net.packets;

import game.combat.magic.NonCombatSpells;
import game.entity.player.Player;

public class MagicOnItems implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		int slot = c.getInStream().readSignedWord();
		int itemId = c.getInStream().readSignedWordA();
		@SuppressWarnings("unused")
		int junk = c.getInStream().readSignedWord();
		int spellId = c.getInStream().readSignedWordA();
		switch (spellId) {
		case 1173:
			c.getNonCombatSpells().superHeatItem(itemId);
			break;
		case 1162:
		case 1178:
			NonCombatSpells.playerAlching(c, spellId, itemId, slot);
			break;
		case 1155: // Lvl-1 enchant sapphire
		case 1165: // Lvl-2 enchant emerald
		case 1176: // Lvl-3 enchant ruby
		case 1180: // Lvl-4 enchant diamond
		case 1187: // Lvl-5 enchant dragonstone
		case 6003: // Lvl-6 enchant onyx
			c.getMagic().enchantItem(itemId, spellId);
			break;

		}
		c.getCombat().resetPlayerAttack();
		c.usingMagic = false;
	}
}
