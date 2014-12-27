package game.content.gambling;

import engine.util.Misc;
import game.entity.player.Player;
 
public class DiceHandler {
     
    public static final int ROLL_TIMER = 1000, DICE_BAG = 15084;
 
    interface Data {
        public int diceId();
        public int diceSize();
        public int diceGfx();
    }
     
    enum Dice implements Data {
        DIE_6_SIDES(15086, 6, 2072),
        DICE_6_SIDES(15088, 12, 2074),
        DIE_8_SIDES(15090, 8, 2071),
        DIE_10_SIDES(15092, 10, 2070),
        DIE_12_SIDES(15094, 12, 2073),
        DIE_20_SIDES(15096, 20, 2068),
        DICE_UP_TO_100(15098, 100, 2075),
        DIE_4_SIDES(15100, 4, 2069);
         
        private int id, sides, gfx;
         
        Dice(int id, int sides, int gfx) {
            this.id = id;
            this.sides = sides;
            this.gfx = gfx;
        }
         
        @Override
        public int diceId() {
            return id;
        }
 
        @Override
        public int diceSize() {
            return sides;
        }
 
        @Override
        public int diceGfx() {
            return gfx;
        }   
    }
     
    /**
     * Handles rolling of the dice.
     * @param c
     *      The player.
     * @param id
     *      The id of the dice.
     * @param clan
     *      If the player is in a clan.
     * @return
     *      Whether or not to roll the dice.
     */
    public static boolean rollDice(Player c, int id, boolean clan) {
        if(c.lastRoll < System.currentTimeMillis()){
            c.lastRoll = System.currentTimeMillis()+ROLL_TIMER;
            for(Dice d: Dice.values()) {
                if(d.diceId() == id) {
			if (c.clan == null) {
                        	selfRoll(c, Misc.random(d.diceSize()-1)+1, d.diceId());
                        } else {
                       		rollToClan(c, Misc.random(d.diceSize()-1)+1, d.diceId());
                    }
    				c.startAnimation(11900);
                    c.gfx0(d.diceGfx());
                    return true;
                }
            }
        } else {
            for(Dice d: Dice.values()) {
                if(d.diceId() == id) {
                    return true;
                }
            }
        }
        return false;
    }
     
    /**
     * Handles the rolling of the dice to a clan.
     * @param c
     *      The player.
     * @param roll
     *      What the player rolled.
     * @param item
     *      The id of the dice.
     */
    public static void rollToClan(Player c, int roll, int item) {
			if (c.clan != null) {
				c.clan.sendChat(c, "rolled @red@"+roll+"@bla@ on the @red@"+c.getItems().getItemName(item)+" @bla@in Clan Chat");
			}
		}
     
    /**
     * Handles the rolling of the dice to a player.
     * @param c
     *      The player.
     * @param roll
     *      What the player rolled on the dice.
     * @param item
     *      The id of the dice.
     */
    public static void selfRoll(Player c, int roll, int item) {
        c.sendMessage("You rolled @red@"+roll+"@bla@ on the "+c.getItems().getItemName(item)+" via private roll."); 
        c.forcedChat((Misc.optimizeText(c.playerName)) +" rolled "+roll+" on the "+c.getItems().getItemName(item)+" via private roll.");
    }
     
    /**
     * Handles selecting the dice
     * @param c
     *      The player.
     * @param item
     *      The dice id.
     * @return
     *      Whether or not a dice were selected.
     */
    public static boolean selectDice(Player c, int item){
        for(Dice d: Dice.values()) {
            if(item == d.diceId() || item == DICE_BAG){
                c.getDH().sendOption5(c.getItems().getItemName(Dice.DIE_6_SIDES.diceId()), c.getItems().getItemName(Dice.DICE_6_SIDES.diceId()), c.getItems().getItemName(Dice.DIE_8_SIDES.diceId()), c.getItems().getItemName(Dice.DIE_10_SIDES.diceId()), "Next Page");
                c.diceItem = item;
                return true;
            }
        }
        return false;
    }
     
    /**
     * Handles putting the dice away.
     * @param c
     *      The player.
     * @param itemId
     *      The dice id.
     * @return
     *      Whether or not the dice were put up.
     */
    public static boolean putupDice(Player c, int itemId) {
        for(Dice d: Dice.values()){
            if(itemId == d.diceId()){
                c.getItems().deleteItem2(itemId, 1);
                c.getItems().addItem(DICE_BAG, 1);
                return true;
            }
        }
        return false;
    }
     
    /**
     * Handles all the clicking for the dice.
     * @param c
     *      The player.
     * @param actionButtonId
     *      Action button id of what is clicked.
     * @return
     *      Whether or not a click was handled.
     */
    public static boolean handleClick(Player c, int actionButtonId) {
        int[][] dice = {{Dice.DIE_6_SIDES.diceId()}, {Dice.DICE_6_SIDES.diceId()}, {Dice.DIE_8_SIDES.diceId()}, {Dice.DIE_10_SIDES.diceId()},
                        {Dice.DIE_12_SIDES.diceId()}, {Dice.DIE_20_SIDES.diceId()}, {Dice.DICE_UP_TO_100.diceId()}, {Dice.DIE_4_SIDES.diceId()}};
        int DICE = 0;
        if(actionButtonId-9190 >= 0 && actionButtonId-9190 <= 5) {
            if(c.page == 0) {
                c.getPA().removeAllWindows();
                if(actionButtonId-9190 <= 3){
                    if(c.getItems().playerHasItem(c.diceItem, 1)) {
                        c.getItems().deleteItem(c.diceItem, 1);
                        c.getItems().addItem(dice[actionButtonId-9190][DICE], 1);
                    }
                } else {
                    c.getDH().sendOption5(c.getItems().getItemName(Dice.DIE_12_SIDES.diceId()), c.getItems().getItemName(Dice.DIE_20_SIDES.diceId()), c.getItems().getItemName(Dice.DICE_UP_TO_100.diceId()), c.getItems().getItemName(Dice.DIE_4_SIDES.diceId()), "Return");
                    c.page = 1;
                }
            } else if(c.page == 1) {
                c.getPA().removeAllWindows();
                if(actionButtonId-9190 <= 3) {
                    if(c.getItems().playerHasItem(c.diceItem, 1)){
                        c.getItems().deleteItem(c.diceItem, 1);
                        c.getItems().addItem(dice[actionButtonId-9186][DICE], 1);
                    }
                } else {
                    c.getPA().closeAllWindows();
                }
                c.page = 0;
            }
            return true;
        }
        return false;
    }
     
}