package lilithscythemod.Skill;

import lilithscythemod.ModCore;
import lilithscythemod.Entity.EntityCustomState;
import lilithscythemod.Entity.EntityCustomStateManager;
import lilithscythemod.Entity.EntityDataManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SkillEventHook {
	private float getGCD=0;
	private int getCT	=0;
	private byte getState;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase && SkillNBTData.getNBT((EntityLivingBase) event.entity) == null) {
        	SkillNBTData.register((EntityLivingBase)event.entity);
        }
    }
	@SubscribeEvent
	public void skillUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase target = event.entityLiving;
			SkillNBTData list = SkillNBTData.get(target);
			NBTTagList skills =list.getNBTList();
			if(!event.entity.worldObj.isRemote){

			if(SkillNBTData.get(target)!=null && list.getNBTList().tagCount() > 0)
			{
				//登録されている各スキルのCT処理
				if(target.ticksExisted%20==0){

					for (int x = 0; x < skills.tagCount(); x++)
					{
						ctTimeProcess(target,list,x);
						chargeTimeProcess(target,list,x);
						//ステートによる実行処理
						stateProcess(target,list,x);
						ModCore.TestState=list.getNBTByte(x,list.Tag_State);
					}
				}
				//GCDの処理
				if (target.ticksExisted % 2 == 0){
					gcdTimeProcess(target);
				}
			}
			}


	}
	/**詠唱時間の更新処理メソッド*/
	private void chargeTimeProcess(EntityLivingBase target, SkillNBTData list,int x) {

		//ステートが詠唱なら実行
		if(list.getNBTByte(x,list.Tag_State)==list.State_Chant){
			float par1 = list.getNBTFloat(x, SkillNBTData.Tag_ChargeTime);
			if(par1>0){
				list.setNBTFloat(x, SkillNBTData.Tag_ChargeTime, par1-1.0F);
			}else{
				list.setNBTByte(x, SkillNBTData.Tag_State, SkillNBTData.State_ChantEnd);
			}
		}
	}
	/**GCDの更新処理をするメソッド*/
	private void gcdTimeProcess(EntityLivingBase entity){
			//ステートがGCDTIMEなら実行
			if(EntityCustomStateManager.getstate(entity,SkillNBTData.Tag_State)==EntityCustomState.State_GCDTime){
				double par1=EntityDataManager.getData(entity,SkillNBTData.Tag_GlobalCoolDown);
				if(par1>0){
					EntityDataManager.updateGCD(entity,SkillNBTData.Tag_GlobalCoolDown, par1-0.1D);
				}else{
					EntityCustomStateManager.updateState(entity, SkillNBTData.Tag_State, EntityCustomState.State_GCDFree);
				}

			}
	}
	/**CTの更新処理をするメソッド*/
	private void ctTimeProcess(EntityLivingBase entity,SkillNBTData list,int index){

		//ステートがクールダウン中なら実行
		if(list.getNBTByte(index,list.Tag_State) ==list.State_CoolDown){
			int par =list.getNBTInt(index, list.Tag_CoolTime);
			if(par>0){
				list.setNBTInt(index, list.Tag_CoolTime, par-1);
			}else{
				list.setNBTByte(index,list.Tag_State, list.State_Ready);
			}
		}
	}
	/**State更新メソッド*/
	private void stateProcess(EntityLivingBase entity,SkillNBTData list,int index){

		if(list.getNBTByte(index,list.Tag_State)==SkillNBTData.State_ChantEnd){
			/*ここで発動メソッドを呼ぶ*/
			String skillName = list.getNBTString(index,SkillNBTData.Tag_SkillName );
			SkillManager.getSkillList().get(skillName).invocationSkill(entity.worldObj,entity);

			list.setNBTByte(index,SkillNBTData.Tag_State,SkillNBTData.State_CoolDown);
			EntityCustomStateManager.updateState(entity, SkillNBTData.Tag_State, EntityCustomState.State_GCDTime);
		}
	}
}
