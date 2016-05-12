package lilithscythemod.Entity;

import lilithscythemod.lilithMath;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class EntityLib {

	private static double PosX;
	private static double PosY;
	private static double PosZ;
//初期位置設定
public static void setEntityPos(Entity entity,float adjustX,float adjustY,float adjustZ){
	entity.posX +=setPosX(entity, adjustZ, adjustX);
	entity.posY +=setPosY(entity, adjustY);
	entity.posZ +=setPosZ(entity, adjustZ, adjustX);
}
//初速度を指定
public static void setEntityMotion(Entity entity){
    entity.motionX = (double)(-MathHelper.sin(entity.rotationYaw / 180.0F * (float)Math.PI) * 
            MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI));
    entity.motionZ = (double)(MathHelper.cos(entity.rotationYaw / 180.0F * (float)Math.PI) * 
            MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI));
    entity.motionY = (double)(-MathHelper.sin(entity.rotationPitch / 180.0F * (float)Math.PI));
}
//初期状態の向きを決定（なにもずらさない用）
public static void setLocation(Entity entity,EntityLivingBase base)
{
	entity.setLocationAndAngles(base.posX, base.posY + 
               (double)base.getEyeHeight(), base.posZ,
               base.rotationYaw , base.rotationPitch );
}


//初期状態の向きを決定(複数弾ずらして撃つ用）
public static void setLocation(Entity entity,EntityLivingBase base,float addYaw,float addPitch)
{
	entity.setLocationAndAngles(base.posX, base.posY + 
               (double)base.getEyeHeight(), base.posZ,
               base.rotationYaw + addYaw, base.rotationPitch + addPitch);
	
}

//SetPosX
public static double setPosX(Entity entity,float adjustZ,float adjustX){
	PosX = -(double)(MathHelper.sin( lilithMath.setrad(entity.rotationYaw) ) * (1.0F + adjustZ))
       		- (double)(MathHelper.cos( lilithMath.setrad(entity.rotationYaw) ) * adjustX);
	
	return PosX;

}
//SetPosY
public static double setPosY(Entity entity, float adjustY){
	PosY = 0.10000000149011612D + adjustY;
	return PosY;
}
//SetPosZ
public static double setPosZ(Entity entity,float adjustZ,float adjustX){
	PosZ = (double)(MathHelper.cos( lilithMath.setrad(entity.rotationYaw) ) * (1.0F + adjustZ))
		- (double)(MathHelper.sin( lilithMath.setrad(entity.rotationYaw) ) * adjustX);
	return PosZ;
}

/**
 * 指定した値を速度にセット
 * @param entity
 * @param x
 * @param y
 * @param z
 */
public static void setEntityMotion(Entity entity, double x,double y,double z){
	entity.motionX = x;
	entity.motionY = y;
	entity.motionZ = z;
	
}
/**
 * Entityの速度、角度などを設定する
 * @param entity
 * @param p_70186_1_
 * @param p_70186_3_
 * @param p_70186_5_
 * @param p_70186_7_
 * @param p_70186_8_
 */
public static void EntityThrowableHeading(Entity entity,double p_70186_1_, double p_70186_3_,double p_70186_5_, float p_70186_7_, float p_70186_8_){
	float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
	 p_70186_1_ /= (double)f2;
	 p_70186_3_ /= (double)f2;
	 p_70186_5_ /= (double)f2;
     p_70186_1_ *= (double)p_70186_7_;
     p_70186_3_ *= (double)p_70186_7_;
     p_70186_5_ *= (double)p_70186_7_;
       entity.motionX = p_70186_1_;
       entity.motionY = p_70186_3_;
       entity.motionZ = p_70186_5_;
       float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
       entity.prevRotationYaw = entity.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
       entity.prevRotationPitch = entity.rotationPitch = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);
       
}

/**
 * ブロックにあたっているかどうか
 * @param entity
 * @param xTile
 * @param yTile
 * @param zTile
 * @return
 */
public static Boolean getBoundBlock(Entity entity,int xTile,int yTile,int zTile){
	Boolean bool = null;
	 //激突したブロックを確認している
     Block block = entity.worldObj.getBlock(xTile, yTile, zTile);
     //当たり判定に接触しているかどうか
     if (block.getMaterial() != Material.air){
    	 block.setBlockBoundsBasedOnState(entity.worldObj, xTile, yTile, zTile);
     	AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(entity.worldObj, xTile, yTile, zTile);
     	 if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ)))
         {
           return true;
         }
    	 
     }
	    return bool;
	
}
/**
 * 位置更新メソッド
 * @param entity
 * @param addX
 * @param addY
 * @param addZ
 */
public static void updatePos(Entity entity,double addX,double addY, double addZ){
	entity.posX += addX;
	entity.posY += addY;
	entity.posZ += addZ;
}


/*日本語版 */
/**
* すべての弾の基礎となる生成処理	Create Shots Base of All
* @param user          : 弾の持ち主、当たった場合持ち主が当てたことになる。また、持ち主が死ぬと消滅したり、アイテム化する
* @param source        : 弾の発射元。あまり意味は無い
* @param pos           : 弾の発射地点
* @param angle         : 弾の進行方向
* @param slope		 : 弾の進行方向を軸とした傾き。度数で指定（180にすると弾が上下ひっくり返る）
* @param rotate        : 弾が角度を変えるときに利用する軸のベクトル（回転軸）
* @param rotationSpeed : 弾が角度を変える速度（毎フレーム指定した角度（度数）で向きを変える）。回転の軸はrotateVectorX,Y,Z
* @param rotationEnd	 : 弾の角速度を0にする時間。特に終わらせないなら9999あたりを入れる
* @param firstSpeed    : 弾の初速度
* @param limitSpeed    : 弾の限界速度。これ以上は速く（遅く）ならない
* @param acceleration  : 弾の加速度。毎フレームこの数値分速く（遅く）なる
* @param gravity       : 弾のX軸方向の重力値。0.0Dで重力の影響を受けない
* @param shot			 : 弾の動作以外のデータ
* @return 生成したEntityShotを返す
*/



	
}
