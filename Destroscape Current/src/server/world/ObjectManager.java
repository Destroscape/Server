package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	
	public void process() {
		for (final Object o : objects) {
			if (o.tick > 0) {
				o.tick--;
			} else {
				updateObject(o);
				toRemove.add(o);
			}
		}
		for (final Object o : toRemove) {
			if (o.objectId == 2732) {
				for (final Player player : PlayerHandler.players) {
					if (player != null) {
						final Client c = (Client)player;
						Server.itemHandler.createGroundItem(c, 592, o.objectX, o.objectY, 1, c.playerId);
					}
				}
			}
			if (isObelisk(o.newId)) {
				final int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
	                //New Donor Area
					c.getPA().checkObjectSpawn(-1, 2849, 3353, 0, 10); // prayer banking
					c.getPA().checkObjectSpawn(-1, 2851, 3353, 0, 10); // prayer banking
					c.getPA().checkObjectSpawn(-1, 2853, 3353, 0, 10); // prayer banking end of del
					c.getPA().checkObjectSpawn(26972, 2849, 3353, 0, 10); // prayer banking 
					c.getPA().checkObjectSpawn(26972, 2850, 3353, 0, 10); // prayer banking
					c.getPA().checkObjectSpawn(26972, 2851, 3353, 0, 10); // prayer banking
					c.getPA().checkObjectSpawn(26972, 2852, 3353, 0, 10); // prayer banking
					c.getPA().checkObjectSpawn(26972, 2853, 3353, 0, 10); // prayer banking
					c.getPA().checkObjectSpawn(409, 2853, 3348, 1, 10); // prayer
					c.getPA().checkObjectSpawn(-1, 2851, 3341, 0, 10); //a door
					c.getPA().checkObjectSpawn(-1, 2831, 3353, 0, 10); // smith room
					c.getPA().checkObjectSpawn(-1, 2830, 3353, 0, 10); // smith room
					c.getPA().checkObjectSpawn(-1, 2829, 3353, 0, 10); // smith room
					c.getPA().checkObjectSpawn(-1, 2829, 3354, 0, 10); // smith room
					c.getPA().checkObjectSpawn(2782, 2830, 3352, 0, 10); // smith room
					c.getPA().checkObjectSpawn(2782, 2831, 3352, 0, 10); // smith room
					c.getPA().checkObjectSpawn(26972, 2832, 3346, 0, 10); // smith room
					c.getPA().checkObjectSpawn(-1, 2812, 3338, 0, 10); // farm
					c.getPA().checkObjectSpawn(-1, 2812, 3335, 0, 10); // farm
					c.getPA().checkObjectSpawn(-1, 2809, 3335, 0, 10); // farm
					c.getPA().checkObjectSpawn(-1, 2809, 3338, 0, 10); // farm
					//Edgeville deletes
                c.getPA().checkObjectSpawn(-1, 2849, 3496, 0, 10); // Edgeville bank removeing objects in bank Chair
	        c.getPA().checkObjectSpawn(-1, 3092, 3496, 0, 10); // Edgeville bank removeing objects in bank Chair
	        c.getPA().checkObjectSpawn(-1, 3091, 3495, 0, 10); // Edgeville bank removeing objects in bank Table
                c.getPA().checkObjectSpawn(-1, 3095, 3499, -1, 10); //REMOVE CHAIR IN BANK
                c.getPA().checkObjectSpawn(-1, 3090, 3494, -1, 10); //REMOVE CHAIR IN BANK
	        c.getPA().checkObjectSpawn(-1, 3095, 3498, -1, 10); //REMOVE TABLE IN BANK
	        c.getPA().checkObjectSpawn(-1, 3078, 3510, -1, 10); //REMOVE TABLE IN SHOP
	        c.getPA().checkObjectSpawn(-1, 3080, 3510, -1, 10); //REMOVE BOXES in shop
	        c.getPA().checkObjectSpawn(-1, 3077, 3512, -1, 10); //REMOVE STEPLADDERS
	        c.getPA().checkObjectSpawn(-1, 3081, 3510, -1, 10); //REMOVE TABLE IN SHOP
	        c.getPA().checkObjectSpawn(-1, 3098, 3496, -1, 10); //REMOVE TREE AT HOME
	        c.getPA().checkObjectSpawn(-1, 3096, 3501, -1, 10); //REMOVE TREE AT HOME
	c.getPA().checkObjectSpawn(172, 3104, 3505, 3, 10); //C key chest
	c.getPA().checkObjectSpawn(11356, 2526, 4784, 0, 10); //frost dragon portals
	c.getPA().checkObjectSpawn(13409, 2843, 3492, 4, 10);//Nomad - Dungeon
	c.getPA().checkObjectSpawn(3515, 3091, 3500, 2, 10); //Max cape rake
		c.getPA().checkObjectSpawn(4874, 3091, 3486, 0, 10); //1 stall
		c.getPA().checkObjectSpawn(4875, 3093, 3486, 0, 10); //25 stall
		c.getPA().checkObjectSpawn(4876, 3095, 3486, 0, 10); //50 stall
		c.getPA().checkObjectSpawn(4877, 3097, 3486, 0, 10); //75 stall
		c.getPA().checkObjectSpawn(4878, 3099, 3486, 0, 10); //90 stall
		c.getPA().checkObjectSpawn(409, 3094, 3500, 0, 10); //Alter at home
		c.getPA().checkObjectSpawn(2480, 2716, 4835, 0, 10); //Water Alter
		c.getPA().checkObjectSpawn(410, 3091, 3505, 0, 10); //Gutix Alter
		c.getPA().checkObjectSpawn(411, 3097, 3500, 0, 10); //weird pray shit, lol at home
		c.getPA().checkObjectSpawn(6552, 3099, 3506, 2, 10); //Ancient alter
		c.getPA().checkObjectSpawn(4008, 3095, 3506, 2, 10); //spec alter
		c.getPA().checkObjectSpawn(104, 2522, 4780, 1, 10); //Donatorchest
		c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); //portal home FunPk
		c.getPA().checkObjectSpawn(1032, 2605, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2603, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3155, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3153, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2536, 4778, 0, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2537, 4777, 1, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2536, 4776, 2, 10); //warning sign donor
		c.getPA().checkObjectSpawn(7315, 2536, 4777, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(7316, 2605, 3153, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(4008, 2851, 2965, 1, 10); //spec alter
		c.getPA().checkObjectSpawn(11356, 2836, 2977, 0, 10); //frost dragon portals
		c.getPA().checkObjectSpawn(8972, 2474, 3440, 0, 10); //Strykeworms portal
		c.getPA().checkObjectSpawn(194, 2423, 3525, 0, 10); //Dungeoneering Rock
		c.getPA().checkObjectSpawn(16081, 1879, 4620, 0, 10); //Dungeoneering lvl 1 tele
		c.getPA().checkObjectSpawn(2014, 1921, 4640, 0, 10); //Dungeoneering Money
		c.getPA().checkObjectSpawn(16078, 1869, 4622, 0, 10); //Dungeoneering Rope
		c.getPA().checkObjectSpawn(2930, 2383, 4714, 3, 10); //Dungeoneering Boss 1 door
		c.getPA().checkObjectSpawn(1032, 2382, 4714, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(79, 3044, 5105, 1, 10); //dungie blocker
		c.getPA().checkObjectSpawn(10778, 2867, 9530, 1, 10); //dung floor 4 portal
		c.getPA().checkObjectSpawn(7272, 3233, 9316, 1, 10); //dung floor 5 portal
		c.getPA().checkObjectSpawn(4408, 2869, 9949, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(410, 1860, 4625, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(6552, 1859, 4617, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(7318, 2772, 4454, 1, 10); //dung floor 7 portalEND
		c.getPA().checkObjectSpawn(4412, 1919, 4640, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3048, 5233, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2980, 5111, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2867, 9527, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3234, 9327, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2387, 4721, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2429, 4680, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2790, 9328, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3060, 5209, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3229, 9312, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2864, 9950, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2805, 4440, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2744, 4453, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3017, 5243, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2427, 9411, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(2465, 2422, 9429, 0, 10); //escape ladder
				c.getPA().checkObjectSpawn(2213, 2530, 4776, 0, 10); //1 stall
                c.getPA().checkObjectSpawn(2094, 3032, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2094, 3033, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3034, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3035, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3036, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3037, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3038, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3039, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3040, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3041, 9836, 0, 10);
                c.getPA().checkObjectSpawn(14859, 3042, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3043, 9836, 0, 10);
                c.getPA().checkObjectSpawn(3044, 3036, 9831, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3037, 9835, -1, 10);
                c.getPA().checkObjectSpawn(2783, 3034, 9832, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3495, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3079, 3501, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3080, 3501, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2598, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4779, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4778, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2598, 4777, 1, 10);
		c.getPA().checkObjectSpawn(12356, 2845, 2957, 1, 10);
				c.getPA().checkObjectSpawn(2403, 2844, 2957, 2, 10);
		c.getPA().checkObjectSpawn(2996, 2854, 2962, 1, 10);//al key chest
	
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.
	    c.getPA().checkObjectSpawn(14859, 2520, 4773, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4775, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4774, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(13617, 2527, 4770, 2, 10); //Barrelportal donor	
		
		c.getPA().checkObjectSpawn(411, 2862, 2965, 1, 10); // Curse Prayers

		
		c.getPA().checkObjectSpawn(13615, 2525, 4770, 2, 10); // hill giants donor
		c.getPA().checkObjectSpawn(13620, 2523, 4770, 2, 10); // steel drags donor
		c.getPA().checkObjectSpawn(13619, 2521, 4770, 2, 10); // tormented demons donor


		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10);

		c.getPA().checkObjectSpawn(410, 2864, 2955, 1, 10); 

		c.getPA().checkObjectSpawn(4874, 2849, 2995, 1, 10);
		c.getPA().checkObjectSpawn(4875, 2849, 2996, 1, 10);
		c.getPA().checkObjectSpawn(4876, 2849, 2997, 0, 10);
		c.getPA().checkObjectSpawn(4877, 2849, 2998, 0, 10);
		c.getPA().checkObjectSpawn(4878, 2849, 2999, 0, 10);

		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(6552, 2842, 2954, 1, 10); //ancient prayers
		c.getPA().checkObjectSpawn(409, 2852, 2950, 2, 10);
		c.getPA().checkObjectSpawn(409, 2530, 4779, 3, 10);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4780, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4775, 1, 10);
		c.getPA().checkObjectSpawn(1530, 3093, 3487, 1, 10);

                                          //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1278, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2429, 9416, 0, 10);//cooking range dung!
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
		c.getPA().checkObjectSpawn(104, 2522, 4780, 1, 10); //Donatorchest
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
		//new home
		c.getPA().checkObjectSpawn(-1, 2956, 3212, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2952, 3211, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2958, 3207, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2960, 3202, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2957, 3202, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2956, 3202, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2954, 3202, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2953, 3202, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2953, 3203, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2953, 3205, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2954, 3205, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2957, 3205, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2956, 3202, 0, 10);
		c.getPA().checkObjectSpawn(2213, 2955, 3202, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2958, 3204, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2960, 3203, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2960, 3204, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2957, 3202, 0, 10);
		c.getPA().checkObjectSpawn(2213, 2958, 3202, 0, 10);
		c.getPA().checkObjectSpawn(1158, 2960, 3202, 0, 10);
		c.getPA().checkObjectSpawn(1158, 2953, 3202, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2948, 3203, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2947, 3204, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3202, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2951, 3202, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2951, 3203, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2951, 3204, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2951, 3205, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3207, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3208, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2947, 3207, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2947, 3208, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2966, 3209, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2966, 3212, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2967, 3211, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2966, 3211, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2966, 3213, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2967, 3213, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2970, 3209, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2970, 3212, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2970, 3214, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2965, 3215, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2964, 3209, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2963, 3216, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2969, 3212, -1, 10);
		c.getPA().checkObjectSpawn(6552, 2951, 3218, 0, 10);
		c.getPA().checkObjectSpawn(411, 2958, 3218, 0, 10);
		c.getPA().checkObjectSpawn(409, 2954, 3218, 0, 10);
		c.getPA().checkObjectSpawn(4008, 2960, 3218, 0, 10);
		c.getPA().checkObjectSpawn(410, 2956, 3218, 0, 10);
		c.getPA().checkObjectSpawn(4874, 2951, 3217, 0, 10);
		c.getPA().checkObjectSpawn(4875, 2951, 3216, 0, 10);
		c.getPA().checkObjectSpawn(4876, 2951, 3215, 0, 10);
		c.getPA().checkObjectSpawn(4877, 2951, 3213, 0, 10);
		c.getPA().checkObjectSpawn(4878, 2951, 3212, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2947, 3218, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2947, 3216, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3218, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3217, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3216, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3215, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3213, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3212, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2946, 3211, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2947, 3211, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2948, 3211, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2949, 3211, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2948, 3213, -1, 10);
		c.getPA().checkObjectSpawn(2213, 2946, 3211, 0, 10);
		c.getPA().checkObjectSpawn(2090, 2946, 3212, 0, 10);
		c.getPA().checkObjectSpawn(2094, 2946, 3213, 0, 10);
		c.getPA().checkObjectSpawn(2092, 2946, 3214, 0, 10);
		c.getPA().checkObjectSpawn(2096, 2946, 3215, 0, 10);
		c.getPA().checkObjectSpawn(2102, 2946, 3216, 0, 10);
		c.getPA().checkObjectSpawn(2105, 2946, 3217, 0, 10);
		c.getPA().checkObjectSpawn(14859, 2946, 3218, 0, 10);
	 if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		 }else{
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public boolean objectExists(final int x, final int y) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y) {
				return true;
			}
		}
		return false;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}