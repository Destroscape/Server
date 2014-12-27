package engine.util;

import java.text.NumberFormat;

public class Misc {

	public static int getCurrentHP(int i, int i1, int i2) {
		double x = (double) i / (double) i1;
		return (int) Math.round(x * i2);
	}

	public static String insertCommasToNumber(String number) {
		return number.length() < 4 ? number : insertCommasToNumber(number
				.substring(0, number.length() - 3))
				+ ","
				+ number.substring(number.length() - 3, number.length());
	}

	public static final char playerNameXlateTable[] = { '_', 'a', 'b', 'c',
			'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
			'3', '4', '5', '6', '7', '8', '9', '[', ']', '/', '-', ' ' };

	public static char xlateTable[] = { ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n',
			's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 'b',
			'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-',
			'&', '*', '\\', '\'', '@', '#', '+', '=', '\243', '$', '%', '"',
			'[', ']', '>', '<', '_' };

	private static char decodeBuf[] = new char[4096];
	public static byte directionDeltaX[] = new byte[] { 0, 1, 1, 1, 0, -1, -1,
			-1 };
	public static byte directionDeltaY[] = new byte[] { 1, 1, 0, -1, -1, -1, 0,
			1 };
	public static byte xlateDirectionToClient[] = new byte[] { 1, 2, 4, 7, 6,
			5, 3, 0 };

	public static int direction(final int srcX, final int srcY, final int x,
			final int y) {
		final double dx = (double) x - srcX, dy = (double) y - srcY;
		double angle = Math.atan(dy / dx);
		angle = Math.toDegrees(angle);
		if (Double.isNaN(angle)) {
			return -1;
		}
		if (Math.signum(dx) < 0) {
			angle += 180.0;
		}
		return (int) (((90 - angle) / 22.5 + 16) % 16);
	}

	public static String basicEncrypt(String s) {
		String toReturn = "";
		for (int j = 0; j < s.length(); j++) {
			toReturn += (int)s.charAt(j);
		}
		//System.out.println("Encrypt: " + toReturn);
		return toReturn;	
	}

	public static String format(final int num) {
		return NumberFormat.getInstance().format(num);
	}

	public static String formatPlayerName(String str) {
		str = Misc.ucFirst(str);
		str.replace("_", " ");
		return str;
	}

	public static String Hex(final byte data[]) {
		return Misc.Hex(data, 0, data.length);
	}

	public static String Hex(final byte data[], final int offset, final int len) {
		String temp = "";
		for (int cntr = 0; cntr < len; cntr++) {
			final int num = data[offset + cntr] & 0xFF;
			String myStr;
			if (num < 16) {
				myStr = "0";
			} else {
				myStr = "";
			}
			temp += myStr + Integer.toHexString(num) + " ";
		}
		return temp.toUpperCase().trim();
	}

	public static int hexToInt(final byte data[], final int offset,
			final int len) {
		int temp = 0;
		int i = 1000;
		for (int cntr = 0; cntr < len; cntr++) {
			final int num = (data[offset + cntr] & 0xFF) * i;
			temp += num;
			if (i > 1) {
				i = i / 1000;
			}
		}
		return temp;
	}

	public static String longToPlayerName(long l) {
		int i = 0;
		final char ac[] = new char[12];

		while (l != 0L) {
			final long l1 = l;

			l /= 37L;
			ac[11 - i++] = Misc.xlateTable[(int) (l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}

	public static String longToPlayerName2(long l) {
		int i = 0;
		final char ac[] = new char[99];
		while (l != 0L) {
			final long l1 = l;
			l /= 37L;
			ac[11 - i++] = Misc.playerNameXlateTable[(int) (l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}

	public static String optimizeText(final String text) {
		final char buf[] = text.toCharArray();
		boolean endMarker = true;
		for (int i = 0; i < buf.length; i++) {
			final char c = buf[i];
			if (endMarker && c >= 'a' && c <= 'z') {
				buf[i] -= 0x20;
				endMarker = false;
			}
			if (c == '.' || c == '!' || c == '?') {
				endMarker = true;
			}
		}
		return new String(buf, 0, buf.length);
	}

	public static long playerNameToInt64(final String s) {
		long l = 0L;
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);
			l *= 37L;
			if (c >= 'A' && c <= 'Z') {
				l += 1 + c - 65;
			} else if (c >= 'a' && c <= 'z') {
				l += 1 + c - 97;
			} else if (c >= '0' && c <= '9') {
				l += 27 + c - 48;
			}
		}
		while (l % 37L == 0L && l != 0L) {
			l /= 37L;
		}
		return l;
	}

	public static void print(final String str) {
		System.out.print(str);
	}

	public static void print_debug(final String str) {
		System.out.print(str);
	}

	public static void println(final String str) {
		System.out.println(str);
	}

	public static void println_debug(final String str) {
		System.out.println(str);
	}

	public static int random(final int range) {
		return (int) (java.lang.Math.random() * (range + 1));
	}

	public static int random2(final int range2) {
		return (int) (java.lang.Math.random() * range2 + 1);
	}

	public static int random3(final int range3) {
		return (int) (java.lang.Math.random() * range3 + 1);
	}

	public static int random4(final int range4) {
		return (int) (java.lang.Math.random() * range4 + 1);
	}

	public static int random5(final int range5) {
		return (int) (java.lang.Math.random() * range5 + 1);
	}

	public static void textPack(final byte packedData[], java.lang.String text) {
		if (text.length() > 80) {
			text = text.substring(0, 80);
		}
		text = text.toLowerCase();
		int carryOverNibble = -1;
		int ofs = 0;
		for (int idx = 0; idx < text.length(); idx++) {
			final char c = text.charAt(idx);
			int tableIdx = 0;
			for (int i = 0; i < Misc.xlateTable.length; i++) {
				if (c == Misc.xlateTable[i]) {
					tableIdx = i;
					break;
				}
			}
			if (tableIdx > 12) {
				tableIdx += 195;
			}
			if (carryOverNibble == -1) {
				if (tableIdx < 13) {
					carryOverNibble = tableIdx;
				} else {
					packedData[ofs++] = (byte) tableIdx;
				}
			} else if (tableIdx < 13) {
				packedData[ofs++] = (byte) ((carryOverNibble << 4) + tableIdx);
				carryOverNibble = -1;
			} else {
				packedData[ofs++] = (byte) ((carryOverNibble << 4) + (tableIdx >> 4));
				carryOverNibble = tableIdx & 0xf;
			}
		}
		if (carryOverNibble != -1) {
			packedData[ofs++] = (byte) (carryOverNibble << 4);
		}
	}

	public static String textUnpack(final byte packedData[], final int size) {
		int idx = 0, highNibble = -1;
		for (int i = 0; i < size * 2; i++) {
			final int val = packedData[i / 2] >> 4 - 4 * (i % 2) & 0xf;
			if (highNibble == -1) {
				if (val < 13) {
					Misc.decodeBuf[idx++] = Misc.xlateTable[val];
				} else {
					highNibble = val;
				}
			} else {
				Misc.decodeBuf[idx++] = Misc.xlateTable[(highNibble << 4) + val
						- 195];
				highNibble = -1;
			}
		}
		return new String(Misc.decodeBuf, 0, idx);
	}

	public static String ucFirst(String str) {
		str = str.toLowerCase();
		if (str.length() > 1) {
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		} else {
			return str.toUpperCase();
		}
		return str;
	}
	
	public static String longToReportPlayerName(long l) {
		int i = 0;
		final char ac[] = new char[12];
		while (l != 0L) {
			final long l1 = l;
			l /= 37L;
			ac[11 - i++] = Misc.playerNameXlateTable[(int) (l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}

}
