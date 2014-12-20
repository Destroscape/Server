package engine.network;

import game.net.packets.Packet;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/*
 * Project Insanity - Evolved v.3
 * RS2ProtocolEncoder,java
 */

public class RS2ProtocolEncoder implements ProtocolEncoder {

	/**
	 * Only CodecFactory can create us.
	 */
	protected RS2ProtocolEncoder() {
	}

	@Override
	/**
	 * Releases resources used by this encoder.
	 * @param session
	 */
	public void dispose(final IoSession session) throws Exception {
	}

	@Override
	/**
	 * Encodes a message.
	 * @param session
	 * @param message
	 * @param out
	 */
	public void encode(final IoSession session, final Object message,
			final ProtocolEncoderOutput out) throws Exception {
		try {
			synchronized (session) {
				final Packet p = (Packet) message;
				final byte[] data = p.getData();
				final int dataLength = p.getLength();
				ByteBuffer buffer;
				if (!p.isBare()) {
					buffer = ByteBuffer.allocate(dataLength + 3);
					final int id = p.getId();
					buffer.put((byte) id);
					if (p.getSize() != Packet.Size.Fixed) { // variable length
						// Logger.log("variable length: id="+id+",dataLength="+dataLength);
						if (p.getSize() == Packet.Size.VariableByte) {
							if (dataLength > 255) {
								throw new IllegalArgumentException(
										"Tried to send packet length "
												+ dataLength
												+ " in 8 bits [pid="
												+ p.getId() + "]");
							}
							buffer.put((byte) dataLength);
						} else if (p.getSize() == Packet.Size.VariableShort) {
							if (dataLength > 65535) {
								throw new IllegalArgumentException(
										"Tried to send packet length "
												+ dataLength
												+ " in 16 bits [pid="
												+ p.getId() + "]");
							}
							buffer.put((byte) (dataLength >> 8));
							buffer.put((byte) dataLength);
						}
					}
				} else {
					buffer = ByteBuffer.allocate(dataLength);
				}
				buffer.put(data, 0, dataLength);
				buffer.flip();
				out.write(buffer);
			}
		} catch (final Exception err) {
			err.printStackTrace();
		}
	}

}
