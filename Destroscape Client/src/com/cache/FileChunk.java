package com.cache;

public class FileChunk {

	/**
	 * Creates the file chunk for the path and data.
	 * @param path
	 * @param data
	 */
	public FileChunk(String path, byte[] data) {
		this.path = path;
		this.data = data;
	}

	/**
	 * Returns the name of the FileChunk (the file name).
	 * @return
	 */
	public String getName() {
		String[] directories = null;
		if (System.getProperty("file.separator").equals("/")) {
			directories = getPath().split(System.getProperty("file.separator"));
		} else {
			directories = getPath().split("\\\\");
		}
		if (directories != null) {
			return directories[directories.length - 1].replaceAll("/", "\\");
		}
		return null;
	}

	/**
	 * Returns the name of the FileChunk without the extension (the file name without extension).
	 * @return
	 */
	public String getNameWithoutExtension() {
		return getName().substring(0, getName().indexOf("."));
	}

	/**
	 * Gets the extension of the FileChunk.
	 * @return
	 */
	public String getExtension() {
		return getName().substring(getName().indexOf("."), getName().length());
	}

	/**
	 * Returns whether or not the FileChunk is an image.
	 * @return
	 */
	public boolean isImage() {
		String extension = getExtension().toLowerCase();
		String[] acceptable = { ".png", ".jpg", ".jpeg", ".bmp", ".gif" };
		for (String s : acceptable) {
			if (extension.equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the data stored in the byte array.
	 * @return
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Returns the path of the file.
	 * @return
	 */
	public String getPath() {
		return path.replace("." + System.getProperty("file.separator") + "in" + System.getProperty("file.separator"), "");
	}

	/**
	 * The byte array that stores the file data.
	 */
	private byte[] data;

	/**
	 * The path of the file.
	 */
	private String path;

}