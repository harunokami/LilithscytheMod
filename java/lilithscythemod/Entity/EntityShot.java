package lilithscythemod.Entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityShot extends Entity implements IProjectile{

    //地中判定に使うもの
    protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected int inTile;
    protected int inData;
    protected boolean inGround;
    private Block field_145790_g;
    private float fallSpeed=0F;

    // この弾を撃ったエンティティ
    public Entity shootingEntity;
    public Entity targetEntity ;
    public int canBePickedUp;
    //地中・空中にいる時間
    protected int ticksInGround;
    protected int ticksInAir;

    // ダメージの大きさ
    protected double damage;
    protected DamageSource damageSource;

    // ノックバックの大きさ
    protected int knockbackStrength = 1;
    //効果を発生させた中心座標と発生した時間（チック単位）を保存する
    protected MovingObjectPosition Movingobject;
    //HitSoundRocation
    protected String entityHitSoundRocation ="";

    protected Vec3  vec3;
    protected Vec3  vec31;
	//Potionの効果時間（【20tick ≒ 1秒】なので）
    protected int duration =0;
	//PotionのLv
    protected int amplifier = 0;

    //攻撃できるかどうか
    private boolean isVillagerHit = true;
    private boolean isMobHit = true;
    private boolean isAnimalHit = false;
    private boolean isunknownHit = false;
    private boolean isPlayerHit =false;


	public EntityShot(World p_i1582_1_) {
		super(p_i1582_1_);
		 this.renderDistanceWeight = 10.0D;
	     this.setSize(1.0F, 1.0F);
	}
	 public EntityShot(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_)
	    {
	        super(p_i1754_1_);
	        this.renderDistanceWeight = 10.0D;
	        this.setSize(0.5F, 0.5F);
	        this.setPosition(p_i1754_2_, p_i1754_4_, p_i1754_6_);
	        this.yOffset = 0.0F;
	    }
	/**
	 * 敵モブがつかったりする場合の関数
	 * par2EntityLivingBase 攻撃側
	 * par3EntityLivingBase ターゲット
	 */
	public EntityShot(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float speed, float speed2,float fall)
	{

		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLivingBase;
		this.targetEntity=par3EntityLivingBase;
		this.setFallSpeed(0);
		this.damage =par2EntityLivingBase.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();

		  if ( !(par2EntityLivingBase instanceof EntityPlayer) )
	        {
			  this.setAttackHitPlayer(true);
	        }

		    this.posY = par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight() - 0.10000000149011612D;
	        double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
	        double d1 = par3EntityLivingBase.boundingBox.minY + (double)(par3EntityLivingBase.height / 3.0F) - this.posY;
	        double d2 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
	        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

	        if (d3 >0)
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

	/** 発射する弾を生成・初期パラメータの定義をする。
    * @param par1World :このワールド
    * @param par2EntityLivingBase :弾源となるエンティティ。このModの場合、弾を撃ったプレイヤーがここに入る
    * @param speed :弾の速度計算に使われる値
    * @param speed2 :弾の速度計算に使われる値2
    * @param adjustX :プレイヤーから見て水平方向に、発射する弾をずらす(複数発射するときなどに使用する)
    * @param adjustZ :プレイヤーから見て前後方向に弾をずらす
    * @param adjustY :プレイヤーから見て上下方向に弾をずらす
	* @param damage  :ヒットしたときに与えるダメージ
	* @param fallSpeed:落下速度
	 */
   public EntityShot(World par1World, EntityLivingBase par2EntityLivingBase, float speed, float speed2,
           float adjustX, float adjustZ, float adjustY,double damage,float fallSpeed)
      {

  		  super(par1World);
  		  this.renderDistanceWeight = 10.0D;
          this.shootingEntity = par2EntityLivingBase;
          this.yOffset = 0.0F;
          this.setSize(0.5F, 0.5F);
          this.damage=damage;
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


	/** 発射する弾を生成・初期パラメータの定義をする。
    * @param par1World :このワールド
    * @param par2EntityLivingBase :弾源となるエンティティ。このModの場合、弾を撃ったプレイヤーがここに入る
    * @param speed :弾の速度計算に使われる値
    * @param speed2 :弾の速度計算に使われる値2
    * @param adjustX :プレイヤーから見て水平方向に、発射する弾をずらす(複数発射するときなどに使用する)
    * @param adjustZ :プレイヤーから見て前後方向に弾をずらす
    * @param adjustY :プレイヤーから見て上下方向に弾をずらす
	* @param damage  :ヒットしたときに与えるダメージ
	* @param fallSpeed:落下速度
	* @param rotationSpeed:この分だけ角度をずらす（Yaw）
	* @param rotationPitch:この分だけ角度をずらす（Pitch）
	 */
   public EntityShot(World par1World, EntityLivingBase par2EntityLivingBase, float speed, float speed2,
           float adjustX, float adjustZ, float adjustY,double damage,float fallSpeed,float rotationYaw,float rotationPitch)
      {

  		  super(par1World);
  		  this.renderDistanceWeight = 10.0D;
          this.shootingEntity = par2EntityLivingBase;
          this.yOffset = 0.0F;
          this.setSize(0.5F, 0.5F);
          this.damage=damage;
          //初期設定
          //向き
          EntityLib.setLocation(this, par2EntityLivingBase, rotationYaw, rotationPitch);
        //位置の調整
          EntityLib.setEntityPos(this, adjustX, adjustY, adjustZ);
          setPosition(posX, posY, posZ);
        //初速度
          EntityLib.setEntityMotion(this);
          this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed , speed2);
      }


	protected void entityInit() {

	}

	public void setThrowableHeading(double p_70186_1_, double p_70186_3_,
			double p_70186_5_, float p_70186_7_, float p_70186_8_) {
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
	  @Override
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

	        //空気じゃないブロックに当たった
	        if (this.isGround())
	        {

	            int j = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

	            //前のTickに確認した埋まりブロックのIDとメタを照合している。違ったら埋まり状態を解除、一致したら埋まり状態を継続。
	            //埋まり状態2tick継続でこのエンティティを消す
	            if (this.getGroundBlock() == this.field_145790_g && j == this.inData)
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
	        else{
	        //１チック毎の処理
	            tickProcess();


	         //移動更新
	            entityupdate();
	         //消滅処理
	            deadProcess();
	            this.setPosition(this.posX, this.posY, this.posZ);
	            this.func_145775_I();
	        }

	    }



	  /**ここからは使用メソッド一覧*/

	  /**空気ブロック以外にぶつかっているかどうかを返す*/
	  protected boolean isGround(){
		   //激突したブロックを確認している
          Block block = worldObj.getBlock(this.xTile, this.yTile, this.zTile);
        //当たり判定に接触しているかどうか
          if (block.getMaterial() != Material.air)
          {
          	block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
          	AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);
          	 if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
               {
          		return true;
               }
          }else{
        	  return false;
          }

          return false;
	  }

	  /** 衝突しているブロックを返す*/
	  protected Block getGroundBlock(){
		  //激突したブロックを確認している
          Block block = worldObj.getBlock(this.xTile, this.yTile, this.zTile);
          if (block.getMaterial() != Material.air)
          {
        	  return block;
          }
		return Blocks.air;
	  }

	  /**1チック毎の処理*/
	  protected void tickProcess()
	  {
		  ++this.ticksInAir;
	      //ブロックとの衝突判定
          this.vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
          this.vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);


          MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
          vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
          vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        //ブロックに当たった
          if (movingobjectposition != null)
          {
          	vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);

          }
          hitEntityCheck(movingobjectposition);
	  }
	  protected void hitBlockCheck()
	  {

	  }

	  /**
	   * Entityと衝突したかの判定をする
	   * @param movingobjectposition =調べたいオブジェクト
	   */
	  protected void hitEntityCheck(MovingObjectPosition movingobjectposition)
		{
	         //========Entityとの衝突判定=============
          Entity entity = null;
          List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
          double d0 = 0.0D;
          int l;
          float f1;
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
              this.AttackEntityCheck(movingobjectposition);

          }

		}
	  /**当たったエンティティそれそれについての判定部分。
       * @param movingobjectposition = 判定するオブジェクト。nullにすることで特定の種類のエンティティに当たらないようにできる。
       */
	  protected void AttackEntityCheck(MovingObjectPosition movingobjectposition)
	  {

	      //当たったエンティティそれそれについての判定部分。
          //ここでMovingObjectPosition = nullにすることで特定の種類のエンティティに当たらないようにできる。
         if (movingobjectposition != null && movingobjectposition.entityHit != null)
         {
             if (movingobjectposition.entityHit == this.shootingEntity)
             {
            	 movingobjectposition=null;
             }
             else if(movingobjectposition.entityHit instanceof EntityPlayer && !this.isPlayerHit){
            	 movingobjectposition=null;
             }
             else if (movingobjectposition.entityHit instanceof EntityAnimal && (!this.isAnimalHit))
             {
            	 movingobjectposition=null;
             }
             else if (movingobjectposition.entityHit instanceof EntityVillager && !this.isVillagerHit)
             {
            	 movingobjectposition=null;
             }else if(movingobjectposition.entityHit instanceof IMob && !this.isMobHit){
            	 movingobjectposition=null;
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
            	 //当たったときの特殊処理をする
            	 entityHitSpecialEvent((EntityLivingBase)movingobjectposition.entityHit);

                 //速度が大きいほど、ダメージも大きくなる
                 int i1 = (int)this.getDamage();

                 //0~2程度の乱数値を上乗せ
                 i1 += this.rand.nextInt(3);

                 DamageSource damagesource = null;

                 //別メソッドでダメージソースを確認
                 damagesource = this.getDamageSource();

                 //バニラ矢と同様、このエンティティが燃えているなら対象に着火することも出来る
                 if (this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman))
                 {
                     movingobjectposition.entityHit.setFire(5);
                 }
                 	//ダメージを与える処理
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
                         this.playSound(entityHitSoundRocation,1, 1);

                         //当たったあと、弾を消去する。エンティティ貫通がONの弾種はそのまま残す。
                         if (!(movingobjectposition.entityHit instanceof EntityEnderman) && !this.isPenetrateEntity())
                         {
                             this.setDead();
                         }
                       }else
                        {
                     	  this.motionX *= -0.10000000149011612D;
                          this.motionY *= -0.10000000149011612D;
                          this.motionZ *= -0.10000000149011612D;
                          this.rotationYaw += 180.0F;
                          this.prevRotationYaw += 180.0F;
                          this.ticksInAir = 0;
                        }


             }else if (!this.isPenetrateBlock())  //エンティティには当たってない。ブロックに当たった。
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
    	            this.playSound(entityHitSoundRocation,1, 1);
                 this.inGround = true;

                 if (this.field_145790_g.getMaterial() != Material.air)
                 {
                     this.field_145790_g.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
                 }
             }
         }
	  }

	  /**Entityの移動処理*/
	  protected void entityupdate(){

		  float f1;
		  float f2;
		  float f3;
		  //特殊な動きを追加
          specialMotion();
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

         this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
         this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

          //重力落下
          //落下速度は別メソッドで設定している。デフォルトでは0.0F。
          f1 = this.getfallSpeed();

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
          //落下
          this.posY-=f1;
	  }

    /**
     * @param Entity又はブロックにヒットした時の音声のディレクトリを指定する
     */
    protected void setentityHitSoundRocation(String s)
    {
    	this.entityHitSoundRocation=s;
    }
    /**
     * @param Entityにヒットした時の特殊なイベントを記述する
     */
	protected void entityHitSpecialEvent(EntityLivingBase entitylivingbase)
	{

	}
	/**
	 *  @param 発射体Entityの特殊な動きを記述する
	 */
	protected void specialMotion() {
	}

/**
 * @param 消滅判定や処理をする*/
	protected void deadProcess(){
        //一定以上遅くなったら消える
        if ((this.worldObj.isRemote && this.motionX * this.motionX + this.motionZ * this.motionZ < 0.001D)||this.ticksInAir%200==0)
        {
        	this.setDead();
        }
	}

	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		    this.xTile = p_70037_1_.getShort("xTile");
	        this.yTile = p_70037_1_.getShort("yTile");
	        this.zTile = p_70037_1_.getShort("zTile");
	        this.inTile =p_70037_1_.getByte("inTile") & 255;
	        this.inData = p_70037_1_.getByte("inData") & 255;
	        this.inGround = p_70037_1_.getByte("inGround") == 1;

	        if (p_70037_1_.hasKey("pickup", 99))
	        {
	            this.canBePickedUp = p_70037_1_.getByte("pickup");

	        }else if (p_70037_1_.hasKey("player", 99))
	        {
	            this.canBePickedUp = p_70037_1_.getBoolean("player") ? 1 : 0;
	        }

	        if (p_70037_1_.hasKey("damage"))
	        {
	            this.damage = p_70037_1_.getDouble("damage");
	        }

	}

	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		p_70014_1_.setShort("xTile", (short)this.xTile);
		p_70014_1_.setShort("yTile", (short)this.yTile);
		p_70014_1_.setShort("zTile", (short)this.zTile);
		p_70014_1_.setByte("inTile", (byte)this.inTile);
		p_70014_1_.setByte("inData", (byte)this.inData);
		p_70014_1_.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		p_70014_1_.setDouble("damage", this.damage);
		p_70014_1_.setByte("pickup", (byte)this.canBePickedUp);
	}

		  // 以下、当MOD用のパラメータ定義部分
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
	    /**Mobにヒットさせるかどうか*/
	    public void setAttackHitMob(boolean par1){
	    	this.isMobHit=par1;
	    }
	    /**Animalにヒットさせるかどうか*/
	    public void setAttackHitAnimal(boolean par1){
	    	this.isAnimalHit=par1;
	    }
	    /**村人にヒットさせるかどうか*/
	    public void setAttackHitVillager(boolean par1){
	    	this.isVillagerHit=par1;
	    }
	    /**自分以外のプレイヤーにヒットさせるかどうか*/
	    public void setAttackHitPlayer(boolean par1){
	    	this.isPlayerHit=par1;
	    }
	    /**DamageSet*/
	    public void setDamage(double par1)
	    {
	        this.damage = par1;
	    }
	    /**Entityのダメージ取得*/
	    public double getDamage()
	    {
	        return this.damage;
	    }

	    /**ヒット時のノックバック強度*/
	    public void setKnockbackStrength(int par1)
	    {
	        this.knockbackStrength = par1;
	    }

	    public boolean canAttackWithItem()
	    {
	        return false;
	    }
	    /**ブロックに埋まっていない時間*/
	    public int getTickInAir(){
	    	return this.ticksInAir;
	    }
	    /**ヒットしたMovingObject*/
	    public MovingObjectPosition getMovingobject(){
	    	return  this.Movingobject;
	    }
	    /**
	     * parには継続時間/sを入力
	     */
	    public void setDuration(int par){
	    	this.duration = par * 20;
	    }
	    /**
	     * parにはポーションの強さを入力
	     */
	    public void setAmplifier(int par){
	    	this.amplifier=par;
	    }
	    public int getDuration(){
	    	return this.duration;
	    }
	    public int getAmplifier(){
	    	return this.amplifier;
	    }


	    /**落下速度*/
	    public float getfallSpeed()
	    {
	    	return this.fallSpeed;
	    }
	    public void setFallSpeed(float par1){

	    	this.fallSpeed=par1;
	    }

	    /**ダメージソースのタイプ */
	    public void setDamageSource(DamageSource par1){
	    	this.damageSource=par1;
	    }
	    public DamageSource getDamageSource(){
	    	return this.damageSource!=null ? this.damageSource : DamageSource.generic;
	    }
	    /** ブロック貫通 */
	    public boolean isPenetrateBlock()
	    {
	    	return true;
	    }

	    /**エンティティ貫通*/
	    public boolean isPenetrateEntity()
	    {
	    	return false;
	    }
}