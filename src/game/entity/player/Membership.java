package game.entity.player;

import game.entity.player.Player;
import game.entity.player.PlayerSave;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Membership {

	public Player c;

	public Membership(Player c) {
		this.c = c;
	}

	private int getTodayDate(Player c) {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		return (month * 100 + day);
	}

	public void giveMembership(Player c) {
		c.startDate = getTodayDate(c);
		c.membership = true;
		c.playerRights = 4;
		PlayerSave.saveGame(c);
		c.sendMessage("@blu@You have just recieved a month membership!");
		c.sendMessage("@blu@You are now a member. Please relog safely for your membership to take place.");
	}

	public void checkDate(Player c) {
		if(c.membership && c.startDate <= 0) {
			c.startDate = getTodayDate(c);
			PlayerSave.saveGame(c);
			c.sendMessage("Date Given.");
		} else if(getDaysLeft(c) <= 0) {
			c.membership = false;
			c.playerRights = 0;
			c.startDate = 0;
			PlayerSave.saveGame(c);
			c.sendMessage("@red@Your membership has expired!");
		}
	}

	public int getDaysLeft(Player c) {
		return (31 - (getTodayDate(c) - c.startDate));
	}
}