package lilithscythemod.Weapons;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class LilithscytheWeapons extends ItemSword{

	public LilithscytheWeapons(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}
	public float getExtendedReach(ItemStack itemstack) {
		return 4;
	}

}
