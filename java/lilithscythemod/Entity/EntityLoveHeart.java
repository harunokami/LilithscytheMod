package lilithscythemod.Entity;

import java.util.List;

import lilithscythemod.EffectSoundPlay;
import lilithscythemod.ModCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityLoveHeart extends Entity implements IProjectile 
{
	  public EntityLoveHeart(World par1World)
	    {
	        super(par1World);
	        this.renderDistanceWeight = 10.0D;
	        this.setSize(0.5F, 0.5F);
	        this.damage = (double)ModCore.ViviMaterial.getDamageVsEntity()*0.72D;
	    } 
	
    //地中判定に使うもの 
    protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected int inTile;
    protected int inData;
    protected boolean inGround;
    private Block field_145790_g;
    
    // この弾を撃ったエンティティ 
    public Entity shootingEntity;
 
    //地中・空中にいる時間 
    protected int ticksInGround;
    protected int ticksInAir;
 
    // ダメージの大きさ 
    protected double damage = (double)ModCore.ViviMaterial.getDamageVsEntity()*0.72D;
 
    // ノックバックの大きさ 
    protected int knockbackStrength = 1;
    //凍結効果を発生させた中心座標と発生した時間（チック単位）を保存する
    protected double IcePosX[];
    protected double IcePosY[];
    protected double IcePosZ[];
    protected MovingObjectPosition IceSetMOP[];
    
	//Potionの効果時間（【20tick ≒ 1秒】なので）
	int duration = 20 * 20;

	//PotionのLv
	int amplifier = 20;
    
	   
	    
	  // 発射する弾を生成・初期パラメータの定義をする。
	     // @param par1World :このワールド
	     // @param par2EntityLivingBase :弾源となるエンティティ。このModの場合、弾を撃ったプレイヤーがここに入る
	     // @param speed :弾の速度計算に使われる値
	     // @param speed2 :弾の速度計算に使われる値2
	     // @param adjustX :プレイヤーから見て水平方向に、発射する弾をずらす(複数発射するときなどに使用する)
	     // @param adjustZ :プレイヤーから見て前後方向に弾をずらす
	     // @param adjustY :プレイヤーから見て上下方向に弾をずらす*/
	    public EntityLoveHeart(World par1World, EntityLivingBase par2EntityLivingBase, float speed, float speed2,
	            float adjustX, float adjustZ, float adjustY)
	       {
	       	super(par1World);
	           this.renderDistanceWeight = 10.0D;
	           this.shootingEntity = par2EntityLivingBase;
	           this.yOffset = 0.0F;
	           this.setSize(0.5F, 0.5F);
	            //初期状態での向きの決定
	            // ここのrotationYawをいじることでway弾などができる	           
	           
	           EntityLib.setLocation(this,par2EntityLivingBase);
	           //位置の調整
	           this.posX += EntityLib.setPosX(this, adjustZ, adjustX);
	           this.posY += EntityLib.setPosY(this, adjustY);
	           this.posZ += EntityLib.setPosZ(this, adjustZ, adjustX);
	           
	      
	           //初速度
	           this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 
                       MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
               this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 
                       MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
               this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
               
	           this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed * 1.5F, speed2);
	       }
	@Override
	public void setThrowableHeading(double p_70186_1_, double p_70186_3_,
			double p_70186_5_, float p_70186_7_, float p_70186_8_) 
	{
		 float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
		 p_70186_1_ /= (double)f2;
		 p_70186_3_ /= (double)f2;
		 p_70186_5_ /= (double)f2;
	     p_70186_1_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
	     p_70186_3_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
	     p_70186_5_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
	     p_70186_1_ *= (double)p_70186_7_;
	     p_70186_3_ *= (double)p_70186_7_;
	     p_70186_5_ *= (double)p_70186_7_;
	        this.motionX = p_70186_1_;
	        this.motionY = p_70186_3_;
	        this.motionZ = p_70186_5_;
	        float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
	        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
	        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);
	        this.ticksInGround = 0;
		
	}
	 @SideOnly(Side.CLIENT)
	 public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	    {
	        this.setPosition(par1, par3, par5);
	        this.setRotation(par7, par8);
	    }
	 
	  @SideOnly(Side.CLIENT)
	    
	     //* Sets the velocity to the args. Args: x, y, z
	     //速度の処理。クライアント・サーバ間での同期処理にて利用されている。
	     
	    public void setVelocity(double par1, double par3, double par5)
	    {
	        this.motionX = par1;
	        this.motionY = par3;
	        this.motionZ = par5;
	 
	        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
	        {
	            float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
	            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
	            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)f) * 180.0D / Math.PI);
	            this.prevRotationPitch = this.rotationPitch;
	            this.prevRotationYaw = this.rotationYaw;
	            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
	            this.ticksInGround = 0;
	        }
	    }
	  	    
	     // Tick毎に呼ばれる更新処理。
	     //速度の更新、衝突判定などをここで行う。
	    public void onUpdate()
	    {
	        super.onUpdate();
	 
	        //直前のパラメータと新パラメータを一致させているところ。
	        //また、速度に応じてエンティティの向きを調整し、常に進行方向に前面が向くようにしている。
	        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
	        {
	            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
	            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
	            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
	        }
	 
	        //激突したブロックを確認している
	        Block i = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
	 

                Block block = worldObj.getBlock(this.xTile, this.yTile, this.zTile);
	 
	            //当たり判定に接触しているかどうか
	            if (block.getMaterial() != Material.air)
	            {
	            	block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
	            	AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);
	            	 if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
	                 {
	                     this.inGround = true;
	                 }
	            }
	     
	 
	        //空気じゃないブロックに当たった
	        if (this.inGround)
	        {
	        	
	            int j = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
	 
	            //前のTickに確認した埋まりブロックのIDとメタを照合している。違ったら埋まり状態を解除、一致したら埋まり状態を継続。
	            //埋まり状態2tick継続でこのエンティティを消す
	            if (block == this.field_145790_g && j == this.inData)
	            {
	            	++this.ticksInGround;
	            	//ブロック貫通の場合、20tick（1秒間）はブロック中にあっても消えないようになる。
	            	int limit = this.isPenetrateBlock() ? 20 : 2;
	 
	                if (this.ticksInGround > limit)
	                {
	                    this.setDead();
	                }
	            }
	            //埋まり状態の解除処理
	            else
	            {
	                this.inGround = false;
	                this.motionX *= (double)(this.rand.nextFloat() * 0.1F);
	                this.motionY *= (double)(this.rand.nextFloat() * 0.1F);
	                this.motionZ *= (double)(this.rand.nextFloat() * 0.1F);
	                this.ticksInGround = 0;
	                this.ticksInAir = 0;
	            }
	            
	        }
	        //埋まってない時。速度の更新。
	        else
	        {
	            ++this.ticksInAir;
	            //ブロックとの衝突判定
	            Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
	            Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
	            
	            
	            MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
	            vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
	            vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
	 
	            //ブロックに当たった
	            if (movingobjectposition != null)
	            {
	            	vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
	            	
	            }
	 
	            //Entityとの衝突判定。
	            Entity entity = null;
	            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
	            double d0 = 0.0D;
	            int l;
	            float f1;
	            boolean isVillager = false;
	 
	            //1ブロック分の範囲内にいるエンティティ全てに対して繰り返す
	            for (l = 0; l < list.size(); ++l)
	            {
	                Entity entity1 = (Entity)list.get(l);
	 
	                //発射物自身or発射後5tick以外だとすりぬける
	                if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
	                {
	                    f1 = 0.3F;
	                    AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double)f1, (double)f1, (double)f1);
	                    MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);
	 
	                    if (movingobjectposition1 != null)
	                    {
	                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
	 
	                        if (d1 < d0 || d0 == 0.0D)
	                        {
	                            entity = entity1;
	                            d0 = d1;
	                        }
	                    }
	                }
	            }
	 
	            //エンティティに当たった
	            if (entity != null)
	            {
	                movingobjectposition = new MovingObjectPosition(entity);
	            }
	 
	            //当たったエンティティそれそれについての判定部分。
	             //ここでmovingobjectposition = nullにすることで特定の種類のエンティティに当たらないようにできる。
	            if (movingobjectposition != null && movingobjectposition.entityHit != null)
	            {
	                if (movingobjectposition.entityHit instanceof EntityPlayer) 
	                {
	                	//プレイヤーに当たった時
	                	EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;
	                	
	 
	                    if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && 
	                           !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
	                    {
	                    	//PvPが許可されていないと当たらない
	                        movingobjectposition = null;
	                    }
	                    else if (entityplayer == this.shootingEntity)
	                    {
	                    	//対象が撃った本人の場合も当たらない
	                    	movingobjectposition = null;
	                    }
	                }
	                else if (movingobjectposition.entityHit instanceof EntityTameable || 
	                             movingobjectposition.entityHit instanceof EntityHorse)
	                {
	                	//事故防止の為、EntityTameable（犬や猫などのペット）、馬にも当たらないようにする
	                	movingobjectposition = null;
	                }
	                else if (movingobjectposition.entityHit instanceof EntityVillager)
	                {
	                	//村人に当たった場合にフラグがtrueになる
	                	isVillager = true;
	                }
	            }
	 
	            float f2;
	            float f3;
	 
	            //当たったあとの処理
	            if (movingobjectposition != null)
	            {
	            	//エンティティに当たった
	                if (movingobjectposition.entityHit != null)
	                {
	                	//衝突時の弾の速度を計算
	                    f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
	                    
	                    //速度が大きいほど、ダメージも大きくなる
	                    int i1 = MathHelper.ceiling_double_int((double)f2 * this.damage);
	                    
	                    //0~2程度の乱数値を上乗せ
	                    i1 += this.rand.nextInt(3);
	 
	                    DamageSource damagesource = null;
	 
	                    //別メソッドでダメージソースを確認
	                    damagesource = this.thisDamageSource(this.shootingEntity);
	 
	                    //バニラ矢と同様、このエンティティが燃えているなら対象に着火することも出来る
	                    if (this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman))
	                    {
	                        movingobjectposition.entityHit.setFire(5);
	                    }
	 
	                    if (isVillager)
	                    {
	                    	//対象が村人だった場合の処理
	                    	EntityVillager villager = (EntityVillager) movingobjectposition.entityHit;
	                    	//ダメージに相当する量の回復効果をもたらす
	                    	villager.heal((float)i1);
	                    	//ただしノックバックは有る
	                    	if (this.knockbackStrength > 0)
	                        {
	                            f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
	 
	                            if (f3 > 0.0F)
	                            {
	                                movingobjectposition.entityHit.addVelocity(this.motionX * 
	                                    (double)this.knockbackStrength * 0.6000000238418579D / (double)f3, 0.1D, this.motionZ * 
	                                    (double)this.knockbackStrength * 0.6000000238418579D / (double)f3);
	                            }
	                        }
	                        else
	                        {
	                        	movingobjectposition.entityHit.hurtResistantTime = 0;
	                        }
	                    }
	                    else
	                    {
	                    	//村人以外なら、ダメージを与える処理を呼ぶ
	                    	if (movingobjectposition.entityHit.attackEntityFrom(damagesource, (float)i1))
	                        {
	                    		//ダメージを与えることに成功したら以下の処理を行う
	                            if (movingobjectposition.entityHit instanceof EntityLivingBase)
	                            {
	                   
	                    			
	                                EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.entityHit;
	                                entitylivingbase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,duration,amplifier));
	                                entitylivingbase.addPotionEffect(new PotionEffect(Potion.wither.id,duration,3));
	                                //ノックバック
	                                if (this.knockbackStrength > 0)
	                                {
	                                    f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
	 
	                                    if (f3 > 0.0F)
	                                    {
	                                        movingobjectposition.entityHit.addVelocity(this.motionX * 
	                                            (double)this.knockbackStrength * 0.6000000238418579D / (double)f3, 0.1D, this.motionZ * 
	                                            (double)this.knockbackStrength * 0.6000000238418579D / (double)f3);
	                                    }
	                                }
	                                else
	                                {
	                                	movingobjectposition.entityHit.hurtResistantTime = 0;
	                                }
	                           
	                                //ヒットしたモブの座標を取得して氷に閉じ込める
	                                for(int y=0;y<=2;y++){
	                                	for(int z=-1;z<=1;z++){
	                                		for(int x=-1;x<=1;x++){
	                                			if(this.worldObj.getBlock((int)movingobjectposition.entityHit.posX + x
	                                					                 ,(int)movingobjectposition.entityHit.posY + y
	                                					                 ,(int)movingobjectposition.entityHit.posZ + z).getMaterial()== Material.air){
	                                			this.worldObj.setBlock((int)movingobjectposition.entityHit.posX + x
	                                					              ,(int)movingobjectposition.entityHit.posY + y
	                                					              , (int)movingobjectposition.entityHit.posZ + z
	                                					              ,Blocks.ice);
	                                			
	                                			}
	                                		}
	                                	}
	                                }
	                                
	                                //マルチプレイ時に、両者がプレイヤーだった時のパケット送信処理
	                                if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && 
	                                        movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
	                                {
	                                	 ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
	                                }
	                            }
	                            //ここでヒット時の効果音がなる
	                            this.worldObj.playSoundAtEntity(entity, EffectSoundPlay.Vivi2, 1, 1);
	                           
	                            //当たったあと、弾を消去する。エンティティ貫通がONの弾種はそのまま残す。
	                            if (!(movingobjectposition.entityHit instanceof EntityEnderman) && !this.isPenetrateEntity())
	                            {
	                                this.setDead();
	                            }
	                        }
	                    }
	 
	                }
	                else if (!this.isPenetrateBlock())  //エンティティには当たってない。ブロックに当たった。
	                {
	                    this.xTile = movingobjectposition.blockX;
	                    this.yTile = movingobjectposition.blockY;
	                    this.zTile = movingobjectposition.blockZ;
	                    this.field_145790_g = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
	                    this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
	                    this.motionX = (double)((float)(movingobjectposition.hitVec.xCoord - this.posX));
	                    this.motionY = (double)((float)(movingobjectposition.hitVec.yCoord - this.posY));
	                    this.motionZ = (double)((float)(movingobjectposition.hitVec.zCoord - this.posZ));
	                    f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + 
	                             this.motionZ * this.motionZ);
	                    this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
	                    this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
	                    this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
	                    //ここでヒット時の効果音がなる
			            this.playSound("lilithscythemod:entityhit.LoveHeartHit",1, 1);
			            this.worldObj.playSoundAtEntity(this, EffectSoundPlay.Vivi2, 1, 1);
	                    this.inGround = true;
	 
	                    if (this.field_145790_g.getMaterial() != Material.air)
	                    {
	                        this.field_145790_g.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
	                    }
	                }
	            }
	 
	            //改めてポジションに速度を加算。向きも更新。
	            this.posX += this.motionX;
	            this.posY += this.motionY;
	            this.posZ += this.motionZ;
	          
	            f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
	            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
	            this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI);
	 
	            while ( this.rotationPitch - this.prevRotationPitch < -180.0F)
	            {
	            	this.prevRotationPitch -= 360.0F;
	            }
	 
	            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
	            {
	                this.prevRotationPitch += 360.0F;
	            }
	 
	            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
	            {
	                this.prevRotationYaw -= 360.0F;
	            }
	 
	            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
	            {
	                this.prevRotationYaw += 360.0F;
	            }
	 
	            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
	            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
	 
	            //徐々に減速する
	            float f4 = 1.00F;
	 
	            //重力落下
	            //落下速度は別メソッドで設定している。デフォルトでは0.0F。
	            f1 = this.fallSpeed();
	 
	            //水中に有る
	            if (this.isInWater())
	            {
	            	//泡パーティクルが出る
	                for (int j1 = 0; j1 < 4; ++j1)
	                {
	                    f3 = 0.25F;
	                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f3, this.posY - this.motionY * 
	                         (double)f3, this.posZ - this.motionZ * (double)f3, this.motionX, this.motionY, this.motionZ);
	                }
	 
	                //減速も大きくなる今回は減速しない
	                f4 = 1.0F;
	            }
	 
	            this.motionX *= (double)f4;
	            this.motionY *= (double)f4;
	            this.motionZ *= (double)f4;
	            this.motionY -= (double)f1;
	 
	            //一定以上遅くなったら消える
	            if (this.worldObj.isRemote && this.motionX * this.motionX + this.motionZ * this.motionZ < 0.001D)
	            {
	            	this.setDead();
	            }
	 
	 
	            this.setPosition(this.posX, this.posY, this.posZ);
	            this.func_145775_I();
	        }
	    }
	@Override
 //dataWatcherを利用したサーバ・クライアント間の同期処理だと思う
	protected void entityInit() {
		
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		 this.xTile = p_70037_1_.getShort("xTile");
	        this.yTile = p_70037_1_.getShort("yTile");
	        this.zTile = p_70037_1_.getShort("zTile");
	        this.inTile =p_70037_1_.getByte("inTile") & 255;
	        this.inData = p_70037_1_.getByte("inData") & 255;
	        this.inGround = p_70037_1_.getByte("inGround") == 1;
	 
	        if (p_70037_1_.hasKey("damage"))
	        {
	            this.damage = p_70037_1_.getDouble("damage");
	        }
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		p_70014_1_.setShort("xTile", (short)this.xTile);
		p_70014_1_.setShort("yTile", (short)this.yTile);
		p_70014_1_.setShort("zTile", (short)this.zTile);
		p_70014_1_.setByte("inTile", (byte)this.inTile);
		p_70014_1_.setByte("inData", (byte)this.inData);
		p_70014_1_.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		p_70014_1_.setDouble("damage", this.damage);
		
	}
	
      //プレイヤーと衝突した時のメソッド。今回は何もしない
     
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
 
    }
	
     // ブロックに対し、上を歩いたかという判定の対象になるか、というEntityクラスのメソッド。
     // 耕地を荒らしたりするのに使う。
     
    protected boolean canTriggerWalking()
    {
        return false;
    }
 
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }
 
    public void setDamage(double par1)
    {
        this.damage = par1;
    }
 
    public double getDamage()
    {
        return this.damage;
    }
 
    public void setKnockbackStrength(int par1)
    {
        this.knockbackStrength = par1;
    }
 
    public boolean canAttackWithItem()
    {
        return false;
    } 
	  // 以下、当MOD用のパラメータ定義部分
	 
    //落下速度 
    public float fallSpeed()
    {
    	return 0.0F;
    }
 
    //ダメージソースのタイプ */
    public DamageSource thisDamageSource(Entity entity)
    {
        /**発射元のEntityがnullだった場合の対策を含む。*/
    	return entity != null ? EntityDamageSource.causeIndirectMagicDamage(entity, this) : DamageSource.magic;
    }

    // ブロック貫通 
    public boolean isPenetrateBlock()
    {
    	return false;
    }
 
    //エンティティ貫通 
    public boolean isPenetrateEntity()
    {
    	return true;
    }
 
}

