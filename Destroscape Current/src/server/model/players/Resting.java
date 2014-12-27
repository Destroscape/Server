package server.model.players;

import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;

public class Resting {

	static final int CYCLES_PER_INCREASE = 3, REST_ANIMATION = 11786,
			STAND_UP_ANIMATION = 11788, ENERGY_INCREASE = 4;

	public static void startResting(final Client player) {
		player.startAnimation(REST_ANIMATION);
		player.stopMovement();
		player.isResting = true;
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer e) {
				if (player.isResting && player.runEnergy < 100) {
					player.runEnergy += ENERGY_INCREASE;
					player.getPA().sendFrame126("" + player.runEnergy, 149);
				} else
					e.stop();
			}
		}, 600 * CYCLES_PER_INCREASE);
	}

	public static void stopResting(final Player player) {
		player.startAnimation(STAND_UP_ANIMATION);
		player.stopMovement();
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer e) {
				player.isResting = false;
				e.stop();
			}
		}, 1000);
	}

}