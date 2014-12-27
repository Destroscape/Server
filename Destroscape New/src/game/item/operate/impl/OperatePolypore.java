package game.item.operate.impl;

import game.entity.player.Player;
import game.item.operate.OperateItem;

public class OperatePolypore implements OperateItem {
    
    /**
     * The effects of operating the Book
     */
    @Override
    public void execute(Player p, int itemId) {
		p.sendMessage("You have " + p.ppsLeft + " charges left!");
    }
    

}