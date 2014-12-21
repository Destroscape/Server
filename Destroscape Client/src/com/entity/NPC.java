package com.entity;

import com.Class36;
import com.Model;
import com.animation.Animation;
import com.gfx.SpotAnim;

public final class NPC extends Entity {

	private Model method450() {
		if (super.anim >= 0 && super.anInt1529 == 0) {
			int k = Animation.anims[super.anim].frames[super.anInt1527];
			int i1 = -1;
			if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511)
				i1 = Animation.anims[super.anInt1517].frames[super.anInt1518];
			return desc.method164(i1, k,
					Animation.anims[super.anim].anIntArray357);
		}
		int l = -1;
		if (super.anInt1517 >= 0)
			l = Animation.anims[super.anInt1517].frames[super.anInt1518];
		return desc.method164(-1, l, null);
	}

	@Override
	public Model getRotatedModel() {
		if (desc == null)
			return null;
		Model model = method450();
		if (model == null)
			return null;
		super.height = model.modelHeight;
		if (super.anInt1520 != -1 && super.anInt1521 != -1) {
			SpotAnim spotAnim = SpotAnim.cache[super.anInt1520];
			Model model_1 = spotAnim.getModel();
			if (model_1 != null) {
				int j = spotAnim.aAnimation_407.frames[super.anInt1521];
				Model model_2 = new Model(true, Class36.method532(j), false,
						model_1);
				model_2.method475(0, -super.anInt1524, 0);
				model_2.method469();
				model_2.method470(j);
				model_2.anIntArrayArray1658 = null;
				model_2.anIntArrayArray1657 = null;
				if (spotAnim.anInt410 != 128 || spotAnim.anInt411 != 128)
					model_2.method478(spotAnim.anInt410, spotAnim.anInt410,
							spotAnim.anInt411);
				model_2.method479(frontLight, backLight, rightLight,
						middleLight, leftLight, true);
				Model aModel[] = { model, model_2 };
				model = new Model(aModel);
			}
		}
		if (desc.aByte68 == 1)
			model.aBoolean1659 = true;
		return model;
	}

	public int frontLight = 68;
	public int backLight = 820;
	public int rightLight = 0;
	public int middleLight = -1; // Cannot be 0
	public int leftLight = 0;

	@Override
	public boolean isVisible() {
		return desc != null;
	}

	public NPC() {
	}

	public EntityDef desc;
}
