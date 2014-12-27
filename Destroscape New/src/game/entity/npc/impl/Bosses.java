package game.entity.npc.impl;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class Bosses {
	public static NPC npcs[] = new NPC[NPCHandler.maxNPCs];
	
	
	static int BurrowAnim = 12976, TransformID = 9462,
			BurrowUpAnim = 11788, IStrykeWyrmID = 9463, BurrowWait;
	
	public static void StrykeWyrmBurrow(final Player player) {
		CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer e) {
				if (BurrowWait == 0) {
				NPCHandler.npcs[9463].startAnimation(12796);
				BurrowWait++;
			} else if (BurrowWait == 1) {
				NPCHandler.npcs[9463].requestTransform(TransformID);
			} else if (BurrowWait < 3) {
				BurrowWait++;
			} else if (BurrowWait == 3) {
				//NPCHandler.npcs[9463].requestTransform(TransformID + 1);
				NPCHandler.npcs[9463].startAnimation(13713);
				BurrowWait = 0;
				stop();
			}
			}

			@Override
			public void stop() {
				
			}
		}, 2);
	}

	public static void StrykeWyrmRaise(final Player player) {
		CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer e) {
				
			}

			@Override
			public void stop() {
				
			}
		}, 1);
	}

}
