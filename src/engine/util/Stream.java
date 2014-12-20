package engine.util;

import game.Config;

/*
 * Project Insanity - Evolved v.3
 * Stream.java
 */

public class Stream {

	private static final int frameStackSize = 10;
	private int frameStackPtr = -1;
	private final int frameStack[] = new int[Stream.frameStackSize];
	public byte buffer[] = null;
	public int currentOffset = 0;
	public int bitPosition = 0;
	public static int bitMaskOut[] = new int[32];

	static {
		for (int i = 0; i < 32; i++) {
			Stream.bitMaskOut[i] = (1 << i) - 1;
		}
	}

	public ISAACRandomGen packetEncryption = null;

	public Stream() {
	}

	public Stream(final byte abyte0[]) {
		buffer = abyte0;
		currentOffset = 0;
	}

	public void createFrame(final int id) {
		ensureCapacity(1);
		buffer[currentOffset++] = (byte) (id + packetEncryption.getNextKey());
	}

	public void createFrameVarSize(final int id) {
		ensureCapacity(3);
		buffer[currentOffset++] = (byte) (id + packetEncryption.getNextKey());
		buffer[currentOffset++] = 0;
		if (frameStackPtr >= Stream.frameStackSize - 1) {
			throw new RuntimeException("Stack overflow");
		} else {
			frameStack[++frameStackPtr] = currentOffset;
		}
	}

	public void createFrameVarSizeWord(final int id) {
		ensureCapacity(2);
		buffer[currentOffset++] = (byte) (id + packetEncryption.getNextKey());
		writeWord(0);
		if (frameStackPtr >= Stream.frameStackSize - 1) {
			throw new RuntimeException("Stack overflow");
		} else {
			frameStack[++frameStackPtr] = currentOffset;
		}
	}

	public void endFrameVarSize() {
		if (frameStackPtr < 0) {
			throw new RuntimeException("Stack empty");
		} else {
			writeFrameSize(currentOffset - frameStack[frameStackPtr--]);
		}
	}

	public void endFrameVarSizeWord() {
		if (frameStackPtr < 0) {
			throw new RuntimeException("Stack empty");
		} else {
			writeFrameSizeWord(currentOffset - frameStack[frameStackPtr--]);
		}
	}

	public void ensureCapacity(final int len) {
		if (currentOffset + len + 1 >= buffer.length) {
			final byte[] oldBuffer = buffer;
			final int newLength = buffer.length * 2;
			buffer = new byte[newLength];
			System.arraycopy(oldBuffer, 0, buffer, 0, oldBuffer.length);
			ensureCapacity(len);
		}
	}

	public void finishBitAccess() {
		currentOffset = (bitPosition + 7) / 8;
	}

	public void initBitAccess() {
		bitPosition = currentOffset * 8;
	}

	public void readBytes(final byte abyte0[], final int i, final int j) {
		for (int k = j; k < j + i; k++) {
			abyte0[k] = buffer[currentOffset++];
		}
	}

	public void readBytes_reverse(final byte abyte0[], final int i, final int j) {
		for (int k = j + i - 1; k >= j; k--) {
			abyte0[k] = buffer[currentOffset++];
		}
	}

	public void readBytes_reverseA(final byte abyte0[], final int i, final int j) {
		ensureCapacity(i);
		for (int k = j + i - 1; k >= j; k--) {
			abyte0[k] = (byte) (buffer[currentOffset++] - 128);
		}
	}

	public int readDWord() {
		currentOffset += 4;
		return ((buffer[currentOffset - 4] & 0xff) << 24)
				+ ((buffer[currentOffset - 3] & 0xff) << 16)
				+ ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
	}

	public int readDWord_v1() {
		currentOffset += 4;
		return ((buffer[currentOffset - 2] & 0xff) << 24)
				+ ((buffer[currentOffset - 1] & 0xff) << 16)
				+ ((buffer[currentOffset - 4] & 0xff) << 8)
				+ (buffer[currentOffset - 3] & 0xff);
	}

	public int readDWord_v2() {
		currentOffset += 4;
		return ((buffer[currentOffset - 3] & 0xff) << 24)
				+ ((buffer[currentOffset - 4] & 0xff) << 16)
				+ ((buffer[currentOffset - 1] & 0xff) << 8)
				+ (buffer[currentOffset - 2] & 0xff);
	}

	public long readQWord() {
		final long l = readDWord() & 0xffffffffL;
		final long l1 = readDWord() & 0xffffffffL;
		return (l << 32) + l1;
	}
	
	public long readQWord2() {
		final long l = readDWord() & 0xffffffffL;
		final long l1 = readDWord() & 0xffffffffL;
		return (l << 32) + l1;
	}

	public byte readSignedByte() {
		return buffer[currentOffset++];
	}

	public byte readSignedByteA() {
		return (byte) (buffer[currentOffset++] - 128);
	}

	public byte readSignedByteC() {
		return (byte) -buffer[currentOffset++];
	}

	public byte readSignedByteS() {
		return (byte) (128 - buffer[currentOffset++]);
	}

	public int readSignedWord() {
		currentOffset += 2;
		int i = ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readSignedWordA() {
		currentOffset += 2;
		int i = ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] - 128 & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readSignedWordBigEndian() {
		currentOffset += 2;
		int i = ((buffer[currentOffset - 1] & 0xff) << 8)
				+ (buffer[currentOffset - 2] & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readSignedWordBigEndianA() {
		currentOffset += 2;
		int i = ((buffer[currentOffset - 1] & 0xff) << 8)
				+ (buffer[currentOffset - 2] - 128 & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public java.lang.String readString() {
		final int i = currentOffset;
		while (buffer[currentOffset++] != 10) {
			;
		}
		return new String(buffer, i, currentOffset - i - 1);
	}

	public int readUnsignedByte() {
		return buffer[currentOffset++] & 0xff;
	}

	public int readUnsignedByteA() {
		return buffer[currentOffset++] - 128 & 0xff;
	}

	public int readUnsignedByteC() {
		return -buffer[currentOffset++] & 0xff;
	}

	public int readUnsignedByteS() {
		return 128 - buffer[currentOffset++] & 0xff;
	}

	public int readUnsignedWord() {
		currentOffset += 2;
		return ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] & 0xff);
	}

	public int readUnsignedWordA() {
		currentOffset += 2;
		return ((buffer[currentOffset - 2] & 0xff) << 8)
				+ (buffer[currentOffset - 1] - 128 & 0xff);
	}

	public int readUnsignedWordBigEndian() {
		currentOffset += 2;
		return ((buffer[currentOffset - 1] & 0xff) << 8)
				+ (buffer[currentOffset - 2] & 0xff);
	}

	public int readUnsignedWordBigEndianA() {
		currentOffset += 2;
		return ((buffer[currentOffset - 1] & 0xff) << 8)
				+ (buffer[currentOffset - 2] - 128 & 0xff);
	}

	public void reset() {
		if (!(currentOffset > Config.BUFFER_SIZE)) {
			final byte[] oldBuffer = buffer;
			buffer = new byte[Config.BUFFER_SIZE];
			for (int i = 0; i < currentOffset; i++) {
				buffer[i] = oldBuffer[i];
			}
		}
	}

	public void write3Byte(final int i) {
		ensureCapacity(3);
		buffer[currentOffset++] = (byte) (i >> 16);
		buffer[currentOffset++] = (byte) (i >> 8);
		buffer[currentOffset++] = (byte) i;
	}

	public void writeBits(int numBits, final int value) {
		ensureCapacity((int) Math.ceil(numBits * 8) * 4);
		int bytePos = bitPosition >> 3;
		int bitOffset = 8 - (bitPosition & 7);
		bitPosition += numBits;

		for (; numBits > bitOffset; bitOffset = 8) {
			buffer[bytePos] &= ~Stream.bitMaskOut[bitOffset];
			buffer[bytePos++] |= value >> numBits - bitOffset
					& Stream.bitMaskOut[bitOffset];

			numBits -= bitOffset;
		}
		if (numBits == bitOffset) {
			buffer[bytePos] &= ~Stream.bitMaskOut[bitOffset];
			buffer[bytePos] |= value & Stream.bitMaskOut[bitOffset];
		} else {
			buffer[bytePos] &= ~(Stream.bitMaskOut[numBits] << bitOffset
					- numBits);
			buffer[bytePos] |= (value & Stream.bitMaskOut[numBits]) << bitOffset
					- numBits;
		}
	}

	public void writeByte(final int i) {
		ensureCapacity(1);
		buffer[currentOffset++] = (byte) i;
	}

	public void writeByteA(final int i) {
		ensureCapacity(1);
		buffer[currentOffset++] = (byte) (i + 128);
	}

	public void writeByteC(final int i) {
		ensureCapacity(1);
		buffer[currentOffset++] = (byte) -i;
	}

	public void writeBytes(final byte abyte0[], final int i, final int j) {
		ensureCapacity(i);
		for (int k = j; k < j + i; k++) {
			buffer[currentOffset++] = abyte0[k];
		}
	}

	public void writeByteS(final int i) {
		ensureCapacity(1);
		buffer[currentOffset++] = (byte) (128 - i);
	}

	public void writeBytes_reverse(final byte abyte0[], final int i, final int j) {
		ensureCapacity(i);
		for (int k = j + i - 1; k >= j; k--) {
			buffer[currentOffset++] = abyte0[k];
		}
	}

	public void writeBytes_reverseA(final byte abyte0[], final int i,
			final int j) {
		ensureCapacity(i);
		for (int k = j + i - 1; k >= j; k--) {
			buffer[currentOffset++] = (byte) (abyte0[k] + 128);
		}
	}

	public void writeDWord(final int i) {
		ensureCapacity(4);
		buffer[currentOffset++] = (byte) (i >> 24);
		buffer[currentOffset++] = (byte) (i >> 16);
		buffer[currentOffset++] = (byte) (i >> 8);
		buffer[currentOffset++] = (byte) i;
	}

	public void writeDWord_v1(final int i) {
		ensureCapacity(4);
		buffer[currentOffset++] = (byte) (i >> 8);
		buffer[currentOffset++] = (byte) i;
		buffer[currentOffset++] = (byte) (i >> 24);
		buffer[currentOffset++] = (byte) (i >> 16);
	}

	public void writeDWord_v2(final int i) {
		ensureCapacity(4);
		buffer[currentOffset++] = (byte) (i >> 16);
		buffer[currentOffset++] = (byte) (i >> 24);
		buffer[currentOffset++] = (byte) i;
		buffer[currentOffset++] = (byte) (i >> 8);
	}

	public void writeDWordBigEndian(final int i) {
		ensureCapacity(4);
		buffer[currentOffset++] = (byte) i;
		buffer[currentOffset++] = (byte) (i >> 8);
		buffer[currentOffset++] = (byte) (i >> 16);
		buffer[currentOffset++] = (byte) (i >> 24);
	}

	public void writeFrameSize(final int i) {
		buffer[currentOffset - i - 1] = (byte) i;
	}

	public void writeFrameSizeWord(final int i) {
		buffer[currentOffset - i - 2] = (byte) (i >> 8);
		buffer[currentOffset - i - 1] = (byte) i;
	}

	public void writeQWord(final long l) {
		ensureCapacity(8);
		buffer[currentOffset++] = (byte) (int) (l >> 56);
		buffer[currentOffset++] = (byte) (int) (l >> 48);
		buffer[currentOffset++] = (byte) (int) (l >> 40);
		buffer[currentOffset++] = (byte) (int) (l >> 32);
		buffer[currentOffset++] = (byte) (int) (l >> 24);
		buffer[currentOffset++] = (byte) (int) (l >> 16);
		buffer[currentOffset++] = (byte) (int) (l >> 8);
		buffer[currentOffset++] = (byte) (int) l;
	}

	public void writeString(final java.lang.String s) {
		ensureCapacity(s.length());
		System.arraycopy(s.getBytes(), 0, buffer, currentOffset, s.length());
		currentOffset += s.length();
		buffer[currentOffset++] = 10;
	}

	public void writeWord(final int i) {
		ensureCapacity(2);
		buffer[currentOffset++] = (byte) (i >> 8);
		buffer[currentOffset++] = (byte) i;
	}

	public void writeWordA(final int i) {
		ensureCapacity(2);
		buffer[currentOffset++] = (byte) (i >> 8);
		buffer[currentOffset++] = (byte) (i + 128);
	}

	public void writeWordBigEndian(final int i) {
		ensureCapacity(2);
		buffer[currentOffset++] = (byte) i;
		buffer[currentOffset++] = (byte) (i >> 8);
	}

	public void writeWordBigEndian_dup(final int i) {
		ensureCapacity(2);
		buffer[currentOffset++] = (byte) i;
		buffer[currentOffset++] = (byte) (i >> 8);
	}

	public void writeWordBigEndianA(final int i) {
		ensureCapacity(2);
		buffer[currentOffset++] = (byte) (i + 128);
		buffer[currentOffset++] = (byte) (i >> 8);
	}

}
