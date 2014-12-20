package engine.network;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.common.IoSession;

/*
 * Project Insanity - Evolved v.3
 * HostList.java
 */

public class HostList {

	private static HostList list = new HostList();

	public static HostList getHostList() {
		return HostList.list;
	}

	private final Map<String, Integer> connections = new HashMap<String, Integer>();

	final int MAX_CONNECTIONS = 10000;

	public synchronized boolean add(IoSession session) {
		String addr = ((InetSocketAddress) session.getRemoteAddress())
				.getAddress().getHostAddress();
		Integer amt = connections.get(addr);
		if (amt == null) {
			amt = 1;
		} else {
			amt += 1;
		}
		if (amt > MAX_CONNECTIONS | Connection.isIpBanned(addr)) {
			return false;
		} else {
			connections.put(addr, amt);
			return true;
		}
	}

	public synchronized void remove(final IoSession session) {
		if (session.getAttribute("inList") != Boolean.TRUE) {
			return;
		}
		final String addr = ((InetSocketAddress) session.getRemoteAddress())
				.getAddress().getHostAddress();
		Integer amt = connections.get(addr);
		if (amt == null) {
			return;
		}
		amt -= 1;
		if (amt <= 0) {
			connections.remove(addr);
		} else {
			connections.put(addr, amt);
		}
	}

}
