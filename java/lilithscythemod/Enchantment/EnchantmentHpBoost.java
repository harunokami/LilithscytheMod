package lilithscythemod.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentHpBoost extends Enchantment{

	public EnchantmentHpBoost(int id, int weight) {
		super(id, weight,EnumEnchantmentType.armor);
	}
	public int getMaxLevel()
	{
		return 10;
	}
	public int getMinEnchantability(int par1)
	{
		return 15 + (par1 - 1) * 9;
	}
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 50;
	}

}
