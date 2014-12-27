package game.content;

import game.entity.player.Player;
import game.content.achievement.*;

import java.util.HashMap;

public class Food {

	public static enum FoodToEat {
		BANANA(1963, 3, "Banana"), 
		PEACHES(6883, 3, "Peach"), 
		MANTA(391, 22, "Manta Ray"), 
		SHARK(385, 20, "Shark"), 
		LOBSTER(379, 12, "Lobster"), 
		TROUT(333, 7, "Trout"), 
		SALMON(329, 9, "Salmon"), 
		SWORDFISH(373, 14, "Swordfish"), 
		TUNA(361, 10, "Tuna"), 
		MONKFISH(7946, 16, "Monkfish"), 
		CHEESE(1985, 2, "Cheese"), 
		SEA_TURTLE(397, 22, "Sea Turtle"), 
		TUNA_POTATO(7060, 22, "Tuna Potato"), 
		WEB_SNIPPER(18169, 15, "Web snipper"), 
		BOULDABASS(18171, 17, "Bouldabass"), 
		BLUE_CRAB(18175, 22, "Blue crab"), 
		CAVE_MORAY(18177, 25, "Cave moray"), 
		DUSK_EEL(18163, 7, "Dusk eel"), 
		GIANT_FLATFISH(18165, 10, "Giant flatfish"), 
		SHORT_FINNED_EEL(18167, 12, "Short-finned eel"), 
		RED_EYE(18161, 5, "Red-eye"), 
		HEIM_CRAB(18159, 2, "Heim crab"), 
		ROCKTAIL(15272, 23, "Rocktail"), 
		SALVE_EEL(18173, 20, "Salve eel"),
		EASTER_EGG(1961, 33, "Easter egg");

		public static FoodToEat forId(final int id) {
			return FoodToEat.food.get(id);
		}

		private int id;
		private int heal;

		private String name;
		public static HashMap<Integer, FoodToEat> food = new HashMap<Integer, FoodToEat>();

		static {
			for (final FoodToEat f : FoodToEat.values()) {
				FoodToEat.food.put(f.getId(), f);
			}
		}

		private FoodToEat(final int id, final int heal, final String name) {
			this.id = id;
			this.heal = heal;
			this.name = name;
		}

		public int getHeal() {
			return heal;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	private final Player c;

	public Food(final Player c) {
		this.c = c;
	}

	public void eat(final int id, final int slot) {
		if (c.duelRule[6]) {
			c.sendMessage("You may not eat in this duel.");
			return;
		}
		if (System.currentTimeMillis() - c.foodDelay >= 1500
				&& c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			AchievementManager.increase(c, Achievements.MUNCHER);
			c.getItems().deleteItem(id, slot, 1);
			final FoodToEat f = FoodToEat.food.get(id);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3]) + 10) {
				c.playerLevel[3] += f.getHeal();
				if(id != 15272 || id != 1961) {
					//if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					if (c.playerLevel[3] > c.calculateMaxLifePoints())
						//c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
						c.playerLevel[3] = c.calculateMaxLifePoints();
					//this makes sure normal food doesn't overload
				} else {
					// this says if their eating rocktails and their hp level is more then their player xp + 10, then make it playerxp + 10. If it isnt then it will overload anyway.
					//if ((c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])) + 10)) {
					//c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]) + 10;
					if ((c.playerLevel[3] > (c.calculateMaxLifePoints()) + 10)) {
						c.playerLevel[3] = c.calculateMaxLifePoints() + 10;
					}
				}
			}
			c.foodDelay = System.currentTimeMillis();
			/*
			 * if(Config.SOUND) { c.sendSound(c.getSound().FOODEAT, 100); }
			 */
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the " + f.getName() + ".");
		}
	}

	public boolean isFood(final int id) {
		return FoodToEat.food.containsKey(id);
	}

}
