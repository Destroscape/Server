package game.entity;

import game.entity.UpdateFlags.UpdateFlag;

public abstract class Entity {

	/**
	 * The entity interacting with the current entity
	 */
	private Entity interactingEntity;

	/**
	 * Creates an instance of the update flags for every entity
	 */
	private UpdateFlags updateFlags = new UpdateFlags();

	private Animation animation;
	
	private Graphic graphic;

	/**
	 * Returns the entity interacting with the current entity
	 * 
	 * @return
	 */
	public Entity getInteractingEntity() {
		return interactingEntity;
	}

	/**
	 * Sets the interacting entity
	 * 
	 * @param entity
	 */
	public void setInteractingEntity(Entity entity) {
		this.interactingEntity = entity;
	}
	
	/**
	 * The mobs index.
	 */
	private int index = -1;

	/**
	 * Sets the index.
	 * @param index 
	 * 			The index to set.
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gets the index.
	 * @return The index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Returns the update flags for every entity
	 * 
	 * @return
	 */
	public UpdateFlags getUpdateFlags() {
		return updateFlags;
	}

	public Animation getAnimation() {
		return animation;
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
		getUpdateFlags().flag(UpdateFlag.ANIMATION);
	}
	
	public Graphic getGraphic() {
		return graphic;
	}
	
	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
		getUpdateFlags().flag(UpdateFlag.GRAPHICS);
	}

	public abstract void process();

}