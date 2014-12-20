package engine.world;

import java.util.Hashtable;
import java.util.Map;

public class VirtualWorld {

	public VirtualWorld() {
	}

	public static final void main(String args[]) {
		init();
	}

	@SuppressWarnings("rawtypes")
	public static final void init() {
		for (int i = 0; i < 10331; i++) {
			I[i] = null;
			I[i] = new Hashtable();
		}

		engine.world.I.Ib(true);
		for (int j = 0; j < 0x989680; j++)
			I(0, 3222, 3242, 3223, 3243, 0);

	}

	public static final boolean I(int height, int currentX, int currentY,
			int futureX, int futureY, int a) {
		return engine.world.I.Is(height, currentX, currentY, futureX, futureY,
				a);
	}

	@SuppressWarnings("rawtypes")
	public static Map I[] = new Hashtable[10331];

}
