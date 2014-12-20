package engine.network;

import game.entity.player.Player;
import game.net.packets.Packet;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;

/*
 * Project Insanity - Evolved v.3
 * ConnectionHandler.java
 */

public class ConnectionHandler implements IoHandler {

	@Override
	public void exceptionCaught(final IoSession arg0, final Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
		if (arg0.getAttachment() != null) {
			Player plr = (Player) arg0.getAttachment();
			plr.queueMessage((Packet) arg1);
		}
	}

	@Override
	public void messageSent(final IoSession arg0, final Object arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionClosed(final IoSession arg0) throws Exception {
		if (arg0.getAttachment() != null) {
			final Player plr = (Player) arg0.getAttachment();
			plr.disconnected = true;
		}
		HostList.getHostList().remove(arg0);
	}

	@Override
	public void sessionCreated(final IoSession arg0) throws Exception {
		if (!HostList.getHostList().add(arg0)) {
			arg0.close();
		} else {
			arg0.setAttribute("inList", Boolean.TRUE);
		}
	}

	@Override
	public void sessionIdle(final IoSession arg0, final IdleStatus arg1)
			throws Exception {
		arg0.close();
	}

	@Override
	public void sessionOpened(final IoSession arg0) throws Exception {
		arg0.setIdleTime(IdleStatus.BOTH_IDLE, 60);
		arg0.getFilterChain().addLast("protocolFilter",
				new ProtocolCodecFilter(new CodecFactory()));
	}

}
