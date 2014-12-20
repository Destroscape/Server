package engine.network;

import game.entity.player.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Project Insanity - Evolved v.3
 * Connection.java//
 */

public class Connection {

	public static ArrayList<String> bannedIps = new ArrayList<String>();
	public static ArrayList<String> bannedNames = new ArrayList<String>();
	public static ArrayList<String> mutedIps = new ArrayList<String>();
	public static ArrayList<String> mutedNames = new ArrayList<String>();
	public static ArrayList<String> loginLimitExceeded = new ArrayList<String>();
	public static ArrayList<String> starterRecieved1 = new ArrayList<String>();
	public static ArrayList<String> starterRecieved2 = new ArrayList<String>();

	/**
	 * Adding Ban IP
	 **/
	public static void addIpToBanList(final String IP) {
		Connection.bannedIps.add(IP);
	}

	/**
	 * Writes the IP into the text file - use ::ipban username
	 **/
	public static void addIpToFile(final String Name) {
		try {
			final BufferedWriter out = new BufferedWriter(new FileWriter(
					"./Data/bans/IpsBanned.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adding Name To List
	 */
	public static void addIpToLoginList(final String IP) {
		Connection.loginLimitExceeded.add(IP);
	}

	public static void addIpToMuteFile(final String Name) {
		try {
			final BufferedWriter out = new BufferedWriter(new FileWriter(
					"./Data/bans/IpsMuted.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void addIpToMuteList(final String IP) {
		Connection.mutedIps.add(IP);
		Connection.addIpToMuteFile(IP);
	}

	/**
	 * Adding banned username
	 **/
	public static void addNameToBanList(final String name) {
		Connection.bannedNames.add(name.toLowerCase());
	}

	/**
	 * Writes the username into the text file - when using the ::ban playername
	 * command
	 **/
	public static void addNameToFile(final String Name) {
		try {
			final BufferedWriter out = new BufferedWriter(new FileWriter(
					"./Data/bans/UsersBanned.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void addNameToMuteList(final String name) {
		Connection.mutedNames.add(name.toLowerCase());
		Connection.addUserToFile(name);
	}

	public static void addUserToFile(final String Name) {
		try {
			final BufferedWriter out = new BufferedWriter(new FileWriter(
					"./Data/bans/UsersMuted.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads all the Ips from text file then adds them all to ban list
	 **/
	public static void banIps() {
		try {
			final BufferedReader in = new BufferedReader(new FileReader(
					"./Data/bans/IpsBanned.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					Connection.addIpToBanList(data);
				}
			} finally {
				in.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads all usernames from text file then adds them all to the ban list
	 **/
	public static void banUsers() {
		try {
			final BufferedReader in = new BufferedReader(new FileReader(
					"./Data/bans/UsersBanned.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					Connection.addNameToBanList(data);
				}
			} finally {
				in.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkLoginList(final String IP) {
		Connection.loginLimitExceeded.add(IP);
		int num = 0;
		for (final String ips : Connection.loginLimitExceeded) {
			if (IP.equals(ips)) {
				num++;
			}
		}
		if (num > 5) {
			return true;
		}
		return false;
	}

	/**
	 * Clear Name List
	 */
	public static void clearLoginList() {
		Connection.loginLimitExceeded.clear();
	}

	public static void deleteFromFile(final String file, final String name) {
		try {
			final BufferedReader r = new BufferedReader(new FileReader(file));
			final ArrayList<String> contents = new ArrayList<String>();
			while (true) {
				String line = r.readLine();
				if (line == null) {
					break;
				} else {
					line = line.trim();
				}
				if (!line.equalsIgnoreCase(name)) {
					contents.add(line);
				}
			}
			r.close();
			final BufferedWriter w = new BufferedWriter(new FileWriter(file));
			for (final String line : contents) {
				w.write(line, 0, line.length());
				w.newLine();
			}
			w.flush();
			w.close();
		} catch (final Exception e) {
		}
	}

	/**
	 * Adds the banned usernames and ips from the text file to the ban list
	 **/
	public static void initialize() {
		Connection.appendStarters();
		Connection.appendStarters2();
		Connection.banUsers();
		Connection.banIps();
		Connection.muteUsers();
		Connection.muteIps();
	}

	/**
	 * 2 Starters
	 */

	public static void appendStarters() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./Data/Starters/FirstStarterRecieved.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					starterRecieved1.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void appendStarters2() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./Data/Starters/SecondStarterRecieved.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					starterRecieved2.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addIpToStarter1(String IP) {
		starterRecieved1.add(IP);
		addIpToStarterList1(IP);
	}

	public static void addIpToStarter2(String IP) {
		starterRecieved2.add(IP);
		addIpToStarterList2(IP);
	}

	public static void addIpToStarterList1(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"./Data/Starters/FirstStarterRecieved.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addIpToStarterList2(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"./Data/Starters/SecondStarterRecieved.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean hasRecieved1stStarter(String IP) {
		if (starterRecieved1.contains(IP)) {
			return true;
		}
		return false;
	}

	public static boolean hasRecieved2ndStarter(String IP) {
		if (starterRecieved2.contains(IP)) {
			return true;
		}
		return false;
	}

	/**
	 * Contains Ban IP
	 **/
	public static boolean isIpBanned(final String IP) {
		if (Connection.bannedIps.contains(IP)) {
			return true;
		}
		return false;
	}

	public static boolean isMuted(final Player c) {
		// return mutedNames.contains(c.playerName) ||
		// mutedIps.contains(c.connectedFrom);
		return false;
	}

	/**
	 * Contains banned username
	 **/
	public static boolean isNamedBanned(final String name) {
		if (Connection.bannedNames.contains(name.toLowerCase())) {
			return true;
		}
		return false;
	}

	public static void muteIps() {
		try {
			final BufferedReader in = new BufferedReader(new FileReader(
					"./Data/bans/IpsMuted.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					Connection.mutedIps.add(data);
				}
			} finally {
				in.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void muteUsers() {
		try {
			final BufferedReader in = new BufferedReader(new FileReader(
					"./Data/bans/UsersMuted.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					Connection.mutedNames.add(data);
				}
			} finally {
				in.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removing Ban IP
	 **/
	public static void removeIpFromBanList(final String IP) {
		Connection.bannedIps.remove(IP);
	}

	/**
	 * Remove Ip From List
	 */
	public static void removeIpFromLoginList(final String IP) {
		Connection.loginLimitExceeded.remove(IP);
	}

	/**
	 * Removing banned username
	 **/
	public static void removeNameFromBanList(final String name) {
		Connection.bannedNames.remove(name.toLowerCase());
		Connection.deleteFromFile("./Data/bans/UsersBanned.txt", name);
	}

	public static void removeNameFromMuteList(final String name) {
		Connection.bannedNames.remove(name.toLowerCase());
		Connection.deleteFromFile("./Data/bans/UsersMuted.txt", name);
	}

	public static void unIPMuteUser(final String name) {
		Connection.mutedIps.remove(name);
		Connection.deleteFromFile("./Data/bans/IpsMuted.txt", name);
	}

	public static void unMuteUser(final String name) {
		Connection.mutedNames.remove(name);
		Connection.deleteFromFile("./Data/bans/UsersMuted.txt", name);
	}

}
