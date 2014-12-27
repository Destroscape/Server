package game.skill.agility;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;
import game.skill.SkillHandler;

public class BarbarianCourse extends SkillHandler {
	
	/**
	 * Main method
	 */
	
	public static void handleObject(int objectId, Player p) {
		if (!isObstacle(objectId))
			return;
		switch (objectId) {
		
		case 2282:
			handleRope(p);
		break;
		
		case 2294:
			handleLog(p);
			break;
			
		case 20211:
			handleNet(p);
			break;
			
		case 2302:
			handleLedge(p);
			break;
		
		case 3205:
			handleStairs(p);
			break;
			
		case 1948:
			handleWall(p);
			break;
			
		}
	}

	/**
	 * Restores the player details after doing the obstacle
	 */
	
	public static void resetAgilityWalk(final Player c) {
		c.isRunning2 = true;
		c.getPA().sendFrame36(173, 1);
		c.playerWalkIndex = 0x333;
		c.getPA().requestUpdates();
	}
	
	/**
	 * Moves the player to a coordinate with a asigned animation
	 */
	
	private static void specialMove(final Player c, final int walkAnimation,
			final int x, final int y) {
		c.isRunning2 = false;
		c.getPA().sendFrame36(173, 0);
		c.playerWalkIndex = walkAnimation;
		c.getPA().requestUpdates();
		c.getPA().walkTo(x, y);
	}
	
	/**
	 * Checks if its a obstacle
	 */
	
	public static boolean isObstacle(int i) {
		switch (i) {
		case 2282: //rope
		case 2294: //log
		case 20211: //net
		case 2302: //ledge
		case 3205: //stairs
		case 1948: //wall
			return true;
		}
		return false;
	}

	/**
	 * Checks if the player has passed all obstacles
	 */
	
	public static boolean isFinished(Player p) {
		if (p.finishedBarbRope && p.finishedBarbLog && p.finishedBarbNet && p.finishedBarbStairs && p.finishedBarbWall1 && p.finishedBarbWall2 && p.finishedBarbWall3) {
			return true;
		}
		return false;
	}
	
	/**
	 * Obstacle methods
	 */
	
	public static void handleRope(final Player p) {
		if (p.absY >= 3554) {
		p.doingAgility = true;
		p.getPA().startTeleport(2551, 3549, 0, "agility");
		resetAgilityWalk(p);
		p.getPA().addSkillXP((int) 10 * AGILITY_XP,
				p.playerAgility);
		p.finishedBarbRope = true;
		p.doingAgility = false;
		}
	}
	
	public static void handleLog(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 34) {
			p.sendMessage("You need 35 Agility to do this course.");
			return;
		}
			p.doingAgility = true;
			specialMove(p, 762, -10, 0);
			CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (p.absX == 2541) {
						container.stop();
					}
				}

				@Override
				public void stop() {
					resetAgilityWalk(p);
					p.getPA().addSkillXP(
							(int) 15 * AGILITY_XP,
							p.playerAgility);
					p.finishedBarbLog = true;
					p.doingAgility = false;
				}
			}, 1);
		}
	
	public static void handleNet(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 34) {
			p.sendMessage("You need 35 Agility to do this course.");
			return;
		}
		//p.setAnimation(Animation.create(828));
		p.startAnimation(828);
		p.getPA().movePlayer(2538, p.absY, 1);
		p.getPA().addSkillXP((int) 10 * AGILITY_XP,
				p.playerAgility);
		p.finishedBarbNet = true;
	}
	
	public static void handleLedge(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 34) {
			p.sendMessage("You need 35 Agility to do this course.");
			return;
		}
		if (p.absX >= 2536) {
			p.doingAgility = true;
			specialMove(p, 756, -4, 0);
			CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (p.absX == 2532 && p.absY == 3547) {
						container.stop();
					}
				}

				@Override
				public void stop() {
					resetAgilityWalk(p);
					p.getPA().addSkillXP(
							(int) 20 * AGILITY_XP,
							p.playerAgility);
					p.finishedBarbLedge = true;
					p.doingAgility = false;
				}
			}, 1);
		}
	}
	
	public static void handleStairs(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 34) {
			p.sendMessage("You need 35 Agility to do this course.");
			return;
		}
			//p.setAnimation(Animation.create(828));
			p.startAnimation(828);
			p.getPA().movePlayer(2532, 3546, 0);
			p.getPA().addSkillXP((int) 10 * AGILITY_XP,
					p.playerAgility);
			p.finishedBarbStairs = true;
	}
	
	public static void handleWall(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 34) {
			p.sendMessage("You need 35 Agility to do this course.");
			return;
		}
		if (p.absX != 2535 && p.absY != 3553) {
			p.getPA().walkTo(2535 - p.absX, 3553 - p.absY);
			return;
		}
		if (p.objectX == 2536) {
			p.doingAgility = true;
			specialMove(p, 839, +2, 0);
			CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (p.absX == 2537 && p.absY == 3553) {
						container.stop();
					}
				}

				@Override
				public void stop() {
					resetAgilityWalk(p);
					p.getPA().addSkillXP(
							(int) 15 * AGILITY_XP,
							p.playerAgility);
					p.finishedBarbWall1 = true;
					p.doingAgility = false;
				}
			}, 1);

		} else if (p.objectX == 2539) {
			if (p.playerLevel[p.playerAgility] <= 34) {
				p.sendMessage("You need 35 Agility to do this course.");
				return;
			}
			if (p.absX == 2539) {
				p.getPA().walkTo(2538 - p.absX, 3553 - p.absY);
				return;
			}
			if (p.absX != 2538 && p.absY != 3553) {
				p.getPA().walkTo(2538 - p.absX, 3553 - p.absY);
				return;
			}
			if (p.absX == 2538 && p.absY == 3553) {
				p.doingAgility = true;
				specialMove(p, 839, +2, 0);
				CycleEventHandler.getSingleton().addEvent(p,
						new CycleEvent() {
							@Override
							public void execute(
									CycleEventContainer container) {
								if (p.absX == 2540 && p.absY == 3553) {
									container.stop();
								}
							}

							@Override
							public void stop() {
								resetAgilityWalk(p);
								p.getPA()
										.addSkillXP(
												(int) 15
														* AGILITY_XP,
												p.playerAgility);
								p.finishedBarbWall2 = true;
								p.doingAgility = false;
							}
						}, 1);
			}
		} else if (p.objectX == 2542) {
			if (p.playerLevel[p.playerAgility] <= 34) {
				p.sendMessage("You need 35 Agility to do this course.");
				return;
			}
			if (p.absX == 2542) {
				p.getPA().walkTo(2541 - p.absX, 3553 - p.absY);
				return;
			}
			if (p.absX != 2541 && p.absY != 3553) {
				p.getPA().walkTo(2541 - p.absX, 3553 - p.absY);
				return;
			}
			if (p.absX == 2541 && p.absY == 3553) {
				p.doingAgility = true;
				specialMove(p, 839, +2, 0);
				CycleEventHandler.getSingleton().addEvent(p,
						new CycleEvent() {
							@Override
							public void execute(
									CycleEventContainer container) {
								if (p.absX == 2543 && p.absY == 3553) {
									container.stop();
								}
							}

							@Override
							public void stop() {
								p.finishedBarbWall3 = true;
								resetAgilityWalk(p);
								if (isFinished(p)) {
									p.getPA().addSkillXP(50000,
											p.playerAgility);
									p.sendMessage("You have completed the full barbarian agility course.");
									p.sendMessage("You have been rewarded with 50k Agility XP!");
								} else {
									p.getPA().addSkillXP(
											15 * AGILITY_XP,
											p.playerAgility);
								}
								p.finishedBarbRope = false;
								p.finishedBarbLog = false;
								p.finishedBarbNet = false;
								p.finishedBarbLedge = false;
								p.finishedBarbStairs = false;
								p.finishedBarbWall1 = false;
								p.finishedBarbWall2 = false;
								p.finishedBarbWall3 = false;
							}
						}, 1);
			}
		}
	}
	
}
