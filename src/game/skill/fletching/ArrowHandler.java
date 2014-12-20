package game.skill.fletching;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;
import game.skill.SkillHandler;

public class ArrowHandler extends SkillHandler {

	/**
	 * Variable Declarations
	 */

	private final static int HEADLESS = 53;

	protected static enum Arrow {

		HEADLESS_ARROW(314, 52, 53, 1, 15, false), BRONZE(39, HEADLESS, 882, 1,
				40, false), IRON(40, HEADLESS, 884, 15, 58, false), STEEL(41,
						HEADLESS, 886, 30, 95, false), MITH(42, HEADLESS, 888, 45, 133,
								false), ADDY(43, HEADLESS, 890, 60, 150, false), RUNE(44,
										HEADLESS, 892, 75, 207, false), DRAGON(11237, HEADLESS, 11212,
												90, 245, false), SAPH(9189, 9142, 9240, 56, 47, false), EMERALD(
														9190, 9142, 9241, 58, 55, false), RUBY(9191, 9143, 9242, 63,
																70, false), DIAMOND(9192, 9143, 9243, 65, 70, false), DRAGONSTONE(
																		9193, 9144, 9244, 71, 82, false), ONYX(9194, 9144, 9245, 73,
																				94, false),
																				/* CrossBows */
																				BRONZE_CB(9440, 9420, 9454, 9, 12, true), IRON_CB(9442, 9423, 9457, 39,
																						44, true), STEEL_CB(9444, 9425, 9459, 46, 54, true), MITH_CB(
																								9448, 9427, 9461, 54, 64, true), ADDY_CB(9450, 9429, 9463, 54,
																										82, true), RUNE_CB(9452, 9431, 9465, 69, 100, true);

		protected int firstItem, arrow, secondItem, req, XP;
		protected boolean isC;

		private Arrow(final int secondItem, final int firstItem,
				final int arrow, final int req, final int XP, final boolean isC) {
			this.firstItem = firstItem;
			this.arrow = arrow;
			this.secondItem = secondItem;
			this.req = req;
			this.XP = XP;
			this.isC = isC;
		}

		protected int getFirstItem() {
			return firstItem;
		}

		protected int getSecondItem() {
			return secondItem;
		}

		protected int getArrow() {
			return arrow;
		}

		protected int getReq() {
			return req;
		}

		protected int getXP() {
			return XP * FLETCHING_XP;
		}
		
		protected int getXP2() {
			return XP * 70;
		}

		protected boolean getC() {
			return isC;
		}

		protected static Arrow getID(final int ID) {
			for (Arrow a : Arrow.values()) {
				if (a.getSecondItem() == ID) {
					return a;
				}
			}
			return null;
		}
	}

	public static void fletchArrow(final Player c, final int amount) {
		int time = 0;
		final Arrow a = Arrow.getID(c.currentArrow);
		if (c.isSkilling[BowHandler.FLETCHING] == true) {
			c.getPA().closeAllWindows();
			return;
		}
		if (c.playerLevel[BowHandler.FLETCHING] < a.getReq()) {
			c.sendMessage("You must have a fletching level of at least "
					+ a.getReq() + " to do this.");
			c.getPA().closeAllWindows();
			return;
		}
		if (a.getC()) {
			if (!c.getItems().playerHasItem(a.getSecondItem(), 1)
					|| !c.getItems().playerHasItem(a.getFirstItem(), 1)) {
				c.sendMessage("You do not have the correct supplies.");
				return;
			}
			//c.setAnimation(Animation.create(6675));
			c.startAnimation(6675);
			time = 4;
		} else {
			if (!c.getItems().playerHasItem(a.getSecondItem(), 15)
					|| !c.getItems().playerHasItem(a.getFirstItem(), 15)) {
				c.sendMessage("You must have 15 of each supply to fletch these.");
				return;
			}
			//c.setAnimation(Animation.create(BowHandler.ANIMATION));
			c.startAnimation(BowHandler.ANIMATION);
			time = 2;
		}
		c.getPA().closeAllWindows();
		c.isSkilling[BowHandler.FLETCHING] = true;
		c.doAmount = amount;
		c.isArrowing = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.doAmount == 0) {
					container.stop();
					return;
				}
				if (c.isSkilling[BowHandler.FLETCHING] == false) {
					container.stop();
					return;
				}
				if (a.getC()) {
					if (!c.getItems().playerHasItem(a.getSecondItem(), 1)
							|| !c.getItems().playerHasItem(a.getFirstItem(), 1)) {
						c.sendMessage("You do not have the correct supplies.");
						container.stop();
						return;
					}
					//c.setAnimation(Animation.create(6675));
					c.startAnimation(6675);
					c.getItems().deleteItem2(a.getFirstItem(), 1);
					c.getItems().deleteItem2(a.getSecondItem(), 1);
					c.getItems().addItem(a.getArrow(), 1);
				} else {
					if (!c.getItems().playerHasItem(a.getSecondItem(), 15)
							|| !c.getItems()
							.playerHasItem(a.getFirstItem(), 15)) {
						c.sendMessage("You must have 15 of each supply to fletch these.");
						container.stop();
						return;
					}
					//c.setAnimation(Animation.create(BowHandler.ANIMATION));
					c.startAnimation(BowHandler.ANIMATION);
					c.getItems().deleteItem2(a.getFirstItem(), 15);
					c.getItems().deleteItem2(a.getSecondItem(), 15);
					c.getItems().addItem(a.getArrow(), 15);
				}
				if (c.isPVPActive == true) {
					c.getPA().addSkillXP(a.getXP2(), BowHandler.FLETCHING);
				} else {
					c.getPA().addSkillXP(a.getXP(), BowHandler.FLETCHING);
				}
				c.doAmount--;
			}

			@Override
			public void stop() {
				c.getPA().closeAllWindows();
				//c.setAnimation(Animation.create(c.playerStandIndex));
				c.startAnimation(c.playerStandIndex);
				c.doAmount = 0;
				c.isArrowing = false;
				c.isOnInterface = false;
				c.isSkilling[BowHandler.FLETCHING] = false;
			}
		}, time);
	}

	/**
	 * Handles the Interface
	 * 
	 * @param c
	 *            Client
	 * @param arrow
	 */

	protected static void handleInterface1(final Player c, final int arrow) {
		final Arrow a = Arrow.getID(arrow);
		if (c.isSkilling[BowHandler.FLETCHING] == true) {
			return;
		}
		c.currentArrow = arrow;
		c.getPA().sendFrame164(4429);
		c.isOnInterface = true;
		c.isArrowing = true;
		c.stringu = a.getFirstItem();
		boolean view190 = true;
		c.getPA().sendFrame246(1746, view190 ? 140 : 140, a.getArrow());
		c.getPA().sendFrame126(
				StringingHandler.getLine(c) + ""
						+ c.getItems().getItemName(a.getArrow()) + "", 2799);
	}

}
