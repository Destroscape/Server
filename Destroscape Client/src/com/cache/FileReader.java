package com.cache;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class FileReader {

	/**
	 * Initializes the FileReader.
	 */
	public FileReader() {
		files = new LinkedList<FileChunk>();
	}

	/**
	 * Reads the cache.
	 * @param name
	 */
	public void read(String name) {
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(name));
			int count = in.readShort();
			for (int index = 0; index < count; index++) {
				String path = in.readUTF();
				long length = in.readLong();
				byte[] data = new byte[(int) length];
				in.readFully(data);
				data = DataUtilities.decompress(data);
				files.add(new FileChunk(path, data));
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the specified file.
	 * @param directories
	 * @return
	 */
	public FileChunk getFile(String ...directories) {
		if (directories != null) {
			String path = "";
			for (int index = 0; index < directories.length; index++) {
				path += (index > 0 ? "\\" : "") + directories[index];
			}
			for (FileChunk file : files) {
				if (file.getPath().equals(path + file.getExtension())) {
					return file;
				}
			}
		}
		return null;
	}

	/**
	 * Returns the files as a LinkedList.
	 * @return
	 */
	public LinkedList<FileChunk> getFiles() {
		return files;
	}

	/**
	 * The files in the LinkedList.
	 */
	private LinkedList<FileChunk> files;

}
