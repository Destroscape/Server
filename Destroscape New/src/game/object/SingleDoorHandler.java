package game.object;

import game.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Project Insanity - Evolved v.3
 * SingleDoorHandler.java
 */

public class SingleDoorHandler {

	private static SingleDoorHandler singleton = null;

	private static boolean alreadyOpen(final int id) {
		for (final int openDoor : SingleDoorHandler.openDoors) {
			if (openDoor == id) {
				return true;
			}
		}
		return false;
	}

	private static int getNextFace(final SingleDoorHandler d) {
		int f = d.originalFace;
		if (d.type == 0) {
			if (d.open == 0) {
				if (d.originalFace == 0 && d.currentFace == 0) {
					f = 1;
				} else if (d.originalFace == 1 && d.currentFace == 1) {
					f = 2;
				} else if (d.originalFace == 2 && d.currentFace == 2) {
					f = 3;
				} else if (d.originalFace == 3 && d.currentFace == 3) {
					f = 0;
				} else if (d.originalFace != d.currentFace) {
					f = d.originalFace;
				}
			} else if (d.open == 1) {
				if (d.originalFace == 0 && d.currentFace == 0) {
					f = 3;
				} else if (d.originalFace == 1 && d.currentFace == 1) {
					f = 0;
				} else if (d.originalFace == 2 && d.currentFace == 2) {
					f = 1;
				} else if (d.originalFace == 3 && d.currentFace == 3) {
					f = 2;
				} else if (d.originalFace != d.currentFace) {
					f = d.originalFace;
				}
			}
		} else if (d.type == 9) {
			if (d.open == 0) {
				if (d.originalFace == 0 && d.currentFace == 0) {
					f = 3;
				} else if (d.originalFace == 1 && d.currentFace == 1) {
					f = 2;
				} else if (d.originalFace == 2 && d.currentFace == 2) {
					f = 1;
				} else if (d.originalFace == 3 && d.currentFace == 3) {
					f = 0;
				} else if (d.originalFace != d.currentFace) {
					f = d.originalFace;
				}
			} else if (d.open == 1) {
				if (d.originalFace == 0 && d.currentFace == 0) {
					f = 3;
				} else if (d.originalFace == 1 && d.currentFace == 1) {
					f = 0;
				} else if (d.originalFace == 2 && d.currentFace == 2) {
					f = 1;
				} else if (d.originalFace == 3 && d.currentFace == 3) {
					f = 2;
				} else if (d.originalFace != d.currentFace) {
					f = d.originalFace;
				}
			}
		}
		d.currentFace = f;
		return f;
	}

	public static SingleDoorHandler getSingleton() {
		if (SingleDoorHandler.singleton == null) {
			SingleDoorHandler.singleton = new SingleDoorHandler(
					"./Data/cfg/Single Doors.txt");
		}
		return SingleDoorHandler.singleton;
	}

	private final List<SingleDoorHandler> singleDoorHandler = new ArrayList<SingleDoorHandler>();
	private File doorFile;
	private int doorId;
	private int originalId;
	private int doorX;
	private int doorY;
	private int originalX;
	private int originalY;
	private int doorZ;
	private int originalFace;
	private int currentFace;

	private int type;

	private int open;

	private static int[] openDoors = { 1504, 1514, 1517, 1520, 1531, 1534,
			2033, 2035, 2037, 2998, 3271, 4468, 4697, 6101, 6103, 6105, 6107,
			6109, 6111, 6113, 6115, 6976, 6978, 8696, 8819, 10261, 10263,
			10265, 11708, 11710, 11712, 11715, 11994, 12445, 13002,28589, };

	private SingleDoorHandler(final int door, final int x, final int y,
			final int z, final int face, final int type, final int open) {
		doorId = door;
		originalId = door;
		doorX = x;
		doorY = y;
		originalX = x;
		originalY = y;
		doorZ = z;
		originalFace = face;
		currentFace = face;
		this.type = type;
		this.open = open;
	}

	private SingleDoorHandler(final String file) {
		doorFile = new File(file);
	}

	private SingleDoorHandler getDoor(final int id, final int x, final int y,
			final int z) {
		for (final SingleDoorHandler d : singleDoorHandler) {
			if (d.doorId == id) {
				if (d.doorX == x && d.doorY == y && d.doorZ == z) {
					return d;
				}
			}
		}
		return null;
	}

	public boolean handleDoor(final int id, final int x, final int y,
			final int z) {
		final SingleDoorHandler d = getDoor(id, x, y, z);
		if (d == null) {
			if (DoubleDoorHandler.getSingleton().handleDoor(id, x, y, z)) {
				return true;
			}
			return false;
		}
		int xAdjustment = 0, yAdjustment = 0;
		if (d.type == 0) {
			if (d.open == 0) {
				if (d.originalFace == 0 && d.currentFace == 0) {
					xAdjustment = -1;
				} else if (d.originalFace == 1 && d.currentFace == 1) {
					yAdjustment = 1;
				} else if (d.originalFace == 2 && d.currentFace == 2) {
					xAdjustment = 1;
				} else if (d.originalFace == 3 && d.currentFace == 3) {
					yAdjustment = -1;
				}
			} else if (d.open == 1) {
				if (d.originalFace == 0 && d.currentFace == 0) {
					yAdjustment = 1;
				} else if (d.originalFace == 1 && d.currentFace == 1) {
					xAdjustment = 1;
				} else if (d.originalFace == 2 && d.currentFace == 2) {
					yAdjustment = -1;
				} else if (d.originalFace == 3 && d.currentFace == 3) {
					xAdjustment = -1;
				}
			}
		} else if (d.type == 9) {
			if (d.open == 0) {
				if (d.originalFace == 0 && d.currentFace == 0) {
					xAdjustment = 1;
				} else if (d.originalFace == 1 && d.currentFace == 1) {
					xAdjustment = 1;
				} else if (d.originalFace == 2 && d.currentFace == 2) {
					xAdjustment = -1;
				} else if (d.originalFace == 3 && d.currentFace == 3) {
					xAdjustment = -1;
				}
			} else if (d.open == 1) {
				if (d.originalFace == 0 && d.currentFace == 0) {
					xAdjustment = 1;
				} else if (d.originalFace == 1 && d.currentFace == 1) {
					xAdjustment = 1;
				} else if (d.originalFace == 2 && d.currentFace == 2) {
					xAdjustment = -1;
				} else if (d.originalFace == 3 && d.currentFace == 3) {
					xAdjustment = -1;
				}
			}
		}
		if (xAdjustment != 0 || yAdjustment != 0) {
			final Objects o = new Objects(-1, d.doorX, d.doorY, d.doorZ, 0,
					d.type, 0);
			Server.objectHandler.placeObject(o);
		}
		if (d.doorX == d.originalX && d.doorY == d.originalY) {
			d.doorX += xAdjustment;
			d.doorY += yAdjustment;
		} else {
			final Objects o = new Objects(-1, d.doorX, d.doorY, d.doorZ, 0,
					d.type, 0);
			Server.objectHandler.placeObject(o);
			d.doorX = d.originalX;
			d.doorY = d.originalY;
		}
		if (d.doorId == d.originalId) {
			if (d.open == 0) {
				d.doorId += 1;
			} else if (d.open == 1) {
				d.doorId -= 1;
			}
		} else if (d.doorId != d.originalId) {
			if (d.open == 0) {
				d.doorId -= 1;
			} else if (d.open == 1) {
				d.doorId += 1;
			}
		}
		Server.objectHandler.placeObject(new Objects(d.doorId, d.doorX,
				d.doorY, d.doorZ, SingleDoorHandler.getNextFace(d), d.type, 0));
		return true;
	}

	public void load() {
		final long start = System.currentTimeMillis();
		try {
			SingleDoorHandler.singleton.processLineByLine();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("[Stage - 4] Finished Loading: " + singleDoorHandler.size()
				+ " Doors in: " + (System.currentTimeMillis() - start) + "ms.");
	}

	protected void processLine(final String line) {
		final Scanner scanner = new Scanner(line);
		scanner.useDelimiter(" ");
		try {
			while (scanner.hasNextLine()) {
				final int id = Integer.parseInt(scanner.next());
				final int x = Integer.parseInt(scanner.next());
				final int y = Integer.parseInt(scanner.next());
				final int f = Integer.parseInt(scanner.next());
				final int z = Integer.parseInt(scanner.next());
				final int t = Integer.parseInt(scanner.next());
				singleDoorHandler.add(new SingleDoorHandler(id, x, y, z, f, t,
						SingleDoorHandler.alreadyOpen(id) ? 1 : 0));
			}
		} finally {
			scanner.close();
		}
	}

	private final void processLineByLine() throws FileNotFoundException {
		final Scanner scanner = new Scanner(new FileReader(doorFile));
		try {
			while (scanner.hasNextLine()) {
				processLine(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
	}

}
