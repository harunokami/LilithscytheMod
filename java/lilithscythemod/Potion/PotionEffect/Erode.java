package lilithscythemod.Potion.PotionEffect;
import lilithscythemod.ModCore;
import lilithscythemod.Potion.Potion;
import lilithscythemod.Potion.PotionEffectManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
public class Erode extends Potion{

	@Override
	public String getName() {
		
		return "Erode";
	}
/**
 * powerLevel1あがる毎に最大HPの１％を消失させる。
 */
	@Override
	public void effect(EntityLivingBase target, int powerLevel) {
		 //Erode
		 if( PotionEffectManager.activeEffects(target,ModCore.MODID,"Erode") )
		 {
			    double ErodeHP =0;
				double HP=0;
		         int EffectLv = PotionEffectManager.activeEffectLv(target, ModCore.MODID,"Erode");
			    
		         //上限は９９％消失
			     if(EffectLv>98){EffectLv=98;}
			     
			     HP = target.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue() ;
			     ErodeHP =HP*((100D-EffectLv)/100D);
			    //Erode
			     if(target.getHealth()>ErodeHP){
			    	 target.setHealth((float)ErodeHP);
			     }   
		 }
	}
	@Override
	public boolean milkRemove()
	{
		return false;
	}

}
