package game.minigame.warriorsguild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import engine.util.Misc;
import game.Server;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class WarriorsGuild {

	private static WarriorsGuild INSTANCE = new WarriorsGuild();

	public static WarriorsGuild getInstance() {
		return INSTANCE;
	}

	/**
	 * A list of all the players inside the Cyclops arena.
	 */
	public static final List<Player> IN_GAME = new ArrayList<Player>();

	private static HashMap<Integer, ArmourSet> armours = new HashMap<Integer, ArmourSet>();

	static {
		for (ArmourSet set : ArmourSet.values()) {
			for (int i : set.getArmour())
				armours.put(i, set);
		}
	}

	public enum ArmourSet {
		BRONZE(new int[]{1075, 1117, 1155}, 4278, 10),
		IRON(new int[]{1153, 1115, 1067}, 4279, 20),
		STEEL(new int[]{1157, 1119, 1069}, 4280, 30),
		BLACK(new int[]{1165, 1125, 1077}, 4281, 40),
		MITRHIL(new int[]{1159, 1121, 1071}, 4282, 50),
		ADAMANT(new int[]{1161, 1123, 1073}, 4283, 60),
		RUNE(new int[]{1127, 1079, 1163}, 4284, 80);

		public int getReward() {
			return reward;
		}

		public int getNpcId() {
			return npcId;
		}

		public int[] getArmour() {
			return armour;
		}

		private int reward;
		private int npcId;
		private int[] armour;

		/**
		 * @param armour The armour to use
		 * @param npcId  The NPC to spawn
		 * @param reward The token reward
		 */
		ArmourSet(int[] armour, int npcId, int reward) {
			this.armour = armour;
			this.npcId = npcId;
			this.reward = reward;
		}

	}

	static {
		for (ArmourSet set : ArmourSet.values()) {
			for (int i : set.getArmour())
				armours.put(i, set);
		}
	}

	private ArmourSet armour;

	public static boolean isArmourPiece(int itemId) {
		return armours.containsKey(itemId);
	}

	public void armorOnAnimator(final Player c, int itemUsed) {
		armour = armours.get(itemUsed);
		if (armour == null)
			return;
		if(!c.getItems().playerHasItem(armour.getArmour())) {
			c.sendMessage("Need full armour set");
			return;
		}
		for (int i : armour.getArmour()) {	
			c.getItems().deleteItem2(i, 1);

		}
		//c.setAnimation(Animation.create(827));
		c.startAnimation(827);
		c.getDH().sendInformationBox("", "You place your armour on the platform where it disappears....", "", "", "");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.getDH().sendInformationBox("", "The animator hums, something appears to be working. You stand back...", "", "", "");
				container.stop();
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		}, 4);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				//c.getPA().removeAllWindows();
				c.setForceMovement(0, -4, false, false, 0, 70, 2,820, c.absX, c.absY +4,  1000);
				container.stop();
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		}, 4);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if(c != null) {
					c.getPA().removeAllWindows();
					NPCHandler.spawnNpc(c, armour.getNpcId(), 2854, 3536, 0, 0, 30, 10, 80, 100, true, true);

					container.stop();
				}
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		}, 8);
	}

	public void getDrop(Player c, int x, int y) {
		boolean destroyed = false;
		for (int i : armour.getArmour()) {	
			if(Misc.random(60) == 0 && !destroyed) {
				c.sendMessage("Unfortuantly your " + c.getItems().getItemName(i) + " were destroyed.");
				destroyed = true;
			} else {
				Server.itemHandler.createGroundItem(c, i, x, y, 1, c.playerId);
			}
		}
		Server.itemHandler.createGroundItem(c, 8851, x, y, armour.getReward(), c.playerId);
	}

	/**
	 * Method declared to check both inventory and equipment
	 */

	public boolean hasDefender(Player c, int def) {
		return c.getItems().playerHasItem(def, 1) || c.playerEquipment[c.playerShield] == def;
	}

	/**
	 * Cyclops drops
	 */

	public int getDefender(Player c) {
		if(hasDefender(c,8844) && !hasDefender(c, 8845) && !hasDefender(c, 8846) && !hasDefender(c, 8847) && !hasDefender(c, 8848) && !hasDefender(c, 8849) && !hasDefender(c, 8850)) {
			return 8845;
		} else if(hasDefender(c,8845) && !hasDefender(c, 8846) && !hasDefender(c, 8847) && !hasDefender(c, 8848) && !hasDefender(c, 8849) && !hasDefender(c, 8850)) {
			return 8846;
		} else if(hasDefender(c,8846) && !hasDefender(c, 8847) && !hasDefender(c, 8848) && !hasDefender(c, 8849) && !hasDefender(c, 8850)) {
			return 8847;
		} else if(hasDefender(c,8847) && !hasDefender(c, 8848) && !hasDefender(c, 8849) && !hasDefender(c, 8850)) {
			return 8848;
		} else if(hasDefender(c,8848) && !hasDefender(c, 8849) && !hasDefender(c, 8850)) {
			return 8849;
		} else if(hasDefender(c,8849) && !hasDefender(c, 8850)) {
			return 8850;
		} else if(hasDefender(c,8850)) {
			return 20072;
		}
		return 8844;
	}

	public void handleKamfreena(Player c) {

		if (c.absX >= c.objectX) {

			c.sendMessage("You leave the Cyclopes room.");
			IN_GAME.remove(c);
			c.getPA().movePlayer(2846, 3540, 2);
		} else {

			if (!c.getItems().playerHasItem(8851, 100)) {
				//c.getDialogue().sendDialogue(4289, 1);

				c.sendMessage("You need atleast 100 tokens!");
				return;
			}
			c.sendMessage("The Cyclopes will now drop <col=255>"
					+ c.getItems().getItemName(getDefender(c))
					+ "s.");
			IN_GAME.add(c);
			c.getPA().movePlayer(2847, 3540, 2);
			removeTokens(c);
		}
	}

	/**
	 * Called once this player runs out of tokens..
	 */
	public void outOfTokens(final Player c) {
		c.getDH().sendDialogues(80, 4289);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if(IN_GAME.contains(c)) {
					c.getDH().sendDialogues(81, 4289);
					IN_GAME.remove(c);
					c.getPA().movePlayer(2844, 3539, 2);
				}
				container.stop();
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}

		}, 30);

	}

	public void removeTokens(final Player c) {
		if(IN_GAME.contains(c)) {
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if(!IN_GAME.contains(c) || c.disconnected) {
						container.stop();
						return;
					}
					if(c.getItems().playerHasItem(8851, 20)) {
						c.sendMessage("You lose 20 tokens.");
						c.getItems().deleteItem2(8851, 20);
					} 
					if(!c.getItems().playerHasItem(8851, 20)) {
						c.sendMessage("You have ran out of tokens!");
						outOfTokens(c);
						container.stop();
					}
				}

				@Override
				public void stop() {
					// TODO Auto-generated method stub

				}
			}, 100);
		}
	}

	public void killedCyclop(Player c, int x, int y) {
		if(IN_GAME.contains(c)) {
			if(Misc.random(35) == 0) {
				Server.itemHandler.createGroundItem(c, getDefender(c), x, y, 1, c.playerId);
			}
		}
	}
}
