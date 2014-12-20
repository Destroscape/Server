package game.shop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import engine.util.Misc;
import game.Config;
import game.entity.player.PlayerHandler;

@SuppressWarnings("resource")
public class ShopHandler {

	public static int MaxShops = 101;
	public static int MaxShopItems = 172;
	public static int MaxInShopItems = 40;
	public static int MaxShowDelay = 10;
	public static int MaxSpecShowDelay = 600;
	public static int TotalShops = 0;
	public static int[][] ShopItems = new int[ShopHandler.MaxShops][ShopHandler.MaxShopItems];
	public static int[][] ShopItemsN = new int[ShopHandler.MaxShops][ShopHandler.MaxShopItems];
	public static int[][] ShopItemsDelay = new int[ShopHandler.MaxShops][ShopHandler.MaxShopItems];
	public static int[][] ShopItemsSN = new int[ShopHandler.MaxShops][ShopHandler.MaxShopItems];
	public static int[] ShopItemsStandard = new int[ShopHandler.MaxShops];
	public static String[] ShopName = new String[ShopHandler.MaxShops];
	public static int[] ShopSModifier = new int[ShopHandler.MaxShops];
	public static int[] ShopBModifier = new int[ShopHandler.MaxShops];

	public static void shophandler() {
		Misc.println("Shop Handler class successfully loaded");
	}

	public ShopHandler() {
		for (int i = 0; i < ShopHandler.MaxShops; i++) {
			for (int j = 0; j < ShopHandler.MaxShopItems; j++) {
				ResetItem(i, j);
				ShopHandler.ShopItemsSN[i][j] = 0;
			}
			ShopHandler.ShopItemsStandard[i] = 0;
			ShopHandler.ShopSModifier[i] = 0;
			ShopHandler.ShopBModifier[i] = 0;
			ShopHandler.ShopName[i] = "";
		}
		ShopHandler.TotalShops = 0;
		loadShops("Shop Config.cfg");
	}

	public void DiscountItem(final int ShopID, final int ArrayID) {
		ShopHandler.ShopItemsN[ShopID][ArrayID] -= 1;
		if (ShopHandler.ShopItemsN[ShopID][ArrayID] <= 0) {
			ShopHandler.ShopItemsN[ShopID][ArrayID] = 0;
			ResetItem(ShopID, ArrayID);
		}
	}

	public boolean loadShops(final String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[ShopHandler.MaxShopItems * 2];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./Data/CFG/"
					+ FileName));
		} catch (final FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (final IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			final int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("shop")) {
					final int ShopID = Integer.parseInt(token3[0]);
					ShopHandler.ShopName[ShopID] = token3[1].replaceAll("_",
							" ");
					ShopHandler.ShopSModifier[ShopID] = Integer
							.parseInt(token3[2]);
					ShopHandler.ShopBModifier[ShopID] = Integer
							.parseInt(token3[3]);
					for (int i = 0; i < (token3.length - 4) / 2; i++) {
						if (token3[4 + i * 2] != null) {
							ShopHandler.ShopItems[ShopID][i] = Integer
									.parseInt(token3[4 + i * 2]) + 1;
							ShopHandler.ShopItemsN[ShopID][i] = Integer
									.parseInt(token3[5 + i * 2]);
							ShopHandler.ShopItemsSN[ShopID][i] = Integer
									.parseInt(token3[5 + i * 2]);
							ShopHandler.ShopItemsStandard[ShopID]++;
						} else {
							break;
						}
					}
					ShopHandler.TotalShops++;
				}
			} else {
				if (line.equals("[ENDOFSHOPLIST]")) {
					try {
						characterfile.close();
					} catch (final IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (final IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (final IOException ioexception) {
		}
		return false;
	}

	public void process() {
		boolean DidUpdate = false;
		for (int i = 1; i <= MaxShops - 1; i++) {
			for (int j = 0; j < ShopHandler.MaxShopItems; j++) {
				if (ShopHandler.ShopItems[i][j] > 0) {
					if (ShopHandler.ShopItemsDelay[i][j] >= ShopHandler.MaxShowDelay) {
						if (j <= ShopHandler.ShopItemsStandard[i]
								&& ShopHandler.ShopItemsN[i][j] <= ShopHandler.ShopItemsSN[i][j]) {
							if (ShopHandler.ShopItemsN[i][j] < ShopHandler.ShopItemsSN[i][j]) {
								ShopHandler.ShopItemsN[i][j] += 1;
								DidUpdate = true;
								ShopHandler.ShopItemsDelay[i][j] = 1;
								ShopHandler.ShopItemsDelay[i][j] = 0;
								DidUpdate = true;
							}
						} else if (ShopHandler.ShopItemsDelay[i][j] >= ShopHandler.MaxSpecShowDelay) {
							DiscountItem(i, j);
							ShopHandler.ShopItemsDelay[i][j] = 0;
							DidUpdate = true;
						}
					}
					ShopHandler.ShopItemsDelay[i][j]++;
				}
			}
			if (DidUpdate == true) {
				for (int k = 1; k < Config.MAX_PLAYERS; k++) {
					if (PlayerHandler.players[k] != null) {
						if (PlayerHandler.players[k].isShopping == true
								&& PlayerHandler.players[k].myShopId == i) {
							PlayerHandler.players[k].updateShop = true;
							DidUpdate = false;
							PlayerHandler.players[k].updateshop(i);
						}
					}
				}
				DidUpdate = false;
			}
		}
	}

	public void ResetItem(final int ShopID, final int ArrayID) {
		ShopHandler.ShopItems[ShopID][ArrayID] = 0;
		ShopHandler.ShopItemsN[ShopID][ArrayID] = 0;
		ShopHandler.ShopItemsDelay[ShopID][ArrayID] = 0;
	}

}
