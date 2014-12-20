package game.net.packets;

import game.content.PriceChecker;
import game.content.Resting;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.skill.agility.GnomeCourse;
import game.skill.cooking.Cooking;
import game.skill.fishing.Fishing;
import game.skill.woodcutting.Woodcutting;

public class Walking implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			int packetSize) {
		if (c.xInterfaceId == 43933 || c.isChecking) {
			PriceChecker.clearConfig(c);
		}
		if (c.inTrade) {
			c.getTradeAndDuel().declineTrade(true);
		}
		c.clickNpcType = 0;
		c.clickObjectType = 0;
		if (c.doingAgility) {
			GnomeCourse.resetAgilityWalk(c);
		}
		if ((System.currentTimeMillis() - c.mageWalkDelay <= 1000)
				|| (System.currentTimeMillis() - c.rangeWalkDelay <= 1500)/*
				|| (System.currentTimeMillis() - c.meleeWalkDelay <= 750)*/) {
			return;
		}
		if (c.usedEasterRing == true) {
			c.sendMessage("Unmorph before you can start walking again!");
			return;
		}
		if (c.usedRingOfStone == true) {
			c.sendMessage("Unmorph before you can start walking again!");
			return;
		}
if(c.isFading)
return;
		c.inPartyRoom = false;
		c.isMining = false;
		c.getPA().resetSkills();
		c.isArrowing = false;
		c.isStringing = false;
		c.isBanking = false;
		Cooking.resetCooking(c);
		Fishing.resetFishing(c);
		c.walkingToItem = false;
		c.isSkilling[c.playerFletching] = false;
		c.isSkilling[c.playerCrafting] = false;
		c.isSkilling[c.playerSmithing] = false;
		Woodcutting.resetWoodcutting(c);
		if (packetType == 248 || packetType == 164) {
			c.clickObjectType = 0;
			c.clickNpcType = 0;
			c.clickObjectType = 0;
			c.clickNpcType = 0;
			c.faceUpdate(0);
			c.npcIndex = 0;
			c.playerIndex = 0;
			if (c.followId > 0 || c.followId2 > 0) {
				c.getPA().resetFollow();
			}
		}
		c.getPA().removeAllWindows();
		if (c.duelRule[1] && c.duelStatus == 5) {
			if (PlayerHandler.players[c.duelingWith] != null) {
				if (!c.goodDistance(c.getX(), c.getY(),
						PlayerHandler.players[c.duelingWith].getX(),
						PlayerHandler.players[c.duelingWith].getY(), 1)
						|| c.attackTimer == 0) {
					c.sendMessage("Walking has been disabled in this duel!");
				}
			}
			c.playerIndex = 0;
			return;
		}

		if (c.stopPlayerSkill) {
			c.stopPlayerSkill = false;
		}
		if (c.isResting) {
			Resting.stopResting(c);
			return;
		}
		if (c.freezeTimer > 0) {
			if (PlayerHandler.players[c.playerIndex] != null) {
				if (c.goodDistance(c.getX(), c.getY(),
						PlayerHandler.players[c.playerIndex].getX(),
						PlayerHandler.players[c.playerIndex].getY(), 1)
						&& packetType != 98) {
					c.playerIndex = 0;
					return;
				}
			}
			if (packetType != 98) {
				c.sendMessage("A magical force stops you from moving.");
				c.playerIndex = 0;
			}
			return;
		}
		if (c.playerStun) {
			c.sendMessage("You are stunned and cannot move.");
			return;
		}
		if (System.currentTimeMillis() - c.lastSpear < 4000) {
			c.sendMessage("You have been stunned.");
			c.playerIndex = 0;
			return;
		}
		if (System.currentTimeMillis() - c.lastSpear1 < 2000) {
			c.sendMessage("You cannot move anywhere until the dungeon starts.");
			c.playerIndex = 0;
			return;
		}
		if (packetType == 98) {
			c.mageAllowed = true;
		}
		if (c.duelStatus >= 1 && c.duelStatus <= 4 || c.duelStatus == 6) {
			if (c.duelStatus == 6) {
				c.getTradeAndDuel().claimStakedItems();
			}
			return;
		}
		if (c.respawnTimer > 3) {
			return;
		}
		if (c.inTrade) {
			return;
		}
		if (packetType == 248) {
			packetSize -= 14;
		}
if (c.teleTimer > 0) {
     return;
}
		c.newWalkCmdSteps = (packetSize - 5) / 2;
		if (++c.newWalkCmdSteps > Player.walkingQueueSize) {
			c.newWalkCmdSteps = 0;
			return;
		}
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		final int firstStepX = c.getInStream().readSignedWordBigEndianA()
				- c.getMapRegionX() * 8;
		for (int i = 1; i < c.newWalkCmdSteps; i++) {
			c.getNewWalkCmdX()[i] = c.getInStream().readSignedByte();
			c.getNewWalkCmdY()[i] = c.getInStream().readSignedByte();
		}

		final int firstStepY = c.getInStream().readSignedWordBigEndian()
				- c.getMapRegionY() * 8;
		c.setNewWalkCmdIsRunning(c.getInStream().readSignedByteC() == 1);
		for (int i1 = 0; i1 < c.newWalkCmdSteps; i1++) {
			c.getNewWalkCmdX()[i1] += firstStepX;
			c.getNewWalkCmdY()[i1] += firstStepY;
		}
		if ((c.absX - (c.mapRegionX * 8)) - firstStepX != 0
				|| (c.absY - (c.mapRegionY * 8)) - firstStepY != 0)
			//c.setAnimation(Animation.create(65535));
			c.startAnimation(65535);
	}

}
