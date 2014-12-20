import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Project Insanity - Evolved v.3
 * ItemReplace.java
 */

public class ItemReplace {

	public static void main(final String[] args) {
		final ItemReplace ir = new ItemReplace();
		final File dir = new File("characters");
		if (dir.exists()) {
			final File files[] = dir.listFiles();
			for (final File loaded : files) {
				if (loaded.getName().endsWith(".txt")) {
					ir.handleCharacter(loaded);
				}
			}
		}
	}

	public int toReplace = 9010;
	public int altRemove = 9011;
	public int replaceWith = 995;
	public int altReplace = 996;
	public int replaceAmount = 700;

	public int getLineCount(final Scanner s) {
		int count = 0;
		while (s.hasNextLine()) {
			s.nextLine();
			count++;
		}
		return count;
	}

	public void handleCharacter(final File f) {
		try {
			Scanner s = new Scanner(f);
			final String[] contents = new String[getLineCount(s)];
			for (int j = 0; j < contents.length; j++) {
				String temp = s.nextLine();
				if (temp != "") {
					if (temp.split("\t").length > 2) {
						if (temp.contains("item") || temp.contains("bank")) {
							final String[] items = temp.split("\t");
							if (Integer.parseInt(items[1]) == altRemove) {
								items[1] = "" + altReplace;
								items[2] = "" + Integer.parseInt(items[2])
										* 700;
							}
							temp = items[0] + "\t" + items[1] + "\t" + items[2];
						} else if (temp.contains("character-equip = 13")) {
							final String[] items = temp.split("\t");
							if (Integer.parseInt(items[1]) == toReplace) {
								items[1] = "" + replaceWith;
								items[2] = "" + Integer.parseInt(items[2])
										* 700;
							}
							temp = items[0] + "\t" + items[1] + "\t" + items[2];
						}
					}
				}
				contents[j] = temp;
			}
			final FileWriter fw = new FileWriter(f);
			for (final String content : contents) {
				fw.write(content + "\r\n");
			}
			fw.close();
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
