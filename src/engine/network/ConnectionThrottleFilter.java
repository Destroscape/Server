package engine.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.mina.common.IoFilterAdapter;
import org.apache.mina.common.IoSession;

/*
 * Project Insanity - Evolved v.3
 * ConnectionThrottleFilter.java
 */

public class ConnectionThrottleFilter extends IoFilterAdapter {
	private static InetAddress getAddress(final IoSession io) {
		return ((InetSocketAddress) io.getRemoteAddress()).getAddress();
	}

	private long allowedInterval;
	private final Map<InetAddress, Long> clients;
	private final Map<InetAddress, Integer> counts;

	private final Set<InetAddress> connectedAddresses;

	/**
	 * Constructor that takes in a specified wait time.
	 * 
	 * @param allowedInterval
	 *            The number of milliseconds a client is allowed to wait before
	 *            making another successful connection
	 * 
	 */
	public ConnectionThrottleFilter(final long allowedInterval) {
		this.allowedInterval = allowedInterval;
		clients = Collections.synchronizedMap(new HashMap<InetAddress, Long>());
		counts = Collections
				.synchronizedMap(new HashMap<InetAddress, Integer>());
		connectedAddresses = new HashSet<InetAddress>();
	}

	public void acceptedLogin(final IoSession io) {
		connectedAddresses.add(ConnectionThrottleFilter.getAddress(io));
	}

	public void closedSession(final IoSession io) {
		connectedAddresses.remove(ConnectionThrottleFilter.getAddress(io));
	}

	public void connectionOk(final IoSession io) {
		counts.remove(ConnectionThrottleFilter.getAddress(io));
	}

	public void delayClient(final IoSession session, final int delay) {
		final long d = System.currentTimeMillis() - delay;
		clients.put(ConnectionThrottleFilter.getAddress(session), d);
	}

	public int[] getSizes() {
		return new int[] { clients.size(), counts.size(),
				connectedAddresses.size() };
	}

	public boolean isConnected(final IoSession io) {
		return connectedAddresses.contains(ConnectionThrottleFilter
				.getAddress(io));
	}

	/**
	 * Method responsible for deciding if a connection is OK to continue
	 * 
	 * @param session
	 *            The new session that will be verified
	 * @return True if the session meets the criteria, otherwise false
	 */
	public boolean isConnectionOk(final IoSession session) {
		final InetAddress addr = ConnectionThrottleFilter.getAddress(session);
		final long now = System.currentTimeMillis();
		if (clients.containsKey(addr)) {
			final long lastConnTime = clients.get(addr);
			if (now - lastConnTime < allowedInterval) {
				int c = 0;
				if (!counts.containsKey(addr)) {
					counts.put(addr, 0);
				} else {
					c = counts.get(addr) + 1;
				}
				if (c >= 350) {

					c = 0;
				}
				counts.put(addr, c);
				// Logger.err("["+host+"] Session dropped (delay="+(now-lastConnTime)+"ms)");
				return false;
			} else {
				clients.put(addr, now);
				return true;
			}
		} else {
			clients.put(addr, now);
			return true;
		}
	}

	@Override
	public void sessionCreated(final NextFilter nextFilter,
			final IoSession session) throws Exception {
		if (!isConnectionOk(session)) {
			session.close();
			return;
		}
		nextFilter.sessionCreated(session);
	}

	/**
	 * Sets the interval between connections from a client. This value is
	 * measured in milliseconds.
	 * 
	 * @param allowedInterval
	 *            The number of milliseconds a client is allowed to wait before
	 *            making another successful connection
	 */
	public void setAllowedInterval(final long allowedInterval) {
		this.allowedInterval = allowedInterval;
	}

}
