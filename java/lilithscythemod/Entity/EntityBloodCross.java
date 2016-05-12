package lilithscythemod.Entity;

import java.util.Iterator;
import java.util.List;

import lilithscythemod.ModCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBloodCross extends Entity implements IProjectile{
   //コンストラクタ
	public EntityBloodCross(World par1World){
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
    } 
	//変数宣言
	//ダメージ量
	protected double damage=ModCore.DarkNightMaterial.getDamageVsEntity();
	// この弾を撃ったエンティティ 
	private Entity shootingEntity;
	//目標になるエンティティ
    private EntityLiving targetEntity =null;
    public int homing_count;
    public double par1,par3,par5;
    float f1;
    //ヒットしてないかどうかのフラグ
    public static boolean isNoHit = true;
    //ターゲット
    public static boolean isAllMobTarget = true;
    public static Entity pretarget;
    
    //モブが撃ったか自分が撃ったかの判定
    public boolean isAttackMob =false;
    public int canBePickedUp;
	 //地中判定に使うもの 
    protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected int inTile;
    protected int inData;
    protected boolean inGround;
    //地中・空中にいる時間 
    protected int ticksInGround;
    protected int ticksInAir;
    private Block field_145790_g;
	private int knockbackStrength;
	
	
    public EntityBloodCross(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float speed, float speed2)
    {
        super(par1World);
        this.setDamage(damage);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLivingBase;

        if (par2EntityLivingBase instanceof EntityPlayer)
        {
            this.canBePickedUp = 1;
        }else if(par3EntityLivingBase instanceof EntityPlayer){
        	
        	this.isAttackMob=true;
        }
        this.posY = par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight() - 0.10000000149011612D;
        double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
        double d1 = par3EntityLivingBase.boundingBox.minY + (double)(par3EntityLivingBase.height / 3.0F) - this.posY;
        double d2 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(par2EntityLivingBase.posX + d4, this.posY, par2EntityLivingBase.posZ + d5, f2, f3);
            this.yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + (double)f4, d2, speed, speed2);
        }
    }
	public EntityBloodCross(World par1World, EntityLivingBase par2EntityLivingBase,float speed, float speed2,
            float adjustX, float adjustZ, float adjustY) 
	{
		super(par1World);
		this.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLivingBase;
        this.yOffset = 0.0F;
        this.setSize(0.5F, 0.5F);
        //初期設定
        //向き
        EntityLib.setLocation(this,par2EntityLivingBase);
      //位置の調整
        EntityLib.setEntityPos(this, adjustX, adjustY, adjustZ);
        setPosition(posX, posY, posZ);
      //初速度
        EntityLib.setEntityMotion(this);  
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed , speed2);
		
	}


	@Override
	public void setThrowableHeading(double p_70186_1_, double p_70186_3_,double p_70186_5_, float p_70186_7_, float p_70186_8_)
	{
		    EntityLib.EntityThrowableHeading(this, p_70186_1_, p_70186_3_, p_70186_5_, p_70186_7_, p_70186_8_);
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
	
	        //========目標までの位置を調整して追尾する=========
 
	        AxisAlignedBB var19 = this.boundingBox.expand(6.0D, 4.0D, 6.0D);
            Iterator var20 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var19).iterator();
            while(var20.hasNext()){
                this.targetEntity = (EntityLiving) var20.next();
             }
            if((!((Entity)this.targetEntity instanceof EntityPlayer))&&!(this.targetEntity== null)&&!(homing_count==1)){
                double angleXZ;
                double angleY;
                float speed = 0.08F;
                angleXZ = Math.atan2(this.targetEntity.posZ-this.posZ,targetEntity.posX-this.posX);
            //homing_target.posY - this.posYだけだと敵の足元を狙ってしまうので、homing_target.height * 0.7を足して敵の真ん中から少し上をを狙う。                      
                angleY = Math.atan(targetEntity.posY - this.posY + targetEntity.height * 0.5);
            //追尾している矢が地面に近づいたら（矢の下０．７ブロックしたBlockがあったら）下に進まないように設定
            //この設定を入れないと、ほとんどの矢が敵に当たる前に地面に刺ささってしまいます
                Block i2 = worldObj.getBlock((int)posX-1,(int)(posY-0.5),(int)posZ-1);
                if(i2.getMaterial() != Material.air && this.motionY<0)
                {
                   motionY=0;
                }
                //motionX.Y.Z/1.2の数値を下げると慣性が弱まるので敵に当たり易くなります
                motionX = motionX/1.2 + Math.cos(angleXZ) * speed;
                motionY = motionY/1.2 + Math.sin(angleY) * speed;
                motionZ = motionZ/1.2 + Math.sin(angleXZ) * speed;
                posX += motionX;
                posY += motionY;
                posZ += motionZ;
                targetEntity = null;
               }
            //ここで矢の角度を進行方向に合わせます
            prevRotationYaw = rotationYaw = (float)((Math.atan2(par1, par5) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(par3, f1) * 180D) / Math.PI);
            //=====ここまで追尾処理===========
         
	        //直前のパラメータと新パラメータを一致させているところ。
	        //また、速度に応じてエンティティの向きを調整し、常に進行方向に前面が向くようにしている。
	        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
	        {
	            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
	            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
	            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
	        }
	 
	        //激突したブロックを確認している
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
	 
	            //========Entityとの衝突判定=============
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
	 
	            //========当たったあとの処理=============
	            if (movingobjectposition != null)
	            {
	            	//エンティティに当たった
	                if (movingobjectposition.entityHit != null)
	                {
	                	
	                    
	                    //速度が大きいほど、ダメージも大きくなる
	                    int i1 = (int)this.getDamage();
	                    
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
	 
	                                //マルチプレイ時に、両者がプレイヤーだった時のパケット送信処理
	                                if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && 
	                                        movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
	                                {
	                                	 ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
	                                }
	                            }
	 
	                            //ここでヒット時の効果音がなる
	                            this.playSound("lilithscythemod:entityhit.LoveHeartHit",1, 1);

	                            //当たったあと、弾を消去する。エンティティ貫通がONの弾種はそのまま残す。
	                            if (!(movingobjectposition.entityHit instanceof EntityEnderman) && !this.isPenetrateEntity()||this.ticksInAir>200)
	                            {
	                                this.setDead();
	                            }
	                        }else{
	                        	this.motionX *= -0.10000000149011612D;
	                            this.motionY *= -0.10000000149011612D;
	                            this.motionZ *= -0.10000000149011612D;
	                            this.rotationYaw += 180.0F;
	                            this.prevRotationYaw += 180.0F;
	                            this.ticksInAir = 0;
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
	                    this.inGround = true;
	 
	                    if (this.field_145790_g.getMaterial() != Material.air)
	                    {
	                        this.field_145790_g.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
	                    }
	                }
	            }
	            	
	            
	            	
	            
	            //改めてポジションに速度を加算。向きも更新。
	            EntityLib.updatePos(this, this.motionX, this.motionY, this.motionZ);  
	            f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
	            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
	            this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI);
	            for (rotationPitch = (float)((Math.atan2(motionY, f2) * 180D) / Math.PI); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
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
	           /* for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
	            for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
	            for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }*/
	 
	           this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
	           this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
	 
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

	            }
	            //一定以上遅くなったら消える
	            if ((this.worldObj.isRemote && this.motionX * this.motionX + this.motionZ * this.motionZ < 0.001D)||this.ticksInAir%200==0)
	            {
	            	this.setDead();
	            }

	            this.setPosition(this.posX, this.posY, this.posZ);
	            this.func_145775_I();
	        }
     }
	      
		     
	    
	@Override
	protected void entityInit() {
	
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

//NBT読み書き
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
    public int getTickInAir(){
    	return this.ticksInAir;
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
    	return entity != null ? EntityDamageSource.causeIndirectMagicDamage(entity, this) : DamageSource.outOfWorld;
    }

    // ブロック貫通 
    public boolean isPenetrateBlock()
    {
    	return true;
    }
 
    //エンティティ貫通 
    public boolean isPenetrateEntity()
    {
    	return false;
    }
}
