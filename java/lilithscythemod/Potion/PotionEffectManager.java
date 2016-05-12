package lilithscythemod.Potion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StringUtils;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionEffectManager {
		private static HashMap<String, Potion> potionList = new HashMap<String, Potion>();
		private static List<String> AvtivePotionlist=new ArrayList();
		
		public static void registerPotion(String modID, Potion newPotion)
		{
			String unlocalizedPotionName = modID + ":" + newPotion.getName();

			if (!potionList.containsKey(unlocalizedPotionName))
			{
				potionList.put(unlocalizedPotionName, newPotion);
			} else if (potionList.containsKey(unlocalizedPotionName))
			{
				FMLLog.log(Level.ERROR, "[PotionEffectManager] Somehow, %s is already registered. It was not added.", newPotion.getName());
				FMLLog.log(Level.DEBUG, "[PotionEffectManager] $s's unlocalized name: %s", newPotion.getName(), unlocalizedPotionName);
			} else
			{
				FMLLog.log(Level.ERROR, "[PotionEffectManager] Unexpected Error in Registration");
			}
		}

		public static void applyEffect(EntityLivingBase target, String modID, String potionName, int duration, int powerLevel)
		{
			
			String unlocalizatedName = modID + ":" + potionName;
			if (potionList.containsKey(unlocalizatedName))
			{
				PotionNBTList list = PotionNBTList.getNBT(target);
				checkEffectOverwrite(list.getNBTList(), unlocalizatedName);
				list.addPotions(unlocalizatedName, duration, powerLevel);
			} else
			{
				FMLLog.log(Level.ERROR, "%s in %s does not exist!", potionName, modID);
			}
		}

		public static void removeEffects(EntityLivingBase target, String unlocalizatedName, boolean blackList)
		{
			PotionNBTList activeEffects = PotionNBTList.getNBT(target);
			NBTTagList effectList = activeEffects.getNBTList();

			for (int x = 0; x < effectList.tagCount(); x++)
			{
				if (blackList == true)
				{
					if (!activeEffects.getNBTString(x,"name").equals(unlocalizatedName))
						effectList.removeTag(x);
				} else
				{
					if (activeEffects.getNBTString(x,"name").equals(unlocalizatedName))
						effectList.removeTag(x);
				}
			}
		}
		/**
		 * 指定したポーションがTargetに適用されているかどうかをBool値で返すメソッド
		 * @param target:調べたい対象
		 * @param modid:Potion追加のMODID（今回はlilithScytheMod)
		 * @param unlocalizatedName:ポーション名
		 * @return :boolean値
		 */
		public static Boolean activeEffects(EntityLivingBase target, String modID,String unlocalizatedName)
		{
			PotionNBTList activeEffects = PotionNBTList.getNBT(target);
			NBTTagList effectList = activeEffects.getNBTList();
			Boolean activeBool = false;
			
			for (int x = 0; x < effectList.tagCount(); x++)
			{
				if(activeEffects.getNBTString(x,"name").equals(modID +":"+ unlocalizatedName))
				{	
					return true;	
				}
			}
			
			return false;
		}
		/**
		 * 指定のポーションが対象（target)に適用されていてかつそのポーションのLvを返すメソッド
		 * @param target:調べたい対象
		 * @param unlocalizatedName:指定ポーション
		 * @param modid:Potion追加のMODID（今回はlilithScytheMod)
		 * @return int
		 */
		public static int activeEffectLv(EntityLivingBase target, String modID,String unlocalizatedName){
			int lv = 0;
			if(activeEffects(target,modID, unlocalizatedName)){
				PotionNBTList activeEffects = PotionNBTList.getNBT(target);
				NBTTagList effectList = activeEffects.getNBTList();
				for (int x = 0; x < effectList.tagCount(); x++)
				{
					if(activeEffects.getNBTString(x,"name").equals(modID+":"+unlocalizatedName)){
						lv=activeEffects.getNBTInt(x,"powerLevel");
						return lv;
					}	
				}		
				return lv;
			}else{
				return 0;
			}
		}
		/**
		 * 対象にかかっているポーション効果の名前をList型で返す*/
		public static List<String> activeEffectNameList(EntityLivingBase target){
		    List<String> PotionList = new ArrayList();
		    PotionNBTList activeEffects = PotionNBTList.getNBT(target);
			NBTTagList effectList = activeEffects.getNBTList();
			for (int x = 0; x < effectList.tagCount(); x++)
			{
				if(activeEffects.getNBTString(x,"name")!=null)
				{
					PotionList.add(activeEffects.getNBTString(x,"name"));
				}
			}
			return PotionList;
			
		}
		
		/**
		 * 
		 */
		public static List<String> getActiveEffectNameList(EntityLivingBase target){
			AvtivePotionlist = activeEffectNameList(target);
			return AvtivePotionlist;
		}
		/**
		 * 
		 * @param target
		 * @param unlocalizatedName:ここのNameはModIDなども含めたName
		 * @return
		 */
		public static int getPotionDuration(EntityLivingBase target,String unlocalizatedName){
			float duration=0;
		    PotionNBTList activeEffects = PotionNBTList.getNBT(target);
		    NBTTagList effectList = activeEffects.getNBTList();
		    for (int x = 0; x < effectList.tagCount(); x++)
			{
		    	if(activeEffects.getNBTString(x,"name").equals(unlocalizatedName)){
		    		duration = activeEffects.getNBTFloat(x,"duration");
		    		return (int)duration;
		    	}
		    	
			}
		    
		    return (int)duration;
		}
		/**
		 * 秒数を分：秒で変換しString型で返します
		 * @param duration:変換したい時間（/s）
		 * @return
		 */
		@SideOnly(Side.CLIENT)
		public static String getDurationString(int duration){
			if (duration>3600)
	        {
	            return "**:**";
	        }
	        else
	        {
	            
	            return StringUtils.ticksToElapsedTime(duration*20);
	        }
			
		}
		public static NBTTagList activeEffectNBT(EntityLivingBase target){
			 PotionNBTList activeEffects = PotionNBTList.getNBT(target);
			 NBTTagList effectList = activeEffects.getNBTList();
			return effectList;	
		}

		public static void removeEffects(EntityLivingBase target, String modID, String potionName, boolean blackList)
		{
			removeEffects(target, modID + ":" + potionName, blackList);
		}

		public static void removeAllEffects(EntityLivingBase target)
		{
			PotionNBTList activeEffects = PotionNBTList.getNBT(target);
			for (int x = 0; x < activeEffects.getNBTList().tagCount(); x++){
				activeEffects.getNBTList().removeTag(x);
			}
			
		}

		public static void checkEffectOverwrite(NBTTagList list, String potionName)
		{
			for (int x = 0; x < list.tagCount(); x++)
				if (list.getCompoundTagAt(x).getString("name").equals(potionName))
					list.removeTag(x);
		}

		protected static HashMap<String, Potion> getPotionList()
		{
			return potionList;
		}

		protected static Potion getPotion(String key)
		{
			return potionList.get(key);
		}

	}

