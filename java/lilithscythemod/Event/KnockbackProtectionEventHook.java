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

public class KnockbackProtectionEventHook
{
	private double preKnockbackResistanceLv;
	AttributeModifier ResistanceLv= new AttributeModifier(ModCore.knockbackResistanceBoostUUID,"addKnockbackResistance", 0, 0);
	Multimap multimap = HashMultimap.create();
	 @SubscribeEvent
	    public void onLivingHurt(LivingUpdateEvent event) {
		 EntityLivingBase entityLivingBase = event.entityLiving;
		 double knockbackResistanceLv =0;

		 preKnockbackResistanceLv=EntityDataManager.getData(entityLivingBase, "preKnockbackResistanceLv");

		 if (entityLivingBase instanceof EntityPlayer)
		 {
			 int i = 0;
			 ItemStack ArmoritemStack[] = ((EntityPlayer)entityLivingBase).inventory.armorInventory;
			 int j = ArmoritemStack.length;
			 for (int k = 0; k < j; ++k)
		        {
		            ItemStack itemstack = ArmoritemStack[k];

		            if (itemstack != null && EnchantmentHelper.getEnchantmentLevel(ModCore.KnockbackProtection.effectId,itemstack) > 0)
		            {
		            	knockbackResistanceLv += EnchantmentHelper.getEnchantmentLevel(ModCore.KnockbackProtection.effectId,itemstack) * 2F;
		            }
		        }
		 }

			if(preKnockbackResistanceLv != knockbackResistanceLv){
				removeAttribute(entityLivingBase);
				ResistanceLv= new AttributeModifier(ModCore.knockbackResistanceBoostUUID,"addKnockbackResistance",knockbackResistanceLv, 0);
				preKnockbackResistanceLv=knockbackResistanceLv;
				saveHealth(entityLivingBase,preKnockbackResistanceLv,knockbackResistanceLv);
				multimap=getMap();
				entityLivingBase.getAttributeMap().applyAttributeModifiers(multimap);
			}

	 }

		/*@SubscribeEvent
		public void entityKnockbackProtectionCreate(EntityConstructing create)
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
	        multimap.put((SharedMonsterAttributes.knockbackResistance).getAttributeUnlocalizedName(), ResistanceLv);
	        return multimap;
	    }
		private void removeAttribute(EntityLivingBase entityLivingBase){
			entityLivingBase.getAttributeMap().removeAttributeModifiers(multimap);
			multimap.clear();
			multimap=getMap();
	 		entityLivingBase.getAttributeMap().applyAttributeModifiers(multimap);
		}

		private void saveHealth(EntityLivingBase entity,double prePar,double Par){
			EntityDataManager.EntityCustomData(entity, "preKnockbackResistanceLv", prePar);
			EntityDataManager.EntityCustomData(entity, "knockbackResistanceLv", Par);
		}

}
