package lilithscythemod.Entity;

import lilithscythemod.ModCore;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class MoonOfChain extends EntityShot{
	// ダメージの大きさ 
    protected static  double damage = (double)ModCore.ArtemisMaterial.getDamageVsEntity()*1.2D;

	 public MoonOfChain(World par1World, EntityLivingBase par2EntityLivingBase,
			float speed, float speed2, float adjustX, float adjustZ,
			float adjustY, double damage,float fallspeed) {
		super(par1World, par2EntityLivingBase, speed, speed2, adjustX, adjustZ,adjustY, damage,fallspeed);
	}
	 @Override
	public void entityHitSpecialEvent(EntityLivingBase entitylivingbase) 
	 {
		 this.setDuration(20);
		 this.setAmplifier(5);
		 lilithscythemod.Potion.PotionEffectManager.applyEffect(entitylivingbase,"LilithscytheMod","Erode",this.duration,this.amplifier);

	 }
	 @Override
	 public void specialMotion(){
		 this.motionY*=Math.sin(this.ticksInAir);  
	 }
}
