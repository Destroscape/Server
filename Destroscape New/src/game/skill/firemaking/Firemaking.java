package game.skill.firemaking;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.world.Tile;
import game.Server;
import game.clip.region.Region;
import game.entity.player.Player;
import game.object.ObjectHandler;
import game.object.ObjectManager;
import game.object.Objects;
import game.skill.SkillHandler;

public class Firemaking extends SkillHandler {

	Player c;

	public Firemaking(Player c) {
		this.c = c;
	}

	private static int[][] logData = { { 1511, 1, 40, 2732 },
		{7406, 1,  250, 11406},		//	BLUE LOG
		{7405, 1,  250, 11405},		//	GREEN LOG
		{7404, 1,  250, 11404},		//	RED LOG
        	{10328, 1,  250, 20000},    //	WHITE LOG
        	{10329, 1,  250, 20001},	//	PURPLE LOG
		{ 2862, 1, 40, 2732 }, { 1521, 15, 60, 2732 },
		{ 1519, 30, 105, 2732 }, { 6333, 35, 105, 2732 },
		{ 1517, 45, 135, 2732 }, { 10810, 45, 135, 2732 },
		{ 6332, 50, 158, 2732 }, { 1515, 60, 203, 2732 },
		{ 1513, 75, 304, 2732 }, };

	public static boolean playerLogs(Player c, int i, int l) {
		boolean flag = false;
		for (int kl = 0; kl < logData.length; kl++) {
			if ((i == logData[kl][0] && requiredItem(c, l))
					|| (requiredItem(c, i) && l == logData[kl][0])) {
				flag = true;
			}
		}
		return flag;
	}

	private static int getAnimation(Player c, int item, int item1) {
		int[][] data = { { 841, 6714 }, { 843, 6715 }, { 849, 6716 },
				{ 853, 6717 }, { 857, 6718 }, { 861, 6719 } };
		for (int i = 0; i < data.length; i++) {
			if (item == data[i][0] || item1 == data[i][0]) {
				return data[i][1];
			}
		}
		return 733;
	}

	private static boolean requiredItem(Player c, int i) {
		int[] data = { 841, 843, 849, 853, 857, 861, 590 };
		for (int l = 0; l < data.length; l++) {
			if (i == data[l]) {
				return true;
			}
		}
		return false;
	}

	public static void grabData(final Player c, final int useWith, final int withUse) {
		final int[] coords = new int[2];
		coords[0] = c.absX;
		coords[1] = c.absY;
		if (c.woodCutting) {
			return;
		}

		for (@SuppressWarnings("unused")
		Objects o : ObjectHandler.globalObjects) {
			if (Objects.getObjectX() == c.getX()
					&& Objects.getObjectY() == c.getY()
					&& Objects.objectId != -1) {
				c.sendMessage("You can't light a fire here!");
				return;
			}
			if (Objects.getObjectX() == coords[0] && Objects.getObjectY() == coords[1] && Objects.objectId == 2732) {
				c.sendMessage("You can't light a fire here!");
				return;
			}
		}
		if (c.inBank()) {
			c.sendMessage("You cannot light a fire in a bank.");
			return;
		}
		for (int i = 0; i < logData.length; i++) {
			if ((requiredItem(c, useWith) && withUse == logData[i][0] || useWith == logData[i][0]
					&& requiredItem(c, withUse))) {
				if (c.playerLevel[11] < logData[i][1]) {
					c.sendMessage("You need a higher firemaking level to light this log!");
					return;
				}
				if (System.currentTimeMillis() - c.lastFire > 1200) {

					if (c.playerIsFiremaking) {
						return;
					}

					final int[] time = new int[3];
					final int log = logData[i][0];
					final int fire = logData[i][3];
					if (System.currentTimeMillis() - c.lastFire > 3000) {
						//c.setAnimation(Animation.create(getAnimation(c, useWith, withUse)));
						c.startAnimation(getAnimation(c, useWith, withUse));
						time[0] = 4;
						time[1] = 3;
					} else {
						time[0] = 1;
						time[1] = 2;
					}

					c.playerIsFiremaking = true;

					Server.itemHandler.createGroundItem(c, log, coords[0],
							coords[1], 1, c.getId());

					CycleEventHandler.getSingleton().addEvent(c,
							new CycleEvent() {
						@Override
						public void execute(
								CycleEventContainer container) {
							ObjectHandler.createAnObject(c, fire,
									coords[0], coords[1]);
							Server.itemHandler.removeGroundItem(c, log,
									coords[0], coords[1], false);
							c.playerIsFiremaking = false;
							container.stop();
						}

						@Override
						public void stop() {

						}
					}, time[0]);
					if (Region.getClipping(c.getX() - 1, c.getY(),
							c.heightLevel, -1, 0)) {
						c.getPA().walkTo2(-1, 0);
					} else if (Region.getClipping(c.getX() + 1, c.getY(),
							c.heightLevel, 1, 0)) {
						c.getPA().walkTo2(1, 0);
					} else if (Region.getClipping(c.getX(), c.getY() - 1,
							c.heightLevel, 0, -1)) {
						c.getPA().walkTo2(0, -1);
					} else if (Region.getClipping(c.getX(), c.getY() + 1,
							c.heightLevel, 0, 1)) {
						c.getPA().walkTo2(0, 1);
					}
					new Tile(c.absX - 1, c.absY, c.heightLevel);
					CycleEventHandler.getSingleton().addEvent(c,
							new CycleEvent() {
						@Override
						public void execute(
								CycleEventContainer container) {
							//c.setAnimation(Animation.create(65535));
							c.startAnimation(65535);
							c.sendMessage("You light the logs.");
							container.stop();
						}

						@Override
						public void stop() {
						}
					}, time[1]);
					CycleEventHandler.getSingleton().addEvent(c,
							new CycleEvent() {
						@Override
						public void execute(
								CycleEventContainer container) {
							ObjectHandler.createAnObject(c, -1,
									coords[0], coords[1]);
							Server.itemHandler.createGroundItem(c, 592,
									coords[0], coords[1], 1, c.getId());
							container.stop();
						}

						@Override
						public void stop() {

						}
					}, 120);
					if (c.playerEquipment[c.playerHands] == 13851) {
						c.getPA().addSkillXP(
								logData[i][2] * FIREMAKING_XP * 2, 11);
					} else {
						c.getPA().addSkillXP(
								logData[i][2] * FIREMAKING_XP, 11);
					}
					c.getItems().deleteItem(logData[i][0],
							c.getItems().getItemSlot(logData[i][0]), 1);
					c.turnPlayerTo(c.absX + 1, c.absY);
					c.lastFire = System.currentTimeMillis();
				}
			}
		}
		ObjectManager.loadCustomSpawns(c);
	}
}