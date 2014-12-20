package game.skill.dungeoneering;

import game.entity.player.Player;

public class Binding extends DungHandler {

	/**
	 * @author Ochroid | Scott
	 */

	protected static int[][] bindedItems = {
			// Novite Items
			{ 15753, 16207 },
			{ 16273, 15936 },
			{ 16339, 16262 },
			{ 16383, 16024 },
			{ 16405, 16174 },
			{ 16647, 16196 },
			{ 16669, 15925 },
			{ 16691, 15914 },
			{ 16713, 16080 },
			{ 16889, 16127 },
			{ 16935, 16035 },
			{ 17019, 16116 },
			{ 17239, 16013 },
			{ 17341, 15808 },
			// Bathus Items
			{ 15755, 16208 },
			{ 16275, 15937 },
			{ 16341, 16263 },
			{ 16385, 16025 },
			{ 16407, 16175 },
			{ 16649, 16197 },
			{ 16671, 15926 },
			{ 16693, 15915 },
			{ 16715, 16081 },
			{ 16891, 16128 },
			{ 16937, 16036 },
			{ 17021, 16117 },
			{ 17241, 16014 },
			{ 17343, 15809 },
			// Marmaros Items
			{ 15757, 16209 },
			{ 16277, 15938 },
			{ 16343, 16264 },
			{ 16387, 16026 },
			{ 16409, 16176 },
			{ 16651, 16198 },
			{ 16673, 15927 },
			{ 16695, 15916 },
			{ 16717, 16082 },
			{ 16893, 16129 },
			{ 16939, 16037 },
			{ 17023, 16118 },
			{ 17243, 16015 },
			{ 17345, 15810 },
			// Kratonite Items
			{ 15759, 16210 },
			{ 16279, 15939 },
			{ 16345, 16265 },
			{ 16389, 16027 },
			{ 16411, 16177 },
			{ 16653, 16199 },
			{ 16675, 15928 },
			{ 16697, 15917 },
			{ 16719, 16083 },
			{ 16895, 16130 },
			{ 16941, 16038 },
			{ 17025, 16119 },
			{ 17245, 16016 },
			{ 17347, 15811 },
			// Fractite Items
			{ 15761, 16211 },
			{ 16281, 15940 },
			{ 16347, 16266 },
			{ 16391, 16028 },
			{ 16413, 16178 },
			{ 16655, 16200 },
			{ 16677, 15929 },
			{ 16699, 15918 },
			{ 16721, 16084 },
			{ 16897, 16131 },
			{ 16943, 16039 },
			{ 17027, 16120 },
			{ 17247, 16017 },
			{ 17349, 15812 },
			// Zephyrium Items
			{ 15763, 16212 },
			{ 16283, 15941 },
			{ 16349, 16267 },
			{ 16393, 16029 },
			{ 16415, 16179 },
			{ 16657, 16201 },
			{ 16679, 15930 },
			{ 16701, 15919 },
			{ 16723, 16085 },
			{ 16899, 16132 },
			{ 16945, 16040 },
			{ 17029, 16121 },
			{ 17249, 16018 },
			{ 17351, 15813 },
			// Argonite Items
			{ 15765, 16213 },
			{ 16285, 15942 },
			{ 16351, 16268 },
			{ 16395, 16030 },
			{ 16417, 16180 },
			{ 16659, 16202 },
			{ 16681, 15931 },
			{ 16703, 15920 },
			{ 16725, 16086 },
			{ 16901, 16133 },
			{ 16947, 16041 },
			{ 17031, 16122 },
			{ 17251, 16019 },
			{ 17353, 15814 },
			// Katagon Items
			{ 15767, 16214 },
			{ 16287, 15943 },
			{ 16353, 16269 },
			{ 16397, 16031 },
			{ 16419, 16181 },
			{ 16661, 16203 },
			{ 16683, 15932 },
			{ 16705, 15921 },
			{ 16727, 16087 },
			{ 16903, 16134 },
			{ 16949, 16042 },
			{ 17033, 16123 },
			{ 17253, 16020 },
			{ 17355, 15815 },
			// Gorgonite Items
			{ 15769, 16215 }, { 16289, 15944 }, { 16355, 16270 },
			{ 16399, 16032 },
			{ 16421, 16182 },
			{ 16663, 16204 },
			{ 16685, 15933 },
			{ 16707, 15922 },
			{ 16729, 16088 },
			{ 16905, 16135 },
			{ 16951, 16043 },
			{ 17035, 16124 },
			{ 17255, 16021 },
			{ 17357, 15816 },
			// Promethium Items
			{ 15771, 16216 }, { 16291, 15945 }, { 16357, 16271 },
			{ 16401, 16033 }, { 16423, 16183 }, { 16665, 16205 },
			{ 16687, 15934 }, { 16709, 15923 }, { 16731, 16089 },
			{ 16907, 16136 },
			{ 16953, 16044 },
			{ 17037, 16125 },
			{ 17257, 16022 },
			{ 17359, 15817 },
			// Primal Items
			{ 15773, 16217 }, { 16293, 15946 }, { 16359, 16272 },
			{ 16403, 16034 }, { 16425, 16184 }, { 16667, 16206 },
			{ 16689, 15935 }, { 16711, 15924 }, { 16733, 16090 },
			{ 16909, 16137 }, { 16955, 16045 }, { 17039, 16126 },
			{ 17259, 16023 }, { 17361, 15818 },

	};

	public static int[] bindedItem = {
			// Novite
			16207, 15936, 16262, 16024, 16174, 16196,
			15925,
			15914,
			16080,
			15848,
			16127,
			16035,
			16116,
			16218,
			16013,
			15808,
			// Bathus
			16208, 15937, 16263, 16025, 16175, 16197, 15926,
			15915,
			16081,
			15849,
			16128,
			16036,
			16117,
			16219,
			16014,
			15809,
			// Marmaros
			16209, 15938, 16264, 16026, 16176, 16198, 15927, 15916,
			16082,
			15850,
			16129,
			16037,
			16118,
			16220,
			16015,
			15810,
			// Kratonite
			16210, 15939, 16265, 16027, 16177, 16199, 15928, 15917, 16083,
			15851,
			16130,
			16038,
			16119,
			16221,
			16016,
			15811,
			// Fractite
			16211, 15940, 16266, 16028, 16178, 16200, 15929, 15918, 16084,
			15852, 16131,
			16039,
			16120,
			16222,
			16017,
			15812,
			// Zephyrium
			16212, 15941, 16267, 16029, 16179, 16201, 15930, 15919, 16085,
			15853, 16132, 16040,
			16121,
			16223,
			16018,
			15813,
			// Argonite
			16213, 15942, 16268, 16030, 16180, 16202, 15931, 15920, 16086,
			15854, 16133, 16041, 16122,
			16224,
			16019,
			15814,
			// Katagon
			16214, 15943, 16269, 16031, 16181, 16203, 15932, 15921, 16087,
			15855, 16134, 16042, 16123, 16225,
			16020,
			15815,
			// Gorgonite
			16215, 15944, 16270, 16032, 16182, 16204, 15933, 15922, 16088,
			15856, 16135, 16043, 16124, 16226, 16021,
			15816,
			// Promethium
			16216, 15945, 16271, 16033, 16183, 16205, 15934, 15923, 16089,
			15857, 16136, 16044, 16125, 16227, 16022, 15817,
			// Primal
			16217, 15946, 16272, 16034, 16184, 16206, 15935, 15924, 16090,
			15858, 16137, 16045, 16126, 16228, 16023, 15818, };

	public static boolean isBindable(final Player c, int itemId) {
		String name = c.getItems().getItemName(itemId);
		return name.contains("Novite") || name.contains("Bathus")
				|| name.contains("Marmaros") || name.contains("Kratonite")
				|| name.contains("Fractite") || name.contains("Zephyrium")
				|| name.contains("Argonite") || name.contains("Katagon")
				|| name.contains("Gorgonite") || name.contains("Promethium")
				|| name.contains("Primal");
	}

	public static void bindItem(final Player c, final int item) {
		if (!c.canBind(c)) {
			return;
		}
		for (int i = 0; i < bindedItems.length; i++) {
			if (bindedItems[i][0] == item) {
				if (c.bind4 == -1 && c.bind1 > -1 && c.bind2 > -1
						&& c.bind3 > -1) {
					c.bind4 = bindedItems[i][1];
				}
				if (c.bind3 == -1 && c.bind1 > -1 && c.bind2 > -1) {
					c.bind3 = bindedItems[i][1];
				}
				if (c.bind2 == -1 && c.bind1 > -1) {
					c.bind2 = bindedItems[i][1];
				}
				if (c.bind1 == -1) {
					c.bind1 = bindedItems[i][1];
				}
				c.getItems().deleteItem2(bindedItems[i][0], 1);
				c.getItems().addItem(bindedItems[i][1], 1);
				c.sendMessage("You have bounded the "
						+ c.getItems().getItemName(bindedItems[i][0]) + ".");
			}
		}
	}

	protected static void addBindItems(final Player c) {
		if (c.bind1 > -1) {
			c.getItems().addItem(c.bind1, 1);
		}
		if (c.bind2 > -1) {
			c.getItems().addItem(c.bind2, 1);
		}
		if (c.bind3 > -1) {
			c.getItems().addItem(c.bind3, 1);
		}
		if (c.bind4 > -1) {
			c.getItems().addItem(c.bind4, 1);
		}
	}

}