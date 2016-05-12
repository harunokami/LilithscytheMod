package lilithscythemod.Armors;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;

public class ItemArmorBase extends ItemArmor{

	private static final int[] damageReductionAmountArray = null;
	public ItemArmorBase(ArmorMaterial p_i45325_1_, int p_i45325_2_,
			int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}
	  public void setDamageReductionAmount(EntityLivingBase entity,int p_78044_1_,int downProtectNum)
      {
		 int protectNum;
		 protectNum = this.damageReductionAmountArray[p_78044_1_];
		 protectNum -= downProtectNum;
		 this.damageReductionAmountArray[p_78044_1_] = protectNum;
		 
      }

}
