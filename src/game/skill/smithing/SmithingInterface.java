package game.skill.smithing;

import game.entity.player.Player;

/**
 * @author Ochroid | Scott
 */

public class SmithingInterface {

	private static String GetForBars(final int i, final int j, final Player c) {
		if (c.getItems().playerHasItem(i, j)) {
			return "@gre@";
		}
		return "@red@";
	}

	private static String GetForlvl(final int i, final Player c) {
		if (c.playerLevel[13] >= i) {
			return "@whi@";
		}
		return "@bla@";
	}

	private static void makeAddyInterface(final Player c) {
		final String fiveb = SmithingInterface.GetForBars(2361, 5, c);
		final String threeb = SmithingInterface.GetForBars(2361, 3, c);
		final String twob = SmithingInterface.GetForBars(2361, 2, c);
		final String oneb = SmithingInterface.GetForBars(2361, 1, c);
		c.getPA().sendFrame126(fiveb + "5 Bars" + fiveb, 1112);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1109);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1110);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1118);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1111);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1095);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1115);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1090);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1113);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1116);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1114);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1089);
		c.getPA().sendFrame126(" ", 8428);
		c.getPA().sendFrame126(" ", 8429);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1124);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1125);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1126);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1127);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1128);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1129);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1130);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1131);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 13357);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 11459);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(88, c) + "Plate Body"
						+ SmithingInterface.GetForlvl(18, c), 1101);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(86, c) + "Plate Legs"
						+ SmithingInterface.GetForlvl(16, c), 1099);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(86, c) + "Plate Skirt"
						+ SmithingInterface.GetForlvl(16, c), 1100);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(84, c) + "2 Hand Sword"
						+ SmithingInterface.GetForlvl(14, c), 1088);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(82, c) + "Kite Shield"
						+ SmithingInterface.GetForlvl(12, c), 1105);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(81, c) + "Chain Body"
						+ SmithingInterface.GetForlvl(11, c), 1098);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(80, c) + "Battle Axe"
						+ SmithingInterface.GetForlvl(10, c), 1092);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(79, c) + "Warhammer"
						+ SmithingInterface.GetForlvl(9, c), 1083);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(78, c) + "Square Shield"
						+ SmithingInterface.GetForlvl(8, c), 1104);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(77, c) + "Full Helm"
						+ SmithingInterface.GetForlvl(7, c), 1103);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(77, c) + "Throwing Knives"
						+ SmithingInterface.GetForlvl(7, c), 1106);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(76, c) + "Long Sword"
						+ SmithingInterface.GetForlvl(6, c), 1086);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(75, c) + "Scimitar"
						+ SmithingInterface.GetForlvl(5, c), 1087);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(75, c) + "Arrowtips"
						+ SmithingInterface.GetForlvl(5, c), 1108);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(75, c) + "Bolts"
						+ SmithingInterface.GetForlvl(5, c), 1107);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(74, c) + "Sword"
						+ SmithingInterface.GetForlvl(4, c), 1085);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(74, c) + "Bolts"
						+ SmithingInterface.GetForlvl(4, c), 9143);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(74, c) + "Nails"
						+ SmithingInterface.GetForlvl(4, c), 13358);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(73, c) + "Medium Helm"
						+ SmithingInterface.GetForlvl(3, c), 1102);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(72, c) + "Mace"
						+ SmithingInterface.GetForlvl(2, c), 1093);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(70, c) + "Dagger"
						+ SmithingInterface.GetForlvl(1, c), 1094);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(71, c) + "Axe"
						+ SmithingInterface.GetForlvl(1, c), 1091);
		c.getPA().sendFrame34(1211, 0, 1119, 1); // dagger
		c.getPA().sendFrame34(1357, 0, 1120, 1); // axe
		c.getPA().sendFrame34(1111, 0, 1121, 1); // chain body
		c.getPA().sendFrame34(1145, 0, 1122, 1); // med helm
		c.getPA().sendFrame34(9380, 0, 1123, 10); // Bolts
		c.getPA().sendFrame34(1287, 1, 1119, 1); // s-sword
		c.getPA().sendFrame34(1430, 1, 1120, 1); // mace
		c.getPA().sendFrame34(1073, 1, 1121, 1); // platelegs
		c.getPA().sendFrame34(1161, 1, 1122, 1); // full helm
		c.getPA().sendFrame34(43, 1, 1123, 15); // arrowtips
		c.getPA().sendFrame34(1331, 2, 1119, 1); // scimmy
		c.getPA().sendFrame34(1345, 2, 1120, 1); // warhammer
		c.getPA().sendFrame34(1091, 2, 1121, 1); // plateskirt
		c.getPA().sendFrame34(1183, 2, 1122, 1); // Sq. Shield
		c.getPA().sendFrame34(867, 2, 1123, 5); // throwing-knives
		c.getPA().sendFrame34(1301, 3, 1119, 1); // longsword
		c.getPA().sendFrame34(1371, 3, 1120, 1); // battleaxe
		c.getPA().sendFrame34(1123, 3, 1121, 1); // platebody
		c.getPA().sendFrame34(1199, 3, 1122, 1); // kiteshield
		c.getPA().sendFrame34(1317, 4, 1119, 1); // 2h sword
		c.getPA().sendFrame34(4823, 4, 1122, 15); // nails
		c.getPA().sendFrame34(-1, 3, 1123, 1);
		c.getPA().sendFrame126("", 1135);
		c.getPA().sendFrame126("", 1134);
		c.getPA().sendFrame126("", 11461);
		c.getPA().sendFrame126("", 11459);
		c.getPA().sendFrame126("", 1132);
		c.getPA().sendFrame126("", 1096);
		c.getPA().showInterface(994);
	}

	private static void makeBronzeInterface(final Player c) {
		final String fiveb = SmithingInterface.GetForBars(2349, 5, c);
		final String threeb = SmithingInterface.GetForBars(2349, 3, c);
		final String twob = SmithingInterface.GetForBars(2349, 2, c);
		final String oneb = SmithingInterface.GetForBars(2349, 1, c);
		c.getPA().sendFrame126(fiveb + "5 Bars" + fiveb, 1112);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1109);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1110);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1118);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1111);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1095);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1115);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1090);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1113);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1116);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1114);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1089);
		c.getPA().sendFrame126(" ", 8428);
		c.getPA().sendFrame126(" ", 8429);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1124);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1125);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1126);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1127);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1128);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1129);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1130);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1131);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 13357);
		c.getPA().sendFrame126(" ", 11459);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(18, c) + "Plate Body"
						+ SmithingInterface.GetForlvl(18, c), 1101);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(16, c) + "Plate Legs"
						+ SmithingInterface.GetForlvl(16, c), 1099);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(16, c) + "Plate Skirt"
						+ SmithingInterface.GetForlvl(16, c), 1100);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(14, c) + "2 Hand Sword"
						+ SmithingInterface.GetForlvl(14, c), 1088);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(12, c) + "Kite Shield"
						+ SmithingInterface.GetForlvl(12, c), 1105);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(11, c) + "Chain Body"
						+ SmithingInterface.GetForlvl(11, c), 1098);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(10, c) + "Battle Axe"
						+ SmithingInterface.GetForlvl(10, c), 1092);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(9, c) + "Warhammer"
						+ SmithingInterface.GetForlvl(9, c), 1083);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(8, c) + "Square Shield"
						+ SmithingInterface.GetForlvl(8, c), 1104);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(7, c) + "Full Helm"
						+ SmithingInterface.GetForlvl(7, c), 1103);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(7, c) + "Throwing Knives"
						+ SmithingInterface.GetForlvl(7, c), 1106);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(6, c) + "Long Sword"
						+ SmithingInterface.GetForlvl(6, c), 1086);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(5, c) + "Scimitar"
						+ SmithingInterface.GetForlvl(5, c), 1087);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(5, c) + "Arrowtips"
						+ SmithingInterface.GetForlvl(5, c), 1108);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(5, c) + "Bolts"
						+ SmithingInterface.GetForlvl(5, c), 1107);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(4, c) + "Sword"
						+ SmithingInterface.GetForlvl(4, c), 1085);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(4, c) + "Bolts"
						+ SmithingInterface.GetForlvl(4, c), 1107);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(4, c) + "Nails"
						+ SmithingInterface.GetForlvl(4, c), 13358);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(3, c) + "Medium Helm"
						+ SmithingInterface.GetForlvl(3, c), 1102);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(2, c) + "Mace"
						+ SmithingInterface.GetForlvl(2, c), 1093);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(1, c) + "Dagger"
						+ SmithingInterface.GetForlvl(1, c), 1094);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(1, c) + "Axe"
						+ SmithingInterface.GetForlvl(1, c), 1091);
		c.getPA().sendFrame34(1205, 0, 1119, 1);
		c.getPA().sendFrame34(1351, 0, 1120, 1);
		c.getPA().sendFrame34(1103, 0, 1121, 1);
		c.getPA().sendFrame34(1139, 0, 1122, 1);
		c.getPA().sendFrame34(9375, 0, 1123, 10);
		c.getPA().sendFrame34(1277, 1, 1119, 1);
		c.getPA().sendFrame34(1422, 1, 1120, 1);
		c.getPA().sendFrame34(1075, 1, 1121, 1);
		c.getPA().sendFrame34(1155, 1, 1122, 1);
		c.getPA().sendFrame34(39, 1, 1123, 15);
		c.getPA().sendFrame34(1321, 2, 1119, 1);
		c.getPA().sendFrame34(1337, 2, 1120, 1);
		c.getPA().sendFrame34(1087, 2, 1121, 1);
		c.getPA().sendFrame34(1173, 2, 1122, 1);
		c.getPA().sendFrame34(864, 2, 1123, 5);
		c.getPA().sendFrame34(1291, 3, 1119, 1);
		c.getPA().sendFrame34(1375, 3, 1120, 1);
		c.getPA().sendFrame34(1117, 3, 1121, 1);
		c.getPA().sendFrame34(1189, 3, 1122, 1);
		c.getPA().sendFrame34(1307, 4, 1119, 1);
		c.getPA().sendFrame34(4819, 4, 1122, 15);
		c.getPA().sendFrame34(-1, 3, 1123, 1);
		c.getPA().sendFrame126("", 1135);
		c.getPA().sendFrame126("", 1134);
		c.getPA().sendFrame126("", 11461);
		c.getPA().sendFrame126("", 11459);
		c.getPA().sendFrame126("", 1132);
		c.getPA().sendFrame126("", 1096);
		c.getPA().showInterface(994);
	}

	private static void makeIronInterface(final Player c) {
		final String fiveb = SmithingInterface.GetForBars(2351, 5, c);
		final String threeb = SmithingInterface.GetForBars(2351, 3, c);
		final String twob = SmithingInterface.GetForBars(2351, 2, c);
		final String oneb = SmithingInterface.GetForBars(2351, 1, c);
		c.getPA().sendFrame126(fiveb + "5 Bars" + fiveb, 1112);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1109);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1110);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1118);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1111);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1095);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1115);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1090);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1113);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1116);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1114);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1089);
		c.getPA().sendFrame126(" ", 8428);
		c.getPA().sendFrame126(" ", 8429);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1124);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1125);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1126);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1127);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1128);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1129);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1130);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1131);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 13357);
		c.getPA().sendFrame126(" ", 11459);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(33, c) + "Plate Body"
						+ SmithingInterface.GetForlvl(18, c), 1101);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(31, c) + "Plate Legs"
						+ SmithingInterface.GetForlvl(16, c), 1099);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(31, c) + "Plate Skirt"
						+ SmithingInterface.GetForlvl(16, c), 1100);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(29, c) + "2 Hand Sword"
						+ SmithingInterface.GetForlvl(14, c), 1088);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(27, c) + "Kite Shield"
						+ SmithingInterface.GetForlvl(12, c), 1105);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(26, c) + "Chain Body"
						+ SmithingInterface.GetForlvl(11, c), 1098);
		c.getPA().sendFrame126(" ", 11461);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(25, c) + "Battle Axe"
						+ SmithingInterface.GetForlvl(10, c), 1092);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(24, c) + "Warhammer"
						+ SmithingInterface.GetForlvl(9, c), 1083);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(23, c) + "Square Shield"
						+ SmithingInterface.GetForlvl(8, c), 1104);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(22, c) + "Full Helm"
						+ SmithingInterface.GetForlvl(7, c), 1103);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(21, c) + "Throwing Knives"
						+ SmithingInterface.GetForlvl(7, c), 1106);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(21, c) + "Long Sword"
						+ SmithingInterface.GetForlvl(6, c), 1086);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(20, c) + "Scimitar"
						+ SmithingInterface.GetForlvl(5, c), 1087);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(20, c) + "Arrowtips"
						+ SmithingInterface.GetForlvl(5, c), 1108);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(20, c) + "Bolts"
						+ SmithingInterface.GetForlvl(5, c), 1107);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(19, c) + "Sword"
						+ SmithingInterface.GetForlvl(4, c), 1085);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(19, c) + "Bolts"
						+ SmithingInterface.GetForlvl(4, c), 9140);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(19, c) + "Nails"
						+ SmithingInterface.GetForlvl(4, c), 13358);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(18, c) + "Medium Helm"
						+ SmithingInterface.GetForlvl(3, c), 1102);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(17, c) + "Mace"
						+ SmithingInterface.GetForlvl(2, c), 1093);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(15, c) + "Dagger"
						+ SmithingInterface.GetForlvl(1, c), 1094);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(16, c) + "Axe"
						+ SmithingInterface.GetForlvl(1, c), 1091);
		c.getPA().sendFrame34(1203, 0, 1119, 1);
		c.getPA().sendFrame34(1349, 0, 1120, 1);
		c.getPA().sendFrame34(1101, 0, 1121, 1);
		c.getPA().sendFrame34(1137, 0, 1122, 1);
		c.getPA().sendFrame34(9377, 0, 1123, 10);
		c.getPA().sendFrame34(1279, 1, 1119, 1);
		c.getPA().sendFrame34(1420, 1, 1120, 1);
		c.getPA().sendFrame34(1067, 1, 1121, 1);
		c.getPA().sendFrame34(1153, 1, 1122, 1);
		c.getPA().sendFrame34(40, 1, 1123, 15);
		c.getPA().sendFrame34(1323, 2, 1119, 1);
		c.getPA().sendFrame34(1335, 2, 1120, 1);
		c.getPA().sendFrame34(1081, 2, 1121, 1);
		c.getPA().sendFrame34(1175, 2, 1122, 1);
		c.getPA().sendFrame34(863, 2, 1123, 5);
		c.getPA().sendFrame34(1293, 3, 1119, 1);
		c.getPA().sendFrame34(1363, 3, 1120, 1);
		c.getPA().sendFrame34(1115, 3, 1121, 1);
		c.getPA().sendFrame34(1191, 3, 1122, 1);
		c.getPA().sendFrame34(1309, 4, 1119, 1);
		c.getPA().sendFrame34(4820, 4, 1122, 15);
		c.getPA().sendFrame34(-1, 3, 1123, 1);
		c.getPA().sendFrame126("", 1135);
		c.getPA().sendFrame126("", 1134);
		c.getPA().sendFrame126("", 1132);
		c.getPA().sendFrame126("", 1096);
		c.getPA().showInterface(994);
	}

	private static void makeMithInterface(final Player c) {
		final String fiveb = SmithingInterface.GetForBars(2359, 5, c);
		final String threeb = SmithingInterface.GetForBars(2359, 3, c);
		final String twob = SmithingInterface.GetForBars(2359, 2, c);
		final String oneb = SmithingInterface.GetForBars(2359, 1, c);
		c.getPA().sendFrame126(fiveb + "5 Bars" + fiveb, 1112);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1109);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1110);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1118);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1111);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1095);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1115);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1090);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1113);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1116);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1114);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1089);
		c.getPA().sendFrame126(" ", 8428);
		c.getPA().sendFrame126(" ", 8429);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1124);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1125);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1126);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1127);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1128);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1129);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1130);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1131);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 13357);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 11459);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(68, c) + "Plate Body"
						+ SmithingInterface.GetForlvl(18, c), 1101);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(66, c) + "Plate Legs"
						+ SmithingInterface.GetForlvl(16, c), 1099);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(66, c) + "Plate Skirt"
						+ SmithingInterface.GetForlvl(16, c), 1100);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(64, c) + "2 Hand Sword"
						+ SmithingInterface.GetForlvl(14, c), 1088);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(62, c) + "Kite Shield"
						+ SmithingInterface.GetForlvl(12, c), 1105);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(61, c) + "Chain Body"
						+ SmithingInterface.GetForlvl(11, c), 1098);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(60, c) + "Battle Axe"
						+ SmithingInterface.GetForlvl(10, c), 1092);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(59, c) + "Warhammer"
						+ SmithingInterface.GetForlvl(9, c), 1083);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(58, c) + "Square Shield"
						+ SmithingInterface.GetForlvl(8, c), 1104);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(57, c) + "Full Helm"
						+ SmithingInterface.GetForlvl(7, c), 1103);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(57, c) + "Throwing Knives"
						+ SmithingInterface.GetForlvl(7, c), 1106);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(56, c) + "Long Sword"
						+ SmithingInterface.GetForlvl(6, c), 1086);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(55, c) + "Scimitar"
						+ SmithingInterface.GetForlvl(5, c), 1087);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(55, c) + "Arrowtips"
						+ SmithingInterface.GetForlvl(5, c), 1108);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(55, c) + "Bolts"
						+ SmithingInterface.GetForlvl(5, c), 1107);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(54, c) + "Sword"
						+ SmithingInterface.GetForlvl(4, c), 1085);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(54, c) + "Bolts"
						+ SmithingInterface.GetForlvl(4, c), 9142);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(54, c) + "Nails"
						+ SmithingInterface.GetForlvl(4, c), 13358);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(53, c) + "Medium Helm"
						+ SmithingInterface.GetForlvl(3, c), 1102);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(52, c) + "Mace"
						+ SmithingInterface.GetForlvl(2, c), 1093);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(50, c) + "Dagger"
						+ SmithingInterface.GetForlvl(1, c), 1094);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(51, c) + "Axe"
						+ SmithingInterface.GetForlvl(1, c), 1091);
		c.getPA().sendFrame34(1209, 0, 1119, 1); // dagger
		c.getPA().sendFrame34(1355, 0, 1120, 1); // axe
		c.getPA().sendFrame34(1109, 0, 1121, 1); // chain body
		c.getPA().sendFrame34(1143, 0, 1122, 1); // med helm
		c.getPA().sendFrame34(9379, 0, 1123, 10); // Bolts
		c.getPA().sendFrame34(1285, 1, 1119, 1); // s-sword
		c.getPA().sendFrame34(1428, 1, 1120, 1); // mace
		c.getPA().sendFrame34(1071, 1, 1121, 1); // platelegs
		c.getPA().sendFrame34(1159, 1, 1122, 1); // full helm
		c.getPA().sendFrame34(42, 1, 1123, 15); // arrowtips
		c.getPA().sendFrame34(1329, 2, 1119, 1); // scimmy
		c.getPA().sendFrame34(1343, 2, 1120, 1); // warhammer
		c.getPA().sendFrame34(1085, 2, 1121, 1); // plateskirt
		c.getPA().sendFrame34(1181, 2, 1122, 1); // Sq. Shield
		c.getPA().sendFrame34(866, 2, 1123, 5); // throwing-knives
		c.getPA().sendFrame34(1299, 3, 1119, 1); // longsword
		c.getPA().sendFrame34(1369, 3, 1120, 1); // battleaxe
		c.getPA().sendFrame34(1121, 3, 1121, 1); // platebody
		c.getPA().sendFrame34(1197, 3, 1122, 1); // kiteshield
		c.getPA().sendFrame34(1315, 4, 1119, 1); // 2h sword
		c.getPA().sendFrame34(4822, 4, 1122, 15); // nails
		c.getPA().sendFrame34(-1, 3, 1123, 1);
		c.getPA().sendFrame126("", 1135);
		c.getPA().sendFrame126("", 1134);
		c.getPA().sendFrame126("", 11461);
		c.getPA().sendFrame126("", 11459);
		c.getPA().sendFrame126("", 1132);
		c.getPA().sendFrame126("", 1096);
		c.getPA().showInterface(994);
	}

	private static void makeRuneInterface(final Player c) {
		final String fiveb = SmithingInterface.GetForBars(2363, 5, c);
		final String threeb = SmithingInterface.GetForBars(2363, 3, c);
		final String twob = SmithingInterface.GetForBars(2363, 2, c);
		final String oneb = SmithingInterface.GetForBars(2363, 1, c);
		c.getPA().sendFrame126(fiveb + "5 Bars" + fiveb, 1112);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1109);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1110);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1118);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1111);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1095);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1115);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1090);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1113);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1116);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1114);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1089);
		c.getPA().sendFrame126(" ", 8428);
		c.getPA().sendFrame126(" ", 8429);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1124);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1125);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1126);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1127);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1128);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1129);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1130);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1131);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 13357);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 11459);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(88, c) + "Plate Body"
						+ SmithingInterface.GetForlvl(18, c), 1101);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(99, c) + "Plate Legs"
						+ SmithingInterface.GetForlvl(16, c), 1099);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(99, c) + "Plate Skirt"
						+ SmithingInterface.GetForlvl(16, c), 1100);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(99, c) + "2 Hand Sword"
						+ SmithingInterface.GetForlvl(14, c), 1088);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(97, c) + "Kite Shield"
						+ SmithingInterface.GetForlvl(12, c), 1105);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(96, c) + "Chain Body"
						+ SmithingInterface.GetForlvl(11, c), 1098);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(95, c) + "Battle Axe"
						+ SmithingInterface.GetForlvl(10, c), 1092);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(94, c) + "Warhammer"
						+ SmithingInterface.GetForlvl(9, c), 1083);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(93, c) + "Square Shield"
						+ SmithingInterface.GetForlvl(8, c), 1104);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(92, c) + "Full Helm"
						+ SmithingInterface.GetForlvl(7, c), 1103);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(92, c) + "Throwing Knives"
						+ SmithingInterface.GetForlvl(7, c), 1106);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(91, c) + "Long Sword"
						+ SmithingInterface.GetForlvl(6, c), 1086);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(90, c) + "Scimitar"
						+ SmithingInterface.GetForlvl(5, c), 1087);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(90, c) + "Arrowtips"
						+ SmithingInterface.GetForlvl(5, c), 1108);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(90, c) + "Bolts"
						+ SmithingInterface.GetForlvl(5, c), 1107);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(89, c) + "Sword"
						+ SmithingInterface.GetForlvl(4, c), 1085);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(89, c) + "Bolts"
						+ SmithingInterface.GetForlvl(4, c), 9144);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(89, c) + "Nails"
						+ SmithingInterface.GetForlvl(4, c), 13358);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(88, c) + "Medium Helm"
						+ SmithingInterface.GetForlvl(3, c), 1102);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(87, c) + "Mace"
						+ SmithingInterface.GetForlvl(2, c), 1093);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(85, c) + "Dagger"
						+ SmithingInterface.GetForlvl(1, c), 1094);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(86, c) + "Axe"
						+ SmithingInterface.GetForlvl(1, c), 1091);
		c.getPA().sendFrame34(1213, 0, 1119, 1); // dagger
		c.getPA().sendFrame34(1359, 0, 1120, 1); // axe
		c.getPA().sendFrame34(1113, 0, 1121, 1); // chain body
		c.getPA().sendFrame34(1147, 0, 1122, 1); // med helm
		c.getPA().sendFrame34(9381, 0, 1123, 10); // Bolts
		c.getPA().sendFrame34(1289, 1, 1119, 1); // s-sword
		c.getPA().sendFrame34(1432, 1, 1120, 1); // mace
		c.getPA().sendFrame34(1079, 1, 1121, 1); // platelegs
		c.getPA().sendFrame34(1163, 1, 1122, 1); // full helm
		c.getPA().sendFrame34(44, 1, 1123, 15); // arrowtips
		c.getPA().sendFrame34(1333, 2, 1119, 1); // scimmy
		c.getPA().sendFrame34(1347, 2, 1120, 1); // warhammer
		c.getPA().sendFrame34(1093, 2, 1121, 1); // plateskirt
		c.getPA().sendFrame34(1185, 2, 1122, 1); // Sq. Shield
		c.getPA().sendFrame34(868, 2, 1123, 5); // throwing-knives
		c.getPA().sendFrame34(1303, 3, 1119, 1); // longsword
		c.getPA().sendFrame34(1373, 3, 1120, 1); // battleaxe
		c.getPA().sendFrame34(1127, 3, 1121, 1); // platebody
		c.getPA().sendFrame34(1201, 3, 1122, 1); // kiteshield
		c.getPA().sendFrame34(1319, 4, 1119, 1); // 2h sword
		c.getPA().sendFrame34(4824, 4, 1122, 15); // nails
		c.getPA().sendFrame34(-1, 3, 1123, 1);
		c.getPA().sendFrame126("", 1135);
		c.getPA().sendFrame126("", 1134);
		c.getPA().sendFrame126("", 11461);
		c.getPA().sendFrame126("", 11459);
		c.getPA().sendFrame126("", 1132);
		c.getPA().sendFrame126("", 1096);
		c.getPA().sendFrame126(" ", 11460);
		c.getPA().showInterface(994);
	}

	private static void makeSteelInterface(final Player c) {
		final String fiveb = SmithingInterface.GetForBars(2353, 5, c);
		final String threeb = SmithingInterface.GetForBars(2353, 3, c);
		final String twob = SmithingInterface.GetForBars(2353, 2, c);
		final String oneb = SmithingInterface.GetForBars(2353, 1, c);
		c.getPA().sendFrame126(fiveb + "5 Bars" + fiveb, 1112);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1109);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1110);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1118);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1111);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1095);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1115);
		c.getPA().sendFrame126(threeb + "3 Bars" + threeb, 1090);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1113);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1116);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1114);
		c.getPA().sendFrame126(twob + "2 Bars" + twob, 1089);
		c.getPA().sendFrame126(" ", 8428);
		c.getPA().sendFrame126(" ", 8429);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1124);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1125);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1126);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1127);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1128);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1129);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1130);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1131);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 13357);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1132);
		c.getPA().sendFrame126(oneb + "1 Bar" + oneb, 1135);
		c.getPA().sendFrame126("", 11459);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(48, c) + "Plate Body"
						+ SmithingInterface.GetForlvl(18, c), 1101);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(46, c) + "Plate Legs"
						+ SmithingInterface.GetForlvl(16, c), 1099);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(46, c) + "Plate Skirt"
						+ SmithingInterface.GetForlvl(16, c), 1100);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(44, c) + "2 Hand Sword"
						+ SmithingInterface.GetForlvl(14, c), 1088);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(42, c) + "Kite Shield"
						+ SmithingInterface.GetForlvl(12, c), 1105);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(41, c) + "Chain Body"
						+ SmithingInterface.GetForlvl(11, c), 1098);
		c.getPA().sendFrame126("", 11461);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(40, c) + "Battle Axe"
						+ SmithingInterface.GetForlvl(10, c), 1092);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(39, c) + "Warhammer"
						+ SmithingInterface.GetForlvl(9, c), 1083);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(38, c) + "Square Shield"
						+ SmithingInterface.GetForlvl(8, c), 1104);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(37, c) + "Full Helm"
						+ SmithingInterface.GetForlvl(7, c), 1103);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(37, c) + "Throwing Knives"
						+ SmithingInterface.GetForlvl(7, c), 1106);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(36, c) + "Long Sword"
						+ SmithingInterface.GetForlvl(6, c), 1086);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(35, c) + "Scimitar"
						+ SmithingInterface.GetForlvl(5, c), 1087);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(35, c) + "Arrowtips"
						+ SmithingInterface.GetForlvl(5, c), 1108);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(35, c) + "Bolts"
						+ SmithingInterface.GetForlvl(5, c), 1107);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(34, c) + "Sword"
						+ SmithingInterface.GetForlvl(4, c), 1085);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(34, c) + "Bolts"
						+ SmithingInterface.GetForlvl(4, c), 9141);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(34, c) + "Nails"
						+ SmithingInterface.GetForlvl(4, c), 13358);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(33, c) + "Medium Helm"
						+ SmithingInterface.GetForlvl(3, c), 1102);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(32, c) + "Mace"
						+ SmithingInterface.GetForlvl(2, c), 1093);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(30, c) + "Dagger"
						+ SmithingInterface.GetForlvl(1, c), 1094);
		c.getPA().sendFrame126(
				SmithingInterface.GetForlvl(31, c) + "Axe"
						+ SmithingInterface.GetForlvl(1, c), 1091);
		c.getPA().sendFrame126(" ", 1096);
		c.getPA().sendFrame126(" ", 1134);
		c.getPA().sendFrame34(1207, 0, 1119, 1);
		c.getPA().sendFrame34(1353, 0, 1120, 1);
		c.getPA().sendFrame34(1105, 0, 1121, 1);
		c.getPA().sendFrame34(1141, 0, 1122, 1);
		c.getPA().sendFrame34(9378, 0, 1123, 10);
		c.getPA().sendFrame34(1281, 1, 1119, 1);
		c.getPA().sendFrame34(1424, 1, 1120, 1);
		c.getPA().sendFrame34(1069, 1, 1121, 1);
		c.getPA().sendFrame34(1157, 1, 1122, 1);
		c.getPA().sendFrame34(41, 1, 1123, 15);
		c.getPA().sendFrame34(1325, 2, 1119, 1);
		c.getPA().sendFrame34(1339, 2, 1120, 1);
		c.getPA().sendFrame34(1083, 2, 1121, 1);
		c.getPA().sendFrame34(1177, 2, 1122, 1);
		c.getPA().sendFrame34(865, 2, 1123, 5);
		c.getPA().sendFrame34(1295, 3, 1119, 1);
		c.getPA().sendFrame34(1365, 3, 1120, 1);
		c.getPA().sendFrame34(1119, 3, 1121, 1);
		c.getPA().sendFrame34(1193, 3, 1122, 1);
		c.getPA().sendFrame34(1311, 4, 1119, 1);
		c.getPA().sendFrame34(1539, 4, 1122, 15);
		c.getPA().showInterface(994);
	}

	Player c;

	public SmithingInterface(final Player c) {
		this.c = c;
	}

	public void showSmithInterface(final int itemId) {
		if (itemId == 2349) {
			SmithingInterface.makeBronzeInterface(c);
		} else if (itemId == 2351) {
			SmithingInterface.makeIronInterface(c);
		} else if (itemId == 2353) {
			SmithingInterface.makeSteelInterface(c);
		} else if (itemId == 2359) {
			SmithingInterface.makeMithInterface(c);
		} else if (itemId == 2361) {
			SmithingInterface.makeAddyInterface(c);
		} else if (itemId == 2363) {
			SmithingInterface.makeRuneInterface(c);
		}
	}

}
