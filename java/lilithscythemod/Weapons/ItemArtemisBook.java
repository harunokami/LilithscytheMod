package lilithscythemod.Weapons;

import java.util.Iterator;

import lilithscythemod.ModCore;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemArtemisBook extends LilithscytheWeapons {
	//初期設定
	 public ItemArtemisBook(net.minecraft.item.Item.ToolMaterial par1){
		 super(par1);
		 setUnlocalizedName("ArtemisBook")
		 .setTextureName("lilithscythemod:ArtemisBook")
	     .setCreativeTab(ModCore.LilithscytheTab);
	     this.setMaxDamage(-1);//壊れない
	 }
	 //右クリックを溜めて離したときの処理
	    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	    {
	    	
	    	//par4は右クリックの押下時間。
		    int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		    //溜めた時間
		     float f = (float)j / 20.0F;
		     f = (f * f + f * 2.0F) / 3.0F;

		    //タメ時間が一定以下の場合、何も起こさず処理から抜ける。
		     if ((double)f < 5.0D)
		     {
		         return;
		     }

		    //fの上限値。
		     if (f > 1.0F)
		     {
		         f = 1.0F;
		     }

	    }
	    /*
	     * 右クリック時の動作のタイプ。
	     */
	    public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.bow;
	    }
	    /*
	     * 右クリックでの使用（タメ）時間の上限。
	     */
	    public int getMaxItemUseDuration(ItemStack par1ItemStack)
	    {
	        return 72000;
	    }
	    /*
	     * 右クリックでの使用時に呼ばれるメソッド。
	     */
	    public  ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	    	//Potionの効果時間（【20tick ≒ 1秒】なので）
			int duration = 30 * 20;
			//PotionのLv
			int ResistanceLv = 5;
			int Absorptionlv = 5;
			//PotionEffectの設定
			PotionEffect Registance =new PotionEffect(Potion.resistance.id , duration, ResistanceLv); 
			PotionEffect Absorption = new PotionEffect(Potion.field_76444_x.id,duration,Absorptionlv);
			//PotionEffect(Effect)がEntityPlayerに付与されているかの判定
			boolean isResistance = par3EntityPlayer.isPotionActive(Registance.getPotionID());
			boolean isAbsorption = par3EntityPlayer.isPotionActive(Absorption.getPotionID());
			//PotionEffect(Effect)がEntityPlayerに付与されていない場合
			if( !isResistance||!isAbsorption)
			{
				//Itemを振る動作
				par3EntityPlayer.swingItem();
	 
				//ダメージ値を【1】増やす
				par1ItemStack.damageItem(1, par3EntityPlayer);
	 
				//PotionEffect(Effect)をEntityPlayerに付与
				par3EntityPlayer.addPotionEffect(Registance);
				par3EntityPlayer.addPotionEffect(Absorption);
			}
			   Minecraft lmc = Minecraft.getMinecraft();
	             // バフ判定
	             EntityLiving targetEntity =null;
	             Entity lentity = null;
	              if (lmc != null && lmc.objectMouseOver != null) {
	               lentity = lmc.objectMouseOver.entityHit;
	              }
		           AxisAlignedBB var19 = par3EntityPlayer.boundingBox.expand(10.0D, 4.0D, 10.0D);
	               Iterator var20 = par3EntityPlayer.worldObj.getEntitiesWithinAABB(EntityLiving.class, var19).iterator();
	               
	               while(var20.hasNext()){
		                  targetEntity = (EntityLiving) var20.next();  
	                   if ((Entity)targetEntity instanceof IMob) continue;
	                   // 射程距離の判定、MOBの大きさを考慮
	                   double lln = 10.0D + (double)targetEntity.width;
	                   lln *= lln;
	                   if (par3EntityPlayer.getDistanceSqToEntity(targetEntity)<=lln) {
	                	   //もし対象にバフがついてなかったらバフをかける
	                	   boolean isTargetResistance = targetEntity.isPotionActive(Registance.getPotionID());
	                	   boolean isTargetAbsorption = targetEntity.isPotionActive(Absorption.getPotionID());
	                	   if(!isTargetResistance || !isTargetAbsorption){
	                	   targetEntity.addPotionEffect(new PotionEffect(Potion.resistance.id , duration, ResistanceLv));
	                	   targetEntity.addPotionEffect(new PotionEffect(Potion.field_76444_x.id,duration,Absorptionlv));
	                	   }
		                 }
		                }
			//呼ばれた時間をpar1ItemStackに格納
	    	 par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	    	return par1ItemStack;
	    }

}
