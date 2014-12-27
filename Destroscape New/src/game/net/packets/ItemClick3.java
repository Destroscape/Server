package game.net.packets;

import engine.util.Misc;
import game.Config;
import game.content.gambling.DiceHandler;
import game.entity.player.Player;
import game.skill.hunter.ButterflyCatching;
import game.skill.runecrafting.TalismanHandler;
import game.skill.runecrafting.TalismanHandler.talismanData;
import game.skill.summoning.Summoning;

/*
 * Project Insanity - Evolved v.3
 * ItemClick3.java
 */

public class ItemClick3 implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int itemId11 = c.getInStream().readSignedWordBigEndianA();
		final int itemId1 = c.getInStream().readSignedWordA();
		final int itemId = c.getInStream().readSignedWordA();
		if (!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		if (itemId >= DiceHandler.DICE_BAG && itemId <= 15100) {
			DiceHandler.putupDice(c, itemId);
		}
		if (c.getPA().isFilledVial(c, itemId)) {
			c.getItems().deleteItem(itemId, 1);
			c.getItems().addItem(229, 1);
			c.sendMessage("You empty the vial.");
		}
		for (talismanData t : talismanData.values()) {
			if (itemId == t.getTalisman()) {
				TalismanHandler.handleTalisman(c, itemId);
			}
		}

		switch (itemId) {

		case 19757: //Varrock Armour 4
			if (c.effectRestore < 1) {
				c.sendMessage("You have already done this twice today.");
				return;
			}
			if (c.prayer < c.getMaxPrayer()) {
				c.startAnimation(645);
				c.prayer = c.getMaxPrayer();
				c.sendMessage("You recharge your prayer points.");
				c.getPA().refreshSkill(5);
				c.effectRestore -= 1;
			} else {
				c.sendMessage("You already have full prayer points.");
				return;
			}
			break;
		
		case 21498://Necromancer Staves
		case 21499:
		case 21500:
		case 21501:
			if (c.getItems().playerHasItem(itemId, 1)) {
				c.getItems().deleteItem(itemId, 1);
				c.getItems().addItem(15046, 1);
				c.getItems().addItem(592, 1);
				c.sendMessage("As you remove the skull, your staff began to deteriorate into ashes.");
			}
			break;
		
		case 3853:
			c.getDH().sendOption4("Warrior's Guild", "Weapon Game", "Clan Wars", "Party Room");
			c.dialogueAction = 3853;
			break;
		
		case 22496:
			if (c.getItems().playerHasItem(22496)
					&& c.getItems().playerHasItem(995, 1000000)) {
				c.getItems().deleteItem(22496, 1);
				c.getItems().deleteItem2(995, 1000000);
				c.getItems().addItem(22494, 1);
				c.ppsLeft = 3000;
				c.sendMessage("You have repaired your polypore staff for 1M.");
				c.sendMessage("You have " + c.ppsLeft + " charges left.");
			} else {
				c.sendMessage("You need to have 1M to repair your polypore staff.");
			}
			break;

		case 1929:
			c.getItems().deleteItem(1929, 1);
			c.getItems().addItem(1925, 1);
			c.sendMessage("You empty the bucket.");
			break;

		case 12047: // Spirit Wolf
			Summoning.summonFamiliar(c, 12047);
			break;

		case 10014:
		case 10016:
		case 10018:
		case 10020:
			ButterflyCatching.releaseButterfly(c, itemId);

		case 12043: // Dreadfowl
			Summoning.summonFamiliar(c, 12043);
			break;

		case 12059: // Spirit Spider
			Summoning.summonFamiliar(c, 12059);
			break;

		case 12019: // Thorny Snail
			Summoning.summonFamiliar(c, 12019);
			break;

		case 12009: // Granite crab
			Summoning.summonFamiliar(c, 12009);
			break;

		case 12778: // Spirit Mosquito
			Summoning.summonFamiliar(c, 12778);
			break;

		case 12049: // Desert Wyrm
			Summoning.summonFamiliar(c, 12049);
			break;

		case 12055: // Spirit Scorpion
			Summoning.summonFamiliar(c, 12055);
			break;

		case 12808: // Spirit Tz Kih
			Summoning.summonFamiliar(c, 12808);
			break;

		case 12067: // Albino Rat
			Summoning.summonFamiliar(c, 12067);
			break;

		case 12063: // Spirit Kalphite
			Summoning.summonFamiliar(c, 12063);
			break;

		case 12091: // Compost Mound
			Summoning.summonFamiliar(c, 12091);
			break;

		case 12800: // Giant Chinchompa
			Summoning.summonFamiliar(c, 12800);
			break;

		case 12053: // Vampire Bat
			Summoning.summonFamiliar(c, 12053);
			break;

		case 12065: // HONEY_BADGER
			Summoning.summonFamiliar(c, 12065);
			break;

		case 12021: // BEAVER
			Summoning.summonFamiliar(c, 12021);
			break;

		case 12285:
			Summoning.summonFamiliar(c, 12285);
			break;

		case 12818: // VOID_RAVAGER
			Summoning.summonFamiliar(c, 12818);
			break;

		case 12814: // VOID_SHIFTER
			Summoning.summonFamiliar(c, 12814);
			break;

		case 12798: // VOID_TORCHER
			Summoning.summonFamiliar(c, 12798);
			break;

		case 12780: // VOID_SPINNER
			Summoning.summonFamiliar(c, 12780);
			break;

		case 12073: // BRONZE_MINOTAUR
			Summoning.summonFamiliar(c, 12073);
			break;

		case 12087: // BULL_ANT
			Summoning.summonFamiliar(c, 12087);
			break;

		case 12071: // macaw
			Summoning.summonFamiliar(c, 12071);
			break;

		case 12051: // EVIL_TURNIP
			Summoning.summonFamiliar(c, 12051);
			break;

		case 12075: // IRON_MINOTAUR
			Summoning.summonFamiliar(c, 12075);
			break;

		case 12816: // PYRELORD
			Summoning.summonFamiliar(c, 12816);
			break;

		case 12041: // MAGPIE
			Summoning.summonFamiliar(c, 12041);
			break;

		case 12061: // BLOATED_LEECH
			Summoning.summonFamiliar(c, 12061);
			break;

		case 12035: // ABYSSAL_PARASITE
			Summoning.summonFamiliar(c, 12035);
			break;

		case 12027: // SPIRIT_JELLY
			Summoning.summonFamiliar(c, 12027);
			break;

		case 12077: // STEEL_MINOTAUR
			Summoning.summonFamiliar(c, 12077);
			break;

		case 12531: // IBIS
			Summoning.summonFamiliar(c, 12531);
			break;

		case 12810: // GRAAHK
			Summoning.summonFamiliar(c, 12810);
			break;

		case 12812: // KYATT
			Summoning.summonFamiliar(c, 12812);
			break;

		case 12784: // LARUPIA
			Summoning.summonFamiliar(c, 12784);
			break;

		case 12023: // Overlord
			Summoning.summonFamiliar(c, 12023);
			break;

		case 12085: // Smoke Devil
			Summoning.summonFamiliar(c, 12085);
			break;

		case 12037: // The Lurker
			Summoning.summonFamiliar(c, 12037);
			break;

		case 12015: // Cobra
			Summoning.summonFamiliar(c, 12015);
			break;

		case 12045: // Strange Plant
			Summoning.summonFamiliar(c, 12045);
			break;

		case 12079: // Mithril Minotaur
			Summoning.summonFamiliar(c, 12079);
			break;

		case 12802: // FIRE_TITAN
			Summoning.summonFamiliar(c, 12802);
			break;

		case 12804: // MOSS_TITAN
			Summoning.summonFamiliar(c, 12804);
			break;

		case 12806: // ICE_TITAN
			Summoning.summonFamiliar(c, 12806);
			break;

		case 12025: // HYDRA
			Summoning.summonFamiliar(c, 12025);
			break;

		case 12017: // SPIRIT_DAGG
			Summoning.summonFamiliar(c, 12017);
			break;

		case 12788: // LAVA_TITAN
			Summoning.summonFamiliar(c, 12788);
			break;

		case 12776: // SWAMP_TITAN
			Summoning.summonFamiliar(c, 12776);
			break;

		case 12083: // RUNE_MINOTAUR
			Summoning.summonFamiliar(c, 12083);
			break;

		case 12039: // UNICORN
			Summoning.summonFamiliar(c, 12039);
			break;

		case 12786: // GEYSER_TITAN
			Summoning.summonFamiliar(c, 12786);
			break;

		case 12089: // WOLPERTINGER
			Summoning.summonFamiliar(c, 12089);
			break;

		case 12796: // ABYSSAL_TITAN
			Summoning.summonFamiliar(c, 12796);
			break;

		case 12822: // IRON_TITAN
			Summoning.summonFamiliar(c, 12822);
			break;

		case 12790: // STEEL_TITAN
			Summoning.summonFamiliar(c, 12790);
			break;

		case 12123: // BARKER_TOAD
			Summoning.summonFamiliar(c, 12123);
			break;

		case 12031: // WAR_TORT
			Summoning.summonFamiliar(c, 12031);
			break;

		case 12029: // BUNYIP
			Summoning.summonFamiliar(c, 12029);
			break;

		case 12033: // FRUIT_BAT
			Summoning.summonFamiliar(c, 12033);
			break;

		case 12820: // LOCUS
			Summoning.summonFamiliar(c, 12820);
			break;

		case 12057: // ARCTIC_BEAR
			Summoning.summonFamiliar(c, 12057);
			break;

		case 14623: // PHEONIX
			Summoning.summonFamiliar(c, 14623);
			break;

		case 12792: // GOLEM
			Summoning.summonFamiliar(c, 12792);
			break;

		case 12069: // GRANITE_LOBSTER
			Summoning.summonFamiliar(c, 12069);
			break;

		case 12011: // MANTIS
			Summoning.summonFamiliar(c, 12011);
			break;

		case 12081: // ADAMANT_MINOTAUR
			Summoning.summonFamiliar(c, 12081);
			break;

		case 12782: // FORGE_REGENT
			Summoning.summonFamiliar(c, 12782);
			break;

		case 12794: // TALON_BEAST
			Summoning.summonFamiliar(c, 12794);
			break;

		case 12013: // GIANT_ENT
			Summoning.summonFamiliar(c, 12013);
			break;

		case 12095: // Spirit bird
			Summoning.summonFamiliar(c, 12095);
			break;

		case 12097:// Spirit bird
			Summoning.summonFamiliar(c, 12097);
			break;

		case 12099: // Spirit bird
			Summoning.summonFamiliar(c, 12099);
			break;

		case 12101:// Spirit bird
			Summoning.summonFamiliar(c, 12101);
			break;

		case 12103:// Spirit bird
			Summoning.summonFamiliar(c, 12103);
			break;

		case 12105: // Spirit bird
			Summoning.summonFamiliar(c, 12105);
			break;

		case 12107: // Spirit bird
			Summoning.summonFamiliar(c, 12107);
			break;

		case 12007: // Spirit Terrorbird
			Summoning.summonFamiliar(c, 12007);
			break;

		case 12093: // Pack Yak
			Summoning.summonFamiliar(c, 12093);
			break;

		case 1712:
		case 1710:
		case 1708:
		case 1706:
			c.getPA().handleGlory(itemId);
			c.itemUsing = itemId;
			break;

		default:
			if (Config.SERVER_DEBUG = true) {
				if (c.playerRights >= 4) {
					Misc.println(c.playerName + " - Item3rdOption: " + itemId
							+ " : " + itemId11 + " : " + itemId1);
				}
			}
			break;
		}
	}

}
