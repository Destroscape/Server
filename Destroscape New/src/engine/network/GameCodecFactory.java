package engine.network;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import engine.util.ISAACRandomGen;

/*
 * Project Insanity - Evolved v.3
 * GameCodecFactory.java
 */

public class GameCodecFactory implements ProtocolCodecFactory {

	/**
	 * The encoder.
	 */
	private final ProtocolEncoder encoder = new RS2ProtocolEncoder();

	/**
	 * The decoder.
	 */
	private final ProtocolDecoder decoder;

	public GameCodecFactory(final ISAACRandomGen inC) {
		decoder = new RS2ProtocolDecoder(inC);
	}

	@Override
	/**
	 * Get the decoder.
	 */
	public ProtocolDecoder getDecoder() throws Exception {
		return decoder;
	}

	@Override
	/**
	 * Get the encoder.
	 */
	public ProtocolEncoder getEncoder() throws Exception {
		return encoder;
	}

}
