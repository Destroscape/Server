package com.item;

public class CustomItems extends ItemDef {

	public static ItemDef forID(int i) {
		for (int j = 0; j < 10; j++)
			if (cache[j].ID == i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 10;
		ItemDef itemDef = cache[cacheIndex];
		stream.currentOffset = streamIndices[i];
		itemDef.ID = i;
		itemDef.setDefaults();
		itemDef.readValues(stream);
		if (itemDef.certTemplateID != -1)
			itemDef.toNote();
		if (itemDef.lentItemID != -1)
			itemDef.toLend();
		if (!isMembers && itemDef.membersObject) {
			itemDef.name = "Members Object";
			itemDef.description = "Login to a members' server to use this object.";
			itemDef.groundActions = null;
			itemDef.actions = null;
			itemDef.team = 0;
		}
		if (itemDef.editedModelColor != null) {
			for (int i2 = 0; i2 < itemDef.editedModelColor.length; i2++) {
				if (itemDef.newModelColor[i2] == 0) {
					itemDef.newModelColor[i2] = 1;
				}
			}
		}

		int[] BLACK_FIX = { 6568, 10636, 12158, 12159, 12160, 12161, 12162,
				12163, 12164, 12165, 12166, 12167, 12168, 12527, 18017, 18018,
				18019, 18020, 3140, 13481, 14479, 14481, 19337, 19342 };
		for (int a : BLACK_FIX) {
			if (itemDef.ID == a) {
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 0;
				itemDef.newModelColor[0] = 1;
			}
		}

		switch (i) {

		case 20147:
		   	itemDef.editedModelColor = new int[2];
  			itemDef.newModelColor = new int[2];
   			itemDef.editedModelColor[0] = 4550;
   			itemDef.newModelColor[0] = 6020;
   			itemDef.editedModelColor[1] = 4540;
   			itemDef.newModelColor[1] = 6020;
		break;
		
		case 10181:
			itemDef.name = "Try Your Luck Box @bla@[@yel@Easy@bla@]";
			itemDef.description = "Try out your luck with this try your luck box [EASY].";
			itemDef.actions[0] = "Open";
			break;		
		case 13077:
			itemDef.name = "Try Your Luck Box @bla@[@yel@Medium@bla@]";
			itemDef.description = "Try out your luck with this try your luck box [MEDIUM].";
			itemDef.actions[0] = "Open";
			break;				
		case 19040:
			itemDef.name = "Try Your Luck Box @bla@[@yel@Elite@bla@]";
			itemDef.description = "Try out your luck with this try your luck box [ELITE].";
			itemDef.actions[0] = "Open";
			break;
		
		case 9477:
			itemDef.name = "Remote Deposit Box";
			itemDef.description = "A Remotely Accessable Bank Box.";
			break;
			
		case 15674:
			itemDef.name = "Box of Flasks";
			itemDef.description = "A Box Filled With Flasks.";
			itemDef.actions[0] = "Open";
			break;
			
		case 10025:
			itemDef.name = "Box of Charms";
			itemDef.description = "A Box Filled With Charms.";
			break;
		
		case 18336:
			itemDef.name = "Scroll of Imbuedment";
			break;

		case 19771:
			itemDef.name = "Double XP Ring";
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 13562:
			itemDef.name = "Double XP Ring";
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 9952:
			itemDef.name = "Charming Imp";
			itemDef.description = "A Charming Imp, used to collect charms.";
			itemDef.actions = new String[] { null, null, "Configure", null, "Drop" };
			break;

		case 15046:
			itemDef.name = "Necromancer's Skull";
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			break;

		case 2653: //Zamorak plate
			itemDef.maleEquip1 = 3379;
			itemDef.femaleEquip1 = 3379;
			itemDef.modelOffset1 = -1;
			itemDef.modelID = 3375;
			itemDef.modelZoom = 1180;
			itemDef.modelOffset2 = -1;
			itemDef.modelRotation1 = 452;
			itemDef.modelRotation2 = 452;
			break;

		case 23639:
			itemDef.name = "TokHaar-Kal";
			itemDef.value = 60000;
			itemDef.maleEquip1 = 62575;
			itemDef.femaleEquip1 = 62582;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.modelOffset1 = -4;
			itemDef.modelID = 62592;
			itemDef.stackable = false;
			itemDef.description = "A cape made of ancient, enchanted obsidian.";
			itemDef.modelZoom = 1616;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelOffset2 = 0;
			itemDef.modelRotation1 = 339;
			itemDef.modelRotation2 = 192;
			break;

		case 23191:
			itemDef.name = "Potion flask";
			itemDef.modelZoom = 804;
			itemDef.modelRotation2 = 131;
			itemDef.modelRotation1 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.groundActions[2] = "Take";
			itemDef.actions[4] = "Drop";
			itemDef.modelID = 61741;
			break;


		case 23243:
			itemDef.name = "Prayer flask (6)";
			itemDef.description = "6 doses of prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 28488 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23245:
			itemDef.name = "Prayer flask (5)";
			itemDef.description = "5 doses of prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 28488 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23247:
			itemDef.name = "Prayer flask (4)";
			itemDef.description = "4 doses of prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 28488 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23249:
			itemDef.name = "Prayer flask (3)";
			itemDef.description = "3 doses of prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 28488 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23251:
			itemDef.name = "Prayer flask (2)";
			itemDef.description = "2 doses of prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 28488 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23253:
			itemDef.name = "Prayer flask (1)";
			itemDef.description = "1 dose of prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 28488 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23255:
			itemDef.name = "Super attack flask (6)";
			itemDef.description = "6 doses of super attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 43848 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23257:
			itemDef.name = "Super attack flask (5)";
			itemDef.description = "5 doses of super attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 43848 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23259:
			itemDef.name = "Super attack flask (4)";
			itemDef.description = "4 doses of super attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 43848 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23261:
			itemDef.name = "Super attack flask (3)";
			itemDef.description = "3 doses of super attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 43848 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23263:
			itemDef.name = "Super attack flask (2)";
			itemDef.description = "2 doses of super attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 43848 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23265:
			itemDef.name = "Super attack flask (1)";
			itemDef.description = "1 dose of super attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 43848 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23279:
			itemDef.name = "Super strength flask (6)";
			itemDef.description = "6 doses of super strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 119 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23281:
			itemDef.name = "Super strength flask (5)";
			itemDef.description = "5 doses of super strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 119 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23283:
			itemDef.name = "Super strength flask (4)";
			itemDef.description = "4 doses of super strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 119 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23285:
			itemDef.name = "Super strength flask (3)";
			itemDef.description = "3 doses of super strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 119 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23287:
			itemDef.name = "Super strength flask (2)";
			itemDef.description = "2 doses of super strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 119 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23289:
			itemDef.name = "Super strength flask (1)";
			itemDef.description = "1 dose of super strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 119 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23291:
			itemDef.name = "Super defence flask (6)";
			itemDef.description = "6 doses of super defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 8008 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23293:
			itemDef.name = "Super defence flask (5)";
			itemDef.description = "5 doses of super defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 8008 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23295:
			itemDef.name = "Super defence flask (4)";
			itemDef.description = "4 doses of super defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 8008 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23297:
			itemDef.name = "Super defence flask (3)";
			itemDef.description = "3 doses of super defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 8008 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23299:
			itemDef.name = "Super defence flask (2)";
			itemDef.description = "2 doses of super defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 8008 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23301:
			itemDef.name = "Super defence flask (1)";
			itemDef.description = "1 dose of super defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 8008 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23303:
			itemDef.name = "Ranging flask (6)";
			itemDef.description = "6 doses of ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 36680 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23305:
			itemDef.name = "Ranging flask (5)";
			itemDef.description = "5 doses of ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 36680 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23307:
			itemDef.name = "Ranging flask (4)";
			itemDef.description = "4 doses of ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 36680 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23309:
			itemDef.name = "Ranging flask (3)";
			itemDef.description = "3 doses of ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 36680 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23311:
			itemDef.name = "Ranging flask (2)";
			itemDef.description = "2 doses of ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 36680 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23313:
			itemDef.name = "Ranging flask (1)";
			itemDef.description = "1 dose of ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 36680 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23327:
			itemDef.name = "Super antipoison flask (6)";
			itemDef.description = "6 doses of super antipoison.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62404 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23329:
			itemDef.name = "Super antipoison flask (5)";
			itemDef.description = "5 doses of super antipoison.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62404 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23331:
			itemDef.name = "Super antipoison flask (4)";
			itemDef.description = "4 doses of super antipoison.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62404 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23333:
			itemDef.name = "Super antipoison flask (3)";
			itemDef.description = "3 doses of super antipoison.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62404 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23335:
			itemDef.name = "Super antipoison flask (2)";
			itemDef.description = "2 doses of super antipoison.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62404 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23337:
			itemDef.name = "Super antipoison flask (1)";
			itemDef.description = "1 dose of super antipoison.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62404 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23351:
			itemDef.name = "Saradomin brew flask (6)";
			itemDef.description = "6 doses of saradomin brew.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10939 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;
			break;

		case 23353:
			itemDef.name = "Saradomin brew flask (5)";
			itemDef.description = "5 doses of saradomin brew.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10939 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;
			break;

		case 23355:
			itemDef.name = "Saradomin brew flask (4)";
			itemDef.description = "4 doses of saradomin brew.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10939 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;
			break;

		case 23357:
			itemDef.name = "Saradomin brew flask (3)";
			itemDef.description = "3 doses of saradomin brew.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10939 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;
			break;

		case 23359:
			itemDef.name = "Saradomin brew flask (2)";
			itemDef.description = "2 doses of saradomin brew.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10939 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;
			break;

		case 23361:
			itemDef.name = "Saradomin brew flask (1)";
			itemDef.description = "1 dose of saradomin brew.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10939 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;
			break;

		case 23399:
			itemDef.name = "Super restore flask (6)";
			itemDef.description = "6 doses of super restore potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62135 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23401:
			itemDef.name = "Super restore flask (5)";
			itemDef.description = "5 doses of super restore potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62135 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23403:
			itemDef.name = "Super restore flask (4)";
			itemDef.description = "4 doses of super restore potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62135 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23405:
			itemDef.name = "Super restore flask (3)";
			itemDef.description = "3 doses of super restore potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62135 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23407:
			itemDef.name = "Super restore flask (2)";
			itemDef.description = "2 doses of super restore potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62135 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23409:
			itemDef.name = "Super restore flask (1)";
			itemDef.description = "1 dose of super restore potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 62135 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23423:
			itemDef.name = "Magic flask (6)";
			itemDef.description = "6 doses of magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 37440 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23425:
			itemDef.name = "Magic flask (5)";
			itemDef.description = "5 doses of magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 37440 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23427:
			itemDef.name = "Magic flask (4)";
			itemDef.description = "4 doses of magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 37440 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23429:
			itemDef.name = "Magic flask (3)";
			itemDef.description = "3 doses of magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 37440 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23431:
			itemDef.name = "Magic flask (2)";
			itemDef.description = "2 doses of magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 37440 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23433:
			itemDef.name = "Magic flask (1)";
			itemDef.description = "1 dose of magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 37440 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23483:
			itemDef.name = "Recover special flask (6)";
			itemDef.description = "6 doses of recover special.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 38222 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23484:
			itemDef.name = "Recover special flask (5)";
			itemDef.description = "5 doses of recover special.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 38222 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23485:
			itemDef.name = "Recover special flask (4)";
			itemDef.description = "4 doses of recover special.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 38222 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23486:
			itemDef.name = "Recover special flask (3)";
			itemDef.description = "3 doses of recover special.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 38222 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23487:
			itemDef.name = "Recover special flask (2)";
			itemDef.description = "2 doses of recover special.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 38222 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23488:
			itemDef.name = "Recover special flask (1)";
			itemDef.description = "1 dose of recover special.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 38222 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23495:
			itemDef.name = "Extreme attack flask (6)";
			itemDef.description = "6 doses of extreme attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33112 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23496:
			itemDef.name = "Extreme attack flask (5)";
			itemDef.description = "5 doses of extreme attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33112 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23497:
			itemDef.name = "Extreme attack flask (4)";
			itemDef.description = "4 doses of extreme attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33112 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23498:
			itemDef.name = "Extreme attack flask (3)";
			itemDef.description = "3 doses of extreme attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33112 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23499:
			itemDef.name = "Extreme attack flask (2)";
			itemDef.description = "2 doses of extreme attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33112 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23500:
			itemDef.name = "Extreme attack flask (1)";
			itemDef.description = "1 dose of extreme attack potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33112 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23501:
			itemDef.name = "Extreme strength flask (6)";
			itemDef.description = "6 doses of extreme strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 127 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23502:
			itemDef.name = "Extreme strength flask (5)";
			itemDef.description = "5 doses of extreme strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 127 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23503:
			itemDef.name = "Extreme strength flask (4)";
			itemDef.description = "4 doses of extreme strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 127 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23504:
			itemDef.name = "Extreme strength flask (3)";
			itemDef.description = "3 doses of extreme strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 127 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23505:
			itemDef.name = "Extreme strength flask (2)";
			itemDef.description = "2 doses of extreme strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 127 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23506:
			itemDef.name = "Extreme strength flask (1)";
			itemDef.description = "1 dose of extreme strength potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 127 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23507:
			itemDef.name = "Extreme defence flask (6)";
			itemDef.description = "6 doses of extreme defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10198 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23508:
			itemDef.name = "Extreme defence flask (5)";
			itemDef.description = "5 doses of extreme defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10198 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23509:
			itemDef.name = "Extreme defence flask (4)";
			itemDef.description = "4 doses of extreme defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10198 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23510:
			itemDef.name = "Extreme defence flask (3)";
			itemDef.description = "3 doses of extreme defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10198 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23511:
			itemDef.name = "Extreme defence flask (2)";
			itemDef.description = "2 doses of extreme defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10198 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23512:
			itemDef.name = "Extreme defence flask (1)";
			itemDef.description = "1 dose of extreme defence potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 10198 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23513:
			itemDef.name = "Extreme magic flask (6)";
			itemDef.description = "6 doses of extreme magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33490 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23514:
			itemDef.name = "Extreme magic flask (5)";
			itemDef.description = "5 doses of extreme magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33490 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23515:
			itemDef.name = "Extreme magic flask (4)";
			itemDef.description = "4 doses of extreme magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33490 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23516:
			itemDef.name = "Extreme magic flask (3)";
			itemDef.description = "3 doses of extreme magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33490 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23517:
			itemDef.name = "Extreme magic flask (2)";
			itemDef.description = "2 doses of extreme magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33490 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23518:
			itemDef.name = "Extreme magic flask (1)";
			itemDef.description = "1 dose of extreme magic potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 33490 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23519:
			itemDef.name = "Extreme ranging flask (6)";
			itemDef.description = "6 doses of extreme ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 13111 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23520:
			itemDef.name = "Extreme ranging flask (5)";
			itemDef.description = "5 doses of extreme ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 13111 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23521:
			itemDef.name = "Extreme ranging flask (4)";
			itemDef.description = "4 doses of extreme ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 13111 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23522:
			itemDef.name = "Extreme ranging flask (3)";
			itemDef.description = " 3 doses of extreme ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 13111 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23523:
			itemDef.name = "Extreme ranging flask (2)";
			itemDef.description = "2 doses of extreme ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 13111 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23524:
			itemDef.name = "Extreme ranging flask (1)";
			itemDef.description = "1 dose of extreme ranging potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 13111 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23525:
			itemDef.name = "Super prayer flask (6)";
			itemDef.description = "6 doses of super prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 3016 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23526:
			itemDef.name = "Super prayer flask (5)";
			itemDef.description = "5 doses of super prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 3016 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23527:
			itemDef.name = "Super prayer flask (4)";
			itemDef.description = "4 doses of super prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 3016 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23528:
			itemDef.name = "Super prayer flask (3)";
			itemDef.description = "3 doses of super prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 3016 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23529:
			itemDef.name = "Super prayer flask (2)";
			itemDef.description = "2 doses of super prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 3016 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23530:
			itemDef.name = "Super prayer flask (1)";
			itemDef.description = "1 dose of super prayer potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 3016 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23531:
			itemDef.name = "Overload flask (6)";
			itemDef.description = "6 doses of overload potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 0 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23532:
			itemDef.name = "Overload flask (5)";
			itemDef.description = "5 doses of overload potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 0 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23533:
			itemDef.name = "Overload flask (4)";
			itemDef.description = "4 doses of overload potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 0 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23534:
			itemDef.name = "Overload flask (3)";
			itemDef.description = "3 doses of overload potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 0 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23535:
			itemDef.name = "Overload flask (2)";
			itemDef.description = "2 doses of overload potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 0 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23536:
			itemDef.name = "Overload flask (1)";
			itemDef.description = "1 dose of overload potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 0 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 23609:
			itemDef.name = "Prayer renewal flask (6)";
			itemDef.description = "6 doses of prayer renewal.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 926 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61732;
			break;

		case 23611:
			itemDef.name = "Prayer renewal flask (5)";
			itemDef.description = "5 doses of prayer renewal.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 926 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61729;
			break;

		case 23613:
			itemDef.name = "Prayer renewal flask (4)";
			itemDef.description = "4 doses of prayer renewal potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 926 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61764;
			break;

		case 23615:
			itemDef.name = "Prayer renewal flask (3)";
			itemDef.description = "3 doses of prayer renewal potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 926 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61727;
			break;

		case 23617:
			itemDef.name = "Prayer renewal flask (2)";
			itemDef.description = "2 doses of prayer renewal potion.";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 926 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61731;
			break;

		case 23619:
			itemDef.name = "Prayer renewal flask (1)";
			itemDef.description = "1 dose of prayer renewal potion";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 131;
			itemDef.modelRotation2 = 198;
			itemDef.modelOffset2 = 1;
			itemDef.modelOffset1 = -1;
			itemDef.newModelColor = new int[] { 926 };
			itemDef.editedModelColor  = new int[] { 33715 };
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { "Drink", null, null, null, "Drop" };
			itemDef.modelID = 61812;
			break;

		case 22482:
			itemDef.name = "Ganodermic visor";
			itemDef.description = "It's an Ganodermic visor";
			itemDef.actions = new String[] { null, "Wear", "Check", "Clean",
			"Drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 10935;
			itemDef.maleEquip1 = 10373;
			itemDef.femaleEquip1 = 10523;
			itemDef.modelZoom = 1118;
			itemDef.modelRotation1 = 215;
			itemDef.modelRotation2 = 175;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -30;
			break;

		case 22490:
			itemDef.name = "Ganodermic poncho";
			itemDef.description = "It's an Ganodermic poncho";
			itemDef.actions = new String[] { null, "Wear", "Check", "Clean",
			"Drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 10919;
			itemDef.maleEquip1 = 10490;
			itemDef.femaleEquip1 = 10664;
			itemDef.modelZoom = 1513;
			itemDef.modelRotation1 = 485;
			itemDef.modelRotation2 = 13;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			break;
		case 22486:
			itemDef.name = "Ganodermic leggings";
			itemDef.description = "It's an Ganodermic leggings";
			itemDef.actions = new String[] { null, "Wear", "Check", null,
			"Drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 10942;
			itemDef.maleEquip1 = 10486;
			itemDef.femaleEquip1 = 10578;
			itemDef.modelZoom = 1513;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = 8;
			itemDef.modelOffset2 = -18;
			break;

		case 22494:
			itemDef.name = "Polypore staff";
			itemDef.description = "It's an Polypore staff";
			itemDef.actions = new String[] { null, "Wield", "Check", null,
			"Drop" };
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.modelID = 13426;
			itemDef.maleEquip1 = 13417;
			itemDef.femaleEquip1 = 13417;
			itemDef.modelZoom = 3750;
			itemDef.modelRotation1 = 1454;
			itemDef.modelRotation2 = 997;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 8;
			break;

		case 22496:
			itemDef.name = "Polypore staff (degraded)";
			itemDef.modelZoom = 3750;
			itemDef.modelRotation2 = 1454;
			itemDef.modelRotation1 = 997;
			itemDef.modelOffset1 = 8;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.actions = new String[] { null, "Wield", "Check", "Clean",
			"Drop" };
			itemDef.modelID = 13426;
			itemDef.maleEquip1 = 13417;
			itemDef.femaleEquip1 = 13417;
			break;

		case 14001: // black
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 1; // top
			itemDef.newModelColor[1] = 1; // top
			itemDef.newModelColor[2] = 1; // bottom
			itemDef.newModelColor[3] = 1; // bottom
			break;

		case 14002: // grey
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 10388; // top
			itemDef.newModelColor[1] = 10388; // top
			itemDef.newModelColor[2] = 10388; // bottom
			itemDef.newModelColor[3] = 10388; // bottom
			break;

		case 14003: // white
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 127; // top
			itemDef.newModelColor[1] = 127; // top
			itemDef.newModelColor[2] = 127; // bottom
			itemDef.newModelColor[3] = 127; // bottom
			break;

		case 14004: // blue
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 43848; // top
			itemDef.newModelColor[1] = 43848; // top
			itemDef.newModelColor[2] = 43848; // bottom
			itemDef.newModelColor[3] = 43848; // bottom
			break;

		case 14005: // green
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 22428; // top
			itemDef.newModelColor[1] = 22428; // top
			itemDef.newModelColor[2] = 22428; // bottom
			itemDef.newModelColor[3] = 22428; // bottom
			break;

		case 14006: // cyan
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 34503; // top
			itemDef.newModelColor[1] = 34503; // top
			itemDef.newModelColor[2] = 34503; // bottom
			itemDef.newModelColor[3] = 34503; // bottom
			break;

		case 14007: // red
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 926; // top
			itemDef.newModelColor[1] = 926; // top
			itemDef.newModelColor[2] = 926; // bottom
			itemDef.newModelColor[3] = 926; // bottom
			break;

		case 14008: // purple
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 44603; // top
			itemDef.newModelColor[1] = 44603; // top
			itemDef.newModelColor[2] = 44603; // bottom
			itemDef.newModelColor[3] = 44603; // bottom
			break;

		case 14009: // yellow
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 10939; // top
			itemDef.newModelColor[1] = 10939; // top
			itemDef.newModelColor[2] = 10939; // bottom
			itemDef.newModelColor[3] = 10939; // bottom
			break;

		case 14010: // orange
			itemDef.modelID = 65270;
			itemDef.name = "Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.maleEquip1 = 65297;
			itemDef.femaleEquip1 = 65316;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Customise";
			itemDef.actions[3] = "Features";
			itemDef.actions[4] = "Destroy";
			itemDef.editedModelColor = new int[4];
			itemDef.newModelColor = new int[4];
			itemDef.editedModelColor[0] = 65214;
			itemDef.editedModelColor[1] = 65200;
			itemDef.editedModelColor[2] = 65186;
			itemDef.editedModelColor[3] = 62995;
			itemDef.newModelColor[0] = 3016; // top
			itemDef.newModelColor[1] = 3016; // top
			itemDef.newModelColor[2] = 3016; // bottom
			itemDef.newModelColor[3] = 3016; // bottom
			break;


		case 15367:
			itemDef.name = "Seed Box";
			itemDef.description = "Box of Seeds";
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.actions[4] = "Drop";
			break;

		case 15262:
			itemDef.name = "Spirit shard package";
			itemDef.description = "Open this for 25,000 spirit shards.";
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.actions[1] = null;
			itemDef.actions[2] = null;
			itemDef.actions[3] = null;
			itemDef.actions[4] = "Drop";
			break;

		case 15707:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open Interface";
			itemDef.actions[1] = "Wear";
			itemDef.actions[2] = "Teleport To Lobby";
			itemDef.actions[4] = "Drop";
			break;
		}

		return itemDef;
	}

}
