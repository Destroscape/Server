import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/*
 * Project Insanity - Evolved v.3
 * Dexter.java
 */

public class Dexter {

	public static void main(final String[] args) {
		final Dexter dexter = new Dexter();
		// dexter.checkForFlag();
		dexter.checkBanks();
	}

	public void checkBanks() {
		try {
			final File dir = new File("characters");
			if (dir.exists()) {
				String read;
				final File files[] = dir.listFiles();
				for (final File loaded : files) {
					if (loaded.getName().endsWith(".txt")) {
						final Scanner s = new Scanner(loaded);
						int cash = 0;
						while (s.hasNextLine()) {
							read = s.nextLine();
							if (read.startsWith("character-item")
									|| read.startsWith("character-bank")) {
								final String[] temp = read.split("\t");
								final int token1 = Integer.parseInt(temp[1]);
								final int token2 = Integer.parseInt(temp[2]);
								if (token1 == 996) {
									cash += token2;
									if (cash > 12500000) {
										System.out.println("name: "
												+ loaded.getName());
									}
								}
							}
						}
					}
				}
			} else {
				System.out.println("FAIL");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void checkForFlag() {
		try {
			final File dir = new File("characters");
			if (dir.exists()) {
				String read;
				final File files[] = dir.listFiles();
				for (final File loaded : files) {
					if (loaded.getName().endsWith(".txt")) {
						final Scanner s = new Scanner(loaded);
						while (s.hasNextLine()) {
							read = s.nextLine();
							if (read.equalsIgnoreCase("flagged = true")) {
								System.out.println(loaded.getName());
								break;
							}
						}
					}
				}
			}
		} catch (final Exception e) {
		}
	}

	public void logFile(final String name) {
		try {
			final FileWriter fw = new FileWriter("dupers.txt");
			fw.write(name + "\r\n");
			fw.close();
		} catch (final Exception e) {

		}
	}

}
