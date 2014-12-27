package game.item.operate.impl;

import game.entity.player.Player;
import game.item.operate.OperateItem;

public class OperateDFS implements OperateItem {
    
    /**
     * The effects of operating the Book
     */
    @Override
    public void execute(Player p, int itemId) {
		/*if (p.playerIndex > 0) {
			p.getCombat().handleDfs(p);				
		} else if (p.npcIndex > 0) {
			p.getCombat().handleDfsNPC(p);
		}
		p.sendMessage("Your "+p.getItems().getItemName(itemId) +" currently has "+ p.dfsCount +" charges left.");*/
    }
    

}