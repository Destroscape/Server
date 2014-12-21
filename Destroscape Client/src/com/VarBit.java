package com;

import com.stream.Stream;
import com.stream.StreamLoader;

public final class VarBit {

	public static void unpackConfig(StreamLoader streamLoader) {
		Stream stream = new Stream(streamLoader.getDataForName("varbit.dat"));
		int cacheSize = stream.readUnsignedWord();
		if (cache == null)
			cache = new VarBit[cacheSize];
		for (int j = 0; j < cacheSize; j++) {
			if (cache[j] == null)
				cache[j] = new VarBit();
			cache[j].readValues(stream);
			if (cache[j].aBoolean651)
				Varp.cache[cache[j].anInt648].aBoolean713 = true;
		}

		if (stream.currentOffset != stream.buffer.length)
			System.out.println("varbit load mismatch");
	}

	public void readValues(Stream stream) {
		anInt648 = stream.readUnsignedWord();
		anInt649 = stream.readUnsignedByte();
		anInt650 = stream.readUnsignedByte();
	}

	private VarBit() {
		aBoolean651 = false;
	}

	public static VarBit cache[];
	public int anInt648;
	public int anInt649;
	public int anInt650;
	private boolean aBoolean651;
}
