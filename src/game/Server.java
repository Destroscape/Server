package game;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;
import org.Vote.*;
import engine.event.CycleEventHandler;
import engine.event.EventManager;
import engine.event.Task;
import engine.event.TaskScheduler;
import engine.network.Connection;
import engine.network.ConnectionHandler;
import engine.network.ConnectionThrottleFilter;
import engine.util.Logger;
import engine.util.ShutDownHook;
import engine.util.SimpleTimer;
import engine.world.WalkingCheck;
import engine.world.WorldObject;
import game.clip.region.ObjectDef;
import game.clip.region.Region;
import game.content.GodBooks;
import game.content.clan.ClanManager;
import game.content.partyroom.PartyRoom;
import game.content.clues.Cluescrolls;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.item.ItemHandler;
import game.minigame.bountyhunter.BountyHunter;
import game.minigame.fightcaves.FightCaves;
import game.minigame.fightpits.FightPits;
import game.minigame.pestcontrol.PestControl;
import game.object.DoubleDoorHandler;
import game.object.ObjectHandler;
import game.object.ObjectManager;
import game.object.SingleDoorHandler;
import game.shop.ShopHandler;
import game.minigame.rfd.RFD;
//import game.sql.MadTurnipConnection;
import game.sql.Highscores;

public class Server {

	public static boolean sleeping;
	public static final int cycleRate;
	public static boolean UpdateServer = false;
	private static IoAcceptor acceptor;
	private static ConnectionHandler connectionHandler;
	private static ConnectionThrottleFilter throttleFilter;
	public static SimpleTimer engineTimer;
	private static SimpleTimer debugTimer;
	public static boolean shuttingDown = false;
	public static long cycleTime;
	public static long cycles;
	public static long totalCycleTime;
	public static long sleepTime;
	public static boolean shutdownServer = false;
	public static int garbageCollectDelay = 40;
	public static boolean shutdownClientHandler;
	public static int serverlistenerPort;
	public static WorldObject worldObject = new WorldObject();
	public static ItemHandler itemHandler = new ItemHandler();
	public static PestControl pestControl = new PestControl();
	public static PlayerHandler playerHandler = new PlayerHandler();
	//public static MainLoader vote = new MainLoader("www.sweprime.com", "sweprime_voteuse", "sweprimeVote1", "sweprime_voting");
	public static NPCHandler npcHandler = new NPCHandler();
	public static ShopHandler shopHandler = new ShopHandler();
	public static Cluescrolls cs = new Cluescrolls();
	public static ObjectHandler objectHandler = new ObjectHandler();
	public static ObjectManager objectManager = new ObjectManager();
	public static FightPits fightPits = new FightPits();
	public static FightCaves fightCaves = new FightCaves();
	public static GodBooks godbooks = new GodBooks();
	public static ClanManager clanManager = new ClanManager();
	public static PartyRoom partyRoom = new PartyRoom();
	public static BountyHunter bountyHunter = new BountyHunter();
	public static RFD RFD = new RFD();
	//public static MadTurnipConnection md;

	/**
	 * The task scheduler.
	 */
	private static final TaskScheduler scheduler = new TaskScheduler();

	/**
	 * Gets the task scheduler.
	 * 
	 * @return The task scheduler.
	 */
	public static TaskScheduler getTaskScheduler() {
		return scheduler;
	}

	static {
		serverlistenerPort = 43594;
		cycleRate = 600;
		shutdownServer = false;
		engineTimer = new SimpleTimer();
		debugTimer = new SimpleTimer();
		sleepTime = 0;
	}

	public static boolean playerExecuted = false;

	public static long getSleepTimer() {
		return Server.sleepTime;
	}

	public static void main(java.lang.String args[])
			throws NullPointerException, IOException {
		/*
		 * Runtime.getRuntime().addShutdownHook(new Thread() {
		 * 
		 * @Override public void run() { for (Player p : PlayerHandler.players)
		 * { if (p == null) continue;
		 * System.out.println("Saving all players...");
		 * PlayerSave.saveGame((Client) p); } }
		 */
		/**
		 * Starting Up Server
		 */
		System.setOut(new Logger(System.out));
		System.setErr(new Logger(System.err));
		System.out.println("[Stage - 1] Launching " + Config.SERVER_NAME
				+ "...");
		WalkingCheck.check();
		WalkingCheck.check2();
		ObjectDef.loadConfig();
		Region.load();
		Cluescrolls.Clues();
		//md = new MadTurnipConnection();
		//md.start();

	/*Highscores.process();
	if (Highscores.connected) {
		System.out.println("[S] Successfully connected to the Highscores MySQL DB");
	} else {
		System.out.println("[F] Failed to connect to the Highscores MySQL DB");
	}*/
		/**
		 * Accepting Connections
		 */
		Server.acceptor = new SocketAcceptor();
		Server.connectionHandler = new ConnectionHandler();
		SocketAcceptorConfig sac = new SocketAcceptorConfig();
		sac.getSessionConfig().setTcpNoDelay(false);
		sac.setReuseAddress(true);
		sac.setBacklog(100);
		Server.throttleFilter = new ConnectionThrottleFilter(
				Config.CONNECTION_DELAY);
		sac.getFilterChain().addFirst("throttleFilter", Server.throttleFilter);
		Server.acceptor.bind(new InetSocketAddress(Server.serverlistenerPort),
				Server.connectionHandler, sac);
		ShutDownHook shutdownhook = new ShutDownHook();
		Runtime.getRuntime().addShutdownHook(shutdownhook);
		/**
		 * Initialize Handlers
		 */
		itemHandler.createglobalitems();
		EventManager.initialize();
		SingleDoorHandler.getSingleton().load();
		DoubleDoorHandler.getSingleton().load();
		Connection.initialize();
		/**
		 * Server Successfully Loaded
		 */
		System.out.println("[Final Stage] Opened server and connections to Port: "
				+ Server.serverlistenerPort);

		/**
		 * Main Server Tick
		 */
		scheduler.schedule(new Task() {
			@Override
			protected void execute() {
				engineTimer.reset();
				CycleEventHandler.getSingleton().process();
				Server.itemHandler.process();
				Server.playerHandler.process();
				Server.npcHandler.process();
				Server.shopHandler.process();
				Server.objectManager.process();
				Server.pestControl.process();
				FightPits.process();
				cycleTime = engineTimer.elapsed();
				sleepTime = cycleRate - cycleTime;
				totalCycleTime += cycleTime;
				cycles++;
				debug();
				Server.garbageCollectDelay--;
				if (Server.garbageCollectDelay == 0) {
					Server.garbageCollectDelay = 40;
					System.gc();
				}
			}
		});
	} // so the error it's importing, but i tried everything

	public static ObjectManager getObjectManager() {
		return objectManager;
	}

	public static void debug() {
		if (Server.debugTimer.elapsed() > 360 * 1000 || Server.playerExecuted) {
			final long averageCycleTime = Server.totalCycleTime / Server.cycles;
			System.out.println("Average Cycle Time: " + averageCycleTime
					+ "ms : Players online: " + PlayerHandler.playerCount + "");
			Server.totalCycleTime = 0;
			Server.cycles = 0;
			System.gc();
			System.runFinalization();
			Server.debugTimer.reset();
			Server.playerExecuted = false;
		}
	}

	public static void processAllPackets() {
		for (final Player player : PlayerHandler.players) {
			if (player != null) {
				while (player.processQueuedPackets()) {
					;
				}
			}
		}
	}

	public static void setupLoginChannels() {
		/**
		 * Accepting Connections
		 */
		Server.acceptor = new SocketAcceptor();
		Server.connectionHandler = new ConnectionHandler();
		final SocketAcceptorConfig sac = new SocketAcceptorConfig();
		sac.getSessionConfig().setTcpNoDelay(false);
		sac.setReuseAddress(true);
		sac.setBacklog(100);
		Server.throttleFilter = new ConnectionThrottleFilter(
				Config.CONNECTION_DELAY);
		sac.getFilterChain().addFirst("throttleFilter", Server.throttleFilter);
		try {
			Server.acceptor.bind(new InetSocketAddress(
					Server.serverlistenerPort), Server.connectionHandler, sac);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}