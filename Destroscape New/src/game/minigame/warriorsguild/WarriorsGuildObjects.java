package game.minigame.warriorsguild;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;

public class WarriorsGuildObjects {

	boolean readyToEnter = false;

	public static void handleObjects(final Player c, int id) {
		switch(id) {
		//Main entrance
		case 15653:
			int atk = c.playerLevel[c.playerAttack];
			int str = c.playerLevel[c.playerStrength];
			int req = 130;
			if(atk+str < req && atk != 99 && str != 99) {
				c.sendMessage("You need a combined attack and strength level of 130 to enter!");
			} else {
				if(c.objectX == 2877 && c.objectY == 3546) {
					if(c.absX == 2877 && c.absY == 3546 || c.absX == 2876 && c.absY == 3546 && !c.openedDoor) {
						c.getPA().walkTo(c.absX == 2877 ? -1 : 1, 0);
						c.openedDoor = true;
						c.getPA().object(15653, 2877, 3546, 1, 0);
						CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer container) {
								c.getPA().object(15653, 2877, 3546, 0, 0);
								container.stop();
							}

							@Override
							public void stop() {
								// TODO Auto-generated method stub
								c.openedDoor = false;

							}
						},3);
					}
				}
				//c.getPA().moveThroughDoor(c, id, false);
			}
			//OPEN
			break;
			//Double doors to animators
		case 15644:
		case 15641:
			if(c.heightLevel > 0)
				WarriorsGuild.getInstance().handleKamfreena(c);
			else
				handleAnimatorDoor(c);
			break;

		/*case 1738:
			c.getPA().movePlayer(2840,3539, 2);
			break;*/

		case 15638:
			c.getPA().movePlayer(2841,3538, 0);
			break;
		}
	}

	public static void handleAnimatorDoor(final Player c) {
		if(c.objectId == 15644 || c.objectId == 15641) {
			if((c.objectX == 2855 && c.objectY == 3546) || (c.objectX == 2854 && c.objectY == 3546) && !c.openedDoor) {
				if((c.absX == c.objectX && c.absY == c.objectY) || (c.absX == c.objectX && c.absY == c.objectY-1)) {
					c.openedDoor = true;
					c.getPA().object(15641, 2854, 3546, 4, 0);
					c.getPA().object(15644, 2855, 3546, 2, 0);
					c.getPA().walkTo(0, c.absY== c.objectY ?-1:1);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							c.getPA().object(15641, 2854, 3546, 3, 0);
							c.getPA().object(15644, 2855, 3546, 3, 0);
							container.stop();
						}

						@Override
						public void stop() {
							// TODO Auto-generated method stub
							c.openedDoor = false;

						}
					},3);
				}
			}
		}
	}
}
