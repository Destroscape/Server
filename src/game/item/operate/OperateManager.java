package game.item.operate;

import game.entity.player.Player;
import game.item.operate.impl.OperateChaotics;
import game.item.operate.impl.OperateDFS;
import game.item.operate.impl.OperatePolypore;

import java.util.HashMap;
import java.util.Map;

public class OperateManager {
    
    private static Map<Integer, OperateItem> list = new HashMap<Integer, OperateItem>();
    
    static {
        list.put(11283, new OperateDFS());
        list.put(11284, new OperateDFS());
        list.put(22494, new OperatePolypore());
        list.put(18355, new OperateChaotics());
        list.put(18349, new OperateChaotics());
        list.put(18351, new OperateChaotics());
        list.put(18353, new OperateChaotics());
        list.put(18357, new OperateChaotics());
        list.put(18359, new OperateChaotics());
        list.put(18361, new OperateChaotics());
        list.put(18363, new OperateChaotics());
    }
    
    public static void execute(final Player p, final int id) {
        OperateItem item = list.get(id); // The item itself
        if (item != null) { // Make sure the Item exists
            item.execute(p, id);     // The effect of the item loaded from the subclass
                                    // to the interface
        }
    }
    
}