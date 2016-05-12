package lilithscythemod.Weapons;

import lilithscythemod.EffectSoundPlay;
import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityBloodCross;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDarkNightSword  extends LilithscytheWeapons
{
	float damage;
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), 
                        new AttributeModifier(field_111210_e, "Weapon modifier", (double)damage, 0));
        return multimap;
    }	
	//アイコン
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("lilithscythemod:DarkNightSword");
	}
	//エンチャント
	@Override
	 public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {
		 if (EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId,itemStack)<=1) {
	            itemStack.addEnchantment(Enchantment.sharpness, 100);
	            itemStack.addEnchantment(Enchantment.featherFalling, 10);
	            itemStack.addEnchantment(Enchantment.respiration, 3);
	          
		 }
	    }
	//右クリックを離したときの処理
	  public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	    {
		  
		    //par4は右クリックの押下時間。
		    int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
            //右クリックを押した時間計算
		    float f = (float)j / 20.0F;
		    f = (f * f + f * 2.0F) / 3.0F;

		    //タメ時間が一定以下の場合、何も起こさず処理から抜ける。
		    if ((double)f < 0.7D)
		    {
		        return;
		    }

		    //fの上限値。
		    if (f > 1.0F)
		    {
		        f = 1.0F;
		    }
		    EntityBloodCross bullet = new EntityBloodCross(par2World, par3EntityPlayer, 0.5F, 0.3F, 0, 0, 0);		
		    if (!par2World.isRemote)
		    {	
		      par2World.spawnEntityInWorld(bullet);
		      par2World.playSoundAtEntity(par3EntityPlayer, EffectSoundPlay.BloodCross, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		    }

	    }
	    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	    	return par1ItemStack;
	    }
	   public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.block;
	    }
	     // 右クリックでの使用（タメ）時間の上限。
	    public int getMaxItemUseDuration(ItemStack par1ItemStack)
	    {
	        return 72000;
	    }
	    //右クリック時の処理
	    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	    	par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	        return par1ItemStack;
	    }
    //初期設定
	public ItemDarkNightSword(net.minecraft.item.Item.ToolMaterial par1) {
		super(par1);
		setUnlocalizedName("DarkNightSword")
        .setTextureName("lilithscythemod:DarkNightSword")
        .setCreativeTab(ModCore.LilithscytheTab);
		damage = par1.getDamageVsEntity();
		this.setMaxDamage(-1);//壊れない
	}

}