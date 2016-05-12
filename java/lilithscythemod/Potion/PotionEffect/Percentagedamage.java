package lilithscythemod.Potion.PotionEffect;
import lilithscythemod.Potion.Potion;
import net.minecraft.entity.EntityLivingBase;

public class Percentagedamage extends Potion{
	@Override
	public String getName()
	{
		return "Percentagedamage";
	}
/**
 * 毎秒最大HP*poweLevel*1%分のダメージを与える
 */
	@Override
	public void effect(EntityLivingBase target, int powerLevel)
	{
		 float damage=0;
		 float setHp=0;
		 
		damage =target.getMaxHealth()*(powerLevel*0.01F);
		setHp = target.getHealth()-damage;
		if(setHp<0)setHp=0;
		target.setHealth(setHp);
		target.worldObj.spawnParticle("smoke", target.posX, target.posY + 0.5D, target.posZ, 0.0D, 0.0D, 0.0D);
	}
	@Override
	public boolean milkRemove()
	{
		return false;
	}
}
