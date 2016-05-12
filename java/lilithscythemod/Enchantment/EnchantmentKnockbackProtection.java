package lilithscythemod.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentKnockbackProtection extends Enchantment{

	public EnchantmentKnockbackProtection(int id, int weight) {
		super(id, weight,EnumEnchantmentType.armor);
	}
	public int getMaxLevel()
	{
		return 10;
	}
	public int getMinEnchantability(int par1)
	{
		return 25 + par1;
	}
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 50;
	}

}
