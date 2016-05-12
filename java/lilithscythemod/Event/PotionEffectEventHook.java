package lilithscythemod.Event;

import lilithscythemod.ModCore;
import lilithscythemod.Potion.PotionEffectManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionEffectEventHook {

	/**PotionEffectLocations*/
    public static ResourceLocation sample   = new ResourceLocation(ModCore.MODTEXTUREDOMAIN,"textures/items/ViviRodCore.png");
    public static ResourceLocation sample2   = new ResourceLocation("/gui/trap.png");

    /**Event*/
    
	@SubscribeEvent
	 public void onLivingUpdate(LivingUpdateEvent event) {
		
		 EntityLivingBase entityLivingBase = event.entityLiving;
			//BreakProtect
			if( PotionEffectManager.activeEffects(entityLivingBase,ModCore.MODID,"BreakProtect") )
			{
					//防御デバフなので、防御バフを打ち消す
					if(entityLivingBase.isPotionActive(Potion.resistance)){
						entityLivingBase.removePotionEffect(Potion.resistance.id);
					}
					if(entityLivingBase.isPotionActive(Potion.field_76444_x)){
						entityLivingBase.removePotionEffect(Potion.field_76444_x.id);
					}
			}
		 
		 
	}
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event)
    {
		EntityLivingBase entityLivingBase = event.entityLiving;
		if(event.ammount > 0)
        {
			//BreakProtect
			if( PotionEffectManager.activeEffects(entityLivingBase,ModCore.MODID,"BreakProtect") )
			{
				int EffectLv=PotionEffectManager.activeEffectLv(entityLivingBase, ModCore.MODID,"BreakProtect");
				event.ammount = (float) (event.ammount * (1+(EffectLv*0.2)));
			}
        }
    }
	
	@SubscribeEvent
	public void onHeal(LivingHealEvent event){
		EntityLivingBase entityLivingBase = event.entityLiving;
		//Erode
		 if( PotionEffectManager.activeEffects(entityLivingBase,ModCore.MODID,"Erode") )
		 {
			 	double ErodeHP =0;
				 double HP=0;
		         int EffectLv = PotionEffectManager.activeEffectLv(entityLivingBase, ModCore.MODID,"Erode");
			    
		         //上限は９９％消失
			     if(EffectLv>98){EffectLv=98;}
			     
			     HP = entityLivingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue() ;
			     ErodeHP =HP*((100D-EffectLv)/100D);
			    //Erode
			     if(entityLivingBase.getHealth()>=ErodeHP){
			    	 event.setCanceled(true);
			     }   
		 }
	}
	@SubscribeEvent
	public void toBeAttacked(LivingAttackEvent event){
		EntityLivingBase entityLivingBase = event.entityLiving;
		
		
	}
	
}
