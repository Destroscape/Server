package game.content;

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

public class MithrilFlower {

	Player c;

	public MithrilFlower(Player c) {
		this.c = c;
	}

	public static final int FLOWER_ID = 299;

	public static int flower[] = {2980,2981,2982,2983,2984,2985,2986,2987};
	public static int randomflower() {
                return flower[(int)(Math.random()*flower.length)];
        }

	public static void plantFlower(final Player c) {
		final int flowers = randomflower();
		final int[] coords = new int[2];
		coords[0] = c.absX;
		coords[1] = c.absY;

		for (@SuppressWarnings("unused")
		Objects o : ObjectHandler.globalObjects) {
			if (Objects.getObjectX() == c.getX()
					&& Objects.getObjectY() == c.getY()
					&& Objects.objectId != -1) {
				c.sendMessage("You can't plant a flower here!");
				return;
			}
		}
		if (c.inBank()) {
			c.sendMessage("You cannot plant a flower in a bank.");
			return;
		}
				if (System.currentTimeMillis() - c.lastPlant > 1200) {

					final int[] time = new int[3];
					if (System.currentTimeMillis() - c.lastPlant > 3000) {
						c.startAnimation(2291);
						time[0] = 4;
						time[1] = 3;
					} else {
						time[0] = 1;
						time[1] = 2;
					}

					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							ObjectHandler.createAnObject(c, flowers, coords[0], coords[1]);
							container.stop();
						}

						@Override
						public void stop() {

						}
					}, time[0]);
					if (Region.getClipping(c.getX() - 1, c.getY(), c.heightLevel, -1, 0)) {
						c.getPA().walkTo2(-1, 0);
					} else if (Region.getClipping(c.getX() + 1, c.getY(), c.heightLevel, 1, 0)) {
						c.getPA().walkTo2(1, 0);
					} else if (Region.getClipping(c.getX(), c.getY() - 1, c.heightLevel, 0, -1)) {
						c.getPA().walkTo2(0, -1);
					} else if (Region.getClipping(c.getX(), c.getY() + 1, c.heightLevel, 0, 1)) {
						c.getPA().walkTo2(0, 1);
					}
					new Tile(c.absX - 1, c.absY, c.heightLevel);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(
								CycleEventContainer container) {
							c.startAnimation(65535);
							c.sendMessage("You plant the Flower.");
							container.stop();
						}

						@Override
						public void stop() {
						}
					}, time[1]);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(
								CycleEventContainer container) {
							ObjectHandler.createAnObject(c, -1, coords[0], coords[1]);
							container.stop();
						}

						@Override
						public void stop() {

						}
					}, 120);
					c.getItems().deleteItem(299, c.getItems().getItemSlot(299), 1);
					c.turnPlayerTo(c.absX + 1, c.absY);
					c.lastPlant = System.currentTimeMillis();
				}
		ObjectManager.loadCustomSpawns(c);
	}
}