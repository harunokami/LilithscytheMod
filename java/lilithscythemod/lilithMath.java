package lilithscythemod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;


public class lilithMath {

	static float Yaw;

	//Way弾などずらして撃つときに使う
	public static float addYaw(Entity entity,float addNum){
		
		Yaw=entity.rotationYaw+addNum;
		return Yaw;
	}

	//ラジアン変換
	public static float setrad(float degree)
	{
		float rad =  degree/180.0F*(float)Math.PI;
		return rad;
	}
	public static double distance(double value1,double value2){
		double distance = value1 - value2; 
		return  distance;
	}
	public static double twoPointDistance(double x,double y)
	{
		
		double twodistance = (double)MathHelper.sqrt_double(x*x+y*y);	
		return twodistance;
		
	}
	public static float EntityTargetYaw(double x,double y){
		
		float Yaw=(float)((Math.atan2(x, y) * 180D) / (float)Math.PI);
		return Yaw;
	}
	
    /**
     * 恐らくマルチサーバー側の当たり判定処理がおかしい。
     * 視点位置を考慮していない計算なので、標準の視線判定では足元のブロックで判定が終わる。
     */
    public static MovingObjectPosition getRayTrace(EntityPlayer pEntityLiving, double pRange, float pDelta) {
        Vec3 var4 = pEntityLiving.getPosition(pDelta);
        if (pEntityLiving.yOffset == 0.0F) {
           var4.yCoord += pEntityLiving.getEyeHeight();
        }
        Vec3 var5 = pEntityLiving.getLook(pDelta);
        Vec3 var6 = var4.addVector(var5.xCoord * pRange, var5.yCoord * pRange, var5.zCoord * pRange);
        return pEntityLiving.worldObj.rayTraceBlocks(var4, var6);
    }
	

}