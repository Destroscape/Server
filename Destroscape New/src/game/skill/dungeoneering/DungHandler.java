package game.skill.dungeoneering;

public class DungHandler {

	/**
	 * @author Ochroid
	 */

	/**
	 * Thinkable Coods 2720 4897 3483 3092 Last Floor 2387 9607 Floor 3 - 2876
	 * 9477 Floor 2 - 2862 9572
	 */

	protected final static int F1SX = 2807; // Floor 1 Starting Coord X
	protected final static int F1SY = 9201; // Floor 1 Starting Coord Y
	protected final static int F2SX = 2856; // Floor 2 Starting Coord X
	protected final static int F2SY = 9570; // Floor 2 Starting Coord Y
	protected final static int F3SX = 2929; // Floor 3 Starting Coord X
	protected final static int F3SY = 9522; // Floor 3 Starting Coord Y
	protected final static int F4SX = 2614; // Floor 4 Starting Coord X
	protected final static int F4SY = 9524; // Floor 4 Starting Coord Y
	protected final static int F5SX = 2371; // Floor 5 Starting Coord X
	protected final static int F5SY = 9666; // Floor 5 Starting Coord Y

	/**
	 * Arrays
	 */

	public static int allArmour[] = { 15753, 16273, 16339, 16383, 16405, 16647,
			16669, 16691, 16713, 16757, 16889, 16935, 17019, 17063, 17239,
			17341, 15755, 16275, 16297, 16341, 16363, 16385, 16407, 16649,
			16671, 16693, 16695, 16715, 16765, 16891, 16409, 16673, 17071,
			17023, 17343, 17241, 17021, 16937, 15757, 16277, 16299, 16343,
			16365, 16387, 16409, 16437, 16651, 16673, 17071, 16717, 16773,
			16893, 16939, 17023, 17079, 17243, 17345, 15759, 16279, 16301,
			16345, 16367, 16389, 16411, 16653, 16675, 16697, 16719, 16781,
			16895, 16941, 17025, 17087, 17245, 17347, 15761, 16281, 16303,
			16347, 16369, 16391, 16413, 16655, 16677, 16699, 16721, 16789,
			16897, 16943, 16961, 16973, 17027, 17095, 17247, 17349, 15763,
			16283, 16305, 16349, 16371, 16393, 16415, 16657, 16679, 16701,
			16723, 16797, 16899, 16945, 17029, 17103, 17249, 17351, 15765,
			16285, 16307, 16351, 16373, 16395, 16417, 16659, 16681, 16703,
			16725, 16805, 16901, 16947, 17031, 17111, 17251, 17353, 15767,
			16287, 16309, 16353, 16375, 16397, 16419, 16661, 16683, 16705,
			16727, 16813, 16903, 16949, 17033, 17119, 17253, 17355, 15769,
			16289, 16311, 16355, 16377, 16399, 16421, 16663, 16685, 16707,
			16729, 16821, 16905, 16951, 16971, 17035, 17127, 17255, 17357,
			15771, 16291, 16313, 16357, 16379, 16401, 16423, 16665, 16687,
			16709, 16731, 16829, 16907, 16953, 17037, 17037, 17257, 17359,
			15773, 16293, 16315, 16359, 16381, 16403, 16425, 16667, 16689,
			16711, 16733, 16837, 16909, 16955, 17039, 17143, 17259, 17361,
			18159, 18161, 18163, 18167, 18169, 18173, 16207, 15936, 16262,
			16024, 16174, 16196,
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
			15858, 16137, 16045, 16126, 16228, 16023, 15818 };

	protected static int dungFood[] = { 18159, 18161, 18163, 18167, 18169,
			18173 };

	public static int chooseDFF() {
		return dungFood[(int) (Math.random() * dungFood.length)];
	}

	protected static int novite[] = { 15753, 16273, 16339, 16383, 16405, 16647,
			16669, 16691, 16713, 16889, 16935, 17019, 17239, 17341, };

	public static int noviteItem() {
		return novite[(int) (Math.random() * novite.length)];
	}

	protected static int bathus[] = { 15755, 16275, 16341, 16385, 16407, 16649,
			16671, 16693, 16715, 16891, 17343, 17241, 17021, 16937 };

	public static int bathusItem() {
		return bathus[(int) (Math.random() * bathus.length)];
	}

	protected static int marmaros[] = { 15757, 16277, 16343, 16387, 16409,
			16651, 16673, 16717, 16893, 16939, 17023, 17243, 17345, 16695 };

	public static int marmarosItem() {
		return marmaros[(int) (Math.random() * marmaros.length)];
	}

	protected static int kratonite[] = { 15759, 16279, 16345, 16389, 16411,
			16653, 16675, 16697, 16719, 16895, 16941, 17025, 17245, 17347, };

	protected static int kratoniteItem() {
		return kratonite[(int) (Math.random() * kratonite.length)];
	}

	protected static int fractite[] = { 15761, 16281, 16347, 16391, 16413,
			16655, 16677, 16699, 16721, 16897, 16943, 16961, 16973, 17027,
			17247, 17349 };

	public static int fractiteItem() {
		return fractite[(int) (Math.random() * fractite.length)];
	}

	protected static int zephyrium[] = { 15763, 16283, 16349, 16393, 16415,
			16657, 16679, 16701, 16723, 16899, 16945, 17029, 17249, 17351, };

	public static int zephyriumItem() {
		return zephyrium[(int) (Math.random() * zephyrium.length)];
	}

	protected static int argonite[] = { 15765, 16285, 16351, 16395, 16417,
			16659, 16681, 16703, 16725, 16901, 16947, 17031, 17251, 17353, };

	public static int argoniteItem() {
		return argonite[(int) (Math.random() * argonite.length)];
	}

	protected static int katagon[] = { 15767, 16287, 16353, 16397, 16419,
			16661, 16683, 16705, 16727, 16903, 16949, 17033, 17253, 17355, };

	public static int katagonItem() {
		return katagon[(int) (Math.random() * katagon.length)];
	}

	protected static int gorgonite[] = { 15769, 16289, 16355, 16399, 16421,
			16663, 16685, 16707, 16729, 16905, 16951, 17035, 17255, 17357, };

	public static int gorgoniteItem() {
		return gorgonite[(int) (Math.random() * gorgonite.length)];
	}

	protected static int promethium[] = { 15771, 16291, 16357, 16401, 16423,
			16665, 16687, 16709, 16731, 16907, 16953, 17037, 17257, 17359, };

	public static int promethiumItem() {
		return promethium[(int) (Math.random() * promethium.length)];
	}

	protected static int primal[] = { 15773, 16293, 16359, 16403, 16425, 16667,
			16689, 16711, 16733, 16909, 16955, 17039, 17259, 17361, };

	public static int primalItem() {
		return primal[(int) (Math.random() * primal.length)];
	}

	/**
	 * Permissions
	 */

}