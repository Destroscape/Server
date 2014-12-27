package engine.world;

public class Tiles {
	private int x = -1;

	private int y = -1;

	private int h = -1;

	private int f = -1;

	public Tiles(int x, int y) {
		setX(x);
		setY(y);
	}

	public Tiles(int x, int y, int h) {
		setX(x);
		setY(y);
		setH(h);
	}

	public Tiles(int x, int y, int h, int f) {
		setX(x);
		setY(y);
		setH(h);
		setF(f);
	}

	public int getF() {
		return f;
	}

	public int getH() {
		return h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setF(int f) {
		this.f = f;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}