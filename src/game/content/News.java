package game.content;

import game.Config;
import game.entity.player.Player;

public class News {
	
	/**
	 * Title of SWE Prime
	 */
	private static final String TITLE = "-SWE Prime News-";
	
	/**
	 * All lines for the newspaper
	 */
	private static final String[] NEWS = {
		""+TITLE,
	    " ",
	    " ",
	    "Most "+ Config.SERVER_NAME +" news will be available",
	    "On the forums but also some will be posted here!",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    "",
	    ""
	};
	
	/**
	 * Newspaper item ID
	 */
	public static final int NEWSPAPER = 11169;
	
	/**
	 * Displays the news
	 * @param c Client
	 */
	public static void displayNews(final Player c) {
		c.getQuest().clearQuestInterface();
	    for(int i = 0; i < NEWS.length; i++) {
	        c.getPA().sendFrame126(NEWS[i], 8144 + i);
	    }
	    c.getPA().showInterface(8134);
	}
	
	
}
