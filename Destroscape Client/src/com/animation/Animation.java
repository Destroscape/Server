package com.animation;

import com.Class36;
import com.stream.Stream;
import com.stream.StreamLoader;
// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class Animation {

	public static void unpackConfig(StreamLoader streamLoader) {
		Stream stream = new Stream(streamLoader.getDataForName("seq.dat"));
		int length = stream.readUnsignedWord();
		System.out.println("667 Animations Loaded: " + length);
		if (anims == null)
			anims = new Animation[length + 1000];
		for (int j = 0; j < length; j++) {
			if (anims[j] == null)
				anims[j] = new Animation();
			anims[j].readValues(stream);

			try {
				/**
				 * Ganodermic runt
				 */

				anims[15475] = new Animation(); // standanim gano runt
				anims[15475].frameCount = 24;
				anims[15475].delays = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
						4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
				anims[15475].frames = new int[] { 250675320, 250675227,
						250675235, 250675318, 250675289, 250675274, 250675214,
						250675239, 250675253, 250675220, 250675243, 250675278,
						250675225, 250675329, 250675231, 250675327, 250675228,
						250675209, 250675298, 250675323, 250675269, 250675207,
						250675248, 250675255 };

				anims[15476] = new Animation(); // walkanim gano runt
				anims[15476].frameCount = 10;
				anims[15476].delays = new int[] { 7, 7, 7, 7, 7, 7, 7, 3, 3, 1 };
				anims[15476].frames = new int[] { 250675240, 250675263,
						250675250, 250675234, 250675226, 250675212, 250675286,
						250675265, 250675245, 250675236 };

				anims[15477] = new Animation(); // attackanim gano runt
				anims[15477].frameCount = 25;
				anims[15477].priority = 6;
				anims[15477].delays = new int[] { 3, 3, 3, 3, 1, 3, 3, 3, 3, 3,
						3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
				anims[15477].frames = new int[] { 250675268, 250675315,
						250675316, 250675288, 250675203, 250675270, 250675293,
						250675325, 250675304, 250675299, 250675202, 250675205,
						250675251, 250675331, 250675261, 250675291, 250675317,
						250675322, 250675297, 250675279, 250675332, 250675295,
						250675275, 250675292, 250675219 };

				anims[15480] = new Animation(); // deathanim gano runt
				anims[15480].frameCount = 34;
				anims[15480].priority = 10;
				anims[15480].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
						3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
						4, 5, 6, 7, 8, 8 };
				anims[15480].frames = new int[] { 250675257, 250675224,
						250675326, 250675237, 250675256, 250675244, 250675217,
						250675221, 250675229, 250675241, 250675232, 250675204,
						250675283, 250675271, 250675267, 250675314, 250675264,
						250675246, 250675303, 250675306, 250675260, 250675313,
						250675312, 250675277, 250675223, 250675285, 250675296,
						250675334, 250675254, 250675242, 250675309, 250675311,
						250675200, 250675280 };

				/**
				 * Ganodermic Beast
				 */

				anims[15473] = new Animation(); // deathanim gano beast
				anims[15473].frameCount = 43;
				anims[15473].priority = 10;
				anims[15473].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
						3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6, 6, 6,
						6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3 };
				anims[15473].frames = new int[] { 250609813, 250609795,
						250609699, 250609754, 250609736, 250609732, 250609775,
						250609688, 250609664, 250609683, 250609786, 250609707,
						250609724, 250609768, 250609761, 250609697, 250609782,
						250609796, 250609757, 250609805, 250609791, 250609715,
						250609735, 250609701, 250609693, 250609817, 250609692,
						250609788, 250609783, 250609670, 250609751, 250609752,
						250609723, 250609695, 250609684, 250609716, 250609673,
						250609812, 250609729, 250609772, 250609676, 250609820,
						250609773 };

				anims[15466] = new Animation(); // atack anim gano beast
				anims[15466].frameCount = 20;
				anims[15466].priority = 6;
				anims[15466].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
						3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
				anims[15466].frames = new int[] { 250609789, 250609819,
						250609762, 250609728, 250609793, 250609818, 250609718,
						250609704, 250609740, 250609672, 250609756, 250609686,
						250609719, 250609753, 250609682, 250609674, 250609668,
						250609708, 250609678, 250609746 };

				anims[15465] = new Animation(); // walkanim gano beast
				anims[15465].frameCount = 28;
				anims[15465].delays = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
						3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
				anims[15465].frames = new int[] { 250609792, 250609730,
						250609769, 250609806, 250609801, 250609760, 250609787,
						250609749, 250609734, 250609696, 250609689, 250609685,
						250609700, 250609764, 250609671, 250609731, 250609681,
						250609774, 250609821, 250609677, 250609711, 250609742,
						250609758, 250609703, 250609721, 250609804, 250609666,
						250609737 };

				anims[15464] = new Animation(); // standanim gano beast
				anims[15464].frameCount = 24;
				anims[15464].delays = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
						5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
				anims[15464].frames = new int[] { 250609690, 250609706,
						250609808, 250609726, 250609712, 250609765, 250609680,
						250609705, 250609738, 250609709, 250609687, 250609748,
						250609722, 250609766, 250609739, 250609747, 250609741,
						250609771, 250609778, 250609780, 250609720, 250609727,
						250609784, 250609814 };

			} catch (Exception _ex) {
			}
		}
	}

	public int method258(int i) {
		int j = delays[i];
		if (j == 0) {
			Class36 class36 = Class36.method531(frames[i]);
			if (class36 != null)
				j = delays[i] = class36.anInt636;
		}
		if (j == 0)
			j = 1;
		return j;
	}

	private void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				break;
			if (i == 1) {
				frameCount = stream.readUnsignedWord();
				frames = new int[frameCount];
				anIntArray354 = new int[frameCount];
				delays = new int[frameCount];
				for (int j = 0; j < frameCount; j++) {
					frames[j] = stream.readDWord();
					anIntArray354[j] = -1;
				}

				for (int j = 0; j < frameCount; j++)
					delays[j] = stream.readUnsignedByte();

			} else if (i == 2)
				anInt356 = stream.readUnsignedWord();
			else if (i == 3) {
				int k = stream.readUnsignedByte();
				anIntArray357 = new int[k + 1];
				for (int l = 0; l < k; l++)
					anIntArray357[l] = stream.readUnsignedByte();
				anIntArray357[k] = 0x98967f;
			} else if (i == 4)
				aBoolean358 = true;
			else if (i == 5)
				priority = stream.readUnsignedByte();
			else if (i == 6)
				anInt360 = stream.readUnsignedWord();
			else if (i == 7)
				anInt361 = stream.readUnsignedWord();
			else if (i == 8)
				anInt362 = stream.readUnsignedByte();
			else if (i == 9)
				anInt363 = stream.readUnsignedByte();
			else if (i == 10)
				anInt364 = stream.readUnsignedByte();
			else if (i == 11)
				anInt365 = stream.readUnsignedByte();
			else if (i == 12)
				stream.readDWord();
			else
				System.out.println("Error unrecognised seq config code: " + i);
		} while (true);
		if (frameCount == 0) {
			frameCount = 1;
			frames = new int[1];
			frames[0] = -1;
			anIntArray354 = new int[1];
			anIntArray354[0] = -1;
			delays = new int[1];
			delays[0] = -1;
		}
		if (anInt363 == -1)
			if (anIntArray357 != null)
				anInt363 = 2;
			else
				anInt363 = 0;
		if (anInt364 == -1) {
			if (anIntArray357 != null) {
				anInt364 = 2;
				return;
			}
			anInt364 = 0;
		}
		if (anInt360 == 65535)
			anInt360 = 0;
		if (anInt361 == 65535)
			anInt361 = 0;
	}

	private Animation() {
		anInt356 = -1;
		aBoolean358 = false;
		priority = 5;
		anInt360 = -1;
		anInt361 = -1;
		anInt362 = 99;
		anInt363 = -1;
		anInt364 = -1;
		anInt365 = 2;
	}

	public static Animation anims[];
	public int frameCount;
	public int frames[];
	public int anIntArray354[];
	private int[] delays;
	public int anInt356;
	public int anIntArray357[];
	public boolean aBoolean358;
	public int priority;
	public int anInt360;
	public int anInt361;
	public int anInt362;
	public int anInt363;
	public int anInt364;
	public int anInt365;
	public static int anInt367;
}
