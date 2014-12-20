/*package game.combat.prayer;

import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import engine.util.Misc;
import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;

public class CurseEffects {

	private static int[][] deflectData = {	{7, 2228, 12573},//Deflect Mage
		{8, 2229, 12573},//Deflect Range
		{9, 2230, 12573},//Deflect Melee
	};//curseActive, gfx, anim

	public static void appendDeflect(Player c,int otherPlayer, int damage) {
		Player o = PlayerHandler.players[otherPlayer];
		for(int i = 0; i < deflectData.length; i++) {
			if (damage <= 0) {
				return;
			}
			if ((o.playerLevel[3] - damage) >= 3) {
				damage = (int)(damage/2+1);
				if (damage > c.playerLevel[3]) {
					damage = c.playerLevel[3];
				}
				if(o.curseActive[deflectData[i][0]]) {
					c.setHitDiff2(damage);
					c.setHitUpdateRequired2(true);
					c.playerLevel[3] -= damage;
					c.getPA().refreshSkill(3);
					o.gfx0(deflectData[i][1]);
					o.startAnimation(deflectData[i][2]);
				}
			}
			c.updateRequired = true;
		}
	}

	public static void deflects(Player c, NPC n, int damage) {
		int newD = damage / 6;
		if (c.curseActive[7] && n.attackType == 2 || c.curseActive[7] && n.attackType == 3) {
			c.gfx100(2228);
			c.startAnimation(12573);
			damage -= newD;
			n.handleHitMask(newD,0,5,0);
			n.HP -= newD;
			n.hitUpdateRequired = true;
		} else if (c.curseActive[8] && n.attackType == 1) {
			c.gfx100(2229);
			c.startAnimation(12573);
			damage -= newD;
			n.handleHitMask(newD,0,5,0);
			n.HP -= newD;
			n.hitUpdateRequired = true;
		} else if (c.curseActive[9] && n.attackType == 0) {
			c.gfx100(2230);
			c.startAnimation(12573);
			damage -= newD;
			n.handleHitMask(newD, 0,5,0);
			n.HP -= newD;
			n.hitUpdateRequired = true;
		}
	}

	public static void activateCurse(Player c, int i) {
		int r = getRandomChance(c);
		if (c.curseActive[1] && r == 0)
			sapWarrior(c, i);
		if (c.curseActive[2] && r == 1)
			sapRange(c, i);
		if (c.curseActive[3] && r == 2)
			sapMage(c, i);
		if (c.curseActive[4] && r == 3)
			sapSpirit(c, i);
		if (c.curseActive[10] && r == 4)
			leechAttack(c, i);
		if (c.curseActive[11] && r == 5)
			leechRange(c, i);
		if (c.curseActive[12] && r == 6)
			leechMage(c, i);
		if (c.curseActive[13] && r == 7)
			leechDef(c, i);
		if (c.curseActive[14] && r == 8)
			leechStrength(c, i);
		if (c.curseActive[15] && r == 9)
			leechRun(c, i);
		if (c.curseActive[16] && r == 10)
			leechSpec(c, i);
	}

	private static void sapWarrior(Player c, int i) {
		c.gfxId = 2215;
		c.gfxId2 = 2216;
		Player c2 = (Player)PlayerHandler.players[i];
		for (int j = 0; j < 3; j++) {
			if (c.sapTimer < 35)
				c2.playerBonus[j] -= c2.playerBonus[j] * .1;
			else
				c2.playerBonus[j] -= c2.playerBonus[j] * .2;
		}
		c.gfx0(2214);
		c.sapTimer++;
		applySap(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your attack has been sapped by " + Misc.optimizeText(c.playerName) + "!");
	}

	private static void sapRange(Player c, int i) {
		c.gfxId = 2218;
		c.gfxId2 = 2219;
		Player c2 = (Player)PlayerHandler.players[i];
		if (c.sapTimer < 35) {
			c2.playerBonus[4] -= c2.playerBonus[4] * .1;
			c2.playerBonus[9] -= c2.playerBonus[9] * .1;
		} else {
			c2.playerBonus[4] -= c2.playerBonus[4] * .2;
			c2.playerBonus[9] -= c2.playerBonus[9] * .2;
		}
		c.gfx0(2217);
		c.sapTimer++;
		applySap(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your range has been sapped by " + Misc.optimizeText(c.playerName) + "!");
	}

	private static void sapMage(Player c, int i) {
		c.gfxId = 2221;
		c.gfxId2 = 2222;
		Player c2 = (Player)PlayerHandler.players[i];
		if (c.sapTimer < 35) {
			c2.playerBonus[3] -= c2.playerBonus[3] * .1;
			c2.playerBonus[8] -= c2.playerBonus[8] * .1;
		} else {
			c2.playerBonus[3] -= c2.playerBonus[3] * .2;
			c2.playerBonus[8] -= c2.playerBonus[8] * .2;
		}
		c.gfx0(2220);
		c.sapTimer++;
		applySap(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your mage has been sapped by " + Misc.optimizeText(c.playerName) + "!");
	}

	private static void sapSpirit(Player c, int i) {
		Player c2 = (Player)PlayerHandler.players[i];
		if (c2.specAmount <= 0)
			return;
		c.gfxId = 2224;
		c.gfxId2 = 2225;
		c2.specAmount -= .05;
		c.gfx0(2223);
		applySap(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your mage has been sapped by " + Misc.optimizeText(c.playerName) + "!");
	}

	private static void leechAttack(Player c, int i) {
		c.gfxId = 2231;
		c.gfxId2 = 2232;
		Player c2 = (Player)PlayerHandler.players[i];
		if (c.leechTimer < 35) {
			for (int j = 0; j < 3; j++) {
				c.playerBonus[j] += c.playerBonus[j] * .05;
				c2.playerBonus[j] -= c2.playerBonus[j] * .1;
			}
			for (int def = 5; def < 11; def++) {
				c.playerBonus[def] += c.playerBonus[def] * .05;
				c2.playerBonus[def] -= c2.playerBonus[def] * .2;
			}
		} else {
			for (int j = 0; j < 3; j++) {
				c.playerBonus[j] += c.playerBonus[j] * .1;
				c2.playerBonus[j] -= c2.playerBonus[j] * .25;
			}
			for (int def = 5; def < 11; def++) {
				c.playerBonus[def] += c.playerBonus[def] * .1;
				c2.playerBonus[def] -= c2.playerBonus[def] * .25;
			}
		}
		c.leechTimer++;
		applyLeech(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your attack has been leeched by " + Misc.optimizeText(c.playerName) + "!");
		c.sendMessage("You drain your enemy's attack whilst yours increases");
	}

	private static void leechRange(Player c, int i) {
		c.gfxId = 2236;
		c.gfxId2 = 2238;
		Player c2 = (Player)PlayerHandler.players[i];
		if (c.leechTimer < 35) {
			c.playerBonus[4] += c.playerBonus[4] * .05;
			c2.playerBonus[4] -= c2.playerBonus[4] * .1;
		} else {
			c.playerBonus[4] += c.playerBonus[4] * .1;
			c2.playerBonus[4] -= c2.playerBonus[4] * .25;
		}
		c.leechTimer++;
		applyLeech(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your ranged has been leeched by " + Misc.optimizeText(c.playerName) + "!");
		c.sendMessage("You drain your enemy's range whilst yours increases!");
	}

	private static void leechMage(Player c, int i) {
		c.gfxId = 2240;
		c.gfxId2 = 2242;
		Player c2 = (Player)PlayerHandler.players[i];
		if (c.leechTimer < 35) {
			c.playerBonus[3] += c.playerBonus[3] * .05;
			c2.playerBonus[3] -= c2.playerBonus[3] * .1;
		} else {
			c.playerBonus[3] += c.playerBonus[3] * .1;
			c2.playerBonus[3] -= c2.playerBonus[3] * .25;
		}
		c.leechTimer++;
		applyLeech(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your magic has been leeched by " + Misc.optimizeText(c.playerName) + "!");
		c.sendMessage("You drain your enemy's magic whilst yours increases!");
	}

	private static void leechDef(Player c, int i) {
		c.gfxId = 2244; //projectile
		c.gfxId2 = 2246; //impact
		Player c2 = (Player)PlayerHandler.players[i];
		for (int j = 5; j < 10; j++) {
			if (c.leechTimer < 35) {
				c.playerBonus[j] += c.playerBonus[j] * .05;
				c2.playerBonus[j] -= c2.playerBonus[j] * .1;
			} else {
				c.playerBonus[j] += c.playerBonus[j] * .1;
				c2.playerBonus[j] -= c2.playerBonus[j] * .25;
			}
		}
		c.leechTimer++;
		applyLeech(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your defence has been leeched by " + Misc.optimizeText(c.playerName) + "!");
		c.sendMessage("You drain your enemy's defence whilst yours increases!");
	}

	private static void leechStrength(Player c, int i) {
		c.gfxId = 2248; //projectile
		c.gfxId2 = 2250; //impact
		Player c2 = (Player)PlayerHandler.players[i];
		if (c.leechTimer < 35) {
			c.playerBonus[2] += c.playerBonus[2] * .05;
			c2.playerBonus[2] -= c2.playerBonus[2] * .1;
		} else {
			c.playerBonus[2] += c.playerBonus[2] * .1;
			c2.playerBonus[2] -= c2.playerBonus[2] * .25;
		}
		c.leechTimer++;
		applyLeech(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your strength has been leeched by " + Misc.optimizeText(c.playerName) + "!");
		c.sendMessage("You drain your enemy's strength whilst yours increases!");
	}

	private static void leechRun(Player c, int i) {
		c.gfxId = 2252; //projectile
		c.gfxId2 = 2254; //impact
		Player c2 = (Player)PlayerHandler.players[i];
		if (c.leechTimer < 35) { //use if run energy is implented
			//c.runEnergy += Misc.random(10);
			//c2.runEnergy -= Misc.random(10);
			//c.getPA().sendFrame126(c.runEnergy, ###);
			//c2.getPA().sendFrame126(c2.runEnergy, ###);
		} else {
			//c.runEnergy += Misc.random(15);
			//c2.runEnergy -= Misc.random(15);
			//c.getPA().sendFrame126(c.runEnergy, ###);
			//c2.getPA().sendFrame126(c2.runEnergy, ###);
		}
		c.leechTimer++;
		applyLeech(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your run energy has been leeched by " + Misc.optimizeText(c.playerName) + "!");
		c.sendMessage("You drain your enemy's run energy whilst yours increases!");
	}

	private static void leechSpec(Player c, int i) {
		Player c2 = (Player)PlayerHandler.players[i];
		if (c2.specAmount <= 0)
			return;
		c.gfxId = 2256; //projectile
		c.gfxId2 = 2258; //impact
		c.specAmount += .05;
		c2.specAmount -= .05;
		applyLeech(c, i, c.gfxId, c.gfxId2);
		c2.sendMessage("Your special attack has been leeched by " + Misc.optimizeText(c.playerName) + "!");
		c.sendMessage("You drain your enemy's special attack whilst yours increases!");
	}

	private static void applyLeech(final Player c, final int i, int gfx, final int gfx2) {
		final Player c2 = (Player)PlayerHandler.players[i];
		final int pX = c.getX();
		final int pY = c.getY();
		final int nX = PlayerHandler.players[i].getX();
		final int nY = PlayerHandler.players[i].getY();
		final int offX = (pY - nY)* -1;
		final int offY = (pX - nX)* -1;
		c.startAnimation(12575);
		c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, gfx, 9, 9, - c.oldPlayerIndex - 1, 24, 0);			
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer event) {	
				c2.gfx0(c.gfxId2);
				c.getPA().createPlayersProjectile2(nX, nY, offX, offY, 50, 50, gfx2, 9, 9, - c.playerId - 1, 24, 0);	
				event.stop();
			}
		},300);
	}

	private static void applySap(final Player c, final int i, final int gfx, final int gfx2) {
		final Player c2 = (Player)PlayerHandler.players[i];
		final int pX = c.getX();
		final int pY = c.getY();
		final int nX = PlayerHandler.players[i].getX();
		final int nY = PlayerHandler.players[i].getY();
		final int offX = (pY - nY)* -1;
		final int offY = (pX - nX)* -1;
		c.startAnimation(12569);
		c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, gfx, 9, 9, - c.oldPlayerIndex - 1, 24, 0);			
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer event) {
				c2.gfx0(gfx2);
				c.getPA().createPlayersProjectile2(nX, nY, offX, offY, 50, 50, gfx, 9, 9, - c.playerId - 1, 24, 0);	
				event.stop();
			}
		},300);
	}

	private static int getRandomChance(Player c) {
		return Misc.random(30);
	}

	public static int getDistance(Player c, int i) {
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 1)){return c.distance = 50;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 2)){return c.distance = 55;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 3)){return c.distance = 60;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 4)){return c.distance = 65;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 5)){return c.distance = 70;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 6)){return c.distance = 75;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 7)){return c.distance = 80;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 8)){return c.distance = 85;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 9)){return c.distance = 90;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 10)){return c.distance = 95;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 11)){return c.distance = 100;}
		if (c.goodDistance(c.getX(), c.getY(), PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), 12)){return c.distance = 100;}
		return c.distance;
	}

	public static void applySS(Player c, final int i,int damage) {
		int ssheal = (int)(damage/4);
		if(c.playerLevel[3] + ssheal <= c.maxHp()) {
			c.playerLevel[3] += ssheal;
		} else {
			c.playerLevel[3] = c.maxHp();
		}
		c.getPA().refreshSkill(3);
		sendPacket(c, i,damage);
	}

	public static void sendPacket(Player c, final int i,int damage) {
		if(c.oldPlayerIndex > 0) {
			@SuppressWarnings("unused")
			final Player c3 = (Player)PlayerHandler.players[i];
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = PlayerHandler.players[i].getX();
			final int nY = PlayerHandler.players[i].getY();
			final int offX = (pY - nY)* -1;
			final int offY = (pX - nX)* -1;
			getDistance(c, i);
			c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, c.distance, 2263, 9, 9, - c.oldPlayerIndex - 1, 24, 0);
			endPacket(c, i);
		} else {
			SSnpc(c, i,damage);
		}
	}

	public static void endPacket(final Player c, final int i) {	
		final Player c3 = (Player)PlayerHandler.players[i];
		final int pX = c.getX();
		final int pY = c.getY();
		final int nX = PlayerHandler.players[i].getX();
		final int nY = PlayerHandler.players[i].getY();
		final int offX = (pY - nY)* -1;
		final int offY = (pX - nX)* -1;		
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer event) {	
				getDistance(c, i);
				c3.gfx0(2264);
				c.getPA().createPlayersProjectile2(nX, nY, offX, offY, 50, c.distance, 2263, 9, 9, - c.playerId - 1, 24, 0);
				c.soulSplitSend = false;	
				event.stop();
			}
		},300);
	}	

	public static void SSnpc(final Player c, final int i, int damage) {
		if (c.curseActive[18]) {
			int ssheal = (int)(damage/4);
			if (damage <= 0)
				return;
			if(c.playerLevel[3] + ssheal <= c.maxHp()) {
				c.playerLevel[3] += ssheal;
			} else {
				c.playerLevel[3] = c.maxHp();
			}
			c.getPA().refreshSkill(3);
			final int pX = c.getX();
			final int pY = c.getY();
			final int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
			final int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
			final int offX = (pY - nY)* -1;
			final int offY = (pX - nX)* -1;
			c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2263, 9, 9, - c.oldNpcIndex + 1, 24, 0);
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer event) {	
					NPCHandler.npcs[i].gfx0(2264);
					c.getPA().createPlayersProjectile2(nX, nY, offX, offY, 50, 50, 2263, 9, 9, - c.playerId - 1, 24, 0);
					event.stop();
				}
			},300);
		}
	}
}*/