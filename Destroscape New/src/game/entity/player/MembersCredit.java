package game.entity.player;

import java.util.Calendar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import game.Server;

/**
* Source Eudora-World
* Members class
* @author Aintaro
*/

public class MembersCredit {

	private Player p;
	
	public MembersCredit(Player Player) {
		this.p = Player;
	}
	
	private int calculateDays, calculateMonths, calculateYears;
	private static final int TOTAL_YEAR = 365;
	private static final int TOTAL_MONTH = 30;
	
	/**
	* stores the information when a player last logged in.
	*/
	
	public void lastMemberLogin() {
		Calendar C = Calendar.getInstance();
		p.lastLogin[0] = C.get(Calendar.DAY_OF_MONTH);
		p.lastLogin[1] = C.get(Calendar.MONTH);
		p.lastLogin[2] = C.get(Calendar.YEAR);
	}
	
	/**
	* Not perfect , but it works.
	*/
	
	private void lastLoggedIn() {
		int lastTimeLoggedIn;
		lastTimeLoggedIn = calculateDays + calculateMonths + calculateYears;
			p.sendMessage("You last logged in " + lastTimeLoggedIn + " Days ago.");
	}
	
	public void lastIpLoggedIn(String ip) {
		p.sendMessage("You are currently logged in from : " + ip); // current ip?
		p.sendMessage("You last logged in from : " + p.lastIp); // last ip ?
		p.lastIp = ip;
	}
	
	/**
	* Calculates the days last logged in
	*/
	
	private void memberShipDays() {
		Calendar C = Calendar.getInstance();
		if (p.lastLogin[0] > C.get(Calendar.DAY_OF_MONTH)) {
			calculateDays = p.lastLogin[0] - C.get(Calendar.DAY_OF_MONTH);
			p.memberShipDays -= calculateDays;
		} else {
			calculateDays = p.lastLogin[0] - C.get(Calendar.DAY_OF_MONTH);
			p.memberShipDays += calculateDays;
		}
	}
	
	/**
	* Calculates the months last logged in.
	*/
	
	private void memberShipMonths() {
		Calendar C = Calendar.getInstance();
		if (p.lastLogin[1] > C.get(Calendar.MONTH)) {
			calculateMonths = (p.lastLogin[1] - C.get(Calendar.MONTH)) * TOTAL_MONTH;
			p.memberShipDays -= calculateMonths;
		} else {
			calculateMonths = (p.lastLogin[1] - C.get(Calendar.MONTH)) * TOTAL_MONTH;
			p.memberShipDays += calculateMonths;
		}
	}
	
	/**
	* Calculates the Years last logged in.
	* resetMemeberShip method makes it so players never get a negative number displayed.
	*/
	private void memberShipYears() {
		Calendar C = Calendar.getInstance();
		if (p.lastLogin[1] > C.get(Calendar.YEAR)) {
			calculateYears = (p.lastLogin[2] - C.get(Calendar.YEAR)) * TOTAL_YEAR;
			p.memberShipDays -= calculateYears;
			resetMemberShip();
			p.sendMessage("You have " + p.memberShipDays +  " Days of member credit left.");
		} else {
			calculateYears = (p.lastLogin[2] - C.get(Calendar.YEAR)) * TOTAL_YEAR;
			p.memberShipDays += calculateYears;
			resetMemberShip();
			p.sendMessage("You have " + p.memberShipDays +  " Days of member credit left.");
		}
	}
	/**
	* If the player logs in the same day.
	*/
	private void memberShipSameDay() {
		Calendar C = Calendar.getInstance();
		if (p.lastLogin[0] == C.get(Calendar.DAY_OF_MONTH) && p.lastLogin[1] == C.get(Calendar.MONTH) && p.lastLogin[2] == C.get(Calendar.YEAR)) {
			p.sendMessage("Welcome back, You have " + p.memberShipDays + " Days of member credit left.");
		}
	}
	
	/**
	* Checks how many days member credit a player got left.
	*/
	private void memberShip() {
		int calculateDays, calculateMonths, calculateYears;
		Calendar C = Calendar.getInstance();
		resetMemberShip();
		try {
			memberShipDays();
			memberShipMonths();
			memberShipYears();
		} catch(Exception e) {
			System.out.println("Error in MembersCredit Class");
		}
	}
	
	/**
	* Resets the player Membership status.
	*/
	
	private void resetMemberShip() {
		if (p.memberShipDays <= 0) {
			p.memberShipDays = 0;
			p.membership = false;
			p.playerRights = 0;
			p.sendMessage("<col=255>It seems like a month has passed, you can renew your membership at www..com");
		}
	}
	
	/**
	* Checks if the player ismember.
	*/
	
	private void ismember(boolean donatorStatus) {
		if (donatorStatus) {
			memberShip();
			lastMemberLogin();
			
		}
	}
	
	/**
	* Has the player ever logged in Member Status before?
	* If not than it will first get the date for the player and than calculate Membership
	*/
	
	private void firstMemberLogin() {
		if (p.lastLogin[0] == 0) {
					lastMemberLogin();
					memberShip(); 
					lastLoggedIn();
		} else {
			ismember(true);
			lastLoggedIn();
		}
	}
	
	/**
	* This prints how many days they have left.
	*/
	
	public void printStatus(Player p) {
		switch(p.playerRights) {
			case 0:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;
			
			case 1:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;
			
			case 2:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;
			
			case 3:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;
			
			case 4:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;
			
			case 5:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;

			case 6:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;

			case 7:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;

			case 8:
				if (p.membership == true) {
					firstMemberLogin();
				} else {
					ismember(false);
				}
				break;
			default:
				System.out.println("Invalid playerRights[MembersCredit Class]");
				break;
		}
	}
}