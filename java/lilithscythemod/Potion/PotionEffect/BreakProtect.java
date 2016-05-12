package lilithscythemod.Potion.PotionEffect;

import lilithscythemod.Entity.EntityCustomNBTList;
import lilithscythemod.Entity.EntityDataManager;
import lilithscythemod.Potion.Potion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BreakProtect extends Potion{

	AttributeModifier setAttackDamage;
	Multimap multimap;
	private double defaultAttackDamage=0;
	private double preAttackDamage=0;
	private double Attackdamage=0;

	@Override
	public String getName()
	{
		return "BreakProtect";
	}

	@Override
	public void effect(EntityLivingBase target, int powerLevel)
	{
		if(EntityDataManager.activeData(target, "preAttackDamage")){
			preAttackDamage=EntityDataManager.getData(target,"preAttackDamage");
		 }
		if(EntityDataManager.activeData(target, "defaultAttackDamage")){
			defaultAttackDamage=EntityDataManager.getData(target, "defaultAttackDamage");
		}else{
			//Entityに攻撃力が設定されている場合だけ攻撃値を下げる
			if(target.getEntityAttribute(SharedMonsterAttributes.attackDamage)!=null)defaultAttackDamage=target.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		}
		Attackdamage = defaultAttackDamage*(1-(powerLevel/100));

		if(preAttackDamage!=Attackdamage){
			preAttackDamage=Attackdamage;
			setAttackDamage= new AttributeModifier("setAttackDamage", Attackdamage, 0);
			multimap=getMap();
			this.saveAttackDamage(target, preAttackDamage, defaultAttackDamage);
			target.getAttributeMap().applyAttributeModifiers(multimap);
	 		multimap.clear();
		}


	}
	@Override
	public boolean milkRemove()
	{
		return false;
	}

	public Multimap getMap()
    {
        HashMultimap multimap = HashMultimap.create();
        multimap.put((SharedMonsterAttributes.attackDamage).getAttributeUnlocalizedName(), setAttackDamage);
        return multimap;
    }
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void entityCreate(EntityConstructing create)
	{
		if (create.entity instanceof EntityLivingBase)
		{
			if(EntityCustomNBTList.getNBT((EntityLivingBase)create.entity) == null){
				EntityCustomNBTList.register((EntityLivingBase) create.entity);
			}
		}
	}

	private void saveAttackDamage(EntityLivingBase entity,double prePar,double Par){
		EntityDataManager.EntityCustomData(entity, "preAttackDamage", prePar);
		EntityDataManager.EntityCustomData(entity, "defaultAttackDamage", Par);
	}
}
