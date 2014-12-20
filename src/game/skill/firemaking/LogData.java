package game.skill.firemaking;

public class LogData {
	
	public static enum logData {
		
		LOG(1511, 1, 40),
		ACHEY(2862, 1, 40),
		OAK(1521, 15, 60),
		WILLOW(1519, 30, 90),
		TEAK(6333, 35,  105),
		ARCTIC_PINE(10810, 42, 125),
		MAPLE(1517, 45, 135),
		MAHOGANY(6332, 50, 157),
		EUCALYPTUS(12581, 58, 193),
		YEW(1515, 60, 202),
		MAGIC(1513, 75, 303),
		TANGLE_GUM_BRANCHES(17682, 1, 40),
		SEEPING_ELM_BRANCHES(17684, 10, 60),
		BLOOD_SPINDLE_BRANCHES(17686, 20, 90),
		UTUKU_BRANCHES(17688, 30, 105),
		SPINEBEAM_BRANCHES(17690, 40, 125),
		BOVISTRANGLER_BRANCHES(17692, 50, 145),
		THIGAT_BRANCHES(17694, 60, 167),
		CORPSETHORN_BRANCHES(17696, 70, 203),
		ENTGALLOW_BRANCHES(17698, 80, 240),
		GRAVE_CREEPER_BRANCHES(17700, 90, 303);
		
		private int logId, level;
		private int xp;
		
		private logData(int logId, int level, int xp) {
			this.logId = logId;
			this.level = level;
			this.xp = xp;
		}
		
		public int getLogId() {
			return logId;
		}
		
		public int getLevel() {
			return level;
		}
		
		public int getXp() {
			return xp;
		}		
	}
}