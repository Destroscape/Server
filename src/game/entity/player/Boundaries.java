package game.entity.player;

public class Boundaries {

     public static enum Area {
         // HighX LowX HighY LowY
        PIRATE_HOUSE(3044, 3038, 3959, 3949),
        
        /** NPCS **/
        ROCK_CRABS(3696, 2655, 3741, 3700),
        /** NOT SURE **/
        TUTORIAL(2687, 2625, 4735, 2625),
        ARENAS(3044, 3038, 3959, 3949),
        /** SKILLS **/
        CONSTRUCTION(3391, 3331, 3260, 3242),
        /** MINIGAMES **/
        FIGHT_CAVES(2445, 2360, 5125, 5045),
        GOD_WARS(2942, 2815, 5377, 5253),
        BARROWS(3598, 3520, 9750, 9653),
        PEST_CONTROL(2688, 2624, 2622, 2560),
        MAGE_BANK(3040, 3107, 3965, 3947),
        ;
        
        private int highX, lowX, highY, lowY;

        private Area(int highX, int lowX, int highY, int lowY) {
            this.highX = highX;
            this.lowX = lowX;
            this.highY = highY;
            this.lowY = lowY;
        }
        
        public int getHighX() {
            return highX;
        }
        
        public int getLowX() {
            return lowX;
        }
        
        public int getHighY() {
            return highY;
        }
        
        public int getLowY() {
            return lowY;
        }  
    }
     
     /**
      * Checks to see if the players X and Y coordinates are within the area supplied.
      * @param area
      * @param x
      * @param y
      * @return
      */
    public static boolean checkBoundaries(Area area, int x, int y) {
         return (x <= area.getHighX() && x >= area.getLowX() && y <= area.getHighY() && y >= area.getLowY());
    }
 
}