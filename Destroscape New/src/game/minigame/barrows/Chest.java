package game.minigame.barrows;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class Chest {

	public static final int Barrows[] = { 4708, 4710, 4712, 4714, 4716, 4718,
			4720, 4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736, 4738, 4745,
			4747, 4749, 4751, 4753, 4755, 4757, 4759 };

	public static int randomBarrows() {
		return Barrows[(int) (Math.random() * Barrows.length)];
	}

	public static void lootChest(final Player c) {
		if (c.brotherKills < 5) {
			c.sendMessage("You haven't killed enough of the barrows brothers.");
			return;
		}
		if (c.barrowsKillCount == 5 && c.barrowsNpcs[c.randomCoffin][1] == 1) {
			c.sendMessage("The brother has already been spawned.");
			return;
		}
		if (c.barrowsNpcs[c.randomCoffin][1] == 0 && c.barrowsKillCount >= 5) {
			NPCHandler.spawnNpc(c, c.barrowsNpcs[c.randomCoffin][0], 3551,
					9694 - 1, 0, 0, 120, 30, 200, 200, true, true);
			c.barrowsNpcs[c.randomCoffin][1] = 1;
			return;
		}
		if ((c.barrowsKillCount > 5 && c.barrowsNpcs[c.randomCoffin][1] == 2)) {
			c.getPA().resetBarrows();
			if (c.getItems().freeSlots() <= 5) {
				c.sendMessage("Your reward has been put in your bank.");
				c.getItems().addItemToBank(558,
						Misc.random(1000) + 250 + (c.barrowsKillCount * 3));
				c.getItems().addItemToBank(4740,
						Misc.random(50) + 25 + (c.barrowsKillCount / 2));
				c.getItems().addItemToBank(560,
						Misc.random(250) + 100 + (c.barrowsKillCount / 2));
				c.getItems().addItemToBank(565,
						Misc.random(150) + 50 + (c.barrowsKillCount / 2));
				if (Misc.random(9) == 0) {
					c.getItems().addItemToBank(randomBarrows(), 1);
				}
			} else {
				c.getItems().addItem(558,
						Misc.random(1000) + 250 + (c.barrowsKillCount * 3));
				c.getItems().addItem(4740,
						Misc.random(50) + 25 + (c.barrowsKillCount / 2));
				c.getItems().addItem(560,
						Misc.random(250) + 100 + (c.barrowsKillCount / 2));
				c.getItems().addItem(565,
						Misc.random(150) + 50 + (c.barrowsKillCount / 2));
				if (Misc.random(4) == 0) {
					c.getItems().addItem(randomBarrows(), 1);
				}
			}
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				int damage;

				@Override
				public void execute(CycleEventContainer p) {
					if (!inBarrows(c)) {
						p.stop();
						return;
					}
					damage = Misc.random(4) + 1;
					c.getPA().createPlayersProjectile(c.absX, c.absY, c.absX,
							c.absY, 60, 60, 60, 43, 31, -c.playerId - 1, 0);
					c.handleHitMask(damage, 0, 0);
					c.playerLevel[3] -= damage;
					c.getPA().refreshSkill(3);
					c.sendMessage("A bunch of rocks fall from the top of the cave and hit you!");
				}

				@Override
				public void stop() {
					c.getPA().walkableInterface(-1);
				}
			}, 10);
		}
	}

	/**
	 * Shakes the player's screen. Parameters 1, 0, 0, 0 to reset.
	 * 
	 * @param verticleAmount
	 *            How far the up and down shaking goes (1-4).
	 * @param verticleSpeed
	 *            How fast the up and down shaking is.
	 * @param horizontalAmount
	 *            How far the left-right tilting goes.
	 * @param horizontalSpeed
	 *            How fast the right-left tiling goes..
	 */
	public static void shakeScreen(final Player c, int verticleAmount,
			int verticleSpeed, int horizontalAmount, int horizontalSpeed) {
		c.getOutStream().createFrame(35);
		c.getOutStream().writeByte(verticleAmount);
		c.getOutStream().writeByte(verticleSpeed);
		c.getOutStream().writeByte(horizontalAmount);
		c.getOutStream().writeByte(horizontalSpeed);
	}

	public static boolean inBarrows(Player player) {
		return player.absX >= 3524 && player.absX <= 3590
				&& player.absY >= 9665 && player.absY <= 9725;
	}

}
