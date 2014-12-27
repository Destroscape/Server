package game.entity;

public class Animation {

	/**
	 * The animation id to play
	 */
	private final int animationId;

	/**
	 * The delay before playing the animation
	 */
	private final int animationDelay;

	/**
	 * Creates a new instance of the animation with a delay
	 * 
	 * @param animationId
	 * @param animationDelay
	 */
	public Animation(int animationId, int animationDelay) {
		this.animationId = animationId;
		this.animationDelay = animationDelay;
	}

	public static Animation create(int animationId) {
		return new Animation(animationId, 0);
	}

	public static Animation create(int animationId, int delay) {
		return new Animation(animationId, delay);
	}

	/**
	 * Returns the animations id
	 * 
	 * @return
	 */
	public int getAnimationId() {
		return animationId;
	}

	/**
	 * Returns the animations Delay
	 * 
	 * @return
	 */
	public int getAnimationDelay() {
		return animationDelay;
	}

}
