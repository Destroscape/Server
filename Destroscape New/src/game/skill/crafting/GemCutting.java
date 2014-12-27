package game.skill.crafting;

import engine.event.CycleEvent;
import engine.event.CycleEventContainer;
import engine.event.CycleEventHandler;
import game.entity.player.Player;

public class GemCutting extends CraftingData {

	public static void cutGem(final Player c, final int itemUsed,
			final int usedWith) {
		if (c.isSkilling[12] == true) {
			return;
		}
		final int itemId = (itemUsed == 1755 ? usedWith : itemUsed);
		for (final cutGemData g : cutGemData.values()) {
			if (itemId == g.getUncut()) {
				if (c.playerLevel[12] < g.getLevel()) {
					c.sendMessage("You need a crafting level of "
							+ g.getLevel() + " to cut this gem.");
					return;
				}
				if (!c.getItems().playerHasItem(itemId)) {
					return;
				}
				c.isSkilling[12] = true;
				//c.setAnimation(Animation.create(g.getAnimation()));
				c.startAnimation(g.getAnimation());
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (c.isSkilling[12] == true) {
							if (c.getItems().playerHasItem(itemId)) {
								c.getItems().deleteItem(itemId, 1);
								c.getItems().addItem(g.getCut(), 1);
								c.getPA().addSkillXP((int) g.getXP(), 12);
								c.sendMessage("You cut the "
										+ c.getItems().getItemName(itemId)
										.toLowerCase() + ".");
								//c.setAnimation(Animation.create(g.getAnimation()));
								c.startAnimation(g.getAnimation());
							} else {
								container.stop();
							}
						} else {
							container.stop();
						}
					}

					@Override
					public void stop() {
						c.isSkilling[12] = false;
					}
				}, 4);
			}
		}
	}
}