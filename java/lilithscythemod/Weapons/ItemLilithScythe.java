package lilithscythemod.Weapons;

import java.util.Iterator;
import java.util.Random;

import lilithscythemod.ModCore;
import lilithscythemod.Potion.PotionEffectManager;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;


public class ItemLilithScythe  extends LilithscytheWeapons 
{

	//初期設定
    public ItemLilithScythe(net.minecraft.item.Item.ToolMaterial par1)
    {
        super(par1);
        //リリスサイズの登録
         setUnlocalizedName("Lilithscythe")
        .setTextureName("lilithscythemod:Lilithscythe")
        .setCreativeTab(ModCore.LilithscytheTab);
        this.setMaxDamage(-1);//壊れない

    }
    private EntityLiving targetEntity =null;
  //Randomクラスのインスタンス化
    Random rnd = new Random();
	//機能

    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
    	//Potionの効果時間（【20tick ≒ 1秒】なので）
		int duration = 20 * 20;
		//PotionのLv
		int amplifier = 20;
    	 ((EntityLivingBase) p_77644_2_).addPotionEffect(new PotionEffect(Potion.wither.id,duration,amplifier));
         PotionEffectManager.applyEffect((EntityLivingBase)p_77644_2_, ModCore.MODID, "BreakProtect", duration, amplifier);
		return true;

    }
	@Override
	 public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {
		 if (EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId,itemStack)<=1) {
	            itemStack.addEnchantment(Enchantment.looting, 5);
	            itemStack.addEnchantment(Enchantment.featherFalling, 10);
	            itemStack.addEnchantment(Enchantment.respiration, 3);
		 }
		 rnd = new Random();
		 if(entity instanceof EntityPlayer){
			 if( ((EntityPlayer) entity).getItemInUseCount()>0 && ((EntityPlayer) entity).getItemInUse().getItem() instanceof ItemLilithScythe){
				//Randomクラスのインスタンス化
		         Random rnd = new Random();
		         for (int l = 0; l < 10; ++l)
		         {
		             double d0 = (double)((float)entity.posX + rnd.nextFloat()-0.1F);
		             double d1 = (double)((float)entity.posY + rnd.nextFloat()-0.1F);
		             double d2 = (double)((float)entity.posZ + rnd.nextFloat()-0.1F);
		             double d3 = 0.0D;
		             double d4 = 0.0D;
		             double d5 = 0.0D;
		             int i1 = rnd.nextInt(2) * 2 - 1;
		             d3 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;
		             d4 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;
		             d5 = ((double)rnd.nextFloat() - 0.5D) * 0.5D;

		                 world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		         }
			 }
		 }

	    }
    /**
     * 視点位置を考慮していない計算なので、標準の視線判定では足元のブロックで判定が終わる。
     */
    public MovingObjectPosition getRayTrace(EntityLiving pEntityLiving, double pRange, float pDelta) {
        Vec3 var4 = pEntityLiving.getPosition(pDelta);
        if (pEntityLiving.yOffset == 0.0F) {
           var4.yCoord += pEntityLiving.getEyeHeight();
        }
        Vec3 var5 = pEntityLiving.getLook(pDelta);
        Vec3 var6 = var4.addVector(var5.xCoord * pRange, var5.yCoord * pRange, var5.zCoord * pRange);
        return pEntityLiving.worldObj.rayTraceBlocks(var4, var6);
    }
    /**
     * 左クリックの処理
     */
	    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer par3EntityPlayer, Entity par1entity)
	    {           	
           return super.onLeftClickEntity(stack, par3EntityPlayer, par1entity);
	    }


	    /**
	     * 右クリック使用をやめた時に呼ばれるメソッド。右クリックを継続して押していた時間をもとに、エンティティを発射する処理を行う。
	     */
	    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	    {
	     boolean HitFromEntity = false;
		//par4は右クリックの押下時間。
	    int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

	    //右クリック押下時間をもとに計算。20で割り（単位を秒に変換）、なにやら二次関数的な計算式に入れている。
	    //ここではバニラ弓のまま使っているが、独自の計算式でも良いと思います。
	     float f = (float)j / 20.0F;
	     f = (f * f + f * 2.0F) / 3.0F;

	    //タメ時間が一定以下の場合、何も起こさず処理から抜ける。
	     if ((double)f < 3.0D)
	     {
	         return;
	     }

	    //fの上限値。
	     if (f > 1.0F)
	     {
	         f = 1.0F;
	     }


	    //もしサーバー上でしか存在しない場合はワールドで発生させる。
	     if (!par2World.isRemote)
	     {

	                Minecraft lmc = Minecraft.getMinecraft();
	                    // 攻撃判定
	                   Entity lentity = null;
	             if (lmc != null && lmc.objectMouseOver != null) {
	                 lentity = lmc.objectMouseOver.entityHit;
	             }
	    	    AxisAlignedBB var19 = par3EntityPlayer.boundingBox.expand(10.0D, 4.0D, 10.0D);
	            Iterator var20 = par3EntityPlayer.worldObj.getEntitiesWithinAABB(EntityLiving.class, var19).iterator();

	            while(var20.hasNext()){
	                this.targetEntity = (EntityLiving) var20.next();
                    if ((Entity)this.targetEntity instanceof EntityPlayer) continue;
                    if(!((Entity)this.targetEntity instanceof IMob)) continue;
                    // 射程距離の判定、MOBの大きさを考慮
                    double lln = 10.0D + (double)targetEntity.width;
                    lln *= lln;
                    if (par3EntityPlayer.getDistanceSqToEntity(targetEntity)<=lln) {
                          // 範囲攻撃の対象
                        double lvx = targetEntity.posX - par3EntityPlayer.posX;
                        double lvz = par3EntityPlayer.posZ - targetEntity.posZ;
                        float lyaw = (float)Math.toDegrees(Math.atan2(lvx, lvz));
                        float lf = par3EntityPlayer.rotationYaw - lyaw;
                        for (;lf > 360F; lf -= 360);
                        for (;lf < 0F; lf += 360);
                        // 左230dig - 正面180deg - 右100dig
                       if (lf > 120F && lf < 240F) {
                           // 攻撃判定
                              this.targetEntity.attackEntityFrom(DamageSource.generic,ModCore.LilithMaterial.getDamageVsEntity());
                              par3EntityPlayer.attackTargetEntityWithCurrentItem(targetEntity);
                       }
	                 }
	                }

	                if(this.targetEntity!=null){HitFromEntity=true;}
	        }
	     }

	    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	    	return par1ItemStack;
	    }

	    /**
	     * 右クリックでの使用（タメ）時間の上限。
	     */
	    public int getMaxItemUseDuration(ItemStack par1ItemStack)
	    {
	        return 72000;
	    }
	    /**
	     * 右クリック時の動作のタイプ。
	     */
	    public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.bow;
	    }

	    /**
	     * 右クリックでの使用時に呼ばれるメソッド。
	     */
	    public  ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {

			//Potionの効果時間（【20tick ≒ 1秒】なので）
			int duration = 30 * 20;

			//PotionのLv
			int SpeedLv = 4;
			int JumpLv = 2;

			//PotionEffectの設定
			PotionEffect Speed = new PotionEffect(Potion.moveSpeed.id, duration, SpeedLv);
			PotionEffect Jump= new PotionEffect(Potion.jump.id, duration, JumpLv);
			PotionEffect FireResistance=new PotionEffect(Potion.fireResistance.id,duration,1);

			//PotionEffect(Effect)がEntityPlayerに付与されているかの判定
			boolean isMoveSpeed = par3EntityPlayer.isPotionActive(Speed.getPotionID());
			boolean isJump = par3EntityPlayer.isPotionActive(Jump.getPotionID());

			//PotionEffect(Effect)がEntityPlayerに付与されていない場合
			if( !isMoveSpeed||!isJump)
			{
				//Itemを振る動作
				par3EntityPlayer.swingItem();

				//ダメージ値を【1】増やす
				par1ItemStack.damageItem(1, par3EntityPlayer);

				//PotionEffect(Effect)をEntityPlayerに付与
				par3EntityPlayer.addPotionEffect(Speed);
				par3EntityPlayer.addPotionEffect(Jump);
				par3EntityPlayer.addPotionEffect(FireResistance);
			}
			//呼ばれた時間をpar1ItemStackに格納
	    	 par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		     return par1ItemStack;
	    }

		public float getExtendedReach(ItemStack itemstack) {
			return 7;
		}


}


