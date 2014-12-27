package game.content.achievement;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;
import game.entity.player.PlayerSave;

/**
 * 
 * @author 2012 : 18/08/2011
 * 
 */
//Import: import game.content.achievement.*;
//USAGE AchievementManager.increase(c, Achievements.);

public class AchievementManager {

	public static final int MAX_ACHIEVEMENTS = 100;

	private static final int[] REQUIRED_AMOUNT = { 100, 100, 500, 30, 70, 70, 70, 70, 200, 80, 60, 100, 2000, 1, 10, 10, 100, 40, 1000, 500, 50, 100, 800, 600, 400, 80, 70, 60};

	private static final int[] ACHIEVEMENT_POINTS = { 7, 7, 20, 5, 15, 15, 15, 15, 13, 45, 10, 30, 30, 10, 10, 20, 40, 35, 15, 10, 20, 20, 35, 15, 20, 15, 20, 25};

	private static final String[] ACHIEVEMENT_NAME = { "Muncher", "The Rock", "Pocketeer", "Not a Pocketeer", "GWD Warrior", "GWD Ranger", "GWD Darker", "GWD Queen", 
	"King Black King", "The Crystal Key", "Knight Keeper", "Gnome Runner", "The Killer", "XP Master", "Effigy Locator", "Effigy Inspector", "THE Slayer", "Nex Destroyer", "Alchemizer", "Lumberjack", "Birdie", "Dungeoneerer",
	"Shark Tamer", "Rocktail Tamer", "Lumberjacking", "Piece of cake", "No biggie", "Scroller"};



	private static final String[] ACHIEVEMENT_DES = { "Eat any food 100 Times",
			"Slay 100 Rock Crabs", "Pickpocket 500 Times", "Fail Pickpocket 30 Times", "Slay Bandos Boss 70 Times", "Slay Armadyl Boss 70 Times", 
			"Slay Zamorak Boss 70 Times", "Slay Saradomin Boss 70 Times", "Kill KBD 200 Times", "Open Crystal Chest 80 Times", "Win 60 Pest Control Games",
			"Run Gnome Course 100 Times", "Kill 2000 NPCs", "Achieve 200M XP in a skill", "Find 10 Effigiys", "Open 10 Effigys", "Finish 100 Slayer Tasks",
			"Slay Nex 40 Times", "High alch 1000 Times", "Chop 500 Logs", "Find 50 Birdsnests", "Complete 100 Dung Floors", "Catch 800 Sharks", "Catch 600 Rocktails", "Chop 400 Magic Logs", 
			"Finish 80 Easy Clues", "Finish 70 Medium Clues", "Finish 60 Hard Clues"};

	public static void increase(final Player c, int achievement) {
		c.achievement[achievement]++;
		if (c.achievement[achievement] == REQUIRED_AMOUNT[achievement]) {
			c.achieved[achievement] = true;
			c.sendMessage("<col=176>Congratulations! You've completed the achievement <col=225>"
					+ ACHIEVEMENT_NAME[achievement] + "!");
			c.achievementPoints += ACHIEVEMENT_POINTS[achievement];
			c.sendMessage("<col=176>You recieve "
					+ ACHIEVEMENT_POINTS[achievement]
					+ " points! <col=225>You now have the total of "
					+ c.achievementPoints + " achievement points.");
			c.getPA().sendFrame126(
					"You completed the achievement\\n"
							+ ACHIEVEMENT_NAME[achievement] + "!", 23136);
			c.completeAchievements += 1;
			c.hasAchieved = true;
			c.getPA().walkableInterface(23133);
			AchievementExtra.addExtra(c, achievement);
			PlayerSave.saveGame(c);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.hasAchieved = false;
					container.stop();
				}
				@Override
				public void stop() {

				}
			}, 5);
		}
	}

	public static void writeInterface(Player c) {
		for (int i = 0; i < ACHIEVEMENT_DES.length; i++) {
			c.getPA().sendFrame126(
					"@gre@" + (c.achieved[i] ? "" : "@red@") + "" + ACHIEVEMENT_DES[i]
							+ "", 23141 + i);
		}
		c.getPA().sendFrame126("You currently have\\n"+c.achievementPoints+" achievement points.", 23131);
		c.getPA().showInterface(23139);
	}

	/**
	 * Sends the achievement information
	 * 
	 * @param player
	 *            the player
	 * @param button
	 *            the button
	 */
	public static boolean sendInformation(Player player, int button) {
		/**
		 * Checks the button
		 */
		if (button >= 90101 && button <= 90200) {
			/**
			 * The achievement
			 */
			int achievement = button - 90101;
			/**
			 * Not available
			 */
			if (achievement > ACHIEVEMENT_NAME.length) {
				
					player.sendMessage("Achievement: <col=255>"
											+ ACHIEVEMENT_NAME[achievement]
											+ "</col> - <col=255>"
											+ ACHIEVEMENT_DES[achievement]
											+ "</col>. Currently at: <col=255>"
											+ player.achievement[achievement]);
			} else {
				if (player.achievement[achievement] == REQUIRED_AMOUNT[achievement]) {
					player.sendMessage(
							"Achievement: " + ACHIEVEMENT_NAME[achievement]
									+ " <col=255>completed!");
				} else {
					player.sendMessage("Achievement: <col=255>"
											+ ACHIEVEMENT_NAME[achievement]
											+ "</col> - <col=255>"
											+ ACHIEVEMENT_DES[achievement]
											+ "</col>. Currently at: <col=255>"
											+ player.achievement[achievement]);
				}
			}
			return true;
		}
		return false;
	}
}