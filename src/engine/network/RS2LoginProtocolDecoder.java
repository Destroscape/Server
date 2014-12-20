package engine.network;

import java.math.BigInteger;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoFuture;
import org.apache.mina.common.IoFutureListener;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import engine.util.ISAACRandomGen;
import game.Config;
import game.Server;
import game.entity.player.Player;
import game.entity.player.PlayerHandler;
import game.entity.player.PlayerSave;
import game.net.packets.Packet;

/*
 * Project Insanity - Evolved v.3
 * RS2LogingProtocolDecoder.java
 */

public class RS2LoginProtocolDecoder extends CumulativeProtocolDecoder {
	private static final BigInteger RSA_MODULUS = new BigInteger(
			"115579938548561629081385571304433693287640785156578869269141762910300034914951221592501680385204592912126361458631667086434231975561323871874506569951991939859762240787459783837747423571212753881877401347565010233812550132970151904972378756713143853740532153103570524053890025781017547407662875560626503690521");

	private static final BigInteger RSA_EXPONENT = new BigInteger(
			"98894679860981459517032779902458516331198325654825772817437378838793120800323553138244415052271735846016721014911109198451042804241524595865462722074979001348063888941869906514650722203911036984398643411751940651216952839594936494850082378334704613402958087644879029432911326094903055336728285197005345001073");
	 
	private synchronized static void load(final IoSession session,
			final int uid, String name, String pass, final ISAACRandomGen inC,
			final ISAACRandomGen outC, final int version) {
		session.setAttribute("opcode", -1);
		session.setAttribute("size", -1);
		final int loginDelay = 1;
		int returnCode = 2;
		name = name.trim();
		name = name.toLowerCase();
		pass = pass.toLowerCase();
		if (!name.matches("[A-Za-z0-9 ]+")) {
			returnCode = 4;
		}
		if (name.length() > 12) {
			returnCode = 8;
		}
		final Player cl = new Player(session, -1);
		cl.playerName = name;
		cl.playerName2 = cl.playerName;
		cl.playerPass = pass;
		cl.setInStreamDecryption(inC);
		cl.setOutStreamDecryption(outC);
		cl.outStream.packetEncryption = outC;
		cl.saveCharacter = false;
		final char first = name.charAt(0);
		cl.properName = Character.toUpperCase(first)
				+ name.substring(1, name.length());
		if (Connection.isNamedBanned(cl.playerName)) {
			PlayerSave.loadGame(cl, cl.playerName, cl.playerPass);
			if (System.currentTimeMillis() < cl.banEnd) {
				returnCode = 23;
			} else {
				cl.banEnd = 0;
				Connection.removeNameFromBanList(cl.playerName);
			}
		}
		if (PlayerHandler.isPlayerOn(name)) {
			returnCode = 5;
		}
		// if (Config.CLIENT_VERSION != version) {
		// returnCode = 6;
		// }

		if (PlayerHandler.playerCount >= Config.MAX_PLAYERS) {
			returnCode = 7;
		}

		// Login Limit Exceeded
		// if() {
		// returnCode = 9;
		// }

		if (Server.UpdateServer) {
			returnCode = 14;
		}

		/*
		 * if(Connection.isUidBanned(UUID)) { returnCode = 22; }
		 */

		// if(Connection.checkLoginList(loginIp)) {
		// returnCode = 16;
		// }

		// Just Left World Login Delay Included
		// if() {
		// returnCode = 21;
		// }
		if (returnCode == 2) {
			final int load = PlayerSave.loadGame(cl, cl.playerName,
					cl.playerPass);
			if (load == 0) {
				cl.addStarter = true;
			}
			if (load == 3) {
				returnCode = 3;
				cl.saveFile = false;
			} else {
				for (int i = 0; i < cl.playerEquipment.length; i++) {
					if (cl.playerEquipment[i] == 0) {
						cl.playerEquipment[i] = -1;
						cl.playerEquipmentN[i] = 0;
					}
				}
				if (!Server.playerHandler.newPlayerClient(cl)) {
					returnCode = 7;
					cl.saveFile = false;
				} else {
					cl.saveFile = true;
				}
			}
		}
		cl.packetType = -1;
		cl.packetSize = 0;
		final game.net.packets.StaticPacketBuilder bldr = new game.net.packets.StaticPacketBuilder();
		bldr.setBare(true);
		bldr.addByte((byte) returnCode);
		if (returnCode == 2) {
			cl.saveCharacter = true;
			/*
			 * if (cl.playerRights == 1) { bldr.addByte((byte) 1); } else if
			 * (cl.playerRights == 2) { bldr.addByte((byte) 2); } else if
			 * (cl.playerRights == 5) { bldr.addByte((byte) 5); } else if
			 * (cl.playerRights == 6) { bldr.addByte((byte) 6); } else {
			 */
			bldr.addByte((byte) cl.playerRights);
			// }
		} else if (returnCode == 21) {
			bldr.addByte((byte) loginDelay);
		} else {
			bldr.addByte((byte) 0);
		}
		cl.isActive = true;
		bldr.addByte((byte) 0);
		final Packet pkt = bldr.toPacket();
		session.setAttachment(cl);
		session.write(pkt).addListener(new IoFutureListener() {
			@Override
			public void operationComplete(final IoFuture arg0) {
				session.getFilterChain().remove("protocolFilter");
				session.getFilterChain().addFirst("protocolFilter",
						new ProtocolCodecFilter(new GameCodecFactory(inC)));
			}
		});
	}

	private synchronized static String readRS2String(final ByteBuffer in) {
		final StringBuilder sb = new StringBuilder();
		byte b;
		while ((b = in.get()) != 10) {
			sb.append((char) b);
		}
		return sb.toString();
	}

	/**
	 * Releases the buffer used by the given session.
	 * 
	 * @param session
	 *            The session for which to release the buffer
	 * @throws Exception
	 *             if failed to dispose all resources
	 */
	@Override
	public void dispose(final IoSession session) throws Exception {
		super.dispose(session);
	}

	/**
	 * Parses the data in the provided byte buffer and writes it to
	 * <code>out</code> as a <code>Packet</code>.
	 * 
	 * @param session
	 *            The IoSession the data was read from
	 * @param in
	 *            The buffer
	 * @param out
	 *            The decoder output stream to which to write the
	 *            <code>Packet</code>
	 * @return Whether enough data was available to create a packet
	 */
	@Override
	public boolean doDecode(final IoSession session, final ByteBuffer in,
			final ProtocolDecoderOutput out) {
		synchronized (session) {
			final Object loginStageObj = session.getAttribute("LOGIN_STAGE");
			int loginStage = 0;
			if (loginStageObj != null) {
				loginStage = (Integer) loginStageObj;
			}
			// Logger.log("recv login packet, stage: "+loginStage);
			switch (loginStage) {

			case 0:
				if (2 <= in.remaining()) {
					final int protocol = in.get() & 0xff;
					@SuppressWarnings("unused")
					final int nameHash = in.get() & 0xff;
					if (protocol == 14) {
						final long serverSessionKey = ((long) (java.lang.Math
								.random() * 99999999D) << 32)
								+ (long) (java.lang.Math.random() * 99999999D);
						final game.net.packets.StaticPacketBuilder s1Response = new game.net.packets.StaticPacketBuilder();
						s1Response
								.setBare(true)
								.addBytes(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 })
								.addByte((byte) 0).addLong(serverSessionKey);
						session.setAttribute("SERVER_SESSION_KEY",
								serverSessionKey);
						session.write(s1Response.toPacket());
						session.setAttribute("LOGIN_STAGE", 1);
					}
					return true;
				} else {
					in.rewind();
					return false;
				}
			case 1:
				@SuppressWarnings("unused")
				int loginType = -1,
				loginPacketSize = -1,
				loginEncryptPacketSize = -1;
				if (2 <= in.remaining()) {
					loginType = in.get() & 0xff; // should be 16 or 18
					loginPacketSize = in.get() & 0xff;
					loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2);
					if (loginPacketSize <= 0 || loginEncryptPacketSize <= 0) {
						System.out.println("Zero or negative login size.");
						session.close();
						return false;
					}
				} else {
					in.rewind();
					return false;
				}
				if (loginPacketSize <= in.remaining()) {
					final int magic = in.get() & 0xff;
					final int version = in.getUnsignedShort();
					if (magic != 255) {
						// System.out.println("Wrong magic id.");
						session.close();
						return false;
					}
					if (version != 1) {
						// Dont Add Anything
					}
					@SuppressWarnings("unused")
					final int lowMem = in.get() & 0xff;
					for (int i = 0; i < 9; i++) {
						in.getInt();
					}
					loginEncryptPacketSize--;
					if (loginEncryptPacketSize != (in.get() & 0xff)) {
						System.out.println("Encrypted size mismatch.");
						session.close();
						return false;
					}
					byte[] encryptionBytes = new byte[loginEncryptPacketSize];
					in.get(encryptionBytes);
					ByteBuffer rsaBuffer = ByteBuffer.wrap(new BigInteger(
							encryptionBytes).modPow(RSA_EXPONENT, RSA_MODULUS)
							.toByteArray());
					if ((rsaBuffer.get() & 0xff) != 10) {
						System.out.println("Encrypted id != 10.");
						session.close();
						return false;
					}
					final long clientSessionKey = rsaBuffer.getLong();
					final long serverSessionKey = rsaBuffer.getLong();
					final int uid = rsaBuffer.getInt();
					/*
					 * int uid = in.getInt(); if(uid != 352461264) {
					 * session.close(); return false; }
					 */
					// UUID = readRS2String(rsaBuffer);
					final String name = RS2LoginProtocolDecoder
							.readRS2String(rsaBuffer);
					final String pass = RS2LoginProtocolDecoder
							.readRS2String(rsaBuffer);
					final int sessionKey[] = new int[4];
					sessionKey[0] = (int) (clientSessionKey >> 32);
					sessionKey[1] = (int) clientSessionKey;
					sessionKey[2] = (int) (serverSessionKey >> 32);
					sessionKey[3] = (int) serverSessionKey;
					final ISAACRandomGen inC = new ISAACRandomGen(sessionKey);
					for (int i = 0; i < 4; i++) {
						sessionKey[i] += 50;
					}
					final ISAACRandomGen outC = new ISAACRandomGen(sessionKey);
					RS2LoginProtocolDecoder.load(session, uid, name, pass, inC,
							outC, version);
					// WorkerThread.load(session, name, pass, inC, outC);
					session.getFilterChain().remove("protocolFilter");
					session.getFilterChain().addLast("protocolFilter",
							new ProtocolCodecFilter(new GameCodecFactory(inC)));
					return true;
				} else {
					in.rewind();
					return false;
				}
			}
		}
		return false;
	}

}
