package game.content;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;

public class Resting {

	static final int CYCLES_PER_INCREASE = 3, REST_ANIMATION = 11786,
			STAND_UP_ANIMATION = 11788, ENERGY_INCREASE = 3;

	public static void startResting(final Player p) {
		p.startAnimation(REST_ANIMATION);
		p.stopMovement();
		p.isResting = true;
		CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer e) {
				if (p.playerEnergy > 100) {
					p.playerEnergy = 100;
					e.stop();
				}
				if (p.isResting && p.playerEnergy < 100) {
					p.playerEnergy += ENERGY_INCREASE;
					p.getPA().sendFrame126("" + p.playerEnergy, 149);
				} else
					e.stop();
			}

			@Override
			public void stop() {
				stopResting(p);
			}
		}, 2);
	}

	public static void stopResting(final Player p) {
		p.startAnimation(STAND_UP_ANIMATION);
		p.stopMovement();
		CycleEventHandler.getSingleton().addEvent(p, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer e) {
				p.isResting = false;
				e.stop();
			}

			@Override
			public void stop() {
				p.isResting = false;
			}
		}, 1);
	}

}