package lilithscythemod.Entity;

import lilithscythemod.ModCore;
import lilithscythemod.Potion.PotionEffectManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAcidBreak extends EntityShot {

	public EntityAcidBreak(World par1World) {
		    super(par1World);
	        this.renderDistanceWeight = 10.0D;
	        this.setSize(0.5F, 0.5F);
	        this.setDamageSource(DamageSource.magic);
	        this.setAttackHitPlayer(true);
	        this.setAttackHitAnimal(true);
	}
	public EntityAcidBreak(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float speed, float speed2,float fallSpeed){
		super(par1World, par2EntityLivingBase, par3EntityLivingBase, speed, speed2, fallSpeed);
		this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
        this.setDamageSource(DamageSource.magic);
        this.setAttackHitPlayer(true);
        this.setAttackHitAnimal(true);
	}
	/** 毎tick呼ばれる処理 */
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    }
    @Override
	protected void entityHitSpecialEvent(EntityLivingBase entitylivingbase)
    {
        this.setDuration(45);
        this.setAmplifier(5);
        PotionEffectManager.applyEffect(entitylivingbase,ModCore.MODID,"BreakProtect", this.getDuration(),this.getAmplifier());
	}
    @Override
	 public void specialMotion(){

		 this.setFallSpeed(0.2F);

	 }
}

