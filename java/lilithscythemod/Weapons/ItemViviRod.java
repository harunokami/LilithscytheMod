package lilithscythemod.Weapons;

import lilithscythemod.EffectSoundPlay;
import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityLoveHeart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

public class ItemViviRod extends LilithscytheWeapons
{
	//public static int entityIdLoveHeart = 170;
	 float damage;
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), 
                        new AttributeModifier(field_111210_e, "Weapon modifier", (double)damage, 0));
        return multimap;
    }
	
	
	/*
     * 右クリック使用をやめた時に呼ばれるメソッド。右クリックを継続して押していた時間をもとに、エンティティを発射する処理を行う。
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
	//par4は右クリックの押下時間。
    int j = this.getMaxItemUseDuration(par1ItemStack) - par4;


    //右クリック押下時間をもとに計算。20で割り（単位を秒に変換）、なにやら二次関数的な計算式に入れている。
    //ここではバニラ弓のまま使っているが、独自の計算式でも良いと思います。
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

    EntityLoveHeart bullet = new EntityLoveHeart(par2World, par3EntityPlayer, 0.8F, 0.0F, 0.0F, 0.0F, -1.0F);
    
    par2World.playSoundAtEntity(par3EntityPlayer,EffectSoundPlay.Vivi1, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
    
    if (!par2World.isRemote)
    {
        par2World.spawnEntityInWorld(bullet);
    }

    }
    
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	return par1ItemStack;
    }
    
    /*
     * 右クリックでの使用（タメ）時間の上限。
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
    
    /*
     * 右クリック時の動作のタイプ。
     * ここではbow（引き絞るタメ動作）にしているが、ガードや飲食などに変えることも出来、呼ばれるメソッドが異なる。
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
    
    /*
     * 右クリックでの使用時に呼ばれるメソッド。
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
 
        return par1ItemStack;
    }
    
   // @SideOnly(Side.CLIENT)
    /*
     * 右クリックでタメている時のアイコンを変えられる。今回は特に変えていない。
     */
   /* public Icon getItemIconForUseDuration(int par1)
    {
        return ItemviviRod;
    }*/
 
   // @SideOnly(Side.CLIENT)
	//アイテムアイコンのエフェクト
    /*
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItemDamage() > 0;
    }
    */
	 public ItemViviRod(net.minecraft.item.Item.ToolMaterial par1)
	 {
	        super(par1);
	        //ヴィヴィロッドの登録 
	        setUnlocalizedName("ViviRod")
	       .setTextureName("lilithscythemod:ViviRod")
	        .setCreativeTab(ModCore.LilithscytheTab);
	        damage = par1.getDamageVsEntity();

   }

}
