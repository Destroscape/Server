package game.item;

import game.entity.player.Player;

public class ItemDegradation {

	public final static int CHAOTIC_DEGRADE_TIME = 60000;

	public final static int[] chaoticBrokenIds = {
		18350, 18352, 18354, 18356, 18358,
		18360, 18362, 18364, 18366, 18368,
		18370, 18372, 18374
	};	
	
	public static void chaoticDegrade(Player p, int attackTimer) {
		for(int i = 0; i < chaoticBrokenIds.length; i++) {
			if(p.playerEquipment[p.playerWeapon] == chaoticBrokenIds[i] - 1 
					|| p.playerEquipment[p.playerShield] == chaoticBrokenIds[i] - 1 ) {
				p.chaoticDegrade[i] += attackTimer;
				if(p.chaoticDegrade[i] >= CHAOTIC_DEGRADE_TIME) {
					p.chaoticDegrade[i] = CHAOTIC_DEGRADE_TIME;
					p.playerEquipment[p.playerEquipment[p.playerWeapon] == chaoticBrokenIds[i] - 1 
							? p.playerWeapon : p.playerShield] = chaoticBrokenIds[i]; 
					p.setAppearanceUpdateRequired(true);
					p.sendMessage("Your "+p.getItems().getItemName(chaoticBrokenIds[i] - 1)+ " has fully degraded.");
				}	
			}
		}
	}

	public static void chaoticCheckCharges(Player p, int item) {
		for(int i = 0; i < chaoticBrokenIds.length; i++) {
			if(item == chaoticBrokenIds[i]-1) {
				p.sendMessage("Your "+ p.getItems().getItemName(item) +" has "+(100-(int)((p.chaoticDegrade[i]*100.0f)/CHAOTIC_DEGRADE_TIME))+"% of its charge left.");
				break;
			} else if(item == chaoticBrokenIds[i]) {
				p.sendMessage("Your "+ p.getItems().getItemName(item)+" is fully degraded");
				break;
			}
		}
	}

}
