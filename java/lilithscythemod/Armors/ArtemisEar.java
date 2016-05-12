package lilithscythemod.Armors;

import lilithscythemod.ClientProxy;
import lilithscythemod.ItemRegistry;
import lilithscythemod.ModCore;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArtemisEar extends ItemArmor implements ISpecialArmor{

	 private ArmorProperties armorProperty = new ArmorProperties(0, 0, 0);
	 private Boolean HpCheak =false;
	 
	public ArtemisEar(ArmorMaterial p_i45325_1_, int p_i45325_2_,
		int p_i45325_3_) {
		 super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		 this.setMaxDamage(-1);//壊れない
		
		 
	}
	
	@Override
	//エンチャント付与
	 public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) 
	{
		if(itemStack.getItem() == ItemRegistry.ArtemisEar){
	        	if (EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId,itemStack)<=1) {
		            itemStack.addEnchantment(Enchantment.protection, 10);
		            itemStack.addEnchantment(ModCore.HpBoost, 10);
		            itemStack.addEnchantment(ModCore.KnockbackProtection,10);
		            itemStack.addEnchantment(Enchantment.blastProtection,10);
		            itemStack.addEnchantment(Enchantment.projectileProtection,10);
			     }
	        }
		 
	 
    }
	@Override
	   public void onArmorTick(World world, EntityPlayer player, ItemStack armor)
       {

			//Potionの効果時間（【20tick ≒ 1秒】なので）
			int duration = 1 * 20;
			int Absoptionduration = 30*20;
			//PotionのLv
			int inAbsoptionLv = 1;
			int resistanceLv = 4;
			
			//PotionEffectの設定
			PotionEffect inAbsoption = new PotionEffect(Potion.field_76444_x.id, Absoptionduration ,inAbsoptionLv);
			PotionEffect Resistance = new PotionEffect(Potion.resistance.id, duration ,resistanceLv);
			//PotionEffect(Effect)がEntityPlayerに付与されているかの判定
			boolean isinAbsoption = player.isPotionActive(inAbsoption.getPotionID());
			boolean isResistance = player.isPotionActive(Resistance.getPotionID());
			

			if(!isResistance)player.addPotionEffect(Resistance);
			if(!isinAbsoption)player.addPotionEffect(inAbsoption);
       }	   
	  
	   @SideOnly(Side.CLIENT)
	   @Override
	    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		if(itemStack.getItem() == ItemRegistry.ArtemisEar){
	        return ClientProxy.ModelArtemisEar;
	    	}else{
	    		return ClientProxy.biped;
	    	}
	    }
	@Override
	public ArmorProperties getProperties(EntityLivingBase player,
			ItemStack armor, DamageSource source, double damage, int slot) {
		 return armorProperty;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
		
	}

}
