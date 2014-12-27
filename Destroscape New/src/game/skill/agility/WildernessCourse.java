package game.skill.agility;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;
import game.skill.SkillHandler;

public class WildernessCourse extends SkillHandler {
	
	/**
	 * Main method
	 */
	
	public static void handleObject(int objectId, Player p) {
		if (!isObstacle(objectId))
			return;
		switch (objectId) {
		
		case 2288:
			handlePipe(p);
		break;
		
		case 2283:
			handleRope(p);
			break;
			
		case 14758:
			handleStone(p);
			break;
			
		case 2297:
			handleLog(p);
			break;
			
		case 2328:
			handleRocks(p);
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
		case 2288: //pipe
		case 2283: //rope
		case 14758: //stone
		case 2297: //log
		case 2328: //rocks
			return true;
		}
		return false;
	}

	/**
	 * Checks if the player has passed all obstacles
	 */
	
	public static boolean isFinished(Player p) {
		if (p.finishedWildPipe && p.finishedWildRope && p.finishedWildStone && p.finishedWildLog && p.finishedWildRocks) {
			return true;
		}
		return false;
	}
	
	/**
	 * Obstacle methods
	 */
	
	public static void handlePipe(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 38) {
			p.sendMessage("You need 49 Agility to do this course.");
			return;
		}
			p.doingAgility = true;
			specialMove(p, 844, 0, 13);
			CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (p.absY == 3950) {
						container.stop();
					}
				}

				@Override
				public void stop() {
					//p.setAnimation(Animation.create(844));
					p.startAnimation(844);
					resetAgilityWalk(p);
					p.finishedWildPipe = true;
					p.getPA().addSkillXP(15 * AGILITY_XP,
								p.playerAgility);
				}
			}, 1);	
	}
	
	public static void handleRope(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 38) {
			p.sendMessage("You need 49 Agility to do this course.");
			return;
		}
	if (p.absY >= 3953) {
		p.doingAgility = true;
		p.getPA().startTeleport(3005, 3958, 0, "agility");
		resetAgilityWalk(p);
		p.getPA().addSkillXP((int) 15 * AGILITY_XP,
				p.playerAgility);
		p.finishedWildRope = true;
		p.doingAgility = false;
		}
	}
	
	public static void handleStone(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 38) {
			p.sendMessage("You need 49 Agility to do this course.");
			return;
		}
		//p.setAnimation(Animation.create(828));
		p.startAnimation(828);
		p.getPA().movePlayer(2995, 3960, 0);
		p.getPA().addSkillXP((int) 15 * AGILITY_XP,
				p.playerAgility);
		p.finishedWildStone = true;
	}
	
	public static void handleLog(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 38) {
			p.sendMessage("You need 49 Agility to do this course.");
			return;
		}
		p.doingAgility = true;
			specialMove(p, 762, -7 , 0);
			CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (p.absX == 2995) {
						container.stop();
					}
				}

				@Override
				public void stop() {
					resetAgilityWalk(p);
					p.getPA().addSkillXP(
							(int) 20 * AGILITY_XP,
							p.playerAgility);
					p.finishedWildLog = true;
					p.doingAgility = false;
				}
			}, 1);
	}
	
	public static void handleRocks(final Player p) {
		if (p.playerLevel[p.playerAgility] <= 38) {
			p.sendMessage("You need 49 Agility to do this course.");
			return;
		}
		if (p.absY >= 3937) {
		p.getPA().movePlayer(2994, 3932, 0);
		p.finishedWildRocks = true;
		if (isFinished(p)) {
			p.getPA().addSkillXP(80000,
					p.playerAgility);
			p.sendMessage("You have completed the full wilderness agility course.");
			p.sendMessage("You have been rewarded with 80k Agility XP!");
		} else {
			p.getPA().addSkillXP(
					15 * AGILITY_XP,
					p.playerAgility);
		}
		p.finishedWildPipe = false;
		p.finishedWildRope = false;
		p.finishedWildStone = false;
		p.finishedWildLog = false;
		p.finishedWildRocks = false;
	}
	}
	
	
}
