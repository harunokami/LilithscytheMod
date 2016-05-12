package lilithscythemod.Skill;


import java.util.HashMap;

import lilithscythemod.Entity.EntityCustomState;
import lilithscythemod.Entity.EntityCustomStateManager;
import lilithscythemod.Entity.EntityDataManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class SkillManager {
	private static HashMap<String,Skill>skillList = new HashMap<String, Skill>();

	public static void registerSkill(String modID, Skill newSkill)
	{
		String unlocalizedPotionName = modID + ":" + newSkill.getName();
		if (!skillList.containsKey(unlocalizedPotionName))
		{
			skillList.put(unlocalizedPotionName, newSkill);
		} else if (skillList.containsKey(unlocalizedPotionName))
		{
			FMLLog.log(Level.ERROR, "[PotionEffectManager] Somehow, %s is already registered. It was not added.", newSkill.getName());
			FMLLog.log(Level.DEBUG, "[PotionEffectManager] $s's unlocalized name: %s", newSkill.getName(), unlocalizedPotionName);
		} else
		{
			FMLLog.log(Level.ERROR, "[PotionEffectManager] Unexpected Error in Registration");
		}
	}
	/**
	 * 詠唱スキルをセットするメソッド
	 * 過去に１度でもセットしたことのある場合はCTとステートのみ更新する（GCDはEntity自体に持たせる*/
	public static void applyEffect(EntityLivingBase target, String modID, String SkillName)
	{

			String unlocalizatedName = modID + ":" + SkillName;
			SkillNBTData list = SkillNBTData.getNBT(target);
		if(canSkillInvocation(target)){
			if (skillList.containsKey(unlocalizatedName))
			{
				int index = activeSkillIndex(target,unlocalizatedName);
				
						//ステートが開始可能状態ならセット処理を行う
						if(list.getNBTByte(index,SkillNBTData.Tag_State) == SkillNBTData.State_Ready||list.getNBTByte(index,SkillNBTData.Tag_State) == 0x00){
							//スキルデータがすでにEntityにセットされているなら更新、ないなら新規で追加する
							int 	ct=getSkillList().get(unlocalizatedName).getCoolTime();
							float 	charge=getSkillList().get(unlocalizatedName).getChargeTime();
							byte 	state =SkillNBTData.State_Chant;
							if(index!=-1){
									list.setNBTInt(index, SkillNBTData.Tag_CoolTime, ct);
									list.setNBTByte(index, SkillNBTData.Tag_State, state);
									list.setNBTFloat(index, SkillNBTData.Tag_ChargeTime, charge);
									EntityDataManager.EntityCustomData(target, SkillNBTData.Tag_GlobalCoolDown, getGCDTime(unlocalizatedName));
									EntityCustomStateManager.EntityCustomState(target, SkillNBTData.Tag_State, EntityCustomState.State_GCDFree);
							}else{	
									list.addSkillData(unlocalizatedName, ct, charge,state);
									EntityDataManager.EntityCustomData(target, SkillNBTData.Tag_GlobalCoolDown,getGCDTime(unlocalizatedName));
									EntityCustomStateManager.EntityCustomState(target, SkillNBTData.Tag_State,EntityCustomState.State_GCDFree);
							}

						}
						
			}else{
				
				FMLLog.log(Level.ERROR, "%s in %s does not exist!", SkillName, modID);
			}

		}

	}

	/**スキルがすでに登録済みかどうか*/
	public static Boolean activeEffects(EntityLivingBase target, String modID,String SkillName)
	{
		SkillNBTData activeSkills = SkillNBTData.getNBT(target);
		NBTTagList skillList = activeSkills.getNBTList();
		Boolean activeBool = false;

		for (int x = 0; x < skillList.tagCount(); x++)
		{
			if(activeSkills.getNBTString(x,SkillNBTData.Tag_SkillName).equals(modID +":"+ SkillName))
			{
				return true;
			}
		}
		return false;
	}
	/**スキルの登録されているリストインデックスを返す*/
	public static int activeSkillIndex(EntityLivingBase target,String SkillName){
		SkillNBTData activeSkills = SkillNBTData.getNBT(target);
		NBTTagList skillList = activeSkills.getNBTList();
		Boolean activeBool = false;

		for (int x = 0; x < skillList.tagCount(); x++)
		{
			if(activeSkills.getNBTString(x,SkillNBTData.Tag_SkillName).equals(SkillName))
			{
				return x;
			}
		}
		return -1;
	}
	/**特定のステートになっているスキル名を返す*/
	public static String getSpecificStateSkillName(EntityLivingBase target,Byte state){
		SkillNBTData activeSkills = SkillNBTData.getNBT(target);
		NBTTagList skillList = activeSkills.getNBTList();
		for (int x = 0; x < skillList.tagCount(); x++)
		{
			if(activeSkills.getNBTByte(x,SkillNBTData.Tag_State)==state){
				return activeSkills.getNBTString(x, SkillNBTData.Tag_SkillName);
			}
		}
		return null;
	}
	/**スキルを発動可能状態か否か*/
	public static Boolean canSkillInvocation(EntityLivingBase target){

		return EntityCustomStateManager.getstate(target, SkillNBTData.Tag_State)==EntityCustomState.State_GCDFree ?true:EntityDataManager.getData(target, SkillNBTData.Tag_GlobalCoolDown)<=0?true:false;

	}
	/**スキルに対応したGCDを返す*/
	public static double getGCDTime(String SkillName)
	{
		return getSkillList().get(SkillName).getGlobalCoolDown();
	}
	protected static HashMap<String, Skill> getSkillList()
	{
		return skillList;
	}
}
