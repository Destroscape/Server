package game.skill.fletching;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;
import game.skill.SkillHandler;

public class BowHandler extends SkillHandler {

	/**
	 * Variable Declarations
	 */

	private final static int KNIFE = 946;
	protected final static int AMOUNT = 15;
	final static int ANIMATION = 1248;
	protected final static int FLETCHING = 9;

	/**
	 * All of the bow data
	 * 
	 * @author Scott | Ochroid Enum ~ Log ID | Button For Make 1 | Button For
	 *         Make 5 | Button For Make 10 | Button For Make All | Button For
	 *         Fletched Item | Experienced Earned
	 */

	private static enum Bow {

		SHORTBOW(1511, 34205, 34204, 34203, 34202, 50, 5, 1), OAK_SHORTBOW(
				1521, 34185, 34184, 34183, 34182, 54, 17, 20), WILLOW_SHORTBOW(
						1519, 34185, 34184, 34183, 34182, 60, 33, 35), MAPLE_SHORTBOW(
								1517, 34185, 34184, 34183, 34182, 64, 50, 50), YEW_SHORTBOW(
										1515, 34185, 34184, 34183, 34182, 68, 68, 65), MAGIC_SHORTBOW(
												1513, 34170, 34169, 34168, 34167, 72, 83, 80);

		private int log, button, button5, button10, buttonAll, item, XP, req;

		private Bow(final int log, final int button, final int button5,
				final int button10, final int buttonAll, final int item,
				final int XP, final int req) {
			this.log = log;
			this.button = button;
			this.button5 = button5;
			this.button10 = button10;
			this.buttonAll = buttonAll;
			this.item = item;
			this.XP = XP;
			this.req = req;
		}

		private final int getBow() {
			return item;
		}

		private final int getButton1() {
			return button;
		}

		private final int getButton5() {
			return button5;
		}

		private final int getButton10() {
			return button10;
		}

		private final int getButtonAll() {
			return buttonAll;
		}

		private final int getLog() {
			return log;
		}

		private final int getXP() {
			return XP * FLETCHING_XP;
		}

		private final int getReq() {
			return req;
		}

		private static Bow itemID(final int log) {
			for (Bow b : values()) {
				if (b.getLog() == log) {
					return b;
				}
			}
			return null;
		}
	}

	private static enum Crossbow {

		CROSSBOW(1511, 34213, 34212, 34211, 34210, 9440, 12, 9), OAK_CROSSBOW(
				1521, 34193, 34192, 34191, 34190, 9442, 44, 39), WILLOW_CROSSBOW(
						1519, 34193, 34192, 34191, 34190, 9444, 54, 46), MAPLE_CROSSBOW(
								1517, 34193, 34192, 34191, 34190, 9448, 82, 54), YEW_CROSSBOW(
										1515, 34193, 34192, 34191, 34190, 9452, 100, 69);

		private int log, button, button5, button10, buttonAll, item, XP, req;

		private Crossbow(final int log, final int button, final int button5,
				final int button10, final int buttonAll, final int item,
				final int XP, final int req) {
			this.log = log;
			this.button = button;
			this.button5 = button5;
			this.button10 = button10;
			this.buttonAll = buttonAll;
			this.item = item;
			this.XP = XP;
			this.req = req;
		}

		private final int getBow2() {
			return item;
		}

		private final int getButton12() {
			return button;
		}

		private final int getButton52() {
			return button5;
		}

		private final int getButton102() {
			return button10;
		}

		private final int getButtonAll2() {
			return buttonAll;
		}

		private final int getLog2() {
			return log;
		}

		private final int getXP2() {
			return XP * FLETCHING_XP;
		}

		private final int getXP3() {
			return XP * 55;
		}

		private final int getReq2() {
			return req;
		}

		private static Crossbow itemID(final int log) {
			for (Crossbow cr : values()) {
				if (cr.getLog2() == log) {
					return cr;
				}
			}
			return null;
		}
	}

	/**
	 * All of the bow data
	 * 
	 * @author Scott | Ochroid Enum ~ Log ID | Button For Make 1 | Button For
	 *         Make 5 | Button For Make 10 | Button For Make All | Button For
	 *         Fletched Item | Experienced Earned
	 */

	private static enum Longbow {

		LONGBOW(1511, 34209, 34208, 34207, 34206, 48, 10, 10), OAK_LONGBOW(
				1521, 34189, 34188, 34187, 34186, 56, 25, 25), WILLOW_LONGBOW(
						1519, 34189, 34188, 34187, 34186, 58, 42, 40), MAPLE_LONGBOW(
								1517, 34189, 34188, 34187, 34186, 62, 58, 55), YEW_LONGBOW(
										1515, 34189, 34188, 34187, 34186, 66, 75, 70), MAGIC_LONGBOW(
												1513, 34174, 34173, 34172, 34171, 70, 97, 85);

		private int log, button, button5, button10, buttonAll, item, XP, req;

		private Longbow(final int log, final int button, final int button5,
				final int button10, final int buttonAll, final int item,
				final int XP, final int req) {
			this.log = log;
			this.button = button;
			this.button5 = button5;
			this.button10 = button10;
			this.buttonAll = buttonAll;
			this.item = item;
			this.XP = XP;
			this.req = req;
		}

		private final int getBow1() {
			return item;
		}

		private final int getButton11() {
			return button;
		}

		private final int getButton51() {
			return button5;
		}

		private final int getButton101() {
			return button10;
		}

		private final int getButtonAll1() {
			return buttonAll;
		}

		private final int getLog1() {
			return log;
		}

		private final int getXP1() {
			return XP * FLETCHING_XP;
		}

		private final int getXP4() {
			return XP * 55;
		}

		private final int getReq1() {
			return req;
		}

		private static Longbow itemID1(final int log) {
			for (Longbow l : values()) {
				if (l.getLog1() == log) {
					return l;
				}
			}
			return null;
		}
	}

	public static void fletchCrossbow(final Player c, final int button) {
		final Crossbow l = Crossbow.itemID(c.logID);
		if (l == null) {
			return;
		}
		if (button == l.getButton12() || button == l.getButton52()
				|| button == l.getButton102() || button == l.getButtonAll2()) {
			if (c.isSkilling[FLETCHING] == true) {
				return;
			}
			if (c.playerLevel[FLETCHING] < l.getReq2()) {
				c.sendMessage("You must have a fletching level of at least "
						+ l.getReq2() + " to do this.");
				return;
			}
			//c.setAnimation(Animation.create(ANIMATION));
			c.startAnimation(ANIMATION);
			c.getPA().closeAllWindows();
			c.isSkilling[FLETCHING] = true;
			c.doAmount = c.fletchAmount(button);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c.doAmount == 0) {
						container.stop();
						return;
					}
					if (c.isSkilling[FLETCHING] == false) {
						container.stop();
						return;
					}
					if (!c.getItems().playerHasItem(KNIFE)
							|| !c.getItems().playerHasItem(c.logID)) {
						c.sendMessage("You do not have the correct supplies to fletch this.");
						container.stop();
						return;
					}
					//c.setAnimation(Animation.create(ANIMATION));
					c.startAnimation(ANIMATION);
					c.getItems().deleteItem(c.logID, 1);
					c.getItems().addItem(l.getBow2(), 1);
					if (c.isPVPActive == true) {
						c.getPA().addSkillXP(l.getXP3(), FLETCHING);
					} else {
						c.getPA().addSkillXP(l.getXP2(), FLETCHING);
					}
					c.doAmount--;
				}

				@Override
				public void stop() {
					c.getPA().closeAllWindows();
					c.logID = -1;
					//c.setAnimation(Animation.create(c.playerStandIndex));
					c.startAnimation(c.playerStandIndex);
					c.doAmount = 0;
					c.isSkilling[FLETCHING] = false;
				}
			}, 4);
		} else {
			return;
		}
	}

	public static void fletchUnstrungLongbow(final Player c, final int button) {
		final Longbow l = Longbow.itemID1(c.logID);
		if (l == null) {
			return;
		}
		if (button == l.getButton11() || button == l.getButton51()
				|| button == l.getButton101() || button == l.getButtonAll1()) {
			if (c.isSkilling[FLETCHING] == true) {
				return;
			}
			if (c.playerLevel[FLETCHING] < l.getReq1()) {
				c.sendMessage("You must have a fletching level of at least "
						+ l.getReq1() + " to do this.");
				return;
			}
			//c.setAnimation(Animation.create(ANIMATION));
			c.startAnimation(ANIMATION);
			c.getPA().closeAllWindows();
			c.isSkilling[FLETCHING] = true;
			c.doAmount = c.fletchAmount(button);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c.doAmount == 0) {
						container.stop();
						return;
					}
					if (c.isSkilling[FLETCHING] == false) {
						container.stop();
						return;
					}
					if (!c.getItems().playerHasItem(KNIFE)
							|| !c.getItems().playerHasItem(c.logID)) {
						c.sendMessage("You do not have the correct supplies to fletch this.");
						container.stop();
						return;
					}
					//c.setAnimation(Animation.create(ANIMATION));
					c.startAnimation(ANIMATION);
					c.getItems().deleteItem(c.logID, 1);
					c.getItems().addItem(l.getBow1(), 1);
					if (c.isPVPActive == true) {
						c.getPA().addSkillXP(l.getXP4(), FLETCHING);
					} else {
						c.getPA().addSkillXP(l.getXP1(), FLETCHING);
					}
					c.doAmount--;
				}

				@Override
				public void stop() {
					c.getPA().closeAllWindows();
					c.logID = -1;
					//c.setAnimation(Animation.create(c.playerStandIndex));
					c.startAnimation(c.playerStandIndex);
					c.doAmount = 0;
					c.isSkilling[FLETCHING] = false;
				}
			}, 4);
		} else {
			return;
		}
	}

	public static void fletchUnstrungBow(final Player c, final int button) {
		final Bow b = Bow.itemID(c.logID);
		if (b == null) {
			return;
		}
		if (button == b.getButton1() || button == b.getButton5()
				|| button == b.getButton10() || button == b.getButtonAll()) {
			if (c.isSkilling[FLETCHING] == true) {
				return;
			}
			if (c.playerLevel[FLETCHING] < b.getReq()) {
				c.sendMessage("You must have a fletching level of at least "
						+ b.getReq() + " to do this.");
				return;
			}
			final int is = c.logID;
			//c.setAnimation(Animation.create(ANIMATION));
			c.startAnimation(ANIMATION);
			c.getPA().closeAllWindows();
			c.isSkilling[FLETCHING] = true;
			c.doAmount = c.fletchAmount(button);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c.doAmount == 0) {
						container.stop();
						return;
					}
					if (is != c.logID) {
						container.stop();
						return;
					}
					if (c.isSkilling[FLETCHING] == false) {
						container.stop();
						return;
					}
					if (!c.getItems().playerHasItem(KNIFE)
							|| !c.getItems().playerHasItem(c.logID)) {
						c.sendMessage("You do not have the correct supplies to fletch this.");
						container.stop();
						return;
					}
					//c.setAnimation(Animation.create(ANIMATION));
					c.startAnimation(ANIMATION);
					c.getItems().deleteItem(c.logID, 1);
					c.getItems().addItem(b.getBow(), 1);
					if (c.isPVPActive == true) {
						c.getPA().addSkillXP(b.getXP() * 2, FLETCHING);
					} else {
						c.getPA().addSkillXP(b.getXP(), FLETCHING);
					}
					c.doAmount--;
				}

				@Override
				public void stop() {
					c.getPA().closeAllWindows();
					c.logID = -1;
					//c.setAnimation(Animation.create(c.playerStandIndex));
					c.startAnimation(c.playerStandIndex);
					c.doAmount = 0;
					c.isSkilling[FLETCHING] = false;
				}
			}, 4);
		} else {
			return;
		}
	}

	/**
	 * Handles The Fletching Interface
	 * 
	 * @param c
	 *            Client
	 * @param useWith
	 *            First Item Used
	 * @param itemUsed
	 *            Item which is used
	 */

	public static void handleInterface(final Player c, final int useWith,
			final int itemUsed) {
		if (useWith == KNIFE && itemUsed == 1511 || useWith == 1511
				&& itemUsed == KNIFE) {
			openFletching(c, 1511);
			c.logID = 1511;
			return;
		} else if (useWith == KNIFE && itemUsed == 1521 || useWith == 1521
				&& itemUsed == KNIFE) {
			openFletching1(c, 1521);
			c.logID = 1521;
			return;
		} else if (useWith == KNIFE && itemUsed == 1519 || useWith == 1519
				&& itemUsed == KNIFE) {
			openFletching3(c, 1519);
			c.logID = 1519;
			return;
		} else if (useWith == KNIFE && itemUsed == 1517 || useWith == 1517
				&& itemUsed == KNIFE) {
			openFletching4(c, 1517);
			c.logID = 1517;
			return;
		} else if (useWith == KNIFE && itemUsed == 1515 || useWith == 1515
				&& itemUsed == KNIFE) {
			openFletching2(c, 1515);
			c.logID = 1515;
			return;
		} else if (useWith == KNIFE && itemUsed == 1513 || useWith == 1513
				&& itemUsed == KNIFE) {
			openFletching5(c, 1513);
			c.logID = 1513;
			return;
		}
	}

	/**
	 * Handles Every Fletching Button
	 * 
	 * @param c
	 *            Client
	 * @param button
	 *            Button ID
	 */

	public static void handleFletchingButtons(final Player c, final int button) {
		if (c.isSkilling[FLETCHING] == true) {
			return;
		}
		if (!c.getItems().playerHasItem(KNIFE)
				&& !c.getItems().playerHasItem(c.logID)) {
			return;
		}
		if (button >= 34214 && button <= 34217) {
			fletchShaft(c, button);
			return;
		}
		if (button <= 34185 && button >= 34181 || button <= 34170
				&& button >= 34167 || button >= 34202 && button <= 34205) {
			fletchUnstrungBow(c, button);
			return;
		}
		if (button <= 34189 && button >= 34186 || button <= 34174
				&& button >= 34171 || button >= 34206 && button <= 34209) {
			fletchUnstrungLongbow(c, button);
			return;
		}
		if (button <= 34213 && button >= 34210 || button <= 34193
				&& button >= 34190) {
			fletchCrossbow(c, button);
			return;
		}
	}

	/**
	 * Fletches Arrow Shafts
	 * 
	 * @param c
	 *            Client
	 * @param button
	 *            Button ID
	 */

	private static void fletchShaft(final Player c, final int button) {
		int item = -1;
		switch (button) {
		case 34217:
			if (System.currentTimeMillis() - c.lastThieve < 750) {
				return;
			}
			c.lastThieve = System.currentTimeMillis();
			if (c.getItems().playerHasItem(c.logID)) {
				item = c.logID;
			}
			if (c.getItems().playerHasItem(KNIFE)
					&& c.getItems().playerHasItem(item)) {
				//c.setAnimation(Animation.create(ANIMATION));
				c.startAnimation(ANIMATION);
				c.getItems().deleteItem(item, 1);
				c.getItems().addItem(52, AMOUNT);
				if (c.isPVPActive == true) {
					c.getPA().addSkillXP(175, FLETCHING);
				} else {
					c.getPA().addSkillXP(100, FLETCHING);
				}
				c.getPA().closeAllWindows();
				c.logID = -1;
			} else {
				c.sendMessage("You do not have the correct supplies to fletch this.");
				return;
			}
			break;
		case 34216:
		case 34215:
		case 34214:
			if (c.isSkilling[FLETCHING] == true) {
				return;
			}
			//c.setAnimation(Animation.create(ANIMATION));
			c.startAnimation(ANIMATION);
			c.getPA().closeAllWindows();
			c.isSkilling[FLETCHING] = true;
			c.doAmount = c.fletchAmount(button);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c.doAmount == 0) {
						container.stop();
						return;
					}
					if (c.isSkilling[FLETCHING] == false) {
						container.stop();
						return;
					}
					if (!c.getItems().playerHasItem(KNIFE)
							|| !c.getItems().playerHasItem(c.logID)) {
						c.sendMessage("You do not have the correct supplies to fletch this.");
						container.stop();
						return;
					}
					//c.setAnimation(Animation.create(ANIMATION));
					c.startAnimation(ANIMATION);
					c.getItems().deleteItem(c.logID, 1);
					c.getItems().addItem(52, AMOUNT);
					if (c.isPVPActive == true) {
						c.getPA().addSkillXP(200, FLETCHING);
					} else {
						c.getPA().addSkillXP(100, FLETCHING);
					}

					c.doAmount--;
				}

				@Override
				public void stop() {
					c.getPA().closeAllWindows();
					c.logID = -1;
					//c.setAnimation(Animation.create(c.playerStandIndex));
					c.startAnimation(c.playerStandIndex);
					c.doAmount = 0;
					c.isSkilling[FLETCHING] = false;
				}
			}, 4);
			break;
		}
	}

	/**
	 * Handles the actual interface
	 * 
	 * @param c
	 *            Client
	 * @param item
	 *            Log
	 */

	private static void openFletching(final Player c, final int item) {
		if (item == 1511) {
			c.getPA().sendFrame164(8899);
			c.getPA().sendFrame126("What would you like to make?", 8879);
			c.getPA().sendFrame246(8902, 250, 841);
			c.getPA().sendFrame246(8903, 250, 839);
			c.getPA().sendFrame246(8904, 250, 9440);
			c.getPA().sendFrame246(8905, 250, 52);
			c.getPA().sendFrame126("Shortbow", 8906);
			c.getPA().sendFrame126("Longbow", 8910);
			c.getPA().sendFrame126("Wooden Stock", 8914);
			c.getPA().sendFrame126("Arrow Shafts", 8918);
			c.logID = item;
		} else {
			return;
		}
	}

	private static void openFletching1(final Player c, final int item) {
		if (item == 1521) {
			c.getPA().sendFrame164(8880);
			c.getPA().sendFrame126("What would you like to make?", 8879);
			c.getPA().sendFrame246(8884, 250, 845);
			c.getPA().sendFrame246(8883, 250, 843);
			c.getPA().sendFrame246(8885, 250, 9442);
			c.getPA().sendFrame126("Shortbow", 8889);
			c.getPA().sendFrame126("Longbow", 8893);
			c.getPA().sendFrame126("Crossbow Stock", 8897);
			c.logID = item;
		} else {
			return;
		}
	}

	private static void openFletching2(final Player c, final int item) {
		if (item == 1515) {
			c.getPA().sendFrame164(8880);
			c.getPA().sendFrame126("What would you like to make?", 8879);
			c.getPA().sendFrame246(8884, 250, 855);
			c.getPA().sendFrame246(8883, 250, 857);
			c.getPA().sendFrame246(8885, 250, 9444);
			c.getPA().sendFrame126("Shortbow", 8889);
			c.getPA().sendFrame126("Longbow", 8893);
			c.getPA().sendFrame126("Crossbow Stock", 8897);
			c.logID = item;
		} else {
			return;
		}
	}

	private static void openFletching3(final Player c, final int item) {
		if (item == 1519) {
			c.getPA().sendFrame164(8880);
			c.getPA().sendFrame126("What would you like to make?", 8879);
			c.getPA().sendFrame246(8884, 250, 847);
			c.getPA().sendFrame246(8883, 250, 849);
			c.getPA().sendFrame246(8885, 250, 9448);
			c.getPA().sendFrame126("Shortbow", 8889);
			c.getPA().sendFrame126("Longbow", 8893);
			c.getPA().sendFrame126("Crossbow Stock", 8897);
			c.logID = item;
		} else {
			return;
		}
	}

	private static void openFletching4(final Player c, final int item) {
		if (item == 1517) {
			c.getPA().sendFrame164(8880);
			c.getPA().sendFrame126("What would you like to make?", 8879);
			c.getPA().sendFrame246(8884, 250, 851);
			c.getPA().sendFrame246(8883, 250, 853);
			c.getPA().sendFrame246(8885, 250, 9452);
			c.getPA().sendFrame126("Shortbow", 8889);
			c.getPA().sendFrame126("Longbow", 8893);
			c.getPA().sendFrame126("Crossbow Stock", 8897);
			c.logID = item;
		} else {
			return;
		}
	}

	private static void openFletching5(final Player c, final int item) {
		if (item == 1513) {
			c.getPA().sendFrame164(8866);
			c.getPA().sendFrame126("What would you like to make?", 8879); // 79
			c.getPA().sendFrame246(8870, 250, 859);
			c.getPA().sendFrame246(8869, 250, 861);
			c.getPA().sendFrame126("Shortbow", 8874);
			c.getPA().sendFrame126("Longbow", 8878);
			c.logID = item;
		} else {
			return;
		}
	}

}