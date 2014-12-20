package game.entity;

public final class Graphic {

	private final int graphicId;
	private final int graphicDelay;
	private final int graphicHeight;

	private Graphic(int graphicId, int graphicDelay, int graphicHeight) {
		this.graphicId = graphicId;
		this.graphicDelay = graphicDelay;
		this.graphicHeight = graphicHeight;
	}

	public int getGraphicId() {
		return graphicId;
	}

	public int getGraphicDelay() {
		return graphicDelay;
	}

	public int getGraphicHeight() {
		return graphicHeight;
	}

	public static Graphic create(int graphicId) {
		return create(graphicId, 0);
	}
	
	public static Graphic highGraphic(int graphicId) {
		return new Graphic(graphicId, 0, 100);
	}

	public static Graphic create(int graphicId, int graphicDelay) {
		return new Graphic(graphicId, graphicDelay, 0);
	}

	public static Graphic create(int graphicId, int graphicDelay,
			int graphicHeight) {
		return new Graphic(graphicId, graphicDelay, graphicHeight);
	}

}