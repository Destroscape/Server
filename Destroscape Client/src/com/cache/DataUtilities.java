package com.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class DataUtilities {

	/**
	 * Returns the file as a byte array.
	 * @param file
	 * @return
	 */
	public static byte[] getData(File file) {
		try {
			byte[] data = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(data);
			fis.close();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Compresses the raw data in gzip format.
	 * @param raw
	 * @return
	 */
	public static byte[] compress(byte[] raw) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzo = new GZIPOutputStream(bos);
			try {
				gzo.write(raw, 0, raw.length);
			} finally {
				gzo.close();
				bos.close();
			}
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Decompresses the data from gzip.
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] decompress(byte[] data) throws IOException {
		GZIPInputStream gzi = new GZIPInputStream(new ByteArrayInputStream(data));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		while ((len = gzi.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		out.close();
		return out.toByteArray();
	}

	/**
	 * Writes data to the specified file.
	 * @param file
	 * @param data
	 */
	public static void write(String file, byte[] data) {
		try {
			if (data != null) {
				OutputStream out = new FileOutputStream(file);
				out.write(data);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
