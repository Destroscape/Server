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
 * DoubleDoorHandler.java
 */

public class DoubleDoorHandler {

	private static DoubleDoorHandler singleton = null;

	private static int getNextLeftFace(final DoubleDoorHandler d) {
		int f = d.originalFace;
		if (d.open == 0) {
			if (d.originalFace == 0 && d.currentFace == 0) {
				f = 3;
			} else if (d.originalFace == 1 && d.currentFace == 1) {
				f = 0;
			} else if (d.originalFace == 2 && d.currentFace == 2) {
				f = 1;
			} else if (d.originalFace == 3 && d.currentFace == 3) {
				f = 0;
			} else if (d.originalFace != d.currentFace) {
				f = d.originalFace;
			}
		} else if (d.open == 1) {
			if (d.originalFace == 0 && d.currentFace == 0) {
				f = 1;
			} else if (d.originalFace == 1 && d.currentFace == 1) {
				f = 2;
			} else if (d.originalFace == 2 && d.currentFace == 2) {
				f = 1;
			} else if (d.originalFace == 3 && d.currentFace == 3) {
				f = 2;
			} else if (d.originalFace != d.currentFace) {
				f = d.originalFace;
			}
		}
		d.currentFace = f;
		return f;
	}

	private static int getNextRightFace(final DoubleDoorHandler d) {
		int f = d.originalFace;
		if (d.open == 0) {
			if (d.originalFace == 0 && d.currentFace == 0) {
				f = 1;
			} else if (d.originalFace == 1 && d.currentFace == 1) {
				f = 2;
			} else if (d.originalFace == 2 && d.currentFace == 2) {
				f = 3;
			} else if (d.originalFace == 3 && d.currentFace == 3) {
				f = 2;
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
		d.currentFace = f;
		return f;
	}

	public static DoubleDoorHandler getSingleton() {
		if (DoubleDoorHandler.singleton == null) {
			DoubleDoorHandler.singleton = new DoubleDoorHandler(
					"./Data//cfg/Double Doors.txt");
		}
		return DoubleDoorHandler.singleton;
	}

	private final List<DoubleDoorHandler> doors = new ArrayList<DoubleDoorHandler>();
	private File doorFile;
	private int doorId;
	private int originalId;
	private int open;
	private int x;
	private int y;
	private int z;
	private int originalX;
	private int originalY;

	private int currentFace;

	private int originalFace;

	private static int[] openDoors = { 1520, 1517, 1596, 1597 };

	public DoubleDoorHandler(final int id, final int x, final int y,
			final int z, final int f, final int open) {
		doorId = id;
		originalId = id;
		this.open = open;
		this.x = x;
		originalX = x;
		this.y = y;
		this.z = z;
		originalY = y;
		currentFace = f;
		originalFace = f;
	}

	private DoubleDoorHandler(final String file) {
		doorFile = new File(file);
	}

	public void changeLeftDoor(final DoubleDoorHandler d) {
		int xAdjustment = 0, yAdjustment = 0;
		if (d.open == 0) {
			if (d.originalFace == 0 && d.currentFace == 0) {
				xAdjustment = -1;
			} else if (d.originalFace == 1 && d.currentFace == 1) {
				yAdjustment = 1;
			} else if (d.originalFace == 2 && d.currentFace == 2) {
				xAdjustment = +1;
			} else if (d.originalFace == 3 && d.currentFace == 3) {
				yAdjustment = -1;
			}
		} else if (d.open == 1) {
			if (d.originalFace == 0 && d.currentFace == 0) {
				yAdjustment = -1;
			} else if (d.originalFace == 1 && d.currentFace == 1) {
				xAdjustment = -1;
			} else if (d.originalFace == 2 && d.currentFace == 2) {
				xAdjustment = -1;
			} else if (d.originalFace == 3 && d.currentFace == 3) {
				xAdjustment = -1;
			}
		}
		if (xAdjustment != 0 || yAdjustment != 0) {
			Server.objectHandler.placeObject(new Objects(-1, d.x, d.y, d.z, 0,
					0, 0));
		}
		if (d.x == d.originalX && d.y == d.originalY) {
			d.x += xAdjustment;
			d.y += yAdjustment;
		} else {
			Server.objectHandler.placeObject(new Objects(-1, d.x, d.y, d.z, 0,
					0, 0));
			d.x = d.originalX;
			d.y = d.originalY;
		}
		if (d.doorId == d.originalId) {
			if (d.open == 0) {
				d.doorId += 1;
			} else if (d.open == 1) {
				d.doorId -= 1;
			}
		} else if (d.doorId != d.originalId) {
			if (d.open == 0) {
				d.doorId = d.originalId;
			} else if (d.open == 1) {
				d.doorId = d.originalId;
			}
		}
		Server.objectHandler.placeObject(new Objects(d.doorId, d.x, d.y, d.z,
				DoubleDoorHandler.getNextLeftFace(d), 0, 0));
	}

	public void changeRightDoor(final DoubleDoorHandler d) {
		int xAdjustment = 0, yAdjustment = 0;

		if (d.open == 0) {
			if (d.originalFace == 0 && d.currentFace == 0) {
				xAdjustment = -1;
			} else if (d.originalFace == 1 && d.currentFace == 1) {
				yAdjustment = 1;
			} else if (d.originalFace == 2 && d.currentFace == 2) {
				xAdjustment = +1;
			} else if (d.originalFace == 3 && d.currentFace == 3) {
				yAdjustment = -1;
			}
		} else if (d.open == 1) {
			if (d.originalFace == 0 && d.currentFace == 0) {
				xAdjustment = 1;
			} else if (d.originalFace == 1 && d.currentFace == 1) {
				xAdjustment = -1;
			} else if (d.originalFace == 2 && d.currentFace == 2) {
				yAdjustment = -1;
			} else if (d.originalFace == 3 && d.currentFace == 3) {
				xAdjustment = -1;
			}
		}
		if (xAdjustment != 0 || yAdjustment != 0) {
			Server.objectHandler.placeObject(new Objects(-1, d.x, d.y, d.z, 0,
					0, 0));
		}
		if (d.x == d.originalX && d.y == d.originalY) {
			d.x += xAdjustment;
			d.y += yAdjustment;
		} else {
			Server.objectHandler.placeObject(new Objects(-1, d.x, d.y, d.z, 0,
					0, 0));
			d.x = d.originalX;
			d.y = d.originalY;
		}
		if (d.doorId == d.originalId) {
			if (d.open == 0) {
				d.doorId += 1;
			} else if (d.open == 1) {
				d.doorId -= 1;
			}
		} else if (d.doorId != d.originalId) {
			if (d.open == 0) {
				d.doorId = d.originalId;
			} else if (d.open == 1) {
				d.doorId = d.originalId;
			}
		}
		Server.objectHandler.placeObject(new Objects(d.doorId, d.x, d.y, d.z,
				DoubleDoorHandler.getNextRightFace(d), 0, 0));
	}

	private DoubleDoorHandler getDoor(final int id, final int x, final int y,
			final int z) {
		for (final DoubleDoorHandler d : doors) {
			if (d.doorId == id) {
				if (d.x == x && d.y == y && d.z == z) {
					return d;
				}
			}
		}
		return null;
	}

	public boolean handleDoor(final int id, final int x, final int y,
			final int z) {
		final DoubleDoorHandler doorClicked = getDoor(id, x, y, z);
		if (doorClicked == null) {
			return true;
		}
		if (doorClicked.doorId > 12000) {
			return true; // nearly all of these are not opened
		}
		if (doorClicked.open == 0) {
			if (doorClicked.originalFace == 0) {
				final DoubleDoorHandler lowerDoor = getDoor(id - 3, x, y - 1, z);
				final DoubleDoorHandler upperDoor = getDoor(id + 3, x, y + 1, z);
				if (lowerDoor != null) {
					changeLeftDoor(lowerDoor);
					changeRightDoor(doorClicked);
				} else if (upperDoor != null) {
					changeLeftDoor(doorClicked);
					changeRightDoor(upperDoor);
				}
			} else if (doorClicked.originalFace == 1) {
				final DoubleDoorHandler westDoor = getDoor(id - 3, x - 1, y, z);
				final DoubleDoorHandler eastDoor = getDoor(id + 3, x + 1, y, z);
				if (westDoor != null) {
					changeLeftDoor(westDoor);
					changeRightDoor(doorClicked);
				} else if (eastDoor != null) {
					changeLeftDoor(doorClicked);
					changeRightDoor(eastDoor);
				}
			} else if (doorClicked.originalFace == 2) {
				final DoubleDoorHandler lowerDoor = getDoor(id - 3, x, y + 1, z);
				final DoubleDoorHandler upperDoor = getDoor(id + 3, x, y - 1, z);
				if (lowerDoor != null) {
					changeLeftDoor(lowerDoor);
					changeRightDoor(doorClicked);
				} else if (upperDoor != null) {
					changeLeftDoor(doorClicked);
					changeRightDoor(upperDoor);
				}
			} else if (doorClicked.originalFace == 3) {
				final DoubleDoorHandler westDoor = getDoor(id + 3, x - 1, y, z);
				final DoubleDoorHandler eastDoor = getDoor(id - 3, x + 1, y, z);
				if (westDoor != null) {
					changeLeftDoor(westDoor);
					changeRightDoor(doorClicked);
				} else if (eastDoor != null) {
					changeLeftDoor(doorClicked);
					changeRightDoor(eastDoor);
				}
			}
		} else if (doorClicked.open == 1) {
			if (doorClicked.originalFace == 0) {
				final DoubleDoorHandler westDoor = getDoor(id - 3, x - 1, y, z);
				final DoubleDoorHandler upperDoor = getDoor(id + 3, x + 1, y, z);
				if (westDoor != null) {
					changeLeftDoor(westDoor);
					changeRightDoor(doorClicked);
				} else if (upperDoor != null) {
					changeLeftDoor(doorClicked);
					changeRightDoor(upperDoor);
				}
			} else if (doorClicked.originalFace == 1) {
				final DoubleDoorHandler northDoor = getDoor(id - 3, x, y + 1, z);
				final DoubleDoorHandler southDoor = getDoor(id + 3, x, y - 1, z);
				if (northDoor != null) {
					changeLeftDoor(northDoor);
					changeRightDoor(doorClicked);
				} else if (southDoor != null) {
					changeLeftDoor(doorClicked);
					changeRightDoor(southDoor);
				}
			} else if (doorClicked.originalFace == 2) {
				final DoubleDoorHandler westDoor = getDoor(id - 3, x - 1, y, z);
				final DoubleDoorHandler eastDoor = getDoor(id + 3, x, y - 1, z);
				if (westDoor != null) {
					changeLeftDoor(westDoor);
					changeRightDoor(doorClicked);
				} else if (eastDoor != null) {
					changeLeftDoor(doorClicked);
					changeRightDoor(eastDoor);
				}
			} else if (doorClicked.originalFace == 3) {
				final DoubleDoorHandler northDoor = getDoor(id - 3, x, y + 1, z);
				final DoubleDoorHandler southDoor = getDoor(id + 3, x, y - 1, z);
				if (northDoor != null) {
					changeLeftDoor(northDoor);
					changeRightDoor(doorClicked);
				} else if (southDoor != null) {
					changeLeftDoor(doorClicked);
					changeRightDoor(southDoor);
				}
			}
		}
		return true;
	}

	public boolean isOpenDoor(final int id) {
		for (final int openDoor : DoubleDoorHandler.openDoors) {
			if (id == openDoor || id + 3 == openDoor) {
				return true;
			}
		}
		return false;
	}

	public void load() {
		final long start = System.currentTimeMillis();
		try {
			DoubleDoorHandler.singleton.processLineByLine();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("[Stage - 5] Finished Loading: " + doors.size()
				+ " Double Doors in: " + (System.currentTimeMillis() - start)
				+ "ms.");
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
				doors.add(new DoubleDoorHandler(id, x, y, z, f,
						isOpenDoor(id) ? 1 : 0));
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
