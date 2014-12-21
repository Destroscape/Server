package com.object;

import com.Class36;
import com.Client;
import com.MRUNodes;
import com.Model;
import com.OnDemandFetcher;
import com.VarBit;
import com.stream.Stream;
import com.stream.StreamLoader;

public final class ObjectDef {
	private static final int[] showBlack = { 26346, 26347, 26348, 26358, 26359,
		26360, 26361, 26362, 26363, 26364 };

	public static ObjectDef forID(int i) {
		if (i > ObjectDef.streamIndices.length) {
			i = ObjectDef.streamIndices.length - 1;
		}
		for (int j = 0; j < 20; j++) {
			if (ObjectDef.cache[j].type == i) {
				return ObjectDef.cache[j];
			}
		}
		ObjectDef.cacheIndex = (ObjectDef.cacheIndex + 1) % 20;
		ObjectDef class46 = ObjectDef.cache[ObjectDef.cacheIndex];
		try {
			ObjectDef.stream.currentOffset = ObjectDef.streamIndices[i];
		} catch (Exception e) {
			//
		}
		class46.type = i;
		class46.setDefaults();
		class46.readValues(ObjectDef.stream);
		for (int mm = 0; mm < showBlack.length; mm++) {
			if (i == showBlack[mm]) {
				class46.modifiedModelColors = new int[1];
				class46.originalModelColors = new int[1];
				class46.modifiedModelColors[0] = 0;
				class46.originalModelColors[0] = 1;
			}

		}	
		
if (class46.name != null)
			if(i == 1530 || i == 26808 || i == 15516 || i == 15514 || i == 24381 || i == 14507 || 
			   i == 307 || i == 23216 || i == 21600 || i == 4767 || i == 4772 || i == 3485 || class46.name.equalsIgnoreCase("Column") || class46.name.equalsIgnoreCase("Monument") || 
			class46.name.equalsIgnoreCase("Crates") || class46.name.equalsIgnoreCase("Barrel") || 
			 class46.name.equalsIgnoreCase("Rack of barrels") || class46.name.equalsIgnoreCase("Large barrel") 
			 || class46.name.equalsIgnoreCase("Fishing weights") || class46.name.equalsIgnoreCase("Cogs")) {
				for (int i4 = 0; i4 < class46.anIntArray773.length; i4++)
					class46.anIntArray773[i4] = 0;
					class46.aBoolean767 = false;
					return class46;
			}
				
		if(i == 4483) {
			class46.actions[1] = "Buy-gloves";
			class46.hasActions = true;
		}
		if (i == 2573) {
			class46.name = "Prestige Chest";
			class46.actions = new String[] { "Open", null, null, null, null };
		}
		if (i == 172) {
			class46.name = "Crystal Chest";
		}
		if (i == 411) {
			class46.name = "Curse Altar";
		}
		if (i == 6552) {
			class46.name = "Ancient Altar";
		}
		if (i == 410) {
			class46.name = "Lunar Altar";
		}
		if (i == 28698) {
			class46.name = "Armadyl Altar";
			class46.description = "This altar is designated for those who create Armadyl Runes.".getBytes();
			class46.actions[0] = "Craft";
		}
		if (i == 13996) {
			class46.name = "Completionist Stand";
			class46.description = "Acquired only by the best of the best players.".getBytes();
			class46.anInt744 = 1;
			class46.anInt761 = 1;
			class46.anIntArray773 = new int[1];
			class46.anIntArray773[0] = 65274;
			class46.actions = new String[5];
			class46.actions[0] = "Get cape";
			class46.actions[1] = "Check requirements";
			class46.hasActions = true;
			class46.aBoolean767 = true;
			class46.aBoolean762 = false;
			class46.aBoolean769 = false;
		}
		switch (i) {
		//TODO
		}
		return class46;
	}

	public void setDefaults() {
		anIntArray773 = null;
		anIntArray776 = null;
		name = null;
		description = null;
		modifiedModelColors = null;
		originalModelColors = null;
		anInt744 = 1;
		anInt761 = 1;
		aBoolean767 = true;
		aBoolean757 = true;
		hasActions = false;
		aBoolean762 = false;
		aBoolean769 = false;
		aBoolean764 = false;
		anInt781 = -1;
		anInt775 = 16;
		aByte737 = 0;
		aByte742 = 0;
		actions = null;
		anInt746 = -1;
		anInt758 = -1;
		aBoolean751 = false;
		aBoolean779 = true;
		anInt748 = 128;
		anInt772 = 128;
		anInt740 = 128;
		anInt768 = 0;
		anInt738 = 0;
		anInt745 = 0;
		anInt783 = 0;
		aBoolean736 = false;
		aBoolean766 = false;
		anInt760 = -1;
		anInt774 = -1;
		anInt749 = -1;
		childrenIDs = null;
	}

	public void method574(OnDemandFetcher class42_sub1) {
		if (anIntArray773 == null) {
			return;
		}
		for (int element : anIntArray773) {
			class42_sub1.method560(element & 0xffff, 0);
		}
	}

	public static void nullLoader() {
		ObjectDef.mruNodes1 = null;
		ObjectDef.mruNodes2 = null;
		ObjectDef.streamIndices = null;
		ObjectDef.cache = null;
		ObjectDef.stream = null;
	}

	public static void unpackConfig(StreamLoader streamLoader) {
		ObjectDef.stream = new Stream(streamLoader.getDataForName("loc.dat"));
		Stream stream = new Stream(streamLoader.getDataForName("loc.idx"));
		int totalObjects = stream.readUnsignedWord();
		System.out.println("508/525 Objects Loaded: " + totalObjects + "");
		ObjectDef.streamIndices = new int[totalObjects];
		int i = 2;
		for (int j = 0; j < totalObjects; j++) {
			ObjectDef.streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}
		ObjectDef.cache = new ObjectDef[20];
		for (int k = 0; k < 20; k++) {
			ObjectDef.cache[k] = new ObjectDef();
		}
	}

	public boolean method577(int i) {
		if (anIntArray776 == null) {
			if (anIntArray773 == null) {
				return true;
			}
			if (i != 10) {
				return true;
			}
			boolean flag1 = true;
			for (int element : anIntArray773) {
				flag1 &= Model.method463(element & 0xffff);
			}

			return flag1;
		}
		for (int j = 0; j < anIntArray776.length; j++) {
			if (anIntArray776[j] == i) {
				return Model.method463(anIntArray773[j] & 0xffff);
			}
		}

		return true;
	}

	public Model method578(int i, int j, int k, int l, int i1, int j1, int k1) {
		Model model = method581(i, k1, j);
		if (model == null) {
			return null;
		}
		if (aBoolean762 || aBoolean769) {
			model = new Model(aBoolean762, aBoolean769, model);
		}
		if (aBoolean762) {
			int l1 = (k + l + i1 + j1) / 4;
			for (int i2 = 0; i2 < model.anInt1626; i2++) {
				int j2 = model.anIntArray1627[i2];
				int k2 = model.anIntArray1629[i2];
				int l2 = k + (((l - k) * (j2 + 64)) / 128);
				int i3 = j1 + (((i1 - j1) * (j2 + 64)) / 128);
				int j3 = l2 + (((i3 - l2) * (k2 + 64)) / 128);
				model.anIntArray1628[i2] += j3 - l1;
			}
			model.method467();
		}
		return model;
	}

	public boolean method579() {
		if (anIntArray773 == null) {
			return true;
		}
		boolean flag1 = true;
		for (int element : anIntArray773) {
			flag1 &= Model.method463(element & 0xffff);
		}
		return flag1;
	}

	public ObjectDef method580() {
		int i = -1;
		if (anInt774 != -1) {
			VarBit varBit = VarBit.cache[anInt774];
			int j = varBit.anInt648;
			int k = varBit.anInt649;
			int l = varBit.anInt650;
			int i1 = Client.anIntArray1232[l - k];
			i = (clientInstance.variousSettings[j] >> k) & i1;
		} else if (anInt749 != -1) {
			i = clientInstance.variousSettings[anInt749];
		}
		if ((i < 0) || (i >= childrenIDs.length) || (childrenIDs[i] == -1)) {
			return null;
		}
		return ObjectDef.forID(childrenIDs[i]);
	}

	public Model method581(int j, int k, int l) {
		Model model = null;
		long l1;
		if (anIntArray776 == null) {
			if (j != 10) {
				return null;
			}
			l1 = ((type << 8) + l) + ((long) (k + 1) << 32);
			Model model_1 = (Model) ObjectDef.mruNodes2.insertFromCache(l1);
			if (model_1 != null) {
				return model_1;
			}
			if (anIntArray773 == null) {
				return null;
			}
			boolean flag1 = aBoolean751 ^ (l > 3);
			int k1 = anIntArray773.length;
			for (int i2 = 0; i2 < k1; i2++) {
				int l2 = anIntArray773[i2];
				if (flag1) {
					l2 += 0x10000;
				}
				model = (Model) ObjectDef.mruNodes1.insertFromCache(l2);
				if (model == null) {
					model = Model.method462(l2 & 0xffff);
					if (model == null) {
						return null;
					}
					if (flag1) {
						model.method477();
					}
					ObjectDef.mruNodes1.removeFromCache(model, l2);
				}
				if (k1 > 1) {
					ObjectDef.aModelArray741s[i2] = model;
				}
			}

			if (k1 > 1) {
				model = new Model(k1, ObjectDef.aModelArray741s);
			}
		} else {
			int i1 = -1;
			for (int j1 = 0; j1 < anIntArray776.length; j1++) {
				if (anIntArray776[j1] != j) {
					continue;
				}
				i1 = j1;
				break;
			}

			if (i1 == -1) {
				return null;
			}
			l1 = ((type << 8) + (i1 << 3) + l) + ((long) (k + 1) << 32);
			Model model_2 = (Model) ObjectDef.mruNodes2.insertFromCache(l1);
			if (model_2 != null) {
				return model_2;
			}
			int j2 = anIntArray773[i1];
			boolean flag3 = aBoolean751 ^ (l > 3);
			if (flag3) {
				j2 += 0x10000;
			}
			model = (Model) ObjectDef.mruNodes1.insertFromCache(j2);
			if (model == null) {
				model = Model.method462(j2 & 0xffff);
				if (model == null) {
					return null;
				}
				if (flag3) {
					model.method477();
				}
				ObjectDef.mruNodes1.removeFromCache(model, j2);
			}
		}
		boolean flag;
		flag = (anInt748 != 128) || (anInt772 != 128) || (anInt740 != 128);
		boolean flag2;
		flag2 = (anInt738 != 0) || (anInt745 != 0) || (anInt783 != 0);
		Model model_3 = new Model(modifiedModelColors == null,
				Class36.method532(k), (l == 0) && (k == -1) && !flag && !flag2,
				model);
		if (k != -1) {
			model_3.method469();
			model_3.method470(k);
			model_3.anIntArrayArray1658 = null;
			model_3.anIntArrayArray1657 = null;
		}
		while (l-- > 0) {
			model_3.method473();
		}
		if (modifiedModelColors != null) {
			for (int k2 = 0; k2 < modifiedModelColors.length; k2++) {
				model_3.method476(modifiedModelColors[k2],
						originalModelColors[k2]);
			}
		}
		if (flag) {
			model_3.method478(anInt748, anInt740, anInt772);
		}
		if (flag2) {
			model_3.method475(anInt738, anInt745, anInt783);
		}
		model_3.method479(74, 1000, -90, -580, -90, !aBoolean769);
		if (anInt760 == 1) {
			model_3.anInt1654 = model_3.modelHeight;
		}
		ObjectDef.mruNodes2.removeFromCache(model_3, l1);
		return model_3;
	}

	private void readValues(Stream stream) {
		/*int i = -1;
		label0: do {
			int j;
			do {
				j = stream.readUnsignedByte();
				if (j == 0) {
					break label0;
				}
				if (j == 1) {
					int k = stream.readUnsignedByte();
					if (k > 0) {
						if ((anIntArray773 == null) || ObjectDef.lowMem) {
							anIntArray776 = new int[k];
							anIntArray773 = new int[k];
							for (int k1 = 0; k1 < k; k1++) {
								anIntArray773[k1] = stream.readUnsignedWord();
								anIntArray776[k1] = stream.readUnsignedByte();
							}
						} else {
							stream.currentOffset += k * 3;
						}
					}
				} else if (j == 2) {
					name = stream.readString();
				} else if (j == 3) {
					description = stream.readBytes();
				} else if (j == 5) {
					int l = stream.readUnsignedByte();
					if (l > 0) {
						if ((anIntArray773 == null) || ObjectDef.lowMem) {
							anIntArray776 = null;
							anIntArray773 = new int[l];
							for (int l1 = 0; l1 < l; l1++) {
								anIntArray773[l1] = stream.readUnsignedWord();
							}
						} else {
							stream.currentOffset += l * 2;
						}
					}
				} else if (j == 14) {
					anInt744 = stream.readUnsignedByte();
				} else if (j == 15) {
					anInt761 = stream.readUnsignedByte();
				} else if (j == 17) {
					aBoolean767 = false;
				} else if (j == 18) {
					aBoolean757 = false;
				} else if (j == 19) {
					i = stream.readUnsignedByte();
					if (i == 1) {
						hasActions = true;
					}
				} else if (j == 21) {
					aBoolean762 = true;
				} else if (j == 22) {
					aBoolean769 = true; // change to false to fix gowwars
					// waterfalls??
				} else if (j == 23) {
					aBoolean764 = true;
				} else if (j == 24) {
					anInt781 = stream.readUnsignedWord();
					if (anInt781 == 65535) {
						anInt781 = -1;
					}
				} else if (j == 28) {
					anInt775 = stream.readUnsignedByte();
				} else if (j == 29) {
					aByte737 = stream.readSignedByte();
				} else if (j == 39) {
					aByte742 = stream.readSignedByte();
				} else if ((j >= 30) && (j < 39)) {
					if (actions == null) {
						actions = new String[10];
					}
					actions[j - 30] = stream.readString();
					if (actions[j - 30].equalsIgnoreCase("hidden")) {
						actions[j - 30] = null;
					}
				} else if (j == 40) {
					int i1 = stream.readUnsignedByte();
					modifiedModelColors = new int[i1];
					originalModelColors = new int[i1];
					for (int i2 = 0; i2 < i1; i2++) {
						modifiedModelColors[i2] = stream.readUnsignedWord();
						originalModelColors[i2] = stream.readUnsignedWord();
					}
				} else if (j == 60) {
					anInt746 = stream.readUnsignedWord();
				} else if (j == 62) {
					aBoolean751 = true;
				} else if (j == 64) {
					aBoolean779 = false;
				} else if (j == 65) {
					anInt748 = stream.readUnsignedWord();
				} else if (j == 66) {
					anInt772 = stream.readUnsignedWord();
				} else if (j == 67) {
					anInt740 = stream.readUnsignedWord();
				} else if (j == 68) {
					anInt758 = stream.readUnsignedWord();
				} else if (j == 69) {
					anInt768 = stream.readUnsignedByte();
				} else if (j == 70) {
					anInt738 = stream.readSignedWord();
				} else if (j == 71) {
					anInt745 = stream.readSignedWord();
				} else if (j == 72) {
					anInt783 = stream.readSignedWord();
				} else if (j == 73) {
					aBoolean736 = true;
				} else if (j == 74) {
					aBoolean766 = true;
				} else {
					if (j != 75) {
						continue;
					}
					anInt760 = stream.readUnsignedByte();
				}
				continue label0;
			} while (j != 77);
			anInt774 = stream.readUnsignedWord();
			if (anInt774 == 65535) {
				anInt774 = -1;
			}
			anInt749 = stream.readUnsignedWord();
			if (anInt749 == 65535) {
				anInt749 = -1;
			}
			int j1 = stream.readUnsignedByte();
			childrenIDs = new int[j1 + 1];
			for (int j2 = 0; j2 <= j1; j2++) {
				childrenIDs[j2] = stream.readUnsignedWord();
				if (childrenIDs[j2] == 65535) {
					childrenIDs[j2] = -1;
				}
			}

		} while (true);
		if (i == -1) {
			hasActions = (anIntArray773 != null)
					&& ((anIntArray776 == null) || (anIntArray776[0] == 10));
			if (actions != null) {
				hasActions = true;
			}
		}
		if (aBoolean766) {
			aBoolean767 = false;
			aBoolean757 = false;
		}
		if (anInt760 == -1) {
			anInt760 = aBoolean767 ? 1 : 0;
		}
	}*/
			int i = -1;
			label0: do {
				int opcode;
				do {
					opcode = stream.readUnsignedByte();
					if (opcode == 0)
						break label0;
					if (opcode == 1) {
						int k = stream.readUnsignedByte();
						if (k > 0)
							if (anIntArray773 == null || lowMem) {
								anIntArray776 = new int[k];
								anIntArray773 = new int[k];
								for (int k1 = 0; k1 < k; k1++) {
									anIntArray773[k1] = stream.readUnsignedWord();
									anIntArray776[k1] = stream.readUnsignedByte();
								}
							} else {
								stream.currentOffset += k * 3;
							}
					} else if (opcode == 2)
						name = stream.readString();
					else if (opcode == 3)
						description = stream.readBytes();
					else if (opcode == 5) {
						int l = stream.readUnsignedByte();
						if (l > 0)
							if (anIntArray773 == null || lowMem) {
								anIntArray776 = null;
								anIntArray773 = new int[l];
								for (int l1 = 0; l1 < l; l1++)
									anIntArray773[l1] = stream.readUnsignedWord();
							} else {
								;//stream.currentOffset += l * 2;
							}
					} else if (opcode == 14)
						anInt744 = stream.readUnsignedByte();
					else if (opcode == 15)
						anInt761 = stream.readUnsignedByte();
					else if (opcode == 17)
						aBoolean767 = false;
					else if (opcode == 18)
						aBoolean757 = false;
					else if (opcode == 19) {
						i = stream.readUnsignedByte();
						if (i == 1)
							hasActions = true;
					} else if (opcode == 21)
						aBoolean762 = true;
					else if (opcode == 22)
						aBoolean769 = false;//
					else if (opcode == 23)
						aBoolean764 = true;
					else if (opcode == 24) {
						anInt781 = stream.readUnsignedWord();
						if (anInt781 == 65535)
							anInt781 = -1;
					} else if (opcode == 28)
						anInt775 = stream.readUnsignedByte();
					else if (opcode == 29)
						aByte737 = stream.readSignedByte();
					else if (opcode == 39)
						aByte742 = stream.readSignedByte();
					else if (opcode >= 30 && opcode < 39) {
						if (actions == null)
							actions = new String[10];
						actions[opcode - 30] = stream.readString();
						if (actions[opcode - 30].equalsIgnoreCase("hidden"))
							actions[opcode - 30] = null;
					} else if (opcode == 40) {
						int i1 = stream.readUnsignedByte();
						modifiedModelColors = new int[i1];
						originalModelColors = new int[i1];
						for (int i2 = 0; i2 < i1; i2++) {
							modifiedModelColors[i2] = stream.readUnsignedWord();
							originalModelColors[i2] = stream.readUnsignedWord();
						}
					} else if (opcode == 60)
						anInt746 = stream.readUnsignedWord();
					else if (opcode == 62)
						aBoolean751 = true;
					else if (opcode == 64)
						aBoolean779 = false;
					else if (opcode == 65)
						anInt748 = stream.readUnsignedWord();
					else if (opcode == 66)
						anInt772 = stream.readUnsignedWord();
					else if (opcode == 67)
						anInt740 = stream.readUnsignedWord();
					else if (opcode == 68)
						anInt758 = stream.readUnsignedWord();
					else if (opcode == 69)
						anInt768 = stream.readUnsignedByte();
					else if (opcode == 70)
						anInt738 = stream.readSignedWord();
					else if (opcode == 71)
						anInt745 = stream.readSignedWord();
					else if (opcode == 72)
						anInt783 = stream.readSignedWord();
					else if (opcode == 73)
						aBoolean736 = true;
					else if (opcode == 74) {
						aBoolean766 = true;
					} else {
						if (opcode != 75)
							continue;
						anInt760 = stream.readUnsignedByte();
					}
					continue label0;
				} while (opcode != 77);
					anInt774 = stream.readUnsignedWord();
				if (anInt774 == 65535)
					anInt774 = -1;
					anInt749 = stream.readUnsignedWord();
				if (anInt749 == 65535)
					anInt749 = -1;
				int j1 = stream.readUnsignedByte();
				childrenIDs = new int[j1 + 1];
				for (int j2 = 0; j2 <= j1; j2++) {
					childrenIDs[j2] = stream.readUnsignedWord();
					if (childrenIDs[j2] == 65535)
						childrenIDs[j2] = -1;
				}

			} while (true);
			if (i == -1) {
				hasActions = anIntArray773 != null && (anIntArray776 == null || anIntArray776[0] == 10);
				if (actions != null)
					hasActions = true;
			}
			if (aBoolean766) {
				aBoolean767 = false;
				aBoolean757 = false;
			}
			if (anInt760 == -1)
				anInt760 = aBoolean767 ? 1 : 0;
	    }

	public ObjectDef() {
		type = -1;
	}

	public boolean aBoolean736;
	public byte aByte737;
	public int anInt738;
	public String name;
	public int anInt740;
	public static final Model[] aModelArray741s = new Model[4];
	public byte aByte742;
	public int anInt744;
	public int anInt745;
	public int anInt746;
	public int[] originalModelColors;
	public int anInt748;
	public int anInt749;
	public boolean aBoolean751;
	public static boolean lowMem;
	public static Stream stream;
	public int type;
	public static int[] streamIndices;
	public boolean aBoolean757;
	public int anInt758;
	public int childrenIDs[];
	public int anInt760;
	public int anInt761;
	public boolean aBoolean762;
	public boolean aBoolean764;
	public static Client clientInstance;
	public boolean aBoolean766;
	public boolean aBoolean767;
	public int anInt768;
	public boolean aBoolean769;
	public static int cacheIndex;
	public int anInt772;
	public int[] anIntArray773;
	public int anInt774;
	public int anInt775;
	public int dummy;
	public int[] anIntArray776;
	public byte description[];
	public boolean hasActions;
	public boolean aBoolean779;
	public static MRUNodes mruNodes2 = new MRUNodes(30);
	public int anInt781;
	public static ObjectDef[] cache;
	public int anInt783;
	public int[] modifiedModelColors;
	public static MRUNodes mruNodes1 = new MRUNodes(500);
	public String actions[];

}
