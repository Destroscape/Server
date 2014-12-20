package game.item.operate.impl;

import game.entity.player.Player;
import game.item.ItemDegradation;
import game.item.operate.OperateItem;

public class OperateChaotics implements OperateItem {
    
    /**
     * The effects of operating the Book
     */
    @Override
    public void execute(Player p, int itemId) {
		ItemDegradation.chaoticCheckCharges(p, itemId);
    }
    

}