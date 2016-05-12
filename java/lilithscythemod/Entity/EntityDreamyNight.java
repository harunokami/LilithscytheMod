package lilithscythemod.Entity;

import java.util.Iterator;

import lilithscythemod.ModCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityDreamyNight  extends EntityShot 
{
	
	  // ダメージの大きさ 
      protected static  double damage = (double)ModCore.LilithMaterial.getDamageVsEntity()*1.5D;
  	//目標になるエンティティ
      private EntityLiving targetEntity =null;
      public int homing_count;
      public double par1,par3,par5;
      float f1;
      //ヒットしてないかどうかのフラグ
      public static boolean isNoHit = true;
      //モブターゲット
      public static boolean isAllMobTarget = true;
      public static Entity pretarget;
      
	  public EntityDreamyNight(World par1World)
	    {
	        super(par1World);
	        this.renderDistanceWeight = 10.0D;
	        this.setSize(0.5F, 0.5F);
	        this.damage = (double)ModCore.LilithMaterial.getDamageVsEntity()*1.5D;
	        
	    }
	    public EntityDreamyNight(World par1World, EntityLivingBase par2EntityLivingBase, float speed, float speed2,
	            float adjustX, float adjustZ, float adjustY,float fallspeed)
	    {
	    	super(par1World,par2EntityLivingBase,speed,speed2,adjustX,adjustZ,adjustY,damage,fallspeed);
	    }
	    public EntityDreamyNight(World par1World, EntityLivingBase par2EntityLivingBase, float speed, float speed2,
	            float adjustX, float adjustZ, float adjustY,double damage,float fallSpeed,float rotationYaw,float rotationPitch)
	    {
	    	super(par1World,par2EntityLivingBase,speed,speed2,adjustX,adjustZ,adjustY,damage,fallSpeed,rotationYaw,rotationPitch);
	    }
	    public EntityDreamyNight(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float speed, float speed2,float fallSpeed){
			super(par1World, par3EntityLivingBase, par3EntityLivingBase, speed, speed2, fallSpeed);
		}
	    @Override
	    public void onUpdate()
	    {
	    	super.onUpdate();
	    }
	    @Override
	    public void specialMotion(){
	    	   //========目標までの位置を調整して追尾する=========
	           AxisAlignedBB var19 = this.boundingBox.expand(6.0D, 4.0D, 6.0D);
	           Iterator var20 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var19).iterator();
	           while(var20.hasNext()){
	            this.targetEntity = (EntityLiving) var20.next();
	           }
	           if(!((Entity)this.targetEntity instanceof EntityPlayer)&&!(this.targetEntity== null)&&!(homing_count==1)){
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
	        
	    }


	
}
