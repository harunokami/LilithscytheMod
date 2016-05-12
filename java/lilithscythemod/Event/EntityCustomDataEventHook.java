package lilithscythemod.Event;

import lilithscythemod.Entity.EntityCustomNBTList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityCustomDataEventHook {


	@SubscribeEvent(priority = EventPriority.HIGH)
	public void entityCustomDataCreate(EntityConstructing create)
	{
		if (create.entity instanceof EntityLivingBase && EntityCustomNBTList.getNBT((EntityLivingBase) create.entity) == null)
		{
			EntityCustomNBTList.register((EntityLivingBase) create.entity);
		}
	}
	@SubscribeEvent
	public void entityDeathEffect(LivingDeathEvent event){

	}
}
