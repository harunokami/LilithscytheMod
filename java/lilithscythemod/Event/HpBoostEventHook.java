package lilithscythemod.Event;

import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityDataManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;


//TestCode
public class HpBoostEventHook {
	private double preHealth =0;
	private double HP=0;
	AttributeModifier maxHealth= new AttributeModifier(ModCore.HealthBoostUUID,"addHealth", 0, 0);
	Multimap multimap = HashMultimap.create();
	 @SubscribeEvent
	    public void onLivingHurt(LivingUpdateEvent event){
		 if(!event.entityLiving.worldObj.isRemote){
		 double setHp = 0;
		 EntityLivingBase entityLivingBase = event.entityLiving;

		 preHealth=EntityDataManager.getData(entityLivingBase, "preHealth");

		 if (entityLivingBase instanceof EntityPlayer)
		 {
			 int i = 0;
			 ItemStack ArmoritemStack[] = ((EntityPlayer)entityLivingBase).inventory.armorInventory;
			 int j = ArmoritemStack.length;

			 for (int k = 0; k < j; ++k)
		        {
		            ItemStack itemstack = ArmoritemStack[k];

		            if (itemstack != null && EnchantmentHelper.getEnchantmentLevel(ModCore.HpBoost.effectId,itemstack) > 0)
		            {
		            	setHp += EnchantmentHelper.getEnchantmentLevel(ModCore.HpBoost.effectId,itemstack) * 2F;
		            }
		        }
		 }
				if(preHealth != setHp){
					removeAttribute(entityLivingBase);
					maxHealth= new AttributeModifier(ModCore.HealthBoostUUID,"addHealth",setHp, 0);
					preHealth=setHp;
					saveHealth(entityLivingBase,preHealth,setHp);
					multimap=getMap();
					entityLivingBase.getAttributeMap().applyAttributeModifiers(multimap);
				}
				updateHealth(entityLivingBase);
		 }
	 }


		/*@SubscribeEvent
		public void entityHealthCreate(EntityConstructing create)
		{
			if (create.entity instanceof EntityLivingBase)
			{
				if(EntityCustomNBTList.getNBT((EntityLivingBase)create.entity) != null){
					removeAttribute((EntityLivingBase) create.entity);
				}
			}
		}*/

		public Multimap getMap()
	    {   HashMultimap multimap = HashMultimap.create();
	        multimap.put((SharedMonsterAttributes.maxHealth).getAttributeUnlocalizedName(), maxHealth);
	        return multimap;
	    }
		private void removeAttribute(EntityLivingBase entityLivingBase){
			if(entityLivingBase.getAttributeMap()!=null){
				entityLivingBase.getAttributeMap().removeAttributeModifiers(multimap);
			}
			multimap.clear();
			multimap=getMap();
	 		entityLivingBase.getAttributeMap().applyAttributeModifiers(multimap);
		}

		private void saveHealth(EntityLivingBase entity,double prePar,double Par){
			EntityDataManager.EntityCustomData(entity, "preHealth", prePar);
			EntityDataManager.EntityCustomData(entity, "Health", Par);
		}

		protected void updateHealth(EntityLivingBase entity){
			if(entity.getHealth()>entity.getMaxHealth()){
				entity.setHealth(entity.getMaxHealth());
			}
		}



}
