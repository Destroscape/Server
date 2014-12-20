package game.skill.mining;

import engine.event.Task;
import game.Server;
import game.entity.player.Player;

public class Prospecting {
	
    public enum ProspectData {
		COPPER(new short[]{31082, 31080, 31081, 2090, 2091, 3042, 9708, 9709, 9710, 11936, 11937, 11938, 11960, 11961, 11962}, "copper"),
		TIN(new short[]{31078, 31081, 31079, 31077, 2094, 2095, 3043, 9714, 9715, 9716, 11933, 11934, 11935, 11957, 11958, 11959}, "tin"), 
		IRON(new short[]{31073, 31071, 31072, 2092, 2093, 9717, 9718, 9719, 11954, 11955, 11956}, "Iron"), 
		COAL(new short[]{14832, 2096, 2097, 10948, 11930, 11931, 11932, 11963, 11964, 11965, 31069, 31068, 31070},"coal"), 
		SANDSTONE(new short[]{10946}, "sandstone"), 
		GRANITE(new short[]{10947}, "granite"), 
		GOLD(new short[]{11183, 11185, 11184, 31065, 31066, 2098, 2099, 9720, 9721, 9722, 11951, 11952, 11953}, "gold"), 
		SILVER(new short[]{11186, 11187, 11188, 2100, 2101, 11948, 11949, 11950}, "silver"), 
		MITHRIL(new short[]{31088, 2102, 2103, 11942, 11943, 11944, 11945, 11946, 11947, 31086, 31087}, "mithril"), 
		ADAMANTITE(new short[]{31083, 31085, 2104, 2105, 11939, 11940, 11941}, "adamantite"), 
		RUNITE(new short[]{2106, 2107, 14859, 14860, 14861}, "runite"), 
		CLAY(new short[]{11189, 11190, 11191, 2108, 15504, 15503, 15505, 2109, 9711, 9712, 9713, 10949}, "clay");

        /**
         * Constructor for the prospect data.
         * @param objectIds The object id (s).
         * @param oreType The ore type of the rock.
         */

        ProspectData(short[] objectIds, String oreType) {
            this.objectIds = objectIds;
            this.oreType = oreType;
        }

        /**
         * Holds all of the rock object ids.
         */

        private short[] objectIds;

        /**
         * The ore type of the rock.
         */

        private String oreType;

        /**
         * Gets all of the rock object ids.
         * @return All of the rock object ids.
         */

        public short[] getObjectIds() {
            return objectIds;
        }

        /**
         * Gets the ore type of the rock.
         * @return The ore type of the rock.
         */

        public String getOreType() {
            return oreType;
        }

    }
    
    /**
     * Retrieves the information from the enum.
     * @param objectId The object id of the rock.
     * @return The information from the enum.
     */

    private static ProspectData getOre(int objectId) {
        for (ProspectData ore : ProspectData.values()) {
            for (int i = 0; i < ore.getObjectIds().length; i++) {
                if (ore.getObjectIds()[i] == objectId) {
                    return ore;
                }
            }
        }
        return null;
    }

    /**
     * Checks whether the object the player's clicking has an prospect message.
     * @param objectId The object id of the rock.
     * @return It will return true if the object id matches with the one in the enum.
     */

    public static boolean hasProspect(int objectId) {
        return getOre(objectId) != null;
    }
	
	/**
	 * Prospects the rock.
	 * @param c The client class.
	 * @param itemId The name of the item within the object.
	 */
	public static void prospectRock(final Player player, int objectId) {
	     final ProspectData ore = getOre(objectId);
	     if (ore == null)
	    	 return;
	    player.sendMessage("You prospect the rock..");
		Server.getTaskScheduler().schedule(new Task(2) {
			@Override
			public void execute() {
				player.sendMessage("This rock contains "+ore.getOreType()+".");
				this.stop();
			}
		});
	}
}