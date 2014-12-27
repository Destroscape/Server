package game.content;

import game.entity.player.Player;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class LoyaltyProgram {

	private final static String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);

	private final static SimpleTimeZone est = new SimpleTimeZone(-5 * 60 * 60 * 1000, ids[0]);

	private final static Calendar calendar = new GregorianCalendar(est);
	
	private final static int pointsToGive = 1;

	private final static int day = calendar.get(Calendar.DATE),
			month = calendar.get(Calendar.MONTH), 
			year = calendar.get(Calendar.YEAR);

	public static void saveCurrentDate(Player p) {
		if (isOnLoyaltyStreak(p)) {
			p.currentDay = day;
			p.currentMonth = month;
			p.currentYear = year;
			giveStreakReward(p);
		} else if (isDifferentDate(p)) {
			p.currentDay = day;
			p.currentMonth = month;
			p.currentYear = year;
			giveReward(p);
		} else {
			//c.sendMessage("<col=255>You've already received your daily loyalty reward.");
		}
	}
	
	private static boolean isOnLoyaltyStreak(Player p) {
		if (isDifferentDate(p) && p.currentDay == p.currentDay + 1) {
			return true;
		}
		return false;
	}

	private static boolean isDifferentDate(Player p) {
		if (p.currentDay != day || p.currentMonth != month
				|| p.currentYear != year) {
			return true;
		}
		return false;
	}
	
	private static void giveStreakReward(Player p) {
		p.loyaltyPoints += pointsToGive *2;
		p.sendMessage("<col=255>You received x2 Loyalty Points for playing 2 days in a row.");
	}

	private static void giveReward(Player p) {
		p.loyaltyPoints += pointsToGive;
		p.effectRestore = 2;
		p.sendMessage("<col=255>You've been awarded your daily loyalty points!");
	}
}
