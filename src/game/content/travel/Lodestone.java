package game.content.travel;

import game.entity.player.Player;

public class Lodestone {
	
	public enum Button {
		YANILLE(2616, 3105, 94177),
		//LUNAR(2107, 3918, 94138),
		SEERS(2728, 3476, 94180),
		CATHERBY(2807, 3435, 94174),
		BURTHROPE(2902, 3543, 94144),
		FALADOR(2969, 3402, 94162),
		PORT_SARIM(3023, 3208, 94165),
		EDGEVILLE(3087, 3504, 94159),
		DRAYNOR_VILLAGE(3093, 3244, 94168),
		LUMBRIDGE(3223, 3218, 94141),
		BANDIT_CAMP(3176, 2987, 94147),
		VARROCK(3199, 3433, 94156),
		AL_KHARID(3277, 3162, 94153),
		ARDOUGNE(2645, 3291, 94171),
		TAVERLY(2921, 3451, 94150);
		
		private int xPos, yPos, buttonId;
		
		private Button (int xPos, int yPos, int buttonId) {
			this.xPos = xPos;
			this.yPos = yPos;
			this.buttonId = buttonId;
		}
		
		public int getX() {
			return xPos;
		}
		
		public int getY() {
			return yPos;
		}
	}
	
	
	public Lodestone(Player c) {
	}
	
	public static void drawInterface(Player c) {
		c.getPA().showInterface(26000);
		c.gfx0(570);
		c.gfx0(569);
		c.sendMessage("Please choose a location on the map you wish to be teleported to.");
	}
	
	public static void handleButtons(Player c, int buttonId) {
		for(Button button : Button.values()) {
			if(button.buttonId == buttonId) {
				if(c != null) {
					c.getPA().startTeleport(button.getX(), button.getY(), 0, "lunar");
					return;
				}
			}
		}
	}
}