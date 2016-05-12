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

public class LilithArmorWing extends ItemArmor implements ISpecialArmor{

    private ArmorProperties armorProperty = new ArmorProperties(0, 0, 0);


	public LilithArmorWing(ArmorMaterial p_i45325_1_, int p_i45325_2_,int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	    this.setMaxDamage(-1);//壊れない
	    this.setTextureName("lilithscythemod:lilithWingIcon");
	}

	@Override
	//エンチャント付与
	 public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld)
	{

		if(itemStack.getItem() == ItemRegistry.Lilithplate3){
	        	if (EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId,itemStack)<=1 ) {
		            itemStack.addEnchantment(Enchantment.sharpness, 10);
			     }
	        	if(EnchantmentHelper.getEnchantmentLevel(ModCore.HpBoost.effectId, itemStack)<1){
	        		itemStack.addEnchantment(ModCore.HpBoost,5);
	        	}

		}
    }


     //ポーション効果付与
	   public void onArmorTick(World world, EntityPlayer player, ItemStack armor)
    {
		//Potionの効果時間（【20tick ≒ 1秒】なので）
				int duration = 1 * 20;
				int PowerLv = 10;
				int inAbsoptionLv = 10;
				int fireResistanceLv =5;
				int saturationLv = 5;

				//PotionEffectの設定
				PotionEffect power = new PotionEffect(Potion.damageBoost.id, duration, PowerLv);
				PotionEffect inAbsoption = new PotionEffect(Potion.field_76444_x.id, duration , inAbsoptionLv);
				PotionEffect fireResistance = new PotionEffect(Potion.fireResistance.id, duration, fireResistanceLv);
				PotionEffect saturation = new PotionEffect(Potion.field_76443_y.id, duration, saturationLv);
				//PotionEffect(Effect)がEntityPlayerに付与されているかの判定
				boolean ispower = player.isPotionActive(power.getPotionID());
				boolean isinAbsoption = player.isPotionActive(inAbsoption.getPotionID());
				boolean isfireResistance = player.isPotionActive(fireResistance.getPotionID());
				boolean issaturation = player.isPotionActive(saturation.getPotionID());
				if(!ispower || !isinAbsoption || !isfireResistance|| !issaturation)
				{

				     player.addPotionEffect(power);
				     player.addPotionEffect(fireResistance);

				}

    }
	   @Override
	    @SideOnly(Side.CLIENT)
	    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		   if(itemStack.getItem() == ItemRegistry.Lilithplate3){
	        return ClientProxy.ModelArmorLilithWing;
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



